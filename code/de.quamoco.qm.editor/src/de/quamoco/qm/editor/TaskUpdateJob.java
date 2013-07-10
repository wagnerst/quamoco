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

package de.quamoco.qm.editor;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.widgets.Display;

import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.presentation.QmEditorPlugin;

/**
 * Job to create markers for tasks in model text fields
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 62EDB02644D771B0F9DE5F65F2740AB9
 */
public class TaskUpdateJob extends Job {

	/**
	 * Task annotation
	 */
	private static final String TASK_ANNOTATION = "TODO";

	/**
	 * Identifier for marker
	 */
	private static final String MARKER_ID = QmEditorPlugin.INSTANCE
			.getSymbolicName()
			+ ".taskmarker";

	/**
	 * Resource whose contents should be searched for tasks
	 */
	private final Resource resource;

	/**
	 * Workspace file for the resource
	 */
	private final IFile file;

	/**
	 * Pattern to find task annotations
	 */
	private final Pattern pattern;

	/**
	 * Constructor
	 */
	public TaskUpdateJob(Resource resource) {
		super("Task " + resource.getURI().toFileString());
		this.resource = resource;
		file = getFile(resource);

		this.pattern = Pattern.compile(TASK_ANNOTATION + ".*",
				Pattern.CASE_INSENSITIVE);
	}

	/**
	 * Get workspace file for the resource
	 */
	private IFile getFile(Resource resource) {
		URI uri = resource.getURI();
		uri = resource.getResourceSet().getURIConverter().normalize(uri);
		return getFile(uri);
	}

	/**
	 * Get workspace file for
	 */
	private IFile getFile(URI uri) {
		String platformResourceString = uri.toPlatformString(true);
		return platformResourceString != null ? ResourcesPlugin.getWorkspace()
				.getRoot().getFile(new Path(platformResourceString)) : null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IStatus run(IProgressMonitor progressMonitor) {
		try {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					updateMarkers();
				}
			});
		} finally {
			progressMonitor.done();
		}

		return Status.OK_STATUS;
	}

	/**
	 * Update markers of the resource
	 */
	protected void updateMarkers() {
		deleteMarkers();
		createMarkers();
	}

	/**
	 * Delete markers from the resource
	 */
	private void deleteMarkers() {
		try {
			file.deleteMarkers(MARKER_ID, false, IResource.DEPTH_ZERO);
		} catch (CoreException e) {
			QmEditorPlugin.INSTANCE.log(e);
		}
	}

	/**
	 * Create markers for the resource
	 */
	private void createMarkers() {
		for (Iterator<EObject> i = resource.getAllContents(); i.hasNext();) {
			EObject element = i.next();
			createMarker(element);
		}
	}

	/**
	 * Create a marker for an element if applicable
	 */
	private void createMarker(EObject element) {
		if (element instanceof AnnotatedElement) {
			AnnotatedElement annotatedElement = (AnnotatedElement) element;
			EMap<String, String> annotations = annotatedElement
					.getAnnotations();
			for (Entry<String, String> annotation : annotations.entrySet()) {
				if (TASK_ANNOTATION.equalsIgnoreCase(annotation.getKey())) {
					String text = annotation.getValue();
					String message = TASK_ANNOTATION + ": " + text;
					createMarker(annotatedElement, message);
				}
			}
		}
		createMarker(element, QmPackage.eINSTANCE
				.getDescribedElement_Description());
		createMarker(element, QmPackage.eINSTANCE.getImpact_Justification());
		createMarker(element, QmPackage.eINSTANCE
				.getTextEvaluation_Specification());
		createMarker(element, QmPackage.eINSTANCE
				.getTextAggregation_Specification());
	}

	/**
	 * Create a marker for the contents of an attribute
	 */
	private void createMarker(EObject element, EAttribute attribute) {
		if (element.eClass().getEAllAttributes().contains(attribute)) {
			String text = (String) element.eGet(attribute);
			if (text != null) {
				Matcher matcher = pattern.matcher(text);
				while (matcher.find()) {
					String message = text.substring(matcher.start(), matcher
							.end());
					createMarker(element, message);
				}
			}
		}
	}

	/**
	 * Create a marker for an element
	 */
	private void createMarker(EObject element, String message) {
		try {
			IMarker marker = file.createMarker(MARKER_ID);
			marker.setAttribute(IMarker.MESSAGE, message);
			String string = element.eResource().getURIFragment(element);
			marker.setAttribute(EValidator.URI_ATTRIBUTE, string);
		} catch (CoreException e) {
			QmEditorPlugin.INSTANCE.log(e);
		}
	}
}