package com.example.demo.config;

import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * Created by lenovo on 2019/4/2.
 */
@Configuration
public class shiroConfig {
    //1、配置LifecycleBeanPostProcessor管理生命周期
    //2、配置ShiroFilterFactoryBean设置哪些路径需要认证，哪些不需要
    //3、开启shiro Aop 注解支持 AuthorizationAttributeSourceAdvisor
    //4、配置SecurityManager管理自定义Realm、session、缓存
    //Realm实现登陆认证、授权
    //session  设置超时时间、设置sessionDAO（session缓存地方可以使用shiro自带也可以使用radis）、设置session监控
    //缓存  可以设置shiro自定义的，也可以设置radis

    //session超时时间
    @Value("${server.session-timeout}")
    private int tomcatTimeout;

    //管理shiro bean生命周期最好配置上
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean("shiroFilter")
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/docs/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/upload/**", "anon");
        filterChainDefinitionMap.put("/files/**", "anon");/*
        filterChainDefinitionMap.put("/logout", "logout");*/
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/blog/open/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(userRealm());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(ehCacheManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }
    //自定义实现Realm
    @Bean
    UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }
    //使用shiro自带的缓存器
    @Bean
    public SessionDAO sessionDAO() {
        return new MemorySessionDAO();
    }

    /**
     * shiro session的管理
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //session超时时间
        sessionManager.setGlobalSessionTimeout(tomcatTimeout * 1000);
        sessionManager.setSessionDAO(sessionDAO());
        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
        sessionManager.setSessionListeners(listeners);
        return sessionManager;
    }
    //ehCahe缓存使用自己定义的
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManager(cacheManager());
        return em;
    }

    @Bean("cacheManager2")
    CacheManager cacheManager(){
        return CacheManager.create();
    }


}
