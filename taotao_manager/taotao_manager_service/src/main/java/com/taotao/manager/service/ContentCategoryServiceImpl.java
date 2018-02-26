package com.taotao.manager.service;

import com.taotao.manager.mapper.ContentCategoryMapper;
import com.taotao.manager.pojo.ContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.manager.service
 * @date 2018/2/25
 */
@Service
public class ContentCategoryServiceImpl extends BaseServiceImpl<ContentCategory> implements  ContentCategoryService{

   @Autowired
    private ContentCategoryMapper contentCategoryMapper;

    @Override
    public List<ContentCategory> queryContentCategoryByParentId(Long id) {

        //设置父类ID
        ContentCategory where = new ContentCategory();
        where.setParentId(id);

        //根据父类id查询出分类信息
        List<ContentCategory> contentCategories = super.queryListByWhere(where);

        return contentCategories;
    }

    @Override
    public ContentCategory add(ContentCategory contentCategory) {
        contentCategory.setStatus(1);
        contentCategory.setIsParent(false);
        super.saveSelective(contentCategory);

        ContentCategory parent = super.queryById(contentCategory.getParentId());
        //当前添加的节点为叶子节点是,把这个叶子父节点为非叶子节点
        if (!parent.getIsParent()){
            parent.setIsParent(true);
            super.updateByIdSelective(parent);
        }
        //这里别忘了返回数据
        return contentCategory;
    }

    /***
     * 修改  内容类目
     *
     *
     */
    @Override
    public int updateContentCategory(ContentCategory contentCategory) {
        contentCategory.setUpdated(new Date());
        return contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
    }

    /***
     * 删除内容类目
     * 同时递归删除它的子类目
     * 还要判断当前被删除的类目的父类下是否还有子类目
     *        如果没有，则需要修改它的状态isParent=false
     */
    @Override
    public void deleteContentCategory(ContentCategory category) {
        //找出当前节点下所有的子节点
        //要删除的id列表
        List<Object> ids = new ArrayList<>();
        //先添加本身
        ids.add(category.getId());
        //查询所有子节点
        queryIds(category.getId(),ids);

        //删除所有节点
        super.deleteByIds(ids);

        //检查当前要删除的节点的父节点是否还有子节点
        ContentCategory where = new ContentCategory();
        where.setParentId(category.getParentId());
        Integer count = super.queryCountByWhere(where);
        //如果当前父节点没有子节点，更新父节点状态
        if(count < 1){
            ContentCategory parent = new ContentCategory();
            parent.setId(category.getParentId());
            parent.setIsParent(false);
            super.updateByIdSelective(parent);
        }
    }

    /**
     * 查询所有子子节点
     * @param parentId 要搜索的根
     * @param ids 记录查找到的节点
     */
    private void queryIds(Long parentId,List<Object> ids){
        ContentCategory parent = new ContentCategory();
        parent.setParentId(parentId);
        List<ContentCategory> categories = super.queryListByWhere(parent);
        for (ContentCategory category : categories) {
            //如果当前的子节点为父母节点时，递归调用本身
            if (category.getIsParent()) {
                queryIds(category.getId(),ids);
            }
            //记录要删除的节点
            ids.add(category.getId());
        }
    }
}
