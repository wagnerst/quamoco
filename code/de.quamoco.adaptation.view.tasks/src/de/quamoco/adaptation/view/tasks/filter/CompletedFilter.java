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

package de.quamoco.adaptation.view.tasks.filter;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import de.quamoco.adaptation.model.status.AdaptationTask;

/**
 * Filters out all {@link AdaptationTask}s which are marked as completed,
 * if enabled. Default is false.
 * @author Franz Becker
 * @see AdaptationTask#isCompleted()
 */
public class CompletedFilter extends ViewerFilter {

	/** The structured viewer this filter is added to, used for calling refresh() */
	private final StructuredViewer structuredViewer;
	
	/** Whether or not the filter is enabled. */
	private boolean enabled = true;
	
	/**
	 * Initializes the fields.
	 * @param structuredViewer the structured viewer this filter is added to, used for calling refresh()
	 */
	public CompletedFilter(StructuredViewer structuredViewer) {
		this.structuredViewer = structuredViewer;		
	}
	
	/**
	 * Filters the input according to the result of {@link AdaptationTask#isCompleted()}.
	 */
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		// check only if filter enabled
		if (enabled && (element instanceof AdaptationTask)) {
			AdaptationTask task = (AdaptationTask) element;
			return !task.isCompleted();
		}
		// if filter is enabled return false, true otherwise
		return !enabled;			
	}

	/**
	 * Sets the enabled status and calls refresh on the viewer
	 * @param enabled the new value
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		this.structuredViewer.refresh();
	}
}
