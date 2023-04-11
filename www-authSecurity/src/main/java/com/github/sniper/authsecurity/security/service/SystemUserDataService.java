package com.github.sniper.authsecurity.security.service;

import com.github.sniper.authsecurity.authen.AccountProcessApi;
import com.github.sniper.authsecurity.domain.SystemUserSubject;
import com.github.sniper.authsecurity.rbac.po.SystemUser;
import com.github.sniper.common.model.rpc.RpcRequest;
import com.github.sniper.common.model.rpc.RpcResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description  SpringSecurity用户的业务实现
 * @date 2023/4/10 12:12
 */
@Component
public class SystemUserDataService implements UserDetailsService {


    @Autowired
    AccountProcessApi accountProcessApi;


    @Override
    public SystemUserSubject loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        SystemUser sysUserEntity = null;
        RpcResponse<SystemUser> response = accountProcessApi.selectUserByName(new RpcRequest<String>().setEnity(username));
        if(response.isSuccess()){
            sysUserEntity = response.getEntity();
        }
        if (Objects.nonNull(sysUserEntity)){
            // 组装参数
            SystemUserSubject selfUserEntity = new SystemUserSubject();
            BeanUtils.copyProperties(sysUserEntity,selfUserEntity);
            return selfUserEntity;
        }
        return null;
    }
}