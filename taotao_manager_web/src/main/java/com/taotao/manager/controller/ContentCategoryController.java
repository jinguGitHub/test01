package com.taotao.manager.controller;

import com.taotao.manager.pojo.ContentCategory;
import com.taotao.manager.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Locale;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.manager.controller
 * @date 2018/2/25
 */
@Controller
@RequestMapping(value = "/content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    /**
     * 根据父类id查询分类
     * @param id
     * @return
     */
    //    url : '/rest/content/category',
    //    animate: true,
    //    method : "GET",

    @RequestMapping(method = RequestMethod.GET )
    @ResponseBody
    public List<ContentCategory> queryContentCategoryByParentId(@RequestParam(defaultValue = "0")Long id){
        List<ContentCategory> categories = contentCategoryService.queryContentCategoryByParentId(id);
        return categories;
    }

    //$.post("/rest/content/category/add",{parentId:node.parentId,name:node.text}
    @ResponseBody
    @RequestMapping(value = "add",method =RequestMethod.POST)
    public ContentCategory add(ContentCategory contentCategory){
        contentCategory = contentCategoryService.add(contentCategory);
        return contentCategory;
    }

//    type: "POST",
//    url: "/rest/content/category/update",
//    data: {id:node.id,name:node.text},
//    success: function(msg)
    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String update(ContentCategory contentCategory){
        String msg = "0";
        try {
            contentCategoryService.updateContentCategory(contentCategory);
        } catch (Exception e) {
            msg = "1";
            e.printStackTrace();
        }

        return msg;
    }

//    type: "POST",
//    //parentId=${节点的父id}&id=${选中节点的id}
//    url: "/rest/content/category/delete",
//    data : {parentId:node.parentId,id:node.id},
//    success: function(msg)
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public String delete(ContentCategory contentCategory){
        String msg = "0";
        try {
            contentCategoryService.deleteContentCategory(contentCategory);
        } catch (Exception e) {
            msg = "1";
            e.printStackTrace();
        }
        //TODO
        return msg;
    }
}
