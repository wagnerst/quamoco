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

import java.util.List;

import org.conqat.ide.commons.ui.selection.SelectionUtils;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Action to unload resources.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: B8A978AD915885CDC88D36BE6DEE150D
 */
public class UnloadResourceAction implements IObjectActionDelegate {

	/** Editor. */
	private IEditorPart editor;

	/** Resources. */
	private List<Resource> resources;

	/** {@inheritDoc} */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		editor = (IEditorPart) targetPart;
	}

	/** {@inheritDoc} */
	public void run(IAction action) {
		for (Resource resource : resources) {
			resource.getResourceSet().getResources().remove(resource);
			resource.unload();
		}
	}

	/** {@inheritDoc} */
	public void selectionChanged(IAction action, ISelection selection) {
		if (editor == null || editor.isDirty()) {
			action.setEnabled(false);
		} else {
			action.setEnabled(true);
			resources = SelectionUtils.selectAll(selection, Resource.class);
		}
	}
}
