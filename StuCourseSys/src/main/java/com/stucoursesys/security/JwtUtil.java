package com.stucoursesys.security;

import com.stucoursesys.entity.SysUser;
import com.stucoursesys.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final SecretKey key;

    private final long expireSeconds;

    public JwtUtil(@Value("${app.jwt.secret}") String secret,
                   @Value("${app.jwt.expire-seconds}") long expireSeconds) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expireSeconds = expireSeconds;
    }

    public String createToken(SysUser user, Set<String> roles) {
        JwtBuilder builder = Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expireSeconds * 1000L))
                .signWith(key);
        if (user.getStudentNo() != null) {
            builder.claim("studentNo", user.getStudentNo());
        }
        return builder.compact();
    }

    public long getExpireSeconds() {
        return expireSeconds;
    }

    public Claims parse(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException("登录状态已失效，请重新登录");
        } catch (JwtException | IllegalArgumentException e) {
            throw new UnauthorizedException("令牌无效");
        }
    }

    public CurrentUser parseUser(String token) {
        Claims claims = parse(token);
        List<?> rawRoles = claims.get("roles", List.class);
        Set<String> roles = rawRoles == null
                ? Set.of()
                : rawRoles.stream().map(String::valueOf).collect(Collectors.toSet());
        String studentNo = claims.get("studentNo", String.class);
        return new CurrentUser(Long.valueOf(claims.getSubject()), studentNo, roles);
    }
}
