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

package de.quamoco.qm.properties.eval.provider;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Measure;
import de.quamoco.qm.properties.eval.generator.EvaluationGenerator;
import de.quamoco.qm.properties.eval.generator.EvaluationGeneratorFactory;
import de.quamoco.qm.properties.eval.model.InfluencingOrRefiningElement;
import de.quamoco.qm.properties.eval.model.RefiningMeasure;
import de.quamoco.qm.properties.eval.section.MultiMeasureEvaluationSection;

public class MeasureAggregationContentProvider implements
		IStructuredContentProvider {

	/**
	 * Ugly workaround, {@link #getElementsStatic(Object)} is called by
	 * {@link MultiMeasureEvaluationSection} and this result is passed as input
	 * for the {@link TableViewer}. Necessary, since otherwise the user's
	 * current changes will be lost during refresh() of view (for resorting
	 * evaluation).
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		return (Object[]) inputElement;
	}

	public static Object[] getElementsStatic(Object inputElement) {
		List<InfluencingOrRefiningElement<Measure>> result = new LinkedList<InfluencingOrRefiningElement<Measure>>();

		if (inputElement instanceof Evaluation) { // else return empty list as
													// result
			Evaluation evaluation = (Evaluation) inputElement;
			EvaluationGenerator evaluationGenerator = EvaluationGeneratorFactory
					.createEvaluationGenerator(evaluation);

			// create vector to collected all measures relevant for the
			// evaluation
			Vector<InfluencingOrRefiningElement<Measure>> relevantMeasures = new Vector<InfluencingOrRefiningElement<Measure>>();

			double defaultCPs = 0;
			int defaultRanking = 0;

			// if the the evaluation was previously generated get all previously
			// ranked measures
			// that are still relevant (exist and are in a module that can be
			// used)

			if (evaluationGenerator.isGeneratedAggregation(evaluation)) {
				relevantMeasures = evaluationGenerator
						.getCurrentlyUsedMeasures(evaluation);

				// set the default ranking based on the approach used in the
				// existing evaluation
				// all >=0: ranking-based, all -1: contribution-points-based
				for (InfluencingOrRefiningElement<Measure> measure : relevantMeasures) {
					if (measure.getRanking() == -1) {
						defaultRanking = -1;
						break;
					}
				}
			}

			// get all measures that can be used from the evaluation
			for (Measure usableMeasure : evaluation.getUsableMeasures()) {
				// Try to get existing InfluencingOrRefiningElement
				InfluencingOrRefiningElement<Measure> refiningMeasure = getUpdatedRefiningMeasure(
						relevantMeasures, usableMeasure, evaluation
								.getMaximumPoints());

				if (refiningMeasure != null) {
					result.add(refiningMeasure);
				} else {
					result.add(createDefaultWrapper(usableMeasure,
							defaultRanking, defaultCPs, evaluation
									.getMaximumPoints()));
				}
			}

		}

		return result.toArray();
	}

	/**
	 * Searches through the passed list of {@link InfluencingOrRefiningElement}
	 * if an instance already exists for the passed {@link Measure}. If so the
	 * {@link InfluencingOrRefiningElement} (or wrapper) is updated and
	 * returned. If not, null is returned.
	 * 
	 * @param measures
	 * @param factor
	 * @param type
	 * @param maximumPoints
	 * @return
	 */
	protected static InfluencingOrRefiningElement<Measure> getUpdatedRefiningMeasure(
			Vector<InfluencingOrRefiningElement<Measure>> measures,
			Measure measure, int maximumPoints) {

		for (Iterator<InfluencingOrRefiningElement<Measure>> iterator = measures
				.iterator(); iterator.hasNext();) {
			InfluencingOrRefiningElement<Measure> refiningMeasure = iterator
					.next();
			if (refiningMeasure.getElement().equals(measure)) {
				/*
				 * Match => Update values (from model)
				 */
				// TODO2 detect diff here
				refiningMeasure.setMaxPoints(maximumPoints);
				refiningMeasure.setType(RefiningMeasure.convertType(measure
						.getType()));
				refiningMeasure.setModule(measure.getQualityModel()
						.getQualifiedName());

				iterator.remove(); // better performance and ability to
				// detect diff
				return refiningMeasure;

			}
		}
		return null;
	}

	protected static InfluencingOrRefiningElement<Measure> createDefaultWrapper(
			Measure measure, int ranking, double contPoints, int maxPoints) {
		String module = measure.getQualityModel().getQualifiedName();
		return new RefiningMeasure(measure, null, ranking, contPoints,
				maxPoints, module, null, null, 0, 0);
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public void dispose() {
	}

}
