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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;

/**
 * A table-viewer that shows all element of a certain type.
 * 
 * @author herrmama
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Hash: 90AFE9DF9F00F4EF885AE4681DE178B1
 */
public class QmTableViewer extends ListTableViewer {

	/** Type of elements shown in this table. */
	private final EClass typeOfElements;

	/** Constructor. */
	public QmTableViewer(Composite parent, int style, EClass typeOfElements) {
		super(parent, style);
		this.typeOfElements = typeOfElements;
		delegateTableViewer.setLabelProvider(new QmTableLabelProvider());
	}


	/** {@inheritDoc} */
	@Override
	public void initialize(EditingDomain editingDomain,
			AdapterFactory adapterFactory) {
		super.initialize(editingDomain, adapterFactory);
		delegateTableViewer.setContentProvider(this);
	}

	/**
	 * Returns all {@link EObject}s of the {@link EClass} eClass that are
	 * contained in the model.
	 */
	private Object[] findEObjectsOfType(EClass eClass) {
		List<EObject> eObjects = new ArrayList<EObject>();
		List<Resource> resources = editingDomain.getResourceSet()
				.getResources();
		for (Resource resource : resources) {
			Iterator<EObject> iterator = resource.getAllContents();
			while (iterator.hasNext()) {
				EObject eObject = iterator.next();
				if (eObject.eClass() == eClass) {
					eObjects.add(eObject);
				}
			}
		}

		return eObjects.toArray();
	}

	/** Returns all elements that are shown in the table. */
	@Override
	public Object[] getElements(Object inputElement) {
		return findEObjectsOfType(this.typeOfElements);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		// do nothing
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// refresh if the input changed
		delegateTableViewer.setLabelProvider(new QmTableLabelProvider());
		delegateTableViewer.refresh();
	}

}
