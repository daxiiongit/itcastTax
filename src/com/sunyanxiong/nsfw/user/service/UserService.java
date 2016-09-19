package com.sunyanxiong.nsfw.user.service;

import com.sunyanxiong.nsfw.user.entity.User;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Daxiong on 2016/9/11 0011.
 */
public interface UserService {

    // 新增
    public void save(User user);
    // 更新
    public void update(User user);
    // 根据id删除
    public void deleteUserById(Serializable id);
    // 根据id查找
    public User findUserById(Serializable id);
    // 查询列表
    public List<User> findUsers();

    // 导入 Excel 表
    public void importUserExcel(File userExcel,String userExcelFileName,UserService userService) throws Exception;

}
