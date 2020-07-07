package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.manage_cms.service.PageService;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PageserviceTest {
    @Autowired
    PageService pageService;

    @Test
    public void testGetPageHtml() throws TemplateException {
        String pageHtml = pageService.getPageHtml("5ef41c766761b579577909bc");
        System.out.println(pageHtml);
    }
}
