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

import java.util.ArrayList;
import java.util.List;

import org.conqat.ide.commons.ui.selection.SelectionUtils;
import org.conqat.ide.commons.ui.swt.ClipboardUtils;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import de.quamoco.qm.Factor;
import de.quamoco.qm.Measure;


public class ExportAllSubMeasures implements IObjectActionDelegate {

		/** The editing domain */
		private EditingDomain domain;

		/** The factor that elements will be exported */
		private Factor element;

		/**
		 * {@inheritDoc}
		 */
		public void selectionChanged(IAction action, ISelection selection) {
			element = SelectionUtils.checkAndPickFirstSafe(selection, Factor.class);
			if (element == null) {
				return;
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void run(IAction action) {
			List<Measure> measures = new ArrayList<Measure>();
			export(element, measures);
			
			String text = "";
			for(Measure m : measures) {
				text += m.getQualifiedName() + "\r\n";
			}
			
			ClipboardUtils.copyToClipboard(text);
		}

		/** Adds all measuers of 'factor' to 'measures' and calls
		 * itself for all refining factors.
		 * @param factor
		 * @param measures
		 */
		private void export(Factor factor,  List<Measure> measures) {
			
			measures.addAll(factor.getMeasuredByDirect());
			for(Measure m: factor.getMeasuredByDirect()) {
				export(m,measures);
			}
			
			for(Factor ref: factor.getRefinedByDirect()) {
				export(ref, measures);
			}
		}
		
		/**
		 * Exports measures and its refinements
		 * @param measure
		 * @param measures
		 */
		private void export(Measure measure,  List<Measure> measures) {
			measures.addAll(measure.getRefinedByDirect());
			for(Measure m: measure.getRefinedByDirect()) {
				export(m, measures);
			}
		}

		/**
		 * {@inheritDoc}
		 */
		public void setActivePart(IAction action, IWorkbenchPart targetPart) {
			domain = ((IEditingDomainProvider) targetPart).getEditingDomain();
		}
		
		
	
}
