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

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LineReader {

	protected boolean closed = false;

	public boolean isClosed() {
		try {
			return closed || in.available() == -1;
		} catch (IOException e) {
			return true;
		}
	}
	
	public void close() throws IOException {
		this.in.close();
	}

	public static interface GotFirstByteEvent {
		public void gotFirstByte();
	}

	protected List<GotFirstByteEvent> listeners = new LinkedList<GotFirstByteEvent>();

	public void addListener(GotFirstByteEvent l) {
		this.listeners.add(l);
	}

	public void removeListener(GotFirstByteEvent l) {
		this.listeners.remove(l);
	}

	private void fireGotFirstByte() {
		Iterator<GotFirstByteEvent> i = this.listeners.iterator();
		while (i.hasNext()) {
			i.next().gotFirstByte();
		}
	}

	protected BufferedInputStream in;

	public LineReader(BufferedInputStream in) {
		this.in = in;
	}

	public byte[] readLine() throws IOException {
		boolean firstByte = false;
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			int i;
			boolean rread = false;
			while ((i = in.read()) >= 0) {

				if (firstByte == false) {
					firstByte = true;
					this.fireGotFirstByte();
				}

				if (i == '\n' && rread) {
					return bout.toByteArray();
				} else if (rread) {
					rread = false;
					bout.write('\r');
				}
				if (i == '\r') {
					rread = true;
				} else {
					bout.write((byte) i);
				}
			}
			if (i < 0) {
				this.closed = true;
			}
			return bout.toByteArray();
		} catch (SocketException se) {
			this.closed = true;
			return null;
		}
	}

	public int read(byte[] b) throws IOException {
		int l = in.read(b);
		if (l <= 0) {
			this.closed = true;
		}
		return l;
	}

	public int read(byte[] b, int offset, int length) throws IOException {
		int l = in.read(b, offset, length);
		if (l <= 0) {
			this.closed = true;
		}
		return l;
	}

	public int read() throws IOException {
		int l = in.read();
		if (l < 0) {
			this.closed = true;
		}
		return l;
	}
}
