package com.taotao.manager.service;

import com.taotao.manager.pojo.ContentCategory;

import java.util.List;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.manager.service
 * @date 2018/2/25
 */
public interface ContentCategoryService extends BaseService<ContentCategory>{

    List<ContentCategory> queryContentCategoryByParentId(Long id);

    ContentCategory add(ContentCategory contentCategory);

    int updateContentCategory(ContentCategory contentCategory);

    void deleteContentCategory(ContentCategory contentCategory);
}
