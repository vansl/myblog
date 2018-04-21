package com.vansl.entity;

/**
 * @author: vansl
 * @create: 18-4-12 下午4:45
 */
public class BlogType {

    private Integer id;     // 分类ID
    private String typeName;        // 分类名称
    private Integer parentId;       // 父分类ID
    private Integer userId;         // 用户ID

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
