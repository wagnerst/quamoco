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

package de.quamoco.qm.diagram.navigator;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

import de.quamoco.qm.FactorRefinement;
import de.quamoco.qm.Impact;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.Specialization;
import de.quamoco.qm.diagram.edit.parts.EntityEditPart;
import de.quamoco.qm.diagram.edit.parts.EntityNameEditPart;
import de.quamoco.qm.diagram.edit.parts.FactorEditPart;
import de.quamoco.qm.diagram.edit.parts.FactorQualifiedNameEditPart;
import de.quamoco.qm.diagram.edit.parts.FactorRefinementEditPart;
import de.quamoco.qm.diagram.edit.parts.ImpactEditPart;
import de.quamoco.qm.diagram.edit.parts.QualityModelEditPart;
import de.quamoco.qm.diagram.edit.parts.SpecializationEditPart;
import de.quamoco.qm.diagram.part.QmDiagramEditorPlugin;
import de.quamoco.qm.diagram.part.QmVisualIDRegistry;
import de.quamoco.qm.diagram.providers.QmElementTypes;
import de.quamoco.qm.diagram.providers.QmParserProvider;

/**
 * @generated
 */
public class QmNavigatorLabelProvider extends LabelProvider implements
		ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		QmDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put(
						"Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		QmDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put(
						"Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof QmNavigatorItem
				&& !isOwnView(((QmNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof QmNavigatorGroup) {
			QmNavigatorGroup group = (QmNavigatorGroup) element;
			return QmDiagramEditorPlugin.getInstance().getBundledImage(
					group.getIcon());
		}

		if (element instanceof QmNavigatorItem) {
			QmNavigatorItem navigatorItem = (QmNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	 * @generated
	 */
	public Image getImage(View view) {
		switch (QmVisualIDRegistry.getVisualID(view)) {
		case QualityModelEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://www.quamoco.de/qm/v13?QualityModel", QmElementTypes.QualityModel_1000); //$NON-NLS-1$
		case FactorEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.quamoco.de/qm/v13?Factor", QmElementTypes.Factor_2006); //$NON-NLS-1$
		case EntityEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.quamoco.de/qm/v13?Entity", QmElementTypes.Entity_2007); //$NON-NLS-1$
		case FactorRefinementEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.quamoco.de/qm/v13?FactorRefinement", QmElementTypes.FactorRefinement_4010); //$NON-NLS-1$
		case ImpactEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.quamoco.de/qm/v13?Impact", QmElementTypes.Impact_4011); //$NON-NLS-1$
		case SpecializationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.quamoco.de/qm/v13?Specialization", QmElementTypes.Specialization_4012); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = QmDiagramEditorPlugin.getInstance()
				.getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& QmElementTypes.isKnownElementType(elementType)) {
			image = QmElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public String getText(Object element) {
		if (element instanceof QmNavigatorGroup) {
			QmNavigatorGroup group = (QmNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof QmNavigatorItem) {
			QmNavigatorItem navigatorItem = (QmNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		return super.getText(element);
	}

	/**
	 * @generated
	 */
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}
		switch (QmVisualIDRegistry.getVisualID(view)) {
		case QualityModelEditPart.VISUAL_ID:
			return getQualityModel_1000Text(view);
		case FactorEditPart.VISUAL_ID:
			return getFactor_2006Text(view);
		case EntityEditPart.VISUAL_ID:
			return getEntity_2007Text(view);
		case FactorRefinementEditPart.VISUAL_ID:
			return getFactorRefinement_4010Text(view);
		case ImpactEditPart.VISUAL_ID:
			return getImpact_4011Text(view);
		case SpecializationEditPart.VISUAL_ID:
			return getSpecialization_4012Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getQualityModel_1000Text(View view) {
		QualityModel domainModelElement = (QualityModel) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			QmDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 1000); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getFactor_2006Text(View view) {
		IParser parser = QmParserProvider.getParser(QmElementTypes.Factor_2006,
				view.getElement() != null ? view.getElement() : view,
				QmVisualIDRegistry
						.getType(FactorQualifiedNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			QmDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5006); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getEntity_2007Text(View view) {
		IParser parser = QmParserProvider.getParser(QmElementTypes.Entity_2007,
				view.getElement() != null ? view.getElement() : view,
				QmVisualIDRegistry.getType(EntityNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			QmDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5007); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getFactorRefinement_4010Text(View view) {
		FactorRefinement domainModelElement = (FactorRefinement) view
				.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getQualifiedName();
		} else {
			QmDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 4010); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getImpact_4011Text(View view) {
		Impact domainModelElement = (Impact) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getQualifiedName();
		} else {
			QmDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 4011); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getSpecialization_4012Text(View view) {
		Specialization domainModelElement = (Specialization) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getQualifiedName();
		} else {
			QmDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 4012); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return QualityModelEditPart.MODEL_ID.equals(QmVisualIDRegistry
				.getModelID(view));
	}

}
