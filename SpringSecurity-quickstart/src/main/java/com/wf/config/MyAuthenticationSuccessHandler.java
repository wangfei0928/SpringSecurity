package com.wf.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.internal.org.objectweb.asm.Handle;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author: wf
 * @date: 2023年08月09日 16:37
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("msg","登录成功");
        hashMap.put("status",200);
        hashMap.put("authentication",authentication); //打印认证信息
        httpServletResponse.setContentType("application/json;charset=UTF-8");  //设置响应类型
        String s = new ObjectMapper().writeValueAsString(hashMap);  //json格式转字符串
        httpServletResponse.getWriter().println(s);  //打印json格式数据
    }
}
