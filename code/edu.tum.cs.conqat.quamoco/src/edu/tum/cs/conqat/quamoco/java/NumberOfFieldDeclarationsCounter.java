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

/*-------------------------------------------------------------------------+
|                                                                          |
| Copyright 2005-2011 The ConQAT Project                                   |
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
+-------------------------------------------------------------------------*/
package edu.tum.cs.conqat.quamoco.java;

import net.sourceforge.pmd.ast.ASTCompilationUnit;
import net.sourceforge.pmd.ast.ASTFieldDeclaration;

import org.apache.bcel.classfile.JavaClass;
import org.conqat.engine.core.core.AConQATKey;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.java.base.JavaAnalyzerBase;
import org.conqat.engine.java.library.JavaLibrary;
import org.conqat.engine.java.resource.IJavaElement;

/**
 * {@ConQAT.Doc}
 * 
 * @author hummelb
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating GREEN Hash: 384481A52E07F85BFB9C27B50BB2CD98
 */
@AConQATProcessor(description = "Calculates the number of field declarations for each class.")
public class NumberOfFieldDeclarationsCounter extends JavaAnalyzerBase {

	/** Key for messages. */
	@AConQATKey(description = "Number of Field Declarations in Class", type = "java.lang.Integer")
	public static final String FIELD_COUNT_KEY = "#FieldDeclarations";

	/** {@inheritDoc} */
	@Override
	protected void analyze(IJavaElement classElement, JavaClass clazz) {
		ASTCompilationUnit ast;
		try {
			ast = JavaLibrary.getInstance().getAST(classElement);
		} catch (ConQATException e) {
			getLogger().error("Could not get AST for " + classElement.getId(),
					e);
			return;
		}

		classElement.setValue(FIELD_COUNT_KEY,
				ast.findChildrenOfType(ASTFieldDeclaration.class).size());
	}

	/** {@inheritDoc} */
	@Override
	protected String[] getKeys() {
		return new String[] { FIELD_COUNT_KEY };
	}
}