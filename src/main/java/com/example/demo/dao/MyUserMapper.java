package com.example.demo.dao;

import com.example.demo.pojo.MyUser;


public interface MyUserMapper {
    int deleteByPrimaryKey(String uid);

    int insert(MyUser record);

    int insertSelective(MyUser record);

    MyUser selectByPrimaryKey(String uid);

    MyUser findUser(String userName);

    int updateByPrimaryKeySelective(MyUser record);

    int updateByPrimaryKey(MyUser record);


}