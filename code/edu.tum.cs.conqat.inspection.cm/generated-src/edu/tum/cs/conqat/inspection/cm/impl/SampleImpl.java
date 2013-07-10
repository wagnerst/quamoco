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
 * $Id: SampleImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package edu.tum.cs.conqat.inspection.cm.impl;

import edu.tum.cs.conqat.inspection.cm.CmPackage;
import edu.tum.cs.conqat.inspection.cm.InspectionItem;
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
 * An implementation of the model object '<em><b>Sample</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.SampleImpl#getName <em>Name</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.SampleImpl#getPackagePath <em>Package Path</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.SampleImpl#getSourcePath <em>Source Path</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.SampleImpl#getLineNumber <em>Line Number</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.SampleImpl#getSourceStart <em>Source Start</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.SampleImpl#getSourceEnd <em>Source End</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.impl.SampleImpl#getInspectionItem <em>Inspection Item</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SampleImpl extends EObjectImpl implements Sample {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPackagePath() <em>Package Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackagePath()
	 * @generated
	 * @ordered
	 */
	protected static final String PACKAGE_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPackagePath() <em>Package Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackagePath()
	 * @generated
	 * @ordered
	 */
	protected String packagePath = PACKAGE_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getSourcePath() <em>Source Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourcePath()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourcePath() <em>Source Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourcePath()
	 * @generated
	 * @ordered
	 */
	protected String sourcePath = SOURCE_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getLineNumber() <em>Line Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineNumber()
	 * @generated
	 * @ordered
	 */
	protected static final int LINE_NUMBER_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getLineNumber() <em>Line Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineNumber()
	 * @generated
	 * @ordered
	 */
	protected int lineNumber = LINE_NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getSourceStart() <em>Source Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceStart()
	 * @generated
	 * @ordered
	 */
	protected static final int SOURCE_START_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getSourceStart() <em>Source Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceStart()
	 * @generated
	 * @ordered
	 */
	protected int sourceStart = SOURCE_START_EDEFAULT;

	/**
	 * The default value of the '{@link #getSourceEnd() <em>Source End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceEnd()
	 * @generated
	 * @ordered
	 */
	protected static final int SOURCE_END_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getSourceEnd() <em>Source End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceEnd()
	 * @generated
	 * @ordered
	 */
	protected int sourceEnd = SOURCE_END_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SampleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CmPackage.Literals.SAMPLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CmPackage.SAMPLE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPackagePath() {
		return packagePath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackagePath(String newPackagePath) {
		String oldPackagePath = packagePath;
		packagePath = newPackagePath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CmPackage.SAMPLE__PACKAGE_PATH, oldPackagePath, packagePath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSourcePath() {
		return sourcePath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourcePath(String newSourcePath) {
		String oldSourcePath = sourcePath;
		sourcePath = newSourcePath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CmPackage.SAMPLE__SOURCE_PATH, oldSourcePath, sourcePath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLineNumber(int newLineNumber) {
		int oldLineNumber = lineNumber;
		lineNumber = newLineNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CmPackage.SAMPLE__LINE_NUMBER, oldLineNumber, lineNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSourceStart() {
		return sourceStart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceStart(int newSourceStart) {
		int oldSourceStart = sourceStart;
		sourceStart = newSourceStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CmPackage.SAMPLE__SOURCE_START, oldSourceStart, sourceStart));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSourceEnd() {
		return sourceEnd;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceEnd(int newSourceEnd) {
		int oldSourceEnd = sourceEnd;
		sourceEnd = newSourceEnd;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CmPackage.SAMPLE__SOURCE_END, oldSourceEnd, sourceEnd));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InspectionItem getInspectionItem() {
		if (eContainerFeatureID() != CmPackage.SAMPLE__INSPECTION_ITEM) return null;
		return (InspectionItem)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInspectionItem(InspectionItem newInspectionItem, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newInspectionItem, CmPackage.SAMPLE__INSPECTION_ITEM, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInspectionItem(InspectionItem newInspectionItem) {
		if (newInspectionItem != eInternalContainer() || (eContainerFeatureID() != CmPackage.SAMPLE__INSPECTION_ITEM && newInspectionItem != null)) {
			if (EcoreUtil.isAncestor(this, newInspectionItem))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInspectionItem != null)
				msgs = ((InternalEObject)newInspectionItem).eInverseAdd(this, CmPackage.INSPECTION_ITEM__SAMPLES, InspectionItem.class, msgs);
			msgs = basicSetInspectionItem(newInspectionItem, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CmPackage.SAMPLE__INSPECTION_ITEM, newInspectionItem, newInspectionItem));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CmPackage.SAMPLE__INSPECTION_ITEM:
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
			case CmPackage.SAMPLE__INSPECTION_ITEM:
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
			case CmPackage.SAMPLE__INSPECTION_ITEM:
				return eInternalContainer().eInverseRemove(this, CmPackage.INSPECTION_ITEM__SAMPLES, InspectionItem.class, msgs);
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
			case CmPackage.SAMPLE__NAME:
				return getName();
			case CmPackage.SAMPLE__PACKAGE_PATH:
				return getPackagePath();
			case CmPackage.SAMPLE__SOURCE_PATH:
				return getSourcePath();
			case CmPackage.SAMPLE__LINE_NUMBER:
				return getLineNumber();
			case CmPackage.SAMPLE__SOURCE_START:
				return getSourceStart();
			case CmPackage.SAMPLE__SOURCE_END:
				return getSourceEnd();
			case CmPackage.SAMPLE__INSPECTION_ITEM:
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
			case CmPackage.SAMPLE__NAME:
				setName((String)newValue);
				return;
			case CmPackage.SAMPLE__PACKAGE_PATH:
				setPackagePath((String)newValue);
				return;
			case CmPackage.SAMPLE__SOURCE_PATH:
				setSourcePath((String)newValue);
				return;
			case CmPackage.SAMPLE__LINE_NUMBER:
				setLineNumber((Integer)newValue);
				return;
			case CmPackage.SAMPLE__SOURCE_START:
				setSourceStart((Integer)newValue);
				return;
			case CmPackage.SAMPLE__SOURCE_END:
				setSourceEnd((Integer)newValue);
				return;
			case CmPackage.SAMPLE__INSPECTION_ITEM:
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
			case CmPackage.SAMPLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CmPackage.SAMPLE__PACKAGE_PATH:
				setPackagePath(PACKAGE_PATH_EDEFAULT);
				return;
			case CmPackage.SAMPLE__SOURCE_PATH:
				setSourcePath(SOURCE_PATH_EDEFAULT);
				return;
			case CmPackage.SAMPLE__LINE_NUMBER:
				setLineNumber(LINE_NUMBER_EDEFAULT);
				return;
			case CmPackage.SAMPLE__SOURCE_START:
				setSourceStart(SOURCE_START_EDEFAULT);
				return;
			case CmPackage.SAMPLE__SOURCE_END:
				setSourceEnd(SOURCE_END_EDEFAULT);
				return;
			case CmPackage.SAMPLE__INSPECTION_ITEM:
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
			case CmPackage.SAMPLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CmPackage.SAMPLE__PACKAGE_PATH:
				return PACKAGE_PATH_EDEFAULT == null ? packagePath != null : !PACKAGE_PATH_EDEFAULT.equals(packagePath);
			case CmPackage.SAMPLE__SOURCE_PATH:
				return SOURCE_PATH_EDEFAULT == null ? sourcePath != null : !SOURCE_PATH_EDEFAULT.equals(sourcePath);
			case CmPackage.SAMPLE__LINE_NUMBER:
				return lineNumber != LINE_NUMBER_EDEFAULT;
			case CmPackage.SAMPLE__SOURCE_START:
				return sourceStart != SOURCE_START_EDEFAULT;
			case CmPackage.SAMPLE__SOURCE_END:
				return sourceEnd != SOURCE_END_EDEFAULT;
			case CmPackage.SAMPLE__INSPECTION_ITEM:
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
		result.append(" (name: ");
		result.append(name);
		result.append(", packagePath: ");
		result.append(packagePath);
		result.append(", sourcePath: ");
		result.append(sourcePath);
		result.append(", lineNumber: ");
		result.append(lineNumber);
		result.append(", sourceStart: ");
		result.append(sourceStart);
		result.append(", sourceEnd: ");
		result.append(sourceEnd);
		result.append(')');
		return result.toString();
	}

} //SampleImpl
