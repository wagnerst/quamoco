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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Marked Completed</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.adaptation.model.status.UserMarkedCompleted#isHasMarkedCompleted <em>Has Marked Completed</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.UserMarkedCompleted#getLastUpdate <em>Last Update</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.adaptation.model.status.StatusPackage#getUserMarkedCompleted()
 * @model
 * @generated
 */
public interface UserMarkedCompleted extends FulfillmentCriterion {
	/**
	 * Returns the value of the '<em><b>Has Marked Completed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Marked Completed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Marked Completed</em>' attribute.
	 * @see #setHasMarkedCompleted(boolean)
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getUserMarkedCompleted_HasMarkedCompleted()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	boolean isHasMarkedCompleted();

	/**
	 * Sets the value of the '{@link de.quamoco.adaptation.model.status.UserMarkedCompleted#isHasMarkedCompleted <em>Has Marked Completed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <br><br><font color="red">Never call without {@link #setLastUpdate(Date)}!</font><br>
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Marked Completed</em>' attribute.
	 * @see #isHasMarkedCompleted()
	 * @generated
	 */
	void setHasMarkedCompleted(boolean value);

	/**
	 * Returns the value of the '<em><b>Last Update</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Last Update</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Update</em>' attribute.
	 * @see #setLastUpdate(Date)
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getUserMarkedCompleted_LastUpdate()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Date getLastUpdate();

	/**
	 * Sets the value of the '{@link de.quamoco.adaptation.model.status.UserMarkedCompleted#getLastUpdate <em>Last Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Update</em>' attribute.
	 * @see #getLastUpdate()
	 * @generated
	 */
	void setLastUpdate(Date value);

} // UserMarkedCompleted
