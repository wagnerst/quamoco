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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import de.quamoco.adaptation.model.status.AdaptationHistoryItem;
import de.quamoco.adaptation.util.qm.QualityModelUtil;
import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.AnnotationBase;
import de.quamoco.qm.QualityModel;

public class CustomQualityModelItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, ITableItemLabelProvider, IItemPropertySource, IItemColorProvider {
	
	public CustomQualityModelItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	public Collection<?> getChildren(Object object) {
		QualityModel qualityModel = (QualityModel) object;
		List<AdaptationHistoryItem> historyItems = new LinkedList<AdaptationHistoryItem>();
		
		/* Add items of AnnotatedElements */
		List<AnnotatedElement> annotatedElements = QualityModelUtil.getAnnotatedElements(qualityModel);
		for (AnnotatedElement annotatedElement : annotatedElements) {
			for (AnnotationBase baseAnnotation : annotatedElement.getAdvancedAnnotations()) {
				if (baseAnnotation instanceof AdaptationHistoryItem) {
					historyItems.add((AdaptationHistoryItem) baseAnnotation);
				}
			}
		}		
		
		return historyItems;
	}
	
}
