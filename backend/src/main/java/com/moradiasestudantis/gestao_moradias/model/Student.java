package com.moradiasestudantis.gestao_moradias.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "student_profiles")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    @Size(min = 11, max = 11)
    @Pattern(regexp = "^[0-9]+$")
    private String cpf;

    @NotNull
    @Past
    private LocalDate dataNascimento;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10,11}$")
    private String telefone;

    @NotBlank
    private String periodoAtual;

    @NotBlank
    private String curso;
    
    @NotBlank
    private String universidade;

    @NotNull
    private boolean wifi;

    @NotNull
    private boolean garagem;

    @NotNull
    private boolean mobiliado;

    @NotNull
    private boolean banheiroPrivativo;

    @NotNull
    private boolean aceitaAnimais;

    @NotNull
    private boolean fumante;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getPeriodoAtual() {
        return periodoAtual;
    }

    public void setPeriodoAtual(String periodoAtual) {
        this.periodoAtual = periodoAtual;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isGaragem() {
        return garagem;
    }

    public void setGaragem(boolean garagem) {
        this.garagem = garagem;
    }

    public boolean isMobiliado() {
        return mobiliado;
    }

    public void setMobiliado(boolean mobiliado) {
        this.mobiliado = mobiliado;
    }

    public boolean isBanheiroPrivativo() {
        return banheiroPrivativo;
    }

    public void setBanheiroPrivativo(boolean banheiroPrivativo) {
        this.banheiroPrivativo = banheiroPrivativo;
    }

    public boolean isAceitaAnimais() {
        return aceitaAnimais;
    }

    public void setAceitaAnimais(boolean aceitaAnimais) {
        this.aceitaAnimais = aceitaAnimais;
    }

    public boolean isFumante() {
        return fumante;
    }

    public void setFumante(boolean fumante) {
        this.fumante = fumante;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}