package net.java.dev.weblets.impl.faces;

import net.java.dev.weblets.impl.faces.util.FacesElWebletResource;
import net.java.dev.weblets.impl.faces.util.FacesELWebletURL;
import net.java.dev.weblets.FacesElWeblet;

import java.util.Map;

/**
 * Wrapper to enable . notation in our weblet jsf demos!
 * 
 * @author: Werner Punz
 * @date: 13.02.2008.
 */
public class FacesElWebletImpl extends net.java.dev.weblets.FacesElWeblet {
	FacesElWebletResource	resourceHandler	= new FacesElWebletResource();
	FacesELWebletURL		urlHandler		= new FacesELWebletURL();

	/**
	 * enables a weblet.resource[][] notation on the jsf side of things
	 * 
	 * @return
	 */
	public Map getResource() {
		return resourceHandler;
	}

	/**
	 * enables a weblet.url[][] notation on the jsf side of things
	 * 
	 * @return
	 */
	public Map getUrl() {
		return urlHandler;
	}
}
