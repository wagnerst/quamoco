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

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.quamoco.adaptation.model.status.AdaptationHistoryItem;
import de.quamoco.adaptation.model.status.AdaptationSource;
import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.model.status.StatusPackage;
import de.quamoco.qm.NamedElement;
import de.quamoco.qm.QualityModelElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Adaptation History Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.AdaptationHistoryItemImpl#getPerformedActionName <em>Performed Action Name</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.AdaptationHistoryItemImpl#getAffectedElementName <em>Affected Element Name</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.AdaptationHistoryItemImpl#getJustification <em>Justification</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.AdaptationHistoryItemImpl#getAdaptationTasks <em>Adaptation Tasks</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.AdaptationHistoryItemImpl#getTimestamp <em>Timestamp</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.AdaptationHistoryItemImpl#getSource <em>Source</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.impl.AdaptationHistoryItemImpl#getPerformedActionValue <em>Performed Action Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AdaptationHistoryItemImpl extends AdaptationStatusItemImpl implements AdaptationHistoryItem {
	/**
	 * The default value of the '{@link #getPerformedActionName() <em>Performed Action Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformedActionName()
	 * @generated
	 * @ordered
	 */
	protected static final String PERFORMED_ACTION_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPerformedActionName() <em>Performed Action Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformedActionName()
	 * @generated
	 * @ordered
	 */
	protected String performedActionName = PERFORMED_ACTION_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getAffectedElementName() <em>Affected Element Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAffectedElementName()
	 * @generated
	 * @ordered
	 */
	protected static final String AFFECTED_ELEMENT_NAME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getJustification() <em>Justification</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJustification()
	 * @generated
	 * @ordered
	 */
	protected static final String JUSTIFICATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJustification() <em>Justification</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJustification()
	 * @generated
	 * @ordered
	 */
	protected String justification = JUSTIFICATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAdaptationTasks() <em>Adaptation Tasks</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdaptationTasks()
	 * @generated
	 * @ordered
	 */
	protected EList<AdaptationTask> adaptationTasks;

	/**
	 * The default value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected static final Date TIMESTAMP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected Date timestamp = TIMESTAMP_EDEFAULT;

	/**
	 * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected static final AdaptationSource SOURCE_EDEFAULT = AdaptationSource.UNSPECIFIED;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected AdaptationSource source = SOURCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPerformedActionValue() <em>Performed Action Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformedActionValue()
	 * @generated
	 * @ordered
	 */
	protected static final String PERFORMED_ACTION_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPerformedActionValue() <em>Performed Action Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformedActionValue()
	 * @generated
	 * @ordered
	 */
	protected String performedActionValue = PERFORMED_ACTION_VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdaptationHistoryItemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatusPackage.Literals.ADAPTATION_HISTORY_ITEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPerformedActionName() {
		return performedActionName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPerformedActionName(String newPerformedActionName) {
		String oldPerformedActionName = performedActionName;
		performedActionName = newPerformedActionName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatusPackage.ADAPTATION_HISTORY_ITEM__PERFORMED_ACTION_NAME, oldPerformedActionName, performedActionName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getAffectedElementName() {
		EObject container = eContainer();
		if (container instanceof NamedElement) {
			return ((NamedElement) container).getName();
		} 
		if (container instanceof QualityModelElement) {
			return ((QualityModelElement) container).getQualifiedName();
		}
		return container.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getJustification() {
		return justification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJustification(String newJustification) {
		String oldJustification = justification;
		justification = newJustification;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatusPackage.ADAPTATION_HISTORY_ITEM__JUSTIFICATION, oldJustification, justification));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AdaptationTask> getAdaptationTasks() {
		if (adaptationTasks == null) {
			adaptationTasks = new EObjectContainmentEList<AdaptationTask>(AdaptationTask.class, this, StatusPackage.ADAPTATION_HISTORY_ITEM__ADAPTATION_TASKS);
		}
		return adaptationTasks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimestamp(Date newTimestamp) {
		Date oldTimestamp = timestamp;
		timestamp = newTimestamp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatusPackage.ADAPTATION_HISTORY_ITEM__TIMESTAMP, oldTimestamp, timestamp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdaptationSource getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(AdaptationSource newSource) {
		AdaptationSource oldSource = source;
		source = newSource == null ? SOURCE_EDEFAULT : newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatusPackage.ADAPTATION_HISTORY_ITEM__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPerformedActionValue() {
		return performedActionValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPerformedActionValue(String newPerformedActionValue) {
		String oldPerformedActionValue = performedActionValue;
		performedActionValue = newPerformedActionValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StatusPackage.ADAPTATION_HISTORY_ITEM__PERFORMED_ACTION_VALUE, oldPerformedActionValue, performedActionValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StatusPackage.ADAPTATION_HISTORY_ITEM__ADAPTATION_TASKS:
				return ((InternalEList<?>)getAdaptationTasks()).basicRemove(otherEnd, msgs);
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
			case StatusPackage.ADAPTATION_HISTORY_ITEM__PERFORMED_ACTION_NAME:
				return getPerformedActionName();
			case StatusPackage.ADAPTATION_HISTORY_ITEM__AFFECTED_ELEMENT_NAME:
				return getAffectedElementName();
			case StatusPackage.ADAPTATION_HISTORY_ITEM__JUSTIFICATION:
				return getJustification();
			case StatusPackage.ADAPTATION_HISTORY_ITEM__ADAPTATION_TASKS:
				return getAdaptationTasks();
			case StatusPackage.ADAPTATION_HISTORY_ITEM__TIMESTAMP:
				return getTimestamp();
			case StatusPackage.ADAPTATION_HISTORY_ITEM__SOURCE:
				return getSource();
			case StatusPackage.ADAPTATION_HISTORY_ITEM__PERFORMED_ACTION_VALUE:
				return getPerformedActionValue();
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
			case StatusPackage.ADAPTATION_HISTORY_ITEM__PERFORMED_ACTION_NAME:
				setPerformedActionName((String)newValue);
				return;
			case StatusPackage.ADAPTATION_HISTORY_ITEM__JUSTIFICATION:
				setJustification((String)newValue);
				return;
			case StatusPackage.ADAPTATION_HISTORY_ITEM__ADAPTATION_TASKS:
				getAdaptationTasks().clear();
				getAdaptationTasks().addAll((Collection<? extends AdaptationTask>)newValue);
				return;
			case StatusPackage.ADAPTATION_HISTORY_ITEM__TIMESTAMP:
				setTimestamp((Date)newValue);
				return;
			case StatusPackage.ADAPTATION_HISTORY_ITEM__SOURCE:
				setSource((AdaptationSource)newValue);
				return;
			case StatusPackage.ADAPTATION_HISTORY_ITEM__PERFORMED_ACTION_VALUE:
				setPerformedActionValue((String)newValue);
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
			case StatusPackage.ADAPTATION_HISTORY_ITEM__PERFORMED_ACTION_NAME:
				setPerformedActionName(PERFORMED_ACTION_NAME_EDEFAULT);
				return;
			case StatusPackage.ADAPTATION_HISTORY_ITEM__JUSTIFICATION:
				setJustification(JUSTIFICATION_EDEFAULT);
				return;
			case StatusPackage.ADAPTATION_HISTORY_ITEM__ADAPTATION_TASKS:
				getAdaptationTasks().clear();
				return;
			case StatusPackage.ADAPTATION_HISTORY_ITEM__TIMESTAMP:
				setTimestamp(TIMESTAMP_EDEFAULT);
				return;
			case StatusPackage.ADAPTATION_HISTORY_ITEM__SOURCE:
				setSource(SOURCE_EDEFAULT);
				return;
			case StatusPackage.ADAPTATION_HISTORY_ITEM__PERFORMED_ACTION_VALUE:
				setPerformedActionValue(PERFORMED_ACTION_VALUE_EDEFAULT);
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
			case StatusPackage.ADAPTATION_HISTORY_ITEM__PERFORMED_ACTION_NAME:
				return PERFORMED_ACTION_NAME_EDEFAULT == null ? performedActionName != null : !PERFORMED_ACTION_NAME_EDEFAULT.equals(performedActionName);
			case StatusPackage.ADAPTATION_HISTORY_ITEM__AFFECTED_ELEMENT_NAME:
				return AFFECTED_ELEMENT_NAME_EDEFAULT == null ? getAffectedElementName() != null : !AFFECTED_ELEMENT_NAME_EDEFAULT.equals(getAffectedElementName());
			case StatusPackage.ADAPTATION_HISTORY_ITEM__JUSTIFICATION:
				return JUSTIFICATION_EDEFAULT == null ? justification != null : !JUSTIFICATION_EDEFAULT.equals(justification);
			case StatusPackage.ADAPTATION_HISTORY_ITEM__ADAPTATION_TASKS:
				return adaptationTasks != null && !adaptationTasks.isEmpty();
			case StatusPackage.ADAPTATION_HISTORY_ITEM__TIMESTAMP:
				return TIMESTAMP_EDEFAULT == null ? timestamp != null : !TIMESTAMP_EDEFAULT.equals(timestamp);
			case StatusPackage.ADAPTATION_HISTORY_ITEM__SOURCE:
				return source != SOURCE_EDEFAULT;
			case StatusPackage.ADAPTATION_HISTORY_ITEM__PERFORMED_ACTION_VALUE:
				return PERFORMED_ACTION_VALUE_EDEFAULT == null ? performedActionValue != null : !PERFORMED_ACTION_VALUE_EDEFAULT.equals(performedActionValue);
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
		result.append(" (performedActionName: ");
		result.append(performedActionName);
		result.append(", justification: ");
		result.append(justification);
		result.append(", timestamp: ");
		result.append(timestamp);
		result.append(", source: ");
		result.append(source);
		result.append(", performedActionValue: ");
		result.append(performedActionValue);
		result.append(')');
		return result.toString();
	}

} //AdaptationHistoryItemImpl
