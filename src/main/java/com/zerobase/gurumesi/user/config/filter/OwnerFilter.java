package com.zerobase.gurumesi.user.config.filter;

import com.zerobase.gurumesi.domain.common.UserVo;
import com.zerobase.gurumesi.domain.config.JwtAuthenticationProvider;
import com.zerobase.gurumesi.user.service.owner.OwnerService;
import lombok.RequiredArgsConstructor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/owner/*")
@RequiredArgsConstructor
public class OwnerFilter implements Filter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final OwnerService ownerService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("X-AUTH-TOKEN");
        if(!jwtAuthenticationProvider.validateToken(token)){
            throw new ServletException("Invalid Access");
        }
        UserVo vo = jwtAuthenticationProvider.getUserVo(token);
        ownerService.findByIdAndEmail(vo.getId(), vo.getEmail()).orElseThrow(
                ()->new ServletException("Invalid Access")
        );
        //재전송 해줘야 함.
        chain.doFilter(request,response);
    }
}
