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
 * $Id: EvaluationResultImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.quamoco.qm.DoubleInterval;
import de.quamoco.qm.Evaluation;
import de.quamoco.qm.EvaluationResult;
import de.quamoco.qm.QmFactory;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.util.QmModelUtils;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Evaluation Result</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.qm.impl.EvaluationResultImpl#getValue <em>Value</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.EvaluationResultImpl#getResultsFrom <em>Results From</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EvaluationResultImpl extends ResultImpl implements
		EvaluationResult {
	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected DoubleInterval value;

	/**
	 * The cached value of the '{@link #getResultsFrom() <em>Results From</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getResultsFrom()
	 * @generated
	 * @ordered
	 */
	protected Evaluation resultsFrom;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected EvaluationResultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QmPackage.Literals.EVALUATION_RESULT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public DoubleInterval getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValue(DoubleInterval newValue,
			NotificationChain msgs) {
		DoubleInterval oldValue = value;
		value = newValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, QmPackage.EVALUATION_RESULT__VALUE, oldValue, newValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(DoubleInterval newValue) {
		if (newValue != value) {
			NotificationChain msgs = null;
			if (value != null)
				msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - QmPackage.EVALUATION_RESULT__VALUE, null, msgs);
			if (newValue != null)
				msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - QmPackage.EVALUATION_RESULT__VALUE, null, msgs);
			msgs = basicSetValue(newValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.EVALUATION_RESULT__VALUE, newValue, newValue));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Evaluation getResultsFrom() {
		if (resultsFrom != null && resultsFrom.eIsProxy()) {
			InternalEObject oldResultsFrom = (InternalEObject)resultsFrom;
			resultsFrom = (Evaluation)eResolveProxy(oldResultsFrom);
			if (resultsFrom != oldResultsFrom) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, QmPackage.EVALUATION_RESULT__RESULTS_FROM, oldResultsFrom, resultsFrom));
			}
		}
		return resultsFrom;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Evaluation basicGetResultsFrom() {
		return resultsFrom;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setResultsFrom(Evaluation newResultsFrom) {
		Evaluation oldResultsFrom = resultsFrom;
		resultsFrom = newResultsFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.EVALUATION_RESULT__RESULTS_FROM, oldResultsFrom, resultsFrom));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QmPackage.EVALUATION_RESULT__VALUE:
				return basicSetValue(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case QmPackage.EVALUATION_RESULT__VALUE:
				return getValue();
			case QmPackage.EVALUATION_RESULT__RESULTS_FROM:
				if (resolve) return getResultsFrom();
				return basicGetResultsFrom();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case QmPackage.EVALUATION_RESULT__VALUE:
				setValue((DoubleInterval)newValue);
				return;
			case QmPackage.EVALUATION_RESULT__RESULTS_FROM:
				setResultsFrom((Evaluation)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case QmPackage.EVALUATION_RESULT__VALUE:
				setValue((DoubleInterval)null);
				return;
			case QmPackage.EVALUATION_RESULT__RESULTS_FROM:
				setResultsFrom((Evaluation)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case QmPackage.EVALUATION_RESULT__VALUE:
				return value != null;
			case QmPackage.EVALUATION_RESULT__RESULTS_FROM:
				return resultsFrom != null;
		}
		return super.eIsSet(featureID);
	}

	/** {@inheritDoc} */
	@Override
	public DoubleInterval getSchoolGrade() {
		DoubleInterval value = this.getValue();
		double v1 = map(value.getLower());
		double v2 = map(value.getUpper());
		return createSchoolGrade(v1, v2);
	}

	/** {@inheritDoc} */
	@Override
	public DoubleInterval getInvertedSchoolGrade() {
		DoubleInterval value = this.getValue();
		double v1 = map(1 - value.getLower());
		double v2 = map(1 - value.getUpper());
		return createSchoolGrade(v1, v2);
	}

	/** Create the school grade for two values. */
	protected DoubleInterval createSchoolGrade(final double v1, final double v2) {
		DoubleInterval grade = QmFactory.eINSTANCE.createDoubleInterval();
		grade.setLower(v1 < v2 ? v1 : v2);
		grade.setUpper(v1 < v2 ? v2 : v1);
		return grade;
	}

	/** Maps a value to school grades. */
	protected double map(double value) {
		// get the deepest model so that it is ensured that for all factors the
		// same quality model
		// is used for mapping
		QualityModel qm = QmModelUtils.getDeepestModelInRequiresRelation(this
				.getResultsFrom());

		// mapping
		if (value >= qm.getSchoolGradeBoundary2()) {
			return dist(value, 1, qm.getSchoolGradeBoundary2(), 2);
		} else if (value > qm.getSchoolGradeBoundary3()) {
			return dist(value, qm.getSchoolGradeBoundary2(),
					qm.getSchoolGradeBoundary3(), 3);
		} else if (value > qm.getSchoolGradeBoundary4()) {
			return dist(value, qm.getSchoolGradeBoundary3(),
					qm.getSchoolGradeBoundary4(), 4);
		} else if (value > qm.getSchoolGradeBoundary5()) {
			return dist(value, qm.getSchoolGradeBoundary4(),
					qm.getSchoolGradeBoundary5(), 5);
		} else if (value > qm.getSchoolGradeBoundary6()) {
			return dist(value, qm.getSchoolGradeBoundary5(),
					qm.getSchoolGradeBoundary6(), 6);
		} else {
			return 6;
		}
	}

	/** Linear distribution. */
	private double dist(double value, double b1, double b2, int i) {
		double tmp = value - b2;
		tmp = tmp / (b1 - b2);
		return i - tmp;
	}

} // EvaluationResultImpl
