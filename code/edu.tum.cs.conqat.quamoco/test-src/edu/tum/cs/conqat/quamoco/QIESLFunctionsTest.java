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
$Id: codetemplates.xml 18709 2009-03-06 13:31:16Z hummelb $
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
package edu.tum.cs.conqat.quamoco;

import org.conqat.engine.resource.regions.RegionSetDictionary;
import org.conqat.lib.commons.collections.CollectionUtils;
import org.conqat.lib.commons.region.Region;
import org.conqat.lib.commons.region.RegionSet;
import org.conqat.lib.commons.test.CCSMTestCaseBase;

import edu.tum.cs.conqat.quamoco.qiesl.QIESLFunctions;
import edu.tum.cs.conqat.quamoco.qiesl.QPoints;

/**
 * 
 * @author $Author: $
 * @version $Rev: $
 * @levd.rating RED Hash: F78D4C2D4894724B36CFC1D8CC5BFB75
 */
public class QIESLFunctionsTest extends CCSMTestCaseBase {

	private final QIESLFunctions qieslFunctions = new QIESLFunctions(
			new NullFunctionRangeResolver(), new NullFileRangeResolver(),
			new NullFileRangeResolver(), null);

	public void testDistOnFloats() {
		// assertEquals(0.2,
		// qieslFunctions.linearDistribution(QPoints.valueOf(2),
		// QPoints.valueOf(0), QPoints.valueOf(0), QPoints.valueOf(10),
		// QPoints.valueOf(1)).doubleValue(), 0.00001);
		// assertEquals(0.2,
		// qieslFunctions.linearDistribution(QPoints.valueOf(3),
		// QPoints.valueOf(1), QPoints.valueOf(0), QPoints.valueOf(11),
		// QPoints.valueOf(1)).doubleValue(), 0.00001);
		// assertEquals(0.2,
		// qieslFunctions.linearDistribution(QPoints.valueOf(8),
		// QPoints.valueOf(0), QPoints.valueOf(1), QPoints.valueOf(10),
		// QPoints.valueOf(0)).doubleValue(), 0.00001);
		// assertEquals(0.2,
		// qieslFunctions.linearDistribution(QPoints.valueOf(9),
		// QPoints.valueOf(1), QPoints.valueOf(1), QPoints.valueOf(11),
		// QPoints.valueOf(0)).doubleValue(), 0.00001);
		//
		// assertEquals(20,
		// qieslFunctions.linearDistribution(QPoints.valueOf(2),
		// QPoints.valueOf(0), QPoints.valueOf(0), QPoints.valueOf(10),
		// QPoints.valueOf(100)).doubleValue(), 0.00001);
		// assertEquals(20,
		// qieslFunctions.linearDistribution(QPoints.valueOf(3),
		// QPoints.valueOf(1), QPoints.valueOf(0), QPoints.valueOf(11),
		// QPoints.valueOf(100)).doubleValue(), 0.00001);
		// assertEquals(20,
		// qieslFunctions.linearDistribution(QPoints.valueOf(8),
		// QPoints.valueOf(0), QPoints.valueOf(100), QPoints.valueOf(10),
		// QPoints.valueOf(0)).doubleValue(), 0.00001);
		// assertEquals(20,
		// qieslFunctions.linearDistribution(QPoints.valueOf(9),
		// QPoints.valueOf(1), QPoints.valueOf(100), QPoints.valueOf(11),
		// QPoints.valueOf(0)).doubleValue(), 0.00001);
	}

	public void testLinearDist() {
		// assertEquals(20, qieslFunctions.linearDistribution(
		// QPoints.valueOf(100), QPoints.valueOf(0.2)).doubleValue(),
		// 0.00001);
		// assertEquals(22, qieslFunctions.linearDistribution(
		// QPoints.valueOf(110), QPoints.valueOf(0.2)).doubleValue(),
		// 0.00001);

	}

	public void testNegativeinearDist() {
		// assertEquals(80, qieslFunctions.negativeLinearDistribution(
		// QPoints.valueOf(100), QPoints.valueOf(0.2)).doubleValue(),
		// 0.00001);
		// assertEquals(60, qieslFunctions.negativeLinearDistribution(
		// QPoints.valueOf(100), QPoints.valueOf(0.4)).doubleValue(),
		// 0.00001);

	}

	public void testExampes1() {
		// assertEquals(qieslFunctions.linearDistribution(QPoints.valueOf(100),
		// QPoints.valueOf(300.0 / 600.0)).doubleValue(), 50, 0.0001);
		// assertEquals(qieslFunctions.negativeLinearDistribution(
		// QPoints.valueOf(100), QPoints.valueOf(18.0 / 20.0))
		// .doubleValue(), 10, 0.0001);
	}

	/** Test union of {@link RegionSet}s */
	public void testUnionWithSingleRegion() {
		RegionSet unionSet = union(createOneRegionsSet(), createOneRegionsSet());
		assertTrue(unionSet.positionsEqual(createOneRegionsSet()));
	}

	/** Test union of {@link RegionSet}s */
	public void testUnionWithDoubleRegions() {
		RegionSet unionSet = union(createTwoRegionsSet(), createTwoRegionsSet());
		assertTrue(unionSet.positionsEqual(createTwoRegionsSet()));
	}

	/** Test union of {@link RegionSet}s */
	public void testUnionWithLargeRegions() {
		RegionSet unionSet = union(createLargeRegionsSet(),
				createLargeRegionsSet());
		assertTrue(unionSet.positionsEqual(createLargeRegionsSet()));
	}

	/** Computes union of two region sets */
	private RegionSet union(RegionSet set1, RegionSet set2) {
		RegionSetDictionary union = qieslFunctions.union(dictionary(set1),
				dictionary(set2));
		assertEquals(1, union.regionSets.size());
		return CollectionUtils.getAny(union.regionSets.values());
	}

	/** Test case for {@link QIESL#clean(RegionSet)} */
	public void testCleanSingle() {
		assertTrue(createOneRegionsSet().positionsEqual(
				qieslFunctions.clean(createOneRegionsSet())));
	}

	/** Test case for {@link QIESL#clean(RegionSet)} */
	public void testCleanDouble() {
		assertTrue(createTwoRegionsSet().positionsEqual(
				qieslFunctions.clean(createTwoRegionsSet())));
	}

	/**
	 * Assert that two {@link RegionSet}s with the same {@link Region}s are
	 * equal
	 */
	public void testRegionSetEquals() {
		assertEquals(createOneRegionsSet(), createOneRegionsSet());
		assertEquals(createTwoRegionsSet(), createTwoRegionsSet());
	}

	/** Create set with single region */
	private RegionSet createOneRegionsSet() {
		return set(r(1, 2));
	}

	/** Create set with two regions */
	private RegionSet createTwoRegionsSet() {
		return set(r(1, 2), r(5, 6));
	}

	/** Create large region set with overlapping regions and duplicates */
	private RegionSet createLargeRegionsSet() {
		return set(r(1, 2), r(5, 6), r(95, 100), r(80, 96), r(85, 90), r(1, 1),
				r(1, 2));
	}

	// TODO (EJ) Consolidate redundancy with RegionSetTest. (Include test code
	// in ccsm-commons for this)
	/** Creates {@link Region} */
	private Region r(int start, int end) {
		return new Region(start, end);
	}

	/** Create {@link RegionSet} */
	private RegionSet set(Region... regions) {
		RegionSet regionSet = new RegionSet();
		for (Region region : regions) {
			regionSet.add(region);
		}
		return regionSet;
	}

	/** Create {@link RegionSetDictionary} */
	private RegionSetDictionary dictionary(RegionSet... regionSets) {
		RegionSetDictionary dictionary = new RegionSetDictionary();
		for (RegionSet regionSet : regionSets) {
			dictionary.add(regionSet);
		}
		return dictionary;
	}

	// /** Test cases for {@link QIESL#proportion(double, double)} */
	// public void testProportion() {
	// assertProportion(1, 1, 1);
	// assertProportion(5, 5, 1);
	//
	// assertProportion(1, 2, 0.5);
	// assertProportion(2, 4, 0.5);
	//
	// assertProportion(0, 1, 0);
	// assertProportion(0, 5, 0);
	// assertProportion(2, 10, 0.2);
	//
	// assertProportion(0, 0, 0);
	//
	// try {
	// //qieslFunctions.proportion(1, 0);
	// fail("Proportion should be undefined");
	// } catch (IllegalArgumentException e) {
	// // expected
	// }
	// }

	/**
	 * Test case for {@link QIESL#dist(double, double, int, double, int, int)}
	 */
	public void _testDistGrade() {
		assertDistGrade(0, 1);
		assertDistGrade(0.1, 1);
		assertDistGrade(0.166, 1);
		assertDistGrade(0.167, 2);
		assertDistGrade(0.2, 2);
		assertDistGrade(0.33, 2);
		assertDistGrade(0.34, 3);
		assertDistGrade(0.5, 3);
		assertDistGrade(0.51, 4);
		assertDistGrade(0.666, 4);
		assertDistGrade(0.67, 5);
		assertDistGrade(0.83, 5);
		assertDistGrade(0.84, 6);
		assertDistGrade(1, 6);
		assertDistGrade(0.9999999, 6);

		assertDistGrade(-0.5, 1);
		assertDistGrade(1.7, 6);
	}

	/** Assert distribution of grades */
	private void assertDistGrade(double value, Object expected) {
		assertEquals(expected, qieslFunctions.dist(value, 0, 1, 1, 6, 6));
	}

	/**
	 * Test case for {@link QIESL#dist(double, double, int, double, int, int)}
	 */
	public void _testDistGradeInverse() {
		assertDistGradeInverse(0, 6);
		assertDistGradeInverse(0.1, 6);
		assertDistGradeInverse(0.166, 6);
		assertDistGradeInverse(0.167, 5);
		assertDistGradeInverse(0.2, 5);
		assertDistGradeInverse(0.33, 5);
		assertDistGradeInverse(0.34, 4);
		assertDistGradeInverse(0.5, 4);
		assertDistGradeInverse(0.51, 3);
		assertDistGradeInverse(0.666, 3);
		assertDistGradeInverse(0.67, 2);
		assertDistGradeInverse(0.83, 2);
		assertDistGradeInverse(0.84, 1);
		assertDistGradeInverse(1, 1);
		assertDistGradeInverse(0.9999999, 1);

		assertDistGradeInverse(-0.5, 6);
		assertDistGradeInverse(1.7, 1);
	}

	/** Assert distribution of grades */
	private void assertDistGradeInverse(double value, Object expected) {
		assertEquals(expected, qieslFunctions.dist(value, 0, 6, 1, 1, 6));
	}

	/** Test case for {@link QIESL#distIncreasingDegree(double, double, double)} */
	public void _testDistDegree() {
		// assertEquals(0, qieslFunctions.distIncreasingDegree(0, 0, 1));
		// assertEquals(10, qieslFunctions.distIncreasingDegree(1, 0, 1));
		//
		// assertEquals(1, qieslFunctions.distIncreasingDegree(0.15, 0, 1));
		//
		// assertEquals(10, qieslFunctions.distDecreasingDegree(0, 0, 1));
		// assertEquals(0, qieslFunctions.distDecreasingDegree(1, 0, 1));
	}

	/**
	 * This test case can plot the intervals to the console to give a feel for
	 * the dist function.
	 */
	public void _testListIntervals() {
		// boolean printToSys = false;
		// for (int i = -5; i <= 105; i++) {
		// double value = i / 100.0;
		// double degreeInc = qieslFunctions.distIncreasingDegree(value, 0, 1);
		// double degreeDec = qieslFunctions.distDecreasingDegree(value, 0, 1);
		// assertEquals(10.0, degreeInc + degreeDec);
		//
		// if (printToSys) {
		// System.err.println("Value:\t" + value + ": degree: "
		// + degreeInc + "(dec: " + degreeDec + ")");
		// }
		// }
	}

	public void testMean() {
		QPoints v1 = QPoints.valueOf(0, 100);
		QPoints v2 = QPoints.valueOf(50);

		QPoints res = QPoints.valueOf(25, 75);

		assertEquals(res, qieslFunctions.mean(v1, v2));
	}

}
