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
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import de.quamoco.qm.QualityModel;
import de.quamoco.qm.util.QmModelUtils;

/**
 * {@link IChildrenProvider} that determines the root elements of a certain
 * view. The base set for root elements is determined from the
 * {@link QualityModel}s using a certain reference. Whether an element from this
 * base set is a root element, is determined based a number of references for
 * which a root element must not be set.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: A9F69F3CB6A05A05A776BC9A53C0DC07
 */
public class QualityModelRootProvider extends HierarchyProviderBase implements
		IChildrenProvider {

	/** The reference to determine the base set for root elements. */
	private final EReference elementReference;

	/** The references to determine whether an element is a root element. */
	private final EReference[] parentReferences;

	/**
	 * Since there is only one root in a hierarchy, we can directly store the
	 * root element.
	 */
	private ResourceSet resourceSet;

	/** Constructor. */
	public QualityModelRootProvider(EReference elementReference,
			EReference... parentReferences) {
		this.elementReference = elementReference;
		this.parentReferences = parentReferences;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getChildren(Object element) {
		ResourceSet resourceSet = (ResourceSet) element;
		setResourceSet(resourceSet);
		List<EObject> rootElements = new ArrayList<EObject>();
		for (QualityModel model : QmModelUtils.getQualityModels(resourceSet)) {
			for (EObject e : getElements(model)) {
				if (isRoot(e)) {
					rootElements.add(e);
				}
			}
		}
		return (List) rootElements;
	}

	/** Set the {@link ResourceSet}. */
	private void setResourceSet(ResourceSet resourceSet) {
		if (this.resourceSet == resourceSet) {
			return;
		}
		if (this.resourceSet != null) {
			removeAdapter();
		}
		this.resourceSet = resourceSet;
		addAdapter();
	}

	/** Get the elements from a {@link QualityModel}. */
	@SuppressWarnings("unchecked")
	protected List<EObject> getElements(QualityModel model) {
		return (List<EObject>) model.eGet(elementReference);
	}

	/** Check whether a certain element is a root element. */
	protected boolean isRoot(EObject element) {
		for (EReference parentReference : parentReferences) {
			if (parentReference.getEContainingClass().isInstance(element)) {
				if (element.eIsSet(parentReference)) {
					return false;
				}
			}
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasChildren(Object element) {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public boolean providesChildren(Object element) {
		return element instanceof ResourceSet;
	}

	/** Attach the adapter to the elements which need to be listened to. */
	private void addAdapter() {
		resourceSet.eAdapters().add(this);
		for (Resource resource : resourceSet.getResources()) {
			addAdapter(resource);
		}
	}

	/** Detach the adapter from the elements which need to be listened to. */
	private void removeAdapter() {
		resourceSet.eAdapters().remove(this);
		for (Resource resource : resourceSet.getResources()) {
			removeAdapter(resource);
		}
	}

	/** Attach the adapter to a {@link Resource}. */
	private void addAdapter(Resource resource) {
		resource.eAdapters().add(this);
		for (EObject element : resource.getContents()) {
			if (element instanceof QualityModel) {
				addAdapter((QualityModel) element);
			}
		}
	}

	/** Detach the adapter from a {@link Resource}. */
	private void removeAdapter(Resource resource) {
		resource.eAdapters().remove(this);
		for (EObject element : resource.getContents()) {
			if (element instanceof QualityModel) {
				removeAdapter((QualityModel) element);
			}
		}
	}

	/** Attach the adapter to a {@link QualityModel}. */
	private void addAdapter(QualityModel model) {
		model.eAdapters().add(this);
		for (EObject element : getElements(model)) {
			addAdapter(element);
		}
	}

	/** Detach the adapter from a {@link QualityModel}. */
	private void removeAdapter(QualityModel model) {
		model.eAdapters().remove(this);
		for (EObject element : getElements(model)) {
			removeAdapter(element);
		}
	}

	/** Attach the adapter to an element of type E. */
	private void addAdapter(EObject element) {
		element.eAdapters().add(this);
	}

	/** Detach the adapter from an element of type E. */
	private void removeAdapter(EObject element) {
		element.eAdapters().remove(this);
	}

	/** {@inheritDoc} */
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType() == Notification.REMOVING_ADAPTER) {
			return;
		}
		Object notifier = notification.getNotifier();
		if (notifier instanceof ResourceSet) {
			notifyChanged((ResourceSet) notifier, notification);
		} else if (notifier instanceof Resource) {
			notifyChanged((Resource) notifier, notification);
		} else if (notifier instanceof QualityModel) {
			notifyChanged((QualityModel) notifier, notification);
		} else {
			notifyChanged((EObject) notifier, notification);
		}
		super.notifyChanged(notification);
	}

	/** Handle notification on a {@link ResourceSet}. */
	@SuppressWarnings("unchecked")
	private void notifyChanged(
			@SuppressWarnings("unused") ResourceSet notifier,
			Notification notification) {
		if (notification.getFeatureID(ResourceSet.class) == ResourceSet.RESOURCE_SET__RESOURCES) {
			switch (notification.getEventType()) {
			case Notification.ADD:
				addAdapter((Resource) notification.getNewValue());
				break;
			case Notification.ADD_MANY:
				for (Resource resource : (List<Resource>) notification
						.getNewValue()) {
					addAdapter(resource);
				}
				break;
			case Notification.REMOVE:
				removeAdapter((Resource) notification.getOldValue());
				break;
			case Notification.REMOVE_MANY:
				for (Resource resource : (List<Resource>) notification
						.getOldValue()) {
					removeAdapter(resource);
				}
				break;
			}
			refresh(resourceSet);
		}
	}

	/** Handle notification on a {@link Resource}. */
	@SuppressWarnings("unchecked")
	private void notifyChanged(@SuppressWarnings("unused") Resource notifier,
			Notification notification) {
		if (notification.getFeatureID(Resource.class) == Resource.RESOURCE__CONTENTS) {
			switch (notification.getEventType()) {
			case Notification.ADD:
				if (notification.getNewValue() instanceof QualityModel) {
					addAdapter((QualityModel) notification.getNewValue());
				}
				break;
			case Notification.ADD_MANY:
				for (EObject element : (List<EObject>) notification
						.getNewValue()) {
					if (element instanceof QualityModel) {
						addAdapter((QualityModel) element);
					}
				}
				break;
			case Notification.REMOVE:
				if (notification.getOldValue() instanceof QualityModel) {
					removeAdapter((QualityModel) notification.getOldValue());
				}
				break;
			case Notification.REMOVE_MANY:
				for (EObject element : (List<EObject>) notification
						.getOldValue()) {
					if (element instanceof QualityModel) {
						removeAdapter((QualityModel) element);
					}
				}
				break;
			}
			refresh(resourceSet);
		}
	}

	/** Handle notification on a {@link QualityModel}. */
	@SuppressWarnings("unchecked")
	private void notifyChanged(
			@SuppressWarnings("unused") QualityModel notifier,
			Notification notification) {
		if (notification.getFeatureID(QualityModel.class) == elementReference
				.getFeatureID()) {
			switch (notification.getEventType()) {
			case Notification.ADD:
				addAdapter((EObject) notification.getNewValue());
				break;
			case Notification.ADD_MANY:
				for (EObject element : (List<EObject>) notification
						.getNewValue()) {
					addAdapter(element);
				}
				break;
			case Notification.REMOVE:
				removeAdapter((EObject) notification.getOldValue());
				break;
			case Notification.REMOVE_MANY:
				for (EObject element : (List<EObject>) notification
						.getOldValue()) {
					removeAdapter(element);
				}
				break;
			}
			refresh(resourceSet);
		}
	}

	/** Handle notification on an element of type E. */
	private void notifyChanged(@SuppressWarnings("unused") EObject notifier,
			Notification notification) {
		for (EReference parentReference : parentReferences) {
			if (notification.getFeature() == parentReference) {
				refresh(resourceSet);
				return;
			}
		}
	}
}
