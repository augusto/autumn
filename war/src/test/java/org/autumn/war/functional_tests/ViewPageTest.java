package org.autumn.war.functional_tests;

import org.autumn.inject.InjectModel;
import org.autumn.model.domain.Page;
import org.autumn.model.repositories.PageRepository;
import org.autumn.war.servlets.ApplicationServlet;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewPageTest {

    ApplicationServlet applicationServlet = new ApplicationServlet();

    @BeforeMethod
    public void setup() {

    }

    @Test
    public void test() throws IOException, ServletException {
        PageRepository pageRepository = InjectModel.injectPageRepository();
        pageRepository.savePage(new Page("home"));

        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest("/viewpage");
        mockHttpServletRequest.addParameter("pageName", "home");
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();

        applicationServlet.doGet(mockHttpServletRequest, mockHttpServletResponse);

        System.out.println(mockHttpServletResponse.getBody());
    }

}
