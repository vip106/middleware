package com.github.sniper.authsecurity.access;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Reference;

import java.util.List;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/10 11:03
 */
@Data
@ConfigurationProperties(prefix = "access-list")
@RefreshScope
@Configuration
@EnableConfigurationProperties(AccessListProperties.class)
public class AccessListProperties {


    private volatile List<String> whiteList;


    private volatile List<String> blackList;
}
