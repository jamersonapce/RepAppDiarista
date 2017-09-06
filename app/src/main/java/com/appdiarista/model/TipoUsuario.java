package com.appdiarista.model;


public class TipoUsuario {

    private Integer id;
    private String descricao;

    public TipoUsuario() {
    }

    public TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public TipoUsuario(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
