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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edapt.common.ResourceUtils;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasurementMethod;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;

public class FactorMigrator {

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
			for (Factor factor : new ArrayList<Factor>(model.getFactors())) {
				QualityModel target = getLeastCommonQualityModel(factor);
				if (model != target) {
					System.out.println(model.getName() + ":"
							+ factor.getQualifiedName() + " -> "
							+ target.getName());
					factor.setQualityModel(target);
					for (Evaluation eval : factor.getEvaluatedBy()) {
						System.out
								.print(eval.getQualityModel().getName() + " ");
					}
					System.out.println();
				}
			}
			for (Measure measure : new ArrayList<Measure>(model.getMeasures())) {
				QualityModel target = getLeastCommonQualityModel(measure);
				if (model != target) {
					System.out.println(model.getName() + ":"
							+ measure.getQualifiedName() + " -> "
							+ target.getName());
					measure.setQualityModel(target);
					for (MeasurementMethod eval : measure.getDeterminedBy()) {
						System.out
								.print(eval.getQualityModel().getName() + " ");
					}
					System.out.println();
				}
			}
		}

		ResourceUtils.saveResourceSet(resourceSet);
	}

	private static QualityModel getLeastCommonQualityModel(Measure measure) {
		List<MeasurementMethod> evals = measure.getDeterminedBy();
		if (evals.isEmpty()) {
			return measure.getQualityModel();
		}
		if (evals.size() == 1) {
			return evals.get(0).getQualityModel();
		}
		return measure.getQualityModel();
	}

	private static QualityModel getLeastCommonQualityModel(Factor factor) {
		List<Evaluation> evals = factor.getEvaluatedBy();
		if (evals.isEmpty()) {
			return factor.getQualityModel();
		}
		if (evals.size() == 1) {
			return evals.get(0).getQualityModel();
		}
		return factor.getQualityModel();
	}
}
