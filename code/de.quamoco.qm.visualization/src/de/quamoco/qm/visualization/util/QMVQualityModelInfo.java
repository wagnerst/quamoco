/*-------------------------------------------------------------------------+
|                                                                          |
| Fraunhofer-Institut fuer Experimentelles Software Engineering (IESE)     |
|                                                                          |
|                                                                          |
|   NOT TO BE PUBLISHED UNDER QUAMOCO LICENSE                              |
|                                                                          |
+-------------------------------------------------------------------------*/

package de.quamoco.qm.visualization.util;

import iese.inseviz.util.log.IESELogger;

import java.util.ArrayList;
import java.util.List;

import de.quamoco.qm.EvaluationResult;
import de.quamoco.qm.Factor;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.util.QmModelUtils;

/**
 * Copyright (c) 15.08.2011 Fraunhofer IESE. All rights reserved.<br>
 * <br>
 * A cache for Quamoco data to be used by the visualization. <br>
 * <br>
 * created: 15.08.2011 - 11:16:57<br>
 * author: Dr. Henning Barthel.<br>
 * project: QuamocoVis<br>
 * <br>
 */
public class QMVQualityModelInfo
{
	private List<QualityModel> modelList = new ArrayList<QualityModel>();
	private List<QualityModelResult> resultList = new ArrayList<QualityModelResult>();
	private List<Factor> rootFactors = null;

	/**
	 * 
	 */
	public QMVQualityModelInfo()
	{
		super();
	}

	/**
	 * Adds the given result to the cache.
	 * 
	 * @param result
	 */
	public void addResult(QualityModelResult result)
	{
		if (resultList.contains(result) == false)
		{
			resultList.add(result);

			for (EvaluationResult evaluationResult : result.getEvaluationResults())
			{

				QualityModel model = evaluationResult.getResultsFrom().getQualityModel();
				if (modelList.contains(model) == false)
				{
					modelList.add(model);
				}
			}
		}
	}

	/**
	 * Prints out the cached information.
	 * 
	 * @param prefix Prefix to use for each output line.
	 * @param comment Initial comment.
	 */
	public void print(String prefix, String comment)
	{
		IESELogger.info(prefix + comment + "(Base:" + modelList.get(0).getQualifiedName() + ")");

		IESELogger.incPrefix();

		for (QualityModel qm : modelList)
			IESELogger.info(prefix + "Model:" + qm.getQualifiedName());

		for (QualityModelResult qmr : resultList)
			IESELogger.info(prefix + "Result:" + qmr.getSystem());

		IESELogger.decPrefix();
	}

	/**
	 * Returns a list of qualified model names.
	 * 
	 * @return
	 */
	public List<String> getModelNames()
	{
		List<String> modelNames = new ArrayList<String>();
		for (QualityModel model : modelList)
			modelNames.add(model.getQualifiedName());
		return modelNames;
	}

	/**
	 * Returns the modelList.
	 * 
	 * @return the modelList
	 */
	public List<QualityModel> getModelList()
	{
		return modelList;
	}

	/**
	 * Returns the resultList.
	 * 
	 * @return the resultList
	 */
	public List<QualityModelResult> getResultList()
	{
		return resultList;
	}

	/**
	 * Returns the rootFactors.
	 * 
	 * @return the rootFactors
	 */
	public List<Factor> getRootFactors()
	{
		if (rootFactors == null)
		{
			rootFactors = new ArrayList<Factor>();
			detectRootFactors();
		}
		return rootFactors;
	}

	/**
	 * Return the root factor (Quality@Product) for the quality hierarchy.
	 * 
	 * @return
	 */
	public Factor getQualityRootFactor()
	{
		if (rootFactors == null)
		{
			rootFactors = new ArrayList<Factor>();
			detectRootFactors();
		}
		for (Factor factor : rootFactors)
			if (factor.getName().equals("Quality"))
				return factor;
		return null;
	}

	/**
	 * Returns the root factor (Property@Product) for the product factor hierarchy.
	 * 
	 * @return
	 */
	public Factor getProductRootFactor()
	{
		if (rootFactors == null)
		{
			rootFactors = new ArrayList<Factor>();
			detectRootFactors();
		}
		for (Factor factor : rootFactors)
			if (factor.getName().equals("Property"))
			{
				return factor.getRefinedByDirect().get(0);
				// return factor;
			}
		return null;
	}

	/**
	 * Utility method to detect the root factors.
	 */
	private void detectRootFactors()
	{
		for (QualityModel model : modelList)
		{
			for (Factor factor : model.getFactors())
			{
				List<Factor> parentList = factor.getRefinesDirect();
				if (parentList.size() == 0)
					rootFactors.add(factor);
			}
		}
	}

	/**
	 * Returns the school grade boundaries of the deepest model for the quality root factor.
	 * 
	 * @return double array of length 7 (0=null,1=null,2,3,4,5,6)
	 */
	public double[] getSchoolGradeBoundaries()
	{
		QualityModel rootModel = QmModelUtils.getDeepestModelInRequiresRelation(getQualityRootFactor());
		double schoolGradeBounds[] = new double[7];
		schoolGradeBounds[2] = rootModel.getSchoolGradeBoundary2();
		schoolGradeBounds[3] = rootModel.getSchoolGradeBoundary3();
		schoolGradeBounds[4] = rootModel.getSchoolGradeBoundary4();
		schoolGradeBounds[5] = rootModel.getSchoolGradeBoundary5();
		schoolGradeBounds[6] = rootModel.getSchoolGradeBoundary6();

		return schoolGradeBounds;
	}
}
