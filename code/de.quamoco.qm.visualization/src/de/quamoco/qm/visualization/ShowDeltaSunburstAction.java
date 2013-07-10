/*-------------------------------------------------------------------------+
|                                                                          |
| Fraunhofer-Institut fuer Experimentelles Software Engineering (IESE)     |
|                                                                          |
|                                                                          |
|   NOT TO BE PUBLISHED UNDER QUAMOCO LICENSE                              |
|                                                                          |
+-------------------------------------------------------------------------*/

package de.quamoco.qm.visualization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.conqat.ide.commons.ui.logging.LoggingUtils;
import org.conqat.ide.commons.ui.selection.SelectionUtils;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.editor.CustomQmEditor;
import de.quamoco.qm.presentation.QmEditorPlugin;
import edu.tum.cs.emf.commons.resources.UUIDUtils;

/**
 * Shows a delta sunburst
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
public class ShowDeltaSunburstAction implements IObjectActionDelegate
{

	private CustomQmEditor editor;

	private List<QualityModelResult> results;

	/** {@inheritDoc} */
	@Override
	public void run(IAction action)
	{
		if (results.size() != 2)
		{
			MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Message",
			        "You have to select exactly two results.");
			return;
		}
		try
		{
			DeltaSunburstView view = (DeltaSunburstView) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
			        .getActivePage().showView(DeltaSunburstView.ID, getId(), IWorkbenchPage.VIEW_ACTIVATE);
			// /////////////// //

			view.setInput(results, editor);
		}
		catch (PartInitException e)
		{
			LoggingUtils.error(QmEditorPlugin.getPlugin(), "Result view could not be opened", e);
		}

	}

	private String getId()
	{
		List<String> ids = new ArrayList<String>();
		for (QualityModelResult result : results)
		{
			ids.add(UUIDUtils.getId(result));
		}
		Collections.sort(ids);
		String result = "";
		for (String id : ids)
		{
			result += id;
		}
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public void selectionChanged(IAction action, ISelection selection)
	{
		results = SelectionUtils.checkAndSelectAll(selection, QualityModelResult.class);
	}

	/** {@inheritDoc} */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart)
	{
		editor = (CustomQmEditor) targetPart;
	}
}