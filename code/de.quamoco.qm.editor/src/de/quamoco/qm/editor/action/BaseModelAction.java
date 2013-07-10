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

package de.quamoco.qm.editor.action;

import java.util.Map.Entry;

import org.conqat.ide.commons.ui.selection.SelectionUtils;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.QmFactory;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.impl.AnnotationImpl;
import edu.tum.cs.emf.commons.commands.WrappingCompoundCommand;

/**
 * Action to tag an element as base model.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: BE899E28C5BACD1FFD4DC093C67F635A
 */
public class BaseModelAction implements IObjectActionDelegate {

	/** Key for the base model annotation. */
	private static final String BASE_MODEL_KEY = "BaseModel";

	/** Annotated element. */
	private AnnotatedElement element;

	/** Editing domain. */
	private EditingDomain domain;

	/** {@inheritDoc} */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		domain = ((IEditingDomainProvider) targetPart).getEditingDomain();
	}

	/** {@inheritDoc} */
	public void run(IAction action) {
		Entry<String, String> entry = getAnnotation();
		Command command = null;
		if (entry != null) {
			command = new RemoveCommand(domain, element, QmPackage.eINSTANCE
					.getAnnotatedElement_Annotations(), entry);
		} else {
			AnnotationImpl impl = (AnnotationImpl) QmFactory.eINSTANCE
					.create(QmPackage.eINSTANCE.getAnnotation());
			impl.setKey(BASE_MODEL_KEY);
			impl.setValue("");

			command = new AddCommand(domain, element, QmPackage.eINSTANCE
					.getAnnotatedElement_Annotations(), impl);
		}
		domain.getCommandStack().execute(
				new WrappingCompoundCommand(command, element));
	}

	/** {@inheritDoc} */
	public void selectionChanged(IAction action, ISelection selection) {
		element = SelectionUtils.checkAndPickFirstSafe(selection,
				AnnotatedElement.class);
		if (element == null) {
			return;
		}

		if (getAnnotation() != null) {
			action.setChecked(true);
		} else {
			action.setChecked(false);
		}
	}

	/** Get the base model annotation. */
	private Entry<String, String> getAnnotation() {
		for (Entry<String, String> entry : element.getAnnotations().entrySet()) {
			if (BASE_MODEL_KEY.equals(entry.getKey())) {
				return entry;
			}
		}
		return null;
	}
}
