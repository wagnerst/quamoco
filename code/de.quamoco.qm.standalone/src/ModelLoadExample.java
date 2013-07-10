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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import de.quamoco.qm.Entity;
import de.quamoco.qm.Factor;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import edu.tum.cs.emf.commons.resources.UUIDResourceFactory;

/**
 * Example to programmatically load and navigate a model
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class ModelLoadExample {

	/**
	 * main method
	 */
	public static void main(String[] args) throws IOException {

		// load the model
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("qm",
				new UUIDResourceFactory());
		QmPackage.eINSTANCE.getQualityModel();
		ResourceSet resourceSet = new ResourceSetImpl();

		Resource resource = resourceSet.createResource(URI
				.createFileURI("format.qm"));

		resource.load(null);

		// navigate the model
		QualityModel qm = (QualityModel) resource.getContents().get(0);

		for (Entity e : qm.getEntities()) {
			System.out.println("Entity " + e.getName() + " "
					+ resource.getURIFragment(e));
		}

		for (Factor f : qm.getFactors()) {
			System.out.println("Factor " + f.getName() + " "
					+ resource.getURIFragment(f));
			if (f.getCharacterizes() != null) {
				System.out.println("  characterizes Entity "
						+ f.getCharacterizes().getName());
			}
		}
	}
}
