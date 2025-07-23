package com.moradiasestudantis.gestao_moradias.controller;

import com.moradiasestudantis.gestao_moradias.entity.User;
import com.moradiasestudantis.gestao_moradias.num.RoleEnum;
import com.moradiasestudantis.gestao_moradias.repository.UserRepository;
import com.moradiasestudantis.gestao_moradias.security.TokenService;
import com.moradiasestudantis.gestao_moradias.security.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já está em uso.");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setSenha(passwordEncoder.encode(request.getSenha()));
        user.setRole(RoleEnum.USER);


        userRepository.save(user);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

    public static class RegisterRequest {
        private String email;
        private String senha;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
    }

    public static class LoginRequest {
        private String email;
        private String senha;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
    }

 @Autowired
private AuthenticationManager authenticationManager;

@Autowired
private TokenService tokenService;


@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    try {
        var authToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha());
        var authentication = authenticationManager.authenticate(authToken);

        var userDetails = (UserDetailsImpl) authentication.getPrincipal();
        var token = tokenService.gerarToken(userDetails);

        return ResponseEntity.ok(new TokenResponse(token));
    } catch (Exception e) {
        System.out.println("Ocorreu um erro após a autenticação!");
        return ResponseEntity.status(500).body("Erro interno após a autenticação: " + e.getMessage());
    }
}



    
}
