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
   $Id: RandomFileSampleStrategy.java 4974 2012-02-17 09:34:10Z lochmann $            
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
package edu.tum.cs.conqat.inspection.sampling;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.conqat.java.resource.IJavaElement;

import de.quamoco.qm.ManualInstrument;
import de.quamoco.qm.MeasureBase;
import edu.tum.cs.conqat.core.AConQATProcessor;
import edu.tum.cs.conqat.core.ConQATException;
import edu.tum.cs.conqat.inspection.cm.Checklist;
import edu.tum.cs.conqat.inspection.cm.CmFactory;
import edu.tum.cs.conqat.inspection.cm.InspectionEvaluation;
import edu.tum.cs.conqat.inspection.cm.InspectionItem;
import edu.tum.cs.conqat.inspection.cm.InspectionMeasure;
import edu.tum.cs.conqat.inspection.cm.Sample;

/**
 * {@ConQATDoc}
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 2A33E480F2DECEFF485C89064221E4A5
 */
@AConQATProcessor(description = ""
		+ "A sample strategy that selects random file samples (default is 10 per inspector)"
		+ "for evaluation of multiple measures at once.")
public class RandomFileSampleStrategy extends SampleStrategyBase {

	/** The maximum number of measures to be inspected on one checklist. */
	private static final int maxMeasuresPerChecklist = 6;

	/** The default number of samples per measure. */
	private static final int defaultSamplesPerMeasure = 10;

	/** {@inheritDoc} */
	@Override
	public List<Checklist> generateChecklists(
			Map<MeasureBase, ManualInstrument> measuresAndInstruments,
			List<String> inspectors) throws ConQATException {
		List<Checklist> checklists = createChecklistsWithMeasures(
				measuresAndInstruments, inspectors, maxMeasuresPerChecklist);

		List<IJavaElement> javaClassElements = findJavaClassElements();
		Collections.shuffle(javaClassElements);

		int count = calculateCount(inspectors, javaClassElements);

		createInspectionItems(count, checklists, javaClassElements);
		return checklists;
	}

	/**
	 * Calculates how many file samples will be given for each measure to be
	 * inspected.
	 */
	private int calculateCount(List<String> inspectors,
			List<IJavaElement> javaClassElements) throws ConQATException {
		int count = defaultSamplesPerMeasure;
		if (javaClassElements.size() < inspectors.size() * count) {
			count = javaClassElements.size() / inspectors.size();
			if (count == 0) {
				throw new ConQATException(
						"Not enough file samples found or too many inspectors given");
			}
		}
		return count;
	}

	/**
	 * Creates inspection items from file samples for given measure and attaches
	 * them to given checklists.
	 * 
	 * @param count
	 *            how many file samples will be given for the measures to be
	 *            inspected.
	 */
	private void createInspectionItems(int count, List<Checklist> checklists,
			List<IJavaElement> javaClassElements) {
		int offset = 0;
		for (Checklist checklist : checklists) {
			InspectionItem item = CmFactory.eINSTANCE.createInspectionItem();
			item.getInspectionMeasures().addAll(
					checklist.getInspectionMeasures());
			// samples
			List<IJavaElement> classElementSubList = javaClassElements.subList(
					offset, offset + count);
			for (IJavaElement element : classElementSubList) {
				Sample sample = CmFactory.eINSTANCE.createSample();
				sample.setName(element.getName());
				sample.setPackagePath(element.getId());
				sample.setSourcePath(element.getLocation());
				item.getSamples().add(sample);
			}
			// evaluations
			for (Sample sample : item.getSamples()) {
				for (InspectionMeasure measure : item.getInspectionMeasures()) {
					InspectionEvaluation evaluation = CmFactory.eINSTANCE
							.createInspectionEvaluation();
					evaluation.setInspectionMeasure(measure);
					evaluation.setSample(sample);
					item.getInspectionEvaluations().add(evaluation);
				}
			}
			checklist.getInspectionItems().add(item);
			offset += count;
		}
	}

}
