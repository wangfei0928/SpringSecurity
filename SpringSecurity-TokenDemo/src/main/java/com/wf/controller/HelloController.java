package com.wf.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author: wf
 * @date: 2023年08月03日 16:41
 */

@RestController
public class HelloController {


    @RequestMapping("/hello")
    @PreAuthorize("hasAnyAuthority('test333')")
    public String hello(){
        return "hello";
    }
}
