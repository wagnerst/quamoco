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
 * $Id: Entity.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm;

import java.util.List;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An entity type is a type of element which is part of the software product 
 * or which is a resource required during development, maintenance and 
 * use of a software product. A generalization/specialization (is-a) relation 
 * between entity types forms the entity type hierarchy.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.qm.Entity#getIsA <em>Is A</em>}</li>
 *   <li>{@link de.quamoco.qm.Entity#getIsADirect <em>Is ADirect</em>}</li>
 *   <li>{@link de.quamoco.qm.Entity#getPartOf <em>Part Of</em>}</li>
 *   <li>{@link de.quamoco.qm.Entity#getPartOfDirect <em>Part Of Direct</em>}</li>
 *   <li>{@link de.quamoco.qm.Entity#getQualityModel <em>Quality Model</em>}</li>
 *   <li>{@link de.quamoco.qm.Entity#isStakeholder <em>Stakeholder</em>}</li>
 *   <li>{@link de.quamoco.qm.Entity#isUseCase <em>Use Case</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.qm.QmPackage#getEntity()
 * @model
 * @generated
 */
public interface Entity extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Is A</b></em>' containment reference list.
	 * The list contents are of type {@link de.quamoco.qm.Specialization}.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.Specialization#getChild <em>Child</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The parent entity type in the entity type hierarchy
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is A</em>' containment reference list.
	 * @see de.quamoco.qm.QmPackage#getEntity_IsA()
	 * @see de.quamoco.qm.Specialization#getChild
	 * @model opposite="child" containment="true"
	 * @generated
	 */
	EList<Specialization> getIsA();

	/**
	 * Returns the value of the '<em><b>Is ADirect</b></em>' reference list.
	 * The list contents are of type {@link de.quamoco.qm.Entity}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is ADirect</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is ADirect</em>' reference list.
	 * @see de.quamoco.qm.QmPackage#getEntity_IsADirect()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	EList<Entity> getIsADirect();

	/**
	 * Returns the value of the '<em><b>Part Of</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.Decomposition#getChild <em>Child</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Part Of</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Part Of</em>' containment reference.
	 * @see #setPartOf(Decomposition)
	 * @see de.quamoco.qm.QmPackage#getEntity_PartOf()
	 * @see de.quamoco.qm.Decomposition#getChild
	 * @model opposite="child" containment="true"
	 * @generated
	 */
	Decomposition getPartOf();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.Entity#getPartOf <em>Part Of</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Part Of</em>' containment reference.
	 * @see #getPartOf()
	 * @generated
	 */
	void setPartOf(Decomposition value);

	/**
	 * Returns the value of the '<em><b>Part Of Direct</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Part Of Direct</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Part Of Direct</em>' reference.
	 * @see #isSetPartOfDirect()
	 * @see #unsetPartOfDirect()
	 * @see #setPartOfDirect(Entity)
	 * @see de.quamoco.qm.QmPackage#getEntity_PartOfDirect()
	 * @model unsettable="true" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	Entity getPartOfDirect();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.Entity#getPartOfDirect <em>Part Of Direct</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Part Of Direct</em>' reference.
	 * @see #isSetPartOfDirect()
	 * @see #unsetPartOfDirect()
	 * @see #getPartOfDirect()
	 * @generated
	 */
	void setPartOfDirect(Entity value);

	/**
	 * Unsets the value of the '{@link de.quamoco.qm.Entity#getPartOfDirect <em>Part Of Direct</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPartOfDirect()
	 * @see #getPartOfDirect()
	 * @see #setPartOfDirect(Entity)
	 * @generated
	 */
	void unsetPartOfDirect();

	/**
	 * Returns whether the value of the '{@link de.quamoco.qm.Entity#getPartOfDirect <em>Part Of Direct</em>}' reference is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Part Of Direct</em>' reference is set.
	 * @see #unsetPartOfDirect()
	 * @see #getPartOfDirect()
	 * @see #setPartOfDirect(Entity)
	 * @generated
	 */
	boolean isSetPartOfDirect();

	/**
	 * Returns the value of the '<em><b>Quality Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.QualityModel#getEntities <em>Entities</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quality Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quality Model</em>' container reference.
	 * @see #setQualityModel(QualityModel)
	 * @see de.quamoco.qm.QmPackage#getEntity_QualityModel()
	 * @see de.quamoco.qm.QualityModel#getEntities
	 * @model opposite="entities" required="true" transient="false"
	 * @generated
	 */
	QualityModel getQualityModel();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.Entity#getQualityModel <em>Quality Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quality Model</em>' container reference.
	 * @see #getQualityModel()
	 * @generated
	 */
	void setQualityModel(QualityModel value);

	/**
	 * Returns the value of the '<em><b>Stakeholder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stakeholder</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stakeholder</em>' attribute.
	 * @see de.quamoco.qm.QmPackage#getEntity_Stakeholder()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	boolean isStakeholder();

	/**
	 * Returns the value of the '<em><b>Use Case</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Case</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Use Case</em>' attribute.
	 * @see de.quamoco.qm.QmPackage#getEntity_UseCase()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	boolean isUseCase();

	/** Get the {@link Entity}s by which this entity is specialized. */
	List<Entity> getSpecializedByDirect();

	/** Get the {@link Entity}s of which this entity consists. */
	List<Entity> getConsistsOfDirect();

	/**
	 * Get the {@link CharacterizingElement}s by which this entity is
	 * characterized.
	 */
	List<CharacterizingElement> getCharacterizedBy();
	

} // EntityType
