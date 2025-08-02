package com.moradiasestudantis.gestao_moradias.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Residence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endereco;
    private String tipo;
    private String finalidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User owner;

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
