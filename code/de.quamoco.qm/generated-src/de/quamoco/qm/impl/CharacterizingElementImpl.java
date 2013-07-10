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
 * $Id: CharacterizingElementImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.quamoco.qm.CharacterizingElement;
import de.quamoco.qm.Entity;
import de.quamoco.qm.QmPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Characterizing Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.qm.impl.CharacterizingElementImpl#getCharacterizes <em>Characterizes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class CharacterizingElementImpl extends NamedElementImpl
		implements CharacterizingElement {
	/**
	 * The cached value of the '{@link #getCharacterizes() <em>Characterizes</em>}' reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getCharacterizes()
	 * @generated
	 * @ordered
	 */
	protected Entity characterizes;

	/**
	 * This is true if the Characterizes reference has been set. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean characterizesESet;

	/** @generated not */
	private Object evaluationResult;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected CharacterizingElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QmPackage.Literals.CHARACTERIZING_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Entity getCharacterizes() {
		if (characterizes != null && characterizes.eIsProxy()) {
			InternalEObject oldCharacterizes = (InternalEObject)characterizes;
			characterizes = (Entity)eResolveProxy(oldCharacterizes);
			if (characterizes != oldCharacterizes) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, QmPackage.CHARACTERIZING_ELEMENT__CHARACTERIZES, oldCharacterizes, characterizes));
			}
		}
		return characterizes;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Entity basicGetCharacterizes() {
		return characterizes;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setCharacterizes(Entity newCharacterizes) {
		Entity oldCharacterizes = characterizes;
		characterizes = newCharacterizes;
		boolean oldCharacterizesESet = characterizesESet;
		characterizesESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.CHARACTERIZING_ELEMENT__CHARACTERIZES, oldCharacterizes, characterizes, !oldCharacterizesESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetCharacterizes() {
		Entity oldCharacterizes = characterizes;
		boolean oldCharacterizesESet = characterizesESet;
		characterizes = null;
		characterizesESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, QmPackage.CHARACTERIZING_ELEMENT__CHARACTERIZES, oldCharacterizes, null, oldCharacterizesESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetCharacterizes() {
		return characterizesESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case QmPackage.CHARACTERIZING_ELEMENT__CHARACTERIZES:
				if (resolve) return getCharacterizes();
				return basicGetCharacterizes();
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
			case QmPackage.CHARACTERIZING_ELEMENT__CHARACTERIZES:
				setCharacterizes((Entity)newValue);
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
			case QmPackage.CHARACTERIZING_ELEMENT__CHARACTERIZES:
				unsetCharacterizes();
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
			case QmPackage.CHARACTERIZING_ELEMENT__CHARACTERIZES:
				return isSetCharacterizes();
		}
		return super.eIsSet(featureID);
	}
} // CharacterizingElementImpl
