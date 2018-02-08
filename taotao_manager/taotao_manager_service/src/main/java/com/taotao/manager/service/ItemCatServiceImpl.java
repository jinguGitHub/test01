package com.taotao.manager.service;

import com.github.pagehelper.PageHelper;
import com.taotao.manager.mapper.ItemCatMapper;
import com.taotao.manager.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.manager.service
 * @date 2018/2/6
 */
@Service

public class ItemCatServiceImpl extends BaseServiceImpl<ItemCat> implements ItemCatService {
    @Override
    public List<ItemCat> queryItemCatByParentId(Long parentId) {
        //设置查询条件
        ItemCat itemCat = new ItemCat();
        itemCat.setParentId(parentId);
        //执行查询
        List<ItemCat> itemCatsList = super.queryListByWhere(itemCat);

        return  itemCatsList;
    }
   /*@Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public List<ItemCat> queryItemCatByPage(Integer page, Integer rows) {
        //设置分页条件
        PageHelper.startPage(page, rows);
        //查询
        List<ItemCat> list = this.itemCatMapper.select(null);
        //this.itemCatMapper.queryItemCatByPage()
        return list;
    }*/
}
