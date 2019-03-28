package com.wfiis.CalculatorCO2.user.security.authorization;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JWTFilter implements Filter {
    private final TokenProvider tokenProvider;
    private final static String AUTHORIZATION_HEADER = "Authorization";
    private final static String AUTHORIZATION_VALUE_PREFIX = "JWT ";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String jwt = resolveToken(httpServletRequest);

            if (jwt != null) {
                Authentication authentication = this.tokenProvider.getAuthentication(jwt);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
        catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
                | UsernameNotFoundException e) {
            ((HttpServletResponse) servletResponse)
                    .setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String resolveToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(AUTHORIZATION_VALUE_PREFIX)) {
            return bearerToken.substring(AUTHORIZATION_VALUE_PREFIX.length());
        }
        return null;
    }
}
