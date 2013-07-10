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
 * $Id: AnnotatedElement.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Annotated Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An annotated element can have a number of annotations which are key-
 * value-pairs.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.qm.AnnotatedElement#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link de.quamoco.qm.AnnotatedElement#getAdvancedAnnotations <em>Advanced Annotations</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.qm.QmPackage#getAnnotatedElement()
 * @model abstract="true"
 * @generated
 */
public interface AnnotatedElement extends TaggedElement {
	/**
	 * Returns the value of the '<em><b>Annotations</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The key-value-pairs defined by this annotated element
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Annotations</em>' map.
	 * @see de.quamoco.qm.QmPackage#getAnnotatedElement_Annotations()
	 * @model mapType="de.quamoco.qm.Annotation<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 * @generated
	 */
	EMap<String, String> getAnnotations();

	/**
	 * Returns the value of the '<em><b>Advanced Annotations</b></em>' containment reference list.
	 * The list contents are of type {@link de.quamoco.qm.AnnotationBase}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Advanced Annotations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Advanced Annotations</em>' containment reference list.
	 * @see de.quamoco.qm.QmPackage#getAnnotatedElement_AdvancedAnnotations()
	 * @model containment="true"
	 * @generated
	 */
	EList<AnnotationBase> getAdvancedAnnotations();

} // AnnotatedElement
