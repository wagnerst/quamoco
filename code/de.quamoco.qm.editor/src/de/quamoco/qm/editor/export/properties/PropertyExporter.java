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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.conqat.lib.commons.filesystem.FileSystemUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.graphics.Image;

import de.quamoco.qm.QualityModelElement;
import de.quamoco.qm.Source;
import de.quamoco.qm.editor.export.WebSiteExporter;
import de.quamoco.qm.editor.export.properties.elements.PropElement;
import edu.tum.cs.emf.commons.resources.UUIDUtils;

/**
 * Exports the properties view
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
public abstract class PropertyExporter<T extends EObject> {
	
	protected NumberFormat nf = new DecimalFormat("0.00");
	protected NumberFormat nf2 = new DecimalFormat("#.#####");

	protected WebSiteExporter exporter;

	/** The element beeing exported */
	protected T element;

	/** The dir */
	protected String dir;

	/**
	 * Constructor
	 */
	public PropertyExporter(WebSiteExporter parent, T element, String dir) {
		super();
		this.exporter = parent;
		this.element = element;
		this.dir = dir;
	}

	/**
	 * Exports the property
	 * 
	 * @throws IOException
	 */
	public void export() throws IOException {
		File filesDir = new File(dir + "/files");
		FileSystemUtils.ensureDirectoryExists(filesDir);

		File file = new File(filesDir, UUIDUtils.getId(element) + ".html");
		PrintStream out = new PrintStream(new FileOutputStream(file));
		try {

			Image image = exporter.getLabelProvider().getImage(element);
			out.println("<h3><img src=\"icons/"
					+ exporter.getImageId(image) + ".png\"/> "
					+ exporter.getLabelProvider().getText(element) + "</h3>");

			printContent(out);

		} finally {
			out.close();
		}
	}

	/**
	 * Prints the content
	 */
	protected abstract void printContent(PrintStream out) throws IOException;

	/**
	 * Prints a table, where each StructuralElement is a row
	 */
	protected void printTableOfStructuralElements(PrintStream out,
			PropElement[] elements) throws IOException {
		out.println("<table>");
		out.println("<thead><tr><th>Property name</th><th>Property value</th></tr></thead>");

		out.println("<tbody>");
		for (PropElement feature : elements) {
			out.println("<tr>");
			out.println("<td>" + feature.getName() + "</td>");
			out.println("<td>");
			feature.export(out, element);
			out.println("</td>");
			out.println("</tr>");
		}
		out.println("</tbody>");
		out.println("</table>");
	}
	
	/**
	 * Prints the references
	 * @param out
	 */
	protected void printReferences(PrintStream out) {
		if(!(element instanceof QualityModelElement)) {
			return;
		}
		
		QualityModelElement qme = (QualityModelElement) element;
		
		if(qme.getOriginatesFrom().isEmpty()) {
			return;
		}
		
		if(qme.getOriginatesFrom().size() > 1) {
			System.err.println("WARNING: More than 1 sources in element " + qme.getQualifiedName());
		}

		out.print("<h4>References</h4>");
		out.print("<table style=\"border:0px;\">");
		int i = 0;
		for(Source source: qme.getOriginatesFrom()) {
			i++;
			out.print("<tr><td valign=top style=\"width:1cm;border:0px;\">[" +i + "]</td>");
			String url = source.getAnnotations().get("url");
			String descr = source.getDescription();
			if(url != null) {
				String href="<a target=\"_blank\" href=\"" + url + "\">" + url + "</a>";
				descr = descr.replace(url, href);
			}
			
			out.print("<td style=\"border:0px;\">"+descr + "</td></tr>");
			
		}
		out.print("</table>");
	}
	
	/**
	 * Short method name so that lists get easier readable
	 */
	protected PropElement cr(EStructuralFeature feat) {
		return PropElement.createCorrectOne(feat);
	}
}