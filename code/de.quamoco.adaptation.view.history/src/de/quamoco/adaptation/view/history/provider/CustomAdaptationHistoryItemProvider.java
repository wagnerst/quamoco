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

package de.quamoco.adaptation.view.history.provider;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import de.quamoco.adaptation.model.status.AdaptationHistoryItem;
import de.quamoco.adaptation.model.status.provider.AdaptationHistoryItemItemProvider;
import de.quamoco.adaptation.view.history.util.ViewConstants.TableColumns;

// TODO comment
public class CustomAdaptationHistoryItemProvider extends AdaptationHistoryItemItemProvider implements ITableItemLabelProvider {

	protected final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MM:yy HH:mm:ss");
	
	/** Defines the types for which this object is an adapter for. */
	protected Collection<Class<?>> adapterTypes = new LinkedList<Class<?>>();
	
	/**
	 * Calls the super constructor and initializes {@link #adapterTypes}.
	 * @param adapterFactory
	 */
	public CustomAdaptationHistoryItemProvider(AdapterFactory adapterFactory) {
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
	
	/** Provides text for the columns. */
	@Override
	public String getColumnText(Object object, int columnIndex) {
		if (object instanceof AdaptationHistoryItem) {
			AdaptationHistoryItem historyItem = (AdaptationHistoryItem) object;

			if (columnIndex == TableColumns.ACTION.INDEX) {
				return historyItem.getPerformedActionName();
			}
			if (columnIndex == TableColumns.VALUE.INDEX) {
				return historyItem.getPerformedActionValue();
			}
			if (columnIndex == TableColumns.ELEMENT.INDEX) {
				return historyItem.getAffectedElementName();				
			}
			if (columnIndex == TableColumns.JUSTIFICATION.INDEX) {
				return historyItem.getJustification();
			}
			if (columnIndex == TableColumns.SOURCE.INDEX) {
				return historyItem.getSource().getName();
			}
			if (columnIndex == TableColumns.TIMESTAMP.INDEX) {
				Date timestamp = historyItem.getTimestamp();
				if (timestamp != null) {
					return simpleDateFormat.format(timestamp);
				}
			}			
		}
		return "";
	}

}
