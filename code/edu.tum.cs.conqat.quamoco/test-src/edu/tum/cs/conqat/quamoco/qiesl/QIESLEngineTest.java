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

import static edu.tum.cs.conqat.quamoco.qiesl.QPoints.VETO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.conqat.engine.resource.regions.RegionSetDictionary;
import org.conqat.lib.commons.collections.CollectionUtils;
import org.conqat.lib.commons.test.CCSMTestCaseBase;

import de.quamoco.qm.Type;

/**
 * Tests for the {@link QIESLEngine}.
 * 
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Hash: 2936428AD151F06968AB691F182CE6A6
 */
public class QIESLEngineTest extends CCSMTestCaseBase {

	/** The engine under test */
	private final QIESLEngine engine = new QIESLEngine();

	/** Simple test */
	public void testSimple() throws QIESLException {
		assertEquals(1, eval("1"));
	}

	/** Simple tests with variables */
	public void testSimpleMeasures() throws QIESLException {

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("a", 1);
		variables.put("Consistency @Identifier", 3);

		assertEquals(4, eval("%%a%%+%%Consistency @Identifier%%", variables));

	}

	public void testCR3309() throws QIESLException {
		String expr = "mean(allImpactsAndRefinements)";
		Map<String, Object> mandatoryVariables = new HashMap<String, Object>();
		mandatoryVariables.put("a", 2);
		assertEquals("NaN", String
				.valueOf(eval(expr, mandatoryVariables, null)));
	}

	public void testSize() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("a", new RegionSetDictionary());
		try {
			eval("size(%%a%%)", variables);
			fail("QIESLException expected");
		} catch (QIESLException expected) {
			assertEquals("Function size does not exist. Use extent instead",
					expected.getMessage());
		}
	}

	public void testMissingMeasure() throws QIESLException {
		String expr = "result = distRatioToPoints(%%562%% / %%#CountDeclFunction%%)";

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("562", 1);

		try {
			eval(expr, variables);
		} catch (QIESLException e) {
			assertTrue(e.getMessage().contains("Unknown variable"));
		}
	}

	public void testOtherErrorsThanParseError() {
		String expr = "result = 4 \u3434 5t";

		try {
			eval(expr);
		} catch (QIESLException e) {
			assertTrue(e.getMessage().startsWith("Error: Lexical"));
		}
	}

	/** Numbers as measure names */
	public void testNumberAsMeasureName() throws QIESLException {

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("123", 3);
		variables.put("345", 5);

		assertEquals(QPoints.valueOf(4.0), eval("mean(%%123%%,%%345%%)",
				variables));

	}

	public void testUndefinedNonQuotedVariable() {
		try {
			eval("test");
			fail("Exception expected");
		} catch (QIESLException ex) {
			assertTrue(ex.getMessage(), ex.getMessage().contains(
					"Unknown variable"));
		}
	}

	/** Test Results */
	public void testResults() throws QIESLException {

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("a", VETO);

		assertEquals(QPoints.VETO, eval("%%a%%", variables));
	}

	/** Test for mean function */
	public void testMean() throws QIESLException {
		assertEquals(QPoints.valueOf(4.0), eval("mean(3.0,5.0)"));
		assertEquals(QPoints.valueOf(4.0), eval("mean(3,5)"));
		assertEquals(QPoints.valueOf(4.0), eval("mean(3.0,5)"));
	}

	public void testExampleOfBaseModel1() throws QIESLException {
		String expr = "mean("
				+ "%%Same natural language @Comment%%,"
				+ "%%Natural language understandability by developers @Comment%%"
				+ ")";

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("Same natural language @Comment", QPoints.valueOf(1));
		variables.put(
				"Natural language understandability by developers @Comment",
				QPoints.valueOf(2));

		Object res = engine.evaluate(expr, createMandatoryVariables(variables),
				null);

		assertEquals(QPoints.valueOf(1.5), res);

	}

	/**
	 * Tests if the type check for Number works
	 * 
	 * @throws QIESLException
	 */
	public void testReturnTypeNumber() throws QIESLException {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("a", 4);
		Object erg = eval("%%a%%+3", variables);
		assertTrue(erg instanceof Number);
	}

	/**
	 * Tests if the type check for Number works, if a wrong type is returned.
	 */
	public void testReturnTypeNumberWrong() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("a", new LinkedList<Object>());
		try {
			eval("%%a%%", variables, Type.NUMBER);
			fail("QIESLException expected, because the type is wrong.");
		} catch (QIESLException e) {
			// expected
		}
	}

	// TODO (LH) reinclude
	public void testRealQIESLOfAccessibility() throws QIESLException {
		String expr = "int ratio =  distRatioPositive(" + "	size(intersect("
				+ "		nameRange(%%G188 @Block of Text%%),"
				+ "		nameRange(%%C21 @Block of Text%%)" + "	)) / size(%%UI%%)"
				+ ");" + "" + "// 0.0 => 0 points" + "// 1.0 => 100 points"
				+ "return distDegree(ratio, 0.0, 0, 1.0, 100);";
	}

	/** Validation test */
	public void testValidateSyntaxError() {
		try {
			HashMap<String, Object> variables = new HashMap<String, Object>();

			variables.put("a", QPoints.valueOf(2));
			variables.put("b", QPoints.valueOf(0));

			eval("(%%a%%+%%b%%", variables);
			fail("Syntax error expected");
		} catch (QIESLException ex) {
			assertTrue(ex.getMessage().contains("Encountered \"<EOF>\""));
		}
	}

	/** Addition of QPoints */
	public void testQPointsAdd() throws QIESLException {
		HashMap<String, QPoints> variables = new HashMap<String, QPoints>();

		variables.put("two", QPoints.valueOf(2));
		variables.put("zero", QPoints.valueOf(0));

		assertQPoints(QPoints.valueOf(4), "%%two%%+%%two%%+%%zero%%", variables);
		assertQPoints(QPoints.valueOf(2), "%%zero%%+%%two%%", variables);
		assertQPoints(QPoints.valueOf(0), "%%zero%%+%%two%%-%%two%%", variables);
		assertQPoints(QPoints.valueOf(0), "%%two%% + %%zero%% - %%two%%",
				variables);
		assertQPoints(QPoints.VETO, "%%two%%+veto+%%zero%%", variables);
		assertQPoints(QPoints.VETO, "veto+%%two%%+%%zero%%", variables);
	}

	/** Multiplication of QPoints */
	public void testQPointsMult() throws QIESLException {
		HashMap<String, QPoints> variables = new HashMap<String, QPoints>();

		variables.put("three", QPoints.valueOf(3));
		variables.put("two", QPoints.valueOf(2));
		variables.put("zero", QPoints.valueOf(0));

		assertQPoints(QPoints.valueOf(9), "%%three%%*%%three%%", variables);
		assertQPoints(QPoints.valueOf(0), "%%zero%%*%%three%%", variables);
		assertQPoints(QPoints.valueOf(0), "%%zero%%*%%zero%%", variables);
		assertQPoints(QPoints.valueOf(11), "%%three%% + 4 * %%two%%", variables);
		assertQPoints(QPoints.VETO, "%%two%%+veto", variables);
		assertQPoints(QPoints.VETO, "veto+%%two%%", variables);
	}

	/** Division of QPoints */
	public void testQPointsDiv() throws QIESLException {
		HashMap<String, QPoints> variables = new HashMap<String, QPoints>();

		variables.put("a", QPoints.valueOf(12));
		variables.put("b", QPoints.valueOf(4));

		assertQPoints(QPoints.valueOf(3), "%%a%%/%%b%%", variables);
		assertQPoints(QPoints.VETO, "veto/%%b%%", variables);
		assertQPoints(QPoints.VETO, "%%b%%/veto", variables);
	}

	/** Test for CR#3336 */
	public void testDeclaredVariable() throws QIESLException {
		String qiesl = "a=5;result=a;";
		assertEquals(5, eval(qiesl));
	}

	/** Evaluates the expression and expected the given results */
	private void assertQPoints(QPoints expectedResult, String expression,
			Map<String, QPoints> optionalVariables) throws QIESLException {
		assertEquals(expectedResult, engine.evaluate(expression,
				createOptionalVariables(new HashMap<String, Object>(
						optionalVariables)), Type.NUMBER));
	}

	private static QIESLEvalVariables createVariables() {
		return new QIESLEvalVariables(new QPoints[0]);
	}

	private static QIESLEvalVariables createMandatoryVariables(
			Map<String, Object> mandatoryVariables) {
		return new QIESLEvalVariables(new QPoints[0], CollectionUtils
				.<String, Object> emptyMap(), mandatoryVariables);
	}

	private static QIESLEvalVariables createOptionalVariables(
			Map<String, Object> optionalVariables) {
		return new QIESLEvalVariables(new QPoints[0], optionalVariables,
				CollectionUtils.<String, Object> emptyMap());
	}

	private Object eval(String expression) throws QIESLException {
		return eval(expression, CollectionUtils.<String, Object> emptyMap());
	}

	private Object eval(String expression, Map<String, Object> variables)
			throws QIESLException {
		return eval(expression, variables, null);
	}

	private Object eval(String expression,
			Map<String, Object> mandatoryVariables, Type expectedReturnType)
			throws QIESLException {
		return engine.evaluate(expression,
				createMandatoryVariables(mandatoryVariables),
				expectedReturnType);
	}

}
