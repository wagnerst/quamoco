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

import java.util.Vector;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.Measure;
import de.quamoco.qm.properties.eval.EnhancedMappingFunction;
import de.quamoco.qm.properties.eval.ParseQIESLException;
import de.quamoco.qm.properties.eval.model.InfluencingOrRefiningElement;

/**
 * Interface to generate evaluations. Updated due to meta model change in March
 * 2011: - Simplified scale types - Supports FormBasedEvaluations - Generalized
 * generateEvalFunction() Interface
 * 
 * @author klaes
 * 
 */
public interface EvaluationGenerator {

	// This enumeration can be extended if needed
	// A class with the corresponding name should implement this class
	public enum MappingFunction {
		LinearIncreasing, LinearDecreasing
	};

	// Measures provide results as number or finding lists
	public enum Scale {
		NUMBER, FINDINGS, UNDEFINED
	};

	// This is a list of supported range calculations
	// Range delivers in any case a LOC number
	public enum Range {
		NA, METHOD, CLASS, FILE
	};

	/**
	 * @param maxPoints
	 *            MaxPoints value of the evaluated factor
	 * @param factors
	 *            Vector of all elements influencing or refining the factor
	 * @return The generated QIESL specification
	 */
	public CompoundCommand generateRankingBasedAggregation(
			EditingDomain editingDomain, Evaluation evaluation, int maxPoints,
			Vector<InfluencingOrRefiningElement<?>> elements);

	/**
	 * @param maxPoints
	 *            MaxPoints value of the evaluated factor
	 * @param factors
	 *            Vector of all elements influencing or refining the factor
	 * @return The generated QIESL specification
	 */
	public CompoundCommand generatePointBasedAggregation(
			EditingDomain editingDomain, Evaluation evaluation, int maxPoints,
			Vector<InfluencingOrRefiningElement<?>> elements);

	/**
	 * // * @deprecated instead the function call with EnhancedMappingFunction
	 * should be used
	 * 
	 * @param maxPoints
	 *            MaxPoints value of the evaluated factor
	 * @param measureNameForEval
	 *            Name of the measure used for the evaluation
	 * @param measureNameForNorm
	 *            Name of the measure used for the normalization
	 * @param scale
	 *            The scale of the measure used for the evaluation
	 * @param range
	 *            The affected code range used for normalization
	 * @param mappingFunction
	 *            The type of mapping function used
	 * @param lowerBound
	 *            Lower bound of mapping function
	 * @param upperBound
	 *            Upper bound of mapping function
	 * @return The generated QIESL specification
	 */
	// TODO rename (command instead of function)
	public CompoundCommand generateEvalFunction(
			EditingDomain editingDomain,
			Evaluation evaluation,
			int maxPoints, // max point of the evaluated factor
			Measure evaluationMeasure, Measure normalizationMeasure,
			Scale scale, Range range, MappingFunction mappingFunction,
			double lowerBound, // of mapping function
			double upperBound // of mapping function
	);

	/**
	 * @param maxPoints
	 *            MaxPoints value of the evaluated factor
	 * @param measureNameForEval
	 *            Name of the measure used for the evaluation
	 * @param measureNameForNorm
	 *            Name of the measure used for the normalization
	 * @param scale
	 *            The scale of the measure used for the evaluation
	 * @param range
	 *            The affected code range used for normalization
	 * @param mappingFunction
	 *            The type of mapping function used
	 * @param lowerBound
	 *            Lower bound of mapping function
	 * @param upperBound
	 *            Upper bound of mapping function
	 * @return The generated QIESL specification
	 */
	public CompoundCommand generateEvalFunction(EditingDomain editingDomain,
			Evaluation evaluation,
			int maxPoints, // max point of the evaluated factor
			Measure evaluationMeasure, Measure normalizationMeasure,
			Scale scale, Range range, EnhancedMappingFunction mappingFunction);

	public boolean isGenerated(Evaluation evaluation);

	public boolean isGeneratedEvaluationFunction(Evaluation evaluation);

	public boolean isGeneratedAggregation(Evaluation evaluation);

	public int getMaxPoints(Evaluation evaluation) throws ParseQIESLException;

	public Measure getEvaluationMeasure(Evaluation evaluation);

	public Measure getNormalizationMeasure(Evaluation evaluation);

	public Scale getScale(Evaluation evaluation) throws ParseQIESLException;;

	public Range getRange(Evaluation evaluation) throws ParseQIESLException;

	public MappingFunction getMappingFunction(Evaluation evaluation)
			throws ParseQIESLException;

	public double getLowerBound(Evaluation evaluation)
			throws ParseQIESLException;

	public double getUpperBound(Evaluation evaluation)
			throws ParseQIESLException;

	public Vector<InfluencingOrRefiningElement<Factor>> getCurrentlyUsedFactors(
			Evaluation evaluation) throws ParseQIESLException;

	public Vector<InfluencingOrRefiningElement<Measure>> getCurrentlyUsedMeasures(
			Evaluation evaluation);

}
