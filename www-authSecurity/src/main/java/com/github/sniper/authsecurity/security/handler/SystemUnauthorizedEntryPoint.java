package com.github.sniper.authsecurity.security.handler;

import com.github.sniper.authsecurity.constant.AuthKeyProperties;
import com.github.sniper.authsecurity.util.ResultUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description  <p>
 *  未授权的统一处理方式
 *   </p>
 * @date 2023/4/10 12:11
 */
@Component
public class SystemUnauthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        ResultUtil.responseJson(response,ResultUtil.resultCode(Integer.parseInt(AuthKeyProperties.NOT_AUTH.getCode()),
                AuthKeyProperties.NOT_AUTH.getValue()));
    }
}