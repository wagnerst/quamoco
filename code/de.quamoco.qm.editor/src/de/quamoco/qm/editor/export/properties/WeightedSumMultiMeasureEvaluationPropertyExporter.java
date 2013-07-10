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

import de.quamoco.qm.LinearDecreasingFunction;
import de.quamoco.qm.LinearFunction;
import de.quamoco.qm.LinearIncreasingFunction;
import de.quamoco.qm.MeasureRanking;
import de.quamoco.qm.WeightedSumMultiMeasureEvaluation;
import de.quamoco.qm.editor.export.WebSiteExporter;

/**
 * 
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
public class WeightedSumMultiMeasureEvaluationPropertyExporter extends
		EvaluationPropertyExporter<WeightedSumMultiMeasureEvaluation> {

	/**
	 * Constructor
	 */
	public WeightedSumMultiMeasureEvaluationPropertyExporter(
			WebSiteExporter parent, WeightedSumMultiMeasureEvaluation element,
			String dir) {
		super(parent, element, dir);
	}

	@Override
	protected void printAggregation(PrintStream out) throws IOException {
		out.print("<h4>Measure Evaluation</h4>");
		out.print("<table><tr><th>Ranking</th><th>CP</th><th>Name</th><th>Module</th><th>Normalization</th><th>Range</th><th>Function</th></tr>");

		for (MeasureRanking ranking : element.getRankings()) {
			out.print("<tr>");
			out.print("<td>" + ranking.getRank() + "</td>");
			out.print("<td>"
					+ nf.format((ranking.getWeight() * element.getMaximumPoints()))
					+ "</td>");
			out.print("<td>");
			exporter.printReference(ranking.getRankedElement(), out);
			out.print("</td>");
			out.print("<td>"
					+ ranking.getRankedElement().getQualityModel().getName()
					+ "</td>");
			String normMeasure = ranking.getNormlizationMeasure() != null ? ranking
					.getNormlizationMeasure().getQualifiedName() : "";
			if (normMeasure != null) {
				out.print("<td>" + normMeasure + "</td>");
			} else {
				out.print("<td>&nbsp;</td>");
			}
			out.print("<td>" + ranking.getRange() + "</td>");
			String function = "";
			if (ranking.getFunction() instanceof LinearFunction) {
				if (ranking.getFunction() instanceof LinearIncreasingFunction) {
					function += "linear increasing points";
				} else if (ranking.getFunction() instanceof LinearDecreasingFunction) {
					function += "linear decreasing points";
				} else {
					throw new RuntimeException("This should never happen.");
				}
				function += " ("
						+ nf2.format(((LinearFunction) ranking.getFunction())
								.getLowerBound())
						+ " ; "
						+ nf2.format(((LinearFunction) ranking.getFunction())
								.getUpperBound() )+ ")";
			} else {
				throw new RuntimeException("This should never happen.");
			}
			out.print("<td>" + function + "</td>");
			out.print("</tr>");
		}
		out.print("</table>");
	}
}
