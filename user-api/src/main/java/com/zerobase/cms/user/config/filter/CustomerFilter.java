package com.zerobase.cms.user.config.filter;

import com.zerobase.cms.domain.common.UserVo;
import com.zerobase.cms.domain.config.JwtAuthenticationProvider;
import com.zerobase.cms.user.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/customer/*")
@RequiredArgsConstructor
public class CustomerFilter implements Filter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final CustomerService customerService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("X-AUTH-TOKEN");
        if (!jwtAuthenticationProvider.isValidToken(token)) {
            throw new ServletException("Invalid Access");
        }
        UserVo userVo = jwtAuthenticationProvider.getUserVo(token);
        customerService.findByIdAndEmail(userVo.getId(), userVo.getEmail()).orElseThrow(
                () -> new ServletException("Invalid access")
        );
        chain.doFilter(request, response);
    }
}
