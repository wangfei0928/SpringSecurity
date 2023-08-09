package com.wf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: wf
 * @date: 2023年08月09日 15:00
 */

@Controller
public class LoginController {
    @RequestMapping("/login.html") //注意此处是login.html而不是login
    public String login() {
        return "login";//封装为login.html
    }
}

