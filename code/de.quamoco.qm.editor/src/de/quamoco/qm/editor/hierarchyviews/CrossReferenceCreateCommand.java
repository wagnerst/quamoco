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

package de.quamoco.qm.editor.hierarchyviews;

import java.util.Collection;
import java.util.Collections;
import java.util.Map.Entry;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandActionDelegate;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * A command to create an element in a poly hierarchy, in which an element can
 * have two parents.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 54771059C15E10E2D618B69CAF4CCB18
 */
public class CrossReferenceCreateCommand extends CompoundCommand implements
		CommandActionDelegate {

	/** Constructor. */
	public CrossReferenceCreateCommand(EditingDomain domain, EObject parent,
			EReference childrenReference, EReference parentReference,
			EObject child) {
		addCreateCommand(domain, parent, child);
		addLinkCommand(domain, parent, childrenReference, parentReference,
				child);
	}

	/** Add the command to create the new element. */
	private void addCreateCommand(EditingDomain domain, EObject parent,
			EObject child) {
		Entry<EObject, EReference> container = getPossibleContainer(parent,
				child);
		CommandParameter commandParameter = new CommandParameter(container
				.getKey(), container.getValue(), child);
		append(CreateChildCommand.create(domain, container.getKey(),
				commandParameter, Collections.singleton(container.getKey())));
	}

	/** Get the possible container and reference of a value. */
	private Entry<EObject, EReference> getPossibleContainer(EObject owner,
			EObject value) {
		while (owner != null) {
			EReference containment = getPossibleContainment(owner, value);
			if (containment != null) {
				return Collections.singletonMap(owner, containment).entrySet()
						.iterator().next();
			}
			owner = owner.eContainer();
		}
		return null;
	}

	/** Get a reference of an owner in which a value could be contained. */
	private EReference getPossibleContainment(EObject owner, EObject value) {
		for (EReference containment : owner.eClass().getEAllContainments()) {
			if (containment.getEType().isInstance(value)) {
				return containment;
			}
		}
		return null;
	}

	/** Add the command to establish the cross reference. */
	private void addLinkCommand(EditingDomain domain, EObject parent,
			EReference childrenReference, EReference parentReference,
			EObject child) {
		if (childrenReference != null) {
			if (childrenReference.isMany()) {
				append(AddCommand.create(domain, parent, childrenReference,
						child, -1));
			} else {
				append(SetCommand.create(domain, parent, childrenReference,
						child));
			}
		} else if (parentReference != null) {
			if (parentReference.isMany()) {
				append(AddCommand.create(domain, child, parentReference,
						parent, -1));
			} else {
				append(SetCommand
						.create(domain, child, parentReference, parent));
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public Object getImage() {
		return getCreateCommand().getImage();
	}

	/** Get the command to create the new element. */
	private CreateChildCommand getCreateCommand() {
		return (CreateChildCommand) getCommandList().get(0);
	}

	/** {@inheritDoc} */
	@Override
	public String getText() {
		return getCreateCommand().getText();
	}

	/** {@inheritDoc} */
	@Override
	public String getToolTipText() {
		return getCreateCommand().getToolTipText();
	}

	/** {@inheritDoc} */
	@Override
	public Collection<?> getAffectedObjects() {
		return getCreateCommand().getAffectedObjects();
	}
}