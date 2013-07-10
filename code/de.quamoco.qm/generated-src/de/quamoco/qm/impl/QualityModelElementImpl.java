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
 * $Id: QualityModelElementImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.QualityModelElement;
import de.quamoco.qm.Source;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Quality Model Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.qm.impl.QualityModelElementImpl#getQualifiedName <em>Qualified Name</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.QualityModelElementImpl#getOriginatesFrom <em>Originates From</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class QualityModelElementImpl extends EObjectImpl implements QualityModelElement {
	/**
	 * The default value of the '{@link #getQualifiedName() <em>Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualifiedName()
	 * @generated
	 * @ordered
	 */
	protected static final String QUALIFIED_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOriginatesFrom() <em>Originates From</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginatesFrom()
	 * @generated
	 * @ordered
	 */
	protected EList<Source> originatesFrom;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QualityModelElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QmPackage.Literals.QUALITY_MODEL_ELEMENT;
	}

	/** {@inheritDoc} */
	public String getQualifiedName() {
		return "";
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Source> getOriginatesFrom() {
		if (originatesFrom == null) {
			originatesFrom = new EObjectResolvingEList<Source>(Source.class, this, QmPackage.QUALITY_MODEL_ELEMENT__ORIGINATES_FROM);
		}
		return originatesFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case QmPackage.QUALITY_MODEL_ELEMENT__QUALIFIED_NAME:
				return getQualifiedName();
			case QmPackage.QUALITY_MODEL_ELEMENT__ORIGINATES_FROM:
				return getOriginatesFrom();
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
			case QmPackage.QUALITY_MODEL_ELEMENT__ORIGINATES_FROM:
				getOriginatesFrom().clear();
				getOriginatesFrom().addAll((Collection<? extends Source>)newValue);
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
			case QmPackage.QUALITY_MODEL_ELEMENT__ORIGINATES_FROM:
				getOriginatesFrom().clear();
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
			case QmPackage.QUALITY_MODEL_ELEMENT__QUALIFIED_NAME:
				return QUALIFIED_NAME_EDEFAULT == null ? getQualifiedName() != null : !QUALIFIED_NAME_EDEFAULT.equals(getQualifiedName());
			case QmPackage.QUALITY_MODEL_ELEMENT__ORIGINATES_FROM:
				return originatesFrom != null && !originatesFrom.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/** {@inheritDoc} */
	@Override
	public QualityModel getQualityModel() {
		EObject parent = this;
		while (parent != null && !(parent instanceof QualityModel)) {
			parent = parent.eContainer();
		}
		return (QualityModel) parent;
	}

} //QualityModelElementImpl
