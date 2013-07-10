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
 * $Id: MeasureImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.impl;

import java.util.Collection;
import java.util.List;

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
import de.quamoco.qm.Factor;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasureRefinement;
import de.quamoco.qm.Measurement;
import de.quamoco.qm.MeasurementMethod;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.Type;
import de.quamoco.qm.util.QmModelUtils;
import de.quamoco.qm.util.ReferenceClassProxyList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Measure</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.qm.impl.MeasureImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.MeasureImpl#getMeasures <em>Measures</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.MeasureImpl#getMeasuresDirect <em>Measures Direct</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.MeasureImpl#getQualityModel <em>Quality Model</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.MeasureImpl#getRefines <em>Refines</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.MeasureImpl#getRefinesDirect <em>Refines Direct</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MeasureImpl extends CharacterizingElementImpl implements Measure {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final Type TYPE_EDEFAULT = Type.NONE;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected Type type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMeasures() <em>Measures</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getMeasures()
	 * @generated
	 * @ordered
	 */
	protected EList<Measurement> measures;

	/**
	 * The cached value of the '{@link #getRefines() <em>Refines</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRefines()
	 * @generated
	 * @ordered
	 */
	protected EList<MeasureRefinement> refines;

	/** Proxy for derived reference measuresDirect. */
	private EList<Factor> measuresDirect;

	/** Proxy for derived reference refinesDirect. */
	private EList<Measure> refinesDirect;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected MeasureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QmPackage.Literals.MEASURE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Type getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(Type newType) {
		Type oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.MEASURE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Measurement> getMeasures() {
		if (measures == null) {
			measures = new EObjectContainmentWithInverseEList<Measurement>(Measurement.class, this, QmPackage.MEASURE__MEASURES, QmPackage.MEASUREMENT__CHILD);
		}
		return measures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<Factor> getMeasuresDirect() {
		if (measuresDirect == null) {
			measuresDirect = new ReferenceClassProxyList<Measurement, Factor>(
					this, QmPackage.eINSTANCE.getMeasure_MeasuresDirect(),
					QmPackage.eINSTANCE.getMeasure_Measures(),
					QmPackage.eINSTANCE.getMeasurement(), QmPackage.eINSTANCE
							.getMeasurement_Parent());
		}
		return measuresDirect;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public QualityModel getQualityModel() {
		if (eContainerFeatureID() != QmPackage.MEASURE__QUALITY_MODEL) return null;
		return (QualityModel)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQualityModel(QualityModel newQualityModel,
			NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newQualityModel, QmPackage.MEASURE__QUALITY_MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualityModel(QualityModel newQualityModel) {
		if (newQualityModel != eInternalContainer() || (eContainerFeatureID() != QmPackage.MEASURE__QUALITY_MODEL && newQualityModel != null)) {
			if (EcoreUtil.isAncestor(this, newQualityModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQualityModel != null)
				msgs = ((InternalEObject)newQualityModel).eInverseAdd(this, QmPackage.QUALITY_MODEL__MEASURES, QualityModel.class, msgs);
			msgs = basicSetQualityModel(newQualityModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.MEASURE__QUALITY_MODEL, newQualityModel, newQualityModel));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MeasureRefinement> getRefines() {
		if (refines == null) {
			refines = new EObjectContainmentWithInverseEList<MeasureRefinement>(MeasureRefinement.class, this, QmPackage.MEASURE__REFINES, QmPackage.MEASURE_REFINEMENT__CHILD);
		}
		return refines;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<Measure> getRefinesDirect() {
		if (refinesDirect == null) {
			refinesDirect = new ReferenceClassProxyList<MeasureRefinement, Measure>(
					this, QmPackage.eINSTANCE.getMeasure_RefinesDirect(),
					QmPackage.eINSTANCE.getMeasure_Refines(),
					QmPackage.eINSTANCE.getMeasureRefinement(),
					QmPackage.eINSTANCE.getMeasureRefinement_Parent());
		}
		return refinesDirect;
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
			case QmPackage.MEASURE__MEASURES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMeasures()).basicAdd(otherEnd, msgs);
			case QmPackage.MEASURE__QUALITY_MODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetQualityModel((QualityModel)otherEnd, msgs);
			case QmPackage.MEASURE__REFINES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRefines()).basicAdd(otherEnd, msgs);
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
			case QmPackage.MEASURE__MEASURES:
				return ((InternalEList<?>)getMeasures()).basicRemove(otherEnd, msgs);
			case QmPackage.MEASURE__QUALITY_MODEL:
				return basicSetQualityModel(null, msgs);
			case QmPackage.MEASURE__REFINES:
				return ((InternalEList<?>)getRefines()).basicRemove(otherEnd, msgs);
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
			case QmPackage.MEASURE__QUALITY_MODEL:
				return eInternalContainer().eInverseRemove(this, QmPackage.QUALITY_MODEL__MEASURES, QualityModel.class, msgs);
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
			case QmPackage.MEASURE__TYPE:
				return getType();
			case QmPackage.MEASURE__MEASURES:
				return getMeasures();
			case QmPackage.MEASURE__MEASURES_DIRECT:
				return getMeasuresDirect();
			case QmPackage.MEASURE__QUALITY_MODEL:
				return getQualityModel();
			case QmPackage.MEASURE__REFINES:
				return getRefines();
			case QmPackage.MEASURE__REFINES_DIRECT:
				return getRefinesDirect();
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
			case QmPackage.MEASURE__TYPE:
				setType((Type)newValue);
				return;
			case QmPackage.MEASURE__MEASURES:
				getMeasures().clear();
				getMeasures().addAll((Collection<? extends Measurement>)newValue);
				return;
			case QmPackage.MEASURE__MEASURES_DIRECT:
				getMeasuresDirect().clear();
				getMeasuresDirect().addAll((Collection<? extends Factor>)newValue);
				return;
			case QmPackage.MEASURE__QUALITY_MODEL:
				setQualityModel((QualityModel)newValue);
				return;
			case QmPackage.MEASURE__REFINES:
				getRefines().clear();
				getRefines().addAll((Collection<? extends MeasureRefinement>)newValue);
				return;
			case QmPackage.MEASURE__REFINES_DIRECT:
				getRefinesDirect().clear();
				getRefinesDirect().addAll((Collection<? extends Measure>)newValue);
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
			case QmPackage.MEASURE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case QmPackage.MEASURE__MEASURES:
				getMeasures().clear();
				return;
			case QmPackage.MEASURE__MEASURES_DIRECT:
				getMeasuresDirect().clear();
				return;
			case QmPackage.MEASURE__QUALITY_MODEL:
				setQualityModel((QualityModel)null);
				return;
			case QmPackage.MEASURE__REFINES:
				getRefines().clear();
				return;
			case QmPackage.MEASURE__REFINES_DIRECT:
				getRefinesDirect().clear();
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
			case QmPackage.MEASURE__TYPE:
				return type != TYPE_EDEFAULT;
			case QmPackage.MEASURE__MEASURES:
				return measures != null && !measures.isEmpty();
			case QmPackage.MEASURE__MEASURES_DIRECT:
				return !getMeasuresDirect().isEmpty();
			case QmPackage.MEASURE__QUALITY_MODEL:
				return getQualityModel() != null;
			case QmPackage.MEASURE__REFINES:
				return refines != null && !refines.isEmpty();
			case QmPackage.MEASURE__REFINES_DIRECT:
				return !getRefinesDirect().isEmpty();
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
		result.append(" (type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

	/** {@inheritDoc} */
	@Override
	public String getQualifiedName() {
		String qualifiedName = "";
		if (getName() != null) {
			qualifiedName = getName();
		}

		Entity entity = this.getCharacterizes();
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
	public List<Measure> getRefinedByDirect() {
		return QmModelUtils.getInverse(QmPackage.eINSTANCE
				.getMeasure_RefinesDirect(), this, Measure.class);
	}

	/** {@inheritDoc} */
	@Override
	public List<MeasureRefinement> getRefinedBy() {
		return QmModelUtils.getInverse(QmPackage.eINSTANCE
				.getMeasureRefinement_Parent(), this, MeasureRefinement.class);
	}

	/** {@inheritDoc} */
	@Override
	public List<MeasurementMethod> getDeterminedBy() {
		return QmModelUtils.getInverse(QmPackage.eINSTANCE
				.getMeasurementMethod_Determines(), this,
				MeasurementMethod.class);
	}

	/** {@inheritDoc} */
	@Override
	public MeasurementMethod getActualMeasurementMethod(QualityModel context) {
		MeasurementMethod actualMeasurementMethod = null;

		for (MeasurementMethod measurementMethod : getDeterminedBy()) {
			if (QmModelUtils.requires(context, measurementMethod)) {
				if (actualMeasurementMethod == null) {
					actualMeasurementMethod = measurementMethod;
				} else {
					if (QmModelUtils.requires(measurementMethod,
							actualMeasurementMethod)) {
						actualMeasurementMethod = measurementMethod;
					}
				}
			}
		}

		return actualMeasurementMethod;
	}
} // MeasureImpl
