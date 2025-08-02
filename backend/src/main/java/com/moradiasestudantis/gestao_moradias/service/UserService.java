package com.moradiasestudantis.gestao_moradias.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.moradiasestudantis.gestao_moradias.dto.RegisterDto;
import com.moradiasestudantis.gestao_moradias.exception.EmailAlreadyExistsException;
import com.moradiasestudantis.gestao_moradias.model.User;
import com.moradiasestudantis.gestao_moradias.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void register(RegisterDto data) {
        if (repository.existsByEmail(data.getEmail())) {
            throw new EmailAlreadyExistsException("Este e-mail já está cadastrado.");
        }

        User newUser = new User();
        newUser.setEmail(data.getEmail());
        newUser.setSenha(new BCryptPasswordEncoder().encode(data.getSenha()));
        newUser.setRole(data.getRole());

        repository.save(newUser);
    }
}
