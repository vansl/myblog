package com.vansl.entity;

import java.util.Date;

/**
 * @author: vansl
 * @create: 18-4-21 下午8:07
 */
public class BlogComment {

    private Integer id;     //评论ID
    private Date time;      //发表时间
    private String name;    //评论者
    private String contact;    //联系方式
    private String ip;         //评论者ip地址
    private String content;    //评论内容
    private Integer blog_id;   //博客ID

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(Integer blog_id) {
        this.blog_id = blog_id;
    }
}
