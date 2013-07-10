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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import de.quamoco.qm.Entity;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FactorRefinement;
import de.quamoco.qm.QmFactory;
import de.quamoco.qm.util.QmModelUtils;
import edu.tum.cs.eclipse.commons.selection.SelectionUtils;

public class InheritAllDecompositionsAction implements IObjectActionDelegate {

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

		for (FactorRefinement refines : factor.getRefines()) {
			if (QmModelUtils.getRefinementType(refines) == de.quamoco.qm.util.QmModelUtils.RefinementType.REFINE) {
				Factor refinesFactor = refines.getChild();

				List<Factor> allDecompositions = new ArrayList<Factor>();
				for (FactorRefinement decomposes : refinesFactor.getRefines()) {
					if (QmModelUtils.getRefinementType(decomposes) == de.quamoco.qm.util.QmModelUtils.RefinementType.DECOMPOSE) {
						allDecompositions.add(decomposes.getParent());
					}
				}
				
				System.out.print("All decompositions that have to be added: " );

				for (Factor decompFactor : allDecompositions) {
					System.out.println(decompFactor.getQualifiedName());
					
					Factor requiredDecomposition = Utils.searchForFactor(
							domain, decompFactor.getName(), factor
									.getCharacterizes());
					if (requiredDecomposition == null) {
						requiredDecomposition = QmFactory.eINSTANCE
								.createFactor();
						requiredDecomposition.setName(decompFactor.getName());
						requiredDecomposition.setCharacterizes(factor
								.getCharacterizes());
						factor.getQualityModel().getFactors().add(
								requiredDecomposition);
					}

					if (!factor.getRefinedByDirect().contains(
							requiredDecomposition)) {
						factor.getRefinedByDirect().add(requiredDecomposition);
					}
				}
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
