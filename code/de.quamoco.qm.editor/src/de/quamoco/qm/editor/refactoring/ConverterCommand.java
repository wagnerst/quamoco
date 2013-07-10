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

package de.quamoco.qm.editor.refactoring;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.ChangeCommand;

/**
 * Command to convert an element to another type.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: A6E35E82D47BFE3D0BF7C691A4118421
 */
public class ConverterCommand extends ChangeCommand {

	/** The used converter. */
	private final Converter converter;

	/** The result of the conversion. */
	private EObject target;

	/** Constructor. */
	public ConverterCommand(Converter converter) {
		super(converter.getResourceSet());

		this.converter = converter;
	}

	/** {@inheritDoc} */
	@Override
	protected boolean prepare() {
		return super.prepare() && converter.canApply();
	}

	/** {@inheritDoc} */
	@Override
	protected void doExecute() {
		target = converter.apply();
	}

	/** {@inheritDoc} */
	@Override
	public Collection<?> getAffectedObjects() {
		return Collections.singletonList(target);
	}
}
