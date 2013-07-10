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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.structuremergeviewer.ICompareInput;
import org.eclipse.emf.compare.FactoryException;
import org.eclipse.emf.compare.diff.engine.GenericDiffEngine;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.match.engine.GenericMatchEngine;
import org.eclipse.emf.compare.match.metamodel.Match2Elements;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.match.metamodel.UnmatchElement;
import org.eclipse.emf.compare.ui.ModelCompareInput;
import org.eclipse.emf.compare.ui.viewer.content.ModelContentMergeViewer;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import de.quamoco.adaptation.model.status.AdaptationStatusItem;
import de.quamoco.adaptation.util.qm.Activator;
import de.quamoco.qm.QualityModel;

/**
 * Subclasses {@link ModelContentMergeViewer} and provides a viewer
 * that compares two passed models. 
 * @author Franz Becker
 */
public class QualityModelCompareViewer extends ModelContentMergeViewer  {

	/** Dummy editing domain, required for dummy resources */
	protected TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain();
	
	/** The options for matching, empty for default settings. */
	Map<String, Object> matchOptions = new HashMap<String, Object>();
	
	/**
	 * Calls super constructor and initializes the labels.
	 * @param parent parent composite
	 * @param leftLabel the label that is displayed above the left model
	 * @param rightLabel the label that is displayed above the right model
	 */
	public QualityModelCompareViewer(Composite parent, String leftLabel, String rightLabel) {
		super(parent, new CompareConfiguration());
		
		// Set left and right label	and images		
		getCompareConfiguration().setLeftLabel(leftLabel);
		getCompareConfiguration().setRightLabel(rightLabel);
		getCompareConfiguration().setLeftImage(Activator.getImageDescriptor("icons/QualityModel.gif").createImage());
		getCompareConfiguration().setRightImage(Activator.getImageDescriptor("icons/QualityModel.gif").createImage());
	}
	
	/**
	 * Sets the input for the viewer.<br>
	 * <b>Important: Make sure that the copiedModel is copied using {@link CompareCopier}!</b><br>
	 * This is necessary cause the comparison is based on the element's IDs that are copied by the
	 * {@link CompareCopier} into its annotations.
	 * @param copiedModel the copied model
	 * @param originalModel the original model
	 */
	public void setInput(QualityModel copiedModel, QualityModel originalModel) {
		// Whole procedure may throw some exceptions, catched here only.
		try {
			// Match and diff engine provided by framework and used for comparison
			GenericMatchEngine matchEngine = new GenericMatchEngine();
			GenericDiffEngine diffEngine = new GenericDiffEngine() {
				
				// TODO temporary solution to prevent bugs
				@Override
				protected void checkForDiffs(DiffGroup current, Match2Elements match) throws FactoryException {
					try {
						// Ignore AdaptationStatusItems
						if (!(match.getLeftElement() instanceof AdaptationStatusItem) && !(match.getRightElement() instanceof AdaptationStatusItem)) {
							super.checkForDiffs(current, match);
						}
					} catch (Exception e) {
						// do nothing
					}
				}
				
				@Override
				protected void processUnmatchedElements(DiffGroup diffRoot, List<UnmatchElement> unmatched) {
					// Remove AdaptationStatusItems
					for (Iterator<UnmatchElement> iterator = unmatched.iterator(); iterator.hasNext(); ) {						
						UnmatchElement unmatchElement = iterator.next();
						if (unmatchElement.getElement() instanceof AdaptationStatusItem) {
							iterator.remove();
						}
					}
					super.processUnmatchedElements(diffRoot, unmatched);
				}
				
			};

			/*
			 * Matching requires the models to have an eResource. Therefore the quality
			 * models are put into dummy resources that are able to retrieve their
			 * element's IDs correctly.
			 */
			new DummyXMIResource(copiedModel, false);
			new DummyXMIResource(originalModel, true);

			// Perform comparison
			MatchModel matchModel = matchEngine.modelMatch(copiedModel, originalModel, matchOptions);
			DiffModel diffModel = diffEngine.doDiff(matchModel);
			
			// Set the view's input
			ICompareInput compareInput = new ModelCompareInput(matchModel, diffModel);
			this.setInput(compareInput);			
						
			// Expand both trees
			expandAll();
			
		} catch (Exception e) {
			// TODO show error message
			e.printStackTrace();
		}
	}

	@Override
	protected void updateToolItems() {
		// do nothing since there are no tool items
	}
	
	/**
	 * Expands the elements of both (left and right) trees.
	 */
	protected void expandAll() {
		Object leftTreeObject = this.leftPart.getTreePart().getControl();
		Object rightTreeObject = this.rightPart.getTreePart().getControl();
		if (leftTreeObject instanceof Tree) {
			expandAll(((Tree) leftTreeObject).getItems());
		}
		if (rightTreeObject instanceof Tree) {
			expandAll(((Tree) rightTreeObject).getItems());
		}		
	}

	/**
	 * Expands the passed {@link TreeItem}s and recursively
	 * their children.
	 * @param items the {@link TreeItem}s to expand.
	 */
	protected void expandAll(TreeItem[] items) {
		for (TreeItem item : items) {
			item.setExpanded(true);
			expandAll(item.getItems());
		}
	}
	
}
