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

package de.quamoco.qm.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.conqat.lib.commons.assertion.CCSMAssert;
import org.conqat.lib.commons.collections.UnmodifiableList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import de.quamoco.qm.Entity;
import de.quamoco.qm.Evaluation;
import de.quamoco.qm.EvaluationResult;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FactorRefinement;
import de.quamoco.qm.Impact;
import de.quamoco.qm.Instrument;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasurementMethod;
import de.quamoco.qm.MeasurementResult;
import de.quamoco.qm.NormalizationMeasure;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.QualityModelElement;
import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.Tag;
import de.quamoco.qm.TaggedElement;
import edu.tum.cs.emf.commons.cache.ResourceSetCrossReferenceCache;

/**
 * Helper methods to deal with quality models.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 7E27C53E92A4EE1F75F224F6AEABDEF7
 */
public final class QmModelUtils {

	/** Constructor. */
	private QmModelUtils() {
		// hidden
	}

	/** Check whether a {@link QualityModel} requires another one. */
	public static boolean requires(QualityModel source, QualityModel target) {
		return (source == target)
				|| getRequiredQualityModels(source).contains(target);
	}

	/**
	 * Check whether there is a requires relationship between two
	 * {@link QualityModelElement}s.
	 */
	public static boolean requires(QualityModelElement source,
			QualityModelElement target) {
		return requires(source.getQualityModel(), target.getQualityModel());
	}

	/**
	 * Gets all {@link QualityModel}s that are required by the given
	 * {@link QualityModel}.
	 */
	public static List<QualityModel> getRequiredQualityModels(QualityModel model) {
		return getTransitive(model,
				QmPackage.eINSTANCE.getQualityModel_Requires());
	}

	/** Checks whether the element is tagged by a tag with name "tagname". */
	public static boolean isTaggedBy(TaggedElement element, String tagname) {
		if (tagname == null) {
			return false;
		}
		for (Tag tag : element.getTaggedBy()) {
			if (tagname.equalsIgnoreCase(tag.getName())) {
				return true;
			}
		}
		return false;
	}

	/** Get the quality models within a resource set. */
	public static List<QualityModel> getQualityModels(ResourceSet resourceSet) {
		return getRootElementsOfType(resourceSet, QualityModel.class);
	}

	public static <E extends EObject> List<E> getRootElementsOfType(
			ResourceSet resourceSet, Class<E> type) {
		List<EObject> rootElements = getRootElements(resourceSet);
		return getElementsOfType(rootElements, type);
	}

	public static List<EObject> getRootElements(ResourceSet resourceSet) {
		List<EObject> rootElements = new ArrayList<EObject>();
		for (Resource resource : resourceSet.getResources()) {
			for (EObject root : resource.getContents()) {
				rootElements.add(root);
			}
		}
		return rootElements;
	}

	/** Get the refinement between a parent and child factor. */
	public static FactorRefinement getRefinement(Factor parent, Factor child) {
		for (FactorRefinement refinement : child.getRefines()) {
			if (refinement.getParent() == parent) {
				return refinement;
			}
		}
		return null;
	}

	/** Get the impact from a source to a target factor. */
	public static Impact getImpact(Factor source, Factor target) {
		for (Impact impact : source.getInfluences()) {
			if (impact.getTarget() == target) {
				return impact;
			}
		}
		return null;
	}

	/** Return all instruments for the given models. */
	public static List<Instrument> getConcreteMeasures(List<QualityModel> models) {
		List<Instrument> measures = new ArrayList<Instrument>();
		for (QualityModel model : models) {
			for (Measure measure : model.getMeasures()) {
				for (MeasurementMethod determination : measure
						.getDeterminedBy()) {
					if (determination instanceof Instrument) {
						measures.add((Instrument) determination);
					}
				}
			}
		}
		return measures;
	}

	/** Returns all characterizing factors for an entity */
	public static List<Factor> getCharacterizedByFactors(Entity entity) {
		return getInverse(
				QmPackage.eINSTANCE.getCharacterizingElement_Characterizes(),
				entity, Factor.class);
	}

	/** Returns all characterizing measures for an entity. */
	public static List<Measure> getCharacterizedByMeasures(Entity entity) {
		return getInverse(
				QmPackage.eINSTANCE.getCharacterizingElement_Characterizes(),
				entity, Measure.class);
	}

	/** Get the elements that target an element using a certain reference. */
	public static <E extends EObject> List<E> getInverse(EReference reference,
			EObject target, Class<E> type) {
		EClass c = target.eClass();
		EClass t = reference.getEReferenceType();
		CCSMAssert.isTrue(c == t || c.getEAllSuperTypes().contains(t),
				"The reference must target the element's type or any "
						+ "of its supertypes");

		List<EObject> inverse = ResourceSetCrossReferenceCache.getInverse(
				reference, target);
		List<E> result = getElementsOfType(inverse, type);
		return new UnmodifiableList<E>(result);
	}

	/** Extract the elements of a certain type from a list. */
	public static <E> List<E> getElementsOfType(
			List<? extends EObject> elements, Class<E> type) {
		List<E> result = new ArrayList<E>();
		for (EObject element : elements) {
			addIfOfType(result, element, type);
		}
		return result;
	}

	private static <E> void addIfOfType(List<E> result, EObject element,
			Class<E> type) {
		if (type.isInstance(element)) {
			result.add((E) element);
		}
	}

	public static <E> List<E> getContainedElementsOfType(
			List<? extends EObject> rootElements, Class<E> type) {
		List<E> result = new ArrayList<E>();
		for (EObject rootElement : rootElements) {
			addIfOfType(result, rootElement, type);
			for (Iterator<EObject> i = rootElement.eAllContents(); i.hasNext();) {
				addIfOfType(result, i.next(), type);
			}
		}
		return result;
	}

	/**
	 * Returns all sub-types (i.e. follows the specialized-by relationship
	 * transitively.
	 */
	public static Set<Entity> getAllSpecializedByEntities(Entity entity) {
		Set<Entity> result = new HashSet<Entity>();
		Queue<Entity> queue = new LinkedList<Entity>(
				entity.getSpecializedByDirect());
		while (!queue.isEmpty()) {
			Entity e = queue.poll();
			if (!result.contains(e)) {
				result.add(e);
				queue.addAll(e.getSpecializedByDirect());
			}
		}
		return result;
	}

	/**
	 * Returns all super-types (i.e. follows the is-a relationship transitively.
	 */
	public static List<Entity> getAllIsAEntities(Entity entity) {
		return getTransitive(entity, QmPackage.eINSTANCE.getEntity_IsADirect());
	}

	/** Returns all entities, this is part of. */
	public static List<Entity> getAllPartOfEntities(Entity entity) {
		return getTransitive(entity,
				QmPackage.eINSTANCE.getEntity_PartOfDirect());
	}

	/** Returns all entities, that are parts of this entity. */
	public static Set<Entity> getAllConsistsOfEntity(Entity entity) {
		Set<Entity> result = new HashSet<Entity>();
		Queue<Entity> queue = new LinkedList<Entity>(
				entity.getConsistsOfDirect());
		while (!queue.isEmpty()) {
			Entity e = queue.poll();
			if (!result.contains(e)) {
				result.add(e);
				queue.addAll(e.getConsistsOfDirect());
			}
		}
		return result;
	}
	
	/** Returns all entities, that are parts of this entity. */
	public static Set<Entity> getAllConsistsOfOrSpecializedByEntity(Entity entity) {
		Set<Entity> result = new HashSet<Entity>();
		Queue<Entity> queue = new LinkedList<Entity>(
				entity.getConsistsOfDirect());
		queue.addAll(entity.getSpecializedByDirect());
		while (!queue.isEmpty()) {
			Entity e = queue.poll();
			if (!result.contains(e)) {
				result.add(e);
				queue.addAll(e.getConsistsOfDirect());
				queue.addAll(e.getSpecializedByDirect());
			}
		}
		return result;
	}

	/** Returns transitively all refinements and implements of a factor. */
	public static Set<Factor> getAllRefinementsAndImplements(Factor factor) {
		Set<Factor> result = new HashSet<Factor>();
		Queue<Factor> toProcess = new LinkedList<Factor>();

		toProcess.addAll(factor.getRefinedByDirect());
		// toProcess.addAll(getInverse(QmPackage.eINSTANCE
		// .getImplementingFactor_Implements(), factor, FactorBase.class));

		while (!toProcess.isEmpty()) {
			Factor fb = toProcess.poll();
			if (!result.contains(fb)) {
				result.add(fb);
				toProcess.addAll(fb.getRefinedByDirect());
				// toProcess.addAll(getInverse(QmPackage.eINSTANCE
				// .getImplementingFactor_Implements(), fb, Factor.class));
			}
		}

		return result;
	}

	/** Get the transitive closure of a reference, starting from an element. */
	public static <V extends EObject> List<V> getTransitive(V start,
			EReference reference) {
		return getTransitive(start, reference, true);
	}

	/** Get the transitive closure of a reference, starting from an element. */
	public static <V extends EObject> List<V> getTransitive(V start,
			EReference reference, boolean includeStart) {
		List<V> values = new ArrayList<V>();
		TransitiveReferenceIterator<V> i = new TransitiveReferenceIterator<V>(
				start, reference, includeStart);
		while (i.hasNext()) {
			values.add(i.next());
		}
		return values;
	}

	public static boolean isContainedBy(EObject containee, EObject container) {
		EObject element = containee.eContainer();
		while (element != null && element != container) {
			element = element.eContainer();
		}
		return element != null;
	}

	/**
	 * Return all normalization measures that can be used from the given element
	 * via requires relation between quality models.
	 */
	public static List<NormalizationMeasure> getNormalizationMeasures(
			QualityModelElement element) {
		List<NormalizationMeasure> measures = new ArrayList<NormalizationMeasure>();
		List<QualityModel> requiredQualityModels = getRequiredQualityModels(element
				.getQualityModel());
		for (QualityModel qm : requiredQualityModels) {
			for (Measure measure : qm.getMeasures()) {
				if (measure instanceof NormalizationMeasure) {
					measures.add((NormalizationMeasure) measure);
				}
			}
		}
		return measures;
	}

	/**
	 * Returns the deepest quality model regarding the requires relation in the
	 * resource set in which the given element is contained.
	 */
	public static QualityModel getDeepestModelInRequiresRelation(
			QualityModelElement element) {
		ResourceSet resourceSet = element.eResource().getResourceSet();
		List<QualityModel> models = QmModelUtils.getQualityModels(resourceSet);
		QualityModel deepest = null;
		for (QualityModel model : models) {
			if (deepest == null) {
				deepest = model;
			} else if (QmModelUtils.requires(model, deepest)) {
				deepest = model;
			}
		}
		return deepest;
	}

	/** Returns the actual evaluation in the deepest model */
	public static Evaluation getActualEvaluationInDeepestModel(Factor factor) {
		return factor.getActualEvaluation(QmModelUtils
				.getDeepestModelInRequiresRelation(factor));
	}

	/** Returns the measurement method in the deepest model */
	public static MeasurementMethod getActualMeasurementMethodInDeepestModel(
			Measure measure) {
		return measure.getActualMeasurementMethod(QmModelUtils
				.getDeepestModelInRequiresRelation(measure));
	}

	/** Get the child result for a certain factor. */
	public static EvaluationResult getResult(QualityModelResult model,
			Factor factor) {
		Evaluation evaluation = getActualEvaluationInDeepestModel(factor);
		if (evaluation == null) {
			return null;
		}
		List<EvaluationResult> results = QmModelUtils.getInverse(
				QmPackage.eINSTANCE.getEvaluationResult_ResultsFrom(),
				evaluation, EvaluationResult.class);
		for (EvaluationResult result : results) {
			if (result.eContainer() == model) {
				return result;
			}
		}
		return null;
	}

	/** Get the child result for a certain measure. */
	public static MeasurementResult getResult(QualityModelResult model,
			Measure measure) {
		MeasurementMethod method = getActualMeasurementMethodInDeepestModel(measure);
		if (method == null) {
			return null;
		}
		List<MeasurementResult> results = QmModelUtils.getInverse(
				QmPackage.eINSTANCE.getMeasurementResult_ResultsFrom(), method,
				MeasurementResult.class);
		for (MeasurementResult result : results) {
			if (result.eContainer() == model) {
				return result;
			}
		}
		return null;
	}
}