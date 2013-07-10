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
 * $Id: SingleMeasureEvaluationImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.quamoco.qm.Function;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasureEvaluation;
import de.quamoco.qm.NormalizationMeasure;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.SingleMeasureEvaluation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Single Measure Evaluation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.qm.impl.SingleMeasureEvaluationImpl#getMeasure <em>Measure</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.SingleMeasureEvaluationImpl#getNormlizationMeasure <em>Normlization Measure</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.SingleMeasureEvaluationImpl#getFunction <em>Function</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.SingleMeasureEvaluationImpl#getRange <em>Range</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SingleMeasureEvaluationImpl extends FormBasedEvaluationImpl implements SingleMeasureEvaluation {
	/**
	 * The cached value of the '{@link #getMeasure() <em>Measure</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeasure()
	 * @generated
	 * @ordered
	 */
	protected Measure measure;
	/**
	 * The cached value of the '{@link #getNormlizationMeasure() <em>Normlization Measure</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNormlizationMeasure()
	 * @generated
	 * @ordered
	 */
	protected NormalizationMeasure normlizationMeasure;
	/**
	 * The cached value of the '{@link #getFunction() <em>Function</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunction()
	 * @generated
	 * @ordered
	 */
	protected Function function;
	/**
	 * The default value of the '{@link #getRange() <em>Range</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRange()
	 * @generated
	 * @ordered
	 */
	protected static final String RANGE_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getRange() <em>Range</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRange()
	 * @generated
	 * @ordered
	 */
	protected String range = RANGE_EDEFAULT;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SingleMeasureEvaluationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QmPackage.Literals.SINGLE_MEASURE_EVALUATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Measure getMeasure() {
		if (measure != null && measure.eIsProxy()) {
			InternalEObject oldMeasure = (InternalEObject)measure;
			measure = (Measure)eResolveProxy(oldMeasure);
			if (measure != oldMeasure) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, QmPackage.SINGLE_MEASURE_EVALUATION__MEASURE, oldMeasure, measure));
			}
		}
		return measure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Measure basicGetMeasure() {
		return measure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMeasure(Measure newMeasure) {
		Measure oldMeasure = measure;
		measure = newMeasure;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.SINGLE_MEASURE_EVALUATION__MEASURE, oldMeasure, measure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NormalizationMeasure getNormlizationMeasure() {
		if (normlizationMeasure != null && normlizationMeasure.eIsProxy()) {
			InternalEObject oldNormlizationMeasure = (InternalEObject)normlizationMeasure;
			normlizationMeasure = (NormalizationMeasure)eResolveProxy(oldNormlizationMeasure);
			if (normlizationMeasure != oldNormlizationMeasure) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, QmPackage.SINGLE_MEASURE_EVALUATION__NORMLIZATION_MEASURE, oldNormlizationMeasure, normlizationMeasure));
			}
		}
		return normlizationMeasure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NormalizationMeasure basicGetNormlizationMeasure() {
		return normlizationMeasure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNormlizationMeasure(NormalizationMeasure newNormlizationMeasure) {
		NormalizationMeasure oldNormlizationMeasure = normlizationMeasure;
		normlizationMeasure = newNormlizationMeasure;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.SINGLE_MEASURE_EVALUATION__NORMLIZATION_MEASURE, oldNormlizationMeasure, normlizationMeasure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRange() {
		return range;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRange(String newRange) {
		String oldRange = range;
		range = newRange;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.SINGLE_MEASURE_EVALUATION__RANGE, oldRange, range));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Function getFunction() {
		return function;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFunction(Function newFunction, NotificationChain msgs) {
		Function oldFunction = function;
		function = newFunction;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, QmPackage.SINGLE_MEASURE_EVALUATION__FUNCTION, oldFunction, newFunction);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFunction(Function newFunction) {
		if (newFunction != function) {
			NotificationChain msgs = null;
			if (function != null)
				msgs = ((InternalEObject)function).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - QmPackage.SINGLE_MEASURE_EVALUATION__FUNCTION, null, msgs);
			if (newFunction != null)
				msgs = ((InternalEObject)newFunction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - QmPackage.SINGLE_MEASURE_EVALUATION__FUNCTION, null, msgs);
			msgs = basicSetFunction(newFunction, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.SINGLE_MEASURE_EVALUATION__FUNCTION, newFunction, newFunction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QmPackage.SINGLE_MEASURE_EVALUATION__FUNCTION:
				return basicSetFunction(null, msgs);
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
			case QmPackage.SINGLE_MEASURE_EVALUATION__MEASURE:
				if (resolve) return getMeasure();
				return basicGetMeasure();
			case QmPackage.SINGLE_MEASURE_EVALUATION__NORMLIZATION_MEASURE:
				if (resolve) return getNormlizationMeasure();
				return basicGetNormlizationMeasure();
			case QmPackage.SINGLE_MEASURE_EVALUATION__FUNCTION:
				return getFunction();
			case QmPackage.SINGLE_MEASURE_EVALUATION__RANGE:
				return getRange();
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
			case QmPackage.SINGLE_MEASURE_EVALUATION__MEASURE:
				setMeasure((Measure)newValue);
				return;
			case QmPackage.SINGLE_MEASURE_EVALUATION__NORMLIZATION_MEASURE:
				setNormlizationMeasure((NormalizationMeasure)newValue);
				return;
			case QmPackage.SINGLE_MEASURE_EVALUATION__FUNCTION:
				setFunction((Function)newValue);
				return;
			case QmPackage.SINGLE_MEASURE_EVALUATION__RANGE:
				setRange((String)newValue);
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
			case QmPackage.SINGLE_MEASURE_EVALUATION__MEASURE:
				setMeasure((Measure)null);
				return;
			case QmPackage.SINGLE_MEASURE_EVALUATION__NORMLIZATION_MEASURE:
				setNormlizationMeasure((NormalizationMeasure)null);
				return;
			case QmPackage.SINGLE_MEASURE_EVALUATION__FUNCTION:
				setFunction((Function)null);
				return;
			case QmPackage.SINGLE_MEASURE_EVALUATION__RANGE:
				setRange(RANGE_EDEFAULT);
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
			case QmPackage.SINGLE_MEASURE_EVALUATION__MEASURE:
				return measure != null;
			case QmPackage.SINGLE_MEASURE_EVALUATION__NORMLIZATION_MEASURE:
				return normlizationMeasure != null;
			case QmPackage.SINGLE_MEASURE_EVALUATION__FUNCTION:
				return function != null;
			case QmPackage.SINGLE_MEASURE_EVALUATION__RANGE:
				return RANGE_EDEFAULT == null ? range != null : !RANGE_EDEFAULT.equals(range);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == MeasureEvaluation.class) {
			switch (derivedFeatureID) {
				case QmPackage.SINGLE_MEASURE_EVALUATION__MEASURE: return QmPackage.MEASURE_EVALUATION__MEASURE;
				case QmPackage.SINGLE_MEASURE_EVALUATION__NORMLIZATION_MEASURE: return QmPackage.MEASURE_EVALUATION__NORMLIZATION_MEASURE;
				case QmPackage.SINGLE_MEASURE_EVALUATION__FUNCTION: return QmPackage.MEASURE_EVALUATION__FUNCTION;
				case QmPackage.SINGLE_MEASURE_EVALUATION__RANGE: return QmPackage.MEASURE_EVALUATION__RANGE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == MeasureEvaluation.class) {
			switch (baseFeatureID) {
				case QmPackage.MEASURE_EVALUATION__MEASURE: return QmPackage.SINGLE_MEASURE_EVALUATION__MEASURE;
				case QmPackage.MEASURE_EVALUATION__NORMLIZATION_MEASURE: return QmPackage.SINGLE_MEASURE_EVALUATION__NORMLIZATION_MEASURE;
				case QmPackage.MEASURE_EVALUATION__FUNCTION: return QmPackage.SINGLE_MEASURE_EVALUATION__FUNCTION;
				case QmPackage.MEASURE_EVALUATION__RANGE: return QmPackage.SINGLE_MEASURE_EVALUATION__RANGE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (range: ");
		result.append(range);
		result.append(')');
		return result.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getAccumulatedCompleteness(QualityModel evaluationTarget) {
		return Double.valueOf(this.completeness / 100.0);
	}

} //SingleMeasureEvaluationImpl
