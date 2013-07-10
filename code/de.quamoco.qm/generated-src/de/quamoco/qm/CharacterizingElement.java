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
 * $Id: CharacterizingElement.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Characterizing Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.qm.CharacterizingElement#getCharacterizes <em>Characterizes</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.qm.QmPackage#getCharacterizingElement()
 * @model abstract="true"
 * @generated
 */
public interface CharacterizingElement extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Characterizes</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The entity type which this factor characterizes
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Characterizes</em>' reference.
	 * @see #isSetCharacterizes()
	 * @see #unsetCharacterizes()
	 * @see #setCharacterizes(Entity)
	 * @see de.quamoco.qm.QmPackage#getCharacterizingElement_Characterizes()
	 * @model unsettable="true"
	 * @generated
	 */
	Entity getCharacterizes();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.CharacterizingElement#getCharacterizes <em>Characterizes</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Characterizes</em>' reference.
	 * @see #isSetCharacterizes()
	 * @see #unsetCharacterizes()
	 * @see #getCharacterizes()
	 * @generated
	 */
	void setCharacterizes(Entity value);

	/**
	 * Unsets the value of the '{@link de.quamoco.qm.CharacterizingElement#getCharacterizes <em>Characterizes</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCharacterizes()
	 * @see #getCharacterizes()
	 * @see #setCharacterizes(Entity)
	 * @generated
	 */
	void unsetCharacterizes();

	/**
	 * Returns whether the value of the '{@link de.quamoco.qm.CharacterizingElement#getCharacterizes <em>Characterizes</em>}' reference is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Characterizes</em>' reference is set.
	 * @see #unsetCharacterizes()
	 * @see #getCharacterizes()
	 * @see #setCharacterizes(Entity)
	 * @generated
	 */
	boolean isSetCharacterizes();

} // CharacterizingElement
