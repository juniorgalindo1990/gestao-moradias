package com.moradiasestudantis.gestao_moradias.service;

import com.moradiasestudantis.gestao_moradias.entity.Estudante;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class EstudanteSpecification {

    public static Specification<Estudante> filtrar(String preferenciaMoradia, Boolean aceitaPets, Boolean precisaMobilidadeReduzida) {
        return (root, query, builder) -> {
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
    }
}