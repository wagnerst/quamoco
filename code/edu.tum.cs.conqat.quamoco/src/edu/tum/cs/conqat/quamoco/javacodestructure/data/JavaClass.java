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

package edu.tum.cs.conqat.quamoco.javacodestructure.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class JavaClass implements JavaRegion {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5458661151374173025L;

	private JavaFile parent;

	private String name;

	public JavaFile getParent() {
		return parent;
	}

	public void setParent(JavaFile parent) {
		this.parent = parent;
	}

	private int startLine;
	private int endLine;

	@Override
	public boolean equals(Object arg0) {
		if (!(arg0 instanceof JavaClass)) {
			return false;
		}

		JavaClass other = (JavaClass) arg0;

		return this.getName().equals(other.getName())
				&& this.startLine == other.startLine
				&& this.endLine == other.endLine;
	}

	@Override
	public int hashCode() {
		return name.hashCode() ^ startLine ^ endLine;
	}

	private List<JavaClassBodyElement> bodyElements = new LinkedList<JavaClassBodyElement>();

	public JavaClass(JavaFile parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public List<JavaClassBodyElement> getBodyElements() {
		return Collections.unmodifiableList(bodyElements);
	}

	public void addBodyElement(JavaClassBodyElement method) {
		this.bodyElements.add(method);
	}

	public String toShortString() {
		StringBuffer res = new StringBuffer();
		res.append("[class=" + name + "," + startLine + "," + endLine + "]");
		return res.toString();
	}

	@Override
	public String toString() {
		StringBuffer res = new StringBuffer();
		res.append(toShortString());
		for (JavaClassBodyElement method : this.bodyElements) {
			res.append(method.toString());
		}
		return res.toString();
	}

	public int getStartLine() {
		return startLine;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public int getEndLine() {
		return endLine;
	}

	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLengthInLoc() {
		return this.endLine - this.startLine + 1;
	}

	public boolean isLineWithinClass(int line) {
		return line >= startLine && line <= endLine;
	}

	/**
	 * Gets the largest method that contains the specified line
	 * 
	 * @param line
	 * @return
	 */
	public JavaClassBodyElement getLargestBodyElementByLine(int line) {
		List<JavaClassBodyElement> matchingmethods = new ArrayList<JavaClassBodyElement>();
		for (JavaClassBodyElement m : this.bodyElements) {
			if (line >= m.getStartLine() && line <= m.getEndLine()) {
				matchingmethods.add(m);
			}
		}

		if (matchingmethods.isEmpty()) {
			return null;
		}

		Collections.sort(matchingmethods, new Comparator<JavaClassBodyElement>() {

			@Override
			public int compare(JavaClassBodyElement o1, JavaClassBodyElement o2) {
				return Integer.valueOf(o1.getLengthInLoc()).compareTo(
						o1.getLengthInLoc());
			}

		});

		return matchingmethods.get(matchingmethods.size() - 1);

	}

}
