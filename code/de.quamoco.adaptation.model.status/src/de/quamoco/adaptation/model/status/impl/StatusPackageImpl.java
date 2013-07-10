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
package de.quamoco.adaptation.model.status.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import de.quamoco.adaptation.model.status.AdaptationAttributes;
import de.quamoco.adaptation.model.status.AdaptationHistoryItem;
import de.quamoco.adaptation.model.status.AdaptationSource;
import de.quamoco.adaptation.model.status.AdaptationStatusItem;
import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.model.status.FeatureRequired;
import de.quamoco.adaptation.model.status.FulfillmentCriterion;
import de.quamoco.adaptation.model.status.QmAdaptationAttributes;
import de.quamoco.adaptation.model.status.StatusFactory;
import de.quamoco.adaptation.model.status.StatusPackage;
import de.quamoco.adaptation.model.status.UserMarkedCompleted;
import de.quamoco.qm.QmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StatusPackageImpl extends EPackageImpl implements StatusPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adaptationStatusItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adaptationHistoryItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adaptationTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fulfillmentCriterionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userMarkedCompletedEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featureRequiredEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adaptationAttributesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass qmAdaptationAttributesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum adaptationSourceEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType eObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType eStructuralFeatureEDataType = null;

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
	 * @see de.quamoco.adaptation.model.status.StatusPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private StatusPackageImpl() {
		super(eNS_URI, StatusFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link StatusPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static StatusPackage init() {
		if (isInited) return (StatusPackage)EPackage.Registry.INSTANCE.getEPackage(StatusPackage.eNS_URI);

		// Obtain or create and register package
		StatusPackageImpl theStatusPackage = (StatusPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof StatusPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new StatusPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();
		QmPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theStatusPackage.createPackageContents();

		// Initialize created meta-data
		theStatusPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theStatusPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(StatusPackage.eNS_URI, theStatusPackage);
		return theStatusPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAdaptationStatusItem() {
		return adaptationStatusItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdaptationStatusItem_AffectedElementAdaptationId() {
		return (EAttribute)adaptationStatusItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAdaptationHistoryItem() {
		return adaptationHistoryItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdaptationHistoryItem_PerformedActionName() {
		return (EAttribute)adaptationHistoryItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdaptationHistoryItem_AffectedElementName() {
		return (EAttribute)adaptationHistoryItemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdaptationHistoryItem_Justification() {
		return (EAttribute)adaptationHistoryItemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAdaptationHistoryItem_AdaptationTasks() {
		return (EReference)adaptationHistoryItemEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdaptationHistoryItem_Timestamp() {
		return (EAttribute)adaptationHistoryItemEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdaptationHistoryItem_Source() {
		return (EAttribute)adaptationHistoryItemEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdaptationHistoryItem_PerformedActionValue() {
		return (EAttribute)adaptationHistoryItemEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAdaptationTask() {
		return adaptationTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdaptationTask_UserMessage() {
		return (EAttribute)adaptationTaskEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAdaptationTask_FulfillmentCriteria() {
		return (EReference)adaptationTaskEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdaptationTask_AffectedElement() {
		return (EAttribute)adaptationTaskEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdaptationTask_AutoDelete() {
		return (EAttribute)adaptationTaskEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdaptationTask_TaskId() {
		return (EAttribute)adaptationTaskEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdaptationTask_IgnoredByUser() {
		return (EAttribute)adaptationTaskEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFulfillmentCriterion() {
		return fulfillmentCriterionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFulfillmentCriterion_Justification() {
		return (EAttribute)fulfillmentCriterionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFulfillmentCriterion_UserMessage() {
		return (EAttribute)fulfillmentCriterionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFulfillmentCriterion_AdaptationTask() {
		return (EReference)fulfillmentCriterionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUserMarkedCompleted() {
		return userMarkedCompletedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserMarkedCompleted_HasMarkedCompleted() {
		return (EAttribute)userMarkedCompletedEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserMarkedCompleted_LastUpdate() {
		return (EAttribute)userMarkedCompletedEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFeatureRequired() {
		return featureRequiredEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFeatureRequired_RequiredFeatureName() {
		return (EAttribute)featureRequiredEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFeatureRequired_RequiredFeature() {
		return (EAttribute)featureRequiredEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAdaptationAttributes() {
		return adaptationAttributesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdaptationAttributes_AdaptationId() {
		return (EAttribute)adaptationAttributesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQmAdaptationAttributes() {
		return qmAdaptationAttributesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQmAdaptationAttributes_Object() {
		return (EReference)qmAdaptationAttributesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQmAdaptationAttributes_Viewpoint() {
		return (EReference)qmAdaptationAttributesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQmAdaptationAttributes_QualityFocus() {
		return (EReference)qmAdaptationAttributesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getAdaptationSource() {
		return adaptationSourceEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getEObject() {
		return eObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getEStructuralFeature() {
		return eStructuralFeatureEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatusFactory getStatusFactory() {
		return (StatusFactory)getEFactoryInstance();
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
		adaptationStatusItemEClass = createEClass(ADAPTATION_STATUS_ITEM);
		createEAttribute(adaptationStatusItemEClass, ADAPTATION_STATUS_ITEM__AFFECTED_ELEMENT_ADAPTATION_ID);

		adaptationHistoryItemEClass = createEClass(ADAPTATION_HISTORY_ITEM);
		createEAttribute(adaptationHistoryItemEClass, ADAPTATION_HISTORY_ITEM__PERFORMED_ACTION_NAME);
		createEAttribute(adaptationHistoryItemEClass, ADAPTATION_HISTORY_ITEM__AFFECTED_ELEMENT_NAME);
		createEAttribute(adaptationHistoryItemEClass, ADAPTATION_HISTORY_ITEM__JUSTIFICATION);
		createEReference(adaptationHistoryItemEClass, ADAPTATION_HISTORY_ITEM__ADAPTATION_TASKS);
		createEAttribute(adaptationHistoryItemEClass, ADAPTATION_HISTORY_ITEM__TIMESTAMP);
		createEAttribute(adaptationHistoryItemEClass, ADAPTATION_HISTORY_ITEM__SOURCE);
		createEAttribute(adaptationHistoryItemEClass, ADAPTATION_HISTORY_ITEM__PERFORMED_ACTION_VALUE);

		adaptationTaskEClass = createEClass(ADAPTATION_TASK);
		createEAttribute(adaptationTaskEClass, ADAPTATION_TASK__USER_MESSAGE);
		createEReference(adaptationTaskEClass, ADAPTATION_TASK__FULFILLMENT_CRITERIA);
		createEAttribute(adaptationTaskEClass, ADAPTATION_TASK__AFFECTED_ELEMENT);
		createEAttribute(adaptationTaskEClass, ADAPTATION_TASK__AUTO_DELETE);
		createEAttribute(adaptationTaskEClass, ADAPTATION_TASK__TASK_ID);
		createEAttribute(adaptationTaskEClass, ADAPTATION_TASK__IGNORED_BY_USER);

		fulfillmentCriterionEClass = createEClass(FULFILLMENT_CRITERION);
		createEAttribute(fulfillmentCriterionEClass, FULFILLMENT_CRITERION__JUSTIFICATION);
		createEAttribute(fulfillmentCriterionEClass, FULFILLMENT_CRITERION__USER_MESSAGE);
		createEReference(fulfillmentCriterionEClass, FULFILLMENT_CRITERION__ADAPTATION_TASK);

		userMarkedCompletedEClass = createEClass(USER_MARKED_COMPLETED);
		createEAttribute(userMarkedCompletedEClass, USER_MARKED_COMPLETED__HAS_MARKED_COMPLETED);
		createEAttribute(userMarkedCompletedEClass, USER_MARKED_COMPLETED__LAST_UPDATE);

		featureRequiredEClass = createEClass(FEATURE_REQUIRED);
		createEAttribute(featureRequiredEClass, FEATURE_REQUIRED__REQUIRED_FEATURE_NAME);
		createEAttribute(featureRequiredEClass, FEATURE_REQUIRED__REQUIRED_FEATURE);

		adaptationAttributesEClass = createEClass(ADAPTATION_ATTRIBUTES);
		createEAttribute(adaptationAttributesEClass, ADAPTATION_ATTRIBUTES__ADAPTATION_ID);

		qmAdaptationAttributesEClass = createEClass(QM_ADAPTATION_ATTRIBUTES);
		createEReference(qmAdaptationAttributesEClass, QM_ADAPTATION_ATTRIBUTES__OBJECT);
		createEReference(qmAdaptationAttributesEClass, QM_ADAPTATION_ATTRIBUTES__VIEWPOINT);
		createEReference(qmAdaptationAttributesEClass, QM_ADAPTATION_ATTRIBUTES__QUALITY_FOCUS);

		// Create enums
		adaptationSourceEEnum = createEEnum(ADAPTATION_SOURCE);

		// Create data types
		eObjectEDataType = createEDataType(EOBJECT);
		eStructuralFeatureEDataType = createEDataType(ESTRUCTURAL_FEATURE);
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
		QmPackage theQmPackage = (QmPackage)EPackage.Registry.INSTANCE.getEPackage(QmPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		adaptationStatusItemEClass.getESuperTypes().add(theQmPackage.getAnnotationBase());
		adaptationHistoryItemEClass.getESuperTypes().add(this.getAdaptationStatusItem());
		adaptationTaskEClass.getESuperTypes().add(this.getAdaptationStatusItem());
		userMarkedCompletedEClass.getESuperTypes().add(this.getFulfillmentCriterion());
		featureRequiredEClass.getESuperTypes().add(this.getFulfillmentCriterion());
		adaptationAttributesEClass.getESuperTypes().add(theQmPackage.getAnnotationBase());
		qmAdaptationAttributesEClass.getESuperTypes().add(this.getAdaptationAttributes());

		// Initialize classes and features; add operations and parameters
		initEClass(adaptationStatusItemEClass, AdaptationStatusItem.class, "AdaptationStatusItem", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAdaptationStatusItem_AffectedElementAdaptationId(), theEcorePackage.getEString(), "affectedElementAdaptationId", null, 0, 1, AdaptationStatusItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(adaptationHistoryItemEClass, AdaptationHistoryItem.class, "AdaptationHistoryItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAdaptationHistoryItem_PerformedActionName(), theEcorePackage.getEString(), "performedActionName", null, 1, 1, AdaptationHistoryItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAdaptationHistoryItem_AffectedElementName(), theEcorePackage.getEString(), "affectedElementName", null, 1, 1, AdaptationHistoryItem.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAdaptationHistoryItem_Justification(), theEcorePackage.getEString(), "justification", null, 0, 1, AdaptationHistoryItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAdaptationHistoryItem_AdaptationTasks(), this.getAdaptationTask(), null, "adaptationTasks", null, 0, -1, AdaptationHistoryItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAdaptationHistoryItem_Timestamp(), theEcorePackage.getEDate(), "timestamp", null, 1, 1, AdaptationHistoryItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAdaptationHistoryItem_Source(), this.getAdaptationSource(), "source", null, 1, 1, AdaptationHistoryItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdaptationHistoryItem_PerformedActionValue(), theEcorePackage.getEString(), "performedActionValue", null, 1, 1, AdaptationHistoryItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(adaptationTaskEClass, AdaptationTask.class, "AdaptationTask", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAdaptationTask_UserMessage(), theEcorePackage.getEString(), "userMessage", null, 1, 1, AdaptationTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAdaptationTask_FulfillmentCriteria(), this.getFulfillmentCriterion(), this.getFulfillmentCriterion_AdaptationTask(), "fulfillmentCriteria", null, 1, -1, AdaptationTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAdaptationTask_AffectedElement(), this.getEObject(), "affectedElement", null, 1, 1, AdaptationTask.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAdaptationTask_AutoDelete(), theEcorePackage.getEBoolean(), "autoDelete", null, 1, 1, AdaptationTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAdaptationTask_TaskId(), theEcorePackage.getEString(), "taskId", null, 0, 1, AdaptationTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdaptationTask_IgnoredByUser(), theEcorePackage.getEBoolean(), "ignoredByUser", null, 0, 1, AdaptationTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(adaptationTaskEClass, theEcorePackage.getEBoolean(), "isCompleted", 1, 1, IS_UNIQUE, !IS_ORDERED);

		initEClass(fulfillmentCriterionEClass, FulfillmentCriterion.class, "FulfillmentCriterion", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFulfillmentCriterion_Justification(), theEcorePackage.getEString(), "justification", null, 0, 1, FulfillmentCriterion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getFulfillmentCriterion_UserMessage(), theEcorePackage.getEString(), "userMessage", null, 0, 1, FulfillmentCriterion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getFulfillmentCriterion_AdaptationTask(), this.getAdaptationTask(), this.getAdaptationTask_FulfillmentCriteria(), "adaptationTask", null, 1, 1, FulfillmentCriterion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		addEOperation(fulfillmentCriterionEClass, theEcorePackage.getEBoolean(), "isFulfilled", 1, 1, IS_UNIQUE, !IS_ORDERED);

		initEClass(userMarkedCompletedEClass, UserMarkedCompleted.class, "UserMarkedCompleted", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUserMarkedCompleted_HasMarkedCompleted(), theEcorePackage.getEBoolean(), "hasMarkedCompleted", null, 1, 1, UserMarkedCompleted.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getUserMarkedCompleted_LastUpdate(), theEcorePackage.getEDate(), "lastUpdate", null, 1, 1, UserMarkedCompleted.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(featureRequiredEClass, FeatureRequired.class, "FeatureRequired", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFeatureRequired_RequiredFeatureName(), theEcorePackage.getEString(), "requiredFeatureName", null, 1, 1, FeatureRequired.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getFeatureRequired_RequiredFeature(), this.getEStructuralFeature(), "requiredFeature", null, 1, 1, FeatureRequired.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);

		initEClass(adaptationAttributesEClass, AdaptationAttributes.class, "AdaptationAttributes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAdaptationAttributes_AdaptationId(), theEcorePackage.getEString(), "adaptationId", null, 0, 1, AdaptationAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(qmAdaptationAttributesEClass, QmAdaptationAttributes.class, "QmAdaptationAttributes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getQmAdaptationAttributes_Object(), theQmPackage.getEntity(), null, "object", null, 0, 1, QmAdaptationAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getQmAdaptationAttributes_Viewpoint(), theQmPackage.getFactor(), null, "viewpoint", null, 0, 1, QmAdaptationAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getQmAdaptationAttributes_QualityFocus(), theQmPackage.getFactor(), null, "qualityFocus", null, 0, 1, QmAdaptationAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(adaptationSourceEEnum, AdaptationSource.class, "AdaptationSource");
		addEEnumLiteral(adaptationSourceEEnum, AdaptationSource.UNSPECIFIED);
		addEEnumLiteral(adaptationSourceEEnum, AdaptationSource.EDITOR);
		addEEnumLiteral(adaptationSourceEEnum, AdaptationSource.WIZARD);

		// Initialize data types
		initEDataType(eObjectEDataType, EObject.class, "EObject", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(eStructuralFeatureEDataType, EStructuralFeature.class, "EStructuralFeature", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";			
		addAnnotation
		  (eObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "org.eclipse.emf.ecore.EObject"
		   });		
		addAnnotation
		  (eStructuralFeatureEDataType, 
		   source, 
		   new String[] {
			 "name", "org.eclipse.emf.ecore.EStructuralFeature"
		   });
	}

} //StatusPackageImpl
