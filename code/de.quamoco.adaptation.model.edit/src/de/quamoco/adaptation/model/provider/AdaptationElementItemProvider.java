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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import de.quamoco.adaptation.model.AdaptationElement;
import de.quamoco.adaptation.model.AdaptationModelFactory;
import de.quamoco.adaptation.model.AdaptationModelPackage;
import de.quamoco.adaptation.model.provider.descriptor.AdaptationElementReferenceNamePropertyDescriptor;

/**
 * This is the item provider adapter for a {@link de.quamoco.adaptation.model.AdaptationElement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AdaptationElementItemProvider
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
	public AdaptationElementItemProvider(AdapterFactory adapterFactory) {
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

			addReferenceNamePropertyDescriptor(object);
			addShowOnPurposesPropertyDescriptor(object);
			addShowHierarchyByDefaultPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Reference Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addReferenceNamePropertyDescriptorGen(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AdaptationElement_referenceName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AdaptationElement_referenceName_feature", "_UI_AdaptationElement_type"),
				 AdaptationModelPackage.Literals.ADAPTATION_ELEMENT__REFERENCE_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}
	
	/**
	 * This adds a property descriptor for the Reference Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addReferenceNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
		.add(new AdaptationElementReferenceNamePropertyDescriptor(
				getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_AdaptationElement_referenceName_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_AdaptationElement_referenceName_feature", "_UI_AdaptationElement_type"),
				AdaptationModelPackage.Literals.ADAPTATION_ELEMENT__REFERENCE_NAME));
	}

	/**
	 * This adds a property descriptor for the Show On Purposes feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addShowOnPurposesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AdaptationElement_showOnPurposes_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AdaptationElement_showOnPurposes_feature", "_UI_AdaptationElement_type"),
				 AdaptationModelPackage.Literals.ADAPTATION_ELEMENT__SHOW_ON_PURPOSES,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Show Hierarchy By Default feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addShowHierarchyByDefaultPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AdaptationElement_showHierarchyByDefault_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AdaptationElement_showHierarchyByDefault_feature", "_UI_AdaptationElement_type"),
				 AdaptationModelPackage.Literals.ADAPTATION_ELEMENT__SHOW_HIERARCHY_BY_DEFAULT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(AdaptationModelPackage.Literals.ADAPTATION_ELEMENT__SELECTED_ELEMENT_ACTIONS);
			childrenFeatures.add(AdaptationModelPackage.Literals.ADAPTATION_ELEMENT__UNSELECTED_ELEMENT_ACTIONS);
			childrenFeatures.add(AdaptationModelPackage.Literals.ADAPTATION_ELEMENT__PRESELECTION_DESCRIPTORS);
			childrenFeatures.add(AdaptationModelPackage.Literals.ADAPTATION_ELEMENT__ELEMENT_HIERARCHIES);
			childrenFeatures.add(AdaptationModelPackage.Literals.ADAPTATION_ELEMENT__FINALIZE_ACTIONS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns AdaptationElement.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/AdaptationElement"));
	}
	
	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		EClass elementClass = ((AdaptationElement)object).getElementClass();
		String label = elementClass == null ? "<not set>" : elementClass.getName();
		return label == null || label.length() == 0 ?
				getString("_UI_AdaptationElement_type") :
				getString("_UI_AdaptationElement_type") + " \"" + label + "\"";
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

		switch (notification.getFeatureID(AdaptationElement.class)) {
			case AdaptationModelPackage.ADAPTATION_ELEMENT__ELEMENT_CLASS:
			case AdaptationModelPackage.ADAPTATION_ELEMENT__REFERENCE_NAME:
			case AdaptationModelPackage.ADAPTATION_ELEMENT__SHOW_HIERARCHY_BY_DEFAULT:
			case AdaptationModelPackage.ADAPTATION_ELEMENT__REFERENCE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case AdaptationModelPackage.ADAPTATION_ELEMENT__SELECTED_ELEMENT_ACTIONS:
			case AdaptationModelPackage.ADAPTATION_ELEMENT__UNSELECTED_ELEMENT_ACTIONS:
			case AdaptationModelPackage.ADAPTATION_ELEMENT__PRESELECTION_DESCRIPTORS:
			case AdaptationModelPackage.ADAPTATION_ELEMENT__ELEMENT_HIERARCHIES:
			case AdaptationModelPackage.ADAPTATION_ELEMENT__FINALIZE_ACTIONS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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

		newChildDescriptors.add
			(createChildParameter
				(AdaptationModelPackage.Literals.ADAPTATION_ELEMENT__SELECTED_ELEMENT_ACTIONS,
				 AdaptationModelFactory.eINSTANCE.createRemoveElementAction()));

		newChildDescriptors.add
			(createChildParameter
				(AdaptationModelPackage.Literals.ADAPTATION_ELEMENT__SELECTED_ELEMENT_ACTIONS,
				 AdaptationModelFactory.eINSTANCE.createAddTodoAction()));

		newChildDescriptors.add
			(createChildParameter
				(AdaptationModelPackage.Literals.ADAPTATION_ELEMENT__UNSELECTED_ELEMENT_ACTIONS,
				 AdaptationModelFactory.eINSTANCE.createRemoveElementAction()));

		newChildDescriptors.add
			(createChildParameter
				(AdaptationModelPackage.Literals.ADAPTATION_ELEMENT__UNSELECTED_ELEMENT_ACTIONS,
				 AdaptationModelFactory.eINSTANCE.createAddTodoAction()));

		newChildDescriptors.add
			(createChildParameter
				(AdaptationModelPackage.Literals.ADAPTATION_ELEMENT__PRESELECTION_DESCRIPTORS,
				 AdaptationModelFactory.eINSTANCE.createPreselectionDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(AdaptationModelPackage.Literals.ADAPTATION_ELEMENT__ELEMENT_HIERARCHIES,
				 AdaptationModelFactory.eINSTANCE.createElementHierarchy()));

		newChildDescriptors.add
			(createChildParameter
				(AdaptationModelPackage.Literals.ADAPTATION_ELEMENT__FINALIZE_ACTIONS,
				 AdaptationModelFactory.eINSTANCE.createFeatureRequiredAction()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == AdaptationModelPackage.Literals.ADAPTATION_ELEMENT__SELECTED_ELEMENT_ACTIONS ||
			childFeature == AdaptationModelPackage.Literals.ADAPTATION_ELEMENT__UNSELECTED_ELEMENT_ACTIONS;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
