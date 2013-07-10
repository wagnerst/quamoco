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
   $Id: MeasureInfoForm.java 4974 2012-02-17 09:34:10Z lochmann $            
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

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.internal.browser.WorkbenchBrowserSupport;
import org.eclipse.ui.part.FileEditorInput;

import edu.tum.cs.conqat.inspection.cm.Checklist;
import edu.tum.cs.conqat.inspection.cm.Documentation;
import edu.tum.cs.conqat.inspection.cm.InspectionMeasure;

/**
 * 
 * This class creates the measure info tab in the checklist editor.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: EE6963068B91522B6CAE7CE765EB064A
 */
public class MeasureInfoForm {

	/**
	 * The parent multi page editor.
	 */
	private final CustomCmEditor editor;

	/**
	 * The {@link Checklist} to be displayed.
	 */
	private final Checklist checklist;

	/**
	 * The parent composite.
	 */
	private final Composite parent;

	/**
	 * The {@link FormToolkit}.
	 */
	private static FormToolkit toolkit;

	/**
	 * List of all measure sections on the Measure Info Tab.
	 */
	private final List<Section> measureSections = new ArrayList<Section>();

	/** Constructor. */
	public MeasureInfoForm(CustomCmEditor editor, Checklist checklist,
			Composite parent) {
		this.editor = editor;
		this.checklist = checklist;
		this.parent = parent;
	}

	/**
	 * Gets the measure info sections.
	 */
	public List<Section> getSections() {
		return measureSections;
	}

	/**
	 * Creates the form to display information about the measurements to be
	 * inspected and links for additional information.
	 */
	public ScrolledForm create() {
		toolkit = new FormToolkit(parent.getDisplay());
		ScrolledForm form = toolkit.createScrolledForm(parent);

		List<InspectionMeasure> measures = checklist.getInspectionMeasures();
		String measureNames = "";
		for (InspectionMeasure measure : measures) {
			if (measures.indexOf(measure) != measures.size() - 1) {
				measureNames += measure.getMeasureName() + ", ";
			} else {
				measureNames += measure.getMeasureName();
			}
		}
		form.setText(measureNames);
		form.getBody().setLayout(new TableWrapLayout());

		Section section = toolkit.createSection(form.getBody(),
				ExpandableComposite.FOCUS_TITLE);
		final String qmFileName = checklist.getQmFileName();
		section.setText("Quality Model: " + qmFileName);
		section.setLayout(new TableWrapLayout());
		TableWrapData data = new TableWrapData();
		data.grabHorizontal = true;
		data.align = TableWrapData.FILL;
		section.setLayoutData(data);
		section.addExpansionListener(new ExpansionAdapter() {

			/** {@inheritDoc} */
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				openFile(qmFileName);
			}

		});

		// one expandable section per item
		for (InspectionMeasure measure : checklist.getInspectionMeasures()) {
			createMeasureSection(form, measure);
		}
		return form;
	}

	/**
	 * Opens the file in the Eclipse workspace in the default editor.
	 */
	private void openFile(String fileName) {
		IEditorRegistry editorRegistry = PlatformUI.getWorkbench()
				.getEditorRegistry();
		IEditorDescriptor editorDescriptor = editorRegistry
				.getDefaultEditor(fileName);
		String editorID;
		if (editorDescriptor == null) {
			if (editorRegistry.isSystemExternalEditorAvailable(fileName)) {
				editorID = editorRegistry.findEditor(
						IEditorRegistry.SYSTEM_EXTERNAL_EDITOR_ID).getId();
			} else {
				MessageDialog.openError(parent.getShell(),
						"Could not open file " + fileName,
						"No suitable editor found");
				return;
			}
		} else {
			editorID = editorDescriptor.getId();
		}
		IFile file = editor.getProject().getFile(fileName);
		FileEditorInput input = new FileEditorInput(file);
		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		try {
			window.getActivePage().openEditor(input, editorID, true);
		} catch (PartInitException e) {
			MessageDialog.openError(parent.getShell(), "Error opening file "
					+ fileName, e.getMessage());
		}
	}

	/**
	 * Creates one section per measure.
	 */
	private void createMeasureSection(ScrolledForm form,
			InspectionMeasure measure) {
		Section section = toolkit.createSection(form.getBody(),
				ExpandableComposite.TITLE_BAR | ExpandableComposite.EXPANDED
						| ExpandableComposite.TWISTIE);

		section.setText(measure.getMeasureName());
		section.setLayout(new TableWrapLayout());
		TableWrapData data = new TableWrapData();
		data.grabHorizontal = true;
		data.align = TableWrapData.FILL;
		section.setLayoutData(data);

		Composite sectionClient = createMeasureSectionClient(measure, section);
		section.setClient(sectionClient);

		measureSections.add(section);
	}

	/**
	 * Creates the section client for the {@link InspectionMeasure} section.
	 */
	private Composite createMeasureSectionClient(InspectionMeasure measure,
			Section section) {
		TableWrapData data;
		Composite sectionClient = toolkit.createComposite(section);
		TableWrapLayout wrapLayout = new TableWrapLayout();
		wrapLayout.horizontalSpacing = 20;
		wrapLayout.verticalSpacing = 0;
		wrapLayout.verticalSpacing = 10;
		wrapLayout.numColumns = 2;
		sectionClient.setLayout(wrapLayout);

		// Measure Info
		Label measureLabel = toolkit.createLabel(sectionClient, "Measure Info");
		data = new TableWrapData();
		data.valign = TableWrapData.MIDDLE;
		measureLabel.setLayoutData(data);

		Label measureInfoLabel = toolkit.createLabel(sectionClient, measure
				.getMeasureInfo());
		data = new TableWrapData();
		data.valign = TableWrapData.MIDDLE;
		measureInfoLabel.setLayoutData(data);

		// Instrument Info
		Label instrumentLabel = toolkit.createLabel(sectionClient,
				"Manual Instrument Info");
		data = new TableWrapData();
		data.valign = TableWrapData.MIDDLE;
		instrumentLabel.setLayoutData(data);

		Label instrumentInfoLabel = toolkit.createLabel(sectionClient, measure
				.getInstrumentInfo());
		data = new TableWrapData();
		data.valign = TableWrapData.MIDDLE;
		instrumentInfoLabel.setLayoutData(data);

		List<Documentation> documentations = measure.getDocumentations();
		if (documentations.isEmpty()) {
			return sectionClient;
		}
		Label linkLabel = toolkit.createLabel(sectionClient, "Links:");
		data = new TableWrapData();
		data.colspan = 2;
		linkLabel.setLayoutData(data);

		Composite documentationComposite = toolkit
				.createComposite(sectionClient);
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 2;
		documentationComposite.setLayout(layout);
		data = new TableWrapData();
		data.colspan = 2;
		documentationComposite.setLayoutData(data);
		for (Documentation documentation : documentations) {
			@SuppressWarnings("unused")
			Label nameLabel = toolkit.createLabel(documentationComposite,
					documentation.getName());
			String hyperlinkText = documentation.getPath();
			if (documentation.getType().equals("file")) {
				hyperlinkText = (new File(hyperlinkText)).getName();
			}
			Hyperlink pathHyperlink = toolkit.createHyperlink(
					documentationComposite, hyperlinkText, SWT.WRAP);
			pathHyperlink.setHref(documentation.getPath());
			pathHyperlink.setData(documentation.getType());
			pathHyperlink.addHyperlinkListener(new HyperlinkAdapter() {

				@Override
				public void linkActivated(HyperlinkEvent event) {
					Hyperlink hyperlink = (Hyperlink) event.getSource();
					if (hyperlink.getData().equals("url")) {
						try {
							URL url = new URL(hyperlink.getHref().toString());
							int style = IWorkbenchBrowserSupport.AS_EDITOR
									| IWorkbenchBrowserSupport.LOCATION_BAR
									| IWorkbenchBrowserSupport.STATUS;
							IWebBrowser browser = WorkbenchBrowserSupport
									.getInstance().createBrowser(style,
											"org.eclipse.ui.browser.ie",
											"Internet Explorer",
											"Internet Explorer");
							browser.openURL(url);
						} catch (Exception e) {
							MessageDialog.openError(parent.getShell(),
									"Error opening url", e.getMessage());
						}
					} else if (hyperlink.getData().equals("file")) {
						openFile(hyperlink.getText());
					}
				}

			});
		}
		return sectionClient;
	}

	/**
	 * Disposes the {@link FormToolkit};
	 */
	public void dispose() {
		toolkit.dispose();
	}
}
