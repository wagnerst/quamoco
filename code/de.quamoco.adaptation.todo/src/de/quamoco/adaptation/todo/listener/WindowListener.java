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

package de.quamoco.adaptation.todo.listener;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.quamoco.qm.presentation.QmEditor;

public class WindowListener implements IWindowListener, IPartListener2 {
	
	protected Map<EditingDomain, TodoController> observedEditingDomains = new HashMap<EditingDomain, TodoController>();
	
	/**
	 * For initialization the existing workbench windows must be adapted plus
	 * their active parts must be checked.
	 */
	public WindowListener() {
		for (IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
			window.getPartService().addPartListener(this);
			IWorkbenchPart part = window.getPartService().getActivePart();
			if (part != null && part instanceof QmEditor) {
				registerEditingDomain((QmEditor) part);
			}			
		}
	}

	protected void registerEditingDomain(QmEditor editor) {		
		EditingDomain editingDomain = editor.getEditingDomain();
		if (!observedEditingDomains.containsKey(editingDomain)) {
			// Constructor adapts itself to the ResourceSet automatically
			TodoController todoController = new TodoController(editingDomain); 
			todoController.adapt();
			observedEditingDomains.put(editingDomain, todoController);
			
		}		
	}

	protected void unregisterEditingDomain(QmEditor editor) {
		EditingDomain editingDomain = editor.getEditingDomain();
		if (observedEditingDomains.containsKey(editingDomain)) {
			TodoController todoController = observedEditingDomains.get(editingDomain);
			todoController.unadapt();
			observedEditingDomains.remove(editingDomain);
		}		
	}

	/*
	 * Window Listener
	 */		
	
	@Override
	public void windowActivated(IWorkbenchWindow window) {
		window.getPartService().addPartListener(this);
	}
	
	@Override
	public void windowClosed(IWorkbenchWindow window) {
		window.getPartService().removePartListener(this);
	}

	
	/*
	 * Part Listener
	 */
	
	@Override
	public void partActivated(IWorkbenchPartReference partRef) {
		IWorkbenchPart part = partRef.getPart(false);
		if (part instanceof QmEditor) {
			registerEditingDomain((QmEditor) part);
		}		
	}	

	@Override
	public void partClosed(IWorkbenchPartReference partRef) {
		IWorkbenchPart part = partRef.getPart(false);
		if (part instanceof QmEditor) {
			unregisterEditingDomain((QmEditor) part);
		}
	}

	/*
	 * Interface methods not used
	 */
	@Override
	public void windowDeactivated(IWorkbenchWindow window) { /* do nothing */ }	
	@Override
	public void windowOpened(IWorkbenchWindow window) { /* do nothing */ }
	@Override
	public void partBroughtToTop(IWorkbenchPartReference partRef) { /* do nothing */ }
	@Override
	public void partDeactivated(IWorkbenchPartReference partRef) { /* do nothing */ }
	@Override
	public void partOpened(IWorkbenchPartReference partRef) { /* do nothing */ }
	@Override
	public void partHidden(IWorkbenchPartReference partRef) { /* do nothing */ }
	@Override
	public void partVisible(IWorkbenchPartReference partRef) { /* do nothing */ }
	@Override
	public void partInputChanged(IWorkbenchPartReference partRef) { /* do nothing */ }
	
}
