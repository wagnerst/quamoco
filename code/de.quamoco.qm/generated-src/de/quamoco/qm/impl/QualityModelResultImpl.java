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
 * $Id: QualityModelResultImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.impl;

import de.quamoco.qm.EvaluationResult;
import de.quamoco.qm.MeasurementResult;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModelResult;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Quality Model Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.qm.impl.QualityModelResultImpl#getSystem <em>System</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.QualityModelResultImpl#getDate <em>Date</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.QualityModelResultImpl#getMeasurementResults <em>Measurement Results</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.QualityModelResultImpl#getEvaluationResults <em>Evaluation Results</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QualityModelResultImpl extends EObjectImpl implements QualityModelResult {
	/**
	 * The default value of the '{@link #getSystem() <em>System</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystem()
	 * @generated
	 * @ordered
	 */
	protected static final String SYSTEM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSystem() <em>System</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystem()
	 * @generated
	 * @ordered
	 */
	protected String system = SYSTEM_EDEFAULT;

	/**
	 * The default value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
	protected Date date = DATE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMeasurementResults() <em>Measurement Results</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeasurementResults()
	 * @generated
	 * @ordered
	 */
	protected EList<MeasurementResult> measurementResults;

	/**
	 * The cached value of the '{@link #getEvaluationResults() <em>Evaluation Results</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvaluationResults()
	 * @generated
	 * @ordered
	 */
	protected EList<EvaluationResult> evaluationResults;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QualityModelResultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QmPackage.Literals.QUALITY_MODEL_RESULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSystem() {
		return system;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSystem(String newSystem) {
		String oldSystem = system;
		system = newSystem;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.QUALITY_MODEL_RESULT__SYSTEM, oldSystem, system));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDate(Date newDate) {
		Date oldDate = date;
		date = newDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.QUALITY_MODEL_RESULT__DATE, oldDate, date));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MeasurementResult> getMeasurementResults() {
		if (measurementResults == null) {
			measurementResults = new EObjectContainmentEList<MeasurementResult>(MeasurementResult.class, this, QmPackage.QUALITY_MODEL_RESULT__MEASUREMENT_RESULTS);
		}
		return measurementResults;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EvaluationResult> getEvaluationResults() {
		if (evaluationResults == null) {
			evaluationResults = new EObjectContainmentEList<EvaluationResult>(EvaluationResult.class, this, QmPackage.QUALITY_MODEL_RESULT__EVALUATION_RESULTS);
		}
		return evaluationResults;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QmPackage.QUALITY_MODEL_RESULT__MEASUREMENT_RESULTS:
				return ((InternalEList<?>)getMeasurementResults()).basicRemove(otherEnd, msgs);
			case QmPackage.QUALITY_MODEL_RESULT__EVALUATION_RESULTS:
				return ((InternalEList<?>)getEvaluationResults()).basicRemove(otherEnd, msgs);
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
			case QmPackage.QUALITY_MODEL_RESULT__SYSTEM:
				return getSystem();
			case QmPackage.QUALITY_MODEL_RESULT__DATE:
				return getDate();
			case QmPackage.QUALITY_MODEL_RESULT__MEASUREMENT_RESULTS:
				return getMeasurementResults();
			case QmPackage.QUALITY_MODEL_RESULT__EVALUATION_RESULTS:
				return getEvaluationResults();
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
			case QmPackage.QUALITY_MODEL_RESULT__SYSTEM:
				setSystem((String)newValue);
				return;
			case QmPackage.QUALITY_MODEL_RESULT__DATE:
				setDate((Date)newValue);
				return;
			case QmPackage.QUALITY_MODEL_RESULT__MEASUREMENT_RESULTS:
				getMeasurementResults().clear();
				getMeasurementResults().addAll((Collection<? extends MeasurementResult>)newValue);
				return;
			case QmPackage.QUALITY_MODEL_RESULT__EVALUATION_RESULTS:
				getEvaluationResults().clear();
				getEvaluationResults().addAll((Collection<? extends EvaluationResult>)newValue);
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
			case QmPackage.QUALITY_MODEL_RESULT__SYSTEM:
				setSystem(SYSTEM_EDEFAULT);
				return;
			case QmPackage.QUALITY_MODEL_RESULT__DATE:
				setDate(DATE_EDEFAULT);
				return;
			case QmPackage.QUALITY_MODEL_RESULT__MEASUREMENT_RESULTS:
				getMeasurementResults().clear();
				return;
			case QmPackage.QUALITY_MODEL_RESULT__EVALUATION_RESULTS:
				getEvaluationResults().clear();
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
			case QmPackage.QUALITY_MODEL_RESULT__SYSTEM:
				return SYSTEM_EDEFAULT == null ? system != null : !SYSTEM_EDEFAULT.equals(system);
			case QmPackage.QUALITY_MODEL_RESULT__DATE:
				return DATE_EDEFAULT == null ? date != null : !DATE_EDEFAULT.equals(date);
			case QmPackage.QUALITY_MODEL_RESULT__MEASUREMENT_RESULTS:
				return measurementResults != null && !measurementResults.isEmpty();
			case QmPackage.QUALITY_MODEL_RESULT__EVALUATION_RESULTS:
				return evaluationResults != null && !evaluationResults.isEmpty();
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
		result.append(" (system: ");
		result.append(system);
		result.append(", date: ");
		result.append(date);
		result.append(')');
		return result.toString();
	}

} //QualityModelResultImpl
