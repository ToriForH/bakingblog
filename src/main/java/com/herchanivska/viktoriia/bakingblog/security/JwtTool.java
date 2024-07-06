package com.herchanivska.viktoriia.bakingblog.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParseException;
import com.herchanivska.viktoriia.bakingblog.constants.Role;
import com.herchanivska.viktoriia.bakingblog.model.User;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
@Slf4j
public class JwtTool {
    private final Integer accessTokenValidTimeInMinutes;
    private final Integer refreshTokenValidTimeInMinutes;
    private final String accessTokenKey;

    @Autowired
    public JwtTool(@Value("${accessTokenValidTimeInMinutes}") Integer accessTokenValidTimeInMinutes,
                   @Value("${refreshTokenValidTimeInMinutes}") Integer refreshTokenValidTimeInMinutes,
                   @Value("${tokenKey}") String accessTokenKey) {
        this.accessTokenValidTimeInMinutes = accessTokenValidTimeInMinutes;
        this.refreshTokenValidTimeInMinutes = refreshTokenValidTimeInMinutes;
        this.accessTokenKey = accessTokenKey;
    }

    public String createAccessToken(String email, Role role) {
        ClaimsBuilder claims = Jwts.claims().subject(email);
        claims.add("role", Collections.singleton(role.name()));
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MINUTE, accessTokenValidTimeInMinutes);
        return Jwts.builder()
                .claims(claims.build())
                .issuedAt(now)
                .expiration(calendar.getTime())
                .signWith(Keys.hmacShaKeyFor(
                                accessTokenKey.getBytes(StandardCharsets.UTF_8)),
                        Jwts.SIG.HS256)
                .compact();
    }

    public String getEmailOutOfAccessToken(String token) {
        String[] splitToken = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(splitToken[1]));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(payload);
        } catch (Exception e) {
            throw new JsonParseException("Error parsing JSON payload", e);
        }
        return jsonNode.path("sub").asText();
    }

    public boolean isTokenValid(String token, String tokenKey) {
        boolean isValid = false;
        SecretKey key = Keys.hmacShaKeyFor(tokenKey.getBytes());
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            isValid = true;
        } catch (Exception e) {
            log.info("Given token is not valid: " + e.getMessage());
        }
        return isValid;
    }

    public String getAccessTokenKey() {
        return accessTokenKey;
    }

    public String getTokenFromHttpServletRequest(HttpServletRequest servletRequest) {
        return Optional
                .ofNullable(servletRequest.getHeader("Authorization"))
                .filter(authHeader -> authHeader.startsWith("Bearer "))
                .map(token -> token.substring(7))
                .orElse(null);
    }

    public String generateTokenKey() {
        return UUID.randomUUID().toString();
    }
}
