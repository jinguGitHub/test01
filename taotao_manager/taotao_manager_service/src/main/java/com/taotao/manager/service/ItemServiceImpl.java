package com.taotao.manager.service;

import com.taotao.manager.pojo.Item;
import com.taotao.manager.pojo.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.manager.service
 * @date 2018/2/8
 */
@Service
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {
    @Autowired
    private ItemDescService itemDescService;

    @Override
    public void saveItem(Item item, String desc) {
        // 保存商品
        item.setStatus(1);
        super.save(item);

        // 保存商品描述
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);

        this.itemDescService.save(itemDesc);
    }
}
