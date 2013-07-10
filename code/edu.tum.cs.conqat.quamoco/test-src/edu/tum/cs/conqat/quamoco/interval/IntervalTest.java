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
package edu.tum.cs.conqat.quamoco.interval;

import org.conqat.lib.commons.test.CCSMTestCaseBase;

import edu.tum.cs.conqat.quamoco.qiesl.QPoints;

/**
 * Test some QIESL functions for their correct handling of NaN
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Hash: A81C9CB74828A9EE8FB96844A9D60FF3
 */
public class IntervalTest extends CCSMTestCaseBase {

	/** Test union of getLinearDistribution */
	public void testMean() {
		QPoints q1 = QPoints.valueOf(100);
		QPoints q2 = QPoints.valueOf(100);
		QPoints q3 = QPoints.valueOf(100);
		QPoints q4 = QPoints.valueOf(100);
		QPoints q5 = QPoints.valueOf(0, 100);

		QPoints sum = QPoints.valueOf(0);
		sum = QPoints.add(sum, q1);
		sum = QPoints.add(sum, q2);
		sum = QPoints.add(sum, q3);
		sum = QPoints.add(sum, q4);
		sum = QPoints.add(sum, q5);

		sum = QPoints.divide(sum, QPoints.valueOf(5));

		assertEquals(QPoints.valueOf(80, 100), sum);
		assertEquals("[(80.0; 100.0)]", sum.toString());
		assertEquals("[(80,00; 100,00)]", sum.toShortString());
	}

	/** Test union of getLinearDistribution */
	public void testMean2() {
		QPoints q1 = QPoints.valueOf(100);
		QPoints q2 = QPoints.valueOf(100);
		QPoints q3 = QPoints.valueOf(100);
		QPoints q4 = QPoints.valueOf(100);
		QPoints q5 = QPoints.valueOf(0, 100);

		QPoints sum = QPoints.valueOf(0);
		sum = QPoints.add(sum, q5);
		sum = QPoints.add(sum, q1);
		sum = QPoints.add(sum, q2);
		sum = QPoints.add(sum, q3);
		sum = QPoints.add(sum, q4);

		sum = QPoints.divide(sum, QPoints.valueOf(5));

		assertEquals(QPoints.valueOf(80, 100), sum);
		assertEquals("[(80.0; 100.0)]", sum.toString());
		assertEquals("[(80,00; 100,00)]", sum.toShortString());
	}

	/** Test union of getLinearDistribution */
	public void testMean3() {
		QPoints q1 = QPoints.valueOf(100);
		QPoints q2 = QPoints.valueOf(100);
		QPoints q3 = QPoints.valueOf(100);
		QPoints q4 = QPoints.valueOf(100);
		QPoints q5 = QPoints.valueOf(0, 100);

		QPoints sum = QPoints.valueOf(0);
		sum = QPoints.add(sum, q1);
		sum = QPoints.add(sum, q3);
		sum = QPoints.add(sum, q4);
		sum = QPoints.add(sum, q2);
		sum = QPoints.add(sum, q5);

		sum = QPoints.divide(sum, QPoints.valueOf(5));

		assertEquals(QPoints.valueOf(80, 100), sum);
		assertEquals("[(80.0; 100.0)]", sum.toString());
		assertEquals("[(80,00; 100,00)]", sum.toShortString());
	}

}
