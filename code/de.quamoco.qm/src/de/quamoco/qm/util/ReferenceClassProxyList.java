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

import java.lang.reflect.Array;
import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.DelegatingEcoreEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

import edu.tum.cs.emf.commons.EcoreUtils;

/**
 * A list implementation to shortcut a single-valued reference that is modeled
 * as a reference class. The implementation is similar to
 * {@link EClassImpl#getESuperTypes()}.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 9E693D44A96446C075E04BF387F51541
 */
public class ReferenceClassProxyList<R extends EObject, T extends EObject>
		extends DelegatingEcoreEList<T> {

	/** Serial id. */
	private static final long serialVersionUID = 1L;

	/** The shortcut reference which is expected to be multi-valued. */
	private final EReference reference;

	/**
	 * The reference to the reference class which is expected to be
	 * multi-valued.
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
	public ReferenceClassProxyList(InternalEObject owner, EReference reference,
			EReference outgoingReference, EClass referenceClass,
			EReference targetReference) {
		super(owner);

		this.reference = reference;

		this.outgoingReference = outgoingReference;
		this.referenceClass = referenceClass;
		this.targetReference = targetReference;

		owner.eAdapters().add(new ReferenceClassEListAdapter());
	}

	/** Get the instances of the reference class. */
	@SuppressWarnings("unchecked")
	protected EList<R> getReferenceElements() {
		return (EList<R>) owner.eGet(outgoingReference);
	}

	/** {@inheritDoc} */
	@Override
	protected List<T> delegateList() {
		return null;
	}

	/** {@inheritDoc} */
	@Override
	protected List<T> delegateBasicList() {
		return new AbstractSequentialList<T>() {
			@Override
			public ListIterator<T> listIterator(int index) {
				return basicListIterator();
			}

			@Override
			public int size() {
				return delegateSize();
			}
		};
	}

	/** {@inheritDoc} */
	@Override
	protected Iterator<T> delegateIterator() {
		return iterator();
	}

	/** {@inheritDoc} */
	@Override
	protected ListIterator<T> delegateListIterator() {
		return listIterator();
	}

	/** Wrap a value into an instance of the reference class. */
	@SuppressWarnings("unchecked")
	protected R wrap(T element) {
		T referenceElement = (T) EcoreUtil.create(referenceClass);
		referenceElement.eSet(targetReference, element);
		return (R) referenceElement;
	}

	/** Unwrap a value from an instance of the reference class. */
	@SuppressWarnings("unchecked")
	protected T unwrap(R referenceElement) {
		Object element = referenceElement.eGet(targetReference);
		if (element == null) {
			return getNullElement();
		}
		return (T) element;
	}

	/** Get an element that denotes null. */
	@SuppressWarnings("unchecked")
	protected T getNullElement() {
		EClass type = EcoreUtils.getCompatibleTypesInSamePackage(
				targetReference.getEReferenceType()).get(0);
		return (T) EcoreUtil.create(type);
	}

	/** {@inheritDoc} */
	@Override
	protected void delegateAdd(int index, T T) {
		getReferenceElements().add(index, wrap(T));
	}

	/** {@inheritDoc} */
	@Override
	protected void delegateClear() {
		getReferenceElements().clear();
	}

	/** {@inheritDoc} */
	@Override
	protected void delegateAdd(T T) {
		getReferenceElements().add(wrap(T));
	}

	/** {@inheritDoc} */
	@Override
	protected boolean delegateContains(Object object) {
		for (T T : this) {
			if (object == T) {
				return true;
			}
		}
		return false;
	}

	/** {@inheritDoc} */
	@Override
	protected boolean delegateContainsAll(Collection<?> collection) {
		for (Object object : collection) {
			if (!delegateContains(object)) {
				return false;
			}
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	protected boolean delegateEquals(Object object) {
		if (object instanceof List<?>) {
			List<?> list = (List<?>) object;
			if (list.size() == delegateSize()) {
				for (Iterator<?> i = list.iterator(), j = iterator(); i
						.hasNext();) {
					if (i.next() != j.next()) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	/** {@inheritDoc} */
	@Override
	protected T delegateGet(int index) {
		R referenceElement = getReferenceElements().get(index);
		return unwrap(referenceElement);
	}

	/** {@inheritDoc} */
	@Override
	protected int delegateHashCode() {
		int hashCode = 1;
		for (R referenceElement : getReferenceElements()) {
			Object object = unwrap(referenceElement);
			hashCode = 31 * hashCode + (object == null ? 0 : object.hashCode());
		}
		return hashCode;
	}

	/** {@inheritDoc} */
	@Override
	protected int delegateIndexOf(Object object) {
		int index = 0;
		for (R referenceElement : getReferenceElements()) {
			if (object == unwrap(referenceElement)) {
				return index;
			}
			++index;
		}
		return -1;
	}

	/** {@inheritDoc} */
	@Override
	protected boolean delegateIsEmpty() {
		return getReferenceElements().isEmpty();
	}

	/** {@inheritDoc} */
	@Override
	protected int delegateLastIndexOf(Object object) {
		EList<R> referenceElements = getReferenceElements();
		for (int i = referenceElements.size() - 1; i >= 0; --i) {
			if (unwrap(referenceElements.get(i)) == object) {
				return i;
			}
		}
		return -1;
	}

	/** {@inheritDoc} */
	@Override
	protected T delegateRemove(int index) {
		R referenceElement = getReferenceElements().get(index);
		T target = unwrap(referenceElement);
		EcoreUtil.delete(referenceElement);
		return target;
	}

	/** {@inheritDoc} */
	@Override
	protected T delegateSet(int index, T element) {
		R referenceElement = getReferenceElements().get(index);
		T result = unwrap(referenceElement);

		// If this is just a proxy being resolved...
		if (resolveProxy(result) == element) {
			// Force the raw type to be resolved so we don't resolve this
			// endlessly.
			referenceElement.eGet(targetReference);
		} else {
			// Update the classifier and hence the raw type as normal.
			referenceElement.eSet(targetReference, element);
		}
		return result;
	}

	/** {@inheritDoc} */
	@Override
	protected int delegateSize() {
		return getReferenceElements().size();
	}

	/** {@inheritDoc} */
	@Override
	protected Object[] delegateToArray() {
		int size = delegateSize();
		Object[] result = new Object[size];

		int index = 0;
		for (R referenceElement : getReferenceElements()) {
			result[index++] = unwrap(referenceElement);
		}
		return result;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("hiding")
	@Override
	protected <T> T[] delegateToArray(T[] array) {
		int size = delegateSize();
		if (array.length < size) {
			@SuppressWarnings("unchecked")
			T[] newArray = (T[]) Array.newInstance(array.getClass()
					.getComponentType(), size);
			array = newArray;
		}

		if (array.length > size) {
			array[size] = null;
		}

		int index = 0;
		for (R referenceElement : getReferenceElements()) {
			@SuppressWarnings("unchecked")
			T rawType = (T) unwrap(referenceElement);
			array[index++] = rawType;
		}

		return array;
	}

	/** {@inheritDoc} */
	@Override
	protected String delegateToString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("[");
		EList<R> referenceElements = getReferenceElements();
		for (int i = 0, size = delegateSize(); i < size;) {
			stringBuffer.append(String
					.valueOf(unwrap(referenceElements.get(i))));
			if (++i < size) {
				stringBuffer.append(", ");
			}
		}
		stringBuffer.append("]");
		return stringBuffer.toString();
	}

	/** {@inheritDoc} */
	@Override
	protected boolean isInstance(Object object) {
		return reference.getEReferenceType().isInstance(object);
	}

	/** {@inheritDoc} */
	@Override
	public int getFeatureID() {
		return reference.getFeatureID();
	}

	/** {@inheritDoc} */
	@Override
	protected boolean useEquals() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	protected boolean canContainNull() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	protected boolean isUnique() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	protected boolean hasInverse() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	protected boolean hasManyInverse() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	protected boolean hasNavigableInverse() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	protected boolean isEObject() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	protected boolean isContainment() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	protected boolean hasProxies() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	protected boolean hasInstanceClass() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isSet() {
		return owner.eIsSet(outgoingReference);
	}

	/** {@inheritDoc} */
	@Override
	protected NotificationImpl createNotification(int eventType,
			Object oldObject, Object newObject, int index, boolean wasSet) {
		// The notification for this list is implemented by an adapter.
		return null;
	}

	/** {@inheritDoc} */
	@Override
	protected void dispatchNotification(Notification notification) {
		// Do nothing
	}

	/** {@inheritDoc} */
	@Override
	protected EObject resolveProxy(EObject eObject) {
		if (eObject == null) {
			return null;
		}
		return super.resolveProxy(eObject);
	}

	/**
	 * Adapter that selectively registers to the owner and the associated
	 * instances of the reference class.
	 */
	private class ReferenceClassEListAdapter extends SelectiveContentAdapter {

		/** Constructor. */
		public ReferenceClassEListAdapter() {
			super(outgoingReference);
		}

		/** {@inheritDoc} */
		@Override
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification);
			if (notification.getFeature() == outgoingReference) {
				Object oldValueUnwrapped = unwrap(notification.getOldValue());
				Object newValueUnwrapped = unwrap(notification.getNewValue());
				Notification directNotification = new ENotificationImpl(owner,
						notification.getEventType(), getFeatureID(),
						oldValueUnwrapped, newValueUnwrapped, notification
								.getPosition(), notification.wasSet());
				owner.eNotify(directNotification);
			} else if (notification.getFeature() == targetReference) {
				if (notification.getOldValue() != null) {
					Notification directNotification = new ENotificationImpl(
							owner, Notification.REMOVE, getFeatureID(),
							notification.getOldValue(), null,
							getReferenceElements().indexOf(
									notification.getNotifier()), false);
					owner.eNotify(directNotification);
				}
				if (notification.getNewValue() != null) {
					Notification directNotification = new ENotificationImpl(
							owner, Notification.ADD, getFeatureID(), null,
							notification.getNewValue(), getReferenceElements()
									.indexOf(notification.getNotifier()), false);
					owner.eNotify(directNotification);
				}
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
				try {
					return ReferenceClassProxyList.this.unwrap((R) element);
				} catch (ClassCastException e) {
					// ignore
				}
			}
			return element;
		}

	}
}
