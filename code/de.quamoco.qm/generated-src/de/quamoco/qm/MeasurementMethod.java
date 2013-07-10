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
 * $Id: MeasurementMethod.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Determination</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.qm.MeasurementMethod#getDetermines <em>Determines</em>}</li>
 *   <li>{@link de.quamoco.qm.MeasurementMethod#getQualityModel <em>Quality Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.qm.QmPackage#getMeasurementMethod()
 * @model abstract="true"
 * @generated
 */
public interface MeasurementMethod extends AnnotatedElement {

	/**
	 * Returns the value of the '<em><b>Determines</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Determines</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Determines</em>' reference.
	 * @see #setDetermines(Measure)
	 * @see de.quamoco.qm.QmPackage#getMeasurementMethod_Determines()
	 * @model required="true"
	 * @generated
	 */
	Measure getDetermines();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.MeasurementMethod#getDetermines <em>Determines</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Determines</em>' reference.
	 * @see #getDetermines()
	 * @generated
	 */
	void setDetermines(Measure value);

	/**
	 * Returns the value of the '<em><b>Quality Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.QualityModel#getMeasurementMethods <em>Measurement Methods</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quality Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quality Model</em>' container reference.
	 * @see #setQualityModel(QualityModel)
	 * @see de.quamoco.qm.QmPackage#getMeasurementMethod_QualityModel()
	 * @see de.quamoco.qm.QualityModel#getMeasurementMethods
	 * @model opposite="measurementMethods" required="true" transient="false"
	 * @generated
	 */
	QualityModel getQualityModel();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.MeasurementMethod#getQualityModel <em>Quality Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quality Model</em>' container reference.
	 * @see #getQualityModel()
	 * @generated
	 */
	void setQualityModel(QualityModel value);
} // Determination
