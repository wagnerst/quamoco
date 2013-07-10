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
   $Id: GeneratorSelectionWizardPage.java 4974 2012-02-17 09:34:10Z lochmann $            
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

import java.util.Arrays;

import org.conqat.ide.commons.ui.selection.SelectionUtils;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import de.quamoco.qm.guideline.extension.GeneratorExtension;
import de.quamoco.qm.guideline.extension.GeneratorExtensionUtil;
import de.quamoco.qm.guideline.extension.IGenerator;

/**
 * 
 * The {@link WizardPage} for selecting the guideline generator.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: EFBC2A3A7FDF44882547178AEB5C6A40
 */
public class GeneratorSelectionWizardPage extends WizardPage {

	/**
	 * Calls the constructor of the superclass.
	 */
	public GeneratorSelectionWizardPage() {
		super("Guideline Generator");
	}

	/** {@inheritDoc} */
	public void createControl(Composite parent) {
		setPageComplete(false);
		setTitle("Guideline Generator");
		setDescription("Please choose the guideline generator to be used.");
		Composite composite = new Composite(parent, SWT.NONE);
		FillLayout layout = new FillLayout();
		composite.setLayout(layout);
		Table table = new Table(composite, SWT.V_SCROLL | SWT.H_SCROLL
				| SWT.BORDER);
		createTableViewer(table);
		setControl(composite);
	}

	/**
	 * Creates the table viewer displaying the choices of generators in
	 * alphabetical order.
	 */
	private void createTableViewer(Table table) {
		TableViewer viewer = new TableViewer(table);
		viewer.setLabelProvider(new LabelProvider() {

			/** {@inheritDoc} */
			@Override
			public String getText(Object object) {
				if (object instanceof GeneratorExtension) {
					return ((GeneratorExtension) object).getLabel();
				}
				return null;
			}

		});
		Object[] extensions = GeneratorExtensionUtil.getExtensions().toArray();
		Arrays.sort(extensions);
		viewer.add(extensions);
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				GeneratorExtension extension = SelectionUtils
						.checkAndPickFirst(event.getSelection(),
								GeneratorExtension.class);
				IGenerator generator = extension.getGenerator();
				((GuidelineWizard) getWizard()).setGenerator(generator);
				setPageComplete(true);
			}

		});
	}
}
