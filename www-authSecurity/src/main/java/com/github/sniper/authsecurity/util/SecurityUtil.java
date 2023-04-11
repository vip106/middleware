package com.github.sniper.authsecurity.util;

import com.github.sniper.authsecurity.domain.SystemUserSubject;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/10 11:29
 */
public class SecurityUtil {

    /**
     * 获取当前用户信息
     */
    public static SystemUserSubject getUserInfo(){
        SystemUserSubject userDetails = (SystemUserSubject)
                SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        return userDetails;
    }

    /**
     * 获取当前用户ID
     */
    public static Long getUserId(){
        return getUserInfo().getUserId();
    }

    /**
     * 获取当前用户账号
     */
    public static String getUserName(){
        return getUserInfo().getUsername();
    }
}
