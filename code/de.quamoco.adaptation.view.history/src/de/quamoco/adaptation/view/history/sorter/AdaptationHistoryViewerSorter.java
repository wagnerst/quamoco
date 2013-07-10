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

package de.quamoco.adaptation.view.history.sorter;

import java.util.Date;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;

import de.quamoco.adaptation.model.status.AdaptationHistoryItem;
import de.quamoco.adaptation.view.history.AdaptationHistoryView;
import de.quamoco.adaptation.view.history.util.ViewConstants.TableColumns;
import de.quamoco.adaptation.view.sorter.AdaptationViewerSorter;

/**
 * Implements the sorter for the {@link AdaptationHistoryView}
 * @author Franz becker
 */
// TODO
public class AdaptationHistoryViewerSorter extends AdaptationViewerSorter {

	/** The index of the column which is currently used for sorting. 
	 *  Default = {@link TableColumns.DEFAULT_SORT_COLUMN} */
	private int propertyIndex = TableColumns.DEFAULT_SORT_COLUMN;
	
	/** Constant for ascending sort direction. */
	private static final int ASCENDING = 0;
	
	/** Constant for descending sort direction. */
	private static final int DESCENDING = 1;

	/** Current sort direction, default = {@link #ASCENDING} */
	private int direction = ASCENDING;

	/**
	 * Sets the column which shall be used for sorting and 
	 * toggles the search direction if the passed column is
	 * already the sort column.
	 * @param column the column which shall be used for sorting
	 */
	@Override
	public void setColumn(int column) {
		if (column == this.propertyIndex) {
			// Same column as last sort; toggle the direction
			direction = 1 - direction;
		} else {
			// New column; do an ascending sort
			this.propertyIndex = column;
			direction = ASCENDING;
		}
	}

	/**
	 * Implements the comparison, calls helper methods based on 
	 * case distinction.
	 */
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (propertyIndex == TableColumns.NO_DEFAULT_SORTING) {
			// default sorting shall not be applied => return zero
			return 0;
		}
		if (propertyIndex == TableColumns.TIMESTAMP.INDEX) {
			// completed column
			return flipIfDescending(compareTimestamp((AdaptationHistoryItem) e1, (AdaptationHistoryItem) e2));
		} else {				
			// rest => compare based on label
			return flipIfDescending(compareBasedOnLabel(viewer, e1, e2));
		}		
	}

	private int compareTimestamp(AdaptationHistoryItem e1, AdaptationHistoryItem e2) {
		Date d1 = e1.getTimestamp();
		Date d2 = e2.getTimestamp();
		return d1.compareTo(d2);
	}
	
	/**
	 * Compares the two objects based on their labels provided by the label provider
	 * that is attached to the passed viewer.
	 * @param viewer the viewer
	 * @param e1 first object
	 * @param e2 second object
	 * @return an int indicating the less or equals relation
	 */
	private int compareBasedOnLabel(Viewer viewer, Object e1, Object e2) {
		// sort by the label provided by the label provider
		ITableLabelProvider labelProvider = (ITableLabelProvider) ((StructuredViewer) viewer).getLabelProvider();
		String l1 = labelProvider.getColumnText(e1, propertyIndex);
		String l2 = labelProvider.getColumnText(e2, propertyIndex);
		if (l1 == null) {
			l1 = "";
		}
		if (l2 == null) {
			l2 = "";
		}
		return l1.compareTo(l2);
	}
	
	/**
	 * Flips the result if {@link #direction} is {@link #DESCENDING}
	 * @param value the sort result
	 * @return the sort result flipped if sort direction is {@link #DESCENDING}
	 */
	private int flipIfDescending(int value) {
		if (direction == DESCENDING) {
			return -value;
		}
		return value;
	}
}
