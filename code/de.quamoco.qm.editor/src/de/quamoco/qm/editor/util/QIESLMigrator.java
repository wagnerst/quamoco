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

package de.quamoco.qm.editor.util;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edapt.common.ResourceUtils;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.QIESLEvaluation;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;

public class QIESLMigrator {

	private static String migrate(String qiesl) {
		StringBuffer result = new StringBuffer();

		int linDistIndex = qiesl.indexOf("linearDistribution");
		if (linDistIndex == -1) {
			return qiesl;
		}
		int bracketOpenIndex = qiesl.indexOf("(", linDistIndex);
		int openBrackets = 1;

		int pos = bracketOpenIndex + 1;
		while (openBrackets != 0) {
			if (qiesl.charAt(pos) == '(') {
				openBrackets++;
			} else if (qiesl.charAt(pos) == ')') {
				openBrackets--;
			}
			pos++;
		}

		int commas = 0;
		while (commas < 3) {
			pos--;
			if (qiesl.charAt(pos) == ',') {
				commas++;
			}
		}
		int firstStart = pos + 1;
		int firstEnd = qiesl.indexOf(",", pos + 1);
		double value = Double.parseDouble(qiesl.substring(firstStart, firstEnd)
				.trim());
		result.append(qiesl.substring(0, pos + 1));
		result.append(value / 100);
		result.append("\n\t\t");

		int secondStart = qiesl.indexOf(",", firstEnd + 1) + 1;
		int secondEnd = qiesl.indexOf(")", secondStart);
		value = Double.parseDouble(qiesl.substring(secondStart, secondEnd)
				.trim());
		result.append(qiesl.subSequence(firstEnd, secondStart));
		result.append(value / 100);
		result.append(")");
		String rest = qiesl.substring(secondEnd + 1, qiesl.length());
		if (rest.length() > 0) {
			result.append(migrate(rest));
		}

		return result.toString();
	}

	public static void main(String[] args) throws IOException {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*",
				new XMIResourceFactoryImpl());
		QmPackage.eINSTANCE.getQualityModel();

		ResourceSet resourceSet = new ResourceSetImpl();
		File dir = new File(
				"C:/Projekte/Quamoco/quamoco_svn/wps/WP 1 - QM Foundations/WP 1.5 - Base Model/base model/current");
		for (File file : dir.listFiles()) {
			if (file.getName().endsWith(".qm")) {
				resourceSet.getResource(URI.createFileURI(file
						.getAbsolutePath()), true);
			}
		}

		for (Resource resource : resourceSet.getResources()) {
			System.out.println(resource.getURI());
			QualityModel model = (QualityModel) resource.getContents().get(0);
			for (Evaluation evaluation : model.getEvaluations()) {
				if (evaluation instanceof QIESLEvaluation) {
					QIESLEvaluation qieslEvaluation = (QIESLEvaluation) evaluation;
					try {
						qieslEvaluation
								.setSpecification(migrate(qieslEvaluation
										.getSpecification()));
					} catch (RuntimeException e) {
						System.err.println(qieslEvaluation.getQualifiedName());
					}
				}
			}
		}

		ResourceUtils.saveResourceSet(resourceSet);
	}

}
