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

/**
 * 
 */
package de.quamoco.qm.properties.eval.generator;

import java.util.StringTokenizer;
import java.util.Vector;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.Measure;
import de.quamoco.qm.QIESLEvaluation;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.properties.eval.EnhancedMappingFunction;
import de.quamoco.qm.properties.eval.ParseQIESLException;
import de.quamoco.qm.properties.eval.model.InfluencingOrRefiningElement;
import de.quamoco.qm.properties.eval.model.InfluencingOrRefiningFactor;

/**
 * This is the standard implementation of the QIESL generator that allows
 * creating typical QIESL evaluations.
 * 
 * @author klaes
 * 
 */
public class QIESLGeneratorImp implements EvaluationGenerator {

	/** Force user to use {@link EvaluationGeneratorFactory} */
	protected QIESLGeneratorImp() {
	}

	@Override
	public CompoundCommand generateRankingBasedAggregation(
			EditingDomain editingDomain, Evaluation evaluation, int maxPoints,
			Vector<InfluencingOrRefiningElement<?>> factors) {

		// Step 1: Calculate the contribution points
		calulateContributioPointsAndUpdate(factors, maxPoints);

		// Step 2: Generate QIESEL equation
		StringBuffer equation = generateFactorAggreationEquation(factors);

		// Step 3: Generate the textual representation
		StringBuffer s = new StringBuffer();

		s.append("// AGGREGATION (FACTOR RANKING)\n");
		s.append("// GENERATED QIESL – DO NOT MODIFY\n");
		s.append("// BASED ON MAXPOINT VALUE = " + maxPoints + "\n");

		for (InfluencingOrRefiningElement<?> factor : factors) {
			s.append("// FACTOR " + factor.toString() + "\n");
		}

		s.append("\n");
		s.append(equation);

		String qieslString = s.toString();

		SetCommand setSpecificationCommand = new SetCommand(editingDomain,
				evaluation, QmPackage.Literals.TEXT_EVALUATION__SPECIFICATION,
				qieslString);

		CompoundCommand result = new CompoundCommand(
				"Generate ranking based aggregation");
		result.append(setSpecificationCommand);
		return result;
	}

	@Override
	public CompoundCommand generatePointBasedAggregation(
			EditingDomain editingDomain, Evaluation evaluation, int maxPoints,
			Vector<InfluencingOrRefiningElement<?>> factors) {

		// Step 1: Set all rankings to -1
		for (InfluencingOrRefiningElement<?> factor : factors)
			factor.setRanking(-1);

		// Step 2: Generate QIESEL equation
		StringBuffer equation = generateFactorAggreationEquation(factors);

		// Step 3: Generate the textual representation
		StringBuffer s = new StringBuffer();

		s.append("// AGGREGATION (CONTRIBUTION POINTS)\n");
		s.append("// GENERATED QIESL – DO NOT MODIFY\n");
		s.append("// BASED ON MAXPOINT VALUE = " + maxPoints + "\n");

		for (InfluencingOrRefiningElement<?> factor : factors) {
			s.append("// FACTOR " + factor.toString() + "\n");
		}

		s.append("\n");
		s.append(equation);

		String qieslString = s.toString();

		SetCommand setSpecificationCommand = new SetCommand(editingDomain,
				evaluation, QmPackage.Literals.TEXT_EVALUATION__SPECIFICATION,
				qieslString);

		CompoundCommand result = new CompoundCommand(
				"Generate point based aggregation");
		result.append(setSpecificationCommand);
		return result;
	}

	/**
	 * Takes the list of factors contributing to the evaluation with up-to-date
	 * contribution points and calculate the respective evaluation equation in
	 * QIESL.
	 * 
	 * @param factors
	 *            the list of contribution factors
	 * @return evaluation equation based on QIESL
	 */
	private StringBuffer generateFactorAggreationEquation(
			Vector<InfluencingOrRefiningElement<?>> factors) {

		StringBuffer equation = new StringBuffer();

		equation.append("result = 0\n");

		for (InfluencingOrRefiningElement<?> factor : factors) {

			// insert line only if the factor contribute to the evaluation
			// result
			if (factor.getContPoints() != 0) {

				// insert distribution function
				if (factor.getType().equals(
						InfluencingOrRefiningElement.Type.positive)
						| factor.getType().equals(
								InfluencingOrRefiningElement.Type.refinement)) {
					equation.append("  +linearDistribution(");

				} else if (factor.getType().equals(
						InfluencingOrRefiningElement.Type.negative)) {
					equation.append("  +negativeLinearDistribution(");

				} else
					throw new RuntimeException(
							"QIESLGeneratorImp: Relationship type unknown");

				equation.append(factor.getContPoints() + ",");

				equation.append("%%" + factor.getName() + "%%");

				equation.append("/" + factor.getMaxPoints());

				// close distribution function
				equation.append(")\n");
			}
		}
		return equation;
	}

	@Override
	public CompoundCommand generateEvalFunction(EditingDomain editingDomain,
			Evaluation evaluation, int maxPoints, Measure evaluationMeasure,
			Measure normalizationMeasure, Scale scale, Range range,
			MappingFunction mappingFunction, double lowerBound,
			double upperBound) {

		String measureNameForEval = evaluationMeasure.getQualifiedName();
		String measureNameForNorm = normalizationMeasure.getQualifiedName();

		// Step 1: Generate QIESEL equation

		StringBuffer equation = new StringBuffer();

		// generate the equation for number scale
		// percentage or point scale are no more supported by the meta-model

		if (scale.equals(Scale.NUMBER)) {

			equation.append("result = ");

			// insert distribution function
			equation.append("linearDistribution(");

			// insert evaluated measure
			equation.append("%%" + measureNameForEval + "%%");

			// insert normalization measure if defined
			if (!measureNameForNorm.isEmpty()) { // normalization measure is
				// selected
				equation.append("/%%" + measureNameForNorm + "%%");
			}

			// insert bounds and points
			if (mappingFunction.equals(MappingFunction.LinearIncreasing)) {
				equation.append("," + lowerBound);
				equation.append(",0");
				equation.append("," + upperBound);
				equation.append("," + maxPoints);
			} else if (mappingFunction.equals(MappingFunction.LinearDecreasing)) {
				equation.append("," + lowerBound);
				equation.append("," + maxPoints);
				equation.append("," + upperBound);
				equation.append(",0");
			} else
				throw new RuntimeException(
						"QIESLGeneratorImp: MappingFunction unknown");

			// close equation
			equation.append(")");
		}

		// generate the equation for findings scale
		if (scale.equals(Scale.FINDINGS)) {

			equation.append("result = ");

			// insert distribution function
			equation.append("linearDistribution(\n");

			// insert extent go get a number
			equation.append("extent(");

			// if a range is used insert it
			if (!range.equals(Range.NA)) {
				if (range.equals(Range.CLASS))
					equation.append("classRange(");
				if (range.equals(Range.FILE))
					equation.append("fileRange(");
				if (range.equals(Range.METHOD))
					equation.append("methodRange(");
			}

			// insert evaluated measure
			equation.append("%%" + measureNameForEval + "%%");

			// close range function if used
			if (!range.equals(Range.NA))
				equation.append(")");

			// close extent function
			equation.append(")\n");

			// insert normalization measure if defined
			if (!measureNameForNorm.isEmpty()) { // normalization measure is
				// selected
				equation.append("/%%" + measureNameForNorm + "%%");
			}

			// insert bounds and points
			if (mappingFunction.equals(MappingFunction.LinearIncreasing)) {
				equation.append("," + lowerBound);
				equation.append(",0");
				equation.append("," + upperBound);
				equation.append("," + maxPoints);
			} else if (mappingFunction.equals(MappingFunction.LinearDecreasing)) {
				equation.append("," + lowerBound);
				equation.append("," + maxPoints);
				equation.append("," + upperBound);
				equation.append(",0");
			} else
				throw new RuntimeException(
						"QIESLGeneratorImp: MappingFunction unknown");

			// close equation
			equation.append(")");
		}

		// Step 2: Generate the textual representation

		StringBuffer s = new StringBuffer();

		s.append("// EVALUATION FUNCTION (" + measureNameForEval + ")\n");
		s.append("// GENERATED QIESL – DO NOT MODIFY\n");
		s.append("// BASED ON MAXPOINT VALUE = " + maxPoints + "\n");
		s.append("// Measure used for evaluation = " + measureNameForEval
				+ "\n");
		s.append("// Scale = " + scale + "\n");
		s.append("// Range = " + range + "\n");
		s.append("// Measure used for normalization = " + measureNameForNorm
				+ "\n");
		s.append("// Mapping function = " + mappingFunction + "\n");
		s.append("// Lower bound = " + lowerBound + "\n");
		s.append("// Upper bound = " + upperBound + "\n");
		s.append("\n");
		s.append(equation);

		String qieslString = s.toString();

		SetCommand setSpecificationCommand = new SetCommand(editingDomain,
				evaluation, QmPackage.Literals.TEXT_EVALUATION__SPECIFICATION,
				qieslString);

		CompoundCommand result = new CompoundCommand(
				"Generate evaluation function");
		result.append(setSpecificationCommand);
		return result;

	}

	@Override
	public boolean isGenerated(Evaluation evaluation) {
		if (evaluation instanceof QIESLEvaluation) {
			QIESLEvaluation qieslEvaluation = (QIESLEvaluation) evaluation;
			String qiesl = qieslEvaluation.getSpecification();
			return (qiesl != null)
					&& (qiesl.contains("// GENERATED QIESL – DO NOT MODIFY\n"));
		}
		return false;
	}

	@Override
	public boolean isGeneratedEvaluationFunction(Evaluation evaluation) {
		if (evaluation instanceof QIESLEvaluation) {
			QIESLEvaluation qieslEvaluation = (QIESLEvaluation) evaluation;
			String qiesl = qieslEvaluation.getSpecification();
			return (qiesl != null)
					&& (qiesl.contains("// EVALUATION FUNCTION"));
		}
		return false;
	}

	@Override
	public boolean isGeneratedAggregation(Evaluation evaluation) {
		if (evaluation instanceof QIESLEvaluation) {
			QIESLEvaluation qieslEvaluation = (QIESLEvaluation) evaluation;
			String qiesl = qieslEvaluation.getSpecification();
			return (qiesl != null)
					&& ((qiesl.contains("// AGGREGATION (FACTOR RANKING)")) | (qiesl
							.contains("// AGGREGATION (CONTRIBUTION POINTS)")));
		}
		return false;
	}

	@Override
	public int getMaxPoints(Evaluation evaluation) throws ParseQIESLException {
		if (evaluation instanceof QIESLEvaluation) {
			QIESLEvaluation qieslEvaluation = (QIESLEvaluation) evaluation;
			String qiesl = qieslEvaluation.getSpecification();
			StringTokenizer st = new StringTokenizer(qiesl, "\n", false);

			while (st.hasMoreTokens()) {
				String s = st.nextToken();
				if (s.startsWith("// BASED ON MAXPOINT VALUE = ")) {
					int start = s.indexOf("=");
					String content = s.substring(start + 1);
					String trimmed = content.trim();
					return Integer.parseInt(trimmed);
				}
			}
			throw new ParseQIESLException("Cannot determine MaxPoints value");
		}
		throw new ParseQIESLException("Cannot determine MaxPoints value");
	}

	@Override
	public Measure getEvaluationMeasure(Evaluation evaluation) {
		if (evaluation instanceof QIESLEvaluation) {
			QIESLEvaluation qieslEvaluation = (QIESLEvaluation) evaluation;
			String qiesl = qieslEvaluation.getSpecification();
			StringTokenizer st = new StringTokenizer(qiesl, "\n", false);

			while (st.hasMoreTokens()) {
				String s = st.nextToken();
				if (s.startsWith("// Measure used for evaluation = ")) {
					int start = s.indexOf("=");
					String content = s.substring(start + 1);
					String result = content.trim();

					for (Measure measure : evaluation.getQualityModel()
							.getMeasures()) {
						if (result.equals(measure.getQualifiedName())) {
							return measure;
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public Measure getNormalizationMeasure(Evaluation evaluation) {
		if (evaluation instanceof QIESLEvaluation) {
			QIESLEvaluation qieslEvaluation = (QIESLEvaluation) evaluation;
			String qiesl = qieslEvaluation.getSpecification();
			StringTokenizer st = new StringTokenizer(qiesl, "\n", false);

			while (st.hasMoreTokens()) {
				String s = st.nextToken();
				if (s.startsWith("// Measure used for normalization = ")) {
					int start = s.indexOf("=");
					String content = s.substring(start + 1);
					String result = content.trim();

					for (Measure measure : evaluation.getQualityModel()
							.getMeasures()) {
						if (result.equals(measure.getQualifiedName())) {
							return measure;
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public Scale getScale(Evaluation evaluation) throws ParseQIESLException {
		if (evaluation instanceof QIESLEvaluation) {
			QIESLEvaluation qieslEvaluation = (QIESLEvaluation) evaluation;
			String qiesl = qieslEvaluation.getSpecification();
			StringTokenizer st = new StringTokenizer(qiesl, "\n", false);

			while (st.hasMoreTokens()) {
				String s = st.nextToken();
				if (s.startsWith("// Scale = ")) {
					int start = s.indexOf("=");
					String content = s.substring(start + 1);
					String trimmed = content.trim();

					if (trimmed.equals("FINDINGS"))
						return Scale.FINDINGS;
					else if (trimmed.equals("NUMBER"))
						return Scale.NUMBER;
					// "PERCENTAGE" and "POINTS" are no more supported by the
					// meta-model
					else
						return Scale.UNDEFINED;

				}
			}
			throw new ParseQIESLException("Cannot determine Scale");
		}
		throw new ParseQIESLException("Cannot determine Scale");
	}

	@Override
	public Range getRange(Evaluation evaluation) throws ParseQIESLException {
		if (evaluation instanceof QIESLEvaluation) {
			QIESLEvaluation qieslEvaluation = (QIESLEvaluation) evaluation;
			String qiesl = qieslEvaluation.getSpecification();
			StringTokenizer st = new StringTokenizer(qiesl, "\n", false);

			while (st.hasMoreTokens()) {
				String s = st.nextToken();
				if (s.startsWith("// Range = ")) {
					int start = s.indexOf("=");
					String content = s.substring(start + 1);
					String trimmed = content.trim();

					if (trimmed.equals("CLASS"))
						return Range.CLASS;
					else if (trimmed.equals("FILE"))
						return Range.FILE;
					else if (trimmed.equals("METHOD"))
						return Range.METHOD;
					else if (trimmed.equals("NA"))
						return Range.NA;

				}
			}
			throw new ParseQIESLException("Cannot determine Range");
		}
		throw new ParseQIESLException("Cannot determine Range");
	}

	@Override
	public MappingFunction getMappingFunction(Evaluation evaluation)
			throws ParseQIESLException {
		if (evaluation instanceof QIESLEvaluation) {
			QIESLEvaluation qieslEvaluation = (QIESLEvaluation) evaluation;
			String qiesl = qieslEvaluation.getSpecification();
			StringTokenizer st = new StringTokenizer(qiesl, "\n", false);

			while (st.hasMoreTokens()) {
				String s = st.nextToken();
				if (s.startsWith("// Mapping function = ")) {
					int start = s.indexOf("=");
					String content = s.substring(start + 1);
					String trimmed = content.trim();

					if (trimmed.equals("LinearIncreasing"))
						return MappingFunction.LinearIncreasing;
					else if (trimmed.equals("LinearDecreasing"))
						return MappingFunction.LinearDecreasing;
				}
			}
			throw new ParseQIESLException("Cannot determine Mapping function");
		}
		throw new ParseQIESLException("Cannot determine Mapping function");
	}

	@Override
	public double getLowerBound(Evaluation evaluation)
			throws ParseQIESLException {
		if (evaluation instanceof QIESLEvaluation) {
			QIESLEvaluation qieslEvaluation = (QIESLEvaluation) evaluation;
			String qiesl = qieslEvaluation.getSpecification();
			StringTokenizer st = new StringTokenizer(qiesl, "\n", false);

			while (st.hasMoreTokens()) {
				String s = st.nextToken();
				if (s.startsWith("// Lower bound = ")) {
					int start = s.indexOf("=");
					String content = s.substring(start + 1);
					String trimmed = content.trim();
					return Double.parseDouble(trimmed);
				}
			}
			throw new ParseQIESLException("Cannot determine Lower bound value");
		}
		throw new ParseQIESLException("Cannot determine Lower bound value");
	}

	@Override
	public double getUpperBound(Evaluation evaluation)
			throws ParseQIESLException {
		if (evaluation instanceof QIESLEvaluation) {
			QIESLEvaluation qieslEvaluation = (QIESLEvaluation) evaluation;
			String qiesl = qieslEvaluation.getSpecification();
			StringTokenizer st = new StringTokenizer(qiesl, "\n", false);

			while (st.hasMoreTokens()) {
				String s = st.nextToken();
				if (s.startsWith("// Upper bound = ")) {
					int start = s.indexOf("=");
					String content = s.substring(start + 1);
					String trimmed = content.trim();
					return Double.parseDouble(trimmed);
				}
			}
			throw new ParseQIESLException("Cannot determine Upper bound value");
		}
		throw new ParseQIESLException("Cannot determine Upper bound value");
	}

	@Override
	public Vector<InfluencingOrRefiningElement<Factor>> getCurrentlyUsedFactors(
			Evaluation evaluation) throws ParseQIESLException {
		Vector<InfluencingOrRefiningElement<Factor>> factors = new Vector<InfluencingOrRefiningElement<Factor>>();
		if (evaluation instanceof QIESLEvaluation) {
			QIESLEvaluation qieslEvaluation = (QIESLEvaluation) evaluation;
			String qiesl = qieslEvaluation.getSpecification();

			StringTokenizer st = new StringTokenizer(qiesl, "\n", false);

			while (st.hasMoreTokens()) {
				String s = st.nextToken();
				if (s.startsWith("// FACTOR ")) {
					int start = s.indexOf("R");
					String content = s.substring(start + 1);
					String factorLine = content.trim();

					StringTokenizer st2 = new StringTokenizer(factorLine, ";",
							false);

					String name = st2.nextToken().trim();

					int ranking = Integer.parseInt(st2.nextToken().trim());
					double contPoints = Double.parseDouble(st2.nextToken()
							.trim());
					int maxPoints = Integer.parseInt(st2.nextToken().trim());
					InfluencingOrRefiningElement.Type type = InfluencingOrRefiningElement
							.parseType(st2.nextToken().trim());
					String modul = st2.nextToken().trim();

					for (Factor qmFactor : evaluation.getQualityModel()
							.getFactors()) {
						if (name.equals(qmFactor.getQualifiedName())) {
							InfluencingOrRefiningElement<Factor> factor = new InfluencingOrRefiningFactor(
									qmFactor, ranking, contPoints, maxPoints,
									type, modul);
							factors.add(factor);
							break;
						}
					}

				}
			}
		}
		return factors;
	}

	private void calulateContributioPointsAndUpdate(
			Vector<InfluencingOrRefiningElement<?>> factors, int maxPoints) {

		int numberOfRankings = 0;
		int highestRanking = 0;

		for (InfluencingOrRefiningElement<?> factor : factors) {

			if (factor.getRanking() > 0)
				numberOfRankings++;

			if (factor.getRanking() > highestRanking)
				highestRanking = factor.getRanking();

			// initialize 0 contribution for all factors
			factor.setContPoints(0.0);
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

			for (InfluencingOrRefiningElement<?> factor : factors) {
				if (factor.getRanking() == i)
					usagesOfRanking++;
			}

			if (usagesOfRanking > 0) {

				for (int j = 1; j <= usagesOfRanking; j++) {
					weight += weights[weightIndex];
					weightIndex++;
				}

				weight = weight / usagesOfRanking;

				for (InfluencingOrRefiningElement<?> factor : factors) {

					if (factor.getRanking() == i)
						factor.setContPoints(weight * maxPoints);
				}
			}
		}
	}

	@Override
	public CompoundCommand generateEvalFunction(EditingDomain editingDomain,
			Evaluation evaluation, int maxPoints, Measure evaluationMeasure,
			Measure normalizationMeasure, Scale scale, Range range,
			EnhancedMappingFunction mappingFunction) {

		if (mappingFunction.getType() == EvaluationGenerator.MappingFunction.LinearDecreasing
				| mappingFunction.getType() == EvaluationGenerator.MappingFunction.LinearIncreasing) {

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

	@Override
	public Vector<InfluencingOrRefiningElement<Measure>> getCurrentlyUsedMeasures(
			Evaluation evaluation) {
		throw new UnsupportedOperationException(
				"QIESL is not supported for MultiMeasureEvaluations!");
	}

}
