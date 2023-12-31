package com.wf.controller;

import com.wf.domain.ResponseResult;
import com.wf.domain.User;
import com.wf.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wf
 * @date: 2023年08月03日 20:22
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        //登录
        return loginService.login(user);

    }


    @RequestMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

}
