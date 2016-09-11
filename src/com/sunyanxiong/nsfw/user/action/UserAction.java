package com.sunyanxiong.nsfw.user.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sunyanxiong.nsfw.user.entity.User;
import com.sunyanxiong.nsfw.user.service.UserService;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by Daxiong on 2016/9/11 0011.
 */
public class UserAction extends ActionSupport {

    @Resource
    private UserService userService;

    private List<User> userList;

    private User user;

    // 显示列表
    public String listUI(){
        userList = userService.findUsers();
        return "listUI";
    }
    // 进入新增页面
    public String addUI(){
        return "addUI";
    }

    // 执行增加操作
    public String add(){
        if(user != null){
            userService.save(user);
        }
        return SUCCESS;
    }
    // 进入编辑页面
    public String editUI(){
        if (user != null && user.getId() != null){
            userService.findUserById(user.getId());
        }
        return "editUI";
    }
    // 执行编辑操作
    public String edit(){
        if (user != null){
            userService.update(user);
        }
        return SUCCESS;
    }
    // 根据id删除
    public String delete(){
        if (user != null && user.getId() != null){
            userService.deleteUserById(user.getId());
        }
        return SUCCESS;
    }


    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
