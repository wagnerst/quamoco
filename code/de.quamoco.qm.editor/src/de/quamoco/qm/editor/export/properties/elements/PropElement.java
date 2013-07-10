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

package de.quamoco.qm.editor.export.properties.elements;

import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import de.quamoco.qm.editor.export.WebSiteExporter;

/**
 * Root class of all types of rows that can be exported
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
public abstract class PropElement {
	
	protected NumberFormat nf = new DecimalFormat("0.00");
	
	public static WebSiteExporter webSiteExporter;

	/**
	 * The name of the row
	 */
	public abstract String getName();

	/**
	 * Writes the element to the stream 
	 * @throws IOException 
	 */
	public abstract void export(PrintStream out, EObject element) throws IOException;


	/**
	 * A factory method to automatically create the correct objects for
	 * StructuralFeatures
	 * 
	 * @param feat
	 * @return
	 */
	public static PropElement createCorrectOne(EStructuralFeature feat) {
		if (feat instanceof EAttribute) {
			return new FeatureSingleValue(feat);
		} else {
			if (feat.isMany()) {
				return new FeatureMultipleReference(feat);
			}
			return new FeatureSingleReference(feat);
		}
	}
}
