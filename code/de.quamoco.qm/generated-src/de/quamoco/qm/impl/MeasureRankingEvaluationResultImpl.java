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
 * $Id: MeasureRankingEvaluationResultImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.impl;

import de.quamoco.qm.DoubleInterval;
import de.quamoco.qm.MeasureRanking;
import de.quamoco.qm.MeasureRankingEvaluationResult;
import de.quamoco.qm.QmPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Measure Ranking Evaluation Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.qm.impl.MeasureRankingEvaluationResultImpl#getRatioAffected <em>Ratio Affected</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.MeasureRankingEvaluationResultImpl#getValue <em>Value</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.MeasureRankingEvaluationResultImpl#getResultsFrom <em>Results From</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MeasureRankingEvaluationResultImpl extends EObjectImpl implements MeasureRankingEvaluationResult {
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
	 * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected DoubleInterval value;

	/**
	 * The cached value of the '{@link #getResultsFrom() <em>Results From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultsFrom()
	 * @generated
	 * @ordered
	 */
	protected MeasureRanking resultsFrom;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MeasureRankingEvaluationResultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QmPackage.Literals.MEASURE_RANKING_EVALUATION_RESULT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.MEASURE_RANKING_EVALUATION_RESULT__RATIO_AFFECTED, oldRatioAffected, ratioAffected));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DoubleInterval getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValue(DoubleInterval newValue, NotificationChain msgs) {
		DoubleInterval oldValue = value;
		value = newValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, QmPackage.MEASURE_RANKING_EVALUATION_RESULT__VALUE, oldValue, newValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(DoubleInterval newValue) {
		if (newValue != value) {
			NotificationChain msgs = null;
			if (value != null)
				msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - QmPackage.MEASURE_RANKING_EVALUATION_RESULT__VALUE, null, msgs);
			if (newValue != null)
				msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - QmPackage.MEASURE_RANKING_EVALUATION_RESULT__VALUE, null, msgs);
			msgs = basicSetValue(newValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.MEASURE_RANKING_EVALUATION_RESULT__VALUE, newValue, newValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasureRanking getResultsFrom() {
		if (resultsFrom != null && resultsFrom.eIsProxy()) {
			InternalEObject oldResultsFrom = (InternalEObject)resultsFrom;
			resultsFrom = (MeasureRanking)eResolveProxy(oldResultsFrom);
			if (resultsFrom != oldResultsFrom) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, QmPackage.MEASURE_RANKING_EVALUATION_RESULT__RESULTS_FROM, oldResultsFrom, resultsFrom));
			}
		}
		return resultsFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasureRanking basicGetResultsFrom() {
		return resultsFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResultsFrom(MeasureRanking newResultsFrom) {
		MeasureRanking oldResultsFrom = resultsFrom;
		resultsFrom = newResultsFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.MEASURE_RANKING_EVALUATION_RESULT__RESULTS_FROM, oldResultsFrom, resultsFrom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QmPackage.MEASURE_RANKING_EVALUATION_RESULT__VALUE:
				return basicSetValue(null, msgs);
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
			case QmPackage.MEASURE_RANKING_EVALUATION_RESULT__RATIO_AFFECTED:
				return getRatioAffected();
			case QmPackage.MEASURE_RANKING_EVALUATION_RESULT__VALUE:
				return getValue();
			case QmPackage.MEASURE_RANKING_EVALUATION_RESULT__RESULTS_FROM:
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
			case QmPackage.MEASURE_RANKING_EVALUATION_RESULT__RATIO_AFFECTED:
				setRatioAffected((Double)newValue);
				return;
			case QmPackage.MEASURE_RANKING_EVALUATION_RESULT__VALUE:
				setValue((DoubleInterval)newValue);
				return;
			case QmPackage.MEASURE_RANKING_EVALUATION_RESULT__RESULTS_FROM:
				setResultsFrom((MeasureRanking)newValue);
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
			case QmPackage.MEASURE_RANKING_EVALUATION_RESULT__RATIO_AFFECTED:
				setRatioAffected(RATIO_AFFECTED_EDEFAULT);
				return;
			case QmPackage.MEASURE_RANKING_EVALUATION_RESULT__VALUE:
				setValue((DoubleInterval)null);
				return;
			case QmPackage.MEASURE_RANKING_EVALUATION_RESULT__RESULTS_FROM:
				setResultsFrom((MeasureRanking)null);
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
			case QmPackage.MEASURE_RANKING_EVALUATION_RESULT__RATIO_AFFECTED:
				return ratioAffected != RATIO_AFFECTED_EDEFAULT;
			case QmPackage.MEASURE_RANKING_EVALUATION_RESULT__VALUE:
				return value != null;
			case QmPackage.MEASURE_RANKING_EVALUATION_RESULT__RESULTS_FROM:
				return resultsFrom != null;
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

} //MeasureRankingEvaluationResultImpl
