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

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.diagram.edit.parts.EntityEditPart;
import de.quamoco.qm.diagram.edit.parts.EntityNameEditPart;
import de.quamoco.qm.diagram.edit.parts.FactorEditPart;
import de.quamoco.qm.diagram.edit.parts.FactorQualifiedNameEditPart;
import de.quamoco.qm.diagram.edit.parts.FactorRefinementEditPart;
import de.quamoco.qm.diagram.edit.parts.ImpactEditPart;
import de.quamoco.qm.diagram.edit.parts.QualityModelEditPart;
import de.quamoco.qm.diagram.edit.parts.SpecializationEditPart;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class QmVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "de.quamoco.qm.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (QualityModelEditPart.MODEL_ID.equals(view.getType())) {
				return QualityModelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return de.quamoco.qm.diagram.part.QmVisualIDRegistry.getVisualID(view
				.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				QmDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "
								+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return String.valueOf(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (QmPackage.eINSTANCE.getQualityModel().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((QualityModel) domainElement)) {
			return QualityModelEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = de.quamoco.qm.diagram.part.QmVisualIDRegistry
				.getModelID(containerView);
		if (!QualityModelEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (QualityModelEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = de.quamoco.qm.diagram.part.QmVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = QualityModelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case QualityModelEditPart.VISUAL_ID:
			if (QmPackage.eINSTANCE.getFactor().isSuperTypeOf(
					domainElement.eClass())) {
				return FactorEditPart.VISUAL_ID;
			}
			if (QmPackage.eINSTANCE.getEntity().isSuperTypeOf(
					domainElement.eClass())) {
				return EntityEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = de.quamoco.qm.diagram.part.QmVisualIDRegistry
				.getModelID(containerView);
		if (!QualityModelEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (QualityModelEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = de.quamoco.qm.diagram.part.QmVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = QualityModelEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case FactorEditPart.VISUAL_ID:
			if (FactorQualifiedNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EntityEditPart.VISUAL_ID:
			if (EntityNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case QualityModelEditPart.VISUAL_ID:
			if (FactorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EntityEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (QmPackage.eINSTANCE.getFactorRefinement().isSuperTypeOf(
				domainElement.eClass())) {
			return FactorRefinementEditPart.VISUAL_ID;
		}
		if (QmPackage.eINSTANCE.getImpact().isSuperTypeOf(
				domainElement.eClass())) {
			return ImpactEditPart.VISUAL_ID;
		}
		if (QmPackage.eINSTANCE.getSpecialization().isSuperTypeOf(
				domainElement.eClass())) {
			return SpecializationEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(QualityModel element) {
		return true;
	}

}
