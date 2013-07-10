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

package de.quamoco.qm.diagram.edit.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import de.quamoco.qm.Factor;
import de.quamoco.qm.diagram.edit.policies.FactorItemSemanticEditPolicy;
import de.quamoco.qm.diagram.part.QmVisualIDRegistry;
import de.quamoco.qm.diagram.providers.QmElementTypes;

/**
 * @generated
 */
public class FactorEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2006;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public FactorEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new FactorItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		LayoutEditPolicy lep = new LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child
						.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		FactorFigure figure = new FactorFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public FactorFigure getPrimaryShape() {
		return (FactorFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof FactorQualifiedNameEditPart) {
			((FactorQualifiedNameEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureFactorNameLabel());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof FactorQualifiedNameEditPart) {
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
		return getContentPane();
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(40, 40);
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model so
	 * you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane. Respects
	 * layout one may have set for generated figure.
	 * 
	 * @param nodeShape
	 *            instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	protected void setForegroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setForegroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setBackgroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setBackgroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineWidth(int width) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineWidth(width);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineType(int style) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineStyle(style);
		}
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(QmVisualIDRegistry
				.getType(FactorQualifiedNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnSource() {
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		types.add(QmElementTypes.FactorRefinement_4010);
		types.add(QmElementTypes.Impact_4011);
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnSourceAndTarget(
			IGraphicalEditPart targetEditPart) {
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		if (targetEditPart instanceof de.quamoco.qm.diagram.edit.parts.FactorEditPart) {
			types.add(QmElementTypes.FactorRefinement_4010);
		}
		if (targetEditPart instanceof de.quamoco.qm.diagram.edit.parts.FactorEditPart) {
			types.add(QmElementTypes.Impact_4011);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMATypesForTarget(
			IElementType relationshipType) {
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		if (relationshipType == QmElementTypes.FactorRefinement_4010) {
			types.add(QmElementTypes.Factor_2006);
		}
		if (relationshipType == QmElementTypes.Impact_4011) {
			types.add(QmElementTypes.Factor_2006);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnTarget() {
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		types.add(QmElementTypes.FactorRefinement_4010);
		types.add(QmElementTypes.Impact_4011);
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMATypesForSource(
			IElementType relationshipType) {
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		if (relationshipType == QmElementTypes.FactorRefinement_4010) {
			types.add(QmElementTypes.Factor_2006);
		}
		if (relationshipType == QmElementTypes.Impact_4011) {
			types.add(QmElementTypes.Factor_2006);
		}
		return types;
	}

	/**
	 * @generated NOT
	 */
	public class FactorFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureFactorNameLabel;

		/**
		 * @generated
		 */
		public FactorFigure() {

			BorderLayout layoutThis = new BorderLayout();
			this.setLayoutManager(layoutThis);

			this.setLineWidth(1);
			this.setForegroundColor(ColorConstants.black);
			createContents();
		}

		/**
		 * 
		 */
		@Override
		protected void paintChildren(Graphics graphics) {
			// Intentionally do nothing!
		}

		/**
		 * 
		 */
		@Override
		public Dimension getMinimumSize(int wHint, int hHint) {
			Dimension dim = super.getMinimumSize(wHint, hHint);
			dim.width += 4;
			return dim;
		}

		/**
		 * 
		 */
		@Override
		public Dimension getPreferredSize(int wHint, int hHint) {
			Dimension dim = super.getPreferredSize(wHint, hHint);
			dim.width += 4;
			return dim;
		}

		/**
		 * 
		 */
		@Override
		public void paintFigure(Graphics graphics) {
			super.paintFigure(graphics);

			Factor f = (Factor) resolveSemanticElement();
			String text = f.getQualifiedName();

			Dimension ges = TextUtilities.INSTANCE.getStringExtents(text,
					getFont());

			if (!text.contains("_")) {
				int x = getBounds().x + getBounds().width / 2 - ges.width / 2;
				int y = getBounds().y + getBounds().height / 2 - ges.height / 2;

				graphics.drawString(text, x, y);
				return;
			}

			int w = 0;
			int ycorr = 0;

			Font font = graphics.getFont();
			Font fontDefault = new Font(font.getDevice(), font.getFontData()[0]
					.getName(), font.getFontData()[0].getHeight(), 0);
			Font boldFont = new Font(font.getDevice(), font.getFontData()[0]
					.getName(), font.getFontData()[0].getHeight(), SWT.BOLD);

			try {

				for (int i = 0; i < text.length(); i++) {

					char c = text.charAt(i);

					if (text.charAt(i) == '_') {
						ycorr = 5;
						continue;
					}

					if (c == '[' || c == ']' || c == '@') {
						// graphics.setFont(boldFont);
					} else {
						// graphics.setFont(fontDefault);
					}

					String s = text.charAt(i) + "";
					Dimension d = TextUtilities.INSTANCE.getStringExtents(s,
							graphics.getFont());
					int x = w + getBounds().x + getBounds().width / 2
							- ges.width / 2;
					int y = getBounds().y + getBounds().height / 2 - ges.height
							/ 2 + ycorr;
					w += d.width;

					graphics.drawString(s, x, y);

					ycorr = 0;
				}
			} finally {
				graphics.setFont(font);
				fontDefault.dispose();
				boldFont.dispose();
			}

		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureFactorNameLabel = new WrappingLabel();
			fFigureFactorNameLabel.setText("");
			fFigureFactorNameLabel.setForegroundColor(ColorConstants.black);

			this.add(fFigureFactorNameLabel, BorderLayout.CENTER);

		}

		/**
		 * @generated
		 */
		private boolean myUseLocalCoordinates = false;

		/**
		 * @generated
		 */
		protected boolean useLocalCoordinates() {
			return myUseLocalCoordinates;
		}

		/**
		 * @generated
		 */
		protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
			myUseLocalCoordinates = useLocalCoordinates;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureFactorNameLabel() {
			return fFigureFactorNameLabel;
		}

	}

}
