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

/*--------------------------------------------------------------------------+
$Id: QIESLEvalVariables.java 4974 2012-02-17 09:34:10Z lochmann $
|                                                                          |
| Copyright 2005-2010 by the ConQAT Project                                |
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
+--------------------------------------------------------------------------*/
package edu.tum.cs.conqat.quamoco.qiesl;

import java.util.HashMap;
import java.util.Map;

import org.conqat.lib.commons.collections.CollectionUtils;
import org.conqat.lib.commons.collections.UnmodifiableMap;

/**
 * 
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Hash: D9B6AAFDB5BE7038B78BC8632A49418F
 */
public class QIESLEvalVariables implements IQIESLEvalVariables {

	private final QPoints[] allImpactsAndRefinements;
	private final Map<String, Object> mandatoryVariables = new HashMap<String, Object>();
	private final Map<String, Object> optionalVariables = new HashMap<String, Object>();

	public QIESLEvalVariables(QPoints[] allImpactsAndRefinements) {
		this.allImpactsAndRefinements = allImpactsAndRefinements;
	}

	public QIESLEvalVariables(QPoints[] allImpactsAndRefinements,
			Map<String, Object> optionalVariables,
			Map<String, Object> mandatoryVariables) {
		this.allImpactsAndRefinements = allImpactsAndRefinements;
		this.optionalVariables.putAll(optionalVariables);
		this.mandatoryVariables.putAll(mandatoryVariables);
	}

	public void addOptionalVariable(String name, Object value) {
		optionalVariables.put(name, value);
	}

	public void addMandatoryVariable(String name, Object value) {
		mandatoryVariables.put(name, value);
	}

	/** {@inheritDoc} */
	@Override
	public QPoints[] getAllImpactsAndRefinements() {
		return allImpactsAndRefinements;
	}

	/** {@inheritDoc} */
	@Override
	public UnmodifiableMap<String, Object> getMandatoryVariables() {
		return CollectionUtils.asUnmodifiable(mandatoryVariables);
	}

	/** {@inheritDoc} */
	@Override
	public UnmodifiableMap<String, Object> getOptionalVariables() {
		return CollectionUtils.asUnmodifiable(optionalVariables);
	}

}
