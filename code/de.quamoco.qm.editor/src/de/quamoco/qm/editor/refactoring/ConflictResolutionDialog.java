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

package de.quamoco.qm.editor.refactoring;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;

import de.quamoco.qm.provider.QmEditPlugin;

/**
 * Dialog to resolve conflicts for the merge refactoring.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: BCC1163D3B7ABBABE81B6480849F0FD7
 */
public class ConflictResolutionDialog extends TitleAreaDialog {

	/** The merge helper. */
	private final Merge merge;

	/** The label provider. */
	private final AdapterFactoryLabelProvider labelProvider;

	/** Constructor. */
	public ConflictResolutionDialog(Merge merge, AdapterFactory adapterFactory) {
		super(Display.getDefault().getActiveShell());

		this.merge = merge;
		this.labelProvider = new AdapterFactoryLabelProvider(adapterFactory);
	}

	/** {@inheritDoc} */
	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Merge");
	}

	/** {@inheritDoc} */
	@Override
	protected void initializeBounds() {
		super.initializeBounds();
		getShell().setSize(600, 400);
	}

	/** {@inheritDoc} */
	@Override
	protected Control createDialogArea(Composite parent) {
		parent = (Composite) super.createDialogArea(parent);

		setTitle("Resolve Conflicts");
		setMessage("The values of some properties are conflicting. "
				+ "For each property, please choose the element from "
				+ "which the value should be taken.");

		final TableViewer viewer = new TableViewer(parent, SWT.FULL_SELECTION);
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.setContentProvider(new ArrayContentProvider());

		createFeatureColumn(viewer);

		createValueColumn(viewer, true);
		createChoiceColumn(viewer, true);

		createChoiceColumn(viewer, false);
		createValueColumn(viewer, false);

		viewer.setInput(merge.getConflicts());

		return parent;
	}

	/** Create the column to display the feature. */
	private void createFeatureColumn(TableViewer viewer) {
		TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.None);
		TableColumn column = viewerColumn.getColumn();
		column.setWidth(100);
		column.setText("Property");
		viewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Conflict conflict = (Conflict) element;
				return getString(conflict.getFeature());
			}

			private String getString(EStructuralFeature feature) {
				String key = "_UI_" + feature.getEContainingClass().getName()
						+ "_" + feature.getName() + "_feature";
				return QmEditPlugin.INSTANCE.getString(key);
			}
		});
	}

	/** Create a column for the value of a feature for an element. */
	private void createValueColumn(final TableViewer viewer, final boolean first) {
		TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.None);
		TableColumn column = viewerColumn.getColumn();
		column.setWidth(200);
		column.setText(labelProvider.getText(first ? merge.getFirst() : merge
				.getSecond()));
		column.setImage(labelProvider.getImage(first ? merge.getFirst() : merge
				.getSecond()));
		viewerColumn.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				Conflict conflict = (Conflict) element;
				Object value = getValue(conflict);
				return labelProvider.getText(value);
			}

			private Object getValue(Conflict conflict) {
				return first ? conflict.getFirstValue() : conflict
						.getSecondValue();
			}

			@Override
			public Image getImage(Object element) {
				Conflict conflict = (Conflict) element;
				Object value = getValue(conflict);
				return labelProvider.getImage(value);
			}
		});
	}

	/**
	 * Create the column for the resolution of a feature's conflict in favor of
	 * an element.
	 */
	private void createChoiceColumn(final TableViewer viewer,
			final boolean first) {
		TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.None);
		TableColumn column = viewerColumn.getColumn();
		column.setWidth(20);
		column.setText("x");
		viewerColumn.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				Conflict conflict = (Conflict) element;
				return first == conflict.getResolution() ? "x" : "";
			}

		});
		viewerColumn.setEditingSupport(new EditingSupport(viewer) {

			@Override
			protected boolean canEdit(Object element) {
				return true;
			}

			@Override
			protected CellEditor getCellEditor(Object element) {
				return new CheckboxCellEditor((Composite) getViewer()
						.getControl());
			}

			@Override
			protected Object getValue(Object element) {
				Conflict conflict = (Conflict) element;
				return first == conflict.getResolution();
			}

			@Override
			protected void setValue(Object element, Object value) {
				Conflict conflict = (Conflict) element;
				conflict.resolve(first);
				getViewer().refresh();
			}
		});

		column.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for (Conflict conflict : merge.getConflicts()) {
					conflict.resolve(first);
				}
				viewer.refresh();
			}
		});
	}
}
