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
 * $Id: Factor.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm;

import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Factor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A factor associates a property to an entity type. A factor influences at 
 * least one quality aspect, which is specified by an impact for each 
 * influenced quality aspect. The factor can be quantified by a number of 
 * measures. We do not evaluate the factor, but rather the impact on a 
 * certain quality aspect, as the evaluation depends on the influenced 
 * quality aspect. Factors are one of the basic building blocks of quality 
 * models. They help to concretize quality requirements by specifying on a 
 * more detailed level what properties of entity types are important for 
 * specific quality aspects.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.qm.Factor#getInfluences <em>Influences</em>}</li>
 *   <li>{@link de.quamoco.qm.Factor#getInfluencesDirect <em>Influences Direct</em>}</li>
 *   <li>{@link de.quamoco.qm.Factor#getRefines <em>Refines</em>}</li>
 *   <li>{@link de.quamoco.qm.Factor#getRefinesDirect <em>Refines Direct</em>}</li>
 *   <li>{@link de.quamoco.qm.Factor#getQualityModel <em>Quality Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.qm.QmPackage#getFactor()
 * @model
 * @generated
 */
public interface Factor extends CharacterizingElement {
	/**
	 * Returns the value of the '<em><b>Influences</b></em>' containment reference list.
	 * The list contents are of type {@link de.quamoco.qm.Impact}.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.Impact#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The impacts of this factor on quality aspects
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Influences</em>' containment reference list.
	 * @see de.quamoco.qm.QmPackage#getFactor_Influences()
	 * @see de.quamoco.qm.Impact#getSource
	 * @model opposite="source" containment="true" ordered="false"
	 * @generated
	 */
	EList<Impact> getInfluences();

	/**
	 * Returns the value of the '<em><b>Influences Direct</b></em>' reference list.
	 * The list contents are of type {@link de.quamoco.qm.Factor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Influences Direct</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Influences Direct</em>' reference list.
	 * @see de.quamoco.qm.QmPackage#getFactor_InfluencesDirect()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	EList<Factor> getInfluencesDirect();

	/**
	 * Returns the value of the '<em><b>Refines</b></em>' containment reference list.
	 * The list contents are of type {@link de.quamoco.qm.FactorRefinement}.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.FactorRefinement#getChild <em>Child</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Refines</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Refines</em>' containment reference list.
	 * @see de.quamoco.qm.QmPackage#getFactor_Refines()
	 * @see de.quamoco.qm.FactorRefinement#getChild
	 * @model opposite="child" containment="true"
	 * @generated
	 */
	EList<FactorRefinement> getRefines();

	/**
	 * Returns the value of the '<em><b>Refines Direct</b></em>' reference list.
	 * The list contents are of type {@link de.quamoco.qm.Factor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Refines Direct</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Refines Direct</em>' reference list.
	 * @see de.quamoco.qm.QmPackage#getFactor_RefinesDirect()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	EList<Factor> getRefinesDirect();

	/**
	 * Returns the value of the '<em><b>Quality Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.QualityModel#getFactors <em>Factors</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quality Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quality Model</em>' container reference.
	 * @see #setQualityModel(QualityModel)
	 * @see de.quamoco.qm.QmPackage#getFactor_QualityModel()
	 * @see de.quamoco.qm.QualityModel#getFactors
	 * @model opposite="factors" required="true" transient="false"
	 * @generated
	 */
	QualityModel getQualityModel();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.Factor#getQualityModel <em>Quality Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quality Model</em>' container reference.
	 * @see #getQualityModel()
	 * @generated
	 */
	void setQualityModel(QualityModel value);

	/** Get the {@link Factor}s by which this factor is refined. */
	List<Factor> getRefinedByDirect();

	/** Get the {@link FactorRefinement}s by which this factor is refined. */
	List<FactorRefinement> getRefinedBy();

	/** Get the {@link Factor}s by which this factor is influenced. */
	List<Factor> getInfluencedByDirect();

	/** Get the {@link Impact}s by which this factor is influenced. */
	List<Impact> getInfluencedBy();

	/** Get the {@link Measure}s by which this factor is measured. */
	List<Measure> getMeasuredByDirect();

	List<Measurement> getMeasuredBy();

	List<Evaluation> getEvaluatedBy();

	Evaluation getActualEvaluation(QualityModel context);
	
	HashMap<QualityModel, Double> getAccumulatedCompleteness();
	
	boolean isQualityAspect();
} // Factor
