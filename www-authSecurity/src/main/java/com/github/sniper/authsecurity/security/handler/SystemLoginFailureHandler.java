package com.github.sniper.authsecurity.security.handler;

import com.github.sniper.authsecurity.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/10 12:08
 */
@Slf4j
@Component
public class SystemLoginFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 这些对于操作的处理类可以根据不同异常进行不同处理
        if (exception instanceof UsernameNotFoundException){
            log.error("【登录失败】",exception);
            ResultUtil.responseJson(response, ResultUtil.resultCode(500,"用户名不存在"));
        }
        else if (exception instanceof LockedException){
            log.error("【登录失败】",exception);
            ResultUtil.responseJson(response,ResultUtil.resultCode(500,"用户被冻结"));
        }
        else if (exception instanceof BadCredentialsException){
            log.error("【登录失败】",exception);
            ResultUtil.responseJson(response,ResultUtil.resultCode(500,"用户名密码不正确"));
        }else{
            ResultUtil.responseJson(response,ResultUtil.resultCode(500,"登录失败"));
        }
    }
}