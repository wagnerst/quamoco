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

package de.quamoco.qm.editor.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.ChangeCommand;

import de.quamoco.qm.Factor;

/**
 * Command for deleting the factor and its refining factors.
 * 
 * @author lochmann
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 21899B89AC7F1557B6105C418AA7D9B0
 */
public class DeleteFactorCommand extends ChangeCommand {

	/** The source that should be added to all elements. */
	private final Factor factor;

	/** Constructor. */
	public DeleteFactorCommand(Factor factor) {
		super(factor.eResource().getResourceSet());

		this.factor = factor;
	}

	/** {@inheritDoc} */
	@Override
	protected void doExecute() {
		deleteAllRefiningFactors(factor);
	}

	/** Delete a factor and all sub-factors regarding the refines relation. */
	private void deleteAllRefiningFactors(Factor factor) {
		List<Factor> factors = new ArrayList<Factor>();
		factors.addAll(factor.getRefinedByDirect());
		for (Factor subFactor : factors) {
			deleteAllRefiningFactors(subFactor);
		}
		EcoreUtil.delete(factor);
	}
}