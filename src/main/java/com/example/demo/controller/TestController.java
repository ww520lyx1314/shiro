package com.example.demo.controller;

import com.example.demo.config.ApplicationContextRegister;
import com.example.demo.domain.UserDO;
import com.example.demo.server.MenuService;
import com.example.demo.server.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by lenovo on 2019/4/2.
 */
@Controller
@RequestMapping("")
public class TestController {
    @Autowired
    private UserService userService;

    @RequiresPermissions("sys:menu:menu1111")
    @RequestMapping("test")
    @ResponseBody
    public Set<String>  test(){
        List<UserDO> list=userService.list(new HashMap());MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
        UserDO usrDo = (UserDO)SecurityUtils.getSubject().getPrincipal();
        Set<String> perms = menuService.listPerms(usrDo.getUserId());

        return perms;
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    String loginSubmit (String username, String password) {

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);;
            return "index";
        } catch (AuthenticationException e) {
            return "login";
        }
    }
}
