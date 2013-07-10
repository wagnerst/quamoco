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

package de.quamoco.adaptation.wizard.util.elementsselection.provider;

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;

/**
 * Container that holds a {@link QualityModel} and a {@link EReference}
 * of it to serve as a dummy root.<br>
 * 
 * This class inherits from {@link EObjectImpl} to be adaptable by adapter factories
 * (needs to be a subtype of {@link Notifier}.
 * @author Franz Becker
 * @levd.rating GREEN Hash: 6B13838FA7733D01A4225D2C369CAA22
 */
public class TransientRoot extends EObjectImpl {

	/** The {@link QualityModel} of interest. */
	private QualityModel qualityModel;
	
	/** The reference of the {@link QualityModel} describing the desired children. */
	private EReference reference;
	
	/**
	 * Calls the super constructor and initializes variables
	 * @param qualityModel the {@link QualityModel} that shall be used.
	 * @param reference the {@link EReference} of the {@link QualityModel} that describe the desired children.
	 */
	public TransientRoot(QualityModel qualityModel, EReference reference) {
		super();
		this.qualityModel = qualityModel; // may be null if getReferencedElements is overriden by dummy subclass
		this.reference = reference; // may be null if getReferencedElements is overriden by dummy subclass
		if (reference != null && !QmPackage.Literals.ANNOTATED_ELEMENT.isSuperTypeOf((EClass) reference.getEType())) {
			throw new IllegalArgumentException("Only referenced to annotated elements are allowed!");
		}
	}
	
	/**
	 * @return the {@link QualityModel}
	 */
	public QualityModel getQualityModel() {
		return qualityModel;
	}
	
	/**
	 * @return the reference
	 */
	public EReference getReference() {
		return reference;
	}	
	
	/**
	 * @return the {@link AnnotatedElement}s that are accessible using eGet on the
	 * {@link QualityModel} using the stored reference.
	 */
	@SuppressWarnings("unchecked")
	public Collection<AnnotatedElement> getReferencedElements() {
		Collection<AnnotatedElement> result = new LinkedList<AnnotatedElement>();
		Object getResult = qualityModel.eGet(reference);
		
		if (reference.isMany()) {
			result.addAll((Collection<? extends AnnotatedElement>) getResult);
		} else {
			result.add((AnnotatedElement) getResult);
		}
		
		return result;
	}
	
}
