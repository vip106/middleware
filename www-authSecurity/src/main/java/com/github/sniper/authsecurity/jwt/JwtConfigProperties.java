package com.github.sniper.authsecurity.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/9 20:32
 */
@Getter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfigProperties {
    /**
     * @description: 秘钥
     * @author Sniper_lw
     * @date: 2023/4/9 20:34
     */
    @Setter
    private String secret;

    /**
     * @description: token-header
     * @author Sniper_lw
     * @date: 2023/4/9 20:34
     */
    @Setter
    private String tokenHeader;
    /**
     * @description: token 前缀
     * @author Sniper_lw
     * @date: 2023/4/9 20:34
     */
    @Setter
    private String tokenPrefix;
    /**
     * @description: token 过期时间
     * @author Sniper_lw
     * @date: 2023/4/9 20:34
     */
    @Setter
    private Integer expiration;


    /**
     * @description: 不需要认证的接口
     * @author Sniper_lw
     * @date: 2023/4/9 20:37
     */
    @Setter
    private String antMatchers;

    public void setExpiration(Integer expiration) {
        this.expiration = expiration * 1000;
    }
}
