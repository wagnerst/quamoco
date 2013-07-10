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

package de.quamoco.qm.search.ui.factories;

import org.eclipse.emf.search.ui.areas.IModelSearchArea;
import org.eclipse.emf.search.ui.areas.IModelSearchAreaFactory;
import org.eclipse.emf.search.ui.pages.AbstractModelSearchPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import de.quamoco.qm.search.ui.areas.ModelSearchParticipantArea;


/**
 * Wrapper class which create a specific participant area into the model search page.
 */
public class ModelSearchParticipantAreaFactory implements
		IModelSearchAreaFactory {
	public IModelSearchArea createArea(Composite parent,
			AbstractModelSearchPage searchPage) {
		return new ModelSearchParticipantArea(parent, searchPage, SWT.NONE);
	}

	public IModelSearchArea createArea(Composite parent,
			AbstractModelSearchPage searchPage, String nsURI) {
		return new ModelSearchParticipantArea(parent, searchPage, SWT.NONE,
				nsURI);
	}
}
