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

package de.quamoco.qm.properties.eval.provider;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

import de.quamoco.qm.properties.eval.model.InfluencingOrRefiningElement;

/**
 * Provides a {@link ViewerComparator} for the
 * {@link InfluencingOrRefiningElement}s.<br>
 * First sorts by ranking, then by contribution points and then by name.
 * 
 * @author Franz Becker
 */
public class InfluencingOrRefiningElementComparator extends ViewerComparator {

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		int result = 0;
		ColumnViewer columnViewer = (ColumnViewer) viewer;

		/*
		 * 1st sort by ranking
		 */
		ColumnLabelProvider rankingLabelProvider = (ColumnLabelProvider) columnViewer
				.getLabelProvider(InfluencingOrRefiningElementLabelProvider.COLUMN_RANKING);
		int ranking1 = Integer.parseInt(rankingLabelProvider.getText(e1));
		int ranking2 = Integer.parseInt(rankingLabelProvider.getText(e2));
		result = compareRankings(ranking1, ranking2);

		if (result == 0) {
			/*
			 * 2nd sort by contribution points
			 */
			ColumnLabelProvider contributionPointsLabelProvider = (ColumnLabelProvider) columnViewer
					.getLabelProvider(InfluencingOrRefiningElementLabelProvider.COLUMN_CONTRIBUTION_POINTS);
			double contPoints1 = Double
					.parseDouble(contributionPointsLabelProvider.getText(e1));
			double contPoints2 = Double
					.parseDouble(contributionPointsLabelProvider.getText(e2));
			result = compareContributionPoints(contPoints1, contPoints2);

			if (result == 0) {
				/*
				 * 3rd sort by name
				 */
				ColumnLabelProvider nameLabelProvider = (ColumnLabelProvider) columnViewer
						.getLabelProvider(InfluencingOrRefiningElementLabelProvider.COLUMN_NAME);
				String name1 = nameLabelProvider.getText(e1);
				String name2 = nameLabelProvider.getText(e2);
				result = compareName(name1, name2);
			}
		}

		return result;
	}

	@Override
	public boolean isSorterProperty(Object element, String property) {
		return true;
	}

	private int compareName(String name1, String name2) {
		// The one with the lesser value should appear first
		return name1.compareTo(name2);
	}

	private int compareContributionPoints(Double contPoints1, Double contPoints2) {
		// The one with the greater value should appear first
		return contPoints2.compareTo(contPoints1);
	}

	private int compareRankings(Integer ranking1, Integer ranking2) {
		if (ranking1 < 0 && ranking2 < 0) {
			return 0; // both are negative => same level
		} else if (ranking1 < 0 && ranking2 >= 0) {
			return 1; // ranking2 should appear first
		} else if (ranking1 >= 0 && ranking2 < 0) {
			return -1; // ranking1 should appear first
		} else if (ranking1 == 0 && ranking2 > 0) {
			return 1; // ranking2 should appear first
		} else if (ranking1 > 0 && ranking2 == 0) {
			return -1; // ranking1 should appear first
		} else {
			return ranking1.compareTo(ranking2); // both are positive, use
													// standard compare
		}
	}

}
