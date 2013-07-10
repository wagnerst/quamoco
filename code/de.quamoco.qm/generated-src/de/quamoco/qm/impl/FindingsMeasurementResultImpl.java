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
 * $Id: FindingsMeasurementResultImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.impl;

import de.quamoco.qm.FindingMessage;
import de.quamoco.qm.FindingsMeasurementResult;
import de.quamoco.qm.QmPackage;

import edu.tum.cs.conqat.quamoco.FindingCollection;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Findings Measurement Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.qm.impl.FindingsMeasurementResultImpl#getCount <em>Count</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.FindingsMeasurementResultImpl#getFindings <em>Findings</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.FindingsMeasurementResultImpl#getFindingMessages <em>Finding Messages</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FindingsMeasurementResultImpl extends MeasurementResultImpl implements FindingsMeasurementResult {
	/**
	 * The default value of the '{@link #getCount() <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCount()
	 * @generated
	 * @ordered
	 */
	protected static final int COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCount() <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCount()
	 * @generated
	 * @ordered
	 */
	protected int count = COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getFindings() <em>Findings</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFindings()
	 * @generated
	 * @ordered
	 */
	protected static final FindingCollection FINDINGS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFindings() <em>Findings</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFindings()
	 * @generated
	 * @ordered
	 */
	protected FindingCollection findings = FINDINGS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFindingMessages() <em>Finding Messages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFindingMessages()
	 * @generated
	 * @ordered
	 */
	protected EList<FindingMessage> findingMessages;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FindingsMeasurementResultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QmPackage.Literals.FINDINGS_MEASUREMENT_RESULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCount() {
		return count;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCount(int newCount) {
		int oldCount = count;
		count = newCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.FINDINGS_MEASUREMENT_RESULT__COUNT, oldCount, count));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FindingCollection getFindings() {
		return findings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFindings(FindingCollection newFindings) {
		FindingCollection oldFindings = findings;
		findings = newFindings;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.FINDINGS_MEASUREMENT_RESULT__FINDINGS, oldFindings, findings));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FindingMessage> getFindingMessages() {
		if (findingMessages == null) {
			findingMessages = new EObjectContainmentEList<FindingMessage>(FindingMessage.class, this, QmPackage.FINDINGS_MEASUREMENT_RESULT__FINDING_MESSAGES);
		}
		return findingMessages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QmPackage.FINDINGS_MEASUREMENT_RESULT__FINDING_MESSAGES:
				return ((InternalEList<?>)getFindingMessages()).basicRemove(otherEnd, msgs);
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
			case QmPackage.FINDINGS_MEASUREMENT_RESULT__COUNT:
				return getCount();
			case QmPackage.FINDINGS_MEASUREMENT_RESULT__FINDINGS:
				return getFindings();
			case QmPackage.FINDINGS_MEASUREMENT_RESULT__FINDING_MESSAGES:
				return getFindingMessages();
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
			case QmPackage.FINDINGS_MEASUREMENT_RESULT__COUNT:
				setCount((Integer)newValue);
				return;
			case QmPackage.FINDINGS_MEASUREMENT_RESULT__FINDINGS:
				setFindings((FindingCollection)newValue);
				return;
			case QmPackage.FINDINGS_MEASUREMENT_RESULT__FINDING_MESSAGES:
				getFindingMessages().clear();
				getFindingMessages().addAll((Collection<? extends FindingMessage>)newValue);
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
			case QmPackage.FINDINGS_MEASUREMENT_RESULT__COUNT:
				setCount(COUNT_EDEFAULT);
				return;
			case QmPackage.FINDINGS_MEASUREMENT_RESULT__FINDINGS:
				setFindings(FINDINGS_EDEFAULT);
				return;
			case QmPackage.FINDINGS_MEASUREMENT_RESULT__FINDING_MESSAGES:
				getFindingMessages().clear();
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
			case QmPackage.FINDINGS_MEASUREMENT_RESULT__COUNT:
				return count != COUNT_EDEFAULT;
			case QmPackage.FINDINGS_MEASUREMENT_RESULT__FINDINGS:
				return FINDINGS_EDEFAULT == null ? findings != null : !FINDINGS_EDEFAULT.equals(findings);
			case QmPackage.FINDINGS_MEASUREMENT_RESULT__FINDING_MESSAGES:
				return findingMessages != null && !findingMessages.isEmpty();
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
		result.append(" (count: ");
		result.append(count);
		result.append(", findings: ");
		result.append(findings);
		result.append(')');
		return result.toString();
	}

} //FindingsMeasurementResultImpl
