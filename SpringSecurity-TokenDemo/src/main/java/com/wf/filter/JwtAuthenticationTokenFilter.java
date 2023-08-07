package com.wf.filter;

import com.wf.domain.LoginUser;
import com.wf.util.JwtUtil;
import com.wf.util.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: wf
 * @date: 2023年08月04日 18:15
 */

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    @Autowired
    private RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        //获取token
        String token = httpServletRequest.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        //解析token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }

        //从redis中获取用户信息
        String redisKey = "login:" + userId;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if (loginUser == null){
            throw new RuntimeException("用户未登录");
        }
        //存入SecurityContextHolder

        //获取权限信息封装到 authenticationToken 中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
