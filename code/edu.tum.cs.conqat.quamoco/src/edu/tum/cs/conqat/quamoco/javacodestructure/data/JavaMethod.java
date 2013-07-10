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


public class JavaMethod extends JavaClassBodyElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 393813851352231516L;

	protected String name;

	protected int maxBlockNestingDepth;
	
	protected boolean abstractMethod;
	
	protected int numberOfParametes;

	public int getNumberOfParametes() {
		return numberOfParametes;
	}

	public void setNumberOfParametes(int numberOfParametes) {
		this.numberOfParametes = numberOfParametes;
	}

	public boolean isAbstractMethod() {
		return abstractMethod;
	}

	public void setAbstractMethod(boolean abstractMethod) {
		this.abstractMethod = abstractMethod;
	}

	public int getMaxBlockNestingDepth() {
		return maxBlockNestingDepth;
	}

	public void setMaxBlockNestingDepth(int maxBlockNestingDepth) {
		this.maxBlockNestingDepth = maxBlockNestingDepth;
	}

	public JavaMethod(JavaClass parent, String name) {
		super(parent);
		this.name = name;
	}

	public String toString() {
		return toShortString();
	}

	@Override
	public String toShortString() {
		return "[method=" + this.name + "," + this.getStartLine() + ","
				+ this.getEndLine() + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (!(arg0 instanceof JavaMethod)) {
			return false;
		}

		JavaMethod other = (JavaMethod) arg0;

		return this.getParent().equals(other.getParent())
				&& this.getStartLine() == other.getStartLine()
				&& this.getEndLine() == other.getEndLine()
				&& this.name.equals(other.name);
	}

	@Override
	public int hashCode() {
		return this.getParent().hashCode() ^ this.name.hashCode()
				^ this.getStartLine() ^ this.getEndLine();
	}

}
