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
 * $Id: EntityItemProvider.java 4974 2012-02-17 09:34:10Z lochmann $
 */
package de.quamoco.qm.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
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

import de.quamoco.qm.CharacterizingElement;
import de.quamoco.qm.Entity;
import de.quamoco.qm.QmFactory;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.provider.util.LoopAvoidingItemPropertyDescriptor;
import de.quamoco.qm.provider.util.TransientItemProviderUtils;

/**
 * This is the item provider adapter for a {@link de.quamoco.qm.Entity} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * @generated
 */
public class EntityItemProvider extends NamedElementItemProvider implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource,
		IItemColorProvider {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EntityItemProvider(AdapterFactory adapterFactory) {
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

			addIsADirectPropertyDescriptor(object);
			addPartOfDirectPropertyDescriptor(object);
			addQualityModelPropertyDescriptor(object);
			addStakeholderPropertyDescriptor(object);
			addUseCasePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Is ADirect feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void addIsADirectPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new LoopAvoidingItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Entity_isADirect_feature"), getString(
						"_UI_PropertyDescriptor_description",
						"_UI_Entity_isADirect_feature", "_UI_Entity_type"),
				QmPackage.Literals.ENTITY__IS_ADIRECT, true, false, true, null,
				null, null));
	}

	/**
	 * This adds a property descriptor for the Part Of Direct feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void addPartOfDirectPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new LoopAvoidingItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory)
						.getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Entity_partOfDirect_feature"), getString(
						"_UI_PropertyDescriptor_description",
						"_UI_Entity_partOfDirect_feature", "_UI_Entity_type"),
				QmPackage.Literals.ENTITY__PART_OF_DIRECT, true, false, true,
				null, null, null));
	}

	/**
	 * This adds a property descriptor for the Quality Model feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addQualityModelPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Entity_qualityModel_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Entity_qualityModel_feature", "_UI_Entity_type"),
				 QmPackage.Literals.ENTITY__QUALITY_MODEL,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Stakeholder feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addStakeholderPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Entity_stakeholder_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Entity_stakeholder_feature", "_UI_Entity_type"),
				 QmPackage.Literals.ENTITY__STAKEHOLDER,
				 false,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Use Case feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addUseCasePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Entity_useCase_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Entity_useCase_feature", "_UI_Entity_type"),
				 QmPackage.Literals.ENTITY__USE_CASE,
				 false,
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(
			Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(QmPackage.Literals.ENTITY__IS_A);
			childrenFeatures.add(QmPackage.Literals.ENTITY__PART_OF);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Entity.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Object getImage(Object object) {
		Entity entity = (Entity) object;
		String image = "Entity";

		if (entity.isStakeholder()) {
			image += "Stakeholder";
		} else if (entity.isUseCase()) {
			image += "UseCase";
		}

		return QmEditPlugin.INSTANCE.getImage("full/obj16/" + image);
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		Entity entity = (Entity) object;
		String text = entity.getName();
		if (text == null) {
			text = "";
		}
		QualityModel qualityModel = entity.getQualityModel();
		if (qualityModel != null) {
			text += " [" + qualityModel.getName() + "]";
		}
		return text;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to
	 * update any cached children and by creating a viewer notification, which
	 * it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Entity.class)) {
		case QmPackage.ENTITY__STAKEHOLDER:
		case QmPackage.ENTITY__USE_CASE:
			fireNotifyChanged(new ViewerNotification(notification,
					notification.getNotifier(), false, true));
			return;
		case QmPackage.ENTITY__IS_A:
		case QmPackage.ENTITY__PART_OF:
			fireNotifyChanged(new ViewerNotification(notification,
					notification.getNotifier(), true, false));
			return;
		case QmPackage.NAMED_ELEMENT__NAME:
			notifyCharacterizingElements(notification);
		}
		super.notifyChanged(notification);
	}

	private void notifyCharacterizingElements(Notification notification) {
		Entity entity = (Entity) notification.getNotifier();
		for (CharacterizingElement element : entity.getCharacterizedBy()) {
			fireNotifyChanged(new ViewerNotification(notification, element,
					false, true));
		}
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

		newChildDescriptors.add
			(createChildParameter
				(QmPackage.Literals.ENTITY__IS_A,
				 QmFactory.eINSTANCE.createSpecialization()));

		newChildDescriptors.add
			(createChildParameter
				(QmPackage.Literals.ENTITY__PART_OF,
				 QmFactory.eINSTANCE.createDecomposition()));
	}

	/** {@inheritDoc} */
	@Override
	public Object getParent(Object object) {
		EObject element = (EObject) object;
		return TransientItemProviderUtils.getParent(adapterFactory, element,
				QmPackage.eINSTANCE.getEntity_QualityModel());
	}

}
