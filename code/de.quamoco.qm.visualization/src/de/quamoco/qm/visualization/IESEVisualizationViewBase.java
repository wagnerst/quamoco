/*-------------------------------------------------------------------------+
|                                                                          |
| Fraunhofer-Institut fuer Experimentelles Software Engineering (IESE)     |
|                                                                          |
|                                                                          |
|   NOT TO BE PUBLISHED UNDER QUAMOCO LICENSE                              |
|                                                                          |
+-------------------------------------------------------------------------*/

package de.quamoco.qm.visualization;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;

import de.quamoco.qm.visualization.swt.integration.EmbeddedSwingComposite;

public abstract class IESEVisualizationViewBase<E> extends
		VisualizationViewBase<E> {

	/** The panel containing the bridged swing visualization view. */
	// protected JPanel panel = null;

	protected Browser text;

	private Composite enclosingSwtComposite;

	private EmbeddedSwingComposite embeddedSwingComposite;

	/** {@inheritDoc} */
	@Override
	public final void createPartControl(Composite parent) {

		SashForm sash = new SashForm(parent, SWT.HORIZONTAL);

		enclosingSwtComposite = new Composite(sash, SWT.None);
		enclosingSwtComposite.setLayout(new FillLayout());

		Group group = new Group(sash, SWT.None);
		group.setText("Information");
		group.setLayout(new FillLayout());

		text = new Browser(group, SWT.None);

		sash.setWeights(new int[] { 2, 1 });

		createActions();
	}

	@Override
	protected final void inputChanged(E element) {
		if (embeddedSwingComposite != null) {
			embeddedSwingComposite.dispose();
		}

		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		embeddedSwingComposite = new EmbeddedSwingComposite(
				enclosingSwtComposite, SWT.NONE) {
			@Override
			protected JComponent createSwingComponent() {
				return panel;
			}
		};
		inputChanged(element, panel);

		embeddedSwingComposite.populate();
		enclosingSwtComposite.layout();
	}

	protected abstract void inputChanged(E element, JPanel panel);

	protected abstract void createActions();

	protected void showTooltip(final String tooltip) {
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				text.setText(tooltip);
			}
		});
	}

	/** {@inheritDoc} */
	@Override
	public void setFocus() {

	}
}
