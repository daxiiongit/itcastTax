package com.sunyanxiong.core.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Daxiong on 2016/9/11 0011.
 */

/*
*  创建基类的目的：定义常用方法，方便其他模块来使用该模块，功能重用。
*  使用泛型的目的：在这里使用泛型是为了在各个模块使用该模块时，需要针对不同的实体来进行增删改差操作。
*
* */
public interface BaseDao<T> {

    // 新增
    public void save(T entity);
    // 修改
    public void update(T entity);
    // 根据 id 删除
    public void delete(Serializable id);
    // 根据 id 查找
    public T findObjectById(Serializable id);
    // 找寻列表
    public List<T> findObjects();

}
