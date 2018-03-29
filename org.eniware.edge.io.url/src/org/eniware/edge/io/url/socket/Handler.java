/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.io.url.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * {@link URLStreamHandler} implementation to read from a socket.
 * 
 * <p>This handler enables a {@code socket://} style URL read-only connection.
 * After constructing the URL, call {@link URLConnection#getInputStream()} to
 * read from the socket.</p>
 * 
 * <p>To enable this handler, pass the following property to the JVM:</p>
 * 
 * <code>-Djava.protocol.handler.pkgs=net.solarnetwork.node.io.url</code>
 * 
 * @version $Revision$ $Date$
 */
public class Handler extends URLStreamHandler {

	/* (non-Javadoc)
	 * @see java.net.URLStreamHandler#openConnection(java.net.URL)
	 */
	@Override
	protected URLConnection openConnection(URL u) throws IOException {
		return new URLConnection(u) {
			Socket socket = null;

			@Override
			public void connect() throws IOException {
				socket = new Socket(getURL().getHost(), getURL().getPort());
				if ( getConnectTimeout() > 0 ) {
					socket.setSoTimeout(getConnectTimeout());
				}
			}

			@Override
			public InputStream getInputStream() throws IOException {
				if ( socket == null ) {
					connect();
				}
				return socket.getInputStream();
			}
			
		};
	}

}
