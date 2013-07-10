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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import de.quamoco.qm.editor.tableviews.ListTableViewer.QmTableLabelProvider;

/**
 * Encapsulates a QmTableViewer, and adds a filter field, which searches all
 * columns
 * 
 * @author lochmann
 * 
 */
public class FilteredQmTableViewer extends Viewer {

	/**
	 * The main table of all factors
	 */
	private final QmTableViewer mainViewer;

	/**
	 * A composite that divides the window
	 */
	private final Composite mainComposite;

	/**
	 * The search-text field
	 */
	private final Text searchField;

	/**
	 * Constructor
	 * 
	 * @param parent
	 * @param style
	 */
	public FilteredQmTableViewer(Composite parent, int style,
			EClass typeOfElements) {
		super();

		mainComposite = new Composite(parent, style);

		mainComposite.setLayout(new GridLayout(1, false));

		// create the search field
		this.searchField = new Text(mainComposite, SWT.SEARCH | SWT.CANCEL
				| SWT.ICON_CANCEL);
		searchField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.searchField.addModifyListener(new SearchFieldModifiedListener());
		GridData gridData2 = new GridData(GridData.FILL_HORIZONTAL);
		this.searchField.setLayoutData(gridData2);

		// create the table viewer
		mainViewer = new QmTableViewer(mainComposite, SWT.V_SCROLL,
				typeOfElements);
		mainViewer.delegateTableViewer
				.addSelectionChangedListener(new SelectionChangedListenerOfMain());
		GridData gridData = new GridData(GridData.FILL_BOTH);
		mainViewer.delegateTableViewer.getControl().setLayoutData(gridData);

	}

	/**
	 * Initialized the control
	 * 
	 * @param editingDomain
	 * @param adapterFactory
	 */
	public void initialize(EditingDomain editingDomain,
			AdapterFactory adapterFactory) {
		this.mainViewer.initialize(editingDomain, adapterFactory);
	}

	/**
	 * Delegates to the QmTableViewwer
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.mainViewer.inputChanged(viewer, oldInput, newInput);
	}

	/**
	 * Returns the QmTabelViewer
	 * 
	 * @returns the QmTableViewer
	 */
	public QmTableViewer getQmTableViewer() {
		return this.mainViewer;
	}

	/** {@inheritDoc} */
	@Override
	public Object getInput() {
		return mainViewer.delegateTableViewer.getInput();
	}

	/** {@inheritDoc} */
	@Override
	public ISelection getSelection() {
		return this.mainViewer.delegateTableViewer.getSelection();
	}

	/** {@inheritDoc} */
	@Override
	public void refresh() {
		mainViewer.delegateTableViewer.refresh();
	}

	/** {@inheritDoc} */
	@Override
	protected void inputChanged(Object input, Object oldInput) {
		this.mainViewer.inputChanged(this, input, oldInput);
	}

	/** {@inheritDoc} */
	@Override
	public void setInput(Object input) {
		this.mainViewer.delegateTableViewer.setInput(input);
	}

	/** {@inheritDoc} */
	@Override
	public void setSelection(ISelection selection, boolean reveal) {
		this.mainViewer.delegateTableViewer.setSelection(selection, reveal);
	}

	/**
	 * The Listener for changed text in the search-field
	 * 
	 * @author lochmann
	 * 
	 */
	class SearchFieldModifiedListener implements ModifyListener {

		/** {@inheritDoc} */
		@Override
		public void modifyText(ModifyEvent e) {

			// set the search text as filter
			String s = searchField.getText();
			mainViewer.delegateTableViewer
					.setFilters(new ViewerFilter[] { new QmViewerFilter(s) });
		}
	}

	/**
	 * The filter for the QmTableViewer
	 * 
	 * @author lochmann
	 * 
	 */
	class QmViewerFilter extends org.eclipse.jface.viewers.ViewerFilter {

		/**
		 * Current filter
		 */
		private String filterString;

		/**
		 * @param filterString
		 */
		public QmViewerFilter(String filterString) {
			this.filterString = filterString;
		}

		/** {@inheritDoc} */
		@Override
		public boolean select(Viewer viewer, Object parentElement,
				Object element) {

			// returns true, if one column of the row contains the filter text

			filterString = filterString.toLowerCase();
			boolean res = false;
			for (int i = 0; i < mainViewer.delegateTableViewer.getTable()
					.getColumnCount(); i++) {
				String text = ((QmTableLabelProvider) mainViewer.delegateTableViewer
						.getLabelProvider()).getColumnText(element, i);
				if (text == null) {
					text = "";
				}
				text = text.toLowerCase();
				res = res || text.contains(filterString);
			}
			return res;
		}

	}

	/**
	 * A change-listener that delegates the events
	 */
	class SelectionChangedListenerOfMain implements ISelectionChangedListener {
		/** {@inheritDoc} */
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			// fire the event
			fireSelectionChanged(event);
		}
	}

	/** {@inheritDoc} */
	@Override
	public Control getControl() {
		return this.mainComposite;
	}

	/**
	 * Sort according to the first column
	 */
	public void initializeSorting() {
		this.mainViewer.initializeSorting();
	}

}
