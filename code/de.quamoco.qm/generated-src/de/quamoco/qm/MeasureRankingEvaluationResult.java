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
 * $Id: MeasureRankingEvaluationResult.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measure Ranking Evaluation Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.qm.MeasureRankingEvaluationResult#getRatioAffected <em>Ratio Affected</em>}</li>
 *   <li>{@link de.quamoco.qm.MeasureRankingEvaluationResult#getValue <em>Value</em>}</li>
 *   <li>{@link de.quamoco.qm.MeasureRankingEvaluationResult#getResultsFrom <em>Results From</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.qm.QmPackage#getMeasureRankingEvaluationResult()
 * @model
 * @generated
 */
public interface MeasureRankingEvaluationResult extends EObject {
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
	 * @see de.quamoco.qm.QmPackage#getMeasureRankingEvaluationResult_RatioAffected()
	 * @model
	 * @generated
	 */
	double getRatioAffected();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.MeasureRankingEvaluationResult#getRatioAffected <em>Ratio Affected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ratio Affected</em>' attribute.
	 * @see #getRatioAffected()
	 * @generated
	 */
	void setRatioAffected(double value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #setValue(DoubleInterval)
	 * @see de.quamoco.qm.QmPackage#getMeasureRankingEvaluationResult_Value()
	 * @model containment="true" required="true"
	 * @generated
	 */
	DoubleInterval getValue();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.MeasureRankingEvaluationResult#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' containment reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(DoubleInterval value);

	/**
	 * Returns the value of the '<em><b>Results From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Results From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Results From</em>' reference.
	 * @see #setResultsFrom(MeasureRanking)
	 * @see de.quamoco.qm.QmPackage#getMeasureRankingEvaluationResult_ResultsFrom()
	 * @model required="true"
	 * @generated
	 */
	MeasureRanking getResultsFrom();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.MeasureRankingEvaluationResult#getResultsFrom <em>Results From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Results From</em>' reference.
	 * @see #getResultsFrom()
	 * @generated
	 */
	void setResultsFrom(MeasureRanking value);

} // MeasureRankingEvaluationResult
