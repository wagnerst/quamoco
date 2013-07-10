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

import de.quamoco.qm.FactorRanking;
import de.quamoco.qm.WeightedSumFactorAggregation;
import de.quamoco.qm.editor.export.WebSiteExporter;

/**
 * Exports an weighted sum factor aggregation
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
public class WeightedSumFactorAggregationPropertyExporter extends EvaluationPropertyExporter<WeightedSumFactorAggregation> {

	/**
	 * Constructor
	 */
	public WeightedSumFactorAggregationPropertyExporter(WebSiteExporter parent,
			WeightedSumFactorAggregation element, String dir) {
		super(parent, element, dir);
	}

	/**
	 * {@inheritDoc}
	 * @throws IOException 
	 */
	@Override
	protected void printAggregation(PrintStream out) throws IOException {
		out.print("<h4>Factor Aggregation</h4>");
		out.print("<table><tr><th>Ranking</th><th>CP</th><th>Name</th><th>Module</th></tr>");
		for(FactorRanking ranking: element.getRankings()) {
			out.print("<tr>");
			out.print("<td>" + ranking.getRank() + "</td>");
			out.print("<td>" + nf.format((ranking.getWeight()* element.getMaximumPoints())) + "</td>");
			out.print("<td>");
			exporter.printReference(ranking.getRankedElement(), out);
			out.print("</td>");
			out.print("<td>" + ranking.getRankedElement().getQualityModel().getName() + "</td>");
			out.print("</tr>");
		}
		out.print("</table>");
		
	}

}
