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
 * $Id: QmPackage.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.quamoco.qm.QmFactory
 * @model kind="package"
 * @generated
 */
public interface QmPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "qm";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.quamoco.de/qm/v17";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "qm";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	QmPackage eINSTANCE = de.quamoco.qm.impl.QmPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.QualityModelElementImpl <em>Quality Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.QualityModelElementImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getQualityModelElement()
	 * @generated
	 */
	int QUALITY_MODEL_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL_ELEMENT__QUALIFIED_NAME = 0;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL_ELEMENT__ORIGINATES_FROM = 1;

	/**
	 * The number of structural features of the '<em>Quality Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.TaggedElementImpl <em>Tagged Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.TaggedElementImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getTaggedElement()
	 * @generated
	 */
	int TAGGED_ELEMENT = 8;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_ELEMENT__QUALIFIED_NAME = QUALITY_MODEL_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_ELEMENT__ORIGINATES_FROM = QUALITY_MODEL_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_ELEMENT__TAGGED_BY = QUALITY_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tagged Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_ELEMENT_FEATURE_COUNT = QUALITY_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.AnnotatedElementImpl <em>Annotated Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.AnnotatedElementImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getAnnotatedElement()
	 * @generated
	 */
	int ANNOTATED_ELEMENT = 5;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATED_ELEMENT__QUALIFIED_NAME = TAGGED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATED_ELEMENT__ORIGINATES_FROM = TAGGED_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATED_ELEMENT__TAGGED_BY = TAGGED_ELEMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATED_ELEMENT__ANNOTATIONS = TAGGED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATED_ELEMENT__ADVANCED_ANNOTATIONS = TAGGED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Annotated Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATED_ELEMENT_FEATURE_COUNT = TAGGED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.DescribedElementImpl <em>Described Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.DescribedElementImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getDescribedElement()
	 * @generated
	 */
	int DESCRIBED_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBED_ELEMENT__QUALIFIED_NAME = ANNOTATED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBED_ELEMENT__ORIGINATES_FROM = ANNOTATED_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBED_ELEMENT__TAGGED_BY = ANNOTATED_ELEMENT__TAGGED_BY;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.NamedElementImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 4;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.FactorImpl <em>Factor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.FactorImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getFactor()
	 * @generated
	 */
	int FACTOR = 13;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.ImpactImpl <em>Impact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.ImpactImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getImpact()
	 * @generated
	 */
	int IMPACT = 14;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.QualityModelImpl <em>Quality Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.QualityModelImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getQualityModel()
	 * @generated
	 */
	int QUALITY_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBED_ELEMENT__ANNOTATIONS = ANNOTATED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBED_ELEMENT__ADVANCED_ANNOTATIONS = ANNOTATED_ELEMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBED_ELEMENT__DESCRIPTION = ANNOTATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Described Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIBED_ELEMENT_FEATURE_COUNT = ANNOTATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__QUALIFIED_NAME = DESCRIBED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__ORIGINATES_FROM = DESCRIBED_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__TAGGED_BY = DESCRIBED_ELEMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__ANNOTATIONS = DESCRIBED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__ADVANCED_ANNOTATIONS = DESCRIBED_ELEMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__DESCRIPTION = DESCRIBED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = DESCRIBED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__TITLE = DESCRIBED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = DESCRIBED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__QUALIFIED_NAME = NAMED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__ORIGINATES_FROM = NAMED_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__TAGGED_BY = NAMED_ELEMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__ANNOTATIONS = NAMED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__ADVANCED_ANNOTATIONS = NAMED_ELEMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__TITLE = NAMED_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Entities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__ENTITIES = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Factors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__FACTORS = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Evaluations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__EVALUATIONS = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Measures</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__MEASURES = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Measurement Methods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__MEASUREMENT_METHODS = NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Tools</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__TOOLS = NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__TAGS = NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__SOURCES = NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Requires</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__REQUIRES = NAMED_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>School Grade Boundary2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY2 = NAMED_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>School Grade Boundary3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY3 = NAMED_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>School Grade Boundary4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY4 = NAMED_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>School Grade Boundary5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY5 = NAMED_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>School Grade Boundary6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY6 = NAMED_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The number of structural features of the '<em>Quality Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.AnnotationImpl <em>Annotation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.AnnotationImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getAnnotation()
	 * @generated
	 */
	int ANNOTATION = 6;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.EntityImpl <em>Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.EntityImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getEntity()
	 * @generated
	 */
	int ENTITY = 9;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.MeasureImpl <em>Measure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.MeasureImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getMeasure()
	 * @generated
	 */
	int MEASURE = 19;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.TagImpl <em>Tag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.TagImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getTag()
	 * @generated
	 */
	int TAG = 7;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.CharacterizingElementImpl <em>Characterizing Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.CharacterizingElementImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getCharacterizingElement()
	 * @generated
	 */
	int CHARACTERIZING_ELEMENT = 12;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.EvaluationImpl <em>Evaluation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.EvaluationImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getEvaluation()
	 * @generated
	 */
	int EVALUATION = 15;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.FactorRefinementImpl <em>Factor Refinement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.FactorRefinementImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getFactorRefinement()
	 * @generated
	 */
	int FACTOR_REFINEMENT = 16;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.MeasurementMethodImpl <em>Measurement Method</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.MeasurementMethodImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getMeasurementMethod()
	 * @generated
	 */
	int MEASUREMENT_METHOD = 20;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.InstrumentImpl <em>Instrument</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.InstrumentImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getInstrument()
	 * @generated
	 */
	int INSTRUMENT = 22;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.ToolBasedInstrumentImpl <em>Tool Based Instrument</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.ToolBasedInstrumentImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getToolBasedInstrument()
	 * @generated
	 */
	int TOOL_BASED_INSTRUMENT = 24;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.ManualInstrumentImpl <em>Manual Instrument</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.ManualInstrumentImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getManualInstrument()
	 * @generated
	 */
	int MANUAL_INSTRUMENT = 23;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.ToolImpl <em>Tool</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.ToolImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getTool()
	 * @generated
	 */
	int TOOL = 25;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.SourceImpl <em>Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.SourceImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getSource()
	 * @generated
	 */
	int SOURCE = 2;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__QUALIFIED_NAME = NAMED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__ORIGINATES_FROM = NAMED_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__TAGGED_BY = NAMED_ELEMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__ANNOTATIONS = NAMED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__ADVANCED_ANNOTATIONS = NAMED_ELEMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__TITLE = NAMED_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__QUALITY_MODEL = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_FEATURE_COUNT = 2;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__QUALIFIED_NAME = NAMED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__ORIGINATES_FROM = NAMED_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__TAGGED_BY = NAMED_ELEMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__ANNOTATIONS = NAMED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__ADVANCED_ANNOTATIONS = NAMED_ELEMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__TITLE = NAMED_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__QUALITY_MODEL = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__QUALIFIED_NAME = NAMED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__ORIGINATES_FROM = NAMED_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__TAGGED_BY = NAMED_ELEMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__ANNOTATIONS = NAMED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__ADVANCED_ANNOTATIONS = NAMED_ELEMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__TITLE = NAMED_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Is A</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__IS_A = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is ADirect</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__IS_ADIRECT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Part Of</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__PART_OF = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Part Of Direct</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__PART_OF_DIRECT = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__QUALITY_MODEL = NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Stakeholder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__STAKEHOLDER = NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Use Case</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__USE_CASE = NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.MeasurementImpl <em>Measurement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.MeasurementImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getMeasurement()
	 * @generated
	 */
	int MEASUREMENT = 17;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.SpecializationImpl <em>Specialization</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.SpecializationImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getSpecialization()
	 * @generated
	 */
	int SPECIALIZATION = 10;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIALIZATION__QUALIFIED_NAME = ANNOTATED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIALIZATION__ORIGINATES_FROM = ANNOTATED_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIALIZATION__TAGGED_BY = ANNOTATED_ELEMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIALIZATION__ANNOTATIONS = ANNOTATED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIALIZATION__ADVANCED_ANNOTATIONS = ANNOTATED_ELEMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIALIZATION__PARENT = ANNOTATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Child</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIALIZATION__CHILD = ANNOTATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Specialization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIALIZATION_FEATURE_COUNT = ANNOTATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.DecompositionImpl <em>Decomposition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.DecompositionImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getDecomposition()
	 * @generated
	 */
	int DECOMPOSITION = 11;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECOMPOSITION__QUALIFIED_NAME = ANNOTATED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECOMPOSITION__ORIGINATES_FROM = ANNOTATED_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECOMPOSITION__TAGGED_BY = ANNOTATED_ELEMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECOMPOSITION__ANNOTATIONS = ANNOTATED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECOMPOSITION__ADVANCED_ANNOTATIONS = ANNOTATED_ELEMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECOMPOSITION__PARENT = ANNOTATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Child</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECOMPOSITION__CHILD = ANNOTATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Decomposition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECOMPOSITION_FEATURE_COUNT = ANNOTATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTERIZING_ELEMENT__QUALIFIED_NAME = NAMED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTERIZING_ELEMENT__ORIGINATES_FROM = NAMED_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTERIZING_ELEMENT__TAGGED_BY = NAMED_ELEMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTERIZING_ELEMENT__ANNOTATIONS = NAMED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTERIZING_ELEMENT__ADVANCED_ANNOTATIONS = NAMED_ELEMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTERIZING_ELEMENT__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTERIZING_ELEMENT__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTERIZING_ELEMENT__TITLE = NAMED_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Characterizes</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTERIZING_ELEMENT__CHARACTERIZES = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Characterizing Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTERIZING_ELEMENT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR__QUALIFIED_NAME = CHARACTERIZING_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR__ORIGINATES_FROM = CHARACTERIZING_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR__TAGGED_BY = CHARACTERIZING_ELEMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR__ANNOTATIONS = CHARACTERIZING_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR__ADVANCED_ANNOTATIONS = CHARACTERIZING_ELEMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR__DESCRIPTION = CHARACTERIZING_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR__NAME = CHARACTERIZING_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR__TITLE = CHARACTERIZING_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Characterizes</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR__CHARACTERIZES = CHARACTERIZING_ELEMENT__CHARACTERIZES;

	/**
	 * The feature id for the '<em><b>Influences</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR__INFLUENCES = CHARACTERIZING_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Influences Direct</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR__INFLUENCES_DIRECT = CHARACTERIZING_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Refines</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR__REFINES = CHARACTERIZING_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Refines Direct</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR__REFINES_DIRECT = CHARACTERIZING_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR__QUALITY_MODEL = CHARACTERIZING_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Factor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_FEATURE_COUNT = CHARACTERIZING_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPACT__QUALIFIED_NAME = ANNOTATED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPACT__ORIGINATES_FROM = ANNOTATED_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPACT__TAGGED_BY = ANNOTATED_ELEMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPACT__ANNOTATIONS = ANNOTATED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPACT__ADVANCED_ANNOTATIONS = ANNOTATED_ELEMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPACT__SOURCE = ANNOTATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPACT__TARGET = ANNOTATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Justification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPACT__JUSTIFICATION = ANNOTATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Effect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPACT__EFFECT = ANNOTATED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Impact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPACT_FEATURE_COUNT = ANNOTATED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION__QUALIFIED_NAME = NAMED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION__ORIGINATES_FROM = NAMED_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION__TAGGED_BY = NAMED_ELEMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION__ANNOTATIONS = NAMED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION__ADVANCED_ANNOTATIONS = NAMED_ELEMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION__TITLE = NAMED_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Evaluates</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION__EVALUATES = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION__QUALITY_MODEL = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Maximum Points</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION__MAXIMUM_POINTS = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Completeness</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION__COMPLETENESS = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_REFINEMENT__QUALIFIED_NAME = ANNOTATED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_REFINEMENT__ORIGINATES_FROM = ANNOTATED_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_REFINEMENT__TAGGED_BY = ANNOTATED_ELEMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_REFINEMENT__ANNOTATIONS = ANNOTATED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_REFINEMENT__ADVANCED_ANNOTATIONS = ANNOTATED_ELEMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Child</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_REFINEMENT__CHILD = ANNOTATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_REFINEMENT__PARENT = ANNOTATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Factor Refinement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_REFINEMENT_FEATURE_COUNT = ANNOTATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT__QUALIFIED_NAME = ANNOTATED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT__ORIGINATES_FROM = ANNOTATED_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT__TAGGED_BY = ANNOTATED_ELEMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT__ANNOTATIONS = ANNOTATED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT__ADVANCED_ANNOTATIONS = ANNOTATED_ELEMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT__PARENT = ANNOTATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Child</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT__CHILD = ANNOTATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_FEATURE_COUNT = ANNOTATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.MeasureRefinementImpl <em>Measure Refinement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.MeasureRefinementImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getMeasureRefinement()
	 * @generated
	 */
	int MEASURE_REFINEMENT = 18;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_REFINEMENT__QUALIFIED_NAME = ANNOTATED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_REFINEMENT__ORIGINATES_FROM = ANNOTATED_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_REFINEMENT__TAGGED_BY = ANNOTATED_ELEMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_REFINEMENT__ANNOTATIONS = ANNOTATED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_REFINEMENT__ADVANCED_ANNOTATIONS = ANNOTATED_ELEMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_REFINEMENT__PARENT = ANNOTATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Child</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_REFINEMENT__CHILD = ANNOTATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Measure Refinement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_REFINEMENT_FEATURE_COUNT = ANNOTATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__QUALIFIED_NAME = CHARACTERIZING_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__ORIGINATES_FROM = CHARACTERIZING_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__TAGGED_BY = CHARACTERIZING_ELEMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__ANNOTATIONS = CHARACTERIZING_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__ADVANCED_ANNOTATIONS = CHARACTERIZING_ELEMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__DESCRIPTION = CHARACTERIZING_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__NAME = CHARACTERIZING_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__TITLE = CHARACTERIZING_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Characterizes</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__CHARACTERIZES = CHARACTERIZING_ELEMENT__CHARACTERIZES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__TYPE = CHARACTERIZING_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Measures</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__MEASURES = CHARACTERIZING_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Measures Direct</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__MEASURES_DIRECT = CHARACTERIZING_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__QUALITY_MODEL = CHARACTERIZING_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Refines</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__REFINES = CHARACTERIZING_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Refines Direct</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__REFINES_DIRECT = CHARACTERIZING_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Measure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_FEATURE_COUNT = CHARACTERIZING_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_METHOD__QUALIFIED_NAME = ANNOTATED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_METHOD__ORIGINATES_FROM = ANNOTATED_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_METHOD__TAGGED_BY = ANNOTATED_ELEMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_METHOD__ANNOTATIONS = ANNOTATED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_METHOD__ADVANCED_ANNOTATIONS = ANNOTATED_ELEMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Determines</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_METHOD__DETERMINES = ANNOTATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_METHOD__QUALITY_MODEL = ANNOTATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Measurement Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_METHOD_FEATURE_COUNT = ANNOTATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.AnnotationBaseImpl <em>Annotation Base</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.AnnotationBaseImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getAnnotationBase()
	 * @generated
	 */
	int ANNOTATION_BASE = 26;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.TextEvaluationImpl <em>Text Evaluation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.TextEvaluationImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getTextEvaluation()
	 * @generated
	 */
	int TEXT_EVALUATION = 28;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.QIESLEvaluationImpl <em>QIESL Evaluation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.QIESLEvaluationImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getQIESLEvaluation()
	 * @generated
	 */
	int QIESL_EVALUATION = 27;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.FormBasedEvaluationImpl <em>Form Based Evaluation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.FormBasedEvaluationImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getFormBasedEvaluation()
	 * @generated
	 */
	int FORM_BASED_EVALUATION = 29;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.SingleMeasureEvaluationImpl <em>Single Measure Evaluation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.SingleMeasureEvaluationImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getSingleMeasureEvaluation()
	 * @generated
	 */
	int SINGLE_MEASURE_EVALUATION = 30;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.FactorAggregationImpl <em>Factor Aggregation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.FactorAggregationImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getFactorAggregation()
	 * @generated
	 */
	int FACTOR_AGGREGATION = 31;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.NormalizationMeasureImpl <em>Normalization Measure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.NormalizationMeasureImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getNormalizationMeasure()
	 * @generated
	 */
	int NORMALIZATION_MEASURE = 32;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.TextAggregationImpl <em>Text Aggregation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.TextAggregationImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getTextAggregation()
	 * @generated
	 */
	int TEXT_AGGREGATION = 34;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.QIESLAggregationImpl <em>QIESL Aggregation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.QIESLAggregationImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getQIESLAggregation()
	 * @generated
	 */
	int QIESL_AGGREGATION = 33;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.FormBasedMeasureAggregationImpl <em>Form Based Measure Aggregation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.FormBasedMeasureAggregationImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getFormBasedMeasureAggregation()
	 * @generated
	 */
	int FORM_BASED_MEASURE_AGGREGATION = 35;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.MeasureAggregationImpl <em>Measure Aggregation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.MeasureAggregationImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getMeasureAggregation()
	 * @generated
	 */
	int MEASURE_AGGREGATION = 21;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_AGGREGATION__QUALIFIED_NAME = MEASUREMENT_METHOD__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_AGGREGATION__ORIGINATES_FROM = MEASUREMENT_METHOD__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_AGGREGATION__TAGGED_BY = MEASUREMENT_METHOD__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_AGGREGATION__ANNOTATIONS = MEASUREMENT_METHOD__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_AGGREGATION__ADVANCED_ANNOTATIONS = MEASUREMENT_METHOD__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Determines</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_AGGREGATION__DETERMINES = MEASUREMENT_METHOD__DETERMINES;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_AGGREGATION__QUALITY_MODEL = MEASUREMENT_METHOD__QUALITY_MODEL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_AGGREGATION__DESCRIPTION = MEASUREMENT_METHOD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_AGGREGATION__NAME = MEASUREMENT_METHOD_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_AGGREGATION__TITLE = MEASUREMENT_METHOD_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Measure Aggregation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_AGGREGATION_FEATURE_COUNT = MEASUREMENT_METHOD_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENT__QUALIFIED_NAME = MEASUREMENT_METHOD__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENT__ORIGINATES_FROM = MEASUREMENT_METHOD__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENT__TAGGED_BY = MEASUREMENT_METHOD__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENT__ANNOTATIONS = MEASUREMENT_METHOD__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENT__ADVANCED_ANNOTATIONS = MEASUREMENT_METHOD__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Determines</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENT__DETERMINES = MEASUREMENT_METHOD__DETERMINES;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENT__QUALITY_MODEL = MEASUREMENT_METHOD__QUALITY_MODEL;

	/**
	 * The number of structural features of the '<em>Instrument</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENT_FEATURE_COUNT = MEASUREMENT_METHOD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_INSTRUMENT__QUALIFIED_NAME = INSTRUMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_INSTRUMENT__ORIGINATES_FROM = INSTRUMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_INSTRUMENT__TAGGED_BY = INSTRUMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_INSTRUMENT__ANNOTATIONS = INSTRUMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_INSTRUMENT__ADVANCED_ANNOTATIONS = INSTRUMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Determines</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_INSTRUMENT__DETERMINES = INSTRUMENT__DETERMINES;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_INSTRUMENT__QUALITY_MODEL = INSTRUMENT__QUALITY_MODEL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_INSTRUMENT__DESCRIPTION = INSTRUMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_INSTRUMENT__NAME = INSTRUMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_INSTRUMENT__TITLE = INSTRUMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Manual Instrument</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_INSTRUMENT_FEATURE_COUNT = INSTRUMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_BASED_INSTRUMENT__QUALIFIED_NAME = INSTRUMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_BASED_INSTRUMENT__ORIGINATES_FROM = INSTRUMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_BASED_INSTRUMENT__TAGGED_BY = INSTRUMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_BASED_INSTRUMENT__ANNOTATIONS = INSTRUMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_BASED_INSTRUMENT__ADVANCED_ANNOTATIONS = INSTRUMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Determines</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_BASED_INSTRUMENT__DETERMINES = INSTRUMENT__DETERMINES;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_BASED_INSTRUMENT__QUALITY_MODEL = INSTRUMENT__QUALITY_MODEL;

	/**
	 * The feature id for the '<em><b>Tool</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_BASED_INSTRUMENT__TOOL = INSTRUMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Metric</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_BASED_INSTRUMENT__METRIC = INSTRUMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Tool Based Instrument</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_BASED_INSTRUMENT_FEATURE_COUNT = INSTRUMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__QUALIFIED_NAME = NAMED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__ORIGINATES_FROM = NAMED_ELEMENT__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__TAGGED_BY = NAMED_ELEMENT__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__ANNOTATIONS = NAMED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__ADVANCED_ANNOTATIONS = NAMED_ELEMENT__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__TITLE = NAMED_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__QUALITY_MODEL = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tool</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Annotation Base</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_BASE_FEATURE_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_EVALUATION__QUALIFIED_NAME = EVALUATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_EVALUATION__ORIGINATES_FROM = EVALUATION__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_EVALUATION__TAGGED_BY = EVALUATION__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_EVALUATION__ANNOTATIONS = EVALUATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_EVALUATION__ADVANCED_ANNOTATIONS = EVALUATION__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_EVALUATION__DESCRIPTION = EVALUATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_EVALUATION__NAME = EVALUATION__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_EVALUATION__TITLE = EVALUATION__TITLE;

	/**
	 * The feature id for the '<em><b>Evaluates</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_EVALUATION__EVALUATES = EVALUATION__EVALUATES;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_EVALUATION__QUALITY_MODEL = EVALUATION__QUALITY_MODEL;

	/**
	 * The feature id for the '<em><b>Maximum Points</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_EVALUATION__MAXIMUM_POINTS = EVALUATION__MAXIMUM_POINTS;

	/**
	 * The feature id for the '<em><b>Completeness</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_EVALUATION__COMPLETENESS = EVALUATION__COMPLETENESS;

	/**
	 * The feature id for the '<em><b>Specification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_EVALUATION__SPECIFICATION = EVALUATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Text Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_EVALUATION_FEATURE_COUNT = EVALUATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_EVALUATION__QUALIFIED_NAME = TEXT_EVALUATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_EVALUATION__ORIGINATES_FROM = TEXT_EVALUATION__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_EVALUATION__TAGGED_BY = TEXT_EVALUATION__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_EVALUATION__ANNOTATIONS = TEXT_EVALUATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_EVALUATION__ADVANCED_ANNOTATIONS = TEXT_EVALUATION__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_EVALUATION__DESCRIPTION = TEXT_EVALUATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_EVALUATION__NAME = TEXT_EVALUATION__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_EVALUATION__TITLE = TEXT_EVALUATION__TITLE;

	/**
	 * The feature id for the '<em><b>Evaluates</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_EVALUATION__EVALUATES = TEXT_EVALUATION__EVALUATES;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_EVALUATION__QUALITY_MODEL = TEXT_EVALUATION__QUALITY_MODEL;

	/**
	 * The feature id for the '<em><b>Maximum Points</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_EVALUATION__MAXIMUM_POINTS = TEXT_EVALUATION__MAXIMUM_POINTS;

	/**
	 * The feature id for the '<em><b>Completeness</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_EVALUATION__COMPLETENESS = TEXT_EVALUATION__COMPLETENESS;

	/**
	 * The feature id for the '<em><b>Specification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_EVALUATION__SPECIFICATION = TEXT_EVALUATION__SPECIFICATION;

	/**
	 * The number of structural features of the '<em>QIESL Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_EVALUATION_FEATURE_COUNT = TEXT_EVALUATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_EVALUATION__QUALIFIED_NAME = EVALUATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_EVALUATION__ORIGINATES_FROM = EVALUATION__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_EVALUATION__TAGGED_BY = EVALUATION__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_EVALUATION__ANNOTATIONS = EVALUATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_EVALUATION__ADVANCED_ANNOTATIONS = EVALUATION__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_EVALUATION__DESCRIPTION = EVALUATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_EVALUATION__NAME = EVALUATION__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_EVALUATION__TITLE = EVALUATION__TITLE;

	/**
	 * The feature id for the '<em><b>Evaluates</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_EVALUATION__EVALUATES = EVALUATION__EVALUATES;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_EVALUATION__QUALITY_MODEL = EVALUATION__QUALITY_MODEL;

	/**
	 * The feature id for the '<em><b>Maximum Points</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_EVALUATION__MAXIMUM_POINTS = EVALUATION__MAXIMUM_POINTS;

	/**
	 * The feature id for the '<em><b>Completeness</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_EVALUATION__COMPLETENESS = EVALUATION__COMPLETENESS;

	/**
	 * The number of structural features of the '<em>Form Based Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_EVALUATION_FEATURE_COUNT = EVALUATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION__QUALIFIED_NAME = FORM_BASED_EVALUATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION__ORIGINATES_FROM = FORM_BASED_EVALUATION__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION__TAGGED_BY = FORM_BASED_EVALUATION__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION__ANNOTATIONS = FORM_BASED_EVALUATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION__ADVANCED_ANNOTATIONS = FORM_BASED_EVALUATION__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION__DESCRIPTION = FORM_BASED_EVALUATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION__NAME = FORM_BASED_EVALUATION__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION__TITLE = FORM_BASED_EVALUATION__TITLE;

	/**
	 * The feature id for the '<em><b>Evaluates</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION__EVALUATES = FORM_BASED_EVALUATION__EVALUATES;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION__QUALITY_MODEL = FORM_BASED_EVALUATION__QUALITY_MODEL;

	/**
	 * The feature id for the '<em><b>Maximum Points</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION__MAXIMUM_POINTS = FORM_BASED_EVALUATION__MAXIMUM_POINTS;

	/**
	 * The feature id for the '<em><b>Completeness</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION__COMPLETENESS = FORM_BASED_EVALUATION__COMPLETENESS;

	/**
	 * The feature id for the '<em><b>Measure</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION__MEASURE = FORM_BASED_EVALUATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Normlization Measure</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION__NORMLIZATION_MEASURE = FORM_BASED_EVALUATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION__FUNCTION = FORM_BASED_EVALUATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Range</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION__RANGE = FORM_BASED_EVALUATION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Single Measure Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION_FEATURE_COUNT = FORM_BASED_EVALUATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_AGGREGATION__QUALIFIED_NAME = FORM_BASED_EVALUATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_AGGREGATION__ORIGINATES_FROM = FORM_BASED_EVALUATION__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_AGGREGATION__TAGGED_BY = FORM_BASED_EVALUATION__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_AGGREGATION__ANNOTATIONS = FORM_BASED_EVALUATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_AGGREGATION__ADVANCED_ANNOTATIONS = FORM_BASED_EVALUATION__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_AGGREGATION__DESCRIPTION = FORM_BASED_EVALUATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_AGGREGATION__NAME = FORM_BASED_EVALUATION__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_AGGREGATION__TITLE = FORM_BASED_EVALUATION__TITLE;

	/**
	 * The feature id for the '<em><b>Evaluates</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_AGGREGATION__EVALUATES = FORM_BASED_EVALUATION__EVALUATES;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_AGGREGATION__QUALITY_MODEL = FORM_BASED_EVALUATION__QUALITY_MODEL;

	/**
	 * The feature id for the '<em><b>Maximum Points</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_AGGREGATION__MAXIMUM_POINTS = FORM_BASED_EVALUATION__MAXIMUM_POINTS;

	/**
	 * The feature id for the '<em><b>Completeness</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_AGGREGATION__COMPLETENESS = FORM_BASED_EVALUATION__COMPLETENESS;

	/**
	 * The number of structural features of the '<em>Factor Aggregation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_AGGREGATION_FEATURE_COUNT = FORM_BASED_EVALUATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMALIZATION_MEASURE__QUALIFIED_NAME = MEASURE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMALIZATION_MEASURE__ORIGINATES_FROM = MEASURE__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMALIZATION_MEASURE__TAGGED_BY = MEASURE__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMALIZATION_MEASURE__ANNOTATIONS = MEASURE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMALIZATION_MEASURE__ADVANCED_ANNOTATIONS = MEASURE__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMALIZATION_MEASURE__DESCRIPTION = MEASURE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMALIZATION_MEASURE__NAME = MEASURE__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMALIZATION_MEASURE__TITLE = MEASURE__TITLE;

	/**
	 * The feature id for the '<em><b>Characterizes</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMALIZATION_MEASURE__CHARACTERIZES = MEASURE__CHARACTERIZES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMALIZATION_MEASURE__TYPE = MEASURE__TYPE;

	/**
	 * The feature id for the '<em><b>Measures</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMALIZATION_MEASURE__MEASURES = MEASURE__MEASURES;

	/**
	 * The feature id for the '<em><b>Measures Direct</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMALIZATION_MEASURE__MEASURES_DIRECT = MEASURE__MEASURES_DIRECT;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMALIZATION_MEASURE__QUALITY_MODEL = MEASURE__QUALITY_MODEL;

	/**
	 * The feature id for the '<em><b>Refines</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMALIZATION_MEASURE__REFINES = MEASURE__REFINES;

	/**
	 * The feature id for the '<em><b>Refines Direct</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMALIZATION_MEASURE__REFINES_DIRECT = MEASURE__REFINES_DIRECT;

	/**
	 * The number of structural features of the '<em>Normalization Measure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMALIZATION_MEASURE_FEATURE_COUNT = MEASURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_AGGREGATION__QUALIFIED_NAME = MEASURE_AGGREGATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_AGGREGATION__ORIGINATES_FROM = MEASURE_AGGREGATION__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_AGGREGATION__TAGGED_BY = MEASURE_AGGREGATION__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_AGGREGATION__ANNOTATIONS = MEASURE_AGGREGATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_AGGREGATION__ADVANCED_ANNOTATIONS = MEASURE_AGGREGATION__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Determines</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_AGGREGATION__DETERMINES = MEASURE_AGGREGATION__DETERMINES;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_AGGREGATION__QUALITY_MODEL = MEASURE_AGGREGATION__QUALITY_MODEL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_AGGREGATION__DESCRIPTION = MEASURE_AGGREGATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_AGGREGATION__NAME = MEASURE_AGGREGATION__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_AGGREGATION__TITLE = MEASURE_AGGREGATION__TITLE;

	/**
	 * The feature id for the '<em><b>Specification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_AGGREGATION__SPECIFICATION = MEASURE_AGGREGATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Text Aggregation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_AGGREGATION_FEATURE_COUNT = MEASURE_AGGREGATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_AGGREGATION__QUALIFIED_NAME = TEXT_AGGREGATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_AGGREGATION__ORIGINATES_FROM = TEXT_AGGREGATION__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_AGGREGATION__TAGGED_BY = TEXT_AGGREGATION__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_AGGREGATION__ANNOTATIONS = TEXT_AGGREGATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_AGGREGATION__ADVANCED_ANNOTATIONS = TEXT_AGGREGATION__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Determines</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_AGGREGATION__DETERMINES = TEXT_AGGREGATION__DETERMINES;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_AGGREGATION__QUALITY_MODEL = TEXT_AGGREGATION__QUALITY_MODEL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_AGGREGATION__DESCRIPTION = TEXT_AGGREGATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_AGGREGATION__NAME = TEXT_AGGREGATION__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_AGGREGATION__TITLE = TEXT_AGGREGATION__TITLE;

	/**
	 * The feature id for the '<em><b>Specification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_AGGREGATION__SPECIFICATION = TEXT_AGGREGATION__SPECIFICATION;

	/**
	 * The number of structural features of the '<em>QIESL Aggregation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QIESL_AGGREGATION_FEATURE_COUNT = TEXT_AGGREGATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_MEASURE_AGGREGATION__QUALIFIED_NAME = MEASURE_AGGREGATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_MEASURE_AGGREGATION__ORIGINATES_FROM = MEASURE_AGGREGATION__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_MEASURE_AGGREGATION__TAGGED_BY = MEASURE_AGGREGATION__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_MEASURE_AGGREGATION__ANNOTATIONS = MEASURE_AGGREGATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_MEASURE_AGGREGATION__ADVANCED_ANNOTATIONS = MEASURE_AGGREGATION__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Determines</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_MEASURE_AGGREGATION__DETERMINES = MEASURE_AGGREGATION__DETERMINES;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_MEASURE_AGGREGATION__QUALITY_MODEL = MEASURE_AGGREGATION__QUALITY_MODEL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_MEASURE_AGGREGATION__DESCRIPTION = MEASURE_AGGREGATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_MEASURE_AGGREGATION__NAME = MEASURE_AGGREGATION__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_MEASURE_AGGREGATION__TITLE = MEASURE_AGGREGATION__TITLE;

	/**
	 * The number of structural features of the '<em>Form Based Measure Aggregation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_BASED_MEASURE_AGGREGATION_FEATURE_COUNT = MEASURE_AGGREGATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.RankingImpl <em>Ranking</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.RankingImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getRanking()
	 * @generated
	 */
	int RANKING = 46;

	/**
	 * The feature id for the '<em><b>Rank</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RANKING__RANK = 0;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RANKING__WEIGHT = 1;

	/**
	 * The number of structural features of the '<em>Ranking</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RANKING_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.FactorRankingImpl <em>Factor Ranking</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.FactorRankingImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getFactorRanking()
	 * @generated
	 */
	int FACTOR_RANKING = 36;

	/**
	 * The feature id for the '<em><b>Rank</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_RANKING__RANK = RANKING__RANK;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_RANKING__WEIGHT = RANKING__WEIGHT;

	/**
	 * The feature id for the '<em><b>Factor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_RANKING__FACTOR = RANKING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Factor Ranking</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTOR_RANKING_FEATURE_COUNT = RANKING_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.FunctionImpl <em>Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.FunctionImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getFunction()
	 * @generated
	 */
	int FUNCTION = 38;

	/**
	 * The number of structural features of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.LinearFunctionImpl <em>Linear Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.LinearFunctionImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getLinearFunction()
	 * @generated
	 */
	int LINEAR_FUNCTION = 37;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_FUNCTION__LOWER_BOUND = FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_FUNCTION__UPPER_BOUND = FUNCTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Linear Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_FUNCTION_FEATURE_COUNT = FUNCTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.LinearIncreasingFunctionImpl <em>Linear Increasing Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.LinearIncreasingFunctionImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getLinearIncreasingFunction()
	 * @generated
	 */
	int LINEAR_INCREASING_FUNCTION = 39;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_INCREASING_FUNCTION__LOWER_BOUND = LINEAR_FUNCTION__LOWER_BOUND;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_INCREASING_FUNCTION__UPPER_BOUND = LINEAR_FUNCTION__UPPER_BOUND;

	/**
	 * The number of structural features of the '<em>Linear Increasing Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_INCREASING_FUNCTION_FEATURE_COUNT = LINEAR_FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.LinearDecreasingFunctionImpl <em>Linear Decreasing Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.LinearDecreasingFunctionImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getLinearDecreasingFunction()
	 * @generated
	 */
	int LINEAR_DECREASING_FUNCTION = 40;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_DECREASING_FUNCTION__LOWER_BOUND = LINEAR_FUNCTION__LOWER_BOUND;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_DECREASING_FUNCTION__UPPER_BOUND = LINEAR_FUNCTION__UPPER_BOUND;

	/**
	 * The number of structural features of the '<em>Linear Decreasing Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_DECREASING_FUNCTION_FEATURE_COUNT = LINEAR_FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.WeightedSumFactorAggregationImpl <em>Weighted Sum Factor Aggregation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.WeightedSumFactorAggregationImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getWeightedSumFactorAggregation()
	 * @generated
	 */
	int WEIGHTED_SUM_FACTOR_AGGREGATION = 41;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_FACTOR_AGGREGATION__QUALIFIED_NAME = FACTOR_AGGREGATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_FACTOR_AGGREGATION__ORIGINATES_FROM = FACTOR_AGGREGATION__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_FACTOR_AGGREGATION__TAGGED_BY = FACTOR_AGGREGATION__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_FACTOR_AGGREGATION__ANNOTATIONS = FACTOR_AGGREGATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_FACTOR_AGGREGATION__ADVANCED_ANNOTATIONS = FACTOR_AGGREGATION__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_FACTOR_AGGREGATION__DESCRIPTION = FACTOR_AGGREGATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_FACTOR_AGGREGATION__NAME = FACTOR_AGGREGATION__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_FACTOR_AGGREGATION__TITLE = FACTOR_AGGREGATION__TITLE;

	/**
	 * The feature id for the '<em><b>Evaluates</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_FACTOR_AGGREGATION__EVALUATES = FACTOR_AGGREGATION__EVALUATES;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_FACTOR_AGGREGATION__QUALITY_MODEL = FACTOR_AGGREGATION__QUALITY_MODEL;

	/**
	 * The feature id for the '<em><b>Maximum Points</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_FACTOR_AGGREGATION__MAXIMUM_POINTS = FACTOR_AGGREGATION__MAXIMUM_POINTS;

	/**
	 * The feature id for the '<em><b>Completeness</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_FACTOR_AGGREGATION__COMPLETENESS = FACTOR_AGGREGATION__COMPLETENESS;

	/**
	 * The feature id for the '<em><b>Rankings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_FACTOR_AGGREGATION__RANKINGS = FACTOR_AGGREGATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Weighted Sum Factor Aggregation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_FACTOR_AGGREGATION_FEATURE_COUNT = FACTOR_AGGREGATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.FindingsUnionMeasureAggregationImpl <em>Findings Union Measure Aggregation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.FindingsUnionMeasureAggregationImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getFindingsUnionMeasureAggregation()
	 * @generated
	 */
	int FINDINGS_UNION_MEASURE_AGGREGATION = 42;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDINGS_UNION_MEASURE_AGGREGATION__QUALIFIED_NAME = FORM_BASED_MEASURE_AGGREGATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDINGS_UNION_MEASURE_AGGREGATION__ORIGINATES_FROM = FORM_BASED_MEASURE_AGGREGATION__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDINGS_UNION_MEASURE_AGGREGATION__TAGGED_BY = FORM_BASED_MEASURE_AGGREGATION__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDINGS_UNION_MEASURE_AGGREGATION__ANNOTATIONS = FORM_BASED_MEASURE_AGGREGATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDINGS_UNION_MEASURE_AGGREGATION__ADVANCED_ANNOTATIONS = FORM_BASED_MEASURE_AGGREGATION__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Determines</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDINGS_UNION_MEASURE_AGGREGATION__DETERMINES = FORM_BASED_MEASURE_AGGREGATION__DETERMINES;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDINGS_UNION_MEASURE_AGGREGATION__QUALITY_MODEL = FORM_BASED_MEASURE_AGGREGATION__QUALITY_MODEL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDINGS_UNION_MEASURE_AGGREGATION__DESCRIPTION = FORM_BASED_MEASURE_AGGREGATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDINGS_UNION_MEASURE_AGGREGATION__NAME = FORM_BASED_MEASURE_AGGREGATION__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDINGS_UNION_MEASURE_AGGREGATION__TITLE = FORM_BASED_MEASURE_AGGREGATION__TITLE;

	/**
	 * The number of structural features of the '<em>Findings Union Measure Aggregation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDINGS_UNION_MEASURE_AGGREGATION_FEATURE_COUNT = FORM_BASED_MEASURE_AGGREGATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.NumberMeanMeasureAggregationImpl <em>Number Mean Measure Aggregation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.NumberMeanMeasureAggregationImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getNumberMeanMeasureAggregation()
	 * @generated
	 */
	int NUMBER_MEAN_MEASURE_AGGREGATION = 43;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_MEAN_MEASURE_AGGREGATION__QUALIFIED_NAME = FORM_BASED_MEASURE_AGGREGATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_MEAN_MEASURE_AGGREGATION__ORIGINATES_FROM = FORM_BASED_MEASURE_AGGREGATION__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_MEAN_MEASURE_AGGREGATION__TAGGED_BY = FORM_BASED_MEASURE_AGGREGATION__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_MEAN_MEASURE_AGGREGATION__ANNOTATIONS = FORM_BASED_MEASURE_AGGREGATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_MEAN_MEASURE_AGGREGATION__ADVANCED_ANNOTATIONS = FORM_BASED_MEASURE_AGGREGATION__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Determines</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_MEAN_MEASURE_AGGREGATION__DETERMINES = FORM_BASED_MEASURE_AGGREGATION__DETERMINES;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_MEAN_MEASURE_AGGREGATION__QUALITY_MODEL = FORM_BASED_MEASURE_AGGREGATION__QUALITY_MODEL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_MEAN_MEASURE_AGGREGATION__DESCRIPTION = FORM_BASED_MEASURE_AGGREGATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_MEAN_MEASURE_AGGREGATION__NAME = FORM_BASED_MEASURE_AGGREGATION__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_MEAN_MEASURE_AGGREGATION__TITLE = FORM_BASED_MEASURE_AGGREGATION__TITLE;

	/**
	 * The number of structural features of the '<em>Number Mean Measure Aggregation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_MEAN_MEASURE_AGGREGATION_FEATURE_COUNT = FORM_BASED_MEASURE_AGGREGATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.ManualEvaluationImpl <em>Manual Evaluation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.ManualEvaluationImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getManualEvaluation()
	 * @generated
	 */
	int MANUAL_EVALUATION = 44;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_EVALUATION__QUALIFIED_NAME = EVALUATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_EVALUATION__ORIGINATES_FROM = EVALUATION__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_EVALUATION__TAGGED_BY = EVALUATION__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_EVALUATION__ANNOTATIONS = EVALUATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_EVALUATION__ADVANCED_ANNOTATIONS = EVALUATION__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_EVALUATION__DESCRIPTION = EVALUATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_EVALUATION__NAME = EVALUATION__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_EVALUATION__TITLE = EVALUATION__TITLE;

	/**
	 * The feature id for the '<em><b>Evaluates</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_EVALUATION__EVALUATES = EVALUATION__EVALUATES;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_EVALUATION__QUALITY_MODEL = EVALUATION__QUALITY_MODEL;

	/**
	 * The feature id for the '<em><b>Maximum Points</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_EVALUATION__MAXIMUM_POINTS = EVALUATION__MAXIMUM_POINTS;

	/**
	 * The feature id for the '<em><b>Completeness</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_EVALUATION__COMPLETENESS = EVALUATION__COMPLETENESS;

	/**
	 * The number of structural features of the '<em>Manual Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_EVALUATION_FEATURE_COUNT = EVALUATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.MeasureEvaluationImpl <em>Measure Evaluation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.MeasureEvaluationImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getMeasureEvaluation()
	 * @generated
	 */
	int MEASURE_EVALUATION = 45;

	/**
	 * The feature id for the '<em><b>Measure</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_EVALUATION__MEASURE = 0;

	/**
	 * The feature id for the '<em><b>Normlization Measure</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_EVALUATION__NORMLIZATION_MEASURE = 1;

	/**
	 * The feature id for the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_EVALUATION__FUNCTION = 2;

	/**
	 * The feature id for the '<em><b>Range</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_EVALUATION__RANGE = 3;

	/**
	 * The number of structural features of the '<em>Measure Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_EVALUATION_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.MultiMeasureEvaluationImpl <em>Multi Measure Evaluation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.MultiMeasureEvaluationImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getMultiMeasureEvaluation()
	 * @generated
	 */
	int MULTI_MEASURE_EVALUATION = 47;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_MEASURE_EVALUATION__QUALIFIED_NAME = FORM_BASED_EVALUATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_MEASURE_EVALUATION__ORIGINATES_FROM = FORM_BASED_EVALUATION__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_MEASURE_EVALUATION__TAGGED_BY = FORM_BASED_EVALUATION__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_MEASURE_EVALUATION__ANNOTATIONS = FORM_BASED_EVALUATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_MEASURE_EVALUATION__ADVANCED_ANNOTATIONS = FORM_BASED_EVALUATION__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_MEASURE_EVALUATION__DESCRIPTION = FORM_BASED_EVALUATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_MEASURE_EVALUATION__NAME = FORM_BASED_EVALUATION__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_MEASURE_EVALUATION__TITLE = FORM_BASED_EVALUATION__TITLE;

	/**
	 * The feature id for the '<em><b>Evaluates</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_MEASURE_EVALUATION__EVALUATES = FORM_BASED_EVALUATION__EVALUATES;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_MEASURE_EVALUATION__QUALITY_MODEL = FORM_BASED_EVALUATION__QUALITY_MODEL;

	/**
	 * The feature id for the '<em><b>Maximum Points</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_MEASURE_EVALUATION__MAXIMUM_POINTS = FORM_BASED_EVALUATION__MAXIMUM_POINTS;

	/**
	 * The feature id for the '<em><b>Completeness</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_MEASURE_EVALUATION__COMPLETENESS = FORM_BASED_EVALUATION__COMPLETENESS;

	/**
	 * The number of structural features of the '<em>Multi Measure Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_MEASURE_EVALUATION_FEATURE_COUNT = FORM_BASED_EVALUATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.WeightedSumMultiMeasureEvaluationImpl <em>Weighted Sum Multi Measure Evaluation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.WeightedSumMultiMeasureEvaluationImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getWeightedSumMultiMeasureEvaluation()
	 * @generated
	 */
	int WEIGHTED_SUM_MULTI_MEASURE_EVALUATION = 48;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_MULTI_MEASURE_EVALUATION__QUALIFIED_NAME = MULTI_MEASURE_EVALUATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Originates From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_MULTI_MEASURE_EVALUATION__ORIGINATES_FROM = MULTI_MEASURE_EVALUATION__ORIGINATES_FROM;

	/**
	 * The feature id for the '<em><b>Tagged By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_MULTI_MEASURE_EVALUATION__TAGGED_BY = MULTI_MEASURE_EVALUATION__TAGGED_BY;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_MULTI_MEASURE_EVALUATION__ANNOTATIONS = MULTI_MEASURE_EVALUATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_MULTI_MEASURE_EVALUATION__ADVANCED_ANNOTATIONS = MULTI_MEASURE_EVALUATION__ADVANCED_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_MULTI_MEASURE_EVALUATION__DESCRIPTION = MULTI_MEASURE_EVALUATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_MULTI_MEASURE_EVALUATION__NAME = MULTI_MEASURE_EVALUATION__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_MULTI_MEASURE_EVALUATION__TITLE = MULTI_MEASURE_EVALUATION__TITLE;

	/**
	 * The feature id for the '<em><b>Evaluates</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_MULTI_MEASURE_EVALUATION__EVALUATES = MULTI_MEASURE_EVALUATION__EVALUATES;

	/**
	 * The feature id for the '<em><b>Quality Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_MULTI_MEASURE_EVALUATION__QUALITY_MODEL = MULTI_MEASURE_EVALUATION__QUALITY_MODEL;

	/**
	 * The feature id for the '<em><b>Maximum Points</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_MULTI_MEASURE_EVALUATION__MAXIMUM_POINTS = MULTI_MEASURE_EVALUATION__MAXIMUM_POINTS;

	/**
	 * The feature id for the '<em><b>Completeness</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_MULTI_MEASURE_EVALUATION__COMPLETENESS = MULTI_MEASURE_EVALUATION__COMPLETENESS;

	/**
	 * The feature id for the '<em><b>Rankings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_MULTI_MEASURE_EVALUATION__RANKINGS = MULTI_MEASURE_EVALUATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Weighted Sum Multi Measure Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SUM_MULTI_MEASURE_EVALUATION_FEATURE_COUNT = MULTI_MEASURE_EVALUATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.MeasureRankingImpl <em>Measure Ranking</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.MeasureRankingImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getMeasureRanking()
	 * @generated
	 */
	int MEASURE_RANKING = 49;

	/**
	 * The feature id for the '<em><b>Measure</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_RANKING__MEASURE = MEASURE_EVALUATION__MEASURE;

	/**
	 * The feature id for the '<em><b>Normlization Measure</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_RANKING__NORMLIZATION_MEASURE = MEASURE_EVALUATION__NORMLIZATION_MEASURE;

	/**
	 * The feature id for the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_RANKING__FUNCTION = MEASURE_EVALUATION__FUNCTION;

	/**
	 * The feature id for the '<em><b>Range</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_RANKING__RANGE = MEASURE_EVALUATION__RANGE;

	/**
	 * The feature id for the '<em><b>Rank</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_RANKING__RANK = MEASURE_EVALUATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_RANKING__WEIGHT = MEASURE_EVALUATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Measure Ranking</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_RANKING_FEATURE_COUNT = MEASURE_EVALUATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.QualityModelResultImpl <em>Quality Model Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.QualityModelResultImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getQualityModelResult()
	 * @generated
	 */
	int QUALITY_MODEL_RESULT = 50;

	/**
	 * The feature id for the '<em><b>System</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL_RESULT__SYSTEM = 0;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL_RESULT__DATE = 1;

	/**
	 * The feature id for the '<em><b>Measurement Results</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL_RESULT__MEASUREMENT_RESULTS = 2;

	/**
	 * The feature id for the '<em><b>Evaluation Results</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL_RESULT__EVALUATION_RESULTS = 3;

	/**
	 * The number of structural features of the '<em>Quality Model Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITY_MODEL_RESULT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.ResultImpl <em>Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.ResultImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getResult()
	 * @generated
	 */
	int RESULT = 59;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESULT__MESSAGE = 0;

	/**
	 * The number of structural features of the '<em>Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESULT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.MeasurementResultImpl <em>Measurement Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.MeasurementResultImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getMeasurementResult()
	 * @generated
	 */
	int MEASUREMENT_RESULT = 51;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_RESULT__MESSAGE = RESULT__MESSAGE;

	/**
	 * The feature id for the '<em><b>Results From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_RESULT__RESULTS_FROM = RESULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Measurement Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_RESULT_FEATURE_COUNT = RESULT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.NumberMeasurementResultImpl <em>Number Measurement Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.NumberMeasurementResultImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getNumberMeasurementResult()
	 * @generated
	 */
	int NUMBER_MEASUREMENT_RESULT = 52;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_MEASUREMENT_RESULT__MESSAGE = MEASUREMENT_RESULT__MESSAGE;

	/**
	 * The feature id for the '<em><b>Results From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_MEASUREMENT_RESULT__RESULTS_FROM = MEASUREMENT_RESULT__RESULTS_FROM;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_MEASUREMENT_RESULT__VALUE = MEASUREMENT_RESULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Number Measurement Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_MEASUREMENT_RESULT_FEATURE_COUNT = MEASUREMENT_RESULT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.FindingsMeasurementResultImpl <em>Findings Measurement Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.FindingsMeasurementResultImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getFindingsMeasurementResult()
	 * @generated
	 */
	int FINDINGS_MEASUREMENT_RESULT = 53;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDINGS_MEASUREMENT_RESULT__MESSAGE = MEASUREMENT_RESULT__MESSAGE;

	/**
	 * The feature id for the '<em><b>Results From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDINGS_MEASUREMENT_RESULT__RESULTS_FROM = MEASUREMENT_RESULT__RESULTS_FROM;

	/**
	 * The feature id for the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDINGS_MEASUREMENT_RESULT__COUNT = MEASUREMENT_RESULT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Findings</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDINGS_MEASUREMENT_RESULT__FINDINGS = MEASUREMENT_RESULT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Finding Messages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDINGS_MEASUREMENT_RESULT__FINDING_MESSAGES = MEASUREMENT_RESULT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Findings Measurement Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDINGS_MEASUREMENT_RESULT_FEATURE_COUNT = MEASUREMENT_RESULT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.DoubleIntervalImpl <em>Double Interval</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.DoubleIntervalImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getDoubleInterval()
	 * @generated
	 */
	int DOUBLE_INTERVAL = 54;

	/**
	 * The feature id for the '<em><b>Lower</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_INTERVAL__LOWER = 0;

	/**
	 * The feature id for the '<em><b>Upper</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_INTERVAL__UPPER = 1;

	/**
	 * The number of structural features of the '<em>Double Interval</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_INTERVAL_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.EvaluationResultImpl <em>Evaluation Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.EvaluationResultImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getEvaluationResult()
	 * @generated
	 */
	int EVALUATION_RESULT = 55;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_RESULT__MESSAGE = RESULT__MESSAGE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_RESULT__VALUE = RESULT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Results From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_RESULT__RESULTS_FROM = RESULT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Evaluation Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_RESULT_FEATURE_COUNT = RESULT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.SingleMeasureEvaluationResultImpl <em>Single Measure Evaluation Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.SingleMeasureEvaluationResultImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getSingleMeasureEvaluationResult()
	 * @generated
	 */
	int SINGLE_MEASURE_EVALUATION_RESULT = 56;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION_RESULT__MESSAGE = EVALUATION_RESULT__MESSAGE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION_RESULT__VALUE = EVALUATION_RESULT__VALUE;

	/**
	 * The feature id for the '<em><b>Results From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION_RESULT__RESULTS_FROM = EVALUATION_RESULT__RESULTS_FROM;

	/**
	 * The feature id for the '<em><b>Ratio Affected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION_RESULT__RATIO_AFFECTED = EVALUATION_RESULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Single Measure Evaluation Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEASURE_EVALUATION_RESULT_FEATURE_COUNT = EVALUATION_RESULT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.MultiMeasureEvaluationResultImpl <em>Multi Measure Evaluation Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.MultiMeasureEvaluationResultImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getMultiMeasureEvaluationResult()
	 * @generated
	 */
	int MULTI_MEASURE_EVALUATION_RESULT = 57;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_MEASURE_EVALUATION_RESULT__MESSAGE = EVALUATION_RESULT__MESSAGE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_MEASURE_EVALUATION_RESULT__VALUE = EVALUATION_RESULT__VALUE;

	/**
	 * The feature id for the '<em><b>Results From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_MEASURE_EVALUATION_RESULT__RESULTS_FROM = EVALUATION_RESULT__RESULTS_FROM;

	/**
	 * The feature id for the '<em><b>Evaluation Results</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_MEASURE_EVALUATION_RESULT__EVALUATION_RESULTS = EVALUATION_RESULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Multi Measure Evaluation Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTI_MEASURE_EVALUATION_RESULT_FEATURE_COUNT = EVALUATION_RESULT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.MeasureRankingEvaluationResultImpl <em>Measure Ranking Evaluation Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.MeasureRankingEvaluationResultImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getMeasureRankingEvaluationResult()
	 * @generated
	 */
	int MEASURE_RANKING_EVALUATION_RESULT = 58;

	/**
	 * The feature id for the '<em><b>Ratio Affected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_RANKING_EVALUATION_RESULT__RATIO_AFFECTED = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_RANKING_EVALUATION_RESULT__VALUE = 1;

	/**
	 * The feature id for the '<em><b>Results From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_RANKING_EVALUATION_RESULT__RESULTS_FROM = 2;

	/**
	 * The number of structural features of the '<em>Measure Ranking Evaluation Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_RANKING_EVALUATION_RESULT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.impl.FindingMessageImpl <em>Finding Message</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.impl.FindingMessageImpl
	 * @see de.quamoco.qm.impl.QmPackageImpl#getFindingMessage()
	 * @generated
	 */
	int FINDING_MESSAGE = 60;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDING_MESSAGE__MESSAGE = 0;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDING_MESSAGE__LOCATION = 1;

	/**
	 * The number of structural features of the '<em>Finding Message</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINDING_MESSAGE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.quamoco.qm.Effect <em>Effect</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.Effect
	 * @see de.quamoco.qm.impl.QmPackageImpl#getEffect()
	 * @generated
	 */
	int EFFECT = 61;


	/**
	 * The meta object id for the '{@link de.quamoco.qm.Type <em>Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.qm.Type
	 * @see de.quamoco.qm.impl.QmPackageImpl#getType()
	 * @generated
	 */
	int TYPE = 62;


	/**
	 * The meta object id for the '<em>Finding Collection</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.tum.cs.conqat.quamoco.FindingCollection
	 * @see de.quamoco.qm.impl.QmPackageImpl#getFindingCollection()
	 * @generated
	 */
	int FINDING_COLLECTION = 63;


	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.DescribedElement <em>Described Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Described Element</em>'.
	 * @see de.quamoco.qm.DescribedElement
	 * @generated
	 */
	EClass getDescribedElement();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.DescribedElement#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see de.quamoco.qm.DescribedElement#getDescription()
	 * @see #getDescribedElement()
	 * @generated
	 */
	EAttribute getDescribedElement_Description();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.Evaluation <em>Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Evaluation</em>'.
	 * @see de.quamoco.qm.Evaluation
	 * @generated
	 */
	EClass getEvaluation();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.qm.Evaluation#getEvaluates <em>Evaluates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Evaluates</em>'.
	 * @see de.quamoco.qm.Evaluation#getEvaluates()
	 * @see #getEvaluation()
	 * @generated
	 */
	EReference getEvaluation_Evaluates();

	/**
	 * Returns the meta object for the container reference '{@link de.quamoco.qm.Evaluation#getQualityModel <em>Quality Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Quality Model</em>'.
	 * @see de.quamoco.qm.Evaluation#getQualityModel()
	 * @see #getEvaluation()
	 * @generated
	 */
	EReference getEvaluation_QualityModel();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.Evaluation#getMaximumPoints <em>Maximum Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Points</em>'.
	 * @see de.quamoco.qm.Evaluation#getMaximumPoints()
	 * @see #getEvaluation()
	 * @generated
	 */
	EAttribute getEvaluation_MaximumPoints();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.Evaluation#getCompleteness <em>Completeness</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Completeness</em>'.
	 * @see de.quamoco.qm.Evaluation#getCompleteness()
	 * @see #getEvaluation()
	 * @generated
	 */
	EAttribute getEvaluation_Completeness();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.FactorRefinement <em>Factor Refinement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Factor Refinement</em>'.
	 * @see de.quamoco.qm.FactorRefinement
	 * @generated
	 */
	EClass getFactorRefinement();

	/**
	 * Returns the meta object for the container reference '{@link de.quamoco.qm.FactorRefinement#getChild <em>Child</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Child</em>'.
	 * @see de.quamoco.qm.FactorRefinement#getChild()
	 * @see #getFactorRefinement()
	 * @generated
	 */
	EReference getFactorRefinement_Child();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.qm.FactorRefinement#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see de.quamoco.qm.FactorRefinement#getParent()
	 * @see #getFactorRefinement()
	 * @generated
	 */
	EReference getFactorRefinement_Parent();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.Factor <em>Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Factor</em>'.
	 * @see de.quamoco.qm.Factor
	 * @generated
	 */
	EClass getFactor();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.Factor#getInfluences <em>Influences</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Influences</em>'.
	 * @see de.quamoco.qm.Factor#getInfluences()
	 * @see #getFactor()
	 * @generated
	 */
	EReference getFactor_Influences();

	/**
	 * Returns the meta object for the reference list '{@link de.quamoco.qm.Factor#getInfluencesDirect <em>Influences Direct</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Influences Direct</em>'.
	 * @see de.quamoco.qm.Factor#getInfluencesDirect()
	 * @see #getFactor()
	 * @generated
	 */
	EReference getFactor_InfluencesDirect();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.Factor#getRefines <em>Refines</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Refines</em>'.
	 * @see de.quamoco.qm.Factor#getRefines()
	 * @see #getFactor()
	 * @generated
	 */
	EReference getFactor_Refines();

	/**
	 * Returns the meta object for the reference list '{@link de.quamoco.qm.Factor#getRefinesDirect <em>Refines Direct</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Refines Direct</em>'.
	 * @see de.quamoco.qm.Factor#getRefinesDirect()
	 * @see #getFactor()
	 * @generated
	 */
	EReference getFactor_RefinesDirect();

	/**
	 * Returns the meta object for the container reference '{@link de.quamoco.qm.Factor#getQualityModel <em>Quality Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Quality Model</em>'.
	 * @see de.quamoco.qm.Factor#getQualityModel()
	 * @see #getFactor()
	 * @generated
	 */
	EReference getFactor_QualityModel();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.Entity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity</em>'.
	 * @see de.quamoco.qm.Entity
	 * @generated
	 */
	EClass getEntity();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.Entity#getIsA <em>Is A</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Is A</em>'.
	 * @see de.quamoco.qm.Entity#getIsA()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_IsA();

	/**
	 * Returns the meta object for the reference list '{@link de.quamoco.qm.Entity#getIsADirect <em>Is ADirect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Is ADirect</em>'.
	 * @see de.quamoco.qm.Entity#getIsADirect()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_IsADirect();

	/**
	 * Returns the meta object for the containment reference '{@link de.quamoco.qm.Entity#getPartOf <em>Part Of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Part Of</em>'.
	 * @see de.quamoco.qm.Entity#getPartOf()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_PartOf();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.qm.Entity#getPartOfDirect <em>Part Of Direct</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Part Of Direct</em>'.
	 * @see de.quamoco.qm.Entity#getPartOfDirect()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_PartOfDirect();

	/**
	 * Returns the meta object for the container reference '{@link de.quamoco.qm.Entity#getQualityModel <em>Quality Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Quality Model</em>'.
	 * @see de.quamoco.qm.Entity#getQualityModel()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_QualityModel();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.Entity#isStakeholder <em>Stakeholder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stakeholder</em>'.
	 * @see de.quamoco.qm.Entity#isStakeholder()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Stakeholder();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.Entity#isUseCase <em>Use Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Use Case</em>'.
	 * @see de.quamoco.qm.Entity#isUseCase()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_UseCase();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.Impact <em>Impact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Impact</em>'.
	 * @see de.quamoco.qm.Impact
	 * @generated
	 */
	EClass getImpact();

	/**
	 * Returns the meta object for the container reference '{@link de.quamoco.qm.Impact#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see de.quamoco.qm.Impact#getSource()
	 * @see #getImpact()
	 * @generated
	 */
	EReference getImpact_Source();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.qm.Impact#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see de.quamoco.qm.Impact#getTarget()
	 * @see #getImpact()
	 * @generated
	 */
	EReference getImpact_Target();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.Impact#getEffect <em>Effect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Effect</em>'.
	 * @see de.quamoco.qm.Impact#getEffect()
	 * @see #getImpact()
	 * @generated
	 */
	EAttribute getImpact_Effect();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.Impact#getJustification <em>Justification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Justification</em>'.
	 * @see de.quamoco.qm.Impact#getJustification()
	 * @see #getImpact()
	 * @generated
	 */
	EAttribute getImpact_Justification();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.QualityModel <em>Quality Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Quality Model</em>'.
	 * @see de.quamoco.qm.QualityModel
	 * @generated
	 */
	EClass getQualityModel();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.QualityModel#getEntities <em>Entities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entities</em>'.
	 * @see de.quamoco.qm.QualityModel#getEntities()
	 * @see #getQualityModel()
	 * @generated
	 */
	EReference getQualityModel_Entities();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.QualityModel#getFactors <em>Factors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Factors</em>'.
	 * @see de.quamoco.qm.QualityModel#getFactors()
	 * @see #getQualityModel()
	 * @generated
	 */
	EReference getQualityModel_Factors();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.QualityModel#getMeasures <em>Measures</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Measures</em>'.
	 * @see de.quamoco.qm.QualityModel#getMeasures()
	 * @see #getQualityModel()
	 * @generated
	 */
	EReference getQualityModel_Measures();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.QualityModel#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tags</em>'.
	 * @see de.quamoco.qm.QualityModel#getTags()
	 * @see #getQualityModel()
	 * @generated
	 */
	EReference getQualityModel_Tags();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.QualityModel#getTools <em>Tools</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tools</em>'.
	 * @see de.quamoco.qm.QualityModel#getTools()
	 * @see #getQualityModel()
	 * @generated
	 */
	EReference getQualityModel_Tools();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.QualityModel#getSources <em>Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sources</em>'.
	 * @see de.quamoco.qm.QualityModel#getSources()
	 * @see #getQualityModel()
	 * @generated
	 */
	EReference getQualityModel_Sources();

	/**
	 * Returns the meta object for the reference list '{@link de.quamoco.qm.QualityModel#getRequires <em>Requires</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Requires</em>'.
	 * @see de.quamoco.qm.QualityModel#getRequires()
	 * @see #getQualityModel()
	 * @generated
	 */
	EReference getQualityModel_Requires();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.QualityModel#getSchoolGradeBoundary2 <em>School Grade Boundary2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>School Grade Boundary2</em>'.
	 * @see de.quamoco.qm.QualityModel#getSchoolGradeBoundary2()
	 * @see #getQualityModel()
	 * @generated
	 */
	EAttribute getQualityModel_SchoolGradeBoundary2();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.QualityModel#getSchoolGradeBoundary3 <em>School Grade Boundary3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>School Grade Boundary3</em>'.
	 * @see de.quamoco.qm.QualityModel#getSchoolGradeBoundary3()
	 * @see #getQualityModel()
	 * @generated
	 */
	EAttribute getQualityModel_SchoolGradeBoundary3();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.QualityModel#getSchoolGradeBoundary4 <em>School Grade Boundary4</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>School Grade Boundary4</em>'.
	 * @see de.quamoco.qm.QualityModel#getSchoolGradeBoundary4()
	 * @see #getQualityModel()
	 * @generated
	 */
	EAttribute getQualityModel_SchoolGradeBoundary4();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.QualityModel#getSchoolGradeBoundary5 <em>School Grade Boundary5</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>School Grade Boundary5</em>'.
	 * @see de.quamoco.qm.QualityModel#getSchoolGradeBoundary5()
	 * @see #getQualityModel()
	 * @generated
	 */
	EAttribute getQualityModel_SchoolGradeBoundary5();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.QualityModel#getSchoolGradeBoundary6 <em>School Grade Boundary6</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>School Grade Boundary6</em>'.
	 * @see de.quamoco.qm.QualityModel#getSchoolGradeBoundary6()
	 * @see #getQualityModel()
	 * @generated
	 */
	EAttribute getQualityModel_SchoolGradeBoundary6();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.QualityModel#getEvaluations <em>Evaluations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Evaluations</em>'.
	 * @see de.quamoco.qm.QualityModel#getEvaluations()
	 * @see #getQualityModel()
	 * @generated
	 */
	EReference getQualityModel_Evaluations();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.QualityModel#getMeasurementMethods <em>Measurement Methods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Measurement Methods</em>'.
	 * @see de.quamoco.qm.QualityModel#getMeasurementMethods()
	 * @see #getQualityModel()
	 * @generated
	 */
	EReference getQualityModel_MeasurementMethods();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see de.quamoco.qm.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.quamoco.qm.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.NamedElement#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see de.quamoco.qm.NamedElement#getTitle()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Title();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.Instrument <em>Instrument</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instrument</em>'.
	 * @see de.quamoco.qm.Instrument
	 * @generated
	 */
	EClass getInstrument();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.AnnotatedElement <em>Annotated Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotated Element</em>'.
	 * @see de.quamoco.qm.AnnotatedElement
	 * @generated
	 */
	EClass getAnnotatedElement();

	/**
	 * Returns the meta object for the map '{@link de.quamoco.qm.AnnotatedElement#getAnnotations <em>Annotations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Annotations</em>'.
	 * @see de.quamoco.qm.AnnotatedElement#getAnnotations()
	 * @see #getAnnotatedElement()
	 * @generated
	 */
	EReference getAnnotatedElement_Annotations();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.AnnotatedElement#getAdvancedAnnotations <em>Advanced Annotations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Advanced Annotations</em>'.
	 * @see de.quamoco.qm.AnnotatedElement#getAdvancedAnnotations()
	 * @see #getAnnotatedElement()
	 * @generated
	 */
	EReference getAnnotatedElement_AdvancedAnnotations();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotation</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getAnnotation();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAnnotation()
	 * @generated
	 */
	EAttribute getAnnotation_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAnnotation()
	 * @generated
	 */
	EAttribute getAnnotation_Value();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.Tag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag</em>'.
	 * @see de.quamoco.qm.Tag
	 * @generated
	 */
	EClass getTag();

	/**
	 * Returns the meta object for the container reference '{@link de.quamoco.qm.Tag#getQualityModel <em>Quality Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Quality Model</em>'.
	 * @see de.quamoco.qm.Tag#getQualityModel()
	 * @see #getTag()
	 * @generated
	 */
	EReference getTag_QualityModel();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.TaggedElement <em>Tagged Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tagged Element</em>'.
	 * @see de.quamoco.qm.TaggedElement
	 * @generated
	 */
	EClass getTaggedElement();

	/**
	 * Returns the meta object for the reference list '{@link de.quamoco.qm.TaggedElement#getTaggedBy <em>Tagged By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Tagged By</em>'.
	 * @see de.quamoco.qm.TaggedElement#getTaggedBy()
	 * @see #getTaggedElement()
	 * @generated
	 */
	EReference getTaggedElement_TaggedBy();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.Measure <em>Measure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measure</em>'.
	 * @see de.quamoco.qm.Measure
	 * @generated
	 */
	EClass getMeasure();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.Measure#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.quamoco.qm.Measure#getType()
	 * @see #getMeasure()
	 * @generated
	 */
	EAttribute getMeasure_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.Measure#getMeasures <em>Measures</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Measures</em>'.
	 * @see de.quamoco.qm.Measure#getMeasures()
	 * @see #getMeasure()
	 * @generated
	 */
	EReference getMeasure_Measures();

	/**
	 * Returns the meta object for the reference list '{@link de.quamoco.qm.Measure#getMeasuresDirect <em>Measures Direct</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Measures Direct</em>'.
	 * @see de.quamoco.qm.Measure#getMeasuresDirect()
	 * @see #getMeasure()
	 * @generated
	 */
	EReference getMeasure_MeasuresDirect();

	/**
	 * Returns the meta object for the container reference '{@link de.quamoco.qm.Measure#getQualityModel <em>Quality Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Quality Model</em>'.
	 * @see de.quamoco.qm.Measure#getQualityModel()
	 * @see #getMeasure()
	 * @generated
	 */
	EReference getMeasure_QualityModel();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.Measure#getRefines <em>Refines</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Refines</em>'.
	 * @see de.quamoco.qm.Measure#getRefines()
	 * @see #getMeasure()
	 * @generated
	 */
	EReference getMeasure_Refines();

	/**
	 * Returns the meta object for the reference list '{@link de.quamoco.qm.Measure#getRefinesDirect <em>Refines Direct</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Refines Direct</em>'.
	 * @see de.quamoco.qm.Measure#getRefinesDirect()
	 * @see #getMeasure()
	 * @generated
	 */
	EReference getMeasure_RefinesDirect();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.MeasurementMethod <em>Measurement Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measurement Method</em>'.
	 * @see de.quamoco.qm.MeasurementMethod
	 * @generated
	 */
	EClass getMeasurementMethod();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.qm.MeasurementMethod#getDetermines <em>Determines</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Determines</em>'.
	 * @see de.quamoco.qm.MeasurementMethod#getDetermines()
	 * @see #getMeasurementMethod()
	 * @generated
	 */
	EReference getMeasurementMethod_Determines();

	/**
	 * Returns the meta object for the container reference '{@link de.quamoco.qm.MeasurementMethod#getQualityModel <em>Quality Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Quality Model</em>'.
	 * @see de.quamoco.qm.MeasurementMethod#getQualityModel()
	 * @see #getMeasurementMethod()
	 * @generated
	 */
	EReference getMeasurementMethod_QualityModel();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.CharacterizingElement <em>Characterizing Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Characterizing Element</em>'.
	 * @see de.quamoco.qm.CharacterizingElement
	 * @generated
	 */
	EClass getCharacterizingElement();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.qm.CharacterizingElement#getCharacterizes <em>Characterizes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Characterizes</em>'.
	 * @see de.quamoco.qm.CharacterizingElement#getCharacterizes()
	 * @see #getCharacterizingElement()
	 * @generated
	 */
	EReference getCharacterizingElement_Characterizes();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.QualityModelElement <em>Quality Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Quality Model Element</em>'.
	 * @see de.quamoco.qm.QualityModelElement
	 * @generated
	 */
	EClass getQualityModelElement();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.QualityModelElement#getQualifiedName <em>Qualified Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Qualified Name</em>'.
	 * @see de.quamoco.qm.QualityModelElement#getQualifiedName()
	 * @see #getQualityModelElement()
	 * @generated
	 */
	EAttribute getQualityModelElement_QualifiedName();

	/**
	 * Returns the meta object for the reference list '{@link de.quamoco.qm.QualityModelElement#getOriginatesFrom <em>Originates From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Originates From</em>'.
	 * @see de.quamoco.qm.QualityModelElement#getOriginatesFrom()
	 * @see #getQualityModelElement()
	 * @generated
	 */
	EReference getQualityModelElement_OriginatesFrom();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.ToolBasedInstrument <em>Tool Based Instrument</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tool Based Instrument</em>'.
	 * @see de.quamoco.qm.ToolBasedInstrument
	 * @generated
	 */
	EClass getToolBasedInstrument();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.qm.ToolBasedInstrument#getTool <em>Tool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Tool</em>'.
	 * @see de.quamoco.qm.ToolBasedInstrument#getTool()
	 * @see #getToolBasedInstrument()
	 * @generated
	 */
	EReference getToolBasedInstrument_Tool();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.ToolBasedInstrument#getMetric <em>Metric</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Metric</em>'.
	 * @see de.quamoco.qm.ToolBasedInstrument#getMetric()
	 * @see #getToolBasedInstrument()
	 * @generated
	 */
	EAttribute getToolBasedInstrument_Metric();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.ManualInstrument <em>Manual Instrument</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Manual Instrument</em>'.
	 * @see de.quamoco.qm.ManualInstrument
	 * @generated
	 */
	EClass getManualInstrument();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.AnnotationBase <em>Annotation Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotation Base</em>'.
	 * @see de.quamoco.qm.AnnotationBase
	 * @generated
	 */
	EClass getAnnotationBase();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.QIESLEvaluation <em>QIESL Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>QIESL Evaluation</em>'.
	 * @see de.quamoco.qm.QIESLEvaluation
	 * @generated
	 */
	EClass getQIESLEvaluation();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.TextEvaluation <em>Text Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text Evaluation</em>'.
	 * @see de.quamoco.qm.TextEvaluation
	 * @generated
	 */
	EClass getTextEvaluation();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.TextEvaluation#getSpecification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Specification</em>'.
	 * @see de.quamoco.qm.TextEvaluation#getSpecification()
	 * @see #getTextEvaluation()
	 * @generated
	 */
	EAttribute getTextEvaluation_Specification();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.FormBasedEvaluation <em>Form Based Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form Based Evaluation</em>'.
	 * @see de.quamoco.qm.FormBasedEvaluation
	 * @generated
	 */
	EClass getFormBasedEvaluation();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.SingleMeasureEvaluation <em>Single Measure Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Single Measure Evaluation</em>'.
	 * @see de.quamoco.qm.SingleMeasureEvaluation
	 * @generated
	 */
	EClass getSingleMeasureEvaluation();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.FactorAggregation <em>Factor Aggregation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Factor Aggregation</em>'.
	 * @see de.quamoco.qm.FactorAggregation
	 * @generated
	 */
	EClass getFactorAggregation();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.NormalizationMeasure <em>Normalization Measure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Normalization Measure</em>'.
	 * @see de.quamoco.qm.NormalizationMeasure
	 * @generated
	 */
	EClass getNormalizationMeasure();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.QIESLAggregation <em>QIESL Aggregation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>QIESL Aggregation</em>'.
	 * @see de.quamoco.qm.QIESLAggregation
	 * @generated
	 */
	EClass getQIESLAggregation();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.TextAggregation <em>Text Aggregation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text Aggregation</em>'.
	 * @see de.quamoco.qm.TextAggregation
	 * @generated
	 */
	EClass getTextAggregation();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.TextAggregation#getSpecification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Specification</em>'.
	 * @see de.quamoco.qm.TextAggregation#getSpecification()
	 * @see #getTextAggregation()
	 * @generated
	 */
	EAttribute getTextAggregation_Specification();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.FormBasedMeasureAggregation <em>Form Based Measure Aggregation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form Based Measure Aggregation</em>'.
	 * @see de.quamoco.qm.FormBasedMeasureAggregation
	 * @generated
	 */
	EClass getFormBasedMeasureAggregation();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.MeasureAggregation <em>Measure Aggregation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measure Aggregation</em>'.
	 * @see de.quamoco.qm.MeasureAggregation
	 * @generated
	 */
	EClass getMeasureAggregation();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.FactorRanking <em>Factor Ranking</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Factor Ranking</em>'.
	 * @see de.quamoco.qm.FactorRanking
	 * @generated
	 */
	EClass getFactorRanking();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.qm.FactorRanking#getFactor <em>Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Factor</em>'.
	 * @see de.quamoco.qm.FactorRanking#getFactor()
	 * @see #getFactorRanking()
	 * @generated
	 */
	EReference getFactorRanking_Factor();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.LinearFunction <em>Linear Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Linear Function</em>'.
	 * @see de.quamoco.qm.LinearFunction
	 * @generated
	 */
	EClass getLinearFunction();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.LinearFunction#getLowerBound <em>Lower Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Bound</em>'.
	 * @see de.quamoco.qm.LinearFunction#getLowerBound()
	 * @see #getLinearFunction()
	 * @generated
	 */
	EAttribute getLinearFunction_LowerBound();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.LinearFunction#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Bound</em>'.
	 * @see de.quamoco.qm.LinearFunction#getUpperBound()
	 * @see #getLinearFunction()
	 * @generated
	 */
	EAttribute getLinearFunction_UpperBound();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.Function <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function</em>'.
	 * @see de.quamoco.qm.Function
	 * @generated
	 */
	EClass getFunction();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.LinearIncreasingFunction <em>Linear Increasing Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Linear Increasing Function</em>'.
	 * @see de.quamoco.qm.LinearIncreasingFunction
	 * @generated
	 */
	EClass getLinearIncreasingFunction();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.LinearDecreasingFunction <em>Linear Decreasing Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Linear Decreasing Function</em>'.
	 * @see de.quamoco.qm.LinearDecreasingFunction
	 * @generated
	 */
	EClass getLinearDecreasingFunction();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.WeightedSumFactorAggregation <em>Weighted Sum Factor Aggregation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Weighted Sum Factor Aggregation</em>'.
	 * @see de.quamoco.qm.WeightedSumFactorAggregation
	 * @generated
	 */
	EClass getWeightedSumFactorAggregation();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.WeightedSumFactorAggregation#getRankings <em>Rankings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rankings</em>'.
	 * @see de.quamoco.qm.WeightedSumFactorAggregation#getRankings()
	 * @see #getWeightedSumFactorAggregation()
	 * @generated
	 */
	EReference getWeightedSumFactorAggregation_Rankings();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.FindingsUnionMeasureAggregation <em>Findings Union Measure Aggregation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Findings Union Measure Aggregation</em>'.
	 * @see de.quamoco.qm.FindingsUnionMeasureAggregation
	 * @generated
	 */
	EClass getFindingsUnionMeasureAggregation();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.NumberMeanMeasureAggregation <em>Number Mean Measure Aggregation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Number Mean Measure Aggregation</em>'.
	 * @see de.quamoco.qm.NumberMeanMeasureAggregation
	 * @generated
	 */
	EClass getNumberMeanMeasureAggregation();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.ManualEvaluation <em>Manual Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Manual Evaluation</em>'.
	 * @see de.quamoco.qm.ManualEvaluation
	 * @generated
	 */
	EClass getManualEvaluation();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.MeasureEvaluation <em>Measure Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measure Evaluation</em>'.
	 * @see de.quamoco.qm.MeasureEvaluation
	 * @generated
	 */
	EClass getMeasureEvaluation();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.qm.MeasureEvaluation#getMeasure <em>Measure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Measure</em>'.
	 * @see de.quamoco.qm.MeasureEvaluation#getMeasure()
	 * @see #getMeasureEvaluation()
	 * @generated
	 */
	EReference getMeasureEvaluation_Measure();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.qm.MeasureEvaluation#getNormlizationMeasure <em>Normlization Measure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Normlization Measure</em>'.
	 * @see de.quamoco.qm.MeasureEvaluation#getNormlizationMeasure()
	 * @see #getMeasureEvaluation()
	 * @generated
	 */
	EReference getMeasureEvaluation_NormlizationMeasure();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.MeasureEvaluation#getRange <em>Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Range</em>'.
	 * @see de.quamoco.qm.MeasureEvaluation#getRange()
	 * @see #getMeasureEvaluation()
	 * @generated
	 */
	EAttribute getMeasureEvaluation_Range();

	/**
	 * Returns the meta object for the containment reference '{@link de.quamoco.qm.MeasureEvaluation#getFunction <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Function</em>'.
	 * @see de.quamoco.qm.MeasureEvaluation#getFunction()
	 * @see #getMeasureEvaluation()
	 * @generated
	 */
	EReference getMeasureEvaluation_Function();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.Ranking <em>Ranking</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ranking</em>'.
	 * @see de.quamoco.qm.Ranking
	 * @generated
	 */
	EClass getRanking();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.Ranking#getRank <em>Rank</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rank</em>'.
	 * @see de.quamoco.qm.Ranking#getRank()
	 * @see #getRanking()
	 * @generated
	 */
	EAttribute getRanking_Rank();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.Ranking#getWeight <em>Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weight</em>'.
	 * @see de.quamoco.qm.Ranking#getWeight()
	 * @see #getRanking()
	 * @generated
	 */
	EAttribute getRanking_Weight();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.MultiMeasureEvaluation <em>Multi Measure Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multi Measure Evaluation</em>'.
	 * @see de.quamoco.qm.MultiMeasureEvaluation
	 * @generated
	 */
	EClass getMultiMeasureEvaluation();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.WeightedSumMultiMeasureEvaluation <em>Weighted Sum Multi Measure Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Weighted Sum Multi Measure Evaluation</em>'.
	 * @see de.quamoco.qm.WeightedSumMultiMeasureEvaluation
	 * @generated
	 */
	EClass getWeightedSumMultiMeasureEvaluation();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.WeightedSumMultiMeasureEvaluation#getRankings <em>Rankings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rankings</em>'.
	 * @see de.quamoco.qm.WeightedSumMultiMeasureEvaluation#getRankings()
	 * @see #getWeightedSumMultiMeasureEvaluation()
	 * @generated
	 */
	EReference getWeightedSumMultiMeasureEvaluation_Rankings();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.MeasureRanking <em>Measure Ranking</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measure Ranking</em>'.
	 * @see de.quamoco.qm.MeasureRanking
	 * @generated
	 */
	EClass getMeasureRanking();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.QualityModelResult <em>Quality Model Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Quality Model Result</em>'.
	 * @see de.quamoco.qm.QualityModelResult
	 * @generated
	 */
	EClass getQualityModelResult();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.QualityModelResult#getSystem <em>System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>System</em>'.
	 * @see de.quamoco.qm.QualityModelResult#getSystem()
	 * @see #getQualityModelResult()
	 * @generated
	 */
	EAttribute getQualityModelResult_System();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.QualityModelResult#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see de.quamoco.qm.QualityModelResult#getDate()
	 * @see #getQualityModelResult()
	 * @generated
	 */
	EAttribute getQualityModelResult_Date();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.QualityModelResult#getMeasurementResults <em>Measurement Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Measurement Results</em>'.
	 * @see de.quamoco.qm.QualityModelResult#getMeasurementResults()
	 * @see #getQualityModelResult()
	 * @generated
	 */
	EReference getQualityModelResult_MeasurementResults();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.QualityModelResult#getEvaluationResults <em>Evaluation Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Evaluation Results</em>'.
	 * @see de.quamoco.qm.QualityModelResult#getEvaluationResults()
	 * @see #getQualityModelResult()
	 * @generated
	 */
	EReference getQualityModelResult_EvaluationResults();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.Result <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Result</em>'.
	 * @see de.quamoco.qm.Result
	 * @generated
	 */
	EClass getResult();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.Result#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see de.quamoco.qm.Result#getMessage()
	 * @see #getResult()
	 * @generated
	 */
	EAttribute getResult_Message();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.FindingMessage <em>Finding Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Finding Message</em>'.
	 * @see de.quamoco.qm.FindingMessage
	 * @generated
	 */
	EClass getFindingMessage();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.FindingMessage#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see de.quamoco.qm.FindingMessage#getMessage()
	 * @see #getFindingMessage()
	 * @generated
	 */
	EAttribute getFindingMessage_Message();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.FindingMessage#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see de.quamoco.qm.FindingMessage#getLocation()
	 * @see #getFindingMessage()
	 * @generated
	 */
	EAttribute getFindingMessage_Location();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.MeasurementResult <em>Measurement Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measurement Result</em>'.
	 * @see de.quamoco.qm.MeasurementResult
	 * @generated
	 */
	EClass getMeasurementResult();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.qm.MeasurementResult#getResultsFrom <em>Results From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Results From</em>'.
	 * @see de.quamoco.qm.MeasurementResult#getResultsFrom()
	 * @see #getMeasurementResult()
	 * @generated
	 */
	EReference getMeasurementResult_ResultsFrom();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.NumberMeasurementResult <em>Number Measurement Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Number Measurement Result</em>'.
	 * @see de.quamoco.qm.NumberMeasurementResult
	 * @generated
	 */
	EClass getNumberMeasurementResult();

	/**
	 * Returns the meta object for the containment reference '{@link de.quamoco.qm.NumberMeasurementResult#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see de.quamoco.qm.NumberMeasurementResult#getValue()
	 * @see #getNumberMeasurementResult()
	 * @generated
	 */
	EReference getNumberMeasurementResult_Value();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.FindingsMeasurementResult <em>Findings Measurement Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Findings Measurement Result</em>'.
	 * @see de.quamoco.qm.FindingsMeasurementResult
	 * @generated
	 */
	EClass getFindingsMeasurementResult();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.FindingsMeasurementResult#getCount <em>Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Count</em>'.
	 * @see de.quamoco.qm.FindingsMeasurementResult#getCount()
	 * @see #getFindingsMeasurementResult()
	 * @generated
	 */
	EAttribute getFindingsMeasurementResult_Count();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.FindingsMeasurementResult#getFindings <em>Findings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Findings</em>'.
	 * @see de.quamoco.qm.FindingsMeasurementResult#getFindings()
	 * @see #getFindingsMeasurementResult()
	 * @generated
	 */
	EAttribute getFindingsMeasurementResult_Findings();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.FindingsMeasurementResult#getFindingMessages <em>Finding Messages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Finding Messages</em>'.
	 * @see de.quamoco.qm.FindingsMeasurementResult#getFindingMessages()
	 * @see #getFindingsMeasurementResult()
	 * @generated
	 */
	EReference getFindingsMeasurementResult_FindingMessages();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.DoubleInterval <em>Double Interval</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Interval</em>'.
	 * @see de.quamoco.qm.DoubleInterval
	 * @generated
	 */
	EClass getDoubleInterval();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.DoubleInterval#getLower <em>Lower</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower</em>'.
	 * @see de.quamoco.qm.DoubleInterval#getLower()
	 * @see #getDoubleInterval()
	 * @generated
	 */
	EAttribute getDoubleInterval_Lower();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.DoubleInterval#getUpper <em>Upper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper</em>'.
	 * @see de.quamoco.qm.DoubleInterval#getUpper()
	 * @see #getDoubleInterval()
	 * @generated
	 */
	EAttribute getDoubleInterval_Upper();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.EvaluationResult <em>Evaluation Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Evaluation Result</em>'.
	 * @see de.quamoco.qm.EvaluationResult
	 * @generated
	 */
	EClass getEvaluationResult();

	/**
	 * Returns the meta object for the containment reference '{@link de.quamoco.qm.EvaluationResult#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see de.quamoco.qm.EvaluationResult#getValue()
	 * @see #getEvaluationResult()
	 * @generated
	 */
	EReference getEvaluationResult_Value();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.qm.EvaluationResult#getResultsFrom <em>Results From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Results From</em>'.
	 * @see de.quamoco.qm.EvaluationResult#getResultsFrom()
	 * @see #getEvaluationResult()
	 * @generated
	 */
	EReference getEvaluationResult_ResultsFrom();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.SingleMeasureEvaluationResult <em>Single Measure Evaluation Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Single Measure Evaluation Result</em>'.
	 * @see de.quamoco.qm.SingleMeasureEvaluationResult
	 * @generated
	 */
	EClass getSingleMeasureEvaluationResult();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.SingleMeasureEvaluationResult#getRatioAffected <em>Ratio Affected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ratio Affected</em>'.
	 * @see de.quamoco.qm.SingleMeasureEvaluationResult#getRatioAffected()
	 * @see #getSingleMeasureEvaluationResult()
	 * @generated
	 */
	EAttribute getSingleMeasureEvaluationResult_RatioAffected();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.MultiMeasureEvaluationResult <em>Multi Measure Evaluation Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multi Measure Evaluation Result</em>'.
	 * @see de.quamoco.qm.MultiMeasureEvaluationResult
	 * @generated
	 */
	EClass getMultiMeasureEvaluationResult();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.qm.MultiMeasureEvaluationResult#getEvaluationResults <em>Evaluation Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Evaluation Results</em>'.
	 * @see de.quamoco.qm.MultiMeasureEvaluationResult#getEvaluationResults()
	 * @see #getMultiMeasureEvaluationResult()
	 * @generated
	 */
	EReference getMultiMeasureEvaluationResult_EvaluationResults();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.MeasureRankingEvaluationResult <em>Measure Ranking Evaluation Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measure Ranking Evaluation Result</em>'.
	 * @see de.quamoco.qm.MeasureRankingEvaluationResult
	 * @generated
	 */
	EClass getMeasureRankingEvaluationResult();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.qm.MeasureRankingEvaluationResult#getRatioAffected <em>Ratio Affected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ratio Affected</em>'.
	 * @see de.quamoco.qm.MeasureRankingEvaluationResult#getRatioAffected()
	 * @see #getMeasureRankingEvaluationResult()
	 * @generated
	 */
	EAttribute getMeasureRankingEvaluationResult_RatioAffected();

	/**
	 * Returns the meta object for the containment reference '{@link de.quamoco.qm.MeasureRankingEvaluationResult#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see de.quamoco.qm.MeasureRankingEvaluationResult#getValue()
	 * @see #getMeasureRankingEvaluationResult()
	 * @generated
	 */
	EReference getMeasureRankingEvaluationResult_Value();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.qm.MeasureRankingEvaluationResult#getResultsFrom <em>Results From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Results From</em>'.
	 * @see de.quamoco.qm.MeasureRankingEvaluationResult#getResultsFrom()
	 * @see #getMeasureRankingEvaluationResult()
	 * @generated
	 */
	EReference getMeasureRankingEvaluationResult_ResultsFrom();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.Tool <em>Tool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tool</em>'.
	 * @see de.quamoco.qm.Tool
	 * @generated
	 */
	EClass getTool();

	/**
	 * Returns the meta object for the container reference '{@link de.quamoco.qm.Tool#getQualityModel <em>Quality Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Quality Model</em>'.
	 * @see de.quamoco.qm.Tool#getQualityModel()
	 * @see #getTool()
	 * @generated
	 */
	EReference getTool_QualityModel();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.Source <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Source</em>'.
	 * @see de.quamoco.qm.Source
	 * @generated
	 */
	EClass getSource();

	/**
	 * Returns the meta object for the container reference '{@link de.quamoco.qm.Source#getQualityModel <em>Quality Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Quality Model</em>'.
	 * @see de.quamoco.qm.Source#getQualityModel()
	 * @see #getSource()
	 * @generated
	 */
	EReference getSource_QualityModel();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.Measurement <em>Measurement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measurement</em>'.
	 * @see de.quamoco.qm.Measurement
	 * @generated
	 */
	EClass getMeasurement();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.qm.Measurement#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see de.quamoco.qm.Measurement#getParent()
	 * @see #getMeasurement()
	 * @generated
	 */
	EReference getMeasurement_Parent();

	/**
	 * Returns the meta object for the container reference '{@link de.quamoco.qm.Measurement#getChild <em>Child</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Child</em>'.
	 * @see de.quamoco.qm.Measurement#getChild()
	 * @see #getMeasurement()
	 * @generated
	 */
	EReference getMeasurement_Child();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.Specialization <em>Specialization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specialization</em>'.
	 * @see de.quamoco.qm.Specialization
	 * @generated
	 */
	EClass getSpecialization();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.qm.Specialization#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see de.quamoco.qm.Specialization#getParent()
	 * @see #getSpecialization()
	 * @generated
	 */
	EReference getSpecialization_Parent();

	/**
	 * Returns the meta object for the container reference '{@link de.quamoco.qm.Specialization#getChild <em>Child</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Child</em>'.
	 * @see de.quamoco.qm.Specialization#getChild()
	 * @see #getSpecialization()
	 * @generated
	 */
	EReference getSpecialization_Child();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.Decomposition <em>Decomposition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decomposition</em>'.
	 * @see de.quamoco.qm.Decomposition
	 * @generated
	 */
	EClass getDecomposition();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.qm.Decomposition#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see de.quamoco.qm.Decomposition#getParent()
	 * @see #getDecomposition()
	 * @generated
	 */
	EReference getDecomposition_Parent();

	/**
	 * Returns the meta object for the container reference '{@link de.quamoco.qm.Decomposition#getChild <em>Child</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Child</em>'.
	 * @see de.quamoco.qm.Decomposition#getChild()
	 * @see #getDecomposition()
	 * @generated
	 */
	EReference getDecomposition_Child();

	/**
	 * Returns the meta object for class '{@link de.quamoco.qm.MeasureRefinement <em>Measure Refinement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measure Refinement</em>'.
	 * @see de.quamoco.qm.MeasureRefinement
	 * @generated
	 */
	EClass getMeasureRefinement();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.qm.MeasureRefinement#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see de.quamoco.qm.MeasureRefinement#getParent()
	 * @see #getMeasureRefinement()
	 * @generated
	 */
	EReference getMeasureRefinement_Parent();

	/**
	 * Returns the meta object for the container reference '{@link de.quamoco.qm.MeasureRefinement#getChild <em>Child</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Child</em>'.
	 * @see de.quamoco.qm.MeasureRefinement#getChild()
	 * @see #getMeasureRefinement()
	 * @generated
	 */
	EReference getMeasureRefinement_Child();

	/**
	 * Returns the meta object for enum '{@link de.quamoco.qm.Effect <em>Effect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Effect</em>'.
	 * @see de.quamoco.qm.Effect
	 * @generated
	 */
	EEnum getEffect();

	/**
	 * Returns the meta object for enum '{@link de.quamoco.qm.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Type</em>'.
	 * @see de.quamoco.qm.Type
	 * @generated
	 */
	EEnum getType();

	/**
	 * Returns the meta object for data type '{@link edu.tum.cs.conqat.quamoco.FindingCollection <em>Finding Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Finding Collection</em>'.
	 * @see edu.tum.cs.conqat.quamoco.FindingCollection
	 * @model instanceClass="edu.tum.cs.conqat.quamoco.FindingCollection" serializeable="false"
	 * @generated
	 */
	EDataType getFindingCollection();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	QmFactory getQmFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.DescribedElementImpl <em>Described Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.DescribedElementImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getDescribedElement()
		 * @generated
		 */
		EClass DESCRIBED_ELEMENT = eINSTANCE.getDescribedElement();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIBED_ELEMENT__DESCRIPTION = eINSTANCE.getDescribedElement_Description();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.EvaluationImpl <em>Evaluation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.EvaluationImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getEvaluation()
		 * @generated
		 */
		EClass EVALUATION = eINSTANCE.getEvaluation();

		/**
		 * The meta object literal for the '<em><b>Evaluates</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVALUATION__EVALUATES = eINSTANCE.getEvaluation_Evaluates();

		/**
		 * The meta object literal for the '<em><b>Quality Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVALUATION__QUALITY_MODEL = eINSTANCE.getEvaluation_QualityModel();

		/**
		 * The meta object literal for the '<em><b>Maximum Points</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVALUATION__MAXIMUM_POINTS = eINSTANCE.getEvaluation_MaximumPoints();

		/**
		 * The meta object literal for the '<em><b>Completeness</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVALUATION__COMPLETENESS = eINSTANCE.getEvaluation_Completeness();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.FactorRefinementImpl <em>Factor Refinement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.FactorRefinementImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getFactorRefinement()
		 * @generated
		 */
		EClass FACTOR_REFINEMENT = eINSTANCE.getFactorRefinement();

		/**
		 * The meta object literal for the '<em><b>Child</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACTOR_REFINEMENT__CHILD = eINSTANCE.getFactorRefinement_Child();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACTOR_REFINEMENT__PARENT = eINSTANCE.getFactorRefinement_Parent();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.FactorImpl <em>Factor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.FactorImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getFactor()
		 * @generated
		 */
		EClass FACTOR = eINSTANCE.getFactor();

		/**
		 * The meta object literal for the '<em><b>Influences</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACTOR__INFLUENCES = eINSTANCE.getFactor_Influences();

		/**
		 * The meta object literal for the '<em><b>Influences Direct</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACTOR__INFLUENCES_DIRECT = eINSTANCE.getFactor_InfluencesDirect();

		/**
		 * The meta object literal for the '<em><b>Refines</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACTOR__REFINES = eINSTANCE.getFactor_Refines();

		/**
		 * The meta object literal for the '<em><b>Refines Direct</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACTOR__REFINES_DIRECT = eINSTANCE.getFactor_RefinesDirect();

		/**
		 * The meta object literal for the '<em><b>Quality Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACTOR__QUALITY_MODEL = eINSTANCE.getFactor_QualityModel();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.EntityImpl <em>Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.EntityImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getEntity()
		 * @generated
		 */
		EClass ENTITY = eINSTANCE.getEntity();

		/**
		 * The meta object literal for the '<em><b>Is A</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY__IS_A = eINSTANCE.getEntity_IsA();

		/**
		 * The meta object literal for the '<em><b>Is ADirect</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY__IS_ADIRECT = eINSTANCE.getEntity_IsADirect();

		/**
		 * The meta object literal for the '<em><b>Part Of</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY__PART_OF = eINSTANCE.getEntity_PartOf();

		/**
		 * The meta object literal for the '<em><b>Part Of Direct</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY__PART_OF_DIRECT = eINSTANCE.getEntity_PartOfDirect();

		/**
		 * The meta object literal for the '<em><b>Quality Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY__QUALITY_MODEL = eINSTANCE.getEntity_QualityModel();

		/**
		 * The meta object literal for the '<em><b>Stakeholder</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__STAKEHOLDER = eINSTANCE.getEntity_Stakeholder();

		/**
		 * The meta object literal for the '<em><b>Use Case</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__USE_CASE = eINSTANCE.getEntity_UseCase();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.ImpactImpl <em>Impact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.ImpactImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getImpact()
		 * @generated
		 */
		EClass IMPACT = eINSTANCE.getImpact();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPACT__SOURCE = eINSTANCE.getImpact_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPACT__TARGET = eINSTANCE.getImpact_Target();

		/**
		 * The meta object literal for the '<em><b>Effect</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPACT__EFFECT = eINSTANCE.getImpact_Effect();

		/**
		 * The meta object literal for the '<em><b>Justification</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPACT__JUSTIFICATION = eINSTANCE.getImpact_Justification();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.QualityModelImpl <em>Quality Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.QualityModelImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getQualityModel()
		 * @generated
		 */
		EClass QUALITY_MODEL = eINSTANCE.getQualityModel();

		/**
		 * The meta object literal for the '<em><b>Entities</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUALITY_MODEL__ENTITIES = eINSTANCE.getQualityModel_Entities();

		/**
		 * The meta object literal for the '<em><b>Factors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUALITY_MODEL__FACTORS = eINSTANCE.getQualityModel_Factors();

		/**
		 * The meta object literal for the '<em><b>Measures</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUALITY_MODEL__MEASURES = eINSTANCE.getQualityModel_Measures();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUALITY_MODEL__TAGS = eINSTANCE.getQualityModel_Tags();

		/**
		 * The meta object literal for the '<em><b>Tools</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUALITY_MODEL__TOOLS = eINSTANCE.getQualityModel_Tools();

		/**
		 * The meta object literal for the '<em><b>Sources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUALITY_MODEL__SOURCES = eINSTANCE.getQualityModel_Sources();

		/**
		 * The meta object literal for the '<em><b>Requires</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUALITY_MODEL__REQUIRES = eINSTANCE.getQualityModel_Requires();

		/**
		 * The meta object literal for the '<em><b>School Grade Boundary2</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY2 = eINSTANCE.getQualityModel_SchoolGradeBoundary2();

		/**
		 * The meta object literal for the '<em><b>School Grade Boundary3</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY3 = eINSTANCE.getQualityModel_SchoolGradeBoundary3();

		/**
		 * The meta object literal for the '<em><b>School Grade Boundary4</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY4 = eINSTANCE.getQualityModel_SchoolGradeBoundary4();

		/**
		 * The meta object literal for the '<em><b>School Grade Boundary5</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY5 = eINSTANCE.getQualityModel_SchoolGradeBoundary5();

		/**
		 * The meta object literal for the '<em><b>School Grade Boundary6</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY6 = eINSTANCE.getQualityModel_SchoolGradeBoundary6();

		/**
		 * The meta object literal for the '<em><b>Evaluations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUALITY_MODEL__EVALUATIONS = eINSTANCE.getQualityModel_Evaluations();

		/**
		 * The meta object literal for the '<em><b>Measurement Methods</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUALITY_MODEL__MEASUREMENT_METHODS = eINSTANCE.getQualityModel_MeasurementMethods();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.NamedElementImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__TITLE = eINSTANCE.getNamedElement_Title();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.InstrumentImpl <em>Instrument</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.InstrumentImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getInstrument()
		 * @generated
		 */
		EClass INSTRUMENT = eINSTANCE.getInstrument();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.AnnotatedElementImpl <em>Annotated Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.AnnotatedElementImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getAnnotatedElement()
		 * @generated
		 */
		EClass ANNOTATED_ELEMENT = eINSTANCE.getAnnotatedElement();

		/**
		 * The meta object literal for the '<em><b>Annotations</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANNOTATED_ELEMENT__ANNOTATIONS = eINSTANCE.getAnnotatedElement_Annotations();

		/**
		 * The meta object literal for the '<em><b>Advanced Annotations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANNOTATED_ELEMENT__ADVANCED_ANNOTATIONS = eINSTANCE.getAnnotatedElement_AdvancedAnnotations();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.AnnotationImpl <em>Annotation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.AnnotationImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getAnnotation()
		 * @generated
		 */
		EClass ANNOTATION = eINSTANCE.getAnnotation();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANNOTATION__KEY = eINSTANCE.getAnnotation_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANNOTATION__VALUE = eINSTANCE.getAnnotation_Value();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.TagImpl <em>Tag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.TagImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getTag()
		 * @generated
		 */
		EClass TAG = eINSTANCE.getTag();

		/**
		 * The meta object literal for the '<em><b>Quality Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG__QUALITY_MODEL = eINSTANCE.getTag_QualityModel();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.TaggedElementImpl <em>Tagged Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.TaggedElementImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getTaggedElement()
		 * @generated
		 */
		EClass TAGGED_ELEMENT = eINSTANCE.getTaggedElement();

		/**
		 * The meta object literal for the '<em><b>Tagged By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAGGED_ELEMENT__TAGGED_BY = eINSTANCE.getTaggedElement_TaggedBy();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.MeasureImpl <em>Measure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.MeasureImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getMeasure()
		 * @generated
		 */
		EClass MEASURE = eINSTANCE.getMeasure();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASURE__TYPE = eINSTANCE.getMeasure_Type();

		/**
		 * The meta object literal for the '<em><b>Measures</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURE__MEASURES = eINSTANCE.getMeasure_Measures();

		/**
		 * The meta object literal for the '<em><b>Measures Direct</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURE__MEASURES_DIRECT = eINSTANCE.getMeasure_MeasuresDirect();

		/**
		 * The meta object literal for the '<em><b>Quality Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURE__QUALITY_MODEL = eINSTANCE.getMeasure_QualityModel();

		/**
		 * The meta object literal for the '<em><b>Refines</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURE__REFINES = eINSTANCE.getMeasure_Refines();

		/**
		 * The meta object literal for the '<em><b>Refines Direct</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURE__REFINES_DIRECT = eINSTANCE.getMeasure_RefinesDirect();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.MeasurementMethodImpl <em>Measurement Method</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.MeasurementMethodImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getMeasurementMethod()
		 * @generated
		 */
		EClass MEASUREMENT_METHOD = eINSTANCE.getMeasurementMethod();

		/**
		 * The meta object literal for the '<em><b>Determines</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASUREMENT_METHOD__DETERMINES = eINSTANCE.getMeasurementMethod_Determines();

		/**
		 * The meta object literal for the '<em><b>Quality Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASUREMENT_METHOD__QUALITY_MODEL = eINSTANCE.getMeasurementMethod_QualityModel();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.CharacterizingElementImpl <em>Characterizing Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.CharacterizingElementImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getCharacterizingElement()
		 * @generated
		 */
		EClass CHARACTERIZING_ELEMENT = eINSTANCE.getCharacterizingElement();

		/**
		 * The meta object literal for the '<em><b>Characterizes</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHARACTERIZING_ELEMENT__CHARACTERIZES = eINSTANCE.getCharacterizingElement_Characterizes();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.QualityModelElementImpl <em>Quality Model Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.QualityModelElementImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getQualityModelElement()
		 * @generated
		 */
		EClass QUALITY_MODEL_ELEMENT = eINSTANCE.getQualityModelElement();

		/**
		 * The meta object literal for the '<em><b>Qualified Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUALITY_MODEL_ELEMENT__QUALIFIED_NAME = eINSTANCE.getQualityModelElement_QualifiedName();

		/**
		 * The meta object literal for the '<em><b>Originates From</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUALITY_MODEL_ELEMENT__ORIGINATES_FROM = eINSTANCE.getQualityModelElement_OriginatesFrom();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.ToolBasedInstrumentImpl <em>Tool Based Instrument</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.ToolBasedInstrumentImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getToolBasedInstrument()
		 * @generated
		 */
		EClass TOOL_BASED_INSTRUMENT = eINSTANCE.getToolBasedInstrument();

		/**
		 * The meta object literal for the '<em><b>Tool</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOOL_BASED_INSTRUMENT__TOOL = eINSTANCE.getToolBasedInstrument_Tool();

		/**
		 * The meta object literal for the '<em><b>Metric</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOOL_BASED_INSTRUMENT__METRIC = eINSTANCE.getToolBasedInstrument_Metric();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.ManualInstrumentImpl <em>Manual Instrument</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.ManualInstrumentImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getManualInstrument()
		 * @generated
		 */
		EClass MANUAL_INSTRUMENT = eINSTANCE.getManualInstrument();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.AnnotationBaseImpl <em>Annotation Base</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.AnnotationBaseImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getAnnotationBase()
		 * @generated
		 */
		EClass ANNOTATION_BASE = eINSTANCE.getAnnotationBase();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.QIESLEvaluationImpl <em>QIESL Evaluation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.QIESLEvaluationImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getQIESLEvaluation()
		 * @generated
		 */
		EClass QIESL_EVALUATION = eINSTANCE.getQIESLEvaluation();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.TextEvaluationImpl <em>Text Evaluation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.TextEvaluationImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getTextEvaluation()
		 * @generated
		 */
		EClass TEXT_EVALUATION = eINSTANCE.getTextEvaluation();

		/**
		 * The meta object literal for the '<em><b>Specification</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT_EVALUATION__SPECIFICATION = eINSTANCE.getTextEvaluation_Specification();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.FormBasedEvaluationImpl <em>Form Based Evaluation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.FormBasedEvaluationImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getFormBasedEvaluation()
		 * @generated
		 */
		EClass FORM_BASED_EVALUATION = eINSTANCE.getFormBasedEvaluation();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.SingleMeasureEvaluationImpl <em>Single Measure Evaluation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.SingleMeasureEvaluationImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getSingleMeasureEvaluation()
		 * @generated
		 */
		EClass SINGLE_MEASURE_EVALUATION = eINSTANCE.getSingleMeasureEvaluation();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.FactorAggregationImpl <em>Factor Aggregation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.FactorAggregationImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getFactorAggregation()
		 * @generated
		 */
		EClass FACTOR_AGGREGATION = eINSTANCE.getFactorAggregation();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.NormalizationMeasureImpl <em>Normalization Measure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.NormalizationMeasureImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getNormalizationMeasure()
		 * @generated
		 */
		EClass NORMALIZATION_MEASURE = eINSTANCE.getNormalizationMeasure();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.QIESLAggregationImpl <em>QIESL Aggregation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.QIESLAggregationImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getQIESLAggregation()
		 * @generated
		 */
		EClass QIESL_AGGREGATION = eINSTANCE.getQIESLAggregation();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.TextAggregationImpl <em>Text Aggregation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.TextAggregationImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getTextAggregation()
		 * @generated
		 */
		EClass TEXT_AGGREGATION = eINSTANCE.getTextAggregation();

		/**
		 * The meta object literal for the '<em><b>Specification</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT_AGGREGATION__SPECIFICATION = eINSTANCE.getTextAggregation_Specification();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.FormBasedMeasureAggregationImpl <em>Form Based Measure Aggregation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.FormBasedMeasureAggregationImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getFormBasedMeasureAggregation()
		 * @generated
		 */
		EClass FORM_BASED_MEASURE_AGGREGATION = eINSTANCE.getFormBasedMeasureAggregation();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.MeasureAggregationImpl <em>Measure Aggregation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.MeasureAggregationImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getMeasureAggregation()
		 * @generated
		 */
		EClass MEASURE_AGGREGATION = eINSTANCE.getMeasureAggregation();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.FactorRankingImpl <em>Factor Ranking</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.FactorRankingImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getFactorRanking()
		 * @generated
		 */
		EClass FACTOR_RANKING = eINSTANCE.getFactorRanking();

		/**
		 * The meta object literal for the '<em><b>Factor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACTOR_RANKING__FACTOR = eINSTANCE.getFactorRanking_Factor();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.LinearFunctionImpl <em>Linear Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.LinearFunctionImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getLinearFunction()
		 * @generated
		 */
		EClass LINEAR_FUNCTION = eINSTANCE.getLinearFunction();

		/**
		 * The meta object literal for the '<em><b>Lower Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINEAR_FUNCTION__LOWER_BOUND = eINSTANCE.getLinearFunction_LowerBound();

		/**
		 * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINEAR_FUNCTION__UPPER_BOUND = eINSTANCE.getLinearFunction_UpperBound();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.FunctionImpl <em>Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.FunctionImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getFunction()
		 * @generated
		 */
		EClass FUNCTION = eINSTANCE.getFunction();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.LinearIncreasingFunctionImpl <em>Linear Increasing Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.LinearIncreasingFunctionImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getLinearIncreasingFunction()
		 * @generated
		 */
		EClass LINEAR_INCREASING_FUNCTION = eINSTANCE.getLinearIncreasingFunction();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.LinearDecreasingFunctionImpl <em>Linear Decreasing Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.LinearDecreasingFunctionImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getLinearDecreasingFunction()
		 * @generated
		 */
		EClass LINEAR_DECREASING_FUNCTION = eINSTANCE.getLinearDecreasingFunction();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.WeightedSumFactorAggregationImpl <em>Weighted Sum Factor Aggregation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.WeightedSumFactorAggregationImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getWeightedSumFactorAggregation()
		 * @generated
		 */
		EClass WEIGHTED_SUM_FACTOR_AGGREGATION = eINSTANCE.getWeightedSumFactorAggregation();

		/**
		 * The meta object literal for the '<em><b>Rankings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEIGHTED_SUM_FACTOR_AGGREGATION__RANKINGS = eINSTANCE.getWeightedSumFactorAggregation_Rankings();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.FindingsUnionMeasureAggregationImpl <em>Findings Union Measure Aggregation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.FindingsUnionMeasureAggregationImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getFindingsUnionMeasureAggregation()
		 * @generated
		 */
		EClass FINDINGS_UNION_MEASURE_AGGREGATION = eINSTANCE.getFindingsUnionMeasureAggregation();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.NumberMeanMeasureAggregationImpl <em>Number Mean Measure Aggregation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.NumberMeanMeasureAggregationImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getNumberMeanMeasureAggregation()
		 * @generated
		 */
		EClass NUMBER_MEAN_MEASURE_AGGREGATION = eINSTANCE.getNumberMeanMeasureAggregation();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.ManualEvaluationImpl <em>Manual Evaluation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.ManualEvaluationImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getManualEvaluation()
		 * @generated
		 */
		EClass MANUAL_EVALUATION = eINSTANCE.getManualEvaluation();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.MeasureEvaluationImpl <em>Measure Evaluation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.MeasureEvaluationImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getMeasureEvaluation()
		 * @generated
		 */
		EClass MEASURE_EVALUATION = eINSTANCE.getMeasureEvaluation();

		/**
		 * The meta object literal for the '<em><b>Measure</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURE_EVALUATION__MEASURE = eINSTANCE.getMeasureEvaluation_Measure();

		/**
		 * The meta object literal for the '<em><b>Normlization Measure</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURE_EVALUATION__NORMLIZATION_MEASURE = eINSTANCE.getMeasureEvaluation_NormlizationMeasure();

		/**
		 * The meta object literal for the '<em><b>Range</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASURE_EVALUATION__RANGE = eINSTANCE.getMeasureEvaluation_Range();

		/**
		 * The meta object literal for the '<em><b>Function</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURE_EVALUATION__FUNCTION = eINSTANCE.getMeasureEvaluation_Function();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.RankingImpl <em>Ranking</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.RankingImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getRanking()
		 * @generated
		 */
		EClass RANKING = eINSTANCE.getRanking();

		/**
		 * The meta object literal for the '<em><b>Rank</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RANKING__RANK = eINSTANCE.getRanking_Rank();

		/**
		 * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RANKING__WEIGHT = eINSTANCE.getRanking_Weight();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.MultiMeasureEvaluationImpl <em>Multi Measure Evaluation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.MultiMeasureEvaluationImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getMultiMeasureEvaluation()
		 * @generated
		 */
		EClass MULTI_MEASURE_EVALUATION = eINSTANCE.getMultiMeasureEvaluation();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.WeightedSumMultiMeasureEvaluationImpl <em>Weighted Sum Multi Measure Evaluation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.WeightedSumMultiMeasureEvaluationImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getWeightedSumMultiMeasureEvaluation()
		 * @generated
		 */
		EClass WEIGHTED_SUM_MULTI_MEASURE_EVALUATION = eINSTANCE.getWeightedSumMultiMeasureEvaluation();

		/**
		 * The meta object literal for the '<em><b>Rankings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEIGHTED_SUM_MULTI_MEASURE_EVALUATION__RANKINGS = eINSTANCE.getWeightedSumMultiMeasureEvaluation_Rankings();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.MeasureRankingImpl <em>Measure Ranking</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.MeasureRankingImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getMeasureRanking()
		 * @generated
		 */
		EClass MEASURE_RANKING = eINSTANCE.getMeasureRanking();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.QualityModelResultImpl <em>Quality Model Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.QualityModelResultImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getQualityModelResult()
		 * @generated
		 */
		EClass QUALITY_MODEL_RESULT = eINSTANCE.getQualityModelResult();

		/**
		 * The meta object literal for the '<em><b>System</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUALITY_MODEL_RESULT__SYSTEM = eINSTANCE.getQualityModelResult_System();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUALITY_MODEL_RESULT__DATE = eINSTANCE.getQualityModelResult_Date();

		/**
		 * The meta object literal for the '<em><b>Measurement Results</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUALITY_MODEL_RESULT__MEASUREMENT_RESULTS = eINSTANCE.getQualityModelResult_MeasurementResults();

		/**
		 * The meta object literal for the '<em><b>Evaluation Results</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUALITY_MODEL_RESULT__EVALUATION_RESULTS = eINSTANCE.getQualityModelResult_EvaluationResults();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.ResultImpl <em>Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.ResultImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getResult()
		 * @generated
		 */
		EClass RESULT = eINSTANCE.getResult();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESULT__MESSAGE = eINSTANCE.getResult_Message();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.FindingMessageImpl <em>Finding Message</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.FindingMessageImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getFindingMessage()
		 * @generated
		 */
		EClass FINDING_MESSAGE = eINSTANCE.getFindingMessage();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINDING_MESSAGE__MESSAGE = eINSTANCE.getFindingMessage_Message();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINDING_MESSAGE__LOCATION = eINSTANCE.getFindingMessage_Location();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.MeasurementResultImpl <em>Measurement Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.MeasurementResultImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getMeasurementResult()
		 * @generated
		 */
		EClass MEASUREMENT_RESULT = eINSTANCE.getMeasurementResult();

		/**
		 * The meta object literal for the '<em><b>Results From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASUREMENT_RESULT__RESULTS_FROM = eINSTANCE.getMeasurementResult_ResultsFrom();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.NumberMeasurementResultImpl <em>Number Measurement Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.NumberMeasurementResultImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getNumberMeasurementResult()
		 * @generated
		 */
		EClass NUMBER_MEASUREMENT_RESULT = eINSTANCE.getNumberMeasurementResult();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NUMBER_MEASUREMENT_RESULT__VALUE = eINSTANCE.getNumberMeasurementResult_Value();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.FindingsMeasurementResultImpl <em>Findings Measurement Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.FindingsMeasurementResultImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getFindingsMeasurementResult()
		 * @generated
		 */
		EClass FINDINGS_MEASUREMENT_RESULT = eINSTANCE.getFindingsMeasurementResult();

		/**
		 * The meta object literal for the '<em><b>Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINDINGS_MEASUREMENT_RESULT__COUNT = eINSTANCE.getFindingsMeasurementResult_Count();

		/**
		 * The meta object literal for the '<em><b>Findings</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINDINGS_MEASUREMENT_RESULT__FINDINGS = eINSTANCE.getFindingsMeasurementResult_Findings();

		/**
		 * The meta object literal for the '<em><b>Finding Messages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FINDINGS_MEASUREMENT_RESULT__FINDING_MESSAGES = eINSTANCE.getFindingsMeasurementResult_FindingMessages();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.DoubleIntervalImpl <em>Double Interval</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.DoubleIntervalImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getDoubleInterval()
		 * @generated
		 */
		EClass DOUBLE_INTERVAL = eINSTANCE.getDoubleInterval();

		/**
		 * The meta object literal for the '<em><b>Lower</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOUBLE_INTERVAL__LOWER = eINSTANCE.getDoubleInterval_Lower();

		/**
		 * The meta object literal for the '<em><b>Upper</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOUBLE_INTERVAL__UPPER = eINSTANCE.getDoubleInterval_Upper();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.EvaluationResultImpl <em>Evaluation Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.EvaluationResultImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getEvaluationResult()
		 * @generated
		 */
		EClass EVALUATION_RESULT = eINSTANCE.getEvaluationResult();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVALUATION_RESULT__VALUE = eINSTANCE.getEvaluationResult_Value();

		/**
		 * The meta object literal for the '<em><b>Results From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVALUATION_RESULT__RESULTS_FROM = eINSTANCE.getEvaluationResult_ResultsFrom();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.SingleMeasureEvaluationResultImpl <em>Single Measure Evaluation Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.SingleMeasureEvaluationResultImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getSingleMeasureEvaluationResult()
		 * @generated
		 */
		EClass SINGLE_MEASURE_EVALUATION_RESULT = eINSTANCE.getSingleMeasureEvaluationResult();

		/**
		 * The meta object literal for the '<em><b>Ratio Affected</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SINGLE_MEASURE_EVALUATION_RESULT__RATIO_AFFECTED = eINSTANCE.getSingleMeasureEvaluationResult_RatioAffected();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.MultiMeasureEvaluationResultImpl <em>Multi Measure Evaluation Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.MultiMeasureEvaluationResultImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getMultiMeasureEvaluationResult()
		 * @generated
		 */
		EClass MULTI_MEASURE_EVALUATION_RESULT = eINSTANCE.getMultiMeasureEvaluationResult();

		/**
		 * The meta object literal for the '<em><b>Evaluation Results</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MULTI_MEASURE_EVALUATION_RESULT__EVALUATION_RESULTS = eINSTANCE.getMultiMeasureEvaluationResult_EvaluationResults();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.MeasureRankingEvaluationResultImpl <em>Measure Ranking Evaluation Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.MeasureRankingEvaluationResultImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getMeasureRankingEvaluationResult()
		 * @generated
		 */
		EClass MEASURE_RANKING_EVALUATION_RESULT = eINSTANCE.getMeasureRankingEvaluationResult();

		/**
		 * The meta object literal for the '<em><b>Ratio Affected</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASURE_RANKING_EVALUATION_RESULT__RATIO_AFFECTED = eINSTANCE.getMeasureRankingEvaluationResult_RatioAffected();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURE_RANKING_EVALUATION_RESULT__VALUE = eINSTANCE.getMeasureRankingEvaluationResult_Value();

		/**
		 * The meta object literal for the '<em><b>Results From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURE_RANKING_EVALUATION_RESULT__RESULTS_FROM = eINSTANCE.getMeasureRankingEvaluationResult_ResultsFrom();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.ToolImpl <em>Tool</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.ToolImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getTool()
		 * @generated
		 */
		EClass TOOL = eINSTANCE.getTool();

		/**
		 * The meta object literal for the '<em><b>Quality Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOOL__QUALITY_MODEL = eINSTANCE.getTool_QualityModel();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.SourceImpl <em>Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.SourceImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getSource()
		 * @generated
		 */
		EClass SOURCE = eINSTANCE.getSource();

		/**
		 * The meta object literal for the '<em><b>Quality Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOURCE__QUALITY_MODEL = eINSTANCE.getSource_QualityModel();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.MeasurementImpl <em>Measurement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.MeasurementImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getMeasurement()
		 * @generated
		 */
		EClass MEASUREMENT = eINSTANCE.getMeasurement();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASUREMENT__PARENT = eINSTANCE.getMeasurement_Parent();

		/**
		 * The meta object literal for the '<em><b>Child</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASUREMENT__CHILD = eINSTANCE.getMeasurement_Child();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.SpecializationImpl <em>Specialization</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.SpecializationImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getSpecialization()
		 * @generated
		 */
		EClass SPECIALIZATION = eINSTANCE.getSpecialization();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPECIALIZATION__PARENT = eINSTANCE.getSpecialization_Parent();

		/**
		 * The meta object literal for the '<em><b>Child</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPECIALIZATION__CHILD = eINSTANCE.getSpecialization_Child();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.DecompositionImpl <em>Decomposition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.DecompositionImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getDecomposition()
		 * @generated
		 */
		EClass DECOMPOSITION = eINSTANCE.getDecomposition();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECOMPOSITION__PARENT = eINSTANCE.getDecomposition_Parent();

		/**
		 * The meta object literal for the '<em><b>Child</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECOMPOSITION__CHILD = eINSTANCE.getDecomposition_Child();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.impl.MeasureRefinementImpl <em>Measure Refinement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.impl.MeasureRefinementImpl
		 * @see de.quamoco.qm.impl.QmPackageImpl#getMeasureRefinement()
		 * @generated
		 */
		EClass MEASURE_REFINEMENT = eINSTANCE.getMeasureRefinement();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURE_REFINEMENT__PARENT = eINSTANCE.getMeasureRefinement_Parent();

		/**
		 * The meta object literal for the '<em><b>Child</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURE_REFINEMENT__CHILD = eINSTANCE.getMeasureRefinement_Child();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.Effect <em>Effect</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.Effect
		 * @see de.quamoco.qm.impl.QmPackageImpl#getEffect()
		 * @generated
		 */
		EEnum EFFECT = eINSTANCE.getEffect();

		/**
		 * The meta object literal for the '{@link de.quamoco.qm.Type <em>Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.qm.Type
		 * @see de.quamoco.qm.impl.QmPackageImpl#getType()
		 * @generated
		 */
		EEnum TYPE = eINSTANCE.getType();

		/**
		 * The meta object literal for the '<em>Finding Collection</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.tum.cs.conqat.quamoco.FindingCollection
		 * @see de.quamoco.qm.impl.QmPackageImpl#getFindingCollection()
		 * @generated
		 */
		EDataType FINDING_COLLECTION = eINSTANCE.getFindingCollection();

	}

} //QmPackage
