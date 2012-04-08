package org.autumn.web.controller.page;

import org.autumn.web.Controller;
import org.autumn.web.PageTemplate;
import org.autumn.web.Request;
import org.autumn.web.Response;

public class NewPage implements Controller {
    @Override
    public void onRequest(Request request, Response response) throws Exception {
        response.render(PageTemplate.NEW_PAGE, null);
    }
}
