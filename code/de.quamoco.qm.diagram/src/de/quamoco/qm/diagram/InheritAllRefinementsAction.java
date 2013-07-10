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

package de.quamoco.qm.diagram;

import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.Entity;
import de.quamoco.qm.Factor;
import de.quamoco.qm.QmFactory;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.impl.AnnotationImpl;
import edu.tum.cs.eclipse.commons.selection.SelectionUtils;
import edu.tum.cs.emf.commons.commands.WrappingCompoundCommand;

/**
 * Inherits all factors that must refine the selected Factor. These factors are
 * generated based on the is-a hiearchy of entities.
 * 
 * @author lochmann
 * 
 */
public class InheritAllRefinementsAction implements IObjectActionDelegate {

	/**
	 * Annotated element
	 */
	private Factor factor;

	/**
	 * Editing domain
	 */
	private EditingDomain domain;

	/**
	 * {@inheritDoc}
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		domain = ((IEditingDomainProvider) targetPart).getEditingDomain();
	}

	/**
	 * {@inheritDoc}
	 */
	public void run(IAction action) {

		if (factor == null) {
			return;
		}

		Entity e = factor.getCharacterizes();
		if (e == null) {
			return;
		}

		for (Entity sub : e.getSpecializedByDirect()) {
			Factor refFactor = Utils.searchForFactor(domain, factor.getName(),
					sub);
			if (refFactor == null) {
				refFactor = QmFactory.eINSTANCE.createFactor();
				refFactor.setName(factor.getName());
				refFactor.setCharacterizes(sub);
				factor.getQualityModel().getFactors().add(refFactor);
			}

			if (!factor.getRefinedByDirect().contains(refFactor)) {
				factor.getRefinedByDirect().add(refFactor);
			}

		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		factor = SelectionUtils.checkAndPickFirstSafe(selection, Factor.class);
	}

}
