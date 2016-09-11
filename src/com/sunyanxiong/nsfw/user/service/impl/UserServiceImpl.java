package com.sunyanxiong.nsfw.user.service.impl;

import com.sunyanxiong.nsfw.user.dao.UserDao;
import com.sunyanxiong.nsfw.user.entity.User;
import com.sunyanxiong.nsfw.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Daxiong on 2016/9/11 0011.
 */

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void save(User user) {
         userDao.save(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteUserById(Serializable id) {
        userDao.delete(id);
    }

    @Override
    public User findUserById(Serializable id) {
        return userDao.findObjectById(id);
    }

    @Override
    public List<User> findUsers() {
        return userDao.findObjects();
    }
}
