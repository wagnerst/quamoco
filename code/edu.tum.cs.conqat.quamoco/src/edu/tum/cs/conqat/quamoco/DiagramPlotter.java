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

package edu.tum.cs.conqat.quamoco;

import java.awt.Font;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.conqat.lib.commons.collections.Pair;

import de.progra.charting.ChartEncoder;
import de.progra.charting.CoordSystem;
import de.progra.charting.DefaultChart;
import de.progra.charting.model.ObjectChartDataModel;
import de.progra.charting.render.BarChartRenderer;
import edu.tum.cs.conqat.quamoco.lightweightxml.Document;
import edu.tum.cs.conqat.quamoco.lightweightxml.LightweightXMLParser;
import edu.tum.cs.conqat.quamoco.lightweightxml.Node;

/**
 * Outputs diagrams with all values for each factor
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
public class DiagramPlotter {

	/** summary html file that is parsed */
	private String summaryFile;

	/** directory where the diagrams are written */
	private String outputDir;

	/** saves the names of the factors */
	private List<String> columns;

	/** saves the parsed data, i.e. the entire html table */
	private List<HashMap<String, Object>> data;

	/**
	 * Does all the work
	 * 
	 * @throws Exception
	 */
	public void process() throws Exception {
		parseSummaryFile();
		outputDiagrams();
	}

	/**
	 * Output's the diagrams. It needs that 'columns' and 'data' is set
	 */
	private void outputDiagrams() {

		// Create one diagram for each column of the table
		for (String variable : columns) {

			// If the column does not contain Doubles, then ignore it
			if (!(data.get(0).get(variable) instanceof Double)) {
				continue;
			}

			// The data is saved here.
			List<Pair<String, Double>> transformed = new ArrayList<Pair<String, Double>>();

			for (HashMap<String, Object> entry : data) {
				Object value = (entry.get(variable));
				String system = (String) entry.get(columns.get(0));
				String type = (String) entry.get(columns.get(1));

				// ignore all non-java systems
				if (!type.equals("java")) {
					continue;
				}

				// all fields should contain doubles
				if (!(value instanceof Double)) {
					System.err.println("Parsing of '" + variable + "' of '"
							+ system + "' failed: '" + value + "'");
					continue;
				}

				transformed
						.add(new Pair<String, Double>(system, (Double) value));
			}

			// print the diagram
			printDiagram(transformed, variable);
		}
	}

	/**
	 * Prints the diagram
	 * 
	 * @param values
	 * @param title
	 */
	private void printDiagram(List<Pair<String, Double>> values, String title) {

		// sort it
		Collections.sort(values, new Comparator<Pair<String, Double>>() {

			@Override
			public int compare(Pair<String, Double> o1, Pair<String, Double> o2) {
				Double v1 = o1.getSecond();
				Double v2 = o2.getSecond();
				return -Double.compare(v1, v2);
			}
		});

		// transform the data for the library that we use
		String[] columns = new String[values.size()];
		double[] rows = new double[values.size()];

		int i = 0;
		for (Pair<String, Double> v : values) {
			columns[i] = v.getFirst();
			rows[i] = v.getSecond();
			i++;
		}

		printDiagramIntern(columns, rows, title);
	}

	/**
	 * Calls the library functions to print the diagram
	 * 
	 * @param columns
	 * @param values
	 * @param title
	 */
	private void printDiagramIntern(String[] columns, double[] values,
			final String title) {

		// transform the data
		double[][] model = new double[values.length][1];
		for (int i = 0; i < values.length; i++) {
			model[i][0] = values[i];
		}

		String[] rows = columns;

		// size of the image
		int width = 800;
		int height = 600;

		// Create the data model
		ObjectChartDataModel data = new ObjectChartDataModel(model,
				new String[] { title }, rows);

		// Create the chart with coordinate system and the given axis labels
		final DefaultChart c = new DefaultChart(data, title,
				DefaultChart.LINEAR_X_LINEAR_Y, "Year", "Growth");

		// Set the coordinate system, the format for the x-axis values and
		// the coord. system layout
		CoordSystem cs = new CoordSystem(data, new DecimalFormat(), false,
				true, false);
		c.setCoordSystem(cs);

		// Add a bar chart renderer
		c.addChartRenderer(
				new BarChartRenderer(c.getCoordSystem(), c.getChartDataModel(),
						new DecimalFormat("00"), new Font("sans", Font.ITALIC,
								9), 1.0f), 1);

		c.setBounds(new Rectangle(0, 0, width, height));

		//
		//
		//
		// Sometimes the diagram plotter hangs. Therefore
		// we start it as a thread and kill it after 12 sec
		//
		//
		//

		Runnable r = new Runnable() {

			@Override
			public void run() {

				try {
					ChartEncoder.createPNG(new FileOutputStream(outputDir
							+ "/dia_" + title.replaceAll("[^a-zA-Z]", "_")
							+ ".png"), c);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		Thread t = new Thread(r);
		t.start();

		for (int zeit = 0; zeit < 120; zeit++) {
			// wait 0.1 seconds
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (!t.isAlive()) {
				break;
			}
		}
		t.stop();

	}

	/**
	 * Parses the html file and fills the data structures 'columns' and 'data'
	 * 
	 * @throws Exception
	 */
	private void parseSummaryFile() throws Exception {
		columns = new ArrayList<String>();
		data = new LinkedList<HashMap<String, Object>>();

		try {
			FileInputStream inst = new FileInputStream(summaryFile);
			try {
				Document doc = LightweightXMLParser.parseHTML(inst, true,
						"UTF-8");
				Node table = doc.evaluateXPathSingle("html/body/table");
				List<Node> trs = table.evaluateXPath("tr");
				boolean first = true;
				boolean second = true;
				for (Node tr : trs) {
					if (first) {
						first = false;
						List<Node> ths = tr.evaluateXPath("th");
						for (Node th : ths) {
							columns.add(th.getText());
						}
					} else if (second) {
						second = false;
						// just ignore the second line
					} else {
						HashMap<String, Object> currentLine = null;
						List<Node> tds = tr.evaluateXPath("td");
						int i = 0;
						for (Node td : tds) {
							Object value = parseTd(td);
							if (currentLine == null) {
								currentLine = new HashMap<String, Object>();
								data.add(currentLine);
							}
							currentLine.put(columns.get(i), value);
							i++;
						}
					}

				}
			} finally {
				inst.close();
			}
		} catch (IOException e) {
			throw new Exception(e);
		}
	}

	/**
	 * Parses the string of a single table cell
	 * 
	 * @param td
	 */
	private Object parseTd(Node td) {
		String text = td.getText();

		// parse the output of lochmann's variant
		if (text.startsWith("[ENTIRE_PRODUCT:ENTIRE_PRODUCT=")) {
			text = text.substring(32);
			int i = text.indexOf("]");
			text = text.substring(0, i);

			int j = text.indexOf(",");
			if (j == -1) {
				return Double.valueOf(text);
			}

			String t1 = text.substring(1, j);
			String t2 = text.substring(j + 1, text.length() - 1);

			return (Double.valueOf(t1) + Double.valueOf(t2)) / 2;
			// parse a double value (interval or single value)
		} else if (text.trim().equals("-")) {
			// it is an unknown double
			return Double.NaN;
		} else if (text.startsWith("[")) {

			System.out.println("The text is '" + text + "'");

			// it's an interval
			int i = text.indexOf(';');

			if (i == -1) {
				return Double.NaN;
			}

			System.out.println("The index is '" + i + "'");

			Double value1 = Double.valueOf(text.substring(1, i));
			Double value2 = Double.valueOf(text.substring(i + 1,
					text.length() - 1));
			return (value1 + value2) / 2;
		} else {
			try {
				return Double.valueOf(text);
			} catch (NumberFormatException e) {
				// it is a not a double
				return text;
			}
		}

	}

	/**
	 * Main-method for calling it. This main-method is used by now.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		DiagramPlotter d = new DiagramPlotter();
		d.summaryFile = args[0];
		d.outputDir = args[1];

		d.process();
	}

}
