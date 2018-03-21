package com.taotao.portal.controller;

import com.taotao.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.portal.controller
 * @date 2018/2/25
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @Autowired
    private ContentService contentService;

    //注入首页大广告的Id
    @Value("${TAOTAO_PORTAL_AD_ID}")
    private Long TAOTAO_PORTAL_AD_ID;

    @RequestMapping(method = RequestMethod.GET)
    public String toIndex(Model model) {
        // 调用内容服务，查询大广告数据,大广告分类id为31
        String AD = contentService.queryAD(TAOTAO_PORTAL_AD_ID);

        //把大广告保存到model中 传递到到前台
        model.addAttribute("AD",AD);
        return "index";

    }
}
