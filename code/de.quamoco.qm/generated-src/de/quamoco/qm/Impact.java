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
 * $Id: Impact.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Impact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An impact defines an influence of a factor on a quality aspect. The effect 
 * of the influence can be either positive or negative. The influence a factor 
 * has on a quality aspect is quantified by a single evaluation, which is 
 * termed impact evaluation. Each impact needs to specify in an explicit 
 * justification why the factor has an impact on the quality aspect. To 
 * ensure that the model contains only relevant impacts, this description of 
 * the justification is vital for the impact.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.quamoco.qm.Impact#getSource <em>Source</em>}</li>
 *   <li>{@link de.quamoco.qm.Impact#getTarget <em>Target</em>}</li>
 *   <li>{@link de.quamoco.qm.Impact#getJustification <em>Justification</em>}</li>
 *   <li>{@link de.quamoco.qm.Impact#getEffect <em>Effect</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.quamoco.qm.QmPackage#getImpact()
 * @model
 * @generated
 */
public interface Impact extends AnnotatedElement {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.quamoco.qm.Factor#getInfluences <em>Influences</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The factors which have this impact on the quality aspect
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source</em>' container reference.
	 * @see #setSource(Factor)
	 * @see de.quamoco.qm.QmPackage#getImpact_Source()
	 * @see de.quamoco.qm.Factor#getInfluences
	 * @model opposite="influences" required="true" transient="false" ordered="false"
	 * @generated
	 */
	Factor getSource();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.Impact#getSource <em>Source</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' container reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Factor value);

	/**
	 * Returns the value of the '<em><b>Effect</b></em>' attribute.
	 * The default value is <code>"POSITIVE"</code>.
	 * The literals are from the enumeration {@link de.quamoco.qm.Effect}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether this impact has positive or negative effect
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Effect</em>' attribute.
	 * @see de.quamoco.qm.Effect
	 * @see #isSetEffect()
	 * @see #unsetEffect()
	 * @see #setEffect(Effect)
	 * @see de.quamoco.qm.QmPackage#getImpact_Effect()
	 * @model default="POSITIVE" unsettable="true"
	 * @generated
	 */
	Effect getEffect();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.Impact#getEffect <em>Effect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Effect</em>' attribute.
	 * @see de.quamoco.qm.Effect
	 * @see #isSetEffect()
	 * @see #unsetEffect()
	 * @see #getEffect()
	 * @generated
	 */
	void setEffect(Effect value);

	/**
	 * Unsets the value of the '{@link de.quamoco.qm.Impact#getEffect <em>Effect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEffect()
	 * @see #getEffect()
	 * @see #setEffect(Effect)
	 * @generated
	 */
	void unsetEffect();

	/**
	 * Returns whether the value of the '{@link de.quamoco.qm.Impact#getEffect <em>Effect</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Effect</em>' attribute is set.
	 * @see #unsetEffect()
	 * @see #getEffect()
	 * @see #setEffect(Effect)
	 * @generated
	 */
	boolean isSetEffect();

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The quality aspect on which this impact has effect
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Factor)
	 * @see de.quamoco.qm.QmPackage#getImpact_Target()
	 * @model required="true"
	 * @generated
	 */
	Factor getTarget();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.Impact#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Factor value);

	/**
	 * Returns the value of the '<em><b>Justification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The justification of this impact
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Justification</em>' attribute.
	 * @see #setJustification(String)
	 * @see de.quamoco.qm.QmPackage#getImpact_Justification()
	 * @model required="true"
	 * @generated
	 */
	String getJustification();

	/**
	 * Sets the value of the '{@link de.quamoco.qm.Impact#getJustification <em>Justification</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Justification</em>' attribute.
	 * @see #getJustification()
	 * @generated
	 */
	void setJustification(String value);

} // Impact
