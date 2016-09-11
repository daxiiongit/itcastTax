package com.sunyanxiong.service;

import com.sunyanxiong.entity.Person;

/**
 * Created by Daxiong on 2016/9/9 0009.
 */
public interface PersonService {

    public void save();


    // 测试声明式事务：插入数据
    public void insertPerson(Person person);

    // 测试声明式事务：查找数据
    public Person findPersonById(int id);

}
