/*-------------------------------------------------------------------------+
|                                                                          |
| Fraunhofer-Institut fuer Experimentelles Software Engineering (IESE)     |
|                                                                          |
|                                                                          |
|   NOT TO BE PUBLISHED UNDER QUAMOCO LICENSE                              |
|                                                                          |
+-------------------------------------------------------------------------*/

package de.quamoco.qm.visualization.util;

import iese.inseviz.data.IESEDataNode;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.Impact;
import de.quamoco.qm.Measure;
import de.quamoco.qm.QualityModelElement;

/**
 * Copyright (c) 01.08.2011 Fraunhofer IESE. All rights reserved.<br>
 * <br>
 * Creates vertices for different types of quamoco elements. The element provided is used as the data object of each
 * created vertex, i.e. with vertex.getData() this element can be retrieved (as java.lang.Object).<br>
 * <br>
 * created: 01.08.2011 - 14:45:43<br>
 * author: Dr. Henning Barthel.<br>
 * project: QuamocoVis<br>
 * <br>
 * $Revision: 72 $<br>
 * $Id: QMVVertexFactory.java 72 2011-09-23 12:35:43Z barthel $<br>
 * 
 * @version $Revision: 72 $
 * 
 */
public class QMVVertexFactory
{
	private Map<String, IESEDataNode> elementVertexMap = new HashMap<String, IESEDataNode>();

	public IESEDataNode createVertex(Factor entity)
	{
		IESEDataNode vertex = new IESEDataNode(entity);
		vertex.setToString(entity.getQualifiedName());
		elementVertexMap.put(entity.getQualifiedName(), vertex);
		return vertex;
	}

	public IESEDataNode createVertex(Impact entity)
	{
		IESEDataNode vertex = new IESEDataNode(entity);
		// vertex.setToString(entity.getEffect() + "->" + entity.getQualifiedName());
		vertex.setToString(entity.getTarget().getQualifiedName());
		elementVertexMap.put(entity.getQualifiedName(), vertex);
		return vertex;
	}

	public IESEDataNode createVertex(Measure entity)
	{
		IESEDataNode vertex = new IESEDataNode(entity);
		vertex.setToString(entity.getQualifiedName());
		elementVertexMap.put(entity.getQualifiedName(), vertex);
		return vertex;
	}

	// public IESEDataNode createVertex(Effect entity)
	// {
	// IESEDataNode vertex = new IESEDataNode(entity);
	// if (entity == Effect.POSITIVE)
	// vertex.setToString("+");
	// else
	// vertex.setToString("-");
	//
	// elementVertexMap.put("" + entity, vertex);
	// return vertex;
	// }

	public IESEDataNode createVertex(Evaluation entity)
	{
		IESEDataNode vertex = new IESEDataNode(entity);
		vertex.setToString(entity.getQualifiedName());
		elementVertexMap.put(entity.getQualifiedName(), vertex);
		return vertex;
	}

	public QualityModelElement getEntity(IESEDataNode vertex)
	{
		return (QualityModelElement) vertex.getData();
	}

	public IESEDataNode getVertex(QualityModelElement entity)
	{
		return elementVertexMap.get(entity.getQualifiedName());
	}

	public boolean vertexExists(QualityModelElement entity)
	{
		return elementVertexMap.get(entity.getQualifiedName()) != null;
	}

	public Collection<IESEDataNode> getVertices()
	{
		return elementVertexMap.values();
	}
}
