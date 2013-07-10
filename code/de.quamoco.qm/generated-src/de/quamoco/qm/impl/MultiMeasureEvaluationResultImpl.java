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
 * $Id: MultiMeasureEvaluationResultImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.impl;

import de.quamoco.qm.MeasureRankingEvaluationResult;
import de.quamoco.qm.MultiMeasureEvaluationResult;
import de.quamoco.qm.QmPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Multi Measure Evaluation Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.qm.impl.MultiMeasureEvaluationResultImpl#getEvaluationResults <em>Evaluation Results</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MultiMeasureEvaluationResultImpl extends EvaluationResultImpl implements MultiMeasureEvaluationResult {
	/**
	 * The cached value of the '{@link #getEvaluationResults() <em>Evaluation Results</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvaluationResults()
	 * @generated
	 * @ordered
	 */
	protected EList<MeasureRankingEvaluationResult> evaluationResults;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MultiMeasureEvaluationResultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QmPackage.Literals.MULTI_MEASURE_EVALUATION_RESULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MeasureRankingEvaluationResult> getEvaluationResults() {
		if (evaluationResults == null) {
			evaluationResults = new EObjectContainmentEList<MeasureRankingEvaluationResult>(MeasureRankingEvaluationResult.class, this, QmPackage.MULTI_MEASURE_EVALUATION_RESULT__EVALUATION_RESULTS);
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
			case QmPackage.MULTI_MEASURE_EVALUATION_RESULT__EVALUATION_RESULTS:
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
			case QmPackage.MULTI_MEASURE_EVALUATION_RESULT__EVALUATION_RESULTS:
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
			case QmPackage.MULTI_MEASURE_EVALUATION_RESULT__EVALUATION_RESULTS:
				getEvaluationResults().clear();
				getEvaluationResults().addAll((Collection<? extends MeasureRankingEvaluationResult>)newValue);
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
			case QmPackage.MULTI_MEASURE_EVALUATION_RESULT__EVALUATION_RESULTS:
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
			case QmPackage.MULTI_MEASURE_EVALUATION_RESULT__EVALUATION_RESULTS:
				return evaluationResults != null && !evaluationResults.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //MultiMeasureEvaluationResultImpl
