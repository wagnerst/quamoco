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

package de.quamoco.adaptation.view.tasks;

import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import de.quamoco.adaptation.view.AdaptationFilteredTree;
import de.quamoco.adaptation.view.listener.ColumnSelectionListener;
import de.quamoco.adaptation.view.sorter.AdaptationViewerSorter;
import de.quamoco.adaptation.view.tasks.provider.CompletionColumnEditingSupport;
import de.quamoco.adaptation.view.tasks.sorter.AdaptationTaskViewerSorter;
import de.quamoco.adaptation.view.tasks.util.ViewConstants.TableColumns;

/**
 * Extends {@link AdaptationFilteredTree} and configures it for the {@link AdaptationTasksView}.
 * @author Franz Becker
 */
public class AdaptationTasksFilteredTree extends AdaptationFilteredTree {
	
	/**
	 * Calls the super constructor.
	 */
	public AdaptationTasksFilteredTree(Composite parent) {
		super(parent);
	}

	/**
	 * Creates a new {@link AdaptationTaskViewerSorter}.
	 */
	@Override
	protected AdaptationViewerSorter getViewerSorter() {
		return new AdaptationTaskViewerSorter();
	}	
	
	/**
	 * Creates the columns for the {@link AdaptationTasksFilteredTree}.
	 */
	@Override
	protected void createColumns(TreeViewer treeViewer) {
		// Get the title, tool tips and bounds from Constants.TableColumns
		String titles[] = TableColumns.getTitles();
		String tooltips[] = TableColumns.getToolTips();
		int bounds[] = TableColumns.getBounds();
		
		for (int i = 0; i < titles.length; i++) {
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.NONE);
			column.getColumn().setText(titles[i]);
			column.getColumn().setToolTipText(tooltips[i]);
			column.getColumn().setWidth(bounds[i]);
			column.getColumn().setResizable(true);
			column.getColumn().setMoveable(true);
			
			// Add selection listener for sorting
			ColumnSelectionListener columnSelectionListener = new ColumnSelectionListener(treeViewer, column.getColumn(), i);
			column.getColumn().addSelectionListener(columnSelectionListener);
			
			// If default sorting column mark it
			if (i == TableColumns.DEFAULT_SORT_COLUMN) {
				// Mark column as default sort column
				columnSelectionListener.widgetSelected(null);
			}
			
			// Add editing support for the completed column
			if (i == TableColumns.COMPLETED.INDEX) {
				EditingSupport editingSupport = new CompletionColumnEditingSupport(column.getViewer());
				column.setEditingSupport(editingSupport);
				Image completeColumnHeader = Activator.getImageDescriptor("icons/header_complete.gif").createImage();
				column.getColumn().setImage(completeColumnHeader);
			}
		}	
	}


}
