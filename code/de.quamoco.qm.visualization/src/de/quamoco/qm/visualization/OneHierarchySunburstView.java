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
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.jgrapht.Graphs;

import de.quamoco.qm.Factor;
import de.quamoco.qm.FindingMessage;
import de.quamoco.qm.Measure;
import de.quamoco.qm.QualityModelElement;
import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.visualization.model.QMVModelStructureHierarchy;
import de.quamoco.qm.visualization.util.QMVQualityModelInfo;
import de.quamoco.qm.visualization.util.QMVResultInfo;

/**
 * TODO: Add documentation.
 * 
 * @author herrmama
 * @author $Author: HenningBarthel $
 * @version $Rev: 4968 $
 * @levd.rating RED Rev:
 */
public class OneHierarchySunburstView extends IESEVisualizationViewBase<QualityModelResult>
{

	/** The identifier of the view as in the plugin.xml. */
	public static final String ID = OneHierarchySunburstView.class.getName();

	/** The swing visualization view. */
	private IESEView view = null;
	/** A handler for the swing tool tips. */
	private IESETooltipHandler tooltipHandler = null;

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
		panel.removeAll();
		panel.add(view);

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
				OneHierarchySunburstView.this.showTooltip(arg1);
			}

			@Override
			public void showTooltip(IESERenderer arg0, String arg1)
			{
				OneHierarchySunburstView.this.showTooltip(arg1);
			}

			@Override
			public void clear()
			{
				OneHierarchySunburstView.this.showTooltip("");
			}
		});

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
	}

	/** {@inheritDoc} */
	@Override
	public void setFocus()
	{
		// if (splitPane != null)
		// splitPane.setDividerLocation(0.25);

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

		// create hierarchy
		QMVModelStructureHierarchy hierarchy = new QMVModelStructureHierarchy();
		hierarchy.createHierarchy(modelInfo);
		// hierarchy.getHierarchy().printTree("", "HiearchyTree:");

		// create sunburst from hierarchy
		final IESESunburstRenderer sunburst = createSunburst(view, hierarchy);

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
		QMVResultInfo resultInfo = new QMVResultInfo();
		resultInfo.collectResultInfo(hierarchy.getModelInfo());

		// compute sunburst render properties
		IESESunburstRenderProperties<IESEDataNode, IESEDataEdge> sunburstRenderProperties = new IESESunburstRenderProperties<IESEDataNode, IESEDataEdge>();
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
			else
				if (entity instanceof Measure)
				{
					Measure measure = (Measure) entity;
					value = resultInfo.getValue(hierarchy.getModelInfo().getResultList().get(0), measure);
				}

			if (value != null)
			{
				double lower = Math.round(value[0] * eps) / eps;
				double upper = Math.round(value[1] * eps) / eps;

				double colorWeight = lower + 0.5 * (upper - lower);

				Boolean positiveImpact = (Boolean) vertex.getAttribute("positiveImpact");
				if (entity instanceof Measure)
				{
					IESEDataNode parentFactorVertex = Graphs.predecessorListOf(hierarchy.getHierarchy(), vertex).get(0);
					positiveImpact = (Boolean) parentFactorVertex.getAttribute("positiveImpact");
				}
				if (positiveImpact != null)
				{
					sunburstRenderProperties.getPiePaintManager().setDefaultInterpolationColors(outerPaintColors);
					sunburstRenderProperties.getPiePaintManager().setDefaultInterpolationColorFractions(
					        outerPaintColorKeys);
					if (positiveImpact == false)
						colorWeight = 1.0 - colorWeight;
				}
				vertex.setAttribute("colorWeight", colorWeight);
				sunburstRenderProperties.setPaint(vertex, RenderType.FILLED, radius, size, colorWeight);
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
				renderer.setTooltipText(valueTT);
			}
		}
		return sunburst;
	}

	/**
	 * Creates the tooltip text for the given entity.
	 * 
	 * @param resultInfo
	 * @param entity
	 * @param vertex
	 * @param value
	 * @return
	 */
	private String createTooltipText(QMVResultInfo resultInfo, QualityModelElement entity, IESEDataNode vertex,
	        double value[])
	{

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
		if (value == null || vertex.getAttribute("colorWeight") == null)
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

		Double colorWeight = (Double) vertex.getAttribute("colorWeight");
		// double grade = getGrade(colorWeight);
		double grade = getGrade(resultInfo, colorWeight);

		if (entity instanceof Factor)
		{
			// valueTT += "<b>Color Weight:</b>" + round(colorWeight) + "<br>";
			valueTT += "<b>Grade:</b>" + round(grade) + "<br>";
		}
		else
			if (entity instanceof Measure)
			{
				// // test
				// Boolean positiveImpact = (Boolean) vertex.getAttribute("positiveImpact");
				// if (positiveImpact != null && positiveImpact == false)
				// colorWeight = 1.0 - colorWeight;
				// // test end
				valueTT += "<b>Value:</b>" + colorWeight + "<br>";
			}

		valueTT += "<b>Description:</b><br>";

		if (entity instanceof Factor)
			valueTT += ((Factor) entity).getDescription();
		else
			if (entity instanceof Measure)
				valueTT += ((Measure) entity).getDescription();

		if (entity instanceof Measure)
		{
			Measure measure = (Measure) entity;

			List<FindingMessage> findingMessages = resultInfo.getFindingMessages(getElement(), measure);

			if (findingMessages != null && findingMessages.size() > 0)
			{
				valueTT += "<br><b>" + findingMessages.size() + " Findings:</b>";
				valueTT += "<ol>";

				for (int i = 0; i < findingMessages.size(); i++)
				{
					if (i >= 50)
						break;
					FindingMessage message = findingMessages.get(i);
					valueTT += "<li>" + message.getMessage();
					String location = message.getLocation();
					// if (i < 50)
					{
						int lastMinus = location.lastIndexOf("-");
						if (lastMinus != -1)
						{
							location = location.substring(0, lastMinus) + "to" + location.substring(lastMinus + 1);
						}
					}
					valueTT += "<br><b>location:</b>" + location;
					valueTT += "</li>";
				}
				valueTT += "</ol>";
			}
		}
		valueTT += "</html>";

		return valueTT;
	}

	// /**
	// * Computes a scholar grade for a given weight from [0,1].
	// *
	// * @param w
	// * @return the grade
	// */
	// public static double getGrade(double w)
	// {
	// double grade = 1;
	// double t = 0.0;
	// if (w <= 0.9)
	// {
	// grade = 6.0;
	// return grade;
	// }
	// // else
	// {
	// double notenInterval[] = {
	// 0.9, 0.92, 0.94, 0.96, 0.98, 1.0
	// };
	// grade = 5.0;
	// for (int i = 0; i < notenInterval.length - 1; i++)
	// {
	// if (notenInterval[i] < w && w <= notenInterval[i + 1])
	// {
	// t = ((w - notenInterval[i]) / (notenInterval[i + 1] - notenInterval[i]));
	// break;
	// }
	// grade -= 1.0;
	// }
	// }
	// if (t < 1.0)
	// grade = grade + (1.0 - t);
	// return grade;
	// }

	/**
	 * Computes a scholar grade for a given weight from [0,1].
	 * 
	 * @param w
	 * @return the grade
	 */
	public static double getGrade(QMVResultInfo resultInfo, double w)
	{
		double schoolGradeBoundaries[] = resultInfo.getModelInfo().getSchoolGradeBoundaries();

		// System.out.println("use model school grade boundaries:");
		// for (int i = 2; i <= 6; i++)
		// System.out.println("\tgrade " + i + ":" + schoolGradeBoundaries[i]);

		double grade = 1;
		double t = 0.0;
		if (w <= schoolGradeBoundaries[6])
		{
			grade = 6.0;
			return grade;
		}
		double notenInterval[] = {
		        schoolGradeBoundaries[6], schoolGradeBoundaries[5], schoolGradeBoundaries[4], schoolGradeBoundaries[3],
		        schoolGradeBoundaries[2], 1.0
		// 0.9, 0.92, 0.94, 0.96, 0.98, 1.0
		};
		grade = 5.0;
		for (int i = 0; i < notenInterval.length - 1; i++)
		{
			if (notenInterval[i] < w && w <= notenInterval[i + 1])
			{
				t = ((w - notenInterval[i]) / (notenInterval[i + 1] - notenInterval[i]));
				break;
			}
			grade -= 1.0;
		}
		if (t < 1.0)
			grade = grade + (1.0 - t);
		return grade;
	}

	/**
	 * Rounds the given value up to 5 decimal places.
	 * 
	 * @param value
	 * @return the rounded value.
	 */
	public static double round(double value)
	{
		double eps = 1000.0;
		return Math.round(value * eps) / eps;
	}
}
