package org.autumn.web.controller.page;

import org.autumn.common.lang.Collections;
import org.autumn.model.domain.Page;
import org.autumn.model.repositories.PageRepository;
import org.autumn.web.Controller;
import org.autumn.web.Request;
import org.autumn.web.Response;

import java.io.IOException;

import static org.autumn.common.lang.Collections.asMap;

public class CreatePage implements Controller {

    private PageRepository pageRepository;

    public CreatePage(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @Override
    public void onRequest(Request request, Response response) throws IOException {
        String name = request.getParameter("name");
        String body = request.getParameter("body");
        Page newPage = new Page(name);
        newPage.setBody(body);
        pageRepository.savePage(newPage);


        response.redirectTo(ViewPage.class, asMap("name", name));
    }
}
