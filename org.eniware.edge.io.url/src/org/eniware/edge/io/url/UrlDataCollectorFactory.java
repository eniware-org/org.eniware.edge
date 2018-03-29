/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.io.url;

import org.springframework.beans.factory.ObjectFactory;

/**
 * {@link ObjectFactory} implementation for {@link UrlDataCollector}
 * objects.
 * 
 * <p>The configurable properties of this class are:</p>
 * 
 * <dl class="class-properties">
 *   <dt>url</dt>
 *   <dd>The URL to access the character data from. Either this or 
 *   {@code urlFactory} must be configured.</dd>
 *   
 *   <dt>urlFactory</dt>
 *   <dd>A factory for creating URL values dynamically. This allows dynamic
 *   URLs to be used, such as those with the current date in the path. Either
 *   this or {@code url} must be configured. If this is configured it will
 *   take precedence over any configured {@code url} value.</dd>
 *   
 *   <dt>connectionTimeout</dt>
 *   <dd>A timeout to use for connecting to the configured {@code url}.
 *   Defaults to </dd>
 *   
 *   <dt>matchExpression</dt>
 *   <dd>A regular expression to match against lines of data read from the URL.
 *   The collector will ignore all lines of data until this expression matches
 *   a line read from the configured URL. Once a match is found, it will stop
 *   reading any further data. Defaults to 
 *   {@link #DEFAULT_MATCH_EXPRESSION}.</dd>
 *   
 *   <dt>encoding</dt>
 *   <dd>The character encoding to use for reading the URL data. If configured
 *   as <em>null</em> then this class will attempt to use the encoding
 *   specified by the URL connection itself. If the URL connection does not
 *   provide an encoding, {@link #DEFAULT_ENCODING} will be used. Defaults to
 *   <em>null</em>.</dd>
 *   
 *   <dt>skipToLastLine</dt>
 *   <dd>If <em>true</em> then read all available data from the URL and 
 *   return the last line found, as long as it also matches the configured
 *   {@code matchExpression} property. This mode means that the entire URL
 *   data stream must be read each time {@link #collectData()} is called.
 *   Defaults to <em>false</em>.</dd>
 * </dl>
 * 
 * @version $Id$
 */
public class UrlDataCollectorFactory
implements ObjectFactory<UrlDataCollector> {

	private String url = null;
	private ObjectFactory<String> urlFactory = null;
	private int connectionTimeout = UrlDataCollector.DEFAULT_CONNECTION_TIMEOUT;
	private String matchExpression = UrlDataCollector.DEFAULT_MATCH_EXPRESSION;
	private String encoding = null;
	private boolean skipToLastLine = false;
	
	@Override
	public UrlDataCollector getObject() {
		UrlDataCollector collector = new UrlDataCollector();
		collector.setUrl(this.url);
		collector.setUrlFactory(this.urlFactory);
		collector.setConnectionTimeout(this.connectionTimeout);
		collector.setMatchExpression(this.matchExpression);
		collector.setEncoding(this.encoding);
		collector.setSkipToLastLine(this.skipToLastLine);
		return collector;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the connectionTimeout
	 */
	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	/**
	 * @param connectionTimeout the connectionTimeout to set
	 */
	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	/**
	 * @return the matchExpression
	 */
	public String getMatchExpression() {
		return matchExpression;
	}

	/**
	 * @param matchExpression the matchExpression to set
	 */
	public void setMatchExpression(String matchExpression) {
		this.matchExpression = matchExpression;
	}

	/**
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @param encoding the encoding to set
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * @return the urlFactory
	 */
	public ObjectFactory<String> getUrlFactory() {
		return urlFactory;
	}

	/**
	 * @param urlFactory the urlFactory to set
	 */
	public void setUrlFactory(ObjectFactory<String> urlFactory) {
		this.urlFactory = urlFactory;
	}

	/**
	 * @return the skipToLastLine
	 */
	public boolean isSkipToLastLine() {
		return skipToLastLine;
	}

	/**
	 * @param skipToLastLine the skipToLastLine to set
	 */
	public void setSkipToLastLine(boolean skipToLastLine) {
		this.skipToLastLine = skipToLastLine;
	}

}
