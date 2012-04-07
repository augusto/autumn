package org.autumn.web.http;

import org.autumn.web.Controller;
import org.autumn.web.PageTemplate;
import org.autumn.web.Renderer;
import org.autumn.web.Response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpResponse implements Response {

    private HttpServletResponse response;
    private Renderer renderer;

    public HttpResponse(HttpServletResponse response, Renderer renderer) {
        this.response = response;
        this.renderer = renderer;
    }

    @Override
    public void sendTo(Class<? extends Controller> page) throws IOException {
        response.sendRedirect(page.getCanonicalName());
    }

    @Override
    public void render(PageTemplate template, Object model) {


    }
}
