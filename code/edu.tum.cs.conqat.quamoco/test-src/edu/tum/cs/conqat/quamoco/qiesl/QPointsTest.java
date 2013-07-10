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

import org.conqat.lib.commons.test.CCSMTestCaseBase;

/**
 * Tests for {@link QPoints}.
 * 
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Hash: 3FE8D4225A71C00D798F9DAFCAF31296
 */
public class QPointsTest extends CCSMTestCaseBase {

	/** Creation test */
	public void testCreation() {
		assertTrue(VETO.isVeto());
	//	assertEquals(5.0, QPoints.valueOf(5.0).doubleValue());
	}

	/** Equals test */
	public void testEquals() {
		assertTrue(VETO.equals(VETO));
		assertTrue(QPoints.valueOf(5.0).equals(QPoints.valueOf(5.0)));
		assertFalse(QPoints.valueOf(3.0).equals(QPoints.valueOf(5.0)));
	}
	
	/** Equals test */
	public void testMean() {
		assertTrue(VETO.equals(VETO));
		assertTrue(QPoints.valueOf(5.0).equals(QPoints.valueOf(5.0)));
		assertFalse(QPoints.valueOf(3.0).equals(QPoints.valueOf(5.0)));
	}

	/** Addition test */
	public void testAdd() {
		assertEquals(QPoints.valueOf(4), QPoints.add(2, 2));
		assertEquals(QPoints.valueOf(2), QPoints.add(0, 2));
		assertEquals(QPoints.valueOf(0), QPoints.add(0, 0));
		assertEquals(QPoints.valueOf(0), QPoints.add(2, -2));
		assertEquals(VETO, QPoints.add(2, VETO));
		assertEquals(VETO, QPoints.add(VETO, 2));
	}

	/** Subtraction test */
	public void testSubtract() {
		assertEquals(QPoints.valueOf(0), QPoints.subtract(2, 2));
		assertEquals(VETO, QPoints.subtract(VETO, 2));
	}

	/** Add error case */
	public void testAddErrorCase() {
		try {
			QPoints.add("Hello", "QPoints");
			fail("NumberFormatException expected");
		} catch (NumberFormatException expected) {
			// ok
		}
	}

	/** ToString */
	public void testToString() throws Exception {
		assertEquals("veto", VETO.toString());
		assertEquals("[1.0]", QPoints.valueOf(1).toString());
	}
}
