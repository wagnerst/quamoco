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

package de.quamoco.adaptation.util.resource;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * Provides an abstract {@link IResourceVisitor} that searches for files having
 * a specified file extension and a given root element (specified by the type parameter
 * and the {@link #isTypeInstance(EObject)}).
 * @author Franz Becker
 *
 * @param <TypeParameter> make sure that {@link #isTypeInstance(EObject)} returns true
 * for instance of this type.
 */
public abstract class AbstractResourceVisitor<TypeParameter extends EObject> implements IResourceVisitor {

	/** The list of found elements. */
	private LinkedList<TypeParameter> elements = new LinkedList<TypeParameter>();
	
	/** The editing domain in which the elements's resource shall be loaded. */
	private EditingDomain editingDomain;

	private List<String> fileExtensions;
		
	/** 
	 * Initializes the fields
	 * @param editingDomain initialize {@link #editingDomain}
	 */
	public AbstractResourceVisitor(EditingDomain editingDomain, List<String> fileExtensions) {
		this.editingDomain = editingDomain;
		this.fileExtensions = fileExtensions;
	}

	/**
	 * Visits the resource and its members and fills the {@link #elements} list 
	 * accordingly.
	 * @see #QualityModelResourceVisitor(EditingDomain)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean visit(IResource resource) {
		if (isDesiredResource(resource)) {
			/*
			 * Handling EMF Resource -> get URI then load it into editing domain.
			 */
			try {
				URI uri = URI.createFileURI(resource.getFullPath().toString());
				Resource eResource = editingDomain.getResourceSet().getResource(uri, true);			
				EList<EObject> contents = eResource.getContents();
				if (contents.size() > 0) {
					if (isTypeInstance(contents.get(0))) {
						elements.add((TypeParameter) contents.get(0));
					}				
				}

			} catch (Exception e) { /* don't care */ }

		}
		return true;
	}	

	/**
	 * Has to be implemented by subclasses in the following way:
	 * <code>return eObject instanceof TypeParameter</code>!
	 * @param eObject the object
	 * @return true if eObject is instanceof TypeParameter, false otherwise
	 */
	protected abstract boolean isTypeInstance(EObject eObject);

	/**
	 * Returns the found elements, call {@link #visit(IResource)} fist, obviously.
	 * @return the found elements
	 */
	public List<TypeParameter> getElements() {
		return elements;
	}
	
	/**
	 * Checks if the passed resource has the right file extension
	 * @param resource the resource
	 * @return true if resource is a file (has file extension) and it's lower cased file extension is 
	 * 			in {@link #fileExtensions}.
	 */
	public boolean isDesiredResource(IResource resource) {
		if (resource.exists()) {
			String fileExtension = resource.getFileExtension();
			// fileExtension.toLowerCase() is only included if someone types e.g. My.EXTENSION
			return fileExtension != null && fileExtensions.contains(fileExtension.toLowerCase());
		}
		return false;			   
	}

}
