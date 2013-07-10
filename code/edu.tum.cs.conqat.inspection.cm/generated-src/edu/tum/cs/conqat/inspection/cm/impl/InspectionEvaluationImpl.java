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
 * $Id: InspectionEvaluationImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package edu.tum.cs.conqat.inspection.cm.impl;

import edu.tum.cs.conqat.inspection.cm.CmPackage;
import edu.tum.cs.conqat.inspection.cm.InspectionEvaluation;
import edu.tum.cs.conqat.inspection.cm.InspectionItem;
import edu.tum.cs.conqat.inspection.cm.InspectionMeasure;
import edu.tum.cs.conqat.inspection.cm.Sample;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Inspection Evaluation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.InspectionEvaluationImpl#getValue <em>Value</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.InspectionEvaluationImpl#getSample <em>Sample</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.InspectionEvaluationImpl#getInspectionMeasure <em>Inspection Measure</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.InspectionEvaluationImpl#getInspectionItem <em>Inspection Item</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InspectionEvaluationImpl extends EObjectImpl implements InspectionEvaluation {
	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final int VALUE_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected int value = VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSample() <em>Sample</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSample()
	 * @generated
	 * @ordered
	 */
	protected Sample sample;

	/**
	 * The cached value of the '{@link #getInspectionMeasure() <em>Inspection Measure</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInspectionMeasure()
	 * @generated
	 * @ordered
	 */
	protected InspectionMeasure inspectionMeasure;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InspectionEvaluationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CmPackage.Literals.INSPECTION_EVALUATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(int newValue) {
		int oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CmPackage.INSPECTION_EVALUATION__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sample getSample() {
		if (sample != null && sample.eIsProxy()) {
			InternalEObject oldSample = (InternalEObject)sample;
			sample = (Sample)eResolveProxy(oldSample);
			if (sample != oldSample) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CmPackage.INSPECTION_EVALUATION__SAMPLE, oldSample, sample));
			}
		}
		return sample;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sample basicGetSample() {
		return sample;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSample(Sample newSample) {
		Sample oldSample = sample;
		sample = newSample;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CmPackage.INSPECTION_EVALUATION__SAMPLE, oldSample, sample));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InspectionMeasure getInspectionMeasure() {
		if (inspectionMeasure != null && inspectionMeasure.eIsProxy()) {
			InternalEObject oldInspectionMeasure = (InternalEObject)inspectionMeasure;
			inspectionMeasure = (InspectionMeasure)eResolveProxy(oldInspectionMeasure);
			if (inspectionMeasure != oldInspectionMeasure) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CmPackage.INSPECTION_EVALUATION__INSPECTION_MEASURE, oldInspectionMeasure, inspectionMeasure));
			}
		}
		return inspectionMeasure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InspectionMeasure basicGetInspectionMeasure() {
		return inspectionMeasure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInspectionMeasure(InspectionMeasure newInspectionMeasure) {
		InspectionMeasure oldInspectionMeasure = inspectionMeasure;
		inspectionMeasure = newInspectionMeasure;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CmPackage.INSPECTION_EVALUATION__INSPECTION_MEASURE, oldInspectionMeasure, inspectionMeasure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InspectionItem getInspectionItem() {
		if (eContainerFeatureID() != CmPackage.INSPECTION_EVALUATION__INSPECTION_ITEM) return null;
		return (InspectionItem)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInspectionItem(InspectionItem newInspectionItem, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newInspectionItem, CmPackage.INSPECTION_EVALUATION__INSPECTION_ITEM, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInspectionItem(InspectionItem newInspectionItem) {
		if (newInspectionItem != eInternalContainer() || (eContainerFeatureID() != CmPackage.INSPECTION_EVALUATION__INSPECTION_ITEM && newInspectionItem != null)) {
			if (EcoreUtil.isAncestor(this, newInspectionItem))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInspectionItem != null)
				msgs = ((InternalEObject)newInspectionItem).eInverseAdd(this, CmPackage.INSPECTION_ITEM__INSPECTION_EVALUATIONS, InspectionItem.class, msgs);
			msgs = basicSetInspectionItem(newInspectionItem, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CmPackage.INSPECTION_EVALUATION__INSPECTION_ITEM, newInspectionItem, newInspectionItem));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CmPackage.INSPECTION_EVALUATION__INSPECTION_ITEM:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetInspectionItem((InspectionItem)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CmPackage.INSPECTION_EVALUATION__INSPECTION_ITEM:
				return basicSetInspectionItem(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case CmPackage.INSPECTION_EVALUATION__INSPECTION_ITEM:
				return eInternalContainer().eInverseRemove(this, CmPackage.INSPECTION_ITEM__INSPECTION_EVALUATIONS, InspectionItem.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CmPackage.INSPECTION_EVALUATION__VALUE:
				return getValue();
			case CmPackage.INSPECTION_EVALUATION__SAMPLE:
				if (resolve) return getSample();
				return basicGetSample();
			case CmPackage.INSPECTION_EVALUATION__INSPECTION_MEASURE:
				if (resolve) return getInspectionMeasure();
				return basicGetInspectionMeasure();
			case CmPackage.INSPECTION_EVALUATION__INSPECTION_ITEM:
				return getInspectionItem();
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
			case CmPackage.INSPECTION_EVALUATION__VALUE:
				setValue((Integer)newValue);
				return;
			case CmPackage.INSPECTION_EVALUATION__SAMPLE:
				setSample((Sample)newValue);
				return;
			case CmPackage.INSPECTION_EVALUATION__INSPECTION_MEASURE:
				setInspectionMeasure((InspectionMeasure)newValue);
				return;
			case CmPackage.INSPECTION_EVALUATION__INSPECTION_ITEM:
				setInspectionItem((InspectionItem)newValue);
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
			case CmPackage.INSPECTION_EVALUATION__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case CmPackage.INSPECTION_EVALUATION__SAMPLE:
				setSample((Sample)null);
				return;
			case CmPackage.INSPECTION_EVALUATION__INSPECTION_MEASURE:
				setInspectionMeasure((InspectionMeasure)null);
				return;
			case CmPackage.INSPECTION_EVALUATION__INSPECTION_ITEM:
				setInspectionItem((InspectionItem)null);
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
			case CmPackage.INSPECTION_EVALUATION__VALUE:
				return value != VALUE_EDEFAULT;
			case CmPackage.INSPECTION_EVALUATION__SAMPLE:
				return sample != null;
			case CmPackage.INSPECTION_EVALUATION__INSPECTION_MEASURE:
				return inspectionMeasure != null;
			case CmPackage.INSPECTION_EVALUATION__INSPECTION_ITEM:
				return getInspectionItem() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (value: ");
		result.append(value);
		result.append(')');
		return result.toString();
	}

} //InspectionEvaluationImpl
