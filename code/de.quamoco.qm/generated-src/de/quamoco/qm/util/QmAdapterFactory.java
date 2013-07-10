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
 * $Id: QmAdapterFactory.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.util;

import de.quamoco.qm.*;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.AnnotationBase;
import de.quamoco.qm.CharacterizingElement;
import de.quamoco.qm.Decomposition;
import de.quamoco.qm.DescribedElement;
import de.quamoco.qm.Entity;
import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FactorAggregation;
import de.quamoco.qm.FactorRanking;
import de.quamoco.qm.FactorRefinement;
import de.quamoco.qm.FindingsUnionMeasureAggregation;
import de.quamoco.qm.FormBasedEvaluation;
import de.quamoco.qm.FormBasedMeasureAggregation;
import de.quamoco.qm.Function;
import de.quamoco.qm.Impact;
import de.quamoco.qm.Instrument;
import de.quamoco.qm.LinearDecreasingFunction;
import de.quamoco.qm.LinearFunction;
import de.quamoco.qm.LinearIncreasingFunction;
import de.quamoco.qm.ManualEvaluation;
import de.quamoco.qm.ManualInstrument;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasureAggregation;
import de.quamoco.qm.MeasureEvaluation;
import de.quamoco.qm.MeasureRanking;
import de.quamoco.qm.MeasureRefinement;
import de.quamoco.qm.Measurement;
import de.quamoco.qm.MeasurementMethod;
import de.quamoco.qm.MultiMeasureEvaluation;
import de.quamoco.qm.NamedElement;
import de.quamoco.qm.NormalizationMeasure;
import de.quamoco.qm.NumberMeanMeasureAggregation;
import de.quamoco.qm.QIESLAggregation;
import de.quamoco.qm.QIESLEvaluation;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.QualityModelElement;
import de.quamoco.qm.Ranking;
import de.quamoco.qm.SingleMeasureEvaluation;
import de.quamoco.qm.Source;
import de.quamoco.qm.Specialization;
import de.quamoco.qm.Tag;
import de.quamoco.qm.TaggedElement;
import de.quamoco.qm.TextAggregation;
import de.quamoco.qm.TextEvaluation;
import de.quamoco.qm.Tool;
import de.quamoco.qm.ToolBasedInstrument;
import de.quamoco.qm.WeightedSumFactorAggregation;
import de.quamoco.qm.WeightedSumMultiMeasureEvaluation;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.quamoco.qm.QmPackage
 * @generated
 */
public class QmAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static QmPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QmAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = QmPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QmSwitch<Adapter> modelSwitch =
		new QmSwitch<Adapter>() {
			@Override
			public Adapter caseQualityModel(QualityModel object) {
				return createQualityModelAdapter();
			}
			@Override
			public Adapter caseQualityModelElement(QualityModelElement object) {
				return createQualityModelElementAdapter();
			}
			@Override
			public Adapter caseSource(Source object) {
				return createSourceAdapter();
			}
			@Override
			public Adapter caseDescribedElement(DescribedElement object) {
				return createDescribedElementAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object) {
				return createNamedElementAdapter();
			}
			@Override
			public Adapter caseAnnotatedElement(AnnotatedElement object) {
				return createAnnotatedElementAdapter();
			}
			@Override
			public Adapter caseAnnotation(Map.Entry<String, String> object) {
				return createAnnotationAdapter();
			}
			@Override
			public Adapter caseTag(Tag object) {
				return createTagAdapter();
			}
			@Override
			public Adapter caseTaggedElement(TaggedElement object) {
				return createTaggedElementAdapter();
			}
			@Override
			public Adapter caseEntity(Entity object) {
				return createEntityAdapter();
			}
			@Override
			public Adapter caseSpecialization(Specialization object) {
				return createSpecializationAdapter();
			}
			@Override
			public Adapter caseDecomposition(Decomposition object) {
				return createDecompositionAdapter();
			}
			@Override
			public Adapter caseCharacterizingElement(CharacterizingElement object) {
				return createCharacterizingElementAdapter();
			}
			@Override
			public Adapter caseFactor(Factor object) {
				return createFactorAdapter();
			}
			@Override
			public Adapter caseImpact(Impact object) {
				return createImpactAdapter();
			}
			@Override
			public Adapter caseEvaluation(Evaluation object) {
				return createEvaluationAdapter();
			}
			@Override
			public Adapter caseFactorRefinement(FactorRefinement object) {
				return createFactorRefinementAdapter();
			}
			@Override
			public Adapter caseMeasurement(Measurement object) {
				return createMeasurementAdapter();
			}
			@Override
			public Adapter caseMeasureRefinement(MeasureRefinement object) {
				return createMeasureRefinementAdapter();
			}
			@Override
			public Adapter caseMeasure(Measure object) {
				return createMeasureAdapter();
			}
			@Override
			public Adapter caseMeasurementMethod(MeasurementMethod object) {
				return createMeasurementMethodAdapter();
			}
			@Override
			public Adapter caseMeasureAggregation(MeasureAggregation object) {
				return createMeasureAggregationAdapter();
			}
			@Override
			public Adapter caseInstrument(Instrument object) {
				return createInstrumentAdapter();
			}
			@Override
			public Adapter caseManualInstrument(ManualInstrument object) {
				return createManualInstrumentAdapter();
			}
			@Override
			public Adapter caseToolBasedInstrument(ToolBasedInstrument object) {
				return createToolBasedInstrumentAdapter();
			}
			@Override
			public Adapter caseTool(Tool object) {
				return createToolAdapter();
			}
			@Override
			public Adapter caseAnnotationBase(AnnotationBase object) {
				return createAnnotationBaseAdapter();
			}
			@Override
			public Adapter caseQIESLEvaluation(QIESLEvaluation object) {
				return createQIESLEvaluationAdapter();
			}
			@Override
			public Adapter caseTextEvaluation(TextEvaluation object) {
				return createTextEvaluationAdapter();
			}
			@Override
			public Adapter caseFormBasedEvaluation(FormBasedEvaluation object) {
				return createFormBasedEvaluationAdapter();
			}
			@Override
			public Adapter caseSingleMeasureEvaluation(SingleMeasureEvaluation object) {
				return createSingleMeasureEvaluationAdapter();
			}
			@Override
			public Adapter caseFactorAggregation(FactorAggregation object) {
				return createFactorAggregationAdapter();
			}
			@Override
			public Adapter caseNormalizationMeasure(NormalizationMeasure object) {
				return createNormalizationMeasureAdapter();
			}
			@Override
			public Adapter caseQIESLAggregation(QIESLAggregation object) {
				return createQIESLAggregationAdapter();
			}
			@Override
			public Adapter caseTextAggregation(TextAggregation object) {
				return createTextAggregationAdapter();
			}
			@Override
			public Adapter caseFormBasedMeasureAggregation(FormBasedMeasureAggregation object) {
				return createFormBasedMeasureAggregationAdapter();
			}
			@Override
			public Adapter caseFactorRanking(FactorRanking object) {
				return createFactorRankingAdapter();
			}
			@Override
			public Adapter caseLinearFunction(LinearFunction object) {
				return createLinearFunctionAdapter();
			}
			@Override
			public Adapter caseFunction(Function object) {
				return createFunctionAdapter();
			}
			@Override
			public Adapter caseLinearIncreasingFunction(LinearIncreasingFunction object) {
				return createLinearIncreasingFunctionAdapter();
			}
			@Override
			public Adapter caseLinearDecreasingFunction(LinearDecreasingFunction object) {
				return createLinearDecreasingFunctionAdapter();
			}
			@Override
			public Adapter caseWeightedSumFactorAggregation(WeightedSumFactorAggregation object) {
				return createWeightedSumFactorAggregationAdapter();
			}
			@Override
			public Adapter caseFindingsUnionMeasureAggregation(FindingsUnionMeasureAggregation object) {
				return createFindingsUnionMeasureAggregationAdapter();
			}
			@Override
			public Adapter caseNumberMeanMeasureAggregation(NumberMeanMeasureAggregation object) {
				return createNumberMeanMeasureAggregationAdapter();
			}
			@Override
			public Adapter caseManualEvaluation(ManualEvaluation object) {
				return createManualEvaluationAdapter();
			}
			@Override
			public Adapter caseMeasureEvaluation(MeasureEvaluation object) {
				return createMeasureEvaluationAdapter();
			}
			@Override
			public Adapter caseRanking(Ranking object) {
				return createRankingAdapter();
			}
			@Override
			public Adapter caseMultiMeasureEvaluation(MultiMeasureEvaluation object) {
				return createMultiMeasureEvaluationAdapter();
			}
			@Override
			public Adapter caseWeightedSumMultiMeasureEvaluation(WeightedSumMultiMeasureEvaluation object) {
				return createWeightedSumMultiMeasureEvaluationAdapter();
			}
			@Override
			public Adapter caseMeasureRanking(MeasureRanking object) {
				return createMeasureRankingAdapter();
			}
			@Override
			public Adapter caseQualityModelResult(QualityModelResult object) {
				return createQualityModelResultAdapter();
			}
			@Override
			public Adapter caseMeasurementResult(MeasurementResult object) {
				return createMeasurementResultAdapter();
			}
			@Override
			public Adapter caseNumberMeasurementResult(NumberMeasurementResult object) {
				return createNumberMeasurementResultAdapter();
			}
			@Override
			public Adapter caseFindingsMeasurementResult(FindingsMeasurementResult object) {
				return createFindingsMeasurementResultAdapter();
			}
			@Override
			public Adapter caseDoubleInterval(DoubleInterval object) {
				return createDoubleIntervalAdapter();
			}
			@Override
			public Adapter caseEvaluationResult(EvaluationResult object) {
				return createEvaluationResultAdapter();
			}
			@Override
			public Adapter caseSingleMeasureEvaluationResult(SingleMeasureEvaluationResult object) {
				return createSingleMeasureEvaluationResultAdapter();
			}
			@Override
			public Adapter caseMultiMeasureEvaluationResult(MultiMeasureEvaluationResult object) {
				return createMultiMeasureEvaluationResultAdapter();
			}
			@Override
			public Adapter caseMeasureRankingEvaluationResult(MeasureRankingEvaluationResult object) {
				return createMeasureRankingEvaluationResultAdapter();
			}
			@Override
			public Adapter caseResult(Result object) {
				return createResultAdapter();
			}
			@Override
			public Adapter caseFindingMessage(FindingMessage object) {
				return createFindingMessageAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.DescribedElement <em>Described Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.DescribedElement
	 * @generated
	 */
	public Adapter createDescribedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.Evaluation <em>Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.Evaluation
	 * @generated
	 */
	public Adapter createEvaluationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.FactorRefinement <em>Factor Refinement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.FactorRefinement
	 * @generated
	 */
	public Adapter createFactorRefinementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.Factor <em>Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.Factor
	 * @generated
	 */
	public Adapter createFactorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.Entity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.Entity
	 * @generated
	 */
	public Adapter createEntityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.Impact <em>Impact</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.Impact
	 * @generated
	 */
	public Adapter createImpactAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.QualityModel <em>Quality Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.QualityModel
	 * @generated
	 */
	public Adapter createQualityModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.Instrument <em>Instrument</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.Instrument
	 * @generated
	 */
	public Adapter createInstrumentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.AnnotatedElement <em>Annotated Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.AnnotatedElement
	 * @generated
	 */
	public Adapter createAnnotatedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createAnnotationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.Tag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.Tag
	 * @generated
	 */
	public Adapter createTagAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.TaggedElement <em>Tagged Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.TaggedElement
	 * @generated
	 */
	public Adapter createTaggedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.Measure <em>Measure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.Measure
	 * @generated
	 */
	public Adapter createMeasureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.MeasurementMethod <em>Measurement Method</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.MeasurementMethod
	 * @generated
	 */
	public Adapter createMeasurementMethodAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.CharacterizingElement <em>Characterizing Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.CharacterizingElement
	 * @generated
	 */
	public Adapter createCharacterizingElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.QualityModelElement <em>Quality Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.QualityModelElement
	 * @generated
	 */
	public Adapter createQualityModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.ToolBasedInstrument <em>Tool Based Instrument</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.ToolBasedInstrument
	 * @generated
	 */
	public Adapter createToolBasedInstrumentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.ManualInstrument <em>Manual Instrument</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.ManualInstrument
	 * @generated
	 */
	public Adapter createManualInstrumentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.AnnotationBase <em>Annotation Base</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.AnnotationBase
	 * @generated
	 */
	public Adapter createAnnotationBaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.QIESLEvaluation <em>QIESL Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.QIESLEvaluation
	 * @generated
	 */
	public Adapter createQIESLEvaluationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.TextEvaluation <em>Text Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.TextEvaluation
	 * @generated
	 */
	public Adapter createTextEvaluationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.FormBasedEvaluation <em>Form Based Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.FormBasedEvaluation
	 * @generated
	 */
	public Adapter createFormBasedEvaluationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.SingleMeasureEvaluation <em>Single Measure Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.SingleMeasureEvaluation
	 * @generated
	 */
	public Adapter createSingleMeasureEvaluationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.FactorAggregation <em>Factor Aggregation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.FactorAggregation
	 * @generated
	 */
	public Adapter createFactorAggregationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.NormalizationMeasure <em>Normalization Measure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.NormalizationMeasure
	 * @generated
	 */
	public Adapter createNormalizationMeasureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.QIESLAggregation <em>QIESL Aggregation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.QIESLAggregation
	 * @generated
	 */
	public Adapter createQIESLAggregationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.TextAggregation <em>Text Aggregation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.TextAggregation
	 * @generated
	 */
	public Adapter createTextAggregationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.FormBasedMeasureAggregation <em>Form Based Measure Aggregation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.FormBasedMeasureAggregation
	 * @generated
	 */
	public Adapter createFormBasedMeasureAggregationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.MeasureAggregation <em>Measure Aggregation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.MeasureAggregation
	 * @generated
	 */
	public Adapter createMeasureAggregationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.FactorRanking <em>Factor Ranking</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.FactorRanking
	 * @generated
	 */
	public Adapter createFactorRankingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.LinearFunction <em>Linear Function</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.LinearFunction
	 * @generated
	 */
	public Adapter createLinearFunctionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.Function <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.Function
	 * @generated
	 */
	public Adapter createFunctionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.LinearIncreasingFunction <em>Linear Increasing Function</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.LinearIncreasingFunction
	 * @generated
	 */
	public Adapter createLinearIncreasingFunctionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.LinearDecreasingFunction <em>Linear Decreasing Function</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.LinearDecreasingFunction
	 * @generated
	 */
	public Adapter createLinearDecreasingFunctionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.WeightedSumFactorAggregation <em>Weighted Sum Factor Aggregation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.WeightedSumFactorAggregation
	 * @generated
	 */
	public Adapter createWeightedSumFactorAggregationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.FindingsUnionMeasureAggregation <em>Findings Union Measure Aggregation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.FindingsUnionMeasureAggregation
	 * @generated
	 */
	public Adapter createFindingsUnionMeasureAggregationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.NumberMeanMeasureAggregation <em>Number Mean Measure Aggregation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.NumberMeanMeasureAggregation
	 * @generated
	 */
	public Adapter createNumberMeanMeasureAggregationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.ManualEvaluation <em>Manual Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.ManualEvaluation
	 * @generated
	 */
	public Adapter createManualEvaluationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.MeasureEvaluation <em>Measure Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.MeasureEvaluation
	 * @generated
	 */
	public Adapter createMeasureEvaluationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.Ranking <em>Ranking</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.Ranking
	 * @generated
	 */
	public Adapter createRankingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.MultiMeasureEvaluation <em>Multi Measure Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.MultiMeasureEvaluation
	 * @generated
	 */
	public Adapter createMultiMeasureEvaluationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.WeightedSumMultiMeasureEvaluation <em>Weighted Sum Multi Measure Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.WeightedSumMultiMeasureEvaluation
	 * @generated
	 */
	public Adapter createWeightedSumMultiMeasureEvaluationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.MeasureRanking <em>Measure Ranking</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.MeasureRanking
	 * @generated
	 */
	public Adapter createMeasureRankingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.QualityModelResult <em>Quality Model Result</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.QualityModelResult
	 * @generated
	 */
	public Adapter createQualityModelResultAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.Result <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.Result
	 * @generated
	 */
	public Adapter createResultAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.FindingMessage <em>Finding Message</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.FindingMessage
	 * @generated
	 */
	public Adapter createFindingMessageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.MeasurementResult <em>Measurement Result</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.MeasurementResult
	 * @generated
	 */
	public Adapter createMeasurementResultAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.NumberMeasurementResult <em>Number Measurement Result</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.NumberMeasurementResult
	 * @generated
	 */
	public Adapter createNumberMeasurementResultAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.FindingsMeasurementResult <em>Findings Measurement Result</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.FindingsMeasurementResult
	 * @generated
	 */
	public Adapter createFindingsMeasurementResultAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.DoubleInterval <em>Double Interval</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.DoubleInterval
	 * @generated
	 */
	public Adapter createDoubleIntervalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.EvaluationResult <em>Evaluation Result</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.EvaluationResult
	 * @generated
	 */
	public Adapter createEvaluationResultAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.SingleMeasureEvaluationResult <em>Single Measure Evaluation Result</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.SingleMeasureEvaluationResult
	 * @generated
	 */
	public Adapter createSingleMeasureEvaluationResultAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.MultiMeasureEvaluationResult <em>Multi Measure Evaluation Result</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.MultiMeasureEvaluationResult
	 * @generated
	 */
	public Adapter createMultiMeasureEvaluationResultAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.MeasureRankingEvaluationResult <em>Measure Ranking Evaluation Result</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.MeasureRankingEvaluationResult
	 * @generated
	 */
	public Adapter createMeasureRankingEvaluationResultAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.Tool <em>Tool</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.Tool
	 * @generated
	 */
	public Adapter createToolAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.Source <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.Source
	 * @generated
	 */
	public Adapter createSourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.Measurement <em>Measurement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.Measurement
	 * @generated
	 */
	public Adapter createMeasurementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.Specialization <em>Specialization</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.Specialization
	 * @generated
	 */
	public Adapter createSpecializationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.Decomposition <em>Decomposition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.Decomposition
	 * @generated
	 */
	public Adapter createDecompositionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.MeasureRefinement <em>Measure Refinement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.MeasureRefinement
	 * @generated
	 */
	public Adapter createMeasureRefinementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //QmAdapterFactory
