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
package de.quamoco.adaptation.model.status;

import java.util.Date;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Adaptation History Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem#getPerformedActionName <em>Performed Action Name</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem#getAffectedElementName <em>Affected Element Name</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem#getJustification <em>Justification</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem#getAdaptationTasks <em>Adaptation Tasks</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem#getTimestamp <em>Timestamp</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem#getSource <em>Source</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem#getPerformedActionValue <em>Performed Action Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.adaptation.model.status.StatusPackage#getAdaptationHistoryItem()
 * @model
 * @generated
 */
public interface AdaptationHistoryItem extends AdaptationStatusItem {
	/**
	 * Returns the value of the '<em><b>Performed Action Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Performed Action Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Performed Action Name</em>' attribute.
	 * @see #setPerformedActionName(String)
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getAdaptationHistoryItem_PerformedActionName()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getPerformedActionName();

	/**
	 * Sets the value of the '{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem#getPerformedActionName <em>Performed Action Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Performed Action Name</em>' attribute.
	 * @see #getPerformedActionName()
	 * @generated
	 */
	void setPerformedActionName(String value);

	/**
	 * Returns the value of the '<em><b>Affected Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Affected Element Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Affected Element Name</em>' attribute.
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getAdaptationHistoryItem_AffectedElementName()
	 * @model required="true" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	String getAffectedElementName();

	/**
	 * Returns the value of the '<em><b>Justification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Justification</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Justification</em>' attribute.
	 * @see #setJustification(String)
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getAdaptationHistoryItem_Justification()
	 * @model ordered="false"
	 * @generated
	 */
	String getJustification();

	/**
	 * Sets the value of the '{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem#getJustification <em>Justification</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Justification</em>' attribute.
	 * @see #getJustification()
	 * @generated
	 */
	void setJustification(String value);

	/**
	 * Returns the value of the '<em><b>Adaptation Tasks</b></em>' containment reference list.
	 * The list contents are of type {@link de.quamoco.adaptation.model.status.AdaptationTask}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Adaptation Tasks</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Adaptation Tasks</em>' containment reference list.
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getAdaptationHistoryItem_AdaptationTasks()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<AdaptationTask> getAdaptationTasks();

	/**
	 * Returns the value of the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timestamp</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timestamp</em>' attribute.
	 * @see #setTimestamp(Date)
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getAdaptationHistoryItem_Timestamp()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Date getTimestamp();

	/**
	 * Sets the value of the '{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem#getTimestamp <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timestamp</em>' attribute.
	 * @see #getTimestamp()
	 * @generated
	 */
	void setTimestamp(Date value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' attribute.
	 * The literals are from the enumeration {@link de.quamoco.adaptation.model.status.AdaptationSource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' attribute.
	 * @see de.quamoco.adaptation.model.status.AdaptationSource
	 * @see #setSource(AdaptationSource)
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getAdaptationHistoryItem_Source()
	 * @model required="true"
	 * @generated
	 */
	AdaptationSource getSource();

	/**
	 * Sets the value of the '{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem#getSource <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' attribute.
	 * @see de.quamoco.adaptation.model.status.AdaptationSource
	 * @see #getSource()
	 * @generated
	 */
	void setSource(AdaptationSource value);

	/**
	 * Returns the value of the '<em><b>Performed Action Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Performed Action Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Performed Action Value</em>' attribute.
	 * @see #setPerformedActionValue(String)
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getAdaptationHistoryItem_PerformedActionValue()
	 * @model required="true"
	 * @generated
	 */
	String getPerformedActionValue();

	/**
	 * Sets the value of the '{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem#getPerformedActionValue <em>Performed Action Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Performed Action Value</em>' attribute.
	 * @see #getPerformedActionValue()
	 * @generated
	 */
	void setPerformedActionValue(String value);

} // AdaptationHistoryItem
