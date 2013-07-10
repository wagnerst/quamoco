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

package de.quamoco.adaptation.view.filter;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * Subclasses {@link PatternFilter} and overrides its isLeafMatch
 * method to include every column in the filtering process.
 * @author Franz Becker
 */
public class ColumnPatternFilter extends PatternFilter {

	/**
	 * Assumes that the viewer is a {@link TreeViewer} and its
	 * label provider an {@link ITableLabelProvider}.
	 * For each column in the tree, wordMatches is called and the
	 * composed result is returned.
	 */
	@Override
	protected boolean isLeafMatch(Viewer viewer, Object element){
		TreeViewer treeViewer = (TreeViewer) viewer;
		ITableLabelProvider labelProvider = (ITableLabelProvider) treeViewer.getLabelProvider();
		
		boolean returnValue = false;
		
		// Iterate over each column, get the label, call wordMatches and 
		// compose the result.
		for (int i = 0; i < treeViewer.getTree().getColumnCount(); i++) {
			String columnText = labelProvider.getColumnText(element, i);
			returnValue = returnValue || wordMatches(columnText);
		}
		
		return returnValue;
	}
}

