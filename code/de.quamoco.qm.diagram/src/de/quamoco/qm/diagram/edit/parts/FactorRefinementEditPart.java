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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

import de.quamoco.qm.FactorRefinement;
import de.quamoco.qm.diagram.edit.policies.FactorRefinementItemSemanticEditPolicy;
import de.quamoco.qm.util.QmModelUtils;
import de.quamoco.qm.util.QmModelUtils.RefinementType;

/**
 * @generated
 */
public class FactorRefinementEditPart extends ConnectionNodeEditPart implements
		ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4010;

	/**
	 * @generated
	 */
	public FactorRefinementEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new FactorRefinementItemSemanticEditPolicy());
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model so
	 * you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */

	protected Connection createConnectionFigure() {
		return new RefinementFigure();
	}

	/**
	 * @generated
	 */
	public RefinementFigure getPrimaryShape() {
		return (RefinementFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class RefinementFigure extends PolylineConnectionEx {

		/**
		 * @generated NOT
		 */
		public RefinementFigure() {
			this.setLineWidth(1);
			this.setForegroundColor(ColorConstants.black);

			FactorRefinement ref = (FactorRefinement) resolveSemanticElement();

			setForegroundColor(new Color(null, 0, 0, 0));

			if (ref.getQualityModel().isTaggedBy("KLL")) {
				RefinementType refType = QmModelUtils.getRefinementType(ref);
				if (refType == RefinementType.REFINE) {
					setStyleToValidRefinement();
				} else if (refType == RefinementType.INVALID_REFINE) {
					setStyleToInvalidRefinement();
				} else if (refType == RefinementType.DECOMPOSE) {
					setStyleToValidDecomposition();
				} else if (refType == RefinementType.INVALID_DECOMPOSE) {
					setStyleToInvalidDecomposition();
				} else {
					setStyleToInvalidRefinement();
				}
			} else {
				setStyleToValidDecomposition();
			}

		}

		/**
		 * @generated
		 */
		private RotatableDecoration createTargetDecoration() {
			PolygonDecoration df = new PolygonDecoration();
			df.setFill(true);
			df.setLineWidth(1);
			df.setForegroundColor(ColorConstants.black);
			return df;
		}

		/**
		 * 
		 */
		private void setStyleToValidDecomposition() {
			this.setForegroundColor(ColorConstants.black);
			setTargetDecoration(createTargetDecoration(ColorConstants.black));
		}

		/**
		 * 
		 */
		private void setStyleToInvalidDecomposition() {
			this.setForegroundColor(ColorConstants.red);
			setTargetDecoration(createTargetDecoration(ColorConstants.red));
		}

		/**
		 * 
		 */
		private void setStyleToValidRefinement() {
			this.setLineStyle(org.eclipse.swt.SWT.LINE_DASH);
			this.setLineDash(new int[] { 3, 3 });
			this.setLineWidth(1);
			this.setForegroundColor(ColorConstants.black);

			setTargetDecoration(createTargetDecorationRefinement(ColorConstants.black));
		}

		/**
		 * 
		 */
		private void setStyleToInvalidRefinement() {
			this.setLineStyle(org.eclipse.swt.SWT.LINE_DASH);
			this.setLineDash(new int[] { 3, 3 });
			this.setLineWidth(1);
			this.setForegroundColor(ColorConstants.red);

			setTargetDecoration(createTargetDecorationRefinement(ColorConstants.red));
		}

		/**
		 * @generated NOT
		 */
		private RotatableDecoration createTargetDecoration(Color color) {
			PolylineDecoration df = new PolylineDecoration();
			df.setFill(true);
			df.setBackgroundColor(ColorConstants.white);
			df.setLineWidth(1);
			df.setForegroundColor(color);
			df.setScale(7, 7);
			return df;
		}

		/**
		 * 
		 * @return
		 */
		private RotatableDecoration createTargetDecorationRefinement(Color color) {
			PolygonDecoration df = new PolygonDecoration();
			PointList l = new PointList();
			l.addPoint(0, 0);
			l.addPoint(-1, 1);
			l.addPoint(-1, -1);

			df.setTemplate(l);
			df.setFill(true);
			df.setBackgroundColor(ColorConstants.white);
			df.setLineWidth(1);
			df.setForegroundColor(color);
			df.setLineStyle(org.eclipse.swt.SWT.LINE_DASH);
			df.setScale(7, 7);
			return df;
		}

	}

}
