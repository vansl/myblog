package com.vansl.dto;

import java.util.List;

/**
 * @author: vansl
 * @create: 18-4-15 下午5:54
 */
public class TypeTreeNode {

    private Integer id;
    private String text;
    private List<TypeTreeNode> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<TypeTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TypeTreeNode> children) {
        this.children = children;
    }
}