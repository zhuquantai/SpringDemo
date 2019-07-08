package com.example.demo.shiro.config;

import com.example.demo.pojo.MyUser;
import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("doGetAuthorizationInfo++");
        Subject subject = SecurityUtils.getSubject();
        MyUser myUser = (MyUser) subject.getPrincipal();

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //这里可以通过user从数据库获取其权限信息
        simpleAuthorizationInfo.addStringPermission("user:one");
        return simpleAuthorizationInfo;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("doGetAuthenticationInfo++");
        UsernamePasswordToken user = (UsernamePasswordToken) authenticationToken;
        String username = user.getUsername();
        String password = Arrays.toString(user.getPassword());
        MyUser myUser = userService.findMyUser(username);
        if (myUser != null && myUser.getPassword().equals(password))
            return new SimpleAuthenticationInfo(myUser, myUser.getPassword(), "");

        return null;
    }
}
