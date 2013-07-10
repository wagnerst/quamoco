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



public abstract class JavaClassBodyElement implements JavaRegion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1025719243944081288L;

	private int startLine;
	private int endLine;

	private JavaClass parent;

	@Override
	public boolean equals(Object arg0) {
		if (!(arg0 instanceof JavaClassBodyElement)) {
			return false;
		}

		JavaClassBodyElement other = (JavaClassBodyElement) arg0;

		return this.getParent().equals(other.getParent())
				&& this.getStartLine() == other.getStartLine()
				&& this.getEndLine() == other.getEndLine();
	}

	@Override
	public int hashCode() {
		return parent.hashCode() ^ startLine ^ endLine;
	}

	public JavaClass getParent() {
		return parent;
	}

	public void setParent(JavaClass parent) {
		this.parent = parent;
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

	public JavaClassBodyElement(JavaClass parent) {
		this.parent = parent;
	}

	public int getLengthInLoc() {
		return this.endLine - this.startLine + 1;
	}

}
