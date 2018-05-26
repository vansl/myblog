package com.vansl.dao;

import com.vansl.entity.User;

/**
 * @author: vansl
 * @create: 18-5-20 下午11:11
 */
public interface UserDao {

    // 通过用户名查询用户id
    Integer selectIdByUserName(String userName);

    // 通过用户名查询密码
    String selectPasswordByUserName(String userName);

    // 通过用户名查询角色
    String selectRoleByUserName(String userName);

    // 新增用户
    Integer insertUser(User user);

    // 删除用户
    Integer deleteUser(Integer id);

    // 更新用户
    Integer updateUser(User user);
}
