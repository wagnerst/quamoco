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

package de.quamoco.qm.application;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.ActionSetRegistry;
import org.eclipse.ui.internal.registry.IActionSetDescriptor;

import de.quamoco.qm.presentation.QmEditorPlugin;

/**
 * RCP's action bar advisor
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @ConQAT.Rating YELLOW Hash: E1BB2EB84DA66A273C262625B6331854
 */
@SuppressWarnings("restriction")
public class QmWindowActionBarAdvisor extends ActionBarAdvisor {

	/**
	 * Constructor
	 */
	public QmWindowActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		IWorkbenchWindow window = getActionBarConfigurer()
				.getWindowConfigurer().getWindow();
		menuBar.add(createFileMenu(window));
		menuBar.add(createEditMenu(window));
		menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		menuBar.add(createWindowMenu(window));
		menuBar.add(createHelpMenu(window));
	}

	/**
	 * Creates the 'File' menu.
	 */
	protected IMenuManager createFileMenu(IWorkbenchWindow window) {
		IMenuManager menu = new MenuManager(getString("_UI_Menu_File_label"),
				IWorkbenchActionConstants.M_FILE);
		menu.add(new GroupMarker(IWorkbenchActionConstants.FILE_START));

		IMenuManager newMenu = new MenuManager(getString("_UI_Menu_New_label"),
				"new");
		newMenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		// added
		newMenu
				.add(ContributionItemFactory.NEW_WIZARD_SHORTLIST
						.create(window));

		menu.add(newMenu);
		menu.add(new Separator());
		menu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		menu.add(new Separator());
		addToMenuAndRegister(menu, ActionFactory.CLOSE.create(window));
		addToMenuAndRegister(menu, ActionFactory.CLOSE_ALL.create(window));
		menu.add(new Separator());
		addToMenuAndRegister(menu, ActionFactory.SAVE.create(window));
		addToMenuAndRegister(menu, ActionFactory.SAVE_AS.create(window));
		addToMenuAndRegister(menu, ActionFactory.SAVE_ALL.create(window));
		menu.add(new Separator());
		addToMenuAndRegister(menu, ActionFactory.IMPORT.create(window));
		addToMenuAndRegister(menu, ActionFactory.EXPORT.create(window));
		menu.add(new Separator());
		addToMenuAndRegister(menu, ActionFactory.QUIT.create(window));
		menu.add(new GroupMarker(IWorkbenchActionConstants.FILE_END));
		return menu;
	}

	/**
	 * Creates the 'Edit' menu.
	 */
	protected IMenuManager createEditMenu(IWorkbenchWindow window) {
		IMenuManager menu = new MenuManager(getString("_UI_Menu_Edit_label"),
				IWorkbenchActionConstants.M_EDIT);
		menu.add(new GroupMarker(IWorkbenchActionConstants.EDIT_START));

		addToMenuAndRegister(menu, ActionFactory.UNDO.create(window));
		addToMenuAndRegister(menu, ActionFactory.REDO.create(window));
		menu.add(new GroupMarker(IWorkbenchActionConstants.UNDO_EXT));
		menu.add(new Separator());

		addToMenuAndRegister(menu, ActionFactory.CUT.create(window));
		addToMenuAndRegister(menu, ActionFactory.COPY.create(window));
		addToMenuAndRegister(menu, ActionFactory.PASTE.create(window));
		menu.add(new GroupMarker(IWorkbenchActionConstants.CUT_EXT));
		menu.add(new Separator());

		addToMenuAndRegister(menu, ActionFactory.DELETE.create(window));
		addToMenuAndRegister(menu, ActionFactory.SELECT_ALL.create(window));
		menu.add(new Separator());

		addToMenuAndRegister(menu, ActionFactory.FIND.create(window));

		menu.add(new GroupMarker(IWorkbenchActionConstants.ADD_EXT));

		menu.add(new GroupMarker(IWorkbenchActionConstants.EDIT_END));
		menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		return menu;
	}

	/**
	 * Creates the 'Window' menu.
	 */
	protected IMenuManager createWindowMenu(IWorkbenchWindow window) {
		IMenuManager menu = new MenuManager(getString("_UI_Menu_Window_label"),
				IWorkbenchActionConstants.M_WINDOW);

		addToMenuAndRegister(menu, ActionFactory.OPEN_NEW_WINDOW.create(window));
		menu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		menu.add(ContributionItemFactory.OPEN_WINDOWS.create(window));

		// show view menu
		MenuManager showView = new MenuManager("Show View", "showView");
		showView.add(ContributionItemFactory.VIEWS_SHORTLIST.create(window));
		menu.add(showView);

		menu.add(ActionFactory.PREFERENCES.create(window));

		// suppress unnecessary actions
		removeActionSet("org.eclipse.ui.edit.text.actionSet.annotationNavigation");
		removeActionSet("org.eclipse.ui.edit.text.actionSet.navigation");
		removeActionSet("org.eclipse.ui.edit.text.actionSet.convertLineDelimitersTo");
		removeActionSet("org.eclipse.ui.actionSet.openFiles");
		removeActionSet("org.conqat.ide.core.model.modelActions");

		return menu;
	}

	/**
	 * Remove an action set from the action registry
	 */
	private void removeActionSet(String actionSetId) {
		ActionSetRegistry reg = WorkbenchPlugin.getDefault()
				.getActionSetRegistry();
		IActionSetDescriptor[] actionSets = reg.getActionSets();
		// removing annoying gotoLastPosition Message.
		for (int i = 0; i < actionSets.length; i++) {
			if (!actionSets[i].getId().equals(actionSetId))
				continue;
			IExtension ext = actionSets[i].getConfigurationElement()
					.getDeclaringExtension();
			reg.removeExtension(ext, new Object[] { actionSets[i] });
		}
	}

	/**
	 * Creates the 'Help' menu.
	 */
	protected IMenuManager createHelpMenu(
			@SuppressWarnings("unused") IWorkbenchWindow window) {
		IMenuManager menu = new MenuManager(getString("_UI_Menu_Help_label"),
				IWorkbenchActionConstants.M_HELP);
		// Welcome or intro page would go here
		// Help contents would go here
		// Tips and tricks page would go here
		menu.add(new GroupMarker(IWorkbenchActionConstants.HELP_START));
		menu.add(new GroupMarker(IWorkbenchActionConstants.HELP_END));
		menu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		return menu;
	}

	/**
	 * Adds the specified action to the given menu and also registers the action
	 * with the action bar configurer, in order to activate its key binding.
	 */
	protected void addToMenuAndRegister(IMenuManager menuManager, IAction action) {
		menuManager.add(action);
		getActionBarConfigurer().registerGlobalAction(action);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		IActionBarConfigurer configurer = getActionBarConfigurer();
		IWorkbenchWindow window = configurer.getWindowConfigurer().getWindow();

		// add marker for additions
		coolBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));

		// navigation history
		IToolBarManager navToolBar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
		navToolBar.add(new Separator(IWorkbenchActionConstants.HISTORY_GROUP));

		IWorkbenchAction forwardHistoryAction = ActionFactory.FORWARD_HISTORY
				.create(window);
		configurer.registerGlobalAction(forwardHistoryAction);
		IWorkbenchAction backwardHistoryAction = ActionFactory.BACKWARD_HISTORY
				.create(window);
		configurer.registerGlobalAction(backwardHistoryAction);

		navToolBar.add(backwardHistoryAction);
		navToolBar.add(forwardHistoryAction);
		coolBar.add(new ToolBarContributionItem(navToolBar,
				IWorkbenchActionConstants.TOOLBAR_NAVIGATE));
	}

	/**
	 * This looks up a string in the plugin's plugin.properties file.
	 */
	private static String getString(String key) {
		return QmEditorPlugin.INSTANCE.getString(key);
	}
}
