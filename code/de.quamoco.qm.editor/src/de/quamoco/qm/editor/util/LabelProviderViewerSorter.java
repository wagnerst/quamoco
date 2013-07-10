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

package de.quamoco.qm.editor.util;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

/**
 * Sorter that uses an {@link ILabelProvider} to sort model elements of a
 * certain {@link EClass}.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 30CB6822E2651B26A2130A62BC8BBD45
 */
public class LabelProviderViewerSorter extends ViewerSorter {

	/** The {@link ILabelProvider} on which sorting is based. */
	private final ILabelProvider labelProvider;

	/** Whether sorting is case sensitive or not. */
	private final boolean caseSensitive;

	/** The classes of which model elements are sorted. */
	private final EClass[] sortedTypes;

	/** Constructor. */
	public LabelProviderViewerSorter(ILabelProvider labelProvider,
			boolean caseSensitive, EClass... sortedTypes) {
		this.labelProvider = labelProvider;
		this.caseSensitive = caseSensitive;
		this.sortedTypes = sortedTypes;
	}

	/** {@inheritDoc} */
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (sorted(e1) && sorted(e2)) {
			String s1 = getText(e1);
			String s2 = getText(e2);
			return s1.compareTo(s2);
		}
		return 0;
	}

	/** Get the text for a certain element. */
	private String getText(Object e) {
		String text = labelProvider.getText(e);
		if (!caseSensitive) {
			text = text.toLowerCase();
		}
		return text;
	}

	/** Check whether a certain element are sorted based on their type. */
	private boolean sorted(Object e) {
		for (EClass sortedType : sortedTypes) {
			if (sortedType.isInstance(e)) {
				return true;
			}
		}
		return false;
	}
}
