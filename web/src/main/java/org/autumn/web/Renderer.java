package org.autumn.web;

import java.io.IOException;
import java.io.Writer;

public interface Renderer {
    void render(Writer writer, PageTemplate pageTemplate, Object model) throws IOException;
}
