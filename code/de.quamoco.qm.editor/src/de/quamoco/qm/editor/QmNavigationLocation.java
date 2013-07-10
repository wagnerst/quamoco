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

package de.quamoco.qm.editor;

import java.util.List;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.INavigationLocation;
import org.eclipse.ui.NavigationLocation;

/**
 * Navigation location for the quality model editor
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 20C76E8271277EFF486F65C71A3016CB
 */
public class QmNavigationLocation extends NavigationLocation {

	/**
	 * List of URIs
	 */
	private final List<String> uris;

	/**
	 * Constructor
	 */
	protected QmNavigationLocation(CustomQmEditor editor) {
		super(editor);
		uris = editor.getSelectedURIs();
		if (uris.isEmpty()) {
			ResourceSet resourceSet = editor.getEditingDomain()
					.getResourceSet();
			uris.add(resourceSet.getResources().get(0).getURI().toString());
		}
	}

	/**
	 * Get the editor
	 */
	private CustomQmEditor getEditor() {
		return (CustomQmEditor) getEditorPart();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean mergeInto(INavigationLocation currentLocation) {
		if (currentLocation instanceof QmNavigationLocation) {
			QmNavigationLocation location = (QmNavigationLocation) currentLocation;

			if (uris.equals(location.uris)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void restoreLocation() {
		getEditor().setSelection(uris);
	}

	/**
	 * {@inheritDoc}
	 */
	public void restoreState(IMemento memento) {
		// not required, as the navigation location is transient
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveState(IMemento memento) {
		// not required, as the navigation location is transient
	}

	/**
	 * {@inheritDoc}
	 */
	public void update() {
		// not required
	}
}
