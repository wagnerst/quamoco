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

import org.eclipse.swt.graphics.Image;

import de.quamoco.qm.QualityModelElement;
import de.quamoco.qm.editor.tableviews.QmTableViewer;

/**
 * A column that shows the resource of a {@link QualityModelElement}.
 * 
 * @author herrmama
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating YELLOW Hash: 087D35FEE68871F62F4407EF9F20BAD4
 */
public class QmResourceColumn extends QmTableColumn {

	/** Constructor. */
	public QmResourceColumn(QmTableViewer parent) {
		super(parent);
		tableColumn.setWidth(75);
		tableColumn.setText("Resource");
	}

	/** {@inheritDoc} */
	@Override
	public Image getColumnImage(Object element) {
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String getColumnText(Object element) {
		QualityModelElement modelElement = (QualityModelElement) element;
		return modelElement.getQualityModel().getName();
	}

	/** {@inheritDoc} */
	@Override
	public String getSortObject(Object element) {
		return getColumnText(element);
	}
}
