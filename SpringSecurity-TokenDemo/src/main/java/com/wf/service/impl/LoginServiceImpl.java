package com.wf.service.impl;

import com.wf.domain.LoginUser;
import com.wf.domain.ResponseResult;
import com.wf.domain.User;
import com.wf.service.LoginService;
import com.wf.util.JwtUtil;
import com.wf.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author: wf
 * @date: 2023年08月03日 20:25
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (authenticate == null) {
            //如果认证没通过，给出对应的提示
            throw new RuntimeException("登录失败");
        }

        //如果认证通过了，使用userid生成一个jwt  jwt存入ResponseResult返回
        LoginUser loginUser= (LoginUser)authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(id);


        HashMap<String, String> map = new HashMap<>();
        map.put("token",jwt);

        redisCache.setCacheObject("login:"+id,loginUser);
        //把完整的用户信息存入redis userid作为key
        return new ResponseResult<>(200,"登录成功",map);
    }
}
