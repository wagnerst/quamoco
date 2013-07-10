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
 * $Id: Checklist.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package edu.tum.cs.conqat.inspection.cm;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Checklist</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Checklist has a name, an  inspector, and several inspection items 
 * that have to be evaluated.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.Checklist#getInspector <em>Inspector</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.Checklist#getInspectionItems <em>Inspection Items</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.Checklist#getInspectionMeasures <em>Inspection Measures</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.Checklist#getQmFileName <em>Qm File Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getChecklist()
 * @model
 * @generated
 */
public interface Checklist extends EObject {
	/**
	 * Returns the value of the '<em><b>Inspector</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The inspector the checklist shall be completed by.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Inspector</em>' attribute.
	 * @see #setInspector(String)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getChecklist_Inspector()
	 * @model required="true"
	 * @generated
	 */
	String getInspector();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.Checklist#getInspector <em>Inspector</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inspector</em>' attribute.
	 * @see #getInspector()
	 * @generated
	 */
	void setInspector(String value);

	/**
	 * Returns the value of the '<em><b>Inspection Items</b></em>' containment reference list.
	 * The list contents are of type {@link edu.tum.cs.conqat.inspection.cm.InspectionItem}.
	 * It is bidirectional and its opposite is '{@link edu.tum.cs.conqat.inspection.cm.InspectionItem#getChecklist <em>Checklist</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inspection Items</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inspection Items</em>' containment reference list.
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getChecklist_InspectionItems()
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionItem#getChecklist
	 * @model opposite="checklist" containment="true" required="true" ordered="false"
	 * @generated
	 */
	EList<InspectionItem> getInspectionItems();

	/**
	 * Returns the value of the '<em><b>Inspection Measures</b></em>' containment reference list.
	 * The list contents are of type {@link edu.tum.cs.conqat.inspection.cm.InspectionMeasure}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inspection Measures</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inspection Measures</em>' containment reference list.
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getChecklist_InspectionMeasures()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<InspectionMeasure> getInspectionMeasures();

	/**
	 * Returns the value of the '<em><b>Qm File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the quality model file.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Qm File Name</em>' attribute.
	 * @see #setQmFileName(String)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getChecklist_QmFileName()
	 * @model required="true"
	 * @generated
	 */
	String getQmFileName();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.Checklist#getQmFileName <em>Qm File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qm File Name</em>' attribute.
	 * @see #getQmFileName()
	 * @generated
	 */
	void setQmFileName(String value);

} // Checklist
