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
 * $Id: InspectionItem.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package edu.tum.cs.conqat.inspection.cm;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Inspection Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An Inspection Item is one item to be inspected consisting of one or more samples and one or more measures.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.InspectionItem#getChecklist <em>Checklist</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.InspectionItem#getInspectionMeasures <em>Inspection Measures</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.InspectionItem#getSamples <em>Samples</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.InspectionItem#getInspectionEvaluations <em>Inspection Evaluations</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getInspectionItem()
 * @model
 * @generated
 */
public interface InspectionItem extends EObject {
	/**
	 * Returns the value of the '<em><b>Checklist</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.tum.cs.conqat.inspection.cm.Checklist#getInspectionItems <em>Inspection Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Checklist</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Checklist</em>' container reference.
	 * @see #setChecklist(Checklist)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getInspectionItem_Checklist()
	 * @see edu.tum.cs.conqat.inspection.cm.Checklist#getInspectionItems
	 * @model opposite="inspectionItems" required="true" transient="false"
	 * @generated
	 */
	Checklist getChecklist();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.InspectionItem#getChecklist <em>Checklist</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Checklist</em>' container reference.
	 * @see #getChecklist()
	 * @generated
	 */
	void setChecklist(Checklist value);

	/**
	 * Returns the value of the '<em><b>Inspection Measures</b></em>' reference list.
	 * The list contents are of type {@link edu.tum.cs.conqat.inspection.cm.InspectionMeasure}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inspection Measures</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inspection Measures</em>' reference list.
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getInspectionItem_InspectionMeasures()
	 * @model required="true"
	 * @generated
	 */
	EList<InspectionMeasure> getInspectionMeasures();

	/**
	 * Returns the value of the '<em><b>Samples</b></em>' containment reference list.
	 * The list contents are of type {@link edu.tum.cs.conqat.inspection.cm.Sample}.
	 * It is bidirectional and its opposite is '{@link edu.tum.cs.conqat.inspection.cm.Sample#getInspectionItem <em>Inspection Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Samples</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Samples</em>' containment reference list.
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getInspectionItem_Samples()
	 * @see edu.tum.cs.conqat.inspection.cm.Sample#getInspectionItem
	 * @model opposite="inspectionItem" containment="true" required="true"
	 * @generated
	 */
	EList<Sample> getSamples();

	/**
	 * Returns the value of the '<em><b>Inspection Evaluations</b></em>' containment reference list.
	 * The list contents are of type {@link edu.tum.cs.conqat.inspection.cm.InspectionEvaluation}.
	 * It is bidirectional and its opposite is '{@link edu.tum.cs.conqat.inspection.cm.InspectionEvaluation#getInspectionItem <em>Inspection Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inspection Evaluations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inspection Evaluations</em>' containment reference list.
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getInspectionItem_InspectionEvaluations()
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionEvaluation#getInspectionItem
	 * @model opposite="inspectionItem" containment="true" required="true"
	 * @generated
	 */
	EList<InspectionEvaluation> getInspectionEvaluations();

} // InspectionItem
