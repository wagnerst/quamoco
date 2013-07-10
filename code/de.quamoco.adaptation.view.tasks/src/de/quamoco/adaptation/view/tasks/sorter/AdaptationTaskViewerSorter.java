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

package de.quamoco.adaptation.view.tasks.sorter;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;

import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.view.sorter.AdaptationViewerSorter;
import de.quamoco.adaptation.view.tasks.AdaptationTasksView;
import de.quamoco.adaptation.view.tasks.util.ViewConstants;
import de.quamoco.adaptation.view.tasks.util.ViewConstants.TableColumns;

/**
 * Implements the sorter for the {@link AdaptationTasksView}
 * @author Franz becker
 */
// TODO
public class AdaptationTaskViewerSorter extends AdaptationViewerSorter {

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
		if (propertyIndex == TableColumns.COMPLETED.INDEX) {
			// completed column
			return flipIfDescending(compareCompleted((AdaptationTask) e1, (AdaptationTask) e2));
		} else {				
			// rest => compare based on label
			return flipIfDescending(compareBasedOnLabel(viewer, e1, e2));
		}		
	}

	/**
	 * Compares the two passed markers according to their {@link IMarker#DONE}
	 * status. If a marker is marked as done, there is an additional sorting
	 * according to its {@link ViewConstants#COMPLETION_TIMESTAMP_ATTRIBUTE} which
	 * indicates when it was completed or set uncompleted again. 
	 * This is useful to show the user first the markers whose status were
	 * recently changed so that he is able to easily revoke this change.
	 * @param m1 first marker
	 * @param m2 second marker
	 * @return an int indicating the less or equals relation
	 */
	private int compareCompleted(AdaptationTask m1, AdaptationTask m2) {
		Boolean m1done = m1.isCompleted();
		Boolean m2done = m2.isCompleted();
		
//		/*
//		 * further comparison has to be performed
//		 * iff m1done and m2done are both true or both are false
//		 * => sort them by their completion timestamp.
//		 */
//		if ((m1done && m2done) || (!m1done && !m2done)) {
//			Date m1Date = MarkerUtil.getCompletionTimestamp(m1);
//			Date m2Date = MarkerUtil.getCompletionTimestamp(m2);		
//			
//			if ((m1Date != null) && (m2Date != null)) {
//				// both are having dates => use built-in comparison
//				// if both are done different order than if both are not done
//				if (m1done) {	// sufficient to check only one value
//					return m2Date.compareTo(m1Date);
//				} else {
//					return m1Date.compareTo(m2Date);
//				}				 
//			} // else is a failure - somehow date was not set, do nothing
//			
//		}
		// else return compareTo result
		return m1done.compareTo(m2done);
		// TODO
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
		String label1 = labelProvider.getColumnText(e1, propertyIndex);
		String label2 = labelProvider.getColumnText(e2, propertyIndex);
		return label1.compareTo(label2);
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
