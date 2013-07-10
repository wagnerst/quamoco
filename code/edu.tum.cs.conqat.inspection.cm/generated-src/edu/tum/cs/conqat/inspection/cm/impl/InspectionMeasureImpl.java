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
 * $Id: InspectionMeasureImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package edu.tum.cs.conqat.inspection.cm.impl;

import edu.tum.cs.conqat.inspection.cm.CmPackage;
import edu.tum.cs.conqat.inspection.cm.Documentation;
import edu.tum.cs.conqat.inspection.cm.InspectionMeasure;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Inspection Measure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.InspectionMeasureImpl#getMeasureInfo <em>Measure Info</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.InspectionMeasureImpl#getMeasureName <em>Measure Name</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.InspectionMeasureImpl#getInstrumentInfo <em>Instrument Info</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.InspectionMeasureImpl#getSampleType <em>Sample Type</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.InspectionMeasureImpl#getDocumentations <em>Documentations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InspectionMeasureImpl extends EObjectImpl implements InspectionMeasure {
	/**
	 * The default value of the '{@link #getMeasureInfo() <em>Measure Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeasureInfo()
	 * @generated
	 * @ordered
	 */
	protected static final String MEASURE_INFO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMeasureInfo() <em>Measure Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeasureInfo()
	 * @generated
	 * @ordered
	 */
	protected String measureInfo = MEASURE_INFO_EDEFAULT;

	/**
	 * The default value of the '{@link #getMeasureName() <em>Measure Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeasureName()
	 * @generated
	 * @ordered
	 */
	protected static final String MEASURE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMeasureName() <em>Measure Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeasureName()
	 * @generated
	 * @ordered
	 */
	protected String measureName = MEASURE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getInstrumentInfo() <em>Instrument Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstrumentInfo()
	 * @generated
	 * @ordered
	 */
	protected static final String INSTRUMENT_INFO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInstrumentInfo() <em>Instrument Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstrumentInfo()
	 * @generated
	 * @ordered
	 */
	protected String instrumentInfo = INSTRUMENT_INFO_EDEFAULT;

	/**
	 * The default value of the '{@link #getSampleType() <em>Sample Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSampleType()
	 * @generated
	 * @ordered
	 */
	protected static final String SAMPLE_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSampleType() <em>Sample Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSampleType()
	 * @generated
	 * @ordered
	 */
	protected String sampleType = SAMPLE_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDocumentations() <em>Documentations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentations()
	 * @generated
	 * @ordered
	 */
	protected EList<Documentation> documentations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InspectionMeasureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CmPackage.Literals.INSPECTION_MEASURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMeasureInfo() {
		return measureInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMeasureInfo(String newMeasureInfo) {
		String oldMeasureInfo = measureInfo;
		measureInfo = newMeasureInfo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CmPackage.INSPECTION_MEASURE__MEASURE_INFO, oldMeasureInfo, measureInfo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMeasureName() {
		return measureName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMeasureName(String newMeasureName) {
		String oldMeasureName = measureName;
		measureName = newMeasureName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CmPackage.INSPECTION_MEASURE__MEASURE_NAME, oldMeasureName, measureName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInstrumentInfo() {
		return instrumentInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstrumentInfo(String newInstrumentInfo) {
		String oldInstrumentInfo = instrumentInfo;
		instrumentInfo = newInstrumentInfo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CmPackage.INSPECTION_MEASURE__INSTRUMENT_INFO, oldInstrumentInfo, instrumentInfo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSampleType() {
		return sampleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSampleType(String newSampleType) {
		String oldSampleType = sampleType;
		sampleType = newSampleType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CmPackage.INSPECTION_MEASURE__SAMPLE_TYPE, oldSampleType, sampleType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Documentation> getDocumentations() {
		if (documentations == null) {
			documentations = new EObjectContainmentWithInverseEList<Documentation>(Documentation.class, this, CmPackage.INSPECTION_MEASURE__DOCUMENTATIONS, CmPackage.DOCUMENTATION__INSPECTION_MEASURE);
		}
		return documentations;
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
			case CmPackage.INSPECTION_MEASURE__DOCUMENTATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDocumentations()).basicAdd(otherEnd, msgs);
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
			case CmPackage.INSPECTION_MEASURE__DOCUMENTATIONS:
				return ((InternalEList<?>)getDocumentations()).basicRemove(otherEnd, msgs);
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
			case CmPackage.INSPECTION_MEASURE__MEASURE_INFO:
				return getMeasureInfo();
			case CmPackage.INSPECTION_MEASURE__MEASURE_NAME:
				return getMeasureName();
			case CmPackage.INSPECTION_MEASURE__INSTRUMENT_INFO:
				return getInstrumentInfo();
			case CmPackage.INSPECTION_MEASURE__SAMPLE_TYPE:
				return getSampleType();
			case CmPackage.INSPECTION_MEASURE__DOCUMENTATIONS:
				return getDocumentations();
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
			case CmPackage.INSPECTION_MEASURE__MEASURE_INFO:
				setMeasureInfo((String)newValue);
				return;
			case CmPackage.INSPECTION_MEASURE__MEASURE_NAME:
				setMeasureName((String)newValue);
				return;
			case CmPackage.INSPECTION_MEASURE__INSTRUMENT_INFO:
				setInstrumentInfo((String)newValue);
				return;
			case CmPackage.INSPECTION_MEASURE__SAMPLE_TYPE:
				setSampleType((String)newValue);
				return;
			case CmPackage.INSPECTION_MEASURE__DOCUMENTATIONS:
				getDocumentations().clear();
				getDocumentations().addAll((Collection<? extends Documentation>)newValue);
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
			case CmPackage.INSPECTION_MEASURE__MEASURE_INFO:
				setMeasureInfo(MEASURE_INFO_EDEFAULT);
				return;
			case CmPackage.INSPECTION_MEASURE__MEASURE_NAME:
				setMeasureName(MEASURE_NAME_EDEFAULT);
				return;
			case CmPackage.INSPECTION_MEASURE__INSTRUMENT_INFO:
				setInstrumentInfo(INSTRUMENT_INFO_EDEFAULT);
				return;
			case CmPackage.INSPECTION_MEASURE__SAMPLE_TYPE:
				setSampleType(SAMPLE_TYPE_EDEFAULT);
				return;
			case CmPackage.INSPECTION_MEASURE__DOCUMENTATIONS:
				getDocumentations().clear();
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
			case CmPackage.INSPECTION_MEASURE__MEASURE_INFO:
				return MEASURE_INFO_EDEFAULT == null ? measureInfo != null : !MEASURE_INFO_EDEFAULT.equals(measureInfo);
			case CmPackage.INSPECTION_MEASURE__MEASURE_NAME:
				return MEASURE_NAME_EDEFAULT == null ? measureName != null : !MEASURE_NAME_EDEFAULT.equals(measureName);
			case CmPackage.INSPECTION_MEASURE__INSTRUMENT_INFO:
				return INSTRUMENT_INFO_EDEFAULT == null ? instrumentInfo != null : !INSTRUMENT_INFO_EDEFAULT.equals(instrumentInfo);
			case CmPackage.INSPECTION_MEASURE__SAMPLE_TYPE:
				return SAMPLE_TYPE_EDEFAULT == null ? sampleType != null : !SAMPLE_TYPE_EDEFAULT.equals(sampleType);
			case CmPackage.INSPECTION_MEASURE__DOCUMENTATIONS:
				return documentations != null && !documentations.isEmpty();
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
		result.append(" (measureInfo: ");
		result.append(measureInfo);
		result.append(", measureName: ");
		result.append(measureName);
		result.append(", instrumentInfo: ");
		result.append(instrumentInfo);
		result.append(", sampleType: ");
		result.append(sampleType);
		result.append(')');
		return result.toString();
	}

} //InspectionMeasureImpl
