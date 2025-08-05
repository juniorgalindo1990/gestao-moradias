package com.moradiasestudantis.gestao_moradias.dto;

public class StudentFilterDto {
    private boolean wifi;
    private Boolean garagem;
    private Boolean banheiroPrivativo;

    // Construtor padrão
    public StudentFilterDto() {
    }

    // Construtor com todos os argumentos
    public StudentFilterDto(boolean wifi, Boolean garagem, Boolean banheiroPrivativo) {
        this.wifi = wifi;
        this.garagem = garagem;
        this.banheiroPrivativo = banheiroPrivativo;
    }

    // Getters e Setters
    public boolean getWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getGaragem() {
        return garagem;
    }

    public void setGaragem(Boolean garagem) {
        this.garagem = garagem;
    }

    public Boolean getBanheiroPrivativo() {
        return banheiroPrivativo;
    }

    public void setBanheiroPrivativo(Boolean banheiroPrivativo) {
        this.banheiroPrivativo = banheiroPrivativo;
    }
}