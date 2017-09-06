package com.appdiarista.model;

public class ContratoDiaria {

    private Contrato contrato;
    private Diaria diaria;

    public ContratoDiaria() {
    }

    public ContratoDiaria(Contrato contrato, Diaria diaria) {
        this.contrato = contrato;
        this.diaria = diaria;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Diaria getDiaria() {
        return diaria;
    }

    public void setDiaria(Diaria diaria) {
        this.diaria = diaria;
    }
}
