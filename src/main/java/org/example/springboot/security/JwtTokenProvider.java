package org.example.springboot.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@PropertySource("classpath:jwt-secret.properties")
@Component
public class JwtTokenProvider {
    @Value("${jwt.expiration-time}")
    private long JWT_TOKEN_EXPIRATION_SECOND;

    @Value("${jwt.secret}")
    private String secret;

    private final UserProfileDetailsService userProfileDetailsService;

    private SecretKey getSignKey() {
       byte[] keyBytes = Decoders.BASE64.decode(secret);
       return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRATION_SECOND * 1000))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Date getExpireDate(Claims claims) {
        return claims.getExpiration();
    }

    public String getUsername(Claims claims) {
        return claims.getSubject();
    }

    public boolean isTokenExpiration(Claims claims) {
        return getExpireDate(claims).before(new Date(System.currentTimeMillis()));
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaimsFromToken(token);
        UserProfileDetails userProfileDetails = userProfileDetailsService.loadUserByUsername(getUsername(claims));
        return new UsernamePasswordAuthenticationToken(userProfileDetails, null, userProfileDetails.getAuthorities());
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);

            if (isTokenExpiration(claims)) {
                return false;
            }
            return true;
        } catch (JwtException e) {
            throw new JwtException("jwt authentication failed");
        }
    }
}
