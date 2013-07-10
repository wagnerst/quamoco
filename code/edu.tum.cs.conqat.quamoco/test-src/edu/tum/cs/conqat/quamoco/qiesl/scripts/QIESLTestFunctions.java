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
$Id: QIESLTestFunctions.java 5044 2013-01-14 14:22:34Z lochmann $
|                                                                          |
| Copyright 2005-2010 by the ConQAT Project                                |
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
package edu.tum.cs.conqat.quamoco.qiesl.scripts;

import org.conqat.engine.commons.findings.Finding;
import org.conqat.engine.commons.findings.FindingCategory;
import org.conqat.engine.commons.findings.FindingGroup;
import org.conqat.engine.commons.findings.FindingReport;
import org.conqat.engine.commons.findings.location.TextRegionLocation;
import org.conqat.engine.resource.regions.RegionSetDictionary;
import org.conqat.lib.commons.logging.ILogger;
import org.conqat.lib.commons.region.Region;
import org.conqat.lib.commons.region.RegionSet;

import edu.tum.cs.conqat.quamoco.FindingCollection;
import edu.tum.cs.conqat.quamoco.IFileRangeResolver;
import edu.tum.cs.conqat.quamoco.IFunctionRangeResolver;
import edu.tum.cs.conqat.quamoco.qiesl.QIESLFunctions;
import edu.tum.cs.conqat.quamoco.qiesl.QPoints;

/**
 * 
 * @author $Author: lochmann $
 * @version $Rev: 5044 $
 * @levd.rating RED Hash: 936E7B22F245FF558154963521C3CE32
 */
public class QIESLTestFunctions extends QIESLFunctions {

	/** Constructor */
	public QIESLTestFunctions(IFunctionRangeResolver functionRangeResolver,
			IFileRangeResolver fileRangeResolver,IFileRangeResolver classRangeResolver, ILogger logger) {
		super(functionRangeResolver, fileRangeResolver,classRangeResolver, logger);
	}
	
	/** Creates {@link Region} */
	public static QPoints qpoints(double value) {
		return QPoints.valueOf(value);
	}

	/** Creates {@link Region} */
	public static Region r(int start, int end) {
		return new Region(start, end);
	}

	/** Create {@link RegionSet} */
	public static RegionSet set(Region... regions) {
		RegionSet regionSet = new RegionSet();
		for (Region region : regions) {
			regionSet.add(region);
		}
		return regionSet;
	}

	/** Create {@link RegionSet} */
	public static RegionSetDictionary dict(RegionSet... sets) {
		RegionSetDictionary dictionary = new RegionSetDictionary();
		for (RegionSet set : sets) {
			dictionary.add(set);
		}
		return dictionary;
	}

	/** Create a finding with default values for category, group and tool */
	public static Finding f(String path, int firstLine) {
		return f("category", "group", "tool", path, firstLine);
	}

	/** Create a finding */
	public static Finding f(String categoryName, String groupName,
			String originTool, String path, int firstLine) {
		FindingReport report = new FindingReport();

		FindingCategory category = report.getOrCreateCategory(categoryName);

		FindingGroup group = category.getOrCreateFindingGroup(groupName);

		TextRegionLocation location = new TextRegionLocation(path, path, 10, 100,  firstLine, firstLine);

		Finding finding = group.createFinding(location);

		return finding;
	}

	/** Create a {@link FindingCollection} */
	public static FindingCollection fcol(Finding... findings) {
		FindingCollection collection = new FindingCollection();

		for (Finding finding : findings) {
			collection.add(finding);
		}

		return collection;
	}

}
