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

package de.quamoco.adaptation.view;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

import de.quamoco.adaptation.view.filter.ColumnPatternFilter;
import de.quamoco.adaptation.view.sorter.AdaptationViewerSorter;

/**
 * Extends {@link FilteredTree} and configures it for the needs of
 * the adaptation views. The main method here is {@link #doCreateTreeViewer(Composite, int)}
 * which is overridden and creates a {@link SelfRefreshingTreeViewer} and the desired columns.
 * @author Franz Becker
 */
public abstract class AdaptationFilteredTree extends FilteredTree {

	/** The style of the tree view */
	private final static int style = SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION;
	
	/**
	 * Calls the super constructor with the defined {@link #style} and a new instance
	 * of {@link ColumnPatternFilter} as {@link PatternFilter}.
	 * @param parent
	 */
	public AdaptationFilteredTree(Composite parent) {
		super(parent, style, new ColumnPatternFilter(), true);
	}

	/**
	 * Creates a {@link SelfRefreshingTreeViewer} and then does the following: 
	 * <ul>
	 * <li>Add a sorter for the columns (before creation of columns!)</li>
	 * <li>Create the columns</li>
	 * <li>Configures the viewer</li>
	 * </ul>
	 */
	@Override
	protected TreeViewer doCreateTreeViewer(Composite parent, int style) {
		TreeViewer treeViewer = new SelfRefreshingTreeViewer(parent, style);
		
		// Add sorter for sorting functionality
		// It is IMPORTANT to set the sorter before columns are created
		// (will be used by ColumnSelectionListener!
		ViewerSorter sorter = getViewerSorter();
		if (sorter != null) {
			treeViewer.setSorter(sorter);
		}
		
		// Add Columns and show their headers and lines
		createColumns(treeViewer);
		treeViewer.getTree().setHeaderVisible(true);
		treeViewer.getTree().setLinesVisible(true);
		
		return treeViewer;
	}
	
	/**
	 * To be implemented by subclass.
	 * @return the viewer's sorter.
	 */
	protected abstract AdaptationViewerSorter getViewerSorter();

	/**
	 * To be implemented by subclass. Is supposed to create the necessary columns.
	 * @param treeViewer the tree viewer to which the columns shall be added.
	 */
	protected abstract void createColumns(TreeViewer treeViewer);
	
}
