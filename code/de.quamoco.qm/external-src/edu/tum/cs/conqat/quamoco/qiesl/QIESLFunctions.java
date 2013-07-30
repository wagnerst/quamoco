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

package edu.tum.cs.conqat.quamoco.qiesl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.conqat.engine.commons.findings.Finding;
import org.conqat.engine.commons.findings.location.ElementLocation;
import org.conqat.engine.commons.findings.location.TextRegionLocation;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.resource.regions.RegionSetDictionary;

import de.quamoco.utils.*;


import edu.tum.cs.conqat.quamoco.FindingCollection;
import edu.tum.cs.conqat.quamoco.IFileRangeResolver;
import edu.tum.cs.conqat.quamoco.IFunctionRangeResolver;
import edu.tum.cs.conqat.quamoco.QuamocoUtils;
import edu.tum.cs.conqat.quamoco.interval.Grade;
import edu.tum.cs.conqat.quamoco.interval.GradeInterval;
import edu.tum.cs.conqat.quamoco.interval.StdDouble;

/**
 * Functions used by QIESL expressions.
 * 
 * @author $Author: lochmann $
 * @version $Rev: 5044 $
 * @levd.rating RED Hash: 0A8248E97F8329E8DC784FDF0E0DE186
 */
public class QIESLFunctions {

	/** Function range resolver */
	private final IFunctionRangeResolver functionRangeResolver;

	/** File range resolver */
	private final IFileRangeResolver classRangeResolver;

	/** File range resolver */
	private final IFileRangeResolver fileRangeResolver;

	/** Logger used for logging */
	private final de.quamoco.utils.Logger logger;

	/** Constructor */
	public QIESLFunctions(IFunctionRangeResolver functionRangeResolver,
			IFileRangeResolver fileRangeResolver,
			IFileRangeResolver classRangeResolver) {
		Assert
				.assertNotNull("functionRangeResolver must not be null. Use NullFunctionRangeResolver instead", functionRangeResolver);
		Assert
				.assertNotNull("fileRangeResolver must not be null. Use NullFileRangeResolver instead", fileRangeResolver);
		this.functionRangeResolver = functionRangeResolver;
		this.fileRangeResolver = fileRangeResolver;
		this.classRangeResolver = classRangeResolver;
		
	}

	public QPoints[] asQPointArray(Number... values) {
		List<QPoints> result = new ArrayList<QPoints>();
		for (Number value : values) {
			if (isUnknown(value)) {
				result.add(QPoints.UNKNOWN);
			} else {
				result.add(QPoints.asQPoints(value));
			}
		}
		return CollectionUtils.toArray(result, QPoints.class);
	}

	/** Intersection of RegionSetDictionaries */
	public RegionSetDictionary intersect(
			@SuppressWarnings("unused") RegionSetDictionary... ranges) {
		throw new IllegalArgumentException(
				"intersect on RegionSetDictionary not implemented.");
	}

	/** Intersection of FindingsCollections */
	public FindingCollection intersect(FindingCollection... sets) {

		if (sets.length == 0 || containsUnknowns(sets)) {
			return QIESLFunctions.UNKNOWN_FINDING_COLLECTION; // unknown, if
			// empty input
		}

		FindingCollection result = new FindingCollection();
		result.addAll(sets[0]);
		for (int i = 1; i < sets.length; i++) {
			result.retainAll(sets[i]);
		}
		return result;
	}

	/**
	 * 
	 * 
	 * @param collections
	 * @return
	 */
	public FindingCollection union(FindingCollection... collections) {

		if (collections.length == 0 || containsUnknowns(collections)) {
			return QIESLFunctions.UNKNOWN_FINDING_COLLECTION; // unknown, if
			// empty input
		}

		FindingCollection result = new FindingCollection();
		for (FindingCollection collection : collections) {
			result.addAll(collection);
		}
		return result;
	}

	/** Union of RegionSetDictionaries */
	public RegionSetDictionary union(RegionSetDictionary... dicts) {

		if (dicts.length == 0 || containsUnknowns(dicts)) {
			return QIESLFunctions.UNKNOWN_REGION_SET_DICT; // unknown, if empty
			// input
		}

		RegionSetDictionary tmp = new RegionSetDictionary();

		for (RegionSetDictionary rsd : dicts) {
			for (RegionSet set : rsd) {
				tmp.add(set);
			}
		}

		RegionSetDictionary result = new RegionSetDictionary();

		// get rid of duplicates
		for (RegionSet set : tmp) {
			result.add(clean(set));
		}

		return result;
	}

	/**
	 * Cleans a region set, i.e. removes duplicates. This implemented by
	 * reverting a region set dictionary twice.
	 * 
	 * TODO (EJ) Move into RegionSet
	 */
	public RegionSet clean(RegionSet set) {
		if (set.isEmpty()) {
			return set;
		}
		int lastPosition = set.getLastPosition();
		RegionSet inverted = set.createInverted(set.getName(), lastPosition);

		// was whole file
		if (inverted.isEmpty()) {
			return set;
		}
		return inverted.createInverted(inverted.getName(), lastPosition);
	}

	/**
	 * Returns true if one of the values is unknown
	 * 
	 * @param values
	 */
	private static boolean containsUnknowns(Object[] values) {
		for (Object n : values) {
			if (QIESLFunctions.isUnknown(n)) {
				return true;
			}
		}

		return false;
	}

	public Number max(Number... values) {
		if (containsUnknowns(values)) {
			return QIESLFunctions.UNKNOWN_DOUBLE;
		}
		if (containsQPoint(values)) {
			return max(asQPointArray(values));
		}
		return MathUtils.max(Arrays.asList(values));
	}

	/** Maximum */
	public QPoints max(QPoints... values) {
		if (values.length == 0) {
			return QPoints.UNKNOWN;
		}

		if (QPoints.hasVeto(values)) {
			return QPoints.VETO;
		}

		QPoints max = null;
		for (QPoints element : values) {
			if (max == null) {
				max = element;
			} else {
				max = QPoints.max(max, element);
			}
		}

		return max;
	}

	public Number min(Number... values) {
		if (containsUnknowns(values)) {
			return QIESLFunctions.UNKNOWN_DOUBLE;
		}
		if (containsQPoint(values)) {
			return min(asQPointArray(values));
		}
		return MathUtils.min(Arrays.asList(values));
	}

	/** Minimum */
	public QPoints min(QPoints... values) {
		if (values.length == 0) {
			return QPoints.UNKNOWN;
		}

		if (QPoints.hasVeto(values)) {
			return QPoints.VETO;
		}

		QPoints min = null;
		for (QPoints element : values) {
			if (min == null) {
				min = element;
			} else {
				min = QPoints.min(min, element);
			}
		}

		return min;
	}

	public Number mean(Number... values) {
		if (containsUnknowns(values)) {
			return QIESLFunctions.UNKNOWN_DOUBLE;
		}
		if (containsQPoint(values)) {
			return mean(asQPointArray(values));
		}
		return MathUtils.mean(Arrays.asList(values));
	}

	private boolean containsQPoint(Number... values) {
		for (Number value : values) {
			if (value instanceof QPoints) {
				return true;
			}
		}
		return false;
	}

	/** Minimum */
	public QPoints mean(QPoints... values) {
		if (values.length == 0) {
			return QPoints.UNKNOWN;
		}

		if (QPoints.hasVeto(values)) {
			return QPoints.VETO;
		}

		QPoints sum = null;
		for (QPoints element : values) {
			if (sum == null) {
				sum = element;
			} else {
				sum = QPoints.add(sum, element);
			}
		}

		return QPoints.divide(sum, QPoints.valueOf(values.length));
	}

	/**
	 * Computes a grade (or degree) for a value on a integer scale
	 * 
	 * @param value
	 *            Value that gets graded.
	 * @param low
	 *            Value corresponding to lowest grade.
	 * @param lowResult
	 *            Lowest grade
	 * @param high
	 *            Value corresponding to highest grade.
	 * @param highResult
	 *            Highest grade
	 * @param numberOfGrades
	 *            Number of grades.
	 */
	// TODO adapt to use the interval arithmetic
	public QPoints dist(Number value, Number low, Number lowResult,
			Number high, Number highResult, Number numberOfGrades) {

		if (isUnknown(value)) {
			double l = lowResult.doubleValue();
			double h = highResult.doubleValue();
			if (l < h) {
				return QPoints.valueOf(l, h);
			}
			return QPoints.valueOf(h, l);
		}

		double l = lowResult.doubleValue();
		double h = highResult.doubleValue();

		if (QPoints.hasVeto(value, low, lowResult, high, highResult,
				numberOfGrades)) {
			return QPoints.VETO;
		}

		double valueRange = high.doubleValue() - low.doubleValue();
		double increment = valueRange / numberOfGrades.doubleValue();

		if (value.doubleValue() <= low.doubleValue()) {
			return QPoints.valueOf(l);
		}
		if (value.doubleValue() >= high.doubleValue()) {
			return QPoints.valueOf(h);
		}

		int interval = 1;
		double valueDiff = value.doubleValue() - low.doubleValue();
		while (valueDiff > interval * increment) {
			interval++;
		}

		if (l < h) {
			return QPoints.valueOf(interval + l - 1);
		}

		return QPoints.valueOf(l - interval + 1);
	}

	// TODO execute the whole calculation with intervals
	public QPoints linearDistribution(QPoints value, final Number low,
			final Number lowResult, final Number high, final Number highResult) {

		if (isUnknown(value)) {
			double l = lowResult.doubleValue();
			double h = highResult.doubleValue();
			if (l < h) {
				return QPoints.valueOf(l, h);
			}
			return QPoints.valueOf(h, l);
		}

		if (QPoints.hasVeto(value, low, lowResult, high, highResult)) {
			return QPoints.VETO;
		}

		double lowerBound = value.getInnerDataField().getLowerBound()
				.doubleValue();
		double upperBound = value.getInnerDataField().getUpperBound()
				.doubleValue();

		double res1 = linearDistOnDouble(lowerBound, low, lowResult, high,
				highResult);
		double res2 = linearDistOnDouble(upperBound, low, lowResult, high,
				highResult);

		if (res1 > res2) {
			return QPoints.valueOf(new GradeInterval(new Grade(new StdDouble(
					res2)), new Grade(new StdDouble(res1))));
		}
		return QPoints
				.valueOf(new GradeInterval(new Grade(new StdDouble(res1)),
						new Grade(new StdDouble(res2))));

	}

	/**
	 * Computes a grade (or degree) for a value on a floating point scale
	 * 
	 * @param value
	 * @param low
	 * @param lowResult
	 * @param high
	 * @param highResult
	 */
	public QPoints linearDistribution(Number value, Number low,
			Number lowResult, Number high, Number highResult) {

		if (isUnknown(value)) {
			double l = lowResult.doubleValue();
			double h = highResult.doubleValue();
			if (l < h) {
				return QPoints.valueOf(l, h);
			}
			return QPoints.valueOf(h, l);
		}

		if (QPoints.hasVeto(value, low, lowResult, high, highResult)) {
			return QPoints.VETO;
		}

		return QPoints.valueOf(linearDistOnDouble(value.doubleValue(), low,
				lowResult, high, highResult));

	}

	/**
	 * A linearDistribution on a double
	 * 
	 * @param value
	 * @param low
	 * @param lowResult
	 * @param high
	 * @param highResult
	 */
	private double linearDistOnDouble(double value, Number low,
			Number lowResult, Number high, Number highResult) {

		double valueRange = high.doubleValue() - low.doubleValue();
		double resultRange = Math.abs(highResult.doubleValue()
				- lowResult.doubleValue());

		if (value <= low.doubleValue()) {
			return lowResult.doubleValue();
		}
		if (value >= high.doubleValue()) {
			return highResult.doubleValue();
		}

		double valueDiff = value - low.doubleValue();

		double result = valueDiff / valueRange;

		if (lowResult.doubleValue() < highResult.doubleValue()) {
			return result * resultRange + lowResult.doubleValue();
		}

		return lowResult.doubleValue() - result * resultRange;
	}

	/**
	 * Distributes value on a floating scale of [0..toDistribute].
	 * 
	 * @param toDistribute
	 * @param value
	 */
	public QPoints linearDistribution(Number toDistribute, Number value) {
		return linearDistribution(value, 0, 0, 1, toDistribute);
	}

	/**
	 * Distributes -value on a floating scale of [0..toDistribute].
	 * 
	 * @param toDistribute
	 * @param value
	 */
	public QPoints negativeLinearDistribution(Number toDistribute, Number value) {
		return linearDistribution(value, 0, toDistribute, 1, 0);
	}

	/** Determine size of a region set dictionary. */
	public double loc(RegionSetDictionary dict) {

		if (isUnknown(dict)) {
			return UNKNOWN_DOUBLE;
		}

		CCSMPre.isNotNull(dict);
		return QuamocoUtils.getPositionCount(dict);
	}

	/**
	 * The this method creates a {@link RegionSetDictionary} that contains a
	 * region for each function affected by a finding stored in the finding
	 * report.
	 * 
	 * @throws ConQATException
	 */
	public RegionSetDictionary methodRange(FindingCollection report)
			throws ConQATException {

		if (isUnknown(report)) {
			return QIESLFunctions.UNKNOWN_REGION_SET_DICT;
		}

		RegionSetDictionary result = new RegionSetDictionary();
		for (Finding finding : report) {
			ElementLocation location = finding.getLocation();
			// if (!(location instanceof CodeLineLocation)) {
			// logger.warn("MethodRange applied on " + location.getClass()
			// + " finding=" + finding.toString()
			// + ". Ignoring the finding.");
			// continue;
			// }
			TextRegionLocation lineLocation = (TextRegionLocation) location;
			try {
				RegionSetDictionary regionSet = functionRangeResolver
						.obtainRegion(lineLocation);
				if (regionSet == null) {
					logger.warn("Found no region for line location: "
							+ lineLocation + ". Ignoring the finding.");
					continue;
				}

				result.addAll(regionSet);

			} catch (ConQATException e) {
				logger.  warn(e + "");
			}

		}
		return result;
	}

	/**
	 * Forwards to {@link #fileRange(FindingCollection)}.
	 * 
	 * @throws ConQATException
	 */
	public RegionSetDictionary classRange(FindingCollection report)
			throws ConQATException {
		if (isUnknown(report)) {
			return QIESLFunctions.UNKNOWN_REGION_SET_DICT;
		}

		RegionSetDictionary result = new RegionSetDictionary();
		for (Finding finding : report) {
			ElementLocation location = finding.getLocation();
			Assert.isInstanceOf(location, ElementLocation.class);
			RegionSetDictionary region = classRangeResolver
					.obtainRegion(location);
			result.addAll(region);
		}

		return result;
	}

	/**
	 * The this method creates a {@link RegionSetDictionary} that contains a
	 * region for each file affected by a finding stored in the finding report.
	 */
	public RegionSetDictionary fileRange(FindingCollection report)
			throws ConQATException {

		if (isUnknown(report)) {
			return QIESLFunctions.UNKNOWN_REGION_SET_DICT;
		}

		RegionSetDictionary result = new RegionSetDictionary();
		for (Finding finding : report) {
			ElementLocation location = finding.getLocation();
			Assert.isInstanceOf(location, ElementLocation.class);
			RegionSetDictionary region = fileRangeResolver
					.obtainRegion(location);
			result.addAll(region);
		}

		return result;
	}

	/**
	 * Forwards to {@link #methodRange(FindingCollection)}.
	 * 
	 * @throws ConQATException
	 */
	public RegionSetDictionary functionRange(FindingCollection report)
			throws ConQATException {
		return methodRange(report);
	}

	/**
	 * Checks an array of QPoints for empty
	 * 
	 * @param a
	 */
	public boolean isEmpty(QPoints[] a) {
		if (a == null) {
			return true;
		}
		return a.length == 0;
	}

	/**
	 * Generic extent on arrays
	 * 
	 * @param <T>
	 * @param a
	 */
	public <T> Double extent(T[] a) {
		if (isUnknown(a)) {
			return UNKNOWN_DOUBLE;
		}
		return Double.valueOf(a.length);
	}

	/**
	 * The extent of a FindingCollection is the number of findings
	 * 
	 * @param fcol
	 */
	public Double extent(FindingCollection fcol) {
		if (isUnknown(fcol)) {
			return UNKNOWN_DOUBLE;
		}

		return Double.valueOf(fcol.size());
	}

	/**
	 * The extent of a RegionSet is determined
	 * 
	 * @param regionSet
	 */
	public Double extent(RegionSet regionSet) {
		if (isUnknown(regionSet)) {
			return UNKNOWN_DOUBLE;
		}
		return Double.valueOf(regionSet.getPositionCount());
	}

	public static final FindingCollection UNKNOWN_FINDING_COLLECTION = new FindingCollection();
	public static final Double UNKNOWN_DOUBLE = new Double(Double.NaN);
	public static final RegionSetDictionary UNKNOWN_REGION_SET_DICT = new RegionSetDictionary();

	/** Check whether an object is a NaN value */
	public static boolean isUnknown(Object o) {

		if (o == null) {
			return true;
		}
		if (o == UNKNOWN_FINDING_COLLECTION) {
			return true;
		}
		if (o == UNKNOWN_DOUBLE) {
			return true;
		}
		if (o == UNKNOWN_REGION_SET_DICT) {
			return true;
		}

		if (o instanceof Double) {
			return Double.isNaN(((Double) o).doubleValue());
		}
		if (o instanceof Float) {
			return Float.isNaN(((Float) o).floatValue());
		}
		return false;
	}

	/**
	 * The extent of a RegionSetDictionary is determined
	 * 
	 * @param dict
	 */
	public Double extent(RegionSetDictionary dict) {
		if (isUnknown(dict)) {
			return UNKNOWN_DOUBLE;
		}
		return Double.valueOf(QuamocoUtils.getPositionCount(dict));
	}

	/**
	 * 
	 */
	public Number proportion(Number a, Number b) {
		if (containsUnknowns(new Object[] { a, b })) {
			return QIESLFunctions.UNKNOWN_DOUBLE;
		}
		if (containsQPoint(a, b)) {
			return proportion(QPoints.asQPoints(a), QPoints.asQPoints(b));
		}
		if (a.doubleValue() == 0.0 && b.doubleValue() == 0.0) {
			return Double.valueOf(0);
		}

		if (b.doubleValue() == 0) {
			throw new IllegalArgumentException("Proportion " + a + "/" + b
					+ "undefined");
		}

		return a.doubleValue() / b.doubleValue();
	}

	/**
	 * Correct calculation with QPoints
	 * 
	 * @param a
	 * @param b
	 */
	public Number proportion(QPoints a, QPoints b) {
		if (a.isSingleValue() && b.isSingleValue()
				&& a.getSingleValue().doubleValue() == 0.0
				&& b.getSingleValue().doubleValue() == 0.0) {
			return QPoints.asQPoints(0);
		}

		if (b.isSingleValue() && b.getSingleValue().doubleValue() == 0.0) {
			throw new IllegalArgumentException("Proportion " + a + "/" + b
					+ "undefined");
		}

		return QPoints.divide(a, b);
	}

}
