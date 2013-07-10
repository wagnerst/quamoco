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
 | de.quamoco.qm.guideline.docbook
 |                                                                       |
   $Id: EGuidelineTargetFormat.java 4974 2012-02-17 09:34:10Z lochmann $            
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
package de.quamoco.qm.guideline.docbook;

/**
 * The target format constants.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating GREEN Hash: 3A146429A7DD9B7A6767C008091C0644
 */
public enum EGuidelineTargetFormat {

	/**
	 * Constant for PDF target format.
	 */
	PDF("pdf", "pdf", "PDF"),

	/**
	 * Constant for HTML (single) target format.
	 */
	HTML_SINGLE("htmlsingle", "zip", "HTML (single)"),

	/**
	 * Constant for HTML (multi) target format.
	 */
	HTML_MULTI("html", "zip", "HTML (multi)"),

	/**
	 * Constant for XML format.
	 */
	XML(null, "xml", "Docbook XML");

	/**
	 * The File Extension corresponding to the target format.
	 */
	private String fileExtension;

	/**
	 * The label of the format.
	 */
	private String label;

	/**
	 * The corresponding ant target.
	 */
	private String target;

	/**
	 * The constructor.
	 */
	private EGuidelineTargetFormat(String target, String fileExt, String label) {
		this.target = target;
		this.fileExtension = fileExt;
		this.label = label;
	}

	/**
	 * @returns the file extension corresponding to the target format.
	 */
	public String getFileExtension() {
		return fileExtension;
	}

	/**
	 * @returns the label.
	 */
	public String getLabel() {
		return label;
	}

	/** Returns target. */
	public String getTarget() {
		return target;
	}
}
