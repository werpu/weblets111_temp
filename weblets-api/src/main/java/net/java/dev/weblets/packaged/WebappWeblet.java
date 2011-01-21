package net.java.dev.weblets.packaged;

import net.java.dev.weblets.*;
import net.java.dev.weblets.packaged.WebletResourceloadingUtils;
import net.java.dev.weblets.util.CopyStrategy;
import net.java.dev.weblets.util.CopyStrategyImpl;
import net.java.dev.weblets.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Weblet for serving resources from a classical webapp structures This is currently only working in a portlet environment if weblets is triggered outside of
 * the portlet context so use it with care!
 *
 * @author Werner Punz
 */
public class WebappWeblet extends Weblet {
    String _packageName = "";
    private String _resourceRoot;

    /**
     * init method which is called by default to process the parameters
     *
     * @param config the webletconfig to be processed
     */
    public void init(WebletConfig config) {
        super.init(config);
        // fetch the weblets init param
        _packageName = config.getInitParameter("package");
        // fetch the resource root param
        /*auto fix path according to the specs the root must begin with a leading /*/
        _resourceRoot = config.getInitParameter("resourceRoot");
        if (StringUtils.isBlank(_resourceRoot))
            _resourceRoot = "/";
        _resourceRoot = _resourceRoot.trim();
        if (!_resourceRoot.startsWith("/") && !_resourceRoot.startsWith("\\"))
            _resourceRoot = "/" + _resourceRoot;

        // init param missing, lets throw an error
        if (_packageName == null && _resourceRoot == null) {
            throw new WebletException("Missing either init parameter \"package\" or " + " or init parameter \"resourceRoot\" for " + " Weblet \""
                    + config.getWebletName() + "\"");
        }
        // the init was successful we now have all we need
        _resourceRoot = (_packageName != null) ? _packageName.replace('.', '/') : _resourceRoot;
    }

    public void service(WebletRequest request, WebletResponse response) throws IOException {
        String resourcePath = _resourceRoot + request.getPathInfo();
        // this might fail on some containers overriding the HttpServlet
        // but for demo purposes this is ok
        HttpServletRequest httpRequest = (HttpServletRequest) request.getExternalRequest();

        /*we now have the resource handling decoupled from the mimetypes*/
        CopyStrategy copyProvider = new CopyStrategyImpl();
        if (resourcePath.indexOf("WEB-INF/") != -1 || resourcePath.indexOf("WEB-INF\\") != -1) {
            // security breach nothing in WEB-INF is allowed to be accessed
            return;
        }
        if (resourcePath.indexOf("META-INF/") != -1 || resourcePath.indexOf("META-INF\\") != -1) {
            // security breach nothing in WEB-INF is allowed to be accessed
            return;
        }
        URL url = httpRequest.getSession().getServletContext().getResource("/" + resourcePath);
        WebletResourceloadingUtils.getInstance().loadFromUrl(getWebletConfig(), request, response, url, copyProvider);
    }

    public InputStream serviceStream(String pathInfo, String mimetype) throws IOException, WebletException {
        return null; // To change body of implemented methods use File |
        // Settings | File Templates.
    }

    public void destroy() {
        _resourceRoot = null;
        _packageName = null;
        super.destroy();
	}
}
