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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;

import org.conqat.lib.commons.assertion.CCSMPre;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * Iterator that visits the transitive closure of a reference from a start
 * element. Notably, it also visits the start element and prunes cycles to avoid
 * infinite recursion.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 6B24E524AC8346BD3E4A9E76341387E0
 */
public class TransitiveReferenceIterator<V extends EObject> implements
		Iterator<V> {

	/** Elements that have already been visited. */
	private final Set<V> done = new HashSet<V>();

	/** Elements that are still to be visited. */
	private final Queue<V> toVisit = new LinkedList<V>();

	/** The reflexive reference. */
	private final EReference reference;

	/** Constructor. */
	public TransitiveReferenceIterator(V start, EReference reference) {
		this(start, reference, true);
	}

	/** Constructor. */
	public TransitiveReferenceIterator(V start, EReference reference,
			boolean includeStart) {
		CCSMPre.isTrue(reference.getEContainingClass().isInstance(start),
				"The start element must be an instance "
						+ "of the class that defines the reference.");
		CCSMPre.isTrue(reference.getEContainingClass() == reference
				.getEReferenceType(), "The reference must be a reflexive.");
		this.reference = reference;
		if (includeStart) {
			toVisit.add(start);
		} else {
			toVisit.addAll(getNewSuccessors(start));
		}
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasNext() {
		return !toVisit.isEmpty();
	}

	/** {@inheritDoc} */
	@Override
	public V next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		V next = toVisit.remove();
		done.add(next);
		toVisit.addAll(getNewSuccessors(next));
		return next;
	}

	/**
	 * Get only those successors of an element w.r.t. the reference that have
	 * not yet been visited.
	 */
	private List<V> getNewSuccessors(EObject element) {
		List<V> newSucessors = new ArrayList<V>();
		for (V successor : getSuccessors(element)) {
			if (!done.contains(successor)) {
				newSucessors.add(successor);
			}
		}
		return newSucessors;
	}

	/** Get the successors of an element w.r.t. the reference. */
	@SuppressWarnings("unchecked")
	private List<V> getSuccessors(EObject element) {
		if (reference.isMany()) {
			return (List<V>) element.eGet(reference);
		} else if (element.eIsSet(reference)) {
			return Collections.singletonList((V) element.eGet(reference));
		}
		return Collections.emptyList();
	}

	/** {@inheritDoc} */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
