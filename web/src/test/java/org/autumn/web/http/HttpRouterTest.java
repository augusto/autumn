package org.autumn.web.http;

import org.autumn.web.Controller;
import org.autumn.web.Request;
import org.autumn.web.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HttpRouterTest {

    final Controller viewPageController = new TestController();
    final Controller createPageController = new TestController();
    final Controller pageNotFoundController = new TestController();

    private HttpRouter router = new HttpRouter();

    @BeforeMethod
    public void setup() {

        router.add("/viewpage", viewPageController);
        router.add("/createpage", createPageController);
        router.setNotFoundController(pageNotFoundController);
    }

    @Test
    public void can_route_registered_controller() {
        assertThat(router.findControllerForUri("/viewpage"), equalTo(viewPageController));
    }

    @Test
    public void can_route_to_page_not_found_if_page_is_not_found_and_not_found_controller_is_set() {
        assertThat(router.findControllerForUri("/non_registered_page"), equalTo(pageNotFoundController));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void throws_illegal_state_exception_if_page_is_not_found_and_not_found_controller_is_not_set() {
        router.setNotFoundController(null);
        router.findControllerForUri("/non_registered_page");
    }

    private class TestController implements Controller {

        @Override
        public void onRequest(Request request, Response httpResponse) throws Exception {

        }
    }
}
