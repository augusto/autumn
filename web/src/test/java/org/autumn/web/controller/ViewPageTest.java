package org.autumn.web.controller;

import org.autumn.model.domain.Page;
import org.autumn.model.repositories.PageRepository;
import org.autumn.web.PageTemplate;
import org.autumn.web.Request;
import org.autumn.web.Response;
import org.autumn.web.controller.page.CreatePage;
import org.autumn.web.controller.page.ViewPage;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.mockito.Mockito.verify;

public class ViewPageTest {

    @Mock
    Response response;

    @Mock
    Request request;

    @Mock
    PageRepository pageRepository;

    Page page;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
        page = new Page("page");
    }

    @Test
    public void user_is_redirected_to_create_page_when_page_does_not_exist() throws IOException {
        ViewPage viewPage = new ViewPage(pageRepository);
        Mockito.when(request.getParameter("pageName")).thenReturn(null);
        viewPage.onRequest(request, response);

        verify(response).sendTo(CreatePage.class);
    }

    @Test
    public void can_display_existing_page() throws IOException {
        ViewPage viewPage = new ViewPage(pageRepository);
        Mockito.when(request.getParameter("pageName")).thenReturn("test_page");
        Mockito.when(pageRepository.findPageByName("test_page")).thenReturn(page);
        viewPage.onRequest(request, response);

        verify(response).render(PageTemplate.VIEW_PAGE, page);
    }
}
