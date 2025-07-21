package com.moradiasestudantis.gestao_moradias.service;

import com.moradiasestudantis.gestao_moradias.num.RoleEnum;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service; 

import com.moradiasestudantis.gestao_moradias.dto.RegisterDto;
import com.moradiasestudantis.gestao_moradias.entity.User;
import com.moradiasestudantis.gestao_moradias.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;


	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public void register(RegisterDto dto) {
		if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
			throw new IllegalStateException("E-mail já cadastrado.");
		}

		User user = new User();
		user.setEmail(dto.getEmail());
	
		user.setSenha(passwordEncoder.encode(dto.getSenha()));

		user.setRole(RoleEnum.valueOf(dto.getRole().toUpperCase()));

		userRepository.save(user);
	}
}