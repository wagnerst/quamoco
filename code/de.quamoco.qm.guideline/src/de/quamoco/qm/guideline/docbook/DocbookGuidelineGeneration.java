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
 | de.quamoco.qm.guideline.docbook
 |                                                                       |
   $Id: DocbookGuidelineGeneration.java 4974 2012-02-17 09:34:10Z lochmann $            
 |                                                                       |
 | Copyright (c)  2004-2009 Technische Universitaet Muenchen             |
 |                                                                       |
 | Technische Universitaet Muenchen               #########  ##########  |
 | Institut fuer Informatik - Lehrstuhl IV           ##  ##  ##  ##  ##  |
 | Prof. Dr. Manfred Broy                            ##  ##  ##  ##  ##  |
 | Boltzmannstr. 3                                   ##  ##  ##  ##  ##  |
 | 85748 Garching bei Muenchen                       ##  ##  ##  ##  ##  |
 | Germany                                           ##  ######  ##  ##  |
 +-----------------------------------------------------------------------*/
package de.quamoco.qm.guideline.docbook;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.dialogs.IOverwriteQuery;
import org.eclipse.ui.wizards.datatransfer.ImportOperation;
import org.eclipse.ui.wizards.datatransfer.ZipFileStructureProvider;

import de.quamoco.qm.QualityModel;
import de.quamoco.qm.guideline.QmGuidelineActivator;
import de.quamoco.qm.guideline.extension.IGenerator;
import de.quamoco.qm.presentation.QmEditorPlugin;

/**
 * Export {@link QualityModel}s as PDF, HTML (single), HTML (multi) or Docbook
 * XML File using the Docbook Framework.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: DFC9E203B05B26DF3D0C52B428A01939
 */
public class DocbookGuidelineGeneration {

	/**
	 * The Quality Models that are being exported
	 */
	private final List<QualityModel> qualityModels;

	/**
	 * The {@link IProject} where the Project Folder is saved in.
	 */
	private IProject project;

	/**
	 * The guideline generator to be used.
	 */
	private final IGenerator generator;

	/**
	 * The format of the Guideline.
	 */
	private final EGuidelineTargetFormat targetFormat;

	/**
	 * The path to the directory where Guideline should be stored.
	 */
	private final String targetPath;

	/**
	 * {@link IOverwriteQuery} for copying {@link ImportOperation}.
	 */
	private static final IOverwriteQuery OVERWRITE_ALL_QUERY = new IOverwriteQuery() {
		public String queryOverwrite(String pathString) {
			return IOverwriteQuery.ALL;
		}
	};

	/**
	 * Constructor for exporting Quality Models to path.
	 * 
	 * @param qualityModels
	 *            the quality models to export.
	 * @param targetFormat
	 *            the target format of the guideline file.
	 * @param targetPath
	 *            the target path where file should be stored.
	 * @param generator
	 *            the guideline generator.
	 */
	public DocbookGuidelineGeneration(List<QualityModel> qualityModels,
			IGenerator generator, EGuidelineTargetFormat targetFormat,
			String targetPath) {
		this.qualityModels = qualityModels;
		this.generator = generator;
		this.targetFormat = targetFormat;
		this.targetPath = targetPath;
	}

	/**
	 * Exports the {@link QualityModel}s.
	 */
	public void generateGuideline() throws InvocationTargetException,
			InterruptedException {
		// check whether DBF doesn't need to be used
		try {
			if (targetFormat == EGuidelineTargetFormat.XML) {
				runGenerator();
			} else {
				importDBF();
				runGenerator();
				callAnt();
				copyGuidelineFile();
				cleanUp();
			}
		} catch (IOException e) {
			throw new InvocationTargetException(e);
		}
	}

	/**
	 * Imports the Docbook Framework together with the project folder structure
	 * (invisibly) into the plugin workspace.
	 */
	private void importDBF() throws IOException, InvocationTargetException,
			InterruptedException {
		project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				".guideline_generation");
		if (project.exists()) {
			try {
				project.delete(true, null);
			} catch (CoreException e) {
				QmEditorPlugin.INSTANCE.log(e);
			}
		}
		ZipFile dbfZipFile = getDBFZipFile();

		ZipFileStructureProvider zipFileStructureProvider = new ZipFileStructureProvider(
				dbfZipFile);
		ImportOperation io = new ImportOperation(project.getFullPath(),
				zipFileStructureProvider.getRoot(), zipFileStructureProvider,
				OVERWRITE_ALL_QUERY);
		io.setCreateContainerStructure(false);
		io.run(null);
	}

	/**
	 * @returns the ZipFile where DBF is stored, ready to be imported
	 */
	private ZipFile getDBFZipFile() throws IOException {
		String dbfFolder = "zips/DBF.zip";
		File bundleFile = FileLocator.getBundleFile(QmGuidelineActivator
				.getDefault().getBundle());

		if (bundleFile.isDirectory()) {
			// Run from workbench
			URL url = FileLocator.find(QmGuidelineActivator.getDefault()
					.getBundle(), new Path(dbfFolder), null);
			url = FileLocator.resolve(url);
			return new ZipFile(new File(url.getPath()));
		}
		// Run from eclipse product so unzip from jar.
		ZipFile bundleZipFile = new ZipFile(bundleFile);
		ZipEntry dbfEntry = bundleZipFile.getEntry(dbfFolder);
		File tempDbfFile = unjarToTempFile(bundleZipFile, dbfEntry);
		return new ZipFile(tempDbfFile);
	}

	/**
	 * Unjars the DBF - {@link ZipEntry} from the plugin jar to a temporary
	 * file.
	 * 
	 * @param bundleZipFile
	 *            the plugin jar.
	 * @param dbfEntry
	 *            the Docbook Framework folder within the plugin jar.
	 */
	private File unjarToTempFile(ZipFile bundleZipFile, ZipEntry dbfEntry)
			throws IOException, FileNotFoundException {
		File tempDbfFile = File.createTempFile("DBF", ".zip");
		tempDbfFile.deleteOnExit();
		copyFile(bundleZipFile.getInputStream(dbfEntry), tempDbfFile);
		bundleZipFile.close();
		return tempDbfFile;
	}

	/**
	 * Runs the generator extension.
	 */
	private void runGenerator() throws IOException {
		generator.generateXML(qualityModels, getOutputStream());
	}

	/**
	 * Returns the stream to write the Docbook XML-file to.
	 */
	private OutputStream getOutputStream() throws IOException {
		if (targetFormat == EGuidelineTargetFormat.XML) {
			return new FileOutputStream(targetPath);
		}
		IFolder guidelineFolder = project.getFolder(project
				.getProjectRelativePath().append(
						"//guidelinegeneration//src//docbook//Guideline"));
		File file = Platform.getLocation().append(
				guidelineFolder.getFullPath().append("//guideline.xml"))
				.toFile();
		return new FileOutputStream(file);

	}

	/**
	 * build.xml executed by Ant
	 */
	private void callAnt() {
		// get location of Docbook Framework
		String dbfLocation = Platform.getLocation().append(
				project.getFullPath()).toString();
		// get location of build.xml
		File buildFile = Platform.getLocation().append(
				project.getFullPath()
						.append("//guidelinegeneration//build.xml")).toFile();
		// create Ant project
		final Project p = new Project();
		prepareProject(dbfLocation, buildFile, p);
		runAnt(p);
	}

	/**
	 * Prepares the Ant Project by adding a {@link DefaultLogger} and a
	 * {@link ProjectHelper}, initializing and parsing the Ant Project.
	 */
	private void prepareProject(String dbfLocation, File buildFile,
			final Project p) {
		p.setProperty("dbf.basedir", dbfLocation);
		p.setUserProperty("ant.file", buildFile.getAbsolutePath());
		// add Logger to Ant project
		DefaultLogger consoleLogger = new DefaultLogger();
		consoleLogger.setErrorPrintStream(System.err);
		consoleLogger.setOutputPrintStream(System.out);
		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
		p.addBuildListener(consoleLogger);
		// initialize Ant project
		p.fireBuildStarted();
		p.init();
		// add ProjectHelper to Ant project
		ProjectHelper helper = ProjectHelper.getProjectHelper();
		p.addReference("ant.projectHelper", helper);
		// parse build.xml
		helper.parse(p, buildFile);
	}

	/**
	 * Executes Ant.
	 */
	private void runAnt(final Project p) {
		p.executeTarget(targetFormat.getTarget());
		p.fireBuildFinished(null);
	}

	/**
	 * Copies the generated Guideline to the destination directory.
	 */
	private void copyGuidelineFile() throws FileNotFoundException, IOException {
		File targetFile = new File(targetPath);
		File sourceFile = null;
		sourceFile = getSourceFile("//" + targetFormat.getTarget()
				+ "//Guideline." + targetFormat.getFileExtension());
		copyFile(new FileInputStream(sourceFile), targetFile);
	}

	/**
	 * Defines the source file.
	 */
	private File getSourceFile(String path) {
		return Platform.getLocation().append(
				project.getFile(
						"//guidelinegeneration//target//Guideline" + path)
						.getFullPath()).toFile();

	}

	/**
	 * Copies one file to another using buffered streams.
	 */
	private void copyFile(InputStream in, File destFile) throws IOException {
		OutputStream out = new FileOutputStream(destFile);
		BufferedInputStream instream = new BufferedInputStream(in);
		BufferedOutputStream outstream = new BufferedOutputStream(out);
		while (instream.available() > 0) {
			outstream.write(instream.read());
		}
		instream.close();
		outstream.close();
	}

	/**
	 * Deletes invisible Project ".guideline_generation".
	 */
	private void cleanUp() {
		try {
			project.delete(true, null);
		} catch (CoreException e) {
			QmEditorPlugin.INSTANCE.log(e);
		}
	}

}
