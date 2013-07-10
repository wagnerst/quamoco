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

package de.quamoco.qm.properties;

import org.eclipse.jface.fieldassist.IContentProposal;

import de.quamoco.qm.CharacterizingElement;
import de.quamoco.qm.Measure;
import edu.tum.cs.conqat.quamoco.qiesl.QIESLEngine;

/**
 * {@link IContentProposal} to propose measures and factors for specifications
 * of aggregations and evaluations.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: A1057477D1B24A1F54E834DA3403AD59
 */
public class SpecificationContentProposal implements IContentProposal {

	/** The element which is proposed. */
	private final CharacterizingElement element;

	/** Constructor. */
	public SpecificationContentProposal(CharacterizingElement element) {
		this.element = element;
	}

	/** {@inheritDoc} */
	@Override
	public String getContent() {
		return QIESLEngine.MEASURE_NAME_DELIMITER + getProposal()
				+ QIESLEngine.MEASURE_NAME_DELIMITER;
	}

	/** Get the proposal. */
	private String getProposal() {
		return element.getQualifiedName();
	}

	/** {@inheritDoc} */
	@Override
	public int getCursorPosition() {
		return getProposal().length() + 4;
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return element.getDescription();
	}

	/** {@inheritDoc} */
	@Override
	public String getLabel() {
		String type = ": ";
		if (element instanceof Measure) {
			Measure measure = (Measure) element;
			type += measure.getType().getLiteral();
		}
		return getProposal() + type + " - "
				+ element.getQualityModel().getName();
	}
}
