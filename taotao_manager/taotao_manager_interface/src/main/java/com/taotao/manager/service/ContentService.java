package com.taotao.manager.service;

import com.taotao.manager.pojo.Content;
import com.taotao.manager.utils.TaoResult;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.manager.service
 * @date 2018/2/25
 */
public interface ContentService extends BaseService<Content> {

    /**
     * 内容列表展示
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
    TaoResult<Content> queryContent(Long categoryId, Integer page, Integer rows);

    /**
     * 首页大广告轮播图
     * @param taotao_ad_id
     * @return
     */
    String queryAD(Long taotao_ad_id);

    /**
     * 修改内容信息
     * @param content
     * @return
     */
    //int updateSelective(Content content);
}
