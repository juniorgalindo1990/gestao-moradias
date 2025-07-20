package com.moradiasestudantis.gestao_moradias.security;

import java.sql.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Value;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(UserDetailsImpl userDetails) {
        return Jwts.builder()
            .setIssuer("gestao-moradias")
            .setSubject(userDetails.getUsername())
            .claim("role", userDetails.getUser().getRole().name())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }

    public String getSubject(String token) {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
}

