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
 * $Id: QmPackageImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.AnnotationBase;
import de.quamoco.qm.CharacterizingElement;
import de.quamoco.qm.Decomposition;
import de.quamoco.qm.DescribedElement;
import de.quamoco.qm.DoubleInterval;
import de.quamoco.qm.Effect;
import de.quamoco.qm.Entity;
import de.quamoco.qm.Evaluation;
import de.quamoco.qm.EvaluationResult;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FactorAggregation;
import de.quamoco.qm.FactorRanking;
import de.quamoco.qm.FactorRefinement;
import de.quamoco.qm.FindingMessage;
import de.quamoco.qm.FindingsMeasurementResult;
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
import de.quamoco.qm.MeasureRankingEvaluationResult;
import de.quamoco.qm.MeasureRefinement;
import de.quamoco.qm.Measurement;
import de.quamoco.qm.MeasurementMethod;
import de.quamoco.qm.MeasurementResult;
import de.quamoco.qm.MultiMeasureEvaluation;
import de.quamoco.qm.MultiMeasureEvaluationResult;
import de.quamoco.qm.NamedElement;
import de.quamoco.qm.NormalizationMeasure;
import de.quamoco.qm.NumberMeanMeasureAggregation;
import de.quamoco.qm.NumberMeasurementResult;
import de.quamoco.qm.QIESLAggregation;
import de.quamoco.qm.QIESLEvaluation;
import de.quamoco.qm.QmFactory;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.QualityModelElement;
import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.Ranking;
import de.quamoco.qm.Result;
import de.quamoco.qm.SingleMeasureEvaluation;
import de.quamoco.qm.SingleMeasureEvaluationResult;
import de.quamoco.qm.Source;
import de.quamoco.qm.Specialization;
import de.quamoco.qm.Tag;
import de.quamoco.qm.TaggedElement;
import de.quamoco.qm.TextAggregation;
import de.quamoco.qm.TextEvaluation;
import de.quamoco.qm.Tool;
import de.quamoco.qm.ToolBasedInstrument;
import de.quamoco.qm.Type;
import de.quamoco.qm.WeightedSumFactorAggregation;
import de.quamoco.qm.WeightedSumMultiMeasureEvaluation;
import edu.tum.cs.conqat.quamoco.FindingCollection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class QmPackageImpl extends EPackageImpl implements QmPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass describedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass evaluationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass factorRefinementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass factorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass impactEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass qualityModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instrumentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass annotatedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass annotationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tagEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taggedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measurementMethodEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass characterizingElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass qualityModelElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass toolBasedInstrumentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manualInstrumentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass annotationBaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass qieslEvaluationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textEvaluationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formBasedEvaluationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass singleMeasureEvaluationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass factorAggregationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass normalizationMeasureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass qieslAggregationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textAggregationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formBasedMeasureAggregationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measureAggregationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass factorRankingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass linearFunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass linearIncreasingFunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass linearDecreasingFunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass weightedSumFactorAggregationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass findingsUnionMeasureAggregationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numberMeanMeasureAggregationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manualEvaluationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measureEvaluationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rankingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass multiMeasureEvaluationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass weightedSumMultiMeasureEvaluationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measureRankingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass qualityModelResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass findingMessageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measurementResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numberMeasurementResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass findingsMeasurementResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass doubleIntervalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass evaluationResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass singleMeasureEvaluationResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass multiMeasureEvaluationResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measureRankingEvaluationResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass toolEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measurementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass specializationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass decompositionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measureRefinementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum effectEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum typeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType findingCollectionEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see de.quamoco.qm.QmPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private QmPackageImpl() {
		super(eNS_URI, QmFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link QmPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static QmPackage init() {
		if (isInited) return (QmPackage)EPackage.Registry.INSTANCE.getEPackage(QmPackage.eNS_URI);

		// Obtain or create and register package
		QmPackageImpl theQmPackage = (QmPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof QmPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new QmPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theQmPackage.createPackageContents();

		// Initialize created meta-data
		theQmPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theQmPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(QmPackage.eNS_URI, theQmPackage);
		return theQmPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDescribedElement() {
		return describedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescribedElement_Description() {
		return (EAttribute)describedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEvaluation() {
		return evaluationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEvaluation_Evaluates() {
		return (EReference)evaluationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEvaluation_QualityModel() {
		return (EReference)evaluationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEvaluation_MaximumPoints() {
		return (EAttribute)evaluationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEvaluation_Completeness() {
		return (EAttribute)evaluationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFactorRefinement() {
		return factorRefinementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFactorRefinement_Child() {
		return (EReference)factorRefinementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFactorRefinement_Parent() {
		return (EReference)factorRefinementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFactor() {
		return factorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFactor_Influences() {
		return (EReference)factorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFactor_InfluencesDirect() {
		return (EReference)factorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFactor_Refines() {
		return (EReference)factorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFactor_RefinesDirect() {
		return (EReference)factorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFactor_QualityModel() {
		return (EReference)factorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntity() {
		return entityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntity_IsA() {
		return (EReference)entityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntity_IsADirect() {
		return (EReference)entityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntity_PartOf() {
		return (EReference)entityEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntity_PartOfDirect() {
		return (EReference)entityEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntity_QualityModel() {
		return (EReference)entityEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntity_Stakeholder() {
		return (EAttribute)entityEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntity_UseCase() {
		return (EAttribute)entityEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImpact() {
		return impactEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImpact_Source() {
		return (EReference)impactEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImpact_Target() {
		return (EReference)impactEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImpact_Effect() {
		return (EAttribute)impactEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImpact_Justification() {
		return (EAttribute)impactEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQualityModel() {
		return qualityModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQualityModel_Entities() {
		return (EReference)qualityModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQualityModel_Factors() {
		return (EReference)qualityModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQualityModel_Measures() {
		return (EReference)qualityModelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQualityModel_Tags() {
		return (EReference)qualityModelEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQualityModel_Tools() {
		return (EReference)qualityModelEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQualityModel_Sources() {
		return (EReference)qualityModelEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQualityModel_Requires() {
		return (EReference)qualityModelEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQualityModel_SchoolGradeBoundary2() {
		return (EAttribute)qualityModelEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQualityModel_SchoolGradeBoundary3() {
		return (EAttribute)qualityModelEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQualityModel_SchoolGradeBoundary4() {
		return (EAttribute)qualityModelEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQualityModel_SchoolGradeBoundary5() {
		return (EAttribute)qualityModelEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQualityModel_SchoolGradeBoundary6() {
		return (EAttribute)qualityModelEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQualityModel_Evaluations() {
		return (EReference)qualityModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQualityModel_MeasurementMethods() {
		return (EReference)qualityModelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedElement() {
		return namedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Name() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Title() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInstrument() {
		return instrumentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnnotatedElement() {
		return annotatedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotatedElement_Annotations() {
		return (EReference)annotatedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotatedElement_AdvancedAnnotations() {
		return (EReference)annotatedElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnnotation() {
		return annotationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAnnotation_Key() {
		return (EAttribute)annotationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAnnotation_Value() {
		return (EAttribute)annotationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTag() {
		return tagEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTag_QualityModel() {
		return (EReference)tagEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTaggedElement() {
		return taggedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaggedElement_TaggedBy() {
		return (EReference)taggedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasure() {
		return measureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMeasure_Type() {
		return (EAttribute)measureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasure_Measures() {
		return (EReference)measureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasure_MeasuresDirect() {
		return (EReference)measureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasure_QualityModel() {
		return (EReference)measureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasure_Refines() {
		return (EReference)measureEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasure_RefinesDirect() {
		return (EReference)measureEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasurementMethod() {
		return measurementMethodEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasurementMethod_Determines() {
		return (EReference)measurementMethodEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasurementMethod_QualityModel() {
		return (EReference)measurementMethodEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCharacterizingElement() {
		return characterizingElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCharacterizingElement_Characterizes() {
		return (EReference)characterizingElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQualityModelElement() {
		return qualityModelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQualityModelElement_QualifiedName() {
		return (EAttribute)qualityModelElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQualityModelElement_OriginatesFrom() {
		return (EReference)qualityModelElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getToolBasedInstrument() {
		return toolBasedInstrumentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getToolBasedInstrument_Tool() {
		return (EReference)toolBasedInstrumentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getToolBasedInstrument_Metric() {
		return (EAttribute)toolBasedInstrumentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getManualInstrument() {
		return manualInstrumentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnnotationBase() {
		return annotationBaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQIESLEvaluation() {
		return qieslEvaluationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTextEvaluation() {
		return textEvaluationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTextEvaluation_Specification() {
		return (EAttribute)textEvaluationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormBasedEvaluation() {
		return formBasedEvaluationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSingleMeasureEvaluation() {
		return singleMeasureEvaluationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFactorAggregation() {
		return factorAggregationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNormalizationMeasure() {
		return normalizationMeasureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQIESLAggregation() {
		return qieslAggregationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTextAggregation() {
		return textAggregationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTextAggregation_Specification() {
		return (EAttribute)textAggregationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormBasedMeasureAggregation() {
		return formBasedMeasureAggregationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasureAggregation() {
		return measureAggregationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFactorRanking() {
		return factorRankingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFactorRanking_Factor() {
		return (EReference)factorRankingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLinearFunction() {
		return linearFunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLinearFunction_LowerBound() {
		return (EAttribute)linearFunctionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLinearFunction_UpperBound() {
		return (EAttribute)linearFunctionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunction() {
		return functionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLinearIncreasingFunction() {
		return linearIncreasingFunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLinearDecreasingFunction() {
		return linearDecreasingFunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWeightedSumFactorAggregation() {
		return weightedSumFactorAggregationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWeightedSumFactorAggregation_Rankings() {
		return (EReference)weightedSumFactorAggregationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFindingsUnionMeasureAggregation() {
		return findingsUnionMeasureAggregationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNumberMeanMeasureAggregation() {
		return numberMeanMeasureAggregationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getManualEvaluation() {
		return manualEvaluationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasureEvaluation() {
		return measureEvaluationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasureEvaluation_Measure() {
		return (EReference)measureEvaluationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasureEvaluation_NormlizationMeasure() {
		return (EReference)measureEvaluationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMeasureEvaluation_Range() {
		return (EAttribute)measureEvaluationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasureEvaluation_Function() {
		return (EReference)measureEvaluationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRanking() {
		return rankingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRanking_Rank() {
		return (EAttribute)rankingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRanking_Weight() {
		return (EAttribute)rankingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMultiMeasureEvaluation() {
		return multiMeasureEvaluationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWeightedSumMultiMeasureEvaluation() {
		return weightedSumMultiMeasureEvaluationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWeightedSumMultiMeasureEvaluation_Rankings() {
		return (EReference)weightedSumMultiMeasureEvaluationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasureRanking() {
		return measureRankingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQualityModelResult() {
		return qualityModelResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQualityModelResult_System() {
		return (EAttribute)qualityModelResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQualityModelResult_Date() {
		return (EAttribute)qualityModelResultEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQualityModelResult_MeasurementResults() {
		return (EReference)qualityModelResultEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQualityModelResult_EvaluationResults() {
		return (EReference)qualityModelResultEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResult() {
		return resultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResult_Message() {
		return (EAttribute)resultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFindingMessage() {
		return findingMessageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFindingMessage_Message() {
		return (EAttribute)findingMessageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFindingMessage_Location() {
		return (EAttribute)findingMessageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasurementResult() {
		return measurementResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasurementResult_ResultsFrom() {
		return (EReference)measurementResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNumberMeasurementResult() {
		return numberMeasurementResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNumberMeasurementResult_Value() {
		return (EReference)numberMeasurementResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFindingsMeasurementResult() {
		return findingsMeasurementResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFindingsMeasurementResult_Count() {
		return (EAttribute)findingsMeasurementResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFindingsMeasurementResult_Findings() {
		return (EAttribute)findingsMeasurementResultEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFindingsMeasurementResult_FindingMessages() {
		return (EReference)findingsMeasurementResultEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDoubleInterval() {
		return doubleIntervalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDoubleInterval_Lower() {
		return (EAttribute)doubleIntervalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDoubleInterval_Upper() {
		return (EAttribute)doubleIntervalEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEvaluationResult() {
		return evaluationResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEvaluationResult_Value() {
		return (EReference)evaluationResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEvaluationResult_ResultsFrom() {
		return (EReference)evaluationResultEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSingleMeasureEvaluationResult() {
		return singleMeasureEvaluationResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSingleMeasureEvaluationResult_RatioAffected() {
		return (EAttribute)singleMeasureEvaluationResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMultiMeasureEvaluationResult() {
		return multiMeasureEvaluationResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMultiMeasureEvaluationResult_EvaluationResults() {
		return (EReference)multiMeasureEvaluationResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasureRankingEvaluationResult() {
		return measureRankingEvaluationResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMeasureRankingEvaluationResult_RatioAffected() {
		return (EAttribute)measureRankingEvaluationResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasureRankingEvaluationResult_Value() {
		return (EReference)measureRankingEvaluationResultEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasureRankingEvaluationResult_ResultsFrom() {
		return (EReference)measureRankingEvaluationResultEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTool() {
		return toolEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTool_QualityModel() {
		return (EReference)toolEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSource() {
		return sourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSource_QualityModel() {
		return (EReference)sourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasurement() {
		return measurementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasurement_Parent() {
		return (EReference)measurementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasurement_Child() {
		return (EReference)measurementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpecialization() {
		return specializationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecialization_Parent() {
		return (EReference)specializationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecialization_Child() {
		return (EReference)specializationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDecomposition() {
		return decompositionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDecomposition_Parent() {
		return (EReference)decompositionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDecomposition_Child() {
		return (EReference)decompositionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasureRefinement() {
		return measureRefinementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasureRefinement_Parent() {
		return (EReference)measureRefinementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasureRefinement_Child() {
		return (EReference)measureRefinementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEffect() {
		return effectEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getType() {
		return typeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getFindingCollection() {
		return findingCollectionEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QmFactory getQmFactory() {
		return (QmFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		qualityModelEClass = createEClass(QUALITY_MODEL);
		createEReference(qualityModelEClass, QUALITY_MODEL__ENTITIES);
		createEReference(qualityModelEClass, QUALITY_MODEL__FACTORS);
		createEReference(qualityModelEClass, QUALITY_MODEL__EVALUATIONS);
		createEReference(qualityModelEClass, QUALITY_MODEL__MEASURES);
		createEReference(qualityModelEClass, QUALITY_MODEL__MEASUREMENT_METHODS);
		createEReference(qualityModelEClass, QUALITY_MODEL__TOOLS);
		createEReference(qualityModelEClass, QUALITY_MODEL__TAGS);
		createEReference(qualityModelEClass, QUALITY_MODEL__SOURCES);
		createEReference(qualityModelEClass, QUALITY_MODEL__REQUIRES);
		createEAttribute(qualityModelEClass, QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY2);
		createEAttribute(qualityModelEClass, QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY3);
		createEAttribute(qualityModelEClass, QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY4);
		createEAttribute(qualityModelEClass, QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY5);
		createEAttribute(qualityModelEClass, QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY6);

		qualityModelElementEClass = createEClass(QUALITY_MODEL_ELEMENT);
		createEAttribute(qualityModelElementEClass, QUALITY_MODEL_ELEMENT__QUALIFIED_NAME);
		createEReference(qualityModelElementEClass, QUALITY_MODEL_ELEMENT__ORIGINATES_FROM);

		sourceEClass = createEClass(SOURCE);
		createEReference(sourceEClass, SOURCE__QUALITY_MODEL);

		describedElementEClass = createEClass(DESCRIBED_ELEMENT);
		createEAttribute(describedElementEClass, DESCRIBED_ELEMENT__DESCRIPTION);

		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__TITLE);

		annotatedElementEClass = createEClass(ANNOTATED_ELEMENT);
		createEReference(annotatedElementEClass, ANNOTATED_ELEMENT__ANNOTATIONS);
		createEReference(annotatedElementEClass, ANNOTATED_ELEMENT__ADVANCED_ANNOTATIONS);

		annotationEClass = createEClass(ANNOTATION);
		createEAttribute(annotationEClass, ANNOTATION__KEY);
		createEAttribute(annotationEClass, ANNOTATION__VALUE);

		tagEClass = createEClass(TAG);
		createEReference(tagEClass, TAG__QUALITY_MODEL);

		taggedElementEClass = createEClass(TAGGED_ELEMENT);
		createEReference(taggedElementEClass, TAGGED_ELEMENT__TAGGED_BY);

		entityEClass = createEClass(ENTITY);
		createEReference(entityEClass, ENTITY__IS_A);
		createEReference(entityEClass, ENTITY__IS_ADIRECT);
		createEReference(entityEClass, ENTITY__PART_OF);
		createEReference(entityEClass, ENTITY__PART_OF_DIRECT);
		createEReference(entityEClass, ENTITY__QUALITY_MODEL);
		createEAttribute(entityEClass, ENTITY__STAKEHOLDER);
		createEAttribute(entityEClass, ENTITY__USE_CASE);

		specializationEClass = createEClass(SPECIALIZATION);
		createEReference(specializationEClass, SPECIALIZATION__PARENT);
		createEReference(specializationEClass, SPECIALIZATION__CHILD);

		decompositionEClass = createEClass(DECOMPOSITION);
		createEReference(decompositionEClass, DECOMPOSITION__PARENT);
		createEReference(decompositionEClass, DECOMPOSITION__CHILD);

		characterizingElementEClass = createEClass(CHARACTERIZING_ELEMENT);
		createEReference(characterizingElementEClass, CHARACTERIZING_ELEMENT__CHARACTERIZES);

		factorEClass = createEClass(FACTOR);
		createEReference(factorEClass, FACTOR__INFLUENCES);
		createEReference(factorEClass, FACTOR__INFLUENCES_DIRECT);
		createEReference(factorEClass, FACTOR__REFINES);
		createEReference(factorEClass, FACTOR__REFINES_DIRECT);
		createEReference(factorEClass, FACTOR__QUALITY_MODEL);

		impactEClass = createEClass(IMPACT);
		createEReference(impactEClass, IMPACT__SOURCE);
		createEReference(impactEClass, IMPACT__TARGET);
		createEAttribute(impactEClass, IMPACT__JUSTIFICATION);
		createEAttribute(impactEClass, IMPACT__EFFECT);

		evaluationEClass = createEClass(EVALUATION);
		createEReference(evaluationEClass, EVALUATION__EVALUATES);
		createEReference(evaluationEClass, EVALUATION__QUALITY_MODEL);
		createEAttribute(evaluationEClass, EVALUATION__MAXIMUM_POINTS);
		createEAttribute(evaluationEClass, EVALUATION__COMPLETENESS);

		factorRefinementEClass = createEClass(FACTOR_REFINEMENT);
		createEReference(factorRefinementEClass, FACTOR_REFINEMENT__CHILD);
		createEReference(factorRefinementEClass, FACTOR_REFINEMENT__PARENT);

		measurementEClass = createEClass(MEASUREMENT);
		createEReference(measurementEClass, MEASUREMENT__PARENT);
		createEReference(measurementEClass, MEASUREMENT__CHILD);

		measureRefinementEClass = createEClass(MEASURE_REFINEMENT);
		createEReference(measureRefinementEClass, MEASURE_REFINEMENT__PARENT);
		createEReference(measureRefinementEClass, MEASURE_REFINEMENT__CHILD);

		measureEClass = createEClass(MEASURE);
		createEAttribute(measureEClass, MEASURE__TYPE);
		createEReference(measureEClass, MEASURE__MEASURES);
		createEReference(measureEClass, MEASURE__MEASURES_DIRECT);
		createEReference(measureEClass, MEASURE__QUALITY_MODEL);
		createEReference(measureEClass, MEASURE__REFINES);
		createEReference(measureEClass, MEASURE__REFINES_DIRECT);

		measurementMethodEClass = createEClass(MEASUREMENT_METHOD);
		createEReference(measurementMethodEClass, MEASUREMENT_METHOD__DETERMINES);
		createEReference(measurementMethodEClass, MEASUREMENT_METHOD__QUALITY_MODEL);

		measureAggregationEClass = createEClass(MEASURE_AGGREGATION);

		instrumentEClass = createEClass(INSTRUMENT);

		manualInstrumentEClass = createEClass(MANUAL_INSTRUMENT);

		toolBasedInstrumentEClass = createEClass(TOOL_BASED_INSTRUMENT);
		createEReference(toolBasedInstrumentEClass, TOOL_BASED_INSTRUMENT__TOOL);
		createEAttribute(toolBasedInstrumentEClass, TOOL_BASED_INSTRUMENT__METRIC);

		toolEClass = createEClass(TOOL);
		createEReference(toolEClass, TOOL__QUALITY_MODEL);

		annotationBaseEClass = createEClass(ANNOTATION_BASE);

		qieslEvaluationEClass = createEClass(QIESL_EVALUATION);

		textEvaluationEClass = createEClass(TEXT_EVALUATION);
		createEAttribute(textEvaluationEClass, TEXT_EVALUATION__SPECIFICATION);

		formBasedEvaluationEClass = createEClass(FORM_BASED_EVALUATION);

		singleMeasureEvaluationEClass = createEClass(SINGLE_MEASURE_EVALUATION);

		factorAggregationEClass = createEClass(FACTOR_AGGREGATION);

		normalizationMeasureEClass = createEClass(NORMALIZATION_MEASURE);

		qieslAggregationEClass = createEClass(QIESL_AGGREGATION);

		textAggregationEClass = createEClass(TEXT_AGGREGATION);
		createEAttribute(textAggregationEClass, TEXT_AGGREGATION__SPECIFICATION);

		formBasedMeasureAggregationEClass = createEClass(FORM_BASED_MEASURE_AGGREGATION);

		factorRankingEClass = createEClass(FACTOR_RANKING);
		createEReference(factorRankingEClass, FACTOR_RANKING__FACTOR);

		linearFunctionEClass = createEClass(LINEAR_FUNCTION);
		createEAttribute(linearFunctionEClass, LINEAR_FUNCTION__LOWER_BOUND);
		createEAttribute(linearFunctionEClass, LINEAR_FUNCTION__UPPER_BOUND);

		functionEClass = createEClass(FUNCTION);

		linearIncreasingFunctionEClass = createEClass(LINEAR_INCREASING_FUNCTION);

		linearDecreasingFunctionEClass = createEClass(LINEAR_DECREASING_FUNCTION);

		weightedSumFactorAggregationEClass = createEClass(WEIGHTED_SUM_FACTOR_AGGREGATION);
		createEReference(weightedSumFactorAggregationEClass, WEIGHTED_SUM_FACTOR_AGGREGATION__RANKINGS);

		findingsUnionMeasureAggregationEClass = createEClass(FINDINGS_UNION_MEASURE_AGGREGATION);

		numberMeanMeasureAggregationEClass = createEClass(NUMBER_MEAN_MEASURE_AGGREGATION);

		manualEvaluationEClass = createEClass(MANUAL_EVALUATION);

		measureEvaluationEClass = createEClass(MEASURE_EVALUATION);
		createEReference(measureEvaluationEClass, MEASURE_EVALUATION__MEASURE);
		createEReference(measureEvaluationEClass, MEASURE_EVALUATION__NORMLIZATION_MEASURE);
		createEReference(measureEvaluationEClass, MEASURE_EVALUATION__FUNCTION);
		createEAttribute(measureEvaluationEClass, MEASURE_EVALUATION__RANGE);

		rankingEClass = createEClass(RANKING);
		createEAttribute(rankingEClass, RANKING__RANK);
		createEAttribute(rankingEClass, RANKING__WEIGHT);

		multiMeasureEvaluationEClass = createEClass(MULTI_MEASURE_EVALUATION);

		weightedSumMultiMeasureEvaluationEClass = createEClass(WEIGHTED_SUM_MULTI_MEASURE_EVALUATION);
		createEReference(weightedSumMultiMeasureEvaluationEClass, WEIGHTED_SUM_MULTI_MEASURE_EVALUATION__RANKINGS);

		measureRankingEClass = createEClass(MEASURE_RANKING);

		qualityModelResultEClass = createEClass(QUALITY_MODEL_RESULT);
		createEAttribute(qualityModelResultEClass, QUALITY_MODEL_RESULT__SYSTEM);
		createEAttribute(qualityModelResultEClass, QUALITY_MODEL_RESULT__DATE);
		createEReference(qualityModelResultEClass, QUALITY_MODEL_RESULT__MEASUREMENT_RESULTS);
		createEReference(qualityModelResultEClass, QUALITY_MODEL_RESULT__EVALUATION_RESULTS);

		measurementResultEClass = createEClass(MEASUREMENT_RESULT);
		createEReference(measurementResultEClass, MEASUREMENT_RESULT__RESULTS_FROM);

		numberMeasurementResultEClass = createEClass(NUMBER_MEASUREMENT_RESULT);
		createEReference(numberMeasurementResultEClass, NUMBER_MEASUREMENT_RESULT__VALUE);

		findingsMeasurementResultEClass = createEClass(FINDINGS_MEASUREMENT_RESULT);
		createEAttribute(findingsMeasurementResultEClass, FINDINGS_MEASUREMENT_RESULT__COUNT);
		createEAttribute(findingsMeasurementResultEClass, FINDINGS_MEASUREMENT_RESULT__FINDINGS);
		createEReference(findingsMeasurementResultEClass, FINDINGS_MEASUREMENT_RESULT__FINDING_MESSAGES);

		doubleIntervalEClass = createEClass(DOUBLE_INTERVAL);
		createEAttribute(doubleIntervalEClass, DOUBLE_INTERVAL__LOWER);
		createEAttribute(doubleIntervalEClass, DOUBLE_INTERVAL__UPPER);

		evaluationResultEClass = createEClass(EVALUATION_RESULT);
		createEReference(evaluationResultEClass, EVALUATION_RESULT__VALUE);
		createEReference(evaluationResultEClass, EVALUATION_RESULT__RESULTS_FROM);

		singleMeasureEvaluationResultEClass = createEClass(SINGLE_MEASURE_EVALUATION_RESULT);
		createEAttribute(singleMeasureEvaluationResultEClass, SINGLE_MEASURE_EVALUATION_RESULT__RATIO_AFFECTED);

		multiMeasureEvaluationResultEClass = createEClass(MULTI_MEASURE_EVALUATION_RESULT);
		createEReference(multiMeasureEvaluationResultEClass, MULTI_MEASURE_EVALUATION_RESULT__EVALUATION_RESULTS);

		measureRankingEvaluationResultEClass = createEClass(MEASURE_RANKING_EVALUATION_RESULT);
		createEAttribute(measureRankingEvaluationResultEClass, MEASURE_RANKING_EVALUATION_RESULT__RATIO_AFFECTED);
		createEReference(measureRankingEvaluationResultEClass, MEASURE_RANKING_EVALUATION_RESULT__VALUE);
		createEReference(measureRankingEvaluationResultEClass, MEASURE_RANKING_EVALUATION_RESULT__RESULTS_FROM);

		resultEClass = createEClass(RESULT);
		createEAttribute(resultEClass, RESULT__MESSAGE);

		findingMessageEClass = createEClass(FINDING_MESSAGE);
		createEAttribute(findingMessageEClass, FINDING_MESSAGE__MESSAGE);
		createEAttribute(findingMessageEClass, FINDING_MESSAGE__LOCATION);

		// Create enums
		effectEEnum = createEEnum(EFFECT);
		typeEEnum = createEEnum(TYPE);

		// Create data types
		findingCollectionEDataType = createEDataType(FINDING_COLLECTION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		qualityModelEClass.getESuperTypes().add(this.getNamedElement());
		sourceEClass.getESuperTypes().add(this.getNamedElement());
		describedElementEClass.getESuperTypes().add(this.getAnnotatedElement());
		namedElementEClass.getESuperTypes().add(this.getDescribedElement());
		annotatedElementEClass.getESuperTypes().add(this.getTaggedElement());
		tagEClass.getESuperTypes().add(this.getNamedElement());
		taggedElementEClass.getESuperTypes().add(this.getQualityModelElement());
		entityEClass.getESuperTypes().add(this.getNamedElement());
		specializationEClass.getESuperTypes().add(this.getAnnotatedElement());
		decompositionEClass.getESuperTypes().add(this.getAnnotatedElement());
		characterizingElementEClass.getESuperTypes().add(this.getNamedElement());
		factorEClass.getESuperTypes().add(this.getCharacterizingElement());
		impactEClass.getESuperTypes().add(this.getAnnotatedElement());
		evaluationEClass.getESuperTypes().add(this.getNamedElement());
		factorRefinementEClass.getESuperTypes().add(this.getAnnotatedElement());
		measurementEClass.getESuperTypes().add(this.getAnnotatedElement());
		measureRefinementEClass.getESuperTypes().add(this.getAnnotatedElement());
		measureEClass.getESuperTypes().add(this.getCharacterizingElement());
		measurementMethodEClass.getESuperTypes().add(this.getAnnotatedElement());
		measureAggregationEClass.getESuperTypes().add(this.getMeasurementMethod());
		measureAggregationEClass.getESuperTypes().add(this.getNamedElement());
		instrumentEClass.getESuperTypes().add(this.getMeasurementMethod());
		manualInstrumentEClass.getESuperTypes().add(this.getInstrument());
		manualInstrumentEClass.getESuperTypes().add(this.getNamedElement());
		toolBasedInstrumentEClass.getESuperTypes().add(this.getInstrument());
		toolEClass.getESuperTypes().add(this.getNamedElement());
		qieslEvaluationEClass.getESuperTypes().add(this.getTextEvaluation());
		textEvaluationEClass.getESuperTypes().add(this.getEvaluation());
		formBasedEvaluationEClass.getESuperTypes().add(this.getEvaluation());
		singleMeasureEvaluationEClass.getESuperTypes().add(this.getFormBasedEvaluation());
		singleMeasureEvaluationEClass.getESuperTypes().add(this.getMeasureEvaluation());
		factorAggregationEClass.getESuperTypes().add(this.getFormBasedEvaluation());
		normalizationMeasureEClass.getESuperTypes().add(this.getMeasure());
		qieslAggregationEClass.getESuperTypes().add(this.getTextAggregation());
		textAggregationEClass.getESuperTypes().add(this.getMeasureAggregation());
		formBasedMeasureAggregationEClass.getESuperTypes().add(this.getMeasureAggregation());
		factorRankingEClass.getESuperTypes().add(this.getRanking());
		linearFunctionEClass.getESuperTypes().add(this.getFunction());
		linearIncreasingFunctionEClass.getESuperTypes().add(this.getLinearFunction());
		linearDecreasingFunctionEClass.getESuperTypes().add(this.getLinearFunction());
		weightedSumFactorAggregationEClass.getESuperTypes().add(this.getFactorAggregation());
		findingsUnionMeasureAggregationEClass.getESuperTypes().add(this.getFormBasedMeasureAggregation());
		numberMeanMeasureAggregationEClass.getESuperTypes().add(this.getFormBasedMeasureAggregation());
		manualEvaluationEClass.getESuperTypes().add(this.getEvaluation());
		multiMeasureEvaluationEClass.getESuperTypes().add(this.getFormBasedEvaluation());
		weightedSumMultiMeasureEvaluationEClass.getESuperTypes().add(this.getMultiMeasureEvaluation());
		measureRankingEClass.getESuperTypes().add(this.getMeasureEvaluation());
		measureRankingEClass.getESuperTypes().add(this.getRanking());
		measurementResultEClass.getESuperTypes().add(this.getResult());
		numberMeasurementResultEClass.getESuperTypes().add(this.getMeasurementResult());
		findingsMeasurementResultEClass.getESuperTypes().add(this.getMeasurementResult());
		evaluationResultEClass.getESuperTypes().add(this.getResult());
		singleMeasureEvaluationResultEClass.getESuperTypes().add(this.getEvaluationResult());
		multiMeasureEvaluationResultEClass.getESuperTypes().add(this.getEvaluationResult());

		// Initialize classes and features; add operations and parameters
		initEClass(qualityModelEClass, QualityModel.class, "QualityModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getQualityModel_Entities(), this.getEntity(), this.getEntity_QualityModel(), "entities", null, 0, -1, QualityModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getQualityModel_Factors(), this.getFactor(), this.getFactor_QualityModel(), "factors", null, 0, -1, QualityModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getQualityModel_Evaluations(), this.getEvaluation(), this.getEvaluation_QualityModel(), "evaluations", null, 0, -1, QualityModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getQualityModel_Measures(), this.getMeasure(), this.getMeasure_QualityModel(), "measures", null, 0, -1, QualityModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getQualityModel_MeasurementMethods(), this.getMeasurementMethod(), this.getMeasurementMethod_QualityModel(), "measurementMethods", null, 0, -1, QualityModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getQualityModel_Tools(), this.getTool(), this.getTool_QualityModel(), "tools", null, 0, -1, QualityModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getQualityModel_Tags(), this.getTag(), this.getTag_QualityModel(), "tags", null, 0, -1, QualityModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getQualityModel_Sources(), this.getSource(), this.getSource_QualityModel(), "sources", null, 0, -1, QualityModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getQualityModel_Requires(), this.getQualityModel(), null, "requires", null, 0, -1, QualityModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getQualityModel_SchoolGradeBoundary2(), theXMLTypePackage.getDouble(), "schoolGradeBoundary2", "0.98", 1, 1, QualityModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getQualityModel_SchoolGradeBoundary3(), theXMLTypePackage.getDouble(), "schoolGradeBoundary3", "0.96", 1, 1, QualityModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getQualityModel_SchoolGradeBoundary4(), theXMLTypePackage.getDouble(), "schoolGradeBoundary4", "0.94", 1, 1, QualityModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getQualityModel_SchoolGradeBoundary5(), theXMLTypePackage.getDouble(), "schoolGradeBoundary5", "0.92", 1, 1, QualityModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getQualityModel_SchoolGradeBoundary6(), theXMLTypePackage.getDouble(), "schoolGradeBoundary6", "0.90", 1, 1, QualityModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(qualityModelElementEClass, QualityModelElement.class, "QualityModelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getQualityModelElement_QualifiedName(), ecorePackage.getEString(), "qualifiedName", null, 0, 1, QualityModelElement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getQualityModelElement_OriginatesFrom(), this.getSource(), null, "originatesFrom", null, 0, -1, QualityModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sourceEClass, Source.class, "Source", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSource_QualityModel(), this.getQualityModel(), this.getQualityModel_Sources(), "qualityModel", null, 1, 1, Source.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(describedElementEClass, DescribedElement.class, "DescribedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDescribedElement_Description(), ecorePackage.getEString(), "description", null, 0, 1, DescribedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(namedElementEClass, NamedElement.class, "NamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), ecorePackage.getEString(), "name", null, 1, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNamedElement_Title(), ecorePackage.getEString(), "title", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(annotatedElementEClass, AnnotatedElement.class, "AnnotatedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAnnotatedElement_Annotations(), this.getAnnotation(), null, "annotations", null, 0, -1, AnnotatedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnnotatedElement_AdvancedAnnotations(), this.getAnnotationBase(), null, "advancedAnnotations", null, 0, -1, AnnotatedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(annotationEClass, Map.Entry.class, "Annotation", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAnnotation_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnnotation_Value(), ecorePackage.getEString(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tagEClass, Tag.class, "Tag", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTag_QualityModel(), this.getQualityModel(), this.getQualityModel_Tags(), "qualityModel", null, 1, 1, Tag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(taggedElementEClass, TaggedElement.class, "TaggedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTaggedElement_TaggedBy(), this.getTag(), null, "taggedBy", null, 0, -1, TaggedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(entityEClass, Entity.class, "Entity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEntity_IsA(), this.getSpecialization(), this.getSpecialization_Child(), "isA", null, 0, -1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntity_IsADirect(), this.getEntity(), null, "isADirect", null, 0, -1, Entity.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getEntity_PartOf(), this.getDecomposition(), this.getDecomposition_Child(), "partOf", null, 0, 1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntity_PartOfDirect(), this.getEntity(), null, "partOfDirect", null, 0, 1, Entity.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getEntity_QualityModel(), this.getQualityModel(), this.getQualityModel_Entities(), "qualityModel", null, 1, 1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntity_Stakeholder(), ecorePackage.getEBoolean(), "stakeholder", null, 0, 1, Entity.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntity_UseCase(), ecorePackage.getEBoolean(), "useCase", null, 0, 1, Entity.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(specializationEClass, Specialization.class, "Specialization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSpecialization_Parent(), this.getEntity(), null, "parent", null, 1, 1, Specialization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpecialization_Child(), this.getEntity(), this.getEntity_IsA(), "child", null, 1, 1, Specialization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(decompositionEClass, Decomposition.class, "Decomposition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDecomposition_Parent(), this.getEntity(), null, "parent", null, 1, 1, Decomposition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDecomposition_Child(), this.getEntity(), this.getEntity_PartOf(), "child", null, 1, 1, Decomposition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(characterizingElementEClass, CharacterizingElement.class, "CharacterizingElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCharacterizingElement_Characterizes(), this.getEntity(), null, "characterizes", null, 0, 1, CharacterizingElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(factorEClass, Factor.class, "Factor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFactor_Influences(), this.getImpact(), this.getImpact_Source(), "influences", null, 0, -1, Factor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getFactor_InfluencesDirect(), this.getFactor(), null, "influencesDirect", null, 0, -1, Factor.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getFactor_Refines(), this.getFactorRefinement(), this.getFactorRefinement_Child(), "refines", null, 0, -1, Factor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFactor_RefinesDirect(), this.getFactor(), null, "refinesDirect", null, 0, -1, Factor.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getFactor_QualityModel(), this.getQualityModel(), this.getQualityModel_Factors(), "qualityModel", null, 1, 1, Factor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(impactEClass, Impact.class, "Impact", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getImpact_Source(), this.getFactor(), this.getFactor_Influences(), "source", null, 1, 1, Impact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getImpact_Target(), this.getFactor(), null, "target", null, 1, 1, Impact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getImpact_Justification(), ecorePackage.getEString(), "justification", null, 1, 1, Impact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getImpact_Effect(), this.getEffect(), "effect", "POSITIVE", 0, 1, Impact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(evaluationEClass, Evaluation.class, "Evaluation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEvaluation_Evaluates(), this.getFactor(), null, "evaluates", null, 1, 1, Evaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEvaluation_QualityModel(), this.getQualityModel(), this.getQualityModel_Evaluations(), "qualityModel", null, 1, 1, Evaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEvaluation_MaximumPoints(), ecorePackage.getEInt(), "maximumPoints", "100", 0, 1, Evaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEvaluation_Completeness(), ecorePackage.getEInt(), "completeness", "100", 1, 1, Evaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(factorRefinementEClass, FactorRefinement.class, "FactorRefinement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFactorRefinement_Child(), this.getFactor(), this.getFactor_Refines(), "child", null, 1, 1, FactorRefinement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFactorRefinement_Parent(), this.getFactor(), null, "parent", null, 1, 1, FactorRefinement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(measurementEClass, Measurement.class, "Measurement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMeasurement_Parent(), this.getFactor(), null, "parent", null, 1, 1, Measurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeasurement_Child(), this.getMeasure(), this.getMeasure_Measures(), "child", null, 1, 1, Measurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(measureRefinementEClass, MeasureRefinement.class, "MeasureRefinement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMeasureRefinement_Parent(), this.getMeasure(), null, "parent", null, 1, 1, MeasureRefinement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeasureRefinement_Child(), this.getMeasure(), this.getMeasure_Refines(), "child", null, 1, 1, MeasureRefinement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(measureEClass, Measure.class, "Measure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMeasure_Type(), this.getType(), "type", null, 0, 1, Measure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeasure_Measures(), this.getMeasurement(), this.getMeasurement_Child(), "measures", null, 0, -1, Measure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getMeasure_MeasuresDirect(), this.getFactor(), null, "measuresDirect", null, 0, -1, Measure.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getMeasure_QualityModel(), this.getQualityModel(), this.getQualityModel_Measures(), "qualityModel", null, 1, 1, Measure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeasure_Refines(), this.getMeasureRefinement(), this.getMeasureRefinement_Child(), "refines", null, 0, -1, Measure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeasure_RefinesDirect(), this.getMeasure(), null, "refinesDirect", null, 0, -1, Measure.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(measurementMethodEClass, MeasurementMethod.class, "MeasurementMethod", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMeasurementMethod_Determines(), this.getMeasure(), null, "determines", null, 1, 1, MeasurementMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeasurementMethod_QualityModel(), this.getQualityModel(), this.getQualityModel_MeasurementMethods(), "qualityModel", null, 1, 1, MeasurementMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(measureAggregationEClass, MeasureAggregation.class, "MeasureAggregation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(instrumentEClass, Instrument.class, "Instrument", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(manualInstrumentEClass, ManualInstrument.class, "ManualInstrument", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(toolBasedInstrumentEClass, ToolBasedInstrument.class, "ToolBasedInstrument", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getToolBasedInstrument_Tool(), this.getTool(), null, "tool", null, 1, 1, ToolBasedInstrument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getToolBasedInstrument_Metric(), ecorePackage.getEString(), "metric", null, 1, 1, ToolBasedInstrument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(toolEClass, Tool.class, "Tool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTool_QualityModel(), this.getQualityModel(), this.getQualityModel_Tools(), "qualityModel", null, 1, 1, Tool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(annotationBaseEClass, AnnotationBase.class, "AnnotationBase", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(qieslEvaluationEClass, QIESLEvaluation.class, "QIESLEvaluation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(textEvaluationEClass, TextEvaluation.class, "TextEvaluation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTextEvaluation_Specification(), ecorePackage.getEString(), "specification", null, 1, 1, TextEvaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(formBasedEvaluationEClass, FormBasedEvaluation.class, "FormBasedEvaluation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(singleMeasureEvaluationEClass, SingleMeasureEvaluation.class, "SingleMeasureEvaluation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(factorAggregationEClass, FactorAggregation.class, "FactorAggregation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(normalizationMeasureEClass, NormalizationMeasure.class, "NormalizationMeasure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(qieslAggregationEClass, QIESLAggregation.class, "QIESLAggregation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(textAggregationEClass, TextAggregation.class, "TextAggregation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTextAggregation_Specification(), ecorePackage.getEString(), "specification", null, 1, 1, TextAggregation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(formBasedMeasureAggregationEClass, FormBasedMeasureAggregation.class, "FormBasedMeasureAggregation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(factorRankingEClass, FactorRanking.class, "FactorRanking", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFactorRanking_Factor(), this.getFactor(), null, "factor", null, 1, 1, FactorRanking.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(linearFunctionEClass, LinearFunction.class, "LinearFunction", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLinearFunction_LowerBound(), ecorePackage.getEDouble(), "lowerBound", null, 1, 1, LinearFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLinearFunction_UpperBound(), ecorePackage.getEDouble(), "upperBound", null, 1, 1, LinearFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(functionEClass, Function.class, "Function", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(linearIncreasingFunctionEClass, LinearIncreasingFunction.class, "LinearIncreasingFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(linearDecreasingFunctionEClass, LinearDecreasingFunction.class, "LinearDecreasingFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(weightedSumFactorAggregationEClass, WeightedSumFactorAggregation.class, "WeightedSumFactorAggregation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWeightedSumFactorAggregation_Rankings(), this.getFactorRanking(), null, "rankings", null, 1, -1, WeightedSumFactorAggregation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(findingsUnionMeasureAggregationEClass, FindingsUnionMeasureAggregation.class, "FindingsUnionMeasureAggregation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(numberMeanMeasureAggregationEClass, NumberMeanMeasureAggregation.class, "NumberMeanMeasureAggregation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(manualEvaluationEClass, ManualEvaluation.class, "ManualEvaluation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(measureEvaluationEClass, MeasureEvaluation.class, "MeasureEvaluation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMeasureEvaluation_Measure(), this.getMeasure(), null, "measure", null, 1, 1, MeasureEvaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeasureEvaluation_NormlizationMeasure(), this.getNormalizationMeasure(), null, "normlizationMeasure", null, 0, 1, MeasureEvaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeasureEvaluation_Function(), this.getFunction(), null, "function", null, 1, 1, MeasureEvaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMeasureEvaluation_Range(), ecorePackage.getEString(), "range", null, 0, 1, MeasureEvaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rankingEClass, Ranking.class, "Ranking", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRanking_Rank(), ecorePackage.getEInt(), "rank", null, 0, 1, Ranking.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRanking_Weight(), ecorePackage.getEDouble(), "weight", null, 0, 1, Ranking.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(multiMeasureEvaluationEClass, MultiMeasureEvaluation.class, "MultiMeasureEvaluation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(weightedSumMultiMeasureEvaluationEClass, WeightedSumMultiMeasureEvaluation.class, "WeightedSumMultiMeasureEvaluation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWeightedSumMultiMeasureEvaluation_Rankings(), this.getMeasureRanking(), null, "rankings", null, 1, -1, WeightedSumMultiMeasureEvaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(measureRankingEClass, MeasureRanking.class, "MeasureRanking", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(qualityModelResultEClass, QualityModelResult.class, "QualityModelResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getQualityModelResult_System(), ecorePackage.getEString(), "system", null, 1, 1, QualityModelResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getQualityModelResult_Date(), ecorePackage.getEDate(), "date", null, 1, 1, QualityModelResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getQualityModelResult_MeasurementResults(), this.getMeasurementResult(), null, "measurementResults", null, 0, -1, QualityModelResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getQualityModelResult_EvaluationResults(), this.getEvaluationResult(), null, "evaluationResults", null, 0, -1, QualityModelResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(measurementResultEClass, MeasurementResult.class, "MeasurementResult", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMeasurementResult_ResultsFrom(), this.getMeasurementMethod(), null, "resultsFrom", null, 1, 1, MeasurementResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(numberMeasurementResultEClass, NumberMeasurementResult.class, "NumberMeasurementResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNumberMeasurementResult_Value(), this.getDoubleInterval(), null, "value", null, 1, 1, NumberMeasurementResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(findingsMeasurementResultEClass, FindingsMeasurementResult.class, "FindingsMeasurementResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFindingsMeasurementResult_Count(), ecorePackage.getEInt(), "count", null, 1, 1, FindingsMeasurementResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFindingsMeasurementResult_Findings(), this.getFindingCollection(), "findings", null, 0, 1, FindingsMeasurementResult.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFindingsMeasurementResult_FindingMessages(), this.getFindingMessage(), null, "findingMessages", null, 0, -1, FindingsMeasurementResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(doubleIntervalEClass, DoubleInterval.class, "DoubleInterval", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDoubleInterval_Lower(), ecorePackage.getEDouble(), "lower", null, 1, 1, DoubleInterval.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoubleInterval_Upper(), ecorePackage.getEDouble(), "upper", null, 1, 1, DoubleInterval.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(evaluationResultEClass, EvaluationResult.class, "EvaluationResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEvaluationResult_Value(), this.getDoubleInterval(), null, "value", null, 1, 1, EvaluationResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEvaluationResult_ResultsFrom(), this.getEvaluation(), null, "resultsFrom", null, 1, 1, EvaluationResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(singleMeasureEvaluationResultEClass, SingleMeasureEvaluationResult.class, "SingleMeasureEvaluationResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSingleMeasureEvaluationResult_RatioAffected(), ecorePackage.getEDouble(), "ratioAffected", null, 0, 1, SingleMeasureEvaluationResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(multiMeasureEvaluationResultEClass, MultiMeasureEvaluationResult.class, "MultiMeasureEvaluationResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMultiMeasureEvaluationResult_EvaluationResults(), this.getMeasureRankingEvaluationResult(), null, "evaluationResults", null, 0, -1, MultiMeasureEvaluationResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(measureRankingEvaluationResultEClass, MeasureRankingEvaluationResult.class, "MeasureRankingEvaluationResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMeasureRankingEvaluationResult_RatioAffected(), ecorePackage.getEDouble(), "ratioAffected", null, 0, 1, MeasureRankingEvaluationResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeasureRankingEvaluationResult_Value(), this.getDoubleInterval(), null, "value", null, 1, 1, MeasureRankingEvaluationResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeasureRankingEvaluationResult_ResultsFrom(), this.getMeasureRanking(), null, "resultsFrom", null, 1, 1, MeasureRankingEvaluationResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resultEClass, Result.class, "Result", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResult_Message(), ecorePackage.getEString(), "message", null, 0, 1, Result.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(findingMessageEClass, FindingMessage.class, "FindingMessage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFindingMessage_Message(), ecorePackage.getEString(), "message", null, 0, 1, FindingMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFindingMessage_Location(), ecorePackage.getEString(), "location", null, 0, 1, FindingMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(effectEEnum, Effect.class, "Effect");
		addEEnumLiteral(effectEEnum, Effect.POSITIVE);
		addEEnumLiteral(effectEEnum, Effect.NEGATIVE);

		initEEnum(typeEEnum, Type.class, "Type");
		addEEnumLiteral(typeEEnum, Type.NONE);
		addEEnumLiteral(typeEEnum, Type.FINDINGS);
		addEEnumLiteral(typeEEnum, Type.NUMBER);

		// Initialize data types
		initEDataType(findingCollectionEDataType, FindingCollection.class, "FindingCollection", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";			
		addAnnotation
		  (qualityModelElementEClass, 
		   source, 
		   new String[] {
			 "constraints", "violatesRequires"
		   });			
		addAnnotation
		  (describedElementEClass, 
		   source, 
		   new String[] {
			 "constraints", "noDescription"
		   });																								
	}

} //QmPackageImpl
