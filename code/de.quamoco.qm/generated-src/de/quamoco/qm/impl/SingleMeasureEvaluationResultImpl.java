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
 * $Id: SingleMeasureEvaluationResultImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.impl;

import de.quamoco.qm.QmPackage;
import de.quamoco.qm.SingleMeasureEvaluationResult;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Single Measure Evaluation Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.qm.impl.SingleMeasureEvaluationResultImpl#getRatioAffected <em>Ratio Affected</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SingleMeasureEvaluationResultImpl extends EvaluationResultImpl implements SingleMeasureEvaluationResult {
	/**
	 * The default value of the '{@link #getRatioAffected() <em>Ratio Affected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRatioAffected()
	 * @generated
	 * @ordered
	 */
	protected static final double RATIO_AFFECTED_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getRatioAffected() <em>Ratio Affected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRatioAffected()
	 * @generated
	 * @ordered
	 */
	protected double ratioAffected = RATIO_AFFECTED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SingleMeasureEvaluationResultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QmPackage.Literals.SINGLE_MEASURE_EVALUATION_RESULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getRatioAffected() {
		return ratioAffected;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRatioAffected(double newRatioAffected) {
		double oldRatioAffected = ratioAffected;
		ratioAffected = newRatioAffected;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.SINGLE_MEASURE_EVALUATION_RESULT__RATIO_AFFECTED, oldRatioAffected, ratioAffected));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case QmPackage.SINGLE_MEASURE_EVALUATION_RESULT__RATIO_AFFECTED:
				return getRatioAffected();
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
			case QmPackage.SINGLE_MEASURE_EVALUATION_RESULT__RATIO_AFFECTED:
				setRatioAffected((Double)newValue);
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
			case QmPackage.SINGLE_MEASURE_EVALUATION_RESULT__RATIO_AFFECTED:
				setRatioAffected(RATIO_AFFECTED_EDEFAULT);
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
			case QmPackage.SINGLE_MEASURE_EVALUATION_RESULT__RATIO_AFFECTED:
				return ratioAffected != RATIO_AFFECTED_EDEFAULT;
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
		result.append(" (ratioAffected: ");
		result.append(ratioAffected);
		result.append(')');
		return result.toString();
	}

} //SingleMeasureEvaluationResultImpl
