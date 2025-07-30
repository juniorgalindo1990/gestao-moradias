package com.moradiasestudantis.gestao_moradias.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstudanteFiltroDto {
    private String preferenciaMoradia;
    private Boolean aceitaPets;
    private Boolean precisaMobilidadeReduzida;
}
