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
 * $Id: QualityModelResult.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm;

import java.util.Date;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Quality Model Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.qm.QualityModelResult#getSystem <em>System</em>}</li>
 *   <li>{@link de.quamoco.qm.QualityModelResult#getDate <em>Date</em>}</li>
 *   <li>{@link de.quamoco.qm.QualityModelResult#getMeasurementResults <em>Measurement Results</em>}</li>
 *   <li>{@link de.quamoco.qm.QualityModelResult#getEvaluationResults <em>Evaluation Results</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.qm.QmPackage#getQualityModelResult()
 * @model
 * @generated
 */
public interface QualityModelResult extends EObject {
	/**
	 * Returns the value of the '<em><b>System</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System</em>' attribute.
	 * @see #setSystem(String)
	 * @see de.quamoco.qm.QmPackage#getQualityModelResult_System()
	 * @model required="true"
	 * @generated
	 */
	String getSystem();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.QualityModelResult#getSystem <em>System</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System</em>' attribute.
	 * @see #getSystem()
	 * @generated
	 */
	void setSystem(String value);

	/**
	 * Returns the value of the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Date</em>' attribute.
	 * @see #setDate(Date)
	 * @see de.quamoco.qm.QmPackage#getQualityModelResult_Date()
	 * @model required="true"
	 * @generated
	 */
	Date getDate();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.QualityModelResult#getDate <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date</em>' attribute.
	 * @see #getDate()
	 * @generated
	 */
	void setDate(Date value);

	/**
	 * Returns the value of the '<em><b>Measurement Results</b></em>' containment reference list.
	 * The list contents are of type {@link de.quamoco.qm.MeasurementResult}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Measurement Results</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Measurement Results</em>' containment reference list.
	 * @see de.quamoco.qm.QmPackage#getQualityModelResult_MeasurementResults()
	 * @model containment="true"
	 * @generated
	 */
	EList<MeasurementResult> getMeasurementResults();

	/**
	 * Returns the value of the '<em><b>Evaluation Results</b></em>' containment reference list.
	 * The list contents are of type {@link de.quamoco.qm.EvaluationResult}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Evaluation Results</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Evaluation Results</em>' containment reference list.
	 * @see de.quamoco.qm.QmPackage#getQualityModelResult_EvaluationResults()
	 * @model containment="true"
	 * @generated
	 */
	EList<EvaluationResult> getEvaluationResults();

} // QualityModelResult
