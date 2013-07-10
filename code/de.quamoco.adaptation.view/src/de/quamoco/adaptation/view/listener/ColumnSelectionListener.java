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

package de.quamoco.adaptation.view.listener;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TreeColumn;

import de.quamoco.adaptation.view.sorter.AdaptationViewerSorter;

/**
 * If a column is select, change search field and direction
 * @author Franz Becker
 */
public class ColumnSelectionListener extends SelectionAdapter {

	/** The tree viewer of the view */
	final private TreeViewer viewer;
	
	/** The sorter */
	final private AdaptationViewerSorter sorter;
	
	/** The tree column this listener is attached to. */
	final private TreeColumn column;
	
	/** The column index of the column this listener is attached to */
	final private int columnIndex;
	
	/**
	 * Initializes the fields and retrieves the sorter automatically.
	 * It is assumed that the sorter is of type {@link AdaptationTaskViewerSorter}.
	 * @param viewer the viewer that shall be sorted
	 * @param column the column this listener is attached to
	 * @param columnIndex the index of the column
	 */
	public ColumnSelectionListener(TreeViewer viewer, TreeColumn column, int columnIndex) {
		this.viewer = viewer;
		this.sorter = (AdaptationViewerSorter) viewer.getSorter();
		this.column = column;
		this.columnIndex = columnIndex;		
	}
	
	/**
	 * Sets the column by which shall be sorted, the search direction,
	 * the column images and then refreshes the viewer.
	 */
	@Override
	public void widgetSelected(SelectionEvent e) {
		sorter.setColumn(columnIndex);		
		int dir = viewer.getTree().getSortDirection();
		if (viewer.getTree().getSortColumn() == column) {
			dir = (dir == SWT.UP) ? SWT.DOWN : SWT.UP;
		} else {
			dir = SWT.UP;	// default: ascending
		}
		viewer.getTree().setSortDirection(dir);
		viewer.getTree().setSortColumn(column);
		viewer.refresh();
	}

}
