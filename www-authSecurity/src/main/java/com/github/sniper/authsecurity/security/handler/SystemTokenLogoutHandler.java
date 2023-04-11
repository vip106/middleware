package com.github.sniper.authsecurity.security.handler;

import com.github.sniper.authsecurity.jwt.JwtConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/10 12:11
 */
@Component
public class SystemTokenLogoutHandler implements LogoutHandler {


    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private JwtConfigProperties jwtConfigProperties;


    public SystemTokenLogoutHandler(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 执行相关的登出操作
     *
     * @param request
     * @param response
     * @param authentication
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String tokenHeader = request.getHeader(jwtConfigProperties.getTokenHeader());
        if (tokenHeader != null) {
            String token = tokenHeader.replace(jwtConfigProperties.getTokenPrefix(), "");
            //清空当前用户缓存中的权限数据
            redisTemplate.delete(token);
        }
    }
}
