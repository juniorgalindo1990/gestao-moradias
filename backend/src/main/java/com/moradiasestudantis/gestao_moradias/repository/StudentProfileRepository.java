package com.moradiasestudantis.gestao_moradias.repository;

import com.moradiasestudantis.gestao_moradias.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
    
    Optional<StudentProfile> findByUserId(Long userId);
}