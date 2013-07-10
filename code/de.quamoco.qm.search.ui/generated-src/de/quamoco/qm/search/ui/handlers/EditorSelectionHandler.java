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

package de.quamoco.qm.search.ui.handlers;

import java.util.Arrays;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.search.ui.handlers.AbstractModelElementEditorSelectionHandler;
import org.eclipse.ui.IEditorPart;

import de.quamoco.qm.presentation.QmEditor;

/**
 * Defines entity responsible of editor selection handling.
 * 
 * In other words users defines here how the search double clicked result will be handled in 
 * terms of corresponding editor selection.
 *  
 */
public class EditorSelectionHandler extends
		AbstractModelElementEditorSelectionHandler {

	public boolean isCompatibleModelElementEditorSelectionHandler(
			IEditorPart part) {
		return (part instanceof QmEditor);
	}

	public IStatus handleOpenTreeEditorWithSelection(IEditorPart part,
			Object selection) {

		if (part instanceof QmEditor) {
			((QmEditor) part).setSelectionToViewer(Arrays
					.asList(new Object[] { selection }));
			return Status.OK_STATUS;
		}

		return Status.CANCEL_STATUS;
	}

	@Override
	protected String getNsURI() {
		return ""; // TODO: user to return appropriate nsURI
	}
}
