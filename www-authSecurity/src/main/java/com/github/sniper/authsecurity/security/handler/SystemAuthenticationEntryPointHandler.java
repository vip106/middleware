package com.github.sniper.authsecurity.security.handler;

import com.github.sniper.authsecurity.constant.AuthKeyProperties;
import com.github.sniper.authsecurity.util.ResultUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/10 12:05
 */
@Component
public class SystemAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    /**
     * 用户未登录返回结果
     * @Author Sans
     * @CreateTime 2019/10/3 9:01
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception){
        ResultUtil.responseJson(response,ResultUtil.resultCode(Integer.parseInt(AuthKeyProperties.NOT_LOGIN.getCode()),
                AuthKeyProperties.NOT_LOGIN.getValue()));
    }
}