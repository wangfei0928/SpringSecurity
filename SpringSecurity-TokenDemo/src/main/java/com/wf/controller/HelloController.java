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
//    @PreAuthorize("hasAnyAuthority('system:dept:list','admin','test')")
//    @PreAuthorize("hasAuthority('system:dept:list')")
//    @PreAuthorize("hasRole('system:dept:list')")   //自动拼接了"ROLE_"前缀
    @PreAuthorize("@ex.hasAuthority('system:dept:list')")
    public String hello(){
        return "hello";
    }
}
