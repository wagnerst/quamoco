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
 | edu.tum.cs.conqat.inspection.cm.editor
 |                                                                       |
   $Id: ChecklistEvaluationForm.java 4974 2012-02-17 09:34:10Z lochmann $            
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

import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.ide.IDE;

import edu.tum.cs.conqat.inspection.cm.Checklist;
import edu.tum.cs.conqat.inspection.cm.CmPackage;
import edu.tum.cs.conqat.inspection.cm.InspectionEvaluation;
import edu.tum.cs.conqat.inspection.cm.InspectionItem;
import edu.tum.cs.conqat.inspection.cm.InspectionMeasure;
import edu.tum.cs.conqat.inspection.cm.Sample;

/**
 * This class creates the checklist form in which the {@link Checklist}s will be
 * displayed for evaluation.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: B0AF8C9C8243EDCAE0CCD17055BC7248
 */
public class ChecklistEvaluationForm {

	/**
	 * The {@link Checklist} to be displayed.
	 */
	Checklist checklist;

	/**
	 * The parent multi page editor.
	 */
	private final CustomCmEditor editor;

	/**
	 * The parent composite.
	 */
	private final Composite parent;

	/**
	 * The {@link FormToolkit}.
	 */
	private static FormToolkit toolkit;

	/** ID for an inspection marker. */
	private final String INSPECTION_MARKER_ID = "INSPECTION_MARKER";

	/** Constructor. */
	public ChecklistEvaluationForm(CustomCmEditor editor, Checklist checklist,
			Composite parent) {
		this.editor = editor;
		this.checklist = checklist;
		this.parent = parent;
	}

	/**
	 * Creates the form to fill out checklists.
	 */
	public ScrolledForm create() {
		toolkit = new FormToolkit(parent.getDisplay());
		ScrolledForm form = toolkit.createScrolledForm(parent);
		form.setText("Inspector: " + checklist.getInspector());
		form.getBody().setLayout(new TableWrapLayout());

		// one expandable section per item
		for (InspectionItem item : checklist.getInspectionItems()) {
			createItemSection(form, item);
		}
		return form;
	}

	/**
	 * Creates a section for the given {@link InspectionItem}.
	 */
	private void createItemSection(ScrolledForm form, InspectionItem item) {
		Section section = toolkit.createSection(form.getBody(),
				ExpandableComposite.TITLE_BAR | ExpandableComposite.EXPANDED
						| ExpandableComposite.TWISTIE);
		String measureNames = "";
		List<InspectionMeasure> measures = item.getInspectionMeasures();
		for (InspectionMeasure measure : measures) {
			if (measures.indexOf(measure) != measures.size() - 1) {
				measureNames += measure.getMeasureName() + ", ";
			} else {
				measureNames += measure.getMeasureName();
			}
		}
		section.setText(measureNames);
		section.setLayout(new TableWrapLayout());
		TableWrapData data = new TableWrapData();
		data.grabHorizontal = true;
		data.align = TableWrapData.FILL;
		section.setLayoutData(data);

		Composite sectionClient = createItemSectionClient(item, section);
		section.setClient(sectionClient);
	}

	/**
	 * Creates the section client for the {@link InspectionItem} section.
	 */
	private Composite createItemSectionClient(InspectionItem item,
			Section section) {
		TableWrapData data;
		Composite sectionClient = toolkit.createComposite(section);
		TableWrapLayout wrapLayout = new TableWrapLayout();
		wrapLayout.horizontalSpacing = 20;
		wrapLayout.verticalSpacing = 0;
		wrapLayout.numColumns = 2;
		sectionClient.setLayout(wrapLayout);

		Label itemsLabel = toolkit.createLabel(sectionClient,
				"Items to be inspected\n\n");
		data = new TableWrapData();
		data.valign = TableWrapData.MIDDLE;
		itemsLabel.setLayoutData(data);

		Label valuesLabel = toolkit.createLabel(sectionClient,
				"Value of each item (1 = very good, 6 = poor)\n\n");
		data = new TableWrapData();
		data.valign = TableWrapData.MIDDLE;
		valuesLabel.setLayoutData(data);

		for (Sample sample : item.getSamples()) {
			createSampleRow(sectionClient, sample);
		}
		return sectionClient;
	}

	/**
	 * Creates one row for a {@link Sample}.
	 */
	private void createSampleRow(Composite sectionClient, Sample sample) {
		createSampleComposite(sectionClient, sample);

		List<InspectionMeasure> measures = sample.getInspectionItem()
				.getInspectionMeasures();
		List<InspectionEvaluation> evaluations = sample.getInspectionItem()
				.getInspectionEvaluations();
		if (measures.size() != 1) {
			Composite evaluationComposite = toolkit
					.createComposite(sectionClient);
			evaluationComposite.setLayout(new TableWrapLayout());
			for (InspectionMeasure measure : measures) {
				Hyperlink measureLink = toolkit
						.createHyperlink(evaluationComposite, measure
								.getMeasureName(), SWT.NONE);
				measureLink.setUnderlined(false);
				measureLink.addHyperlinkListener(new HyperlinkAdapter() {

					@Override
					public void linkActivated(HyperlinkEvent e) {
						editor.switchActivePage(1);
						List<Section> measureSections = editor
								.getMeasureInfoForm().getSections();
						for (Section section : measureSections) {
							if (section.getText().equals(e.getLabel())) {
								section.getClient().setFocus();
								section.setExpanded(true);
								break;
							}
						}
					}

				});
				for (InspectionEvaluation evaluation : evaluations) {
					if (evaluation.getSample().equals(sample)
							&& evaluation.getInspectionMeasure()
									.equals(measure)) {
						createButtonComposite(evaluationComposite, evaluation);
					}
				}
			}
		} else {
			for (InspectionEvaluation evaluation : evaluations) {
				if (evaluation.getSample().equals(sample)) {
					createButtonComposite(sectionClient, evaluation);
				}
			}
		}
	}

	/**
	 * Creates the {@link Composite} containing the location of a {@link Sample}
	 * as hyperlink and its name.
	 */
	private void createSampleComposite(Composite sectionClient, Sample sample) {
		Composite sampleComposite = toolkit.createComposite(sectionClient);
		sampleComposite.setLayout(new TableWrapLayout());
		Hyperlink samplePath = toolkit.createHyperlink(sampleComposite, sample
				.getPackagePath()
				+ ":\n", SWT.NONE);
		samplePath.setData(sample);
		samplePath.addHyperlinkListener(new HyperlinkAdapter() {

			@Override
			public void linkActivated(HyperlinkEvent event) {
				Hyperlink hyperlink = (Hyperlink) event.getSource();
				try {
					openJavaFile((Sample) hyperlink.getData());
				} catch (CoreException e) {
					MessageDialog.openError(parent.getShell(),
							"Error opening file",
							"The file could not be opened:\n" + e.getMessage());
				}
			}

		});
		@SuppressWarnings("unused")
		Label sampleLabel = toolkit.createLabel(sampleComposite, sample
				.getName()
				+ "\n\n");
		TableWrapData data = new TableWrapData(TableWrapData.LEFT,
				TableWrapData.TOP);
		data.indent = 5;
		data.grabHorizontal = false;
		data.grabVertical = false;
		sampleComposite.setLayoutData(data);
	}

	/**
	 * Opens the file containing given {@link Sample}. If the file is contained
	 * in the workspace, a marker will be set on the appropriate sample
	 * location. If the file is not found in the workspace, it will be opened as
	 * external file, if possible.
	 */
	private void openJavaFile(Sample sample) throws CoreException {
		IType type = findTypeInWorkspace(sample.getPackagePath());
		if (type != null) {
			IResource resource = type.getUnderlyingResource();
			if (resource != null && resource instanceof IFile) {
				IFile file = (IFile) resource;
				// find and delete old inspection markers
				IMarker[] markers = resource.findMarkers(IMarker.BOOKMARK,
						false, IResource.DEPTH_INFINITE);
				for (IMarker oldMarker : markers) {
					if (oldMarker.getAttribute(IMarker.SOURCE_ID).equals(
							INSPECTION_MARKER_ID)) {
						oldMarker.delete();
					}
				}
				// set new marker
				IMarker marker = file.createMarker(IMarker.TASK);
				marker.setAttribute(IMarker.SOURCE_ID, INSPECTION_MARKER_ID);
				marker.setAttribute(IDE.EDITOR_ID_ATTR,
						"org.eclipse.ui.JavaEditor");
				marker.setAttribute(IMarker.TRANSIENT, true);
				int sourceStart = sample.getSourceStart();
				int sourceEnd = sample.getSourceEnd();
				if (sourceStart != -1 && sourceEnd != -1) {
					marker.setAttribute(IMarker.CHAR_START, sourceStart);
					marker.setAttribute(IMarker.CHAR_END, sourceEnd);
				}
				int lineNumber = sample.getLineNumber();
				if (lineNumber != -1) {
					marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
				}
				IDE.openEditor(editor.getSite().getPage(), marker);
			}
		} else {
			String sourcePath = sample.getSourcePath();
			String absolutePath = sourcePath.substring(0, sourcePath
					.indexOf(".java") + 5);
			openExternalFile(absolutePath);

		}
	}

	/**
	 * Finds the {@link IType} in the workspace corresponding to the given
	 * packagePath, or null if none found.
	 */
	private IType findTypeInWorkspace(String packagePath) throws CoreException {
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot()
				.getProjects();
		for (IProject project : projects) {
			if (!project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
				continue;
			}
			IJavaProject javaProject = JavaCore.create(project);
			int index = packagePath.length();
			while (index != -1) {
				packagePath = packagePath.substring(0, index);
				IType type = javaProject.findType(packagePath);
				if (type != null) {
					return type;
				}
				index = packagePath.lastIndexOf(".");
			}
		}
		return null;
	}

	/**
	 * Opens a file outside the workspace.
	 */
	private void openExternalFile(Object href) {
		String path = href.toString();
		IFileStore fileStore = EFS.getLocalFileSystem()
				.getStore(new Path(path));
		if (fileStore.fetchInfo().exists()) {
			IWorkbenchPage page = editor.getSite().getPage();
			try {
				IDE.openEditorOnFileStore(page, fileStore);
			} catch (PartInitException e) {
				MessageDialog.openError(parent.getShell(),
						"Error opening file", "The file " + href.toString()
								+ " could not be opened");
			}
		} else {
			MessageDialog.openError(parent.getShell(), "Error opening file",
					"The file " + href.toString() + " does not exist");
		}
	}

	/**
	 * Creates the button composite for selecting a value between 1 and 5.
	 */
	private void createButtonComposite(Composite buttonCompositeParent,
			final InspectionEvaluation evaluation) {
		Composite buttonComposite = toolkit
				.createComposite(buttonCompositeParent);
		TableWrapData data = new TableWrapData(TableWrapData.FILL,
				TableWrapData.TOP);
		data.indent = 5;
		buttonComposite.setLayoutData(data);
		buttonComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
		for (int i = 1; i <= 6; i++) {
			Button button = toolkit.createButton(buttonComposite, String
					.valueOf(i), SWT.RADIO);
			button.setData(new Integer(i));
			button.addSelectionListener(new SelectionAdapter() {
				/** {@inheritDoc} */
				@Override
				public void widgetSelected(SelectionEvent e) {
					Integer value = (Integer) e.widget.getData();
					EditingDomain editingDomain = editor.getEditingDomain();
					SetCommand setCommand = new SetCommand(editingDomain,
							evaluation, CmPackage.eINSTANCE
									.getInspectionEvaluation_Value(), value);
					editingDomain.getCommandStack().execute(setCommand);
				}
			});
			if (evaluation.getValue() == i) {
				button.setSelection(true);
			} else {
				button.setSelection(false);
			}
		}
		// Create "undefined" radio button
		Button button = toolkit.createButton(buttonComposite, "undefined",
				SWT.RADIO);
		button.setData(new Integer(0));
		button.addSelectionListener(new SelectionAdapter() {
			/** {@inheritDoc} */
			@Override
			public void widgetSelected(SelectionEvent e) {
				EditingDomain editingDomain = editor.getEditingDomain();
				Integer value = (Integer) e.widget.getData();
				SetCommand setCommand = new SetCommand(editingDomain,
						evaluation, CmPackage.eINSTANCE
								.getInspectionEvaluation_Value(), value);
				editingDomain.getCommandStack().execute(setCommand);
			}
		});
		if (evaluation.getValue() == 0) {
			button.setSelection(true);
		} else {
			button.setSelection(false);
		}
	}

	/**
	 * Disposes the {@link FormToolkit}.
	 */
	public void dispose() {
		toolkit.dispose();
	}

}
