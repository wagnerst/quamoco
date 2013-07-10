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

package de.quamoco.adaptation.wizard.util;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.quamoco.adaptation.model.AdaptationElement;
import de.quamoco.adaptation.model.ElementAction;
import de.quamoco.adaptation.model.Wizard;
import de.quamoco.adaptation.wizard.util.elementsselection.provider.TransientRoot;
import de.quamoco.adaptation.wizard.util.preselection.PreselectionDialog;
import de.quamoco.adaptation.wizard.util.preselection.PreselectionRetriever;
import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.QualityModel;

/**
 * Handles the model for the whole wizard, i.e. which elements 
 * are selected and which are not.
 * @author Franz Becker
 * @ConQAT.Rating YELLOW Hash: C29DEB2BCC30BA18E87ACAA1F61FBA3E
 */
public class WizardState {	

	private List<AdaptationElement> adapatationElements;
	private Set<AnnotatedElement> selectedElements; // contains elements of referenceQualityModel
	private Set<AnnotatedElement> unselectedElements; // contains elements of referenceQualityModel	
	private Map<AnnotatedElement, CompoundCommand> selectedElementsActions; // contains elements of referenceQualityModel	
	private Map<AnnotatedElement, CompoundCommand> unselectedElementActions; // contains elements of referenceQualityModel
	private Map<EClass, PreselectionRetriever> preselectionDescriptors;
	
	private QualityModel referenceQualityModel;	
	private QualityModel qualityModel;
	private EditingDomain editingDomain;
	
	private boolean handlesPreselection = false;
	
	/** Maps the elements of {@link #referenceQualityModel} to elements of {@link #qualityModel}. */
	protected Map<AnnotatedElement, AnnotatedElement> originalToCopyMap = new HashMap<AnnotatedElement, AnnotatedElement>();
	
	public WizardState(Wizard wizard, EditingDomain editingDomain) {
		this.editingDomain = editingDomain;
		adapatationElements = wizard.getAdaptationElements();
		selectedElementsActions = new HashMap<AnnotatedElement, CompoundCommand>(NotifyingHashSet.INITIAL_CAPACITY);
		unselectedElementActions = new HashMap<AnnotatedElement, CompoundCommand>(NotifyingHashSet.INITIAL_CAPACITY);
		selectedElements = new NotifyingHashSet(this, selectedElementsActions);
		unselectedElements = new NotifyingHashSet(this, unselectedElementActions);
		
		// Preselection descriptors
		preselectionDescriptors = new HashMap<EClass, PreselectionRetriever>();
		for (AdaptationElement adaptationElement : wizard.getAdaptationElements()) {
			preselectionDescriptors.put(adaptationElement.getElementClass(), new PreselectionRetriever(adaptationElement));
		}
	}
		
	public void setQualityModel(QualityModel referenceQualityModel, QualityModel qualityModel, Map<AnnotatedElement, AnnotatedElement> originalToCopyMap) {
		if (referenceQualityModel != this.referenceQualityModel || qualityModel != this.qualityModel) {
			this.referenceQualityModel = referenceQualityModel;
			this.qualityModel = qualityModel;
			this.originalToCopyMap = originalToCopyMap;
			initializeState();
		}
	}
	
	private void initializeState() {
		/*
		 * Clear the state
		 */
		selectedElements.clear();
		unselectedElements.clear();
		selectedElementsActions.clear();
		unselectedElementActions.clear();
		for (PreselectionRetriever preselectionRetriever : preselectionDescriptors.values()) {
			preselectionRetriever.modelChanged(originalToCopyMap);
		}		
		
		/*
		 * Set the new state
		 */
		// TODO consider initial preselection here
		Collection<AnnotatedElement> allReferencedElements = new LinkedList<AnnotatedElement>();
		for (AdaptationElement adaptationElement : adapatationElements) {
			TransientRoot transientRoot = new TransientRoot(referenceQualityModel, adaptationElement.getReference());
			Collection<AnnotatedElement> referencedElements = transientRoot.getReferencedElements();

			// Initialize actions
			initializeActions(adaptationElement, referencedElements);	
			
			allReferencedElements.addAll(referencedElements);
		}

		// Set all unselected			
		unselectedElements.addAll(allReferencedElements);
	}

	private void initializeActions(AdaptationElement adaptationElement, Collection<AnnotatedElement> referencedElements) {
		List<ElementAction> selectedActions = adaptationElement.getSelectedElementActions();
		List<ElementAction> unselectedActions = adaptationElement.getUnselectedElementActions();
		
		for (AnnotatedElement referencedElement : referencedElements) {
			AnnotatedElement element = originalToCopyMap.get(referencedElement);
			if (selectedActions.size() > 0) {
				selectedElementsActions.put(referencedElement, getCompoundCommand(element, selectedActions));
			}				
			if (unselectedActions.size() > 0) {
				unselectedElementActions.put(referencedElement, getCompoundCommand(element, unselectedActions));
			}				
		}		
	}
	
	protected void executeActionsFor(Set<AnnotatedElement> addedElements, Map<AnnotatedElement, CompoundCommand> actionMap) {
		for (AnnotatedElement addedElement : addedElements) {
			executeActionsFor(addedElement, actionMap);
		}
	}
	
	protected void undoActionsFor(Set<AnnotatedElement> removedElements, Map<AnnotatedElement, CompoundCommand> actionMap) {
		for (AnnotatedElement removedElement : removedElements) {
			undoActionsFor(removedElement, actionMap);
		}
	}
	
	private void executeActionsFor(AnnotatedElement referenceElement, Map<AnnotatedElement, CompoundCommand> actionMap) {
		if (actionMap.containsKey(referenceElement)) {
			CompoundCommand commandToExecute = actionMap.get(referenceElement);
			if (commandToExecute.canExecute()) {
				editingDomain.getCommandStack().execute(commandToExecute);
			} else {
				System.out.println("Error! Can't execute command for " + referenceElement.getQualifiedName());
			}	
		} 
	}
	

	
	private void undoActionsFor(AnnotatedElement referenceElement, Map<AnnotatedElement, CompoundCommand> actionMap) {
		if (actionMap.containsKey(referenceElement)) {
			CompoundCommand commandToUndo = actionMap.get(referenceElement);
			if (commandToUndo.canUndo()) {
				commandToUndo.undo();
			} else {
				System.out.println("Error! Can't undo command for " + referenceElement.getQualifiedName());
			}
		}
	}
	

	
	/**
	 * Creates a {@link CompoundCommand} out of a list of {@link ElementAction}s.
	 * @param element the {@link AnnotatedElement} for which actions shall be performed.
	 * @param actions the list of actions that shall be performed
	 * @return the {@link CompoundCommand} with all the actions.
	 */
	private CompoundCommand getCompoundCommand(AnnotatedElement element, List<ElementAction> actions) {
		CompoundCommand result = new CompoundCommand();
		for (ElementAction action : actions) {
			result.append(action.getCommand(qualityModel, element, editingDomain));
		}
		return result;
	}
	
	/*
	 * Methods to get and modify the state
	 */
	
	public List<AdaptationElement> getAdaptationElements() {
		return adapatationElements;
	}
	
	public QualityModel getQualityModel() {
		return qualityModel;
	}
	
	public QualityModel getReferenceQualityModel() {
		return referenceQualityModel;
	}
	
	public AnnotatedElement getQualityModelElement(AnnotatedElement referenceElement) {
		if (originalToCopyMap.containsKey(referenceElement)) {
			return originalToCopyMap.get(referenceElement);
		}
		return null;
	}

	public synchronized boolean isSelected(Object element) {
		return selectedElements.contains(element);
	}
	
	public synchronized boolean areSelected(Collection<AnnotatedElement> elements) {
		return selectedElements.containsAll(elements);
	}

	public synchronized void select(AnnotatedElement element) {
		boolean somethingUpdated = unselectedElements.remove(element);
		somethingUpdated |= selectedElements.add(element);		
		if (somethingUpdated) {
			handlePreselection(Collections.singleton(element), true);
		}		
	}
	
	public synchronized void select(Collection<AnnotatedElement> elements) {
		boolean somethingUpdated = unselectedElements.removeAll(elements);
		somethingUpdated |= selectedElements.addAll(elements);
		if (somethingUpdated) {
			handlePreselection(elements, true);
		}		
	}
	
	public synchronized void unselect(AnnotatedElement element) {
		boolean somethingUpdated = selectedElements.remove(element);
		somethingUpdated |= unselectedElements.add(element);
		if (somethingUpdated) {
			handlePreselection(Collections.singleton(element), false);
		}		
	}
	
	public synchronized void unselect(Collection<AnnotatedElement> elements) {
		boolean somethingUpdated = selectedElements.removeAll(elements);
		somethingUpdated |= unselectedElements.addAll(elements);
		if (somethingUpdated) {
			handlePreselection(elements, false);
		}		
	}
	
	public synchronized void selectWithoutPreselection(AnnotatedElement element) {
		unselectedElements.remove(element);
		selectedElements.add(element);		
	}
	
	/*
	 * Preselection handling
	 */
	// TODO handle preselection for unselecting elements
	// this would require to check if an element is used by another preselection descriptor!
	private void handlePreselection(Collection<AnnotatedElement> elements, boolean selected) {
		if (handlesPreselection || !selected) { return; }
		
		handlesPreselection = true;
		try {
			/*
			 * Get transitive closure
			 */
			Set<AnnotatedElement> transitiveClosure = new HashSet<AnnotatedElement>();
			for (AnnotatedElement element : elements) {
				transitiveClosure.addAll(getPreselectionFor(element));
			}
			int setSize = 0;
			while (setSize != transitiveClosure.size()) {
				setSize = transitiveClosure.size();
				Set<AnnotatedElement> newElements = new HashSet<AnnotatedElement>();
				for (AnnotatedElement element : transitiveClosure) {
					newElements.addAll(getPreselectionFor(element));
				}
				transitiveClosure.addAll(newElements);
			}
			
			transitiveClosure.removeAll(selected ? selectedElements : unselectedElements); // show only "new" elements
			if (transitiveClosure.size() > 0) {
				// TODO set real parent				
				PreselectionDialog dialog = new PreselectionDialog(null, this, elements, transitiveClosure, selected);
				dialog.open();
			}			
		} finally {
			handlesPreselection = false;
		}
		
	}
	
	private Set<AnnotatedElement> getPreselectionFor(AnnotatedElement element) {
		if (preselectionDescriptors.containsKey(element.eClass())) {
			PreselectionRetriever retriever = preselectionDescriptors.get(element.eClass());
			return retriever.getPreselectionFor(element);
		}
		return Collections.emptySet();
	}
	
}
