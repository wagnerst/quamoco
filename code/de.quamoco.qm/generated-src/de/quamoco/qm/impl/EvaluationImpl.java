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
 * $Id: EvaluationImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.impl;

import java.util.ArrayList;
import java.util.List;

import org.conqat.lib.commons.string.StringUtils;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FactorRefinement;
import de.quamoco.qm.Impact;
import de.quamoco.qm.Measure;
import de.quamoco.qm.Measurement;
import de.quamoco.qm.NormalizationMeasure;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.util.QmModelUtils;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Impact Evaluation</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.qm.impl.EvaluationImpl#getEvaluates <em>Evaluates</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.EvaluationImpl#getQualityModel <em>Quality Model</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.EvaluationImpl#getMaximumPoints <em>Maximum Points</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.EvaluationImpl#getCompleteness <em>Completeness</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class EvaluationImpl extends NamedElementImpl implements
		Evaluation {
	/**
	 * The cached value of the '{@link #getEvaluates() <em>Evaluates</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getEvaluates()
	 * @generated
	 * @ordered
	 */
	protected Factor evaluates;

	/**
	 * The default value of the '{@link #getMaximumPoints() <em>Maximum Points</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getMaximumPoints()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_POINTS_EDEFAULT = 100;

	/**
	 * The cached value of the '{@link #getMaximumPoints() <em>Maximum Points</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getMaximumPoints()
	 * @generated
	 * @ordered
	 */
	protected int maximumPoints = MAXIMUM_POINTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getCompleteness() <em>Completeness</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompleteness()
	 * @generated
	 * @ordered
	 */
	protected static final int COMPLETENESS_EDEFAULT = 100;

	/**
	 * The cached value of the '{@link #getCompleteness() <em>Completeness</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompleteness()
	 * @generated
	 * @ordered
	 */
	protected int completeness = COMPLETENESS_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected EvaluationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QmPackage.Literals.EVALUATION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Factor getEvaluates() {
		if (evaluates != null && evaluates.eIsProxy()) {
			InternalEObject oldEvaluates = (InternalEObject)evaluates;
			evaluates = (Factor)eResolveProxy(oldEvaluates);
			if (evaluates != oldEvaluates) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, QmPackage.EVALUATION__EVALUATES, oldEvaluates, evaluates));
			}
		}
		return evaluates;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Factor basicGetEvaluates() {
		return evaluates;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setEvaluates(Factor newEvaluates) {
		Factor oldEvaluates = evaluates;
		evaluates = newEvaluates;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.EVALUATION__EVALUATES, oldEvaluates, evaluates));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public QualityModel getQualityModel() {
		if (eContainerFeatureID() != QmPackage.EVALUATION__QUALITY_MODEL) return null;
		return (QualityModel)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQualityModel(QualityModel newQualityModel,
			NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newQualityModel, QmPackage.EVALUATION__QUALITY_MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualityModel(QualityModel newQualityModel) {
		if (newQualityModel != eInternalContainer() || (eContainerFeatureID() != QmPackage.EVALUATION__QUALITY_MODEL && newQualityModel != null)) {
			if (EcoreUtil.isAncestor(this, newQualityModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQualityModel != null)
				msgs = ((InternalEObject)newQualityModel).eInverseAdd(this, QmPackage.QUALITY_MODEL__EVALUATIONS, QualityModel.class, msgs);
			msgs = basicSetQualityModel(newQualityModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.EVALUATION__QUALITY_MODEL, newQualityModel, newQualityModel));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumPoints() {
		return maximumPoints;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumPoints(int newMaximumPoints) {
		int oldMaximumPoints = maximumPoints;
		maximumPoints = newMaximumPoints;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.EVALUATION__MAXIMUM_POINTS, oldMaximumPoints, maximumPoints));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCompleteness() {
		return completeness;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompleteness(int newCompleteness) {
		int oldCompleteness = completeness;
		completeness = newCompleteness;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.EVALUATION__COMPLETENESS, oldCompleteness, completeness));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QmPackage.EVALUATION__QUALITY_MODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetQualityModel((QualityModel)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QmPackage.EVALUATION__QUALITY_MODEL:
				return basicSetQualityModel(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case QmPackage.EVALUATION__QUALITY_MODEL:
				return eInternalContainer().eInverseRemove(this, QmPackage.QUALITY_MODEL__EVALUATIONS, QualityModel.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case QmPackage.EVALUATION__EVALUATES:
				if (resolve) return getEvaluates();
				return basicGetEvaluates();
			case QmPackage.EVALUATION__QUALITY_MODEL:
				return getQualityModel();
			case QmPackage.EVALUATION__MAXIMUM_POINTS:
				return getMaximumPoints();
			case QmPackage.EVALUATION__COMPLETENESS:
				return getCompleteness();
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
			case QmPackage.EVALUATION__EVALUATES:
				setEvaluates((Factor)newValue);
				return;
			case QmPackage.EVALUATION__QUALITY_MODEL:
				setQualityModel((QualityModel)newValue);
				return;
			case QmPackage.EVALUATION__MAXIMUM_POINTS:
				setMaximumPoints((Integer)newValue);
				return;
			case QmPackage.EVALUATION__COMPLETENESS:
				setCompleteness((Integer)newValue);
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
			case QmPackage.EVALUATION__EVALUATES:
				setEvaluates((Factor)null);
				return;
			case QmPackage.EVALUATION__QUALITY_MODEL:
				setQualityModel((QualityModel)null);
				return;
			case QmPackage.EVALUATION__MAXIMUM_POINTS:
				setMaximumPoints(MAXIMUM_POINTS_EDEFAULT);
				return;
			case QmPackage.EVALUATION__COMPLETENESS:
				setCompleteness(COMPLETENESS_EDEFAULT);
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
			case QmPackage.EVALUATION__EVALUATES:
				return evaluates != null;
			case QmPackage.EVALUATION__QUALITY_MODEL:
				return getQualityModel() != null;
			case QmPackage.EVALUATION__MAXIMUM_POINTS:
				return maximumPoints != MAXIMUM_POINTS_EDEFAULT;
			case QmPackage.EVALUATION__COMPLETENESS:
				return completeness != COMPLETENESS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (maximumPoints: ");
		result.append(maximumPoints);
		result.append(", completeness: ");
		result.append(completeness);
		result.append(')');
		return result.toString();
	}

	/** {@inheritDoc} */
	@Override
	public String getQualifiedName() {
		String qualifiedName = "";
		if (getQualityModel() != null
				&& !StringUtils.isEmpty(getQualityModel().getName())) {
			qualifiedName += getQualityModel().getName();
		}

		if (getEvaluates() != null) {
			qualifiedName += "/" + getEvaluates().getQualifiedName();
		}

		String name = getName();
		if (StringUtils.isEmpty(name)) {
			name = eClass().getName();
		}
		qualifiedName += "/" + name;

		return qualifiedName;
	}

	/** {@inheritDoc} */
	@Override
	public List<NormalizationMeasure> getNormalizationMeasures() {
		return QmModelUtils.getNormalizationMeasures(this);
	}

	/** {@inheritDoc} */
	@Override
	public List<Measure> getUsableMeasures() {
		List<Measure> measures = new ArrayList<Measure>();
		for (Measurement measurement : getUsableMeasurements()) {
			measures.add(measurement.getChild());
		}
		return measures;
	}

	/** {@inheritDoc} */
	@Override
	public List<Measurement> getUsableMeasurements() {
		List<Measurement> measurements = new ArrayList<Measurement>();
		if (getEvaluates() != null) {
			for (Measurement measurement : getEvaluates().getMeasuredBy()) {
				if (QmModelUtils.requires(this, measurement)) {
					measurements.add(measurement);
				}
			}
		}
		return measurements;
	}

	/** {@inheritDoc} */
	public List<Factor> getUsableFactors() {
		List<Factor> factors = new ArrayList<Factor>();
		for (Impact impact : getUsableImpacts()) {
			factors.add(impact.getSource());
		}
		for (FactorRefinement refinement : getUsableRefinements()) {
			factors.add(refinement.getChild());
		}
		return factors;
	}

	/** {@inheritDoc} */
	@Override
	public List<Impact> getUsableImpacts() {
		List<Impact> impacts = new ArrayList<Impact>();
		if (getEvaluates() != null) {
			for (Impact impact : getEvaluates().getInfluencedBy()) {
				if (QmModelUtils.requires(this, impact)) {
					impacts.add(impact);
				}
			}
		}
		return impacts;
	}

	/** {@inheritDoc} */
	@Override
	public List<FactorRefinement> getUsableRefinements() {
		List<FactorRefinement> refinements = new ArrayList<FactorRefinement>();
		if (getEvaluates() != null) {
			for (FactorRefinement impact : getEvaluates().getRefinedBy()) {
				if (QmModelUtils.requires(this, impact)) {
					refinements.add(impact);
				}
			}
		}
		return refinements;
	}

} // ImpactEvaluationImpl
