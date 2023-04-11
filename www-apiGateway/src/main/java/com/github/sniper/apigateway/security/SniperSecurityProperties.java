package com.github.sniper.apigateway.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/10 15:29
 */
@Component
@Data
@EnableConfigurationProperties(SniperSecurityProperties.class)
@ConfigurationProperties(prefix = "www.security")
public class SniperSecurityProperties {
    /**
     * 访问auth-security服务的loadBalance地址，服务名
     */
    private String authUrl;

    /**
     * 不需要进行鉴权的接口和path。
     */
    private String[] ignorePath;
}
