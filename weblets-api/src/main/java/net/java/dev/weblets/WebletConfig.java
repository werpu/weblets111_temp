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

import java.util.Iterator;
import java.util.Set;

public interface WebletConfig {
	static int	WEBLET_TYPE_LOCAL		= 1;
	static int	WEBLET_TYPE_PROXY		= 2;
	static int	WEBLET_TYPE_REDIRECT	= 3;
	static int	WEBLET_TYPE_OTHER		= 100;

	public WebletContainer getWebletContainer();

	public String getWebletName();

	public String getWebletVersion();

	public String getInitParameter(String paramName);

	public Iterator getInitParameterNames();

	public String getMimeType(String resourcePath);

    /**
     *
     * @return a list of allowed resources depending on the init params
     * of the weblet
     */

    public Set getAllowedResources();
    // public int getWebletType(); /*disable some subsystems if this type of*/
}