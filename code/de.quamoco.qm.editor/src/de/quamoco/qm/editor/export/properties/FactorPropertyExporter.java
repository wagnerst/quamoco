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
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import de.quamoco.qm.Factor;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.editor.export.WebSiteExporter;
import de.quamoco.qm.editor.export.properties.elements.FeatureMultipleOppositeReference;
import de.quamoco.qm.editor.export.properties.elements.MultipleReference;
import de.quamoco.qm.editor.export.properties.elements.PropElement;

/**
 * Exports factors
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
public class FactorPropertyExporter extends PropertyExporter<Factor> {

	/**
	 * Constructor
	 */
	public FactorPropertyExporter(WebSiteExporter parent, Factor element,
			String dir) {
		super(parent, element, dir);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void printContent(PrintStream out) throws IOException {
		out.print("<h4>Attributes</h4>");

		printTableOfStructuralElements(
				out,
				new PropElement[] {
						cr(QmPackage.eINSTANCE
								.getQualityModelElement_QualifiedName()),
						cr(QmPackage.eINSTANCE.getNamedElement_Name()),
						cr(QmPackage.eINSTANCE
								.getCharacterizingElement_Characterizes()),
						cr(QmPackage.eINSTANCE.getNamedElement_Title()),
						cr(QmPackage.eINSTANCE
								.getDescribedElement_Description()),
						cr(QmPackage.eINSTANCE.getFactor_QualityModel()),
						new CompletenessExporter() });

		out.print("<h4>Hierarchy</h4>");

		printTableOfStructuralElements(out,
				new PropElement[] { new RefinedByPropElement(),
						cr(QmPackage.eINSTANCE.getFactor_RefinesDirect()) });

		out.print("<h4>References</h4>");

		printTableOfStructuralElements(
				out,
				new PropElement[] {
						new FeatureMultipleOppositeReference(
								QmPackage.eINSTANCE.getEvaluation_Evaluates(),
								"Evaluated by"),
						new FeatureMultipleOppositeReference(
								QmPackage.eINSTANCE.getMeasure_MeasuresDirect(),
								"Measured By"),
						cr(QmPackage.eINSTANCE.getFactor_InfluencesDirect()),
						new FeatureMultipleOppositeReference(
								QmPackage.eINSTANCE
										.getFactor_InfluencesDirect(),
								"Influenced by") });
		
		printReferences(out);

	}

	

	/**
	 * Exports the reference to "Refined By"
	 * 
	 */
	private static class RefinedByPropElement extends MultipleReference {

		@Override
		public List<? extends EObject> getReferencedObjects(EObject eobject) {
			return ((Factor) eobject).getRefinedByDirect();
		}

		@Override
		public String getName() {
			return "Refined by";
		}

	}

	/**
	 * Exports the table with completeness values
	 * 
	 */
	private static class CompletenessExporter extends PropElement {

		@Override
		public void export(PrintStream out, EObject element) {
			Factor f = (Factor) element;
			out.print("<table border=0><tr><td><b>Module</b></td><td><b>Completeness</b></td></tr>");
			for (Map.Entry<QualityModel, Double> item : f
					.getAccumulatedCompleteness().entrySet()) {
				out.print("<tr><td>" + item.getKey().getName() + "</td><td>"
						+ ((int)(100.0*item.getValue())) + "%</td></tr>");
			}
			out.print("</table>");
		}

		@Override
		public String getName() {
			return "Completeness";
		}

	}
}
