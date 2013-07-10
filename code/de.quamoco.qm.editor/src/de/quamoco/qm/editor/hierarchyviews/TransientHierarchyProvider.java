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

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.graphics.Image;

import edu.tum.cs.emf.commons.resources.ResourceLocatorUtils;

/**
 * {@link ReferenceHierarchyProvider} that inserts {@link TransientElement}s for
 * the reference.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: F90987F9AB984D11AC0C4365445A590E
 */
public class TransientHierarchyProvider extends ReferenceHierarchyProvider {

	/** The label for the {@link TransientElement}s. */
	private String label;

	/** The key for the images for the {@link TransientElement}s. */
	private Image image;

	/**
	 * Constructor. In addition to
	 * {@link ReferenceHierarchyProvider#ReferenceHierarchyProvider(EReference)}
	 * , a label needs to be provided.
	 */
	public TransientHierarchyProvider(EReference childrenReference) {
		super(childrenReference);
	}

	/**
	 * Constructor. In addition to
	 * {@link ReferenceHierarchyProvider#ReferenceHierarchyProvider(EReference, EReference)}
	 * , a label needs to be provided.
	 */
	public TransientHierarchyProvider(EReference childrenReference,
			EReference parentReference) {
		super(childrenReference, parentReference);
	}

	/** {@inheritDoc} */
	@Override
	public boolean providesParent(Object element) {
		if (element instanceof TransientElement) {
			TransientElement t = (TransientElement) element;
			return isTransientElement(t);
		}
		return super.providesParent(element);
	}

	/** Check whether this is a transient element of this provider. */
	private boolean isTransientElement(TransientElement t) {
		return t.getReference() == getReference()
				&& t.isOpposite() == isOpposite();
	}

	/** {@inheritDoc} */
	@Override
	public Object getParent(Object element) {
		if (element instanceof TransientElement) {
			TransientElement t = (TransientElement) element;
			return t.getElement();
		}
		Object parent = super.getParent(element);
		if (parent != null) {
			return getOrCreateTransientElement((EObject) parent);
		}
		return parent;
	}

	/** {@inheritDoc} */
	@Override
	public boolean providesChildren(Object element) {
		if (element instanceof TransientElement) {
			TransientElement t = (TransientElement) element;
			return isTransientElement(t);
		}
		return super.providesChildren(element);
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof TransientElement) {
			TransientElement t = (TransientElement) element;
			return super.hasChildren(t.getElement());
		}
		return super.hasChildren(element);
	}

	/** {@inheritDoc} */
	@Override
	public List<Object> getChildren(Object element) {
		if (element instanceof TransientElement) {
			TransientElement t = (TransientElement) element;
			return super.getChildren(t.getElement());
		}
		List<Object> children = super.getChildren(element);
		if (!children.isEmpty()) {
			EObject eObject = (EObject) element;
			TransientElement t = getOrCreateTransientElement(eObject);
			return Collections.<Object> singletonList(t);
		}
		return children;
	}

	/**
	 * Get or, if null, create the {@link TransientElement} for a certain model
	 * element.
	 */
	private TransientElement getOrCreateTransientElement(EObject eObject) {
		TransientElement t = getTransientElement(eObject);
		if (t == null) {
			t = new TransientElement(eObject, getReference(), isOpposite());
			eObject.eAdapters().add(t);
		}
		return t;
	}

	/** Get the {@link TransientElement} for a certain model element. */
	private TransientElement getTransientElement(EObject eObject) {
		for (Adapter adapter : eObject.eAdapters()) {
			if (adapter instanceof TransientElement) {
				TransientElement t = (TransientElement) adapter;
				if (t.getElement() == eObject && isTransientElement(t)) {
					return t;
				}
			}
		}
		return null;
	}

	/** Get the reference to be used as key for {@link TransientElement}s. */
	private EReference getReference() {
		if (childrenReference != null) {
			return childrenReference;
		} else if (parentReference != null) {
			return parentReference;
		}
		return null;
	}

	/**
	 * Check whether this provider navigates in the opposite direction of the
	 * reference.
	 */
	private boolean isOpposite() {
		return childrenReference == null;
	}

	/** {@inheritDoc} */
	@Override
	public void notifyChanged(EReference reference, EObject target) {
		if (isOpposite()) {
			notifyTransientElement(target);
		}
	}

	/** {@inheritDoc} */
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getFeature() == getReference() && !isOpposite()) {
			notifyTransientElement((EObject) notification.getNotifier());
		}
	}

	/**
	 * Notify the {@link TransientElement} about changes to its model element.
	 */
	private void notifyTransientElement(EObject target) {
		TransientElement t = getTransientElement(target);
		if (t == null) {
			refresh(target);
		} else {
			refresh(t);
		}
	}

	/** Get the label for the {@link TransientElement}s. */
	private String getLabel() {
		if (label != null) {
			return label;
		}
		if (childrenReference != null) {
			return ResourceLocatorUtils.getString(childrenReference);
		}
		if (parentReference != null) {
			return "Opposite "
					+ ResourceLocatorUtils.getString(parentReference);
		}
		return "";
	}

	/** Set the label for the {@link TransientElement}s. */
	public TransientHierarchyProvider setLabel(String label) {
		this.label = label;
		return this;
	}

	/** Get the image for the {@link TransientElement}s. */
	private Image getImage() {
		return image;
	}

	/** Set the image for the {@link TransientElement}s. */
	public TransientHierarchyProvider setImage(Image image) {
		this.image = image;
		return this;
	}

	/** {@inheritDoc} */
	@Override
	public List<Object> getMenuItems(Object element) {
		if (element instanceof TransientElement) {
			TransientElement t = (TransientElement) element;
			return super.getMenuItems(t.getElement());
		}
		List<Object> items = super.getMenuItems(element);
		MenuManager manager = new MenuManager(getLabel(),
				ExtendedImageRegistry.INSTANCE.getImageDescriptor(getImage()),
				null);
		for (Object item : items) {
			if (item instanceof IAction) {
				manager.add((IAction) item);
			} else if (item instanceof IContributionItem) {
				manager.add((IContributionItem) item);
			}
		}
		return Collections.<Object> singletonList(manager);
	}

	/**
	 * A transient element is an element that needs to be shown in the
	 * hierarchy, but which does not have a corresponding element.
	 */
	public class TransientElement extends AdapterImpl implements
			IItemLabelProvider {

		/** The context element used as key. */
		private final EObject element;

		/** The reference used as key. */
		private final EReference reference;

		/** Whether we navigate in the opposite direction or not. */
		private final boolean opposite;

		/** Constructor. */
		public TransientElement(EObject element, EReference reference,
				boolean opposite) {
			this.element = element;
			this.reference = reference;
			this.opposite = opposite;
		}

		/** Get the context element. */
		public EObject getElement() {
			return element;
		}

		/** Get the reference. */
		public EReference getReference() {
			return reference;
		}

		/** Get navigation direction. */
		public boolean isOpposite() {
			return opposite;
		}

		/** {@inheritDoc} */
		@Override
		public Object getImage(Object object) {
			return TransientHierarchyProvider.this.getImage();
		}

		/** {@inheritDoc} */
		@Override
		public String getText(Object object) {
			return getLabel();
		}
	}
}
