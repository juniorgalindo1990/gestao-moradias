package com.moradiasestudantis.gestao_moradias.service;

import com.moradiasestudantis.gestao_moradias.entity.Estudante;
import com.moradiasestudantis.gestao_moradias.repository.EstudanteRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudanteService {

    private final EstudanteRepository estudanteRepository;

    public List<Estudante> buscarPorPreferencias(String preferenciaMoradia, Boolean aceitaPets, Boolean precisaMobilidadeReduzida) {
        Specification<Estudante> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (preferenciaMoradia != null && !preferenciaMoradia.isBlank()) {
                predicates.add(builder.equal(root.get("preferenciaMoradia"), preferenciaMoradia));
            }
            if (aceitaPets != null) {
                predicates.add(builder.equal(root.get("aceitaPets"), aceitaPets));
            }
            if (precisaMobilidadeReduzida != null) {
                predicates.add(builder.equal(root.get("precisaMobilidadeReduzida"), precisaMobilidadeReduzida));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };

        return estudanteRepository.findAll(spec);
    }
}
