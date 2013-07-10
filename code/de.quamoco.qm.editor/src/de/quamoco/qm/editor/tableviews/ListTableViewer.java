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

/*-----------------------------------------------------------------------+
 | de.quamoco.qm.editor
 |                                                                       |
   $Id: PropertiesTableViewer.java 3022 2010-05-18 16:05:22Z herrmama $            
 |                                                                       |
 | Copyright (c)  2004-2010 Technische Universitaet Muenchen             |
 |                                                                       |
 | Technische Universitaet Muenchen               #########  ##########  |
 | Institut fuer Informatik - Lehrstuhl IV           ##  ##  ##  ##  ##  |
 | Prof. Dr. Manfred Broy                            ##  ##  ##  ##  ##  |
 | Boltzmannstr. 3                                   ##  ##  ##  ##  ##  |
 | 85748 Garching bei Muenchen                       ##  ##  ##  ##  ##  |
 | Germany                                           ##  ######  ##  ##  |
 +-----------------------------------------------------------------------*/
package de.quamoco.qm.editor.tableviews;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import de.quamoco.qm.editor.tableviews.generalcolumns.QmTableColumn;

/**
 * This is the table viewer for displaying model element of one type in a list.
 * It supports multiple columns to show different attributes.
 * 
 * 
 * @author niessner
 * @author lochmann
 * @author $Author: herrmama $
 * @version $Rev: 3022 $
 * @levd.rating GREEN Hash:
 */
public abstract class ListTableViewer implements
		IStructuredContentProvider {
	
	/** The table viewer to which we delegate */
	protected TableViewer delegateTableViewer;

	

	/** The {@link EditingDomain}. */
	protected EditingDomain editingDomain;

	/** The {@link AdapterFactoryLabelProvider}. */
	protected AdapterFactoryLabelProvider adapterFactoryLabelProvider;

	/** List of Columns shown in the table */
	private final List<QmTableColumn> columns = new ArrayList<QmTableColumn>();

	/**
	 * Since, TableColumn must not be subclassed, we map TableColumn to
	 * QmTableColumn, which holds additional needed information.
	 */
	private final HashMap<TableColumn, QmTableColumn> tableColumnToQmTableColumn = new HashMap<TableColumn, QmTableColumn>();

	
	/**
	 * Returns the table viewer to which we delegate
	 */
	public TableViewer getDelegateTableViewer() {
		return delegateTableViewer;
	}
	
	/**
	 * @returns the AdapterFactoryLabelProvider
	 */
	public AdapterFactoryLabelProvider getAdapterFactoryLabelProvider() {
		return adapterFactoryLabelProvider;
	}

	/**
	 * This method registers adds a new table column and adds it to all
	 * necessary objects.
	 * 
	 * @param tableColumn
	 * @param qmTableColumn
	 */
	public void registerTableColumn(TableColumn tableColumn,
			QmTableColumn qmTableColumn) {
		this.tableColumnToQmTableColumn.put(tableColumn, qmTableColumn);
		this.columns.add(qmTableColumn);
		tableColumn.addSelectionListener(new TableSortSelectionListener());
	}

	/**
	 * Calls the constructor of the super class.
	 */
	public ListTableViewer(Composite parent, int style) {
		this.delegateTableViewer = new TableViewer(parent, style);
	}

	/**
	 * Initializes the table viewer by creating the table, the columns and
	 * setting the content- and label provider.
	 */
	public void initialize(EditingDomain editingDomain,
			AdapterFactory adapterFactory) {

		this.editingDomain = editingDomain;

		Table table = delegateTableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		delegateTableViewer.setSorter(new QmViewerSorter());

		adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(
				adapterFactory);
		delegateTableViewer.setLabelProvider(new QmTableLabelProvider());

		editingDomain.getCommandStack().addCommandStackListener(
				new CommandStackListener() {
					public void commandStackChanged(EventObject event) {
						Display.getDefault().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								delegateTableViewer.refresh();
							}
						});
					}
				});
	}

	/**
	 * The Label-Provider for this TableView. It delegates all calls to the
	 * corresponding QmTableColumn. The QmTableColumn realizes the
	 * getColumnImage and getColumnText individually for each column.
	 * 
	 * @author lochmann
	 * 
	 */
	class QmTableLabelProvider implements ITableLabelProvider {

		/** {@inheritDoc} */
		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			QmTableColumn tableColumn = tableColumnToQmTableColumn
					.get(delegateTableViewer.getTable().getColumns()[columnIndex]);
			if (tableColumn == null) {
				return null;
			}
			return tableColumn.getColumnImage(element);
		}

		/** {@inheritDoc} */
		@Override
		public String getColumnText(Object element, int columnIndex) {
			QmTableColumn tableColumn = tableColumnToQmTableColumn
					.get(delegateTableViewer.getTable().getColumns()[columnIndex]);
			return tableColumn.getColumnText(element);
		}

		/** {@inheritDoc} */
		public void addListener(ILabelProviderListener listener) {
			// not needed
		}

		/** {@inheritDoc} */
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		/** {@inheritDoc} */
		public void removeListener(ILabelProviderListener listener) {
			// not needed
		}

		/** {@inheritDoc} */
		@Override
		public void dispose() {
			// not needed
		}

	}

	/**
	 * A selection listener for each TableColumn. It assures the sorting of a
	 * column, when clicked.
	 * 
	 * @author lochmann
	 * 
	 */
	class TableSortSelectionListener implements SelectionListener {

		/**
		 * Method called, when a TableColumn is clicked. It starts sorting of
		 * the column, and changes sorting direction if necessary.
		 */
		@Override
		public void widgetSelected(SelectionEvent e) {
			TableColumn oldTableColumn = delegateTableViewer.getTable().getSortColumn();
			TableColumn newTableColumn = (TableColumn) e.getSource();

			if (oldTableColumn == newTableColumn) {
				if (delegateTableViewer.getTable().getSortDirection() == SWT.UP) {
					delegateTableViewer.getTable().setSortDirection(SWT.DOWN);
				} else {
					delegateTableViewer.getTable().setSortDirection(SWT.UP);
				}
			} else {
				delegateTableViewer.getTable().setSortColumn(newTableColumn);
				delegateTableViewer.getTable().setSortDirection(SWT.UP);
			}

			delegateTableViewer.refresh();
		}

		/**
		 * Called, when a Tablecolumn is selected.
		 */
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			widgetSelected(e);
		}
	}

	/**
	 * The ViewerSorter that sorts the table according to the selected column.
	 * 
	 * @author lochmann
	 * 
	 */
	class QmViewerSorter extends ViewerSorter {

		/**
		 * It compares to rows of the table, respecting the selected sorting
		 * column. The comparison is actually delegated to the
		 * QmTableColum.getSortObject() Method.
		 */
		@Override
		public int compare(Viewer viewer, Object e1, Object e2) {

			TableViewer tableViewer = (TableViewer) viewer;

			if (tableViewer.getTable().getSortColumn() == null) {
				return 0;
			}

			QmTableColumn sortColumn = tableColumnToQmTableColumn
					.get(tableViewer.getTable().getSortColumn());

			String c1 = sortColumn.getSortObject(e1);
			String c2 = sortColumn.getSortObject(e2);

			if (c1 == null) {
				c1 = "";
			}
			if (c2 == null) {
				c2 = "";
			}

			if (tableViewer.getTable().getSortDirection() == SWT.UP) {
				return c1.compareTo(c2);
			}
			return c2.compareTo(c1);
		}
	}

	/**
	 * Initialize the table viewer for sorting
	 */
	public void initializeSorting() {
		delegateTableViewer.getTable().setSortDirection(SWT.UP);
		delegateTableViewer.getTable().setSortColumn(delegateTableViewer.getTable().getColumn(0));
	}

}
