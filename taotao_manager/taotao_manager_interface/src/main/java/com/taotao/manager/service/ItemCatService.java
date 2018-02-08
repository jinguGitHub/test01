package com.taotao.manager.service;

import com.github.abel533.mapper.Mapper;
import com.taotao.manager.pojo.ItemCat;

import java.util.List;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.manager.service
 * @date 2018/2/6
 */
public interface ItemCatService extends BaseService<ItemCat>{
    /****
     * 根据商品父类目id查询数据
     * @param parentId
     * @return
     */
    List<ItemCat> queryItemCatByParentId(Long parentId);
    /****
     *分页查询商品类目
     * @param page
     * @param rows
     * @return
     */
    //List<ItemCat> queryItemCatByPage(Integer page,Integer rows);
}
