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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class JavaFile implements JavaRegion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5340387237223304343L;

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

	private String path;

	private List<JavaClass> javaClasses = new LinkedList<JavaClass>();

	private int startLine;
	private int endLine;

	@Override
	public boolean equals(Object arg0) {
		if (!(arg0 instanceof JavaFile)) {
			return false;
		}

		JavaFile other = (JavaFile) arg0;

		return this.path.equals(other.path);
	}

	@Override
	public int hashCode() {
		return this.path.hashCode();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<JavaClass> getJavaClasses() {
		return Collections.unmodifiableList(javaClasses);
	}

	public void addJavaClass(JavaClass javaClass) {
		this.javaClasses.add(javaClass);
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(toShortString());
		for(JavaClass clazz: this.javaClasses) {
			result.append(clazz.toString());
		}
		return result.toString();
	}

	public String toShortString() {
		return "[file=" + path + "," + startLine + "," + endLine + "]";
	}

	public int getLengthInLoc() {
		return this.endLine - this.startLine;
	}
	
	public JavaClass getClassByLine(int line) {
		for(JavaClass clazz: this.javaClasses) {
			if(clazz.isLineWithinClass(line)) {
				return clazz;
			}
		}
		return null;
	}

}
