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

package de.quamoco.qm.properties;

import java.util.Iterator;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.source.AnnotationModel;
import org.eclipse.jface.text.source.IAnnotationAccess;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;
import org.eclipse.ui.texteditor.AnnotationPreference;
import org.eclipse.ui.texteditor.DefaultMarkerAnnotationAccess;
import org.eclipse.ui.texteditor.MarkerAnnotationPreferences;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;

import edu.tum.cs.emf.commons.sections.FeaturePropertySectionBase;
import edu.tum.cs.emf.commons.sections.PropertySectionConstants;

/**
 * The base class for Text Properties in the Property Section that check the
 * text for spelling errors.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: C034EEE452222D6BF818FBCAFA04A0F5
 */
public abstract class SpellCheckedTextPropertySectionBase extends
		FeaturePropertySectionBase<EObject, EAttribute> {

	/** Defines whether {@link Text} is a text with multiple lines. */
	private final boolean multiline;

	/** The text widget for the element. */
	protected StyledText text;

	/**
	 * The minimum number of displayed lines (in case of multi-line text
	 * fields).
	 */
	private int minLineCount = 3;

	/**
	 * The maximum number of displayed lines (in case of multi-line text
	 * fields).
	 */
	private int maxLineCount = 20;

	/**
	 * The constructor.
	 * 
	 * @param attribute
	 *            the {@link EAttribute} of the element.
	 * @param multiline
	 *            defines whether {@link Text} is a text with multiple lines.
	 */
	public SpellCheckedTextPropertySectionBase(EAttribute attribute,
			boolean multiline) {
		super(attribute);
		this.multiline = multiline;
	}

	/** {@inheritDoc} */
	@Override
	protected void createValue(Composite composite) {
		int style = SWT.BORDER;
		if (multiline) {
			style |= SWT.MULTI | SWT.WRAP | SWT.V_SCROLL;
		} else {
			style |= SWT.SINGLE;
		}
		if (!getFeature().isChangeable()) {
			style |= SWT.READ_ONLY;
		}

		createSpellCheckedText(composite, style);

		final GridData data = GridDataFactory.defaultsFor(text).create();
		data.grabExcessHorizontalSpace = true;
		data.horizontalIndent = ITabbedPropertyConstants.HSPACE;
		data.horizontalAlignment = GridData.FILL;
		if (!multiline) {
			data.heightHint = PropertySectionConstants.STANDARD_BOUND - 4;
		} else {
			text.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					refreshHeight();
				}
			});
		}
		text.setLayoutData(data);
	}

	/** Create the text field that is spell checked. */
	private void createSpellCheckedText(Composite composite, int style) {
		AnnotationModel annotationModel = new AnnotationModel();
		IAnnotationAccess annotationAccess = new DefaultMarkerAnnotationAccess();

		final SourceViewer sourceViewer = new SourceViewer(composite, null,
				null, true, style);
		text = sourceViewer.getTextWidget();
		// text.setIndent(2);

		final SourceViewerDecorationSupport support = new SourceViewerDecorationSupport(
				sourceViewer, null, annotationAccess, EditorsUI
						.getSharedTextColors());

		Iterator<?> e = new MarkerAnnotationPreferences()
				.getAnnotationPreferences().iterator();
		while (e.hasNext())
			support.setAnnotationPreference((AnnotationPreference) e.next());

		support.install(EditorsUI.getPreferenceStore());

		Document document = new Document("");

		// NOTE: Configuration must be applied before the document is set in
		// order for
		// Hyperlink coloring to work. (Presenter needs document object up
		// front)
		sourceViewer.configure(new TextSourceViewerConfiguration(EditorsUI
				.getPreferenceStore()));
		sourceViewer.setDocument(document, annotationModel);
	}

	/** {@inheritDoc} */
	@Override
	public void refresh() {
		super.refresh();

		IObservableValue modelObservable = EMFEditObservables.observeValue(
				getEditingDomain(), getElement(), getFeature());

		ISWTObservableValue viewObservable = SWTObservables.observeText(text,
				SWT.FocusOut);
		getContext().bindValue(viewObservable, modelObservable);

		if (multiline) {
			refreshHeight();
		}
	}

	/** {@inheritDoc} */
	@Override
	public void setFocus() {
		text.setFocus();
	}

	/** Sets the minimum number of displayed lines. */
	protected void setMinLineCount(int minLineCount) {
		this.minLineCount = minLineCount;
	}

	/** Sets the maximum number of displayed lines. */
	protected void setMaxLineCount(int maxLineCount) {
		this.maxLineCount = maxLineCount;
	}

	/** Refresh the height of the text widget. */
	private void refreshHeight() {
		int lines = Math.min(maxLineCount, Math.max(minLineCount, text
				.getLineCount()));
		int newHeight = lines * text.getLineHeight();

		GridData data = (GridData) text.getLayoutData();
		if (data.heightHint != newHeight) {
			data.heightHint = newHeight;
			layout();
		}
	}
}
