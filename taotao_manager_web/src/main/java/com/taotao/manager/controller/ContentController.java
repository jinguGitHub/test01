package com.taotao.manager.controller;

import com.taotao.manager.pojo.Content;
import com.taotao.manager.service.ContentService;
import com.taotao.manager.utils.TaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.manager.controller
 * @date 2018/2/26
 */
@Controller
@RequestMapping(value = "/content")
public class ContentController {
    @Autowired
    private ContentService contentService;
    ////提交到后台的RESTful
    //	$.ajax({
    //  type: "POST",
    //  url: "/rest/content",

    //接收表单content-add.jsp中的数据
    //return msg
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String add(Content content){
        String msg = "0";
        try {
            contentService.saveSelective(content);
        } catch (Exception e) {
            msg = "1";
            e.printStackTrace();
        }
        //TODO
        return msg;
    }

    /**
     * 新增内容到列表中
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
    //    categoryId:4
    //    page:1
    //    rows:20
    ///rest/content?categoryId=4&page=1&rows=20
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public TaoResult<Content> queryContent(Long categoryId,Integer page,Integer rows){
        TaoResult<Content> result = contentService.queryContent(categoryId, page, rows);
        return result;
    }

    // 编辑列表内容 url:/rest/content/edit 执行post提交

    /**
     * 修改列表信息
     * @param content
     * @return
     */
    //参数
    //    categoryId:4
    //    id:4
    //    title:fafaafaafa
    //    subTitle:gsfgfsgf
    //    titleDesc:fsgfgsfsgf4433534fgsggsfdgfdgdfg
    //    url:www.jd.com
    //    pic:
    //    pic2:
    //    content:<span>faa</span><span>faa</span><span>faa</span>
    @ResponseBody
    @RequestMapping(value = "/edit")
    public Map<String,Object> modify(Content content){
        //修改时间
        content.setUpdated(new Date());

        //执行内容修改
        //  响应数据需要，所以这里可以写一个Map响应数据
        Map<String, Object> dataMap = new HashMap<String,Object>();
        try {
            contentService.updateByIdSelective(content);
            dataMap.put("status",200);
        } catch (Exception e) {
            dataMap.put("status",500);
            e.printStackTrace();
        }
        return dataMap;
    }

    //$.post("/rest/content/delete",params, function(data)  params = {"ids":ids};
    @ResponseBody
    @RequestMapping(value = "delete")
    public Map<String,Object> delete(@RequestParam(value = "ids")List<Object> ids){

        //定义一个Map来封装json数据
        Map<String,Object> dataMap = new HashMap<String,Object>();

        //执行批量删除操作
        try {
            contentService.deleteByIds(ids);
            dataMap.put("status",200);
        } catch (Exception e) {
            dataMap.put("status",500);
            e.printStackTrace();
        }
        return dataMap;
    }
}
