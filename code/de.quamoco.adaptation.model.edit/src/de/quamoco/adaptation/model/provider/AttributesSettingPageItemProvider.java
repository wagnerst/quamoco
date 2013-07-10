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
package de.quamoco.adaptation.model.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import de.quamoco.adaptation.model.AdaptationModelPackage;
import de.quamoco.adaptation.model.AttributesSettingPage;
import de.quamoco.adaptation.model.provider.descriptor.AttributesSettingPageFactorHierarchyPropertyDescriptor;
import de.quamoco.adaptation.model.provider.descriptor.AttributesSettingPageObjectHierarchyPropertyDescriptor;

/**
 * This is the item provider adapter for a {@link de.quamoco.adaptation.model.AttributesSettingPage} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AttributesSettingPageItemProvider
	extends WizardPageItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributesSettingPageItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addObjectHierarchyLevelPropertyDescriptor(object);
			addViewpointExceptionsPropertyDescriptor(object);
			addQualityFocusHierarchyLevelPropertyDescriptor(object);
			addObjectHierarchiesPropertyDescriptor(object);
			addViewpointHierarchiesPropertyDescriptor(object);
			addQualityFocusHierarchiesPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Object Hierarchy Level feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addObjectHierarchyLevelPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AttributesSettingPage_objectHierarchyLevel_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AttributesSettingPage_objectHierarchyLevel_feature", "_UI_AttributesSettingPage_type"),
				 AdaptationModelPackage.Literals.ATTRIBUTES_SETTING_PAGE__OBJECT_HIERARCHY_LEVEL,
				 false,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Viewpoint Exceptions feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addViewpointExceptionsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AttributesSettingPage_viewpointExceptions_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AttributesSettingPage_viewpointExceptions_feature", "_UI_AttributesSettingPage_type"),
				 AdaptationModelPackage.Literals.ATTRIBUTES_SETTING_PAGE__VIEWPOINT_EXCEPTIONS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Quality Focus Hierarchy Level feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addQualityFocusHierarchyLevelPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AttributesSettingPage_qualityFocusHierarchyLevel_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AttributesSettingPage_qualityFocusHierarchyLevel_feature", "_UI_AttributesSettingPage_type"),
				 AdaptationModelPackage.Literals.ATTRIBUTES_SETTING_PAGE__QUALITY_FOCUS_HIERARCHY_LEVEL,
				 false,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Object Hierarchies feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addObjectHierarchiesPropertyDescriptorGen(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AttributesSettingPage_objectHierarchies_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AttributesSettingPage_objectHierarchies_feature", "_UI_AttributesSettingPage_type"),
				 AdaptationModelPackage.Literals.ATTRIBUTES_SETTING_PAGE__OBJECT_HIERARCHIES,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Object Hierarchies feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void addObjectHierarchiesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new AttributesSettingPageObjectHierarchyPropertyDescriptor(getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_AttributesSettingPage_objectHierarchies_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_AttributesSettingPage_objectHierarchies_feature", "_UI_AttributesSettingPage_type"),
				AdaptationModelPackage.Literals.ATTRIBUTES_SETTING_PAGE__OBJECT_HIERARCHIES));
	}

	/**
	 * This adds a property descriptor for the Viewpoint Hierarchies feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addViewpointHierarchiesPropertyDescriptorGen(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AttributesSettingPage_viewpointHierarchies_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AttributesSettingPage_viewpointHierarchies_feature", "_UI_AttributesSettingPage_type"),
				 AdaptationModelPackage.Literals.ATTRIBUTES_SETTING_PAGE__VIEWPOINT_HIERARCHIES,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Viewpoint Hierarchies feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void addViewpointHierarchiesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new AttributesSettingPageFactorHierarchyPropertyDescriptor(getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_AttributesSettingPage_viewpointHierarchies_feature"), getString(
						"_UI_PropertyDescriptor_description", "_UI_AttributesSettingPage_viewpointHierarchies_feature",
						"_UI_AttributesSettingPage_type"), AdaptationModelPackage.Literals.ATTRIBUTES_SETTING_PAGE__VIEWPOINT_HIERARCHIES));
	}

	/**
	 * This adds a property descriptor for the Quality Focus Hierarchies feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addQualityFocusHierarchiesPropertyDescriptorGen(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AttributesSettingPage_qualityFocusHierarchies_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AttributesSettingPage_qualityFocusHierarchies_feature", "_UI_AttributesSettingPage_type"),
				 AdaptationModelPackage.Literals.ATTRIBUTES_SETTING_PAGE__QUALITY_FOCUS_HIERARCHIES,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Quality Focus Hierarchies feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addQualityFocusHierarchiesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
		(new AttributesSettingPageFactorHierarchyPropertyDescriptor(getRootAdapterFactory(),
			 getResourceLocator(),
			 getString("_UI_AttributesSettingPage_qualityFocusHierarchies_feature"),
			 getString("_UI_PropertyDescriptor_description", "_UI_AttributesSettingPage_qualityFocusHierarchies_feature", "_UI_AttributesSettingPage_type"),
			 AdaptationModelPackage.Literals.ATTRIBUTES_SETTING_PAGE__QUALITY_FOCUS_HIERARCHIES));
	}

	/**
	 * This returns AttributesSettingPage.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/AttributesSettingPage"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getTextGen(Object object) {
		String label = ((AttributesSettingPage)object).getTitle();
		return label == null || label.length() == 0 ?
			getString("_UI_AttributesSettingPage_type") :
			getString("_UI_AttributesSettingPage_type") + " " + label;
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_AttributesSettingPage_type");
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(AttributesSettingPage.class)) {
			case AdaptationModelPackage.ATTRIBUTES_SETTING_PAGE__OBJECT_HIERARCHY_LEVEL:
			case AdaptationModelPackage.ATTRIBUTES_SETTING_PAGE__VIEWPOINT_EXCEPTIONS:
			case AdaptationModelPackage.ATTRIBUTES_SETTING_PAGE__QUALITY_FOCUS_HIERARCHY_LEVEL:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

}
