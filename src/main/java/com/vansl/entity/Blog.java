package com.vansl.entity;

import java.util.Date;

/**
 * @author: vansl
 * @create: 18-4-25 下午10:34
 */
public class Blog {

    private Integer id;         //博客ID
    private String title;       //博客标题
    private Date time;          //上传时间
    private Integer published;  //是否已发表，0已发表，1未发表（在草稿箱）
    private String content;     //博客正文（包含html格式）
    private String text;     //博客正文（纯文本）
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

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
