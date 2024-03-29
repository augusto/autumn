package org.autumn.web;

import java.io.IOException;
import java.util.Map;

public interface Response  {
    void redirectTo(Class<? extends Controller> controller) throws IOException;

    void redirectTo(Class<? extends Controller> controller, Map<String, String> parameters) throws IOException;

    void render(PageTemplate template, Object model) throws IOException;
}
