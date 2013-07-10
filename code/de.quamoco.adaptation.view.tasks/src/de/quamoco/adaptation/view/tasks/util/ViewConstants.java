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

package de.quamoco.adaptation.view.tasks.util;

/**
 * Defines constants for the view
 * @author Franz Becker
 */
public final class ViewConstants {

	/**
	 * Defines an enumeration for the table columns.
	 */
	public enum TableColumns {
		COMPLETED(0, "", "Indicates whether the task has been completed or not", 61),		
		MESSAGE(1, "Task", "A description of the task", 300),
		NAME(2, "Element Name", "The name of the element affected by this task", 100),
		CLASS(3, "Element Class", "The class / type of the element affected by this task", 100);
		
		/** Indicates that sorting shall not be applied by default. */
		public static final int NO_DEFAULT_SORTING = -1;
		
		/** The column based on which shall be sorted by default.
		 *  When there shall be no default sorting set to {@link #NO_DEFAULT_SORTING} */
		public static final int DEFAULT_SORT_COLUMN = 1;		
		
		/*
		 * public variables for convenience reasons,
		 * OK here since enum is used to manage actual constants.
		 */
		public final int INDEX;
		public final String TITLE;
		public final String TOOLTIP;
		public final int WIDTH;
		
		// TODO some more comments
		private TableColumns(int index, String title, String tooltip, int width) {
			this.INDEX = index;
			this.TITLE = title;
			this.TOOLTIP = tooltip;
			this.WIDTH = width;
		}
		
		public static String[] getTitles() {
			String[] titles = new String[4];
			titles[TableColumns.COMPLETED.INDEX] = TableColumns.COMPLETED.TITLE;
			titles[TableColumns.NAME.INDEX] = TableColumns.NAME.TITLE;
			titles[TableColumns.MESSAGE.INDEX] = TableColumns.MESSAGE.TITLE;
			titles[TableColumns.CLASS.INDEX] = TableColumns.CLASS.TITLE;
			return titles;
		}

		public static String[] getToolTips() {
			String[] toolTips = new String[4];
			toolTips[TableColumns.COMPLETED.INDEX] = TableColumns.COMPLETED.TOOLTIP;
			toolTips[TableColumns.NAME.INDEX] = TableColumns.NAME.TOOLTIP;
			toolTips[TableColumns.MESSAGE.INDEX] = TableColumns.MESSAGE.TOOLTIP;
			toolTips[TableColumns.CLASS.INDEX] = TableColumns.CLASS.TOOLTIP;
			return toolTips;
		}

		public static int[] getBounds() {
			int[] bounds = new int[4];
			bounds[TableColumns.COMPLETED.INDEX] = TableColumns.COMPLETED.WIDTH;
			bounds[TableColumns.NAME.INDEX] = TableColumns.NAME.WIDTH;
			bounds[TableColumns.MESSAGE.INDEX] = TableColumns.MESSAGE.WIDTH;
			bounds[TableColumns.CLASS.INDEX] = TableColumns.CLASS.WIDTH;
			return bounds;
		}

	}
	
	/** Prevent instantiation */
	private ViewConstants() {}
}
