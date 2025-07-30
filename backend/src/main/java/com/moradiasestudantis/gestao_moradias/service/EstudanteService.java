package com.moradiasestudantis.gestao_moradias.service;

import com.moradiasestudantis.gestao_moradias.entity.Estudante;
import com.moradiasestudantis.gestao_moradias.repository.EstudanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudanteService {

    private final EstudanteRepository estudanteRepository;

    public List<Estudante> buscarPorPreferencias(String preferenciaMoradia, Boolean aceitaPets, Boolean precisaMobilidadeReduzida) {
        Specification<Estudante> spec = EstudanteSpecification.filtrar(preferenciaMoradia, aceitaPets, precisaMobilidadeReduzida);
        return estudanteRepository.findAll(spec);
    }
}