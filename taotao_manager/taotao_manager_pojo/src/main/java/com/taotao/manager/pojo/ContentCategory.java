package com.taotao.manager.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_content_category")
public class ContentCategory extends BasePojo {

    //    1
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    2
    @Column(name = "parent_id")
    private Long parentId;

    //    3
    private String name;

    //    4
    private Integer status;

    //    5
    @Column(name = "sort_order")
    private Integer sortOrder;

    //    6
    @Column(name = "is_parent")
    private Boolean isParent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    // 1.扩展字段，用于EasyUI中tree结构
    public String getText() {
        return getName();
    }

    // 2.扩展字段，用于EasyUI中tree结构
    public String getState() {
        return getIsParent() ? "closed" : "open";
    }

}
