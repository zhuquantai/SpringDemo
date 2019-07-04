package com.example.demo.controller;

import com.example.demo.dao.MyUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private MyUserMapper myUserMapper;

    @RequestMapping("/")
    public String helloworld(){
        return myUserMapper.selectByPrimaryKey("1").getPassword();
    }
}
