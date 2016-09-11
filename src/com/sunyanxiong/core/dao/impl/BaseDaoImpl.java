package com.sunyanxiong.core.dao.impl;

import com.sunyanxiong.core.dao.BaseDao;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Daxiong on 2016/9/11 0011.
 */

/*
* 该类采用 abstract 关键字作用：为了不让其他类可以直接实例化操作
* */

public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    /*
    * clazz 的作用：利用反射机制来获取相应的实体类
    * */
    private Class<T> clazz;

    public BaseDaoImpl(){
        // 返回泛型父类，获取泛型参数的类型
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public void save(Object entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public void update(Object entity) {
        getHibernateTemplate().update(entity);
    }

    @Override
    public void delete(Serializable id) {
        getHibernateTemplate().delete(findObjectById(id));
    }

    @Override
    public T findObjectById(Serializable id) {
        return getHibernateTemplate().get(clazz,id);
    }

    @Override
    public List<T> findObjects() {
        Query query = getSession().createQuery("FROM " + clazz.getSimpleName());
        return query.list();
    }
}
