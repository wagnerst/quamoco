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

package de.quamoco.qm.incubator;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class Doc {
	
	static {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.createResource(URI.createFileURI("../de.quamoco.qm/model/qm.ecore"));
		resource.load(null);
		
		EPackage ePackage = (EPackage) resource.getContents().get(0);
		
		System.out.println("\\begin{description}");
		
		for(EClassifier classifier : ePackage.getEClassifiers()) {
			String doc = EcoreUtil.getDocumentation(classifier);
			System.out.println("\\item[" + classifier.getName() + ".]");
			System.out.println(doc);
			System.out.println();
			
			if(classifier instanceof EClass) {
				EClass eClass = (EClass) classifier;
				if(!eClass.getEStructuralFeatures().isEmpty()) {
					System.out.println("\\begin{itemize}");
					for(EStructuralFeature feature : eClass.getEStructuralFeatures()) {
						System.out.println("\\item \\textit{" + feature.getName() + "}:");
						System.out.println(EcoreUtil.getDocumentation(feature));
					}
					System.out.println("\\end{itemize}");
					System.out.println();
				}
			}
			else if(classifier instanceof EEnum) {
				EEnum eEnum = (EEnum) classifier;
				System.out.println("\\begin{itemize}");
				for(EEnumLiteral literal : eEnum.getELiterals()) {
					System.out.println("\\item \\textit{" + literal.getName() + "}:");
					System.out.println(EcoreUtil.getDocumentation(literal));
				}
				System.out.println("\\end{itemize}");
				System.out.println();
			}
		}
		
		System.out.println("\\end{description}");
	}

}
