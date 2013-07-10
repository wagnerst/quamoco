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
$Id: QIESLArithmetic.java 4974 2012-02-17 09:34:10Z lochmann $
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

import org.apache.commons.jexl2.JexlArithmetic;

/**
 * Extends the standard arithmetic of JEXL.
 * 
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Hash: F1D093232244FB25C9E1EF024CC85D05
 */
public class QIESLArithmetic extends JexlArithmetic {

	/** Default constructor */
	public QIESLArithmetic() {
		super(false);
	}

	/** {@inheritDoc} */
	@Override
	public Object add(Object arg0, Object arg1) {

		if (arg0 instanceof QPoints || arg1 instanceof QPoints) {
			QPoints val1 = QPoints.asQPoints(arg0);
			QPoints val2 = QPoints.asQPoints(arg1);
			return QPoints.add(val1, val2);
		}
		
		
		return super.add(arg0, arg1);
	}

	/** {@inheritDoc} */
	@Override
	public Object subtract(Object arg0, Object arg1) {
		
		if (arg0 instanceof QPoints || arg1 instanceof QPoints) {
			QPoints val1 = QPoints.asQPoints(arg0);
			QPoints val2 = QPoints.asQPoints(arg1);
			return QPoints.subtract(val1, val2);
		}
		
		
		return super.subtract(arg0, arg1);
	}

	/** {@inheritDoc} */
	@Override
	public Object multiply(Object arg0, Object arg1) {
		
		if (arg0 instanceof QPoints || arg1 instanceof QPoints) {
			QPoints val1 = QPoints.asQPoints(arg0);
			QPoints val2 = QPoints.asQPoints(arg1);
			return QPoints.multiply(val1, val2);
		}
		
		
		return super.multiply(arg0, arg1);
	}

	/** {@inheritDoc} */
	@Override
	public Object divide(Object arg0, Object arg1) {
		
		if (arg0 instanceof QPoints || arg1 instanceof QPoints) {
			QPoints val1 = QPoints.asQPoints(arg0);
			QPoints val2 = QPoints.asQPoints(arg1);
			return QPoints.divide(val1, val2);
		}
		
		
		return super.divide(arg0, arg1);
	}
}
