package com.wf.expressionRoot;

import com.wf.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: wf
 * @date: 2023年08月08日 12:18
 */

@Component("ex")
public class WFExpressionRoot {


    boolean hasAuthority(String authority){

        //获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();

        //判断用户权限集合中是否存在authority
        return permissions.contains(authority);
    }
}
