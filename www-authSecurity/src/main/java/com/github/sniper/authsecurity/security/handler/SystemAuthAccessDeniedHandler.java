package com.github.sniper.authsecurity.security.handler;

import com.github.sniper.authsecurity.constant.AuthKeyProperties;
import com.github.sniper.authsecurity.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/10 12:04
 */
@Slf4j
@Component
public class SystemAuthAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * 暂无权限返回结果
     * @Author Sans
     * @CreateTime 2019/10/3 8:41
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception){
        ResultUtil.responseJson(response,ResultUtil.resultCode(Integer.parseInt(AuthKeyProperties.DENIED_ACCESS.getCode()),
                AuthKeyProperties.DENIED_ACCESS.getValue()));
    }
}