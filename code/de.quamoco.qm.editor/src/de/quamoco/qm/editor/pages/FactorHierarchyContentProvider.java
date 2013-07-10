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

package de.quamoco.qm.editor.pages;

import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.swt.graphics.Image;

import de.quamoco.qm.QmPackage;
import de.quamoco.qm.editor.hierarchyviews.HierarchyContentProvider;
import de.quamoco.qm.editor.hierarchyviews.QualityModelRootProvider;
import de.quamoco.qm.editor.hierarchyviews.TransientHierarchyProvider;
import de.quamoco.qm.provider.QmEditPlugin;

/**
 * Content provider for the factor hierarchy.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class FactorHierarchyContentProvider extends HierarchyContentProvider {

	/** Constructor. */
	public FactorHierarchyContentProvider() {
		super(new QualityModelRootProvider(
				QmPackage.eINSTANCE.getQualityModel_Factors(),
				QmPackage.eINSTANCE.getFactor_RefinesDirect()),
				new TransientHierarchyProvider(null,
						QmPackage.eINSTANCE.getFactor_InfluencesDirect())
						.setLabel("Influenced By").setImage(
								getImage("full/obj16/FactorImpact")),
				new TransientHierarchyProvider(null,
						QmPackage.eINSTANCE.getFactor_RefinesDirect())
						.setLabel("Refined By").setImage(
								getImage("full/obj16/FactorRefinement")),
				new TransientHierarchyProvider(null,
						QmPackage.eINSTANCE.getMeasure_RefinesDirect())
						.setLabel("Refined By").setImage(
								getImage("full/obj16/MeasureRefinement")),
				new TransientHierarchyProvider(null,
						QmPackage.eINSTANCE.getMeasure_MeasuresDirect())
						.setLabel("Measured By").setImage(
								getImage("full/obj16/Measurement")),
				new TransientHierarchyProvider(null,
						QmPackage.eINSTANCE.getEvaluation_Evaluates())
						.setLabel("Evaluated By").setImage(
								getImage("full/obj16/Evaluation")),
				new TransientHierarchyProvider(null,
						QmPackage.eINSTANCE.getMeasurementMethod_Determines())
						.setLabel("Determined By").setImage(
								getImage("full/obj16/MeasurementMethod")),
				new TransientHierarchyProvider(
						QmPackage.eINSTANCE.getFactor_Influences()).setLabel(
						"Impacts").setImage(getImage("full/obj16/Impact")));
	}

	/** Helper method to get an image by its key. */
	private static Image getImage(String imageKey) {
		Object image = QmEditPlugin.INSTANCE.getImage(imageKey);
		return ExtendedImageRegistry.INSTANCE.getImage(image);
	}
}
