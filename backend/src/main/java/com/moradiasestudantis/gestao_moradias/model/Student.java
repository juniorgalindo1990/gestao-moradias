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

    @NotBlank(message = "Nome completo é obrigatório")
    private String nomeCompleto;
    
    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 11, message = "CPF deve ter 11 caracteres")
    @Pattern(regexp = "^[0-9]+$", message = "CPF deve conter apenas números")
    private String cpf;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento deve ser no passado")
    private LocalDate dataNascimento;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "Telefone deve ter 10 ou 11 dígitos")
    private String telefone;

    @NotBlank(message = "Período atual é obrigatório")
    private String periodoAtual;

    @NotBlank(message = "Curso é obrigatório")
    private String curso;
    private boolean wifi;
    private boolean garagem;
    private boolean mobiliado;
    private boolean banheiroPrivativo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    
    public Student() {
    }    

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}