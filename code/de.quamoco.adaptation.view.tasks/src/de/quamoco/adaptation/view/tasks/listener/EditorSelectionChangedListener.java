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

package de.quamoco.adaptation.view.tasks.listener;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.AnnotationBase;
import de.quamoco.qm.presentation.QmEditor;

/**
 * This class acts on selection changed events in file editors, tries
 * to get selected {@link AnnotatedElement}s, their corresponding 
 * {@link AdaptationTask}s and then marks them in the view.
 * @author Franz Becker
 */
public class EditorSelectionChangedListener implements ISelectionListener {

	/** Required to call {@link TreeViewer#setSelection(ISelection, boolean)} */
	private TreeViewer treeViewer;
	
	/** Parameter for {@link TreeViewer#setSelection(ISelection, boolean)} */
	private boolean reveal;

	/**
	 * Initializes the fields
	 * @param treeViewer the tree viewer whose selection shall be set.
	 * @param reveal reveal new selection or not
	 * @see {@link TreeViewer#setSelection(ISelection, boolean)}
	 */
	public EditorSelectionChangedListener(TreeViewer treeViewer, boolean reveal) {
		this.treeViewer = treeViewer;
		this.reveal = reveal;
	}
	
	/**
	 * Collects all {@link AdaptationTask} of selected {@link AnnotatedElement}s
	 * and selects them in the {@link #treeViewer}.
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (part instanceof QmEditor) {
			List<AdaptationTask> tasksToSelect = new LinkedList<AdaptationTask>();
			if (selection instanceof StructuredSelection) {
				for (Object object : ((StructuredSelection) selection).toList()) {
					if (object instanceof AnnotatedElement) {	// add all tasks to result
						for (AnnotationBase baseAnnotation : ((AnnotatedElement) object).getAdvancedAnnotations()) {
							if (baseAnnotation instanceof AdaptationTask) {
								tasksToSelect.add((AdaptationTask) baseAnnotation);
								// TODO replace by util method
							}
						}
						
					}
				}
			}			
			treeViewer.setSelection(new StructuredSelection(tasksToSelect), reveal);
		}
	}
	
}

