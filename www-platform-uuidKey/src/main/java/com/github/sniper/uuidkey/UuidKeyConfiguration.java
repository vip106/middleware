package com.github.sniper.uuidkey;

import cn.hutool.core.net.NetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/12 10:14
 */
@SpringBootApplication
@Slf4j
public class UUIDKeyConfig {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(UuidKeyConfiguration.class,args);
        ServerProperties serverProperties = applicationContext.getBean(ServerProperties.class);
        log.info("请访问swagger访问页面：{}", "http://"+ NetUtil.localIpv4s().stream().findFirst().get()+":"+
                serverProperties.getPort()+"/docs.html");
    }
}
