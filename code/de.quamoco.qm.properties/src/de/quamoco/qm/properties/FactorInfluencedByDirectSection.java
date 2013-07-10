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

package de.quamoco.qm.properties;

import de.quamoco.qm.QmPackage;
import edu.tum.cs.emf.commons.sections.OppositeReferencePropertySectionBase;

/**
 * The section to show the "influencesDirect" references targeting a certain
 * "Factor".
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: C216C630777FA377401399ABF5058CD1
 */
public class FactorInfluencedByDirectSection extends
		OppositeReferencePropertySectionBase {

	/** Constructor. */
	public FactorInfluencedByDirectSection() {
		super(QmPackage.eINSTANCE.getFactor_InfluencesDirect(),
				"Influenced By");
	}
}
