package com.spring.user.Service;


import com.google.common.base.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final String SECRET_KEY = "d4f0e1b67b795c9bfb6e9abddae8a61e8330fd48573397e95a9c3f845b50c4b9";

    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    // Generic method to extract a specific claim from a JWT token.
    // The claim to be extracted is specified by the claimsResolver function.
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    public String generateToken(UserDetails userDetails) {
        return  generationToken(new HashMap<>(),userDetails);
    }

    public String generationToken(Map<String, Object> extraClaims , UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *60* 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()))&& !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    // Method to extract all claims (information) from a given JWT token.
    private Claims extractAllClaims(String token) {
        // Parse the JWT token using the secret key to extract the claims.
        return Jwts
                .parserBuilder() // Create a new JWT parser builder.
                .setSigningKey(getSignInKey()) // Set the signing key used to verify the token's signature.
                .build() // Build the JWT parser.
                .parseClaimsJws(token) // Parse the token.
                .getBody(); // Get the claims from the token.
    }

    private Key getSignInKey (){
       byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
       return Keys.hmacShaKeyFor(keyBytes);
    }
}
