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
 * $Id: InspectionMeasure.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package edu.tum.cs.conqat.inspection.cm;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Inspection Measure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A measure to be inspected.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getMeasureInfo <em>Measure Info</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getMeasureName <em>Measure Name</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getInstrumentInfo <em>Instrument Info</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getSampleType <em>Sample Type</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getDocumentations <em>Documentations</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getInspectionMeasure()
 * @model
 * @generated
 */
public interface InspectionMeasure extends EObject {
	/**
	 * Returns the value of the '<em><b>Measure Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Optional information on the measurement.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Measure Info</em>' attribute.
	 * @see #setMeasureInfo(String)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getInspectionMeasure_MeasureInfo()
	 * @model required="true"
	 * @generated
	 */
	String getMeasureInfo();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getMeasureInfo <em>Measure Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Measure Info</em>' attribute.
	 * @see #getMeasureInfo()
	 * @generated
	 */
	void setMeasureInfo(String value);

	/**
	 * Returns the value of the '<em><b>Measure Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the measurement evaluated in the checklist.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Measure Name</em>' attribute.
	 * @see #setMeasureName(String)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getInspectionMeasure_MeasureName()
	 * @model required="true"
	 * @generated
	 */
	String getMeasureName();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getMeasureName <em>Measure Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Measure Name</em>' attribute.
	 * @see #getMeasureName()
	 * @generated
	 */
	void setMeasureName(String value);

	/**
	 * Returns the value of the '<em><b>Instrument Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Optional information on the manual instrument.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Instrument Info</em>' attribute.
	 * @see #setInstrumentInfo(String)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getInspectionMeasure_InstrumentInfo()
	 * @model required="true"
	 * @generated
	 */
	String getInstrumentInfo();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getInstrumentInfo <em>Instrument Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instrument Info</em>' attribute.
	 * @see #getInstrumentInfo()
	 * @generated
	 */
	void setInstrumentInfo(String value);

	/**
	 * Returns the value of the '<em><b>Sample Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The type of the sample, e.g. Identifier.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sample Type</em>' attribute.
	 * @see #setSampleType(String)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getInspectionMeasure_SampleType()
	 * @model required="true"
	 * @generated
	 */
	String getSampleType();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.InspectionMeasure#getSampleType <em>Sample Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sample Type</em>' attribute.
	 * @see #getSampleType()
	 * @generated
	 */
	void setSampleType(String value);

	/**
	 * Returns the value of the '<em><b>Documentations</b></em>' containment reference list.
	 * The list contents are of type {@link edu.tum.cs.conqat.inspection.cm.Documentation}.
	 * It is bidirectional and its opposite is '{@link edu.tum.cs.conqat.inspection.cm.Documentation#getInspectionMeasure <em>Inspection Measure</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Documentations</em>' containment reference list.
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getInspectionMeasure_Documentations()
	 * @see edu.tum.cs.conqat.inspection.cm.Documentation#getInspectionMeasure
	 * @model opposite="inspectionMeasure" containment="true"
	 * @generated
	 */
	EList<Documentation> getDocumentations();

} // InspectionMeasure
