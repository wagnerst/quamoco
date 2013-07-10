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
 * $Id: EntityImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.impl;

import java.util.Collection;
import java.util.Iterator;
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

import de.quamoco.qm.CharacterizingElement;
import de.quamoco.qm.Decomposition;
import de.quamoco.qm.Entity;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.Specialization;
import de.quamoco.qm.util.QmModelUtils;
import de.quamoco.qm.util.ReferenceClassProxyElement;
import de.quamoco.qm.util.ReferenceClassProxyList;
import de.quamoco.qm.util.TransitiveReferenceIterator;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Entity Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.qm.impl.EntityImpl#getIsA <em>Is A</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.EntityImpl#getIsADirect <em>Is ADirect</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.EntityImpl#getPartOf <em>Part Of</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.EntityImpl#getPartOfDirect <em>Part Of Direct</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.EntityImpl#getQualityModel <em>Quality Model</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.EntityImpl#isStakeholder <em>Stakeholder</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.EntityImpl#isUseCase <em>Use Case</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EntityImpl extends NamedElementImpl implements Entity {
	/**
	 * The cached value of the '{@link #getIsA() <em>Is A</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIsA()
	 * @generated
	 * @ordered
	 */
	protected EList<Specialization> isA;

	/**
	 * The cached value of the '{@link #getPartOf() <em>Part Of</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPartOf()
	 * @generated
	 * @ordered
	 */
	protected Decomposition partOf;

	/** Proxy for derived reference partOfDirect. */
	private ReferenceClassProxyElement<Decomposition, Entity> partOfDirect;

	/** Proxy for derived reference isADirect. */
	private EList<Entity> isADirect;

	/**
	 * The default value of the '{@link #isStakeholder() <em>Stakeholder</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isStakeholder()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STAKEHOLDER_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isUseCase() <em>Use Case</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isUseCase()
	 * @generated
	 * @ordered
	 */
	protected static final boolean USE_CASE_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected EntityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QmPackage.Literals.ENTITY;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Specialization> getIsA() {
		if (isA == null) {
			isA = new EObjectContainmentWithInverseEList<Specialization>(Specialization.class, this, QmPackage.ENTITY__IS_A, QmPackage.SPECIALIZATION__CHILD);
		}
		return isA;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<Entity> getIsADirect() {
		if (isADirect == null) {
			isADirect = new ReferenceClassProxyList<Specialization, Entity>(this,
					QmPackage.eINSTANCE.getEntity_IsADirect(),
					QmPackage.eINSTANCE.getEntity_IsA(), QmPackage.eINSTANCE
							.getSpecialization(), QmPackage.eINSTANCE
							.getSpecialization_Parent());
		}
		return isADirect;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Decomposition getPartOf() {
		return partOf;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPartOf(Decomposition newPartOf,
			NotificationChain msgs) {
		Decomposition oldPartOf = partOf;
		partOf = newPartOf;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, QmPackage.ENTITY__PART_OF, oldPartOf, newPartOf);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPartOf(Decomposition newPartOf) {
		if (newPartOf != partOf) {
			NotificationChain msgs = null;
			if (partOf != null)
				msgs = ((InternalEObject)partOf).eInverseRemove(this, QmPackage.DECOMPOSITION__CHILD, Decomposition.class, msgs);
			if (newPartOf != null)
				msgs = ((InternalEObject)newPartOf).eInverseAdd(this, QmPackage.DECOMPOSITION__CHILD, Decomposition.class, msgs);
			msgs = basicSetPartOf(newPartOf, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.ENTITY__PART_OF, newPartOf, newPartOf));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Entity getPartOfDirect() {
		return basicGetPartOfDirect();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Entity basicGetPartOfDirect() {
		if (partOfDirect == null) {
			partOfDirect = new ReferenceClassProxyElement<Decomposition, Entity>(this,
					QmPackage.eINSTANCE.getEntity_PartOfDirect(),
					QmPackage.eINSTANCE.getEntity_PartOf(), QmPackage.eINSTANCE
							.getDecomposition(), QmPackage.eINSTANCE
							.getDecomposition_Parent());
		}
		return partOfDirect.get();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setPartOfDirect(Entity newPartOfDirect) {
		basicGetPartOfDirect();
		partOfDirect.set(newPartOfDirect);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void unsetPartOfDirect() {
		basicGetPartOfDirect();
		partOfDirect.unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isSetPartOfDirect() {
		basicGetPartOfDirect();
		return partOfDirect.isSet();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public QualityModel getQualityModel() {
		if (eContainerFeatureID() != QmPackage.ENTITY__QUALITY_MODEL) return null;
		return (QualityModel)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQualityModel(QualityModel newQualityModel,
			NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newQualityModel, QmPackage.ENTITY__QUALITY_MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualityModel(QualityModel newQualityModel) {
		if (newQualityModel != eInternalContainer() || (eContainerFeatureID() != QmPackage.ENTITY__QUALITY_MODEL && newQualityModel != null)) {
			if (EcoreUtil.isAncestor(this, newQualityModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQualityModel != null)
				msgs = ((InternalEObject)newQualityModel).eInverseAdd(this, QmPackage.QUALITY_MODEL__ENTITIES, QualityModel.class, msgs);
			msgs = basicSetQualityModel(newQualityModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.ENTITY__QUALITY_MODEL, newQualityModel, newQualityModel));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isStakeholder() {
		for (Iterator<Entity> i = new TransitiveReferenceIterator<Entity>(this,
				QmPackage.eINSTANCE.getEntity_IsADirect()); i.hasNext();) {
			Entity e = i.next();
			if ("Stakeholder".equals(e.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isUseCase() {
		for (Iterator<Entity> i = new TransitiveReferenceIterator<Entity>(this,
				QmPackage.eINSTANCE.getEntity_IsADirect()); i.hasNext();) {
			Entity e = i.next();
			if ("UseCase".equals(e.getName())) {
				return true;
			}
		}
		return false;
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
			case QmPackage.ENTITY__IS_A:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIsA()).basicAdd(otherEnd, msgs);
			case QmPackage.ENTITY__PART_OF:
				if (partOf != null)
					msgs = ((InternalEObject)partOf).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - QmPackage.ENTITY__PART_OF, null, msgs);
				return basicSetPartOf((Decomposition)otherEnd, msgs);
			case QmPackage.ENTITY__QUALITY_MODEL:
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
			case QmPackage.ENTITY__IS_A:
				return ((InternalEList<?>)getIsA()).basicRemove(otherEnd, msgs);
			case QmPackage.ENTITY__PART_OF:
				return basicSetPartOf(null, msgs);
			case QmPackage.ENTITY__QUALITY_MODEL:
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
			case QmPackage.ENTITY__QUALITY_MODEL:
				return eInternalContainer().eInverseRemove(this, QmPackage.QUALITY_MODEL__ENTITIES, QualityModel.class, msgs);
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
			case QmPackage.ENTITY__IS_A:
				return getIsA();
			case QmPackage.ENTITY__IS_ADIRECT:
				return getIsADirect();
			case QmPackage.ENTITY__PART_OF:
				return getPartOf();
			case QmPackage.ENTITY__PART_OF_DIRECT:
				if (resolve) return getPartOfDirect();
				return basicGetPartOfDirect();
			case QmPackage.ENTITY__QUALITY_MODEL:
				return getQualityModel();
			case QmPackage.ENTITY__STAKEHOLDER:
				return isStakeholder();
			case QmPackage.ENTITY__USE_CASE:
				return isUseCase();
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
			case QmPackage.ENTITY__IS_A:
				getIsA().clear();
				getIsA().addAll((Collection<? extends Specialization>)newValue);
				return;
			case QmPackage.ENTITY__IS_ADIRECT:
				getIsADirect().clear();
				getIsADirect().addAll((Collection<? extends Entity>)newValue);
				return;
			case QmPackage.ENTITY__PART_OF:
				setPartOf((Decomposition)newValue);
				return;
			case QmPackage.ENTITY__PART_OF_DIRECT:
				setPartOfDirect((Entity)newValue);
				return;
			case QmPackage.ENTITY__QUALITY_MODEL:
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
			case QmPackage.ENTITY__IS_A:
				getIsA().clear();
				return;
			case QmPackage.ENTITY__IS_ADIRECT:
				getIsADirect().clear();
				return;
			case QmPackage.ENTITY__PART_OF:
				setPartOf((Decomposition)null);
				return;
			case QmPackage.ENTITY__PART_OF_DIRECT:
				unsetPartOfDirect();
				return;
			case QmPackage.ENTITY__QUALITY_MODEL:
				setQualityModel((QualityModel)null);
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
			case QmPackage.ENTITY__IS_A:
				return isA != null && !isA.isEmpty();
			case QmPackage.ENTITY__IS_ADIRECT:
				return !getIsADirect().isEmpty();
			case QmPackage.ENTITY__PART_OF:
				return partOf != null;
			case QmPackage.ENTITY__PART_OF_DIRECT:
				return isSetPartOfDirect();
			case QmPackage.ENTITY__QUALITY_MODEL:
				return getQualityModel() != null;
			case QmPackage.ENTITY__STAKEHOLDER:
				return isStakeholder() != STAKEHOLDER_EDEFAULT;
			case QmPackage.ENTITY__USE_CASE:
				return isUseCase() != USE_CASE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return getQualifiedName();
	}

	/** {@inheritDoc} */
	@Override
	public List<Entity> getSpecializedByDirect() {
		return QmModelUtils.getInverse(QmPackage.eINSTANCE
				.getEntity_IsADirect(), this, Entity.class);
	}

	/** {@inheritDoc} */
	@Override
	public List<Entity> getConsistsOfDirect() {
		return QmModelUtils.getInverse(QmPackage.eINSTANCE
				.getEntity_PartOfDirect(), this, Entity.class);
	}

	/** {@inheritDoc} */
	@Override
	public List<CharacterizingElement> getCharacterizedBy() {
		return QmModelUtils.getInverse(QmPackage.eINSTANCE
				.getCharacterizingElement_Characterizes(), this,
				CharacterizingElement.class);
	}

} // EntityImpl
