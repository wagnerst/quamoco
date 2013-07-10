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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;

import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.model.status.provider.StatusItemProviderAdapterFactory;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;

// TODO comment
public class TaskAdapterFactory extends StatusItemProviderAdapterFactory {

	protected CustomQualityModelItemProvider tasksQualityModelItemProvider;
	protected CustomAdaptationTaskItemProvider customAdaptationTaskItemProvider;

	public TaskAdapterFactory() {
	    super();
	    supportedTypes.add(ITableItemLabelProvider.class);	 
	}
	
	@Override
	public boolean isFactoryForType(Object type) {
		if (type instanceof QmPackage) {
			return true;
		}
		return super.isFactoryForType(type);
	}
	
	@Override
	public Adapter createAdapter(Notifier target) {
		if (target instanceof QualityModel) {
			return createQualityModelAdapter();
		}
		if (target instanceof AdaptationTask) {
			return createAdaptationTaskAdapter();
		}
		return super.createAdapter(target);
	}

	@Override
	public Adapter createAdaptationTaskAdapter() {
		if (customAdaptationTaskItemProvider == null) {
			customAdaptationTaskItemProvider = new CustomAdaptationTaskItemProvider(this);
		}
		return customAdaptationTaskItemProvider;
	}

	protected Adapter createQualityModelAdapter() {
		if (tasksQualityModelItemProvider == null) {
			tasksQualityModelItemProvider = new CustomQualityModelItemProvider(this);
		}
		return tasksQualityModelItemProvider;
	}
	
}
