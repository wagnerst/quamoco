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

package de.quamoco.adaptation.view.listener;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;

import de.quamoco.qm.QualityModel;
import de.quamoco.qm.presentation.QmEditor;

/**
 * Implements the linkage to the currently active {@link QmEditor}.
 * @author Franz Becker
 */
public class PartListener implements IPartListener2 {

	/** The viewer of this view. */
	private final TreeViewer viewer;
	
	/** The current {@link QmEditor}, set only using {@link #setCurrentEditor(QmEditor)}! */
	private QmEditor currentEditor;
	
	/**
	 * Initializes the fields.
	 * @param viewer value for {@link #viewer}
	 */
	public PartListener(TreeViewer viewer) {
		this.viewer = viewer;
	}

	/**
	 * Checks if the activated part is an {@link QmEditor}, if yes set the underlying 
	 * {@link QualityModel} (more accurate: the first one that could be found within
	 * it's {@link ResourceSet} as input for the {@link #viewer}.
	 * @see #setCurrentEditor(QmEditor)
	 */
	@Override
	public void partActivated(IWorkbenchPartReference partRef) {
		IWorkbenchPart part = partRef.getPart(false);
		if (part instanceof QmEditor) {
			setCurrentEditor((QmEditor) part);
		} else {
			// some other part was activated
			if (part instanceof IEditorPart) {
				// some other editor was activated => clear content of view
				setCurrentEditor(null);
			}
		}		
	}

	/**
	 * Updates {@link #currentEditor} if the passed editor is not the old one
	 * and then sets the {@link #viewer}'s input to the first {@link QualityModel} 
	 * that could be found within the {@link ResourceSet} of the passed {@link QmEditor}.
	 * @param newEditor
	 */
	public void setCurrentEditor(QmEditor newEditor) {		
		if (currentEditor != newEditor) {	// trigger update only if editor has changed
			currentEditor = newEditor;
			try {
				if (currentEditor != null) {
					ResourceSet resourceSet = currentEditor.getEditingDomain().getResourceSet();
					List<QualityModel> qualityModels = getQualityModels(resourceSet);
					if (qualityModels.size() > 0) {			
						if (qualityModels.size() == 1) {
							// if only one model, no need for grouping by model
							viewer.setInput(qualityModels.get(0));
						} else {
							// TODO viewer cannot handle more than one model currently
							viewer.setInput(qualityModels);
						}						
					}					
				} else {
					viewer.setInput(null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
	
	/**
	 * Iterates over the passed {@link ResourceSet} and returns all included
	 * {@link QualityModel}s (assuming that there are no nested {@link QualityModel}s. 
	 * @param resourceSet the {@link ResourceSet} of interest
	 * @return all {@link QualityModel} within the {@link ResourceSet}
	 */
	private List<QualityModel> getQualityModels(ResourceSet resourceSet) {
		List<QualityModel> qualityModels = new LinkedList<QualityModel>();
		for (Resource resource : resourceSet.getResources()) {
			for (EObject eObject : resource.getContents()) {
				if (eObject instanceof QualityModel) {
					qualityModels.add((QualityModel) eObject);
				}
			}
		}
		return qualityModels;
	}

	/**
	 * If the {@link #currentEditor} has been closed reset the viewer.
	 */
	@Override
	public void partClosed(IWorkbenchPartReference partRef) {
		IWorkbenchPart part = partRef.getPart(false);
		if (part == currentEditor) {
			setCurrentEditor(null);
		}
	}
	
	@Override
	public void partDeactivated(IWorkbenchPartReference partRef) {		
	}	
	
	@Override
	public void partBroughtToTop(IWorkbenchPartReference partRef) {		
	}

	@Override
	public void partHidden(IWorkbenchPartReference partRef) {		
	}

	@Override
	public void partInputChanged(IWorkbenchPartReference partRef) {		
	}

	@Override
	public void partOpened(IWorkbenchPartReference partRef) {		
	}

	@Override
	public void partVisible(IWorkbenchPartReference partRef) {		
	}

}
