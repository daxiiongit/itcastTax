package com.sunyanxiong.test;

import com.sunyanxiong.entity.Person;
import com.sunyanxiong.service.PersonService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Daxiong on 2016/9/10 0010.
 */
public class HibernateTest {

    private ApplicationContext ctx;
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    private PersonService personService;

    @Before
    public void before(){
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        personService = (PersonService) ctx.getBean("personService");
        sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @Test
    public void test(){
        Person person = ctx.getBean(Person.class);
        person.hello();

    }

    @Test
    public void testSave(){
        Person person = new Person();
        person.setName("sunyanxiong");
        session.save(person);
    }

    @Test
    public void testInsertPerson(){
        Person person = new Person();
        person.setName("hello");
        personService.insertPerson(person);
    }

    @Test
    public void testFindPersonById(){
        Person person = personService.findPersonById(2);
        System.out.println(person);
    }

    @After
    public void after(){
        transaction.commit();
        session.close();
    }
}
