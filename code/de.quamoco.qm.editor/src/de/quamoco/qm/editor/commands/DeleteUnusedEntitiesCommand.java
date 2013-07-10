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

package de.quamoco.qm.editor.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.ChangeCommand;

import de.quamoco.qm.Entity;
import de.quamoco.qm.QualityModel;

/**
 * Command for deleting unused entities.
 * 
 * @author lochmann
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 2215A85EB1A0D3C83507B4C35C99D6D3
 */
public class DeleteUnusedEntitiesCommand extends ChangeCommand {

	/** The source that should be added to all elements. */
	private final QualityModel qualityModel;

	/** Constructor. */
	public DeleteUnusedEntitiesCommand(QualityModel qualityModel) {
		super(qualityModel.eResource().getResourceSet());

		this.qualityModel = qualityModel;
	}

	/** {@inheritDoc} */
	@Override
	protected void doExecute() {
		List<Entity> toDelete = new ArrayList<Entity>();

		for (Entity entity : qualityModel.getEntities()) {
			if (!isUsed(entity)) {
				toDelete.add(entity);
			}
		}

		for (Entity entity : toDelete) {
			EcoreUtil.delete(entity);
		}
	}

	/**
	 * Determines is an entity is either used by a factor/measure or if it
	 * is part of a meaningful hierarchy.
	 */
	private boolean isUsed(Entity e) {
		if (!e.getCharacterizedBy().isEmpty()) {
			return true;
		}
		for (Entity sub : e.getConsistsOfDirect()) {
			if (isUsed(sub)) {
				return true;
			}
		}
		for (Entity sub : e.getSpecializedByDirect()) {
			if (isUsed(sub)) {
				return true;
			}
		}
		return false;
	}
}