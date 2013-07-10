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

package de.quamoco.qm.editor.export;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.conqat.lib.commons.string.StringUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.quamoco.qm.QmFactory;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.Tag;
import de.quamoco.qm.TaggedElement;
import de.quamoco.qm.util.QmModelUtils;

/**
 * Class for merging a number of quality models into a single quality model.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating GREEN Hash: 20D4168FA41DD377B9CF1C22D09EA6FF
 */
public class QualityModelMerger {

	/** Merge a number of quality models. */
	public Resource merge(ResourceSet resourceSet) {
		List<QualityModel> models = QmModelUtils.getQualityModels(resourceSet);
		Collection<QualityModel> copies = EcoreUtil.copyAll(models);

		QualityModel targetModel = QmFactory.eINSTANCE.createQualityModel();
		targetModel.setName("Merged");
		String description = "Merged from the following modules:"
				+ StringUtils.CR;
		for (QualityModel sourceModel : copies) {
			description += "- " + sourceModel.getName() + StringUtils.CR;
		}
		targetModel.setDescription(description);

		ResourceSet targetResourceSet = new ResourceSetImpl();
		Resource resource = targetResourceSet.createResource(URI
				.createFileURI("result.qm"));
		resource.getContents().add(targetModel);
		resource.getContents().addAll(copies);

		for (QualityModel sourceModel : copies) {
			tagContents(sourceModel, targetModel);
			moveContents(sourceModel, targetModel);
		}

		resource.getContents().removeAll(copies);

		return resource;
	}

	/** Tag contents of a quality model. */
	private void tagContents(QualityModel sourceModel, QualityModel targetModel) {
		Tag tag = QmFactory.eINSTANCE.createTag();
		tag.setName(sourceModel.getName());
		tag.setDescription(sourceModel.getDescription());
		targetModel.getTags().add(tag);

		for (Iterator<EObject> i = sourceModel.eAllContents(); i.hasNext();) {
			EObject element = i.next();
			if (element instanceof TaggedElement) {
				((TaggedElement) element).getTaggedBy().add(tag);
			}
		}
	}

	/** Move contents from a source to a target element. */
	@SuppressWarnings("unchecked")
	private void moveContents(EObject source, EObject target) {
		for (EReference reference : source.eClass().getEAllContainments()) {
			if (reference.isDerived()
					|| !reference.isMany()
					|| !reference.getEContainingClass().isSuperTypeOf(
							target.eClass())) {
				continue;
			}
			List<EObject> sourceValues = new ArrayList<EObject>(
					(List<EObject>) source.eGet(reference));
			List<EObject> targetValues = (List<EObject>) target.eGet(reference);
			targetValues.addAll(sourceValues);
		}
	}
}
