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

import org.conqat.lib.commons.test.CCSMTestCaseBase;

import edu.tum.cs.conqat.quamoco.NullFileRangeResolver;
import edu.tum.cs.conqat.quamoco.NullFunctionRangeResolver;
import edu.tum.cs.conqat.quamoco.qiesl.QIESLFunctions;
import edu.tum.cs.conqat.quamoco.qiesl.QPoints;

public class LinearDistOnIntervalsTest extends CCSMTestCaseBase {
	
	QIESLFunctions qf = new QIESLFunctions(new NullFunctionRangeResolver(), new NullFileRangeResolver(), new NullFileRangeResolver(), null);
	
	public void test1() {
		QPoints q1 = QPoints.valueOf(0.3,0.5);
		QPoints expected = QPoints.valueOf(0.3,0.5);
		
		QPoints result = qf.linearDistribution(q1, 0, 0, 1, 1);
		
		assertEquals(expected, result);
	}
	
	public void test2() {
		QPoints q1 = QPoints.valueOf(0.3,0.5);
		QPoints expected = QPoints.valueOf(3,5);
		
		QPoints result = qf.linearDistribution(q1, 0, 0, 1, 10);
		
		assertEquals(expected, result);
	}
	
	public void test3() {
		QPoints q1 = QPoints.valueOf(0.5,0.7);
		QPoints expected = QPoints.valueOf(3,5);
		
		QPoints result = qf.linearDistribution(q1, 0, 10, 1, 0);
		
		assertEquals(expected, result);
	}
	
	/**
	 * Tests the case, if no findings are found in a system. In this 
	 * case no calibration can be done, and the standard QIESL remains
	 * in the base model. In this case, the maxPoints must be returned,
	 * because as default 0 findings => maxPoints
	 */
	public void testGrenzfall() {
		double ratio = 0;
		
		// this distribution is the standard case, if no calibration was done
		QPoints result = qf.linearDistribution(ratio, 0, 100, 1, 0);
		
		assertEquals(QPoints.valueOf(100), result);		
	}
}
