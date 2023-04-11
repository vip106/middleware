package com.github.sniper.authsecurity.security.handler;

import com.github.sniper.authsecurity.constant.AuthKeyProperties;
import com.github.sniper.authsecurity.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/10 12:09
 */
@Slf4j
@Component
public class SystemLogoutSuccessHandler implements LogoutSuccessHandler {


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String,Object> resultData = new HashMap<>();
        resultData.put(AuthKeyProperties.RESULT_VALUE_KEY.getCode(),AuthKeyProperties.LOGOUT_SUCCESS.getCode());
        resultData.put(AuthKeyProperties.RESULT_VALUE_KEY.getValue(),AuthKeyProperties.LOGOUT_SUCCESS.getValue());
        SecurityContextHolder.clearContext();
        log.info("登出操作机制成功！");
        ResultUtil.responseJson(response, ResultUtil.resultSuccess(resultData));
    }
}