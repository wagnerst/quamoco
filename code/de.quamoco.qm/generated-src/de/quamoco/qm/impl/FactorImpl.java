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
 * $Id: FactorImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import de.quamoco.qm.Entity;
import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FactorRefinement;
import de.quamoco.qm.Impact;
import de.quamoco.qm.Measure;
import de.quamoco.qm.Measurement;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.util.QmModelUtils;
import de.quamoco.qm.util.ReferenceClassProxyList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Factor</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.qm.impl.FactorImpl#getInfluences <em>Influences</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.FactorImpl#getInfluencesDirect <em>Influences Direct</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.FactorImpl#getRefines <em>Refines</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.FactorImpl#getRefinesDirect <em>Refines Direct</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.FactorImpl#getQualityModel <em>Quality Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FactorImpl extends CharacterizingElementImpl implements Factor {
	/**
	 * The cached value of the '{@link #getInfluences() <em>Influences</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getInfluences()
	 * @generated
	 * @ordered
	 */
	protected EList<Impact> influences;

	/**
	 * The cached value of the '{@link #getRefines() <em>Refines</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRefines()
	 * @generated
	 * @ordered
	 */
	protected EList<FactorRefinement> refines;

	/** Proxy for derived reference refinesDirect. */
	protected EList<Factor> refinesDirect;

	/** Proxy for derived reference influencesDirect. */
	protected EList<Factor> influencesDirect;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected FactorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QmPackage.Literals.FACTOR;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Impact> getInfluences() {
		if (influences == null) {
			influences = new EObjectContainmentWithInverseEList<Impact>(Impact.class, this, QmPackage.FACTOR__INFLUENCES, QmPackage.IMPACT__SOURCE);
		}
		return influences;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<Factor> getInfluencesDirect() {
		if (influencesDirect == null) {
			influencesDirect = new ReferenceClassProxyList<Impact, Factor>(
					this, QmPackage.eINSTANCE.getFactor_InfluencesDirect(),
					QmPackage.eINSTANCE.getFactor_Influences(),
					QmPackage.eINSTANCE.getImpact(),
					QmPackage.eINSTANCE.getImpact_Target());
		}
		return influencesDirect;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FactorRefinement> getRefines() {
		if (refines == null) {
			refines = new EObjectContainmentWithInverseEList<FactorRefinement>(FactorRefinement.class, this, QmPackage.FACTOR__REFINES, QmPackage.FACTOR_REFINEMENT__CHILD);
		}
		return refines;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<Factor> getRefinesDirect() {
		if (refinesDirect == null) {
			refinesDirect = new ReferenceClassProxyList<FactorRefinement, Factor>(
					this, QmPackage.eINSTANCE.getFactor_RefinesDirect(),
					QmPackage.eINSTANCE.getFactor_Refines(),
					QmPackage.eINSTANCE.getFactorRefinement(),
					QmPackage.eINSTANCE.getFactorRefinement_Parent());
		}
		return refinesDirect;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public QualityModel getQualityModel() {
		if (eContainerFeatureID() != QmPackage.FACTOR__QUALITY_MODEL) return null;
		return (QualityModel)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQualityModel(QualityModel newQualityModel,
			NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newQualityModel, QmPackage.FACTOR__QUALITY_MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualityModel(QualityModel newQualityModel) {
		if (newQualityModel != eInternalContainer() || (eContainerFeatureID() != QmPackage.FACTOR__QUALITY_MODEL && newQualityModel != null)) {
			if (EcoreUtil.isAncestor(this, newQualityModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQualityModel != null)
				msgs = ((InternalEObject)newQualityModel).eInverseAdd(this, QmPackage.QUALITY_MODEL__FACTORS, QualityModel.class, msgs);
			msgs = basicSetQualityModel(newQualityModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.FACTOR__QUALITY_MODEL, newQualityModel, newQualityModel));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QmPackage.FACTOR__INFLUENCES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInfluences()).basicAdd(otherEnd, msgs);
			case QmPackage.FACTOR__REFINES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRefines()).basicAdd(otherEnd, msgs);
			case QmPackage.FACTOR__QUALITY_MODEL:
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
			case QmPackage.FACTOR__INFLUENCES:
				return ((InternalEList<?>)getInfluences()).basicRemove(otherEnd, msgs);
			case QmPackage.FACTOR__REFINES:
				return ((InternalEList<?>)getRefines()).basicRemove(otherEnd, msgs);
			case QmPackage.FACTOR__QUALITY_MODEL:
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
			case QmPackage.FACTOR__QUALITY_MODEL:
				return eInternalContainer().eInverseRemove(this, QmPackage.QUALITY_MODEL__FACTORS, QualityModel.class, msgs);
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
			case QmPackage.FACTOR__INFLUENCES:
				return getInfluences();
			case QmPackage.FACTOR__INFLUENCES_DIRECT:
				return getInfluencesDirect();
			case QmPackage.FACTOR__REFINES:
				return getRefines();
			case QmPackage.FACTOR__REFINES_DIRECT:
				return getRefinesDirect();
			case QmPackage.FACTOR__QUALITY_MODEL:
				return getQualityModel();
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
			case QmPackage.FACTOR__INFLUENCES:
				getInfluences().clear();
				getInfluences().addAll((Collection<? extends Impact>)newValue);
				return;
			case QmPackage.FACTOR__INFLUENCES_DIRECT:
				getInfluencesDirect().clear();
				getInfluencesDirect().addAll((Collection<? extends Factor>)newValue);
				return;
			case QmPackage.FACTOR__REFINES:
				getRefines().clear();
				getRefines().addAll((Collection<? extends FactorRefinement>)newValue);
				return;
			case QmPackage.FACTOR__REFINES_DIRECT:
				getRefinesDirect().clear();
				getRefinesDirect().addAll((Collection<? extends Factor>)newValue);
				return;
			case QmPackage.FACTOR__QUALITY_MODEL:
				setQualityModel((QualityModel)newValue);
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
			case QmPackage.FACTOR__INFLUENCES:
				getInfluences().clear();
				return;
			case QmPackage.FACTOR__INFLUENCES_DIRECT:
				getInfluencesDirect().clear();
				return;
			case QmPackage.FACTOR__REFINES:
				getRefines().clear();
				return;
			case QmPackage.FACTOR__REFINES_DIRECT:
				getRefinesDirect().clear();
				return;
			case QmPackage.FACTOR__QUALITY_MODEL:
				setQualityModel((QualityModel)null);
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
			case QmPackage.FACTOR__INFLUENCES:
				return influences != null && !influences.isEmpty();
			case QmPackage.FACTOR__INFLUENCES_DIRECT:
				return !getInfluencesDirect().isEmpty();
			case QmPackage.FACTOR__REFINES:
				return refines != null && !refines.isEmpty();
			case QmPackage.FACTOR__REFINES_DIRECT:
				return !getRefinesDirect().isEmpty();
			case QmPackage.FACTOR__QUALITY_MODEL:
				return getQualityModel() != null;
		}
		return super.eIsSet(featureID);
	}

	/** {@inheritDoc} */
	@Override
	public String getQualifiedName() {
		String qualifiedName = "";

		String name = getName();
		if (name != null) {
			qualifiedName = name;
		}

		Entity entity = getCharacterizes();
		if (entity != null) {
			String entityQualifiedName = entity.getQualifiedName();
			if (!"System".equals(entityQualifiedName)
					&& !"".equals(entityQualifiedName)) {
				qualifiedName += " @" + entityQualifiedName;
			}
		}

		return qualifiedName;
	}

	/** {@inheritDoc} */
	@Override
	public List<Factor> getRefinedByDirect() {
		return QmModelUtils.getInverse(
				QmPackage.eINSTANCE.getFactor_RefinesDirect(), this,
				Factor.class);
	}

	/** {@inheritDoc} */
	@Override
	public List<FactorRefinement> getRefinedBy() {
		return QmModelUtils.getInverse(
				QmPackage.eINSTANCE.getFactorRefinement_Parent(), this,
				FactorRefinement.class);
	}

	/** {@inheritDoc} */
	@Override
	public List<Impact> getInfluencedBy() {
		return QmModelUtils.getInverse(QmPackage.eINSTANCE.getImpact_Target(),
				this, Impact.class);
	}

	/** {@inheritDoc} */
	@Override
	public List<Measurement> getMeasuredBy() {
		return QmModelUtils.getInverse(
				QmPackage.eINSTANCE.getMeasurement_Parent(), this,
				Measurement.class);
	}

	/** {@inheritDoc} */
	@Override
	public List<Factor> getInfluencedByDirect() {
		return QmModelUtils.getInverse(
				QmPackage.eINSTANCE.getFactor_InfluencesDirect(), this,
				Factor.class);
	}

	/** {@inheritDoc} */
	@Override
	public List<Measure> getMeasuredByDirect() {
		return QmModelUtils.getInverse(
				QmPackage.eINSTANCE.getMeasure_MeasuresDirect(), this,
				Measure.class);
	}

	/** {@inheritDoc} */
	@Override
	public List<Evaluation> getEvaluatedBy() {
		return QmModelUtils.getInverse(
				QmPackage.eINSTANCE.getEvaluation_Evaluates(), this,
				Evaluation.class);
	}

	/** {@inheritDoc} */
	@Override
	public Evaluation getActualEvaluation(QualityModel context) {
		Evaluation actualEvaluation = null;

		for (Evaluation evaluation : getEvaluatedBy()) {
			if (QmModelUtils.requires(context, evaluation)) {
				if (actualEvaluation == null) {
					actualEvaluation = evaluation;
				} else {
					if (QmModelUtils.requires(evaluation, actualEvaluation)) {
						actualEvaluation = evaluation;
					}
				}
			}
		}

		return actualEvaluation;
	}

	@Override
	public HashMap<QualityModel, Double> getAccumulatedCompleteness() {
		Set<QualityModel> leafQMs = new HashSet<QualityModel>();
		getAllLeafsQMs(this.getEvaluatedBy(), leafQMs);

		HashMap<QualityModel, Double> result = new HashMap<QualityModel, Double>();

		for (QualityModel target : leafQMs) {
			Evaluation e = this.getActualEvaluation(target);
			if (e != null) {
				result.put(target, e.getAccumulatedCompleteness(target));
			}
		}

		return result;
	}

	/**
	 * Calculate the leaf QMs of all given evaluations
	 * 
	 * @param evaluatedBy
	 * @param leafQMs
	 */
	private void getAllLeafsQMs(List<Evaluation> evaluatedBy,
			Set<QualityModel> leafQMs) {
		for (Evaluation eval : evaluatedBy) {
			getAllLeafQMs(eval.getQualityModel(), leafQMs);
		}
	}

	/**
	 * Calculate the leaf QMs of the given QM
	 * 
	 * @param qualityModel
	 * @param leafQMs
	 */
	private void getAllLeafQMs(QualityModel qualityModel,
			Set<QualityModel> leafQMs) {
		List<QualityModel> requiredBy = QmModelUtils.getInverse(
				QmPackage.eINSTANCE.getQualityModel_Requires(), qualityModel,
				QualityModel.class);

		if (requiredBy.isEmpty()) {
			leafQMs.add(qualityModel);
		} else {
			for (QualityModel child : requiredBy) {
				getAllLeafQMs(child, leafQMs);
			}
		}

	}

	@Override
	public boolean isQualityAspect() {
		if(this.getRefinesDirect().size() == 0 && this.getQualifiedName().equals("Quality @Product")) {
			return true;
		}
		if(this.getRefinesDirect().size() == 1) {
			return this.getRefinesDirect().get(0).isQualityAspect();
		}
		return false;
	}
} // FactorImpl
