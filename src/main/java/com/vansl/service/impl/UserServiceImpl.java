package com.vansl.service.impl;

import com.vansl.dao.UserDao;
import com.vansl.entity.User;
import com.vansl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: vansl
 * @create: 18-5-20 下午11:30
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public Integer selectIdByUserName(String userName) {
        return userDao.selectIdByUserName(userName);
    }

    @Override
    public String selectPasswordByUserName(String userName) {
        return userDao.selectPasswordByUserName(userName);
    }

    @Override
    public String selectRoleByUserName(String userName) {
        return userDao.selectRoleByUserName(userName);
    }

    @Override
    public Integer insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public Integer deleteUser(Integer id) {
        return deleteUser(id);
    }

    @Override
    public Integer updateUser(User user) {
        return updateUser(user);
    }
}