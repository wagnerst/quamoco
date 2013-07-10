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

package de.quamoco.qm.editor.export;

import org.conqat.lib.commons.string.StringUtils;

/**
 * Enumeration to denote an Excel version.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating GREEN Hash: AAA570AA00C94B47383E48ACE8F6374D
 */
public enum EExcelVersion {

	/** Excel 2003. */
	EXCEL_2003("xls"),

	/** Excel 2007. */
	EXCEL_2007("xlsx");

	/** File extension. */
	private String extension;

	/** Constructor. */
	private EExcelVersion(String extension) {
		this.extension = extension;
	}

	/** Return the extension. */
	public String getExtension() {
		return extension;
	}

	/** Get the text to display. */
	public String getText() {
		String text = name();
		text = text.replace('_', ' ');
		text = text.toLowerCase();
		text = StringUtils.capitalize(text);
		return text;
	}
}
