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
import org.conqat.engine.commons.findings.location.TextRegionLocation;
import org.conqat.engine.core.core.AConQATFieldParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.resource.IElement;
import org.conqat.engine.resource.regions.RegionSetDictionary;
import org.conqat.engine.resource.text.ITextResource;
import org.conqat.engine.resource.util.ResourceTraversalUtils;

import edu.tum.cs.conqat.quamoco.IFunctionRangeResolver;

/**
 * A function range resolver using the data from an Understand report. The
 * understand report must be read with a "RangeReportReader" before.
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
@AConQATProcessor(description = "Resolves function ranges for C-style languages, based on information obtained from Understand.")
public class FunctionRangeResolverUnderstandBased extends ConQATProcessorBase
		implements IFunctionRangeResolver {

	/** Scope */
	@AConQATFieldParameter(parameter = "scope", attribute = ConQATParamDoc.INPUT_REF_NAME, description = "Scope")
	public ITextResource scope;

	/** locations */
	private Map<String, IElement> elementLocationMap;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IFunctionRangeResolver process() throws ConQATException {
			elementLocationMap = ResourceTraversalUtils.toLowercase(ResourceTraversalUtils
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
	public RegionSetDictionary obtainRegion(TextRegionLocation location)
			throws ConQATException {

		all++;

		final String lowerCase = location.getLocation().replace('\\', '/').toLowerCase();
		IElement element = elementLocationMap.get(lowerCase);
		if (element == null) {
			errors++;
			throw new ConQATException("FunctionRangeResolver: Element for location '"
					+lowerCase + "' not found.");
		}

		List<Range> data = (List<Range>) element
				.getValue(RangeReportReader.KEY_FOR_METHOD_RANGE);

		if (data == null) {
			errors++;
			throw new ConQATException("FunctionRangeResolver: No data on ranges found for location '"
					+ location.toString() + "' not found.");
		}

		int line = location.getRawStartLine();

		for (Range method : data) {
			if (line >= method.getStartLine()
					&& line <= method.getEndLine()) {
				return method.getRegionSetDictionary();		
			}
		}

		errors++;
		throw new ConQATException(
				"FunctionRangeResolver: Despite there is data on ranges, no method/function was found for location '"
						+ location.toString() + "'.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumLocations() {
		return all;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumLocationsWithoutRegion() {
		return errors;
	}

}