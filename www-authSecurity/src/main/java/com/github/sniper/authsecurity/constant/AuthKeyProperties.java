package com.github.sniper.authsecurity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/10 11:33
 */
@Getter
@AllArgsConstructor
public enum AuthKeyProperties {

    /**
     * 登录成功
     */
    LOGIN_SUCCESS("200","登录成功"),

    /**
     * 注销成功
     */
    LOGOUT_SUCCESS("200","注销成功"),

    /**
     * 执行成功
     */
    PROCESS_SUCCESS("200","执行成功"),

    /**
     * 未登录操作
     */
    NOT_LOGIN("401","未登录"),

    /**
     * 未登录操作
     */
    DENIED_ACCESS("401","拒接访问"),

    /**
     * 未授权操作
     */
    NOT_AUTH("403","未授权"),

    /**
     * token非法
     */
    TOKEN_FAILURE("402","token非法"),

    /**
     * 服务已经下线
     */
    SERVER_DOWN("405","服务已经下线！"),

    /**
     * token超时
     */
    TOKEN_TIME_OUT("406","token超时"),

    /**
     * 返回值的键值
     */
    RESULT_VALUE_KEY("code","msg"),

    ;

    private String code;

    private String value;
}
