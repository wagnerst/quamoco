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

package de.quamoco.qm.search.ui.providers;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.search.ui.providers.AbstractMetaModelParticipantsItemProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import de.quamoco.qm.QmPackage;

public class MetaModelParticipantsItemProvider extends
		AbstractMetaModelParticipantsItemProvider {
	private Collection<String> nsURIs;

	public MetaModelParticipantsItemProvider(Collection<String> nsURIs) {
		this.nsURIs = nsURIs;
	}

	public Object[] getElements(Object inputElement) {
		List<EClassifier> eClassifierList = new ArrayList<EClassifier>();

		if (nsURIs.contains(QmPackage.eNS_URI)) {
			eClassifierList.addAll(QmPackage.eINSTANCE.getEClassifiers());
		}

		return eClassifierList.toArray();
	}
}
