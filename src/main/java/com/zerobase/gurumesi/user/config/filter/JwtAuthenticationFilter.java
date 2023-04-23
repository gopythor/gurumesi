package com.zerobase.gurumesi.user.config.filter;

import com.zerobase.gurumesi.domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
@RequiredArgsConstructor
@WebFilter(urlPatterns ="/book")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String token = this.resolveTokenFromRequest(request);
        if (token == null){
            filterChain.doFilter(request, response);
            return;
        }
        Authentication auth = this.jwtAuthenticationProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(auth); // 인증정보를 context에 담음

        //어떤 사용자가 어떤 경로로 요청했는지를 기록하기 위한 로그
        log.info(String.format("[%s] -> %s", this.jwtAuthenticationProvider.getUserPk(token), request.getRequestURI()));

        filterChain.doFilter(request, response); // 유효하지 않으면 바로 실행
    }


    // 요청 헤더에 토큰이 있는지
    private String resolveTokenFromRequest(HttpServletRequest request) throws ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("X-AUTH-TOKEN");
        if(!jwtAuthenticationProvider.validateToken(token)){
            return null;
        }
        return token;
    }

}
