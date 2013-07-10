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

package de.quamoco.adaptation.view.history.util;

/**
 * Defines constants for the view
 * @author Franz Becker
 */
public final class ViewConstants {

	/**
	 * Defines an enumeration for the table columns.
	 */
	public enum TableColumns {
		ACTION(0, "Performed Action", "Indicated the performed action", 250),
		VALUE(1, "Value", "Describes the value of the action (e.g. the name set", 150),
		ELEMENT(2, "Affected Element", "The element that was affected by the action", 150),
		JUSTIFICATION(3, "Justification", "The justification why this action was performed", 200),
		SOURCE(4, "Source", "Describes where this adaptation action came from", 70),
		TIMESTAMP(5, "Timestamp", "The timestamp describing when the action was performed", 100);
		
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
		
		private TableColumns(int index, String title, String tooltip, int width) {
			this.INDEX = index;
			this.TITLE = title;
			this.TOOLTIP = tooltip;
			this.WIDTH = width;
		}
		
		public static String[] getTitles() {
			String[] titles = new String[6];
			titles[TableColumns.ACTION.INDEX] = TableColumns.ACTION.TITLE;
			titles[TableColumns.VALUE.INDEX] = TableColumns.VALUE.TITLE;
			titles[TableColumns.ELEMENT.INDEX] = TableColumns.ELEMENT.TITLE;
			titles[TableColumns.JUSTIFICATION.INDEX] = TableColumns.JUSTIFICATION.TITLE;
			titles[TableColumns.SOURCE.INDEX] = TableColumns.SOURCE.TITLE;
			titles[TableColumns.TIMESTAMP.INDEX] = TableColumns.TIMESTAMP.TITLE;
			return titles;
		}

		public static String[] getToolTips() {
			String[] toolTips = new String[6];
			toolTips[TableColumns.ACTION.INDEX] = TableColumns.ACTION.TOOLTIP;
			toolTips[TableColumns.VALUE.INDEX] = TableColumns.VALUE.TOOLTIP;
			toolTips[TableColumns.ELEMENT.INDEX] = TableColumns.ELEMENT.TOOLTIP;
			toolTips[TableColumns.JUSTIFICATION.INDEX] = TableColumns.JUSTIFICATION.TOOLTIP;
			toolTips[TableColumns.SOURCE.INDEX] = TableColumns.SOURCE.TOOLTIP;
			toolTips[TableColumns.TIMESTAMP.INDEX] = TableColumns.TIMESTAMP.TOOLTIP;
			return toolTips;
		}

		public static int[] getBounds() {
			int[] bounds = new int[6];
			bounds[TableColumns.ACTION.INDEX] = TableColumns.ACTION.WIDTH;
			bounds[TableColumns.VALUE.INDEX] = TableColumns.VALUE.WIDTH;
			bounds[TableColumns.ELEMENT.INDEX] = TableColumns.ELEMENT.WIDTH;
			bounds[TableColumns.JUSTIFICATION.INDEX] = TableColumns.JUSTIFICATION.WIDTH;
			bounds[TableColumns.SOURCE.INDEX] = TableColumns.SOURCE.WIDTH;
			bounds[TableColumns.TIMESTAMP.INDEX] = TableColumns.TIMESTAMP.WIDTH;
			return bounds;
		}

	}
	
	/** Prevent instantiation */
	private ViewConstants() {}
}
