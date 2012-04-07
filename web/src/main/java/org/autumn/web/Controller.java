package org.autumn.web;

import java.io.IOException;

/**
 * This is not really a page, even a component embedded on a page should be able to respond to a request.
 */
public interface Controller {
    public void onRequest(Request request, Response response) throws Exception;
}
