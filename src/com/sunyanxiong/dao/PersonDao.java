package com.sunyanxiong.dao;

import com.sunyanxiong.entity.Person;

/**
 * Created by Daxiong on 2016/9/11 0011.
 */
public interface PersonDao {

    // 测试声明式事务：插入数据
    public void insertPerson(Person person);

    // 测试声明式事务：查找数据
    public Person findPersonById(int id);

}
