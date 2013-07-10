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

package de.quamoco.qm.editor.tableviews.generalcolumns;

import org.eclipse.emf.ecore.EStructuralFeature;

import de.quamoco.qm.editor.tableviews.ListTableViewer;
import edu.tum.cs.emf.commons.resources.ResourceLocatorUtils;

/**
 * This class is a specialization of a QmTableColumn. It serves as the
 * superclass of all table columns, that represent exactly one
 * EStructuralFeature.
 * 
 * @author herrmama
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating YELLOW Hash: 94EDAF2C6F866029993D11B09CBE226B
 */
public abstract class QmFeatureColumn extends QmTableColumn {

	/** The EStructuralFeature that represents the content of the column. */
	protected EStructuralFeature feature;

	/** Which image should be shown. */
	protected EFeatureColumnImage imageType = EFeatureColumnImage.NONE;

	/** Constructor. */
	public QmFeatureColumn(ListTableViewer parent, EStructuralFeature feature,
			String caption, EFeatureColumnImage imageType) {
		super(parent);
		this.feature = feature;
		this.imageType = imageType;
		if (caption == null) {
			if (feature != null) {
				tableColumn.setText(ResourceLocatorUtils.getString(feature));
			}
		} else {
			tableColumn.setText(caption);
		}
	}
}
