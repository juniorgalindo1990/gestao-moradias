package com.moradiasestudantis.gestao_moradias.dto;

import com.moradiasestudantis.gestao_moradias.model.Residence;
import jakarta.validation.constraints.NotBlank;

public record ResidenceDto(
        Long id,

        @NotBlank(message = "O endereço é obrigatório.")
        String endereco,

        String tipo,

        String finalidade
) {
    
    public ResidenceDto(Residence residence) {
        this(
            residence.getId(),
            residence.getEndereco(),
            residence.getTipo(),
            residence.getFinalidade()
        );
    }
}
