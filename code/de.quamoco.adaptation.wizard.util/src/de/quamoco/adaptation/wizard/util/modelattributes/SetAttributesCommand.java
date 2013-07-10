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

package de.quamoco.adaptation.wizard.util.modelattributes;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.model.status.QmAdaptationAttributes;
import de.quamoco.adaptation.model.status.StatusFactory;
import de.quamoco.adaptation.model.status.UserMarkedCompleted;
import de.quamoco.adaptation.util.qm.QualityModelUtil;
import de.quamoco.adaptation.wizard.util.WizardState;
import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.Entity;
import de.quamoco.qm.Factor;
import de.quamoco.qm.QmFactory;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.Tag;

/**
 * Updates the attributes of a {@link QualityModel} according to the contents of 
 * the {@link ModelAttributesComposite}. Does not execute any purpose actions!
 * @author Franz Becker
 * @ConQAT.Rating YELLOW Hash: 0EB20AEE054461B461B70A4E228DE779
 */
public class SetAttributesCommand extends AbstractCommand {
	
	/** The {@link ModelAttributesComposite} delivering the necessary data. */
	private ModelAttributesComposite attributesComposite;
	
	/** The wizard state. */
	private WizardState wizardState;
	
	/*
	 * Data stored for undo reasons
	 */
	private String oldName = null;
	private String oldDescription = null;
	private QmAdaptationAttributes adaptationAttributes = null;
	private Entity newEntity = null;
	private Factor newViewpoint = null;
	private Factor newQualityFocus = null;
	private List<AdaptationTask> tasksToDelete = new LinkedList<AdaptationTask>();
	private List<Tag> tagsToRemoveFromQualityModelUponUndo = new LinkedList<Tag>();
	private List<Tag> tagsToDeleteUponUndo = new LinkedList<Tag>();
	private Collection<AnnotatedElement> preselectedElements = new LinkedList<AnnotatedElement>(); // contains elements of the reference model!
	
	/**
	 * Calls the super constructor and initializes instance variables
	 * @param qualityModel
	 * @param attributesComposite
	 */
	public SetAttributesCommand(ModelAttributesComposite attributesComposite, WizardState wizardState) {
		super();
		this.attributesComposite = attributesComposite;
		this.wizardState = wizardState;
	}
		
	/**
	 * Command can only be executed iff instance variables are unset.
	 * Here {@link #adaptationAttributes} is used as trigger.
	 */
	@Override
	protected boolean prepare() {
		return adaptationAttributes == null;
	}

	/**
	 * Sets the attributes and stores data for undo, see comments below.
	 */
	@Override
	public void execute() {
		QualityModel qualityModel = wizardState.getQualityModel();
		QualityModel referenceModel = wizardState.getReferenceQualityModel();
		
		// Set name and description
		oldName = qualityModel.getName();
		oldDescription = qualityModel.getDescription();
		qualityModel.setName(attributesComposite.getAttributeName());
		qualityModel.setDescription(attributesComposite.getAttributeDescription());
		
		// Create QmAdaptationAttributes
		adaptationAttributes = StatusFactory.eINSTANCE.createQmAdaptationAttributes();
		String object = attributesComposite.getAttributeObject();
		String viewpoint = attributesComposite.getAttributeViewpoint();
		String qualityFocus = attributesComposite.getAttributeQualityFocus();
		Factor referenceViewpoint = null;
		
		/*
		 * Object attribute - iterate over reference entities to search for the matching entity
		 * If found use this one as object, if not found create a new one and add Todo-Entry.
		 */
		if (object != null && !object.isEmpty()) {
			for (Entity entity : referenceModel.getEntities()) {
				if (entity.getName().equals(object)) {
					adaptationAttributes.setObject((Entity) wizardState.getQualityModelElement(entity));
					preselectedElements.add(entity);
					break;
				}
			}
			if (adaptationAttributes.getObject() == null) {
				// not found -> new Entity required
				newEntity = QmFactory.eINSTANCE.createEntity();
				newEntity.setName(object);
				addTodoTask(newEntity);
				QualityModelUtil.createNewAdaptationID(newEntity);
				qualityModel.getEntities().add(newEntity);
			}
		}			

		/*
		 * Viewpoint attribute - iterate over reference factors to search for the matching factor
		 * If found use this one as viewpoint, if not found create a new one and add Todo-Entry.
		 */
		if (viewpoint != null && !viewpoint.isEmpty()) {
			for (Factor factor : referenceModel.getFactors()) {
				String factorName = factor.getName();
				if (factorName.equals(viewpoint)) {					
					adaptationAttributes.setViewpoint((Factor) wizardState.getQualityModelElement(factor));
					referenceViewpoint = factor;
					preselectedElements.add(factor);
					break;
				} 
			}
			if (adaptationAttributes.getViewpoint() == null) {
				// not found -> new Factor required
				newViewpoint = QmFactory.eINSTANCE.createFactor();
				newViewpoint.setName(viewpoint);
				addTodoTask(newViewpoint);
				QualityModelUtil.createNewAdaptationID(newViewpoint);
				qualityModel.getFactors().add(newViewpoint);
			}
		}

		/*
		 * QualityFocus attribute - iterate over reference factors to search for the matching factor
		 * If found use this one as QualityFocus, if not found create a new one and add Todo-Entry.
		 */
		if (qualityFocus != null && !qualityFocus.isEmpty()) {
			for (Factor factor : referenceModel.getFactors()) {
				String factorName = factor.getName();
				if (factorName.equals(qualityFocus)) {
					adaptationAttributes.setQualityFocus((Factor) wizardState.getQualityModelElement(factor));
					preselectedElements.add(factor);
					break;
				}
			}
			if (adaptationAttributes.getQualityFocus() == null) {
				// not found -> new Factor required
				newQualityFocus = QmFactory.eINSTANCE.createFactor();
				newQualityFocus.setName(qualityFocus);
				addTodoTask(newQualityFocus);
				QualityModelUtil.createNewAdaptationID(newQualityFocus);
				qualityModel.getFactors().add(newQualityFocus);
			}
		}

		qualityModel.getAdvancedAnnotations().add(adaptationAttributes);
		
		/*
		 * Handle context
		 */
		// Copy the list so that it can be used for processing (processed elements can be removed)
		List<String> context = new LinkedList<String>();
		context.addAll(attributesComposite.getAttributeContext());
		
		// Search for tags that are within the context
		for (Tag referenceTag : referenceModel.getTags()) {			
			String tagName = referenceTag.getName();
			Tag tag = (Tag) wizardState.getQualityModelElement(referenceTag);
			if (context.contains(tagName)) {
				if (!referenceModel.getTaggedBy().contains(referenceTag)) {
					qualityModel.getTaggedBy().add(tag);					
					tagsToRemoveFromQualityModelUponUndo.add(tag);
				}	
				preselectedElements.add(referenceTag);			
				context.remove(tagName);
			} 
		}
		
		// All remaining elements in context are tags that must be created
		for (String contextItem : context) {
			Tag newTag = QmFactory.eINSTANCE.createTag();
			newTag.setName(contextItem);
			qualityModel.getTags().add(newTag);
			qualityModel.getTaggedBy().add(newTag);
			tagsToDeleteUponUndo.add(newTag);
		}
		
		/*
		 * Handle dependent elements
		 */
		if (adaptationAttributes.getQualityFocus() != null) {
			// Special treatment for viewpoint
			preselectedElements.remove(referenceViewpoint);
			wizardState.selectWithoutPreselection(referenceViewpoint);
			wizardState.select(preselectedElements);
			preselectedElements.add(referenceViewpoint);
		} else {
			wizardState.select(preselectedElements);
		}		
	}

	/**
	 * Adds a task for the user to the passed element.
	 * @param element
	 */
	private void addTodoTask(AnnotatedElement element) {
		AdaptationTask task = StatusFactory.eINSTANCE.createAdaptationTask();
		task.setUserMessage("Please have a look at this automatically generated dummy object!");
		task.setAffectedElementAdaptationId(QualityModelUtil.getAdaptationID(element));
		UserMarkedCompleted criterion = StatusFactory.eINSTANCE.createUserMarkedCompleted();
		criterion.setHasMarkedCompleted(false);
		criterion.setLastUpdate(new Date());
		task.getFulfillmentCriteria().add(criterion);

		element.getAdvancedAnnotations().add(task);
		tasksToDelete.add(task);
	}

	/** Checks if the command can be undone, if yes do so, then executes it. */
	@Override
	public void redo() {
		optimizeRedo();		
		if (canUndo()) {
			undo();
		}
		if (canExecute()) {
			execute();
		}		
	}	
	
	private void optimizeRedo() {
		// hacky optimization of redo
		String object = attributesComposite.getAttributeObject();
		String viewpoint = attributesComposite.getAttributeViewpoint();
		String qualityFocus = attributesComposite.getAttributeQualityFocus();
		for (Iterator<AnnotatedElement> iterator = preselectedElements.iterator(); iterator.hasNext();) {
			AnnotatedElement element = (AnnotatedElement) iterator.next();
			if (element instanceof Entity) {
				if (((Entity) element).getName().equals(object)) {
					iterator.remove();
				}
			} else if (element instanceof Factor) {
				String name = ((Factor) element).getName();
				if (name.equals(viewpoint) || name.equals(qualityFocus)) {
					iterator.remove();
				}
			}
		}
	}
	
	/** Returns true if the command needs to be undone. */
	@Override
	public boolean canUndo() {
		return !prepare();
	}

	/**
	 * Undoes all the changes.
	 */
	@Override
	public void undo() {
		QualityModel qualityModel = wizardState.getQualityModel();
		wizardState.unselect(preselectedElements);
		
		qualityModel.setName(oldName);
		qualityModel.setDescription(oldDescription);		
		EcoreUtil.delete(adaptationAttributes);
		if (newEntity != null) {
			EcoreUtil.delete(newEntity);
		}
		if (newViewpoint != null) {
			EcoreUtil.delete(newViewpoint);
		}
		if (newQualityFocus != null) {
			EcoreUtil.delete(newQualityFocus);
		}
		for (AdaptationTask task : tasksToDelete) {
			EcoreUtil.delete(task);
		}
		qualityModel.getTaggedBy().removeAll(tagsToRemoveFromQualityModelUponUndo);
		for (Tag tagToDelete : tagsToDeleteUponUndo) {
			EcoreUtil.delete(tagToDelete);
		}
		oldName = null;
		oldDescription = null;
		adaptationAttributes = null;
		tagsToRemoveFromQualityModelUponUndo.clear();
		tagsToDeleteUponUndo.clear();
		preselectedElements.clear();
	}
	
}
