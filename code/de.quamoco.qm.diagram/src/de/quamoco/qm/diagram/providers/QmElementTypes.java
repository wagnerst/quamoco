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

package de.quamoco.qm.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

import de.quamoco.qm.QmPackage;
import de.quamoco.qm.diagram.edit.parts.EntityEditPart;
import de.quamoco.qm.diagram.edit.parts.FactorEditPart;
import de.quamoco.qm.diagram.edit.parts.FactorRefinementEditPart;
import de.quamoco.qm.diagram.edit.parts.ImpactEditPart;
import de.quamoco.qm.diagram.edit.parts.QualityModelEditPart;
import de.quamoco.qm.diagram.edit.parts.SpecializationEditPart;
import de.quamoco.qm.diagram.part.QmDiagramEditorPlugin;

/**
 * @generated
 */
public class QmElementTypes extends ElementInitializers {

	/**
	 * @generated
	 */
	private QmElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map elements;

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static Set KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType QualityModel_1000 = getElementType("de.quamoco.qm.diagram.QualityModel_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Factor_2006 = getElementType("de.quamoco.qm.diagram.Factor_2006"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Entity_2007 = getElementType("de.quamoco.qm.diagram.Entity_2007"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType FactorRefinement_4010 = getElementType("de.quamoco.qm.diagram.FactorRefinement_4010"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Impact_4011 = getElementType("de.quamoco.qm.diagram.Impact_4011"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Specialization_4012 = getElementType("de.quamoco.qm.diagram.Specialization_4012"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(
			ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if (eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if (eType instanceof EClass
					&& !((EClass) eType).isAbstract()) {
				element = eType;
			}
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) {
				return QmDiagramEditorPlugin.getInstance()
						.getItemImageDescriptor(
								eClass.getEPackage().getEFactoryInstance()
										.create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap();

			elements.put(QualityModel_1000, QmPackage.eINSTANCE
					.getQualityModel());

			elements.put(Factor_2006, QmPackage.eINSTANCE.getFactor());

			elements.put(Entity_2007, QmPackage.eINSTANCE.getEntity());

			elements.put(FactorRefinement_4010, QmPackage.eINSTANCE
					.getFactorRefinement());

			elements.put(Impact_4011, QmPackage.eINSTANCE.getImpact());

			elements.put(Specialization_4012, QmPackage.eINSTANCE
					.getSpecialization());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet();
			KNOWN_ELEMENT_TYPES.add(QualityModel_1000);
			KNOWN_ELEMENT_TYPES.add(Factor_2006);
			KNOWN_ELEMENT_TYPES.add(Entity_2007);
			KNOWN_ELEMENT_TYPES.add(FactorRefinement_4010);
			KNOWN_ELEMENT_TYPES.add(Impact_4011);
			KNOWN_ELEMENT_TYPES.add(Specialization_4012);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case QualityModelEditPart.VISUAL_ID:
			return QualityModel_1000;
		case FactorEditPart.VISUAL_ID:
			return Factor_2006;
		case EntityEditPart.VISUAL_ID:
			return Entity_2007;
		case FactorRefinementEditPart.VISUAL_ID:
			return FactorRefinement_4010;
		case ImpactEditPart.VISUAL_ID:
			return Impact_4011;
		case SpecializationEditPart.VISUAL_ID:
			return Specialization_4012;
		}
		return null;
	}

}
