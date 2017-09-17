package com.appdiarista.model;


public class Diaria {

    private Integer id;
    private String data;

    public Diaria() {
    }

    public Diaria(Integer id, String data) {
        this.id = id;
        this.data = data;
    }

    public Diaria(String data) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
