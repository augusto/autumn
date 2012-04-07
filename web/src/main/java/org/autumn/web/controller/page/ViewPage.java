package org.autumn.web.controller.page;

import org.autumn.model.domain.Page;
import org.autumn.model.repositories.PageRepository;
import org.autumn.web.Controller;
import org.autumn.web.PageTemplate;
import org.autumn.web.Request;
import org.autumn.web.Response;

import java.io.IOException;

public class ViewPage implements Controller {

    private PageRepository pages;

    public ViewPage(PageRepository pages) {
        this.pages = pages;
    }

    public void onRequest(Request request, Response httpResponse) throws IOException {

        String pageName = request.getParameter("pageName");
        Page page = pages.findPageByName(pageName);
        if( page == null) {
            httpResponse.sendTo(CreatePage.class);
        } else {
            httpResponse.render(PageTemplate.VIEW_PAGE, page);
        }
    }
}