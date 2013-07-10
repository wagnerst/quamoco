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
$Id: ManualFindingsReaderTest.java 5044 2013-01-14 14:22:34Z lochmann $
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

import org.conqat.engine.commons.findings.Finding;
import org.conqat.engine.commons.findings.location.QualifiedNameLocation;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.core.logging.testutils.ProcessorInfoMock;
import org.conqat.lib.commons.collections.CollectionUtils;
import org.conqat.lib.commons.test.CCSMTestCaseBase;

/**
 * Simple test for {@link ManualFindingsReader}.
 * 
 * @author deissenb
 * @author $Author: lochmann $
 * @version $Rev: 5044 $
 * @levd.rating RED Hash: E6C4E40E9CE57E1E7691072F2E845578
 */
public class ManualFindingsReaderTest extends CCSMTestCaseBase {

	/** Test basic functionality. */
	public void test() throws ConQATException {
		ManualFindingsReader reader = new ManualFindingsReader();
		reader.init(new ProcessorInfoMock());
		reader.setInputFile(useTestFile("ManualFindingsReaderTest.xls"));
		MetricCollection result = reader.process();

		assertEquals(1, result.size());

		FindingCollection findingsCollection1 = (FindingCollection) result
				.get("TestMeasure1");
		Finding finding1 = CollectionUtils.getAny(findingsCollection1);
		assertEquals(finding1.getMessage(), "TestMessage1");
		

	}

}
