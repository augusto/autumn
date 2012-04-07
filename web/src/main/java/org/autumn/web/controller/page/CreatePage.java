package org.autumn.web.controller.page;

import org.autumn.web.Controller;
import org.autumn.web.PageTemplate;
import org.autumn.web.Request;
import org.autumn.web.Response;

import java.io.IOException;

public class CreatePage implements Controller {

    @Override
    public void onRequest(Request request, Response response) throws IOException {
        response.render(PageTemplate.CREATE_PAGE, null);
    }
}
