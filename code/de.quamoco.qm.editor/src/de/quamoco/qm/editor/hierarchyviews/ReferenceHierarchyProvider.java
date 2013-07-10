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

package de.quamoco.qm.editor.hierarchyviews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CommandActionDelegate;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import edu.tum.cs.emf.commons.EcoreUtils;
import edu.tum.cs.emf.commons.cache.ICrossReferenceListener;
import edu.tum.cs.emf.commons.cache.ResourceSetCrossReferenceCache;

/**
 * {@link IHierarchyProvider} that provides parent and children based on
 * references. References can be provided for the parent and/or children
 * direction.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 10230909F49B3B04C2AC9BE225806B0B
 */
public class ReferenceHierarchyProvider extends HierarchyProviderBase implements
		IParentProvider, IChildrenProvider, ICrossReferenceListener,
		IMenuProvider {

	/**
	 * The reference to determine the children for an element. If null, the
	 * {@link ResourceSetCrossReferenceCache} is used to determine the inverse
	 * of the {@link #parentReference}.
	 */
	protected final EReference childrenReference;

	/**
	 * The reference to determine the parent for an element. If null, the
	 * {@link ResourceSetCrossReferenceCache} is used to determine the inverse
	 * of the {@link #childrenReference}.
	 */
	protected final EReference parentReference;

	/** Constructor. One of the two references can be null. */
	public ReferenceHierarchyProvider(EReference childrenReference,
			EReference parentReference) {
		this.childrenReference = childrenReference;
		this.parentReference = parentReference;
	}

	/**
	 * Constructor. The parent reference is set to the opposite of the children
	 * reference.
	 */
	public ReferenceHierarchyProvider(EReference childrenReference) {
		this(childrenReference, childrenReference.getEOpposite());
	}

	/** {@inheritDoc} */
	@Override
	public boolean providesParent(Object element) {
		EClass childrenType = getChildrenType();
		return childrenType.isInstance(element);
	}

	/** Get the type with which children must be compatible. */
	private EClass getChildrenType() {
		if (childrenReference != null) {
			return childrenReference.getEReferenceType();
		} else if (parentReference != null) {
			return parentReference.getEContainingClass();
		}
		return null;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public Object getParent(Object element) {
		EObject eObject = (EObject) element;
		if (parentReference != null) {
			if (parentReference.isMany()) {
				List values = (List) eObject.eGet(parentReference);
				return values.isEmpty() ? null : values.get(0);
			}
			return eObject.eGet(parentReference);
		}
		if (childrenReference.isContainment()) {
			return eObject.eContainer();
		}
		List<EObject> inverse = ResourceSetCrossReferenceCache.getInverse(
				childrenReference, eObject);
		return inverse.isEmpty() ? null : inverse.iterator().next();
	}

	/** {@inheritDoc} */
	@Override
	public boolean providesChildren(Object element) {
		EClass parentType = getParentType();
		return parentType.isInstance(element);
	}

	/** Get the type with which a parent must be compatible. */
	private EClass getParentType() {
		if (parentReference != null) {
			return parentReference.getEReferenceType();
		} else if (childrenReference != null) {
			return childrenReference.getEContainingClass();
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasChildren(Object element) {
		final EObject eObject = (EObject) element;
		if (childrenReference != null) {
			attachAdapter(eObject);
			return eObject.eIsSet(childrenReference);
		} else if (parentReference != null) {
			attachCrossReferenceListener(eObject);
			List<EObject> inverse = ResourceSetCrossReferenceCache.getInverse(
					parentReference, eObject);
			return !inverse.isEmpty();
		}
		return false;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getChildren(Object element) {
		final EObject eObject = (EObject) element;
		if (childrenReference != null) {
			attachAdapter(eObject);
			if (childrenReference.isMany()) {
				return (List) eObject.eGet(childrenReference);
			}
			Object value = eObject.eGet(childrenReference);
			return value == null ? Collections.EMPTY_LIST : Collections
					.singletonList(value);
		} else if (parentReference != null) {
			attachCrossReferenceListener(eObject);
			List<EObject> inverse = ResourceSetCrossReferenceCache.getInverse(
					parentReference, eObject);
			return new ArrayList<Object>(inverse);
		}
		return Collections.EMPTY_LIST;
	}

	/** Attach this provider as adapter to a model element. */
	private void attachAdapter(EObject eObject) {
		if (!eObject.eAdapters().contains(this)) {
			eObject.eAdapters().add(this);
		}
	}

	/** {@inheritDoc} */
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getFeature() == childrenReference) {
			refresh(notification.getNotifier());
		}
	}

	/**
	 * Attach this provider as {@link ICrossReferenceListener} to a model
	 * element.
	 */
	private void attachCrossReferenceListener(EObject eObject) {
		ResourceSetCrossReferenceCache cache = ResourceSetCrossReferenceCache
				.getCache(eObject);
		if (cache != null) {
			cache.addListener(parentReference, eObject, this);
		}
	}

	/** {@inheritDoc} */
	@Override
	public void notifyChanged(EReference reference, EObject target) {
		refresh(target);
	}

	/** {@inheritDoc} */
	@Override
	public boolean providesMenuItems(Object element) {
		return providesChildren(element);
	}

	/** {@inheritDoc} */
	@Override
	public List<Object> getMenuItems(Object element) {
		EObject parent = (EObject) element;
		final EditingDomain domain = AdapterFactoryEditingDomain
				.getEditingDomainFor(parent);

		List<Object> items = new ArrayList<Object>();

		List<EClass> types = EcoreUtils
				.getCompatibleTypesInSamePackage(getChildrenType());
		for (EClass type : types) {
			EObject child = EcoreUtil.create(type);
			if (childrenReference != null && childrenReference.isContainment()) {
				CommandParameter commandParameter = new CommandParameter(
						parent, childrenReference, child);
				Command command = CreateChildCommand.create(domain, parent,
						commandParameter, Collections.singleton(parent));
				items.add(createAction(domain, command));
			} else {
				CrossReferenceCreateCommand command = new CrossReferenceCreateCommand(
						domain, parent, childrenReference, parentReference,
						child);
				items.add(createAction(domain, command));
			}
		}

		return items;
	}

	/** Create an action based on a command. */
	private Action createAction(final EditingDomain domain,
			final Command command) {
		String text = command.getLabel();
		ImageDescriptor image = null;
		if (command instanceof CommandActionDelegate) {
			CommandActionDelegate delegate = (CommandActionDelegate) command;
			text = delegate.getText();
			image = ExtendedImageRegistry.getInstance().getImageDescriptor(
					delegate.getImage());
		}
		Action action = new Action(text, image) {
			@Override
			public void run() {
				domain.getCommandStack().execute(command);
			}
		};
		return action;
	}
}
