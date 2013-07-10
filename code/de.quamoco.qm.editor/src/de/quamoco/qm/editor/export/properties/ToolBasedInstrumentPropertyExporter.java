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

import org.eclipse.emf.ecore.EObject;

import de.quamoco.qm.QmPackage;
import de.quamoco.qm.ToolBasedInstrument;
import de.quamoco.qm.editor.export.WebSiteExporter;
import de.quamoco.qm.editor.export.properties.elements.PropElement;

public class ToolBasedInstrumentPropertyExporter extends
		InstrumentPropertyExporter<ToolBasedInstrument> {

	/**
	 * 
	 */
	public ToolBasedInstrumentPropertyExporter(WebSiteExporter parent,
			ToolBasedInstrument element, String dir) {
		super(parent, element, dir);
	}

	@Override
	protected PropElement[] getAdditionalAttributes() {
		return new PropElement[] {
				new ToolOutput(),
				cr(QmPackage.eINSTANCE.getToolBasedInstrument_Metric()) };
	}
	
	private static class ToolOutput extends PropElement {

		@Override
		public String getName() {
			return "Tool";
		}

		@Override
		public void export(PrintStream out, EObject element) throws IOException {
			ToolBasedInstrument inst = (ToolBasedInstrument) element;
			String url = inst.getTool().getAnnotations().get("url");
			if(url != null) {
				out.print("<a target=\"_blank\" href=\"" + url + "\">" + inst.getTool().getName() + "</a>");
			} else {
				out.print(inst.getTool().getName());
			}
		}
		
	}

}
