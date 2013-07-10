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

package edu.tum.cs.conqat.quamoco.understand;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.conqat.engine.commons.ConQATPipelineProcessorBase;
import org.conqat.engine.commons.node.NodeUtils;
import org.conqat.engine.core.core.AConQATAttribute;
import org.conqat.engine.core.core.AConQATParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.resource.IResource;
import org.conqat.engine.resource.text.ITextElement;
import org.conqat.engine.resource.text.ITextResource;
import org.conqat.engine.resource.util.ResourceTraversalUtils;
import org.conqat.lib.commons.collections.Pair;

/**
 * {@ConQAT.Doc}
 * 
 * @author Klaus Lochmann
 * @author $Author: lochmann $
 * @version $Rev: 3418 $
 * @levd.rating YELLOW Hash: B9475193C0C241643F138BA50954C68F
 */
@AConQATProcessor(description = "This processor reads a csv file of understand. If multiple files are read, the metric names must be disjunct.")
public class UnderstandCSVReportReader extends
		ConQATPipelineProcessorBase<IResource> {

	/** all reports */
	private List<ITextResource> reports = new LinkedList<ITextResource>();

	/** Filter for counting only some types of entities */
	private String rowFilter = "File";

	/** Filter for counting only some types of entities */
	private String prefixForOutput = "";

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "report-file", description = "csv file of understand", minOccurrences = 1)
	public void addReport(
			@AConQATAttribute(name = "file", description = "text resource of csv file") ITextResource file) {
		reports.add(file);
	}

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "row-filter", description = "filter for types of rows", minOccurrences = 0)
	public void setRowFilter(
			@AConQATAttribute(name = "text", description = "string") String string) {
		rowFilter = string;
	}

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "prefix-for-output", description = "prefix for output", minOccurrences = 0)
	public void setPrefixForOutput(
			@AConQATAttribute(name = "text", description = "string") String string) {
		prefixForOutput = string;
	}

	/**
	 * Loads a csv file and stores it's values in the scope
	 * 
	 * @param report
	 * @throws ConQATException
	 */
	private void loadReport(ITextElement report, IResource input)
			throws ConQATException {
		byte[] content = report.getContent();

		Pair<String[], List<String[]>> csv = null;

		InputStream in = new ByteArrayInputStream(content);
		try {
			try {
				csv = CSVReader.readCSV(in);
			} finally {
				in.close();
			}
		} catch (IOException e) {
			throw new ConQATException(e);
		}

		String[] header = csv.getFirst();

		// getLogger().info("header length=" + header.length);

		double[] sum = new double[header.length];

		for (String[] row : csv.getSecond()) {
			String firstColumn = row[0];

			// For each parsed file, take the sum of the columns
			if (firstColumn.matches(rowFilter)) {
				// getLogger().info("row length=" + row.length);
				// if (row.length > sum.length) {
				// getLogger().info("row is too long: '" + s(row));
				// }
				for (int i = 1; i < row.length; i++) {
					double value;
					try {
						value = Double.valueOf(row[i]);
					} catch (NumberFormatException e) {
						value = 0;
					}
					sum[i] += value;
				}
			}
		}

		for (int i = 0; i < header.length; i++) {
			String key = header[i];
			if (!prefixForOutput.isEmpty()) {
				key = prefixForOutput + "/" + key;
			}
			input.setValue(key, sum[i]);
			NodeUtils.addToDisplayList(input, key);
		}
	}

	private String s(String[] row) {
		String res = "";
		for (String s : row) {
			res += s + ",";
		}
		return res;
	}

	/**
	 * Process all report files {@inheritDoc}
	 */
	@Override
	protected void processInput(IResource input) throws ConQATException {
		for (ITextResource report : this.reports) {
			for (ITextElement reportelement : ResourceTraversalUtils
					.listTextElements(report)) {
				loadReport(reportelement, input);
			}
		}
	}

}
