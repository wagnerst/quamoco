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

/*-----------------------------------------------------------------------+
 | edu.tum.cs.conqat.quamoco.inspection.cm.editor
 |                                                                       |
   $Id: CustomCmEditor.java 4974 2012-02-17 09:34:10Z lochmann $            
 |                                                                       |
 | Copyright (c)  2004-2010 Technische Universitaet Muenchen             |
 |                                                                       |
 | Technische Universitaet Muenchen               #########  ##########  |
 | Institut fuer Informatik - Lehrstuhl IV           ##  ##  ##  ##  ##  |
 | Prof. Dr. Manfred Broy                            ##  ##  ##  ##  ##  |
 | Boltzmannstr. 3                                   ##  ##  ##  ##  ##  |
 | 85748 Garching bei Muenchen                       ##  ##  ##  ##  ##  |
 | Germany                                           ##  ######  ##  ##  |
 +-----------------------------------------------------------------------*/
package edu.tum.cs.conqat.inspection.cm.presentation;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.FileEditorInput;

import edu.tum.cs.conqat.inspection.cm.Checklist;

/**
 * Customized CmEditor that builds the page for filling out checklists.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 003B2EE8B842B0EC7E8739B4CAB786B4
 */
public class CustomCmEditor extends CmEditor {

	/**
	 * The invisible {@link IProject} containing additional information files
	 * and the checklist itself.
	 */
	private IProject project;

	/** The path to the zip project in the workspace. */
	private String zipPath;

	/** The checklist evaluation form. */
	private ChecklistEvaluationForm checklistEvaluationForm;

	/** The measure info form. */
	private MeasureInfoForm measureInfoForm;

	/** {@inheritDoc} */
	@Override
	public void createPages() {
		// Unzip included files into invisible workspace project
		try {
			unzipFiles();
		} catch (Exception e) {
			CmEditorPlugin.INSTANCE.log(e);
		}
		// Creates the model from the editor input
		//
		createModel();

		// Only creates the other pages if there is something that can be edited
		//
		List<Resource> resources = getEditingDomain().getResourceSet()
				.getResources();
		if (!resources.isEmpty()) {
			List<EObject> eObjects = resources.get(0).getContents();
			if (eObjects == null || eObjects.isEmpty()) {
				MessageDialog.openError(getContainer().getShell(), "Error",
						"Resources are empty");
			}
			EObject eObject = eObjects.get(0);
			if (!(eObject instanceof Checklist)) {
				super.createPages();
				return;
			}
			Checklist checklist = (Checklist) eObject;

			Composite parent = getContainer();
			checklistEvaluationForm = new ChecklistEvaluationForm(this,
					checklist, parent);
			ScrolledForm form = checklistEvaluationForm.create();
			int pageIndex = addPage(form);
			setPageText(pageIndex, "Checklist Evaluation");

			measureInfoForm = new MeasureInfoForm(this, checklist, parent);
			form = measureInfoForm.create();
			pageIndex = addPage(form);
			setPageText(pageIndex, "Measure Info");
		}
	}

	/**
	 * Unzips the .cm file and saves the included files into an invisible
	 * workspace folder.
	 */
	private void unzipFiles() throws CoreException, IOException {
		FileEditorInput input = (FileEditorInput) getEditorInput();
		project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				"." + input.getName());
		if (!project.exists()) {
			project.create(null);
			project.open(null);
			zipPath = input.getPath().toString();
			BufferedInputStream bufInStream = new BufferedInputStream(
					new FileInputStream(zipPath));
			ZipInputStream zipInput = new ZipInputStream(bufInStream);
			ZipEntry entry = zipInput.getNextEntry();
			while (entry != null) {
				String outputPath = Platform.getLocation().append(
						project.getName() + "//" + entry.getName()).toString();
				BufferedOutputStream out = new BufferedOutputStream(
						new FileOutputStream(outputPath));
				byte[] buffer = new byte[1024];
				int length = zipInput.read(buffer);
				while (length >= 0) {
					out.write(buffer, 0, length);
					length = zipInput.read(buffer);
				}
				out.close();
				entry = zipInput.getNextEntry();
			}
			zipInput.close();
		}
		project.open(null);
		project.refreshLocal(IResource.DEPTH_ONE, null);
		IFile file = project.getFile("Checklist");
		IFileStore fileLocation = EFS.getLocalFileSystem().getStore(
				file.getLocationURI());
		FileStoreEditorInput fileStoreEditorInput = new FileStoreEditorInput(
				fileLocation);
		setInput(fileStoreEditorInput);
	}

	/** {@inheritDoc} */
	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		super.doSave(progressMonitor);
		FileStoreEditorInput input = (FileStoreEditorInput) getEditorInput();
		URI uri = input.getURI();
		File file = new File(uri);
		String[] uriParts = uri.getPath().split("/");
		String zipFileName = uriParts[uriParts.length - 2].substring(1);
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot()
				.getProjects();
		File zipFile = null;
		for (IProject project : projects) {
			IResource resource = project.findMember(zipFileName);
			if (resource != null) {
				zipFile = resource.getLocation().toFile();
				break;
			}
		}
		try {
			updateZipProject(file, zipFile);
		} catch (IOException e) {
			MessageDialog.openError(getContainer().getShell(),
					"Error saving Checklist", e.getMessage());
		}
	}

	/**
	 * Updates the Checklist file in the zip-Project
	 */
	private void updateZipProject(File checklistFile, File zipFile)
			throws IOException {
		File tempFile = File.createTempFile(zipFile.getName(), null);
		tempFile.delete();

		boolean renameOk = zipFile.renameTo(tempFile);
		if (!renameOk) {
			throw new IOException("Could not rename the file "
					+ zipFile.getAbsolutePath() + " to "
					+ tempFile.getAbsolutePath());
		}
		byte[] buf = new byte[1024];

		ZipInputStream zin = new ZipInputStream(new FileInputStream(tempFile));
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));

		ZipEntry entry = zin.getNextEntry();
		while (entry != null) {
			if (entry.getName().equals(checklistFile.getName())) {
				// Add new Checklist
				InputStream in = new FileInputStream(checklistFile);
				out.putNextEntry(new ZipEntry(checklistFile.getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			} else {
				// add other files
				out.putNextEntry(entry);
			}
			entry = zin.getNextEntry();
		}
		zin.close();
		out.close();
		tempFile.delete();
	}

	/**
	 * Gets the current project.
	 */
	public IProject getProject() {
		return project;
	}

	/** Gets the measure info form. */
	public MeasureInfoForm getMeasureInfoForm() {
		return measureInfoForm;
	}

	/**
	 * Switches the active page to page with specified index.
	 */
	public void switchActivePage(int index) {
		setActivePage(index);
	}

	/** {@inheritDoc} */
	@Override
	public void dispose() {
		// delete after editor's closed?
		// try {
		// project.delete(true, null);
		// } catch (CoreException e) {
		// CmEditorPlugin.INSTANCE.log(e);
		// }
		checklistEvaluationForm.dispose();
		measureInfoForm.dispose();
		super.dispose();
	}
}
