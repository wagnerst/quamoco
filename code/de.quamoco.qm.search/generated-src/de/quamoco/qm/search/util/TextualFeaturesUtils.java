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

package de.quamoco.qm.search.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;

import de.quamoco.qm.QmPackage;

public class TextualFeaturesUtils

{

	public TextualFeaturesUtils() {
	}

	private static TextualFeaturesUtils instance;

	public static TextualFeaturesUtils instance() {
		return instance == null ? instance = new TextualFeaturesUtils()
				: instance;
	}

	public List<ETypedElement> getParticipantTextualTypedElement() {
		List<ETypedElement> list = new ArrayList<ETypedElement>();

		list.addAll(getQmPackageParticipantTextualTypedElement());

		return list;
	}

	public List<ETypedElement> getQmPackageParticipantTextualTypedElement() {
		return Arrays.asList(new ETypedElement[] {

		QmPackage.Literals.DESCRIBED_ELEMENT__DESCRIPTION,
				QmPackage.Literals.ANNOTATION__KEY,
				QmPackage.Literals.ANNOTATION__VALUE,
				QmPackage.Literals.TEXT_AGGREGATION__SPECIFICATION,
				QmPackage.Literals.IMPACT__JUSTIFICATION,
				QmPackage.Literals.NAMED_ELEMENT__NAME,
				QmPackage.Literals.TEXT_EVALUATION__SPECIFICATION });
	}

	public List<ETypedElement> getOwnedETypedElementsFromEObject(EObject obj) {
		List<ETypedElement> list = new ArrayList<ETypedElement>();
		for (ETypedElement e : getParticipantTextualTypedElement()) {
			if (e instanceof EStructuralFeature) {
				try {
					Object o = obj.eGet((EStructuralFeature) e);
					if (o instanceof String) {
						list.add(e);
					}
				} catch (Throwable t) {
					//ugly
				}
			}
		}
		return list;
	}

	public String getTextFromETypedElement(EObject obj, ETypedElement elem) {
		if (elem instanceof EStructuralFeature) {
			Object o = obj.eGet((EStructuralFeature) elem);
			if (o instanceof String) {
				return (String) o;
			}
		}
		return null;
	}

	public String getTextFromEStructuralFeatureIfAny(EObject obj) {
		List<ETypedElement> lst = getOwnedETypedElementsFromEObject(obj);
		if (!lst.isEmpty()) {
			ETypedElement elem = lst.get(0);
			if (elem instanceof EStructuralFeature) {
				Object o = obj.eGet((EStructuralFeature) elem);
				if (o instanceof String) {
					return (String) o;
				}
			}
		}
		return null;
	}

	public void setTextForEStructuralFeatureIfAny(EObject obj, Object val) {
		for (ETypedElement e : getParticipantTextualTypedElement()) {
			if (e instanceof EStructuralFeature) {
				try {
					if (val instanceof String) {
						obj.eSet((EStructuralFeature) e, val);
					}
				} catch (Throwable ex) {
					//TODO: User to handle exception in an elegant way
				}
			}
		}
	}

	public void setTextForETypedElement(EObject obj, ETypedElement elem,
			Object val) {
		if (elem instanceof EStructuralFeature) {
			try {
				if (val instanceof String) {
					obj.eSet((EStructuralFeature) elem, val);
				}
			} catch (Throwable ex) {
				//TODO: User to handle exception in an elegant way
			}
		}
	}
}