package org.autumn.web.http;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.autumn.web.PageTemplate;
import org.autumn.web.Renderer;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class FreemarkerRenderer implements Renderer {
    private Configuration cfg;

    public FreemarkerRenderer() {
        cfg = new Configuration();
        cfg.setClassForTemplateLoading(Renderer.class,"");
        cfg.setObjectWrapper(new DefaultObjectWrapper());
    }

    @Override
    public void render(Writer writer, PageTemplate pageTemplate, Object model) throws IOException {

        try {
            Template template = cfg.getTemplate(pageTemplate.getTemplateName());
            template.process(model, writer);
        } catch (TemplateException e) {
            throw new RuntimeException("Error processing the template " + pageTemplate.getTemplateName(), e);
        }

    }
}
