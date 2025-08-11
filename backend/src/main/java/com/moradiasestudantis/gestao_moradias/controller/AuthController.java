package com.moradiasestudantis.gestao_moradias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moradiasestudantis.gestao_moradias.dto.LoginDto;
import com.moradiasestudantis.gestao_moradias.dto.RegisterDto;
import com.moradiasestudantis.gestao_moradias.dto.TokenDto;
import com.moradiasestudantis.gestao_moradias.model.User;
import com.moradiasestudantis.gestao_moradias.security.TokenService;
import com.moradiasestudantis.gestao_moradias.service.UserService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto loginDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.senha());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);
        var token = this.tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new TokenDto(token));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<User> register(@RequestBody @Valid RegisterDto data) {        
        User newUser = this.userService.register(data);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok().build();
    }
}