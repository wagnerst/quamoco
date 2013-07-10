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
 * $Id: QmFactoryImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.impl;

import de.quamoco.qm.*;
import edu.tum.cs.conqat.quamoco.FindingCollection;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import de.quamoco.qm.Decomposition;
import de.quamoco.qm.Effect;
import de.quamoco.qm.Entity;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FactorRanking;
import de.quamoco.qm.FactorRefinement;
import de.quamoco.qm.FindingsUnionMeasureAggregation;
import de.quamoco.qm.Impact;
import de.quamoco.qm.LinearDecreasingFunction;
import de.quamoco.qm.LinearIncreasingFunction;
import de.quamoco.qm.ManualEvaluation;
import de.quamoco.qm.ManualInstrument;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasureRanking;
import de.quamoco.qm.MeasureRefinement;
import de.quamoco.qm.Measurement;
import de.quamoco.qm.NormalizationMeasure;
import de.quamoco.qm.NumberMeanMeasureAggregation;
import de.quamoco.qm.QIESLAggregation;
import de.quamoco.qm.QIESLEvaluation;
import de.quamoco.qm.QmFactory;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.SingleMeasureEvaluation;
import de.quamoco.qm.Source;
import de.quamoco.qm.Specialization;
import de.quamoco.qm.Tag;
import de.quamoco.qm.TextAggregation;
import de.quamoco.qm.TextEvaluation;
import de.quamoco.qm.Tool;
import de.quamoco.qm.ToolBasedInstrument;
import de.quamoco.qm.Type;
import de.quamoco.qm.WeightedSumFactorAggregation;
import de.quamoco.qm.WeightedSumMultiMeasureEvaluation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class QmFactoryImpl extends EFactoryImpl implements QmFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static QmFactory init() {
		try {
			QmFactory theQmFactory = (QmFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.quamoco.de/qm/v17"); 
			if (theQmFactory != null) {
				return theQmFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new QmFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QmFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case QmPackage.QUALITY_MODEL: return createQualityModel();
			case QmPackage.SOURCE: return createSource();
			case QmPackage.ANNOTATION: return (EObject)createAnnotation();
			case QmPackage.TAG: return createTag();
			case QmPackage.ENTITY: return createEntity();
			case QmPackage.SPECIALIZATION: return createSpecialization();
			case QmPackage.DECOMPOSITION: return createDecomposition();
			case QmPackage.FACTOR: return createFactor();
			case QmPackage.IMPACT: return createImpact();
			case QmPackage.FACTOR_REFINEMENT: return createFactorRefinement();
			case QmPackage.MEASUREMENT: return createMeasurement();
			case QmPackage.MEASURE_REFINEMENT: return createMeasureRefinement();
			case QmPackage.MEASURE: return createMeasure();
			case QmPackage.MANUAL_INSTRUMENT: return createManualInstrument();
			case QmPackage.TOOL_BASED_INSTRUMENT: return createToolBasedInstrument();
			case QmPackage.TOOL: return createTool();
			case QmPackage.QIESL_EVALUATION: return createQIESLEvaluation();
			case QmPackage.TEXT_EVALUATION: return createTextEvaluation();
			case QmPackage.SINGLE_MEASURE_EVALUATION: return createSingleMeasureEvaluation();
			case QmPackage.NORMALIZATION_MEASURE: return createNormalizationMeasure();
			case QmPackage.QIESL_AGGREGATION: return createQIESLAggregation();
			case QmPackage.TEXT_AGGREGATION: return createTextAggregation();
			case QmPackage.FACTOR_RANKING: return createFactorRanking();
			case QmPackage.LINEAR_INCREASING_FUNCTION: return createLinearIncreasingFunction();
			case QmPackage.LINEAR_DECREASING_FUNCTION: return createLinearDecreasingFunction();
			case QmPackage.WEIGHTED_SUM_FACTOR_AGGREGATION: return createWeightedSumFactorAggregation();
			case QmPackage.FINDINGS_UNION_MEASURE_AGGREGATION: return createFindingsUnionMeasureAggregation();
			case QmPackage.NUMBER_MEAN_MEASURE_AGGREGATION: return createNumberMeanMeasureAggregation();
			case QmPackage.MANUAL_EVALUATION: return createManualEvaluation();
			case QmPackage.WEIGHTED_SUM_MULTI_MEASURE_EVALUATION: return createWeightedSumMultiMeasureEvaluation();
			case QmPackage.MEASURE_RANKING: return createMeasureRanking();
			case QmPackage.QUALITY_MODEL_RESULT: return createQualityModelResult();
			case QmPackage.NUMBER_MEASUREMENT_RESULT: return createNumberMeasurementResult();
			case QmPackage.FINDINGS_MEASUREMENT_RESULT: return createFindingsMeasurementResult();
			case QmPackage.DOUBLE_INTERVAL: return createDoubleInterval();
			case QmPackage.EVALUATION_RESULT: return createEvaluationResult();
			case QmPackage.SINGLE_MEASURE_EVALUATION_RESULT: return createSingleMeasureEvaluationResult();
			case QmPackage.MULTI_MEASURE_EVALUATION_RESULT: return createMultiMeasureEvaluationResult();
			case QmPackage.MEASURE_RANKING_EVALUATION_RESULT: return createMeasureRankingEvaluationResult();
			case QmPackage.FINDING_MESSAGE: return createFindingMessage();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case QmPackage.EFFECT:
				return createEffectFromString(eDataType, initialValue);
			case QmPackage.TYPE:
				return createTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case QmPackage.EFFECT:
				return convertEffectToString(eDataType, instanceValue);
			case QmPackage.TYPE:
				return convertTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FactorRefinement createFactorRefinement() {
		FactorRefinementImpl factorRefinement = new FactorRefinementImpl();
		return factorRefinement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Factor createFactor() {
		FactorImpl factor = new FactorImpl();
		return factor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entity createEntity() {
		EntityImpl entity = new EntityImpl();
		return entity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Impact createImpact() {
		ImpactImpl impact = new ImpactImpl();
		return impact;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QualityModel createQualityModel() {
		QualityModelImpl qualityModel = new QualityModelImpl();
		return qualityModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createAnnotation() {
		AnnotationImpl annotation = new AnnotationImpl();
		return annotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tag createTag() {
		TagImpl tag = new TagImpl();
		return tag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Measure createMeasure() {
		MeasureImpl measure = new MeasureImpl();
		return measure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManualInstrument createManualInstrument() {
		ManualInstrumentImpl manualInstrument = new ManualInstrumentImpl();
		return manualInstrument;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ToolBasedInstrument createToolBasedInstrument() {
		ToolBasedInstrumentImpl toolBasedInstrument = new ToolBasedInstrumentImpl();
		return toolBasedInstrument;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QIESLEvaluation createQIESLEvaluation() {
		QIESLEvaluationImpl qieslEvaluation = new QIESLEvaluationImpl();
		return qieslEvaluation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TextEvaluation createTextEvaluation() {
		TextEvaluationImpl textEvaluation = new TextEvaluationImpl();
		return textEvaluation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleMeasureEvaluation createSingleMeasureEvaluation() {
		SingleMeasureEvaluationImpl singleMeasureEvaluation = new SingleMeasureEvaluationImpl();
		return singleMeasureEvaluation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NormalizationMeasure createNormalizationMeasure() {
		NormalizationMeasureImpl normalizationMeasure = new NormalizationMeasureImpl();
		return normalizationMeasure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QIESLAggregation createQIESLAggregation() {
		QIESLAggregationImpl qieslAggregation = new QIESLAggregationImpl();
		return qieslAggregation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TextAggregation createTextAggregation() {
		TextAggregationImpl textAggregation = new TextAggregationImpl();
		return textAggregation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FactorRanking createFactorRanking() {
		FactorRankingImpl factorRanking = new FactorRankingImpl();
		return factorRanking;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinearIncreasingFunction createLinearIncreasingFunction() {
		LinearIncreasingFunctionImpl linearIncreasingFunction = new LinearIncreasingFunctionImpl();
		return linearIncreasingFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinearDecreasingFunction createLinearDecreasingFunction() {
		LinearDecreasingFunctionImpl linearDecreasingFunction = new LinearDecreasingFunctionImpl();
		return linearDecreasingFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeightedSumFactorAggregation createWeightedSumFactorAggregation() {
		WeightedSumFactorAggregationImpl weightedSumFactorAggregation = new WeightedSumFactorAggregationImpl();
		return weightedSumFactorAggregation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FindingsUnionMeasureAggregation createFindingsUnionMeasureAggregation() {
		FindingsUnionMeasureAggregationImpl findingsUnionMeasureAggregation = new FindingsUnionMeasureAggregationImpl();
		return findingsUnionMeasureAggregation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NumberMeanMeasureAggregation createNumberMeanMeasureAggregation() {
		NumberMeanMeasureAggregationImpl numberMeanMeasureAggregation = new NumberMeanMeasureAggregationImpl();
		return numberMeanMeasureAggregation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManualEvaluation createManualEvaluation() {
		ManualEvaluationImpl manualEvaluation = new ManualEvaluationImpl();
		return manualEvaluation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeightedSumMultiMeasureEvaluation createWeightedSumMultiMeasureEvaluation() {
		WeightedSumMultiMeasureEvaluationImpl weightedSumMultiMeasureEvaluation = new WeightedSumMultiMeasureEvaluationImpl();
		return weightedSumMultiMeasureEvaluation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasureRanking createMeasureRanking() {
		MeasureRankingImpl measureRanking = new MeasureRankingImpl();
		return measureRanking;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QualityModelResult createQualityModelResult() {
		QualityModelResultImpl qualityModelResult = new QualityModelResultImpl();
		return qualityModelResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NumberMeasurementResult createNumberMeasurementResult() {
		NumberMeasurementResultImpl numberMeasurementResult = new NumberMeasurementResultImpl();
		return numberMeasurementResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FindingsMeasurementResult createFindingsMeasurementResult() {
		FindingsMeasurementResultImpl findingsMeasurementResult = new FindingsMeasurementResultImpl();
		return findingsMeasurementResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DoubleInterval createDoubleInterval() {
		DoubleIntervalImpl doubleInterval = new DoubleIntervalImpl();
		return doubleInterval;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EvaluationResult createEvaluationResult() {
		EvaluationResultImpl evaluationResult = new EvaluationResultImpl();
		return evaluationResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleMeasureEvaluationResult createSingleMeasureEvaluationResult() {
		SingleMeasureEvaluationResultImpl singleMeasureEvaluationResult = new SingleMeasureEvaluationResultImpl();
		return singleMeasureEvaluationResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultiMeasureEvaluationResult createMultiMeasureEvaluationResult() {
		MultiMeasureEvaluationResultImpl multiMeasureEvaluationResult = new MultiMeasureEvaluationResultImpl();
		return multiMeasureEvaluationResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasureRankingEvaluationResult createMeasureRankingEvaluationResult() {
		MeasureRankingEvaluationResultImpl measureRankingEvaluationResult = new MeasureRankingEvaluationResultImpl();
		return measureRankingEvaluationResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FindingMessage createFindingMessage() {
		FindingMessageImpl findingMessage = new FindingMessageImpl();
		return findingMessage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tool createTool() {
		ToolImpl tool = new ToolImpl();
		return tool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Source createSource() {
		SourceImpl source = new SourceImpl();
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Measurement createMeasurement() {
		MeasurementImpl measurement = new MeasurementImpl();
		return measurement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Specialization createSpecialization() {
		SpecializationImpl specialization = new SpecializationImpl();
		return specialization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Decomposition createDecomposition() {
		DecompositionImpl decomposition = new DecompositionImpl();
		return decomposition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasureRefinement createMeasureRefinement() {
		MeasureRefinementImpl measureRefinement = new MeasureRefinementImpl();
		return measureRefinement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Effect createEffectFromString(EDataType eDataType, String initialValue) {
		Effect result = Effect.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEffectToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type createTypeFromString(EDataType eDataType, String initialValue) {
		Type result = Type.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QmPackage getQmPackage() {
		return (QmPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static QmPackage getPackage() {
		return QmPackage.eINSTANCE;
	}

} //QmFactoryImpl
