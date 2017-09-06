package com.appdiarista.model;

import java.math.BigDecimal;
import java.util.Date;

public class Diarista extends Usuario{
    private Integer id;
    private String nome;
    private String telefone;
    private Date dataNascimento;
    private String cpf;
    private String email;
    private String sobreMim;
    private BigDecimal valorDiaria;
    private Double latitude;
    private Double longitude;

    public Diarista() {
    }

    public Diarista(Integer id, String nome, String telefone, Date dataNascimento, String cpf, String email,
                    String sobreMim, BigDecimal valorDiaria, Double latitude, Double longitude) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.email = email;
        this.sobreMim = sobreMim;
        this.valorDiaria = valorDiaria;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Diarista(String nome, String telefone, Date dataNascimento, String cpf, String email, String sobreMim,
                    BigDecimal valorDiaria, Double latitude, Double longitude) {
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.email = email;
        this.sobreMim = sobreMim;
        this.valorDiaria = valorDiaria;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSobreMim() {
        return sobreMim;
    }

    public void setSobreMim(String sobreMim) {
        this.sobreMim = sobreMim;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(BigDecimal valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
