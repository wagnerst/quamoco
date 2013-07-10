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

package de.quamoco.adaptation.view.history;

import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import de.quamoco.adaptation.view.AdaptationFilteredTree;
import de.quamoco.adaptation.view.history.provider.JustificationColumnEditingSupport;
import de.quamoco.adaptation.view.history.sorter.AdaptationHistoryViewerSorter;
import de.quamoco.adaptation.view.history.util.ViewConstants.TableColumns;
import de.quamoco.adaptation.view.listener.ColumnSelectionListener;
import de.quamoco.adaptation.view.sorter.AdaptationViewerSorter;

/**
 * 
 * @author Franz Becker
 */
public class AdaptationHistoryFilteredTree extends AdaptationFilteredTree {

	public AdaptationHistoryFilteredTree(Composite parent) {
		super(parent);
	}

	@Override
	protected AdaptationViewerSorter getViewerSorter() {
		return new AdaptationHistoryViewerSorter();
	}
	
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
			
			if (i == TableColumns.JUSTIFICATION.INDEX) {
				EditingSupport editingSupport = new JustificationColumnEditingSupport(column.getViewer());
				column.setEditingSupport(editingSupport);
			}
		}		
	}
	
}
