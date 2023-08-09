package com.wf.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wf
 * @date: 2023年08月09日 12:04
 */

@RestController
public class IndexController {


    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
