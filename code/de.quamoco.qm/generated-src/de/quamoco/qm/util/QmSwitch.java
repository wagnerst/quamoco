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
 * $Id: QmSwitch.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.util;

import de.quamoco.qm.*;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
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
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see de.quamoco.qm.QmPackage
 * @generated
 */
public class QmSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static QmPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QmSwitch() {
		if (modelPackage == null) {
			modelPackage = QmPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case QmPackage.QUALITY_MODEL: {
				QualityModel qualityModel = (QualityModel)theEObject;
				T result = caseQualityModel(qualityModel);
				if (result == null) result = caseNamedElement(qualityModel);
				if (result == null) result = caseDescribedElement(qualityModel);
				if (result == null) result = caseAnnotatedElement(qualityModel);
				if (result == null) result = caseTaggedElement(qualityModel);
				if (result == null) result = caseQualityModelElement(qualityModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.QUALITY_MODEL_ELEMENT: {
				QualityModelElement qualityModelElement = (QualityModelElement)theEObject;
				T result = caseQualityModelElement(qualityModelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.SOURCE: {
				Source source = (Source)theEObject;
				T result = caseSource(source);
				if (result == null) result = caseNamedElement(source);
				if (result == null) result = caseDescribedElement(source);
				if (result == null) result = caseAnnotatedElement(source);
				if (result == null) result = caseTaggedElement(source);
				if (result == null) result = caseQualityModelElement(source);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.DESCRIBED_ELEMENT: {
				DescribedElement describedElement = (DescribedElement)theEObject;
				T result = caseDescribedElement(describedElement);
				if (result == null) result = caseAnnotatedElement(describedElement);
				if (result == null) result = caseTaggedElement(describedElement);
				if (result == null) result = caseQualityModelElement(describedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.NAMED_ELEMENT: {
				NamedElement namedElement = (NamedElement)theEObject;
				T result = caseNamedElement(namedElement);
				if (result == null) result = caseDescribedElement(namedElement);
				if (result == null) result = caseAnnotatedElement(namedElement);
				if (result == null) result = caseTaggedElement(namedElement);
				if (result == null) result = caseQualityModelElement(namedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.ANNOTATED_ELEMENT: {
				AnnotatedElement annotatedElement = (AnnotatedElement)theEObject;
				T result = caseAnnotatedElement(annotatedElement);
				if (result == null) result = caseTaggedElement(annotatedElement);
				if (result == null) result = caseQualityModelElement(annotatedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.ANNOTATION: {
				@SuppressWarnings("unchecked") Map.Entry<String, String> annotation = (Map.Entry<String, String>)theEObject;
				T result = caseAnnotation(annotation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.TAG: {
				Tag tag = (Tag)theEObject;
				T result = caseTag(tag);
				if (result == null) result = caseNamedElement(tag);
				if (result == null) result = caseDescribedElement(tag);
				if (result == null) result = caseAnnotatedElement(tag);
				if (result == null) result = caseTaggedElement(tag);
				if (result == null) result = caseQualityModelElement(tag);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.TAGGED_ELEMENT: {
				TaggedElement taggedElement = (TaggedElement)theEObject;
				T result = caseTaggedElement(taggedElement);
				if (result == null) result = caseQualityModelElement(taggedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.ENTITY: {
				Entity entity = (Entity)theEObject;
				T result = caseEntity(entity);
				if (result == null) result = caseNamedElement(entity);
				if (result == null) result = caseDescribedElement(entity);
				if (result == null) result = caseAnnotatedElement(entity);
				if (result == null) result = caseTaggedElement(entity);
				if (result == null) result = caseQualityModelElement(entity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.SPECIALIZATION: {
				Specialization specialization = (Specialization)theEObject;
				T result = caseSpecialization(specialization);
				if (result == null) result = caseAnnotatedElement(specialization);
				if (result == null) result = caseTaggedElement(specialization);
				if (result == null) result = caseQualityModelElement(specialization);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.DECOMPOSITION: {
				Decomposition decomposition = (Decomposition)theEObject;
				T result = caseDecomposition(decomposition);
				if (result == null) result = caseAnnotatedElement(decomposition);
				if (result == null) result = caseTaggedElement(decomposition);
				if (result == null) result = caseQualityModelElement(decomposition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.CHARACTERIZING_ELEMENT: {
				CharacterizingElement characterizingElement = (CharacterizingElement)theEObject;
				T result = caseCharacterizingElement(characterizingElement);
				if (result == null) result = caseNamedElement(characterizingElement);
				if (result == null) result = caseDescribedElement(characterizingElement);
				if (result == null) result = caseAnnotatedElement(characterizingElement);
				if (result == null) result = caseTaggedElement(characterizingElement);
				if (result == null) result = caseQualityModelElement(characterizingElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.FACTOR: {
				Factor factor = (Factor)theEObject;
				T result = caseFactor(factor);
				if (result == null) result = caseCharacterizingElement(factor);
				if (result == null) result = caseNamedElement(factor);
				if (result == null) result = caseDescribedElement(factor);
				if (result == null) result = caseAnnotatedElement(factor);
				if (result == null) result = caseTaggedElement(factor);
				if (result == null) result = caseQualityModelElement(factor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.IMPACT: {
				Impact impact = (Impact)theEObject;
				T result = caseImpact(impact);
				if (result == null) result = caseAnnotatedElement(impact);
				if (result == null) result = caseTaggedElement(impact);
				if (result == null) result = caseQualityModelElement(impact);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.EVALUATION: {
				Evaluation evaluation = (Evaluation)theEObject;
				T result = caseEvaluation(evaluation);
				if (result == null) result = caseNamedElement(evaluation);
				if (result == null) result = caseDescribedElement(evaluation);
				if (result == null) result = caseAnnotatedElement(evaluation);
				if (result == null) result = caseTaggedElement(evaluation);
				if (result == null) result = caseQualityModelElement(evaluation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.FACTOR_REFINEMENT: {
				FactorRefinement factorRefinement = (FactorRefinement)theEObject;
				T result = caseFactorRefinement(factorRefinement);
				if (result == null) result = caseAnnotatedElement(factorRefinement);
				if (result == null) result = caseTaggedElement(factorRefinement);
				if (result == null) result = caseQualityModelElement(factorRefinement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.MEASUREMENT: {
				Measurement measurement = (Measurement)theEObject;
				T result = caseMeasurement(measurement);
				if (result == null) result = caseAnnotatedElement(measurement);
				if (result == null) result = caseTaggedElement(measurement);
				if (result == null) result = caseQualityModelElement(measurement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.MEASURE_REFINEMENT: {
				MeasureRefinement measureRefinement = (MeasureRefinement)theEObject;
				T result = caseMeasureRefinement(measureRefinement);
				if (result == null) result = caseAnnotatedElement(measureRefinement);
				if (result == null) result = caseTaggedElement(measureRefinement);
				if (result == null) result = caseQualityModelElement(measureRefinement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.MEASURE: {
				Measure measure = (Measure)theEObject;
				T result = caseMeasure(measure);
				if (result == null) result = caseCharacterizingElement(measure);
				if (result == null) result = caseNamedElement(measure);
				if (result == null) result = caseDescribedElement(measure);
				if (result == null) result = caseAnnotatedElement(measure);
				if (result == null) result = caseTaggedElement(measure);
				if (result == null) result = caseQualityModelElement(measure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.MEASUREMENT_METHOD: {
				MeasurementMethod measurementMethod = (MeasurementMethod)theEObject;
				T result = caseMeasurementMethod(measurementMethod);
				if (result == null) result = caseAnnotatedElement(measurementMethod);
				if (result == null) result = caseTaggedElement(measurementMethod);
				if (result == null) result = caseQualityModelElement(measurementMethod);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.MEASURE_AGGREGATION: {
				MeasureAggregation measureAggregation = (MeasureAggregation)theEObject;
				T result = caseMeasureAggregation(measureAggregation);
				if (result == null) result = caseMeasurementMethod(measureAggregation);
				if (result == null) result = caseNamedElement(measureAggregation);
				if (result == null) result = caseDescribedElement(measureAggregation);
				if (result == null) result = caseAnnotatedElement(measureAggregation);
				if (result == null) result = caseTaggedElement(measureAggregation);
				if (result == null) result = caseQualityModelElement(measureAggregation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.INSTRUMENT: {
				Instrument instrument = (Instrument)theEObject;
				T result = caseInstrument(instrument);
				if (result == null) result = caseMeasurementMethod(instrument);
				if (result == null) result = caseAnnotatedElement(instrument);
				if (result == null) result = caseTaggedElement(instrument);
				if (result == null) result = caseQualityModelElement(instrument);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.MANUAL_INSTRUMENT: {
				ManualInstrument manualInstrument = (ManualInstrument)theEObject;
				T result = caseManualInstrument(manualInstrument);
				if (result == null) result = caseInstrument(manualInstrument);
				if (result == null) result = caseNamedElement(manualInstrument);
				if (result == null) result = caseMeasurementMethod(manualInstrument);
				if (result == null) result = caseDescribedElement(manualInstrument);
				if (result == null) result = caseAnnotatedElement(manualInstrument);
				if (result == null) result = caseTaggedElement(manualInstrument);
				if (result == null) result = caseQualityModelElement(manualInstrument);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.TOOL_BASED_INSTRUMENT: {
				ToolBasedInstrument toolBasedInstrument = (ToolBasedInstrument)theEObject;
				T result = caseToolBasedInstrument(toolBasedInstrument);
				if (result == null) result = caseInstrument(toolBasedInstrument);
				if (result == null) result = caseMeasurementMethod(toolBasedInstrument);
				if (result == null) result = caseAnnotatedElement(toolBasedInstrument);
				if (result == null) result = caseTaggedElement(toolBasedInstrument);
				if (result == null) result = caseQualityModelElement(toolBasedInstrument);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.TOOL: {
				Tool tool = (Tool)theEObject;
				T result = caseTool(tool);
				if (result == null) result = caseNamedElement(tool);
				if (result == null) result = caseDescribedElement(tool);
				if (result == null) result = caseAnnotatedElement(tool);
				if (result == null) result = caseTaggedElement(tool);
				if (result == null) result = caseQualityModelElement(tool);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.ANNOTATION_BASE: {
				AnnotationBase annotationBase = (AnnotationBase)theEObject;
				T result = caseAnnotationBase(annotationBase);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.QIESL_EVALUATION: {
				QIESLEvaluation qieslEvaluation = (QIESLEvaluation)theEObject;
				T result = caseQIESLEvaluation(qieslEvaluation);
				if (result == null) result = caseTextEvaluation(qieslEvaluation);
				if (result == null) result = caseEvaluation(qieslEvaluation);
				if (result == null) result = caseNamedElement(qieslEvaluation);
				if (result == null) result = caseDescribedElement(qieslEvaluation);
				if (result == null) result = caseAnnotatedElement(qieslEvaluation);
				if (result == null) result = caseTaggedElement(qieslEvaluation);
				if (result == null) result = caseQualityModelElement(qieslEvaluation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.TEXT_EVALUATION: {
				TextEvaluation textEvaluation = (TextEvaluation)theEObject;
				T result = caseTextEvaluation(textEvaluation);
				if (result == null) result = caseEvaluation(textEvaluation);
				if (result == null) result = caseNamedElement(textEvaluation);
				if (result == null) result = caseDescribedElement(textEvaluation);
				if (result == null) result = caseAnnotatedElement(textEvaluation);
				if (result == null) result = caseTaggedElement(textEvaluation);
				if (result == null) result = caseQualityModelElement(textEvaluation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.FORM_BASED_EVALUATION: {
				FormBasedEvaluation formBasedEvaluation = (FormBasedEvaluation)theEObject;
				T result = caseFormBasedEvaluation(formBasedEvaluation);
				if (result == null) result = caseEvaluation(formBasedEvaluation);
				if (result == null) result = caseNamedElement(formBasedEvaluation);
				if (result == null) result = caseDescribedElement(formBasedEvaluation);
				if (result == null) result = caseAnnotatedElement(formBasedEvaluation);
				if (result == null) result = caseTaggedElement(formBasedEvaluation);
				if (result == null) result = caseQualityModelElement(formBasedEvaluation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.SINGLE_MEASURE_EVALUATION: {
				SingleMeasureEvaluation singleMeasureEvaluation = (SingleMeasureEvaluation)theEObject;
				T result = caseSingleMeasureEvaluation(singleMeasureEvaluation);
				if (result == null) result = caseFormBasedEvaluation(singleMeasureEvaluation);
				if (result == null) result = caseMeasureEvaluation(singleMeasureEvaluation);
				if (result == null) result = caseEvaluation(singleMeasureEvaluation);
				if (result == null) result = caseNamedElement(singleMeasureEvaluation);
				if (result == null) result = caseDescribedElement(singleMeasureEvaluation);
				if (result == null) result = caseAnnotatedElement(singleMeasureEvaluation);
				if (result == null) result = caseTaggedElement(singleMeasureEvaluation);
				if (result == null) result = caseQualityModelElement(singleMeasureEvaluation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.FACTOR_AGGREGATION: {
				FactorAggregation factorAggregation = (FactorAggregation)theEObject;
				T result = caseFactorAggregation(factorAggregation);
				if (result == null) result = caseFormBasedEvaluation(factorAggregation);
				if (result == null) result = caseEvaluation(factorAggregation);
				if (result == null) result = caseNamedElement(factorAggregation);
				if (result == null) result = caseDescribedElement(factorAggregation);
				if (result == null) result = caseAnnotatedElement(factorAggregation);
				if (result == null) result = caseTaggedElement(factorAggregation);
				if (result == null) result = caseQualityModelElement(factorAggregation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.NORMALIZATION_MEASURE: {
				NormalizationMeasure normalizationMeasure = (NormalizationMeasure)theEObject;
				T result = caseNormalizationMeasure(normalizationMeasure);
				if (result == null) result = caseMeasure(normalizationMeasure);
				if (result == null) result = caseCharacterizingElement(normalizationMeasure);
				if (result == null) result = caseNamedElement(normalizationMeasure);
				if (result == null) result = caseDescribedElement(normalizationMeasure);
				if (result == null) result = caseAnnotatedElement(normalizationMeasure);
				if (result == null) result = caseTaggedElement(normalizationMeasure);
				if (result == null) result = caseQualityModelElement(normalizationMeasure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.QIESL_AGGREGATION: {
				QIESLAggregation qieslAggregation = (QIESLAggregation)theEObject;
				T result = caseQIESLAggregation(qieslAggregation);
				if (result == null) result = caseTextAggregation(qieslAggregation);
				if (result == null) result = caseMeasureAggregation(qieslAggregation);
				if (result == null) result = caseMeasurementMethod(qieslAggregation);
				if (result == null) result = caseNamedElement(qieslAggregation);
				if (result == null) result = caseDescribedElement(qieslAggregation);
				if (result == null) result = caseAnnotatedElement(qieslAggregation);
				if (result == null) result = caseTaggedElement(qieslAggregation);
				if (result == null) result = caseQualityModelElement(qieslAggregation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.TEXT_AGGREGATION: {
				TextAggregation textAggregation = (TextAggregation)theEObject;
				T result = caseTextAggregation(textAggregation);
				if (result == null) result = caseMeasureAggregation(textAggregation);
				if (result == null) result = caseMeasurementMethod(textAggregation);
				if (result == null) result = caseNamedElement(textAggregation);
				if (result == null) result = caseDescribedElement(textAggregation);
				if (result == null) result = caseAnnotatedElement(textAggregation);
				if (result == null) result = caseTaggedElement(textAggregation);
				if (result == null) result = caseQualityModelElement(textAggregation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.FORM_BASED_MEASURE_AGGREGATION: {
				FormBasedMeasureAggregation formBasedMeasureAggregation = (FormBasedMeasureAggregation)theEObject;
				T result = caseFormBasedMeasureAggregation(formBasedMeasureAggregation);
				if (result == null) result = caseMeasureAggregation(formBasedMeasureAggregation);
				if (result == null) result = caseMeasurementMethod(formBasedMeasureAggregation);
				if (result == null) result = caseNamedElement(formBasedMeasureAggregation);
				if (result == null) result = caseDescribedElement(formBasedMeasureAggregation);
				if (result == null) result = caseAnnotatedElement(formBasedMeasureAggregation);
				if (result == null) result = caseTaggedElement(formBasedMeasureAggregation);
				if (result == null) result = caseQualityModelElement(formBasedMeasureAggregation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.FACTOR_RANKING: {
				FactorRanking factorRanking = (FactorRanking)theEObject;
				T result = caseFactorRanking(factorRanking);
				if (result == null) result = caseRanking(factorRanking);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.LINEAR_FUNCTION: {
				LinearFunction linearFunction = (LinearFunction)theEObject;
				T result = caseLinearFunction(linearFunction);
				if (result == null) result = caseFunction(linearFunction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.FUNCTION: {
				Function function = (Function)theEObject;
				T result = caseFunction(function);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.LINEAR_INCREASING_FUNCTION: {
				LinearIncreasingFunction linearIncreasingFunction = (LinearIncreasingFunction)theEObject;
				T result = caseLinearIncreasingFunction(linearIncreasingFunction);
				if (result == null) result = caseLinearFunction(linearIncreasingFunction);
				if (result == null) result = caseFunction(linearIncreasingFunction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.LINEAR_DECREASING_FUNCTION: {
				LinearDecreasingFunction linearDecreasingFunction = (LinearDecreasingFunction)theEObject;
				T result = caseLinearDecreasingFunction(linearDecreasingFunction);
				if (result == null) result = caseLinearFunction(linearDecreasingFunction);
				if (result == null) result = caseFunction(linearDecreasingFunction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.WEIGHTED_SUM_FACTOR_AGGREGATION: {
				WeightedSumFactorAggregation weightedSumFactorAggregation = (WeightedSumFactorAggregation)theEObject;
				T result = caseWeightedSumFactorAggregation(weightedSumFactorAggregation);
				if (result == null) result = caseFactorAggregation(weightedSumFactorAggregation);
				if (result == null) result = caseFormBasedEvaluation(weightedSumFactorAggregation);
				if (result == null) result = caseEvaluation(weightedSumFactorAggregation);
				if (result == null) result = caseNamedElement(weightedSumFactorAggregation);
				if (result == null) result = caseDescribedElement(weightedSumFactorAggregation);
				if (result == null) result = caseAnnotatedElement(weightedSumFactorAggregation);
				if (result == null) result = caseTaggedElement(weightedSumFactorAggregation);
				if (result == null) result = caseQualityModelElement(weightedSumFactorAggregation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.FINDINGS_UNION_MEASURE_AGGREGATION: {
				FindingsUnionMeasureAggregation findingsUnionMeasureAggregation = (FindingsUnionMeasureAggregation)theEObject;
				T result = caseFindingsUnionMeasureAggregation(findingsUnionMeasureAggregation);
				if (result == null) result = caseFormBasedMeasureAggregation(findingsUnionMeasureAggregation);
				if (result == null) result = caseMeasureAggregation(findingsUnionMeasureAggregation);
				if (result == null) result = caseMeasurementMethod(findingsUnionMeasureAggregation);
				if (result == null) result = caseNamedElement(findingsUnionMeasureAggregation);
				if (result == null) result = caseDescribedElement(findingsUnionMeasureAggregation);
				if (result == null) result = caseAnnotatedElement(findingsUnionMeasureAggregation);
				if (result == null) result = caseTaggedElement(findingsUnionMeasureAggregation);
				if (result == null) result = caseQualityModelElement(findingsUnionMeasureAggregation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.NUMBER_MEAN_MEASURE_AGGREGATION: {
				NumberMeanMeasureAggregation numberMeanMeasureAggregation = (NumberMeanMeasureAggregation)theEObject;
				T result = caseNumberMeanMeasureAggregation(numberMeanMeasureAggregation);
				if (result == null) result = caseFormBasedMeasureAggregation(numberMeanMeasureAggregation);
				if (result == null) result = caseMeasureAggregation(numberMeanMeasureAggregation);
				if (result == null) result = caseMeasurementMethod(numberMeanMeasureAggregation);
				if (result == null) result = caseNamedElement(numberMeanMeasureAggregation);
				if (result == null) result = caseDescribedElement(numberMeanMeasureAggregation);
				if (result == null) result = caseAnnotatedElement(numberMeanMeasureAggregation);
				if (result == null) result = caseTaggedElement(numberMeanMeasureAggregation);
				if (result == null) result = caseQualityModelElement(numberMeanMeasureAggregation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.MANUAL_EVALUATION: {
				ManualEvaluation manualEvaluation = (ManualEvaluation)theEObject;
				T result = caseManualEvaluation(manualEvaluation);
				if (result == null) result = caseEvaluation(manualEvaluation);
				if (result == null) result = caseNamedElement(manualEvaluation);
				if (result == null) result = caseDescribedElement(manualEvaluation);
				if (result == null) result = caseAnnotatedElement(manualEvaluation);
				if (result == null) result = caseTaggedElement(manualEvaluation);
				if (result == null) result = caseQualityModelElement(manualEvaluation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.MEASURE_EVALUATION: {
				MeasureEvaluation measureEvaluation = (MeasureEvaluation)theEObject;
				T result = caseMeasureEvaluation(measureEvaluation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.RANKING: {
				Ranking ranking = (Ranking)theEObject;
				T result = caseRanking(ranking);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.MULTI_MEASURE_EVALUATION: {
				MultiMeasureEvaluation multiMeasureEvaluation = (MultiMeasureEvaluation)theEObject;
				T result = caseMultiMeasureEvaluation(multiMeasureEvaluation);
				if (result == null) result = caseFormBasedEvaluation(multiMeasureEvaluation);
				if (result == null) result = caseEvaluation(multiMeasureEvaluation);
				if (result == null) result = caseNamedElement(multiMeasureEvaluation);
				if (result == null) result = caseDescribedElement(multiMeasureEvaluation);
				if (result == null) result = caseAnnotatedElement(multiMeasureEvaluation);
				if (result == null) result = caseTaggedElement(multiMeasureEvaluation);
				if (result == null) result = caseQualityModelElement(multiMeasureEvaluation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.WEIGHTED_SUM_MULTI_MEASURE_EVALUATION: {
				WeightedSumMultiMeasureEvaluation weightedSumMultiMeasureEvaluation = (WeightedSumMultiMeasureEvaluation)theEObject;
				T result = caseWeightedSumMultiMeasureEvaluation(weightedSumMultiMeasureEvaluation);
				if (result == null) result = caseMultiMeasureEvaluation(weightedSumMultiMeasureEvaluation);
				if (result == null) result = caseFormBasedEvaluation(weightedSumMultiMeasureEvaluation);
				if (result == null) result = caseEvaluation(weightedSumMultiMeasureEvaluation);
				if (result == null) result = caseNamedElement(weightedSumMultiMeasureEvaluation);
				if (result == null) result = caseDescribedElement(weightedSumMultiMeasureEvaluation);
				if (result == null) result = caseAnnotatedElement(weightedSumMultiMeasureEvaluation);
				if (result == null) result = caseTaggedElement(weightedSumMultiMeasureEvaluation);
				if (result == null) result = caseQualityModelElement(weightedSumMultiMeasureEvaluation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.MEASURE_RANKING: {
				MeasureRanking measureRanking = (MeasureRanking)theEObject;
				T result = caseMeasureRanking(measureRanking);
				if (result == null) result = caseMeasureEvaluation(measureRanking);
				if (result == null) result = caseRanking(measureRanking);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.QUALITY_MODEL_RESULT: {
				QualityModelResult qualityModelResult = (QualityModelResult)theEObject;
				T result = caseQualityModelResult(qualityModelResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.MEASUREMENT_RESULT: {
				MeasurementResult measurementResult = (MeasurementResult)theEObject;
				T result = caseMeasurementResult(measurementResult);
				if (result == null) result = caseResult(measurementResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.NUMBER_MEASUREMENT_RESULT: {
				NumberMeasurementResult numberMeasurementResult = (NumberMeasurementResult)theEObject;
				T result = caseNumberMeasurementResult(numberMeasurementResult);
				if (result == null) result = caseMeasurementResult(numberMeasurementResult);
				if (result == null) result = caseResult(numberMeasurementResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.FINDINGS_MEASUREMENT_RESULT: {
				FindingsMeasurementResult findingsMeasurementResult = (FindingsMeasurementResult)theEObject;
				T result = caseFindingsMeasurementResult(findingsMeasurementResult);
				if (result == null) result = caseMeasurementResult(findingsMeasurementResult);
				if (result == null) result = caseResult(findingsMeasurementResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.DOUBLE_INTERVAL: {
				DoubleInterval doubleInterval = (DoubleInterval)theEObject;
				T result = caseDoubleInterval(doubleInterval);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.EVALUATION_RESULT: {
				EvaluationResult evaluationResult = (EvaluationResult)theEObject;
				T result = caseEvaluationResult(evaluationResult);
				if (result == null) result = caseResult(evaluationResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.SINGLE_MEASURE_EVALUATION_RESULT: {
				SingleMeasureEvaluationResult singleMeasureEvaluationResult = (SingleMeasureEvaluationResult)theEObject;
				T result = caseSingleMeasureEvaluationResult(singleMeasureEvaluationResult);
				if (result == null) result = caseEvaluationResult(singleMeasureEvaluationResult);
				if (result == null) result = caseResult(singleMeasureEvaluationResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.MULTI_MEASURE_EVALUATION_RESULT: {
				MultiMeasureEvaluationResult multiMeasureEvaluationResult = (MultiMeasureEvaluationResult)theEObject;
				T result = caseMultiMeasureEvaluationResult(multiMeasureEvaluationResult);
				if (result == null) result = caseEvaluationResult(multiMeasureEvaluationResult);
				if (result == null) result = caseResult(multiMeasureEvaluationResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.MEASURE_RANKING_EVALUATION_RESULT: {
				MeasureRankingEvaluationResult measureRankingEvaluationResult = (MeasureRankingEvaluationResult)theEObject;
				T result = caseMeasureRankingEvaluationResult(measureRankingEvaluationResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case QmPackage.RESULT: {
				Result result = (Result)theEObject;
				T theResult = caseResult(result);
				if (theResult == null) theResult = defaultCase(theEObject);
				return theResult;
			}
			case QmPackage.FINDING_MESSAGE: {
				FindingMessage findingMessage = (FindingMessage)theEObject;
				T result = caseFindingMessage(findingMessage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Described Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Described Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDescribedElement(DescribedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Evaluation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEvaluation(Evaluation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Factor Refinement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Factor Refinement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFactorRefinement(FactorRefinement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Factor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Factor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFactor(Factor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntity(Entity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Impact</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Impact</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImpact(Impact object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Quality Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Quality Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQualityModel(QualityModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instrument</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instrument</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstrument(Instrument object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Annotated Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Annotated Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnnotatedElement(AnnotatedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Annotation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Annotation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnnotation(Map.Entry<String, String> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tag</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tag</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTag(Tag object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tagged Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tagged Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTaggedElement(TaggedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasure(Measure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measurement Method</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measurement Method</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasurementMethod(MeasurementMethod object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Characterizing Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Characterizing Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCharacterizingElement(CharacterizingElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Quality Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Quality Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQualityModelElement(QualityModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tool Based Instrument</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tool Based Instrument</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseToolBasedInstrument(ToolBasedInstrument object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Manual Instrument</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Manual Instrument</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseManualInstrument(ManualInstrument object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Annotation Base</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Annotation Base</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnnotationBase(AnnotationBase object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>QIESL Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>QIESL Evaluation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQIESLEvaluation(QIESLEvaluation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Text Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Text Evaluation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTextEvaluation(TextEvaluation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Form Based Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Form Based Evaluation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormBasedEvaluation(FormBasedEvaluation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Single Measure Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Single Measure Evaluation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSingleMeasureEvaluation(SingleMeasureEvaluation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Factor Aggregation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Factor Aggregation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFactorAggregation(FactorAggregation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Normalization Measure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Normalization Measure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNormalizationMeasure(NormalizationMeasure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>QIESL Aggregation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>QIESL Aggregation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQIESLAggregation(QIESLAggregation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Text Aggregation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Text Aggregation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTextAggregation(TextAggregation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Form Based Measure Aggregation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Form Based Measure Aggregation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormBasedMeasureAggregation(FormBasedMeasureAggregation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measure Aggregation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measure Aggregation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasureAggregation(MeasureAggregation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Factor Ranking</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Factor Ranking</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFactorRanking(FactorRanking object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Linear Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Linear Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLinearFunction(LinearFunction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFunction(Function object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Linear Increasing Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Linear Increasing Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLinearIncreasingFunction(LinearIncreasingFunction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Linear Decreasing Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Linear Decreasing Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLinearDecreasingFunction(LinearDecreasingFunction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Weighted Sum Factor Aggregation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Weighted Sum Factor Aggregation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWeightedSumFactorAggregation(WeightedSumFactorAggregation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Findings Union Measure Aggregation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Findings Union Measure Aggregation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFindingsUnionMeasureAggregation(FindingsUnionMeasureAggregation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Number Mean Measure Aggregation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Number Mean Measure Aggregation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNumberMeanMeasureAggregation(NumberMeanMeasureAggregation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Manual Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Manual Evaluation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseManualEvaluation(ManualEvaluation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measure Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measure Evaluation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasureEvaluation(MeasureEvaluation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ranking</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ranking</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRanking(Ranking object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Multi Measure Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Multi Measure Evaluation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMultiMeasureEvaluation(MultiMeasureEvaluation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Weighted Sum Multi Measure Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Weighted Sum Multi Measure Evaluation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWeightedSumMultiMeasureEvaluation(WeightedSumMultiMeasureEvaluation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measure Ranking</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measure Ranking</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasureRanking(MeasureRanking object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Quality Model Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Quality Model Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQualityModelResult(QualityModelResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResult(Result object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Finding Message</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Finding Message</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFindingMessage(FindingMessage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measurement Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measurement Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasurementResult(MeasurementResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Number Measurement Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Number Measurement Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNumberMeasurementResult(NumberMeasurementResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Findings Measurement Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Findings Measurement Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFindingsMeasurementResult(FindingsMeasurementResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Double Interval</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Double Interval</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDoubleInterval(DoubleInterval object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Evaluation Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Evaluation Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEvaluationResult(EvaluationResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Single Measure Evaluation Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Single Measure Evaluation Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSingleMeasureEvaluationResult(SingleMeasureEvaluationResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Multi Measure Evaluation Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Multi Measure Evaluation Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMultiMeasureEvaluationResult(MultiMeasureEvaluationResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measure Ranking Evaluation Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measure Ranking Evaluation Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasureRankingEvaluationResult(MeasureRankingEvaluationResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tool</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tool</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTool(Tool object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSource(Source object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measurement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasurement(Measurement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Specialization</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Specialization</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpecialization(Specialization object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Decomposition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Decomposition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDecomposition(Decomposition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measure Refinement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measure Refinement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasureRefinement(MeasureRefinement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //QmSwitch
