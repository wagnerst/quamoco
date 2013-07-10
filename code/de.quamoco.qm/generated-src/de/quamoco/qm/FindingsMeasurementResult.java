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
 * $Id: FindingsMeasurementResult.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm;

import edu.tum.cs.conqat.quamoco.FindingCollection;
import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Findings Measurement Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.qm.FindingsMeasurementResult#getCount <em>Count</em>}</li>
 *   <li>{@link de.quamoco.qm.FindingsMeasurementResult#getFindings <em>Findings</em>}</li>
 *   <li>{@link de.quamoco.qm.FindingsMeasurementResult#getFindingMessages <em>Finding Messages</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.qm.QmPackage#getFindingsMeasurementResult()
 * @model
 * @generated
 */
public interface FindingsMeasurementResult extends MeasurementResult {
	/**
	 * Returns the value of the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Count</em>' attribute.
	 * @see #setCount(int)
	 * @see de.quamoco.qm.QmPackage#getFindingsMeasurementResult_Count()
	 * @model required="true"
	 * @generated
	 */
	int getCount();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.FindingsMeasurementResult#getCount <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Count</em>' attribute.
	 * @see #getCount()
	 * @generated
	 */
	void setCount(int value);

	/**
	 * Returns the value of the '<em><b>Findings</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Findings</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Findings</em>' attribute.
	 * @see #setFindings(FindingCollection)
	 * @see de.quamoco.qm.QmPackage#getFindingsMeasurementResult_Findings()
	 * @model dataType="de.quamoco.qm.FindingCollection" transient="true"
	 * @generated
	 */
	FindingCollection getFindings();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.FindingsMeasurementResult#getFindings <em>Findings</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Findings</em>' attribute.
	 * @see #getFindings()
	 * @generated
	 */
	void setFindings(FindingCollection value);

	/**
	 * Returns the value of the '<em><b>Finding Messages</b></em>' containment reference list.
	 * The list contents are of type {@link de.quamoco.qm.FindingMessage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Finding Messages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Finding Messages</em>' containment reference list.
	 * @see de.quamoco.qm.QmPackage#getFindingsMeasurementResult_FindingMessages()
	 * @model containment="true"
	 * @generated
	 */
	EList<FindingMessage> getFindingMessages();

} // FindingsMeasurementResult
