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


import de.quamoco.adaptation.model.AdaptationModelPackage;
import de.quamoco.adaptation.model.FeatureDescriptor;
import de.quamoco.adaptation.model.provider.descriptor.FeatureDescriptorFeatureNamePropertyDescriptor;
import de.quamoco.adaptation.model.provider.descriptor.FeatureDescriptorOtherElementTypeNamePropertyDescriptor;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link de.quamoco.adaptation.model.FeatureDescriptor} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FeatureDescriptorItemProvider
	extends ItemProviderAdapter
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
	public FeatureDescriptorItemProvider(AdapterFactory adapterFactory) {
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

			addFeatureNamePropertyDescriptor(object);
			addReferenceTypePropertyDescriptor(object);
			addOtherElementTypeNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Feature Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFeatureNamePropertyDescriptorGen(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FeatureDescriptor_featureName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FeatureDescriptor_featureName_feature", "_UI_FeatureDescriptor_type"),
				 AdaptationModelPackage.Literals.FEATURE_DESCRIPTOR__FEATURE_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}
	
	/**
	 * This adds a property descriptor for the Feature Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addFeatureNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new FeatureDescriptorFeatureNamePropertyDescriptor(
				getRootAdapterFactory(), 
				getResourceLocator(), 
				getString("_UI_FeatureDescriptor_featureName_feature"), 
				getString("_UI_PropertyDescriptor_description", "_UI_FeatureDescriptor_featureName_feature", "_UI_FeatureDescriptor_type"), 
				AdaptationModelPackage.Literals.FEATURE_DESCRIPTOR__FEATURE_NAME));		
	}

	/**
	 * This adds a property descriptor for the Reference Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addReferenceTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FeatureDescriptor_referenceType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FeatureDescriptor_referenceType_feature", "_UI_FeatureDescriptor_type"),
				 AdaptationModelPackage.Literals.FEATURE_DESCRIPTOR__REFERENCE_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Other Element Type Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOtherElementTypeNamePropertyDescriptorGen(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FeatureDescriptor_otherElementTypeName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FeatureDescriptor_otherElementTypeName_feature", "_UI_FeatureDescriptor_type"),
				 AdaptationModelPackage.Literals.FEATURE_DESCRIPTOR__OTHER_ELEMENT_TYPE_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}
	
	/**
	 * @generated NOT
	 */
	protected void addOtherElementTypeNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new FeatureDescriptorOtherElementTypeNamePropertyDescriptor(
				getRootAdapterFactory(), 
				getResourceLocator(), 
				getString("_UI_FeatureDescriptor_otherElementTypeName_feature"), 
				getString("_UI_PropertyDescriptor_description", "_UI_FeatureDescriptor_otherElementTypeName_feature", "_UI_FeatureDescriptor_type"), 
				AdaptationModelPackage.Literals.FEATURE_DESCRIPTOR__OTHER_ELEMENT_TYPE_NAME));
	}

	/**
	 * This returns FeatureDescriptor.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/FeatureDescriptor"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((FeatureDescriptor)object).getFeatureName();
		return label == null || label.length() == 0 ?
			getString("_UI_FeatureDescriptor_type") :
			getString("_UI_FeatureDescriptor_type") + " " + label;
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

		switch (notification.getFeatureID(FeatureDescriptor.class)) {
			case AdaptationModelPackage.FEATURE_DESCRIPTOR__FEATURE:
			case AdaptationModelPackage.FEATURE_DESCRIPTOR__FEATURE_NAME:
			case AdaptationModelPackage.FEATURE_DESCRIPTOR__REFERENCE_TYPE:
			case AdaptationModelPackage.FEATURE_DESCRIPTOR__OTHER_ELEMENT_TYPE:
			case AdaptationModelPackage.FEATURE_DESCRIPTOR__OTHER_ELEMENT_TYPE_NAME:
			case AdaptationModelPackage.FEATURE_DESCRIPTOR__ELEMENT_TYPE:
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

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return AdaptationEditPlugin.INSTANCE;
	}

}
