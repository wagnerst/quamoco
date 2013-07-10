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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Adaptation Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.adaptation.model.status.AdaptationTask#getUserMessage <em>User Message</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.AdaptationTask#getFulfillmentCriteria <em>Fulfillment Criteria</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.AdaptationTask#getAffectedElement <em>Affected Element</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.AdaptationTask#isAutoDelete <em>Auto Delete</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.AdaptationTask#getTaskId <em>Task Id</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.AdaptationTask#isIgnoredByUser <em>Ignored By User</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.adaptation.model.status.StatusPackage#getAdaptationTask()
 * @model
 * @generated
 */
public interface AdaptationTask extends AdaptationStatusItem {
	/**
	 * Returns the value of the '<em><b>User Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Message</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Message</em>' attribute.
	 * @see #setUserMessage(String)
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getAdaptationTask_UserMessage()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getUserMessage();

	/**
	 * Sets the value of the '{@link de.quamoco.adaptation.model.status.AdaptationTask#getUserMessage <em>User Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Message</em>' attribute.
	 * @see #getUserMessage()
	 * @generated
	 */
	void setUserMessage(String value);

	/**
	 * Returns the value of the '<em><b>Fulfillment Criteria</b></em>' containment reference list.
	 * The list contents are of type {@link de.quamoco.adaptation.model.status.FulfillmentCriterion}.
	 * It is bidirectional and its opposite is '{@link de.quamoco.adaptation.model.status.FulfillmentCriterion#getAdaptationTask <em>Adaptation Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fulfillment Criteria</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fulfillment Criteria</em>' containment reference list.
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getAdaptationTask_FulfillmentCriteria()
	 * @see de.quamoco.adaptation.model.status.FulfillmentCriterion#getAdaptationTask
	 * @model opposite="adaptationTask" containment="true" required="true" ordered="false"
	 * @generated
	 */
	EList<FulfillmentCriterion> getFulfillmentCriteria();

	/**
	 * Returns the value of the '<em><b>Affected Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Affected Element</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Affected Element</em>' attribute.
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getAdaptationTask_AffectedElement()
	 * @model dataType="de.quamoco.adaptation.model.status.EObject" required="true" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	EObject getAffectedElement();

	/**
	 * Returns the value of the '<em><b>Auto Delete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Auto Delete</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Auto Delete</em>' attribute.
	 * @see #setAutoDelete(boolean)
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getAdaptationTask_AutoDelete()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	boolean isAutoDelete();

	/**
	 * Sets the value of the '{@link de.quamoco.adaptation.model.status.AdaptationTask#isAutoDelete <em>Auto Delete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auto Delete</em>' attribute.
	 * @see #isAutoDelete()
	 * @generated
	 */
	void setAutoDelete(boolean value);

	/**
	 * Returns the value of the '<em><b>Task Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Task Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task Id</em>' attribute.
	 * @see #setTaskId(String)
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getAdaptationTask_TaskId()
	 * @model
	 * @generated
	 */
	String getTaskId();

	/**
	 * Sets the value of the '{@link de.quamoco.adaptation.model.status.AdaptationTask#getTaskId <em>Task Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Task Id</em>' attribute.
	 * @see #getTaskId()
	 * @generated
	 */
	void setTaskId(String value);

	/**
	 * Returns the value of the '<em><b>Ignored By User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The user may want to choose to ignore a certain AdaptationTask.<br/>
	 * The UI shall then set this value accordingly. When it is true the task is considered to be completed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ignored By User</em>' attribute.
	 * @see #setIgnoredByUser(boolean)
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getAdaptationTask_IgnoredByUser()
	 * @model
	 * @generated
	 */
	boolean isIgnoredByUser();

	/**
	 * Sets the value of the '{@link de.quamoco.adaptation.model.status.AdaptationTask#isIgnoredByUser <em>Ignored By User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ignored By User</em>' attribute.
	 * @see #isIgnoredByUser()
	 * @generated
	 */
	void setIgnoredByUser(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true" ordered="false"
	 * @generated
	 */
	boolean isCompleted();

} // AdaptationTask
