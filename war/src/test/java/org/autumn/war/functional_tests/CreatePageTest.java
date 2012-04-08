package org.autumn.war.functional_tests;

import org.autumn.inject.InjectModel;
import org.autumn.model.domain.Page;
import org.autumn.model.repositories.PageRepository;
import org.autumn.war.servlets.ApplicationServlet;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class CreatePageTest {
    ApplicationServlet applicationServlet = new ApplicationServlet();

    @BeforeMethod
    public void setup() {

    }

    @Test
    public void test() throws IOException, ServletException {
        PageRepository pageRepository = InjectModel.injectPageRepository();

        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest("/newpage");
        mockHttpServletRequest.addParameter("pageName", "home");
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();

        applicationServlet.doGet(mockHttpServletRequest, mockHttpServletResponse);

        String responseBody = mockHttpServletResponse.getBody();
        assertThat(responseBody, containsString("action=\"/createpage\""));

        mockHttpServletRequest = new MockHttpServletRequest("/createpage");
        mockHttpServletRequest.addParameter("name", "home");
        mockHttpServletRequest.addParameter("body", "This is the page body");
        mockHttpServletResponse = new MockHttpServletResponse();

        applicationServlet.doGet(mockHttpServletRequest, mockHttpServletResponse);
        assertThat(mockHttpServletResponse.getRedirectLocation(), equalTo("viewpage?name=home"));

    }
}
