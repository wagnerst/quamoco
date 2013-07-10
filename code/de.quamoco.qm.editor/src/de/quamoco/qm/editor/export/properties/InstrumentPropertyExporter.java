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

import java.io.IOException;
import java.io.PrintStream;

import de.quamoco.qm.Instrument;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.editor.export.WebSiteExporter;
import de.quamoco.qm.editor.export.properties.elements.PropElement;

public abstract class InstrumentPropertyExporter<T extends Instrument> extends
		PropertyExporter<T> {

	/**
	 * Constructor
	 */
	public InstrumentPropertyExporter(WebSiteExporter parent, T element,
			String dir) {
		super(parent, element, dir);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void printContent(PrintStream out) throws IOException {
		out.print("<h4>Attributes" + "" + "</h4>");

		PropElement[] always = new PropElement[] {
				cr(QmPackage.eINSTANCE.getMeasurementMethod_Determines()),
				cr(QmPackage.eINSTANCE.getMeasurementMethod_QualityModel()),
				cr(QmPackage.eINSTANCE.getQualityModelElement_QualifiedName()) };

		PropElement[] additional = getAdditionalAttributes();

		PropElement[] all = new PropElement[always.length + additional.length];

		int target = 0;

		for (int i = 0; i < always.length; i++) {
			all[target++] = always[i];
		}
		for (int i = 0; i < additional.length; i++) {
			all[target++] = additional[i];
		}

		printTableOfStructuralElements(out, all);

		printReferences(out);

	}

	/**
	 * 
	 */
	protected abstract PropElement[] getAdditionalAttributes();
}
