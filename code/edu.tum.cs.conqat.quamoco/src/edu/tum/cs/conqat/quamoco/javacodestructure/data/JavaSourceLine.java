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

public class JavaSourceLine implements JavaRegion {

	/**
	 * 
	 */
	private static final long serialVersionUID = -129536826665063785L;

	private JavaRegion parent;

	private int line;

	public JavaSourceLine(JavaRegion parent, int line) {
		this.parent = parent;
		this.line = line;
	}

	@Override
	public boolean equals(Object arg0) {
		if (!(arg0 instanceof JavaSourceLine)) {
			return false;
		}

		JavaSourceLine other = (JavaSourceLine) arg0;

		return this.parent.equals(other.parent) && this.line == other.line;
	}

	@Override
	public int hashCode() {
		return parent.hashCode() ^ this.line;
	}

	public JavaRegion getParent() {
		return parent;
	}

	public void setParent(JavaRegion parent) {
		this.parent = parent;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getLengthInLoc() {
		return 1;
	}

	@Override
	public String toString() {
		return parent.toString() + "[line=" + line + "]";
	}

	public String toShortString() {
		return parent.toShortString() + "[line=" + line + "]";
	}

}
