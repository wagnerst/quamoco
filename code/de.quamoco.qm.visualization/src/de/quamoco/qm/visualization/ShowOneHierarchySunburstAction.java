/*-------------------------------------------------------------------------+
|                                                                          |
| Fraunhofer-Institut fuer Experimentelles Software Engineering (IESE)     |
|                                                                          |
|                                                                          |
|   NOT TO BE PUBLISHED UNDER QUAMOCO LICENSE                              |
|                                                                          |
+-------------------------------------------------------------------------*/

package de.quamoco.qm.visualization;

import org.conqat.ide.commons.ui.logging.LoggingUtils;
import org.conqat.ide.commons.ui.selection.SelectionUtils;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.editor.CustomQmEditor;
import de.quamoco.qm.presentation.QmEditorPlugin;
import edu.tum.cs.emf.commons.resources.UUIDUtils;

public class ShowOneHierarchySunburstAction implements IObjectActionDelegate {

	private CustomQmEditor editor;

	private QualityModelResult result;

	/** {@inheritDoc} */
	@Override
	public void run(IAction action) {
		try {
			OneHierarchySunburstView view = (OneHierarchySunburstView) PlatformUI
					.getWorkbench()
					.getActiveWorkbenchWindow()
					.getActivePage()
					.showView(OneHierarchySunburstView.ID,
							UUIDUtils.getId(result),
							IWorkbenchPage.VIEW_ACTIVATE);
			view.setInput(result, editor);
		} catch (PartInitException e) {
			LoggingUtils.error(QmEditorPlugin.getPlugin(),
					"Result view could not be opened", e);
		}

	}

	/** {@inheritDoc} */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		result = SelectionUtils.checkAndPickFirst(selection,
				QualityModelResult.class);
	}

	/** {@inheritDoc} */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		editor = (CustomQmEditor) targetPart;
	}
}
