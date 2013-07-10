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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.model.status.FulfillmentCriterion;
import de.quamoco.adaptation.model.status.StatusPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Adaptation Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.AdaptationTaskImpl#getUserMessage <em>User Message</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.AdaptationTaskImpl#getFulfillmentCriteria <em>Fulfillment Criteria</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.AdaptationTaskImpl#getAffectedElement <em>Affected Element</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.AdaptationTaskImpl#isAutoDelete <em>Auto Delete</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.AdaptationTaskImpl#getTaskId <em>Task Id</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.AdaptationTaskImpl#isIgnoredByUser <em>Ignored By User</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AdaptationTaskImpl extends AdaptationStatusItemImpl implements AdaptationTask {
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
	 * The cached value of the '{@link #getFulfillmentCriteria() <em>Fulfillment Criteria</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFulfillmentCriteria()
	 * @generated
	 * @ordered
	 */
	protected EList<FulfillmentCriterion> fulfillmentCriteria;

	/**
	 * The default value of the '{@link #getAffectedElement() <em>Affected Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAffectedElement()
	 * @generated
	 * @ordered
	 */
	protected static final EObject AFFECTED_ELEMENT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isAutoDelete() <em>Auto Delete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutoDelete()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AUTO_DELETE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAutoDelete() <em>Auto Delete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutoDelete()
	 * @generated
	 * @ordered
	 */
	protected boolean autoDelete = AUTO_DELETE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTaskId() <em>Task Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaskId()
	 * @generated
	 * @ordered
	 */
	protected static final String TASK_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTaskId() <em>Task Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaskId()
	 * @generated
	 * @ordered
	 */
	protected String taskId = TASK_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #isIgnoredByUser() <em>Ignored By User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIgnoredByUser()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IGNORED_BY_USER_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIgnoredByUser() <em>Ignored By User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIgnoredByUser()
	 * @generated
	 * @ordered
	 */
	protected boolean ignoredByUser = IGNORED_BY_USER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdaptationTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatusPackage.Literals.ADAPTATION_TASK;
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
			eNotify(new ENotificationImpl(this, Notification.SET, StatusPackage.ADAPTATION_TASK__USER_MESSAGE, oldUserMessage, userMessage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FulfillmentCriterion> getFulfillmentCriteria() {
		if (fulfillmentCriteria == null) {
			fulfillmentCriteria = new EObjectContainmentWithInverseEList<FulfillmentCriterion>(FulfillmentCriterion.class, this, StatusPackage.ADAPTATION_TASK__FULFILLMENT_CRITERIA, StatusPackage.FULFILLMENT_CRITERION__ADAPTATION_TASK);
		}
		return fulfillmentCriteria;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EObject getAffectedElement() {
		return eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAutoDelete() {
		return autoDelete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAutoDelete(boolean newAutoDelete) {
		boolean oldAutoDelete = autoDelete;
		autoDelete = newAutoDelete;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatusPackage.ADAPTATION_TASK__AUTO_DELETE, oldAutoDelete, autoDelete));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTaskId(String newTaskId) {
		String oldTaskId = taskId;
		taskId = newTaskId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatusPackage.ADAPTATION_TASK__TASK_ID, oldTaskId, taskId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIgnoredByUser() {
		return ignoredByUser;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIgnoredByUser(boolean newIgnoredByUser) {
		boolean oldIgnoredByUser = ignoredByUser;
		ignoredByUser = newIgnoredByUser;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatusPackage.ADAPTATION_TASK__IGNORED_BY_USER, oldIgnoredByUser, ignoredByUser));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isCompleted() {
		// Tasks ignored by the user are considered to be completed
		if (ignoredByUser) {
			return true;
		}
		// Check if at least one fulfillment criterion is fulfilled
		for (FulfillmentCriterion criterion : getFulfillmentCriteria()) {
			if (!criterion.isFulfilled()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatusPackage.ADAPTATION_TASK__FULFILLMENT_CRITERIA:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFulfillmentCriteria()).basicAdd(otherEnd, msgs);
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
			case StatusPackage.ADAPTATION_TASK__FULFILLMENT_CRITERIA:
				return ((InternalEList<?>)getFulfillmentCriteria()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StatusPackage.ADAPTATION_TASK__USER_MESSAGE:
				return getUserMessage();
			case StatusPackage.ADAPTATION_TASK__FULFILLMENT_CRITERIA:
				return getFulfillmentCriteria();
			case StatusPackage.ADAPTATION_TASK__AFFECTED_ELEMENT:
				return getAffectedElement();
			case StatusPackage.ADAPTATION_TASK__AUTO_DELETE:
				return isAutoDelete();
			case StatusPackage.ADAPTATION_TASK__TASK_ID:
				return getTaskId();
			case StatusPackage.ADAPTATION_TASK__IGNORED_BY_USER:
				return isIgnoredByUser();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StatusPackage.ADAPTATION_TASK__USER_MESSAGE:
				setUserMessage((String)newValue);
				return;
			case StatusPackage.ADAPTATION_TASK__FULFILLMENT_CRITERIA:
				getFulfillmentCriteria().clear();
				getFulfillmentCriteria().addAll((Collection<? extends FulfillmentCriterion>)newValue);
				return;
			case StatusPackage.ADAPTATION_TASK__AUTO_DELETE:
				setAutoDelete((Boolean)newValue);
				return;
			case StatusPackage.ADAPTATION_TASK__TASK_ID:
				setTaskId((String)newValue);
				return;
			case StatusPackage.ADAPTATION_TASK__IGNORED_BY_USER:
				setIgnoredByUser((Boolean)newValue);
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
			case StatusPackage.ADAPTATION_TASK__USER_MESSAGE:
				setUserMessage(USER_MESSAGE_EDEFAULT);
				return;
			case StatusPackage.ADAPTATION_TASK__FULFILLMENT_CRITERIA:
				getFulfillmentCriteria().clear();
				return;
			case StatusPackage.ADAPTATION_TASK__AUTO_DELETE:
				setAutoDelete(AUTO_DELETE_EDEFAULT);
				return;
			case StatusPackage.ADAPTATION_TASK__TASK_ID:
				setTaskId(TASK_ID_EDEFAULT);
				return;
			case StatusPackage.ADAPTATION_TASK__IGNORED_BY_USER:
				setIgnoredByUser(IGNORED_BY_USER_EDEFAULT);
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
			case StatusPackage.ADAPTATION_TASK__USER_MESSAGE:
				return USER_MESSAGE_EDEFAULT == null ? userMessage != null : !USER_MESSAGE_EDEFAULT.equals(userMessage);
			case StatusPackage.ADAPTATION_TASK__FULFILLMENT_CRITERIA:
				return fulfillmentCriteria != null && !fulfillmentCriteria.isEmpty();
			case StatusPackage.ADAPTATION_TASK__AFFECTED_ELEMENT:
				return AFFECTED_ELEMENT_EDEFAULT == null ? getAffectedElement() != null : !AFFECTED_ELEMENT_EDEFAULT.equals(getAffectedElement());
			case StatusPackage.ADAPTATION_TASK__AUTO_DELETE:
				return autoDelete != AUTO_DELETE_EDEFAULT;
			case StatusPackage.ADAPTATION_TASK__TASK_ID:
				return TASK_ID_EDEFAULT == null ? taskId != null : !TASK_ID_EDEFAULT.equals(taskId);
			case StatusPackage.ADAPTATION_TASK__IGNORED_BY_USER:
				return ignoredByUser != IGNORED_BY_USER_EDEFAULT;
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
		result.append(" (userMessage: ");
		result.append(userMessage);
		result.append(", autoDelete: ");
		result.append(autoDelete);
		result.append(", taskId: ");
		result.append(taskId);
		result.append(", ignoredByUser: ");
		result.append(ignoredByUser);
		result.append(')');
		return result.toString();
	}

} //AdaptationTaskImpl
