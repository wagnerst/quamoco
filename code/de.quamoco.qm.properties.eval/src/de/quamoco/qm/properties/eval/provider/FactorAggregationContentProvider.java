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
import de.quamoco.qm.Factor;
import de.quamoco.qm.Impact;
import de.quamoco.qm.properties.eval.ParseQIESLException;
import de.quamoco.qm.properties.eval.generator.EvaluationGenerator;
import de.quamoco.qm.properties.eval.generator.EvaluationGeneratorFactory;
import de.quamoco.qm.properties.eval.model.InfluencingOrRefiningElement;
import de.quamoco.qm.properties.eval.model.InfluencingOrRefiningElement.Type;
import de.quamoco.qm.properties.eval.model.InfluencingOrRefiningFactor;
import de.quamoco.qm.properties.eval.section.FactorAggregationSection;

public class FactorAggregationContentProvider implements
		IStructuredContentProvider {

	/**
	 * Ugly workaround, {@link #getElementsStatic(Object)} is called by
	 * {@link FactorAggregationSection} and this result is passed as input for
	 * the {@link TableViewer}. Necessary, since otherwise the user's current
	 * changes will be lost during refresh() of view (for resorting evaluation).
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		return (Object[]) inputElement;
	}

	public static Object[] getElementsStatic(Object inputElement) {
		List<InfluencingOrRefiningElement<Factor>> result = new LinkedList<InfluencingOrRefiningElement<Factor>>();

		if (inputElement instanceof Evaluation) { // else turn empty list as
													// result

			Evaluation evaluation = (Evaluation) inputElement;
			EvaluationGenerator evaluationGenerator = EvaluationGeneratorFactory
					.createEvaluationGenerator(evaluation);

			Factor evaluatedFactor = evaluation.getEvaluates();

			// create vector to collected all factors relevant for the
			// evaluation
			Vector<InfluencingOrRefiningElement<Factor>> relevantFactors = new Vector<InfluencingOrRefiningElement<Factor>>();

			try {
				double defaultCPs = 0;
				int defaultRanking = 0;

				// if the the evaluation was previously generated get all
				// previously ranked factors
				// that are still relevant (exist and are in a module that can
				// be used)

				if (evaluationGenerator.isGeneratedAggregation(evaluation)) {

					relevantFactors = evaluationGenerator
							.getCurrentlyUsedFactors(evaluation);

					// set the default ranking based on the approach used in the
					// existing evaluation
					// all >=0: ranking-based, all -1: contribution-points-based
					for (InfluencingOrRefiningElement<Factor> factor : relevantFactors) {
						if (factor.getRanking() == -1) {
							defaultRanking = -1;
							break;
						}
					}
				}

				// get all factors with an influence on the evaluated factor
				// AND can be used in the evaluation (based on module
				// constrains)

				for (Impact impact : evaluatedFactor.getInfluencedBy()) {

					// get factors influencing the evaluated factor
					Factor influencingFactor = impact.getSource();

					// check if factor is (still) usable/relevant
					if (evaluation.getUsableFactors().contains(
							influencingFactor)) {

						// chose the type based on the impact effect
						Type type = null;
						switch (impact.getEffect()) {
						case NEGATIVE:
							type = Type.negative;
							break;
						case POSITIVE:
							type = Type.positive;
							break;
						}

						// Try to get existing InfluencingOrRefiningElement
						InfluencingOrRefiningElement<Factor> influencingOrRefiningElement = getUpdatedInfluencingOrRefiningFactor(
								relevantFactors, influencingFactor, type,
								evaluation.getMaximumPoints());

						if (influencingOrRefiningElement != null) {
							result.add(influencingOrRefiningElement);
						} else {
							result.add(createDefaultWrapper(influencingFactor,
									type, defaultRanking, defaultCPs,
									evaluation.getMaximumPoints()));
						}
					}
				}

				// get all factors refining the evaluated factor
				// AND can be used in the evaluation (based on module
				// constrains)

				for (Factor refiningFactor : evaluatedFactor
						.getRefinedByDirect()) {

					// check if factor is (still) usable/relevant
					if (evaluation.getUsableFactors().contains(refiningFactor)) {

						// Try to get existing InfluencingOrRefiningElement
						InfluencingOrRefiningElement<Factor> influencingOrRefiningElement = getUpdatedInfluencingOrRefiningFactor(
								relevantFactors, refiningFactor,
								Type.refinement, evaluation.getMaximumPoints());
						if (influencingOrRefiningElement != null) {
							result.add(influencingOrRefiningElement);
						} else {
							result.add(createDefaultWrapper(refiningFactor,
									Type.refinement, defaultRanking,
									defaultCPs, evaluation.getMaximumPoints()));
						}
					}

				}

				// TODO2 detect diff here
				/*
				 * if (!qieslFactors.isEmpty()) { // there is some diff }
				 */
			} catch (ParseQIESLException e) {
				// TODO2 give the user some message
				e.printStackTrace();
			}
		}

		return result.toArray();
	}

	/**
	 * Searches through the passed list of {@link InfluencingOrRefiningElement}
	 * if an instance already exists for the passed {@link Factor}. If so the
	 * {@link InfluencingOrRefiningElement} (or wrapper) is updated and
	 * returned. If not, null is returned.
	 * 
	 * @param qieslFactors
	 * @param factor
	 * @param type
	 * @param maximumPoints
	 * @return
	 */
	protected static InfluencingOrRefiningElement<Factor> getUpdatedInfluencingOrRefiningFactor(
			Vector<InfluencingOrRefiningElement<Factor>> qieslFactors,
			Factor factor, Type type, int maximumPoints) {

		String name = factor.getQualifiedName();
		if ((name != null) && (qieslFactors != null)) {
			for (Iterator<InfluencingOrRefiningElement<Factor>> iterator = qieslFactors
					.iterator(); iterator.hasNext();) {
				InfluencingOrRefiningElement<Factor> influencingOrRefiningElement = iterator
						.next();
				if (name.equals(influencingOrRefiningElement.getName())) {
					/*
					 * Match => Update values (from model)
					 */
					// TODO2 detect diff here
					influencingOrRefiningElement.setMaxPoints(maximumPoints);
					influencingOrRefiningElement.setType(type);
					influencingOrRefiningElement.setModule(factor
							.getQualityModel().getQualifiedName());

					// Assign concrete factor instance
					influencingOrRefiningElement.setElement(factor);

					iterator.remove(); // better performance and ability to
					// detect diff
					return influencingOrRefiningElement;
				}
			}
		}
		return null;
	}

	protected static InfluencingOrRefiningElement<Factor> createDefaultWrapper(
			Factor factor, Type type, int ranking, double contPoints,
			int maxPoints) {
		String module = factor.getQualityModel().getQualifiedName();
		return new InfluencingOrRefiningFactor(factor, ranking, contPoints,
				maxPoints, type, module);
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public void dispose() {
	}

}
