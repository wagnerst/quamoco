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
import iese.inseviz.data.IESEDataGraph;
import iese.inseviz.data.IESEDataNode;
import iese.inseviz.data.IESEDataTree;
import iese.inseviz.graph.IESETree;
import iese.inseviz.util.log.IESELogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.DirectedGraph;

import de.quamoco.qm.Effect;
import de.quamoco.qm.Factor;
import de.quamoco.qm.Impact;
import de.quamoco.qm.visualization.util.QMVFactorWeightInfo;
import de.quamoco.qm.visualization.util.QMVQualityModelInfo;
import de.quamoco.qm.visualization.util.QMVUtils;
import de.quamoco.qm.visualization.util.QMVVertexFactory;

/**
 * Copyright (c) 13.09.2011 Fraunhofer IESE. All rights reserved.<br>
 * <br>
 * COMMENT <br>
 * <br>
 * created: 13.09.2011 - 15:54:22<br>
 * author: Dr. Henning Barthel.<br>
 * project: QuamocoVis<br>
 * <br>
 * $Revision: 72 $<br>
 * $Id: QMVImpactsEdgeBundlingHierarchy.java 72 2011-09-23 12:35:43Z barthel $<br>
 * 
 * @version $Revision: 72 $
 * 
 */
public class QMVImpactsEdgeBundlingHierarchy
{
	private QMVQualityModelInfo modelInfo = null;
	private Map<IESEDataNode, Double> weightMap = null;
	private QMVVertexFactory vertexFactory = null;
	private IESEDataTree hierarchy = null;
	private IESEDataNode propertyAtProductRoot = null;
	private IESEDataNode qualityAtProductRoot = null;
	private IESEDataNode commonRoot = null;
	private boolean skipNullWeight = false;

	private QMVFactorWeightInfo weightInfo = null;

	// private List<IESEDataEdge> edgeBundlingEdges = null;
	private IESEDataGraph edgeBundlingGraph = null;

	public void createHierarchy(QMVQualityModelInfo modelInfo)
	{
		this.modelInfo = modelInfo;
		weightMap = new HashMap<IESEDataNode, Double>();
		vertexFactory = new QMVVertexFactory();
		hierarchy = new IESEDataTree();

		// collect weighting info. Factor with no weight are skipped
		weightInfo = new QMVFactorWeightInfo();
		weightInfo.collectWeightInfo(modelInfo);

		IESELogger.info("QMVImpactsEdgeBundlingHierarchy.createHierarchy(" + modelInfo.getModelNames() + ")");

		{
			IESEDataNode vertex = vertexFactory.createVertex(modelInfo.getProductRootFactor());
			hierarchy.addVertex(vertex);
			weightMap.put(vertex, 1.0);

			createPropertyAtProductHierarchy(modelInfo.getProductRootFactor());
		}
		createQualityAtProductHierarchy(modelInfo.getQualityRootFactor());
		connectHierarchy();
		QMVUtils.sortEdgesByTargetNames(hierarchy);

		// createSampleEdgebundlingGraph();
		createEdgebundlingGraph();

		IESELogger.info("DONE QMVImpactsEdgeBundlingHierarchy.createHierarchy(" + modelInfo.getModelNames() + ")");

	}

	private void createPropertyAtProductHierarchy(Factor factor)
	{
		IESELogger.incPrefix();
		IESELogger.info("createPropertyAtProductHierarchy(" + factor.getQualifiedName() + ")");

		List<Factor> influencedByList = factor.getInfluencedByDirect();// outer
		                                                               // part
		List<Factor> refinedByList = factor.getRefinedByDirect();// inner part

		// influencedBy factors dominate redefindeBy factor! Because the
		// hierarchy should be
		// bipartite in the sense that the inner part represents
		// 'Quality@Product' and the outer
		// part 'Property @Product Part', we start with inner part first.
		if (influencedByList.size() == 0 && refinedByList.size() > 0)
		{
			IESELogger.incPrefix();
			IESELogger.info("#refinedBy:" + refinedByList.size());
			IESELogger.info("refinedBy:" + QMVUtils.toString(refinedByList));

			IESEDataNode factorVertex = vertexFactory.getVertex(factor);

			for (Factor childFactor : refinedByList)
			{
				Double weight = weightInfo.getWeight(factor, childFactor);// 1.0;//
				                                                          // getWeight(factor,
				                                                          // childFactor);
				IESELogger.info("factor " + childFactor.getQualifiedName() + ":weight=" + weight);

				if (weight == null || weight == 0.0)
				{
					IESELogger.incPrefix();
					IESELogger.info("skip factor because weight=0.0");
					IESELogger.decPrefix();
					weight = 1.0;
				}

				IESEDataNode childFactorVertex = vertexFactory.createVertex(childFactor);
				hierarchy.addVertex(childFactorVertex);
				IESEDataEdge edge = hierarchy.addEdge(factorVertex, childFactorVertex);
				weightMap.put(childFactorVertex, weight);

				// check the impact
				Boolean positiveImpact = hasPositiveImpact(factor, childFactor);
				childFactorVertex.setAttribute("positiveImpact", positiveImpact);

				IESELogger.incPrefix();
				IESELogger.info("created vertex " + childFactorVertex);
				IESELogger.info("created edge " + edge);
				IESELogger.decPrefix();
			}
			IESELogger.decPrefix();

			IESELogger.info("go down hierarchy for " + factor.getQualifiedName());
			for (Factor childFactor : refinedByList)
			{
				IESEDataNode childFactorVertex = vertexFactory.getVertex(childFactor);
				if (childFactorVertex == null)// has been skipped because
				                              // weight=0.0
					continue;
				double weight = weightMap.get(childFactorVertex);
				if (weight > 0.0)
					createPropertyAtProductHierarchy(childFactor);
			}
		}
		IESELogger.info("DONE createPropertyAtProductHierarchy(" + factor.getQualifiedName() + ")");
		IESELogger.decPrefix();
	}

	private void createQualityAtProductHierarchy(Factor factor)
	{
		IESELogger.incPrefix();
		IESELogger.info("createQualityAtProductHierarchy('" + factor.getQualifiedName() + "')");

		List<Factor> influencedByList = factor.getInfluencedByDirect();
		List<Factor> redefinedByList = factor.getRefinedByDirect();

		IESEDataNode source = vertexFactory.getVertex(factor);
		if (source == null)
		{
			source = vertexFactory.createVertex(factor);
			hierarchy.addVertex(source);
			weightMap.put(source, 1.0);
		}

		if (influencedByList.size() != 0)
		{
			// influenced before refined. Here: nothing to do
		}
		else
		{
			IESELogger.incPrefix();
			IESELogger.info("#redefinedBy:" + redefinedByList.size());
			for (Factor childFactor : redefinedByList)
			{
				Double weight = weightInfo.getWeight(factor, childFactor);
				if (weight == null || weight == 0.0)
				{
					continue;
				}

				IESEDataNode target = vertexFactory.createVertex(childFactor);
				hierarchy.addVertex(target);
				hierarchy.addEdge(source, target);

				weightMap.put(target, weight);

				createQualityAtProductHierarchy(childFactor);

			}
			IESELogger.decPrefix();
		}
		IESELogger.info("DONE createQualityAtProductHierarchy('" + factor.getQualifiedName() + "')");
		IESELogger.decPrefix();

	}

	private void connectHierarchy()
	{
		propertyAtProductRoot = vertexFactory.getVertex(modelInfo.getProductRootFactor());
		qualityAtProductRoot = vertexFactory.getVertex(modelInfo.getQualityRootFactor());

		commonRoot = new IESEDataNode("CommonRoot");
		hierarchy.setRootVertex(commonRoot);
		weightMap.put(commonRoot, 1.0);

		hierarchy.addVertex(commonRoot);
		hierarchy.addEdge(commonRoot, propertyAtProductRoot);
		hierarchy.addEdge(commonRoot, qualityAtProductRoot);

		weightMap.put(qualityAtProductRoot, 0.25);
		weightMap.put(propertyAtProductRoot, 0.75);
	}

	private void createEdgebundlingGraph()
	{
		edgeBundlingGraph = new IESEDataGraph();

		DirectedGraph<IESEDataNode, IESEDataEdge> ppTree = IESETree.getSubtree(hierarchy, propertyAtProductRoot);
		DirectedGraph<IESEDataNode, IESEDataEdge> qpTree = IESETree.getSubtree(hierarchy, qualityAtProductRoot);

		List<IESEDataNode> ppLeafs = new ArrayList<IESEDataNode>();
		List<IESEDataNode> qpLeafs = new ArrayList<IESEDataNode>();

		for (IESEDataNode vertex : ppTree.vertexSet())
		{
			if (ppTree.outDegreeOf(vertex) == 0)
				ppLeafs.add(vertex);
		}
		for (IESEDataNode vertex : qpTree.vertexSet())
		{
			if (qpTree.outDegreeOf(vertex) == 0)
				qpLeafs.add(vertex);
		}

		for (IESEDataNode sourceVertex : qpLeafs)
		{
			IESELogger.info(sourceVertex);
			IESELogger.incPrefix();

			Factor sourceFactor = (Factor) vertexFactory.getEntity(sourceVertex);

			List<Factor> influencedByList = sourceFactor.getInfluencedByDirect();

			for (Factor targetFactor : influencedByList)
			{
				Double weight = weightInfo.getWeight(sourceFactor, targetFactor);// weightMap.get(targetVertex);
				// IESELogger.info(weight + ":" +
				// targetFactor.getQualifiedName());
				// if (weight != null && weight > 0.0)
				{
					IESEDataNode targetVertex = vertexFactory.getVertex(targetFactor);

					if (targetVertex != null)
					{
						edgeBundlingGraph.addVertex(sourceVertex);
						edgeBundlingGraph.addVertex(targetVertex);
						IESEDataEdge edge = edgeBundlingGraph.addEdge(sourceVertex, targetVertex);

						// System.out.println("added edgeBundling edge:" +
						// edge);
					}
				}
			}

			IESELogger.decPrefix();
		}
		// edgeBundlingGraph.printGraph("", "EdgeBundlingGraph:");
		System.out.println("#bundled edges:" + edgeBundlingGraph.edgeSet().size());
	}

	/****************************************************************/
	/********************** UTILS ***********************************/
	/****************************************************************/

	/**
	 * To be revisited because result may not be calculated. If no impacts are found null is returned. If exact one
	 * impact is found, true is returned if it is a positive impact. If there are more than one impacts, and all have
	 * the same value, true is returned if that value is positive. If the impact differ in their values, null is
	 * returned, and the corresponding vertex in the tree get the attribute 'differingImpacts' which may be used during
	 * rendering with e.g. a special color coding.
	 * 
	 * @param source
	 * @param target
	 * @return null or true if positive impact
	 */
	private Boolean hasPositiveImpact(Factor source, Factor target)
	{

		List<Impact> impactList = target.getInfluences();
		if (impactList == null || impactList.size() == 0)
			return null;
		if (impactList.size() == 1)
		{
			if (impactList.get(0).getEffect().getValue() == Effect.NEGATIVE_VALUE)
				return false;
			else
				return true;
		}
		for (int i = 0; i < impactList.size() - 1; i++)
		{
			Impact i1 = impactList.get(i);
			Impact i2 = impactList.get(i + 1);

			if (i1.getEffect().getValue() != i2.getEffect().getValue())
			{
				IESEDataNode targetVertex = vertexFactory.getVertex(target);
				if (targetVertex != null)
				{
					targetVertex.setAttribute("differingImpacts", true);
				}
				return null;
			}
		}
		if (impactList.get(0).getEffect().getValue() == Effect.NEGATIVE_VALUE)
			return false;
		else
			return true;
	}

	/****************************************************************/
	/********************** SETTERS AND GETTERS *********************/
	/****************************************************************/
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
	 * Returns the weightMap.
	 * 
	 * @return the weightMap
	 */
	public Map<IESEDataNode, Double> getWeightMap()
	{
		return weightMap;
	}

	public Double getWeight(IESEDataNode vertex)
	{
		return weightMap.get(vertex);
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
	 * Returns the propertyAtProductRoot.
	 * 
	 * @return the propertyAtProductRoot
	 */
	public IESEDataNode getPropertyAtProductRoot()
	{
		return propertyAtProductRoot;
	}

	/**
	 * Returns the qualityAtProductRoot.
	 * 
	 * @return the qualityAtProductRoot
	 */
	public IESEDataNode getQualityAtProductRoot()
	{
		return qualityAtProductRoot;
	}

	/**
	 * Returns the commonRoot.
	 * 
	 * @return the commonRoot
	 */
	public IESEDataNode getCommonRoot()
	{
		return commonRoot;
	}

	/**
	 * Returns the edgeBundlingEdges.
	 * 
	 * @return the edgeBundlingEdges
	 */
	public IESEDataGraph getEdgeBundlingGraph()
	{
		return edgeBundlingGraph;
	}
}
