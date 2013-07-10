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

package de.quamoco.qm.incubator;

import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

public class GenericTableViewer<I, R, C, E> extends TableViewer {

	private List<C> columns;
	
	private IGenericTableContentProvider<I, R, C, E> contentProvider;

	private IGenericTableLabelProvider<R, C, E> labelProvider;

	public GenericTableViewer(Composite parent, int style) {
		super(parent, style);
		
		getTable().setHeaderVisible(true);
	}

	public void setGenericContentProvider(IGenericTableContentProvider<I, R, C, E> contentProvider) {
		this.contentProvider = contentProvider;
		setContentProvider(new IStructuredContentProvider() {

			public Object[] getElements(Object inputElement) {
				return GenericTableViewer.this.contentProvider.getRows((I) inputElement).toArray();
			}

			public void dispose() {
				
			}

			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
				if(newInput != oldInput) {
					updateColumns((I) newInput);
				}
			}
			
		});
	}

	public void setGenericLabelProvider(final IGenericTableLabelProvider<R, C, E> labelProvider) {
		this.labelProvider = labelProvider;
		setLabelProvider(new ITableLabelProvider() {

			public Image getColumnImage(Object object, int columnIndex) {
				R row = (R) object;
				if(columnIndex == 0) {
					return labelProvider.getRowImage(row);
				}
				E element = contentProvider.getElement(row, columns.get(columnIndex-1));
				return GenericTableViewer.this.labelProvider.getElementImage(element);
			}

			public String getColumnText(Object object, int columnIndex) {
				R row = (R) object;
				if(columnIndex == 0) {
					return labelProvider.getRowText(row);
				}
				E element = contentProvider.getElement(row, columns.get(columnIndex-1));
				return GenericTableViewer.this.labelProvider.getElementText(element);
			}

			public void addListener(ILabelProviderListener listener) {
				
			}

			public void dispose() {
				
			}

			public boolean isLabelProperty(Object element, String property) {
				return false;
			}

			public void removeListener(ILabelProviderListener listener) {
				
			}
			
		});
	}

	protected void updateColumns(I input) {
		removeColumns();		
		addFirstColumn();
		addColumns(input);
	}

	private void removeColumns() {
		for(TableColumn tableColumn : getTable().getColumns()) {
			tableColumn.dispose();
		}
	}
	
	private void addFirstColumn() {
		TableColumn tableColumn = new TableColumn(getTable(), SWT.None);
		tableColumn.setWidth(200);
	}

	private void addColumns(I input) {
		columns = contentProvider.getColumns(input);
		for(C column : columns) {
			TableColumn tableColumn = new TableColumn(getTable(), SWT.None);
			tableColumn.setWidth(50);
			tableColumn.setText(labelProvider.getColumnText(column));
			tableColumn.setToolTipText(labelProvider.getColumnText(column));
		}
	}	
}
