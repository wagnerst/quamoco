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
| Copyright 2005-2010 Technische Universitaet Muenchen                     |
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.conqat.lib.commons.test.CCSMTestCaseBase;

import edu.tum.cs.conqat.quamoco.qiesl.CalibrationUtils;

/**
 * Test some QIESL functions for their correct handling of NaN
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Hash: A81C9CB74828A9EE8FB96844A9D60FF3
 */
public class CalibrationUtilsTest extends CCSMTestCaseBase {

	/** Test union of getLinearDistribution */
	public void testGetLinearDistributionsOnEmpty() {
		String qiesl = "";
		List<String> s = CalibrationUtils.getLinearDistributions(qiesl);

		assertEquals(Arrays.asList(new String[] {}), s);
	}

	/** Test union of getLinearDistribution */
	public void testGetLinearDistributionsOnSimple() {
		String qiesl = "result=linearDistribution(%%hallo%%,0,0,1,1);";
		List<String> s = CalibrationUtils.getLinearDistributions(qiesl);

		assertEquals(
				Arrays
						.asList(new String[] { "linearDistribution(%%hallo%%,0,0,1,1)" }),
				s);
	}

	/** Test union of getLinearDistribution */
	public void testGetLinearDistributionsOnComposed() {
		String qiesl = "result=linearDistribution(%%hallo%%,0,0,1,1)+linearDistribution(%%gugu%%,0,0,1,1);";
		List<String> s = CalibrationUtils.getLinearDistributions(qiesl);

		assertEquals(Arrays.asList(new String[] {
				"linearDistribution(%%hallo%%,0,0,1,1)",
				"linearDistribution(%%gugu%%,0,0,1,1)" }), s);
	}
	
	/** Test union of getLinearDistribution */
	public void testGetLinearDistributionsOnComposedKlamern() {
		String qiesl = "result=linearDistribution(extent(%%hallo%%),0,0,1,1)+linearDistribution(extent(%%gugu%%),0,0,1,1);";
		List<String> s = CalibrationUtils.getLinearDistributions(qiesl);

		assertEquals(Arrays.asList(new String[] {
				"linearDistribution(extent(%%hallo%%),0,0,1,1)",
				"linearDistribution(extent(%%gugu%%),0,0,1,1)" }), s);
	}

	/** Test union of getLinearDistribution */
	public void testGetLinearDistributionsOnComposedWithLineBreak() {
		String qiesl = "result=\nlinearDistribution(%%hallo%%,0,0,1,1)\r+\r\nlinearDistribution(%%gugu%%,0,0,1,1);";
		List<String> s = CalibrationUtils.getLinearDistributions(qiesl);

		assertEquals(Arrays.asList(new String[] {
				"linearDistribution(%%hallo%%,0,0,1,1)",
				"linearDistribution(%%gugu%%,0,0,1,1)" }), s);
	}

	/** Test union of getLinearDistribution */
	public void testGetLinearDistributionsOnComposedWithLineBreakWithinLinear() {
		String qiesl = "result=\nlinearDistribution(\r%%hallo%%,0,\r\n0,1,\n1)\r+\r\nlinearDistribution(%%gugu%%,\n0,\r\n0,1,1);";
		List<String> s = CalibrationUtils.getLinearDistributions(qiesl);

		assertEquals(Arrays.asList(new String[] {
				"linearDistribution(\r%%hallo%%,0,\r\n0,1,\n1)",
				"linearDistribution(%%gugu%%,\n0,\r\n0,1,1)" }), s);
	}

	/** Test union of getLinearDistribution */
	public void testGetParseLinearDistributionWrong() {
		String qiesl = "linearDistribution(a,b,c)";
		String[] s = CalibrationUtils.getParseLinearDistribution(qiesl);

		assertNull(s);
	}

	/** Test union of getLinearDistribution */
	public void testGetParseLinearDistributionWrong2() {
		String qiesl = "linearDistribution(a,b,c,d,e,f,g)";
		String[] s = CalibrationUtils.getParseLinearDistribution(qiesl);

		assertNull(s);
	}

	/** Test union of getLinearDistribution */
	public void testGetParseLinearDistributionSimple() {
		String qiesl = "linearDistribution(a,b,c,d,e)";
		List<String> s = Arrays.asList(CalibrationUtils
				.getParseLinearDistribution(qiesl));

		assertEquals(Arrays.asList(new String[] { "a", "b", "c", "d", "e", }),
				s);
	}
	
	

	/** Test union of getLinearDistribution */
	public void testGetParseLinearDistributionSimple2() {
		String qiesl = "linearDistribution(size(%%a%%),b,c,d,e)";
		List<String> s = Arrays.asList(CalibrationUtils
				.getParseLinearDistribution(qiesl));

		assertEquals(Arrays.asList(new String[] { "size(%%a%%)", "b", "c", "d",
				"e", }), s);
	}

	/** Test union of getLinearDistribution */
	public void testGetParseLinearDistributionSimple3() {
		String qiesl = "linearDistribution(size(%%Java/Inconsistent identity @Class%%),b,c,d,e)";
		List<String> s = Arrays.asList(CalibrationUtils
				.getParseLinearDistribution(qiesl));

		assertEquals(Arrays.asList(new String[] {
				"size(%%Java/Inconsistent identity @Class%%)", "b", "c", "d",
				"e", }), s);
	}

	/** Test union of getLinearDistribution */
	public void testGetParseLinearDistributionSimple4() {
		String qiesl = "linearDistribution(extent(classRange(%%Java/Inconsistent identity @Class%%))/%%Java/LOC%%,b,c,d,e)";
		List<String> s = Arrays.asList(CalibrationUtils
				.getParseLinearDistribution(qiesl));

		assertEquals(
				Arrays
						.asList(new String[] {
								"extent(classRange(%%Java/Inconsistent identity @Class%%))/%%Java/LOC%%",
								"b", "c", "d", "e", }), s);
	}

	/** Test union of getLinearDistribution */
	public void testGetParseLinearDistributionSimple5() {
		String qiesl = "linearDistribution(extent(classRange(%%Java/Inconsistent identity\n @Class%%))/%%Java/LOC%%,b,c,d,e)";
		List<String> s = Arrays.asList(CalibrationUtils
				.getParseLinearDistribution(qiesl));

		assertEquals(
				Arrays
						.asList(new String[] {
								"extent(classRange(%%Java/Inconsistent identity\n @Class%%))/%%Java/LOC%%",
								"b", "c", "d", "e", }), s);
	}

	/** Test union of getLinearDistribution */
	public void testGetParseLinearDistributionSimple6() {
		String qiesl = "linearDistribution(extent(classRange(%%Java/Inconsistent identity\n @Class%%))/%%Java/LOC%%,b,c,d,e)";
		List<String> s = Arrays.asList(CalibrationUtils
				.getParseLinearDistribution(qiesl));

		assertEquals(
				Arrays
						.asList(new String[] {
								"extent(classRange(%%Java/Inconsistent identity\n @Class%%))/%%Java/LOC%%",
								"b", "c", "d", "e", }), s);
	}
	
	/** Test union of getLinearDistribution */
	public void testGetParseLinearDistributionSimple7() {
		String qiesl = "linearDistribution(extent(classRange(%%Java/Inconsistent identity @Class%%))/%%Java/LOC%%,0.00,25.0,0.25,0)";
		List<String> s = Arrays.asList(CalibrationUtils
				.getParseLinearDistribution(qiesl));

		assertEquals(
				Arrays
						.asList(new String[] {
								"extent(classRange(%%Java/Inconsistent identity @Class%%))/%%Java/LOC%%",
								"0.00", "25.0", "0.25", "0" }), s);
	}
	
	

	/** Test union of getLinearDistribution */
	public void testGetParseLinearDistributionRealLife() {
		String qiesl = "linearDistribution("+"\r\n" + "		extent(\r\n"
				+ "			classRange(\r\n"
				+ "				%%Java/Inconsistent identity @Class%%\r\n" + "			)\r\n"
				+ "		)\r\n" + "		/%%Java/LOC%%\r\n" + "		"+","+"0.00\r\n"
				+ "		"+","+"25.0\r\n" + "		"+","+"0.25\r\n" + "		"+","+"0\r\n" + "	)";
		System.out.println(qiesl);
		List<String> s = Arrays.asList(CalibrationUtils
				.getParseLinearDistribution(qiesl));

		assertEquals(Arrays.asList(new String[] { "\r\n" + "		extent(\r\n"
				+ "			classRange(\r\n"
				+ "				%%Java/Inconsistent identity @Class%%\r\n" + "			)\r\n"
				+ "		)\r\n" + "		/%%Java/LOC%%\r\n" + "		", "0.00\r\n"
				+ "		", "25.0\r\n" + "		", "0.25\r\n" + "		", "0\r\n	" }),
				s);
	}
	
	/** Test union of getLinearDistribution */
	public void testSubstitution1() {
		String qiesl = "result=linearDistribution(a,b,c,d,e);";
		HashMap<Integer, String> subst = new HashMap<Integer, String>();
		subst.put(0, "linearDist(hallo)");

		String s = CalibrationUtils.substitute(qiesl, subst);
		
		assertEquals("result=linearDist(hallo);", s );
	}
	
	/** Test union of getLinearDistribution */
	public void testSubstitution2() {
		String qiesl = "result=linearDistribution(a,b,c,d,e)+linearDistribution(a,b,c,d)+linearDistribution(1,2,3,4,5);";
		HashMap<Integer, String> subst = new HashMap<Integer, String>();
		subst.put(0, "linearDist(hallo)");
		subst.put(1, "linearDist(gugu)");

		String s = CalibrationUtils.substitute(qiesl, subst);
		
		assertEquals("result=linearDist(hallo)+linearDistribution(a,b,c,d)+linearDist(gugu);", s );
	}
	
	/** Test union of getLinearDistribution */
	public void testSubstitution3() {
		String qiesl = "result=linearDistribution(a,b,c,d,e)+linearDistribution(4,5,6,7,8)+linearDistribution(1,2,3,4,5);";
		HashMap<Integer, String> subst = new HashMap<Integer, String>();
		subst.put(0, "linearDist(hallo)");
		subst.put(2, "linearDist(gugu)");

		String s = CalibrationUtils.substitute(qiesl, subst);
		
		assertEquals("result=linearDist(hallo)+linearDistribution(4,5,6,7,8)+linearDist(gugu);", s );
	}

}
