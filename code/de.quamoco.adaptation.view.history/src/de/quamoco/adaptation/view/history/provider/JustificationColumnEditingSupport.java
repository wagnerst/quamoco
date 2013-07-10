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

package de.quamoco.adaptation.view.history.provider;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

import de.quamoco.adaptation.model.status.AdaptationHistoryItem;
import de.quamoco.adaptation.model.status.StatusPackage;
import de.quamoco.adaptation.util.resource.ResourceUtil;

public class JustificationColumnEditingSupport extends EditingSupport {

	private TextCellEditor editor;
	private final EStructuralFeature justificationFeature = StatusPackage.eINSTANCE.getAdaptationHistoryItem_Justification();

	public JustificationColumnEditingSupport(ColumnViewer viewer) {
		super(viewer);
		editor = new TextCellEditor((Composite) viewer.getControl());
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return editor;
	}

	@Override
	protected Object getValue(Object element) {
		if (element instanceof AdaptationHistoryItem) {
			return ((AdaptationHistoryItem) element).getJustification();
		}
		return "";
	}

	@Override
	protected void setValue(Object element, Object value) {
		if ((element instanceof AdaptationHistoryItem) && (value instanceof String)) {
			String justification = (String) value;
			AdaptationHistoryItem historyItem = (AdaptationHistoryItem) element;
			if (!justification.equals(historyItem.getJustification())) {
				// TODO create a task when justification is unset
				// execute the command on the editing domain
				EditingDomain editingDomain = ResourceUtil.getEditingDomainFor(historyItem);
				if (editingDomain != null) {	
					Command removeCommand = new SetCommand(editingDomain, historyItem, justificationFeature, justification); 
					editingDomain.getCommandStack().execute(removeCommand);
				}
			}						
		}
	}

}
