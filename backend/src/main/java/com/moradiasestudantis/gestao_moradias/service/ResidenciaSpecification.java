package com.moradiasestudantis.gestao_moradias.service;


import com.moradiasestudantis.gestao_moradias.entity.Residencia;
import org.springframework.data.jpa.domain.Specification;

public class ResidenciaSpecification {

    public static Specification<Residencia> filtrar(String tipo, String finalidade) {
        return (root, query, builder) -> {
            Specification<Residencia> spec = Specification.where(null);

            if (tipo != null && !tipo.isEmpty()) {
                spec = spec.and((r, q, cb) -> cb.equal(r.get("tipo"), tipo));
            }
            if (finalidade != null && !finalidade.isEmpty()) {
                spec = spec.and((r, q, cb) -> cb.equal(r.get("finalidade"), finalidade));
            }

            return spec.toPredicate(root, query, builder);
        };
    }
}