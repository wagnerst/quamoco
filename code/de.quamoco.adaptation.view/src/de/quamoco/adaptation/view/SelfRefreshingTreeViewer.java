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

package de.quamoco.adaptation.view;

import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import de.quamoco.adaptation.util.resource.ResourceUtil;

/**
 * A {@link TreeViewer} that automatically adapts the {@link CommandStack} of the
 * passed input's {@link EditingDomain}(s) and calls {@link TreeViewer#refresh()}
 * each time any command is executed.<br>
 * <br>
 * <b>Might be improved by refreshing more selectively.</b>
 * @author Franz Becker
 */
public class SelfRefreshingTreeViewer extends TreeViewer {

	/** The {@link EditingDomain}s that are currently adapted. */
	protected Set<EditingDomain> adaptedEditingDomains = new HashSet<EditingDomain>();
	
	/** A {@link CommandStackListener} that always calls {@link TreeViewer#refresh()}. */
	protected final CommandStackListener commandStackListener = new CommandStackListener() {

		@Override
		public void commandStackChanged(EventObject event) {
			// TODO improve - efficiency
			refresh();
		}

	};

	/**
	 * Calls the super constructor
	 */
	public SelfRefreshingTreeViewer(Composite parent, int style) {
		super(parent, style);			
	}

	/**
	 * Removes the listeners from the previously adapted editing domains, 
	 * {@link #updateAdaptedEditingDomains(Collection) gets} the new ones and adapts them.
	 */
	@Override
	protected void inputChanged(Object input, Object oldInput) {
		super.inputChanged(input, oldInput);
		
		/* Remove listeners */
		removeAllListener();
		
		/* Update adaptedEditingDomains */
		if (input instanceof EObject) {
			updateAdaptedEditingDomains(Collections.singleton(input));
		} else if (input instanceof Collection<?>) {
			updateAdaptedEditingDomains((Collection<?>) input);
		}
		
		/* Add listeners */
		adaptEditingDomains();
		
	}
	
	/**
	 * Adds the {@link #commandStackListener} to all {@link #adaptedEditingDomains}.
	 */
	protected void adaptEditingDomains() {
		for (EditingDomain editingDomain : adaptedEditingDomains) {
			editingDomain.getCommandStack().addCommandStackListener(commandStackListener);
		}
	}
	
	/**
	 * Removes the {@link #commandStackListener} from all {@link #adaptEditingDomains()}.
	 */
	protected void removeAllListener() {
		for (EditingDomain editingDomain : adaptedEditingDomains) {
			editingDomain.getCommandStack().removeCommandStackListener(commandStackListener);
		}
	}
	
	/**
	 * Updates {@link #adaptedEditingDomains} by searching {@link EObject} within
	 * the passed input objects and trying to retrieve their {@link EditingDomain}
	 * by using {@link ResourceUtil#getEditingDomainFor(EObject)}.
	 * @param inputObjects a collection of objects passed as the input
	 */
	protected void updateAdaptedEditingDomains(Collection<?> inputObjects) {
		adaptedEditingDomains.clear();
		for (Object object : inputObjects) {
			if (object instanceof EObject) {
				EditingDomain editingDomain = ResourceUtil.getEditingDomainFor((EObject) object);
				if (editingDomain != null) {
					adaptedEditingDomains.add(editingDomain);
				}
			}				
		}
		
	}
	
	/**
	 * Disposes this control (removes all listeners and disposes {@link #getControl()}).
	 */
	public void dispose() {
		removeAllListener();
		getControl().dispose();
	}
}
