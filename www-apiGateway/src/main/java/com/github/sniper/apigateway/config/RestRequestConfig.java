package com.github.sniper.apigateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/10 15:18
 */

@Configuration
public class RestRequestConfig {

    @Bean
    @LoadBalanced
    public RestTemplate registerRestTemplate() {
        return new RestTemplate();
    }

    //    @LoadBalanced
//    @Bean
//    WebClient.Builder webClientBuilder() {
//        return WebClient.builder();
//    }
//
//    @Bean
//    WebClient webClient() {
//        return webClientBuilder().build();
//    }
}
