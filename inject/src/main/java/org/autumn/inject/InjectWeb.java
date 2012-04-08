package org.autumn.inject;

import org.autumn.web.Controller;
import org.autumn.web.Renderer;
import org.autumn.web.Router;
import org.autumn.web.controller.page.CreatePage;
import org.autumn.web.controller.page.NewPage;
import org.autumn.web.controller.page.ViewPage;
import org.autumn.web.http.FreemarkerRenderer;
import org.autumn.web.http.HttpRouter;

public class InjectWeb {

    private static final Renderer renderer  = new FreemarkerRenderer();
    private static final Router router;

    static {
        HttpRouter httpRouter = new HttpRouter(renderer);
        httpRouter.add("/viewpage", injectViewPage());
        httpRouter.add("/createpage", injectCreatePage());
        httpRouter.add("/newpage", injectNewPage());
        httpRouter.add("/", injectViewPage());
        router = httpRouter;
    }

    // ????
    public Renderer injectRenderer() {
        return renderer;
    }

    public static Controller injectViewPage() {
        return new ViewPage(InjectModel.injectPageRepository());
    }

    public static Controller injectCreatePage() {
        return new CreatePage(InjectModel.injectPageRepository());
    }

    public static Controller injectNewPage() {
        return new NewPage();
    }

    public static Router injectRouter() {
        return router;
    }

}
