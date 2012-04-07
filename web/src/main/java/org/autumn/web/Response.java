package org.autumn.web;

import java.io.IOException;

public interface Response  {
    void sendTo(Class<? extends Controller> page) throws IOException;

    void render(PageTemplate template, Object model) throws IOException;
}
