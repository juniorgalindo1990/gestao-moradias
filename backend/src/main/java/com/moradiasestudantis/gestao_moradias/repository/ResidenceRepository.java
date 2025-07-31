package com.moradiasestudantis.gestao_moradias.repository;

import com.moradiasestudantis.gestao_moradias.model.Residence;
import com.moradiasestudantis.gestao_moradias.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ResidenceRepository extends JpaRepository<Residence, Long>, JpaSpecificationExecutor<Residence> {

    /**
     * Encontra todas as residências associadas a um usuário (proprietário).
     * @param owner O usuário proprietário.
     * @return Uma lista de residências.
     */
    List<Residence> findByOwner(User owner);
}
