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

package de.quamoco.adaptation.view.tasks.provider;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.swt.graphics.Image;

import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.model.status.provider.AdaptationTaskItemProvider;
import de.quamoco.adaptation.view.tasks.Activator;
import de.quamoco.adaptation.view.tasks.util.ViewConstants.TableColumns;
import de.quamoco.qm.NamedElement;

// TODO comment
public class CustomAdaptationTaskItemProvider extends AdaptationTaskItemProvider implements ITableItemLabelProvider {

	/** The image shown if a task is completed, fakes a checked checkbox. */
	protected static final Image COMPLETE = Activator.getImageDescriptor("icons/complete_tsk.gif").createImage();
	
	/** The image shown if a task is incomplete, fakes an unchecked checkbox. */
	protected static final Image INCOMPLETE = Activator.getImageDescriptor("icons/incomplete_tsk.gif").createImage();
	
	/** The image shown if a task is ignored by the user. */
	protected static final Image IGNORED = Activator.getImageDescriptor("icons/actions/hide_ignored.gif").createImage();
	
	/** Defines the types for which this object is an adapter for. */
	protected Collection<Class<?>> adapterTypes = new LinkedList<Class<?>>();
	
	/**
	 * Calls the super constructor and initializes {@link #adapterTypes}.
	 * @param adapterFactoryd
	 */
	public CustomAdaptationTaskItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
		
		adapterTypes.add(ITableItemLabelProvider.class);
		adapterTypes.add(ITreeItemContentProvider.class);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		if (adapterTypes.contains(type)) {
			return true;
		}
		return super.isAdapterForType(type);
	}
	
	@Override
	// TODO implement children
	public boolean hasChildren(Object object) {
		return false;
	}
	
	@Override
	public Collection<?> getChildren(Object object) {
		return Collections.emptyList();
	}
	
	@Override
	public Object getParent(Object object) {
		return null;
	}

	/** Provides images for the columns. */
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if ((columnIndex == TableColumns.COMPLETED.INDEX) && (element instanceof AdaptationTask)) {
			AdaptationTask task = (AdaptationTask) element;	
			if (task.isIgnoredByUser()) {
				return IGNORED;
			} else {
				return task.isCompleted() ? COMPLETE : INCOMPLETE;	
			}			
		} 
		return null;		
	}	
	
	/** Provides text for the columns. */
	@Override
	public String getColumnText(Object object, int columnIndex) {
		// TODO
		if (object instanceof AdaptationTask) {
			AdaptationTask task = (AdaptationTask) object;
			EObject affectedElement = task.getAffectedElement();

			if (columnIndex == TableColumns.COMPLETED.INDEX) {
				return "";
			}
			if (columnIndex == TableColumns.NAME.INDEX) {
				if (affectedElement instanceof NamedElement) {
					return ((NamedElement) affectedElement).getName();
				}				
			}
			if (columnIndex == TableColumns.MESSAGE.INDEX) {
				return task.getUserMessage();
			}
			if (columnIndex == TableColumns.CLASS.INDEX) {
				if (affectedElement != null) {
					return affectedElement.eClass().getName();
				}
			}			
		}
		return "";
	}

	@Override
	public void notifyChanged(Notification notification) {
		// TODO Auto-generated method stub
		super.notifyChanged(notification);
	}
	
	
	

}
