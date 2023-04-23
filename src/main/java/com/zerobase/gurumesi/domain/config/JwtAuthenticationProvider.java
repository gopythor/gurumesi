package com.zerobase.gurumesi.domain.config;

import com.zerobase.gurumesi.domain.common.UserVo;
import com.zerobase.gurumesi.domain.common.UserType;
import com.zerobase.gurumesi.domain.util.Aes256Util;
import com.zerobase.gurumesi.user.service.customer.CustomerService;
import com.zerobase.gurumesi.user.service.owner.OwnerService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationProvider {

    private final CustomerService customerService;
    private final OwnerService ownerService;

    private String secretKey = "secretKey";

    private long tokenValidTime = 1000L * 60 * 60 * 24;

    public String createToken(String userPk, Long id, UserType userType){
        Claims claims = Jwts.claims().setSubject(Aes256Util.encrypt(userPk))
                        .setId(Aes256Util.encrypt(id.toString()));
        claims.put("roles", userType);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+tokenValidTime))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }
    public boolean validateToken(String jwtToken){
        try{
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(jwtToken);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e){
            return false;
        }
    }

    public UserVo getUserVo(String token){
        Claims c = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody();
        return new UserVo(Long.valueOf
                (Objects.requireNonNull(Aes256Util.decrypt(c.getId()))),
                Aes256Util.decrypt(c.getSubject()));
    }

    public String decodeToJson(final String base64){
        return StringUtils.newStringUtf8(Base64.decodeBase64(base64));
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();
        String rolesJson = (String)claims.get("roles");
        log.info(rolesJson);
        UserDetails userDetails;

        if (Objects.equals(rolesJson, "ROLE_CUSTOMER")){
            userDetails = customerService.loadUserByUsername((Aes256Util.decrypt(this.getUserPk(token))));
        } else if (Objects.equals(rolesJson, "ROLE_OWNER")){
            userDetails = ownerService.loadUserByUsername((Aes256Util.decrypt(this.getUserPk(token))));
        } else {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

    }

    // UserPk 구하기
    public String getUserPk(String token) {
        return this.parseClaims(token).getSubject(); //위에서 setSubject 해준 username 리턴됨
    }

    // 토큰이 유효한지 확인하기 위해 파싱
    private Claims parseClaims(String token) {
        // 토큰 만료 시간 지났는데 토큰을 파싱하려고 하면 expiredException 날 수 있으므로 try~ 에러 핸들링 해주기
        try {
            // claim 정보를 가져옴
            return Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}

