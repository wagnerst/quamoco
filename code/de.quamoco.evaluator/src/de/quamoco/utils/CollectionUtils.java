/*-------------------------------------------------------------------------+
|                                                                          |
| Copyright 2005-2011 The ConQAT Project                                   |
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
+-------------------------------------------------------------------------*/
package de.quamoco.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

/**
 * This class offers utility methods for collections. In can be seen as an
 * extension to {@link Collections}.
 * 
 * @author $Author: hummelb $
 * @version $Revision: 36113 $
 * @ConQAT.Rating GREEN Hash: BD91818BBEC1384FA5759433B70F8949
 */
public class CollectionUtils {
	/** Empty unmodifiable list. */
	@SuppressWarnings("unchecked")
	private static final UnmodifiableList<?> EMPTY_LIST = new UnmodifiableList<Object>(
			Collections.EMPTY_LIST);

	/** Empty unmodifiable map. */
	@SuppressWarnings("unchecked")
	private static final UnmodifiableMap<?, ?> EMPTY_MAP = new UnmodifiableMap<Object, Object>(
			Collections.EMPTY_MAP);

	/** Empty unmodifiable set. */
	@SuppressWarnings("unchecked")
	private static final UnmodifiableSet<?> EMPTY_SET = new UnmodifiableSet<Object>(
			Collections.EMPTY_SET);

	/**
	 * Create an hashed set from an array.
	 * 
	 * @param <T>
	 *            type
	 * @param elements
	 *            elements in the set.
	 * @return the set.
	 * @see Arrays#asList(Object[])
	 */
	public static <T> HashSet<T> asHashSet(T... elements) {
		HashSet<T> result = new HashSet<T>();

		Collections.addAll(result, elements);

		return result;
	}

	/**
	 * Returns a new unmodifiable collection wrapping the given collection. This
	 * method is here to avoid retyping the generic argument for the collection
	 * class.
	 */
	public static <T> UnmodifiableCollection<T> asUnmodifiable(Collection<T> c) {
		return new UnmodifiableCollection<T>(c);
	}

//	/**
//	 * Returns a new unmodifiable hashed list map wrapping the given map. This
//	 * method is here to avoid retyping the generic arguments for the sorted map
//	 * class.
//	 * 
//	 * @deprecated See {@link HashedListMap} and
//	 *             {@link UnmodifiableHashedListMap} for details.
//	 */
//	@Deprecated
//	public static <S, T> UnmodifiableHashedListMap<S, T> asUnmodifiable(
//			HashedListMap<S, T> m) {
//		return new UnmodifiableHashedListMap<S, T>(m);
//	}

	/**
	 * Returns a new unmodifiable list wrapping the given list. This method is
	 * here to avoid retyping the generic argument for the list class.
	 */
	public static <T> UnmodifiableList<T> asUnmodifiable(List<T> l) {
		return new UnmodifiableList<T>(l);
	}

	/**
	 * Returns a new unmodifiable map wrapping the given map. This method is
	 * here to avoid retyping the generic arguments for the map class.
	 */
	public static <S, T> UnmodifiableMap<S, T> asUnmodifiable(Map<S, T> m) {
		return new UnmodifiableMap<S, T>(m);
	}

	/**
	 * Returns a new unmodifiable set wrapping the given set. This method is
	 * here to avoid retyping the generic argument for the set class.
	 */
	public static <T> UnmodifiableSet<T> asUnmodifiable(Set<T> s) {
		return new UnmodifiableSet<T>(s);
	}

//	/**
//	 * Returns a new unmodifiable sorted map wrapping the given sorted map. This
//	 * method is here to avoid retyping the generic arguments for the sorted map
//	 * class.
//	 */
//	public static <S, T> UnmodifiableSortedMap<S, T> asUnmodifiable(
//			SortedMap<S, T> m) {
//		return new UnmodifiableSortedMap<S, T>(m);
//	}
//
//	/**
//	 * Returns a new unmodifiable sorted set wrapping the given sorted set. This
//	 * method is here to avoid retyping the generic argument for the sorted set
//	 * class.
//	 */
//	public static <T> UnmodifiableSortedSet<T> asUnmodifiable(SortedSet<T> s) {
//		return new UnmodifiableSortedSet<T>(s);
//	}

	/**
	 * The way this method is defined allows to assign it to all parameterized
	 * types, i.e. bot of the following statements are accepted by the compiler
	 * without warnings:
	 * 
	 * <pre>
	 * UnmodifiableList&lt;String&gt; emptyList = CollectionUtils.emptyList();
	 * 
	 * UnmodifiableList&lt;Date&gt; emptyList = CollectionUtils.emptyList();
	 * </pre>
	 * 
	 * @param <T>
	 * @return the emtpy list
	 */
	@SuppressWarnings("unchecked")
	public static final <T> UnmodifiableList<T> emptyList() {
		return (UnmodifiableList<T>) EMPTY_LIST;
	}

	/** Returns an empty map. See {@link #emptyList()} for further details. */
	@SuppressWarnings("unchecked")
	public static final <S, T> UnmodifiableMap<S, T> emptyMap() {
		return (UnmodifiableMap<S, T>) EMPTY_MAP;
	}

	/** Returns an empty set. See {@link #emptyList()} for further details. */
	@SuppressWarnings("unchecked")
	public static final <T> UnmodifiableSet<T> emptySet() {
		return (UnmodifiableSet<T>) EMPTY_SET;
	}

	/**
	 * Sorts the specified list into ascending order, according to the
	 * <i>natural ordering</i> of its elements.
	 * <p>
	 * All elements in the list must implement the Comparable interface.
	 * Furthermore, all elements in the list must be mutually comparable (that
	 * is, e1.compareTo(e2) must not throw a <code>ClassCastException</code> for
	 * any elements e1 and e2 in the list).
	 * <p>
	 * This method does not modify the original collection.
	 */
	public static <T extends Comparable<? super T>> List<T> sort(
			Collection<T> collection) {
		ArrayList<T> list = new ArrayList<T>(collection);
		Collections.sort(list);
		return list;
	}

	/**
	 * Returns a list that contains the elements of the specified list in
	 * reversed order.
	 * <p>
	 * This method does not modify the original collection.
	 */
	public static <T> List<T> reverse(Collection<T> list) {
		ArrayList<T> reversed = new ArrayList<T>(list);
		Collections.reverse(reversed);
		return reversed;
	}

	/**
	 * Sorts the specified list according to the order induced by the specified
	 * comparator.
	 * <p>
	 * All elements in the list must implement the Comparable interface.
	 * Furthermore, all elements in the list must be mutually comparable (that
	 * is, e1.compareTo(e2) must not throw a <code>ClassCastException</code> for
	 * any elements e1 and e2 in the list).
	 * <p>
	 * This method does not modify the original collection.
	 */
	public static <T> List<T> sort(Collection<T> collection,
			Comparator<? super T> comparator) {
		ArrayList<T> list = new ArrayList<T>(collection);
		Collections.sort(list, comparator);
		return list;
	}

	/** Returns a sorted unmodifiable list for a collection. */
	public static <T extends Comparable<T>> UnmodifiableList<T> asSortedUnmodifiableList(
			Collection<T> collection) {
		return asUnmodifiable(sort(collection));
	}

	/**
	 * Returns a sorted unmodifiable list for a collection.
	 * 
	 * @param comparator
	 *            Comparator used for sorting
	 */
	public static <T extends Comparable<T>> UnmodifiableList<T> asSortedUnmodifiableList(
			Collection<T> collection, Comparator<? super T> comparator) {
		return asUnmodifiable(sort(collection, comparator));
	}

	/**
	 * Returns one object from an {@link Iterable} or <code>null</code> if the
	 * iterable is empty.
	 */
	public static <T> T getAny(Iterable<T> iterable) {
		Iterator<T> iterator = iterable.iterator();
		if (!iterator.hasNext()) {
			return null;
		}
		return iterator.next();
	}

	/**
	 * Convert collection to array. This is a bit cleaner version of
	 * {@link Collection#toArray(Object[])}.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(Collection<? extends T> collection,
			Class<T> type) {
		T[] result = (T[]) java.lang.reflect.Array.newInstance(type,
				collection.size());

		Iterator<? extends T> it = collection.iterator();
		for (int i = 0; i < collection.size(); i++) {
			result[i] = it.next();
		}

		return result;
	}

	/**
	 * Copy an array. This is a shortcut for
	 * {@link Arrays#copyOf(Object[], int)} that does not require to specify the
	 * length.
	 */
	public static <T> T[] copyArray(T[] original) {
		return Arrays.copyOf(original, original.length);
	}

//	/**
//	 * Compute list of unordered pairs for all elements contained in a
//	 * collection.
//	 */
//	public static <T> List<ImmutablePair<T, T>> computeUnorderedPairs(
//			Collection<T> collection) {
//		List<T> elements = new ArrayList<T>(collection);
//		List<ImmutablePair<T, T>> pairs = new ArrayList<ImmutablePair<T, T>>();
//
//		int size = elements.size();
//		for (int firstIndex = 0; firstIndex < size; firstIndex++) {
//			for (int secondIndex = firstIndex + 1; secondIndex < size; secondIndex++) {
//				pairs.add(new ImmutablePair<T, T>(elements.get(firstIndex),
//						elements.get(secondIndex)));
//			}
//		}
//
//		return pairs;
//	}

	/** Returns the last element in list or <code>null</code>, if list is empty. */
	@SuppressWarnings("unchecked")
	public static <T> T getLast(List<T> list) {
		if (list.isEmpty()) {
			return null;
		}
		if (list instanceof Deque<?>) {
			return ((Deque<T>) list).getLast();
		}
		return list.get(list.size() - 1);
	}

	/**
	 * Returns the sublist of all but the first element in list or
	 * <code>null</code>, if list is empty.
	 */
	public static <T> List<T> getRest(List<T> list) {
		if (list.isEmpty()) {
			return null;
		}
		return list.subList(1, list.size());
	}

//	/** Sorts the pair list by first index. */
//	public static <S extends Comparable<S>, T> void sortByFirst(
//			final PairList<S, T> list) {
//		SortableDataUtils.sort(new ISortableData() {
//
//			@Override
//			public void swap(int i, int j) {
//				list.swapEntries(i, j);
//			}
//
//			@Override
//			public int size() {
//				return list.size();
//			}
//
//			@Override
//			public boolean isLess(int i, int j) {
//				return list.getFirst(i).compareTo(list.getFirst(j)) < 0;
//			}
//		});
//	}

	/**
	 * Returns a list implementation that allows for efficient random access.
	 * 
	 * If the passed collection already supports random access, it gets returned
	 * directly. Otherwise a list that supports random access is returned with
	 * the same content as the passed list.
	 */
	public static <T> List<T> asRandomAccessList(Collection<T> list) {
		// It is not guaranteed that implementations of RandomAccess also
		// implement List. Hence, we check for both.
		if (list instanceof List<?> && list instanceof RandomAccess) {
			return (List<T>) list;
		}

		return new ArrayList<T>(list);
	}

	/**
	 * Return a set containing the union of all provided collections. We use a
	 * {@link HashSet}, i.e. the elements should support hashing.
	 * 
	 * We use two separate arguments to ensure on the interface level that at
	 * least one collection is provided. This is transparent for the caller.
	 */
	public static <T> HashSet<T> unionSet(Collection<T> collection1,
			Collection<T>... furtherCollections) {
		HashSet<T> result = new HashSet<T>(collection1);
		for (Collection<T> collection : furtherCollections) {
			result.addAll(collection);
		}
		return result;
	}

	/**
	 * Return a set containing the intersection of all provided collections. We
	 * use a {@link HashSet}, i.e. the elements should support hashing.
	 * 
	 * We use two separate arguments to ensure on the interface level that at
	 * least one collection is provided. This is transparent for the caller.
	 */
	public static <T> HashSet<T> intersectionSet(Collection<T> collection1,
			Collection<T>... furtherCollections) {
		HashSet<T> result = new HashSet<T>(collection1);
		for (Collection<T> collection : furtherCollections) {
			if (collection instanceof Set) {
				result.retainAll(collection);
			} else {
				// if the collection is not already a set, it will be
				// significantly faster to first build a set, to speed up the
				// containment query in the following call.
				result.retainAll(new HashSet<T>(collection));
			}
		}
		return result;
	}

	/** Checks whether collection is null or empty */
	public static boolean isNullOrEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}
}