package com.vansl.dto;

import java.util.Date;

/**
 * @author: vansl
 * @create: 18-4-25 下午11:26
 */
public class BlogData {

    private Integer id;         //博客ID
    private String title;       //博客标题
    private Date time;          //发表时间
    private Integer pv;         //博客点击量
    private Integer published;    //博客是否已发表(0已发表,1未发表)
    private Integer typeId;    //博客分类id
    private String typeName;    //博客分类名称

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

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published= published;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypePId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


}
