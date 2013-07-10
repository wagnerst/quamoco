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

import de.quamoco.qm.Entity;
import de.quamoco.qm.Factor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Qm Adaptation Attributes</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.adaptation.model.status.QmAdaptationAttributes#getObject <em>Object</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.QmAdaptationAttributes#getViewpoint <em>Viewpoint</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.QmAdaptationAttributes#getQualityFocus <em>Quality Focus</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.adaptation.model.status.StatusPackage#getQmAdaptationAttributes()
 * @model
 * @generated
 */
public interface QmAdaptationAttributes extends AdaptationAttributes {
	/**
	 * Returns the value of the '<em><b>Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object</em>' reference.
	 * @see #setObject(Entity)
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getQmAdaptationAttributes_Object()
	 * @model
	 * @generated
	 */
	Entity getObject();

	/**
	 * Sets the value of the '{@link de.quamoco.adaptation.model.status.QmAdaptationAttributes#getObject <em>Object</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object</em>' reference.
	 * @see #getObject()
	 * @generated
	 */
	void setObject(Entity value);

	/**
	 * Returns the value of the '<em><b>Viewpoint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Viewpoint</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Viewpoint</em>' reference.
	 * @see #setViewpoint(Factor)
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getQmAdaptationAttributes_Viewpoint()
	 * @model
	 * @generated
	 */
	Factor getViewpoint();

	/**
	 * Sets the value of the '{@link de.quamoco.adaptation.model.status.QmAdaptationAttributes#getViewpoint <em>Viewpoint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Viewpoint</em>' reference.
	 * @see #getViewpoint()
	 * @generated
	 */
	void setViewpoint(Factor value);

	/**
	 * Returns the value of the '<em><b>Quality Focus</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quality Focus</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quality Focus</em>' reference.
	 * @see #setQualityFocus(Factor)
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getQmAdaptationAttributes_QualityFocus()
	 * @model
	 * @generated
	 */
	Factor getQualityFocus();

	/**
	 * Sets the value of the '{@link de.quamoco.adaptation.model.status.QmAdaptationAttributes#getQualityFocus <em>Quality Focus</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quality Focus</em>' reference.
	 * @see #getQualityFocus()
	 * @generated
	 */
	void setQualityFocus(Factor value);

} // QmAdaptationAttributes
