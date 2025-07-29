package com.moradiasestudantis.gestao_moradias.dto;

import jakarta.validation.constraints.*;

public record StudentProfileDto(
    @NotBlank(message = "O nome completo é obrigatório.")
    String nomeCompleto,

    @NotBlank(message = "O CPF é obrigatório.")
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos.")
    String cpf,

    @NotNull(message = "A idade é obrigatória.")
    @Min(value = 18, message = "Você deve ser maior de idade.")
    int idade,

    @NotBlank(message = "O nome da universidade é obrigatório.")
    String universidade,

    @NotBlank(message = "O nome do curso é obrigatório.")
    String curso,

    @NotNull(message = "A preferência sobre animais é obrigatória.")
    boolean aceitaAnimais,

    @NotNull(message = "A preferência sobre fumo é obrigatória.")
    boolean fumante
) {
}