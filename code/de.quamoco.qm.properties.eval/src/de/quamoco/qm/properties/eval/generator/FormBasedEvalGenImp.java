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

package de.quamoco.qm.properties.eval.generator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FactorAggregation;
import de.quamoco.qm.FactorRanking;
import de.quamoco.qm.FormBasedEvaluation;
import de.quamoco.qm.Function;
import de.quamoco.qm.Impact;
import de.quamoco.qm.LinearDecreasingFunction;
import de.quamoco.qm.LinearFunction;
import de.quamoco.qm.LinearIncreasingFunction;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasureEvaluation;
import de.quamoco.qm.MeasureRanking;
import de.quamoco.qm.NormalizationMeasure;
import de.quamoco.qm.QmFactory;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.Ranking;
import de.quamoco.qm.SingleMeasureEvaluation;
import de.quamoco.qm.WeightedSumFactorAggregation;
import de.quamoco.qm.WeightedSumMultiMeasureEvaluation;
import de.quamoco.qm.properties.eval.EnhancedMappingFunction;
import de.quamoco.qm.properties.eval.ParseQIESLException;
import de.quamoco.qm.properties.eval.model.InfluencingOrRefiningElement;
import de.quamoco.qm.properties.eval.model.InfluencingOrRefiningElement.Type;
import de.quamoco.qm.properties.eval.model.InfluencingOrRefiningFactor;
import de.quamoco.qm.properties.eval.model.RefiningMeasure;

public class FormBasedEvalGenImp implements EvaluationGenerator {

	/** Force user to use {@link EvaluationGeneratorFactory} */
	protected FormBasedEvalGenImp() {
	}

	@Override
	public CompoundCommand generatePointBasedAggregation(
			EditingDomain editingDomain, Evaluation evaluation, int maxPoints,
			Vector<InfluencingOrRefiningElement<?>> elements) {

		// Step 1: Set all rankings to -1
		for (InfluencingOrRefiningElement<?> element : elements) {
			element.setRanking(-1);
		}

		// Step 2: Generate command for model update
		CompoundCommand result = createCommandToUpdateRankings(editingDomain,
				elements, evaluation);

		result.setLabel("Generate point pased aggregation");
		return result;
	}

	@Override
	public CompoundCommand generateRankingBasedAggregation(
			EditingDomain editingDomain, Evaluation evaluation, int maxPoints,
			Vector<InfluencingOrRefiningElement<?>> elements) {

		// Step 1: Calculate the contribution points
		calulateContributioPointsAndUpdate(elements, maxPoints);

		// Step 2: Generate command for model update
		CompoundCommand result = createCommandToUpdateRankings(editingDomain,
				elements, evaluation);

		result.setLabel("Generate ranking based aggregation");
		return result;
	}

	@Override
	public Measure getEvaluationMeasure(Evaluation evaluation) {
		if (evaluation instanceof SingleMeasureEvaluation) {
			return ((SingleMeasureEvaluation) evaluation).getMeasure();
		}

		return null;
	}

	@Override
	public Vector<InfluencingOrRefiningElement<Factor>> getCurrentlyUsedFactors(
			Evaluation evaluation) {
		Vector<InfluencingOrRefiningElement<Factor>> factors = new Vector<InfluencingOrRefiningElement<Factor>>();

		if (evaluation instanceof WeightedSumFactorAggregation) {
			WeightedSumFactorAggregation wsEvaluation = (WeightedSumFactorAggregation) evaluation;

			if (wsEvaluation.getEvaluates() != null) {
				for (FactorRanking fRanking : wsEvaluation.getRankings()) {
					if (fRanking.getFactor() != null) {
						Type type = getTypeOf(wsEvaluation.getEvaluates(),
								fRanking.getFactor());
						if (type != null) { // else the factor or relationship
											// does'nt exist

							int ranking = fRanking.getRank();
							double contPoints = (fRanking.getWeight() * wsEvaluation
									.getMaximumPoints());
							int maxPoints = -1;

							String modul = fRanking.getFactor()
									.getQualityModel().getName();

							InfluencingOrRefiningElement<Factor> factor = new InfluencingOrRefiningFactor(
									fRanking.getFactor(), ranking, contPoints,
									maxPoints, type, modul);

							factors.add(factor);
						}
					}
				}
			}

		}
		return factors;
	}

	@Override
	public Vector<InfluencingOrRefiningElement<Measure>> getCurrentlyUsedMeasures(
			Evaluation evaluation) {
		Vector<InfluencingOrRefiningElement<Measure>> measures = new Vector<InfluencingOrRefiningElement<Measure>>();

		if (evaluation instanceof WeightedSumMultiMeasureEvaluation) {
			WeightedSumMultiMeasureEvaluation wsEvaluation = (WeightedSumMultiMeasureEvaluation) evaluation;
			Factor evaluatedFactor = wsEvaluation.getEvaluates();

			for (MeasureRanking measureRanking : wsEvaluation.getRankings()) {
				if (measureRanking.getMeasure() != null
						&& measureRanking.getMeasure().getMeasuresDirect()
								.contains(evaluatedFactor)) {
					Function function = measureRanking.getFunction();

					double lowerBound = 0.0;
					double upperBound = 0.0;
					MappingFunction mappingFunction = null;
					if (function instanceof LinearFunction) {
						LinearFunction linearFunction = (LinearFunction) function;
						if (linearFunction instanceof LinearDecreasingFunction) {
							mappingFunction = MappingFunction.LinearDecreasing;
						} else if (linearFunction instanceof LinearIncreasingFunction) {
							mappingFunction = MappingFunction.LinearIncreasing;
						} // else ignore
						lowerBound = linearFunction.getLowerBound();
						upperBound = linearFunction.getUpperBound();
					}

					Measure measure = measureRanking.getMeasure();
					NormalizationMeasure normalizationMeasure = measureRanking
							.getNormlizationMeasure();
					int ranking = measureRanking.getRank();
					double contPoints = (measureRanking.getWeight() * wsEvaluation
							.getMaximumPoints());
					int maxPoints = -1;
					String module = measure.getQualityModel().getName();
					Range range = getRange(measureRanking);

					InfluencingOrRefiningElement<Measure> measureElement = new RefiningMeasure(
							measure, normalizationMeasure, ranking, contPoints,
							maxPoints, module, range, mappingFunction,
							lowerBound, upperBound);

					measures.add(measureElement);
				}
			}
		}
		return measures;
	}

	private Type getTypeOf(Factor evaluatedFactor, Factor factor) {

		/*
		 * Influencing factors
		 */
		for (Impact impact : evaluatedFactor.getInfluencedBy()) {

			// check whether factor influence the evaluated factor
			if (impact.getSource() == factor) {
				// return the type based on the impact effect
				switch (impact.getEffect()) {
				case NEGATIVE:
					return Type.negative;
				case POSITIVE:
					return Type.positive;
				}
			}
		}
		if (evaluatedFactor.getRefinedByDirect().contains(factor)) {
			return Type.refinement;
		}

		return null; // factor is no more part of a refinement or infl.
						// relationship
	}

	@Override
	public double getLowerBound(Evaluation evaluation)
			throws ParseQIESLException {

		SingleMeasureEvaluation smEvaluation;

		if (!(evaluation instanceof SingleMeasureEvaluation))
			return 0;
		else
			smEvaluation = (SingleMeasureEvaluation) evaluation;

		Function function = smEvaluation.getFunction();

		LinearFunction lFunction;

		if (!(function instanceof LinearFunction))
			return 0;
		else
			lFunction = (LinearFunction) function;

		return lFunction.getLowerBound();
	}

	@Override
	public MappingFunction getMappingFunction(Evaluation evaluation) {

		SingleMeasureEvaluation smEvaluation;

		if (!(evaluation instanceof SingleMeasureEvaluation))
			return null;
		else
			smEvaluation = (SingleMeasureEvaluation) evaluation;

		Function function = smEvaluation.getFunction();

		if (function instanceof LinearIncreasingFunction)
			return MappingFunction.LinearIncreasing;
		else if (function instanceof LinearDecreasingFunction)
			return MappingFunction.LinearDecreasing;
		else
			return null;

	}

	@Override
	public int getMaxPoints(Evaluation evaluation) throws ParseQIESLException {

		return evaluation.getMaximumPoints();
	}

	@Override
	public Measure getNormalizationMeasure(Evaluation evaluation) {
		if (evaluation instanceof SingleMeasureEvaluation) {
			return ((SingleMeasureEvaluation) evaluation)
					.getNormlizationMeasure();
		}
		return null;
	}

	@Override
	public Range getRange(Evaluation evaluation) {
		if (evaluation instanceof MeasureEvaluation) {
			return getRange((MeasureEvaluation) evaluation);
		}
		return null;
	}

	protected Range getRange(MeasureEvaluation measureEvaluation) {
		String sRange = measureEvaluation.getRange();
		if ("Class".equalsIgnoreCase(sRange))
			return Range.CLASS;
		else if ("File".equalsIgnoreCase(sRange))
			return Range.FILE;
		else if ("Method".equalsIgnoreCase(sRange))
			return Range.METHOD;
		else if ("NA".equalsIgnoreCase(sRange))
			return Range.NA;
		return null;
	}

	@Override
	public Scale getScale(Evaluation evaluation) {

		SingleMeasureEvaluation smEvaluation;

		if (!(evaluation instanceof SingleMeasureEvaluation))
			return null;
		else
			smEvaluation = (SingleMeasureEvaluation) evaluation;

		int typId = smEvaluation.getMeasure().getType().getValue();

		if (typId == de.quamoco.qm.Type.FINDINGS_VALUE)
			return Scale.FINDINGS;
		else if (typId == de.quamoco.qm.Type.NUMBER_VALUE)
			return Scale.NUMBER;
		else if (typId == de.quamoco.qm.Type.NONE_VALUE)
			return Scale.UNDEFINED;
		else
			return null;
	}

	@Override
	public double getUpperBound(Evaluation evaluation) {

		SingleMeasureEvaluation smEvaluation;

		if (!(evaluation instanceof SingleMeasureEvaluation))
			return 0;
		else
			smEvaluation = (SingleMeasureEvaluation) evaluation;

		Function function = smEvaluation.getFunction();

		LinearFunction lFunction;

		if (!(function instanceof LinearFunction))
			return 0;
		else
			lFunction = (LinearFunction) function;

		return lFunction.getUpperBound();
	}

	@Override
	public boolean isGeneratedEvaluationFunction(Evaluation evaluation) {

		SingleMeasureEvaluation smEvaluation;

		if (!(evaluation instanceof SingleMeasureEvaluation))
			return false;
		else
			smEvaluation = (SingleMeasureEvaluation) evaluation;

		return (smEvaluation.getFunction() != null);
	}

	@Override
	public boolean isGeneratedAggregation(Evaluation evaluation) {
		if (evaluation instanceof WeightedSumFactorAggregation) {
			return !((WeightedSumFactorAggregation) evaluation).getRankings()
					.isEmpty();
		} else if (evaluation instanceof WeightedSumMultiMeasureEvaluation) {
			return !((WeightedSumMultiMeasureEvaluation) evaluation)
					.getRankings().isEmpty();
		}
		return false;
	}

	@Override
	public boolean isGenerated(Evaluation evaluation) {

		return (isGeneratedEvaluationFunction(evaluation) || isGeneratedAggregation(evaluation));
	}

	@Override
	public CompoundCommand generateEvalFunction(EditingDomain editingDomain,
			Evaluation evaluation, int maxPoints, Measure evaluationMeasure,
			Measure normalizationMeasure, Scale scale, Range range,
			MappingFunction mappingFunction, double lowerBound,
			double upperBound) {

		SingleMeasureEvaluation smEvaluation;

		if (!(evaluation instanceof SingleMeasureEvaluation))
			return null;
		else
			smEvaluation = (SingleMeasureEvaluation) evaluation;

		// 1: Update attributes of evaluation
		SetCommand setMaximumPointsCommand = new SetCommand(editingDomain,
				smEvaluation,
				QmPackage.eINSTANCE.getEvaluation_MaximumPoints(), maxPoints);
		SetCommand setMeasureCommand = new SetCommand(editingDomain,
				smEvaluation, QmPackage.eINSTANCE
						.getMeasureEvaluation_Measure(), evaluationMeasure);
		SetCommand setNormalizationMeasureCommand = new SetCommand(
				editingDomain, smEvaluation, QmPackage.eINSTANCE
						.getMeasureEvaluation_NormlizationMeasure(),
				normalizationMeasure);
		SetCommand setRangeCommand = new SetCommand(editingDomain,
				smEvaluation, QmPackage.eINSTANCE.getMeasureEvaluation_Range(),
				range.toString());

		// 2: Create command to remove existing (mapping) function
		DeleteCommand clearFunctionCommand = new DeleteCommand(editingDomain,
				Collections.singleton(smEvaluation.getFunction()));

		// 3: Create new (mapping) function and set bounds for LinearFunction
		LinearFunction lFunction;

		if (mappingFunction.equals(MappingFunction.LinearDecreasing)) {
			lFunction = QmFactory.eINSTANCE.createLinearDecreasingFunction();

		} else if (mappingFunction.equals(MappingFunction.LinearIncreasing)) {
			lFunction = QmFactory.eINSTANCE.createLinearIncreasingFunction();

		} else
			return null;

		lFunction.setLowerBound(lowerBound);
		lFunction.setUpperBound(upperBound);

		SetCommand setFunctionCommand = new SetCommand(editingDomain,
				smEvaluation, QmPackage.eINSTANCE
						.getMeasureEvaluation_Function(), lFunction);

		CompoundCommand result = new CompoundCommand();
		result.append(setMaximumPointsCommand);
		result.append(setMeasureCommand);
		result.append(setNormalizationMeasureCommand);
		result.append(setRangeCommand);
		if (smEvaluation.getFunction() != null)
			result.append(clearFunctionCommand);
		result.append(setFunctionCommand);
		return result;
	}

	@Override
	public CompoundCommand generateEvalFunction(EditingDomain editingDomain,
			Evaluation evaluation, int maxPoints, Measure evaluationMeasure,
			Measure normalizationMeasure, Scale scale, Range range,
			EnhancedMappingFunction mappingFunction) {

		if (mappingFunction.getType().equals(
				EvaluationGenerator.MappingFunction.LinearDecreasing)
				|| mappingFunction.getType().equals(
						EvaluationGenerator.MappingFunction.LinearIncreasing)) {

			return this.generateEvalFunction(editingDomain, evaluation,
					maxPoints, evaluationMeasure, normalizationMeasure, scale,
					range, mappingFunction.getType(), mappingFunction
							.getNodes()[0], mappingFunction.getNodes()[1]);

		} else {
			de.quamoco.qm.properties.eval.util.Util.showErrorDialog("Warning",
					"Not support evaluation function");

			return null;
		}
	}

	private void calulateContributioPointsAndUpdate(
			Vector<InfluencingOrRefiningElement<?>> elements, int maxPoints) {
		int numberOfRankings = 0;
		int highestRanking = 0;

		// Determine numberOfRankings, highestRanking and reset contribution
		// points
		for (InfluencingOrRefiningElement<?> element : elements) {
			if (element.getRanking() > 0) {
				numberOfRankings++;
			}
			if (element.getRanking() > highestRanking) {
				highestRanking = element.getRanking();
			}
			// initialize 0 contribution for all factors
			element.setContPoints(0.0);
		}

		// determine the weights with Rank-Order Centroid (ROC) method
		double[] weights = new double[numberOfRankings + 1]; // [0] is unused!
		for (int i = 1; i <= numberOfRankings; i++) {
			double weight = 0;
			for (int j = i; j <= numberOfRankings; j++) {
				weight += 1 / (double) j;
			}
			weights[i] = weight / numberOfRankings;
		}

		// set contribution points for each factor
		int weightIndex = 1;
		for (int i = 1; i <= highestRanking; i++) {
			double weight = 0;
			int usagesOfRanking = 0;

			for (InfluencingOrRefiningElement<?> element : elements) {
				if (element.getRanking() == i) {
					usagesOfRanking++;
				}
			}

			if (usagesOfRanking > 0) {
				for (int j = 1; j <= usagesOfRanking; j++) {
					weight += weights[weightIndex];
					weightIndex++;
				}

				weight = weight / usagesOfRanking;
				for (InfluencingOrRefiningElement<?> element : elements) {
					if (element.getRanking() == i) {
						element.setContPoints(weight * maxPoints);
					}
				}
			}
		}
	}

	/**
	 * This internal method creates a command to update the rankings for an
	 * weighted sum aggregation based on a vector of
	 * InfluencingOrRefiningElement.
	 */
	private CompoundCommand createCommandToUpdateRankings(
			EditingDomain editingDomain,
			Vector<InfluencingOrRefiningElement<?>> elements,
			Evaluation evaluation) {

		/*
		 * Set attributes: rankingFeature, rankings
		 */
		EStructuralFeature rankingFeature;
		EList<? extends Ranking> oldRankings;
		if (evaluation instanceof WeightedSumMultiMeasureEvaluation) {
			WeightedSumMultiMeasureEvaluation eval = (WeightedSumMultiMeasureEvaluation) evaluation;
			rankingFeature = QmPackage.eINSTANCE
					.getWeightedSumMultiMeasureEvaluation_Rankings();
			oldRankings = eval.getRankings();
		} else if (evaluation instanceof WeightedSumFactorAggregation) {
			WeightedSumFactorAggregation weightedSumFactorAggregation = (WeightedSumFactorAggregation) evaluation;
			rankingFeature = QmPackage.eINSTANCE
					.getWeightedSumFactorAggregation_Rankings();
			oldRankings = weightedSumFactorAggregation.getRankings();
		} else {
			return null; // nothing to do here
		}

		// 2.1: Create command to remove existing rankings
		RemoveCommand clearFactorRankingsCommand = new RemoveCommand(
				editingDomain, evaluation, rankingFeature, oldRankings);

		// 2.2 create for each InfluencingOrRefiningElement a ranking evaluation
		List<Ranking> newRankings = new LinkedList<Ranking>();
		for (InfluencingOrRefiningElement<?> element : elements) {
			if (element instanceof InfluencingOrRefiningFactor) {
				FactorRanking factorRanking = QmFactory.eINSTANCE
						.createFactorRanking();
				factorRanking.setFactor((Factor) element.getElement());
				factorRanking.setRank(element.getRanking());
				factorRanking.setWeight(element.getContPoints()
						/ evaluation.getMaximumPoints());
				newRankings.add(factorRanking);
			} else if (element instanceof RefiningMeasure) {
				RefiningMeasure refiningMeasure = (RefiningMeasure) element;
				MeasureRanking measureRanking = QmFactory.eINSTANCE
						.createMeasureRanking();
				measureRanking.setMeasure(refiningMeasure.getElement());
				measureRanking.setNormlizationMeasure(refiningMeasure
						.getNormalizationMeasure());
				measureRanking.setRank(refiningMeasure.getRanking());
				measureRanking.setWeight(refiningMeasure.getContPoints()
						/ evaluation.getMaximumPoints());
				Range range = refiningMeasure.getRange();
				measureRanking
						.setRange(range == null ? null : range.toString());
				// Add evaluation function
				LinearFunction function = null;
				if (MappingFunction.LinearDecreasing.equals(refiningMeasure
						.getMappingFunction())) {
					function = QmFactory.eINSTANCE
							.createLinearDecreasingFunction();
				} else if (MappingFunction.LinearIncreasing
						.equals(refiningMeasure.getMappingFunction())) {
					function = QmFactory.eINSTANCE
							.createLinearIncreasingFunction();
				}
				if (function != null) {
					function.setLowerBound(refiningMeasure.getLowerBound());
					function.setUpperBound(refiningMeasure.getUpperBound());
					measureRanking.setFunction(function);
				}

				newRankings.add(measureRanking);
			}
		}
		AddCommand addRankingsCommand = new AddCommand(editingDomain,
				evaluation, rankingFeature, newRankings);

		CompoundCommand result = new CompoundCommand();

		result.append(clearFactorRankingsCommand);
		result.append(addRankingsCommand);
		return result;
	}

	/**
	 * This function is a preliminary function that should later be provided by
	 * the evaluations. It will return a value between 0 and 1.
	 * 
	 * @param eval
	 *            the considered evaluation
	 * @return evaluation result
	 */
	public double calculateEvaluationResult(Evaluation eval) {

		if (eval instanceof FormBasedEvaluation) {

			if (eval instanceof FactorAggregation) {

				if (eval instanceof WeightedSumFactorAggregation) {

					double result = 0;

					for (InfluencingOrRefiningElement<Factor> factor : this
							.getCurrentlyUsedFactors(eval)) {

						double subFactorResult = -1.0; // TODO TUM
														// factor.getFactor().getEvaluatedBy().getMostSpecific().calculateEvaluationResult()

						if (factor.getType().equals(Type.negative))
							subFactorResult = 1 - subFactorResult;

						result += subFactorResult;
					}
					return result;

				} else
					return -1.0; // not supported aggregation

			} else if (eval instanceof SingleMeasureEvaluation) {

				SingleMeasureEvaluation smEvaluation = (SingleMeasureEvaluation) eval;

				// get measurement result (dealing with findings and numbers)
				double measureResult = -1.0;

				if (smEvaluation.getMeasure().getType().equals(
						de.quamoco.qm.Type.FINDINGS)) {

					// TODO TUM Object findings =
					// smEvaluation.getMeasure().calculateMeasurementResult();

					// TODO TUM if required determine range

					// TODO TUM if range=NA, count the findings

				} else if (smEvaluation.getMeasure().getType().equals(
						de.quamoco.qm.Type.NUMBER)) {

					measureResult = -1.0; // TODO TUM
											// smEvaluation.getMeasure().calculateMeasurementResult();

				} else
					return -1.0; // unknown measurement scale type

				// normalize the measurement result if required

				if (smEvaluation.getNormlizationMeasure() != null) {

					double normMeasureResult = -1.0; // TODO TUM
														// smEvaluation.getNormlizationMeasure().calculateMeasurementResult();

					measureResult = measureResult / normMeasureResult;
				}

				// mapping of the measurement value on the evaluation scale

				if (((SingleMeasureEvaluation) eval).getFunction() instanceof LinearIncreasingFunction) {

					LinearIncreasingFunction incFunc = (LinearIncreasingFunction) smEvaluation
							.getFunction();

					if (measureResult <= incFunc.getLowerBound())
						return 0;
					else if (measureResult >= incFunc.getLowerBound())
						return 1;
					else {
						double range = incFunc.getLowerBound()
								- incFunc.getLowerBound();
						double distance = measureResult
								- incFunc.getLowerBound();

						return distance / range;
					}

				} else if (((SingleMeasureEvaluation) eval).getFunction() instanceof LinearDecreasingFunction) {

					LinearIncreasingFunction incFunc = (LinearIncreasingFunction) smEvaluation
							.getFunction();

					if (measureResult <= incFunc.getLowerBound())
						return 1;
					else if (measureResult >= incFunc.getLowerBound())
						return 0;
					else {
						double range = incFunc.getLowerBound()
								- incFunc.getLowerBound();
						double distance = measureResult
								- incFunc.getLowerBound();

						return 1 - distance / range;
					}

				} else
					return -1.0; // not supported single measure evaluation

			} else
				return -1.0; // not a single measure evaluation

		} else
			return -1.0; // not a form-based evaluation

	}
}
