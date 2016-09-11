package com.sunyanxiong.dao;

import com.sunyanxiong.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.support.DaoSupport;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Created by Daxiong on 2016/9/11 0011.
 */
public class PersonDaoImpl extends HibernateDaoSupport implements PersonDao {

    private SessionFactory sessionFactory;

    @Override
    public void insertPerson(Person person) {
        getHibernateTemplate().save(person);
    }

    @Override
    public Person findPersonById(int id) {
        Person person = (Person) getHibernateTemplate().get(Person.class,id);
        return person;
    }

}
