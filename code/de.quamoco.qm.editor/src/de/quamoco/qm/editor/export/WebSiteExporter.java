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

package de.quamoco.qm.editor.export;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.conqat.lib.commons.filesystem.FileSystemUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

import de.quamoco.qm.Entity;
import de.quamoco.qm.QualityModelElement;
import de.quamoco.qm.editor.export.properties.PropertyExporter;
import de.quamoco.qm.editor.export.properties.PropertyExporterFactory;
import de.quamoco.qm.editor.export.properties.elements.PropElement;
import de.quamoco.qm.editor.pages.EntityHierarchyContentProvider;
import de.quamoco.qm.editor.pages.FactorHierarchyContentProvider;
import de.quamoco.qm.presentation.QmEditorPlugin;
import edu.tum.cs.emf.commons.resources.UUIDUtils;

/**
 * Exporter to export the quality model to a web site.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class WebSiteExporter {

	/** Identifier for the entity hierarchy. */
	private static final String ENTITY_HIERARCHY_ID = "entities";

	/** Identifier for the factor hierarchy. */
	private static final String FACTOR_HIERARCHY_ID = "factors";

	/** The resource set with the quality model. */
	private final ResourceSet resourceSet;

	/** The label provider to obtain text and images for elements. */
	private AdapterFactoryLabelProvider labelProvider;

	/** Map of images to ids with which they are saved. */
	private Map<Image, String> imageToId;

	/** The currently free image id. */
	private int currentId;

	/** Elements that are shown in the hierarchies. */
	private Set<Object> elements;

	/** The directory where we export to */
	private String dir;

	/** Constructor. */
	public WebSiteExporter(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
		PropElement.webSiteExporter = this;
	}

	/** Export the quality model to web site in a directory. */
	public void export(String dir, IProgressMonitor monitor) throws IOException {

		this.dir = dir;

		monitor.beginTask("Export quality model to web site",
				getNumberOfElements());

		imageToId = new IdentityHashMap<Image, String>();
		currentId = 0;

		AdapterFactory factory = ((AdapterFactoryEditingDomain) AdapterFactoryEditingDomain
				.getEditingDomainFor(resourceSet)).getAdapterFactory();
		labelProvider = new AdapterFactoryLabelProvider(factory);

		exportStaticFiles();
		monitor.worked(1);
		exportExplorerFile(monitor);
		monitor.worked(1);
		exportDefaultJSFile();
		monitor.worked(1);

		monitor.done();
	}

	/** Get the total number of elements. */
	private int getNumberOfElements() {
		elements = new HashSet<Object>();
		int number = getNumberOfElements(new FactorHierarchyContentProvider());
		number += getNumberOfElements(new EntityHierarchyContentProvider());
		return number;
	}

	/** Get the number of elements in a hierarchy. */
	private int getNumberOfElements(ITreeContentProvider provider) {
		int number = 0;
		for (Object object : provider.getElements(resourceSet)) {
			number += getNumberOfElements(provider, object);
		}
		return number;
	}

	/** Get the number of children of an element. */
	private int getNumberOfElements(ITreeContentProvider provider, Object object) {
		int number = 1;
		elements.add(object);
		for (Object child : provider.getChildren(object)) {
			number += getNumberOfElements(provider, child);
		}
		return number;
	}

	/** Export the static files from the resources folder. */
	private void exportStaticFiles() throws IOException {
		URL url = FileLocator.toFileURL(QmEditorPlugin.getPlugin().getBundle()
				.getResource("resources"));
		File sourceDir = new File(url.getFile());
		File targetDir = new File(dir);
		FileSystemUtils.ensureDirectoryExists(targetDir);
		FileSystemUtils.copyFiles(sourceDir, targetDir, new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return !pathname.getAbsolutePath().contains(".svn");
			}
		});
	}

	/** Export the Java Script file that defines types and their images. */
	private void exportDefaultJSFile() throws IOException {
		FileSystemUtils.ensureDirectoryExists(new File(dir + "/scripts"));
		File defaultJSFile = new File(dir + "/scripts/default.js");
		PrintStream out = new PrintStream(new FileOutputStream(defaultJSFile));

		try {
			FileSystemUtils.copy(WebSiteExporter.class
					.getResourceAsStream("default.js_prefix"), out);

			String[] ids = imageToId.values().toArray(new String[0]);
			for (int i = 0; i < ids.length; i++) {
				String id = ids[i];
				out.println(id + ":");
				out.println("{");
				out.println("icon:");
				out.println("{");
				out.println("image: \"icons/" + id + ".png\"");
				out.println("}");
				if (i < ids.length - 1) {
					out.println("},");
				} else {
					out.println("}");
				}
			}

			FileSystemUtils.copy(WebSiteExporter.class
					.getResourceAsStream("default.js_suffix"), out);
		} finally {
			out.close();
		}
	}

	/** Export the explorer file which shows the hierarchies. */
	protected void exportExplorerFile(IProgressMonitor monitor)
			throws FileNotFoundException, IOException {
		File explorerFile = new File(dir + "/explorer.html");
		PrintStream out = new PrintStream(new FileOutputStream(explorerFile));

		try {
			FileSystemUtils.copy(WebSiteExporter.class
					.getResourceAsStream("explorer.html_prefix"), out);

			out.println("<ul>");
			out.println("<li><a href=\"#factors\">Factors</a></li>");
			out.println("<li><a href=\"#entities\">Entities</a></li>");
			out.println("</ul>");

			exportFactorHierarchy(out, monitor);
			exportEntityHierarchy(out, monitor);

			FileSystemUtils.copy(WebSiteExporter.class
					.getResourceAsStream("explorer.html_suffix"), out);

		} finally {
			out.close();
		}
	}

	/** Export the entity hierarchy. */
	private void exportEntityHierarchy(PrintStream out, IProgressMonitor monitor)
			throws IOException {
		ITreeContentProvider contentProvider = new EntityHierarchyContentProvider();
		exportHierarchy(ENTITY_HIERARCHY_ID, contentProvider, out, monitor);
	}

	/** Export the factor hierarchy. */
	protected void exportFactorHierarchy(PrintStream out,
			IProgressMonitor monitor) throws IOException {
		ITreeContentProvider contentProvider = new FactorHierarchyContentProvider();
		exportHierarchy(FACTOR_HIERARCHY_ID, contentProvider, out, monitor);
	}

	/** Export a hierarchy based on a content provider. */
	protected void exportHierarchy(String id,
			ITreeContentProvider contentProvider, PrintStream out,
			IProgressMonitor monitor) throws IOException {
		out.println("<div class=\"tree\" id=\"" + id + "\">");
		exportChildren(resourceSet, contentProvider, out, monitor);
		out.println("</div>");
	}

	/** Export an element in the hierarchy. */
	private void exportElement(Object element,
			ITreeContentProvider contentProvider, PrintStream out,
			IProgressMonitor monitor) throws IOException {
		String label = labelProvider.getText(element);
		Image image = labelProvider.getImage(element);
		String id = getImageId(image);

		String idString = "";
		if (element instanceof EObject) {
			idString = "id=\"" + UUIDUtils.getId((EObject) element) + "\" ";
			exportElementProperties((EObject) element);
		}

		out.println("<li " + idString + "rel=\"" + id + "\">");
		out.println("<a href=\"#\">" + label + "</a>");

		monitor.worked(1);
		exportChildren(element, contentProvider, out, monitor);
		out.println("</li>");
	}

	/** Export the element properties to a separate file. */
	private void exportElementProperties(EObject element) throws IOException {
		PropertyExporter<?> exporter = PropertyExporterFactory.getExporter(
				this, element, dir);
		exporter.export();
	}

	/** Print a reference to an element. */
	public void printReference(EObject value, PrintStream out)
			throws IOException {

		Image valueImage = labelProvider.getImage(value);
		out.print("<img src=\"icons/" + getImageId(valueImage) + ".png\"/> ");

		if (elements.contains(value)) {
			String hierarchy = FACTOR_HIERARCHY_ID;
			if (value instanceof Entity) {
				hierarchy = ENTITY_HIERARCHY_ID;
			}
			out.print("<a href=\"javascript:selectObject('" + hierarchy
					+ "', '" + UUIDUtils.getId(value) + "');\">");
		}

		String label = labelProvider.getText(value);
		out.print(label);
		if (elements.contains(value)) {
			out.print("</a>");
		}
	}

	/** Get the id for an image. */
	public String getImageId(Image image) throws IOException {
		String id = imageToId.get(image);
		if (id == null) {
			id = "image" + currentId;
			currentId++;
			imageToId.put(image, id);
			ImageLoader loader = new ImageLoader();
			loader.data = new ImageData[] { image.getImageData() };
			FileSystemUtils.ensureDirectoryExists(new File(dir + "/icons"));
			loader.save(dir + "/icons/" + id + ".png", SWT.IMAGE_PNG);
		}
		return id;
	}

	/** Export the children of an element based on a content provider. */
	protected void exportChildren(Object element,
			ITreeContentProvider contentProvider, PrintStream out,
			IProgressMonitor monitor) throws IOException {
		Object[] children = contentProvider.getChildren(element);
		if (allOfType(children, QualityModelElement.class)) {
			Arrays.sort(children, new ObjectSortComparator());
		}
		if (children.length > 0) {
			out.println("<ul>");
			for (Object child : children) {
				exportElement(child, contentProvider, out, monitor);
			}
			out.println("</ul>");
		}
	}

	/**
	 * Checks if all elements of an array are of a certain type
	 * 
	 * @param children
	 * @param class1
	 * @return
	 */
	private boolean allOfType(Object[] children,
			Class<QualityModelElement> class1) {
		for (Object o : children) {
			if (!class1.isInstance(o)) {
				return false;
			}
		}
		return true;

	}

	/**
	 * Compares QualityModelElements according to getQualifiedName() and
	 * toString() for all other objects
	 * 
	 * @author lochmann
	 * @author $Author: lochmann $
	 * @version $Rev: 4974 $
	 * @levd.rating RED Rev:
	 */
	private static class ObjectSortComparator implements Comparator<Object> {

		/** {@inheritDoc} */
		@Override
		public int compare(final Object arg0, final Object arg1) {
			if (arg0 instanceof QualityModelElement
					&& arg1 instanceof QualityModelElement) {
				QualityModelElement q1 = (QualityModelElement) arg0;
				QualityModelElement q2 = (QualityModelElement) arg1;
				return q1.getQualifiedName().compareTo(q2.getQualifiedName());
			}

			String s1 = String.valueOf(arg0);
			String s2 = String.valueOf(arg1);
			return s1.compareTo(s2);
		}

	}

	/**
	 * Returns the label provider
	 */
	public AdapterFactoryLabelProvider getLabelProvider() {
		return this.labelProvider;
	}

	/**
	 * Returns the dir where we are exporting to
	 */
	public String getDir() {
		return this.dir;
	}
}
