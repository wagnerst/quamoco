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
 * $Id$
 */
package de.quamoco.adaptation.model.status;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import de.quamoco.qm.QmPackage;

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
 * @see de.quamoco.adaptation.model.status.StatusFactory
 * @model kind="package"
 * @generated
 */
public interface StatusPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "status";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "de.quamoco.adaptation.model.status";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "de.quamoco.adaptation.model";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StatusPackage eINSTANCE = de.quamoco.adaptation.model.status.impl.StatusPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.quamoco.adaptation.model.status.impl.AdaptationStatusItemImpl <em>Adaptation Status Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.adaptation.model.status.impl.AdaptationStatusItemImpl
	 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getAdaptationStatusItem()
	 * @generated
	 */
	int ADAPTATION_STATUS_ITEM = 0;

	/**
	 * The feature id for the '<em><b>Affected Element Adaptation Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_STATUS_ITEM__AFFECTED_ELEMENT_ADAPTATION_ID = QmPackage.ANNOTATION_BASE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Adaptation Status Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_STATUS_ITEM_FEATURE_COUNT = QmPackage.ANNOTATION_BASE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.quamoco.adaptation.model.status.impl.AdaptationHistoryItemImpl <em>Adaptation History Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.adaptation.model.status.impl.AdaptationHistoryItemImpl
	 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getAdaptationHistoryItem()
	 * @generated
	 */
	int ADAPTATION_HISTORY_ITEM = 1;

	/**
	 * The feature id for the '<em><b>Affected Element Adaptation Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_HISTORY_ITEM__AFFECTED_ELEMENT_ADAPTATION_ID = ADAPTATION_STATUS_ITEM__AFFECTED_ELEMENT_ADAPTATION_ID;

	/**
	 * The feature id for the '<em><b>Performed Action Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_HISTORY_ITEM__PERFORMED_ACTION_NAME = ADAPTATION_STATUS_ITEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Affected Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_HISTORY_ITEM__AFFECTED_ELEMENT_NAME = ADAPTATION_STATUS_ITEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Justification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_HISTORY_ITEM__JUSTIFICATION = ADAPTATION_STATUS_ITEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Adaptation Tasks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_HISTORY_ITEM__ADAPTATION_TASKS = ADAPTATION_STATUS_ITEM_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_HISTORY_ITEM__TIMESTAMP = ADAPTATION_STATUS_ITEM_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_HISTORY_ITEM__SOURCE = ADAPTATION_STATUS_ITEM_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Performed Action Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_HISTORY_ITEM__PERFORMED_ACTION_VALUE = ADAPTATION_STATUS_ITEM_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Adaptation History Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_HISTORY_ITEM_FEATURE_COUNT = ADAPTATION_STATUS_ITEM_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link de.quamoco.adaptation.model.status.impl.AdaptationTaskImpl <em>Adaptation Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.adaptation.model.status.impl.AdaptationTaskImpl
	 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getAdaptationTask()
	 * @generated
	 */
	int ADAPTATION_TASK = 2;

	/**
	 * The feature id for the '<em><b>Affected Element Adaptation Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_TASK__AFFECTED_ELEMENT_ADAPTATION_ID = ADAPTATION_STATUS_ITEM__AFFECTED_ELEMENT_ADAPTATION_ID;

	/**
	 * The feature id for the '<em><b>User Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_TASK__USER_MESSAGE = ADAPTATION_STATUS_ITEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Fulfillment Criteria</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_TASK__FULFILLMENT_CRITERIA = ADAPTATION_STATUS_ITEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Affected Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_TASK__AFFECTED_ELEMENT = ADAPTATION_STATUS_ITEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Auto Delete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_TASK__AUTO_DELETE = ADAPTATION_STATUS_ITEM_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Task Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_TASK__TASK_ID = ADAPTATION_STATUS_ITEM_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_TASK__IGNORED_BY_USER = ADAPTATION_STATUS_ITEM_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Adaptation Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_TASK_FEATURE_COUNT = ADAPTATION_STATUS_ITEM_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link de.quamoco.adaptation.model.status.impl.FulfillmentCriterionImpl <em>Fulfillment Criterion</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.adaptation.model.status.impl.FulfillmentCriterionImpl
	 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getFulfillmentCriterion()
	 * @generated
	 */
	int FULFILLMENT_CRITERION = 3;

	/**
	 * The feature id for the '<em><b>Justification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULFILLMENT_CRITERION__JUSTIFICATION = 0;

	/**
	 * The feature id for the '<em><b>User Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULFILLMENT_CRITERION__USER_MESSAGE = 1;

	/**
	 * The feature id for the '<em><b>Adaptation Task</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULFILLMENT_CRITERION__ADAPTATION_TASK = 2;

	/**
	 * The number of structural features of the '<em>Fulfillment Criterion</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FULFILLMENT_CRITERION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.quamoco.adaptation.model.status.impl.UserMarkedCompletedImpl <em>User Marked Completed</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.adaptation.model.status.impl.UserMarkedCompletedImpl
	 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getUserMarkedCompleted()
	 * @generated
	 */
	int USER_MARKED_COMPLETED = 4;

	/**
	 * The feature id for the '<em><b>Justification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MARKED_COMPLETED__JUSTIFICATION = FULFILLMENT_CRITERION__JUSTIFICATION;

	/**
	 * The feature id for the '<em><b>User Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MARKED_COMPLETED__USER_MESSAGE = FULFILLMENT_CRITERION__USER_MESSAGE;

	/**
	 * The feature id for the '<em><b>Adaptation Task</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MARKED_COMPLETED__ADAPTATION_TASK = FULFILLMENT_CRITERION__ADAPTATION_TASK;

	/**
	 * The feature id for the '<em><b>Has Marked Completed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MARKED_COMPLETED__HAS_MARKED_COMPLETED = FULFILLMENT_CRITERION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Last Update</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MARKED_COMPLETED__LAST_UPDATE = FULFILLMENT_CRITERION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>User Marked Completed</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_MARKED_COMPLETED_FEATURE_COUNT = FULFILLMENT_CRITERION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.quamoco.adaptation.model.status.impl.FeatureRequiredImpl <em>Feature Required</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.adaptation.model.status.impl.FeatureRequiredImpl
	 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getFeatureRequired()
	 * @generated
	 */
	int FEATURE_REQUIRED = 5;

	/**
	 * The feature id for the '<em><b>Justification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_REQUIRED__JUSTIFICATION = FULFILLMENT_CRITERION__JUSTIFICATION;

	/**
	 * The feature id for the '<em><b>User Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_REQUIRED__USER_MESSAGE = FULFILLMENT_CRITERION__USER_MESSAGE;

	/**
	 * The feature id for the '<em><b>Adaptation Task</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_REQUIRED__ADAPTATION_TASK = FULFILLMENT_CRITERION__ADAPTATION_TASK;

	/**
	 * The feature id for the '<em><b>Required Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_REQUIRED__REQUIRED_FEATURE_NAME = FULFILLMENT_CRITERION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Required Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_REQUIRED__REQUIRED_FEATURE = FULFILLMENT_CRITERION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Feature Required</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_REQUIRED_FEATURE_COUNT = FULFILLMENT_CRITERION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.quamoco.adaptation.model.status.impl.AdaptationAttributesImpl <em>Adaptation Attributes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.adaptation.model.status.impl.AdaptationAttributesImpl
	 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getAdaptationAttributes()
	 * @generated
	 */
	int ADAPTATION_ATTRIBUTES = 6;

	/**
	 * The feature id for the '<em><b>Adaptation Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_ATTRIBUTES__ADAPTATION_ID = QmPackage.ANNOTATION_BASE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Adaptation Attributes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_ATTRIBUTES_FEATURE_COUNT = QmPackage.ANNOTATION_BASE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.quamoco.adaptation.model.status.impl.QmAdaptationAttributesImpl <em>Qm Adaptation Attributes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.adaptation.model.status.impl.QmAdaptationAttributesImpl
	 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getQmAdaptationAttributes()
	 * @generated
	 */
	int QM_ADAPTATION_ATTRIBUTES = 7;

	/**
	 * The feature id for the '<em><b>Adaptation Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QM_ADAPTATION_ATTRIBUTES__ADAPTATION_ID = ADAPTATION_ATTRIBUTES__ADAPTATION_ID;

	/**
	 * The feature id for the '<em><b>Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QM_ADAPTATION_ATTRIBUTES__OBJECT = ADAPTATION_ATTRIBUTES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Viewpoint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QM_ADAPTATION_ATTRIBUTES__VIEWPOINT = ADAPTATION_ATTRIBUTES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Quality Focus</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QM_ADAPTATION_ATTRIBUTES__QUALITY_FOCUS = ADAPTATION_ATTRIBUTES_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Qm Adaptation Attributes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QM_ADAPTATION_ATTRIBUTES_FEATURE_COUNT = ADAPTATION_ATTRIBUTES_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link de.quamoco.adaptation.model.status.AdaptationSource <em>Adaptation Source</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.quamoco.adaptation.model.status.AdaptationSource
	 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getAdaptationSource()
	 * @generated
	 */
	int ADAPTATION_SOURCE = 8;

	/**
	 * The meta object id for the '<em>EObject</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EObject
	 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getEObject()
	 * @generated
	 */
	int EOBJECT = 9;

	/**
	 * The meta object id for the '<em>EStructural Feature</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EStructuralFeature
	 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getEStructuralFeature()
	 * @generated
	 */
	int ESTRUCTURAL_FEATURE = 10;


	/**
	 * Returns the meta object for class '{@link de.quamoco.adaptation.model.status.AdaptationStatusItem <em>Adaptation Status Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Adaptation Status Item</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationStatusItem
	 * @generated
	 */
	EClass getAdaptationStatusItem();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.adaptation.model.status.AdaptationStatusItem#getAffectedElementAdaptationId <em>Affected Element Adaptation Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Affected Element Adaptation Id</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationStatusItem#getAffectedElementAdaptationId()
	 * @see #getAdaptationStatusItem()
	 * @generated
	 */
	EAttribute getAdaptationStatusItem_AffectedElementAdaptationId();

	/**
	 * Returns the meta object for class '{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem <em>Adaptation History Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Adaptation History Item</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationHistoryItem
	 * @generated
	 */
	EClass getAdaptationHistoryItem();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem#getPerformedActionName <em>Performed Action Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Performed Action Name</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationHistoryItem#getPerformedActionName()
	 * @see #getAdaptationHistoryItem()
	 * @generated
	 */
	EAttribute getAdaptationHistoryItem_PerformedActionName();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem#getAffectedElementName <em>Affected Element Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Affected Element Name</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationHistoryItem#getAffectedElementName()
	 * @see #getAdaptationHistoryItem()
	 * @generated
	 */
	EAttribute getAdaptationHistoryItem_AffectedElementName();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem#getJustification <em>Justification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Justification</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationHistoryItem#getJustification()
	 * @see #getAdaptationHistoryItem()
	 * @generated
	 */
	EAttribute getAdaptationHistoryItem_Justification();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem#getAdaptationTasks <em>Adaptation Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Adaptation Tasks</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationHistoryItem#getAdaptationTasks()
	 * @see #getAdaptationHistoryItem()
	 * @generated
	 */
	EReference getAdaptationHistoryItem_AdaptationTasks();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationHistoryItem#getTimestamp()
	 * @see #getAdaptationHistoryItem()
	 * @generated
	 */
	EAttribute getAdaptationHistoryItem_Timestamp();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationHistoryItem#getSource()
	 * @see #getAdaptationHistoryItem()
	 * @generated
	 */
	EAttribute getAdaptationHistoryItem_Source();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem#getPerformedActionValue <em>Performed Action Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Performed Action Value</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationHistoryItem#getPerformedActionValue()
	 * @see #getAdaptationHistoryItem()
	 * @generated
	 */
	EAttribute getAdaptationHistoryItem_PerformedActionValue();

	/**
	 * Returns the meta object for class '{@link de.quamoco.adaptation.model.status.AdaptationTask <em>Adaptation Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Adaptation Task</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationTask
	 * @generated
	 */
	EClass getAdaptationTask();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.adaptation.model.status.AdaptationTask#getUserMessage <em>User Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Message</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationTask#getUserMessage()
	 * @see #getAdaptationTask()
	 * @generated
	 */
	EAttribute getAdaptationTask_UserMessage();

	/**
	 * Returns the meta object for the containment reference list '{@link de.quamoco.adaptation.model.status.AdaptationTask#getFulfillmentCriteria <em>Fulfillment Criteria</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fulfillment Criteria</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationTask#getFulfillmentCriteria()
	 * @see #getAdaptationTask()
	 * @generated
	 */
	EReference getAdaptationTask_FulfillmentCriteria();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.adaptation.model.status.AdaptationTask#getAffectedElement <em>Affected Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Affected Element</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationTask#getAffectedElement()
	 * @see #getAdaptationTask()
	 * @generated
	 */
	EAttribute getAdaptationTask_AffectedElement();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.adaptation.model.status.AdaptationTask#isAutoDelete <em>Auto Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Auto Delete</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationTask#isAutoDelete()
	 * @see #getAdaptationTask()
	 * @generated
	 */
	EAttribute getAdaptationTask_AutoDelete();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.adaptation.model.status.AdaptationTask#getTaskId <em>Task Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Task Id</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationTask#getTaskId()
	 * @see #getAdaptationTask()
	 * @generated
	 */
	EAttribute getAdaptationTask_TaskId();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.adaptation.model.status.AdaptationTask#isIgnoredByUser <em>Ignored By User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ignored By User</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationTask#isIgnoredByUser()
	 * @see #getAdaptationTask()
	 * @generated
	 */
	EAttribute getAdaptationTask_IgnoredByUser();

	/**
	 * Returns the meta object for class '{@link de.quamoco.adaptation.model.status.FulfillmentCriterion <em>Fulfillment Criterion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fulfillment Criterion</em>'.
	 * @see de.quamoco.adaptation.model.status.FulfillmentCriterion
	 * @generated
	 */
	EClass getFulfillmentCriterion();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.adaptation.model.status.FulfillmentCriterion#getJustification <em>Justification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Justification</em>'.
	 * @see de.quamoco.adaptation.model.status.FulfillmentCriterion#getJustification()
	 * @see #getFulfillmentCriterion()
	 * @generated
	 */
	EAttribute getFulfillmentCriterion_Justification();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.adaptation.model.status.FulfillmentCriterion#getUserMessage <em>User Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Message</em>'.
	 * @see de.quamoco.adaptation.model.status.FulfillmentCriterion#getUserMessage()
	 * @see #getFulfillmentCriterion()
	 * @generated
	 */
	EAttribute getFulfillmentCriterion_UserMessage();

	/**
	 * Returns the meta object for the container reference '{@link de.quamoco.adaptation.model.status.FulfillmentCriterion#getAdaptationTask <em>Adaptation Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Adaptation Task</em>'.
	 * @see de.quamoco.adaptation.model.status.FulfillmentCriterion#getAdaptationTask()
	 * @see #getFulfillmentCriterion()
	 * @generated
	 */
	EReference getFulfillmentCriterion_AdaptationTask();

	/**
	 * Returns the meta object for class '{@link de.quamoco.adaptation.model.status.UserMarkedCompleted <em>User Marked Completed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Marked Completed</em>'.
	 * @see de.quamoco.adaptation.model.status.UserMarkedCompleted
	 * @generated
	 */
	EClass getUserMarkedCompleted();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.adaptation.model.status.UserMarkedCompleted#isHasMarkedCompleted <em>Has Marked Completed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Marked Completed</em>'.
	 * @see de.quamoco.adaptation.model.status.UserMarkedCompleted#isHasMarkedCompleted()
	 * @see #getUserMarkedCompleted()
	 * @generated
	 */
	EAttribute getUserMarkedCompleted_HasMarkedCompleted();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.adaptation.model.status.UserMarkedCompleted#getLastUpdate <em>Last Update</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Update</em>'.
	 * @see de.quamoco.adaptation.model.status.UserMarkedCompleted#getLastUpdate()
	 * @see #getUserMarkedCompleted()
	 * @generated
	 */
	EAttribute getUserMarkedCompleted_LastUpdate();

	/**
	 * Returns the meta object for class '{@link de.quamoco.adaptation.model.status.FeatureRequired <em>Feature Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Required</em>'.
	 * @see de.quamoco.adaptation.model.status.FeatureRequired
	 * @generated
	 */
	EClass getFeatureRequired();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.adaptation.model.status.FeatureRequired#getRequiredFeatureName <em>Required Feature Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Required Feature Name</em>'.
	 * @see de.quamoco.adaptation.model.status.FeatureRequired#getRequiredFeatureName()
	 * @see #getFeatureRequired()
	 * @generated
	 */
	EAttribute getFeatureRequired_RequiredFeatureName();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.adaptation.model.status.FeatureRequired#getRequiredFeature <em>Required Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Required Feature</em>'.
	 * @see de.quamoco.adaptation.model.status.FeatureRequired#getRequiredFeature()
	 * @see #getFeatureRequired()
	 * @generated
	 */
	EAttribute getFeatureRequired_RequiredFeature();

	/**
	 * Returns the meta object for class '{@link de.quamoco.adaptation.model.status.AdaptationAttributes <em>Adaptation Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Adaptation Attributes</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationAttributes
	 * @generated
	 */
	EClass getAdaptationAttributes();

	/**
	 * Returns the meta object for the attribute '{@link de.quamoco.adaptation.model.status.AdaptationAttributes#getAdaptationId <em>Adaptation Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Adaptation Id</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationAttributes#getAdaptationId()
	 * @see #getAdaptationAttributes()
	 * @generated
	 */
	EAttribute getAdaptationAttributes_AdaptationId();

	/**
	 * Returns the meta object for class '{@link de.quamoco.adaptation.model.status.QmAdaptationAttributes <em>Qm Adaptation Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Qm Adaptation Attributes</em>'.
	 * @see de.quamoco.adaptation.model.status.QmAdaptationAttributes
	 * @generated
	 */
	EClass getQmAdaptationAttributes();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.adaptation.model.status.QmAdaptationAttributes#getObject <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Object</em>'.
	 * @see de.quamoco.adaptation.model.status.QmAdaptationAttributes#getObject()
	 * @see #getQmAdaptationAttributes()
	 * @generated
	 */
	EReference getQmAdaptationAttributes_Object();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.adaptation.model.status.QmAdaptationAttributes#getViewpoint <em>Viewpoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Viewpoint</em>'.
	 * @see de.quamoco.adaptation.model.status.QmAdaptationAttributes#getViewpoint()
	 * @see #getQmAdaptationAttributes()
	 * @generated
	 */
	EReference getQmAdaptationAttributes_Viewpoint();

	/**
	 * Returns the meta object for the reference '{@link de.quamoco.adaptation.model.status.QmAdaptationAttributes#getQualityFocus <em>Quality Focus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Quality Focus</em>'.
	 * @see de.quamoco.adaptation.model.status.QmAdaptationAttributes#getQualityFocus()
	 * @see #getQmAdaptationAttributes()
	 * @generated
	 */
	EReference getQmAdaptationAttributes_QualityFocus();

	/**
	 * Returns the meta object for enum '{@link de.quamoco.adaptation.model.status.AdaptationSource <em>Adaptation Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Adaptation Source</em>'.
	 * @see de.quamoco.adaptation.model.status.AdaptationSource
	 * @generated
	 */
	EEnum getAdaptationSource();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.ecore.EObject <em>EObject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>EObject</em>'.
	 * @see org.eclipse.emf.ecore.EObject
	 * @model instanceClass="org.eclipse.emf.ecore.EObject"
	 *        extendedMetaData="name='org.eclipse.emf.ecore.EObject'"
	 * @generated
	 */
	EDataType getEObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.ecore.EStructuralFeature <em>EStructural Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>EStructural Feature</em>'.
	 * @see org.eclipse.emf.ecore.EStructuralFeature
	 * @model instanceClass="org.eclipse.emf.ecore.EStructuralFeature"
	 *        extendedMetaData="name='org.eclipse.emf.ecore.EStructuralFeature'"
	 * @generated
	 */
	EDataType getEStructuralFeature();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	StatusFactory getStatusFactory();

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
		 * The meta object literal for the '{@link de.quamoco.adaptation.model.status.impl.AdaptationStatusItemImpl <em>Adaptation Status Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.adaptation.model.status.impl.AdaptationStatusItemImpl
		 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getAdaptationStatusItem()
		 * @generated
		 */
		EClass ADAPTATION_STATUS_ITEM = eINSTANCE.getAdaptationStatusItem();

		/**
		 * The meta object literal for the '<em><b>Affected Element Adaptation Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_STATUS_ITEM__AFFECTED_ELEMENT_ADAPTATION_ID = eINSTANCE.getAdaptationStatusItem_AffectedElementAdaptationId();

		/**
		 * The meta object literal for the '{@link de.quamoco.adaptation.model.status.impl.AdaptationHistoryItemImpl <em>Adaptation History Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.adaptation.model.status.impl.AdaptationHistoryItemImpl
		 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getAdaptationHistoryItem()
		 * @generated
		 */
		EClass ADAPTATION_HISTORY_ITEM = eINSTANCE.getAdaptationHistoryItem();

		/**
		 * The meta object literal for the '<em><b>Performed Action Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_HISTORY_ITEM__PERFORMED_ACTION_NAME = eINSTANCE.getAdaptationHistoryItem_PerformedActionName();

		/**
		 * The meta object literal for the '<em><b>Affected Element Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_HISTORY_ITEM__AFFECTED_ELEMENT_NAME = eINSTANCE.getAdaptationHistoryItem_AffectedElementName();

		/**
		 * The meta object literal for the '<em><b>Justification</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_HISTORY_ITEM__JUSTIFICATION = eINSTANCE.getAdaptationHistoryItem_Justification();

		/**
		 * The meta object literal for the '<em><b>Adaptation Tasks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADAPTATION_HISTORY_ITEM__ADAPTATION_TASKS = eINSTANCE.getAdaptationHistoryItem_AdaptationTasks();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_HISTORY_ITEM__TIMESTAMP = eINSTANCE.getAdaptationHistoryItem_Timestamp();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_HISTORY_ITEM__SOURCE = eINSTANCE.getAdaptationHistoryItem_Source();

		/**
		 * The meta object literal for the '<em><b>Performed Action Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_HISTORY_ITEM__PERFORMED_ACTION_VALUE = eINSTANCE.getAdaptationHistoryItem_PerformedActionValue();

		/**
		 * The meta object literal for the '{@link de.quamoco.adaptation.model.status.impl.AdaptationTaskImpl <em>Adaptation Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.adaptation.model.status.impl.AdaptationTaskImpl
		 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getAdaptationTask()
		 * @generated
		 */
		EClass ADAPTATION_TASK = eINSTANCE.getAdaptationTask();

		/**
		 * The meta object literal for the '<em><b>User Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_TASK__USER_MESSAGE = eINSTANCE.getAdaptationTask_UserMessage();

		/**
		 * The meta object literal for the '<em><b>Fulfillment Criteria</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADAPTATION_TASK__FULFILLMENT_CRITERIA = eINSTANCE.getAdaptationTask_FulfillmentCriteria();

		/**
		 * The meta object literal for the '<em><b>Affected Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_TASK__AFFECTED_ELEMENT = eINSTANCE.getAdaptationTask_AffectedElement();

		/**
		 * The meta object literal for the '<em><b>Auto Delete</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_TASK__AUTO_DELETE = eINSTANCE.getAdaptationTask_AutoDelete();

		/**
		 * The meta object literal for the '<em><b>Task Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_TASK__TASK_ID = eINSTANCE.getAdaptationTask_TaskId();

		/**
		 * The meta object literal for the '<em><b>Ignored By User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_TASK__IGNORED_BY_USER = eINSTANCE.getAdaptationTask_IgnoredByUser();

		/**
		 * The meta object literal for the '{@link de.quamoco.adaptation.model.status.impl.FulfillmentCriterionImpl <em>Fulfillment Criterion</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.adaptation.model.status.impl.FulfillmentCriterionImpl
		 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getFulfillmentCriterion()
		 * @generated
		 */
		EClass FULFILLMENT_CRITERION = eINSTANCE.getFulfillmentCriterion();

		/**
		 * The meta object literal for the '<em><b>Justification</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FULFILLMENT_CRITERION__JUSTIFICATION = eINSTANCE.getFulfillmentCriterion_Justification();

		/**
		 * The meta object literal for the '<em><b>User Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FULFILLMENT_CRITERION__USER_MESSAGE = eINSTANCE.getFulfillmentCriterion_UserMessage();

		/**
		 * The meta object literal for the '<em><b>Adaptation Task</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FULFILLMENT_CRITERION__ADAPTATION_TASK = eINSTANCE.getFulfillmentCriterion_AdaptationTask();

		/**
		 * The meta object literal for the '{@link de.quamoco.adaptation.model.status.impl.UserMarkedCompletedImpl <em>User Marked Completed</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.adaptation.model.status.impl.UserMarkedCompletedImpl
		 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getUserMarkedCompleted()
		 * @generated
		 */
		EClass USER_MARKED_COMPLETED = eINSTANCE.getUserMarkedCompleted();

		/**
		 * The meta object literal for the '<em><b>Has Marked Completed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_MARKED_COMPLETED__HAS_MARKED_COMPLETED = eINSTANCE.getUserMarkedCompleted_HasMarkedCompleted();

		/**
		 * The meta object literal for the '<em><b>Last Update</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_MARKED_COMPLETED__LAST_UPDATE = eINSTANCE.getUserMarkedCompleted_LastUpdate();

		/**
		 * The meta object literal for the '{@link de.quamoco.adaptation.model.status.impl.FeatureRequiredImpl <em>Feature Required</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.adaptation.model.status.impl.FeatureRequiredImpl
		 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getFeatureRequired()
		 * @generated
		 */
		EClass FEATURE_REQUIRED = eINSTANCE.getFeatureRequired();

		/**
		 * The meta object literal for the '<em><b>Required Feature Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE_REQUIRED__REQUIRED_FEATURE_NAME = eINSTANCE.getFeatureRequired_RequiredFeatureName();

		/**
		 * The meta object literal for the '<em><b>Required Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE_REQUIRED__REQUIRED_FEATURE = eINSTANCE.getFeatureRequired_RequiredFeature();

		/**
		 * The meta object literal for the '{@link de.quamoco.adaptation.model.status.impl.AdaptationAttributesImpl <em>Adaptation Attributes</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.adaptation.model.status.impl.AdaptationAttributesImpl
		 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getAdaptationAttributes()
		 * @generated
		 */
		EClass ADAPTATION_ATTRIBUTES = eINSTANCE.getAdaptationAttributes();

		/**
		 * The meta object literal for the '<em><b>Adaptation Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADAPTATION_ATTRIBUTES__ADAPTATION_ID = eINSTANCE.getAdaptationAttributes_AdaptationId();

		/**
		 * The meta object literal for the '{@link de.quamoco.adaptation.model.status.impl.QmAdaptationAttributesImpl <em>Qm Adaptation Attributes</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.adaptation.model.status.impl.QmAdaptationAttributesImpl
		 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getQmAdaptationAttributes()
		 * @generated
		 */
		EClass QM_ADAPTATION_ATTRIBUTES = eINSTANCE.getQmAdaptationAttributes();

		/**
		 * The meta object literal for the '<em><b>Object</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QM_ADAPTATION_ATTRIBUTES__OBJECT = eINSTANCE.getQmAdaptationAttributes_Object();

		/**
		 * The meta object literal for the '<em><b>Viewpoint</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QM_ADAPTATION_ATTRIBUTES__VIEWPOINT = eINSTANCE.getQmAdaptationAttributes_Viewpoint();

		/**
		 * The meta object literal for the '<em><b>Quality Focus</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QM_ADAPTATION_ATTRIBUTES__QUALITY_FOCUS = eINSTANCE.getQmAdaptationAttributes_QualityFocus();

		/**
		 * The meta object literal for the '{@link de.quamoco.adaptation.model.status.AdaptationSource <em>Adaptation Source</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.quamoco.adaptation.model.status.AdaptationSource
		 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getAdaptationSource()
		 * @generated
		 */
		EEnum ADAPTATION_SOURCE = eINSTANCE.getAdaptationSource();

		/**
		 * The meta object literal for the '<em>EObject</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.ecore.EObject
		 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getEObject()
		 * @generated
		 */
		EDataType EOBJECT = eINSTANCE.getEObject();

		/**
		 * The meta object literal for the '<em>EStructural Feature</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.ecore.EStructuralFeature
		 * @see de.quamoco.adaptation.model.status.impl.StatusPackageImpl#getEStructuralFeature()
		 * @generated
		 */
		EDataType ESTRUCTURAL_FEATURE = eINSTANCE.getEStructuralFeature();

	}

} //StatusPackage
