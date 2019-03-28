package com.wfiis.CalculatorCO2.security.authorization;

import com.wfiis.CalculatorCO2.security.AppUserDetailsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class TokenProvider {
    private final int tokenValidityInMilliseconds;
    private final String secretKey;
    private final AppUserDetailsService appUserDetailsService;

    public TokenProvider(@Value("${security.token.expired-time}") int tokenValidityInMilliseconds,
                         @Value("${security.token.secret-key}") String secretKey, AppUserDetailsService appUserDetailsService) {
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds;
        this.secretKey = secretKey;
        this.appUserDetailsService = appUserDetailsService;
    }


    public String createToken(String username) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + this.tokenValidityInMilliseconds);

        return Jwts.builder().setId(UUID.randomUUID().toString()).setSubject(username)
                .setIssuedAt(now).signWith(SignatureAlgorithm.HS512, this.secretKey)
                .setExpiration(validity).compact();
    }

    public Authentication getAuthentication(String token) {
        String email = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token)
                .getBody().getSubject();

        UserDetails userDetails = appUserDetailsService.loadUserByUsername(email);

            return new UsernamePasswordAuthenticationToken(userDetails, "",
                userDetails.getAuthorities());
    }
}
