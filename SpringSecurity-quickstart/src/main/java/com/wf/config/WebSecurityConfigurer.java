package com.wf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

/**
 * @author: wf
 * @date: 2023年08月09日 12:06
 * permitAll() 代表放行该资源,该资源为公共资源，无需认证和授权可以直接访问
 *    anyRequest().authenticated() 代表所有请求,必须认证之后才能访问
 *    formLogin() 代表开启表单认证
 * 注意: 放行资源必须放在所有认证请求之前!
 *
 */

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/index").permitAll() //放行/index请求
                .mvcMatchers("/login.html").permitAll() //放行/login.html请求
                .anyRequest().authenticated() //其它请求需要登录认证后才能访问
                .and()
                .formLogin() //默认form表单页面登录
                .loginPage("/login.html") //使用自定义登录页面登录页面登录
                .loginProcessingUrl("/doLogin") //使用自定义登录页面时需要重新指定url，对应login.html中的action路径
                .usernameParameter("uname") //重新指定用户名名称
                .passwordParameter("pwd") //重新指定密码名称
//                .successForwardUrl("/index") //认证成功后跳转路径  forward 跳转路径  始终在认证成功之后跳转到指定请求 地址栏不变
//                .defaultSuccessUrl("/hello") //默认认证成功后跳转路径
                //.defaultSuccessUrl("/hello",true) //第二个参数设置为true时总是跳转，效果同successForwardUrl一致，默认false
                //redirect 重定向  注意:如果之前有请求过的路径,会优先跳转之前的请求路径 地址栏改变
//                .failureUrl("/login.html") //登录失败后跳转路径
                .successHandler(new MyAuthenticationSuccessHandler())//认证成功时处理，前后端分离解决方案
                //.failureForwardUrl("/login.html")//认证失败之后，forward跳转
//                .failureUrl("/login.html") //默认认证失败之后，redirect跳转
                .failureHandler(new MyAuthenticationFailureHandler())//认证失败时处理，前后端解决方案
                .and()
                .logout()
                .logoutRequestMatcher(new OrRequestMatcher(
                        new AntPathRequestMatcher("/aaa", "GET"),
                        new AntPathRequestMatcher("/bbb", "POST")
                ))
                .invalidateHttpSession(true)//默认开启会话失效
                .clearAuthentication(true)//默认清除认证标志
//                .logoutSuccessUrl("/login.html")//注销登录成功后跳转的页面
                .logoutSuccessHandler(new MyLogoutSuccessHandler())
                .and()
                .csrf().disable();//此处先关闭CSRF跨站保护
    }
}
