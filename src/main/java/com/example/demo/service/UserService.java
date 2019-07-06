package com.example.demo.service;

import com.example.demo.dao.MyUserMapper;
import com.example.demo.pojo.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    MyUserMapper myUserMapper;

    public MyUser findMyUser(String username){
        return myUserMapper.findUser(username);
    }
}
