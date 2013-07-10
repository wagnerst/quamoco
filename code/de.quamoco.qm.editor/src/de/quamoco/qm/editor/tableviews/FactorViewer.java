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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;

import de.quamoco.qm.QmPackage;
import de.quamoco.qm.editor.tableviews.generalcolumns.EFeatureColumnImage;
import de.quamoco.qm.editor.tableviews.generalcolumns.QmListFeatureColumn;
import de.quamoco.qm.editor.tableviews.generalcolumns.QmResourceColumn;
import de.quamoco.qm.editor.tableviews.generalcolumns.QmSingleFeatureColumn;
import de.quamoco.qm.editor.tableviews.generalcolumns.QmTableColumn;

/**
 * A viewer for browsing a list of factors.
 * 
 * @author herrmama
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
public class FactorViewer extends Viewer {

	/** The {@link EditingDomain}. */
	private EditingDomain editingDomain;

	/** The {@link AdapterFactoryLabelProvider}. */
	private AdapterFactory adapterFactory;

	/** The main table of all factors */
	private final FilteredQmTableViewer mainViewer;

	/** A sash that divides the window */
	private final SashForm mainSash;

	/** All sub-tables */
	private final List<ReferenceTableViewer> subElements = new ArrayList<ReferenceTableViewer>();

	/** Constructor. */
	public FactorViewer(Composite parent, int style) {

		// create a sash, which will contain the tree viewer and the sub list
		// views
		mainSash = new SashForm(parent, SWT.HORIZONTAL);

		// create a filtered table viewer for the factors
		mainViewer = new FilteredQmTableViewer(mainSash, style,
				QmPackage.eINSTANCE.getFactor());

		// set a selection listener
		mainViewer
				.addSelectionChangedListener(new SelectionChangedListenerOfMain());
	}

	/** @return the QmTableViewer */
	public FilteredQmTableViewer getMainViewer() {
		return this.mainViewer;
	}

	/** Initialized the UI. */
	public void initialize(EditingDomain editingDomain,
			AdapterFactory adapterFactory) {
		this.editingDomain = editingDomain;
		this.adapterFactory = adapterFactory;

		this.mainViewer.initialize(editingDomain, adapterFactory);

		createColumns();

		// Creates all sub-tables
		createSubTables();

		// Splits the window in 70:30
		mainSash.setWeights(new int[] { 70, 30 });
	}

	/** Create the columns of the table viewer. */
	private void createColumns() {
		// Name of the Factor
		QmTableColumn col = new QmSingleFeatureColumn(mainViewer
				.getQmTableViewer(),
				QmPackage.eINSTANCE.getNamedElement_Name(), "Name",
				EFeatureColumnImage.PARENT, null);
		col.getTableColumn().setWidth(300);

		// Characterized Entity
		new QmSingleFeatureColumn(mainViewer.getQmTableViewer(),
				QmPackage.eINSTANCE.getCharacterizingElement_Characterizes(),
				null, EFeatureColumnImage.NONE, null);

		// Characterized Entity
		new QmListFeatureColumn(mainViewer.getQmTableViewer(),
				QmPackage.eINSTANCE.getFactor_InfluencesDirect(),
				"Impacts", EFeatureColumnImage.NONE, null);

		// Show Tags
		new QmListFeatureColumn(mainViewer.getQmTableViewer(),
				QmPackage.eINSTANCE.getTaggedElement_TaggedBy(), "Tags",
				EFeatureColumnImage.NONE, null);

		// Name of the resource
		new QmResourceColumn(mainViewer.getQmTableViewer());
	}

	/** Invokes all creation methods for the sub-tables. */
	public void createSubTables() {
		ExpandBar expandBar = new ExpandBar(mainSash, SWT.RESIZE);

		addMeasuredByTable(expandBar);
		addRefinedByTable(expandBar);
		addRefinesTable(expandBar);
		addImpactedTable(expandBar);
		addImpactsTable(expandBar);
	}

	/** Sub-Table for impacted factors. */
	private void addImpactsTable(ExpandBar expandBar) {
		// create a reference table viewer
		ReferenceTableViewer impactTableViewer = new ReferenceTableViewer(
				expandBar, SWT.V_SCROLL, QmPackage.eINSTANCE
						.getFactor_Influences());

		createSubTable(expandBar, impactTableViewer, "Influences",
				QmPackage.eINSTANCE.getImpact_Target());
	}

	private void createSubTable(ExpandBar expandBar,
			ReferenceTableViewer referenceTableViewer, String label,
			EReference reference) {
		// initialize it
		referenceTableViewer.initialize(editingDomain, adapterFactory);
		referenceTableViewer.delegateTableViewer.getTable().setHeaderVisible(
				false);

		// set the selection listener
		referenceTableViewer.delegateTableViewer
				.addSelectionChangedListener(new SelectionChangedListenerOfSubElements());
		// add it to the sash
		subElements.add(referenceTableViewer);

		// create a column in it
		QmSingleFeatureColumn column = new QmSingleFeatureColumn(
				referenceTableViewer, reference, label,
				EFeatureColumnImage.PARENT, null);
		column.getTableColumn().setWidth(-1);

		// set the name, with, etc. of the column
		ExpandItem item = new ExpandItem(expandBar, SWT.NONE, 0);
		item.setText(label);
		item.setHeight(referenceTableViewer.delegateTableViewer.getControl()
				.computeSize(100, 120, false).y);
		item.setControl(referenceTableViewer.delegateTableViewer.getControl());
		item.setExpanded(true);
	}

	/** Sub-table for impacting factors. */
	private void addImpactedTable(ExpandBar expandBar) {
		// create a reference table viewer
		ReferenceTableViewer impactedTableViewer = new OppositeReferencesTableViewer(
				expandBar, SWT.V_SCROLL, QmPackage.eINSTANCE.getImpact_Target());

		createSubTable(expandBar, impactedTableViewer, "Influenced By",
				QmPackage.eINSTANCE.getImpact_Source());
	}

	/** Sub-table for refines. */
	private void addRefinesTable(ExpandBar expandBar) {
		// create a reference tabel viewer
		ReferenceTableViewer refinesTableViewer = new ReferenceTableViewer(
				expandBar, SWT.V_SCROLL, QmPackage.eINSTANCE
						.getFactor_Refines());

		createSubTable(expandBar, refinesTableViewer, "Refines",
				QmPackage.eINSTANCE.getFactorRefinement_Parent());
	}

	/** Sub-table for refined by. */
	private void addRefinedByTable(ExpandBar expandBar) {
		// create a reference table viewer
		ReferenceTableViewer refinesTableViewer = new OppositeReferencesTableViewer(
				expandBar, SWT.V_SCROLL, QmPackage.eINSTANCE
						.getFactorRefinement_Parent());

		createSubTable(expandBar, refinesTableViewer, "Refined By",
				QmPackage.eINSTANCE.getFactorRefinement_Child());
	}

	/** Sub-table for measuredby. */
	private void addMeasuredByTable(ExpandBar expandBar) {
		// create a reference table viewer
		ReferenceTableViewer mTableViewer = new OppositeReferencesTableViewer(
				expandBar, SWT.V_SCROLL, QmPackage.eINSTANCE
						.getMeasurement_Parent());

		createSubTable(expandBar, mTableViewer, "Measured By", null);
	}

	/** {@inheritDoc} */
	@Override
	public Control getControl() {
		return mainSash;
	}

	/** {@inheritDoc} */
	@Override
	public Object getInput() {
		return mainViewer.getInput();
	}

	/** {@inheritDoc} */
	@Override
	public ISelection getSelection() {
		return this.mainViewer.getSelection();
	}

	/** {@inheritDoc} */
	@Override
	public void refresh() {
		mainViewer.refresh();
	}

	/** {@inheritDoc} */
	@Override
	protected void inputChanged(Object input, Object oldInput) {
		this.mainViewer.inputChanged(this, input, oldInput);
	}

	/** {@inheritDoc} */
	@Override
	public void setInput(Object input) {
		this.mainViewer.setInput(input);
	}

	/** {@inheritDoc} */
	@Override
	public void setSelection(ISelection selection, boolean reveal) {
		this.mainViewer.setSelection(selection, reveal);
	}

	/** {@inheritDoc} */
	@Override
	protected void fireSelectionChanged(SelectionChangedEvent event) {
		super.fireSelectionChanged(event);
	}

	/** Flag that indicated that the sub-tables are currently updated */
	protected boolean updating = false;

	/** Selection listener, for listening to the main-table. */
	class SelectionChangedListenerOfMain implements ISelectionChangedListener {

		/** {@inheritDoc} */
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			// fire the event
			fireSelectionChanged(event);

			// update all sub-tables
			synchronized (FactorViewer.this) {
				updating = true;
			}
			try {
				StructuredSelection ss = (StructuredSelection) event
						.getSelection();
				Object o = ss.getFirstElement();
				for (ReferenceTableViewer cl : subElements) {
					cl.delegateTableViewer.setInput(o);
				}
			} finally {
				synchronized (FactorViewer.this) {
					updating = false;
				}
			}
		}
	}

	/** Selection-listener for listening to sub-tables. */
	class SelectionChangedListenerOfSubElements implements
			ISelectionChangedListener {

		/** {@inheritDoc} */
		@Override
		public void selectionChanged(final SelectionChangedEvent event) {

			// if the sub-tables are not currently updated, fire the event

			boolean up = false;
			synchronized (FactorViewer.this) {
				up = updating;
			}
			if (!up) {
				fireSelectionChanged(event);
			}
		}
	}

	/** Sorts according to the first column. */
	public void initializeSorting() {
		mainViewer.initializeSorting();
	}

}
