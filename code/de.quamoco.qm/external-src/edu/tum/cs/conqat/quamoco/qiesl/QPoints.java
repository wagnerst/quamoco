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

/*--------------------------------------------------------------------------+
$Id: QPoints.java 4974 2012-02-17 09:34:10Z lochmann $
|                                                                          |
| Copyright 2005-2010 by the ConQAT Project                                |
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
+--------------------------------------------------------------------------*/
package edu.tum.cs.conqat.quamoco.qiesl;

import java.util.List;

import org.conqat.lib.commons.assertion.CCSMPre;

import de.quamoco.qm.DoubleInterval;
import de.quamoco.qm.QmFactory;
import edu.tum.cs.conqat.quamoco.interval.Grade;
import edu.tum.cs.conqat.quamoco.interval.GradeInterval;
import edu.tum.cs.conqat.quamoco.interval.ShortStringProvider;
import edu.tum.cs.conqat.quamoco.interval.StdDouble;

/**
 * 
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Hash: 402807D96F2539E56A9AADBB1DCB5587
 */
public class QPoints extends Number implements ShortStringProvider {

	/** The interval value. */
	private final GradeInterval value;
	/** Indicates whether this is a veto. */
	private final boolean isVeto;
	/** The veto constant */
	public static QPoints VETO = new QPoints();

	/**
	 * Unknown rating, i.e. [0..1]
	 */
	public static QPoints UNKNOWN = QPoints.valueOf(0, 1);

	/**
	 * Returns the inner data field
	 */
	public GradeInterval getInnerDataField() {
		return value;
	}

	/** Constructs a new veto element */
	private QPoints() {
		this.isVeto = true;
		value = null;
	}

	/** Constructs a new QPoints object */
	private QPoints(GradeInterval value) {
		this.isVeto = false;
		this.value = value;
	}

	/** Constructs a new {@link QPoints} with the given value. */
	private QPoints(double value) {
		this.isVeto = false;
		this.value = new GradeInterval(new Grade(new StdDouble(value)));
	}

	/** Constructs a new {@link QPoints} with the given value. */
	private QPoints(double lowerBound, double upperBound) {
		this.isVeto = false;
		this.value = new GradeInterval(new Grade(new StdDouble(lowerBound)),
				new Grade(new StdDouble(upperBound)));
	}

	/** Checks if this is a single value */
	public boolean isSingleValue() {
		return (value.getLowerBound().equals(value.getUpperBound()));
	}

	/** Returns the single value */
	public Grade getSingleValue() {
		if (value.getLowerBound().equals(value.getUpperBound())) {
			return value.getLowerBound();
		}
		return null;
	}

	/** Constructs a new {@link QPoints} with the given value. */
	public static QPoints valueOf(double value) {
		return new QPoints(value);
	}

	/** Constructs a new {@link QPoints} with the given value. */
	public static QPoints valueOf(GradeInterval value) {
		return new QPoints(value);
	}

	/** Constructs a new {@link QPoints} with the given value. */
	public static QPoints valueOf(double lowerBound, double upperBound) {
		return new QPoints(lowerBound, upperBound);
	}

	/** Convert from a {@link DoubleInterval}. */
	public static QPoints valueOf(DoubleInterval interval) {
		double lower = interval.getLower();
		double upper = interval.getUpper();
		return QPoints.valueOf(lower, upper);
	}

	/** Tries to convert the given Object into a {@link QPoints}. */
	public static QPoints asQPoints(Object object) {
		if (object instanceof QPoints) {
			return (QPoints) object;
		}

		if (QIESLFunctions.isUnknown(object)) {
			return UNKNOWN;
		}

		if (object instanceof Number) {
			return valueOf(((Number) object).doubleValue());
		}
		
		throw new NumberFormatException(object + " cannot be converted to "
				+ QPoints.class.getSimpleName());
	}

	/** Adds the given {@link QPoints}. */
	public static QPoints add(QPoints argument1, QPoints argument2) {
		CCSMPre.isNotNull(argument1);
		CCSMPre.isNotNull(argument2);

		if (hasVeto(argument1, argument2)) {
			return VETO;
		}
		return new QPoints(argument1.value.plus(argument2.value));
	}

	/** Subtracts the given {@link QPoints}. */
	public static QPoints subtract(QPoints argument1, QPoints argument2) {
		CCSMPre.isNotNull(argument1);
		CCSMPre.isNotNull(argument2);

		if (hasVeto(argument1, argument2)) {
			return VETO;
		}
		return new QPoints(argument1.value.minus(argument2.value));
	}

	/** Multiplies the given {@link QPoints}. */
	public static QPoints multiply(QPoints argument1, QPoints argument2) {
		CCSMPre.isNotNull(argument1);
		CCSMPre.isNotNull(argument2);

		if (hasVeto(argument1, argument2)) {
			return VETO;
		}
		return new QPoints(argument1.value.multiply(argument2.value));
	}

	/** Divides the given {@link QPoints}. */
	public static QPoints divide(QPoints argument1, QPoints argument2) {
		CCSMPre.isNotNull(argument1);
		CCSMPre.isNotNull(argument2);

		if (hasVeto(argument1, argument2)) {
			return VETO;
		}
		return new QPoints(argument1.value.divide(argument2.value));
	}

	/** The maximum of two QPoints */
	public static QPoints max(QPoints argument1, QPoints argument2) {
		CCSMPre.isNotNull(argument1);
		CCSMPre.isNotNull(argument2);

		if (hasVeto(argument1, argument2)) {
			return VETO;
		}
		return new QPoints(argument1.value.max(argument2.value));
	}

	/** The maximum of two QPoints */
	public static QPoints min(QPoints argument1, QPoints argument2) {
		CCSMPre.isNotNull(argument1);
		CCSMPre.isNotNull(argument2);

		if (hasVeto(argument1, argument2)) {
			return VETO;
		}
		return new QPoints(argument1.value.min(argument2.value));
	}

	/** Tries to add the given Objects as {@link QPoints}. */
	public static QPoints add(Object argument1, Object argument2) {
		return add(asQPoints(argument1), asQPoints(argument2));
	}

	/** Tries to subtract the given Objects as {@link QPoints}. */
	public static QPoints subtract(Object argument1, Object argument2) {
		return subtract(asQPoints(argument1), asQPoints(argument2));
	}

	/** Tries to multiply the given Objects as {@link QPoints}. */
	public static QPoints multiply(Object argument1, Object argument2) {
		return multiply(asQPoints(argument1), asQPoints(argument2));
	}

	/** Tries to divide the given Objects as {@link QPoints}. */
	public static QPoints divide(Object argument1, Object argument2) {
		return divide(asQPoints(argument1), asQPoints(argument2));
	}

	/** Tries to divide the given Objects as {@link QPoints}. */
	public static QPoints max(Object argument1, Object argument2) {
		return max(asQPoints(argument1), asQPoints(argument2));
	}

	/** Tries to divide the given Objects as {@link QPoints}. */
	public static QPoints min(Object argument1, Object argument2) {
		return min(asQPoints(argument1), asQPoints(argument2));
	}

	/** Checks if one of the arguments is a veto. */
	public static boolean hasVeto(List<Object> arguments) {
		for (Object qPoints : arguments) {
			if ((qPoints instanceof QPoints) && ((QPoints) qPoints).isVeto) {
				return true;
			}
		}
		return false;
	}

	/** Checks if one of the arguments is a veto. */
	public static boolean hasVeto(QPoints... arguments) {
		for (QPoints qPoints : arguments) {
			if (qPoints.isVeto) {
				return true;
			}
		}
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		if (isVeto) {
			return "veto";
		}
		return "" + value.toString();
	}

	/** Checks whether this QPoints is a veto. */
	public boolean isVeto() {
		return isVeto;
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (isVeto) {
			return Integer.MIN_VALUE;
		}
		long bits = value.hashCode();
		return (int) (bits ^ (bits >>> 32));
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		QPoints other = (QPoints) obj;

		if (isVeto || other.isVeto) {
			return isVeto == other.isVeto;
		}

		return this.value.equals(other.value);
	}

	/** Checks if the number is a QPoint veto. */
	public static boolean isVeto(Object number) {
		return (number instanceof QPoints) && ((QPoints) number).isVeto();
	}

	/** Checks if one of the numbers is a QPoint veto. */
	public static boolean hasVeto(Object... numbers) {
		for (Object number : numbers) {
			if (isVeto(number)) {
				return true;
			}
		}
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public String toShortString() {
		if (isVeto) {
			return "veto";
		}
		return "" + value.toShortString();
	}

	/** {@inheritDoc} */
	@Override
	public double doubleValue() {
		if(this.isSingleValue()) {
			return this.getSingleValue().doubleValue();
		}
		
		throw new UnsupportedOperationException();
	}

	/** {@inheritDoc} */
	@Override
	public float floatValue() {
		throw new UnsupportedOperationException();
	}

	/** {@inheritDoc} */
	@Override
	public int intValue() {
		throw new UnsupportedOperationException();
	}

	/** {@inheritDoc} */
	@Override
	public long longValue() {
		throw new UnsupportedOperationException();
	}

	/** Convert to a {@link DoubleInterval} */
	public DoubleInterval doubleIntervalValue() {
		DoubleInterval interval = QmFactory.eINSTANCE.createDoubleInterval();
		GradeInterval dataField = getInnerDataField();
		interval.setLower(dataField.getLowerBound().doubleValue());
		interval.setUpper(dataField.getUpperBound().doubleValue());
		return interval;
	}
}
