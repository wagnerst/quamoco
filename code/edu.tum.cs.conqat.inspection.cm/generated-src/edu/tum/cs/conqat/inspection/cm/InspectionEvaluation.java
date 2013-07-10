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
 * $Id: InspectionEvaluation.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package edu.tum.cs.conqat.inspection.cm;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Inspection Evaluation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * One evaluation of a sample relating to a measure.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.InspectionEvaluation#getValue <em>Value</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.InspectionEvaluation#getSample <em>Sample</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.InspectionEvaluation#getInspectionMeasure <em>Inspection Measure</em>}</li>
 *   <li>{@link edu.tum.cs.conqat.inspection.cm.InspectionEvaluation#getInspectionItem <em>Inspection Item</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getInspectionEvaluation()
 * @model
 * @generated
 */
public interface InspectionEvaluation extends EObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The value of the evaluation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(int)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getInspectionEvaluation_Value()
	 * @model default="-1" required="true"
	 * @generated
	 */
	int getValue();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.InspectionEvaluation#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(int value);

	/**
	 * Returns the value of the '<em><b>Sample</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sample</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sample</em>' reference.
	 * @see #setSample(Sample)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getInspectionEvaluation_Sample()
	 * @model required="true"
	 * @generated
	 */
	Sample getSample();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.InspectionEvaluation#getSample <em>Sample</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sample</em>' reference.
	 * @see #getSample()
	 * @generated
	 */
	void setSample(Sample value);

	/**
	 * Returns the value of the '<em><b>Inspection Measure</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inspection Measure</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inspection Measure</em>' reference.
	 * @see #setInspectionMeasure(InspectionMeasure)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getInspectionEvaluation_InspectionMeasure()
	 * @model required="true"
	 * @generated
	 */
	InspectionMeasure getInspectionMeasure();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.InspectionEvaluation#getInspectionMeasure <em>Inspection Measure</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inspection Measure</em>' reference.
	 * @see #getInspectionMeasure()
	 * @generated
	 */
	void setInspectionMeasure(InspectionMeasure value);

	/**
	 * Returns the value of the '<em><b>Inspection Item</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.tum.cs.conqat.inspection.cm.InspectionItem#getInspectionEvaluations <em>Inspection Evaluations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inspection Item</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inspection Item</em>' container reference.
	 * @see #setInspectionItem(InspectionItem)
	 * @see edu.tum.cs.conqat.inspection.cm.CmPackage#getInspectionEvaluation_InspectionItem()
	 * @see edu.tum.cs.conqat.inspection.cm.InspectionItem#getInspectionEvaluations
	 * @model opposite="inspectionEvaluations" required="true" transient="false"
	 * @generated
	 */
	InspectionItem getInspectionItem();

	/**
	 * Sets the value of the '{@link edu.tum.cs.conqat.inspection.cm.InspectionEvaluation#getInspectionItem <em>Inspection Item</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inspection Item</em>' container reference.
	 * @see #getInspectionItem()
	 * @generated
	 */
	void setInspectionItem(InspectionItem value);

} // InspectionEvaluation
