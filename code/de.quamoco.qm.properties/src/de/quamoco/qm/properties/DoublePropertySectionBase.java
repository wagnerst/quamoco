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

/*-------------------------------------------------------------------------+
|                                                                          |
| Copyright 2005-2011 The ConQAT Project                                   |
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
+-------------------------------------------------------------------------*/
package de.quamoco.qm.properties;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;

import edu.tum.cs.emf.commons.sections.FeaturePropertySectionBase;
import edu.tum.cs.emf.commons.sections.PropertySectionConstants;

/**
 * The base class for Text Properties in the Property Section.
 * 
 * @author niessner
 * @author $Author: deissenb $
 * @version $Rev: 34252 $
 * @levd.rating YELLOW Hash: 53BEA9A0EF8816D144ACC3EB4C495CC9
 */
public abstract class DoublePropertySectionBase extends
		FeaturePropertySectionBase<EObject, EAttribute> {

	/** Defines whether {@link Text} is a text with multiple lines. */
	private final boolean multiline;

	/** The text widget for the element. */
	protected Text text;

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
	public DoublePropertySectionBase(EAttribute attribute, boolean multiline) {
		super(attribute);
		this.multiline = multiline;
	}

	/** {@inheritDoc} */
	@Override
	protected void createValue(Composite composite) {
		int style = SWT.NONE;
		if (multiline) {
			style |= SWT.MULTI | SWT.WRAP | SWT.V_SCROLL;
		}
		if (!getFeature().isChangeable()) {
			style |= SWT.READ_ONLY;
		}

		text = getWidgetFactory().createText(composite, "", style); //$NON-NLS-1$
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
	
	private class StringToDoubleConverter extends UpdateValueStrategy {
		public Object getFromType() {
			return String.class;
		}

		public Object getToType() {
			return Double.class;
		}

		@Override
		public Object convert(Object toObject) {
			return new Double((String) toObject);
		}

	}

	private class DoubleToStringConverter extends UpdateValueStrategy {
		public Object getFromType() {
			return Double.class;
		}

		public Object getToType() {
			return String.class;
		}

		@Override
		public Object convert(Object toObject) {
			return String.valueOf(toObject);
		}

	}

	/** {@inheritDoc} */
	@Override
	public void refresh() {
		super.refresh();

		IObservableValue modelObservable = EMFEditObservables.observeValue(
				getEditingDomain(), getElement(), getFeature());
		
		ISWTObservableValue viewObservable = SWTObservables.observeText(text,
				SWT.FocusOut);
		getContext().bindValue(viewObservable, modelObservable,
				new StringToDoubleConverter(), new DoubleToStringConverter());

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