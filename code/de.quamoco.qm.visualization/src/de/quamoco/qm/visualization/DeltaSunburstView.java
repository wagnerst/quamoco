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
import iese.inseviz.data.IESEDataNode;
import iese.inseviz.util.log.IESELogger;
import iese.inseviz.util.log.IESELogger.IESELogLevel;
import iese.inseviz.util.ui.image.IESECaptureUtil;
import iese.inseviz.visualization.IESEAbstractView;
import iese.inseviz.visualization.IESEView;
import iese.inseviz.visualization.interaction.IESECaptureRequestHandler;
import iese.inseviz.visualization.interaction.IESEHighlightHandler;
import iese.inseviz.visualization.interaction.IESEPickingHandler;
import iese.inseviz.visualization.interaction.IESETooltipHandler;
import iese.inseviz.visualization.interaction.IESETooltipListener;
import iese.inseviz.visualization.renderer.IESEPieShapeRenderer;
import iese.inseviz.visualization.renderer.IESEPieShapeRenderer.PieLabelOrientation;
import iese.inseviz.visualization.renderer.IESERenderer;
import iese.inseviz.visualization.renderer.IESERenderer.RenderType;
import iese.inseviz.visualization.renderer.stroke.IESEBasicStroke;
import iese.inseviz.visualization.renderer.sunburst.IESESunburstProperties;
import iese.inseviz.visualization.renderer.sunburst.IESESunburstRenderProperties;
import iese.inseviz.visualization.renderer.sunburst.IESESunburstRenderer;
import iese.inseviz.visualization.renderer.sunburst.interaction.IESESunburstPickhandler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JPanel;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;

import de.quamoco.qm.Factor;
import de.quamoco.qm.Measure;
import de.quamoco.qm.QualityModelElement;
import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.visualization.model.QMVModelStructureHierarchy;
import de.quamoco.qm.visualization.util.QMVQualityModelInfo;
import de.quamoco.qm.visualization.util.QMVResultInfo;

public class DeltaSunburstView extends IESEVisualizationViewBase<List<QualityModelResult>>
{

	/** The identifier of the view as in the plugin.xml. */
	public static final String ID = DeltaSunburstView.class.getName();
	/** The swing visualization view. */
	private IESEView view = null;

	private IESESunburstRenderer sunburst = null;
	/** A handler for the swing tool tips. */
	private IESETooltipHandler tooltipHandler = null;
	private boolean deltaA_B = true;
	private Map<IESEDataNode, Paint> abPaintMap = null;
	private Map<IESEDataNode, Paint> baPaintMap = null;
	private Map<IESEDataNode, String> abTooltipMap = null;
	private Map<IESEDataNode, String> baTooltipMap = null;

	/** {@inheritDoc} */
	@Override
	protected void createActions()
	{
		// add action buttons
		IToolBarManager toolBar = getViewSite().getActionBars().getToolBarManager();
		Action action = new Action("View All", IAction.AS_PUSH_BUTTON)
		{
			/** {@inheritDoc} */
			@Override
			public void run()
			{
				if (view != null)
					view.viewAll();
			}
		};
		Action abAction = new Action("A-B", IAction.AS_RADIO_BUTTON)
		{
			/** {@inheritDoc} */
			@Override
			public void run()
			{
				if (isChecked())
				{
					if (deltaA_B == true)
						return;
					deltaA_B = true;

					toggleDeltaComputation(true);
				}
			}
		};
		Action baAction = new Action("B-A", IAction.AS_RADIO_BUTTON)
		{
			/** {@inheritDoc} */
			@Override
			public void run()
			{
				if (isChecked())
				{
					if (deltaA_B == false)
						return;
					deltaA_B = false;
					toggleDeltaComputation(false);
				}
			}
		};
		if (deltaA_B)
			abAction.setChecked(true);
		else
			baAction.setChecked(true);

		toolBar.add(abAction);
		toolBar.add(baAction);
		toolBar.add(action);
	}

	/** {@inheritDoc} */
	@Override
	protected void inputChanged(List<QualityModelResult> element, JPanel panel)
	{
		if (getElement() == null)
		{
			System.err.println("DeltaSunburstView: no results specified!");
			System.err.flush();
			return;
		}

		IESELogger.setLevel(IESELogLevel.OFF);

		QMVQualityModelInfo modelInfo = new QMVQualityModelInfo();

		for (QualityModelResult result : getElement())
		{
			modelInfo.addResult(result);
		}

		// create the visualization view
		view = createVisualization(modelInfo);
		panel.removeAll();
		panel.add(view);

		tooltipHandler = new IESETooltipHandler(view);
		tooltipHandler.addTooltipListener(new IESETooltipListener()
		{

			@Override
			public void showTooltip(BufferedImage arg0, String arg1)
			{
				DeltaSunburstView.this.showTooltip(arg1);
			}

			@Override
			public void showTooltip(IESERenderer arg0, String arg1)
			{
				DeltaSunburstView.this.showTooltip(arg1);
			}

			@Override
			public void clear()
			{
				DeltaSunburstView.this.showTooltip("");
			}
		});

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
	}

	/**
	 * Creates the visualization and returns the view containing all renderers.
	 * 
	 * @param modelInfo
	 * @return the view to the visualization
	 */
	/**
	 * @param modelInfo
	 * @return the visualization view
	 */
	private IESEView createVisualization(QMVQualityModelInfo modelInfo)
	{
		// create a view
		final IESEView view = new IESEView();

		{
			// setup view text info to show the name of both result sets in the view
			QualityModelResult resultA = modelInfo.getResultList().get(0);
			QualityModelResult resultB = modelInfo.getResultList().get(1);

			Font textInfoFont = new Font("Arial", Font.BOLD, 12);
			view.setDrawTextInfo(true);
			view.addTextInfo("A=" + resultA.getSystem().trim(), textInfoFont, Color.black,
			        IESEView.ViewTextInfoPosition.BOTTOM_LEFT);
			view.addTextInfo("B=" + resultB.getSystem().trim(), textInfoFont, Color.black,
			        IESEView.ViewTextInfoPosition.BOTTOM_LEFT);

		}
		// initialize a-b/b-a delta toggling maps
		abPaintMap = new HashMap<IESEDataNode, Paint>();
		baPaintMap = new HashMap<IESEDataNode, Paint>();
		abTooltipMap = new HashMap<IESEDataNode, String>();
		baTooltipMap = new HashMap<IESEDataNode, String>();
		// create hierarchy
		QMVModelStructureHierarchy hierarchy = new QMVModelStructureHierarchy();
		hierarchy.createHierarchy(modelInfo);
		// hierarchy.getHierarchy().printTree("", "HiearchyTree:");

		// create sunburst from hierarchy
		sunburst = this.createSunburst(view, hierarchy);

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
		IESEPickingHandler pickingHandler = new IESEPickingHandler(view);
		// IESEDraggingHandler draggingHandler = new IESEDraggingHandler(view);
		tooltipHandler = new IESETooltipHandler(view);

		IESESunburstPickhandler focusHandler = new IESESunburstPickhandler(view, sunburst);
		focusHandler.setAnimated(true);
		focusHandler.setPercentage(0.9);// take 90% of parental angle extent for
		                                // the child

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
	private IESESunburstRenderer createSunburst(IESEAbstractView view, QMVModelStructureHierarchy hierarchy)
	{
		// compute sunburst properties
		IESESunburstProperties<IESEDataNode, IESEDataEdge> sunburstProperties = new IESESunburstProperties<IESEDataNode, IESEDataEdge>(
		        hierarchy.getHierarchy(), hierarchy.getHierarchy().getRootVertex(), null);

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
		sunburstProperties.setRootRadius(100.0);
		sunburstProperties.setDefaultMinAngleExtent(0.5);
		sunburstProperties.setDefaultAngularDistance(0.5);
		sunburstProperties.setSegmentAngularDistance(2.0);

		sunburstProperties.setDefaultLabelOrientation(PieLabelOrientation.RADIAL);
		sunburstProperties.setUseTangentialLabelOrientationOnMinAngleExtent(true);
		sunburstProperties.setLabelOrientation(hierarchy.getHierarchy().getRootVertex(), PieLabelOrientation.RADIAL);

		for (IESEDataNode vertex : hierarchy.getHierarchy().vertexSet())
		{
			if (vertex.hasAttribute("ringDistanceMult"))
			{
				double mult = (Double) vertex.getAttribute("ringDistanceMult");

				sunburstProperties.setSize(vertex,
				        sunburstProperties.getSize(vertex) + mult * sunburstProperties.getDefaultSize());
				sunburstProperties.setDistance(vertex, 70.0);

			}
		}

		sunburstProperties.setInverseSunburst(false);

		// now compute the sunburst properties
		sunburstProperties.computeSunburstProperties(0.0, 360.0);

		// get result values
		QMVQualityModelInfo qualityModelInfo = hierarchy.getModelInfo();
		QMVResultInfo resultInfo = new QMVResultInfo();
		resultInfo.collectResultInfo(qualityModelInfo);

		// compute sunburst render properties
		IESESunburstRenderProperties<IESEDataNode, IESEDataEdge> sunburstRenderProperties = new IESESunburstRenderProperties<IESEDataNode, IESEDataEdge>();
		Font font68 = new Font("Arial", Font.BOLD, 68);
		Font font24 = new Font("Arial", Font.BOLD, 24);

		// define shading color key-frames

		Color gelb = new Color(235, 255, 10);
		Color gruen = new Color(0, 195, 149);
		Color blau = new Color(79, 129, 189);
		Color lila = new Color(160, 132, 230);
		Color pink = new Color(255, 125, 218);

		Color factorDeltaPaintColors[] = {
		        gruen, gruen, gelb, blau, lila, pink, pink,
		};
		double factorDeltaPaintColorKeys[] = {
		        -5.0, -1.0, -0.0001, 0.0, 0.0001, 1.0, 5.0
		};
		Color measureDeltaPaintColorsPositive[] = {
		        pink, pink, lila, blau, gelb, gruen, gruen,
		// gelb, gruen, blau, lila, pink
		};
		Color measureDeltaPaintColorsNegative[] = {
		        gruen, gruen, gelb, blau, lila, pink, pink,
		};
		// !!!!!!!!!!!
		measureDeltaPaintColorsNegative = measureDeltaPaintColorsPositive;

		double measureDeltaPaintColorKeys[] = {
		        -1.0, -0.02, -0.0001, 0.0, 0.0001, 0.02, 1.0
		// 0.0, 0.499, 0.5, 0.501, 1.0
		};

		sunburstRenderProperties.setDefaultFillColorHL(Color.cyan);
		sunburstRenderProperties.setDefaultLabelFont(font24);
		sunburstRenderProperties.setDefaultLabelColor(Color.black);

		sunburstRenderProperties.getPiePaintManager().setDefaultInterpolationColors(factorDeltaPaintColors);
		sunburstRenderProperties.getPiePaintManager().setDefaultInterpolationColorFractions(factorDeltaPaintColorKeys);

		for (IESEDataNode vertex : sunburstProperties.getSunburstVertices())
		{
			sunburstRenderProperties.getPiePaintManager().setDefaultInterpolationColors(factorDeltaPaintColors);
			sunburstRenderProperties.getPiePaintManager().setDefaultInterpolationColorFractions(
			        factorDeltaPaintColorKeys);

			double radius = sunburstProperties.getRadius(vertex);
			double size = sunburstProperties.getSize(vertex);

			QualityModelElement entity = hierarchy.getVertexFactory().getEntity(vertex);

			if (entity instanceof Factor)
			{
				Factor factor = (Factor) entity;
				sunburstRenderProperties.getPiePaintManager().setDefaultInterpolationColors(factorDeltaPaintColors);
				sunburstRenderProperties.getPiePaintManager().setDefaultInterpolationColorFractions(
				        factorDeltaPaintColorKeys);

				defineFactorPaint(sunburstProperties, sunburstRenderProperties, resultInfo, qualityModelInfo, vertex,
				        factor);
			}
			else
				if (entity instanceof Measure)
				{
					Measure measure = (Measure) entity;

					Boolean positiveImpact = (Boolean) vertex.getAttribute("positiveImpact");

					// set painting
					if (positiveImpact != null)
					{
						if (positiveImpact == true)
							sunburstRenderProperties.getPiePaintManager().setDefaultInterpolationColors(
							        measureDeltaPaintColorsPositive);
						else
							sunburstRenderProperties.getPiePaintManager().setDefaultInterpolationColors(
							        measureDeltaPaintColorsNegative);

						sunburstRenderProperties.getPiePaintManager().setDefaultInterpolationColorFractions(
						        measureDeltaPaintColorKeys);

						defineMeasurePaint(sunburstProperties, sunburstRenderProperties, resultInfo, qualityModelInfo,
						        vertex, measure, positiveImpact);
					}
					else
						sunburstRenderProperties.setPaint(vertex, RenderType.FILLED, radius, size);
				}
				else
					// no factor, no measure => no shading
					sunburstRenderProperties.setPaint(vertex, RenderType.FILLED, radius, size);

			// highlighting and picking
			sunburstRenderProperties.setPaint(vertex, RenderType.HIGHLIGHT_FILLED, radius, size);
			sunburstRenderProperties.setPaint(vertex, RenderType.PICKED_FILLED, radius, size);

			// sunburstRenderProperties.setOmitVertexLabel(vertex, true);
		}
		// create sunburst renderer using the sunburst properties
		IESESunburstRenderer sunburst = new IESESunburstRenderer("Sunburst");
		sunburst.setRenderRoot(false);
		sunburst.setRenderRootAsCircle(true);
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

				// use the value to precompute tooltip text for A-B and B-A
				deltaA_B = false;
				String valueTT_BA = createTooltipText(hierarchy.getModelInfo(), resultInfo, entity, vertex);
				// vertex.setAttribute("tooltipBA", valueTT_BA);
				baTooltipMap.put(vertex, valueTT_BA);

				deltaA_B = true;
				String valueTT_AB = createTooltipText(hierarchy.getModelInfo(), resultInfo, entity, vertex);
				// vertex.setAttribute("tooltipAB", valueTT_AB);
				abTooltipMap.put(vertex, valueTT_AB);

				renderer.setTooltipText(valueTT_AB);
			}
		}
		return sunburst;
	}

	private void defineFactorPaint(IESESunburstProperties<IESEDataNode, IESEDataEdge> sunburstProperties,
	        IESESunburstRenderProperties<IESEDataNode, IESEDataEdge> sunburstRenderProperties,
	        QMVResultInfo resultInfo, QMVQualityModelInfo qualityModelInfo, IESEDataNode vertex, Factor factor)
	{
		boolean debug = false;

		double radius = sunburstProperties.getRadius(vertex);
		double size = sunburstProperties.getSize(vertex);

		QualityModelResult resultA = qualityModelInfo.getResultList().get(0);
		QualityModelResult resultB = qualityModelInfo.getResultList().get(1);
		double valueA[] = resultInfo.getValue(resultA, factor);
		double valueB[] = resultInfo.getValue(resultB, factor);
		Boolean positiveImpact = (Boolean) vertex.getAttribute("positiveImpact");

		double eps = 10000.0;
		double lower = Math.round(valueA[0] * eps) / eps;
		double upper = Math.round(valueA[1] * eps) / eps;

		double colorWeightA = lower + 0.5 * (upper - lower);

		lower = Math.round(valueB[0] * eps) / eps;
		upper = Math.round(valueB[1] * eps) / eps;

		double colorWeightB = lower + 0.5 * (upper - lower);

		if (positiveImpact != null && positiveImpact == false)
		{
			colorWeightA = 1.0 - colorWeightA;
			colorWeightB = 1.0 - colorWeightB;
		}
		double gradeA = OneHierarchySunburstView.getGrade(resultInfo, colorWeightA);
		double gradeB = OneHierarchySunburstView.getGrade(resultInfo, colorWeightB);

		vertex.setAttribute("gradeA", gradeA);
		vertex.setAttribute("gradeB", gradeB);

		if (debug)
		{
			System.out.println("defineFactorPaint:");
			System.out.println("\tfactor:" + factor.getQualifiedName());
			System.out.println("\t\tvalueA:[" + valueA[0] + "," + valueA[1] + "]");
			System.out.println("\t\tvalueB:[" + valueB[0] + "," + valueB[1] + "]");

			System.out.println("\t\tcolorWeightA:" + colorWeightA);
			System.out.println("\t\tcolorWeightB:" + colorWeightB);

			System.out.println("\t\tgradeA:" + gradeA);
			System.out.println("\t\tgradeB:" + gradeB);
		}
		// precompute painting

		// B-A
		Double deltaColorWeightBA = gradeB - gradeA;
		sunburstRenderProperties.setPaint(vertex, RenderType.FILLED, radius, size, deltaColorWeightBA);
		baPaintMap.put(vertex, sunburstRenderProperties.getPaint(vertex, RenderType.FILLED));

		// A-B
		Double deltaColorWeightAB = gradeA - gradeB;
		sunburstRenderProperties.setPaint(vertex, RenderType.FILLED, radius, size, deltaColorWeightAB);
		abPaintMap.put(vertex, sunburstRenderProperties.getPaint(vertex, RenderType.FILLED));

		if (debug)
		{
			System.out.println("\t\ta-b color weight:" + deltaColorWeightAB);
			System.out.println("\t\tb-a color weight:" + deltaColorWeightBA);
		}
		// finally set the real painting (color weight and paint to use)
		if (deltaA_B)
		{
			vertex.setAttribute("colorWeight", deltaColorWeightAB);
			sunburstRenderProperties.setPaint(vertex, RenderType.FILLED, radius, size, deltaColorWeightAB);
		}
		else
		{
			vertex.setAttribute("colorWeight", deltaColorWeightBA);
			sunburstRenderProperties.setPaint(vertex, RenderType.FILLED, radius, size, deltaColorWeightBA);
		}

	}

	// private void definePaintAndTooltip(IESESunburstProperties<IESEDataNode, IESEDataEdge> sunburstProperties,
	// IESESunburstRenderProperties<IESEDataNode, IESEDataEdge> sunburstRenderProperties,
	// QMVResultInfo resultInfo, QMVQualityModelInfo qualityModelInfo, IESEDataNode vertex, Factor factor)
	// {
	// double radius = sunburstProperties.getRadius(vertex);
	// double size = sunburstProperties.getSize(vertex);
	//
	// QualityModelResult resultA = qualityModelInfo.getResultList().get(0);
	// QualityModelResult resultB = qualityModelInfo.getResultList().get(1);
	//
	// deltaA_B = false;
	// Double deltaColorWeightBA = getDeltaGradeColorWeight(resultInfo, resultA, resultB, factor);
	//
	// if (deltaColorWeightBA != null)
	// {
	// vertex.setAttribute("colorWeight", deltaColorWeightBA);
	// sunburstRenderProperties.setPaint(vertex, RenderType.FILLED, radius, size, deltaColorWeightBA);
	// baPaintMap.put(vertex, sunburstRenderProperties.getPaint(vertex, RenderType.FILLED));
	// }
	//
	// deltaA_B = true;
	// Double deltaColorWeightAB = getDeltaGradeColorWeight(resultInfo, resultA, resultB, factor);
	//
	// if (deltaColorWeightAB != null)
	// {
	// vertex.setAttribute("colorWeight", deltaColorWeightAB);
	// sunburstRenderProperties.setPaint(vertex, RenderType.FILLED, radius, size, deltaColorWeightAB);
	// abPaintMap.put(vertex, sunburstRenderProperties.getPaint(vertex, RenderType.FILLED));
	// }
	// else
	// sunburstRenderProperties.setPaint(vertex, RenderType.FILLED, radius, size);
	// }

	/**
	 * Returns the measure values with respect to the impact.
	 * 
	 * @param qualityModelInfo
	 * @param resultInfo
	 * @param measure
	 * @param positiveImpact
	 * @return
	 */
	private Double[] getMeasureValues(QMVQualityModelInfo qualityModelInfo, QMVResultInfo resultInfo, Measure measure,
	        Boolean positiveImpact)
	{
		QualityModelResult resultA = qualityModelInfo.getResultList().get(0);
		QualityModelResult resultB = qualityModelInfo.getResultList().get(1);
		double valueA[] = resultInfo.getValue(resultA, measure);
		double valueB[] = resultInfo.getValue(resultB, measure);
		Double measureValueA = getMidValue(valueA[0], valueA[1]);
		Double measureValueB = getMidValue(valueB[0], valueB[1]);

		if (positiveImpact != null && positiveImpact == false)
		{
			measureValueA = 1.0 - measureValueA;
			measureValueB = 1.0 - measureValueB;
		}

		return new Double[] {
		        measureValueA, measureValueB
		};
	}

	/**
	 * Computes the paints for A-B and B-A and store the information in the maps.
	 * 
	 * @param sunburstProperties
	 * @param sunburstRenderProperties
	 * @param resultInfo
	 * @param qualityModelInfo
	 * @param vertex
	 * @param measure
	 * @param positiveImpact
	 */
	private void defineMeasurePaint(IESESunburstProperties<IESEDataNode, IESEDataEdge> sunburstProperties,
	        IESESunburstRenderProperties<IESEDataNode, IESEDataEdge> sunburstRenderProperties,
	        QMVResultInfo resultInfo, QMVQualityModelInfo qualityModelInfo, IESEDataNode vertex, Measure measure,
	        Boolean positiveImpact)
	{
		double radius = sunburstProperties.getRadius(vertex);
		double size = sunburstProperties.getSize(vertex);

		Double measureValues[] = getMeasureValues(qualityModelInfo, resultInfo, measure, positiveImpact);
		Double measureValueA = measureValues[0];
		Double measureValueB = measureValues[1];

		// precompute painting
		Double deltaColorWeightAB = null, deltaColorWeightBA = null;
		// A-B
		deltaColorWeightAB = measureValueA - measureValueB;
		sunburstRenderProperties.setPaint(vertex, RenderType.FILLED, radius, size, deltaColorWeightAB);
		abPaintMap.put(vertex, sunburstRenderProperties.getPaint(vertex, RenderType.FILLED));

		// B-A
		deltaColorWeightBA = measureValueB - measureValueA;
		sunburstRenderProperties.setPaint(vertex, RenderType.FILLED, radius, size, deltaColorWeightBA);
		baPaintMap.put(vertex, sunburstRenderProperties.getPaint(vertex, RenderType.FILLED));

		// finally set the real painting (color weight and paint to use)
		if (deltaA_B)
		{
			vertex.setAttribute("colorWeight", deltaColorWeightAB);
			sunburstRenderProperties.setPaint(vertex, RenderType.FILLED, radius, size, deltaColorWeightAB);
		}
		else
		{
			vertex.setAttribute("colorWeight", deltaColorWeightBA);
			sunburstRenderProperties.setPaint(vertex, RenderType.FILLED, radius, size, deltaColorWeightBA);
		}
	}

	/**
	 * Creates the tooltip string.
	 * 
	 * @param qualityModelInfo
	 * @param resultInfo
	 * @param entity
	 * @param vertex
	 * @param value
	 * @return
	 */
	private String createTooltipText(QMVQualityModelInfo qualityModelInfo, QMVResultInfo resultInfo,
	        QualityModelElement entity, IESEDataNode vertex)
	{

		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
		numberFormat.setMinimumFractionDigits(3);

		String valueTT = "<html>";
		if (entity instanceof Factor)
			valueTT += "<b>Factor:</b>";
		else
			if (entity instanceof Measure)
				valueTT += "<b>Measure:</b>";
			else
				valueTT += "<b>" + entity.getClass().getName() + ":</b>";

		valueTT += "" + vertex + "<br>";

		// if no value or no colorWeight: simple TT with name and description
		if (vertex.getAttribute("colorWeight") == null)
		{
			valueTT += "<b>Description:</b><br>";
			if (entity instanceof Factor)
				valueTT += ((Factor) entity).getDescription();
			else
				if (entity instanceof Measure)
					valueTT += ((Measure) entity).getDescription();
				else
					valueTT += "-";

			valueTT += "</html>";
			return valueTT;
		}

		if (entity instanceof Factor)
		{
			Double gradeA = null, gradeB = null;
			Double colorWeight = (Double) vertex.getAttribute("colorWeight");
			if (vertex.hasAttribute("gradeA"))
				gradeA = (Double) vertex.getAttribute("gradeA");
			if (vertex.hasAttribute("gradeB"))
				gradeB = (Double) vertex.getAttribute("gradeB");

			// valueTT += "<b>Color Weight:</b>" + round(colorWeight) + "<br>";
			if (gradeA != null)
			{
				valueTT += "<b>GradeA:</b>" + numberFormat.format(round(gradeA)) + "<br>";
				// valueTT += "<b>GradeA:</b>" + numberFormat.format(gradeA) + "<br>";
			}
			if (gradeB != null)
			{
				valueTT += "<b>GradeB:</b>" + numberFormat.format(round(gradeB)) + "<br>";
				// valueTT += "<b>GradeB:</b>" + numberFormat.format(gradeB) + "<br>";
			}

			if (gradeA != null && gradeB != null)
			{
				double deltaGrade = 0.0;

				if (deltaA_B)
					deltaGrade = gradeA - gradeB;
				else
					deltaGrade = gradeB - gradeA;

				String deltaComment = "";
				if (deltaA_B)
					deltaComment = "<b>GradeA-GradeB:</b>";
				else
					deltaComment = "<b>GradeB-GradeA:</b>";
				valueTT += deltaComment + numberFormat.format(round(deltaGrade)) + "<br>";
				// valueTT += deltaComment + numberFormat.format(deltaGrade) + "<br>";
			}
		}
		else
			if (entity instanceof Measure)
			{
				// valueTT += "<b>Value:</b>" + colorWeight + "<br>";
				Boolean positiveImpact = (Boolean) vertex.getAttribute("positiveImpact");
				// System.out.println("measure " + entity.getQualifiedName() + " impact:" + positiveImpact);
				if (positiveImpact != null)
				{
					valueTT += "<b>Impact:</b>" + (positiveImpact ? "positive" : "negative") + "<br>";
				}

				Double measureValues[] = getMeasureValues(qualityModelInfo, resultInfo, (Measure) entity,
				        positiveImpact);
				Double measureValueA = measureValues[0];
				Double measureValueB = measureValues[1];

				Double deltaValue = null;
				if (deltaA_B)
					deltaValue = measureValueA - measureValueB;
				else
					deltaValue = measureValueB - measureValueA;
				deltaValue = this.round(deltaValue);

				valueTT += "<b>ValueA:</b>" + numberFormat.format(round(measureValueA)) + "<br>";
				valueTT += "<b>ValueB:</b>" + numberFormat.format(round(measureValueB)) + "<br>";

				String deltaComment = "";
				if (deltaA_B)
					deltaComment = "<b>ValueA-ValueB:</b>";
				else
					deltaComment = "<b>ValueB-ValueA:</b>";
				// valueTT += deltaComment + round(deltaValue) + "<br>";
				valueTT += deltaComment + numberFormat.format(round(deltaValue)) + "<br>";
			}

		valueTT += "<b>Description:</b><br>";

		if (entity instanceof Factor)
			valueTT += ((Factor) entity).getDescription();
		else
			if (entity instanceof Measure)
				valueTT += ((Measure) entity).getDescription();

		// if (false && entity instanceof Measure)
		// {
		// Measure measure = (Measure) entity;
		// List<FindingMessage> findingMessages = resultInfo.getFindingMessages(getElement().get(0), measure);
		// if (findingMessages != null && findingMessages.size() > 0)
		// {
		// valueTT += "<br><b>" + findingMessages.size() + " Findings:</b>";
		// valueTT += "<ol>";
		// for (FindingMessage message : findingMessages)
		// {
		// valueTT += "<li>" + message.getMessage();
		// String location = message.getLocation();
		// int lastMinus = location.lastIndexOf("-");
		// if (lastMinus != -1)
		// {
		// location = location.substring(0, lastMinus) + "to" + location.substring(lastMinus + 1);
		// }
		// valueTT += "<br><b>location:</b>" + location;
		// valueTT += "</li>";
		// }
		// valueTT += "</ol>";
		// }
		// }
		valueTT += "</html>";

		return valueTT;
	}

	// protected Double getDeltaGradeColorWeight(QMVResultInfo resultInfo, QualityModelResult resultA,
	// QualityModelResult resultB, Factor factor)
	// {
	// boolean debug = false;
	//
	// Double colorWeight = null;
	//
	// double minDelta = -6.0;
	// double maxDelta = 6.0;
	//
	// double valueA[] = resultInfo.getValue(resultA, factor);
	// double valueB[] = resultInfo.getValue(resultB, factor);
	//
	// if (valueA != null && valueB != null && valueA.length == 2 && valueB.length == 2)
	// {
	// if (debug)
	// {
	// System.out.println("getDeltaGradeColorWeight:");
	// System.out.println("\tfactor:" + factor.getQualifiedName());
	// }
	// double deltaGrade = getDeltaGrade(valueA, valueB);
	// colorWeight = (deltaGrade - minDelta) / (maxDelta - minDelta);
	//
	// if (debug)
	// {
	// System.out.println("\tdeltaGrade(" + factor.getQualifiedName() + ")=" + deltaGrade);
	// System.out.println("\tdeltaColorWeight=" + colorWeight);
	// }
	// }
	// return colorWeight;
	// }

	// protected Double getDeltaMeasureColorWeight(QMVResultInfo resultInfo, QualityModelResult resultA,
	// QualityModelResult resultB, Measure measure)
	// {
	// boolean debug = true;
	//
	// Double colorWeight = null;
	//
	// double minDelta = -1.0;
	// double maxDelta = 1.0;
	//
	// double valueA[] = resultInfo.getValue(resultA, measure);
	// double valueB[] = resultInfo.getValue(resultB, measure);
	//
	// if (valueA != null && valueB != null && valueA.length == 2 && valueB.length == 2)
	// {
	// if (debug)
	// {
	// System.out.println("getDeltaMeasureColorWeight:");
	// System.out.println("\tmeasure:" + measure.getQualifiedName());
	// }
	//
	// double deltaValue = getDeltaValue(valueA, valueB);
	//
	// // colorWeight = this.round((deltaValue - minDelta) / (maxDelta - minDelta));
	// colorWeight = deltaValue;
	//
	// if (debug)
	// {
	// System.out.println("\tdeltaValue(" + measure.getQualifiedName() + ")=" + deltaValue);
	// System.out.println("\tdeltaColorWeight=" + colorWeight);
	// }
	// }
	// return colorWeight;
	// }

	/**
	 * Returns the grade delta, based on the actual selected type (A-B or B-A).
	 * 
	 * @param valueA
	 * @param valueB
	 * @return the grade delta.
	 */
	protected double getDeltaGrade(QMVResultInfo resultInfo, double valueA[], double valueB[])
	{
		double gradeA = getGrade(resultInfo, valueA[0], valueA[1]);
		double gradeB = getGrade(resultInfo, valueB[0], valueB[1]);
		double deltaGrade = 0.0;

		if (deltaA_B)
			deltaGrade = gradeA - gradeB;
		else
			deltaGrade = gradeB - gradeA;

		return deltaGrade;
	}

	/**
	 * Return the grade for the given interval (returns the mid point).
	 * 
	 * @param lower
	 * @param upper
	 * @return the midpoint grade.
	 */
	protected double getGrade(QMVResultInfo resultInfo, double lower, double upper)
	{
		double grade = getMidValue(lower, upper);
		// return getGrade(grade);
		return OneHierarchySunburstView.getGrade(resultInfo, grade);
	}

	/**
	 * Returns the mid point of the interval.
	 * 
	 * @param lower
	 * @param upper
	 * @return the mid value.
	 */
	protected double getMidValue(double lower, double upper)
	{
		// lower = this.round(lower);
		// upper = this.round(upper);

		double value = lower + 0.5 * (upper - lower);
		return value;
		// return this.round(value);
	}

	/**
	 * Rounds the given value up to 5 decimal places.
	 * 
	 * @param value
	 * @return the rounded value.
	 */
	protected double round(double value)
	{
		double eps = 10000.0;
		return Math.round(value * eps) / eps;
	}

	// /**
	// * Computes a scholar grade for a given weight from [0,1].
	// *
	// * @param w
	// * @return the grade
	// */
	// private int getGrade(double w)
	// {
	// int grade = 1;
	// if (w <= 0.9)
	// grade = 6;
	// else
	// {
	// double notenInterval[] = {
	// 0.9, 0.92, 0.94, 0.96, 0.98, 1.0
	// };
	// grade = 5;
	// for (int i = 0; i < notenInterval.length - 1; i++)
	// {
	// if (notenInterval[i] < w && w <= notenInterval[i + 1])
	// {
	// break;
	// }
	// grade--;
	// }
	// }
	// return grade;
	// }

	private void toggleDeltaComputation(boolean useEvaluation)
	{
		for (IESERenderer renderer : sunburst.getGroupMembers())
		{
			if (renderer instanceof IESEPieShapeRenderer == false)
				continue;

			IESEDataNode vertex = (IESEDataNode) renderer.getData();

			Paint paint = null;
			String tooltipText = null;

			if (deltaA_B)
			{
				paint = abPaintMap.get(vertex);
				tooltipText = abTooltipMap.get(vertex);
			}
			else
			{
				paint = baPaintMap.get(vertex);
				tooltipText = baTooltipMap.get(vertex);
			}
			if (paint != null)
				renderer.assignPaint(RenderType.FILLED, paint);
			if (tooltipText != null)
				renderer.setTooltipText("<html>" + tooltipText + "</html>");

		}
		view.repaint();
	}
}
