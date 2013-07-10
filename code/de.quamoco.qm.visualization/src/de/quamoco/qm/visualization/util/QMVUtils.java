/*-------------------------------------------------------------------------+
|                                                                          |
| Fraunhofer-Institut fuer Experimentelles Software Engineering (IESE)     |
|                                                                          |
|                                                                          |
|   NOT TO BE PUBLISHED UNDER QUAMOCO LICENSE                              |
|                                                                          |
+-------------------------------------------------------------------------*/

package de.quamoco.qm.visualization.util;

import iese.inseviz.data.IESEDataEdge;
import iese.inseviz.data.IESEDataNode;
import iese.inseviz.data.IESEDataTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.quamoco.qm.Factor;
import de.quamoco.qm.QualityModelElement;

/**
 * Copyright (c) 01.08.2011 Fraunhofer IESE. All rights reserved.<br>
 * <br>
 * COMMENT <br>
 * <br>
 * created: 01.08.2011 - 14:34:31<br>
 * author: Dr. Henning Barthel.<br>
 * project: QuamocoVis<br>
 * <br>
 * $Revision: 72 $<br>
 * $Id: QMVUtils.java 72 2011-09-23 12:35:43Z barthel $<br>
 * 
 * @version $Revision: 72 $
 * 
 */
public class QMVUtils
{
	/**
	 * Sorts the given list, assuming that the generic type if of type 'de.quamoco.qm.QualityModelElement'.
	 * 
	 * @param <T>
	 * @param list
	 */
	public static <T> List<T> sortListByQualifiedName(List<T> list)
	{
		List<String> nameList = new ArrayList<String>();
		Map<String, T> nameFactorMap = new HashMap<String, T>();
		for (T element : list)
		{
			String name = ((QualityModelElement) element).getQualifiedName();
			nameList.add(name);
			if (nameFactorMap.get(name) != null)
			{
				System.err.println("Error: factor " + element + " allready in nameFactorMap!");
				System.err.flush();
			}
			nameFactorMap.put(name, element);
		}
		Collections.sort(nameList);
		List<T> sortedList = new ArrayList<T>();
		for (String name : nameList)
		{
			T element = nameFactorMap.get(name);
			if (element instanceof Factor)
				sortedList.add(element);
		}
		return sortedList;
	}

	public static void sortEdgesByTargetNames(IESEDataTree tree)
	{
		List<String> names = new ArrayList<String>();
		List<IESEDataEdge> edges = new ArrayList<IESEDataEdge>();
		Map<String, IESEDataEdge> nameEdgeMap = new HashMap<String, IESEDataEdge>();
		for (IESEDataNode vertex : tree.vertexSet())
		{
			if (tree.outDegreeOf(vertex) == 0)
				continue;

			edges.clear();
			names.clear();
			nameEdgeMap.clear();
			edges.addAll(tree.outgoingEdgesOf(vertex));
			tree.removeAllEdges(edges);
			for (IESEDataEdge edge : edges)
			{
				String name = edge.getTarget().toString();
				names.add(name);
				nameEdgeMap.put(name, edge);
			}
			Collections.sort(names);
			for (String name : names)
			{
				IESEDataEdge edge = nameEdgeMap.get(name);
				tree.addEdge(edge);
			}
		}
	}

	// public static String toString(List<QualityModel> list)
	// {
	// String s = "";
	// for (int i = 0; i < list.size() - 1; i++)
	// s += list.get(i).getQualifiedName() + ",";
	// s += list.get(list.size() - 1).getQualifiedName();
	// return s;
	// }

	public static String toString(List<? extends QualityModelElement> list)
	{
		String s = "";
		if (list.size() > 0)
		{
			for (int i = 0; i < list.size() - 1; i++)
				s += list.get(i).getQualifiedName() + ",";
			s += list.get(list.size() - 1).getQualifiedName();
		}
		return s;
	}

	public static String toString(Set<? extends QualityModelElement> list)
	{
		String s = "";
		Iterator iterator = list.iterator();
		while (iterator.hasNext())
		{
			QualityModelElement element = (QualityModelElement) iterator.next();
			s += element.getQualifiedName() + ",";
		}

		return s;
	}
}
