package com.moradiasestudantis.gestao_moradias.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class UserController {

    @GetMapping("/dashboard")
    public ResponseEntity<String> userDashboard() {
        return ResponseEntity.ok("Bem-vindo, usuário!");
    }
}
