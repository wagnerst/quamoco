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
 * $Id: MeasurementResultImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.impl;

import de.quamoco.qm.MeasurementMethod;
import de.quamoco.qm.MeasurementResult;
import de.quamoco.qm.QmPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Measurement Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.qm.impl.MeasurementResultImpl#getResultsFrom <em>Results From</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class MeasurementResultImpl extends ResultImpl implements MeasurementResult {
	/**
	 * The cached value of the '{@link #getResultsFrom() <em>Results From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultsFrom()
	 * @generated
	 * @ordered
	 */
	protected MeasurementMethod resultsFrom;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MeasurementResultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QmPackage.Literals.MEASUREMENT_RESULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasurementMethod getResultsFrom() {
		if (resultsFrom != null && resultsFrom.eIsProxy()) {
			InternalEObject oldResultsFrom = (InternalEObject)resultsFrom;
			resultsFrom = (MeasurementMethod)eResolveProxy(oldResultsFrom);
			if (resultsFrom != oldResultsFrom) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, QmPackage.MEASUREMENT_RESULT__RESULTS_FROM, oldResultsFrom, resultsFrom));
			}
		}
		return resultsFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasurementMethod basicGetResultsFrom() {
		return resultsFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResultsFrom(MeasurementMethod newResultsFrom) {
		MeasurementMethod oldResultsFrom = resultsFrom;
		resultsFrom = newResultsFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.MEASUREMENT_RESULT__RESULTS_FROM, oldResultsFrom, resultsFrom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case QmPackage.MEASUREMENT_RESULT__RESULTS_FROM:
				if (resolve) return getResultsFrom();
				return basicGetResultsFrom();
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
			case QmPackage.MEASUREMENT_RESULT__RESULTS_FROM:
				setResultsFrom((MeasurementMethod)newValue);
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
			case QmPackage.MEASUREMENT_RESULT__RESULTS_FROM:
				setResultsFrom((MeasurementMethod)null);
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
			case QmPackage.MEASUREMENT_RESULT__RESULTS_FROM:
				return resultsFrom != null;
		}
		return super.eIsSet(featureID);
	}

} //MeasurementResultImpl
