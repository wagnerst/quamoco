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

import java.text.DecimalFormat;

public final class StdDouble implements Comparable<StdDouble>,
		ShortStringProvider {

	private Double value;

	public StdDouble(double val) {
		this.value = val;
	}

	public double doubleValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "" + value;
	}

	@Override
	public int hashCode() {
		return this.value.hashCode();
	}

	@Override
	public boolean equals(Object y) {
		if (y == this) {
			return true;
		}
		if (y == null) {
			return false;
		}
		if (!(y instanceof StdDouble)) {
			return false;
		}
		StdDouble b = (StdDouble) y;
		return this.compareTo(b) == 0;

	}

	@Override
	public int compareTo(StdDouble arg0) {
		return this.value.compareTo(arg0.value);
	}

	private static final DecimalFormat format = new DecimalFormat("0.000");

	@Override
	public String toShortString() {
		return format.format(this.value);
	}

	public StdDouble times(StdDouble b) {
		StdDouble a = this;
		return new StdDouble(a.value * b.value);
	}

	public StdDouble plus(StdDouble b) {
		StdDouble a = this;
		return new StdDouble(a.value + b.value);
	}

	public StdDouble negate() {
		return new StdDouble(-this.value);
	}

	public StdDouble minus(StdDouble b) {
		StdDouble a = this;
		return new StdDouble(a.value - b.value);
	}

	public StdDouble divides(StdDouble b) {
		StdDouble a = this;
		return new StdDouble(a.value / b.value);
	}

	public boolean isNegative() {
		return this.value < 0;
	}

}
