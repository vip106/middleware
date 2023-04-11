package com.github.sniper.authsecurity.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/10 12:38
 */
@ToString
@Component
public class SecurityDynamicProperties {

    @Getter
    @NacosValue(value = "${test}", autoRefreshed = true)
    private String test;

}
