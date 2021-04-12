package com.app.common.controller;

import com.app.common.entity.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 通用Controller，页面跳转等功能
 */
@Controller
public class CommonController {

    @Autowired
    private AppConfig appConfig;

    @RequestMapping("/{page}.html")
    public String showPage(@PathVariable String page) {
        return page;
    }

    @RequestMapping("/{page}/{page1}")
    public String showPage(@PathVariable String page, @PathVariable String page1) {
        return page + "/" + page1;
    }

    /**
     * 跳转到首页,首页路径在application.properties文件中配置
     * @param model 可以向请求中放值
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
        return appConfig.getIndex();
    }

}
