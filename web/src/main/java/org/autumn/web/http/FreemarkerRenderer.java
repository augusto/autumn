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
import java.util.HashMap;
import java.util.Map;

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
            Map<String, Object> rootMap = new HashMap<String, Object>();
            rootMap.put("model", model);
            template.process(rootMap, writer);
        } catch (TemplateException e) {
            throw new RuntimeException("Error processing the template " + pageTemplate.getTemplateName(), e);
        }

    }
}
