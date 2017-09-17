package com.appdiarista.model;


/**
 * Created by FR£D on 06/09/2017.
 */

public class Contrato {
    private Integer id;
    private String dataSolicitacao;
    private String dataAceito;
    private Diarista diarista;
    private Contratante contratante;

    public Contrato() {
    }
    public Contrato(int id) {
        this.id = id;
    }

    public Contrato(String dataSolicitacao, String dataAceito, Diarista diarista, Contratante contratante) {
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

    public String getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(String dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getDataAceito() {
        return dataAceito;
    }

    public void setDataAceito(String dataAceito) {
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
