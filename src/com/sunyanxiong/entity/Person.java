package com.sunyanxiong.entity;

import java.io.Serializable;

/**
 * Created by Daxiong on 2016/9/9 0009.
 */
public class Person implements Serializable{

    private int id;
    private String name;

    public Person(){}

    public void hello(){
        System.out.println(name);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
