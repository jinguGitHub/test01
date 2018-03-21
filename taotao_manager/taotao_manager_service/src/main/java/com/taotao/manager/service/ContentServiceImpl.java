package com.taotao.manager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.mapper.ContentMapper;
import com.taotao.manager.pojo.Content;
import com.taotao.manager.redis.RedisUtils;
import com.taotao.manager.utils.TaoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.manager.service
 * @date 2018/2/25
 */
@Service
public class ContentServiceImpl extends BaseServiceImpl<Content> implements ContentService {

    @Override
    public TaoResult<Content> queryContent(Long categoryId, Integer page, Integer rows) {
        TaoResult<Content> result = new TaoResult<>();

        //设置分页参数
        PageHelper.startPage(page, rows);

        //设置查询条件
        Content where = new Content();
        where.setCategoryId(categoryId);

        //设置每页展示行数rows
        List<Content> contents = super.queryListByWhere(where);
        result.setRows(contents);

        //查询总页数 total
        PageInfo<Content> info = new PageInfo<Content>(contents);
        result.setTotal(info.getTotal());

        return result;
    }


    @Autowired
    private ContentMapper contentMapper;

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private RedisUtils redisUtils;


    @Value("${TAOTAO_PORTAL_AD_ID}")
    private String TAOTAO_PORTAL_AD_ID;

    @Override
    public String queryAD(Long categoryId) {
        // 1. 从缓存中命中
        // 为了很好的管理和维护redis，需要redis的key是有意义的
        try {
            String json = redisUtils.get(TAOTAO_PORTAL_AD_ID);
            // 判断是否为空，如果不为空表示命中了，直接返回
            if (StringUtils.isNotBlank(json)) {
                return json;
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // 2. 如果没有命中，执行原有逻辑，查MySQL
        // 从数据库查询大广告需要的数据，其实就是查询CategoryId为4的数据
        // 根据分类id查询内容
        Content param = new Content();
        param.setCategoryId(categoryId);
        List<Content> list = super.queryListByWhere(param);
        // 遍历内容，把内容封装到List<Map>中，根据前端数据格式进行封装
        // 声明容器存放内容
        List<Map<String,Object>> result = new ArrayList<>();
        for (Content content : list) {
            Map<String,Object> map = new HashMap<>();
            map.put("srcB",content.getPic());
            map.put("height",240);
            map.put("alt","");
            map.put("width",670);
            map.put("src",content.getPic());
            map.put("widthB",550);
            map.put("href",content.getUrl());
            map.put("heightB",240);

            //把封装好的map放到result容器中
            result.add(map);
        }
        String json = "";
        try {
            json = mapper.writeValueAsString(result);
            //设置有效时间
            //redisUtils.set(TAOTAO_PORTAL_AD_KEY, json, 60 * 60 * 24 * 7);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            // 3. 把查到的数据放到redis中
            this.redisUtils.set(TAOTAO_PORTAL_AD_ID, json, 60 * 60 * 24 * 7);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return json;
    }
}
