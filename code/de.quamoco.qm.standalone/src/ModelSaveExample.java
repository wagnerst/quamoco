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

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import de.quamoco.qm.Entity;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FactorRefinement;
import de.quamoco.qm.QmFactory;
import de.quamoco.qm.QualityModel;
import edu.tum.cs.emf.commons.resources.UUIDResourceFactory;

/**
 * Example to programmatically create and save a model
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class ModelSaveExample {

	/**
	 * main method
	 */
	public static void main(String[] args) throws IOException {

		// create the model
		QualityModel qm = QmFactory.eINSTANCE.createQualityModel();

		Factor p = QmFactory.eINSTANCE.createFactor();
		p.setName("Redundancy");
		qm.getFactors().add(p);

		Entity e = QmFactory.eINSTANCE.createEntity();
		e.setName("Code");
		qm.getEntities().add(e);

		Factor f = QmFactory.eINSTANCE.createFactor();
		f.setCharacterizes(e);
		qm.getFactors().add(f);

		FactorRefinement r = QmFactory.eINSTANCE.createFactorRefinement();
		r.setChild(f);
		r.setParent(p);

		// save the model
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("qm",
				new UUIDResourceFactory());
		ResourceSet resourceSet = new ResourceSetImpl();

		Resource resource = resourceSet.createResource(URI
				.createFileURI("format.qm"));
		resource.getContents().add(qm);

		resource.save(Collections.EMPTY_MAP);
	}
}
