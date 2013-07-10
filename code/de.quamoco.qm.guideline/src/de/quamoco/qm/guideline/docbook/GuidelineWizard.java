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
   $Id: GuidelineWizard.java 4974 2012-02-17 09:34:10Z lochmann $            
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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.ui.PlatformUI;

import de.quamoco.qm.QualityModel;
import de.quamoco.qm.guideline.extension.IGenerator;
import de.quamoco.qm.presentation.QmEditorPlugin;
import de.quamoco.qm.provider.QmEditPlugin;

/**
 * The {@link Wizard} for generating a Guideline in a specific format to a
 * specific location.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: A020DBE0F35A48C51FF913335913D75F
 */
public class GuidelineWizard extends Wizard {

	/**
	 * The {@link ResourceSet} containing all {@link QualityModel}s available
	 * for export.
	 */
	private final ResourceSet resourceSet;

	/**
	 * The {@link QualityModel}s to be exported.
	 */
	private List<QualityModel> selectedQualityModels = new ArrayList<QualityModel>();

	/**
	 * The guideline generator to be used.
	 */
	private IGenerator generator;

	/**
	 * The format of the generated Guideline (pdf, htmlsingle, or html).
	 */
	private EGuidelineTargetFormat targetFormat;

	/**
	 * The path to the file where the guideline should be stored.
	 */
	private String targetPath;

	/**
	 * The constructor.
	 */
	public GuidelineWizard(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
		setWindowTitle("Guideline Generation");
	}

	/** {@inheritDoc} */
	@Override
	public void addPages() {
		WizardPage qualityModelSelectionPage = new QualityModelSelectionWizardPage();
		addPage(qualityModelSelectionPage);

		WizardPage generatorSelectionPage = new GeneratorSelectionWizardPage();
		addPage(generatorSelectionPage);

		WizardPage formatSelectionPage = new FormatSelectionWizardPage();
		addPage(formatSelectionPage);

		WizardPage pathSelectionPage = new PathSelectionWizardPage();
		addPage(pathSelectionPage);
	}

	/**
	 * {@inheritDoc} Checks whether targetFormat is consistent with targetPath
	 * file extension.
	 */
	@Override
	public boolean canFinish() {
		if (super.canFinish() && targetPath != null) {
			String extension = FileUtils.getFileExtension(targetPath);
			if (extension.equals(targetFormat.getFileExtension())) {
				return true;
			}
		}
		return false;

	}

	/** {@inheritDoc} */
	@Override
	public boolean performFinish() {
		try {
			getContainer().run(false, false, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {

					monitor.beginTask("Generating guideline...",
							IProgressMonitor.UNKNOWN);
					DocbookGuidelineGeneration docbookGuideline = new DocbookGuidelineGeneration(
							selectedQualityModels, generator, targetFormat,
							targetPath);
					docbookGuideline.generateGuideline();
					monitor.done();
				}
			});
		} catch (InvocationTargetException e) {
			QmEditorPlugin.INSTANCE.log(e);
			MessageDialog.openError(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell(), "Error",
					"The following error occurred during generation: "
							+ e.getCause().getMessage());
		} catch (InterruptedException e) {
			QmEditPlugin.INSTANCE.log(e);
		}
		return true;
	}

	/** Sets generator. */
	/* package */void setGenerator(IGenerator generator) {
		this.generator = generator;
	}

	/** Returns the target format. */
	/* package */EGuidelineTargetFormat getTargetFormat() {
		return targetFormat;
	}

	/** Sets the target format. */
	/* package */void setTargetFormat(EGuidelineTargetFormat targetFormat) {
		this.targetFormat = targetFormat;
	}

	/** Returns the target path. */
	/* package */String getTargetPath() {
		return targetPath;
	}

	/** Sets the target path. */
	/* package */void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	/** Returns resourceSet. */
	/* package */ResourceSet getResourceSet() {
		return resourceSet;
	}

	/** Sets selectedQualityModels. */
	/* package */void setSelectedQualityModels(
			List<QualityModel> selectedQualityModels) {
		this.selectedQualityModels = selectedQualityModels;
	}

	/** {@inheritDoc} */
	@Override
	public boolean needsProgressMonitor() {
		return true;
	}
}
