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
   $Id: IGenerator.java 4974 2012-02-17 09:34:10Z lochmann $            
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

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import de.quamoco.qm.QualityModel;

/**
 * Interface that users of the extension point "generator" have to implement in
 * order to generate a customized guideline.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 9F503717E8A8F24E7C17CA7B350FEBAF
 */
public interface IGenerator {

	/**
	 * Generates the DocBook XML file.
	 * 
	 * @param qualityModels
	 *            the {@link QualityModel}s to be exported.
	 * @param out
	 *            the {@link OutputStream} that the Docbook XML is written to.
	 * @throws IOException
	 *             this exception can occur while writing to the output stream.
	 */
	public void generateXML(List<QualityModel> qualityModels, OutputStream out)
			throws IOException;
}
