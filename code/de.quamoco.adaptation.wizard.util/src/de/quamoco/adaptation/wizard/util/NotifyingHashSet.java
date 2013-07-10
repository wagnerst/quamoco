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

package de.quamoco.adaptation.wizard.util;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.CompoundCommand;

import de.quamoco.qm.AnnotatedElement;

/**
 * This {@link HashSet} notifies the {@link WizardState} every
 * time an element is added or removed so that the necessary
 * actions can be performed.<br><br>
 * 
 * <b>NEVER use the iterator to remove elements from this set!</b><br>
 * If you want to, set USE_NOTIFYING_ITERATOR to true!
 * @author Franz Becker
 * @levd.rating GREEN Hash: F2549CB2C323E7637626544F7D494B6E
 */
public class NotifyingHashSet extends HashSet<AnnotatedElement> {

	public static final int INITIAL_CAPACITY = 200; 
	private static final long serialVersionUID = 1L;
	private static final boolean USE_NOTIFYING_ITERATOR = false;
	
	/*
	 * Variables below are stored efficiently notifying the WizardState.
	 */
	private WizardState wizardState;
	private Map<AnnotatedElement, CompoundCommand> actionMap;
	
	/**
	 * Calls the super constructor and initializes instance variables
	 * @param wizardState
	 * @param actionMap
	 * @param elementActions
	 */
	public NotifyingHashSet(WizardState wizardState, Map<AnnotatedElement, CompoundCommand> actionMap) {
		super(INITIAL_CAPACITY);
		this.wizardState = wizardState;
		this.actionMap = actionMap;
	}

	/** Forwards the call to the super class, if true is returned
	 * {@link WizardState#executeActionsFor(AnnotatedElement, Map, List)} is called. */
	@Override
	public boolean add(AnnotatedElement e) {
		if (super.add(e)) {
			wizardState.executeActionsFor(Collections.singleton(e), actionMap);
			return true;
		}
		return false;
	}
	
	/** Override {@link AbstractCollection#addAll(Collection)} to allow a more efficient notification. */
	@Override
	public boolean addAll(Collection<? extends AnnotatedElement> c) {
		Set<AnnotatedElement> addedElements = new HashSet<AnnotatedElement>(c.size());
		for (AnnotatedElement e : c)
			if (super.add(e)) {				
				addedElements.add(e);
			}	
		if (!addedElements.isEmpty()) {
			wizardState.executeActionsFor(addedElements, actionMap);
			return true;
		}
		return false;
	}

	/** Forwards the call to the super class, if true is returned
	 * {@link WizardState#undoActionsFor(AnnotatedElement, Map, List)} is called. */
	@Override
	public boolean remove(Object o) {
		if (super.remove(o)) {
			wizardState.undoActionsFor(Collections.singleton((AnnotatedElement) o), actionMap);
			return true;
		}
		return false;
	}	
	
	/** Override {@link AbstractSet#removeAll(Collection)} to allow a more efficient notification. */
	@Override
	public boolean removeAll(Collection<?> c) {
		Set<AnnotatedElement> removedElements = new HashSet<AnnotatedElement>(c.size());

		if (size() > c.size()) {
			for (Iterator<?> i = c.iterator(); i.hasNext(); ) {
				Object next = i.next();
				if (super.remove(next)) {
					removedElements.add((AnnotatedElement) next);
				}
			}				
		} else {
			for (Iterator<?> i = iterator(); i.hasNext(); ) {
				Object next = i.next();
				if (c.contains(next)) {
					i.remove();
					if (!USE_NOTIFYING_ITERATOR) {
						removedElements.add((AnnotatedElement) next);
					}					
				}
			}
		}
		
		if (!removedElements.isEmpty()) {
			wizardState.undoActionsFor(removedElements, actionMap);
			return true;
		}
		return false;
	}

	/** Returns a special iterator that wraps the original one for tracking of remove() */
	@Override
	public Iterator<AnnotatedElement> iterator() {
		if (USE_NOTIFYING_ITERATOR) {
			Iterator<AnnotatedElement> iterator = super.iterator();			
			return new NotifyingIterator(iterator);
		} else {
			return super.iterator();
		}		
	}

	/** Not supported! */
	@Override
	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("Retain all is not supported by this set!");
	}
	
	@Override
	public String toString() {
		Iterator<AnnotatedElement> it = iterator();
		if (! it.hasNext())
			return "[]";

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (;;) {
			AnnotatedElement e = it.next();
			sb.append(e.getQualifiedName());
			if (! it.hasNext())
				return sb.append(']').toString();
			sb.append(',').append(' ');
		}
	}

	/**
	 * This iterator wrapped another iterator and performs action
	 * upon removal of the current element. This is required since
	 * it may happen that the iterator is used to remove an element
	 * from the set (e.g. removeAll uses this) - and not the remove
	 * method.
	 * @author Franz Becker
	 */
	private class NotifyingIterator implements Iterator<AnnotatedElement> {

		/** The iterator that is wrapped */
		private Iterator<AnnotatedElement> wrappedIterator;
		
		/** The current element */
		private AnnotatedElement current;
		
		/** Initializes the instance variable
		 * @param wrappedIterator the iterator this one shall wrap */
		public NotifyingIterator(Iterator<AnnotatedElement> wrappedIterator) {
			this.wrappedIterator = wrappedIterator;
		}
		
		/** Forwards the call to the wrapped iterator */
		@Override
		public boolean hasNext() {
			return wrappedIterator.hasNext();
		}

		/** Asks the wrapped iterator for the next element, stores it and then returns it. */
		@Override
		public AnnotatedElement next() {
			current = wrappedIterator.next();
			return current;
		}

		/** Forwards the call to the wrapped iterator and then calls
		 * {@link WizardState#undoActionsFor(AnnotatedElement, Map, List)}. */
		@Override
		public void remove() {			
			wrappedIterator.remove();
			wizardState.undoActionsFor(Collections.singleton(current), actionMap);
		}
		
	}
	
}
