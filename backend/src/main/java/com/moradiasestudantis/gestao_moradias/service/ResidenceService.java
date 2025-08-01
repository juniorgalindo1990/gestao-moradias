package com.moradiasestudantis.gestao_moradias.service;

import com.moradiasestudantis.gestao_moradias.model.Residence;
import com.moradiasestudantis.gestao_moradias.repository.ResidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ResidenceService {

    @Autowired
    private ResidenceRepository residenceRepository;

    public List<Residence> findAll() {
        return residenceRepository.findAll();
    }

    public Residence save(Residence residence) {
        return residenceRepository.save(residence);
    }

    public List<Residence> filterResidences(String tipo, String finalidade) {
        if (StringUtils.hasText(tipo) && StringUtils.hasText(finalidade)) {
            return residenceRepository.findByTipoAndFinalidade(tipo, finalidade);
        } else if (StringUtils.hasText(tipo)) {
            return residenceRepository.findByTipo(tipo);
        } else if (StringUtils.hasText(finalidade)) {
            return residenceRepository.findByFinalidade(finalidade);
        } else {
            return residenceRepository.findAll();
        }
    }
}
