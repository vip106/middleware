package com.github.sniper.authsecurity.access;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/10 11:03
 */
@Slf4j
@Component
public class AccessListController implements CommandLineRunner {

    @Getter
    @Autowired
    private volatile AccessListProperties accessListProperties;

    /**
     * 白名单
     */
    @Value(value="${spring.cloud.nacos.config.server-addr}:127.0.0.1:8848")
    private String serverAdder;

    /**
     * 黑名单
     */
    @Value(value="${spring.cloud.nacos.config.access-list:acl}")
    private String dataId;


    private static final String DEFAULT_ACCESS_KEY = "accessList";

    private static final String DEFAULT_ACCESS_WHITE_KEY = "whiteList";

    private static final String DEFAULT_ACCESS_BLACK_KEY = "blackList";

    /**
     * antPathMatcher
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 获取匹配白名单列表
     * @param requestPath
     * @return
     */
    public boolean matchWhiteAccessList(String requestPath){
        // 获取相关的白名单信息数据信息
        if(CollectionUtils.isEmpty(accessListProperties.getWhiteList())){
            return false;
        }
        return accessListProperties.getWhiteList().stream().anyMatch(param->antPathMatcher.match(param,requestPath));
    }

    /**
     * 获取匹配黑名单列表
     * @param requestPath
     * @return
     */
    public boolean matchBlackAccessList(String requestPath){
        // 获取相关的黑名单信息数据信息
        if(CollectionUtils.isEmpty(accessListProperties.getBlackList())){
            return false;
        }
        return accessListProperties.getBlackList().stream().anyMatch(param->antPathMatcher.match(param,requestPath));
    }


    public void run(String... args) throws Exception {
        try {
            String serverAddr = serverAdder;
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, null, 5000);
            Yaml yaml = new Yaml();
            configService.addListener(dataId, null, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }
                @Override
                public void receiveConfigInfo(String configInfo) {
                    if(StringUtils.isNotEmpty(configInfo)){
                        Map data = yaml.loadAs(configInfo,Map.class);
                        Map accessMap = (Map) data.get(DEFAULT_ACCESS_KEY);
                        accessListProperties.setBlackList((List<String>) accessMap.get(DEFAULT_ACCESS_BLACK_KEY));
                        accessListProperties.setWhiteList((List<String>) accessMap.get(DEFAULT_ACCESS_WHITE_KEY));
                        log.info("动态刷新加载后的访问列表服务控制单:{}",accessListProperties);
                    }
                }
            });
            if(StringUtils.isNotEmpty(content)){
                Map data = yaml.loadAs(content, Map.class);
                Map accessMap = (Map) data.get(DEFAULT_ACCESS_KEY);
                accessListProperties.setBlackList((List<String>) accessMap.get(DEFAULT_ACCESS_BLACK_KEY));
                accessListProperties.setWhiteList((List<String>) accessMap.get(DEFAULT_ACCESS_WHITE_KEY));
                log.info("初始化加载后的访问列表服务控制单:{}",accessListProperties);
            }
        } catch (NacosException e) {
            log.error("加载Nacos服务信息失败!",e);
        }
    }


}
