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

package edu.tum.cs.conqat.quamoco;

import java.io.IOException;
import java.nio.charset.Charset;

import org.conqat.engine.commons.ConQATProcessorBase;
import org.conqat.engine.commons.findings.location.ElementLocation;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.resource.IContentAccessor;
import org.conqat.engine.resource.regions.RegionSetDictionary;
import org.conqat.engine.resource.scope.filesystem.FileContentAccessor;
import org.conqat.engine.resource.text.TextElement;
import org.conqat.lib.commons.assertion.CCSMAssert;
import org.conqat.lib.commons.filesystem.CanonicalFile;
import org.conqat.lib.commons.region.Region;
import org.conqat.lib.commons.region.RegionSet;

/**
 * 
 * {@ConQAT.Doc}
 * 
 * @author Florian Deissenboeck
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 053098A0521CF793A8B8E9EA3944F136
 */
@AConQATProcessor(description = "This processor returns an instance of "
		+ "IFileRangeResolver")
public class FileRangeResolver extends ConQATProcessorBase implements
		IFileRangeResolver {

	/** Returns itself. */
	@Override
	public IFileRangeResolver process() {
		return this;
	}

	/** {@inheritDoc}. Attention: This uses the platforms default charset. */
	private Region obtainRegionInternal(ElementLocation location)
			throws ConQATException {
		try {
			CanonicalFile canonicalFile = new CanonicalFile(
					location.getLocation());
			IContentAccessor contentAccessor = new FileContentAccessor(
					canonicalFile, canonicalFile.getParentFile(), canonicalFile
							.getParentFile().getName());
			TextElement textElement = new TextElement(contentAccessor,
					Charset.defaultCharset());
			return QuamocoUtils.obtainFileRegion(textElement);
		} catch (IOException e) {
			throw new ConQATException(e);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RegionSetDictionary obtainRegion(ElementLocation location)
			throws ConQATException {
		RegionSetDictionary result = new RegionSetDictionary();
		CCSMAssert.isInstanceOf(location, ElementLocation.class);

		Region region = obtainRegionInternal(location);

		RegionSet regionSet = result.get(location.getUniformPath());
		if (regionSet == null) {
			regionSet = new RegionSet(location.getUniformPath());
			result.add(regionSet);
		}
		regionSet.add(region);
		return result;
	}

}
