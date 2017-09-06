package com.appdiarista.model;

import java.util.Date;

public class Diaria {

    private Integer id;
    private Date data;

    public Diaria() {
    }

    public Diaria(Integer id, Date data) {
        this.id = id;
        this.data = data;
    }

    public Diaria(Date data) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
