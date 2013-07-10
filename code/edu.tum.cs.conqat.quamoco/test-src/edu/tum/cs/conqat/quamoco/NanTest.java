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

import org.conqat.lib.commons.test.CCSMTestCaseBase;

import edu.tum.cs.conqat.quamoco.qiesl.QIESLFunctions;

/**
 * Test some QIESL functions for their correct handling of NaN
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Hash: A81C9CB74828A9EE8FB96844A9D60FF3
 */
public class NanTest extends CCSMTestCaseBase {

	/** Test union of mean */
	public void testMean() {
		Double[] a = new Double[] { 1.0, Double.NaN, 2.0, 3.0 };

		// TODO (LH) Flo and I switched the mean implementation to ConQAT
		// MathUtils. Before that, the behavior used to be like this. Does
		// anyone know why?
		// assertEquals(2.0, new QIESLFunctions().mean(a));
		assertEquals(QIESLFunctions.UNKNOWN_DOUBLE, new QIESLFunctions(
				new NullFunctionRangeResolver(), new NullFileRangeResolver(),new NullFileRangeResolver(),
				null).mean(a));
	}

}
