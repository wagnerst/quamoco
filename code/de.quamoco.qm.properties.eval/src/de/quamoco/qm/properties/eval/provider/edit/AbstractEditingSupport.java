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

package de.quamoco.qm.properties.eval.provider.edit;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

import de.quamoco.qm.properties.eval.model.InfluencingOrRefiningElement;

public abstract class AbstractEditingSupport extends EditingSupport {

	protected final TableViewer viewer;
	protected final ColumnLabelProvider labelProvider;
	protected ListenerList valueUpdatedListeners = new ListenerList();

	public AbstractEditingSupport(TableViewer viewer, int columnIndex) {
		super(viewer);
		this.viewer = viewer;
		this.labelProvider = (ColumnLabelProvider) viewer
				.getLabelProvider(columnIndex);
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return new TextCellEditor(viewer.getTable());
	}

	@Override
	protected Object getValue(Object element) {
		return labelProvider.getText(element);
	}

	@Override
	protected final void setValue(Object object, Object value) {
		if (value != null && !value.equals(getValue(object))) { // makes sure
																// that the
																// value has
																// really
																// changed
			if (object instanceof InfluencingOrRefiningElement<?>) {
				try {
					InfluencingOrRefiningElement<?> element = (InfluencingOrRefiningElement<?>) object;
					setValue(element, value);
					fireValueUpdated(element);
					viewer.refresh(); // trigger update of viewer and resorting
				} catch (Exception e) {
				}
			}
		}
	}

	protected abstract void setValue(InfluencingOrRefiningElement<?> element,
			Object value);

	protected void fireValueUpdated(
			final InfluencingOrRefiningElement<?> element) {
		Object[] listeners = valueUpdatedListeners.getListeners();
		for (int i = 0; i < listeners.length; ++i) {
			final IValueUpdatedListener l = (IValueUpdatedListener) listeners[i];
			SafeRunnable.run(new SafeRunnable() {
				public void run() {
					l.valueUpdated(element);
				}
			});
		}
	}

	public void addValueUpdatedListener(IValueUpdatedListener listener) {
		valueUpdatedListeners.add(listener);
	}

	public void removeValueUpdatedListener(IValueUpdatedListener listener) {
		valueUpdatedListeners.remove(listener);
	}

}
