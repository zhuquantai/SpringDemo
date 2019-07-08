package com.example.demo.shiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * shiro configuration class
 */
@Configuration
public class ShiroConfiguration {
    /**
     * 创建ShiroFilterFactoryBean来配置过滤规则
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        /*
        anon:表示可以匿名使用。
        authc:表示需要认证(登录)才能使用，没有参数
        roles：参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，例如admins/user/**=roles["admin,guest"],每个参数通过才算通过，相当于hasAllRoles()方法。
        perms：参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，例如/admins/user/**=perms["user:add:*,user:modify:*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。
        rest：根据请求的方法，相当于/admins/user/**=perms[user:method] ,其中method为post，get，delete等。
        port：当请求的url的端口不是8081是跳转到schemal://serverName:8081?queryString,其中schmal是协议http或https等，serverName是你访问的host,8081是url配置里port的端口，queryString是你访问的url里的？后面的参数。
        authcBasic：没有参数表示httpBasic认证
        ssl:表示安全的url请求，协议为https
        user:当登入操作时不做检查
         */
        Map<String, String> filterMap = new HashMap<String, String>();
        filterMap.put("/one", "authc");
        filterMap.put("/two", "perms[user:two]");
        //没有登录的用户请求需要登录的页面时自动跳转到登录页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //没有权限默认跳转的页面，登录的用户访问了没有被授权的资源自动跳转到的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        //登录成功默认跳转页面
        //shiroFilterFactoryBean.setSuccessUrl("/success");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    @Bean
    public UserRealm getUserRealm(){
        return new UserRealm();
    }
}
