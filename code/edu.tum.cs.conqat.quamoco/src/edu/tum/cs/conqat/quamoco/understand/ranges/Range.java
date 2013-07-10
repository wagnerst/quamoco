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

package edu.tum.cs.conqat.quamoco.understand.ranges;

import org.conqat.engine.resource.regions.RegionSetDictionary;

public class Range {
	private int startLine;
	private int endLine;

	private RegionSetDictionary regionSetDict;

	public RegionSetDictionary getRegionSetDictionary() {
		return regionSetDict;
	}

	public Range(int startLine, int endLine, RegionSetDictionary regionSetDict) {
		super();
		this.startLine = startLine;
		this.endLine = endLine;
		this.regionSetDict = regionSetDict;
	}

	public int getStartLine() {
		return startLine;
	}

	public int getEndLine() {
		return endLine;
	}

}
