/*package com.spring.DemandeCredit.Security;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
    public class JwtAuthenticationProvider implements AuthenticationProvider {

        private static final String SECRET_KEY = "c9e4493d0d8888bde20bec3e604e8077d0485f8f99b696a1b421a49513a96ff9";

        private final UserDetailsService userDetailsService;

        public JwtAuthenticationProvider(UserDetailsService userDetailsService) {
            this.userDetailsService = userDetailsService;
        }

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            String token = (String) authentication.getCredentials();
            String username = null;

            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(getSigningKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
                username = claims.getSubject();
            } catch (Exception e) {
                throw new AuthenticationException("Invalid JWT token") {};
            }

            if (username != null) {
                try {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
                } catch (UsernameNotFoundException e) {
                    throw new AuthenticationException("User not found") {};
                }
            }

            return null;
        }



    @Override
        public boolean supports(Class<?> authentication) {
            return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
        }

    private Key getSigningKey (){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    }*/


