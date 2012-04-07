package org.autumn.web.http;

import org.autumn.web.Request;

import javax.servlet.http.HttpServletRequest;

public class HttpRequest implements Request {

    private HttpServletRequest request;

    public HttpRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        return request.getParameter(name);
    }
}
