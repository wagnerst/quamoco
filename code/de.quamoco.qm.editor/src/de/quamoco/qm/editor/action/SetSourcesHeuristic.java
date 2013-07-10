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

import org.conqat.ide.commons.ui.selection.SelectionUtils;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import de.quamoco.qm.Entity;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasurementMethod;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.Source;
import de.quamoco.qm.ToolBasedInstrument;
import de.quamoco.qm.editor.CustomQmEditor;

public class SetSourcesHeuristic implements IObjectActionDelegate {
	
	private QualityModel qm;

	/** Editing domain. */
	private EditingDomain domain;

	/** {@inheritDoc} */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		domain = ((IEditingDomainProvider) targetPart).getEditingDomain();
	}
		
	@Override
	public void run(IAction action) {
		ResourceSet rs = qm.eResource().getResourceSet();
		SetSourcesHeuristicCommand command = new SetSourcesHeuristicCommand(rs);
		//domain.getCommandStack().execute(command);
		command.doExecute();
	}

	
	/** {@inheritDoc} */
	public void selectionChanged(IAction action, ISelection selection) {
		qm = SelectionUtils.checkAndPickFirstSafe(selection,
				QualityModel.class);
	}


	private static class SetSourcesHeuristicCommand  {
		
		ResourceSet rs;

		public SetSourcesHeuristicCommand(ResourceSet rs) {
			this.rs=rs;
		}

		public void doExecute() {
			for(Resource res: rs.getResources()) {
				TreeIterator<EObject> i = res.getAllContents();
				while(i.hasNext()) {
					EObject eo = i.next();
					if(eo instanceof Measure) {
						processMeasure((Measure) eo);
					} else if(eo instanceof Entity) {
						processEntity((Entity) eo);
					}
				}
				
			}
		}

		private void processEntity(Entity eo) {
			if(eo.getOriginatesFrom().isEmpty()) {
				return;
			}
			
			String prefix = "\"";
			String suffix = "\" [1]";
			
			String descr = eo.getDescription();
			if(!descr.startsWith(prefix)) {
				descr = prefix + descr;
			}
			if(!descr.endsWith(suffix)) {
				descr = descr + suffix;
			}
			
			eo.setDescription(descr);
	
		}

		private void processMeasure(Measure measure) {
			if(measure.getDeterminedBy().isEmpty()) {
				return;
			}
			
			MeasurementMethod mm = (MeasurementMethod) measure.getDeterminedBy().get(0);
			
			if(mm instanceof ToolBasedInstrument) {
				ToolBasedInstrument tbi = (ToolBasedInstrument) mm;
				
				if("true".equals(tbi.getTool().getAnnotations().get("copied"))) {
					
					System.out.println("Measure: " + measure.getQualifiedName() + " was copied: added " + tbi.getTool().getOriginatesFrom().size());
					
					measure.getOriginatesFrom().addAll(tbi.getTool().getOriginatesFrom());
					tbi.getOriginatesFrom().addAll(tbi.getTool().getOriginatesFrom());
					
					if(measure.getOriginatesFrom().size() < tbi.getTool().getOriginatesFrom().size()) {
						System.err.println("copy failed.");
					}
					
					String prefix = "\"";
					String suffix = "\" [1]";
					
					String descr = measure.getDescription();
					if(!descr.startsWith(prefix)) {
						descr = prefix + descr;
					}
					if(!descr.endsWith(suffix)) {
						descr = descr + suffix;
					}
					
					measure.setDescription(descr);
				}
			}
		}

	}

}
