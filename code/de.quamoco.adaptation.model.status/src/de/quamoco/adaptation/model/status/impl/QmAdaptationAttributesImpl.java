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
 * $Id$
 */
package de.quamoco.adaptation.model.status.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.quamoco.adaptation.model.status.QmAdaptationAttributes;
import de.quamoco.adaptation.model.status.StatusPackage;
import de.quamoco.qm.Entity;
import de.quamoco.qm.Factor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Qm Adaptation Attributes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.QmAdaptationAttributesImpl#getObject <em>Object</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.QmAdaptationAttributesImpl#getViewpoint <em>Viewpoint</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.QmAdaptationAttributesImpl#getQualityFocus <em>Quality Focus</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QmAdaptationAttributesImpl extends AdaptationAttributesImpl implements QmAdaptationAttributes {
	/**
	 * The cached value of the '{@link #getObject() <em>Object</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObject()
	 * @generated
	 * @ordered
	 */
	protected Entity object;

	/**
	 * The cached value of the '{@link #getViewpoint() <em>Viewpoint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViewpoint()
	 * @generated
	 * @ordered
	 */
	protected Factor viewpoint;

	/**
	 * The cached value of the '{@link #getQualityFocus() <em>Quality Focus</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualityFocus()
	 * @generated
	 * @ordered
	 */
	protected Factor qualityFocus;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QmAdaptationAttributesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatusPackage.Literals.QM_ADAPTATION_ATTRIBUTES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entity getObject() {
		if (object != null && object.eIsProxy()) {
			InternalEObject oldObject = (InternalEObject)object;
			object = (Entity)eResolveProxy(oldObject);
			if (object != oldObject) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StatusPackage.QM_ADAPTATION_ATTRIBUTES__OBJECT, oldObject, object));
			}
		}
		return object;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entity basicGetObject() {
		return object;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setObject(Entity newObject) {
		Entity oldObject = object;
		object = newObject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatusPackage.QM_ADAPTATION_ATTRIBUTES__OBJECT, oldObject, object));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Factor getViewpoint() {
		if (viewpoint != null && viewpoint.eIsProxy()) {
			InternalEObject oldViewpoint = (InternalEObject)viewpoint;
			viewpoint = (Factor)eResolveProxy(oldViewpoint);
			if (viewpoint != oldViewpoint) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StatusPackage.QM_ADAPTATION_ATTRIBUTES__VIEWPOINT, oldViewpoint, viewpoint));
			}
		}
		return viewpoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Factor basicGetViewpoint() {
		return viewpoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setViewpoint(Factor newViewpoint) {
		Factor oldViewpoint = viewpoint;
		viewpoint = newViewpoint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatusPackage.QM_ADAPTATION_ATTRIBUTES__VIEWPOINT, oldViewpoint, viewpoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Factor getQualityFocus() {
		if (qualityFocus != null && qualityFocus.eIsProxy()) {
			InternalEObject oldQualityFocus = (InternalEObject)qualityFocus;
			qualityFocus = (Factor)eResolveProxy(oldQualityFocus);
			if (qualityFocus != oldQualityFocus) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StatusPackage.QM_ADAPTATION_ATTRIBUTES__QUALITY_FOCUS, oldQualityFocus, qualityFocus));
			}
		}
		return qualityFocus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Factor basicGetQualityFocus() {
		return qualityFocus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualityFocus(Factor newQualityFocus) {
		Factor oldQualityFocus = qualityFocus;
		qualityFocus = newQualityFocus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatusPackage.QM_ADAPTATION_ATTRIBUTES__QUALITY_FOCUS, oldQualityFocus, qualityFocus));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StatusPackage.QM_ADAPTATION_ATTRIBUTES__OBJECT:
				if (resolve) return getObject();
				return basicGetObject();
			case StatusPackage.QM_ADAPTATION_ATTRIBUTES__VIEWPOINT:
				if (resolve) return getViewpoint();
				return basicGetViewpoint();
			case StatusPackage.QM_ADAPTATION_ATTRIBUTES__QUALITY_FOCUS:
				if (resolve) return getQualityFocus();
				return basicGetQualityFocus();
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
			case StatusPackage.QM_ADAPTATION_ATTRIBUTES__OBJECT:
				setObject((Entity)newValue);
				return;
			case StatusPackage.QM_ADAPTATION_ATTRIBUTES__VIEWPOINT:
				setViewpoint((Factor)newValue);
				return;
			case StatusPackage.QM_ADAPTATION_ATTRIBUTES__QUALITY_FOCUS:
				setQualityFocus((Factor)newValue);
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
			case StatusPackage.QM_ADAPTATION_ATTRIBUTES__OBJECT:
				setObject((Entity)null);
				return;
			case StatusPackage.QM_ADAPTATION_ATTRIBUTES__VIEWPOINT:
				setViewpoint((Factor)null);
				return;
			case StatusPackage.QM_ADAPTATION_ATTRIBUTES__QUALITY_FOCUS:
				setQualityFocus((Factor)null);
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
			case StatusPackage.QM_ADAPTATION_ATTRIBUTES__OBJECT:
				return object != null;
			case StatusPackage.QM_ADAPTATION_ATTRIBUTES__VIEWPOINT:
				return viewpoint != null;
			case StatusPackage.QM_ADAPTATION_ATTRIBUTES__QUALITY_FOCUS:
				return qualityFocus != null;
		}
		return super.eIsSet(featureID);
	}

} //QmAdaptationAttributesImpl
