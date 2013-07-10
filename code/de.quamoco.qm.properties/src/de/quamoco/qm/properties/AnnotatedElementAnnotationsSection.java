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

package de.quamoco.qm.properties;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;

import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.QmFactory;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.impl.AnnotationImpl;
import edu.tum.cs.emf.commons.commands.WrappingCompoundCommand;
import edu.tum.cs.emf.commons.resources.ResourceLocatorUtils;
import edu.tum.cs.emf.commons.sections.ElementPropertySectionBase;

/**
 * The Annotations section on the Annotations Tab.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: EA508A7BBA62C987BCAEC149829AC172
 */
public class AnnotatedElementAnnotationsSection extends
		ElementPropertySectionBase<AnnotatedElement> {

	/** The {@link TableViewer} for the annotations table. */
	private TableViewer tableViewer;

	/** The Editing Domain. */
	private EditingDomain editingDomain;

	/** The {@link IObservableList}. */
	private IObservableList list;

	/** {@inheritDoc} */
	@Override
	protected void createLabel(Composite composite) {
		createLabel(composite, "Annotations");
	}

	/** Creates the {@link TableViewer}. */
	@Override
	protected void createValue(Composite composite) {
		tableViewer = new TableViewer(composite, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION);
		tableViewer.setContentProvider(new ObservableListContentProvider());

		createColumn(QmPackage.eINSTANCE.getAnnotation_Key());
		createColumn(QmPackage.eINSTANCE.getAnnotation_Value());
		setUpTable();
	}

	/** Creates the {@link TableViewerColumn}s for key and value. */
	private void createColumn(final EStructuralFeature feature) {
		TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.NONE);
		column.getColumn().setText(ResourceLocatorUtils.getString(feature));
		column.getColumn().setWidth(200);
		column.getColumn().setResizable(true);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				AnnotationImpl impl = (AnnotationImpl) element;
				Object value = impl.eGet(feature);
				if (value == null) {
					return "<Enter " + ResourceLocatorUtils.getString(feature)
							+ ">";
				}
				return value.toString();
			}
		});
		column.setEditingSupport(new AnnotationsEditingSupport(tableViewer,
				feature));
	}

	/** Creates the {@link Table}. */
	private void setUpTable() {
		Table table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		GridData data = new GridData(GridData.FILL_BOTH);
		data.horizontalIndent = ITabbedPropertyConstants.HSPACE;
		data.verticalIndent = ITabbedPropertyConstants.VSPACE;
		table.setLayoutData(data);
	}

	/** {@inheritDoc} */
	@Override
	protected boolean hasButtons() {
		return true;
	}

	/** Creates the Add- and Remove-{@link Button}s. */
	@Override
	protected void createButtons(Composite composite) {
		Composite buttonComposite = getWidgetFactory().createComposite(
				composite);
		GridData buttonData;
		buttonData = new GridData();
		buttonData.verticalAlignment = SWT.TOP;
		buttonData.verticalIndent = ITabbedPropertyConstants.VSPACE;
		buttonComposite.setLayoutData(buttonData);
		buttonComposite.setLayout(GridLayoutFactory.fillDefaults().margins(
				ITabbedPropertyConstants.HMARGIN,
				ITabbedPropertyConstants.VMARGIN).create());
		createAddButton(buttonComposite);
		createRemoveButton(buttonComposite);
	}

	/** Creates the Add-{@link Button}. */
	private void createAddButton(Composite buttonComposite) {
		Button add = getWidgetFactory().createButton(buttonComposite, "add",
				SWT.NONE);
		add.setToolTipText("Add Key-Value-Pair");
		add.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AnnotationImpl impl = (AnnotationImpl) QmFactory.eINSTANCE
						.create(QmPackage.eINSTANCE.getAnnotation());
				Command command = AddCommand.create(editingDomain, getElement(),
						QmPackage.eINSTANCE.getAnnotatedElement_Annotations(),
						impl);
				editingDomain.getCommandStack().execute(
						new WrappingCompoundCommand(command, getElement()));
			}
		});
		GridData buttonData = new GridData();
		buttonData.horizontalAlignment = SWT.FILL;
		add.setLayoutData(buttonData);
	}

	/** Creates the Remove-{@link Button}. */
	private void createRemoveButton(Composite buttonComposite) {
		Button remove = getWidgetFactory().createButton(buttonComposite,
				"remove", SWT.NONE);
		remove.setToolTipText("Remove Selected Key-Value-Pair(s)");
		remove.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection) tableViewer
						.getSelection();
				ArrayList<Command> commandList = new ArrayList<Command>();
				for (Iterator<?> iterator = selection.iterator(); iterator
						.hasNext();) {
					AnnotationImpl impl = (AnnotationImpl) iterator.next();
					Command command = RemoveCommand.create(editingDomain,
							getElement(), QmPackage.eINSTANCE
									.getAnnotatedElement_Annotations(), impl);
					commandList.add(command);
				}
				Command command = new WrappingCompoundCommand(commandList,
						getElement());
				editingDomain.getCommandStack().execute(command);
			}
		});
		GridData buttonData = new GridData();
		buttonData.horizontalAlignment = SWT.FILL;
		remove.setLayoutData(buttonData);
	}

	/** {@inheritDoc} */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(getElement());
	}

	/** {@inheritDoc} */
	@Override
	public void refresh() {
		if (list != null) {
			list.dispose();
		}
		list = EMFEditObservables.observeList(editingDomain, getElement(),
				QmPackage.eINSTANCE.getAnnotatedElement_Annotations());
		tableViewer.setInput(list);
		super.refresh();
	}

	/** {@inheritDoc} */
	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public void setFocus() {
		tableViewer.getTable().setFocus();
	}

	/** Editing Support for TableViewerColumn. */
	class AnnotationsEditingSupport extends EditingSupport {

		/** Indicates which feature (key / value) the EditingSupport is for. */
		private final EStructuralFeature feature;

		/** The Constructor. */
		public AnnotationsEditingSupport(ColumnViewer viewer,
				EStructuralFeature feature) {
			super(viewer);
			this.feature = feature;
		}

		/** {@inheritDoc} */
		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		/** {@inheritDoc} */
		@Override
		protected CellEditor getCellEditor(Object element) {
			return new TextCellEditor(tableViewer.getTable());
		}

		/** {@inheritDoc} */
		@Override
		protected Object getValue(Object element) {
			AnnotationImpl impl = (AnnotationImpl) element;
			Object value = impl.eGet(feature);
			if (value == null) {
				return "";
			}
			return value;
		}

		/** {@inheritDoc} */
		@Override
		protected void setValue(Object element, Object value) {
			Command command = new WrappingCompoundCommand(SetCommand.create(
					editingDomain, element, feature, value),
					AnnotatedElementAnnotationsSection.this.getElement());
			editingDomain.getCommandStack().execute(command);
		}
	}
}