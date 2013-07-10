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

import org.conqat.engine.commons.findings.location.TextRegionLocation;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.resource.regions.RegionSetDictionary;

/**
 * Interface to obtain the code region that is defined by the function (method)
 * that subsumes the provide code location.
 * 
 * @author Florian Deissenboeck
 * @author $Author: lochmann $
 * @version $Rev: 5030 $
 * @levd.rating YELLOW Hash: 9E94E735B46CAE26E808EE57C5E998F5
 */
public interface IFunctionRangeResolver {
	/**
	 * Return code region that is defined by the function (method) that subsumes
	 * the provide code location.
	 */
	public RegionSetDictionary obtainRegion(TextRegionLocation location)
			throws ConQATException;

	/** Returns the number of locations processed */
	int getNumLocations();

	/** Returns the number of locations processed without regions */
	int getNumLocationsWithoutRegion();
}