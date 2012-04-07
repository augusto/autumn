package org.autumn.web;

public interface Router {

    public Controller findControllerForUri(String uriPath);

}
