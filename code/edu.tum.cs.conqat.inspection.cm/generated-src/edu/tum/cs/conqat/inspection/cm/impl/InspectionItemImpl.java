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
 * $Id: InspectionItemImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package edu.tum.cs.conqat.inspection.cm.impl;

import edu.tum.cs.conqat.inspection.cm.Checklist;
import edu.tum.cs.conqat.inspection.cm.CmPackage;
import edu.tum.cs.conqat.inspection.cm.InspectionEvaluation;
import edu.tum.cs.conqat.inspection.cm.InspectionItem;
import edu.tum.cs.conqat.inspection.cm.InspectionMeasure;
import edu.tum.cs.conqat.inspection.cm.Sample;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Inspection Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.InspectionItemImpl#getChecklist <em>Checklist</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.InspectionItemImpl#getInspectionMeasures <em>Inspection Measures</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.InspectionItemImpl#getSamples <em>Samples</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.InspectionItemImpl#getInspectionEvaluations <em>Inspection Evaluations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InspectionItemImpl extends EObjectImpl implements InspectionItem {
	/**
	 * The cached value of the '{@link #getInspectionMeasures() <em>Inspection Measures</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInspectionMeasures()
	 * @generated
	 * @ordered
	 */
	protected EList<InspectionMeasure> inspectionMeasures;

	/**
	 * The cached value of the '{@link #getSamples() <em>Samples</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSamples()
	 * @generated
	 * @ordered
	 */
	protected EList<Sample> samples;

	/**
	 * The cached value of the '{@link #getInspectionEvaluations() <em>Inspection Evaluations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInspectionEvaluations()
	 * @generated
	 * @ordered
	 */
	protected EList<InspectionEvaluation> inspectionEvaluations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InspectionItemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CmPackage.Literals.INSPECTION_ITEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Checklist getChecklist() {
		if (eContainerFeatureID() != CmPackage.INSPECTION_ITEM__CHECKLIST) return null;
		return (Checklist)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetChecklist(Checklist newChecklist, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newChecklist, CmPackage.INSPECTION_ITEM__CHECKLIST, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChecklist(Checklist newChecklist) {
		if (newChecklist != eInternalContainer() || (eContainerFeatureID() != CmPackage.INSPECTION_ITEM__CHECKLIST && newChecklist != null)) {
			if (EcoreUtil.isAncestor(this, newChecklist))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newChecklist != null)
				msgs = ((InternalEObject)newChecklist).eInverseAdd(this, CmPackage.CHECKLIST__INSPECTION_ITEMS, Checklist.class, msgs);
			msgs = basicSetChecklist(newChecklist, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CmPackage.INSPECTION_ITEM__CHECKLIST, newChecklist, newChecklist));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InspectionMeasure> getInspectionMeasures() {
		if (inspectionMeasures == null) {
			inspectionMeasures = new EObjectResolvingEList<InspectionMeasure>(InspectionMeasure.class, this, CmPackage.INSPECTION_ITEM__INSPECTION_MEASURES);
		}
		return inspectionMeasures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Sample> getSamples() {
		if (samples == null) {
			samples = new EObjectContainmentWithInverseEList<Sample>(Sample.class, this, CmPackage.INSPECTION_ITEM__SAMPLES, CmPackage.SAMPLE__INSPECTION_ITEM);
		}
		return samples;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InspectionEvaluation> getInspectionEvaluations() {
		if (inspectionEvaluations == null) {
			inspectionEvaluations = new EObjectContainmentWithInverseEList<InspectionEvaluation>(InspectionEvaluation.class, this, CmPackage.INSPECTION_ITEM__INSPECTION_EVALUATIONS, CmPackage.INSPECTION_EVALUATION__INSPECTION_ITEM);
		}
		return inspectionEvaluations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CmPackage.INSPECTION_ITEM__CHECKLIST:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetChecklist((Checklist)otherEnd, msgs);
			case CmPackage.INSPECTION_ITEM__SAMPLES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSamples()).basicAdd(otherEnd, msgs);
			case CmPackage.INSPECTION_ITEM__INSPECTION_EVALUATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInspectionEvaluations()).basicAdd(otherEnd, msgs);
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
			case CmPackage.INSPECTION_ITEM__CHECKLIST:
				return basicSetChecklist(null, msgs);
			case CmPackage.INSPECTION_ITEM__SAMPLES:
				return ((InternalEList<?>)getSamples()).basicRemove(otherEnd, msgs);
			case CmPackage.INSPECTION_ITEM__INSPECTION_EVALUATIONS:
				return ((InternalEList<?>)getInspectionEvaluations()).basicRemove(otherEnd, msgs);
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
			case CmPackage.INSPECTION_ITEM__CHECKLIST:
				return eInternalContainer().eInverseRemove(this, CmPackage.CHECKLIST__INSPECTION_ITEMS, Checklist.class, msgs);
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
			case CmPackage.INSPECTION_ITEM__CHECKLIST:
				return getChecklist();
			case CmPackage.INSPECTION_ITEM__INSPECTION_MEASURES:
				return getInspectionMeasures();
			case CmPackage.INSPECTION_ITEM__SAMPLES:
				return getSamples();
			case CmPackage.INSPECTION_ITEM__INSPECTION_EVALUATIONS:
				return getInspectionEvaluations();
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
			case CmPackage.INSPECTION_ITEM__CHECKLIST:
				setChecklist((Checklist)newValue);
				return;
			case CmPackage.INSPECTION_ITEM__INSPECTION_MEASURES:
				getInspectionMeasures().clear();
				getInspectionMeasures().addAll((Collection<? extends InspectionMeasure>)newValue);
				return;
			case CmPackage.INSPECTION_ITEM__SAMPLES:
				getSamples().clear();
				getSamples().addAll((Collection<? extends Sample>)newValue);
				return;
			case CmPackage.INSPECTION_ITEM__INSPECTION_EVALUATIONS:
				getInspectionEvaluations().clear();
				getInspectionEvaluations().addAll((Collection<? extends InspectionEvaluation>)newValue);
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
			case CmPackage.INSPECTION_ITEM__CHECKLIST:
				setChecklist((Checklist)null);
				return;
			case CmPackage.INSPECTION_ITEM__INSPECTION_MEASURES:
				getInspectionMeasures().clear();
				return;
			case CmPackage.INSPECTION_ITEM__SAMPLES:
				getSamples().clear();
				return;
			case CmPackage.INSPECTION_ITEM__INSPECTION_EVALUATIONS:
				getInspectionEvaluations().clear();
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
			case CmPackage.INSPECTION_ITEM__CHECKLIST:
				return getChecklist() != null;
			case CmPackage.INSPECTION_ITEM__INSPECTION_MEASURES:
				return inspectionMeasures != null && !inspectionMeasures.isEmpty();
			case CmPackage.INSPECTION_ITEM__SAMPLES:
				return samples != null && !samples.isEmpty();
			case CmPackage.INSPECTION_ITEM__INSPECTION_EVALUATIONS:
				return inspectionEvaluations != null && !inspectionEvaluations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //InspectionItemImpl
