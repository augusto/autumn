package org.autumn.web;

public enum PageTemplate {

    VIEW_PAGE;

    private static final String TEMPLATE_EXTENSION = ".tfl";

    public String getTemplateName() {
        return this.name().toLowerCase().replaceAll("_","") + TEMPLATE_EXTENSION;
    }
}
