package org.autumn.web.controller;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class AServlet extends HttpServlet {
    private final static String TEMPLATES_DIRECTORY = "WEB-INF/templates";

    private Configuration cfg;

    public AServlet() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        cfg = config();

        Map root = new HashMap();
        root.put("user", "Big Joe");
        Map latest = new HashMap();
        root.put("latestProduct", latest);
        latest.put("url", "products/greenmouse.html");
        latest.put("name", "green mouse");

        Template template = cfg.getTemplate("test.ftl");

        PrintWriter writer = resp.getWriter();
        try {
            template.process(root, writer);
        } catch (TemplateException e) {
            throw new RuntimeException("ERROR, ERROR!!!", e);
        }
    }

    private Configuration config() {
        if( cfg == null) {
            cfg = new Configuration();
            cfg.setServletContextForTemplateLoading(getServletContext(), TEMPLATES_DIRECTORY);
            cfg.setObjectWrapper(new DefaultObjectWrapper());
        }

        return cfg;
    }
}

