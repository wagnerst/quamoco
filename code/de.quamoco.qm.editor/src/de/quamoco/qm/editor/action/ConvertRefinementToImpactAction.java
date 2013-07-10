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

import de.quamoco.qm.FactorRefinement;
import de.quamoco.qm.Impact;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.editor.refactoring.Converter;

/**
 * Action to convert a {@link FactorRefinement} to an {@link Impact}.
 * 
 * @author herrmama
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating YELLOW Hash: 7BD266C3A24AF56D7E89E3B6C62E805B
 */
public class ConvertRefinementToImpactAction extends ConverterActionBase {

	/** {@inheritDoc} */
	@Override
	protected void customizeConverter(Converter converter) {
		converter.map(QmPackage.eINSTANCE.getFactorRefinement(),
				QmPackage.eINSTANCE.getImpact());

		converter.map(QmPackage.eINSTANCE.getFactorRefinement_Parent(),
				QmPackage.eINSTANCE.getImpact_Target());
	}
}
