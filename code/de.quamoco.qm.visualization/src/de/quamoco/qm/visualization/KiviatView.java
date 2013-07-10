/*-------------------------------------------------------------------------+
|                                                                          |
| Fraunhofer-Institut fuer Experimentelles Software Engineering (IESE)     |
|                                                                          |
|                                                                          |
|   NOT TO BE PUBLISHED UNDER QUAMOCO LICENSE                              |
|                                                                          |
+-------------------------------------------------------------------------*/

package de.quamoco.qm.visualization;

import iese.inseviz.util.log.IESELogger;
import iese.inseviz.util.log.IESELogger.IESELogLevel;
import iese.inseviz.visualization.IESEView;
import iese.inseviz.visualization.interaction.IESETooltipHandler;
import iese.inseviz.visualization.interaction.IESETooltipListener;
import iese.inseviz.visualization.renderer.IESERenderer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;

import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.visualization.kiviat.QMVKiviatFactory;
import de.quamoco.qm.visualization.util.QMVQualityModelInfo;

/**
 * TODO: Add documentation.
 * 
 * @author herrmama
 * @author $Author: HenningBarthel $
 * @version $Rev: 4947 $
 * @levd.rating RED Rev:
 */
public class KiviatView extends IESEVisualizationViewBase<List<QualityModelResult>>
{

	/** The identifier of the view as in the plugin.xml. */
	public static final String ID = KiviatView.class.getName();
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
		toolBar.add(action);
	}

	/** {@inheritDoc} */
	@Override
	protected void inputChanged(List<QualityModelResult> element, JPanel panel)
	{
		if (getElement() == null)
		{
			System.err.println("KiviatView: no results specified!");
			System.err.flush();
			return;
		}

		IESELogger.setLevel(IESELogLevel.OFF);

		QMVQualityModelInfo modelInfo = new QMVQualityModelInfo();

		for (QualityModelResult result : getElement())
		{
			modelInfo.addResult(result);

		}
		// modelInfo.print("", "ModelInfo:");

		Color colors[] = {
		        Color.blue, Color.green, Color.orange
		};

		QMVKiviatFactory kiviat = new QMVKiviatFactory();
		view = kiviat.create(modelInfo, colors);
		panel.add(view);

		{
			// setup view text info to show the name of all result sets in the view
			for (QualityModelResult result : element)
			{
				Font textInfoFont = new Font("Arial", Font.BOLD, 12);
				view.setDrawTextInfo(true);
				view.addTextInfo(result.getSystem().trim(), textInfoFont, Color.black,
				        IESEView.ViewTextInfoPosition.BOTTOM_LEFT);
			}

		}

		tooltipHandler = new IESETooltipHandler(view);
		tooltipHandler.addTooltipListener(new IESETooltipListener()
		{

			@Override
			public void showTooltip(BufferedImage arg0, String arg1)
			{
				KiviatView.this.showTooltip(arg1);
			}

			@Override
			public void showTooltip(IESERenderer arg0, String arg1)
			{
				KiviatView.this.showTooltip(arg1);
			}

			@Override
			public void clear()
			{
				KiviatView.this.showTooltip("");
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
}
