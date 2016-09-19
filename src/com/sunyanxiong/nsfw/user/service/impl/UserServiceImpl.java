package com.sunyanxiong.nsfw.user.service.impl;

import com.sunyanxiong.core.util.ExcelUtil;
import com.sunyanxiong.nsfw.user.dao.UserDao;
import com.sunyanxiong.nsfw.user.entity.User;
import com.sunyanxiong.nsfw.user.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.math.BigDecimal;
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

    @Override
    public void importUserExcel(File userExcel,String userExcelFileName,UserService userService){

        try {
            FileInputStream fileInputStream = new FileInputStream(userExcel);
            boolean is03Excel = userExcelFileName.matches("^.+\\.(?i)(xls)$");
            //1、读取工作簿
            Workbook workbook = is03Excel ? new HSSFWorkbook(fileInputStream):new XSSFWorkbook(fileInputStream);
            //2、读取工作表
            Sheet sheet = workbook.getSheetAt(0);
            //3、读取行
            if(sheet.getPhysicalNumberOfRows() > 2){
                for(int k = 2; k < sheet.getPhysicalNumberOfRows(); k++){
                    //4、读取单元格
                    Row row = sheet.getRow(k);
                    User user = new User();
                    //用户名
                    Cell cell0 = row.getCell(0);
                    user.setName(cell0.getStringCellValue());
                    //帐号
                    Cell cell1 = row.getCell(1);
                    user.setAccount(cell1.getStringCellValue());
                    //所属部门
                    Cell cell2 = row.getCell(2);
                    user.setDept(cell2.getStringCellValue());
                    //性别
                    Cell cell3 = row.getCell(3);
                    user.setGender(cell3.getStringCellValue().equals("男"));
                    //手机号
                    String mobile = "";
                    Cell cell4 = row.getCell(4);
                    try {
                        mobile = cell4.getStringCellValue();
                    } catch (Exception e) {
                        double dMobile = cell4.getNumericCellValue();
                        mobile = BigDecimal.valueOf(dMobile).toString();
                    }
                    user.setMobile(mobile);

                    //电子邮箱
                    Cell cell5 = row.getCell(5);
                    user.setEmail(cell5.getStringCellValue());
                    //生日
                    Cell cell6 = row.getCell(6);
                    if(cell6.getDateCellValue() != null){
                        user.setBirthday(cell6.getDateCellValue());
                    }
                    //默认用户密码为 123456
                    user.setPassword("123456");
                    //默认用户状态为 有效
                    user.setState(User.USER_STATE_VALID);

                    //5、保存用户
                    userService.save(user);
                }
            }
            workbook.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        /*try {
                // 1.获取文件
                FileInputStream inputStream = new FileInputStream(userExcel);
                boolean is03Excel = userExcelFileName.matches("^.+\\.(?i)((xls))$");
                // 2.获取工作薄
                Workbook workbook = is03Excel ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
                // 3.获取工作表
                Sheet sheet = workbook.getSheetAt(0);
                // 4.获取行
                if (sheet.getPhysicalNumberOfRows() > 2) {
                    User user = new User();
                    for (int k = 2; k < sheet.getPhysicalNumberOfRows(); k++) {
                        Row row = sheet.getRow(k);
                        // 5.读取单元格
                        // 用户名
                        Cell cell0 = row.getCell(0);
                        user.setName(cell0.getStringCellValue());

                        // 帐号
                        Cell cell1 = row.getCell(1);
                        user.setAccount(cell1.getStringCellValue());

                        // 所属部门
                        Cell cell2 = row.getCell(2);
                        user.setDept(cell2.getStringCellValue());

                        // 性别
                        Cell cell3 = row.getCell(3);
                        user.setGender(cell3.equals("男"));

                        // 手机号码
                        String mobile = "";
                        Cell cell4 = row.getCell(4);
                        try {
                            mobile = cell4.getStringCellValue();
                        } catch (Exception e) {
                            double douMobile = cell4.getNumericCellValue();
                            mobile = BigDecimal.valueOf(douMobile).toString();
                        }
                        user.setMobile(mobile);
                        // 电子邮箱
                        Cell cell5 = row.getCell(5);
                        user.setEmail(cell5.getStringCellValue());

                        // 生日
                        Cell cell6 = row.getCell(6);
                        if (cell6.getDateCellValue() != null) {
                            user.setBirthday(cell6.getDateCellValue());
                        }

                        // 设置默认密码
                        user.setPassword("123456");
                        user.setState(user.USER_STATE_VALID);
                        //save(user);
                    }
                }
            workbook.close();
            inputStream.close();
            }catch(Exception e){
                e.printStackTrace();
            }*/

        }
}
