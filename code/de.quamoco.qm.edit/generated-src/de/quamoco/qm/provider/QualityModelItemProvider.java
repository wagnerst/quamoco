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
 * $Id: QualityModelItemProvider.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.QmFactory;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.provider.util.ITransientParentItemProvider;
import de.quamoco.qm.provider.util.TransientChildrenStore;
import de.quamoco.qm.provider.util.TransientItemProvider;
import de.quamoco.qm.provider.util.TransientItemProviderUtils;

/**
 * This is the item provider adapter for a {@link de.quamoco.qm.QualityModel}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class QualityModelItemProvider extends NamedElementItemProvider
		implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource,
		IItemColorProvider, ITransientParentItemProvider {

	/** Special store to maintain the transient children. */
	protected TransientChildrenStore children = new TransientChildrenStore(this);

	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public QualityModelItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addRequiresPropertyDescriptor(object);
			addSchoolGradeBoundary2PropertyDescriptor(object);
			addSchoolGradeBoundary3PropertyDescriptor(object);
			addSchoolGradeBoundary4PropertyDescriptor(object);
			addSchoolGradeBoundary5PropertyDescriptor(object);
			addSchoolGradeBoundary6PropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Requires feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addRequiresPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_QualityModel_requires_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_QualityModel_requires_feature",
						"_UI_QualityModel_type"),
				QmPackage.Literals.QUALITY_MODEL__REQUIRES, true, false, true,
				null, null, null));
	}

	/**
	 * This adds a property descriptor for the School Grade Boundary2 feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addSchoolGradeBoundary2PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_QualityModel_schoolGradeBoundary2_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_QualityModel_schoolGradeBoundary2_feature",
						"_UI_QualityModel_type"),
				QmPackage.Literals.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY2, true,
				false, false, ItemPropertyDescriptor.REAL_VALUE_IMAGE, null,
				null));
	}

	/**
	 * This adds a property descriptor for the School Grade Boundary3 feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addSchoolGradeBoundary3PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_QualityModel_schoolGradeBoundary3_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_QualityModel_schoolGradeBoundary3_feature",
						"_UI_QualityModel_type"),
				QmPackage.Literals.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY3, true,
				false, false, ItemPropertyDescriptor.REAL_VALUE_IMAGE, null,
				null));
	}

	/**
	 * This adds a property descriptor for the School Grade Boundary4 feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addSchoolGradeBoundary4PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_QualityModel_schoolGradeBoundary4_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_QualityModel_schoolGradeBoundary4_feature",
						"_UI_QualityModel_type"),
				QmPackage.Literals.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY4, true,
				false, false, ItemPropertyDescriptor.REAL_VALUE_IMAGE, null,
				null));
	}

	/**
	 * This adds a property descriptor for the School Grade Boundary5 feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addSchoolGradeBoundary5PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_QualityModel_schoolGradeBoundary5_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_QualityModel_schoolGradeBoundary5_feature",
						"_UI_QualityModel_type"),
				QmPackage.Literals.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY5, true,
				false, false, ItemPropertyDescriptor.REAL_VALUE_IMAGE, null,
				null));
	}

	/**
	 * This adds a property descriptor for the School Grade Boundary6 feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addSchoolGradeBoundary6PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_QualityModel_schoolGradeBoundary6_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_QualityModel_schoolGradeBoundary6_feature",
						"_UI_QualityModel_type"),
				QmPackage.Literals.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY6, true,
				false, false, ItemPropertyDescriptor.REAL_VALUE_IMAGE, null,
				null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to
	 * deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand},
	 * {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in
	 * {@link #createCommand}. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(
			Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(QmPackage.Literals.QUALITY_MODEL__ENTITIES);
			childrenFeatures.add(QmPackage.Literals.QUALITY_MODEL__FACTORS);
			childrenFeatures.add(QmPackage.Literals.QUALITY_MODEL__EVALUATIONS);
			childrenFeatures.add(QmPackage.Literals.QUALITY_MODEL__MEASURES);
			childrenFeatures
					.add(QmPackage.Literals.QUALITY_MODEL__MEASUREMENT_METHODS);
			childrenFeatures.add(QmPackage.Literals.QUALITY_MODEL__TOOLS);
			childrenFeatures.add(QmPackage.Literals.QUALITY_MODEL__TAGS);
			childrenFeatures.add(QmPackage.Literals.QUALITY_MODEL__SOURCES);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper
		// feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns QualityModel.gif. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object,
				getResourceLocator().getImage("full/obj16/QualityModel"));
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		QualityModel qualityModel = (QualityModel) object;
		String label = qualityModel.getName();
		return label == null ? "" : label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to
	 * update any cached children and by creating a viewer notification, which
	 * it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(QualityModel.class)) {
		case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY2:
		case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY3:
		case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY4:
		case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY5:
		case QmPackage.QUALITY_MODEL__SCHOOL_GRADE_BOUNDARY6:
			fireNotifyChanged(new ViewerNotification(notification,
					notification.getNotifier(), false, true));
			return;
		case QmPackage.QUALITY_MODEL__ENTITIES:
		case QmPackage.QUALITY_MODEL__FACTORS:
		case QmPackage.QUALITY_MODEL__EVALUATIONS:
		case QmPackage.QUALITY_MODEL__MEASURES:
		case QmPackage.QUALITY_MODEL__MEASUREMENT_METHODS:
		case QmPackage.QUALITY_MODEL__TOOLS:
		case QmPackage.QUALITY_MODEL__TAGS:
		case QmPackage.QUALITY_MODEL__SOURCES:
			fireNotifyChanged(new ViewerNotification(notification,
					notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(
			Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(
				QmPackage.Literals.QUALITY_MODEL__ENTITIES,
				QmFactory.eINSTANCE.createEntity()));

		newChildDescriptors.add(createChildParameter(
				QmPackage.Literals.QUALITY_MODEL__FACTORS,
				QmFactory.eINSTANCE.createFactor()));

		newChildDescriptors.add(createChildParameter(
				QmPackage.Literals.QUALITY_MODEL__EVALUATIONS,
				QmFactory.eINSTANCE.createTextEvaluation()));

		newChildDescriptors.add(createChildParameter(
				QmPackage.Literals.QUALITY_MODEL__EVALUATIONS,
				QmFactory.eINSTANCE.createQIESLEvaluation()));

		newChildDescriptors.add(createChildParameter(
				QmPackage.Literals.QUALITY_MODEL__EVALUATIONS,
				QmFactory.eINSTANCE.createSingleMeasureEvaluation()));

		newChildDescriptors.add(createChildParameter(
				QmPackage.Literals.QUALITY_MODEL__EVALUATIONS,
				QmFactory.eINSTANCE.createWeightedSumFactorAggregation()));

		newChildDescriptors.add(createChildParameter(
				QmPackage.Literals.QUALITY_MODEL__EVALUATIONS,
				QmFactory.eINSTANCE.createManualEvaluation()));

		newChildDescriptors.add(createChildParameter(
				QmPackage.Literals.QUALITY_MODEL__EVALUATIONS,
				QmFactory.eINSTANCE.createWeightedSumMultiMeasureEvaluation()));

		newChildDescriptors.add(createChildParameter(
				QmPackage.Literals.QUALITY_MODEL__MEASURES,
				QmFactory.eINSTANCE.createMeasure()));

		newChildDescriptors.add(createChildParameter(
				QmPackage.Literals.QUALITY_MODEL__MEASURES,
				QmFactory.eINSTANCE.createNormalizationMeasure()));

		newChildDescriptors.add(createChildParameter(
				QmPackage.Literals.QUALITY_MODEL__MEASUREMENT_METHODS,
				QmFactory.eINSTANCE.createManualInstrument()));

		newChildDescriptors.add(createChildParameter(
				QmPackage.Literals.QUALITY_MODEL__MEASUREMENT_METHODS,
				QmFactory.eINSTANCE.createToolBasedInstrument()));

		newChildDescriptors.add(createChildParameter(
				QmPackage.Literals.QUALITY_MODEL__MEASUREMENT_METHODS,
				QmFactory.eINSTANCE.createTextAggregation()));

		newChildDescriptors.add(createChildParameter(
				QmPackage.Literals.QUALITY_MODEL__MEASUREMENT_METHODS,
				QmFactory.eINSTANCE.createQIESLAggregation()));

		newChildDescriptors.add(createChildParameter(
				QmPackage.Literals.QUALITY_MODEL__MEASUREMENT_METHODS,
				QmFactory.eINSTANCE.createFindingsUnionMeasureAggregation()));

		newChildDescriptors.add(createChildParameter(
				QmPackage.Literals.QUALITY_MODEL__MEASUREMENT_METHODS,
				QmFactory.eINSTANCE.createNumberMeanMeasureAggregation()));

		newChildDescriptors.add(createChildParameter(
				QmPackage.Literals.QUALITY_MODEL__TOOLS,
				QmFactory.eINSTANCE.createTool()));

		newChildDescriptors.add(createChildParameter(
				QmPackage.Literals.QUALITY_MODEL__TAGS,
				QmFactory.eINSTANCE.createTag()));

		newChildDescriptors.add(createChildParameter(
				QmPackage.Literals.QUALITY_MODEL__SOURCES,
				QmFactory.eINSTANCE.createSource()));
	}

	/** {@inheritDoc} */
	@Override
	public Collection<?> getChildren(Object object) {
		return children.getChildren(object);
	}

	/** Get the provider that groups the values of a certain children feature. */
	public TransientItemProvider getProvider(EStructuralFeature feature) {
		return children.getProvider(feature);
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	protected Command createRemoveCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection) {
		return TransientItemProviderUtils.createWrappedCommand(this,
				super.createRemoveCommand(domain, owner, feature, collection),
				owner, feature);
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	protected Command createAddCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection, int index) {
		return TransientItemProviderUtils.createWrappedCommand(this, super
				.createAddCommand(domain, owner, feature, collection, index),
				owner, feature);
	}

	/** {@inheritDoc} */
	@Override
	protected Command createCreateChildCommand(EditingDomain domain,
			EObject owner, EStructuralFeature feature, Object value, int index,
			Collection<?> collection) {
		if (value instanceof Evaluation) {
			Evaluation evaluation = (Evaluation) value;
			evaluation.setName(evaluation.eClass().getName());
		}
		return super.createCreateChildCommand(domain, owner, feature, value,
				index, collection);
	}
}
