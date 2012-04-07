package org.autumn.war.servlets;

import org.autumn.inject.InjectWeb;
import org.autumn.web.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ApplicationServlet extends HttpServlet {

    private final Router router;

    public ApplicationServlet() {
        router = InjectWeb.injectRouter();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    private void doProcess(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException {

        System.out.println("Uri: " + httpRequest.getRequestURI());
        Map parameterMap = httpRequest.getParameterMap();
        if( parameterMap.isEmpty() ) {
            System.out.println("Parameter map is empty");
        } else {
            for( Object key : parameterMap.keySet()) {
                System.out.println("Parameter ["+ key + "=" + parameterMap.get(key) + "]");
            }
        }

        try {
            router.route(httpRequest, httpResponse);
        } catch (Exception e) {
            throw new ServletException("Unexpected Error", e);
        }
    }
}
