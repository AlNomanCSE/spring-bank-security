package com.noman.BankBackendApplication.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AuthoritiesLoggingAfterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (null != authentication) {
//            log.info("User: " + authentication.getName() + "is successfully authenticated and " + "has the authorities" + authentication.getAuthorities().toString());
//        }
        log.info("AuthoritiesLoggingAfterFilter validation is in progress ");
        chain.doFilter(request, response);

    }
}
