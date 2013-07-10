/*-------------------------------------------------------------------------+
|                                                                          |
| Fraunhofer-Institut fuer Experimentelles Software Engineering (IESE)     |
|                                                                          |
|                                                                          |
|   NOT TO BE PUBLISHED UNDER QUAMOCO LICENSE                              |
|                                                                          |
+-------------------------------------------------------------------------*/

package de.quamoco.qm.visualization.kiviat;

import iese.inseviz.util.log.IESELogger;
import iese.inseviz.util.ui.image.IESECaptureUtil;
import iese.inseviz.visualization.IESEView;
import iese.inseviz.visualization.interaction.IESECaptureRequestHandler;
import iese.inseviz.visualization.interaction.IESEDraggingHandler;
import iese.inseviz.visualization.interaction.IESEHighlightHandler;
import iese.inseviz.visualization.interaction.IESEPickingHandler;
import iese.inseviz.visualization.renderer.kiviat.IESEKiviatRenderer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.quamoco.qm.Factor;
import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.visualization.util.QMVQualityModelInfo;
import de.quamoco.qm.visualization.util.QMVResultInfo;
import de.quamoco.qm.visualization.util.QMVUtils;

/**
 * Copyright (c) 06.09.2011 Fraunhofer IESE. All rights reserved.<br>
 * <br>
 * COMMENT <br>
 * <br>
 * created: 06.09.2011 - 09:58:05<br>
 * author: Dr. Henning Barthel.<br>
 * project: QuamocoVis<br>
 * <br>
 * $Revision: 72 $<br>
 * $Id: QMVKiviatFactory.java 72 2011-09-23 12:35:43Z barthel $<br>
 * 
 * @version $Revision: 72 $
 * 
 */
public class QMVKiviatFactory // implements ActionListener
{
	private QMVQualityModelInfo modelInfo = null;
	private QMVResultInfo resultInfo = null;
	private IESEKiviatRenderer kiviat = null;
	private double epsilon = 0.00001;

	public IESEView create(QMVQualityModelInfo info, Color colors[]) {
		this.modelInfo = info;

		// IESELogger.setLevel(IESELogLevel.INFO);

		resultInfo = new QMVResultInfo();
		resultInfo.collectResultInfo(modelInfo);

		kiviat = new IESEKiviatRenderer("Quamoco Quality Results");

		boolean skipEmptyCategories = true;

		// get the quality factors as the categories of the kiviat
		List<Factor> categories = this.getQualityFactors();

		// sort the categories
		List<Factor> sortedCategoryList = new ArrayList<Factor>();
		sortedCategoryList.addAll(categories);
		sortedCategoryList = QMVUtils
				.sortListByQualifiedName(sortedCategoryList);

		IESELogger.info("categories:" + QMVUtils.toString(sortedCategoryList));

		// remove empty categories with no relevant value
		if (skipEmptyCategories) {
			List<Factor> skippedFactors = new ArrayList<Factor>();
			for (int i = 0; i < sortedCategoryList.size(); i++) {
				Factor factor = sortedCategoryList.get(i);
				boolean skipFactor = true;
				for (QualityModelResult qmResult : modelInfo.getResultList()) {
					double value[] = resultInfo.getValue(qmResult, factor);
					if (value[1] > 0.0) {
						skipFactor = false;
						break;
					}
				}
				if (skipFactor)
					skippedFactors.add(factor);
			}
			IESELogger.incPrefix();
			IESELogger
					.info("skip factors " + QMVUtils.toString(skippedFactors));
			IESELogger.decPrefix();
			sortedCategoryList.removeAll(skippedFactors);
		}
		// create the graduation labels for each category of the kiviat
		List<Number> graduations = createGraduation(sortedCategoryList);
		IESELogger.incPrefix();
		IESELogger.info("graduations:" + graduations);
		IESELogger.decPrefix();

		// create categories
		for (int i = 0; i < sortedCategoryList.size(); i++) {
			Factor factor = sortedCategoryList.get(i);
			kiviat.addCategory(factor.getQualifiedName(), graduations);
		}

		for (int i = 0; i < modelInfo.getResultList().size(); i++) {
			QualityModelResult qmResult = modelInfo.getResultList().get(i);

			// create category values
			List<Number[]> productValues = new ArrayList<Number[]>();
			for (Factor factor : sortedCategoryList) {
				double value[] = resultInfo.getValue(qmResult, factor);

				double minValue = value[0];
				double maxValue = value[1];
				if (Math.abs(maxValue - minValue) < epsilon)
					productValues.add(new Number[] { minValue });
				else
					productValues.add(new Number[] { minValue, maxValue });
			}
			kiviat.setProductValues(qmResult.getSystem(), productValues);
			kiviat.setProductColor(qmResult.getSystem(), colors[i]);
		}

		// customize kiviat rendering
		kiviat.setShowKiviatBorder(false);
		// kiviat.setShowGraduationCircles(false);
		kiviat.setCategoryLength(100.0);
		kiviat.setCategorySize(1.5);
		kiviat.setStartAngle(45.0);
		kiviat.setAngleExtent(360.0);

		kiviat.createKiviat();

		kiviat.clearRenderTypes();
		kiviat.setMouseOverEnabled(false);

		final IESEView view = new IESEView();
		view.setRenderHighligthedLast(true);

		view.addGroupRenderer(kiviat);

		// interaction
		IESEHighlightHandler highlightHandler = new IESEHighlightHandler(view);
		IESEDraggingHandler draggingHandler = new IESEDraggingHandler(view);
		IESEPickingHandler pickingHandler = new IESEPickingHandler(view);
		IESECaptureRequestHandler captureReqHandler = new IESECaptureRequestHandler(
				view, new IESECaptureUtil());

		view.viewAll();

		return view;
	}

	private List<Number> createGraduation(List<Factor> categories) {
		// compute the min/max values for the categories
		double minValue = Double.POSITIVE_INFINITY;
		double maxValue = Double.NEGATIVE_INFINITY;

		//
		for (QualityModelResult qmResult : modelInfo.getResultList()) {
			for (int i = 0; i < categories.size(); i++) {
				Factor factor = categories.get(i);
				double value[] = resultInfo.getValue(qmResult, factor);

				minValue = Math.min(minValue, value[0]);
				maxValue = Math.max(maxValue, value[1]);
			}
		}
		if (minValue > 0.9)
			minValue = 0.9;

		if (minValue < 0.9) {
			minValue = Math.floor(minValue * 100.0) / 100.0;
		}

		if (maxValue < 1.0)
			maxValue = 1.0;
		if (maxValue > 1.0)
			maxValue = 1.0;

		IESELogger.incPrefix();
		IESELogger.info("createGraduations:");
		IESELogger.incPrefix();
		IESELogger.info("minValue:" + minValue);
		IESELogger.info("maxValue:" + maxValue);
		IESELogger.decPrefix();
		IESELogger.decPrefix();

		double dx = (maxValue - minValue) / 5.0;

		// create graduations
		List<Number> graduations = new ArrayList<Number>();
		graduations.add(minValue);
		for (int j = 1; j <= 5; j++)
			graduations.add(Math.round(100.0 * (minValue + j * dx)) / 100.0);

		return graduations;
	}

	private List<Factor> getQualityFactors() {
		List<Factor> list = new ArrayList<Factor>();

		Factor qualityRootFactor = modelInfo.getQualityRootFactor();
		for (Factor qualityFactor : qualityRootFactor.getRefinedByDirect()) {
			list.add(qualityFactor);
		}
		list = QMVUtils.sortListByQualifiedName(list);
		return list;
	}
}
