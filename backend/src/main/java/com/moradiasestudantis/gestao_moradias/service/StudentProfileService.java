package com.moradiasestudantis.gestao_moradias.service;

import com.moradiasestudantis.gestao_moradias.dto.StudentProfileDto;
import com.moradiasestudantis.gestao_moradias.model.StudentProfile;
import com.moradiasestudantis.gestao_moradias.model.User;
import com.moradiasestudantis.gestao_moradias.repository.StudentProfileRepository;
import com.moradiasestudantis.gestao_moradias.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentProfileService {

    @Autowired
    private StudentProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public StudentProfileDto getProfile(UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        StudentProfile profile = profileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Perfil de estudante não encontrado"));

        return new StudentProfileDto(
                profile.getNomeCompleto(),
                profile.getCpf(),
                profile.getIdade(),
                profile.getUniversidade(),
                profile.getCurso(),
                profile.isAceitaAnimais(),
                profile.isFumante());
    }

    @Transactional
    public StudentProfileDto createOrUpdateProfile(StudentProfileDto profileDto, UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        StudentProfile profile = profileRepository.findByUserId(user.getId())
                .orElse(new StudentProfile());

        profile.setUser(user);
        profile.setNomeCompleto(profileDto.nomeCompleto());
        profile.setCpf(profileDto.cpf());
        profile.setIdade(profileDto.idade());
        profile.setUniversidade(profileDto.universidade());
        profile.setCurso(profileDto.curso());
        profile.setAceitaAnimais(profileDto.aceitaAnimais());
        profile.setFumante(profileDto.fumante());

        profileRepository.save(profile);

        return profileDto;
    }
}