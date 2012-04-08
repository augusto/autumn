package org.autumn.web.http;

import org.autumn.common.lang.Collections;
import org.autumn.web.Controller;
import org.autumn.web.PageTemplate;
import org.autumn.web.Renderer;
import org.autumn.web.Response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class HttpResponse implements Response {

    private HttpServletResponse response;
    private Renderer renderer;

    public HttpResponse(HttpServletResponse response, Renderer renderer) {
        this.response = response;
        this.renderer = renderer;
    }

    @Override
    public void sendTo(Class<? extends Controller> controller) throws IOException {
        sendTo(controller, Collections.EMPTY_MAP);
    }

    @Override
    public void sendTo(Class<? extends Controller> controller, Map<String, String> parameters) throws IOException {
        StringBuilder uriPath = new StringBuilder();
        uriPath.append(controller.getSimpleName().toLowerCase());
        if( ! parameters.isEmpty() ) {
            uriPath.append("?");

            boolean firstParam = true;
            for( String paramName : parameters.keySet()) {
                if( firstParam ) {
                    firstParam = false;
                } else {
                    uriPath.append("&");
                }

                uriPath.append(paramName).append("=").append(parameters.get(paramName));
            }
        }

        response.sendRedirect(uriPath.toString());
    }

    @Override
    public void render(PageTemplate template, Object model) throws IOException {
        PrintWriter writer = response.getWriter();
        renderer.render(writer, template, model);
        writer.close();
    }
}
