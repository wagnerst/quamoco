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

package de.quamoco.adaptation.todo.listener;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.quamoco.adaptation.model.FeatureRequiredAction;
import de.quamoco.adaptation.model.status.AdaptationHistoryItem;
import de.quamoco.adaptation.model.status.AdaptationSource;
import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.model.status.StatusFactory;
import de.quamoco.adaptation.model.status.UserMarkedCompleted;
import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.AnnotationBase;
import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.Measure;
import de.quamoco.qm.NamedElement;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.QualityModelElement;
import de.quamoco.qm.Ranking;
import de.quamoco.qm.WeightedSumFactorAggregation;
import de.quamoco.qm.WeightedSumMultiMeasureEvaluation;

public class ContentListener extends EContentAdapter {

	protected EditingDomain editingDomain;
	protected Map<EClass, List<FeatureRequiredAction>> eClassToFeatureRequiredActionMap;

	public ContentListener(EditingDomain editingDomain, Map<EClass, List<FeatureRequiredAction>> eClassToFeatureRequiredActionMap) {
		super();
		this.editingDomain = editingDomain;
		this.eClassToFeatureRequiredActionMap = eClassToFeatureRequiredActionMap;
	}

	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		
		Object notifier = notification.getNotifier();
		if (notifier instanceof AnnotatedElement && notification.getFeature() != QmPackage.Literals.ANNOTATED_ELEMENT__ADVANCED_ANNOTATIONS) {
			AnnotatedElement element = (AnnotatedElement) notifier;
			EClass eClass = element.eClass();
			if (eClassToFeatureRequiredActionMap.containsKey(eClass)) {
				performActions(eClassToFeatureRequiredActionMap.get(eClass), element);
			}
			
			// Custom todos
			if (element instanceof Factor) {
				handleFactor((Factor) element, notification);
			} else if (element instanceof Measure) {
				handleMeasure((Measure) element, notification);
			} else if (element instanceof Evaluation) {
				handleEvaluation((Evaluation) element, notification);
			}
			
			// Adaptation History
			if (notification.getEventType() == Notification.ADD
				|| notification.getEventType() == Notification.SET
				|| notification.getEventType() == Notification.UNSET
				|| notification.getEventType() == Notification.REMOVE) {
				AdaptationHistoryItem historyItem = StatusFactory.eINSTANCE.createAdaptationHistoryItem();
				historyItem.setSource(AdaptationSource.EDITOR);
				historyItem.setTimestamp(new Date());
				setActionNameAndValueOfItem(element, notification, historyItem);
				element.getAdvancedAnnotations().add(historyItem);
			}
			
		}	
	}
	
	protected void setActionNameAndValueOfItem(QualityModelElement element, Notification notification, AdaptationHistoryItem item) {
		String featureName = ((EStructuralFeature) notification.getFeature()).getName();
		switch (notification.getEventType()) {
		case (Notification.ADD):
			item.setPerformedActionName("Added element to " + featureName);
			item.setPerformedActionValue(getValueName(notification.getNewValue()));
			break;
		case (Notification.REMOVE):
			item.setPerformedActionName("Removed element from " + featureName);
			item.setPerformedActionValue(getValueName(notification.getOldValue()));
			break;
		case (Notification.SET):
			item.setPerformedActionName("Set a value for " + featureName);
			item.setPerformedActionValue(getValueName(notification.getNewValue()));
			break;
		case (Notification.UNSET):
			item.setPerformedActionName("Unset the value for " + featureName);
			item.setPerformedActionValue(getValueName(notification.getOldValue()));
		default:
			item.setPerformedActionName("unknown operation");
		}
	}
	
	protected String getValueName(Object value) {
		if (value instanceof NamedElement) {
			return ((NamedElement) value).getName();
		}
		if (value instanceof QualityModelElement) {
			return ((QualityModelElement) value).getQualifiedName();
		}
		return value == null ? "" : value.toString();
	}

	protected void performActions(List<FeatureRequiredAction> actionList, QualityModelElement element) {
		CompoundCommand compoundCommand = new CompoundCommand();
		QualityModel qualityModel = element.getQualityModel();
		for (FeatureRequiredAction action : actionList) {
			Command command = action.getCommand(qualityModel, editingDomain, element);
			compoundCommand.append(command);
		}
		compoundCommand.execute();
	}	

	protected void handleFactor(Factor factor, Notification notification) {
		switch (notification.getFeatureID(Factor.class)) {
		/*
		 * Influences + Refines (ADD / REMOVE)
		 */
		case (QmPackage.FACTOR__INFLUENCES_DIRECT):
		case (QmPackage.FACTOR__REFINES_DIRECT):
			switch (notification.getEventType()) {
			case (Notification.ADD):
				Factor addedFactor = (Factor) notification.getNewValue();
				addTodoEntry(addedFactor, "Check the evaluations of this factor!", "CHECK_FACTOR_EVALUATION");
			break;
			case (Notification.REMOVE):
				Factor removedFactor = (Factor) notification.getOldValue();
				addTodoEntry(removedFactor, "Check the evaluations of this factor!", "CHECK_FACTOR_EVALUATION");
			break;
			}
		break;
		}
	}
	
	protected void handleMeasure(Measure measure, Notification notification) {
		if (notification.getFeature() == QmPackage.Literals.MEASURE__MEASURES_DIRECT) {
			Factor oldFactor = (Factor) notification.getOldValue();
			if (oldFactor != null) {
				addTodoEntry(oldFactor, "Check the evaluations of this factor!", "CHECK_FACTOR_EVALUATION");
			}
		}
	}

	protected void handleEvaluation(Evaluation evaluation, Notification notification) {
		List<? extends Ranking> rankings = null;

		if (evaluation instanceof WeightedSumFactorAggregation) {
			rankings = ((WeightedSumFactorAggregation) evaluation).getRankings();
		} else if (evaluation instanceof WeightedSumMultiMeasureEvaluation) {
			rankings = ((WeightedSumMultiMeasureEvaluation) evaluation).getRankings();
		} else {
			return;
		} 
		// rankings != null
		boolean hasSomeRankingWithZeroWeight = false;
		for (Ranking ranking : rankings) {
			if (ranking.getWeight() == 0) {
				hasSomeRankingWithZeroWeight = true;
				break;
			}
		}
		if (hasSomeRankingWithZeroWeight) {
			// check if todo entry is necessary
			addTodoEntry(evaluation, "Evaluation has an element with zero contribution points!", "CP_EQUALS_0");

		}
	}	

	protected void addTodoEntry(AnnotatedElement element, String userMessage, String id) {
		if (!todoEntryAlreadyExisting(element, id)) {
			AdaptationTask adaptationTask = StatusFactory.eINSTANCE.createAdaptationTask();
			adaptationTask.setAutoDelete(true);
			adaptationTask.setTaskId(id);
			adaptationTask.setUserMessage(userMessage);
			adaptationTask.getFulfillmentCriteria().add(StatusFactory.eINSTANCE.createUserMarkedCompleted());
			// no command necessary in this context
			element.getAdvancedAnnotations().add(adaptationTask);
		}
	}
	
	/**
	 * Determines if there is already an {@link AdaptationTask}<br/>
	 * <b>HAS SIDE EFFECTS!!!</b>
	 */
	protected boolean todoEntryAlreadyExisting(AnnotatedElement element, String id) {
		for (AnnotationBase annotation : element.getAdvancedAnnotations()) {
			if (annotation instanceof AdaptationTask) {
				AdaptationTask task = (AdaptationTask) annotation;
				if (id != null && id.equals(task.getTaskId())) {
					// If found reset marked completed
					if (task.getFulfillmentCriteria().get(0) instanceof UserMarkedCompleted) {
						((UserMarkedCompleted) task.getFulfillmentCriteria().get(0)).setHasMarkedCompleted(false);
					}
					return true;
				}
			}
		}
		return false;
	}

}
