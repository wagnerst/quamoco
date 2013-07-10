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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.model.status.FulfillmentCriterion;
import de.quamoco.adaptation.model.status.StatusPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fulfillment Criterion</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.FulfillmentCriterionImpl#getJustification <em>Justification</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.FulfillmentCriterionImpl#getUserMessage <em>User Message</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.FulfillmentCriterionImpl#getAdaptationTask <em>Adaptation Task</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class FulfillmentCriterionImpl extends EObjectImpl implements FulfillmentCriterion {
	/**
	 * The default value of the '{@link #getJustification() <em>Justification</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJustification()
	 * @generated
	 * @ordered
	 */
	protected static final String JUSTIFICATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJustification() <em>Justification</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJustification()
	 * @generated
	 * @ordered
	 */
	protected String justification = JUSTIFICATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getUserMessage() <em>User Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserMessage()
	 * @generated
	 * @ordered
	 */
	protected static final String USER_MESSAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUserMessage() <em>User Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserMessage()
	 * @generated
	 * @ordered
	 */
	protected String userMessage = USER_MESSAGE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FulfillmentCriterionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatusPackage.Literals.FULFILLMENT_CRITERION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getJustification() {
		return justification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJustification(String newJustification) {
		String oldJustification = justification;
		justification = newJustification;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatusPackage.FULFILLMENT_CRITERION__JUSTIFICATION, oldJustification, justification));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUserMessage() {
		return userMessage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserMessage(String newUserMessage) {
		String oldUserMessage = userMessage;
		userMessage = newUserMessage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatusPackage.FULFILLMENT_CRITERION__USER_MESSAGE, oldUserMessage, userMessage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdaptationTask getAdaptationTask() {
		if (eContainerFeatureID() != StatusPackage.FULFILLMENT_CRITERION__ADAPTATION_TASK) return null;
		return (AdaptationTask)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAdaptationTask(AdaptationTask newAdaptationTask, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newAdaptationTask, StatusPackage.FULFILLMENT_CRITERION__ADAPTATION_TASK, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAdaptationTask(AdaptationTask newAdaptationTask) {
		if (newAdaptationTask != eInternalContainer() || (eContainerFeatureID() != StatusPackage.FULFILLMENT_CRITERION__ADAPTATION_TASK && newAdaptationTask != null)) {
			if (EcoreUtil.isAncestor(this, newAdaptationTask))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAdaptationTask != null)
				msgs = ((InternalEObject)newAdaptationTask).eInverseAdd(this, StatusPackage.ADAPTATION_TASK__FULFILLMENT_CRITERIA, AdaptationTask.class, msgs);
			msgs = basicSetAdaptationTask(newAdaptationTask, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatusPackage.FULFILLMENT_CRITERION__ADAPTATION_TASK, newAdaptationTask, newAdaptationTask));
	}

	/**
	 * <!-- begin-user-doc -->
	 * Has to be implemented by subclass!
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public abstract boolean isFulfilled();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatusPackage.FULFILLMENT_CRITERION__ADAPTATION_TASK:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetAdaptationTask((AdaptationTask)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatusPackage.FULFILLMENT_CRITERION__ADAPTATION_TASK:
				return basicSetAdaptationTask(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case StatusPackage.FULFILLMENT_CRITERION__ADAPTATION_TASK:
				return eInternalContainer().eInverseRemove(this, StatusPackage.ADAPTATION_TASK__FULFILLMENT_CRITERIA, AdaptationTask.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StatusPackage.FULFILLMENT_CRITERION__JUSTIFICATION:
				return getJustification();
			case StatusPackage.FULFILLMENT_CRITERION__USER_MESSAGE:
				return getUserMessage();
			case StatusPackage.FULFILLMENT_CRITERION__ADAPTATION_TASK:
				return getAdaptationTask();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StatusPackage.FULFILLMENT_CRITERION__JUSTIFICATION:
				setJustification((String)newValue);
				return;
			case StatusPackage.FULFILLMENT_CRITERION__USER_MESSAGE:
				setUserMessage((String)newValue);
				return;
			case StatusPackage.FULFILLMENT_CRITERION__ADAPTATION_TASK:
				setAdaptationTask((AdaptationTask)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case StatusPackage.FULFILLMENT_CRITERION__JUSTIFICATION:
				setJustification(JUSTIFICATION_EDEFAULT);
				return;
			case StatusPackage.FULFILLMENT_CRITERION__USER_MESSAGE:
				setUserMessage(USER_MESSAGE_EDEFAULT);
				return;
			case StatusPackage.FULFILLMENT_CRITERION__ADAPTATION_TASK:
				setAdaptationTask((AdaptationTask)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case StatusPackage.FULFILLMENT_CRITERION__JUSTIFICATION:
				return JUSTIFICATION_EDEFAULT == null ? justification != null : !JUSTIFICATION_EDEFAULT.equals(justification);
			case StatusPackage.FULFILLMENT_CRITERION__USER_MESSAGE:
				return USER_MESSAGE_EDEFAULT == null ? userMessage != null : !USER_MESSAGE_EDEFAULT.equals(userMessage);
			case StatusPackage.FULFILLMENT_CRITERION__ADAPTATION_TASK:
				return getAdaptationTask() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (justification: ");
		result.append(justification);
		result.append(", userMessage: ");
		result.append(userMessage);
		result.append(')');
		return result.toString();
	}

} //FulfillmentCriterionImpl
