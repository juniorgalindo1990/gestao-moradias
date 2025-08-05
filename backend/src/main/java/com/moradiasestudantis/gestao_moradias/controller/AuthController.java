package com.moradiasestudantis.gestao_moradias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moradiasestudantis.gestao_moradias.dto.LoginDto;
import com.moradiasestudantis.gestao_moradias.dto.RegisterDto;
import com.moradiasestudantis.gestao_moradias.dto.TokenDto;
import com.moradiasestudantis.gestao_moradias.exception.EmailAlreadyExistsException;
import com.moradiasestudantis.gestao_moradias.model.User;
import com.moradiasestudantis.gestao_moradias.repository.UserRepository;
import com.moradiasestudantis.gestao_moradias.security.TokenService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")

@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

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
        if (this.repository.existsByEmail(data.getEmail())) {
            throw new EmailAlreadyExistsException("Este e-mail já está cadastrado. Por favor, utilize outro e-mail.");
        }

        User newUser = new User();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getSenha());
        newUser.setName(data.getName());
        newUser.setEmail(data.getEmail());
        newUser.setSenha(encryptedPassword);
        newUser.setRole(data.getRole());

        this.repository.save(newUser);

        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok().build();
    }
}
