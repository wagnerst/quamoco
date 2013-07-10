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
 * $Id: QualityModelImpl.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.quamoco.qm.Entity;
import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasurementMethod;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.Source;
import de.quamoco.qm.Tag;
import de.quamoco.qm.Tool;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Quality Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.quamoco.qm.impl.QualityModelImpl#getEntities <em>Entities</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.QualityModelImpl#getFactors <em>Factors</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.QualityModelImpl#getEvaluations <em>Evaluations</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.QualityModelImpl#getMeasures <em>Measures</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.QualityModelImpl#getMeasurementMethods <em>Measurement Methods</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.QualityModelImpl#getTools <em>Tools</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.QualityModelImpl#getTags <em>Tags</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.QualityModelImpl#getSources <em>Sources</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.QualityModelImpl#getRequires <em>Requires</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.QualityModelImpl#getSchoolGradeBoundary2 <em>School Grade Boundary2</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.QualityModelImpl#getSchoolGradeBoundary3 <em>School Grade Boundary3</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.QualityModelImpl#getSchoolGradeBoundary4 <em>School Grade Boundary4</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.QualityModelImpl#getSchoolGradeBoundary5 <em>School Grade Boundary5</em>}</li>
 *   <li>{@link de.quamoco.qm.impl.QualityModelImpl#getSchoolGradeBoundary6 <em>School Grade Boundary6</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QualityModelImpl extends NamedElementImpl implements QualityModel {
	/**
	 * The cached value of the '{@link #getEntities() <em>Entities</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntities()
	 * @generated
	 * @ordered
	 */
	protected EList<Entity> entities;
	/**
	 * The cached value of the '{@link #getFactors() <em>Factors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFactors()
	 * @generated
	 * @ordered
	 */
	protected EList<Factor> factors;
	/**
	 * The cached value of the '{@link #getEvaluations() <em>Evaluations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvaluations()
	 * @generated
	 * @ordered
	 */
	protected EList<Evaluation> evaluations;
	/**
	 * The cached value of the '{@link #getMeasures() <em>Measures</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeasures()
	 * @generated
	 * @ordered
	 */
	protected EList<Measure> measures;
	/**
	 * The cached value of the '{@link #getMeasurementMethods() <em>Measurement Methods</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeasurementMethods()
	 * @generated
	 * @ordered
	 */
	protected EList<MeasurementMethod> measurementMethods;
	/**
	 * The cached value of the '{@link #getTools() <em>Tools</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTools()
	 * @generated
	 * @ordered
	 */
	protected EList<Tool> tools;
	/**
	 * The cached value of the '{@link #getTags() <em>Tags</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTags()
	 * @generated
	 * @ordered
	 */
	protected EList<Tag> tags;
	/**
	 * The cached value of the '{@link #getSources() <em>Sources</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSources()
	 * @generated
	 * @ordered
	 */
	protected EList<Source> sources;
	/**
	 * The cached value of the '{@link #getRequires() <em>Requires</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequires()
	 * @generated
	 * @ordered
	 */
	protected EList<QualityModel> requires;
	/**
	 * The default value of the '{@link #getSchoolGradeBoundary2() <em>School Grade Boundary2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchoolGradeBoundary2()
	 * @generated
	 * @ordered
	 */
	protected static final double SCHOOL_GRADE_BOUNDARY2_EDEFAULT = 0.98;
	/**
	 * The cached value of the '{@link #getSchoolGradeBoundary2() <em>School Grade Boundary2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchoolGradeBoundary2()
	 * @generated
	 * @ordered
	 */
	protected double schoolGradeBoundary2 = SCHOOL_GRADE_BOUNDARY2_EDEFAULT;
	/**
	 * The default value of the '{@link #getSchoolGradeBoundary3() <em>School Grade Boundary3</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchoolGradeBoundary3()
	 * @generated
	 * @ordered
	 */
	protected static final double SCHOOL_GRADE_BOUNDARY3_EDEFAULT = 0.96;
	/**
	 * The cached value of the '{@link #getSchoolGradeBoundary3() <em>School Grade Boundary3</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchoolGradeBoundary3()
	 * @generated
	 * @ordered
	 */
	protected double schoolGradeBoundary3 = SCHOOL_GRADE_BOUNDARY3_EDEFAULT;
	/**
	 * The default value of the '{@link #getSchoolGradeBoundary4() <em>School Grade Boundary4</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchoolGradeBoundary4()
	 * @generated
	 * @ordered
	 */
	protected static final double SCHOOL_GRADE_BOUNDARY4_EDEFAULT = 0.94;
	/**
	 * The cached value of the '{@link #getSchoolGradeBoundary4() <em>School Grade Boundary4</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchoolGradeBoundary4()
	 * @generated
	 * @ordered
	 */
	protected double schoolGradeBoundary4 = SCHOOL_GRADE_BOUNDARY4_EDEFAULT;
	/**
	 * The default value of the '{@link #getSchoolGradeBoundary5() <em>School Grade Boundary5</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchoolGradeBoundary5()
	 * @generated
	 * @ordered
	 */
	protected static final double SCHOOL_GRADE_BOUNDARY5_EDEFAULT = 0.92;
	/**
	 * The cached value of the '{@link #getSchoolGradeBoundary5() <em>School Grade Boundary5</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchoolGradeBoundary5()
	 * @generated
	 * @ordered
	 */
	protected double schoolGradeBoundary5 = SCHOOL_GRADE_BOUNDARY5_EDEFAULT;
	/**
	 * The default value of the '{@link #getSchoolGradeBoundary6() <em>School Grade Boundary6</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchoolGradeBoundary6()
	 * @generated
	 * @ordered
	 */
	protected static final double SCHOOL_GRADE_BOUNDARY6_EDEFAULT = 0.9;
	/**
	 * The cached value of the '{@link #getSchoolGradeBoundary6() <em>School Grade Boundary6</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchoolGradeBoundary6()
	 * @generated
	 * @ordered
	 */
	protected double schoolGradeBoundary6 = SCHOOL_GRADE_BOUNDARY6_EDEFAULT;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QualityModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QmPackage.Literals.QUALITY_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Entity> getEntities() {
		if (entities == null) {
			entities = new EObjectContainmentWithInverseEList<Entity>(Entity.class, this, QmPackage.QUALITY_MODEL__ENTITIES, QmPackage.ENTITY__QUALITY_MODEL);
		}
		return entities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Factor> getFactors() {
		if (factors == null) {
			factors = new EObjectContainmentWithInverseEList<Factor>(Factor.class, this, QmPackage.QUALITY_MODEL__FACTORS, QmPackage.FACTOR__QUALITY_MODEL);
		}
		return factors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Measure> getMeasures() {
		if (measures == null) {
			measures = new EObjectContainmentWithInverseEList<Measure>(Measure.class, this, QmPackage.QUALITY_MODEL__MEASURES, QmPackage.MEASURE__QUALITY_MODEL);
		}
		return measures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Tag> getTags() {
		if (tags == null) {
			tags = new EObjectContainmentWithInverseEList<Tag>(Tag.class, this, QmPackage.QUALITY_MODEL__TAGS, QmPackage.TAG__QUALITY_MODEL);
		}
		return tags;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Tool> getTools() {
		if (tools == null) {
			tools = new EObjectContainmentWithInverseEList<Tool>(Tool.class, this, QmPackage.QUALITY_MODEL__TOOLS, QmPackage.TOOL__QUALITY_MODEL);
		}
		return tools;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Source> getSources() {
		if (sources == null) {
			sources = new EObjectContainmentWithInverseEList<Source>(Source.class, this, QmPackage.QUALITY_MODEL__SOURCES, QmPackage.SOURCE__QUALITY_MODEL);
		}
		return sources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<QualityModel> getRequires() {
		if (requires == null) {
			requires = new EObjectResolvingEList<QualityModel>(QualityModel.class, this, QmPackage.QUALITY_MODEL__REQUIRES);
		}
		return requires;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getSchoolGradeBoundary2() {
		return schoolGradeBoundary2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchoolGradeBoundary2(double newSchoolGradeBoundary2) {
		double oldSchoolGradeBoundary2 = schoolGradeBoundary2;
		schoolGradeBoundary2 = newSchoolGradeBoundary2;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY2, oldSchoolGradeBoundary2, schoolGradeBoundary2));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getSchoolGradeBoundary3() {
		return schoolGradeBoundary3;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchoolGradeBoundary3(double newSchoolGradeBoundary3) {
		double oldSchoolGradeBoundary3 = schoolGradeBoundary3;
		schoolGradeBoundary3 = newSchoolGradeBoundary3;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY3, oldSchoolGradeBoundary3, schoolGradeBoundary3));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getSchoolGradeBoundary4() {
		return schoolGradeBoundary4;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchoolGradeBoundary4(double newSchoolGradeBoundary4) {
		double oldSchoolGradeBoundary4 = schoolGradeBoundary4;
		schoolGradeBoundary4 = newSchoolGradeBoundary4;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY4, oldSchoolGradeBoundary4, schoolGradeBoundary4));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getSchoolGradeBoundary5() {
		return schoolGradeBoundary5;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchoolGradeBoundary5(double newSchoolGradeBoundary5) {
		double oldSchoolGradeBoundary5 = schoolGradeBoundary5;
		schoolGradeBoundary5 = newSchoolGradeBoundary5;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY5, oldSchoolGradeBoundary5, schoolGradeBoundary5));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getSchoolGradeBoundary6() {
		return schoolGradeBoundary6;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchoolGradeBoundary6(double newSchoolGradeBoundary6) {
		double oldSchoolGradeBoundary6 = schoolGradeBoundary6;
		schoolGradeBoundary6 = newSchoolGradeBoundary6;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY6, oldSchoolGradeBoundary6, schoolGradeBoundary6));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Evaluation> getEvaluations() {
		if (evaluations == null) {
			evaluations = new EObjectContainmentWithInverseEList<Evaluation>(Evaluation.class, this, QmPackage.QUALITY_MODEL__EVALUATIONS, QmPackage.EVALUATION__QUALITY_MODEL);
		}
		return evaluations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MeasurementMethod> getMeasurementMethods() {
		if (measurementMethods == null) {
			measurementMethods = new EObjectContainmentWithInverseEList<MeasurementMethod>(MeasurementMethod.class, this, QmPackage.QUALITY_MODEL__MEASUREMENT_METHODS, QmPackage.MEASUREMENT_METHOD__QUALITY_MODEL);
		}
		return measurementMethods;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QmPackage.QUALITY_MODEL__ENTITIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEntities()).basicAdd(otherEnd, msgs);
			case QmPackage.QUALITY_MODEL__FACTORS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFactors()).basicAdd(otherEnd, msgs);
			case QmPackage.QUALITY_MODEL__EVALUATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEvaluations()).basicAdd(otherEnd, msgs);
			case QmPackage.QUALITY_MODEL__MEASURES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMeasures()).basicAdd(otherEnd, msgs);
			case QmPackage.QUALITY_MODEL__MEASUREMENT_METHODS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMeasurementMethods()).basicAdd(otherEnd, msgs);
			case QmPackage.QUALITY_MODEL__TOOLS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTools()).basicAdd(otherEnd, msgs);
			case QmPackage.QUALITY_MODEL__TAGS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTags()).basicAdd(otherEnd, msgs);
			case QmPackage.QUALITY_MODEL__SOURCES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSources()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QmPackage.QUALITY_MODEL__ENTITIES:
				return ((InternalEList<?>)getEntities()).basicRemove(otherEnd, msgs);
			case QmPackage.QUALITY_MODEL__FACTORS:
				return ((InternalEList<?>)getFactors()).basicRemove(otherEnd, msgs);
			case QmPackage.QUALITY_MODEL__EVALUATIONS:
				return ((InternalEList<?>)getEvaluations()).basicRemove(otherEnd, msgs);
			case QmPackage.QUALITY_MODEL__MEASURES:
				return ((InternalEList<?>)getMeasures()).basicRemove(otherEnd, msgs);
			case QmPackage.QUALITY_MODEL__MEASUREMENT_METHODS:
				return ((InternalEList<?>)getMeasurementMethods()).basicRemove(otherEnd, msgs);
			case QmPackage.QUALITY_MODEL__TOOLS:
				return ((InternalEList<?>)getTools()).basicRemove(otherEnd, msgs);
			case QmPackage.QUALITY_MODEL__TAGS:
				return ((InternalEList<?>)getTags()).basicRemove(otherEnd, msgs);
			case QmPackage.QUALITY_MODEL__SOURCES:
				return ((InternalEList<?>)getSources()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case QmPackage.QUALITY_MODEL__ENTITIES:
				return getEntities();
			case QmPackage.QUALITY_MODEL__FACTORS:
				return getFactors();
			case QmPackage.QUALITY_MODEL__EVALUATIONS:
				return getEvaluations();
			case QmPackage.QUALITY_MODEL__MEASURES:
				return getMeasures();
			case QmPackage.QUALITY_MODEL__MEASUREMENT_METHODS:
				return getMeasurementMethods();
			case QmPackage.QUALITY_MODEL__TOOLS:
				return getTools();
			case QmPackage.QUALITY_MODEL__TAGS:
				return getTags();
			case QmPackage.QUALITY_MODEL__SOURCES:
				return getSources();
			case QmPackage.QUALITY_MODEL__REQUIRES:
				return getRequires();
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY2:
				return getSchoolGradeBoundary2();
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY3:
				return getSchoolGradeBoundary3();
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY4:
				return getSchoolGradeBoundary4();
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY5:
				return getSchoolGradeBoundary5();
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY6:
				return getSchoolGradeBoundary6();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case QmPackage.QUALITY_MODEL__ENTITIES:
				getEntities().clear();
				getEntities().addAll((Collection<? extends Entity>)newValue);
				return;
			case QmPackage.QUALITY_MODEL__FACTORS:
				getFactors().clear();
				getFactors().addAll((Collection<? extends Factor>)newValue);
				return;
			case QmPackage.QUALITY_MODEL__EVALUATIONS:
				getEvaluations().clear();
				getEvaluations().addAll((Collection<? extends Evaluation>)newValue);
				return;
			case QmPackage.QUALITY_MODEL__MEASURES:
				getMeasures().clear();
				getMeasures().addAll((Collection<? extends Measure>)newValue);
				return;
			case QmPackage.QUALITY_MODEL__MEASUREMENT_METHODS:
				getMeasurementMethods().clear();
				getMeasurementMethods().addAll((Collection<? extends MeasurementMethod>)newValue);
				return;
			case QmPackage.QUALITY_MODEL__TOOLS:
				getTools().clear();
				getTools().addAll((Collection<? extends Tool>)newValue);
				return;
			case QmPackage.QUALITY_MODEL__TAGS:
				getTags().clear();
				getTags().addAll((Collection<? extends Tag>)newValue);
				return;
			case QmPackage.QUALITY_MODEL__SOURCES:
				getSources().clear();
				getSources().addAll((Collection<? extends Source>)newValue);
				return;
			case QmPackage.QUALITY_MODEL__REQUIRES:
				getRequires().clear();
				getRequires().addAll((Collection<? extends QualityModel>)newValue);
				return;
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY2:
				setSchoolGradeBoundary2((Double)newValue);
				return;
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY3:
				setSchoolGradeBoundary3((Double)newValue);
				return;
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY4:
				setSchoolGradeBoundary4((Double)newValue);
				return;
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY5:
				setSchoolGradeBoundary5((Double)newValue);
				return;
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY6:
				setSchoolGradeBoundary6((Double)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case QmPackage.QUALITY_MODEL__ENTITIES:
				getEntities().clear();
				return;
			case QmPackage.QUALITY_MODEL__FACTORS:
				getFactors().clear();
				return;
			case QmPackage.QUALITY_MODEL__EVALUATIONS:
				getEvaluations().clear();
				return;
			case QmPackage.QUALITY_MODEL__MEASURES:
				getMeasures().clear();
				return;
			case QmPackage.QUALITY_MODEL__MEASUREMENT_METHODS:
				getMeasurementMethods().clear();
				return;
			case QmPackage.QUALITY_MODEL__TOOLS:
				getTools().clear();
				return;
			case QmPackage.QUALITY_MODEL__TAGS:
				getTags().clear();
				return;
			case QmPackage.QUALITY_MODEL__SOURCES:
				getSources().clear();
				return;
			case QmPackage.QUALITY_MODEL__REQUIRES:
				getRequires().clear();
				return;
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY2:
				setSchoolGradeBoundary2(SCHOOL_GRADE_BOUNDARY2_EDEFAULT);
				return;
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY3:
				setSchoolGradeBoundary3(SCHOOL_GRADE_BOUNDARY3_EDEFAULT);
				return;
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY4:
				setSchoolGradeBoundary4(SCHOOL_GRADE_BOUNDARY4_EDEFAULT);
				return;
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY5:
				setSchoolGradeBoundary5(SCHOOL_GRADE_BOUNDARY5_EDEFAULT);
				return;
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY6:
				setSchoolGradeBoundary6(SCHOOL_GRADE_BOUNDARY6_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case QmPackage.QUALITY_MODEL__ENTITIES:
				return entities != null && !entities.isEmpty();
			case QmPackage.QUALITY_MODEL__FACTORS:
				return factors != null && !factors.isEmpty();
			case QmPackage.QUALITY_MODEL__EVALUATIONS:
				return evaluations != null && !evaluations.isEmpty();
			case QmPackage.QUALITY_MODEL__MEASURES:
				return measures != null && !measures.isEmpty();
			case QmPackage.QUALITY_MODEL__MEASUREMENT_METHODS:
				return measurementMethods != null && !measurementMethods.isEmpty();
			case QmPackage.QUALITY_MODEL__TOOLS:
				return tools != null && !tools.isEmpty();
			case QmPackage.QUALITY_MODEL__TAGS:
				return tags != null && !tags.isEmpty();
			case QmPackage.QUALITY_MODEL__SOURCES:
				return sources != null && !sources.isEmpty();
			case QmPackage.QUALITY_MODEL__REQUIRES:
				return requires != null && !requires.isEmpty();
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY2:
				return schoolGradeBoundary2 != SCHOOL_GRADE_BOUNDARY2_EDEFAULT;
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY3:
				return schoolGradeBoundary3 != SCHOOL_GRADE_BOUNDARY3_EDEFAULT;
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY4:
				return schoolGradeBoundary4 != SCHOOL_GRADE_BOUNDARY4_EDEFAULT;
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY5:
				return schoolGradeBoundary5 != SCHOOL_GRADE_BOUNDARY5_EDEFAULT;
			case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY6:
				return schoolGradeBoundary6 != SCHOOL_GRADE_BOUNDARY6_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (schoolGradeBoundary2: ");
		result.append(schoolGradeBoundary2);
		result.append(", schoolGradeBoundary3: ");
		result.append(schoolGradeBoundary3);
		result.append(", schoolGradeBoundary4: ");
		result.append(schoolGradeBoundary4);
		result.append(", schoolGradeBoundary5: ");
		result.append(schoolGradeBoundary5);
		result.append(", schoolGradeBoundary6: ");
		result.append(schoolGradeBoundary6);
		result.append(')');
		return result.toString();
	}

} //QualityModelImpl
