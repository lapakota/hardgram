package hardsign.server.services;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenProvider {
    private final SecretKey Key;

    public TokenProvider(@Value("${jwt.secret}") String secret) {
        Key =  Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generate(UserDetails userDetails) {
        return doGenerateToken(new HashMap<>(), userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        var currentDate = LocalDateTime.now();
        var tokenExpiration = currentDate.plusMinutes(30); //TODO
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(tokenExpiration.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(Key)
                .compact();
    }

    public String getNicknameFromToken(String token) {
        return getClaimFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token).getExpiration();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        var nickname = getNicknameFromToken(token);
        return (nickname.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getClaimFromToken(String token) {
        return getAllClaimsFromToken(token);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(Key)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        var expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
