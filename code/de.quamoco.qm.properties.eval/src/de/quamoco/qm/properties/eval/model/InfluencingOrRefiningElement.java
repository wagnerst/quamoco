/*-------------------------------------------------------------------------+
|                                                                          |
| Copyright 2012 Technische Universitaet Muenchen and                      |
| Fraunhofer-Institut fuer Experimentelles Software Engineering (IESE)     |
|                                                                          |
| Licensed under the Apache License, Version 2.0 (the "License");          |
| you may not use this file except in compliance with the License.         |
| You may obtain a copy of the License at                                  |
|                                                                          |
|    http://www.apache.org/licenses/LICENSE-2.0                            |
|                                                                          |
| Unless required by applicable law or agreed to in writing, software      |
| distributed under the License is distributed on an "AS IS" BASIS,        |
| WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. |
| See the License for the specific language governing permissions and      |
| limitations under the License.                                           |
|                                                                          |
+-------------------------------------------------------------------------*/

package de.quamoco.qm.properties.eval.model;

import de.quamoco.qm.CharacterizingElement;
import de.quamoco.qm.Ranking;
import de.quamoco.qm.properties.eval.ParseQIESLException;

/**
 * This class is a data container for all relevant information about factors or
 * measures that refine or influence the evaluated element.
 * 
 * @author klaes
 * 
 */
public abstract class InfluencingOrRefiningElement<ElementType extends CharacterizingElement> {

	/**
	 * Provides the type of the relationship between the evaluated element and
	 * the InfluencingOrRefiningElement:
	 * 
	 * For factors as elements: positive = influenced with negative impact
	 * negative = influenced with positive impact refinement = refined by the
	 * element
	 * 
	 * For measures as elements: findings number none
	 * 
	 * @author klaes
	 * 
	 */
	public static enum Type {
		positive, negative, refinement, findings, number, none
	};

	private int ranking; // Ranking -1: no ranking, 0: not relevant, or >0
	private double contPoints; // ContributionPoints
	private int maxPoints; // MaxPoints
	private Type type;
	private String module;
	private ElementType element;

	/**
	 * @param element
	 * @param name
	 * @param ranking
	 * @param contPoints
	 * @param maxPoints
	 * @param type
	 * @param module
	 */
	public InfluencingOrRefiningElement(ElementType element, int ranking,
			double contPoints, int maxPoints, Type type, String module) {
		this.element = element;
		this.ranking = ranking;
		this.contPoints = contPoints;
		this.maxPoints = maxPoints;
		this.type = type;
		this.module = module;
		this.element = element;
	}

	/**
	 * Throws an {@link IllegalStateException} if element has not been set!
	 * 
	 * @return
	 */
	public ElementType getElement() {
		if (element != null) {
			return this.element;
		}
		throw new IllegalStateException("Element has not been set!");
	}

	public String getName() {
		return element.getQualifiedName();
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public double getContPoints() {
		return contPoints;
	}

	public void setContPoints(double contPoints) {
		this.contPoints = contPoints;
	}

	public int getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}

	public Type getType() {
		return type;
	}

	public void setElement(ElementType element) {
		this.element = element;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String toString() {
		return this.getName() + "; " + this.ranking + "; " + this.contPoints
				+ "; " + this.maxPoints + "; " + this.type + "; " + this.module;
	}

	public static Type parseType(String type) throws ParseQIESLException {

		if (type.equals("positive"))
			return Type.positive;
		else if (type.equals("negative"))
			return Type.negative;
		else if (type.equals("refinement"))
			return Type.refinement;
		else
			throw new ParseQIESLException(
					"InfluencingOrRefiningElement: Unknown Type used");

	}

	public boolean equals(Object o) {

		if (o != null & o.getClass().equals(this.getClass())) {

			InfluencingOrRefiningElement<?> f = (InfluencingOrRefiningElement<?>) o;

			if (!(this.getName().equals(f.getName())))
				return false;
			if (!(this.ranking == f.getRanking()))
				return false;
			if (!(this.contPoints == f.getContPoints()))
				return false;
			if (!(this.maxPoints == f.getMaxPoints()))
				return false;
			if (!(this.type.equals(f.getType())))
				return false;
			if (!(this.module.equals(f.getModule())))
				return false;

			return true;

		} else
			return false;
	}

	public boolean represents(Ranking ranking) {
		return ranking.getRankedElement() == element
				&& this.ranking == ranking.getRank()
				&& ranking.getWeight() == (contPoints / maxPoints)
				&& element.getQualityModel() != null;
	}

}
