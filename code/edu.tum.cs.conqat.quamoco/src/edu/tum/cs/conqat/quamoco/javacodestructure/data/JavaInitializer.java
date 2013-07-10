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


public class JavaInitializer extends JavaClassBodyElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3938154352231516L;

	public JavaInitializer(JavaClass parent) {
		super(parent);
	}

	public String toString() {
		return toShortString();
	}

	@Override
	public String toShortString() {
		return "[initializer=" + "," + this.getStartLine() + ","
				+ this.getEndLine() + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (!(arg0 instanceof JavaInitializer)) {
			return false;
		}

		JavaInitializer other = (JavaInitializer) arg0;

		return this.getParent().equals(other.getParent())
				&& this.getStartLine() == other.getStartLine()
				&& this.getEndLine() == other.getEndLine();
	}

	@Override
	public int hashCode() {
		return getParent().hashCode() ^ this.getStartLine() ^ this.getEndLine();
	}

}
