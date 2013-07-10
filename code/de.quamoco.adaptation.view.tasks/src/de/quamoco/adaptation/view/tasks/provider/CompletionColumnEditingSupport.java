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

package de.quamoco.adaptation.view.tasks.provider;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.widgets.Composite;

import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.view.tasks.actions.MarkCompletedHandler;

/**
 * Provides editing support for the completion column.
 * @author Franz Becker
 */
public class CompletionColumnEditingSupport extends EditingSupport {

	/** 
	 * A CellEditor faking a CheckBox. Actually there is no CheckBox
	 * Control created, hence, the CheckBox is simulated via the 
	 * according image by the LabelProvider. As of now there is no
	 * better way to do this - (the CheckBoxTreeViewer does the same).
	 */
	private CheckboxCellEditor editor;

	/**
	 * Create a new instance of the receiver.
	 * @param viewer
	 */
	public CompletionColumnEditingSupport(ColumnViewer viewer) {
		super(viewer);
		editor = new CheckboxCellEditor((Composite) viewer.getControl());
	}

	/** 
	 * Returns always true since the completion field is always editable 
	 */
	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	/**
	 * Returns the {@link CheckboxCellEditor}.
	 */
	@Override
	protected CellEditor getCellEditor(Object element) {
		return editor;
	}

	/**
	 * Returns {@link AdaptationTask#isCompleted()}.
	 */
	@Override
	protected Object getValue(Object element) {
		if (element instanceof AdaptationTask) {
			return ((AdaptationTask) element).isCompleted();
		}
		return new Boolean(false);
	}

	/**
	 * Delegates the call to {@link MarkCompletedHandler#setUserMarkedCompleted(AdaptationTask, boolean)}
	 */
	@Override
	protected void setValue(Object element, Object value) {		
		if ((element instanceof AdaptationTask) && (value instanceof Boolean)) {
			MarkCompletedHandler.setUserMarkedCompleted((AdaptationTask) element, (Boolean) value);
		}
	}
	
	
}
