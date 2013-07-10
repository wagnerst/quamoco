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

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Required</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.adaptation.model.status.FeatureRequired#getRequiredFeatureName <em>Required Feature Name</em>}</li>
 *   <li>{@link de.quamoco.adaptation.model.status.FeatureRequired#getRequiredFeature <em>Required Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.adaptation.model.status.StatusPackage#getFeatureRequired()
 * @model
 * @generated
 */
public interface FeatureRequired extends FulfillmentCriterion {
	/**
	 * Returns the value of the '<em><b>Required Feature Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required Feature Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required Feature Name</em>' attribute.
	 * @see #setRequiredFeatureName(String)
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getFeatureRequired_RequiredFeatureName()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getRequiredFeatureName();

	/**
	 * Sets the value of the '{@link de.quamoco.adaptation.model.status.FeatureRequired#getRequiredFeatureName <em>Required Feature Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Required Feature Name</em>' attribute.
	 * @see #getRequiredFeatureName()
	 * @generated
	 */
	void setRequiredFeatureName(String value);

	/**
	 * Returns the value of the '<em><b>Required Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required Feature</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required Feature</em>' attribute.
	 * @see de.quamoco.adaptation.model.status.StatusPackage#getFeatureRequired_RequiredFeature()
	 * @model dataType="de.quamoco.adaptation.model.status.EStructuralFeature" required="true" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	EStructuralFeature getRequiredFeature();

} // FeatureRequired
