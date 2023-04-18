package com.zerobase.gurumesi.config;

import com.zerobase.gurumesi.domain.common.UserType;
import com.zerobase.gurumesi.domain.util.Aes256Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class jwtAuthenticationProvider {
    private String secretKey = "secretKey";

    private long tokenValidTime = 1000L * 60 * 60 * 24;

    private String createToken(String userPk, Long id, UserType userType){
        Claims claims = Jwts.claims().setSubject(Aes256Util.encrypt(userPk))
                        .setId(id.toString());
        claims.put("roles", userType);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+tokenValidTime))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }
}

