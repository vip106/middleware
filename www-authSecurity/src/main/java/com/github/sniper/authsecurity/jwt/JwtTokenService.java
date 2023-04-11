package com.github.sniper.authsecurity.jwt;

import com.github.sniper.authsecurity.domain.SystemUserSubject;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/10 10:37
 */
public class JwtTokenService {

    private JwtConfigProperties jwtConfigProperties;

    public JwtTokenService() {
    }

    public JwtTokenService(JwtConfigProperties jwtConfigProperties) {
        this.jwtConfigProperties = jwtConfigProperties;
    }

    public String createAccessToken(SystemUserSubject subject) {

        String token = Jwts.builder()
                .setId(String.valueOf(subject.getUserId()))
                .setSubject(subject.getUsername())
                .setIssuedAt(new Date())
                .setIssuer("CALL_CENTER")
                .claim("authorities",subject.getAuthorities())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfigProperties.getExpiration()))
                .signWith(SignatureAlgorithm.HS512,jwtConfigProperties.getSecret())
                .compact();

        return token;
    }

    public Jwt parseAccessToken(String jwtStr) {

        return Jwts.parser().setSigningKey(jwtConfigProperties.getSecret()).parse(jwtStr);
    }
}
