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
package de.quamoco.adaptation.model.status.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import de.quamoco.adaptation.model.status.AdaptationAttributes;
import de.quamoco.adaptation.model.status.AdaptationHistoryItem;
import de.quamoco.adaptation.model.status.AdaptationSource;
import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.model.status.FeatureRequired;
import de.quamoco.adaptation.model.status.QmAdaptationAttributes;
import de.quamoco.adaptation.model.status.StatusFactory;
import de.quamoco.adaptation.model.status.StatusPackage;
import de.quamoco.adaptation.model.status.UserMarkedCompleted;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StatusFactoryImpl extends EFactoryImpl implements StatusFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StatusFactory init() {
		try {
			StatusFactory theStatusFactory = (StatusFactory)EPackage.Registry.INSTANCE.getEFactory("de.quamoco.adaptation.model.status"); 
			if (theStatusFactory != null) {
				return theStatusFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new StatusFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatusFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case StatusPackage.ADAPTATION_HISTORY_ITEM: return createAdaptationHistoryItem();
			case StatusPackage.ADAPTATION_TASK: return createAdaptationTask();
			case StatusPackage.USER_MARKED_COMPLETED: return createUserMarkedCompleted();
			case StatusPackage.FEATURE_REQUIRED: return createFeatureRequired();
			case StatusPackage.ADAPTATION_ATTRIBUTES: return createAdaptationAttributes();
			case StatusPackage.QM_ADAPTATION_ATTRIBUTES: return createQmAdaptationAttributes();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case StatusPackage.ADAPTATION_SOURCE:
				return createAdaptationSourceFromString(eDataType, initialValue);
			case StatusPackage.EOBJECT:
				return createEObjectFromString(eDataType, initialValue);
			case StatusPackage.ESTRUCTURAL_FEATURE:
				return createEStructuralFeatureFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case StatusPackage.ADAPTATION_SOURCE:
				return convertAdaptationSourceToString(eDataType, instanceValue);
			case StatusPackage.EOBJECT:
				return convertEObjectToString(eDataType, instanceValue);
			case StatusPackage.ESTRUCTURAL_FEATURE:
				return convertEStructuralFeatureToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdaptationHistoryItem createAdaptationHistoryItem() {
		AdaptationHistoryItemImpl adaptationHistoryItem = new AdaptationHistoryItemImpl();
		return adaptationHistoryItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdaptationTask createAdaptationTask() {
		AdaptationTaskImpl adaptationTask = new AdaptationTaskImpl();
		return adaptationTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserMarkedCompleted createUserMarkedCompleted() {
		UserMarkedCompletedImpl userMarkedCompleted = new UserMarkedCompletedImpl();
		return userMarkedCompleted;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureRequired createFeatureRequired() {
		FeatureRequiredImpl featureRequired = new FeatureRequiredImpl();
		return featureRequired;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdaptationAttributes createAdaptationAttributes() {
		AdaptationAttributesImpl adaptationAttributes = new AdaptationAttributesImpl();
		return adaptationAttributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QmAdaptationAttributes createQmAdaptationAttributes() {
		QmAdaptationAttributesImpl qmAdaptationAttributes = new QmAdaptationAttributesImpl();
		return qmAdaptationAttributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdaptationSource createAdaptationSourceFromString(EDataType eDataType, String initialValue) {
		AdaptationSource result = AdaptationSource.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAdaptationSourceToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject createEObjectFromString(EDataType eDataType, String initialValue) {
		return (EObject)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEObjectToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EStructuralFeature createEStructuralFeatureFromString(EDataType eDataType, String initialValue) {
		return (EStructuralFeature)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEStructuralFeatureToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatusPackage getStatusPackage() {
		return (StatusPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static StatusPackage getPackage() {
		return StatusPackage.eINSTANCE;
	}

} //StatusFactoryImpl
