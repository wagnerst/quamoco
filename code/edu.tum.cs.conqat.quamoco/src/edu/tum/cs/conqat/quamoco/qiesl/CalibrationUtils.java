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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.conqat.lib.commons.collections.CollectionUtils;

/**
 * Utils for parsing and replacing in QIESL formulas
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
public class CalibrationUtils {

	/** gets an entire linearDistribution(...) statement */
	private final static Pattern LINEAR_DISTRIUBTIONS = Pattern.compile(
			"linearDistribution\\([^,]*?,[^,]*?,[^,]*?,[^,]*?,[^,]*?\\)",
			Pattern.DOTALL);

	/** parse linearDistribution(...) */
	private final static Pattern LINEAR_DISTRIUBTION_PARTS = Pattern
			.compile(
					"linearDistribution\\(([^,]*?),([^,]*?),([^,]*?),([^,]*?),([^,]*?)\\)",
					Pattern.DOTALL);

	/** Gets all linearDistributions as lists of String. */
	public static List<String> getLinearDistributions(String qiesl) {

		Matcher m = LINEAR_DISTRIUBTIONS.matcher(qiesl);

		List<String> result = new ArrayList<String>();
		while (m.find()) {
			result.add(m.group());
		}

		return result;
	}

	/** Gets all linearDistributions as lists of String. */
	public static String[] getParseLinearDistribution(String linearDist) {

		Matcher m = LINEAR_DISTRIUBTION_PARTS.matcher(linearDist);

		if (!m.matches()) {
			return null;
		}

		List<String> result = new ArrayList<String>();
		for (int i = 1; i <= 5; i++) {
			result.add(m.group(i));
		}
		return CollectionUtils.toArray(result, String.class);
	}

	/** substitutes the linearDistributions according to the substitution list */
	public static String substitute(String currentQIESL,
			HashMap<Integer, String> substitutionList) {

		StringBuffer result = new StringBuffer();

		int i = 0;
		Matcher m = LINEAR_DISTRIUBTIONS.matcher(currentQIESL);
		while (m.find()) {
			if (substitutionList.containsKey(i)) {
				m.appendReplacement(result, substitutionList.get(i));
			}
			i++;
		}
		m.appendTail(result);

		return result.toString();

	}

}
