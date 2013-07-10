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

package edu.tum.cs.conqat.quamoco.understand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.conqat.lib.commons.collections.Pair;

/**
 * Parses a CSV file.
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
public class CSVReader {

	/**
	 * reads a csv file
	 * 
	 * @throws IOException
	 * */
	public static Pair<String[], List<String[]>> readCSV(InputStream in)
			throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		String header = reader.readLine();
		String headerFields[] = split(header);

		List<String[]> result = new LinkedList<String[]>();

		int lineNr = 0;
		String line;
		while ((line = reader.readLine()) != null) {
			lineNr++;
			String[] fields = split(line);
			if (fields.length == 0) {
				// ignore empty lines
			} else {
				result.add(fields);
			}
		}

		return new Pair<String[], List<String[]>>(headerFields, result);

	}

	public static String[] split(String s) {
		if (s.startsWith("\"") && s.endsWith("\"")) {
			return splitEscaped(s);
		}
		return splitUnescaped(s);
	}

	public static String[] splitEscaped(String s) {
		if (s.startsWith("\"")) {
			s = s.substring(1);
		}
		if (s.endsWith("\"")) {
			s = s.substring(0, s.length() - 1);
		}

		List<String> res = new LinkedList<String>();

		while (!s.isEmpty()) {
			if (s.startsWith("\"\"")) {
				int i = s.indexOf("\"\"", 2);
				res.add(s.substring(2, i));
				s = s.substring(i + 3);
			} else {
				int i = s.indexOf(",");
				if (i == -1) {
					res.add(s);
					s = "";
				} else {
					res.add(s.substring(0, i));
					s = s.substring(i + 1);
				}
			}

		}

		String[] r = new String[res.size()];
		int i = 0;
		for (String sa : res) {
			r[i++] = sa;
		}
		return r;
	}
	
	public static String[] splitUnescaped(String s) {
		List<String> res = new LinkedList<String>();

		while (!s.isEmpty()) {
			if (s.startsWith("\"")) {
				int i = s.indexOf("\"", 1);
				res.add(s.substring(1, i));
				s = s.substring(i + 2);
			} else {
				int i = s.indexOf(",");
				if (i == -1) {
					res.add(s);
					s = "";
				} else {
					res.add(s.substring(0, i));
					s = s.substring(i + 1);
				}
			}

		}

		String[] r = new String[res.size()];
		int i = 0;
		for (String sa : res) {
			r[i++] = sa;
		}
		return r;
	}

}
