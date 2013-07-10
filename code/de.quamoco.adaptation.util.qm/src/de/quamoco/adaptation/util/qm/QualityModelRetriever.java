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

package de.quamoco.adaptation.util.qm;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import de.quamoco.qm.QualityModel;

// TODO implement for performance reasons
public class QualityModelRetriever {
	
	static {
		ResourcesPlugin.getWorkspace().addResourceChangeListener(new ResourceChangeListener());
	}
	
	private static Set<QualityModel> qualityModels = new HashSet<QualityModel>();
	
	public static TransactionalEditingDomain getEditingDomain() {
//		TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain();
		return null;
		
	}
	
	public static synchronized Collection<QualityModel> getAllQualityModels() {
		if (qualityModels == null) {
			collectQualityModels();
		}
		return qualityModels;	
	}

	private static void collectQualityModels() {
		// TODO Auto-generated method stub
		
	}

	private static class ResourceChangeListener implements IResourceChangeListener {

		@Override
		public void resourceChanged(IResourceChangeEvent event) {
			System.out.println(event);
		}
		
	}
}
