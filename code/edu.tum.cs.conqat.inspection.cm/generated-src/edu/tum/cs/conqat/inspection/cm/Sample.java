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
 * $Id: Sample.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package edu.tum.cs.conqat.inspection.cm;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sample</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * One sample (e.g., a code snippet or source code file) to be inspected.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.Sample#getName <em>Name</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.Sample#getPackagePath <em>Package Path</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.Sample#getSourcePath <em>Source Path</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.Sample#getLineNumber <em>Line Number</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.Sample#getSourceStart <em>Source Start</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.Sample#getSourceEnd <em>Source End</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.Sample#getInspectionItem <em>Inspection Item</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getSample()
 * @model
 * @generated
 */
public interface Sample extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the item.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getSample_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.Sample#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Package Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The path of the package the source code file is contained in.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Package Path</em>' attribute.
	 * @see #setPackagePath(String)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getSample_PackagePath()
	 * @model required="true"
	 * @generated
	 */
	String getPackagePath();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.Sample#getPackagePath <em>Package Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Path</em>' attribute.
	 * @see #getPackagePath()
	 * @generated
	 */
	void setPackagePath(String value);

	/**
	 * Returns the value of the '<em><b>Source Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The path of the source code file this inspection item is taken from.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source Path</em>' attribute.
	 * @see #setSourcePath(String)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getSample_SourcePath()
	 * @model required="true"
	 * @generated
	 */
	String getSourcePath();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.Sample#getSourcePath <em>Source Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Path</em>' attribute.
	 * @see #getSourcePath()
	 * @generated
	 */
	void setSourcePath(String value);

	/**
	 * Returns the value of the '<em><b>Line Number</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The line number indicating the position of the sample in the source code file.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Line Number</em>' attribute.
	 * @see #setLineNumber(int)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getSample_LineNumber()
	 * @model default="-1"
	 * @generated
	 */
	int getLineNumber();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.Sample#getLineNumber <em>Line Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Number</em>' attribute.
	 * @see #getLineNumber()
	 * @generated
	 */
	void setLineNumber(int value);

	/**
	 * Returns the value of the '<em><b>Source Start</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The int value indicating the start position of the sample in the source code file.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source Start</em>' attribute.
	 * @see #setSourceStart(int)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getSample_SourceStart()
	 * @model default="-1"
	 * @generated
	 */
	int getSourceStart();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.Sample#getSourceStart <em>Source Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Start</em>' attribute.
	 * @see #getSourceStart()
	 * @generated
	 */
	void setSourceStart(int value);

	/**
	 * Returns the value of the '<em><b>Source End</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The int value indicating the end position of the sample in the source code file.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source End</em>' attribute.
	 * @see #setSourceEnd(int)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getSample_SourceEnd()
	 * @model default="-1"
	 * @generated
	 */
	int getSourceEnd();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.Sample#getSourceEnd <em>Source End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source End</em>' attribute.
	 * @see #getSourceEnd()
	 * @generated
	 */
	void setSourceEnd(int value);

	/**
	 * Returns the value of the '<em><b>Inspection Item</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.tum.cs.conqat.inspection.cm.InspectionItem#getSamples <em>Samples</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inspection Item</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inspection Item</em>' container reference.
	 * @see #setInspectionItem(InspectionItem)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getSample_InspectionItem()
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionItem#getSamples
	 * @model opposite="samples" required="true" transient="false"
	 * @generated
	 */
	InspectionItem getInspectionItem();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.Sample#getInspectionItem <em>Inspection Item</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inspection Item</em>' container reference.
	 * @see #getInspectionItem()
	 * @generated
	 */
	void setInspectionItem(InspectionItem value);

} // Sample
