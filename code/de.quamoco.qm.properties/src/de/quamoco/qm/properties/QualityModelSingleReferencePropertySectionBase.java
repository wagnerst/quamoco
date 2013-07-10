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

package de.quamoco.qm.properties;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;

import de.quamoco.qm.QualityModelElement;
import de.quamoco.qm.util.QmModelUtils;
import edu.tum.cs.emf.commons.sections.SingleReferencePropertySectionBase;

/**
 * The base class for displaying and editing the value of a single-valued
 * reference in the properties view. Additionally, this base class enforces that
 * the requires relationship between elements from different modules is
 * respected.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 989EB0789E5E6559070C66908DECD89E
 */
public class QualityModelSingleReferencePropertySectionBase extends
		SingleReferencePropertySectionBase {

	/** Constructor. */
	public QualityModelSingleReferencePropertySectionBase(EReference reference,
			EAttribute descriptionAttribute) {
		super(reference, descriptionAttribute);
	}

	/** Constructor. */
	public QualityModelSingleReferencePropertySectionBase(EReference reference) {
		super(reference);
	}

	/** {@inheritDoc} */
	@Override
	protected Collection<?> getChoiceOfValues() {
		Collection<?> values = super.getChoiceOfValues();
		QualityModelElement element = (QualityModelElement) getElement();
		for (Iterator<?> i = values.iterator(); i.hasNext();) {
			QualityModelElement value = (QualityModelElement) i.next();
			if (!QmModelUtils.requires(element, value)) {
				i.remove();
			}
		}
		return values;
	}
}
