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

import de.quamoco.qm.QIESLEvaluation;
import de.quamoco.qm.editor.export.WebSiteExporter;
import de.quamoco.qm.editor.export.properties.elements.PropElement;

public class QIESLEvaluationPropertyExporter extends
		EvaluationPropertyExporter<QIESLEvaluation> {

	public QIESLEvaluationPropertyExporter(WebSiteExporter parent,
			QIESLEvaluation element, String dir) {
		super(parent, element, dir);
	}

	@Override
	protected void printAggregation(PrintStream out) throws IOException {
		out.print("<h4>References</h4>");

		printTableOfStructuralElements(out,
				new PropElement[] { new QIESLPrinter() });

	}

	private static class QIESLPrinter extends PropElement {

		@Override
		public String getName() {
			return "QIESL Evaluation";
		}

		@Override
		public void export(PrintStream out, EObject element) throws IOException {
			out.print("<pre>" + ((QIESLEvaluation) element).getSpecification()
					+ "</pre>");
		}

	}

}
