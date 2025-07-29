package com.moradiasestudantis.gestao_moradias.controller;

import com.moradiasestudantis.gestao_moradias.dto.StudentProfileDto;
import com.moradiasestudantis.gestao_moradias.service.StudentProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile/student")
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentProfileService;

    @GetMapping
    public ResponseEntity<StudentProfileDto> getStudentProfile(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(studentProfileService.getProfile(userDetails));
    }

    @PostMapping
    public ResponseEntity<StudentProfileDto> createStudentProfile(@RequestBody @Valid StudentProfileDto profileDto, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(studentProfileService.createOrUpdateProfile(profileDto, userDetails));
    }

    @PutMapping
    public ResponseEntity<StudentProfileDto> updateStudentProfile(@RequestBody @Valid StudentProfileDto profileDto, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(studentProfileService.createOrUpdateProfile(profileDto, userDetails));
    }
}