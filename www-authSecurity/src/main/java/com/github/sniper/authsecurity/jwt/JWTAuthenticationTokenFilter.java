package com.github.sniper.authsecurity.jwt;

import com.alibaba.fastjson.JSONObject;
import com.github.sniper.authsecurity.access.AccessListController;
import com.github.sniper.authsecurity.constant.AuthKeyProperties;
import com.github.sniper.authsecurity.constant.JwtKeyProperties;
import com.github.sniper.authsecurity.domain.SystemUserSubject;
import com.github.sniper.authsecurity.util.ResultUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/10 10:50
 */
@Slf4j
public class JWTAuthenticationTokenFilter extends BasicAuthenticationFilter {

    private JwtConfigProperties jwtConfigProperties;


    private AccessListController accessListController;


    private RedisTemplate redisTemplate;

    public JWTAuthenticationTokenFilter(AuthenticationManager authenticationManager,
                                        JwtConfigProperties jwtConfigProperties,
                                        AccessListController accessListController,
                                        RedisTemplate redisTemplate) {
        super(authenticationManager);
        this.jwtConfigProperties = jwtConfigProperties;
        this.accessListController = accessListController;
        this.redisTemplate = redisTemplate;
    }

    public void doFilterInternal(HttpServletRequest request , HttpServletResponse response , FilterChain filterChain) throws ServletException, IOException {

        boolean isWhiteList = accessListController.matchWhiteAccessList(request.getRequestURI());

        boolean isBlackList = accessListController.matchBlackAccessList(request.getRequestURI());

        if(isWhiteList){
            log.info("请求服务通过:{} 判定为白名单,无需经过下面服务的检验和过滤控制",request.getRequestURI());
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("ANON","ANON"));
//            filterChain.doFilter(request, response);
//            ResultUtil.responseJson(response,ResultUtil.resultCode(Integer.parseInt(AuthKeyProperties.PROCESS_SUCCESS.getCode()),
//                    AuthKeyProperties.PROCESS_SUCCESS.getValue()));
            filterChain.doFilter(request, response);
            return;
        }

        if(isBlackList){
            log.info("请求服务通过:{} 判定为黑名单,无需经过下面服务的检验和过滤控制，直接返回服务下线！",request.getRequestURI());
            ResultUtil.responseJson(response,ResultUtil.resultCode(Integer.parseInt(AuthKeyProperties.SERVER_DOWN.getCode()),
                    AuthKeyProperties.SERVER_DOWN.getValue()));
            return ;
        }
        log.info("请求服务:{} 不属于任何黑白名单控制列表！",request.getRequestURI());
        // 获取请求头中JWT的Token
        String tokenHeader = request.getHeader(jwtConfigProperties.getTokenHeader());
        // token header头部操作处理机制
        log.info("请求服务:获取到请求服务的header头：{}",tokenHeader);
        if (!StringUtils.isEmpty(tokenHeader) && tokenHeader.startsWith(jwtConfigProperties.getTokenPrefix())) {
            try {
                // 截取JWT前缀
                String token = tokenHeader.replace(jwtConfigProperties.getTokenPrefix(), "");
                // 解析JWT
                Claims claims = Jwts.parser()
                        .setSigningKey(jwtConfigProperties.getSecret())
                        .parseClaimsJws(token)
                        .getBody();
                // 获取用户名
                String username = claims.getSubject();
                // 获取用户id
                String userId=claims.getId();
                Date expireDate = claims.getExpiration();
                // 判断是否为空的操作判断处理
                if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(userId)) {
                    // token
                    if(!redisTemplate.hasKey(token)){
                        ResultUtil.responseJson(response,ResultUtil.resultCode(Integer.parseInt(AuthKeyProperties.TOKEN_TIME_OUT.getCode()),
                                AuthKeyProperties.TOKEN_TIME_OUT.getValue()));
                        return ;
                    }
                    // 获取角色
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    String authority = claims.get(JwtKeyProperties.DEFAULT_PERMISSION_RESOURCE).toString();
                    if(!StringUtils.isEmpty(authority)){
                        List<Map<String,String>> authorityMap = JSONObject.parseObject(authority, List.class);
                        for(Map<String,String> role : authorityMap){
                            if(!StringUtils.isEmpty(role)) {
                                authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                            }
                        }
                    }
                    //组装参数
                    SystemUserSubject selfUserEntity = new SystemUserSubject();
                    selfUserEntity.setUsername(claims.getSubject());
                    selfUserEntity.setUserId(Long.parseLong(claims.getId()));
                    selfUserEntity.setAuthorities(authorities);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(selfUserEntity, userId, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ExpiredJwtException e){
                log.error(AuthKeyProperties.TOKEN_TIME_OUT.getValue(),e);
                ResultUtil.responseJson(response,ResultUtil.resultCode(Integer.parseInt(AuthKeyProperties.TOKEN_TIME_OUT.getCode()),
                        AuthKeyProperties.TOKEN_TIME_OUT.getValue()));
                return ;
            } catch (Exception e) {
                log.error(AuthKeyProperties.TOKEN_FAILURE.getValue(),e);
                ResultUtil.responseJson(response,ResultUtil.resultCode(Integer.parseInt(AuthKeyProperties.TOKEN_FAILURE.getCode()),
                        AuthKeyProperties.TOKEN_FAILURE.getValue()));
                return ;
            }
        }else{
            ResultUtil.responseJson(response,ResultUtil.resultCode(Integer.parseInt(AuthKeyProperties.TOKEN_FAILURE.getCode()),
                    AuthKeyProperties.TOKEN_FAILURE.getValue()));
            return ;
        }
        filterChain.doFilter(request, response);
        return;
    }


}
