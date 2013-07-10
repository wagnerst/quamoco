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
 * $Id: Measurement.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measurement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.qm.Measurement#getParent <em>Parent</em>}</li>
 *   <li>{@link de.quamoco.qm.Measurement#getChild <em>Child</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.qm.QmPackage#getMeasurement()
 * @model
 * @generated
 */
public interface Measurement extends AnnotatedElement {
	/**
	 * Returns the value of the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' reference.
	 * @see #setParent(Factor)
	 * @see de.quamoco.qm.QmPackage#getMeasurement_Parent()
	 * @model required="true"
	 * @generated
	 */
	Factor getParent();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.Measurement#getParent <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(Factor value);

	/**
	 * Returns the value of the '<em><b>Child</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.Measure#getMeasures <em>Measures</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Child</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Child</em>' container reference.
	 * @see #setChild(Measure)
	 * @see de.quamoco.qm.QmPackage#getMeasurement_Child()
	 * @see de.quamoco.qm.Measure#getMeasures
	 * @model opposite="measures" required="true" transient="false"
	 * @generated
	 */
	Measure getChild();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.Measurement#getChild <em>Child</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Child</em>' container reference.
	 * @see #getChild()
	 * @generated
	 */
	void setChild(Measure value);

} // Measurement
