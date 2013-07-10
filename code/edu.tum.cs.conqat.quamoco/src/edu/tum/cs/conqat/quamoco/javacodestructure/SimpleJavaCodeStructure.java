/*-------------------------------------------------------------------------+
|                                                                          |
| Copyright 2012 Technische Universitaet Muenchen and                      |
| Fraunhofer-Institut fuer Experimentelles Software Engineering (IESE)     |
|                                                                          |
| Licensed under the Apache License, Version 2.0 (the "License");          |
| you may not use this file except in compliance with the License.         |
| You may obtain a copy of the License at                                  |
|                                                                          |
|    http://www.apache.org/licenses/LICENSE-2.0                            |
|                                                                          |
| Unless required by applicable law or agreed to in writing, software      |
| distributed under the License is distributed on an "AS IS" BASIS,        |
| WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. |
| See the License for the specific language governing permissions and      |
| limitations under the License.                                           |
|                                                                          |
+-------------------------------------------------------------------------*/

package edu.tum.cs.conqat.quamoco.javacodestructure;

import java.util.HashMap;
import java.util.List;

import net.sourceforge.pmd.ast.ASTAnnotationTypeDeclaration;
import net.sourceforge.pmd.ast.ASTBlock;
import net.sourceforge.pmd.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.ast.ASTCompilationUnit;
import net.sourceforge.pmd.ast.ASTConstructorDeclaration;
import net.sourceforge.pmd.ast.ASTEnumDeclaration;
import net.sourceforge.pmd.ast.ASTFormalParameter;
import net.sourceforge.pmd.ast.ASTInitializer;
import net.sourceforge.pmd.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.ast.ASTMethodDeclarator;
import net.sourceforge.pmd.ast.ASTVariableInitializer;
import net.sourceforge.pmd.ast.JavaParserVisitorAdapter;
import net.sourceforge.pmd.ast.SimpleJavaNode;

import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.java.base.JavaAnalyzerBase;
import org.conqat.engine.java.library.JavaLibrary;
import org.conqat.engine.java.resource.IJavaElement;

import edu.tum.cs.conqat.quamoco.javacodestructure.data.JavaClass;
import edu.tum.cs.conqat.quamoco.javacodestructure.data.JavaClassBodyElement;
import edu.tum.cs.conqat.quamoco.javacodestructure.data.JavaFile;
import edu.tum.cs.conqat.quamoco.javacodestructure.data.JavaInitializer;
import edu.tum.cs.conqat.quamoco.javacodestructure.data.JavaMethod;


@AConQATProcessor(description = "This processor create a data structure describing the method of a class.")
public class SimpleJavaCodeStructure extends JavaAnalyzerBase {

	public static final String OUTPUTKEY = "Java Code Structure";
	public static final String NUMBEROFMETHODS = "#Methods";
	public static final String NUMBEROFCLASSES = "#Classes";

	public static final String LOC = "LoC";
	public static final String AVERAGEMETHODLENGTH = "AvgMethodLength";

	/** for debug messages */
	private String currentLocation = null;

	public HashMap<SimpleJavaNode, JavaClass> nodeToClass = new HashMap<SimpleJavaNode, JavaClass>();

	/** the main class declared in a java file */
	private JavaFile currentJavaFile;

	/**
	 * Return all keys
	 */
	@Override
	protected String[] getKeys() {
		return new String[] { OUTPUTKEY, NUMBEROFMETHODS, AVERAGEMETHODLENGTH,NUMBEROFCLASSES,
				LOC };
	}

	/**
	 * Analyze the java element
	 */
	@Override
	protected void analyze(IJavaElement javaElement,
			org.apache.bcel.classfile.JavaClass clazz) throws ConQATException {

		currentLocation = javaElement.getLocation();

		currentJavaFile = new JavaFile();
		currentJavaFile.setPath(javaElement.getLocation());

		ASTCompilationUnit ast = JavaLibrary.getInstance().getAST(javaElement);
		currentJavaFile.setStartLine(ast.getBeginLine());
		currentJavaFile.setEndLine(ast.getEndLine());

//				 try {
//				 TransformerFactory tFactory = TransformerFactory.newInstance();
//				 Transformer transformer = tFactory.newTransformer();
//				
//				 Document doc = ast.asXml();
//				 DOMSource source = new DOMSource(doc);
//				 FileOutputStream out = new FileOutputStream("c:/tmp/ast.xml");
//				 try {
//				 StreamResult result = new StreamResult(out);
//				 transformer.transform(source, result);
//				 } finally {
//				 out.close();
//				 }
//				
//				 } catch (Exception e) {
//				 e.printStackTrace();
//				 }
				
		try {
			ast.childrenAccept(new ClassParser(), javaElement);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new ConQATException("Error on parsing java file '"
					+ javaElement.getLocation() + "': " + e);
		}

		javaElement.setValue(OUTPUTKEY, currentJavaFile);

		int numberOfMethods = 0;
		{
			for (JavaClass javaClass : currentJavaFile.getJavaClasses()) {
				for (JavaClassBodyElement bodyElement : javaClass
						.getBodyElements()) {
					if (bodyElement instanceof JavaMethod) {
						numberOfMethods++;
					}
				}
			}
		}

		double avgMethodLength = 0;
		{
			for (JavaClass javaClass : currentJavaFile.getJavaClasses()) {
				for (JavaClassBodyElement bodyElement : javaClass
						.getBodyElements()) {
					if (bodyElement instanceof JavaMethod) {
						avgMethodLength += bodyElement.getLengthInLoc();
					}
				}
			}
			if (numberOfMethods > 0) {
				avgMethodLength /= numberOfMethods;
			} else {
				avgMethodLength = 0;
			}
		}

		javaElement.setValue(NUMBEROFMETHODS, numberOfMethods);
		javaElement.setValue(AVERAGEMETHODLENGTH, avgMethodLength);
		javaElement.setValue(LOC, currentJavaFile.getLengthInLoc());
		javaElement.setValue(NUMBEROFCLASSES, currentJavaFile.getJavaClasses().size());
	}

	/**
	 * Search for methods within a class
	 * 
	 * @author lochmann
	 * 
	 */
	private class ClassParser extends JavaParserVisitorAdapter {

		/**
		 * Process a class declaration
		 */
		@Override
		public Object visit(ASTClassOrInterfaceDeclaration node, Object data) {
			createJavaClass(node, data);
			return super.visit(node, data);
		}

		@Override
		public Object visit(ASTAnnotationTypeDeclaration node, Object data) {
			createJavaClass(node, data);
			return super.visit(node, data);
		}

		/**
		 * Create a class
		 * 
		 * @param node
		 * @param data
		 */
		private void createJavaClass(SimpleJavaNode node, Object data) {
			SimpleJavaNode parentClass = getParent(node);
			if (parentClass == null) {
				JavaClass currentClass = new JavaClass(currentJavaFile);

				nodeToClass.put(node, currentClass);

				currentJavaFile.addJavaClass(currentClass);
				currentClass.setName(((IJavaElement) data).getClassName());

				currentClass.setStartLine(node.getBeginLine());
				currentClass.setEndLine(node.getEndLine());
			}
		}

		@Override
		public Object visit(ASTEnumDeclaration node, Object data) {
			createJavaClass(node, data);
			return super.visit(node, data);
		}

		/**
		 * Process a method declaration
		 */
		@Override
		public Object visit(ASTMethodDeclaration node, Object data) {

			ASTMethodDeclarator declarator = node
					.getFirstChildOfType(ASTMethodDeclarator.class);
			List<ASTFormalParameter> params = declarator
					.findChildrenOfType(ASTFormalParameter.class);

			createJavaMethod(node.getMethodName(), node.isAbstract(),
					params.size(), node);

			return super.visit(node, data);
		}

		@Override
		public Object visit(ASTInitializer node, Object data) {

			createJavaInitializer(node);

			return super.visit(node, data);
		}

		@Override
		public Object visit(ASTVariableInitializer node, Object data) {
			createJavaInitializer(node);
			return super.visit(node, data);
		}

		private void createJavaInitializer(SimpleJavaNode node) {

			JavaClass clazz = getContainment(node);

			JavaInitializer method = new JavaInitializer(clazz);

			method.setStartLine(node.getBeginLine());
			method.setEndLine(node.getEndLine());
			clazz.addBodyElement(method);
		}

		/**
		 * Creates a java method
		 * 
		 * @param name
		 * @param node
		 */
		private void createJavaMethod(String name, boolean isAbstract,
				int numberOfParams, SimpleJavaNode node) {

			JavaClass clazz = getContainment(node);

			JavaMethod method = new JavaMethod(clazz, name);

			int maxBlockNesting = getMaxBlockNesting(node);

			method.setNumberOfParametes(numberOfParams);
			method.setAbstractMethod(isAbstract);
			method.setMaxBlockNestingDepth(maxBlockNesting);
			method.setStartLine(node.getBeginLine());
			method.setEndLine(node.getEndLine());
			clazz.addBodyElement(method);
		}

		private SimpleJavaNode getParent(SimpleJavaNode node) {
			ASTEnumDeclaration parentEnum = node
					.getFirstParentOfType(ASTEnumDeclaration.class);

			if (parentEnum != null) {
				return parentEnum;
			}

			ASTClassOrInterfaceDeclaration parentClass = node
					.getFirstParentOfType(ASTClassOrInterfaceDeclaration.class);
			if (parentClass != null) {
				return parentClass;
			}

			ASTAnnotationTypeDeclaration parentAnno = node
					.getFirstParentOfType(ASTAnnotationTypeDeclaration.class);
			if (parentAnno != null) {
				return parentAnno;
			}

			return null;

		}

		private JavaClass getContainment(SimpleJavaNode node) {
			SimpleJavaNode parentClass = getParent(node);

			if (parentClass == null) {
				throw new RuntimeException("method in line "
						+ node.getBeginLine() + " in file " + currentLocation
						+ " without class node found.");
			}

			SimpleJavaNode parentOfParentClass = getParent(parentClass);
			while (parentOfParentClass != null) {
				parentClass = parentOfParentClass;
				parentOfParentClass = getParent(parentClass);
			}

			JavaClass clazz = nodeToClass.get(parentClass);

			if (clazz == null) {
				throw new RuntimeException("method without JavaClass found.");
			}
			return clazz;
		}

		/**
		 * Process a constructor declaration
		 */
		@Override
		public Object visit(ASTConstructorDeclaration node, Object data) {

			//ASTMethodDeclarator declarator = node
			//		.getFirstChildOfType(ASTMethodDeclarator.class);			
			List<ASTFormalParameter> params = node
					.findChildrenOfType(ASTFormalParameter.class);

			createJavaMethod("Constructor", false, params.size(), node);
			return super.visit(node, data);
		}

	}

	public int getMaxBlockNesting(SimpleJavaNode node) {
		BlockNestingDeptParser parser = new BlockNestingDeptParser();
		node.childrenAccept(parser, node);
		return parser.maxDepth;
	}

	private class BlockNestingDeptParser extends JavaParserVisitorAdapter {
		private int maxDepth = 0;

		@Override
		public Object visit(ASTBlock node, Object data) {
			int depth = 0;
			ASTBlock block = node;
			do {
				depth++;
				block = block.getFirstParentOfType(ASTBlock.class);
			} while (block != null);
			this.maxDepth = Math.max(this.maxDepth, depth);
			return super.visit(node, data);
		}
	}
}
