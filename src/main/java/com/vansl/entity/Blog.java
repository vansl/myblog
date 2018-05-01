package com.vansl.entity;

import java.util.Date;

/**
 * @author: vansl
 * @create: 18-4-25 下午10:34
 */
public class Blog {

    private Integer id;         //博客ID
    private String title;       //博客标题
    private Date time;          //发表时间
    private String content;     //博客正文
    private Integer pv;         //博客点击量
    private Integer userId;     //用户ID
    private Integer typeId;     //分类ID

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
