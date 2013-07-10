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
package de.quamoco.adaptation.model.status.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import de.quamoco.adaptation.model.status.AdaptationAttributes;
import de.quamoco.adaptation.model.status.AdaptationHistoryItem;
import de.quamoco.adaptation.model.status.AdaptationStatusItem;
import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.model.status.FeatureRequired;
import de.quamoco.adaptation.model.status.FulfillmentCriterion;
import de.quamoco.adaptation.model.status.QmAdaptationAttributes;
import de.quamoco.adaptation.model.status.StatusPackage;
import de.quamoco.adaptation.model.status.UserMarkedCompleted;
import de.quamoco.qm.AnnotationBase;

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
 * @see de.quamoco.adaptation.model.status.StatusPackage
 * @generated
 */
public class StatusSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static StatusPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatusSwitch() {
		if (modelPackage == null) {
			modelPackage = StatusPackage.eINSTANCE;
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
			case StatusPackage.ADAPTATION_STATUS_ITEM: {
				AdaptationStatusItem adaptationStatusItem = (AdaptationStatusItem)theEObject;
				T result = caseAdaptationStatusItem(adaptationStatusItem);
				if (result == null) result = caseAnnotationBase(adaptationStatusItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatusPackage.ADAPTATION_HISTORY_ITEM: {
				AdaptationHistoryItem adaptationHistoryItem = (AdaptationHistoryItem)theEObject;
				T result = caseAdaptationHistoryItem(adaptationHistoryItem);
				if (result == null) result = caseAdaptationStatusItem(adaptationHistoryItem);
				if (result == null) result = caseAnnotationBase(adaptationHistoryItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatusPackage.ADAPTATION_TASK: {
				AdaptationTask adaptationTask = (AdaptationTask)theEObject;
				T result = caseAdaptationTask(adaptationTask);
				if (result == null) result = caseAdaptationStatusItem(adaptationTask);
				if (result == null) result = caseAnnotationBase(adaptationTask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatusPackage.FULFILLMENT_CRITERION: {
				FulfillmentCriterion fulfillmentCriterion = (FulfillmentCriterion)theEObject;
				T result = caseFulfillmentCriterion(fulfillmentCriterion);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatusPackage.USER_MARKED_COMPLETED: {
				UserMarkedCompleted userMarkedCompleted = (UserMarkedCompleted)theEObject;
				T result = caseUserMarkedCompleted(userMarkedCompleted);
				if (result == null) result = caseFulfillmentCriterion(userMarkedCompleted);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatusPackage.FEATURE_REQUIRED: {
				FeatureRequired featureRequired = (FeatureRequired)theEObject;
				T result = caseFeatureRequired(featureRequired);
				if (result == null) result = caseFulfillmentCriterion(featureRequired);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatusPackage.ADAPTATION_ATTRIBUTES: {
				AdaptationAttributes adaptationAttributes = (AdaptationAttributes)theEObject;
				T result = caseAdaptationAttributes(adaptationAttributes);
				if (result == null) result = caseAnnotationBase(adaptationAttributes);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StatusPackage.QM_ADAPTATION_ATTRIBUTES: {
				QmAdaptationAttributes qmAdaptationAttributes = (QmAdaptationAttributes)theEObject;
				T result = caseQmAdaptationAttributes(qmAdaptationAttributes);
				if (result == null) result = caseAdaptationAttributes(qmAdaptationAttributes);
				if (result == null) result = caseAnnotationBase(qmAdaptationAttributes);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Adaptation Status Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Adaptation Status Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAdaptationStatusItem(AdaptationStatusItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Adaptation History Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Adaptation History Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAdaptationHistoryItem(AdaptationHistoryItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Adaptation Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Adaptation Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAdaptationTask(AdaptationTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fulfillment Criterion</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fulfillment Criterion</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFulfillmentCriterion(FulfillmentCriterion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Marked Completed</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Marked Completed</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserMarkedCompleted(UserMarkedCompleted object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature Required</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature Required</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFeatureRequired(FeatureRequired object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Adaptation Attributes</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Adaptation Attributes</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAdaptationAttributes(AdaptationAttributes object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Qm Adaptation Attributes</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Qm Adaptation Attributes</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQmAdaptationAttributes(QmAdaptationAttributes object) {
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

} //StatusSwitch
