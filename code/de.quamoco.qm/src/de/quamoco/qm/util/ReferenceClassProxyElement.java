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

package de.quamoco.qm.util;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * A helper class to shortcut a single-valued reference that is modeled as a
 * reference class.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 72F03866F545743AAD35F005E7D542DB
 */
public class ReferenceClassProxyElement<R extends EObject, T extends EObject> {

	/** The element from which the reference is resolved. */
	private final InternalEObject owner;

	/** The shortcut reference which is expected to be single-valued. */
	private final EReference reference;

	/**
	 * The reference to the reference class which is expected to be
	 * single-valued.
	 */
	private final EReference outgoingReference;

	/** The reference class itself. */
	private final EClass referenceClass;

	/**
	 * The reference from the reference class to the target of the shortcut
	 * reference.
	 */
	private final EReference targetReference;

	/** Constructor. */
	public ReferenceClassProxyElement(InternalEObject owner, EReference reference,
			EReference outgoingReference, EClass referenceClass,
			EReference targetReference) {
		this.owner = owner;

		this.reference = reference;
		this.outgoingReference = outgoingReference;
		this.referenceClass = referenceClass;
		this.targetReference = targetReference;

		owner.eAdapters().add(new ReferenceClassProxyAdapter());
	}

	/** Whether the shortcut reference is set. */
	public boolean isSet() {
		return get() != null;
	}

	/** Get the value of the shortcut reference. */
	@SuppressWarnings("unchecked")
	public T get() {
		R value = (R) owner.eGet(outgoingReference);
		if (value == null) {
			return null;
		}
		return unwrap(value);
	}

	/** Unwrap a value from an instance of the reference class. */
	@SuppressWarnings("unchecked")
	protected T unwrap(R referenceElement) {
		Object element = referenceElement.eGet(targetReference);
		if (element == null) {
			return null;
		}
		return (T) element;
	}

	/** Set the value of the shortcut reference. */
	@SuppressWarnings("unchecked")
	public void set(T newValue) {
		if (newValue != get()) {
			R oldReferenceElement = (R) owner.eGet(outgoingReference);
			if (oldReferenceElement != null) {
				oldReferenceElement.eSet(targetReference, null);
				owner.eSet(outgoingReference, null);
			}
			if (newValue != null) {
				R newReferenceElement = wrap(newValue);
				owner.eSet(outgoingReference, newReferenceElement);
			}
		}
	}

	/** Unset the value of the shortcut reference. */
	public void unset() {
		set(null);
	}

	/** Wrap a value into an instance of the reference class. */
	@SuppressWarnings("unchecked")
	protected R wrap(T element) {
		T referenceElement = (T) EcoreUtil.create(referenceClass);
		referenceElement.eSet(targetReference, element);
		return (R) referenceElement;
	}

	/**
	 * Adapter that selectively registers to the owner and the associated
	 * instances of the reference class.
	 */
	private class ReferenceClassProxyAdapter extends SelectiveContentAdapter {

		/** Constructor. */
		public ReferenceClassProxyAdapter() {
			super(outgoingReference);
		}

		/** {@inheritDoc} */
		@Override
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification);
			if (notification.getFeature() == outgoingReference) {
				Object oldValueUnwrapped = unwrap(notification.getOldValue());
				Object newValueUnwrapped = unwrap(notification.getNewValue());
				if (oldValueUnwrapped != null || newValueUnwrapped != null) {
					Notification directNotification = new ENotificationImpl(
							owner, notification.getEventType(), reference
									.getFeatureID(), oldValueUnwrapped,
							newValueUnwrapped, notification.getPosition(),
							notification.wasSet());
					owner.eNotify(directNotification);
				}
			} else if (notification.getFeature() == targetReference) {
				Notification directNotification = new ENotificationImpl(owner,
						notification.getEventType(), reference.getFeatureID(),
						notification.getOldValue(), notification.getNewValue(),
						notification.getPosition(), notification.wasSet());
				owner.eNotify(directNotification);
			}
		}

		/** Unwrap an instance of the reference class. */
		@SuppressWarnings("unchecked")
		private Object unwrap(Object element) {
			if (element != null) {
				if (element instanceof List) {
					EList<T> result = new BasicEList<T>();
					for (Object value : (List) element) {
						result.add((T) unwrap(value));
					}
					return result;
				}
				return ReferenceClassProxyElement.this.unwrap((R) element);
			}
			return element;
		}

	}
}
