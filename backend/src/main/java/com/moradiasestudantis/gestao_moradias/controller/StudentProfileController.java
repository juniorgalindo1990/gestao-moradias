package com.moradiasestudantis.gestao_moradias.controller;

import com.moradiasestudantis.gestao_moradias.model.Student;
import com.moradiasestudantis.gestao_moradias.model.User;
import com.moradiasestudantis.gestao_moradias.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class StudentProfileController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/student")
    public ResponseEntity<Student> getStudentProfile(@AuthenticationPrincipal User user) {
        return studentRepository.findByUserId(user.getId())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/student")
    public ResponseEntity<Student> createStudentProfile(@RequestBody @Valid Student studentProfile, @AuthenticationPrincipal User user) {
        if (studentRepository.findByUserId(user.getId()).isPresent()) {
            return ResponseEntity.badRequest().body(null); 
        }

        studentProfile.setUser(user);
        Student savedProfile = studentRepository.save(studentProfile);
        return ResponseEntity.ok(savedProfile);
    }

    @PutMapping("/student")
    public ResponseEntity<Student> updateStudentProfile(@RequestBody @Valid Student studentDetails, @AuthenticationPrincipal User user) {
        Optional<Student> existingProfileOpt = studentRepository.findByUserId(user.getId());

        if (existingProfileOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Student existingProfile = existingProfileOpt.get();
        
        existingProfile.setNomeCompleto(studentDetails.getNomeCompleto());
        existingProfile.setCpf(studentDetails.getCpf());
        existingProfile.setDataNascimento(studentDetails.getDataNascimento());
        existingProfile.setTelefone(studentDetails.getTelefone());
        existingProfile.setCurso(studentDetails.getCurso());
        existingProfile.setPeriodoAtual(studentDetails.getPeriodoAtual());
        existingProfile.setUniversidade(studentDetails.getUniversidade());
        existingProfile.setAceitaAnimais(studentDetails.isAceitaAnimais());
        existingProfile.setFumante(studentDetails.isFumante());
        existingProfile.setWifi(studentDetails.isWifi());
        existingProfile.setGaragem(studentDetails.isGaragem());
        existingProfile.setMobiliado(studentDetails.isMobiliado());
        existingProfile.setBanheiroPrivativo(studentDetails.isBanheiroPrivativo());
        
        Student updatedProfile = studentRepository.save(existingProfile);
        return ResponseEntity.ok(updatedProfile);
    }
}