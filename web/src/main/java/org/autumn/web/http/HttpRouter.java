package org.autumn.web.http;

import org.autumn.web.Controller;
import org.autumn.web.Router;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpRouter implements Router {

    private Map<Pattern, Controller> routes = new HashMap<Pattern, Controller>();
    private Controller notFoundController;

    public void add(String uriPathRegEx, Controller controller) {
        Pattern uriPathPattern = Pattern.compile(uriPathRegEx);
        routes.put(uriPathPattern, controller);
    }

    public Controller findControllerForUri(String uriPath) {
        for( Pattern pattern : routes.keySet()) {
            Matcher matcher = pattern.matcher(uriPath);
            if( matcher.matches()) {
                return routes.get(pattern);
            }
        }

        return getNotFoundController(uriPath);
    }

    public void setNotFoundController(Controller notFoundController) {

        this.notFoundController = notFoundController;
    }

    private Controller getNotFoundController(String uriPath) {
        if (notFoundController == null) {
            throw new IllegalArgumentException("Path " + uriPath + " not found in routes and not found controller" +
                    " has not been set");
        }

        return notFoundController;
    }
}
