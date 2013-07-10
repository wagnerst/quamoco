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

public final class GradeInterval implements PrimitiveOperations<GradeInterval>,
		SetComparable<GradeInterval>, ShortStringProvider {

	private final Grade lowerBound;

	public Grade getLowerBound() {
		return lowerBound;
	}

	public Grade getUpperBound() {
		return upperBound;
	}

	private final Grade upperBound;

	public GradeInterval(Grade value) {
		this.lowerBound = value;
		this.upperBound = value;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof GradeInterval)) {
			return false;
		}
		GradeInterval o = (GradeInterval) other;
		return o.lowerBound.equals(this.lowerBound)
				&& o.upperBound.equals(this.upperBound);
	}

	@Override
	public int hashCode() {
		return this.lowerBound.hashCode() + this.upperBound.hashCode();
	}

	public GradeInterval(Grade lowerBound, Grade upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		if (lowerBound.compareTo(upperBound) > 0) {
			throw new IllegalArgumentException(
					"lowerBound > upperBound is not allowed.");
		}
	}

	@Override
	public String toString() {
		return toShortString();
	}

	@Override
	public String toShortString() {
		String sLower = this.lowerBound.toShortString();
		String sUpper = this.upperBound.toShortString();

		if (sLower.equals(sUpper)) {
			return sLower;
		}

		return "[" + sLower + "; " + sUpper + "]";
	}

	@Override
	public GradeInterval join(GradeInterval other) {
		if (!this.overlaps(other)) {
			throw new IllegalArgumentException(
					"the two intervals are not overlapping");
		}

		return new GradeInterval(other.lowerBound.min(this.lowerBound),
				other.upperBound.max(this.upperBound));
	}

	@Override
	public boolean overlaps(GradeInterval y) {
		GradeInterval x = this;
		// Check if x overlaps with y
		return ((x.upperBound.compareTo(y.lowerBound) >= 0) && (x.lowerBound
				.compareTo(y.lowerBound) <= 0))
				|| ((x.upperBound.compareTo(y.upperBound) >= 0) && (x.lowerBound
						.compareTo(y.upperBound) <= 0))
				|| x.subset(y)
				|| y.subset(x);
	}

	@Override
	public boolean subset(GradeInterval y) {
		GradeInterval x = this;
		// Check if x is a subset of y
		return (x.lowerBound.compareTo(y.lowerBound) >= 0)
				&& (x.upperBound.compareTo(y.upperBound) <= 0);
	}

	@Override
	public GradeInterval max(GradeInterval other) {
		return new GradeInterval(this.lowerBound.max(other.lowerBound),
				this.upperBound.max(other.upperBound));
	}

	@Override
	public GradeInterval min(GradeInterval other) {
		return new GradeInterval(this.lowerBound.min(other.lowerBound),
				this.upperBound.min(other.upperBound));
	}

	@Override
	public GradeInterval plus(GradeInterval other) {
		return new GradeInterval(this.lowerBound.plus(other.lowerBound),
				this.upperBound.plus(other.upperBound));
	}

	@Override
	public GradeInterval minus(GradeInterval other) {
		return new GradeInterval(this.lowerBound.minus(other.upperBound),
				this.upperBound.minus(other.lowerBound));
	}

	@Override
	public GradeInterval divide(GradeInterval other) {
		// [a,b] / [c,d] = [min (a/c, a/d, b/c, b/d), max (a/c, a/d, b/c, b/d)]

		Grade a = this.lowerBound;
		Grade b = this.upperBound;
		Grade c = other.lowerBound;
		Grade d = other.upperBound;

		return new GradeInterval(a.divide(c).min(
				a.divide(d).min(b.divide(c).min(b.divide(d)))), a.divide(c)
				.max(a.divide(d).max(b.divide(c).max(b.divide(d)))));
	}

	@Override
	public GradeInterval multiply(GradeInterval other) {
		// [a,b] / [c,d] = [min (a/c, a/d, b/c, b/d), max (a/c, a/d, b/c, b/d)]

		Grade a = this.lowerBound;
		Grade b = this.upperBound;
		Grade c = other.lowerBound;
		Grade d = other.upperBound;

		return new GradeInterval(a.multiply(c).min(
				a.multiply(d).min(b.multiply(c).min(b.multiply(d)))), a
				.multiply(c).max(
						a.multiply(d).max(b.multiply(c).max(b.multiply(d)))));
	}

	@Override
	public GradeInterval not() {
		return (new GradeInterval(new Grade(1))).minus(this);
	}

}
