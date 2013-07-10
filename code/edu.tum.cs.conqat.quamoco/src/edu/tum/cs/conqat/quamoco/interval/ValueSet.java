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

package edu.tum.cs.conqat.quamoco.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public final class ValueSet<T extends PrimitiveOperations<T>> implements
		PrimitiveOperations<ValueSet<T>>, Iterable<T>, Collection<T>,
		ShortStringProvider {

	private final Set<T> set;

	private static final long serialVersionUID = 1624630113429501169L;

	public ValueSet() {
		this.set = Collections.unmodifiableSet(new HashSet<T>());
	}

	public ValueSet(Collection<? extends T> c) {
		ArrayList<T> copy = new ArrayList<T>(c);
		if (!copy.isEmpty()
				&& copy.iterator().next() instanceof SetComparable<?>) {
			normalize(copy);
		}
		this.set = Collections.unmodifiableSet(new HashSet<T>(copy));
	}

	public ValueSet(T... values) {
		ArrayList<T> copy = new ArrayList<T>(Arrays.asList(values));
		if (!copy.isEmpty()
				&& copy.iterator().next() instanceof SetComparable<?>) {
			normalize(copy);
		}
		this.set = Collections.unmodifiableSet(new HashSet<T>(copy));
	}

	@SuppressWarnings("unchecked")
	public void normalize(ArrayList<T> input) {
		int i = 0;
		int j = 0;
		while (i < input.size()) {
			while (j < input.size()) {
				if (i != j) {
					SetComparable valueI = (SetComparable) input.get(i);
					SetComparable valueJ = (SetComparable) input.get(j);

					SetComparable<?> valueNew = null;
					if (valueI.subset(valueJ)) {
						valueNew = valueJ;
						// System.out.println("subset1");
					} else if (valueJ.subset(valueI)) {
						valueNew = valueI;
						// System.out.println("subset2");
					} else if (valueI.overlaps(valueJ)) {
						valueNew = valueI.join(valueJ);
						// System.out.println("overlaps");
					}

					if (valueNew != null) {
						if (i > j) {
							input.remove(i);
							input.remove(j);
						} else {
							input.remove(j);
							input.remove(i);
						}
						input.add((T) valueNew);
						i = 0;
						j = 0;
						continue;
					}
				}
				j++;
			}
			i++;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ValueSet<T> min(ValueSet<T> a) {
		return applyOperator(new Operator<T>() {
			@Override
			public T operator(List<T> params) {
				return params.get(0).min(params.get(1));
			}
		}, Util.constructList(this, a));
	}

	/**
	 * Result must be ValueSet<RET>, whereby the Operator returns the type RET
	 * 
	 * @param op
	 * @param params
	 * @param result
	 */
	public ValueSet<T> applyOperator(Operator<T> op, List<ValueSet<T>> params) {
		Set<T> result = new HashSet<T>();
		applyOperatorIntern(op, params, 0, new Stack<T>(), result);
		return new ValueSet<T>(result);
	}

	protected void applyOperatorIntern(Operator<T> op,
			List<ValueSet<T>> params, int paramI, Stack<T> actualParams,
			Set<T> result) {
		if (paramI >= params.size()) {
			result.add(op.operator(actualParams));
		} else {
			for (T value : params.get(paramI)) {
				actualParams.push(value);
				applyOperatorIntern(op, params, paramI + 1, actualParams,
						result);
				actualParams.pop();
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public ValueSet<T> not() {
		return applyOperator(new Operator<T>() {
			@Override
			public T operator(List<T> params) {
				return params.get(0).not();
			}
		}, Util.constructList(this));
	}

	@SuppressWarnings("unchecked")
	@Override
	public ValueSet<T> max(ValueSet<T> a) {
		return applyOperator(new Operator<T>() {
			@Override
			public T operator(List<T> params) {
				return params.get(0).max(params.get(1));
			}
		}, Util.constructList(this, a));
	}

	@SuppressWarnings("unchecked")
	@Override
	public ValueSet<T> plus(ValueSet<T> a) {
		return applyOperator(new Operator<T>() {
			@Override
			public T operator(List<T> params) {
				return params.get(0).plus(params.get(1));
			}
		}, Util.constructList(this, a));
	}

	@SuppressWarnings("unchecked")
	@Override
	public ValueSet<T> minus(ValueSet<T> a) {
		return applyOperator(new Operator<T>() {
			@Override
			public T operator(List<T> params) {
				return params.get(0).minus(params.get(1));
			}
		}, Util.constructList(this, a));
	}

	@SuppressWarnings("unchecked")
	@Override
	public ValueSet<T> divide(ValueSet<T> a) {
		return applyOperator(new Operator<T>() {
			@Override
			public T operator(List<T> params) {
				return params.get(0).divide(params.get(1));
			}
		}, Util.constructList(this, a));
	}

	@SuppressWarnings("unchecked")
	@Override
	public ValueSet<T> multiply(ValueSet<T> a) {
		return applyOperator(new Operator<T>() {
			@Override
			public T operator(List<T> params) {
				return params.get(0).multiply(params.get(1));
			}
		}, Util.constructList(this, a));
	}

	// @Override
	// public ValueSet<T> mean(List<ValueSet<T>> param) {
	// List<ValueSet<T>> all = new ArrayList<ValueSet<T>>();
	// all.add(this);
	// all.addAll(param);
	// return applyOperator(new Operator<T>() {
	// @Override
	// public T operator(List<T> params) {
	// T first = params.get(0);
	// List<T> rest = new ArrayList<T>(params);
	// rest.remove(0);
	// return first.mean(rest);
	// }
	// }, all);
	// }

	// public ValueSet<T> mean(ValueSet<T>... params) {
	// return mean(Util.constructList(params));
	// }

	@Override
	public Iterator<T> iterator() {
		return this.set.iterator();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ValueSet<?>) {
			ValueSet<?> a = this;
			ValueSet<?> b = (ValueSet<?>) obj;
			if (a.hashCode() != b.hashCode()) {
				return false;
			}
			return a.set.equals(b.set);
		} else if (obj instanceof Set<?>) {
			return this.set.equals(((Set<?>) obj));
		}
		return false;
	}

	private boolean hashCodeCalc = false;
	private int hashCodeBuffer = 0;

	@Override
	public int hashCode() {
		if (!hashCodeCalc) {
			hashCodeCalc = true;
			hashCodeBuffer = this.set.hashCode();
		}
		return hashCodeBuffer;
	}

	@Override
	public boolean add(T e) {
		throw new IllegalArgumentException("Unmodifiable");
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		throw new IllegalArgumentException("Unmodifiable");
	}

	@Override
	public void clear() {
		throw new IllegalArgumentException("Unmodifiable");
	}

	@Override
	public boolean contains(Object o) {
		return this.set.contains(o);
	}

	public boolean containsAll(Collection<?> superset) {
		return this.set.containsAll(superset);
	}

	@Override
	public boolean isEmpty() {
		return this.set.isEmpty();
	}

	@Override
	public boolean remove(Object o) {
		throw new IllegalArgumentException("Unmodifiable");
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new IllegalArgumentException("Unmodifiable");
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return this.set.retainAll(c);
	}

	@Override
	public int size() {
		return this.set.size();
	}

	@Override
	public Object[] toArray() {
		return this.set.toArray();
	}

	@Override
	public <A> A[] toArray(A[] a) {
		return this.set.toArray(a);
	}

	/** buffer for toString() */
	private String stringBuffer = null;

	@Override
	public String toString() {
		if (this.stringBuffer == null) {
			this.stringBuffer = this.set.toString();
		}
		return this.stringBuffer;
	}

	/** buffer for the short string */
	private String shortString = null;

	@Override
	public String toShortString() {
		if (shortString == null) {
			StringBuffer result = new StringBuffer();
			result.append("[");
			boolean first = true;
			for (T value : this.set) {
				if (!first) {
					result.append(",");
				}
				first = false;
				if (value instanceof ShortStringProvider) {
					result
							.append(((ShortStringProvider) value)
									.toShortString());
				} else {
					result.append(value);
				}

			}
			result.append("]");
			shortString = result.toString();
		}
		return this.shortString;
	}

}
