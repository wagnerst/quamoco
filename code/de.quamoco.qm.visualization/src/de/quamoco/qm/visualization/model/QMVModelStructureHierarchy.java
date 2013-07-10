/*-------------------------------------------------------------------------+
|                                                                          |
| Fraunhofer-Institut fuer Experimentelles Software Engineering (IESE)     |
|                                                                          |
|                                                                          |
|   NOT TO BE PUBLISHED UNDER QUAMOCO LICENSE                              |
|                                                                          |
+-------------------------------------------------------------------------*/

package de.quamoco.qm.visualization.model;

import iese.inseviz.data.IESEDataEdge;
import iese.inseviz.data.IESEDataNode;
import iese.inseviz.data.IESEDataTree;
import iese.inseviz.graph.util.IESETreeLeveling;
import iese.inseviz.util.log.IESELogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.quamoco.qm.Effect;
import de.quamoco.qm.Factor;
import de.quamoco.qm.Impact;
import de.quamoco.qm.Measure;
import de.quamoco.qm.visualization.util.QMVFactorWeightInfo;
import de.quamoco.qm.visualization.util.QMVQualityModelInfo;
import de.quamoco.qm.visualization.util.QMVUtils;
import de.quamoco.qm.visualization.util.QMVVertexFactory;
import de.quamoco.qm.util.QmModelUtils;

/**
 * Copyright (c) 16.09.2011 Fraunhofer IESE. All rights reserved.<br>
 * <br>
 * COMMENT <br>
 * <br>
 * created: 16.09.2011 - 11:57:19<br>
 * author: Dr. Henning Barthel.<br>
 * project: QuamocoVis<br>
 * <br>
 * $Revision: 72 $<br>
 * $Id: QMVModelStructureHierarchy.java 72 2011-09-23 12:35:43Z barthel $<br>
 * 
 * @version $Revision: 72 $
 * 
 */
public class QMVModelStructureHierarchy
{
	private QMVQualityModelInfo modelInfo = null;
	private Map<IESEDataNode, Double> weightMap = null;
	private QMVVertexFactory vertexFactory = null;
	private IESEDataTree hierarchy = null;
	private boolean skipNullWeight = false;

	private QMVFactorWeightInfo weightInfo = null;

	public void createHierarchy(QMVQualityModelInfo modelInfo)
	{
		IESELogger.info("QMVModelStructureHierarchy.createHierarchy(" + modelInfo.getModelNames() + ")");

		this.modelInfo = modelInfo;
		weightMap = new HashMap<IESEDataNode, Double>();
		vertexFactory = new QMVVertexFactory();
		hierarchy = new IESEDataTree();

		// collect weighting info. Factor with no weight are skipped
		weightInfo = new QMVFactorWeightInfo();
		weightInfo.collectWeightInfo(modelInfo);

		Factor rootFactor = modelInfo.getQualityRootFactor();
		IESEDataNode vertex = vertexFactory.createVertex(rootFactor);
		hierarchy.addVertex(vertex);
		hierarchy.setRootVertex(vertex);

		weightMap.put(vertex, 1.0);

		createInnerHierarchy(rootFactor);

		updateInnerHierarchyDistanceInfo();

		// create outer hierarchy starting at the leafs of the inner hierarchy
		List<IESEDataNode> leafVertices = new ArrayList<IESEDataNode>();
		for (IESEDataNode leafVertex : hierarchy.vertexSet())
		{
			if (hierarchy.outDegreeOf(leafVertex) > 0)
				continue;
			leafVertices.add(leafVertex);

		}
		for (IESEDataNode leafVertex : leafVertices)
		{
			Factor factor = (Factor) vertexFactory.getEntity(leafVertex);
			createOuterHierarchy(factor);
		}

		QMVUtils.sortEdgesByTargetNames(hierarchy);

		IESELogger.info("DONE QMVModelStructureHierarchy.createHierarchy(" + modelInfo.getModelNames() + ")");
	}

	private void createInnerHierarchy(Factor factor)
	{
		IESELogger.incPrefix();
		IESELogger.info("createInnerHierarchy(" + factor.getQualifiedName() + "):"
		        + QmModelUtils.getDeepestModelInRequiresRelation(factor).getQualifiedName());

		IESEDataNode parentVertex = vertexFactory.getVertex(factor);

		List<Factor> refinedByList = factor.getRefinedByDirect();// inner part
		List<Factor> factorList = refinedByList;

		if (factor.getInfluencedByDirect().size() > 0)
		{
			parentVertex.setAttribute("IncreaseRingDistance", true);
			IESELogger.info("DONE createInnerHierarchy(" + factor.getQualifiedName() + "):"
			        + QmModelUtils.getDeepestModelInRequiresRelation(factor).getQualifiedName());
			IESELogger.decPrefix();
			return;
		}
		factorList = QMVUtils.sortListByQualifiedName(factorList);

		// create vertices for the child factors and add edges connecting the
		// factor with its child factors.

		for (Factor childFactor : factorList)
		{
			Double weight = weightInfo.getWeight(factor, childFactor);
			if (weight == null || weight == 0.0)
			{
				continue;
			}
			IESELogger.incPrefix();
			IESELogger.info("weight=" + weight + " for " + childFactor.getQualifiedName());
			IESELogger.decPrefix();
			// create vertex
			IESEDataNode vertex = vertexFactory.createVertex(childFactor);
			hierarchy.addVertex(vertex);
			hierarchy.addEdge(parentVertex, vertex);

			weightMap.put(vertex, weight);

			// go down hierarchy
			createInnerHierarchy(childFactor);
		}
		IESELogger.info("DONE createInnerHierarchy(" + factor.getQualifiedName() + "):"
		        + QmModelUtils.getDeepestModelInRequiresRelation(factor).getQualifiedName());
		IESELogger.decPrefix();
	}

	private void updateInnerHierarchyDistanceInfo()
	{
		// hierarchy.printTree("", "Inner Quality Hierarchy:");
		// Map<Integer, List<IESEDataNode>> leveling =
		// hierarchy.getTreeLevelMap();
		IESETreeLeveling<IESEDataNode, IESEDataEdge> leveling = new IESETreeLeveling<IESEDataNode, IESEDataEdge>();
		leveling.computeLeveling(hierarchy, hierarchy.getRootVertex());
		// leveling.print("", "Inner Quality Hierarchy Leveling:");

		int lastLevel = leveling.getLastLevel();
		double mult = 0.0;
		for (IESEDataNode vertex : leveling.getLevelVertices(lastLevel))
			vertex.setAttribute("ringDistanceMult", mult);

		for (int level = lastLevel - 1; level >= 1; level--)
		{
			mult += 1.0;

			for (IESEDataNode vertex : leveling.getLevelVertices(level))
			{
				if (hierarchy.outDegreeOf(vertex) == 0)
					vertex.setAttribute("ringDistanceMult", mult);
			}
		}
	}

	private void createOuterHierarchy(Factor factor)
	{
		IESELogger.incPrefix();
		IESELogger.info("createOuterHierarchy(" + factor.getQualifiedName() + "):"
		        + QmModelUtils.getDeepestModelInRequiresRelation(factor).getQualifiedName());

		IESEDataNode parentVertex = vertexFactory.getVertex(factor);

		List<Factor> influencedByList = factor.getInfluencedByDirect();// outer
		                                                               // part
		List<Factor> refinedByList = factor.getRefinedByDirect();// inner part
		List<Factor> factorList = refinedByList;

		if (influencedByList.size() > 0)
		{
			factorList = influencedByList;
		}

		if (factorList.size() == 0)
		{
			addMeasuredBy(factor);
		}
		else
		{
			factorList = QMVUtils.sortListByQualifiedName(factorList);

			// create vertices for the child factors and add edges connecting
			// the factor with its child factors.

			for (Factor childFactor : factorList)
			{
				Double weight = weightInfo.getWeight(factor, childFactor);
				if (weight == null || weight == 0.0)
				{
					continue;
				}
				IESELogger.incPrefix();
				IESELogger.info("weight=" + weight + " for " + childFactor.getQualifiedName());
				IESELogger.decPrefix();
				// create vertex
				IESEDataNode vertex = vertexFactory.createVertex(childFactor);
				hierarchy.addVertex(vertex);
				hierarchy.addEdge(parentVertex, vertex);

				weightMap.put(vertex, weight);

				Boolean positiveImpact = hasPositiveImpact(childFactor, factor);
				vertex.setAttribute("positiveImpact", positiveImpact);

				if (positiveImpact == null)
					IESELogger.info("cannot find impacts for " + childFactor.getQualifiedName());
				else
				{
					if (positiveImpact)
						IESELogger.info("positive impact on " + factor.getQualifiedName());
					else
						IESELogger.info("negative impact on " + factor.getQualifiedName());
				}

				// go down hierarchy
				createOuterHierarchy(childFactor);
			}
		}
		// consistency: compute total weight
		if (factorList.size() > 0)
		{
			double totalWeight = 0.0;
			for (Factor childFactor : factorList)
			{
				Double weight = weightInfo.getWeight(factor, childFactor);
				if (weight == null || weight == 0.0)
				{
					continue;
				}
				totalWeight += weightMap.get(vertexFactory.getVertex(childFactor));
			}
			IESELogger.info("totalWeight(" + factor.getQualifiedName() + ")=" + totalWeight);
		}

		IESELogger.info("DONE createOuterHierarchy(" + factor.getQualifiedName() + ")");
		IESELogger.decPrefix();
	}

	private void addMeasuredBy(Factor factor)
	{
		IESELogger.incPrefix();
		IESELogger.info("addMeasuredBy(" + factor.getQualifiedName() + "):"
		        + QmModelUtils.getDeepestModelInRequiresRelation(factor).getQualifiedName());

		List<Measure> measuredByList = factor.getMeasuredByDirect();

		IESEDataNode parentVertex = vertexFactory.getVertex(factor);

		for (Measure measure : measuredByList)
		{
			Double weight = weightInfo.getWeight(factor, measure);
			if (weight == null || weight == 0.0)
			{
				continue;
			}
			IESELogger.incPrefix();
			IESELogger.info("measure weight=" + weight + " for " + measure.getQualifiedName());
			IESELogger.decPrefix();

			// create vertex
			IESEDataNode vertex = vertexFactory.createVertex(measure);
			hierarchy.addVertex(vertex);
			hierarchy.addEdge(parentVertex, vertex);

			weightMap.put(vertex, weight);
			{
				// test
				Boolean positiveImpact = (Boolean) parentVertex.getAttribute("positiveImpact");
				if (positiveImpact != null)
					vertex.setAttribute("positiveImpact", positiveImpact);
				// test end
			}
		}

		IESELogger.info("DONE createHierarchy(" + factor.getQualifiedName() + ")");
		IESELogger.decPrefix();
	}

	private Boolean hasPositiveImpact(Factor source, Factor target)
	{
		for (Impact impact : source.getInfluences())
		{
			if (impact.getTarget() == target)
			{
				if (impact.getEffect().getValue() == Effect.NEGATIVE_VALUE)
					return false;
				else
					return true;
			}
		}
		return null;
	}

	/****************************************************************/
	/********************** SETTERS AND GETTERS *********************/
	/****************************************************************/

	public double getWeight(IESEDataNode vertex)
	{
		return weightMap.get(vertex);
	}

	/**
	 * Returns the skipNullWeight.
	 * 
	 * @return the skipNullWeight
	 */
	public boolean isSkipNullWeight()
	{
		return skipNullWeight;
	}

	/**
	 * Set the skipNullWeight value to skipNullWeight.
	 * 
	 * @param skipNullWeight the skipNullWeight to set
	 */
	public void setSkipNullWeight(boolean skipNullWeight)
	{
		this.skipNullWeight = skipNullWeight;
	}

	/**
	 * Returns the modelInfo.
	 * 
	 * @return the modelInfo
	 */
	public QMVQualityModelInfo getModelInfo()
	{
		return modelInfo;
	}

	/**
	 * Returns the vertexFactory.
	 * 
	 * @return the vertexFactory
	 */
	public QMVVertexFactory getVertexFactory()
	{
		return vertexFactory;
	}

	/**
	 * Returns the hierarchy.
	 * 
	 * @return the hierarchy
	 */
	public IESEDataTree getHierarchy()
	{
		return hierarchy;
	}

	/**
	 * Returns the weightInfo.
	 * 
	 * @return the weightInfo
	 */
	public QMVFactorWeightInfo getWeightInfo()
	{
		return weightInfo;
	}
}
