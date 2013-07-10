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

package de.quamoco.qm.help.views;

import java.net.URL;

import org.conqat.ide.commons.ui.selection.SelectionUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;

import de.quamoco.qm.help.QuamocoHelpActivator;
import de.quamoco.qm.provider.util.TransientItemProvider;

/**
 * View to display the help.
 * 
 * @author herrmama
 * @author $Author$
 * @version $Rev$
 * @levd.rating YELLOW Hash: 13439E94ABA74E5766653EEC6989679B
 */
public class QuamocoHelpView extends ViewPart implements ISelectionListener {

	/** The id of the help as specified by the extension. */
	public static final String ID = QuamocoHelpView.class.getName();

	/** The browser to display the help. */
	private Browser browser;

	/** {@inheritDoc} */
	@Override
	public void createPartControl(Composite parent) {
		browser = new Browser(parent, SWT.NONE);
		show("index");

		getSite().getWorkbenchWindow().getSelectionService()
				.addSelectionListener(this);
	}

	/** {@inheritDoc} */
	@Override
	public void dispose() {
		getSite().getWorkbenchWindow().getSelectionService()
				.removeSelectionListener(this);
		super.dispose();
	}

	/** Passing the focus request to the viewer's control. */
	@Override
	public void setFocus() {
		browser.setFocus();
	}

	/** Sets the url for the help files. */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		Object element = SelectionUtils.checkAndPickFirst(selection,
				Object.class);
		if (element != null) {
			if (element instanceof EObject) {
				EObject eObject = (EObject) element;
				while (eObject != null) {
					if (show(eObject.eClass().getName())) {
						return;
					}
					eObject = eObject.eContainer();
				}
			}
			if (element instanceof TransientItemProvider) {
				TransientItemProvider provider = (TransientItemProvider) element;
				if (show(provider.getReference().getEReferenceType().getName())) {
					return;
				}
			}
			if (element instanceof Resource || element instanceof IResource) {
				show("loadingmodels");
				return;
			}
		}

		show("index");
	}

	/**
	 * Show the HTML page with a certain name in the browser. This method
	 * returns true if the HTML page exists.
	 */
	private boolean show(String name) {
		String url = resolveHTMLFile(name);
		if (url != null) {
			browser.setUrl(url);
			return true;
		}
		return false;
	}

	/** Resolve the URL of the HTML page with a certain name. */
	private String resolveHTMLFile(String name) {
		try {
			Bundle bundle = QuamocoHelpActivator.getDefault().getBundle();
			URL url = bundle.getEntry("html/" + name + ".html");
			url = FileLocator.toFileURL(url);
			return url.toExternalForm();
		} catch (Exception e) {
			return null;
		}
	}
}