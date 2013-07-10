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

package de.quamoco.qm.search.engine;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.search.core.engine.IModelSearchQuery;
import org.eclipse.emf.search.core.eval.IModelSearchQueryEvaluator;
import org.eclipse.emf.search.core.parameters.IModelSearchQueryParameters;
import org.eclipse.emf.search.core.results.IModelResultEntry;
import org.eclipse.emf.search.ecore.engine.EcoreMetaModelIntrospector;
import org.eclipse.emf.search.ecore.engine.EcoreModelSearchQuery;
import org.eclipse.emf.search.ecore.results.EcoreModelSearchResultEntry;
import org.eclipse.emf.search.ecore.utils.EcoreModelLoaderUtil;


import de.quamoco.qm.QmPackage;
import de.quamoco.qm.search.Activator;
import de.quamoco.qm.search.evaluators.ModelTextualModelSearchQueryEvaluator;
import de.quamoco.qm.search.l10n.Messages;

/**
 * Gather all model search settings to run a specific query.
 * TODO: the name of the class and its file should include modelName
 * 
 */
public final class ModelSearchQuery extends EcoreModelSearchQuery {
	public final static String Qm_MODEL_SEARCH_IMAGE_PATH = "icons/esearch.gif"; //$NON-NLS-1$
	public final static String Qm_MODEL_SEARCH_RESULT_IMAGE_PATH = "icons/esearch.gif"; //$NON-NLS-1$

	final static class QmSupportedElements {
		private static List<EClassifier> getQmEClassifiersLiterals() {
			List<EClassifier> eclassifiersLiteralsList = new ArrayList<EClassifier>();

			try {

				for (Object o : QmPackage.eINSTANCE.getEClassifiers()) {
					if (o instanceof ENamedElement) {
						eclassifiersLiteralsList.add((EClassifier) o);
					}
				}

			} catch (Throwable t) {
				Activator
						.getDefault()
						.getLog()
						.log(
								new Status(
										IStatus.ERROR,
										Activator.PLUGIN_ID,
										0,
										Messages
												.getString("ModelSearchQuery.QmClassifiersWalkErrorMessage"), t)); //$NON-NLS-1$
			}

			return eclassifiersLiteralsList;
		}

		public static List<EClassifier> getSupportedElements(
				List<? extends Object> participantEClassList) {
			ArrayList<EClassifier> definitiveMetaElementParticipantList = new ArrayList<EClassifier>();
			for (EClassifier eClass : getQmEClassifiersLiterals()) {
				if (participantEClassList.contains(eClass)) {
					definitiveMetaElementParticipantList.add(eClass);
				}
			}
			return definitiveMetaElementParticipantList;
		}
	}

	public ModelSearchQuery(String expr, IModelSearchQueryParameters parameters) {
		super(expr, parameters);
	}

	@Override
	public String getQueryImagePath() {
		return Qm_MODEL_SEARCH_IMAGE_PATH;
	}

	@Override
	public String getResultImagePath() {
		return Qm_MODEL_SEARCH_RESULT_IMAGE_PATH;
	}

	@Override
	public String getBundleSymbolicName() {
		return Activator.getDefault().getBundle().getSymbolicName();
	}

	@Override
	public IStatus search(Object resource, boolean notify) {
		try {
			EObject root = EcoreModelLoaderUtil.openFile(resource, false);

			validParticipantMetaElements = EcoreMetaModelIntrospector
					.discriminateValidMetaElements((EObject) root,
							QmSupportedElements
									.getSupportedElements(participantElements));

			((IModelSearchQueryEvaluator<IModelSearchQuery, Object>) getEvaluator())
					.eval(this, resource, notify);
		} catch (Exception e) {
			return Status.CANCEL_STATUS;
		}
		return Status.OK_STATUS;
	}

	public IStatus search(Object resource, boolean notify,
			IProgressMonitor monitor) {
		try {
			if (monitor.isCanceled()) {
				return Status.CANCEL_STATUS;
			}

			EObject root = EcoreModelLoaderUtil.openFile(resource, false);

			if (monitor.isCanceled()) {
				return Status.CANCEL_STATUS;
			}

			validParticipantMetaElements = EcoreMetaModelIntrospector
					.discriminateValidMetaElements((EObject) root,
							QmSupportedElements
									.getSupportedElements(participantElements));

			if (monitor.isCanceled()) {
				return Status.CANCEL_STATUS;
			}

			((IModelSearchQueryEvaluator<IModelSearchQuery, Object>) getEvaluator())
					.eval(this, resource, notify);

			monitor.setTaskName(getLabel());

			if (monitor.isCanceled()) {
				return Status.CANCEL_STATUS;
			}
		} catch (Exception e) {
			return Status.CANCEL_STATUS;
		}
		return Status.OK_STATUS;
	}

	// Recursively build EObject containment hierarchy
	private IModelResultEntry buildSearchResultEntryHierarchy(
			IModelResultEntry intermediate, Object resource, EObject current,
			EObject leaf) {
		if (current instanceof EObject) {
			IModelResultEntry entryContainer = new EcoreModelSearchResultEntry(
					null, resource, current, false);
			entryContainer.addChildren(intermediate);
			intermediate.setParent(entryContainer);
			return buildSearchResultEntryHierarchy(entryContainer, resource,
					current.eContainer(), leaf);
		} else {
			return intermediate;
		}
	}

	@Override
	public IModelSearchQueryEvaluator<IModelSearchQuery, ?> getEvaluator() {
		evaluator = getModelSearchParameters().getEvaluator();
		//TODO: get the name of evalutar's class compound of modelName and string "TextualModelSearchQueryEvaluator"
		return evaluator != null ? evaluator
				: (evaluator = new ModelTextualModelSearchQueryEvaluator<IModelSearchQuery, Object>());
	}

	@Override
	public String getName() {
		switch (getKind()) {
		case NORMAL_TEXT:
			return Messages.getString("QmModelSearchQuery.NormalTextMessage"); //$NON-NLS-1$
		case CASE_SENSITIVE:
			return Messages
					.getString("QmModelSearchQuery.CaseSensitiveMessage"); //$NON-NLS-1$
		case REGULAR_EXPRESSION:
			return Messages
					.getString("QmModelSearchQuery.RegularExpressionMessage"); //$NON-NLS-1$
		}

		return Messages
				.getString("QmModelSearchQuery.DefaultSearchKindMessage"); //$NON-NLS-1$
	}
}
