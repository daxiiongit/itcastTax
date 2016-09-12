package com.sunyanxiong.nsfw.user.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sunyanxiong.nsfw.user.entity.User;
import com.sunyanxiong.nsfw.user.service.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.UUID;


/**
 * Created by Daxiong on 2016/9/11 0011.
 */
public class UserAction extends ActionSupport {

    @Resource
    private UserService userService;

    private List<User> userList;

    private User user;

    // 批量操作属性
    private String [] selectedRow;

    // 1.获取用户头像
    // (1)头像文件
    private File headImg;
    // (2)头像类型
    private String headImgContentType;
    // (3)头像名称
    private String headImgFileName;

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

        try {
            if(user != null){
                //处理头像
                if(headImg != null){
                    //1、保存头像到upload/user
                    //获取保存路径的绝对地址
                    String filePath = ServletActionContext.getRequest().getServletContext().getRealPath("/upload/user/");
                    String fileName = UUID.randomUUID().toString().replaceAll("-", "") + headImgFileName.substring(headImgFileName.lastIndexOf("."));
                    //复制文件
                    FileUtils.copyFile(headImg, new File(filePath, fileName));

                    //2、设置用户头像路径
                    user.setHeadImg("user/" + fileName);


                    //************************第二种方式保存成功***************************//
                    /*InputStream is = new FileInputStream(headImg);  // 文件输入流
                    String filePath = ServletActionContext.getServletContext().getRealPath("/upload/user/"); //设置上传文件目录
                    String fileName = UUID.randomUUID().toString().replaceAll("-", "") + headImgFileName.substring(headImgFileName.lastIndexOf("."));
                    File toFile = new File(filePath,fileName); // 设置目标文件
                    OutputStream os = new FileOutputStream(toFile);   // 创建一个输出流
                    byte [] buffer = new byte[1024];  // 设置缓存
                    int length = 0;

                    // 读取 headImg 文件输出到 toFile 文件中。
                    while((length = is.read(buffer)) > 0){
                        os.write(buffer,0,length);
                    }

                    // 关闭输入流
                    is.close();
                    // 关闭输出流
                    os.close();*/
                    //**************************************************//

                }
                userService.save(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }
    // 进入编辑页面
    public String editUI(){
        if (user != null && user.getId() != null){
            // 在进入编辑页面的时候需要将查询到的数据保存到 user 对象中。
            user = userService.findUserById(user.getId());
        }
        return "editUI";
    }
    // 执行编辑操作
    public String edit(){

        try {
            if(user != null){
                //处理头像
                if(headImg != null){
                    //1、保存头像到upload/user
                    //获取保存路径的绝对地址
                    String filePath = ServletActionContext.getRequest().getServletContext().getRealPath("/upload/user/");
                    String fileName = UUID.randomUUID().toString().replaceAll("-", "") + headImgFileName.substring(headImgFileName.lastIndexOf("."));
                    //复制文件
                    FileUtils.copyFile(headImg, new File(filePath, fileName));

                    //2、设置用户头像路径
                    user.setHeadImg("user/" + fileName);
                }
                userService.update(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
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


    // 批量删除操作
    public String deleteSelected(){
        if (selectedRow != null){
            for (String id : selectedRow){
                userService.deleteUserById(id);
            }
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

    public String[] getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(String[] selectedRow) {
        this.selectedRow = selectedRow;
    }

    public String getHeadImgFileName() {
        return headImgFileName;
    }

    public void setHeadImgFileName(String headImgFileName) {
        this.headImgFileName = headImgFileName;
    }

    public String getHeadImgContentType() {
        return headImgContentType;
    }

    public void setHeadImgContentType(String headImgContentType) {
        this.headImgContentType = headImgContentType;
    }

    public File getHeadImg() {
        return headImg;
    }

    public void setHeadImg(File headImg) {
        this.headImg = headImg;
    }
}
