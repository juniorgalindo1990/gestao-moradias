package com.moradiasestudantis.gestao_moradias.service;

import com.moradiasestudantis.gestao_moradias.entity.Residencia;
import com.moradiasestudantis.gestao_moradias.repository.ResidenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResidenciaService {

    private final ResidenciaRepository residenciaRepository;

    public List<Residencia> buscarPorFiltro(String tipo, String finalidade) {
        Specification<Residencia> spec = ResidenciaSpecification.filtrar(tipo, finalidade);
        return residenciaRepository.findAll(spec);
    }
}
