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
 * $Id: Evaluation.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Impact Evaluation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An impact evaluation evaluates the influence one factor or more factors 
 * in combination have on a quality aspect. The quality model specifies 
 * exactly one evaluation for each impact. The impact evaluation is based 
 * on the measures associated to the factors. It has to consider all defined 
 * measures for its factors. For these measures, the measurement data 
 * for all entities are aggregated. The result of the evaluation is an impact 
 * evaluation result, which is a value on an ordinal scale.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.qm.Evaluation#getEvaluates <em>Evaluates</em>}</li>
 *   <li>{@link de.quamoco.qm.Evaluation#getQualityModel <em>Quality Model</em>}</li>
 *   <li>{@link de.quamoco.qm.Evaluation#getMaximumPoints <em>Maximum Points</em>}</li>
 *   <li>{@link de.quamoco.qm.Evaluation#getCompleteness <em>Completeness</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.qm.QmPackage#getEvaluation()
 * @model abstract="true"
 * @generated
 */
public interface Evaluation extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Evaluates</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Evaluates</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Evaluates</em>' reference.
	 * @see #setEvaluates(Factor)
	 * @see de.quamoco.qm.QmPackage#getEvaluation_Evaluates()
	 * @model required="true"
	 * @generated
	 */
	Factor getEvaluates();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.Evaluation#getEvaluates <em>Evaluates</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Evaluates</em>' reference.
	 * @see #getEvaluates()
	 * @generated
	 */
	void setEvaluates(Factor value);

	/**
	 * Returns the value of the '<em><b>Quality Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.QualityModel#getEvaluations <em>Evaluations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quality Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quality Model</em>' container reference.
	 * @see #setQualityModel(QualityModel)
	 * @see de.quamoco.qm.QmPackage#getEvaluation_QualityModel()
	 * @see de.quamoco.qm.QualityModel#getEvaluations
	 * @model opposite="evaluations" required="true" transient="false"
	 * @generated
	 */
	QualityModel getQualityModel();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.Evaluation#getQualityModel <em>Quality Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quality Model</em>' container reference.
	 * @see #getQualityModel()
	 * @generated
	 */
	void setQualityModel(QualityModel value);

	/**
	 * Returns the value of the '<em><b>Maximum Points</b></em>' attribute.
	 * The default value is <code>"100"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Points</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Points</em>' attribute.
	 * @see #setMaximumPoints(int)
	 * @see de.quamoco.qm.QmPackage#getEvaluation_MaximumPoints()
	 * @model default="100"
	 * @generated
	 */
	int getMaximumPoints();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.Evaluation#getMaximumPoints <em>Maximum Points</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Points</em>' attribute.
	 * @see #getMaximumPoints()
	 * @generated
	 */
	void setMaximumPoints(int value);

	/**
	 * Returns the value of the '<em><b>Completeness</b></em>' attribute.
	 * The default value is <code>"100"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Completeness</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Completeness</em>' attribute.
	 * @see #setCompleteness(int)
	 * @see de.quamoco.qm.QmPackage#getEvaluation_Completeness()
	 * @model default="100" required="true"
	 * @generated
	 */
	int getCompleteness();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.Evaluation#getCompleteness <em>Completeness</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Completeness</em>' attribute.
	 * @see #getCompleteness()
	 * @generated
	 */
	void setCompleteness(int value);

	List<NormalizationMeasure> getNormalizationMeasures();

	List<Measure> getUsableMeasures();

	List<Measurement> getUsableMeasurements();

	List<Factor> getUsableFactors();

	List<FactorRefinement> getUsableRefinements();

	List<Impact> getUsableImpacts();
	
	Double getAccumulatedCompleteness(QualityModel evaluationTarget);

} // ImpactEvaluation
