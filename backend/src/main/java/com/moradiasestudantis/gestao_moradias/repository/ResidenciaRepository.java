package com.moradiasestudantis.gestao_moradias.repository;

import com.moradiasestudantis.gestao_moradias.entity.Residencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ResidenciaRepository extends JpaRepository<Residencia, Long>, JpaSpecificationExecutor<Residencia> {
}