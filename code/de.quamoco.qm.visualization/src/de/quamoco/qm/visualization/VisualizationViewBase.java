/*-------------------------------------------------------------------------+
|                                                                          |
| Fraunhofer-Institut fuer Experimentelles Software Engineering (IESE)     |
|                                                                          |
|                                                                          |
|   NOT TO BE PUBLISHED UNDER QUAMOCO LICENSE                              |
|                                                                          |
+-------------------------------------------------------------------------*/

package de.quamoco.qm.visualization;

import java.util.Arrays;

import org.eclipse.emf.ecore.EObject;

import de.quamoco.qm.editor.CustomQmEditor;
import edu.tum.cs.emf.commons.views.EditorSpecificViewBase;

/**
 * Base class for views that visualize elements.
 * 
 * @author herrmama
 * @author $Author: herrmama $
 * @version $Rev: 4817 $
 * @levd.rating RED Rev:
 */
public abstract class VisualizationViewBase<E> extends
		EditorSpecificViewBase<CustomQmEditor> {

	/** Element. */
	private E element;

	/** Get the element. */
	protected E getElement() {
		return element;
	}

	/** Set the input element. */
	public final void setInput(E element, CustomQmEditor editor) {
		this.element = element;
		setEditor(editor);
		inputChanged(element);
	}

	/** The input element has changed (to be implemented by subclasses. */
	protected abstract void inputChanged(E element);

	/** Select an element in the quality model editor. */
	protected void locate(EObject element) {
		getEditor().locate(Arrays.asList(element));
	}
}
