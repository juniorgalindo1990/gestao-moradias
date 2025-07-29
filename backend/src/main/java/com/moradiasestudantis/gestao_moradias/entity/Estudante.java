package com.moradiasestudantis.gestao_moradias.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estudante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String preferenciaMoradia; 
    private Boolean aceitaPets;
    private Boolean precisaMobilidadeReduzida;
}
