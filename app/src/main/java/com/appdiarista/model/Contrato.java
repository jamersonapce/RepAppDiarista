package com.appdiarista.model;

import java.util.Date;

/**
 * Created by FR£D on 06/09/2017.
 */

public class Contrato {
    private Integer id;
    private Date dataSolicitacao;
    private Date dataAceito;
    private Diarista diarista;
    private Contratante contratante;

    public Contrato() {
    }

    public Contrato(Date dataSolicitacao, Date dataAceito, Diarista diarista, Contratante contratante) {
        this.dataSolicitacao = dataSolicitacao;
        this.dataAceito = dataAceito;
        this.diarista = diarista;
        this.contratante = contratante;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Date getDataAceito() {
        return dataAceito;
    }

    public void setDataAceito(Date dataAceito) {
        this.dataAceito = dataAceito;
    }

    public Diarista getDiarista() {
        return diarista;
    }

    public void setDiarista(Diarista diarista) {
        this.diarista = diarista;
    }

    public Contratante getContratante() {
        return contratante;
    }

    public void setContratante(Contratante contratante) {
        this.contratante = contratante;
    }
}
