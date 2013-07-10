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
package de.quamoco.adaptation.model.status.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import de.quamoco.adaptation.model.status.AdaptationAttributes;
import de.quamoco.adaptation.model.status.AdaptationHistoryItem;
import de.quamoco.adaptation.model.status.AdaptationStatusItem;
import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.model.status.FeatureRequired;
import de.quamoco.adaptation.model.status.FulfillmentCriterion;
import de.quamoco.adaptation.model.status.QmAdaptationAttributes;
import de.quamoco.adaptation.model.status.StatusPackage;
import de.quamoco.adaptation.model.status.UserMarkedCompleted;
import de.quamoco.qm.AnnotationBase;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.quamoco.adaptation.model.status.StatusPackage
 * @generated
 */
public class StatusAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static StatusPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatusAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = StatusPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StatusSwitch<Adapter> modelSwitch =
		new StatusSwitch<Adapter>() {
			@Override
			public Adapter caseAdaptationStatusItem(AdaptationStatusItem object) {
				return createAdaptationStatusItemAdapter();
			}
			@Override
			public Adapter caseAdaptationHistoryItem(AdaptationHistoryItem object) {
				return createAdaptationHistoryItemAdapter();
			}
			@Override
			public Adapter caseAdaptationTask(AdaptationTask object) {
				return createAdaptationTaskAdapter();
			}
			@Override
			public Adapter caseFulfillmentCriterion(FulfillmentCriterion object) {
				return createFulfillmentCriterionAdapter();
			}
			@Override
			public Adapter caseUserMarkedCompleted(UserMarkedCompleted object) {
				return createUserMarkedCompletedAdapter();
			}
			@Override
			public Adapter caseFeatureRequired(FeatureRequired object) {
				return createFeatureRequiredAdapter();
			}
			@Override
			public Adapter caseAdaptationAttributes(AdaptationAttributes object) {
				return createAdaptationAttributesAdapter();
			}
			@Override
			public Adapter caseQmAdaptationAttributes(QmAdaptationAttributes object) {
				return createQmAdaptationAttributesAdapter();
			}
			@Override
			public Adapter caseAnnotationBase(AnnotationBase object) {
				return createAnnotationBaseAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.adaptation.model.status.AdaptationStatusItem <em>Adaptation Status Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.adaptation.model.status.AdaptationStatusItem
	 * @generated
	 */
	public Adapter createAdaptationStatusItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.adaptation.model.status.AdaptationHistoryItem <em>Adaptation History Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.adaptation.model.status.AdaptationHistoryItem
	 * @generated
	 */
	public Adapter createAdaptationHistoryItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.adaptation.model.status.AdaptationTask <em>Adaptation Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.adaptation.model.status.AdaptationTask
	 * @generated
	 */
	public Adapter createAdaptationTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.adaptation.model.status.FulfillmentCriterion <em>Fulfillment Criterion</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.adaptation.model.status.FulfillmentCriterion
	 * @generated
	 */
	public Adapter createFulfillmentCriterionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.adaptation.model.status.UserMarkedCompleted <em>User Marked Completed</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.adaptation.model.status.UserMarkedCompleted
	 * @generated
	 */
	public Adapter createUserMarkedCompletedAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.adaptation.model.status.FeatureRequired <em>Feature Required</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.adaptation.model.status.FeatureRequired
	 * @generated
	 */
	public Adapter createFeatureRequiredAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.adaptation.model.status.AdaptationAttributes <em>Adaptation Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.adaptation.model.status.AdaptationAttributes
	 * @generated
	 */
	public Adapter createAdaptationAttributesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.adaptation.model.status.QmAdaptationAttributes <em>Qm Adaptation Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.adaptation.model.status.QmAdaptationAttributes
	 * @generated
	 */
	public Adapter createQmAdaptationAttributesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.quamoco.qm.AnnotationBase <em>Annotation Base</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.quamoco.qm.AnnotationBase
	 * @generated
	 */
	public Adapter createAnnotationBaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //StatusAdapterFactory
