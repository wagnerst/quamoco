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
 * <copyright>
 * </copyright>
 *
 * $Id: QmFactory.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.quamoco.qm.QmPackage
 * @generated
 */
public interface QmFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	QmFactory eINSTANCE = de.quamoco.qm.impl.QmFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Factor Refinement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Factor Refinement</em>'.
	 * @generated
	 */
	FactorRefinement createFactorRefinement();

	/**
	 * Returns a new object of class '<em>Factor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Factor</em>'.
	 * @generated
	 */
	Factor createFactor();

	/**
	 * Returns a new object of class '<em>Entity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Entity</em>'.
	 * @generated
	 */
	Entity createEntity();

	/**
	 * Returns a new object of class '<em>Impact</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Impact</em>'.
	 * @generated
	 */
	Impact createImpact();

	/**
	 * Returns a new object of class '<em>Quality Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Quality Model</em>'.
	 * @generated
	 */
	QualityModel createQualityModel();

	/**
	 * Returns a new object of class '<em>Measure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Measure</em>'.
	 * @generated
	 */
	Measure createMeasure();

	/**
	 * Returns a new object of class '<em>Manual Instrument</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Manual Instrument</em>'.
	 * @generated
	 */
	ManualInstrument createManualInstrument();

	/**
	 * Returns a new object of class '<em>Tool Based Instrument</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tool Based Instrument</em>'.
	 * @generated
	 */
	ToolBasedInstrument createToolBasedInstrument();

	/**
	 * Returns a new object of class '<em>QIESL Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>QIESL Evaluation</em>'.
	 * @generated
	 */
	QIESLEvaluation createQIESLEvaluation();

	/**
	 * Returns a new object of class '<em>Text Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Text Evaluation</em>'.
	 * @generated
	 */
	TextEvaluation createTextEvaluation();

	/**
	 * Returns a new object of class '<em>Single Measure Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Single Measure Evaluation</em>'.
	 * @generated
	 */
	SingleMeasureEvaluation createSingleMeasureEvaluation();

	/**
	 * Returns a new object of class '<em>Normalization Measure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Normalization Measure</em>'.
	 * @generated
	 */
	NormalizationMeasure createNormalizationMeasure();

	/**
	 * Returns a new object of class '<em>QIESL Aggregation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>QIESL Aggregation</em>'.
	 * @generated
	 */
	QIESLAggregation createQIESLAggregation();

	/**
	 * Returns a new object of class '<em>Text Aggregation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Text Aggregation</em>'.
	 * @generated
	 */
	TextAggregation createTextAggregation();

	/**
	 * Returns a new object of class '<em>Factor Ranking</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Factor Ranking</em>'.
	 * @generated
	 */
	FactorRanking createFactorRanking();

	/**
	 * Returns a new object of class '<em>Linear Increasing Function</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Linear Increasing Function</em>'.
	 * @generated
	 */
	LinearIncreasingFunction createLinearIncreasingFunction();

	/**
	 * Returns a new object of class '<em>Linear Decreasing Function</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Linear Decreasing Function</em>'.
	 * @generated
	 */
	LinearDecreasingFunction createLinearDecreasingFunction();

	/**
	 * Returns a new object of class '<em>Weighted Sum Factor Aggregation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Weighted Sum Factor Aggregation</em>'.
	 * @generated
	 */
	WeightedSumFactorAggregation createWeightedSumFactorAggregation();

	/**
	 * Returns a new object of class '<em>Findings Union Measure Aggregation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Findings Union Measure Aggregation</em>'.
	 * @generated
	 */
	FindingsUnionMeasureAggregation createFindingsUnionMeasureAggregation();

	/**
	 * Returns a new object of class '<em>Number Mean Measure Aggregation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Number Mean Measure Aggregation</em>'.
	 * @generated
	 */
	NumberMeanMeasureAggregation createNumberMeanMeasureAggregation();

	/**
	 * Returns a new object of class '<em>Manual Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Manual Evaluation</em>'.
	 * @generated
	 */
	ManualEvaluation createManualEvaluation();

	/**
	 * Returns a new object of class '<em>Weighted Sum Multi Measure Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Weighted Sum Multi Measure Evaluation</em>'.
	 * @generated
	 */
	WeightedSumMultiMeasureEvaluation createWeightedSumMultiMeasureEvaluation();

	/**
	 * Returns a new object of class '<em>Measure Ranking</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Measure Ranking</em>'.
	 * @generated
	 */
	MeasureRanking createMeasureRanking();

	/**
	 * Returns a new object of class '<em>Quality Model Result</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Quality Model Result</em>'.
	 * @generated
	 */
	QualityModelResult createQualityModelResult();

	/**
	 * Returns a new object of class '<em>Number Measurement Result</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Number Measurement Result</em>'.
	 * @generated
	 */
	NumberMeasurementResult createNumberMeasurementResult();

	/**
	 * Returns a new object of class '<em>Findings Measurement Result</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Findings Measurement Result</em>'.
	 * @generated
	 */
	FindingsMeasurementResult createFindingsMeasurementResult();

	/**
	 * Returns a new object of class '<em>Double Interval</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Double Interval</em>'.
	 * @generated
	 */
	DoubleInterval createDoubleInterval();

	/**
	 * Returns a new object of class '<em>Evaluation Result</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Evaluation Result</em>'.
	 * @generated
	 */
	EvaluationResult createEvaluationResult();

	/**
	 * Returns a new object of class '<em>Single Measure Evaluation Result</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Single Measure Evaluation Result</em>'.
	 * @generated
	 */
	SingleMeasureEvaluationResult createSingleMeasureEvaluationResult();

	/**
	 * Returns a new object of class '<em>Multi Measure Evaluation Result</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Multi Measure Evaluation Result</em>'.
	 * @generated
	 */
	MultiMeasureEvaluationResult createMultiMeasureEvaluationResult();

	/**
	 * Returns a new object of class '<em>Measure Ranking Evaluation Result</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Measure Ranking Evaluation Result</em>'.
	 * @generated
	 */
	MeasureRankingEvaluationResult createMeasureRankingEvaluationResult();

	/**
	 * Returns a new object of class '<em>Finding Message</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Finding Message</em>'.
	 * @generated
	 */
	FindingMessage createFindingMessage();

	/**
	 * Returns a new object of class '<em>Tool</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tool</em>'.
	 * @generated
	 */
	Tool createTool();

	/**
	 * Returns a new object of class '<em>Source</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Source</em>'.
	 * @generated
	 */
	Source createSource();

	/**
	 * Returns a new object of class '<em>Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Measurement</em>'.
	 * @generated
	 */
	Measurement createMeasurement();

	/**
	 * Returns a new object of class '<em>Specialization</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Specialization</em>'.
	 * @generated
	 */
	Specialization createSpecialization();

	/**
	 * Returns a new object of class '<em>Decomposition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Decomposition</em>'.
	 * @generated
	 */
	Decomposition createDecomposition();

	/**
	 * Returns a new object of class '<em>Measure Refinement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Measure Refinement</em>'.
	 * @generated
	 */
	MeasureRefinement createMeasureRefinement();

	/**
	 * Returns a new object of class '<em>Tag</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag</em>'.
	 * @generated
	 */
	Tag createTag();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	QmPackage getQmPackage();

} //QmFactory
