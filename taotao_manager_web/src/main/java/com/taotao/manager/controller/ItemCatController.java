package com.taotao.manager.controller;

import com.taotao.manager.pojo.ItemCat;
import com.taotao.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.manager.controller
 * @date 2018/2/6
 * // http://127.0.0.1:8081/rest/item/cat/query/1?rows=2
 */
@Controller
@RequestMapping(value = "/item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    /****
     * 分页查询商品类目
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query/{page}")
    public List<ItemCat> queryItemCatByPage(@PathVariable(value = "page") Integer page,
                                            @RequestParam(value = "rows") Integer rows){
        //List<ItemCat> list = this.itemCatService.queryItemCatByPage(page, rows);
        //return list;
        List<ItemCat> list = this.itemCatService.queryListByPage(page, rows);
        return list;
    }
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<ItemCat> queryItemCatByParentId(@RequestParam(value = "id",defaultValue = "0")Long parentId){
        List<ItemCat> list = this.itemCatService.queryItemCatByParentId(parentId);
        return  list;
    }
}
