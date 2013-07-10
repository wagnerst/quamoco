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

package edu.tum.cs.conqat.quamoco.lightweightxml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class CharStream {

	private int index = 0;
	private String buffer;

	public char read() {
		if (index >= buffer.length()) {
			return Character.MAX_VALUE;
		}
		return buffer.charAt(index++);
	}

	public CharStream(InputStream in) throws IOException {
		StringBuffer sb = new StringBuffer();
		int i;
		while ((i = in.read()) >= 0) {
			sb.append((char) i);
		}
		this.buffer = sb.toString();
	}

	public CharStream(InputStream in, String charset) throws IOException,
			UnsupportedEncodingException {
		ByteArrayOutputStream ba = new ByteArrayOutputStream();
		int l;
		byte[] b = new byte[1024];
		while ((l = in.read(b)) > 0) {
			ba.write(b, 0, l);
		}
		this.buffer = new String(ba.toByteArray(), charset);
	}
}
