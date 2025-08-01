package com.moradiasestudantis.gestao_moradias.repository;

import com.moradiasestudantis.gestao_moradias.model.Residence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResidenceRepository extends JpaRepository<Residence, Long> {
    List<Residence> findByTipo(String tipo);
    List<Residence> findByFinalidade(String finalidade);
    List<Residence> findByTipoAndFinalidade(String tipo, String finalidade);
}
