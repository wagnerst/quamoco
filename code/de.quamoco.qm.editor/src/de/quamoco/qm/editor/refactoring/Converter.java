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

package de.quamoco.qm.editor.refactoring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.conqat.lib.commons.assertion.CCSMPre;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.quamoco.qm.util.QmModelUtils;
import edu.tum.cs.emf.commons.resources.UUIDUtils;

/**
 * Helper class to convert an element to another type.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 4A965936BBCE9AE4F93F82F5813BEC21
 */
public class Converter {

	/** Element to be converted to another type. */
	private final EObject source;

	/** {@link ResourceSet} in whose context the conversion is performed. */
	private final ResourceSet resourceSet;

	/** Mapping to convert the value of a feature to another feature. */
	private final Map<EStructuralFeature, EStructuralFeature> featureMapping = new IdentityHashMap<EStructuralFeature, EStructuralFeature>();

	/** Mapping to convert an element of a certain type to another type. */
	private final Map<EClass, EClass> classMapping = new IdentityHashMap<EClass, EClass>();

	/** Constructor. */
	public Converter(EObject element) {
		this.source = element;
		this.resourceSet = element.eResource().getResourceSet();
	}

	/** Map a feature to another feature. */
	public void map(EStructuralFeature source, EStructuralFeature target) {
		CCSMPre.isTrue(source.eClass() == target.eClass(),
				"The features must be of the same type");
		if (source instanceof EAttribute) {
			CCSMPre.isTrue(source.getEType() == target.getEType(),
					"The attributes must have the same type");
		} else {
			EReference sr = (EReference) source;
			EReference tr = (EReference) target;
			CCSMPre.isTrue(tr.getEReferenceType().isSuperTypeOf(
					sr.getEReferenceType()),
					"The references must have compatible type.");
		}
		featureMapping.put(source, target);
	}

	/** Map a class to another class. */
	public void map(EClass source, EClass target) {
		classMapping.put(source, target);
	}

	/** Resolve a feature w.r.t. the mapping. */
	@SuppressWarnings("unchecked")
	public <F extends EStructuralFeature> F resolve(F feature) {
		if (featureMapping.containsKey(feature)) {
			return (F) featureMapping.get(feature);
		}
		return feature;
	}

	/** Resolve a class w.r.t. the mapping. */
	public EClass resolve(EClass eClass) {
		if (classMapping.containsKey(eClass)) {
			return classMapping.get(eClass);
		}
		return eClass;
	}

	/** Check whether the converter can be applied. */
	public boolean canApply() {
		return getContainment() != null;
	}

	/** Get the reference to contain the converted element. */
	private EReference getContainment() {
		EClass targetClass = resolve(source.eClass());
		{
			EReference containment = source.eContainmentFeature();
			containment = resolve(containment);
			if (canReferToTarget(containment, targetClass)) {
				return containment;
			}
		}
		EObject container = source.eContainer();
		for (EReference containment : container.eClass().getEAllContainments()) {
			if (canReferToTarget(containment, targetClass)) {
				return containment;
			}
		}
		return null;
	}

	/** Check whether a reference can refer to an instance of a certain class. */
	private boolean canReferToTarget(EReference reference, EClass eClass) {
		EClass type = reference.getEReferenceType();
		return type.isSuperTypeOf(eClass);
	}

	/** Apply the converter to the source element and its children. */
	public EObject apply() {
		EObject container = source.eContainer();
		EReference containment = getContainment();

		EObject target = apply(container, containment, source);
		return target;
	}

	/** Apply the converter to a single element. */
	private EObject apply(EObject container, EReference containment,
			EObject source) {
		EObject target = EcoreUtil.create(resolve(source.eClass()));

		convertCrossReferences(source, target);
		convertFeatures(source, target);
		convertContents(source, target);

		String id = UUIDUtils.getId(source);
		EcoreUtil.delete(source);

		put(container, containment, target);
		UUIDUtils.setId(target, id);
		return target;
	}

	/** Convert the cross references targeting the source element. */
	private void convertCrossReferences(EObject source, EObject target) {
		for (EReference sourceReference : getCrossReferences(source.eClass())) {
			List<EObject> elements = QmModelUtils.getInverse(sourceReference,
					source, EObject.class);
			EReference targetReference = resolve(sourceReference);
			if (targetReference != sourceReference) {
				for (EObject element : elements) {
					put(element, targetReference, target);
				}
			} else if (!sourceReference.isDerived()) {
				for (EObject element : elements) {
					if (targetReference.getEContainingClass().isSuperTypeOf(
							element.eClass())) {
						put(element, targetReference, target);
					}
				}
			}
		}
	}

	/** Get the cross references to a certain class. */
	private List<EReference> getCrossReferences(EClass eClass) {
		List<EReference> references = new ArrayList<EReference>();
		Resource resource = eClass.eResource();
		for (Iterator<EObject> i = resource.getAllContents(); i.hasNext();) {
			EObject element = i.next();
			if (element instanceof EReference) {
				EReference reference = (EReference) element;
				if (reference.isDerived() || isContainment(reference)
						|| reference.getEOpposite() != null) {
					continue;
				}
				if (canReferToTarget(reference, eClass)) {
					references.add(reference);
				}
			}
		}
		return references;
	}

	/** Convert the values of the features of an element. */
	private void convertFeatures(EObject source, EObject target) {
		for (EStructuralFeature sourceFeature : source.eClass()
				.getEAllStructuralFeatures()) {
			if (sourceFeature instanceof EReference
					&& isContainment((EReference) sourceFeature)) {
				continue;
			}
			EStructuralFeature targetFeature = resolve(sourceFeature);
			if (sourceFeature != targetFeature) {
				move(source, sourceFeature, target, targetFeature);
			} else if (!sourceFeature.isDerived()
					&& targetFeature.getEContainingClass().isSuperTypeOf(
							target.eClass())) {
				move(source, sourceFeature, target, targetFeature);
			}
		}
	}

	/** Move the value of a feature from the source to the target element. */
	@SuppressWarnings("unchecked")
	private void move(EObject source, EStructuralFeature sourceFeature,
			EObject target, EStructuralFeature targetFeature) {
		if (sourceFeature.isMany() && !targetFeature.isMany()) {
			List values = (List) source.eGet(sourceFeature);
			if (!values.isEmpty()) {
				target.eSet(targetFeature, values.get(0));
			}
		} else if (!sourceFeature.isMany() && targetFeature.isMany()) {
			Object value = source.eGet(sourceFeature);
			if (value != null) {
				target.eSet(targetFeature, Collections.singletonList(value));
			}
		} else {
			target.eSet(targetFeature, source.eGet(sourceFeature));
		}
	}

	/** Convert the contents of an element. */
	private void convertContents(EObject source, EObject target) {
		for (EReference containment : source.eClass().getEAllContainments()) {
			List<EObject> contents = new ArrayList<EObject>(get(source,
					containment));
			containment = resolve(containment);
			if (!containment.getEContainingClass().isInstance(target)) {
				continue;
			}
			for (EObject content : contents) {
				if (content.eClass() != resolve(content.eClass())) {
					apply(target, containment, content);
				} else if (containment.getEReferenceType().isInstance(content)) {
					put(target, containment, content);
				}
			}
		}
	}

	/** Get the value of a reference of an element. */
	@SuppressWarnings("unchecked")
	private List<EObject> get(EObject element, EReference reference) {
		Object value = element.eGet(reference);
		if (reference.isMany()) {
			return (List<EObject>) value;
		} else if (value != null) {
			return Collections.singletonList((EObject) value);
		}
		return Collections.emptyList();
	}

	/** Set or add a value to the reference of an element. */
	@SuppressWarnings("unchecked")
	private void put(EObject element, EReference reference, EObject value) {
		if (reference.isMany()) {
			((List) element.eGet(reference)).add(value);
		} else {
			element.eSet(reference, value);
		}
	}

	/** Check whether a reference is part of a containment. */
	private boolean isContainment(EReference reference) {
		return reference.isContainment() || reference.isContainer();
	}

	/**
	 * Get the {@link ResourceSet} in whose context the conversion is performed.
	 */
	public ResourceSet getResourceSet() {
		return resourceSet;
	}
}
