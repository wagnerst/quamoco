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
 * $Id: ToolBasedInstrument.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tool Based Instrument</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.qm.ToolBasedInstrument#getTool <em>Tool</em>}</li>
 *   <li>{@link de.quamoco.qm.ToolBasedInstrument#getMetric <em>Metric</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.qm.QmPackage#getToolBasedInstrument()
 * @model
 * @generated
 */
public interface ToolBasedInstrument extends Instrument {
	/**
	 * Returns the value of the '<em><b>Tool</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tool</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tool</em>' reference.
	 * @see #setTool(Tool)
	 * @see de.quamoco.qm.QmPackage#getToolBasedInstrument_Tool()
	 * @model required="true"
	 * @generated
	 */
	Tool getTool();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.ToolBasedInstrument#getTool <em>Tool</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tool</em>' reference.
	 * @see #getTool()
	 * @generated
	 */
	void setTool(Tool value);

	/**
	 * Returns the value of the '<em><b>Metric</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metric</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metric</em>' attribute.
	 * @see #setMetric(String)
	 * @see de.quamoco.qm.QmPackage#getToolBasedInstrument_Metric()
	 * @model required="true"
	 * @generated
	 */
	String getMetric();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.ToolBasedInstrument#getMetric <em>Metric</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metric</em>' attribute.
	 * @see #getMetric()
	 * @generated
	 */
	void setMetric(String value);

} // ToolBasedInstrument
