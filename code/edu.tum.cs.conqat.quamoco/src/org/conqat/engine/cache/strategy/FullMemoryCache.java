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

package org.conqat.engine.cache.strategy;

import java.util.HashMap;

import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.core.core.IShutdownHook;

@AConQATProcessor(description = "Caches all file in memory.")
public class FullMemoryCache extends CachingStrategy {

	/** Hashmap for caching all data */
	protected HashMap<Object, byte[]> cache = new HashMap<Object, byte[]>();
	
	/** Number of cache hits */
	private long hits = 0;
	
	/** Number of cache misses */
	private long misses = 0;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public byte[] getContent(Object key) {
		byte[] result = this.cache.get(key);
		if(result != null) {
			hits ++;
		} else {
			misses ++;
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setContent(Object key, byte[] content) {
		this.cache.put(key, content);
	}

	/**
	 * Returns this
	 */
	@Override
	public CachingStrategy process() throws ConQATException {
		// Print statistics
		this.getProcessorInfo().registerShutdownHook(new IShutdownHook() {
			@Override
			public void performShutdown() throws ConQATException {
				long size = 0;
				for (byte[] content : cache.values()) {
					size += content.length;
				}
				getLogger().info(
						"Cache contains " + cache.size()
								+ " elements with total size " + size
								+ " bytes.");
				getLogger().info(
						"Of " + (misses + hits) + " accesses, " + hits + " were hits.");
				
			}
		}, true);
		
		return this;
	}

}
