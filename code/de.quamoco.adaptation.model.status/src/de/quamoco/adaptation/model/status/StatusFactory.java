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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.quamoco.adaptation.model.status.StatusPackage
 * @generated
 */
public interface StatusFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StatusFactory eINSTANCE = de.quamoco.adaptation.model.status.impl.StatusFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Adaptation History Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Adaptation History Item</em>'.
	 * @generated
	 */
	AdaptationHistoryItem createAdaptationHistoryItem();

	/**
	 * Returns a new object of class '<em>Adaptation Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Adaptation Task</em>'.
	 * @generated
	 */
	AdaptationTask createAdaptationTask();

	/**
	 * Returns a new object of class '<em>User Marked Completed</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>User Marked Completed</em>'.
	 * @generated
	 */
	UserMarkedCompleted createUserMarkedCompleted();

	/**
	 * Returns a new object of class '<em>Feature Required</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Feature Required</em>'.
	 * @generated
	 */
	FeatureRequired createFeatureRequired();

	/**
	 * Returns a new object of class '<em>Adaptation Attributes</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Adaptation Attributes</em>'.
	 * @generated
	 */
	AdaptationAttributes createAdaptationAttributes();

	/**
	 * Returns a new object of class '<em>Qm Adaptation Attributes</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Qm Adaptation Attributes</em>'.
	 * @generated
	 */
	QmAdaptationAttributes createQmAdaptationAttributes();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	StatusPackage getStatusPackage();

} //StatusFactory
