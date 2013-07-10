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
$Id: NullFunctionRangeResolver.java 5030 2012-09-19 12:24:47Z lochmann $
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
package edu.tum.cs.conqat.quamoco;

import org.conqat.engine.commons.findings.location.TextRegionLocation;
import org.conqat.engine.resource.regions.RegionSetDictionary;

/**
 * 
 * @author $Author: lochmann $
 * @version $Rev: 5030 $
 * @levd.rating RED Hash: 3B4670079D4CB4E10CD4F736093279CE
 */
public class NullFunctionRangeResolver implements IFunctionRangeResolver {

	/** Dummy region */
	public static final RegionSetDictionary DUMMY_REGION_SET = new RegionSetDictionary();

	/** {@inheritDoc} */
	@Override
	public RegionSetDictionary obtainRegion(TextRegionLocation location) {
		return DUMMY_REGION_SET;
	}

	/** {@inheritDoc} */
	@Override
	public int getNumLocationsWithoutRegion() {
		return 0;
	}

	/** {@inheritDoc} */
	@Override
	public int getNumLocations() {
		return 0;
	}

}
