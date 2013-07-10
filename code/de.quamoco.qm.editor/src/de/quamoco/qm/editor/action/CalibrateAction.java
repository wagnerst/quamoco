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

package de.quamoco.qm.editor.action;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.conqat.ide.commons.ui.selection.SelectionUtils;
import org.conqat.lib.commons.collections.CollectionUtils;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import de.quamoco.qm.QualityModel;
import de.quamoco.qm.editor.commands.CalibrateCommand;
import edu.tum.cs.conqat.quamoco.qiesl.ModelCalibrator;

/**
 * Calibrates the Model using calibration data written by the Evaluation
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating YELLOW Hash: F35F4E5B5ED41B23061B029D9B9B846C
 */
public class CalibrateAction implements IObjectActionDelegate {

	/** Model element. */
	private QualityModel element;

	/** Editing domain. */
	private EditingDomain domain;

	/** {@inheritDoc} */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		domain = ((IEditingDomainProvider) targetPart).getEditingDomain();
	}

	/** {@inheritDoc} */
	public void run(IAction action) {
		try {
			FileDialog dialog = new FileDialog(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell(), SWT.OPEN
					| SWT.MULTI);
			dialog.setText("Open calibration files");
			dialog.setFilterExtensions(new String[] { "*.txt", "*.*" });
			dialog.open();
			String[] files = dialog.getFileNames();

			if (files.length == 0) {
				return;
			}

			ModelCalibrator calibrator = new ModelCalibrator(getModels(element));

			for (String filename : files) {
				Properties properties = new Properties();
				BufferedInputStream in = new BufferedInputStream(
						new FileInputStream(dialog.getFilterPath() + "/"
								+ filename));
				try {
					properties.load(in);
					calibrator.addCalibrationData(properties);
				} finally {
					in.close();
				}
			}

			Command command = new CalibrateCommand(calibrator);
			domain.getCommandStack().execute(command);
		} catch (IOException e) {
			MessageDialog.openError(Display.getDefault().getActiveShell(),
					"Error", "Error: " + e);
		}

	}

	private QualityModel[] getModels(QualityModel element2) {
		ResourceSet resourceSet =element2.eResource().getResourceSet();
		
		EcoreUtil.resolveAll(resourceSet);

		List<QualityModel> qmList = new LinkedList<QualityModel>();

		for (Resource res : resourceSet.getResources()) {
			TreeIterator<EObject> it = res.getAllContents();
			while (it.hasNext()) {
				EObject eObject = it.next();
				if (eObject instanceof QualityModel) {
					qmList.add((QualityModel) eObject);
				}
			}
		}
		
		return CollectionUtils.toArray(qmList, QualityModel.class);
	}

	/** {@inheritDoc} */
	public void selectionChanged(IAction action, ISelection selection) {
		EObject obj = SelectionUtils.checkAndPickFirst(selection,
				EObject.class);
		if(QualityModel.class.isInstance(obj))
			element = (QualityModel) obj; 
	}
}
