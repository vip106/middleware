package com.github.sniper.authsecurity.domain;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import java.io.Serializable;
import java.util.Collection;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/9 20:27
 */
@Data
public class SystemUserSubject implements Serializable, UserDetails {
    /**
     * 用户ID
     */
    @Getter
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态:NORMAL正常  PROHIBIT禁用
     */
    private String status;

    /**
     * 用户角色
     */
    private Collection<GrantedAuthority> authorities;

    /**
     * 账户是否过期
     */
    private boolean isAccountNonExpired = false;

    /**
     * 账户是否被锁定
     */
    private boolean isAccountNonLocked = false;

    /**
     * 证书是否过期
     */
    private boolean isCredentialsNonExpired = false;

    /**
     * 账户是否有效
     */
    private boolean enabled = true;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
