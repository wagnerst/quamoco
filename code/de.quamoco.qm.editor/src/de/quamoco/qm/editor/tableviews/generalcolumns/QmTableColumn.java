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

package de.quamoco.qm.editor.tableviews.generalcolumns;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TableColumn;

import de.quamoco.qm.editor.tableviews.ListTableViewer;

/**
 * This class represents a Column in the QmTableViewer. It references the
 * TableColumn-Object and holds additional information
 * 
 * @author herrmama
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating YELLOW Hash: 097F23759A29A1106888910878AD68D2
 */
public abstract class QmTableColumn {

	/** The TableColumn object actually present in the table. */
	protected TableColumn tableColumn;

	/**
	 * The LabelProvider of the TableViewer. It can be used by subclasses to
	 * return the default Text and Image of an Object.
	 */
	protected AdapterFactoryLabelProvider labelProvider;

	/**
	 * Constructor that creates an TableColumn element and adds it to the
	 * QmTableViewer.
	 */
	public QmTableColumn(ListTableViewer parent) {
		this.tableColumn = new TableColumn(parent.getDelegateTableViewer().getTable(), SWT.NONE);
		this.tableColumn.setWidth(200);
		this.tableColumn.setResizable(true);
		this.labelProvider = parent.getAdapterFactoryLabelProvider();
		parent.registerTableColumn(this.tableColumn, this);
	}

	/** Returns the TableColumn Object. */
	public TableColumn getTableColumn() {
		return this.tableColumn;
	}

	/** Method that returns the image for this column. */
	public abstract Image getColumnImage(Object element);

	/** Method that returns the text for this column. */
	public abstract String getColumnText(Object element);

	/**
	 * Method that returns a string according to which this column will be
	 * sorted.
	 */
	public abstract String getSortObject(Object element);

}
