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

import java.math.BigInteger;
import java.text.DecimalFormat;

public final class BigRational implements Comparable<BigRational>, ShortStringProvider {

	public final static BigRational ZERO = new BigRational(0);

	private final BigInteger num;
	private final BigInteger den;

	public BigRational(int numerator, int denominator) {
		this(new BigInteger("" + numerator), new BigInteger("" + denominator));
	}

	public BigRational(int numerator) {
		this(numerator, 1);
	}

	public BigRational(String s) {
		String[] tokens = s.split("/");

		BigInteger pnum;
		BigInteger pden;

		if (tokens.length == 2) {
			pnum = new BigInteger(tokens[0]);
			pden = new BigInteger(tokens[1]);
		} else if (tokens.length == 1) {
			pnum = new BigInteger(tokens[0]);
			pden = BigInteger.ONE;
		} else {
			throw new NumberFormatException("Parse error in BigRational");
		}

		BigInteger g = pnum.gcd(pden);
		pnum = pnum.divide(g);
		pden = pden.divide(g);

		if (pden.compareTo(BigInteger.ZERO) < 0) {
			pden = pden.negate();
			pnum = pnum.negate();
		}

		this.num = pnum;
		this.den = pden;
	}

	public BigRational(BigInteger numerator, BigInteger denominator) {

		if (denominator.equals(BigInteger.ZERO)) {
			throw new RuntimeException("Denominator is zero");
		}

		BigInteger g = numerator.gcd(denominator);
		BigInteger pnum = numerator.divide(g);
		BigInteger pden = denominator.divide(g);

		if (pden.compareTo(BigInteger.ZERO) < 0) {
			pden = pden.negate();
			pnum = pnum.negate();
		}

		this.den = pden;
		this.num = pnum;
	}

	public String toString() {
		if (den.equals(BigInteger.ONE))
			return num + "";
		else
			return num + "/" + den;
	}
	
	private static final DecimalFormat format = new DecimalFormat("0.000");
	
	@Override
	public String toShortString() {
		double val =(num.doubleValue() / den.doubleValue()); 
		return format.format(val);
	}

	public int compareTo(BigRational b) {
		BigRational a = this;
		return a.num.multiply(b.den).compareTo(a.den.multiply(b.num));
	}

	public boolean isZero() {
		return compareTo(ZERO) == 0;
	}

	public boolean isPositive() {
		return compareTo(ZERO) > 0;
	}

	public boolean isNegative() {
		return compareTo(ZERO) < 0;
	}

	public boolean equals(Object y) {
		if (y == this) {
			return true;
		}
		if (y == null) {
			return false;
		}
		if (!(y instanceof BigRational)) {
			return false;
		}
		BigRational b = (BigRational) y;
		return compareTo(b) == 0;
	}

	public int hashCode() {
		return this.toString().hashCode();
	}

	public BigRational times(BigRational b) {
		BigRational a = this;
		return new BigRational(a.num.multiply(b.num), a.den.multiply(b.den));
	}

	public BigRational plus(BigRational b) {
		BigRational a = this;
		BigInteger numerator = a.num.multiply(b.den).add(b.num.multiply(a.den));
		BigInteger denominator = a.den.multiply(b.den);
		return new BigRational(numerator, denominator);
	}

	public BigRational negate() {
		return new BigRational(num.negate(), den);
	}

	public BigRational minus(BigRational b) {
		BigRational a = this;
		return a.plus(b.negate());
	}

	public BigRational reciprocal() {
		return new BigRational(den, num);
	}

	public BigRational divides(BigRational b) {
		BigRational a = this;
		return a.times(b.reciprocal());
	}

}
