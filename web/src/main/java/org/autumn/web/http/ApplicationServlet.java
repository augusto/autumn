package org.autumn.web.http;

import org.autumn.web.Renderer;
import org.autumn.web.Request;
import org.autumn.web.Response;
import org.autumn.web.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApplicationServlet extends HttpServlet {

    private Router router;
    private Renderer renderer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    private void doProcess(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException {
        String uri = httpRequest.getRequestURI();
        Request request = new HttpRequest(httpRequest);
        Response response = new HttpResponse(httpResponse, renderer);
        try {
            router.findControllerForUri(uri).onRequest(request, response);
        } catch (Exception e) {
            throw new ServletException("Unexpected Error", e);
        }
    }
}
