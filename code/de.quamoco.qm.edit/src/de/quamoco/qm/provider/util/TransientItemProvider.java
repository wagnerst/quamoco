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

package de.quamoco.qm.provider.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationWrapper;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import de.quamoco.qm.provider.QmEditPlugin;
import edu.tum.cs.emf.commons.EcoreUtils;
import edu.tum.cs.emf.commons.resources.ResourceLocatorUtils;

/**
 * An item provider for a transient element, i.e. an element that does not exist
 * in the model. A transient element can group the values of a certain feature,
 * thereby providing a better overview.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: FC33B3E33E98E819F0501B1353CE1CB4
 */
public class TransientItemProvider extends ItemProviderAdapter implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

	/** The reference whose values should be grouped. */
	protected final EReference reference;

	/**
	 * Constructor.
	 * 
	 * @param adapterFactory
	 *            The adapter factory
	 * @param eObject
	 *            The parent element
	 * @param reference
	 *            The reference whose values should be grouped
	 */
	public TransientItemProvider(AdapterFactory adapterFactory,
			EObject eObject, EReference reference) {
		super(adapterFactory);
		eObject.eAdapters().add(this);
		this.reference = reference;
	}

	/** @returns reference. */
	public EReference getReference() {
		return reference;
	}

	/** {@inheritDoc} */
	@Override
	public Collection<?> getChildren(Object object) {
		return super.getChildren(target);
	}

	/** {@inheritDoc} */
	@Override
	public Object getParent(Object object) {
		return target;
	}

	/** {@inheritDoc} */
	@Override
	public String getText(Object object) {
		return ResourceLocatorUtils.getString(reference);
	}

	/** {@inheritDoc} */
	@Override
	public Object getImage(Object object) {
		EClass type = reference.getEReferenceType();
		return overlayImage(object, getResourceLocator().getImage(
				"full/obj16/" + type.getName() + "Folder"));
	}

	/** {@inheritDoc} */
	@Override
	public ResourceLocator getResourceLocator() {
		return QmEditPlugin.INSTANCE;
	}

	/** {@inheritDoc} */
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getFeature() == reference) {
			fireNotifyChanged(new NotificationWrapper(this, notification));
		}
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(reference);
		}
		return childrenFeatures;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
		EClass eClass = reference.getEReferenceType();
		for (EClass type : EcoreUtils.getCompatibleTypesInSamePackage(eClass)) {
			Object child = eClass.getEPackage().getEFactoryInstance().create(
					type);
			newChildDescriptors.add(createChildParameter(reference, child));
		}
	}

	/** {@inheritDoc} */
	@Override
	public Collection<?> getNewChildDescriptors(Object object,
			EditingDomain editingDomain, Object sibling) {
		return super.getNewChildDescriptors(target, editingDomain, sibling);
	}

	/**
	 * Modify the command so that the parameters are model elements (otherwise,
	 * we would get a {@link ClassCastException} from EMF). Also, set the owner
	 * of the command to be the parent model element.
	 */
	@Override
	public Command createCommand(final Object object,
			final EditingDomain domain, Class<? extends Command> commandClass,
			CommandParameter commandParameter) {
		CommandParameter localCommandParameter = commandParameter;
		// disable copying transient elements
		if (commandClass == CopyCommand.class) {
			return UnexecutableCommand.INSTANCE;
		}

		Collection<?> oldCollection = localCommandParameter.getCollection();
		Collection<Object> newCollection = new ArrayList<Object>();
		if (oldCollection != null) {
			for (Object o : oldCollection) {
				if (o instanceof EObject) {
					newCollection.add(o);
				} else if (o instanceof TransientItemProvider) {
					newCollection.add(((TransientItemProvider) o).target);
				}
			}

			localCommandParameter = new CommandParameter(localCommandParameter
					.getOwner(), localCommandParameter.getFeature(),
					localCommandParameter.getValue(), newCollection,
					localCommandParameter.getIndex());
		}

		localCommandParameter.setOwner(target);
		return domain.createCommand(commandClass, localCommandParameter);
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	protected Command createRemoveCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection) {
		return createWrappedCommand(super.createRemoveCommand(domain, owner,
				feature, collection), owner);
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	protected Command createAddCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection, int index) {
		return createWrappedCommand(super.createAddCommand(domain, owner,
				feature, collection, index), owner);
	}

	/**
	 * Wrap each command to modify the affected objects, in case the affected
	 * objects are the providers.
	 */
	protected Command createWrappedCommand(Command command, final EObject owner) {
		return new CommandWrapper(command) {
			@Override
			@SuppressWarnings("unchecked")
			public Collection getAffectedObjects() {
				Collection affected = super.getAffectedObjects();
				if (affected.contains(owner)) {
					affected = Collections
							.singleton(TransientItemProvider.this);
				}
				return affected;
			}
		};
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	protected Command createDragAndDropCommand(EditingDomain domain,
			Object owner, float location, int operations, int operation,
			Collection collection) {
		if (new AddCommand(domain, (EObject) owner, reference, collection)
				.canExecute()) {
			return super.createDragAndDropCommand(domain, owner, location,
					operations, operation, collection);
		}
		return UnexecutableCommand.INSTANCE;
	}
}
