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

package edu.tum.cs.conqat.quamoco;

import java.util.HashMap;

import org.conqat.lib.commons.clone.IDeepCloneable;

/**
 * A simple transport object for collections of metrics. This works around
 * ConQAT's limitations for generics. Deep cloning of this class actually
 * performs a shallow clone.
 * 
 * @author deissenb
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 37309F222F9BEB7E0FDFC2A52F8C9C94
 */
public class MetricCollection extends HashMap<String, Object> implements
		IDeepCloneable {
	
	/** Create new collection. */
	public MetricCollection() {
		super();
	}

	/** Copy constructor. This performs a shallow copy only. */
	private MetricCollection(MetricCollection other) {
		putAll(other);
	}

	/** This returns a <i>shallow</i> clone of the metrics. */
	@Override
	public MetricCollection deepClone() {
		return new MetricCollection(this);
	}

}
