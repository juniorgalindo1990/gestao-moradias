package com.moradiasestudantis.gestao_moradias.dto;

import java.time.LocalDate;

import com.moradiasestudantis.gestao_moradias.model.Student;

import jakarta.validation.constraints.*;

public record StudentDto(
    Long id,

    @NotBlank(message = "O nome completo é obrigatório.")
    String nomeCompleto,

    @NotBlank(message = "O CPF é obrigatório.")
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos.")
    String cpf,

    @NotNull(message = "A data de nascimento é obrigatória.")
    @Min(value = 18, message = "Você deve ser maior de idade.")
    LocalDate dataNascimento,

    @NotBlank(message = "O telefone é obrigatório.")
    String telefone,

    @NotBlank(message = "O período atual é obrigatório.")
    String periodoAtual,

    @NotBlank(message = "O nome do curso é obrigatório.")
    String curso,

    @NotNull(message = "A preferência sobre wifi é obrigatória.")
    boolean wifi,

    @NotNull(message = "A preferência sobre garagem é obrigatória.")
    boolean garagem,

    @NotBlank(message = "A preferência sobre mobiliado é obrigatória.")
    boolean mobiliado,

    @NotNull(message = "A preferência sobre banheiro privativo é obrigatória.")
    boolean banheiroPrivativo
) {
    public StudentDto(Student profile) {
        this(
            profile.getId(),
            profile.getNomeCompleto(),
            profile.getCpf(),
            profile.getDataNascimento(),
            profile.getTelefone(),
            profile.getPeriodoAtual(),
            profile.getCurso(),
            profile.isWifi(),
            profile.isGaragem(),
            profile.isMobiliado(),
            profile.isBanheiroPrivativo()
        );
    }
}