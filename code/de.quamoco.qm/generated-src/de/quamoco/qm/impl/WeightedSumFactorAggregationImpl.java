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
 * $Id: WeightedSumFactorAggregationImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.quamoco.qm.CharacterizingElement;
import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FactorRanking;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.WeightedSumFactorAggregation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Weighted Sum Factor Aggregation</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.qm.impl.WeightedSumFactorAggregationImpl#getRankings <em>Rankings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WeightedSumFactorAggregationImpl extends FactorAggregationImpl
		implements WeightedSumFactorAggregation {
	/**
	 * The cached value of the '{@link #getRankings() <em>Rankings</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRankings()
	 * @generated
	 * @ordered
	 */
	protected EList<FactorRanking> rankings;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected WeightedSumFactorAggregationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QmPackage.Literals.WEIGHTED_SUM_FACTOR_AGGREGATION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FactorRanking> getRankings() {
		if (rankings == null) {
			rankings = new EObjectContainmentEList<FactorRanking>(FactorRanking.class, this, QmPackage.WEIGHTED_SUM_FACTOR_AGGREGATION__RANKINGS);
		}
		return rankings;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QmPackage.WEIGHTED_SUM_FACTOR_AGGREGATION__RANKINGS:
				return ((InternalEList<?>)getRankings()).basicRemove(otherEnd, msgs);
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
			case QmPackage.WEIGHTED_SUM_FACTOR_AGGREGATION__RANKINGS:
				return getRankings();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case QmPackage.WEIGHTED_SUM_FACTOR_AGGREGATION__RANKINGS:
				getRankings().clear();
				getRankings().addAll((Collection<? extends FactorRanking>)newValue);
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
			case QmPackage.WEIGHTED_SUM_FACTOR_AGGREGATION__RANKINGS:
				getRankings().clear();
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
			case QmPackage.WEIGHTED_SUM_FACTOR_AGGREGATION__RANKINGS:
				return rankings != null && !rankings.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getAccumulatedCompleteness(QualityModel evaluationTarget) {
		double result = 0;
		for (FactorRanking fr : this.getRankings()) {
			Factor el = fr.getFactor();
			Evaluation actual =el.getActualEvaluation(evaluationTarget);
			double acc = 0;
			if(actual != null) {
				acc = actual	.getAccumulatedCompleteness(evaluationTarget);
			}
			result += fr.getWeight()
					* acc;
						
		}
		return result * this.completeness / 100.0;
	}

} // WeightedSumFactorAggregationImpl
