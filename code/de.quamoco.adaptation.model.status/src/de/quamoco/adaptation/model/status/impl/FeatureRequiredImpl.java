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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.quamoco.adaptation.model.status.FeatureRequired;
import de.quamoco.adaptation.model.status.StatusPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Required</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.FeatureRequiredImpl#getRequiredFeatureName <em>Required Feature Name</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.FeatureRequiredImpl#getRequiredFeature <em>Required Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureRequiredImpl extends FulfillmentCriterionImpl implements FeatureRequired {
	/**
	 * The default value of the '{@link #getRequiredFeatureName() <em>Required Feature Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredFeatureName()
	 * @generated
	 * @ordered
	 */
	protected static final String REQUIRED_FEATURE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRequiredFeatureName() <em>Required Feature Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredFeatureName()
	 * @generated
	 * @ordered
	 */
	protected String requiredFeatureName = REQUIRED_FEATURE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getRequiredFeature() <em>Required Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredFeature()
	 * @generated
	 * @ordered
	 */
	protected static final EStructuralFeature REQUIRED_FEATURE_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureRequiredImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatusPackage.Literals.FEATURE_REQUIRED;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRequiredFeatureName() {
		return requiredFeatureName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRequiredFeatureName(String newRequiredFeatureName) {
		String oldRequiredFeatureName = requiredFeatureName;
		requiredFeatureName = newRequiredFeatureName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatusPackage.FEATURE_REQUIRED__REQUIRED_FEATURE_NAME, oldRequiredFeatureName, requiredFeatureName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EStructuralFeature getRequiredFeature() {
		EObject affectedElement = getAdaptationTask().getAffectedElement();
		if (affectedElement != null) {
			return affectedElement.eClass().getEStructuralFeature(requiredFeatureName);
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StatusPackage.FEATURE_REQUIRED__REQUIRED_FEATURE_NAME:
				return getRequiredFeatureName();
			case StatusPackage.FEATURE_REQUIRED__REQUIRED_FEATURE:
				return getRequiredFeature();
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
			case StatusPackage.FEATURE_REQUIRED__REQUIRED_FEATURE_NAME:
				setRequiredFeatureName((String)newValue);
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
			case StatusPackage.FEATURE_REQUIRED__REQUIRED_FEATURE_NAME:
				setRequiredFeatureName(REQUIRED_FEATURE_NAME_EDEFAULT);
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
			case StatusPackage.FEATURE_REQUIRED__REQUIRED_FEATURE_NAME:
				return REQUIRED_FEATURE_NAME_EDEFAULT == null ? requiredFeatureName != null : !REQUIRED_FEATURE_NAME_EDEFAULT.equals(requiredFeatureName);
			case StatusPackage.FEATURE_REQUIRED__REQUIRED_FEATURE:
				return REQUIRED_FEATURE_EDEFAULT == null ? getRequiredFeature() != null : !REQUIRED_FEATURE_EDEFAULT.equals(getRequiredFeature());
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
		result.append(" (requiredFeatureName: ");
		result.append(requiredFeatureName);
		result.append(')');
		return result.toString();
	}

	/**
	 * Checks if the specified feature is set or not.
	 * @generated NOT
	 */
	@Override
	public boolean isFulfilled() {		
		EObject affectedElement = getAdaptationTask().getAffectedElement();
		EStructuralFeature feature = getRequiredFeature();
		if (feature != null) { // feature != null implies that affectedElement != null
			Object value = affectedElement.eGet(feature, true);
			/*
			 * If feature.isMany() then check if value is an collection and 
			 * return !isEmpty of the collection.
			 * Otherwise return value != null.
			 */
			if (feature.isMany() && (value instanceof Collection<?>)) {
				return !((Collection<?>) value).isEmpty();
			} // else 
			if (value instanceof String) {
				return (value != null) && !((String) value).isEmpty();
			} // else
			return value != null;
		}
		return true; // no feature found, criterion is not valid (=> fulfilled)
	}

} //FeatureRequiredImpl
