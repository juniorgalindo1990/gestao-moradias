package com.moradiasestudantis.gestao_moradias.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Residencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;       // Ex: Apartamento, Casa, Kitnet
    private String finalidade; // Ex: Estudantil, Familiar
    private String endereco;
    private String cidade;
    private String estado;
}