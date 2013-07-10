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

import java.util.List;
import java.util.Map;

import org.conqat.engine.commons.ConQATParamDoc;
import org.conqat.engine.commons.ConQATProcessorBase;
import org.conqat.engine.commons.findings.location.ElementLocation;
import org.conqat.engine.commons.findings.location.TextRegionLocation;
import org.conqat.engine.core.core.AConQATFieldParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.core.core.IShutdownHook;
import org.conqat.engine.resource.IElement;
import org.conqat.engine.resource.regions.RegionSetDictionary;
import org.conqat.engine.resource.text.ITextResource;
import org.conqat.engine.resource.util.ResourceTraversalUtils;

import edu.tum.cs.conqat.quamoco.IFileRangeResolver;

/**
 * A function range resolver using the data from an Understand report. The
 * understand report must be read with a "RangeReportReader" before.
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
@AConQATProcessor(description = "Resolves class ranges for C-style languages, based on information obtained from Understand.")
public class ClassRangeResolverUnderstandBased extends ConQATProcessorBase
		implements IFileRangeResolver {

	/** Scope */
	@AConQATFieldParameter(parameter = "scope", attribute = ConQATParamDoc.INPUT_REF_NAME, description = "Scope")
	public ITextResource scope;

	/** locations */
	private Map<String, IElement> elementLocationMap;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IFileRangeResolver process() throws ConQATException {

		this.getProcessorInfo().registerShutdownHook(new IShutdownHook() {

			@Override
			public void performShutdown() throws ConQATException {
				getLogger().info(
						"Statistics of Class-Ranges: all=" + all + " errors="
								+ errors);
			}
		}, true);
			elementLocationMap = ResourceTraversalUtils
					.toLowercase(ResourceTraversalUtils
							.createLocationToElementMap(scope, IElement.class));
		return this;
	}

	/**
	 * Number of processed requests
	 */
	private int all = 0;

	/**
	 * Number of unsuccessful requests
	 */
	private int errors = 0;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RegionSetDictionary obtainRegion(ElementLocation location) throws ConQATException {

		all++;

		String lowerCase = location.getLocation().replace('\\', '/').toLowerCase();
		IElement element = elementLocationMap.get(lowerCase);
		if (element == null) {
			errors++;
			throw new ConQATException("ClassRange: Element for location '"
					+ lowerCase + "' not found. Sample path in set: "
					+ elementLocationMap.keySet().iterator().next());
		}

		List<Range> data = (List<Range>) element
				.getValue(RangeReportReader.KEY_FOR_CLASS_RANGE);

		if (data == null) {
			errors++;
			throw new ConQATException(
					"ClassRange: No data on ranges found for location '"
							+ location.toString() + "' not found.");
		}

//		if (!(location instanceof CodeLineLocation)) {
//			errors++;
//			throw new ConQATException(
//					"ClassRange: A class-range was applied to a ElementLocation. Because an ElementLocation has so information on the line in the file, the range cannot be calculated.");
//		}

		int line = ((TextRegionLocation) location).getRawStartLine();

		String classes = "";
		for (Range method : data) {
			classes += "class(from=" + method.getStartLine() + ",end=" + method.getEndLine() + ")";
			if (line >= method.getStartLine() && line <= method.getEndLine()) {
				return method.getRegionSetDictionary();
			}
		}

		errors++;
		throw new ConQATException(
				"ClassRange: There is no class at location " + location + ". Classes are at " +classes);
	}

}