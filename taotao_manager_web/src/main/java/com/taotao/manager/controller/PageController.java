package com.taotao.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.manager.controller
 * @date 2018/2/6
 *
 * 访问路径:http://manager.taotao.com:8080/rest/page/index
 */
@Controller
@RequestMapping(value = "/page")
public class PageController {

    /****
     * 通用页面跳转方法
     * @param pageName
     * @return
     */
    @RequestMapping(value = "/{pageName}")
    public String toPage(@PathVariable(value = "pageName") String pageName) {
        return pageName;
    }

}
