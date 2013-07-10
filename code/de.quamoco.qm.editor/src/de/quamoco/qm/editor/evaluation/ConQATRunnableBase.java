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

/*--------------------------------------------------------------------------+
$Id: ConQATRunnableBase.java 4974 2012-02-17 09:34:10Z lochmann $
|                                                                          |
| Copyright 2005-2010 by the ConQAT Project                                |
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
+--------------------------------------------------------------------------*/
package de.quamoco.qm.editor.evaluation;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.conqat.ide.commons.ui.logging.LoggingUtils;
import org.conqat.ide.core.launching.ConQATLaunchUtils;
import org.conqat.ide.core.launching.progress.ExecutionProgress;
import org.conqat.ide.core.launching.progress.ExecutionProgressManager;
import org.conqat.ide.core.launching.progress.IExecutionListener;
import org.conqat.ide.core.preferences.EConQATPreferences;
import org.conqat.ide.core.utils.BundleLocationOrganizer;
import org.conqat.lib.commons.filesystem.FileSystemUtils;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edapt.common.ResourceUtils;
import org.eclipse.emf.edapt.common.URIUtils;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import de.quamoco.qm.QualityModel;
import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.editor.CustomQmEditor;
import de.quamoco.qm.editor.action.ShowResultTreeMapAction;
import de.quamoco.qm.presentation.QmEditorPlugin;

/**
 * Base class for runnables to perform the ConQAT evaluation.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public abstract class ConQATRunnableBase implements IRunnableWithProgress {

	/** The quality model. */
	private final QualityModel model;

	/** The file with the manual measurement results. */
	private final String manualMeasureFile;

	/** The output directory. */
	private final String outputDir;

	/** The directory with the sources. */
	protected final String sourceDir;

	/** The project name. */
	protected final String projectName;

	/** Whether the execution of ConQAT is done. */
	private boolean done = false;

	/** The current execution. */
	public ExecutionProgress progress;

	/** The quality model editor. */
	private final CustomQmEditor editor;

	/**
	 * The name of the block that should be executed by the runnable. Only the
	 * name of the block is required. The path and the extension are added
	 * automatically.
	 */
	private final String blockName;

	/** Constructor. */
	public ConQATRunnableBase(String blockName, QualityModel model,
			String projectName, String sourceDir, String outputDir,
			String manualMeasureFile, CustomQmEditor editor) {
		this.blockName = blockName;
		this.model = model;
		this.projectName = projectName;
		this.sourceDir = sourceDir;
		this.outputDir = outputDir;
		this.manualMeasureFile = manualMeasureFile;
		this.editor = editor;
	}

	/** {@inheritDoc} */
	@Override
	public void run(IProgressMonitor monitor) throws InterruptedException {
		final String blockFilename = getBlock();
		if (blockFilename == null) {
			throw new InterruptedException("ConQAT block not found.");
		}

		final File propertiesFile = createPropertiesFile();
		if (propertiesFile == null) {
			throw new InterruptedException(
					"File with launch properties not found.");
		}

		runConQAT(blockFilename, propertiesFile, monitor);
	}

	/** Get the block to perform the evaluation. */
	private String getBlock() {
		BundleLocationOrganizer organizer = new BundleLocationOrganizer();
		for (File file : organizer.getBundleLocations()) {
			if ("edu.tum.cs.conqat.quamoco".equals(file.getName())) {
				return new File(file, "blocks/editor/" + blockName + ".cqb")
						.getAbsolutePath();
			}
		}
		return null;
	}

	/** Create the properties file with the launch parameters. */
	private File createPropertiesFile() {
		File modelFile = URIUtils.getJavaFile(model.eResource().getURI());
		try {
			FileSystemUtils.ensureDirectoryExists(new File(outputDir));
		} catch (IOException e) {
			LoggingUtils.error(QmEditorPlugin.getPlugin(),
					"Result directory could not be created", e);
			return null;
		}
		final File propertiesFile = new File(outputDir, "launch.properties");

		StringBuffer properties = new StringBuffer();
		properties.append("manual-measure.file=" + manualMeasureFile + "\n");
		properties.append("quality-model.file=" + modelFile.getAbsolutePath()
				+ "\n");
		properties.append("project.name=" + projectName + "\n");
		properties.append("source.dir=" + sourceDir + "\n");
		properties.append("output.dir=" + outputDir + "\n");

		addProperties(properties);

		try {
			FileSystemUtils.writeFile(propertiesFile, properties.toString());
		} catch (IOException e) {
			LoggingUtils.error(QmEditorPlugin.getPlugin(),
					"Launch properties file cound not be created", e);
			return null;
		}
		return propertiesFile;
	}

	/** Add additional properties required for the execution. */
	protected void addProperties(
			@SuppressWarnings("unused") StringBuffer properties) {
		// to be overwritten by sub classes
	}

	/** Execute ConQAT on the block with the launch properties. */
	private void runConQAT(final String blockFilename,
			final File propertiesFile, IProgressMonitor monitor)
			throws InterruptedException {
		final IExecutionListener listener = new ProgressMonitorExecutionListener(
				monitor);
		ExecutionProgressManager.getInstance().addProgressListener(listener);
		try {
			ConQATExecutionExitRunnable exitAction = new ConQATExecutionExitRunnable(
					monitor, listener);
			ConQATLaunchUtils.launchConQAT(blockFilename,
					propertiesFile.getAbsolutePath(),
					EConQATPreferences.CONQAT_VMARGS.getStringValue(),
					exitAction, true);
			synchronized (listener) {
				while (!done && !monitor.isCanceled()) {
					listener.wait(100);
				}
			}
			if (monitor.isCanceled()) {
				progress.kill();
			}
		} catch (CoreException e) {
			LoggingUtils.error(QmEditorPlugin.getPlugin(),
					"ConQAT could not be launched", e);
		}
	}

	/**
	 * The runnable that is executed when the clone detection execution is
	 * finished.
	 */
	private class ConQATExecutionExitRunnable implements Runnable {

		/** The progress monitor. */
		private final IProgressMonitor monitor;

		/** The listener that listens to the execution. */
		private final IExecutionListener listener;

		/** Constructor. */
		public ConQATExecutionExitRunnable(IProgressMonitor monitor,
				IExecutionListener listener) {
			this.monitor = monitor;
			this.listener = listener;
		}

		/** {@inheritDoc} */
		@Override
		public void run() {
			monitor.done();
			if (!monitor.isCanceled()) {
				openReportInBrowser();
				loadResultFile();
			}
			ExecutionProgressManager.getInstance().removeProgressListener(
					listener);
			ConQATRunnableBase.this.done = true;
			synchronized (listener) {
				listener.notify();
			}
		}

		/** Load the result file directly in the editor. */
		private void loadResultFile() {
			try {
				File sourceFile = new File(outputDir, "qm-result/result_"
						+ projectName + ".qmr");
				QualityModelResult result = ResourceUtils.loadElement(URIUtils
						.getURI(sourceFile));
				Date date = result.getDate();
				DateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmm");

				URI modelURI = model.eResource().getURI();
				IFile modelFile = URIUtils.getFile(modelURI);
				IFile resultFile = modelFile.getParent().getFile(
						new Path("result_" + projectName + "_"
								+ formatter.format(date) + ".qmr"));
				URI targetURI = URIUtils.getURI(resultFile);
				File targetFile = URIUtils.getJavaFile(targetURI);
				FileSystemUtils.copyFile(sourceFile, targetFile);

				URIUtils.getFile(modelURI).getParent()
						.refreshLocal(1, new NullProgressMonitor());
				Resource resource = model.eResource().getResourceSet()
						.getResource(targetURI, true);
				EcoreUtil.resolveAll(resource);
				QualityModelResult model = (QualityModelResult) resource
						.getContents().get(0);

				ShowResultTreeMapAction.showResultTreeMap(model, editor);
			} catch (IOException e) {
				LoggingUtils.error(QmEditorPlugin.getPlugin(),
						"Result could not be copied", e);
			} catch (CoreException e) {
				LoggingUtils.error(QmEditorPlugin.getPlugin(),
						"Result could not be refreshed", e);
			}
		}

		/** Open the quality report directly in the browser. */
		private void openReportInBrowser() {
			File file = new File(outputDir, "index.html");
			IFileStore fileStore = EFS.getLocalFileSystem().getStore(
					file.toURI());
			IWorkbenchPage page = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();

			try {
				IDE.openEditorOnFileStore(page, fileStore);
			} catch (PartInitException e) {
				LoggingUtils.error(QmEditorPlugin.getPlugin(),
						"Report could not be opened", e);
			}
		}
	}

	/**
	 * {@link IExecutionListener} that shows the progress on a
	 * {@link IProgressMonitor}.
	 */
	private class ProgressMonitorExecutionListener implements
			IExecutionListener {

		/** Monitor to display progress. */
		private final IProgressMonitor monitor;

		/** Number of finished processors. */
		private int finished = 0;

		/** Constructor. */
		public ProgressMonitorExecutionListener(IProgressMonitor monitor) {
			this.monitor = monitor;
		}

		/** {@inheritDoc} */
		@Override
		public void progressUpdated(ExecutionProgress instance) {
			int finished = instance.getFinishedProcessors();
			if (finished > this.finished) {
				monitor.setTaskName(finished + "/"
						+ instance.getTotalProcessors() + ": "
						+ instance.getCurrentProcessor());
				monitor.worked(finished - this.finished);
				this.finished = finished;
			}
		}

		/** {@inheritDoc} */
		@Override
		public void progressStarted(ExecutionProgress instance) {
			monitor.beginTask(instance.getBlockName(),
					instance.getTotalProcessors());
		}

		/** {@inheritDoc} */
		@Override
		public void progressMicroUpdated(ExecutionProgress instance) {
			// not required
		}

		/** {@inheritDoc} */
		@Override
		public void progressAdded(ExecutionProgress instance) {
			ConQATRunnableBase.this.progress = instance;
			if (monitor.isCanceled()) {
				progress.kill();
			}
		}
	}
}
