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
 * $Id: SingleMeasureEvaluationResult.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Single Measure Evaluation Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.qm.SingleMeasureEvaluationResult#getRatioAffected <em>Ratio Affected</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.qm.QmPackage#getSingleMeasureEvaluationResult()
 * @model
 * @generated
 */
public interface SingleMeasureEvaluationResult extends EvaluationResult {
	/**
	 * Returns the value of the '<em><b>Ratio Affected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ratio Affected</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ratio Affected</em>' attribute.
	 * @see #setRatioAffected(double)
	 * @see de.quamoco.qm.QmPackage#getSingleMeasureEvaluationResult_RatioAffected()
	 * @model
	 * @generated
	 */
	double getRatioAffected();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.SingleMeasureEvaluationResult#getRatioAffected <em>Ratio Affected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ratio Affected</em>' attribute.
	 * @see #getRatioAffected()
	 * @generated
	 */
	void setRatioAffected(double value);

} // SingleMeasureEvaluationResult
