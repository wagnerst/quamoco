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
 | de.quamoco.qm.guideline.docbook
 |                                                                       |
   $Id: GenerateGuidelineAction.java 4974 2012-02-17 09:34:10Z lochmann $            
 |                                                                       |
 | Copyright (c)  2004-2009 Technische Universitaet Muenchen             |
 |                                                                       |
 | Technische Universitaet Muenchen               #########  ##########  |
 | Institut fuer Informatik - Lehrstuhl IV           ##  ##  ##  ##  ##  |
 | Prof. Dr. Manfred Broy                            ##  ##  ##  ##  ##  |
 | Boltzmannstr. 3                                   ##  ##  ##  ##  ##  |
 | 85748 Garching bei Muenchen                       ##  ##  ##  ##  ##  |
 | Germany                                           ##  ######  ##  ##  |
 +-----------------------------------------------------------------------*/
package de.quamoco.qm.guideline.docbook;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import de.quamoco.qm.QualityModel;
import de.quamoco.qm.presentation.QmEditor;

/**
 * The Action for generating a guideline for multiple {@link QualityModel}s.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 6036CC582D06229BCC44826432078615
 */
public class GenerateGuidelineAction implements IEditorActionDelegate {

	/**
	 * The currently active {@link IEditorPart}, the {@link QmEditor}.
	 */
	private QmEditor targetEditor;

	/** {@inheritDoc} */
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		this.targetEditor = (QmEditor) targetEditor;
	}

	/** {@inheritDoc} */
	public void run(IAction action) {
		ResourceSet resourceSet = targetEditor.getEditingDomain()
				.getResourceSet();
		GuidelineWizard wizard = new GuidelineWizard(resourceSet);
		WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), wizard);
		dialog.open();
	}

	/** {@inheritDoc} */
	public void selectionChanged(IAction action, ISelection selection) {
		// not required
	}

}
