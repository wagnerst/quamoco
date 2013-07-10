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

package de.quamoco.qm.properties.eval.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.Measure;
import de.quamoco.qm.Type;
import de.quamoco.qm.properties.eval.generator.EvaluationGenerator.Scale;
import de.quamoco.qm.properties.eval.model.InfluencingOrRefiningElement;
import de.quamoco.qm.util.QmModelUtils;

public class Util {

	private Util() {
	}

	/**
	 * Utility method that converts from {@link Type} to {@link Scale}.
	 * 
	 * @param measureBase
	 *            the measure of interest
	 * @return the {@link Type} of the passed measure converted into
	 *         {@link Scale}.
	 */
	public static Scale getScaleFromMeasure(Measure measureBase) {
		switch (measureBase.getType()) {
		case FINDINGS:
			return Scale.FINDINGS;
		case NUMBER:
			return Scale.NUMBER;
		default:
			return Scale.UNDEFINED;
		}
	}

	/**
	 * Retrieves a list of suitable {@link Measure} for a given
	 * {@link Evaluation} using the
	 * {@link QmModelUtils#getUsableMeasures(Factor)} and removes all those
	 * which doesn't have a correct {@link Scale}.
	 * 
	 * @param evaluation
	 * @return
	 */
	public static List<Measure> getSuitableMeasures(Evaluation evaluation) {
		List<Measure> results = new LinkedList<Measure>();

		results.addAll(evaluation.getUsableMeasures());

		/*
		 * Remove all measures which doesn't have a valid scale!
		 */
		for (Iterator<Measure> iterator = results.iterator(); iterator
				.hasNext();) {
			Measure measureBase = iterator.next();
			if (Util.getScaleFromMeasure(measureBase).equals(Scale.UNDEFINED)) {
				iterator.remove();
			}
		}

		return results;
	}

	/**
	 * Returns the current data residing in the table
	 * 
	 * @return the current data residing in the table
	 */
	public static Vector<InfluencingOrRefiningElement<?>> getTableData(
			TableViewer tableViewer) {
		Vector<InfluencingOrRefiningElement<?>> data = new Vector<InfluencingOrRefiningElement<?>>();
		int i = 0;
		Object element = null;
		do {
			element = tableViewer.getElementAt(i);
			if (element instanceof InfluencingOrRefiningElement<?>) {
				data.add((InfluencingOrRefiningElement<?>) element);
			}
			i++;
		} while (element != null);
		return data;
	}

	public static void showInfoDialog(String title, String message) {
		showOkMessageDialog(title, message, MessageDialog.INFORMATION);
	}

	public static void showErrorDialog(String title, String message) {
		showOkMessageDialog(title, message, MessageDialog.ERROR);
	}

	public static void showOkMessageDialog(String title, String message,
			int dialogImageType) {
		String[] dialogButtonLabels = { IDialogConstants.OK_LABEL };
		MessageDialog dialog = new MessageDialog(null, title, null, message,
				dialogImageType, dialogButtonLabels, 0);
		dialog.open();
	}
}
