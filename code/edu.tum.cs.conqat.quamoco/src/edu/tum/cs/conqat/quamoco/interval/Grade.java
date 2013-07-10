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



public final class Grade implements PrimitiveOperations<Grade>,
		Comparable<Grade>, ShortStringProvider {
	private final StdDouble value;

	public static final Grade FALSE = new Grade(0);
	public static final Grade TRUE = new Grade(1);

	public Grade(StdDouble value) {
		this.value = value;
	}

	public Grade(int value) {
		this.value = new StdDouble(value);
	}
	
	public double doubleValue() {
		return this.value.doubleValue();
	}

	@Override
	public int hashCode() {
		return this.value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Grade)) {
			return false;
		}
		return ((Grade) obj).value.equals(this.value);
	}

	public Grade(String s) {
		int i = s.indexOf("/");
		if (i != -1) {
			double a = Double.valueOf(s.substring(0, i));
			double b = Double.valueOf(s.substring(i + 1));
			this.value = new StdDouble(a / b);
		} else {
			this.value = new StdDouble(Double.valueOf(s));
		}
	}

	public boolean booleanValue() {
		if (!this.isBoolean()) {
			throw new IllegalArgumentException(this + " is not boolean.");
		}
		return this.value.equals(new StdDouble(1));
	}

	public String toString() {
		return "" + value;
	}

	@Override
	public String toShortString() {
		return "" + value.toShortString();
	}

	@Override
	public Grade min(Grade other) {
		if (this.value.compareTo(other.value) < 0) {
			return this;
		}
		return other;
	}

	@Override
	public Grade max(Grade other) {
		if (this.value.compareTo(other.value) > 0) {
			return this;
		}
		return other;
	}

	@Override
	public Grade plus(Grade other) {
		return new Grade(this.value.plus(other.value));
	}

	@Override
	public Grade minus(Grade other) {
		return new Grade(this.value.minus(other.value));
	}

	@Override
	public Grade not() {
		return new Grade(new StdDouble(1).minus(this.value));
	}

	// @Override
	// public Grade mean(List<Grade> param) {
	// BigRational resultValue = this.value;
	// int count = 1;
	// for (Grade i : param) {
	// resultValue = resultValue.plus(i.value);
	// count++;
	// }
	//
	// return new Grade(resultValue.divides(new BigRational(count)));
	// }

	// public Grade mean(Grade... param) {
	// return this.mean(Arrays.asList(param));
	// }

	public boolean isBoolean() {
		return this.value.equals(new StdDouble(0))
				|| this.value.equals(new StdDouble(1));
	}

	@Override
	public int compareTo(Grade o) {
		return this.value.compareTo(o.value);
	}

	@Override
	public Grade divide(Grade a) {
		return new Grade(this.value.divides(a.value));
	}

	@Override
	public Grade multiply(Grade a) {
		return new Grade(this.value.times(a.value));
	}

}
