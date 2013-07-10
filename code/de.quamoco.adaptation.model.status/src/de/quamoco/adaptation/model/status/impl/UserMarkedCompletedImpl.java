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

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.quamoco.adaptation.model.status.StatusPackage;
import de.quamoco.adaptation.model.status.UserMarkedCompleted;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Marked Completed</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.UserMarkedCompletedImpl#isHasMarkedCompleted <em>Has Marked Completed</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.UserMarkedCompletedImpl#getLastUpdate <em>Last Update</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserMarkedCompletedImpl extends FulfillmentCriterionImpl implements UserMarkedCompleted {
	/**
	 * The default value of the '{@link #isHasMarkedCompleted() <em>Has Marked Completed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasMarkedCompleted()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_MARKED_COMPLETED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHasMarkedCompleted() <em>Has Marked Completed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasMarkedCompleted()
	 * @generated
	 * @ordered
	 */
	protected boolean hasMarkedCompleted = HAS_MARKED_COMPLETED_EDEFAULT;

	/**
	 * The default value of the '{@link #getLastUpdate() <em>Last Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastUpdate()
	 * @generated
	 * @ordered
	 */
	protected static final Date LAST_UPDATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLastUpdate() <em>Last Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastUpdate()
	 * @generated
	 * @ordered
	 */
	protected Date lastUpdate = LAST_UPDATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserMarkedCompletedImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatusPackage.Literals.USER_MARKED_COMPLETED;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHasMarkedCompleted() {
		return hasMarkedCompleted;
	}

	/**
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasMarkedCompleted(boolean newHasMarkedCompleted) {
		boolean oldHasMarkedCompleted = hasMarkedCompleted;
		hasMarkedCompleted = newHasMarkedCompleted;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatusPackage.USER_MARKED_COMPLETED__HAS_MARKED_COMPLETED, oldHasMarkedCompleted, hasMarkedCompleted));
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLastUpdate(Date newLastUpdate) {
		Date oldLastUpdate = lastUpdate;
		lastUpdate = newLastUpdate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatusPackage.USER_MARKED_COMPLETED__LAST_UPDATE, oldLastUpdate, lastUpdate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StatusPackage.USER_MARKED_COMPLETED__HAS_MARKED_COMPLETED:
				return isHasMarkedCompleted();
			case StatusPackage.USER_MARKED_COMPLETED__LAST_UPDATE:
				return getLastUpdate();
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
			case StatusPackage.USER_MARKED_COMPLETED__HAS_MARKED_COMPLETED:
				setHasMarkedCompleted((Boolean)newValue);
				return;
			case StatusPackage.USER_MARKED_COMPLETED__LAST_UPDATE:
				setLastUpdate((Date)newValue);
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
			case StatusPackage.USER_MARKED_COMPLETED__HAS_MARKED_COMPLETED:
				setHasMarkedCompleted(HAS_MARKED_COMPLETED_EDEFAULT);
				return;
			case StatusPackage.USER_MARKED_COMPLETED__LAST_UPDATE:
				setLastUpdate(LAST_UPDATE_EDEFAULT);
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
			case StatusPackage.USER_MARKED_COMPLETED__HAS_MARKED_COMPLETED:
				return hasMarkedCompleted != HAS_MARKED_COMPLETED_EDEFAULT;
			case StatusPackage.USER_MARKED_COMPLETED__LAST_UPDATE:
				return LAST_UPDATE_EDEFAULT == null ? lastUpdate != null : !LAST_UPDATE_EDEFAULT.equals(lastUpdate);
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
		result.append(" (hasMarkedCompleted: ");
		result.append(hasMarkedCompleted);
		result.append(", lastUpdate: ");
		result.append(lastUpdate);
		result.append(')');
		return result.toString();
	}

	/**
	 * @generated NOT
	 */
	@Override
	public boolean isFulfilled() {
		return hasMarkedCompleted;
	}

} //UserMarkedCompletedImpl
