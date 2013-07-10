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

/*-----------------------------------------------------------------------+
 | de.quamoco.qm.guideline.extension
 |                                                                       |
   $Id: GeneratorExtensionUtil.java 4974 2012-02-17 09:34:10Z lochmann $            
 |                                                                       |
 | Copyright (c)  2004-2009 Technische Universitaet Muenchen             |
 |                                                                       |
 | Technische Universitaet Muenchen               #########  ##########  |
 | Institut fuer Informatik - Lehrstuhl IV           ##  ##  ##  ##  ##  |
 | Prof. Dr. Manfred Broy                            ##  ##  ##  ##  ##  |
 | Boltzmannstr. 3                                   ##  ##  ##  ##  ##  |
 | 85748 Garching bei Muenchen                       ##  ##  ##  ##  ##  |
 | Germany                                           ##  ######  ##  ##  |
 +-----------------------------------------------------------------------*/
package de.quamoco.qm.guideline.extension;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import de.quamoco.qm.provider.QmEditPlugin;

/**
 * This class provides utilities for managing guideline generator extensions.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating GREEN Hash: 7A9F78988D26F1C33675C871885F762E
 */
public final class GeneratorExtensionUtil {

	/**
	 * The constructor.
	 */
	private GeneratorExtensionUtil() {
		// class may not be instantiated
	}

	/**
	 * Constant for id of generator extension point.
	 */
	public static final String IGENERATOR_ID = "de.quamoco.qm.guideline.generators";

	/**
	 * Gets all generator extensions from the extension registry.
	 */
	public static List<GeneratorExtension> getExtensions() {
		ArrayList<GeneratorExtension> extensions = new ArrayList<GeneratorExtension>();
		for (IConfigurationElement element : Platform.getExtensionRegistry()
				.getConfigurationElementsFor(IGENERATOR_ID)) {
			try {
				IGenerator generator = (IGenerator) element
						.createExecutableExtension("class");
				String label = element.getAttribute("label");
				GeneratorExtension extension = new GeneratorExtension(
						generator, label);
				extensions.add(extension);
			} catch (CoreException e) {
				QmEditPlugin.INSTANCE.log(e);
			}
		}
		return extensions;
	}
}
