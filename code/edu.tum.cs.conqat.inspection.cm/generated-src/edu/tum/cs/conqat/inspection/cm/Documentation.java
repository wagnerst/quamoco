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
 * $Id: Documentation.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package edu.tum.cs.conqat.inspection.cm;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Documentation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A documentation file consisting of a name and a path.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.Documentation#getName <em>Name</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.Documentation#getPath <em>Path</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.Documentation#getType <em>Type</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.Documentation#getInspectionMeasure <em>Inspection Measure</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getDocumentation()
 * @model
 * @generated
 */
public interface Documentation extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the documentation file.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getDocumentation_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.Documentation#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The path to the documentation file.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getDocumentation_Path()
	 * @model required="true"
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.Documentation#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The type of the documentation: either "url" or "file".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getDocumentation_Type()
	 * @model required="true"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.Documentation#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Inspection Measure</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getDocumentations <em>Documentations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inspection Measure</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inspection Measure</em>' container reference.
	 * @see #setInspectionMeasure(InspectionMeasure)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getDocumentation_InspectionMeasure()
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getDocumentations
	 * @model opposite="documentations" required="true" transient="false"
	 * @generated
	 */
	InspectionMeasure getInspectionMeasure();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.Documentation#getInspectionMeasure <em>Inspection Measure</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inspection Measure</em>' container reference.
	 * @see #getInspectionMeasure()
	 * @generated
	 */
	void setInspectionMeasure(InspectionMeasure value);

} // Documentation
