package com.vansl.dao;

/**
 * @author: vansl
 * @create: 18-4-13 下午5:00
 */
public interface BlogDao {

    // 通过用户名查询总记录数
    public Long getTotal(String username);
}
