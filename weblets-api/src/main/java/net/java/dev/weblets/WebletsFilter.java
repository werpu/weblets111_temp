/*
 * Copyright 2005 John R. Fallows
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.java.dev.weblets;

import net.java.dev.weblets.util.ServiceLoader;

import javax.servlet.*;
import java.io.IOException;

/**
 * The WebletsFilter maps requested URLs to Weblet resources.
 * 
 * The <code>META-INF/services/net.java.dev.weblets.WebletsFilter</code> Service Provider configuration file is used to lookup the implementation class for this
 * Filter, as defined by the JAR file specification.
 */
public final class WebletsFilter implements Filter {
	/**
	 * Initializes this Filter.
	 * 
	 * @param config
	 *            the filter configuration
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init(FilterConfig config) throws ServletException {
		try {
			_delegate = (Filter) _WEBLETS_FILTER_CLASS.newInstance();
		} catch (IllegalAccessException e) {
			throw new ServletException("Unable to access " + "WebletsFilter implementation", e);
		} catch (InstantiationException e) {
			throw new ServletException("Unable to instantiate " + "WebletsFilter implementation", e);
		}
		_delegate.init(config);
	}

	/**
	 * Destroys this Filter.
	 */
	public void destroy() {
		_delegate.destroy();
	}

	/**
	 * Processes the incoming request, by looking up the Weblet mapped to the incoming request URL pattern, and dispatching to the Weblet if found, otherwise
	 * the filter chain is executed.
	 * 
	 * @param request
	 *            the servlet request
	 * @param response
	 *            the servlet response
	 * @param chain
	 *            the filter chain
	 * 
	 * @throws IOException
	 *             if an I/O error occurs
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		_delegate.doFilter(request, response, chain);
	}

	private Filter				_delegate;
	// the WebletsFilter Service Provider implementation class
	static private final Class	_WEBLETS_FILTER_CLASS;
	static {
		_WEBLETS_FILTER_CLASS = ServiceLoader.loadService(WebletsFilter.class.getName());
	}
}
