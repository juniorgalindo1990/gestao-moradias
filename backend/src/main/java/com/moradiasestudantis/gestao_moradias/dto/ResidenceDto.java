package com.moradiasestudantis.gestao_moradias.dto;

import com.moradiasestudantis.gestao_moradias.model.Residence;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record ResidenceDto(
        Long id,

        @NotBlank(message = "O endereço é obrigatório.")
        String address,

        String description,

        @PositiveOrZero(message = "O preço deve ser um valor positivo ou zero.")
        double price,

        Long ownerId
) {
    // Construtor para converter a entidade Residence em DTO
    public ResidenceDto(Residence residence) {
        this(
                residence.getId(),
                residence.getAddress(),
                residence.getDescription(),
                residence.getPrice(),
                residence.getOwner().getId()
        );
    }
}
