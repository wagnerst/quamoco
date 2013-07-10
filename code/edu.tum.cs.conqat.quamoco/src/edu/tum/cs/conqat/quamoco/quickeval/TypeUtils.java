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
$Id: TypeUtils.java 4974 2012-02-17 09:34:10Z lochmann $
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
package edu.tum.cs.conqat.quamoco.quickeval;

import org.conqat.lib.commons.assertion.CCSMAssert;

import de.quamoco.qm.Type;
import edu.tum.cs.conqat.quamoco.FindingCollection;

// TODO (EJ) Move into Type in quamoco code
/**
 * Utility methods for measure Types.
 * 
 * @author ladmin
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Hash: AFDDDA448E5C1E4B6ADF275827D0D2CB
 */
public class TypeUtils {

	/** Checks whether a type is primitive */
	public static boolean isPrimitive(Type type) {
		switch (type) {
		case NUMBER:
			return true;
		case FINDINGS:
		case NONE:
			return false;
		default:
			throw new AssertionError("Not implemented for " + type);
		}
	}

	/** Computes a default value for a measure */
	public static Object defaultValue(Type type) throws AssertionError {
		Object value = null;
		switch (type) {
		case FINDINGS:
			value = new FindingCollection();
			break;
		case NUMBER:
			value = 0;
			break;
		case NONE:
			break;
		default:
			CCSMAssert.fail("Not implemented for type: " + type);
		}
		return value;
	}

	/** Convert a string representation to the corresponding object */
	public static Object convert(String valueString, Type type) {
		switch (type) {
		case NUMBER:
			return Double.valueOf(valueString);
		default:
			throw new AssertionError("Not implemented for: " + type);
		}
	}

}
