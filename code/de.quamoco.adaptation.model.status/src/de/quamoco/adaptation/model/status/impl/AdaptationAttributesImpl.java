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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.quamoco.adaptation.model.status.AdaptationAttributes;
import de.quamoco.adaptation.model.status.StatusPackage;
import de.quamoco.qm.impl.AnnotationBaseImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Adaptation Attributes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.AdaptationAttributesImpl#getAdaptationId <em>Adaptation Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AdaptationAttributesImpl extends AnnotationBaseImpl implements AdaptationAttributes {
	/**
	 * The default value of the '{@link #getAdaptationId() <em>Adaptation Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdaptationId()
	 * @generated
	 * @ordered
	 */
	protected static final String ADAPTATION_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAdaptationId() <em>Adaptation Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdaptationId()
	 * @generated
	 * @ordered
	 */
	protected String adaptationId = ADAPTATION_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdaptationAttributesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatusPackage.Literals.ADAPTATION_ATTRIBUTES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAdaptationId() {
		return adaptationId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAdaptationId(String newAdaptationId) {
		String oldAdaptationId = adaptationId;
		adaptationId = newAdaptationId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatusPackage.ADAPTATION_ATTRIBUTES__ADAPTATION_ID, oldAdaptationId, adaptationId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StatusPackage.ADAPTATION_ATTRIBUTES__ADAPTATION_ID:
				return getAdaptationId();
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
			case StatusPackage.ADAPTATION_ATTRIBUTES__ADAPTATION_ID:
				setAdaptationId((String)newValue);
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
			case StatusPackage.ADAPTATION_ATTRIBUTES__ADAPTATION_ID:
				setAdaptationId(ADAPTATION_ID_EDEFAULT);
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
			case StatusPackage.ADAPTATION_ATTRIBUTES__ADAPTATION_ID:
				return ADAPTATION_ID_EDEFAULT == null ? adaptationId != null : !ADAPTATION_ID_EDEFAULT.equals(adaptationId);
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
		result.append(" (adaptationId: ");
		result.append(adaptationId);
		result.append(')');
		return result.toString();
	}

} //AdaptationAttributesImpl
