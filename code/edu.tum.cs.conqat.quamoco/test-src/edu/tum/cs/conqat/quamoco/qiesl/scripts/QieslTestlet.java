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
$Id: QieslTestlet.java 4974 2012-02-17 09:34:10Z lochmann $
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
package edu.tum.cs.conqat.quamoco.qiesl.scripts;

import java.io.File;
import java.io.IOException;

import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.jexl2.Script;
import org.conqat.lib.commons.assertion.CCSMAssert;
import org.conqat.lib.commons.filesystem.FileSystemUtils;
import org.conqat.lib.commons.string.StringUtils;
import org.conqat.lib.commons.test.TestletBase;
import org.junit.Ignore;

import edu.tum.cs.conqat.quamoco.NullFileRangeResolver;
import edu.tum.cs.conqat.quamoco.NullFunctionRangeResolver;
import edu.tum.cs.conqat.quamoco.qiesl.QIESLEngine;

/**
 * Testlet that tests QIESL scripts.
 * 
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Hash: 0CC730A44C94CB001091D9148258EC10
 */
@Ignore
public class QieslTestlet extends TestletBase {

	/** CHECK command */
	private static final String CHECK = "CHECK";

	/** SET command */
	private static final String SET = "SET";

	/** QIESL script under test */
	private final File script;

	/** Jexl engine used to interpret script */
	JexlEngine engine = QIESLEngine
			.createJexlEngine((new QIESLTestFunctions(
					new NullFunctionRangeResolver(),
					new NullFileRangeResolver(),new NullFileRangeResolver(), null)));

	/** Jexl context */
	JexlContext jexlContext = new MapContext();

	/** Constructor */
	public QieslTestlet(File script) {
		this.script = script;

	}

	/** Return name of test input file as name of JUnit test */
	@Override
	public String getName() {
		return script.getName();
	}

	/** {@inheritDoc} */
	@Override
	public void test() throws Exception {
		checkFormat();

		drive();
	}

	/** Assert that script test is according to format */
	private void checkFormat() {
		// TODO Auto-generated method stub
	}

	/** Perform actual test */
	private void drive() throws IOException {
		String content = FileSystemUtils.readFile(script);
		String[] lines = StringUtils.splitLines(content);

		StringBuilder qiesl = new StringBuilder();
		for (String line : lines) {
			if (!StringUtils.isEmpty(line) && !isComment(line)) {

				if (isCommand(line)) {
					if (qiesl.length() > 0) {
						evaluate(qiesl.toString());
						qiesl = new StringBuilder();
					}
					driveCommand(line);
				} else {
					qiesl.append(line + StringUtils.CR);
				}
			}
		}
		if (qiesl.length() > 0) {
			evaluate(qiesl.toString());
		}
	}

	/** Evaluates the given expression with the JexlEngine */
	private Object evaluate(String expression) {
		Script script = engine.createScript(expression);
		return script.execute(jexlContext);
	}

	/** Checks whether a line is a comment */
	private boolean isComment(String line) {
		return line.trim().startsWith("//");
	}

	/** Checks whether a line is a command */
	private boolean isCommand(String line) {
		line = line.trim();
		return line.startsWith(SET) || line.startsWith(CHECK);
	}

	/** Interprets single line */
	private void driveCommand(String line) {
		if (line.startsWith(SET)) {
			String qiesl = removeHead(line, SET);
			evaluate(qiesl);
		} else if (line.startsWith(CHECK)) {
			String check = removeHead(line, CHECK);
			String[] parts = check.split("==");
			CCSMAssert.isTrue(parts.length == 2,
					"Unexpected CHECK format (expecting: 'var == value'): "
							+ line);
			String variable = parts[0].trim();
			Object actualResult = evaluate(variable);

			String expectedResultString = parts[1].trim();
			Object expectedResult = evaluate(expectedResultString);

			assertEquals("Result not as expected: ", expectedResult.toString(),
					actualResult.toString());
		} else {
			throw new RuntimeException("Not implemented");
		}

	}

	/** Removes the head from a line */
	private String removeHead(String line, String command) {
		return StringUtils.stripPrefix(command, line).trim();
	}

}
