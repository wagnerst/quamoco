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

/*-----------------------------------------------------------------------+
 | edu.tum.cs.conqat.quamoco.inspection
 |                                                                       |
   $Id: ChecklistEvaluator.java 29845 2010-08-23 19:17:49Z niessner $            
 |                                                                       |
 | Copyright (c)  2004-2010 Technische Universitaet Muenchen             |
 |                                                                       |
 | Technische Universitaet Muenchen               #########  ##########  |
 | Institut fuer Informatik - Lehrstuhl IV           ##  ##  ##  ##  ##  |
 | Prof. Dr. Manfred Broy                            ##  ##  ##  ##  ##  |
 | Boltzmannstr. 3                                   ##  ##  ##  ##  ##  |
 | 85748 Garching bei Muenchen                       ##  ##  ##  ##  ##  |
 | Germany                                           ##  ######  ##  ##  |
 +-----------------------------------------------------------------------*/
package edu.tum.cs.conqat.inspection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.tum.cs.conqat.commons.ConQATParamDoc;
import edu.tum.cs.conqat.commons.ConQATProcessorBase;
import edu.tum.cs.conqat.conqatdoc.ConQATDoc;
import edu.tum.cs.conqat.core.AConQATAttribute;
import edu.tum.cs.conqat.core.AConQATParameter;
import edu.tum.cs.conqat.core.AConQATProcessor;
import edu.tum.cs.conqat.core.ConQATException;
import edu.tum.cs.conqat.inspection.cm.Checklist;
import edu.tum.cs.conqat.inspection.cm.InspectionEvaluation;
import edu.tum.cs.conqat.inspection.cm.InspectionItem;
import edu.tum.cs.conqat.inspection.cm.InspectionMeasure;
import edu.tum.cs.conqat.quamoco.MetricCollection;

/**
 * {@link ConQATDoc}
 * 
 * @author niessner
 * @author $Author: niessner $
 * @version $Rev: 29845 $
 * @levd.rating YELLOW Hash: A89356FDF750C1DB0FBC392FC5B18321
 */
@AConQATProcessor(description = "This Processor performs the actual evaluation of checklists."
		+ "It calculates a grade (from 1 to 6) from all given checklists.")
public class ChecklistEvaluator extends ConQATProcessorBase {

	/**
	 * The checklists.
	 */
	private Checklist[] checklists;

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "models", minOccurrences = 1, maxOccurrences = 1, description = ""
			+ "The checklist models.")
	public void setChecklists(
			@AConQATAttribute(name = ConQATParamDoc.INPUT_REF_NAME, description = ConQATParamDoc.INPUT_REF_DESC) Checklist[] checklists) {
		this.checklists = checklists;
	}

	/** {@inheritDoc} */
	@Override
	public MetricCollection process() throws ConQATException {
		Map<String, List<Integer>> itemEvaluations = getItemEvaluations();

		Map<String, Double> evaluations = new HashMap<String, Double>();
		for (String name : itemEvaluations.keySet()) {
			double meanSum = 0;
			for (Integer meanValue : itemEvaluations.get(name)) {
				meanSum += meanValue.doubleValue();
			}
			double overallMean = (1. * meanSum)
					/ itemEvaluations.get(name).size();
			double overallMeanRounded = ((int) (overallMean * 10)) / 10.;
			evaluations.put(name, new Double(overallMeanRounded));
		}
		MetricCollection evaluationsCollection = new MetricCollection();
		for (String name : evaluations.keySet()) {
			evaluationsCollection.put(name, evaluations.get(name));
		}
		return evaluationsCollection;
	}

	/**
	 * Gets all item evaluation values for each measure.
	 * 
	 * @throws ConQATException
	 *             if one or more checklists have not been completed yet.
	 */
	private Map<String, List<Integer>> getItemEvaluations()
			throws ConQATException {
		Map<String, List<Integer>> itemEvaluations = new HashMap<String, List<Integer>>();
		List<InspectionEvaluation> inspectionEvaluations = new ArrayList<InspectionEvaluation>();
		for (Checklist checklist : checklists) {
			for (InspectionMeasure measure : checklist.getInspectionMeasures()) {
				itemEvaluations.put(measure.getMeasureName(),
						new ArrayList<Integer>());
			}
			for (InspectionItem item : checklist.getInspectionItems()) {
				inspectionEvaluations.addAll(item.getInspectionEvaluations());
			}
		}
		for (InspectionEvaluation evaluation : inspectionEvaluations) {
			String measureName = evaluation.getInspectionMeasure()
					.getMeasureName();
			Integer value = new Integer(evaluation.getValue());
			if (value.intValue() == -1) {
				throw new ConQATException(
						"One or more checklists have not been completed yet");
			}
			itemEvaluations.get(measureName).add(value);
		}
		return itemEvaluations;
	}
}
