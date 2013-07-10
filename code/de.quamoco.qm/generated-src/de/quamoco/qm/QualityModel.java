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
 * $Id: QualityModel.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Quality Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Modules allow decomposing a large quality model into smaller, dedicated 
 * chunks. All model elements are associated with exactly one module. The 
 * relations between model elements can be established across module 
 * boundaries.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.qm.QualityModel#getEntities <em>Entities</em>}</li>
 *   <li>{@link de.quamoco.qm.QualityModel#getFactors <em>Factors</em>}</li>
 *   <li>{@link de.quamoco.qm.QualityModel#getEvaluations <em>Evaluations</em>}</li>
 *   <li>{@link de.quamoco.qm.QualityModel#getMeasures <em>Measures</em>}</li>
 *   <li>{@link de.quamoco.qm.QualityModel#getMeasurementMethods <em>Measurement Methods</em>}</li>
 *   <li>{@link de.quamoco.qm.QualityModel#getTools <em>Tools</em>}</li>
 *   <li>{@link de.quamoco.qm.QualityModel#getTags <em>Tags</em>}</li>
 *   <li>{@link de.quamoco.qm.QualityModel#getSources <em>Sources</em>}</li>
 *   <li>{@link de.quamoco.qm.QualityModel#getRequires <em>Requires</em>}</li>
 *   <li>{@link de.quamoco.qm.QualityModel#getSchoolGradeBoundary2 <em>School Grade Boundary2</em>}</li>
 *   <li>{@link de.quamoco.qm.QualityModel#getSchoolGradeBoundary3 <em>School Grade Boundary3</em>}</li>
 *   <li>{@link de.quamoco.qm.QualityModel#getSchoolGradeBoundary4 <em>School Grade Boundary4</em>}</li>
 *   <li>{@link de.quamoco.qm.QualityModel#getSchoolGradeBoundary5 <em>School Grade Boundary5</em>}</li>
 *   <li>{@link de.quamoco.qm.QualityModel#getSchoolGradeBoundary6 <em>School Grade Boundary6</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.qm.QmPackage#getQualityModel()
 * @model
 * @generated
 */
public interface QualityModel extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Entities</b></em>' containment reference list.
	 * The list contents are of type {@link de.quamoco.qm.Entity}.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.Entity#getQualityModel <em>Quality Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entities</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entities</em>' containment reference list.
	 * @see de.quamoco.qm.QmPackage#getQualityModel_Entities()
	 * @see de.quamoco.qm.Entity#getQualityModel
	 * @model opposite="qualityModel" containment="true"
	 * @generated
	 */
	EList<Entity> getEntities();

	/**
	 * Returns the value of the '<em><b>Factors</b></em>' containment reference list.
	 * The list contents are of type {@link de.quamoco.qm.Factor}.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.Factor#getQualityModel <em>Quality Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Factors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Factors</em>' containment reference list.
	 * @see de.quamoco.qm.QmPackage#getQualityModel_Factors()
	 * @see de.quamoco.qm.Factor#getQualityModel
	 * @model opposite="qualityModel" containment="true"
	 * @generated
	 */
	EList<Factor> getFactors();

	/**
	 * Returns the value of the '<em><b>Measures</b></em>' containment reference list.
	 * The list contents are of type {@link de.quamoco.qm.Measure}.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.Measure#getQualityModel <em>Quality Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Measures</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Measures</em>' containment reference list.
	 * @see de.quamoco.qm.QmPackage#getQualityModel_Measures()
	 * @see de.quamoco.qm.Measure#getQualityModel
	 * @model opposite="qualityModel" containment="true"
	 * @generated
	 */
	EList<Measure> getMeasures();

	/**
	 * Returns the value of the '<em><b>Tags</b></em>' containment reference list.
	 * The list contents are of type {@link de.quamoco.qm.Tag}.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.Tag#getQualityModel <em>Quality Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tags</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tags</em>' containment reference list.
	 * @see de.quamoco.qm.QmPackage#getQualityModel_Tags()
	 * @see de.quamoco.qm.Tag#getQualityModel
	 * @model opposite="qualityModel" containment="true"
	 * @generated
	 */
	EList<Tag> getTags();

	/**
	 * Returns the value of the '<em><b>Tools</b></em>' containment reference list.
	 * The list contents are of type {@link de.quamoco.qm.Tool}.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.Tool#getQualityModel <em>Quality Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tools</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tools</em>' containment reference list.
	 * @see de.quamoco.qm.QmPackage#getQualityModel_Tools()
	 * @see de.quamoco.qm.Tool#getQualityModel
	 * @model opposite="qualityModel" containment="true"
	 * @generated
	 */
	EList<Tool> getTools();

	/**
	 * Returns the value of the '<em><b>Sources</b></em>' containment reference list.
	 * The list contents are of type {@link de.quamoco.qm.Source}.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.Source#getQualityModel <em>Quality Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sources</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sources</em>' containment reference list.
	 * @see de.quamoco.qm.QmPackage#getQualityModel_Sources()
	 * @see de.quamoco.qm.Source#getQualityModel
	 * @model opposite="qualityModel" containment="true"
	 * @generated
	 */
	EList<Source> getSources();

	/**
	 * Returns the value of the '<em><b>Requires</b></em>' reference list.
	 * The list contents are of type {@link de.quamoco.qm.QualityModel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requires</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requires</em>' reference list.
	 * @see de.quamoco.qm.QmPackage#getQualityModel_Requires()
	 * @model
	 * @generated
	 */
	EList<QualityModel> getRequires();

	/**
	 * Returns the value of the '<em><b>School Grade Boundary2</b></em>' attribute.
	 * The default value is <code>"0.98"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>School Grade Boundary2</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>School Grade Boundary2</em>' attribute.
	 * @see #setSchoolGradeBoundary2(double)
	 * @see de.quamoco.qm.QmPackage#getQualityModel_SchoolGradeBoundary2()
	 * @model default="0.98" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
	 * @generated
	 */
	double getSchoolGradeBoundary2();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.QualityModel#getSchoolGradeBoundary2 <em>School Grade Boundary2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>School Grade Boundary2</em>' attribute.
	 * @see #getSchoolGradeBoundary2()
	 * @generated
	 */
	void setSchoolGradeBoundary2(double value);

	/**
	 * Returns the value of the '<em><b>School Grade Boundary3</b></em>' attribute.
	 * The default value is <code>"0.96"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>School Grade Boundary3</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>School Grade Boundary3</em>' attribute.
	 * @see #setSchoolGradeBoundary3(double)
	 * @see de.quamoco.qm.QmPackage#getQualityModel_SchoolGradeBoundary3()
	 * @model default="0.96" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
	 * @generated
	 */
	double getSchoolGradeBoundary3();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.QualityModel#getSchoolGradeBoundary3 <em>School Grade Boundary3</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>School Grade Boundary3</em>' attribute.
	 * @see #getSchoolGradeBoundary3()
	 * @generated
	 */
	void setSchoolGradeBoundary3(double value);

	/**
	 * Returns the value of the '<em><b>School Grade Boundary4</b></em>' attribute.
	 * The default value is <code>"0.94"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>School Grade Boundary4</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>School Grade Boundary4</em>' attribute.
	 * @see #setSchoolGradeBoundary4(double)
	 * @see de.quamoco.qm.QmPackage#getQualityModel_SchoolGradeBoundary4()
	 * @model default="0.94" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
	 * @generated
	 */
	double getSchoolGradeBoundary4();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.QualityModel#getSchoolGradeBoundary4 <em>School Grade Boundary4</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>School Grade Boundary4</em>' attribute.
	 * @see #getSchoolGradeBoundary4()
	 * @generated
	 */
	void setSchoolGradeBoundary4(double value);

	/**
	 * Returns the value of the '<em><b>School Grade Boundary5</b></em>' attribute.
	 * The default value is <code>"0.92"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>School Grade Boundary5</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>School Grade Boundary5</em>' attribute.
	 * @see #setSchoolGradeBoundary5(double)
	 * @see de.quamoco.qm.QmPackage#getQualityModel_SchoolGradeBoundary5()
	 * @model default="0.92" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
	 * @generated
	 */
	double getSchoolGradeBoundary5();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.QualityModel#getSchoolGradeBoundary5 <em>School Grade Boundary5</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>School Grade Boundary5</em>' attribute.
	 * @see #getSchoolGradeBoundary5()
	 * @generated
	 */
	void setSchoolGradeBoundary5(double value);

	/**
	 * Returns the value of the '<em><b>School Grade Boundary6</b></em>' attribute.
	 * The default value is <code>"0.90"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>School Grade Boundary6</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>School Grade Boundary6</em>' attribute.
	 * @see #setSchoolGradeBoundary6(double)
	 * @see de.quamoco.qm.QmPackage#getQualityModel_SchoolGradeBoundary6()
	 * @model default="0.90" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
	 * @generated
	 */
	double getSchoolGradeBoundary6();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.QualityModel#getSchoolGradeBoundary6 <em>School Grade Boundary6</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>School Grade Boundary6</em>' attribute.
	 * @see #getSchoolGradeBoundary6()
	 * @generated
	 */
	void setSchoolGradeBoundary6(double value);

	/**
	 * Returns the value of the '<em><b>Evaluations</b></em>' containment reference list.
	 * The list contents are of type {@link de.quamoco.qm.Evaluation}.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.Evaluation#getQualityModel <em>Quality Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Evaluations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Evaluations</em>' containment reference list.
	 * @see de.quamoco.qm.QmPackage#getQualityModel_Evaluations()
	 * @see de.quamoco.qm.Evaluation#getQualityModel
	 * @model opposite="qualityModel" containment="true"
	 * @generated
	 */
	EList<Evaluation> getEvaluations();

	/**
	 * Returns the value of the '<em><b>Measurement Methods</b></em>' containment reference list.
	 * The list contents are of type {@link de.quamoco.qm.MeasurementMethod}.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.MeasurementMethod#getQualityModel <em>Quality Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Measurement Methods</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Measurement Methods</em>' containment reference list.
	 * @see de.quamoco.qm.QmPackage#getQualityModel_MeasurementMethods()
	 * @see de.quamoco.qm.MeasurementMethod#getQualityModel
	 * @model opposite="qualityModel" containment="true"
	 * @generated
	 */
	EList<MeasurementMethod> getMeasurementMethods();

} // QualityModel
