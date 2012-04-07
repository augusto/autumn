package org.autumn.web.http;

import org.autumn.web.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpRouter implements Router {

    private Map<Pattern, Controller> routes = new HashMap<Pattern, Controller>();
    private Controller notFoundController;
    private Renderer renderer;

    public HttpRouter(Renderer renderer) {
        this.renderer = renderer;
    }

    public void add(String uriPathRegEx, Controller controller) {
        Pattern uriPathPattern = Pattern.compile(uriPathRegEx);
        routes.put(uriPathPattern, controller);
    }

    public Controller findControllerForUri(String uri) {
        String uriPath = extractPath(uri);
        for( Pattern pattern : routes.keySet()) {
            Matcher matcher = pattern.matcher(uriPath);
            if( matcher.matches()) {
                return routes.get(pattern);
            }
        }

        return getNotFoundController(uriPath);
    }

    private String extractPath(String uri) {
        String uriPath;
        int indexOfParameters = uri.indexOf("?");
        if( indexOfParameters < 0) {
            uriPath = uri;
        } else {
            uriPath = uri.substring(0, indexOfParameters);
        }

        return uriPath;
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

    @Override
    public void route(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        String uri = httpRequest.getRequestURI();
        Request request = new HttpRequest(httpRequest);
        Response response = new HttpResponse(httpResponse, renderer);

        findControllerForUri(uri).onRequest(request, response);
    }
}
