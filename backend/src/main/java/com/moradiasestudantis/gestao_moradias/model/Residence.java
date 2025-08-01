package com.moradiasestudantis.gestao_moradias.model;

import jakarta.persistence.*;

@Entity
public class Residence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endereco;
    private String tipo;
    private String finalidade;

    public Residence() {
    }

    public Residence(String endereco, String tipo, String finalidade) {
        this.endereco = endereco;
        this.tipo = tipo;
        this.finalidade = finalidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }
}
