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

package de.quamoco.qm.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

import de.quamoco.qm.Entity;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FactorRefinement;
import de.quamoco.qm.Impact;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.Specialization;
import de.quamoco.qm.diagram.edit.parts.EntityEditPart;
import de.quamoco.qm.diagram.edit.parts.FactorEditPart;
import de.quamoco.qm.diagram.edit.parts.FactorRefinementEditPart;
import de.quamoco.qm.diagram.edit.parts.ImpactEditPart;
import de.quamoco.qm.diagram.edit.parts.QualityModelEditPart;
import de.quamoco.qm.diagram.edit.parts.SpecializationEditPart;
import de.quamoco.qm.diagram.providers.QmElementTypes;

/**
 * @generated
 */
public class QmDiagramUpdater {

	/**
	 * @generated
	 */
	public static List getSemanticChildren(View view) {
		switch (QmVisualIDRegistry.getVisualID(view)) {
		case QualityModelEditPart.VISUAL_ID:
			return getQualityModel_1000SemanticChildren(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getQualityModel_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		QualityModel modelElement = (QualityModel) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getFactors().iterator(); it.hasNext();) {
			Factor childElement = (Factor) it.next();
			int visualID = QmVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == FactorEditPart.VISUAL_ID) {
				result.add(new QmNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getEntities().iterator(); it.hasNext();) {
			Entity childElement = (Entity) it.next();
			int visualID = QmVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == EntityEditPart.VISUAL_ID) {
				result.add(new QmNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getContainedLinks(View view) {
		switch (QmVisualIDRegistry.getVisualID(view)) {
		case QualityModelEditPart.VISUAL_ID:
			return getQualityModel_1000ContainedLinks(view);
		case FactorEditPart.VISUAL_ID:
			return getFactor_2006ContainedLinks(view);
		case EntityEditPart.VISUAL_ID:
			return getEntity_2007ContainedLinks(view);
		case FactorRefinementEditPart.VISUAL_ID:
			return getFactorRefinement_4010ContainedLinks(view);
		case ImpactEditPart.VISUAL_ID:
			return getImpact_4011ContainedLinks(view);
		case SpecializationEditPart.VISUAL_ID:
			return getSpecialization_4012ContainedLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncomingLinks(View view) {
		switch (QmVisualIDRegistry.getVisualID(view)) {
		case FactorEditPart.VISUAL_ID:
			return getFactor_2006IncomingLinks(view);
		case EntityEditPart.VISUAL_ID:
			return getEntity_2007IncomingLinks(view);
		case FactorRefinementEditPart.VISUAL_ID:
			return getFactorRefinement_4010IncomingLinks(view);
		case ImpactEditPart.VISUAL_ID:
			return getImpact_4011IncomingLinks(view);
		case SpecializationEditPart.VISUAL_ID:
			return getSpecialization_4012IncomingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOutgoingLinks(View view) {
		switch (QmVisualIDRegistry.getVisualID(view)) {
		case FactorEditPart.VISUAL_ID:
			return getFactor_2006OutgoingLinks(view);
		case EntityEditPart.VISUAL_ID:
			return getEntity_2007OutgoingLinks(view);
		case FactorRefinementEditPart.VISUAL_ID:
			return getFactorRefinement_4010OutgoingLinks(view);
		case ImpactEditPart.VISUAL_ID:
			return getImpact_4011OutgoingLinks(view);
		case SpecializationEditPart.VISUAL_ID:
			return getSpecialization_4012OutgoingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getQualityModel_1000ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getFactor_2006ContainedLinks(View view) {
		Factor modelElement = (Factor) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_FactorRefinement_4010(modelElement));
		result
				.addAll(getContainedTypeModelFacetLinks_Impact_4011(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getEntity_2007ContainedLinks(View view) {
		Entity modelElement = (Entity) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Specialization_4012(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getFactorRefinement_4010ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getImpact_4011ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSpecialization_4012ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getFactor_2006IncomingLinks(View view) {
		Factor modelElement = (Factor) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_FactorRefinement_4010(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Impact_4011(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getEntity_2007IncomingLinks(View view) {
		Entity modelElement = (Entity) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Specialization_4012(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getFactorRefinement_4010IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getImpact_4011IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSpecialization_4012IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getFactor_2006OutgoingLinks(View view) {
		Factor modelElement = (Factor) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_FactorRefinement_4010(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Impact_4011(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getEntity_2007OutgoingLinks(View view) {
		Entity modelElement = (Entity) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_Specialization_4012(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getFactorRefinement_4010OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getImpact_4011OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSpecialization_4012OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_FactorRefinement_4010(
			Factor container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getRefines().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof FactorRefinement) {
				continue;
			}
			FactorRefinement link = (FactorRefinement) linkObject;
			if (FactorRefinementEditPart.VISUAL_ID != QmVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Factor dst = link.getParent();
			Factor src = link.getChild();
			result.add(new QmLinkDescriptor(src, dst, link,
					QmElementTypes.FactorRefinement_4010,
					FactorRefinementEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Impact_4011(
			Factor container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getInfluences().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Impact) {
				continue;
			}
			Impact link = (Impact) linkObject;
			if (ImpactEditPart.VISUAL_ID != QmVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Factor dst = link.getTarget();
			Factor src = link.getSource();
			result.add(new QmLinkDescriptor(src, dst, link,
					QmElementTypes.Impact_4011, ImpactEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Specialization_4012(
			Entity container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getIsA().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Specialization) {
				continue;
			}
			Specialization link = (Specialization) linkObject;
			if (SpecializationEditPart.VISUAL_ID != QmVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Entity dst = link.getParent();
			Entity src = link.getChild();
			result.add(new QmLinkDescriptor(src, dst, link,
					QmElementTypes.Specialization_4012,
					SpecializationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_FactorRefinement_4010(
			Factor target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != QmPackage.eINSTANCE
					.getFactorRefinement_Parent()
					|| false == setting.getEObject() instanceof FactorRefinement) {
				continue;
			}
			FactorRefinement link = (FactorRefinement) setting.getEObject();
			if (FactorRefinementEditPart.VISUAL_ID != QmVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Factor src = link.getChild();
			result.add(new QmLinkDescriptor(src, target, link,
					QmElementTypes.FactorRefinement_4010,
					FactorRefinementEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Impact_4011(
			Factor target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != QmPackage.eINSTANCE
					.getImpact_Target()
					|| false == setting.getEObject() instanceof Impact) {
				continue;
			}
			Impact link = (Impact) setting.getEObject();
			if (ImpactEditPart.VISUAL_ID != QmVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Factor src = link.getSource();
			result.add(new QmLinkDescriptor(src, target, link,
					QmElementTypes.Impact_4011, ImpactEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Specialization_4012(
			Entity target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != QmPackage.eINSTANCE
					.getSpecialization_Parent()
					|| false == setting.getEObject() instanceof Specialization) {
				continue;
			}
			Specialization link = (Specialization) setting.getEObject();
			if (SpecializationEditPart.VISUAL_ID != QmVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Entity src = link.getChild();
			result.add(new QmLinkDescriptor(src, target, link,
					QmElementTypes.Specialization_4012,
					SpecializationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_FactorRefinement_4010(
			Factor source) {
		Factor container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Factor) {
				container = (Factor) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getRefines().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof FactorRefinement) {
				continue;
			}
			FactorRefinement link = (FactorRefinement) linkObject;
			if (FactorRefinementEditPart.VISUAL_ID != QmVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Factor dst = link.getParent();
			Factor src = link.getChild();
			if (src != source) {
				continue;
			}
			result.add(new QmLinkDescriptor(src, dst, link,
					QmElementTypes.FactorRefinement_4010,
					FactorRefinementEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Impact_4011(
			Factor source) {
		Factor container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Factor) {
				container = (Factor) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getInfluences().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Impact) {
				continue;
			}
			Impact link = (Impact) linkObject;
			if (ImpactEditPart.VISUAL_ID != QmVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Factor dst = link.getTarget();
			Factor src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new QmLinkDescriptor(src, dst, link,
					QmElementTypes.Impact_4011, ImpactEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Specialization_4012(
			Entity source) {
		Entity container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Entity) {
				container = (Entity) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getIsA().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Specialization) {
				continue;
			}
			Specialization link = (Specialization) linkObject;
			if (SpecializationEditPart.VISUAL_ID != QmVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Entity dst = link.getParent();
			Entity src = link.getChild();
			if (src != source) {
				continue;
			}
			result.add(new QmLinkDescriptor(src, dst, link,
					QmElementTypes.Specialization_4012,
					SpecializationEditPart.VISUAL_ID));
		}
		return result;
	}

}
