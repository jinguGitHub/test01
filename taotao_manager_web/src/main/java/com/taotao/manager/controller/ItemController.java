package com.taotao.manager.controller;

import com.taotao.manager.pojo.Item;
import com.taotao.manager.service.ItemService;
import javassist.runtime.Desc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.manager.controller
 * @date 2018/2/8
 */
@Controller
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping
    @ResponseBody
    public String saveItem(Item item, String desc){
        String msg = "0";
        try {
            itemService.saveItem(item,desc);
            System.out.println("3333");
        } catch (Exception e) {
            msg = "1";
            e.printStackTrace();
        }
        return msg;
    }
}
