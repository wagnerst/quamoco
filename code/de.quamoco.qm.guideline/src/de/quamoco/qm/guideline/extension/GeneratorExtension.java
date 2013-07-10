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
   $Id: GeneratorExtension.java 4974 2012-02-17 09:34:10Z lochmann $            
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

/**
 * This class provides a container for generator extensions and their labels.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating GREEN Hash: D701575DE8E875AEAFE0B59B849A229C
 */
public class GeneratorExtension implements Comparable<GeneratorExtension> {

	/**
	 * The generator.
	 */
	private final IGenerator generator;

	/**
	 * Label for the generator.
	 */
	private final String label;

	/**
	 * The constructor.
	 */
	public GeneratorExtension(IGenerator generator, String label) {
		this.generator = generator;
		this.label = label;
	}

	/**
	 * @returns the label.
	 */
	public String getLabel() {
		return label;
	}

	/** Returns generator. */
	public IGenerator getGenerator() {
		return generator;
	}

	/** {@inheritDoc} */
	public int compareTo(GeneratorExtension ext) {
		return this.getLabel().compareTo(ext.getLabel());
	}
}
