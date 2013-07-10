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
 * $Id$
 */
package de.quamoco.adaptation.model.status;

import de.quamoco.qm.AnnotationBase;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Adaptation Attributes</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.adaptation.model.status.AdaptationAttributes#getAdaptationId <em>Adaptation Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.adaptation.model.status.StatusPackage#getAdaptationAttributes()
 * @model
 * @generated
 */
public interface AdaptationAttributes extends AnnotationBase {
	/**
	 * Returns the value of the '<em><b>Adaptation Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Adaptation Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Adaptation Id</em>' attribute.
	 * @see #setAdaptationId(String)
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getAdaptationAttributes_AdaptationId()
	 * @model
	 * @generated
	 */
	String getAdaptationId();

	/**
	 * Sets the value of the '{@link de.quamoco.adaptation.model.status.AdaptationAttributes#getAdaptationId <em>Adaptation Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Adaptation Id</em>' attribute.
	 * @see #getAdaptationId()
	 * @generated
	 */
	void setAdaptationId(String value);

} // AdaptationAttributes
