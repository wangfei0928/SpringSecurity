package com.wf.handler;

import com.alibaba.fastjson.JSON;
import com.wf.domain.ResponseResult;
import com.wf.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: wf
 * @date: 2023年08月08日 10:48
 */

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        ResponseResult responseResult = new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), "用户认证失败，请重新登录");

        String jsonString = JSON.toJSONString(responseResult);
        //处理异常
        WebUtils.renderString(httpServletResponse,jsonString);
    }
}
