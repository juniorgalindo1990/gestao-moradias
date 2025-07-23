package com.moradiasestudantis.gestao_moradias.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(@NonNull UserDetailsImpl userDetails) {
        String emissor = "gestao-moradias";
        String usuario = userDetails.getUsername();
        String role = userDetails.getUser().getRole().name();
        Date agora = new Date();
        Date expiracao = new Date(System.currentTimeMillis() + 1000 * 60 * 60); // 1 hora

        return Jwts.builder()
            .setIssuer(emissor)
            .setSubject(usuario)
            .claim("role", role)
            .setIssuedAt(agora)
            .setExpiration(expiracao)
            .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
            .compact();
    }

    public String getSubject(@NonNull String token) {
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
}
