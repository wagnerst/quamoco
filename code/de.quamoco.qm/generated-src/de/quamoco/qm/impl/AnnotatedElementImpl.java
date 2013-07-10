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
 * $Id: AnnotatedElementImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.AnnotationBase;
import de.quamoco.qm.QmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Annotated Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.qm.impl.AnnotatedElementImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.AnnotatedElementImpl#getAdvancedAnnotations <em>Advanced Annotations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AnnotatedElementImpl extends TaggedElementImpl implements AnnotatedElement {
	/**
	 * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnnotations()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> annotations;

	/**
	 * The cached value of the '{@link #getAdvancedAnnotations() <em>Advanced Annotations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdvancedAnnotations()
	 * @generated
	 * @ordered
	 */
	protected EList<AnnotationBase> advancedAnnotations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnnotatedElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QmPackage.Literals.ANNOTATED_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getAnnotations() {
		if (annotations == null) {
			annotations = new EcoreEMap<String,String>(QmPackage.Literals.ANNOTATION, AnnotationImpl.class, this, QmPackage.ANNOTATED_ELEMENT__ANNOTATIONS);
		}
		return annotations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AnnotationBase> getAdvancedAnnotations() {
		if (advancedAnnotations == null) {
			advancedAnnotations = new EObjectContainmentEList<AnnotationBase>(AnnotationBase.class, this, QmPackage.ANNOTATED_ELEMENT__ADVANCED_ANNOTATIONS);
		}
		return advancedAnnotations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QmPackage.ANNOTATED_ELEMENT__ANNOTATIONS:
				return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
			case QmPackage.ANNOTATED_ELEMENT__ADVANCED_ANNOTATIONS:
				return ((InternalEList<?>)getAdvancedAnnotations()).basicRemove(otherEnd, msgs);
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
			case QmPackage.ANNOTATED_ELEMENT__ANNOTATIONS:
				if (coreType) return getAnnotations();
				else return getAnnotations().map();
			case QmPackage.ANNOTATED_ELEMENT__ADVANCED_ANNOTATIONS:
				return getAdvancedAnnotations();
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
			case QmPackage.ANNOTATED_ELEMENT__ANNOTATIONS:
				((EStructuralFeature.Setting)getAnnotations()).set(newValue);
				return;
			case QmPackage.ANNOTATED_ELEMENT__ADVANCED_ANNOTATIONS:
				getAdvancedAnnotations().clear();
				getAdvancedAnnotations().addAll((Collection<? extends AnnotationBase>)newValue);
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
			case QmPackage.ANNOTATED_ELEMENT__ANNOTATIONS:
				getAnnotations().clear();
				return;
			case QmPackage.ANNOTATED_ELEMENT__ADVANCED_ANNOTATIONS:
				getAdvancedAnnotations().clear();
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
			case QmPackage.ANNOTATED_ELEMENT__ANNOTATIONS:
				return annotations != null && !annotations.isEmpty();
			case QmPackage.ANNOTATED_ELEMENT__ADVANCED_ANNOTATIONS:
				return advancedAnnotations != null && !advancedAnnotations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AnnotatedElementImpl
