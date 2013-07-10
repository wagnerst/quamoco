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

package edu.tum.cs.conqat.quamoco;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.conqat.engine.commons.ConQATParamDoc;
import org.conqat.engine.commons.ConQATProcessorBase;
import org.conqat.engine.core.core.AConQATAttribute;
import org.conqat.engine.core.core.AConQATParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.lib.commons.collections.CollectionUtils;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import edu.tum.cs.emf.commons.resources.UUIDResourceFactory;

/**
 * {@ConQAT.Doc}
 * 
 * @author Florian Deissenboeck
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Hash: 043B2FDFA8A7313BA0DD00B210EBD867
 */
@AConQATProcessor(description = "This processor loads Quamoco quality models "
		+ "from disk.")
public class ModelLoader extends ConQATProcessorBase {

	/** Model file names. */
	private HashSet<String> filenames;

	/** Set input filename. */
	@AConQATParameter(name = ConQATParamDoc.INPUT_NAME, minOccurrences = 1, description = ""
			+ "Name of the model file.")
	public void addInputFile(
			@AConQATAttribute(name = "file", description = "Filename") String filename) {
		if (filenames == null) {
			filenames = new HashSet<String>();
		}
		filenames.add(filename);
	}

	/** Load model. */
	@Override
	public QualityModel[] process() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				QuamocoUtils.QUAMOCO_MODEL_FILE_SUFFIX,
				new UUIDResourceFactory());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				QuamocoUtils.QUAMOCO_RESULT_FILE_SUFFIX,
				new UUIDResourceFactory());

		QmPackage.eINSTANCE.getQualityModel();
		QmPackage.eINSTANCE.getResult();

		ResourceSet resourceSet = new ResourceSetImpl();

		for (String filename : filenames) {
			resourceSet.getResource(
					URI.createFileURI(new File(filename).getAbsolutePath()),
					true);
		}

		// TODO (FD): We had this here first: EcoreUtil.resolveAll(resourceSet);
		// However, this causes problems when run on a submodel like C#. If
		// everything was resolved,getFactors() for this model also returns
		// factors defined in an "upper" model.

		// Klaus: I think this is no longer relevant.

		EcoreUtil.resolveAll(resourceSet);

		List<QualityModel> qmList = new LinkedList<QualityModel>();

		for (Resource res : resourceSet.getResources()) {
			TreeIterator<EObject> it = res.getAllContents();
			while (it.hasNext()) {
				EObject eObject = it.next();
				if (eObject instanceof QualityModel) {
					qmList.add((QualityModel) eObject);
				}
			}
		}

		getLogger().debug("Returning " + qmList + " quality models.");

		return CollectionUtils.toArray(qmList, QualityModel.class);

	}
}
