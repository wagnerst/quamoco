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

package de.quamoco.qm.editor.export.properties;

import de.quamoco.qm.ManualInstrument;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.editor.export.WebSiteExporter;
import de.quamoco.qm.editor.export.properties.elements.PropElement;

public class ManualInstrumentPropertyExporter extends
		InstrumentPropertyExporter<ManualInstrument> {

	/**
* 
*/
	public ManualInstrumentPropertyExporter(WebSiteExporter parent,
			ManualInstrument element, String dir) {
		super(parent, element, dir);
	}

	@Override
	protected PropElement[] getAdditionalAttributes() {
		return new PropElement[] {
				cr(QmPackage.eINSTANCE.getNamedElement_Name()),
				cr(QmPackage.eINSTANCE.getNamedElement_Title()),
				cr(QmPackage.eINSTANCE.getDescribedElement_Description()) };
	}

}
