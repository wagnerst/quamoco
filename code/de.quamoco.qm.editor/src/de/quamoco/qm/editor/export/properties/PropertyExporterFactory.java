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

package de.quamoco.qm.editor.export.properties;

import org.eclipse.emf.ecore.EObject;

import de.quamoco.qm.Entity;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FindingsUnionMeasureAggregation;
import de.quamoco.qm.Impact;
import de.quamoco.qm.ManualInstrument;
import de.quamoco.qm.Measure;
import de.quamoco.qm.QIESLEvaluation;
import de.quamoco.qm.ToolBasedInstrument;
import de.quamoco.qm.WeightedSumFactorAggregation;
import de.quamoco.qm.WeightedSumMultiMeasureEvaluation;
import de.quamoco.qm.editor.export.WebSiteExporter;

/**
 * Generated the correct property exporters.
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
public class PropertyExporterFactory {

	/**
	 * Returns the correct property exporter the the element type
	 */
	public static PropertyExporter<?> getExporter(WebSiteExporter parent,
			EObject element, String dir) {

		if (element instanceof Factor) {
			return new FactorPropertyExporter(parent, (Factor) element, dir);
		} else if (element instanceof Entity) {
			return new EntityPropertyExporter(parent, (Entity) element, dir);
		} else if (element instanceof WeightedSumFactorAggregation) {
			return new WeightedSumFactorAggregationPropertyExporter(parent,
					(WeightedSumFactorAggregation) element, dir);
		} else if (element instanceof WeightedSumMultiMeasureEvaluation) {
			return new WeightedSumMultiMeasureEvaluationPropertyExporter(
					parent, (WeightedSumMultiMeasureEvaluation) element, dir);
		} else if (element instanceof QIESLEvaluation) {
			return new QIESLEvaluationPropertyExporter(parent,
					(QIESLEvaluation) element, dir);
		} else if (element instanceof Measure) {
			return new MeasurePropertyExporter(parent, (Measure) element, dir);
		} else if (element instanceof ToolBasedInstrument) {
			return new ToolBasedInstrumentPropertyExporter(parent,
					(ToolBasedInstrument) element, dir);
		} else if (element instanceof ManualInstrument) {
			return new ManualInstrumentPropertyExporter(parent,
					(ManualInstrument) element, dir);
		} else if (element instanceof Impact) {
			return new ImpactPropertyExporter(parent, (Impact) element, dir);
		} else if (element instanceof FindingsUnionMeasureAggregation) {
			return new FindingsUnionMeasureAggregationPropertyExporter(parent,
					(FindingsUnionMeasureAggregation) element, dir);
		} else {
			System.err.println("Warning: Using default exporter for class "
					+ element.getClass().getName());
		}

		return new DefaultPropertyExporter(parent, element, dir);
	}
}
