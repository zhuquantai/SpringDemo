package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/one")
    public String getOne(){
        return "/one";
    }

    @RequestMapping("/login")
    public String login(){
        return "/login";
    }

//    @RequestMapping("/error")
//    public String error(){
//        return "/error";
//    }
}
