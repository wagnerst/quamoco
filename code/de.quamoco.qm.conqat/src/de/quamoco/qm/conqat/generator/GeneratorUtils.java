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

package de.quamoco.qm.conqat.generator;

import java.io.File;

/**
 * Collection of utility method used by the generator.
 * 
 * TODO (FD): Some of the stuff here is redundant with things already present in
 * the ConQAT core or CQ.edit core.
 * 
 * @author deissenb
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 4D41AB7B503494A8A3B90D4CF6BF3081
 */
public class GeneratorUtils {

	/** Get block id of a block. */
	public static String getBlockId(File bundleLocation, String blockName) {
		return bundleLocation.getName() + "." + blockName;
	}

	/** Get block location. */
	public static File getBlockLocation(File bundleLocation, String blockName) {
		File result = new File(bundleLocation, "blocks");
		String[] parts = blockName.split("[.]");
		for (int i = 0; i < parts.length; i++) {
			String part = parts[i];
			if (i == parts.length - 1) {
				part += ".cqb";
			}
			result = new File(result, part);
		}
		return result;
	}
}
