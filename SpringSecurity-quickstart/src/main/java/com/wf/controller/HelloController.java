package com.wf.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述
 *
 * @author: wf
 * @date: 2023年08月03日 16:05
 */

@RestController
public class HelloController {


    // 系统id  key 密钥，参数    md5

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
