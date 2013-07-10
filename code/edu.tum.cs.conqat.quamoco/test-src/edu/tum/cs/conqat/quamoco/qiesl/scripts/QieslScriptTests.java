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
$Id: QieslScriptTests.java 4974 2012-02-17 09:34:10Z lochmann $
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

import junit.framework.Test;
import junit.framework.TestSuite;

import org.conqat.lib.commons.filesystem.FileExtensionFilter;
import org.conqat.lib.commons.test.CCSMTestCaseBase;

// TODO (EJ) Consolidate redundancy with IlaTest
/**
 * Test suite that collects {@link QieslTestlet}s
 * 
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Hash: EBDFBA292BFC4CFBFECC399D26B0F419
 */
public class QieslScriptTests extends CCSMTestCaseBase {

	/** Create a test suite. */
	public static Test suite() {
		File[] inputFiles = findQieslScriptFiles();

		TestSuite suite = new TestSuite("QIESL Tests");
		suite.setName("QIESL Test [" + inputFiles.length + " qiesl files]");
		for (File testFile : inputFiles) {
			suite.addTest(new QieslTestlet(testFile));
		}

		return suite;
	}

	/** Determines the files that serve as input for the integration tests */
	private static File[] findQieslScriptFiles() {
		// we have to create an instance here, since useTestFile is
		// an instance name. This name must be static, since JUnit expects
		// suite methods to be static.
		File directory = new QieslScriptTests().useTestFile("");
		return directory.listFiles(new FileExtensionFilter("qiesl"));
	}

}
