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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

import de.quamoco.qm.diagram.providers.QmElementTypes;

/**
 * @generated
 */
public class QmPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createQm1Group());
	}

	/**
	 * Creates "qm" palette tool group
	 * @generated
	 */
	private PaletteContainer createQm1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.Qm1Group_title);
		paletteContainer.setId("createQm1Group"); //$NON-NLS-1$
		paletteContainer.add(createFactor1CreationTool());
		paletteContainer.add(createEntity2CreationTool());
		paletteContainer.add(createRefinement3CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createFactor1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(QmElementTypes.Factor_2006);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Factor1CreationTool_title, null, types);
		entry.setId("createFactor1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(QmElementTypes
				.getImageDescriptor(QmElementTypes.Factor_2006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createEntity2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(QmElementTypes.Entity_2007);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Entity2CreationTool_title, null, types);
		entry.setId("createEntity2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(QmElementTypes
				.getImageDescriptor(QmElementTypes.Entity_2007));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRefinement3CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(QmElementTypes.FactorRefinement_4010);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Refinement3CreationTool_title, null, types);
		entry.setId("createRefinement3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(QmElementTypes
				.getImageDescriptor(QmElementTypes.FactorRefinement_4010));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
