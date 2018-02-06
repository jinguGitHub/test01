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
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public List<ItemCat> queryItemCatByPage(Integer page, Integer rows) {
        //设置分页条件
        PageHelper.startPage(page, rows);
        //查询
        List<ItemCat> list = this.itemCatMapper.select(null);
        //this.itemCatMapper.queryItemCatByPage()
        return list;
    }
}
