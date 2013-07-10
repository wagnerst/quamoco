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

package de.quamoco.qm.editor.tableviews;

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

/**
 * A table-viewer that shows the values of a reference of a certain element.
 * 
 * @author herrmama
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating YELLOW Hash: 9C92ACFD53A2D93E3B6B9171BF96D782
 */
public class ReferenceTableViewer extends ListTableViewer {

	/** The element for which the values of the reference is shown. */
	protected EObject rootObject = null;

	/** The reference that is shown */
	protected final EReference reference;

	/** Constructor. */
	public ReferenceTableViewer(Composite parent, int style,
			EReference reference) {
		super(parent, style);
		this.reference = reference;

		// Auto-resize the table columns
		this.delegateTableViewer.getTable().addControlListener(new ControlAdapter() {

			@Override
			public void controlResized(ControlEvent e) {
				int w = delegateTableViewer.getTable().getBounds().width;
				TableColumn[] columns = delegateTableViewer.getTable().getColumns();
				if (columns.length == 1) {
					columns[0].setWidth(w);
				} else if (columns.length == 2) {
					columns[0].setWidth((int) (w * 0.7));
					columns[1].setWidth((int) (w * 0.3));
				}

			}
		});
	}

	/** Initializes the UI. */
	@Override
	public void initialize(EditingDomain editingDomain,
			AdapterFactory adapterFactory) {
		super.initialize(editingDomain, adapterFactory);
		this.delegateTableViewer.setContentProvider(this);
	}

	/** Returns the elements to show. */
	@Override
	public Object[] getElements(Object inputElement) {
		if (rootObject == null) {
			return new Object[] {};
		}
		List<?> list = (List<?>) this.rootObject.eGet(this.reference);
		return list.toArray();
	}

	/** If the shown element is changed, the table is refreshed. */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.rootObject = (EObject) newInput;
		this.delegateTableViewer.refresh();
		this.delegateTableViewer.setLabelProvider(new QmTableLabelProvider());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		// do nothing
	}
}
