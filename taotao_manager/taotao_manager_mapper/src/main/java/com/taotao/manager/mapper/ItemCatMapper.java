package com.taotao.manager.mapper;

import com.github.abel533.mapper.Mapper;
import com.taotao.manager.pojo.ItemCat;

import java.util.List;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.manager.mapper
 * @date 2018/2/6
 */
public interface ItemCatMapper extends Mapper<ItemCat> {
    /****
     *分页查询商品类目
     * @param page
     * @param rows
     * @return
     */
    List<ItemCat> queryItemCatByPage(Integer page,Integer rows);
}
