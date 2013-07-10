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

package de.quamoco.qm.editor.tableviews;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.swt.widgets.Composite;

import edu.tum.cs.emf.commons.cache.ResourceSetCrossReferenceCache;

/**
 * A table viewer that show the references to a model element that are inverse
 * w.r.t to a certain reference.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: CEFCFFD0CFA24ABFE6FEFCEA3935DD6B
 */
public class OppositeReferencesTableViewer extends ReferenceTableViewer {

	/** Constructor. */
	public OppositeReferencesTableViewer(Composite parent, int style,
			EReference featureToFollow) {
		super(parent, style, featureToFollow);
	}

	/** {@inheritDoc} */
	@Override
	public Object[] getElements(Object inputElement) {
		if (rootObject == null) {
			return new Object[] {};
		}
		return ResourceSetCrossReferenceCache.getInverse(reference,
				rootObject).toArray();
	}
}
