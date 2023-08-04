package com.wf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 功能描述
 *
 * @author: wf
 * @date: 2023年08月03日 16:40
 */
@SpringBootApplication
@MapperScan("com.wf.mapper")
public class TokenApplication {


    public static void main(String[] args) {
        SpringApplication.run(TokenApplication.class,args);
    }
}
