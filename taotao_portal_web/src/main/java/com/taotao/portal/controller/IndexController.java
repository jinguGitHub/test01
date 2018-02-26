package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.portal.controller
 * @date 2018/2/25
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }
}
