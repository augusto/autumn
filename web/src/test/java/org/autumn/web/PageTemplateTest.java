package org.autumn.web;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PageTemplateTest {

    @Test
    public void can_infer_template_file_name_from_enum_name() {
        assertThat(PageTemplate.VIEW_PAGE.getTemplateName(), equalTo("viewpage.tfl"));
    }
}
