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
 * $Id: ChecklistImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package edu.tum.cs.conqat.inspection.cm.impl;

import edu.tum.cs.conqat.inspection.cm.Checklist;
import edu.tum.cs.conqat.inspection.cm.CmPackage;
import edu.tum.cs.conqat.inspection.cm.InspectionItem;
import edu.tum.cs.conqat.inspection.cm.InspectionMeasure;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Checklist</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.ChecklistImpl#getInspector <em>Inspector</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.ChecklistImpl#getInspectionItems <em>Inspection Items</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.ChecklistImpl#getInspectionMeasures <em>Inspection Measures</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.ChecklistImpl#getQmFileName <em>Qm File Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ChecklistImpl extends EObjectImpl implements Checklist {
	/**
	 * The default value of the '{@link #getInspector() <em>Inspector</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInspector()
	 * @generated
	 * @ordered
	 */
	protected static final String INSPECTOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInspector() <em>Inspector</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInspector()
	 * @generated
	 * @ordered
	 */
	protected String inspector = INSPECTOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInspectionItems() <em>Inspection Items</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInspectionItems()
	 * @generated
	 * @ordered
	 */
	protected EList<InspectionItem> inspectionItems;

	/**
	 * The cached value of the '{@link #getInspectionMeasures() <em>Inspection Measures</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInspectionMeasures()
	 * @generated
	 * @ordered
	 */
	protected EList<InspectionMeasure> inspectionMeasures;

	/**
	 * The default value of the '{@link #getQmFileName() <em>Qm File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQmFileName()
	 * @generated
	 * @ordered
	 */
	protected static final String QM_FILE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getQmFileName() <em>Qm File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQmFileName()
	 * @generated
	 * @ordered
	 */
	protected String qmFileName = QM_FILE_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ChecklistImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CmPackage.Literals.CHECKLIST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInspector() {
		return inspector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInspector(String newInspector) {
		String oldInspector = inspector;
		inspector = newInspector;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CmPackage.CHECKLIST__INSPECTOR, oldInspector, inspector));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InspectionItem> getInspectionItems() {
		if (inspectionItems == null) {
			inspectionItems = new EObjectContainmentWithInverseEList<InspectionItem>(InspectionItem.class, this, CmPackage.CHECKLIST__INSPECTION_ITEMS, CmPackage.INSPECTION_ITEM__CHECKLIST);
		}
		return inspectionItems;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InspectionMeasure> getInspectionMeasures() {
		if (inspectionMeasures == null) {
			inspectionMeasures = new EObjectContainmentEList<InspectionMeasure>(InspectionMeasure.class, this, CmPackage.CHECKLIST__INSPECTION_MEASURES);
		}
		return inspectionMeasures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getQmFileName() {
		return qmFileName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQmFileName(String newQmFileName) {
		String oldQmFileName = qmFileName;
		qmFileName = newQmFileName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CmPackage.CHECKLIST__QM_FILE_NAME, oldQmFileName, qmFileName));
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
			case CmPackage.CHECKLIST__INSPECTION_ITEMS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInspectionItems()).basicAdd(otherEnd, msgs);
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
			case CmPackage.CHECKLIST__INSPECTION_ITEMS:
				return ((InternalEList<?>)getInspectionItems()).basicRemove(otherEnd, msgs);
			case CmPackage.CHECKLIST__INSPECTION_MEASURES:
				return ((InternalEList<?>)getInspectionMeasures()).basicRemove(otherEnd, msgs);
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
			case CmPackage.CHECKLIST__INSPECTOR:
				return getInspector();
			case CmPackage.CHECKLIST__INSPECTION_ITEMS:
				return getInspectionItems();
			case CmPackage.CHECKLIST__INSPECTION_MEASURES:
				return getInspectionMeasures();
			case CmPackage.CHECKLIST__QM_FILE_NAME:
				return getQmFileName();
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
			case CmPackage.CHECKLIST__INSPECTOR:
				setInspector((String)newValue);
				return;
			case CmPackage.CHECKLIST__INSPECTION_ITEMS:
				getInspectionItems().clear();
				getInspectionItems().addAll((Collection<? extends InspectionItem>)newValue);
				return;
			case CmPackage.CHECKLIST__INSPECTION_MEASURES:
				getInspectionMeasures().clear();
				getInspectionMeasures().addAll((Collection<? extends InspectionMeasure>)newValue);
				return;
			case CmPackage.CHECKLIST__QM_FILE_NAME:
				setQmFileName((String)newValue);
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
			case CmPackage.CHECKLIST__INSPECTOR:
				setInspector(INSPECTOR_EDEFAULT);
				return;
			case CmPackage.CHECKLIST__INSPECTION_ITEMS:
				getInspectionItems().clear();
				return;
			case CmPackage.CHECKLIST__INSPECTION_MEASURES:
				getInspectionMeasures().clear();
				return;
			case CmPackage.CHECKLIST__QM_FILE_NAME:
				setQmFileName(QM_FILE_NAME_EDEFAULT);
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
			case CmPackage.CHECKLIST__INSPECTOR:
				return INSPECTOR_EDEFAULT == null ? inspector != null : !INSPECTOR_EDEFAULT.equals(inspector);
			case CmPackage.CHECKLIST__INSPECTION_ITEMS:
				return inspectionItems != null && !inspectionItems.isEmpty();
			case CmPackage.CHECKLIST__INSPECTION_MEASURES:
				return inspectionMeasures != null && !inspectionMeasures.isEmpty();
			case CmPackage.CHECKLIST__QM_FILE_NAME:
				return QM_FILE_NAME_EDEFAULT == null ? qmFileName != null : !QM_FILE_NAME_EDEFAULT.equals(qmFileName);
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
		result.append(" (inspector: ");
		result.append(inspector);
		result.append(", qmFileName: ");
		result.append(qmFileName);
		result.append(')');
		return result.toString();
	}

} //ChecklistImpl
