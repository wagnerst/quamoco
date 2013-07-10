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

package de.quamoco.adaptation.wizard.util.elementsselection;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

import de.quamoco.adaptation.wizard.util.WizardState;

/**
 * Overrides the {@link #doCreateTreeViewer(Composite, int)} and creates a
 * {@link CustomTreeViewer} instead. Additionally provides a getter method for
 * it (to avoid casting each time the viewer ist needed).
 * @author Franz Becker
 * @levd.rating GREEN Hash: DA72F3D4831A76023A6BBA4DC507AAAD
 */
public class CustomFilteredTree extends FilteredTree {

	/** The style of the tree view */
	private final static int style = SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION;
	
	/** The state of the wizard */
	private WizardState wizardState;
	
	/**
	 * Calls the super constructor
	 */
	public CustomFilteredTree(Composite parent, PatternFilter patternFilter, WizardState wizardState) {
		super(parent, style, patternFilter, true);
		this.wizardState = wizardState;
		customInit(style, patternFilter);
	}
	
	/**
	 * Overrides the super method to delay initialization
	 */
	protected void init(int treeStyle, PatternFilter filter) {
		/*
		 * Has to be empty because this method is called
		 * by super constructor at a time where the instance
		 * variables are not set. But they are necessary
		 * for correct initialization.
		 */
	}
	
	/**
	 * Calls the super method
	 */
	protected void customInit(int treeStyle, PatternFilter filter) {
		super.init(treeStyle, filter);
	}

	/**
	 * Overrides the super method to create a {@link CustomTreeViewer}} instead.
	 */
	@Override
	protected TreeViewer doCreateTreeViewer(Composite parent, int style) {
		CustomTreeViewer newTreeViewer = new CustomTreeViewer(parent, style, wizardState);		
		return newTreeViewer;
	}
	
	/**
	 * @return the {@link CustomTreeViewer}
	 */
	public CustomTreeViewer getCustomTreeViewer() {
		return (CustomTreeViewer) getViewer();
	}
	
	/**
	 * @return true when the filtering functionality is active, false otherwise.
	 */
	public boolean isFiltering() {
		String filterString = getFilterString();
		return filterString != null && !filterString.isEmpty();
	}

}
