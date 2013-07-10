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

package de.quamoco.adaptation.wizard.util.elementsselection.filter;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.dialogs.PatternFilter;

import de.quamoco.qm.NamedElement;

/**
 * A {@link PatternFilter} that filters by the name of
 * {@link NamedElement}s and not on their labels.
 * Sample label is "QualityAspect xyz", default behavior would
 * according to the whole string, this behavior matches only for xyz.
 * @author Franz Becker
 * @levd.rating GREEN Hash: 0D0CCC0D1D33EE4778D1E70D611CA3B8
 */
public class NamedElementPatternFilter extends PatternFilter {

	/** If true only {@link #isLeafMatch(Viewer, Object)} will only
	 *  return true if the element is a leaf in the model, i.e. if
	 *  the content provider returns false on hasChildren(element). */
	protected final boolean onlyModelLeaves;
	
	/**
	 * Calls the super constructor and sets {@link #onlyModelLeaves} to false.
	 */
	public NamedElementPatternFilter() {
		super();
		onlyModelLeaves = false;
	}
	
	/**
	 * Calls the super constructor and initializes the fields.
	 * @param onlyModelLeaves true if only leaves in the model shall be leaf matches, false otherwise
	 * @see #onlyModelLeaves
	 */
	public NamedElementPatternFilter(boolean onlyModelLeaves) {
		super();
		this.onlyModelLeaves = onlyModelLeaves;
	}
	
	/**
	 * Calls {@link #wordMatches(String)} on the name of the
	 * {@link NamedElement}.
	 */
	@Override
	public boolean isLeafMatch(Viewer viewer, Object element){		
		if (element instanceof NamedElement) {
			NamedElement namedElement = (NamedElement) element;
			if (!onlyModelLeaves || isLeaf(viewer, namedElement)) {					
				return wordMatches(namedElement.getName());
			}
		}
		return false;		
	}

	/**
	 * Asks the {@link ITreeContentProvider} of the viewer if
	 * the passed element has children and returns false if so.
	 * @param viewer the viewer
	 * @param element the element
	 * @return negation of {@link ITreeContentProvider#hasChildren(Object)
	 */
	protected boolean isLeaf(Viewer viewer, NamedElement element) {
		ITreeContentProvider contentProvider = (ITreeContentProvider) ((StructuredViewer) viewer).getContentProvider();
		return !contentProvider.hasChildren(element);
	}
	
}
