package com.sunyanxiong.service.impl;

import com.sunyanxiong.dao.PersonDao;
import com.sunyanxiong.entity.Person;
import com.sunyanxiong.service.PersonService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Daxiong on 2016/9/9 0009.
 */

@Service
public class PersonServiceImpl implements PersonService{

    private PersonDao personDao;

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public void save() {
        System.out.println("personService save...");
    }

    @Override
    public void insertPerson(Person person) {
        personDao.insertPerson(person);
        // 测试声明式事务
        /*int i = 1/0;*/
    }

    @Override
    public Person findPersonById(int id) {
        // 测试声明式事务
        /*Person person = new Person();
        person.setName("111");
        this.insertPerson(person);*/

        return personDao.findPersonById(id);
    }
}
