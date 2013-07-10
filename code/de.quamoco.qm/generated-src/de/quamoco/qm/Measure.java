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
 * $Id: Measure.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm;

import java.util.List;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.qm.Measure#getType <em>Type</em>}</li>
 *   <li>{@link de.quamoco.qm.Measure#getMeasures <em>Measures</em>}</li>
 *   <li>{@link de.quamoco.qm.Measure#getMeasuresDirect <em>Measures Direct</em>}</li>
 *   <li>{@link de.quamoco.qm.Measure#getQualityModel <em>Quality Model</em>}</li>
 *   <li>{@link de.quamoco.qm.Measure#getRefines <em>Refines</em>}</li>
 *   <li>{@link de.quamoco.qm.Measure#getRefinesDirect <em>Refines Direct</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.qm.QmPackage#getMeasure()
 * @model
 * @generated
 */
public interface Measure extends CharacterizingElement {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link de.quamoco.qm.Type}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see de.quamoco.qm.Type
	 * @see #setType(Type)
	 * @see de.quamoco.qm.QmPackage#getMeasure_Type()
	 * @model
	 * @generated
	 */
	Type getType();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.Measure#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see de.quamoco.qm.Type
	 * @see #getType()
	 * @generated
	 */
	void setType(Type value);

	/**
	 * Returns the value of the '<em><b>Measures</b></em>' containment reference list.
	 * The list contents are of type {@link de.quamoco.qm.Measurement}.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.Measurement#getChild <em>Child</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The factors which this measure measures
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Measures</em>' containment reference list.
	 * @see de.quamoco.qm.QmPackage#getMeasure_Measures()
	 * @see de.quamoco.qm.Measurement#getChild
	 * @model opposite="child" containment="true" ordered="false"
	 * @generated
	 */
	EList<Measurement> getMeasures();

	/**
	 * Returns the value of the '<em><b>Measures Direct</b></em>' reference list.
	 * The list contents are of type {@link de.quamoco.qm.Factor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Measures Direct</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Measures Direct</em>' reference list.
	 * @see de.quamoco.qm.QmPackage#getMeasure_MeasuresDirect()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	EList<Factor> getMeasuresDirect();

	/**
	 * Returns the value of the '<em><b>Quality Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.QualityModel#getMeasures <em>Measures</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quality Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quality Model</em>' container reference.
	 * @see #setQualityModel(QualityModel)
	 * @see de.quamoco.qm.QmPackage#getMeasure_QualityModel()
	 * @see de.quamoco.qm.QualityModel#getMeasures
	 * @model opposite="measures" required="true" transient="false"
	 * @generated
	 */
	QualityModel getQualityModel();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.Measure#getQualityModel <em>Quality Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quality Model</em>' container reference.
	 * @see #getQualityModel()
	 * @generated
	 */
	void setQualityModel(QualityModel value);

	/**
	 * Returns the value of the '<em><b>Refines</b></em>' containment reference list.
	 * The list contents are of type {@link de.quamoco.qm.MeasureRefinement}.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.MeasureRefinement#getChild <em>Child</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Refines</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Refines</em>' containment reference list.
	 * @see de.quamoco.qm.QmPackage#getMeasure_Refines()
	 * @see de.quamoco.qm.MeasureRefinement#getChild
	 * @model opposite="child" containment="true"
	 * @generated
	 */
	EList<MeasureRefinement> getRefines();

	/**
	 * Returns the value of the '<em><b>Refines Direct</b></em>' reference list.
	 * The list contents are of type {@link de.quamoco.qm.Measure}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Refines Direct</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Refines Direct</em>' reference list.
	 * @see de.quamoco.qm.QmPackage#getMeasure_RefinesDirect()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	EList<Measure> getRefinesDirect();

	/** Get the {@link Measure}s by which this measure is refined. */
	List<Measure> getRefinedByDirect();
	
	List<MeasureRefinement> getRefinedBy();

	List<MeasurementMethod> getDeterminedBy();

	MeasurementMethod getActualMeasurementMethod(QualityModel context);

} // Measure
