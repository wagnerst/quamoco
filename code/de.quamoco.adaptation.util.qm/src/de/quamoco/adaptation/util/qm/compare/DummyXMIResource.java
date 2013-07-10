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

package de.quamoco.adaptation.util.qm.compare;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import de.quamoco.adaptation.util.qm.QualityModelUtil;
import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.QualityModel;

/**
 * A dummy XMIResource that is necessary for comparing two
 * {@link QualityModel}s since the comparison is based on IDs.
 * If the {@link QualityModel}s are stored in "real" resources
 * their IDs will be different (UUIDs) and therefore not comparable.
 * The IDs are retrieved from the annotations that are created
 * by the {@link CompareCopier}.
 * @author Franz Becker
 * @see QualityModelCompareViewer
 */
public class DummyXMIResource extends XMIResourceImpl {

	/** Counts how much resources have been create and therefore is the
	 *  next id of each resource (included in URI). */
	private static int nextResourceId = 0;
	
	/** The quality model of this "resource" */
	protected QualityModel qualityModel;
	
	/** Mapping of IDs to {@link EObject}s for performance reasons. */
	protected Map<String, EObject> idToEObjectMap;

	/** Indicates whether the IDs for the element shall be retrieved from the annotations
	 * (adaptation ID) or from the {@link QualityModel}s resource. */
	protected boolean retrieveIDsFromResource;

	/** The resource of the {@link QualityModel} when {@link #retrieveIDsFromResource} is true. */
	protected XMIResource resource;

	/**
	 * Calls the super constructor and adds the passed
	 * {@link QualityModel} to it contents. Ignores the 
	 * exception since the only usage of this resource is
	 * to provide the id's of model elements.
	 * @param qualityModel the {@link QualityModel} that shall reside within this resource
	 */
	public DummyXMIResource(QualityModel qualityModel, boolean retrieveIDsFromResource) {
		super();
		setURI(URI.createPlatformResourceURI("/dummy/dummy" + nextResourceId++ + ".qm", true));
		this.qualityModel = qualityModel;
		this.retrieveIDsFromResource = retrieveIDsFromResource;
		if (retrieveIDsFromResource) {
			if (qualityModel.eResource() instanceof XMIResource) {
				this.resource = (XMIResource) qualityModel.eResource();
			} else {
				throw new IllegalArgumentException("Cannot retrieve IDs from resource if the passed quality model" +
						"does not reside within a XMIResource!");
			}
		}		
		int initialCapacity = qualityModel.getEntities().size() + qualityModel.getFactors().size() + qualityModel.getMeasures().size();
		idToEObjectMap = new HashMap<String, EObject>(initialCapacity);
		builtMap(qualityModel);
		try {
			this.getContents().add(qualityModel);
		} catch (Exception e) {
			// ignore 
		}		
	}

	/**
	 * Builds the {@link #idToEObjectMap}.
	 */
	protected void builtMap(Object object) {
		List<AnnotatedElement> annotatedElements = QualityModelUtil.getAnnotatedElements(qualityModel);
		for (AnnotatedElement annotatedElement : annotatedElements) {
			addAnnotatedElementToMap(annotatedElement);
		} 	
	}
	
	/**
	 * Adds a passed {@link AnnotatedElement} to the {@link #idToEObjectMap}
	 * @param element the annotated element
	 */
	protected void addAnnotatedElementToMap(AnnotatedElement element) {
		String id;
		if (retrieveIDsFromResource) {
			id = resource.getID(element);
		} else {
			id = QualityModelUtil.getAdaptationID(element);			 
		}
		if (id != null) {
			idToEObjectMap.put(id, element);
		}
	}

	/**
	 * Retrieves the ID of the passed object.
	 */
	@Override
	public String getID(EObject eObject) {
		if (retrieveIDsFromResource) {
			return resource.getID(eObject);
		} else {
			return QualityModelUtil.getAdaptationID(eObject);			 
		}
	}
	
	/**
	 * Returns the given {@link EObject} for the passed ID.
	 */
	@Override
	public EObject getEObject(String id) {
		if (idToEObjectMap.containsKey(id)) {
			return idToEObjectMap.get(id);
		}		
		return null;		
	}

	/**
	 * Throws an {@link UnsupportedOperationException} since {@link DummyXMIResource}
	 * must not be saved anywhere.
	 */
	@Override
	public void save(Map<?, ?> options) throws IOException {
		throw new UnsupportedOperationException("A dummy resource can't be saved!");
	}

	// TODO 
	@Override
	public URI getURI() {
		return uri;
	}

	@Override
	public String getURIFragment(EObject eObject) {
		String adaptationID = QualityModelUtil.getAdaptationID(eObject);
		if (adaptationID != null) {
			return adaptationID;
		}
		return "dummyFragment";
	}
	
}
