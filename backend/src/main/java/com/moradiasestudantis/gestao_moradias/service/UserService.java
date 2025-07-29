package com.moradiasestudantis.gestao_moradias.service;

import com.moradiasestudantis.gestao_moradias.dto.RegisterDto;
import com.moradiasestudantis.gestao_moradias.model.User;
import com.moradiasestudantis.gestao_moradias.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilizador não encontrado com o email: " + username));
    }

    public void registerUser(RegisterDto registerDto) {
        if (userRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            throw new RuntimeException("Este email já está em uso.");
        }

        User newUser = new User();
        newUser.setEmail(registerDto.getEmail());
        newUser.setSenha(passwordEncoder.encode(registerDto.getSenha()));
        newUser.setRole(registerDto.getRole());

        userRepository.save(newUser);
    }
}
