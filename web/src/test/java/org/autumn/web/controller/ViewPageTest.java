package org.autumn.web.controller;

import org.autumn.common.lang.Collections;
import org.autumn.model.domain.Page;
import org.autumn.model.repositories.PageRepository;
import org.autumn.web.PageTemplate;
import org.autumn.web.Request;
import org.autumn.web.Response;
import org.autumn.web.controller.page.NewPage;
import org.autumn.web.controller.page.ViewPage;
import org.hamcrest.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.autumn.common.lang.Collections.asMap;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    public void user_is_redirected_to_home_page_when_no_page_name_is_given() throws IOException {
        ViewPage viewPage = new ViewPage(pageRepository);
        when(request.getParameter("name")).thenReturn(null);
        viewPage.onRequest(request, response);

        verify(response).redirectTo(eq(NewPage.class), eq(asMap("name", "home")));
    }

    @Test
    public void user_is_redirected_to_create_page_when_page_does_not_exist() throws IOException {
        ViewPage viewPage = new ViewPage(pageRepository);
        when(request.getParameter("name")).thenReturn("page_name");
        when(pageRepository.findPageByName("page_name")).thenReturn(null);
        viewPage.onRequest(request, response);

        verify(response).redirectTo(eq(NewPage.class), eq(asMap("name", "page_name")));
    }

    @Test
    public void can_display_existing_page() throws IOException {
        ViewPage viewPage = new ViewPage(pageRepository);
        when(request.getParameter("name")).thenReturn("test_page");
        when(pageRepository.findPageByName("test_page")).thenReturn(page);
        viewPage.onRequest(request, response);

        verify(response).render(PageTemplate.VIEW_PAGE, page);
    }
}
