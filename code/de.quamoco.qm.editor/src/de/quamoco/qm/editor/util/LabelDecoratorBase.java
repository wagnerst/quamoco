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

package de.quamoco.qm.editor.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * Base class for {@link ILabelDecorator} that provides implementation for
 * registering and notifying listeners.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 958D4F47DBBDB2D8A7B3B5B45738ACEC
 */
public abstract class LabelDecoratorBase implements ILabelDecorator {

	/** The listeners that listen to this decorator. */
	private List<ILabelProviderListener> listeners;

	/** {@inheritDoc} */
	@Override
	public void addListener(ILabelProviderListener listener) {
		if (listeners == null) {
			listeners = new ArrayList<ILabelProviderListener>();
			firstListenerAdded();
		}
		listeners.add(listener);
	}

	/**
	 * Template method to notify that the first listener has been added to the
	 * decorator.
	 */
	protected void firstListenerAdded() {
		// to be overwritten by subclasses
	}

	/** {@inheritDoc} */
	@Override
	public void removeListener(ILabelProviderListener listener) {
		listeners.remove(listener);
		if (listeners.isEmpty()) {
			listeners = null;
			lastListenerRemoved();
		}
	}

	/**
	 * Template method to notify that the last listener has been removed from
	 * the decorator.
	 */
	protected void lastListenerRemoved() {
		// to be overwritten by subclasses
	}

	/** Notify the listeners about changes to certain elements. */
	protected void notifyListeners(Object[] elements) {
		if (listeners == null) {
			return;
		}
		if (elements == null || elements.length == 0) {
			return;
		}
		Display display = Display.getDefault();
		if (display == null || display.isDisposed()) {
			return;
		}
		final LabelProviderChangedEvent event = new LabelProviderChangedEvent(
				this, elements);
		for (final ILabelProviderListener listener : listeners) {
			display.asyncExec(new Runnable() {
				@Override
				public void run() {
					listener.labelProviderChanged(event);
				}
			});
		}
	}

	/** {@inheritDoc} */
	@Override
	public Image decorateImage(Image image, Object element) {
		// default implementation so that sub classes do not need to overwrite
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String decorateText(String text, Object element) {
		// default implementation so that sub classes do not need to overwrite
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public void dispose() {
		// default implementation so that sub classes do not need to overwrite
	}

	/** {@inheritDoc} */
	@Override
	public boolean isLabelProperty(Object element, String property) {
		// default implementation so that sub classes do not need to overwrite
		return false;
	}
}
