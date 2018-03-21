package com.taotao.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.pojo.Item;
import com.taotao.manager.pojo.ItemDesc;
import com.taotao.manager.utils.TaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public TaoResult<Item> queryItemList(Integer page, Integer rows) {
        // 设置分页数据
        PageHelper.startPage(page, rows);

        List<Item> list = super.queryListByWhere(null);

        // 获取分页的详细数据
        PageInfo<Item> pageInfo = new PageInfo<>(list);

        // 封装返回对象
        TaoResult<Item> taoResult = new TaoResult<>();
        taoResult.setTotal(pageInfo.getTotal());
        taoResult.setRows(list);

        return taoResult;
    }
}
