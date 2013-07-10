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
 * $Id: CmPackage.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package edu.tum.cs.conqat.inspection.cm;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see edu.tum.cs.conqat.inspection.cm.CmFactory
 * @model kind="package"
 * @generated
 */
public interface CmPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "cm";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.quamoco.de/cm/v1";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "cm";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CmPackage eINSTANCE = edu.tum.cs.conqat.inspection.cm.impl.CmPackageImpl.init();

	/**
	 * The meta object id for the '{@link edu.tum.cs.conqat.inspection.cm.impl.ChecklistImpl <em>Checklist</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.tum.cs.conqat.inspection.cm.impl.ChecklistImpl
	 * @see edu.tum.cs.conqat.inspection.cm.impl.CmPackageImpl#getChecklist()
	 * @generated
	 */
	int CHECKLIST = 0;

	/**
	 * The feature id for the '<em><b>Inspector</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__INSPECTOR = 0;

	/**
	 * The feature id for the '<em><b>Inspection Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__INSPECTION_ITEMS = 1;

	/**
	 * The feature id for the '<em><b>Inspection Measures</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__INSPECTION_MEASURES = 2;

	/**
	 * The feature id for the '<em><b>Qm File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST__QM_FILE_NAME = 3;

	/**
	 * The number of structural features of the '<em>Checklist</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKLIST_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link edu.tum.cs.conqat.inspection.cm.impl.InspectionMeasureImpl <em>Inspection Measure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.tum.cs.conqat.inspection.cm.impl.InspectionMeasureImpl
	 * @see edu.tum.cs.conqat.inspection.cm.impl.CmPackageImpl#getInspectionMeasure()
	 * @generated
	 */
	int INSPECTION_MEASURE = 1;

	/**
	 * The feature id for the '<em><b>Measure Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSPECTION_MEASURE__MEASURE_INFO = 0;

	/**
	 * The feature id for the '<em><b>Measure Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSPECTION_MEASURE__MEASURE_NAME = 1;

	/**
	 * The feature id for the '<em><b>Instrument Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSPECTION_MEASURE__INSTRUMENT_INFO = 2;

	/**
	 * The feature id for the '<em><b>Sample Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSPECTION_MEASURE__SAMPLE_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Documentations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSPECTION_MEASURE__DOCUMENTATIONS = 4;

	/**
	 * The number of structural features of the '<em>Inspection Measure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSPECTION_MEASURE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link edu.tum.cs.conqat.inspection.cm.impl.InspectionItemImpl <em>Inspection Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.tum.cs.conqat.inspection.cm.impl.InspectionItemImpl
	 * @see edu.tum.cs.conqat.inspection.cm.impl.CmPackageImpl#getInspectionItem()
	 * @generated
	 */
	int INSPECTION_ITEM = 2;

	/**
	 * The feature id for the '<em><b>Checklist</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSPECTION_ITEM__CHECKLIST = 0;

	/**
	 * The feature id for the '<em><b>Inspection Measures</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSPECTION_ITEM__INSPECTION_MEASURES = 1;

	/**
	 * The feature id for the '<em><b>Samples</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSPECTION_ITEM__SAMPLES = 2;

	/**
	 * The feature id for the '<em><b>Inspection Evaluations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSPECTION_ITEM__INSPECTION_EVALUATIONS = 3;

	/**
	 * The number of structural features of the '<em>Inspection Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSPECTION_ITEM_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link edu.tum.cs.conqat.inspection.cm.impl.SampleImpl <em>Sample</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.tum.cs.conqat.inspection.cm.impl.SampleImpl
	 * @see edu.tum.cs.conqat.inspection.cm.impl.CmPackageImpl#getSample()
	 * @generated
	 */
	int SAMPLE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAMPLE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Package Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAMPLE__PACKAGE_PATH = 1;

	/**
	 * The feature id for the '<em><b>Source Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAMPLE__SOURCE_PATH = 2;

	/**
	 * The feature id for the '<em><b>Line Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAMPLE__LINE_NUMBER = 3;

	/**
	 * The feature id for the '<em><b>Source Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAMPLE__SOURCE_START = 4;

	/**
	 * The feature id for the '<em><b>Source End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAMPLE__SOURCE_END = 5;

	/**
	 * The feature id for the '<em><b>Inspection Item</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAMPLE__INSPECTION_ITEM = 6;

	/**
	 * The number of structural features of the '<em>Sample</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAMPLE_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link edu.tum.cs.conqat.inspection.cm.impl.DocumentationImpl <em>Documentation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.tum.cs.conqat.inspection.cm.impl.DocumentationImpl
	 * @see edu.tum.cs.conqat.inspection.cm.impl.CmPackageImpl#getDocumentation()
	 * @generated
	 */
	int DOCUMENTATION = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTATION__NAME = 0;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTATION__PATH = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTATION__TYPE = 2;

	/**
	 * The feature id for the '<em><b>Inspection Measure</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTATION__INSPECTION_MEASURE = 3;

	/**
	 * The number of structural features of the '<em>Documentation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTATION_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link edu.tum.cs.conqat.inspection.cm.impl.InspectionEvaluationImpl <em>Inspection Evaluation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.tum.cs.conqat.inspection.cm.impl.InspectionEvaluationImpl
	 * @see edu.tum.cs.conqat.inspection.cm.impl.CmPackageImpl#getInspectionEvaluation()
	 * @generated
	 */
	int INSPECTION_EVALUATION = 5;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSPECTION_EVALUATION__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Sample</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSPECTION_EVALUATION__SAMPLE = 1;

	/**
	 * The feature id for the '<em><b>Inspection Measure</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSPECTION_EVALUATION__INSPECTION_MEASURE = 2;

	/**
	 * The feature id for the '<em><b>Inspection Item</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSPECTION_EVALUATION__INSPECTION_ITEM = 3;

	/**
	 * The number of structural features of the '<em>Inspection Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSPECTION_EVALUATION_FEATURE_COUNT = 4;


	/**
	 * Returns the meta object for class '{@link edu.tum.cs.conqat.inspection.cm.Checklist <em>Checklist</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Checklist</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.Checklist
	 * @generated
	 */
	EClass getChecklist();

	/**
	 * Returns the meta object for the attribute '{@link edu.tum.cs.conqat.inspection.cm.Checklist#getInspector <em>Inspector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inspector</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.Checklist#getInspector()
	 * @see #getChecklist()
	 * @generated
	 */
	EAttribute getChecklist_Inspector();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.tum.cs.conqat.inspection.cm.Checklist#getInspectionItems <em>Inspection Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Inspection Items</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.Checklist#getInspectionItems()
	 * @see #getChecklist()
	 * @generated
	 */
	EReference getChecklist_InspectionItems();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.tum.cs.conqat.inspection.cm.Checklist#getInspectionMeasures <em>Inspection Measures</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Inspection Measures</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.Checklist#getInspectionMeasures()
	 * @see #getChecklist()
	 * @generated
	 */
	EReference getChecklist_InspectionMeasures();

	/**
	 * Returns the meta object for the attribute '{@link edu.tum.cs.conqat.inspection.cm.Checklist#getQmFileName <em>Qm File Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Qm File Name</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.Checklist#getQmFileName()
	 * @see #getChecklist()
	 * @generated
	 */
	EAttribute getChecklist_QmFileName();

	/**
	 * Returns the meta object for class '{@link edu.tum.cs.conqat.inspection.cm.InspectionMeasure <em>Inspection Measure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Inspection Measure</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionMeasure
	 * @generated
	 */
	EClass getInspectionMeasure();

	/**
	 * Returns the meta object for the attribute '{@link edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getMeasureInfo <em>Measure Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Measure Info</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getMeasureInfo()
	 * @see #getInspectionMeasure()
	 * @generated
	 */
	EAttribute getInspectionMeasure_MeasureInfo();

	/**
	 * Returns the meta object for the attribute '{@link edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getMeasureName <em>Measure Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Measure Name</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getMeasureName()
	 * @see #getInspectionMeasure()
	 * @generated
	 */
	EAttribute getInspectionMeasure_MeasureName();

	/**
	 * Returns the meta object for the attribute '{@link edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getInstrumentInfo <em>Instrument Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instrument Info</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getInstrumentInfo()
	 * @see #getInspectionMeasure()
	 * @generated
	 */
	EAttribute getInspectionMeasure_InstrumentInfo();

	/**
	 * Returns the meta object for the attribute '{@link edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getSampleType <em>Sample Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sample Type</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getSampleType()
	 * @see #getInspectionMeasure()
	 * @generated
	 */
	EAttribute getInspectionMeasure_SampleType();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getDocumentations <em>Documentations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Documentations</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getDocumentations()
	 * @see #getInspectionMeasure()
	 * @generated
	 */
	EReference getInspectionMeasure_Documentations();

	/**
	 * Returns the meta object for class '{@link edu.tum.cs.conqat.inspection.cm.InspectionItem <em>Inspection Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Inspection Item</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionItem
	 * @generated
	 */
	EClass getInspectionItem();

	/**
	 * Returns the meta object for the container reference '{@link edu.tum.cs.conqat.inspection.cm.InspectionItem#getChecklist <em>Checklist</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Checklist</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionItem#getChecklist()
	 * @see #getInspectionItem()
	 * @generated
	 */
	EReference getInspectionItem_Checklist();

	/**
	 * Returns the meta object for the reference list '{@link edu.tum.cs.conqat.inspection.cm.InspectionItem#getInspectionMeasures <em>Inspection Measures</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Inspection Measures</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionItem#getInspectionMeasures()
	 * @see #getInspectionItem()
	 * @generated
	 */
	EReference getInspectionItem_InspectionMeasures();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.tum.cs.conqat.inspection.cm.InspectionItem#getSamples <em>Samples</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Samples</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionItem#getSamples()
	 * @see #getInspectionItem()
	 * @generated
	 */
	EReference getInspectionItem_Samples();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.tum.cs.conqat.inspection.cm.InspectionItem#getInspectionEvaluations <em>Inspection Evaluations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Inspection Evaluations</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionItem#getInspectionEvaluations()
	 * @see #getInspectionItem()
	 * @generated
	 */
	EReference getInspectionItem_InspectionEvaluations();

	/**
	 * Returns the meta object for class '{@link edu.tum.cs.conqat.inspection.cm.Sample <em>Sample</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sample</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.Sample
	 * @generated
	 */
	EClass getSample();

	/**
	 * Returns the meta object for the attribute '{@link edu.tum.cs.conqat.inspection.cm.Sample#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.Sample#getName()
	 * @see #getSample()
	 * @generated
	 */
	EAttribute getSample_Name();

	/**
	 * Returns the meta object for the attribute '{@link edu.tum.cs.conqat.inspection.cm.Sample#getPackagePath <em>Package Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Package Path</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.Sample#getPackagePath()
	 * @see #getSample()
	 * @generated
	 */
	EAttribute getSample_PackagePath();

	/**
	 * Returns the meta object for the attribute '{@link edu.tum.cs.conqat.inspection.cm.Sample#getSourcePath <em>Source Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Path</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.Sample#getSourcePath()
	 * @see #getSample()
	 * @generated
	 */
	EAttribute getSample_SourcePath();

	/**
	 * Returns the meta object for the attribute '{@link edu.tum.cs.conqat.inspection.cm.Sample#getLineNumber <em>Line Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Number</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.Sample#getLineNumber()
	 * @see #getSample()
	 * @generated
	 */
	EAttribute getSample_LineNumber();

	/**
	 * Returns the meta object for the attribute '{@link edu.tum.cs.conqat.inspection.cm.Sample#getSourceStart <em>Source Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Start</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.Sample#getSourceStart()
	 * @see #getSample()
	 * @generated
	 */
	EAttribute getSample_SourceStart();

	/**
	 * Returns the meta object for the attribute '{@link edu.tum.cs.conqat.inspection.cm.Sample#getSourceEnd <em>Source End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source End</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.Sample#getSourceEnd()
	 * @see #getSample()
	 * @generated
	 */
	EAttribute getSample_SourceEnd();

	/**
	 * Returns the meta object for the container reference '{@link edu.tum.cs.conqat.inspection.cm.Sample#getInspectionItem <em>Inspection Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Inspection Item</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.Sample#getInspectionItem()
	 * @see #getSample()
	 * @generated
	 */
	EReference getSample_InspectionItem();

	/**
	 * Returns the meta object for class '{@link edu.tum.cs.conqat.inspection.cm.Documentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documentation</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.Documentation
	 * @generated
	 */
	EClass getDocumentation();

	/**
	 * Returns the meta object for the attribute '{@link edu.tum.cs.conqat.inspection.cm.Documentation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.Documentation#getName()
	 * @see #getDocumentation()
	 * @generated
	 */
	EAttribute getDocumentation_Name();

	/**
	 * Returns the meta object for the attribute '{@link edu.tum.cs.conqat.inspection.cm.Documentation#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.Documentation#getPath()
	 * @see #getDocumentation()
	 * @generated
	 */
	EAttribute getDocumentation_Path();

	/**
	 * Returns the meta object for the attribute '{@link edu.tum.cs.conqat.inspection.cm.Documentation#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.Documentation#getType()
	 * @see #getDocumentation()
	 * @generated
	 */
	EAttribute getDocumentation_Type();

	/**
	 * Returns the meta object for the container reference '{@link edu.tum.cs.conqat.inspection.cm.Documentation#getInspectionMeasure <em>Inspection Measure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Inspection Measure</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.Documentation#getInspectionMeasure()
	 * @see #getDocumentation()
	 * @generated
	 */
	EReference getDocumentation_InspectionMeasure();

	/**
	 * Returns the meta object for class '{@link edu.tum.cs.conqat.inspection.cm.InspectionEvaluation <em>Inspection Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Inspection Evaluation</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionEvaluation
	 * @generated
	 */
	EClass getInspectionEvaluation();

	/**
	 * Returns the meta object for the attribute '{@link edu.tum.cs.conqat.inspection.cm.InspectionEvaluation#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionEvaluation#getValue()
	 * @see #getInspectionEvaluation()
	 * @generated
	 */
	EAttribute getInspectionEvaluation_Value();

	/**
	 * Returns the meta object for the reference '{@link edu.tum.cs.conqat.inspection.cm.InspectionEvaluation#getSample <em>Sample</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Sample</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionEvaluation#getSample()
	 * @see #getInspectionEvaluation()
	 * @generated
	 */
	EReference getInspectionEvaluation_Sample();

	/**
	 * Returns the meta object for the reference '{@link edu.tum.cs.conqat.inspection.cm.InspectionEvaluation#getInspectionMeasure <em>Inspection Measure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Inspection Measure</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionEvaluation#getInspectionMeasure()
	 * @see #getInspectionEvaluation()
	 * @generated
	 */
	EReference getInspectionEvaluation_InspectionMeasure();

	/**
	 * Returns the meta object for the container reference '{@link edu.tum.cs.conqat.inspection.cm.InspectionEvaluation#getInspectionItem <em>Inspection Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Inspection Item</em>'.
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionEvaluation#getInspectionItem()
	 * @see #getInspectionEvaluation()
	 * @generated
	 */
	EReference getInspectionEvaluation_InspectionItem();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CmFactory getCmFactory();

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
		 * The meta object literal for the '{@link edu.tum.cs.conqat.inspection.cm.impl.ChecklistImpl <em>Checklist</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.tum.cs.conqat.inspection.cm.impl.ChecklistImpl
		 * @see edu.tum.cs.conqat.inspection.cm.impl.CmPackageImpl#getChecklist()
		 * @generated
		 */
		EClass CHECKLIST = eINSTANCE.getChecklist();

		/**
		 * The meta object literal for the '<em><b>Inspector</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECKLIST__INSPECTOR = eINSTANCE.getChecklist_Inspector();

		/**
		 * The meta object literal for the '<em><b>Inspection Items</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHECKLIST__INSPECTION_ITEMS = eINSTANCE.getChecklist_InspectionItems();

		/**
		 * The meta object literal for the '<em><b>Inspection Measures</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHECKLIST__INSPECTION_MEASURES = eINSTANCE.getChecklist_InspectionMeasures();

		/**
		 * The meta object literal for the '<em><b>Qm File Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECKLIST__QM_FILE_NAME = eINSTANCE.getChecklist_QmFileName();

		/**
		 * The meta object literal for the '{@link edu.tum.cs.conqat.inspection.cm.impl.InspectionMeasureImpl <em>Inspection Measure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.tum.cs.conqat.inspection.cm.impl.InspectionMeasureImpl
		 * @see edu.tum.cs.conqat.inspection.cm.impl.CmPackageImpl#getInspectionMeasure()
		 * @generated
		 */
		EClass INSPECTION_MEASURE = eINSTANCE.getInspectionMeasure();

		/**
		 * The meta object literal for the '<em><b>Measure Info</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSPECTION_MEASURE__MEASURE_INFO = eINSTANCE.getInspectionMeasure_MeasureInfo();

		/**
		 * The meta object literal for the '<em><b>Measure Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSPECTION_MEASURE__MEASURE_NAME = eINSTANCE.getInspectionMeasure_MeasureName();

		/**
		 * The meta object literal for the '<em><b>Instrument Info</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSPECTION_MEASURE__INSTRUMENT_INFO = eINSTANCE.getInspectionMeasure_InstrumentInfo();

		/**
		 * The meta object literal for the '<em><b>Sample Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSPECTION_MEASURE__SAMPLE_TYPE = eINSTANCE.getInspectionMeasure_SampleType();

		/**
		 * The meta object literal for the '<em><b>Documentations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSPECTION_MEASURE__DOCUMENTATIONS = eINSTANCE.getInspectionMeasure_Documentations();

		/**
		 * The meta object literal for the '{@link edu.tum.cs.conqat.inspection.cm.impl.InspectionItemImpl <em>Inspection Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.tum.cs.conqat.inspection.cm.impl.InspectionItemImpl
		 * @see edu.tum.cs.conqat.inspection.cm.impl.CmPackageImpl#getInspectionItem()
		 * @generated
		 */
		EClass INSPECTION_ITEM = eINSTANCE.getInspectionItem();

		/**
		 * The meta object literal for the '<em><b>Checklist</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSPECTION_ITEM__CHECKLIST = eINSTANCE.getInspectionItem_Checklist();

		/**
		 * The meta object literal for the '<em><b>Inspection Measures</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSPECTION_ITEM__INSPECTION_MEASURES = eINSTANCE.getInspectionItem_InspectionMeasures();

		/**
		 * The meta object literal for the '<em><b>Samples</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSPECTION_ITEM__SAMPLES = eINSTANCE.getInspectionItem_Samples();

		/**
		 * The meta object literal for the '<em><b>Inspection Evaluations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSPECTION_ITEM__INSPECTION_EVALUATIONS = eINSTANCE.getInspectionItem_InspectionEvaluations();

		/**
		 * The meta object literal for the '{@link edu.tum.cs.conqat.inspection.cm.impl.SampleImpl <em>Sample</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.tum.cs.conqat.inspection.cm.impl.SampleImpl
		 * @see edu.tum.cs.conqat.inspection.cm.impl.CmPackageImpl#getSample()
		 * @generated
		 */
		EClass SAMPLE = eINSTANCE.getSample();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SAMPLE__NAME = eINSTANCE.getSample_Name();

		/**
		 * The meta object literal for the '<em><b>Package Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SAMPLE__PACKAGE_PATH = eINSTANCE.getSample_PackagePath();

		/**
		 * The meta object literal for the '<em><b>Source Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SAMPLE__SOURCE_PATH = eINSTANCE.getSample_SourcePath();

		/**
		 * The meta object literal for the '<em><b>Line Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SAMPLE__LINE_NUMBER = eINSTANCE.getSample_LineNumber();

		/**
		 * The meta object literal for the '<em><b>Source Start</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SAMPLE__SOURCE_START = eINSTANCE.getSample_SourceStart();

		/**
		 * The meta object literal for the '<em><b>Source End</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SAMPLE__SOURCE_END = eINSTANCE.getSample_SourceEnd();

		/**
		 * The meta object literal for the '<em><b>Inspection Item</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SAMPLE__INSPECTION_ITEM = eINSTANCE.getSample_InspectionItem();

		/**
		 * The meta object literal for the '{@link edu.tum.cs.conqat.inspection.cm.impl.DocumentationImpl <em>Documentation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.tum.cs.conqat.inspection.cm.impl.DocumentationImpl
		 * @see edu.tum.cs.conqat.inspection.cm.impl.CmPackageImpl#getDocumentation()
		 * @generated
		 */
		EClass DOCUMENTATION = eINSTANCE.getDocumentation();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENTATION__NAME = eINSTANCE.getDocumentation_Name();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENTATION__PATH = eINSTANCE.getDocumentation_Path();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENTATION__TYPE = eINSTANCE.getDocumentation_Type();

		/**
		 * The meta object literal for the '<em><b>Inspection Measure</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENTATION__INSPECTION_MEASURE = eINSTANCE.getDocumentation_InspectionMeasure();

		/**
		 * The meta object literal for the '{@link edu.tum.cs.conqat.inspection.cm.impl.InspectionEvaluationImpl <em>Inspection Evaluation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.tum.cs.conqat.inspection.cm.impl.InspectionEvaluationImpl
		 * @see edu.tum.cs.conqat.inspection.cm.impl.CmPackageImpl#getInspectionEvaluation()
		 * @generated
		 */
		EClass INSPECTION_EVALUATION = eINSTANCE.getInspectionEvaluation();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSPECTION_EVALUATION__VALUE = eINSTANCE.getInspectionEvaluation_Value();

		/**
		 * The meta object literal for the '<em><b>Sample</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSPECTION_EVALUATION__SAMPLE = eINSTANCE.getInspectionEvaluation_Sample();

		/**
		 * The meta object literal for the '<em><b>Inspection Measure</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSPECTION_EVALUATION__INSPECTION_MEASURE = eINSTANCE.getInspectionEvaluation_InspectionMeasure();

		/**
		 * The meta object literal for the '<em><b>Inspection Item</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSPECTION_EVALUATION__INSPECTION_ITEM = eINSTANCE.getInspectionEvaluation_InspectionItem();

	}

} //CmPackage
