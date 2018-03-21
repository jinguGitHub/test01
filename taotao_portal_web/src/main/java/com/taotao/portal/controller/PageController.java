package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.portal.controller
 * @date 2018/3/18
 */
@Controller
@RequestMapping("page")
public class PageController {

    /**
     * 通用页面跳转方法
     * @param pageName
     * @return
     */
    @RequestMapping("{pageName}")
    public String toPage(@PathVariable(value = "pageName")String pageName) {
        return pageName;
    }
}
