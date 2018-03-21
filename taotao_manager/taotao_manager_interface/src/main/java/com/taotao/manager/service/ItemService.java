package com.taotao.manager.service;

import com.taotao.manager.pojo.Item;
import com.taotao.manager.pojo.ItemDesc;
import com.taotao.manager.utils.TaoResult;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.manager.service
 * @date 2018/2/8
 */
public interface ItemService extends BaseService<Item> {
    /****
     * 保存商品
     * @param item
     */
    public void saveItem(Item item,String desc);

    /**
     * 查询商品列表
     * @param page
     * @param rows
     * @return
     */
    TaoResult<Item> queryItemList(Integer page, Integer rows);

}
