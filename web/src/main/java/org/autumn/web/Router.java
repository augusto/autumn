package org.autumn.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Router {

    Controller findControllerForUri(String uriPath);

    void route(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception;
}
