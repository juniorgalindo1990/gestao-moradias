package com.moradiasestudantis.gestao_moradias.dto;

import com.moradiasestudantis.gestao_moradias.model.Residence;
import java.math.BigDecimal;
import java.util.List;

public class ResidenceDto {

    private Long id;
    private String descricao;
    private String tipo;
    private String finalidade;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private boolean wifi;
    private boolean garagem;
    private boolean mobiliado;
    private boolean banheiroPrivativo;
    private BigDecimal valorAluguel;
    private String nomeProprietario;
    private String emailProprietario;
    private List<String> fotos;

    public ResidenceDto(Residence residence) {
        this.id = residence.getId();
        this.descricao = residence.getDescricao();
        this.tipo = residence.getTipo();
        this.finalidade = residence.getFinalidade();
        this.logradouro = residence.getLogradouro();
        this.bairro = residence.getBairro();
        this.cidade = residence.getCidade();
        this.estado = residence.getEstado();
        this.cep = residence.getCep();
        this.wifi = residence.isWifi();
        this.garagem = residence.isGaragem();
        this.mobiliado = residence.isMobiliado();
        this.banheiroPrivativo = residence.isBanheiroPrivativo();
        this.valorAluguel = residence.getValorAluguel();
        this.nomeProprietario = residence.getOwner().getName();
        this.emailProprietario = residence.getOwner().getEmail();
        this.fotos = residence.getFotos();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public BigDecimal getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(BigDecimal valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public String getEmailProprietario() {
        return emailProprietario;
    }

    public void setEmailProprietario(String emailProprietario) {
        this.emailProprietario = emailProprietario;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }
}
