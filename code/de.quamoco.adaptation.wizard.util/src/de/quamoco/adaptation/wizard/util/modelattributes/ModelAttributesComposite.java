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

package de.quamoco.adaptation.wizard.util.modelattributes;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import de.quamoco.adaptation.model.AdaptationModel;
import de.quamoco.adaptation.model.Purpose;
import de.quamoco.adaptation.model.Wizard;
import de.quamoco.adaptation.util.qm.Activator;
import de.quamoco.adaptation.util.swt.SWTUtil;
import de.quamoco.adaptation.wizard.util.modelattributes.provider.ContextComboContentProvider;
import de.quamoco.adaptation.wizard.util.modelattributes.provider.ContextListContentProvider;
import de.quamoco.adaptation.wizard.util.modelattributes.provider.NamedElementLabelProvider;
import de.quamoco.adaptation.wizard.util.modelattributes.provider.ObjectContentProvider;
import de.quamoco.adaptation.wizard.util.modelattributes.provider.PurposeContentProvider;
import de.quamoco.adaptation.wizard.util.modelattributes.provider.QualityFocusContentProvider;
import de.quamoco.adaptation.wizard.util.modelattributes.provider.ViewpointContentProvider;
import de.quamoco.qm.QualityModel;

// TODO refresh ALL comments!
/**

 * @author Franz Becker

 * @see #addModifyListener(Listener)
 * @levd.rating YELLOW Hash: 4A22C487750ABC72AD3293AC6B388C20
 */
public class ModelAttributesComposite extends Composite {

	protected Wizard wizard;
	
	/** The chosen context */
	protected Set<String> context = new HashSet<String>();
	
	/** The added modify listener */
	protected List<Listener> modifyListener = new LinkedList<Listener>();
	
	/*
	 * Visual controls
	 */
	protected Text txtName;
	protected Text txtDescription;
	protected ComboViewer cbObject;
	protected ComboViewer cbViewpoint;
	protected ComboViewer cbQualityFocus;
	protected ComboViewer cbPurpose;
	protected ComboViewer cbContext;
	protected ListViewer lwContext;
	
	/*
	 * Filter buttons
	 */
	protected ToolItem btnFilterName;
	protected ToolItem btnFilterDescription;	
	protected ToolItem btnFilterObject;
	protected ToolItem btnFilterViewpoint;
	protected ToolItem btnFilterQualityFocus;
	protected ToolItem btnFilterPurpose;
	protected ToolItem btnFilterContext;

	/**

	 * @param parent the parent of this composite
	 * @param wizard the {@link Wizard} given by the {@link AdaptationModel}
	 */
	public ModelAttributesComposite(Composite parent, Wizard wizard) {
		super(parent, SWT.None);
		this.wizard = wizard;
		createControls();	
	}

	/**
	 * Creates the visual controls embedded in this composite,
	 * depending on the {@link #style} attribute.
	 */
	protected void createControls() {
	
		this.setLayout(new GridLayout(3, false));

		/* Name row */
		Label lblName = new Label(this, SWT.NONE);
		lblName.setText("Name:");
		txtName = new Text(this, SWT.NONE);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		btnFilterName = createFilterButton();			

		/* Description row */
		Label lblDescription = new Label(this, SWT.NONE);
		lblDescription.setText("Description:");		
		txtDescription = new Text(this, SWT.MULTI | SWT.V_SCROLL);		
		lblDescription.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		GridData layoutTxtDescription = new GridData(SWT.FILL, SWT.FILL, true, false);
		layoutTxtDescription.heightHint = 55;
		txtDescription.setLayoutData(layoutTxtDescription);
		btnFilterDescription = createFilterButton();
		
		/* Object row */
		Label lblObject = new Label(this, SWT.NONE);
		lblObject.setText("Object:");
		cbObject = new ComboViewer(this, SWT.NONE);		
		cbObject.setContentProvider(new ObjectContentProvider(wizard.getAttributesSettingPage()));
		cbObject.setLabelProvider(new NamedElementLabelProvider());		
		cbObject.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		cbObject.setSorter(new ViewerSorter()); // default sorter (by name)
		btnFilterObject = createFilterButton();
		
		/* Viewpoint row */
		Label lblViewpoint = new Label(this, SWT.NONE);
		lblViewpoint.setText("Viewpoint:");
		cbViewpoint = new ComboViewer(this, SWT.NONE);
		cbViewpoint.setContentProvider(new ViewpointContentProvider(wizard.getAttributesSettingPage()));
		cbViewpoint.setLabelProvider(new NamedElementLabelProvider());		
		cbViewpoint.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		cbViewpoint.setSorter(new ViewerSorter());
		btnFilterViewpoint = createFilterButton();
		
		/* Quality Focus row */
		Label lblQualityFocus = new Label(this, SWT.NONE);
		lblQualityFocus.setText("Quality Focus:");
		cbQualityFocus = new ComboViewer(this, SWT.NONE);
		cbQualityFocus.setContentProvider(new QualityFocusContentProvider(wizard.getAttributesSettingPage(), cbViewpoint));
		cbQualityFocus.setLabelProvider(new NamedElementLabelProvider());		
		cbQualityFocus.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		cbQualityFocus.setSorter(new ViewerSorter());
		btnFilterQualityFocus = createFilterButton();
		// Call update method when viewpoint has changed
		cbViewpoint.addPostSelectionChangedListener(new ISelectionChangedListener() {			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				cbQualityFocus.refresh();				
			}
		});
		
		/* Purpose row */
		Label lblPurpose = new Label(this, SWT.NONE);
		lblPurpose.setText("Purpose:");
		cbPurpose = new ComboViewer(this, SWT.READ_ONLY);
		cbPurpose.setContentProvider(new PurposeContentProvider(wizard));
		cbPurpose.setLabelProvider(new NamedElementLabelProvider());		
		cbPurpose.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		cbPurpose.setSorter(new ViewerSorter());
		btnFilterPurpose = createFilterButton();
		
		/* Context row */
		Label lblContext = new Label(this, SWT.NONE);
		lblContext.setText("Context:");
		lblContext.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
	
		Composite contextComposite = new Composite(this, SWT.NONE);
		contextComposite.setLayout(SWTUtil.createNoMarginGridLayout(1, false));
		contextComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		lwContext = new ListViewer(contextComposite);
		lwContext.setContentProvider(new ContextListContentProvider());
		lwContext.setLabelProvider(new NamedElementLabelProvider());
		lwContext.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		lwContext.setSorter(new ViewerSorter());
		lwContext.setInput(context);
		lwContext.getList().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.DEL) {
					IStructuredSelection selection = (IStructuredSelection) lwContext.getSelection();
					if (selection != null && !selection.isEmpty()) {
						for (Iterator<?> iterator = selection.iterator(); iterator.hasNext();) {
							Object next = iterator.next();
							context.remove(next);
							cbContext.add(next);
						}
						lwContext.refresh();
						notifyListener();
					}
				}
			}			
		});
		
		Composite cbContextComposite = new Composite(contextComposite, SWT.NONE);
		cbContextComposite.setLayout(SWTUtil.createNoMarginGridLayout(2, false));
		cbContextComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		cbContext = new ComboViewer(cbContextComposite, SWT.NONE);
		cbContext.setContentProvider(new ContextComboContentProvider(context));
		cbContext.setLabelProvider(new NamedElementLabelProvider());		
		cbContext.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		cbContext.setSorter(new ViewerSorter());		
		Button btnContextAdd = new Button(cbContextComposite, SWT.PUSH);
		btnContextAdd.setText("Add");
		btnContextAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addContext();
			}			
		});
		cbContext.getCombo().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR) {
					addContext();
				}
			}
		});
		
		btnFilterContext = createFilterButton();		
	}
	
	/**
	 * Performs the action of the "add" button for a context selected
	 * in the combo box. This is done in this utility method cause both
	 * the button and the key listener (when pressing enter) perform the
	 * same activity.
	 */
	protected void addContext() {
		String comboText = cbContext.getCombo().getText();
		if (comboText != null && !"".equals(comboText)) {
			context.add(comboText);			
			cbContext.remove(comboText);
			cbContext.getCombo().setText("");
			lwContext.refresh();
			notifyListener();
		}
	}
	
	/**
	 * Creates a filter button.
	 */
	protected ToolItem createFilterButton() {		
		ToolBar toolBar = new ToolBar(this, SWT.NONE);
		final ToolItem filterButton = new ToolItem(toolBar, SWT.CHECK);
		filterButton.setToolTipText("Filter the quality models in the workspace by this criterion.");
		filterButton.setImage(Activator.getImageDescriptor("icons/filter.gif").createImage());
		return filterButton;		
	}
	
	
	/**
	 * @param qualityModels the {@link QualityModel}s that shall be set as input.
	 */
	public void setInput(Collection<QualityModel> qualityModels) {
		cbObject.setInput(qualityModels);
		cbViewpoint.setInput(qualityModels);
		cbQualityFocus.setInput(qualityModels);
		cbPurpose.setInput(qualityModels);	
		cbContext.setInput(qualityModels);
	}	
		
	/**
	 * @return the text of the name field
	 */
	public String getAttributeName() {
		return txtName.getText();		
	}
	
	/**
	 * Sets the text of the name field
	 * @param name the new text
	 */
	public void setAttributeName(String name) {
		txtName.setText(name);
	}
	
	/**
	 * @return the text of the description field
	 */
	public String getAttributeDescription() {
		return txtDescription.getText();
	}
	
	/**
	 * @return the text of the object field
	 */	
	public String getAttributeObject() {
		return cbObject.getCombo().getText();
	}	
	
	/**
	 * @return the text of the viewpoint field
	 */	
	public String getAttributeViewpoint() {
		return cbViewpoint.getCombo().getText();
	}	
	
	/**
	 * @return the text of the quality focus field
	 */
	public String getAttributeQualityFocus() {
		return cbQualityFocus.getCombo().getText();
	}
	
	/**
	 * @return the text of the purpose field
	 */
	public Purpose getAttributePurpose() {
		return (Purpose) ((IStructuredSelection) cbPurpose.getSelection()).getFirstElement();
	}
	
	/**
	 * @return the text of the context field
	 */
	public Set<String> getAttributeContext() {
		return context;
	}
	
	/**
	 * @return true if name shall by used for filtering, false otherwise
	 */
	public boolean isFilterByName() {
		return btnFilterName.getSelection();
	}

	/**
	 * @return true if description shall by used for filtering, false otherwise
	 */
	public boolean isFilterByDescription() {
		return btnFilterDescription.getSelection();
	}

	/**
	 * @return true if object shall by used for filtering, false otherwise
	 */
	public boolean isFilterByObject() {
		return btnFilterObject.getSelection();
	}
	
	/**
	 * @return true if viewpoint shall by used for filtering, false otherwise
	 */
	public boolean isFilterByViewpoint() {
		return btnFilterViewpoint.getSelection();
	}
	
	/**
	 * @return true if quality focus shall by used for filtering, false otherwise
	 */
	public boolean isFilterByQualityFocus() {
		return btnFilterQualityFocus.getSelection();
	}

	/**
	 * @return true if purpose shall by used for filtering, false otherwise
	 */
	public boolean isFilterByPurpose() {
		return btnFilterPurpose.getSelection();
	}

	/**
	 * @return true if context shall by used for filtering, false otherwise
	 */
	public boolean isFilterByContext() {
		return btnFilterContext.getSelection();
	}

	/**
	 * Adds the passed listener as modify listener to all embedded controls.
	 * @param listener the listener that shall be notified when any embedded
	 * control was modified.
	 */
	public void addModifyListener(Listener listener) {
		modifyListener.add(listener);
		txtName.addListener(SWT.Modify, listener);
		txtDescription.addListener(SWT.Modify, listener);
		lwContext.getList().addListener(SWT.Modify, listener);
		cbObject.getControl().addListener(SWT.Modify, listener);
		cbQualityFocus.getControl().addListener(SWT.Modify, listener);
		cbPurpose.getControl().addListener(SWT.Modify, listener);
		btnFilterName.addListener(SWT.Selection, listener);
		btnFilterContext.addListener(SWT.Selection, listener);
		btnFilterPurpose.addListener(SWT.Selection, listener);
		btnFilterViewpoint.addListener(SWT.Selection, listener);
		btnFilterQualityFocus.addListener(SWT.Selection, listener);
		btnFilterObject.addListener(SWT.Selection, listener);
		btnFilterDescription.addListener(SWT.Selection, listener);
	}

	/**
	 * Specifies when the control is valid
	 * @return true if the control is valid (everything's filled out)
	 */
	public boolean isValid() {
		String name = getAttributeName();
//		String description = getAttributeDescription();		
//		String object = getAttributeObject();
//		String qualityFocus = getAttributeQualityFocus();
//		Purpose purpose = getAttributePurpose();
//		List<String> context = getAttributeContext();
		
		return name != null && !name.isEmpty();
//			&& description != null && !description.isEmpty()
//			&& object != null && !object.isEmpty()
//			&& qualityFocus != null && !qualityFocus.isEmpty()
//			&& purpose != null 
//			&& context != null & !context.isEmpty();
	}

	/**
	 * Notifies the registered modify listeners
	 */
	protected void notifyListener() {
		for (Listener listener : modifyListener) {
			listener.handleEvent(new Event());
		}
	}
	
}
