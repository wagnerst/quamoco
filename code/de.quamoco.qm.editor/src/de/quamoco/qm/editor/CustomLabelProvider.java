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

package de.quamoco.qm.editor;

import java.util.Stack;
import java.util.StringTokenizer;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;

/**
 * Custom label provider that supports to display the label in different colors.
 * 
 * @author herrmama
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating YELLOW Hash: 3EDD8DCBA4F142105C0FA899F2C8C688
 */
public class CustomLabelProvider extends DecoratingLabelProvider implements
		INotifyChangedListener, IBaseLabelProvider, IColorProvider,
		IFontProvider, ILabelProvider, ITableColorProvider, ITableFontProvider,
		ITableLabelProvider,
		DelegatingStyledCellLabelProvider.IStyledLabelProvider {

	/** The color to display normal text. */
	private static final Color NORMAL_COLOR = Display.getDefault()
			.getSystemColor(SWT.COLOR_BLACK);

	/** The color to display text that serves as additional information. */
	private static final Color LIGHT_COLOR = new Color(Display.getDefault(),
			new RGB(149, 125, 71));

	/** The color to display text after the @ symbol. */
	private static final Color AT_COLOR = new Color(Display.getDefault(),
			new RGB(0, 0, 128));

	/** The {@link Styler} to display normal text. */
	private final Styler normal = new Styler() {
		@Override
		public void applyStyles(TextStyle textStyle) {
			textStyle.foreground = NORMAL_COLOR;
		}
	};

	/**
	 * The {@link Styler} to display text that serves as additional information.
	 */
	private final Styler lightStyler = new Styler() {
		@Override
		public void applyStyles(TextStyle textStyle) {
			textStyle.foreground = LIGHT_COLOR;
		}
	};

	/** The {@link Styler} to display text after the @ symbol. */
	private final Styler atStyler = new Styler() {
		@Override
		public void applyStyles(TextStyle textStyle) {
			textStyle.foreground = AT_COLOR;
		}
	};

	/** Constructor. */
	public CustomLabelProvider(ILabelProvider labelProvider) {
		super(labelProvider, null);
	}

	/** {@inheritDoc} */
	@Override
	public void notifyChanged(Notification notification) {
		ILabelProvider lp = getLabelProvider();
		if (lp instanceof INotifyChangedListener) {
			((INotifyChangedListener) lp).notifyChanged(notification);
		}
	}

	/** {@inheritDoc} */
	@Override
	public String getText(Object element) {
		String text = super.getText(element);
		text = (text == null) ? "" : text;
		return text;
	}

	/** {@inheritDoc} */
	@Override
	public Color getBackground(Object element, int columnIndex) {
		ILabelProvider lp = getLabelProvider();
		if (lp instanceof ITableColorProvider) {
			return ((ITableColorProvider) lp).getBackground(element,
					columnIndex);
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public Color getForeground(Object element, int columnIndex) {
		ILabelProvider lp = getLabelProvider();
		if (lp instanceof ITableColorProvider) {
			return ((ITableColorProvider) lp).getForeground(element,
					columnIndex);
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public Font getFont(Object element, int columnIndex) {
		ILabelProvider lp = getLabelProvider();
		if (lp instanceof ITableFontProvider) {
			return ((ITableFontProvider) lp).getFont(element, columnIndex);
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		ILabelProvider lp = getLabelProvider();
		if (lp instanceof ITableLabelProvider) {
			return ((ITableLabelProvider) lp).getColumnImage(element,
					columnIndex);
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String getColumnText(Object element, int columnIndex) {
		ILabelProvider lp = getLabelProvider();
		if (lp instanceof ITableLabelProvider) {
			return ((ITableLabelProvider) lp).getColumnText(element,
					columnIndex);
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public StyledString getStyledText(Object element) {

		Stack<Styler> stack = new Stack<Styler>();
		stack.push(normal);

		StyledString styledString = new StyledString();
		String text = getText(element);

		StringTokenizer tokenizer = new StringTokenizer(text, "[]@=</", true);

		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if ("[".equals(token)) {
				// additional information has to be in square brackets
				stack.push(lightStyler);
				styledString.append(token, stack.peek());
			} else if ("]".equals(token)) {
				styledString.append(token, stack.peek());
				stack.pop();
			} else if ("@".equals(token)) {
				// text after the @ element
				stack.push(atStyler);
				styledString.append(token, stack.peek());
			} else if ("<".equals(token) || "=".equals(token)
					|| "/".equals(token)) {
				// an arrow stops the current style
				if (stack.size() > 1) {
					stack.pop();
				}
				styledString.append(token, stack.peek());
			} else {
				styledString.append(token, stack.peek());
			}
		}

		return styledString;
	}
}
