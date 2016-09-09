package com.sunyanxiong.service.impl;

import com.sunyanxiong.service.PersonService;
import org.springframework.stereotype.Service;

/**
 * Created by Daxiong on 2016/9/9 0009.
 */

@Service
public class PersonServiceImpl implements PersonService{

    @Override
    public void save() {
        System.out.println("personService save...");
    }
}
