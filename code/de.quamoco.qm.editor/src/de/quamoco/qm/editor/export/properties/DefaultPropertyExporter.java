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

package de.quamoco.qm.editor.export.properties;

import java.io.IOException;
import java.io.PrintStream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import de.quamoco.qm.editor.export.WebSiteExporter;
import de.quamoco.qm.editor.export.properties.elements.PropElement;

/**
 * Exports a Properties View
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
public class DefaultPropertyExporter extends PropertyExporter<EObject> {

	/**
	 * Constructor
	 */
	public DefaultPropertyExporter(WebSiteExporter parent, EObject element,
			String dir) {
		super(parent, element, dir);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void printContent(PrintStream out) throws IOException {
		final EList<EStructuralFeature> eAllStructuralFeatures = element
				.eClass().getEAllStructuralFeatures();

		PropElement[] features = new PropElement[eAllStructuralFeatures.size()];
		for (int i = 0; i < features.length; i++) {
			features[i] = PropElement.createCorrectOne(eAllStructuralFeatures
					.get(i));
		}
		printTableOfStructuralElements(out, features);
	}

}
