package com.sunyanxiong.action;

import com.sunyanxiong.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by Daxiong on 2016/9/9 0009.
 */
public class PersonAction {

    private PersonService personService;

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public String execute(){
        personService.save();
        return "success";
    }

}
