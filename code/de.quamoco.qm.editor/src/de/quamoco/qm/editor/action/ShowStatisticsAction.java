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

package de.quamoco.qm.editor.action;

import org.conqat.ide.commons.ui.selection.SelectionUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import edu.tum.cs.emf.commons.statistics.StatisticsView;

/**
 * Action to display the number of elements in a model.
 * 
 * @author herrmama
 * @author $Author: deissenb $
 * @version $Rev: 9068 $
 * @levd.rating YELLOW Hash: 8044756AAE947668E45A287E222DB270
 */
public class ShowStatisticsAction implements IObjectActionDelegate {

	/** Model element. */
	private EObject element;

	/** {@inheritDoc} */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// not required
	}

	/** {@inheritDoc} */
	public void run(IAction action) {
		StatisticsView.showStatisticsView(element);
	}

	/** {@inheritDoc} */
	public void selectionChanged(IAction action, ISelection selection) {
		element = SelectionUtils.checkAndPickFirst(selection, EObject.class);
	}
}
