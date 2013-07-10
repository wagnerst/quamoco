/*-------------------------------------------------------------------------+
|                                                                          |
| Fraunhofer-Institut fuer Experimentelles Software Engineering (IESE)     |
|                                                                          |
|                                                                          |
|   NOT TO BE PUBLISHED UNDER QUAMOCO LICENSE                              |
|                                                                          |
+-------------------------------------------------------------------------*/

package de.quamoco.qm.visualization;

import iese.inseviz.data.IESEDataEdge;
import iese.inseviz.data.IESEDataGraph;
import iese.inseviz.data.IESEDataNode;
import iese.inseviz.util.log.IESELogger;
import iese.inseviz.util.log.IESELogger.IESELogLevel;
import iese.inseviz.util.ui.image.IESECaptureUtil;
import iese.inseviz.visualization.IESEAbstractView;
import iese.inseviz.visualization.IESEView;
import iese.inseviz.visualization.interaction.IESECaptureRequestHandler;
import iese.inseviz.visualization.interaction.IESEDraggingHandler;
import iese.inseviz.visualization.interaction.IESEHighlightHandler;
import iese.inseviz.visualization.interaction.IESEPickingHandler;
import iese.inseviz.visualization.interaction.IESETooltipHandler;
import iese.inseviz.visualization.interaction.IESETooltipListener;
import iese.inseviz.visualization.renderer.IESECurveRenderer;
import iese.inseviz.visualization.renderer.IESEPieShapeRenderer;
import iese.inseviz.visualization.renderer.IESEPieShapeRenderer.PieLabelOrientation;
import iese.inseviz.visualization.renderer.IESERenderer;
import iese.inseviz.visualization.renderer.IESERenderer.RenderType;
import iese.inseviz.visualization.renderer.stroke.IESEBasicStroke;
import iese.inseviz.visualization.renderer.sunburst.IESEEdgeBundlingProperties;
import iese.inseviz.visualization.renderer.sunburst.IESEPiePaintManager;
import iese.inseviz.visualization.renderer.sunburst.IESESunburstProperties;
import iese.inseviz.visualization.renderer.sunburst.IESESunburstRenderProperties;
import iese.inseviz.visualization.renderer.sunburst.IESESunburstRenderer;
import iese.inseviz.visualization.renderer.sunburst.interaction.IESEEdgeBundlingEdgeVisibilityHandler;
import iese.inseviz.visualization.renderer.sunburst.interaction.IESESunburstPickhandler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;

import de.quamoco.qm.Factor;
import de.quamoco.qm.Measure;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.QualityModelElement;
import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.visualization.model.QMVImpactsEdgeBundlingHierarchy;
import de.quamoco.qm.visualization.util.QMVQualityModelInfo;
import de.quamoco.qm.visualization.util.QMVResultInfo;
import de.quamoco.qm.util.QmModelUtils;

/**
 * TODO: Add documentation.
 * 
 * @author herrmama
 * @author $Author: HenningBarthel $
 * @version $Rev: 4968 $
 * @levd.rating RED Rev:
 */
public class TwoHierarchySunburstView extends IESEVisualizationViewBase<QualityModelResult>
{

	/** The identifier of the view as in the plugin.xml. */
	public static final String ID = TwoHierarchySunburstView.class.getName();
	/** The swing visualization view. */
	private IESEView view = null;
	/** A handler for the swing tool tips. */
	private IESETooltipHandler tooltipHandler = null;

	/** The edge bundling hierarchy. */
	private QMVImpactsEdgeBundlingHierarchy hierarchy = null;
	/** The sunburst properties. */
	private IESESunburstProperties<IESEDataNode, IESEDataEdge> sunburstProperties = null;
	/** The sunburst render properties. */
	private IESESunburstRenderProperties<IESEDataNode, IESEDataEdge> sunburstRenderProperties = null;
	/** The edge bundling properties. */
	private IESEEdgeBundlingProperties<IESEDataNode, IESEDataEdge> edgeBundling = null;
	/** The sunburst renderer. */
	private IESESunburstRenderer sunburst = null;

	private boolean useEvaluationColoring = true;
	private final Map<IESERenderer, Paint> paintMapEval = new HashMap<IESERenderer, Paint>();
	private final Map<IESERenderer, String> tooltipMapEval = new HashMap<IESERenderer, String>();

	private final Map<IESERenderer, Paint> paintMapCompl = new HashMap<IESERenderer, Paint>();
	private final Map<IESERenderer, String> tooltipMapCompl = new HashMap<IESERenderer, String>();

	/** {@inheritDoc} */
	@Override
	protected void createActions()
	{
		// add action buttons
		IToolBarManager toolBar = getViewSite().getActionBars().getToolBarManager();
		Action viewAllAction = new Action("View All", IAction.AS_PUSH_BUTTON)
		{
			/** {@inheritDoc} */
			@Override
			public void run()
			{
				if (view != null)
					view.viewAll();
			}
		};

		Action evaluationAction = new Action("Evaluation", IAction.AS_RADIO_BUTTON)
		{
			/** {@inheritDoc} */
			@Override
			public void run()
			{
				if (isChecked())
				{
					if (useEvaluationColoring == true)
						return;
					useEvaluationColoring = true;

					toggleColoring(true);
				}
			}
		};
		Action completenessAction = new Action("Completeness", IAction.AS_RADIO_BUTTON)
		{
			/** {@inheritDoc} */
			@Override
			public void run()
			{
				if (isChecked())
				{
					if (useEvaluationColoring == false)
						return;
					useEvaluationColoring = false;
					toggleColoring(false);
				}
			}
		};
		if (useEvaluationColoring)
			evaluationAction.setChecked(true);
		else
			completenessAction.setChecked(true);

		toolBar.add(evaluationAction);
		toolBar.add(completenessAction);
		toolBar.add(viewAllAction);
	}

	/** {@inheritDoc} */
	@Override
	protected void inputChanged(QualityModelResult element, JPanel panel)
	{
		if (getElement() == null)
		{
			System.err.println("OneHierarchySunburstView: no results specified!");
			System.err.flush();
			return;
		}
		// no logging
		IESELogger.setLevel(IESELogLevel.OFF);

		// cache info about models
		QMVQualityModelInfo modelInfo = new QMVQualityModelInfo();
		modelInfo.addResult(getElement());

		// create the visualization view
		view = createVisualization(modelInfo);

		{
			// setup view text info to show the name of the result set in the view
			Font textInfoFont = new Font("Arial", Font.BOLD, 12);
			view.setDrawTextInfo(true);
			view.addTextInfo(element.getSystem().trim(), textInfoFont, Color.black,
			        IESEView.ViewTextInfoPosition.BOTTOM_LEFT);
		}

		// create a tool tip component
		tooltipHandler.addTooltipListener(new IESETooltipListener()
		{

			@Override
			public void showTooltip(BufferedImage arg0, String arg1)
			{
				TwoHierarchySunburstView.this.showTooltip(arg1);
			}

			@Override
			public void showTooltip(IESERenderer arg0, String arg1)
			{
				TwoHierarchySunburstView.this.showTooltip(arg1);
			}

			@Override
			public void clear()
			{
				TwoHierarchySunburstView.this.showTooltip("");
			}
		});

		panel.removeAll();
		panel.add(view);

		// add a key listener to reset the view by key 'r'
		view.addKeyListener(new java.awt.event.KeyAdapter()
		{
			@Override
			public void keyPressed(java.awt.event.KeyEvent e)
			{
				if (view != null)
				{
					if (e.getKeyCode() == KeyEvent.VK_R)
						view.viewAll();
				}
			}
		});

		initializeColorToggeling();
	}

	/**
	 * Creates the visualization and returns the view containing all renderers.
	 * 
	 * @param modelInfo
	 * @return the view to the visualization
	 */
	private IESEView createVisualization(QMVQualityModelInfo modelInfo)
	{
		// create a view
		final IESEView view = new IESEView();

		// create hierarchy
		hierarchy = new QMVImpactsEdgeBundlingHierarchy();
		hierarchy.createHierarchy(modelInfo);

		// create sunburst from hierarchy
		sunburst = createSunburst(view);

		// add sunburst renderers to the view
		view.addRenderer(sunburst);
		for (IESERenderer renderer : sunburst.getGroupMembers())
		{
			view.addRenderer(renderer);

			if (renderer instanceof IESEPieShapeRenderer)
			{
				renderer.removeRenderType(RenderType.PICKED_FILLED);
				renderer.addRenderType(RenderType.PICKED_OUTLINE, Color.blue);
				renderer.assignStroke(RenderType.PICKED_OUTLINE, new IESEBasicStroke(9.0f));
			}
		}

		// some interaction possibilities
		final IESEHighlightHandler highlightHandler = new IESEHighlightHandler(view);
		IESEDraggingHandler draggingHandler = new IESEDraggingHandler(view);

		IESEPickingHandler pickingHandler = new IESEPickingHandler(view);
		// pickingHandler.setAllowMultiPicking(false);// for Sunburst LOD

		tooltipHandler = new IESETooltipHandler(view);

		IESEEdgeBundlingEdgeVisibilityHandler ebVisibleHandler = new IESEEdgeBundlingEdgeVisibilityHandler(view,
		        sunburst, edgeBundling);

		IESESunburstPickhandler focusHandler = new IESESunburstPickhandler(view, sunburst);
		focusHandler.setAnimated(true);
		focusHandler.setPercentage(0.9);// take 90% of parental angle extent for
		                                // the child

		focusHandler.setEdgeBundling(edgeBundling);

		IESECaptureRequestHandler captureReqHandler = new IESECaptureRequestHandler(view, new IESECaptureUtil());

		return view;
	}

	/**
	 * Computes the sunburst and creates the sunburst renderer
	 * 
	 * @param view
	 * @param hierarchy
	 * @return the sunburst renderer
	 */
	private IESESunburstRenderer createSunburst(IESEAbstractView view)
	{
		// compute sunburst properties
		sunburstProperties = new IESESunburstProperties<IESEDataNode, IESEDataEdge>(hierarchy.getHierarchy(), hierarchy
		        .getHierarchy().getRootVertex(), null);

		// set the weights for the sunburst vertices
		for (IESEDataNode vertex : hierarchy.getHierarchy().vertexSet())
		{
			Double weight = hierarchy.getWeight(vertex);
			sunburstProperties.setWeight(vertex, weight);
		}
		// don't forget the weight for the root
		sunburstProperties.setWeight(hierarchy.getHierarchy().getRootVertex(), 0.0);

		// set default sunburst properties attributes to use
		sunburstProperties.setUseCompoundNodes(false);
		sunburstProperties.setDefaultDistance(100.0);
		sunburstProperties.setDefaultSize(200.0);
		sunburstProperties.setDefaultDistance(1.0);
		sunburstProperties.setRootRadius(300.0);
		sunburstProperties.setDefaultMinAngleExtent(0.5);
		sunburstProperties.setDefaultAngularDistance(0.5);
		sunburstProperties.setSegmentAngularDistance(2.0);

		sunburstProperties.setDefaultLabelOrientation(PieLabelOrientation.RADIAL);
		sunburstProperties.setUseTangentialLabelOrientationOnMinAngleExtent(true);
		sunburstProperties.setLabelOrientation(hierarchy.getHierarchy().getRootVertex(), PieLabelOrientation.RADIAL);

		sunburstProperties.setInverseSunburst(true);

		// now compute the sunburst properties using special start angles and
		// angle extents
		// for the inverse sunburst edge bundling visualization:
		double propertyAtProductStartAngle = 100.0;
		double propertyAtProdcutAngleExtent = 160.0;
		sunburstProperties.computeInverseSunburstProperties(new double[] {
		        propertyAtProductStartAngle, 315.0
		}, new double[] {
		        propertyAtProdcutAngleExtent, 90.0
		});

		// get result values
		QMVResultInfo resultInfo = new QMVResultInfo();
		resultInfo.collectResultInfo(hierarchy.getModelInfo());

		// compute sunburst render properties
		sunburstRenderProperties = new IESESunburstRenderProperties<IESEDataNode, IESEDataEdge>();
		Font font68 = new Font("Arial", Font.BOLD, 68);
		Font font24 = new Font("Arial", Font.BOLD, 24);
		double schoolGradeBoundaries[] = resultInfo.getModelInfo().getSchoolGradeBoundaries();
		double minBoundaryValue = schoolGradeBoundaries[6];
		double middleBoundaryValue = (schoolGradeBoundaries[3] + schoolGradeBoundaries[4]) / 2.0;

		// define shading color key-frames
		Color innerPaintColors[] = {
		        Color.red, Color.red, Color.yellow, Color.green
		};
		// double innerPaintColorKeys[] = {
		// 0.0, 0.9, 0.95, 1.0
		// };
		double innerPaintColorKeys[] = {
		        0.0, minBoundaryValue, middleBoundaryValue, 1.0
		};
		Color outerPaintColors[] = {
		        Color.red, Color.red, Color.yellow, Color.green
		};
		// double outerPaintColorKeys[] = {
		// 0.0, 0.9, 0.95, 1.0
		// };
		double outerPaintColorKeys[] = {
		        0.0, minBoundaryValue, middleBoundaryValue, 1.0
		};

		sunburstRenderProperties.setDefaultLabelFont(font24);
		sunburstRenderProperties.setDefaultFillColor(new Color(200, 200, 200));
		sunburstRenderProperties.setDefaultFillColorHL(Color.cyan);
		sunburstRenderProperties.setDefaultLabelFont(font24);
		sunburstRenderProperties.setDefaultLabelColor(Color.black);
		sunburstRenderProperties.getPiePaintManager().setDefaultInterpolationColors(innerPaintColors);
		sunburstRenderProperties.getPiePaintManager().setDefaultInterpolationColorFractions(innerPaintColorKeys);

		double eps = 10000.0;

		for (IESEDataNode vertex : sunburstProperties.getSunburstVertices())
		{
			sunburstRenderProperties.getPiePaintManager().setDefaultInterpolationColors(innerPaintColors);
			sunburstRenderProperties.getPiePaintManager().setDefaultInterpolationColorFractions(innerPaintColorKeys);

			double radius = sunburstProperties.getRadius(vertex);
			double size = sunburstProperties.getSize(vertex);

			QualityModelElement entity = hierarchy.getVertexFactory().getEntity(vertex);

			double value[] = null;
			if (entity instanceof Factor)
			{
				Factor factor = (Factor) entity;
				value = resultInfo.getValue(hierarchy.getModelInfo().getResultList().get(0), factor);
			}
			// else if (entity instanceof Measure) {
			// Measure measure = (Measure) entity;
			// value = resultInfo.getValue(hierarchy.getModelInfo()
			// .getResultList().get(0), measure);
			// }

			if (value != null)
			{
				double lower = Math.round(value[0] * eps) / eps;
				double upper = Math.round(value[1] * eps) / eps;

				double colorWeight = lower + 0.5 * (upper - lower);

				Boolean positiveImpact = (Boolean) vertex.getAttribute("positiveImpact");

				// if (entity instanceof Measure) {
				// IESEDataNode parentFactorVertex = Graphs.predecessorListOf(
				// hierarchy.getHierarchy(), vertex).get(0);
				// positiveImpact = (Boolean) parentFactorVertex
				// .getAttribute("positiveImpact");
				// }
				if (positiveImpact != null)
				{
					sunburstRenderProperties.getPiePaintManager().setDefaultInterpolationColors(outerPaintColors);
					sunburstRenderProperties.getPiePaintManager().setDefaultInterpolationColorFractions(
					        outerPaintColorKeys);
					if (positiveImpact == false)
						colorWeight = 1.0 - colorWeight;

					vertex.setAttribute("colorWeight", colorWeight);

					sunburstRenderProperties.setPaint(vertex, RenderType.FILLED, radius, size, colorWeight);
				}
				else
				{
					// no impact found:
					if (vertex.hasAttribute("differingImpacts"))
					{
						sunburstRenderProperties.setFillColor(vertex, Color.magenta);

						sunburstRenderProperties.setPaint(vertex, RenderType.FILLED, radius, size);
					}
					else
					{
						vertex.setAttribute("colorWeight", colorWeight);
						sunburstRenderProperties.setPaint(vertex, RenderType.FILLED, radius, size, colorWeight);
					}
				}
				// vertex.setAttribute("colorWeight", colorWeight);
				// sunburstRenderProperties.setPaint(vertex, RenderType.FILLED,
				// radius, size, colorWeight);

			}
			else
			{
				// no value found: no shading
				sunburstRenderProperties.setPaint(vertex, RenderType.FILLED, radius, size);
			}
			sunburstRenderProperties.setPaint(vertex, RenderType.HIGHLIGHT_FILLED, radius, size);
			sunburstRenderProperties.setPaint(vertex, RenderType.PICKED_FILLED, radius, size);

			// sunburstRenderProperties.setOmitVertexLabel(vertex, true);
		}
		// create sunburst renderer using the sunburst properties
		sunburst = new IESESunburstRenderer("Sunburst");
		sunburst.setRenderRoot(false);
		sunburst.setRenderRootAsCircle(false);
		sunburst.setPickable(false);
		sunburst.setMouseOverEnabled(false);
		sunburst.computeSunburst(sunburstProperties, sunburstRenderProperties);
		System.out.println("#sunburst pies:" + sunburst.getSunburstPieCount());

		sunburst.clearRenderTypes();// no rendering of the sunburst's bounding
		                            // box

		// set tooltip text
		for (IESERenderer renderer : sunburst.getGroupMembers())
		{
			if (renderer instanceof IESEPieShapeRenderer)
			{
				IESEDataNode vertex = (IESEDataNode) renderer.getData();

				QualityModelElement entity = hierarchy.getVertexFactory().getEntity(vertex);

				double value[] = null;
				if (entity instanceof Factor)
				{
					Factor factor = (Factor) entity;
					value = resultInfo.getValue(hierarchy.getModelInfo().getResultList().get(0), factor);
				}
				else
					if (entity instanceof Measure)
					{
						Measure measure = (Measure) entity;
						value = resultInfo.getValue(hierarchy.getModelInfo().getResultList().get(0), measure);
					}

				String valueTT = createTooltipText(resultInfo, entity, vertex, value);
				tooltipMapEval.put(renderer, valueTT);
				renderer.setTooltipText("<html>" + valueTT + "</html>");
			}
		}

		// create edge bundling
		createEdgeBundling(view);

		return sunburst;
	}

	private String createTooltipText(QMVResultInfo resultInfo, QualityModelElement entity, IESEDataNode vertex,
	        double value[])
	{

		String valueTT = "";
		if (entity instanceof Factor)
			valueTT += "<b>Factor:</b> ";
		else
			if (entity instanceof Measure)
				valueTT += "<b>Measure:</b> ";
			else
				valueTT += "<b>" + entity.getClass().getName() + ":</b>";

		valueTT += "" + vertex + "<br/>";

		// if no value or no colorWeight: simple TT with name and description
		if (value == null || vertex.getAttribute("colorWeight") == null)
		{
			valueTT += "<b>Description:</b><br/>";
			if (entity instanceof Factor)
				valueTT += ((Factor) entity).getDescription();
			else
				if (entity instanceof Measure)
					valueTT += ((Measure) entity).getDescription();
				else
					valueTT += "-";

			valueTT += "";
			return valueTT;
		}

		Double colorWeight = (Double) vertex.getAttribute("colorWeight");
		// int grade = getGrade(colorWeight);
		double grade = OneHierarchySunburstView.getGrade(resultInfo, colorWeight);

		if (entity instanceof Factor)
		{
			valueTT += "<b>Grade:</b> " + OneHierarchySunburstView.round(grade) + "<br/>";
		}
		else
			if (entity instanceof Measure)
				valueTT += "<b>Value:</b> " + colorWeight + "<br/>";

		valueTT += "<b>Description:</b><br/>";

		if (entity instanceof Factor)
			valueTT += ((Factor) entity).getDescription();
		else
			if (entity instanceof Measure)
				valueTT += ((Measure) entity).getDescription();

		valueTT += "";

		return valueTT;
	}

	/**
	 * Computes the edge bundling curves.
	 * 
	 * @param view
	 */
	private void createEdgeBundling(IESEAbstractView view)
	{
		IESEDataGraph edgeBundlingGraph = hierarchy.getEdgeBundlingGraph();
		// System.out.println("edgeList:" + edgeList);

		edgeBundling = new IESEEdgeBundlingProperties<IESEDataNode, IESEDataEdge>(sunburstProperties, edgeBundlingGraph);
		// edgeBundling.setEdgeBundlingFactor(1.0);

		double edgeWidth = 3.0;
		for (IESEDataEdge edge : edgeBundlingGraph.edgeSet())
		{
			Double weight = hierarchy.getWeight(edge.getSource());
			if (weight != null)
			{
				edgeWidth = 3.0 + weight * (10.0 - 3.0);
			}
			edgeBundling.getEdgeRenderer(edge).setEdgeWidth(edgeWidth);
			edgeBundling.getEdgeRenderer(edge).setArrowEdgeShortening(0.0);
			edgeBundling.getEdgeRenderer(edge).setRenderTargetArrow(false);
		}

		Color edgeColor = new Color(0, 200, 0, 100);
		for (IESECurveRenderer renderer : edgeBundling.getEdgeRenderers())
		{
			// renderer.setRenderControlPolygon(true);
			// view.addRenderer(renderer.getControlPolygonRenderer());
			renderer.assignColor(RenderType.FILLED, edgeColor);
			view.addRenderer(renderer);
		}
	}

	private final Map<IESERenderer, Color> labelColorMapCompl = new HashMap<IESERenderer, Color>();

	private void initializeColorToggeling()
	{

		Color complPaintColors[] = {
		        new Color(230, 230, 255), new Color(100, 100, 255), new Color(0, 0, 255)
		};
		double complPaintColorKeys[] = {
		        0.0, 0.5, 1.0
		};

		double epsilon = 0.0001;

		// System.out.println("INITIALIZE COLOR TOGGELING:");
		for (IESERenderer renderer : sunburst.getGroupMembers())
		{
			if (renderer instanceof IESEPieShapeRenderer == false)
				continue;
			IESEDataNode vertex = (IESEDataNode) renderer.getData();
			// System.out.println("vertex:" + vertex);

			Paint paint = renderer.getPaint(RenderType.FILLED);
			Paint paintHL = renderer.getPaint(RenderType.HIGHLIGHT_FILLED);
			Paint paintPicked = renderer.getPaint(RenderType.PICKED_FILLED);

			paintMapEval.put(renderer, paint);
			// tooltipMapEval.put(renderer, renderer.getTooltipText());

			// compute paints for completeness visualization
			IESEPiePaintManager piePaintManager = new IESEPiePaintManager();

			double radius = sunburstProperties.getRadius(vertex);
			double size = sunburstProperties.getSize(vertex);

			if (vertex.getData() instanceof Factor)
			{
				Factor factor = (Factor) vertex.getData();

				QualityModel qmModel = QmModelUtils.getDeepestModelInRequiresRelation(factor);

				Double completeness = factor.getAccumulatedCompleteness().get(qmModel);
				if (completeness == null)
					continue;

				// System.out.println("\tcompleteness(" + vertex + "):"
				// + completeness);

				completeness = Math.round(100.0 * completeness) / 100.0;

				if (completeness <= 0.3)
					labelColorMapCompl.put(renderer, Color.black);
				else
					labelColorMapCompl.put(renderer, Color.yellow);
				// Paint paintCompl = piePaintManager.createPaint(radius, size,
				// completeness);
				Paint paintCompl = piePaintManager.createPaint(radius, size, completeness, complPaintColors,
				        complPaintColorKeys);

				paintMapCompl.put(renderer, paintCompl);
				tooltipMapCompl.put(renderer, tooltipMapEval.get(renderer) + "<br><b>Completeness:</b>" + completeness);

			}
		}
	}

	private void toggleColoring(boolean useEvaluation)
	{
		for (IESERenderer renderer : sunburst.getGroupMembers())
		{
			if (renderer instanceof IESEPieShapeRenderer == false)
				continue;

			IESEDataNode vertex = (IESEDataNode) renderer.getData();

			Paint paint = null;
			String tooltipText = null;
			Color labelColor = Color.black;

			if (useEvaluation)
			{
				paint = paintMapEval.get(renderer);
				tooltipText = tooltipMapEval.get(renderer);
			}
			else
			{
				paint = paintMapCompl.get(renderer);
				tooltipText = tooltipMapCompl.get(renderer);

				labelColor = labelColorMapCompl.get(renderer);
				if (labelColor == null)
					labelColor = Color.black;
			}

			if (paint == null)
				paint = paintMapEval.get(renderer);
			if (tooltipText == null)
				tooltipText = tooltipMapEval.get(renderer);

			renderer.assignPaint(RenderType.FILLED, paint);
			renderer.setTooltipText("<html>" + tooltipText + "</html>");
			((IESEPieShapeRenderer) renderer).setTextColor(labelColor);
		}
		view.repaint();
	}
}
