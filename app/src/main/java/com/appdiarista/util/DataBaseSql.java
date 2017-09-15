package com.appdiarista.util;

/**
 * Created by FR£D on 06/09/2017.
 */

public class DataBaseSql {

    public static String CT_TIPOUSUARIO ="CREATE TABLE tipoUsuario (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, descricao VARCHAR NOT NULL)";
    public static String CT_USUARIO = "CREATE TABLE usuario (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, login VARCHAR UNIQUE NOT NULL, senha VARCHAR NOT NULL, tipoUsuario_id INTEGER REFERENCES tipoUsuario (id))";
    public static String CT_CONTRATANTE = "CREATE TABLE contratante (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome VARCHAR NOT NULL, telefone VARCHAR NOT NULL, dataNascimento VARCHAR, cpf VARCHAR NOT NULL, email VARCHAR, sobreMim VARCHAR, latitude DOUBLE, longitude DOUBLE)";
    public static String CT_DIARISTA    = "CREATE TABLE diarista (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome VARCHAR NOT NULL, telefone VARCHAR NOT NULL, dataNascimento VARCHAR, cpf VARCHAR NOT NULL, email VARCHAR, sobreMim VARCHAR, valorDiaria DECIMAL NOT NULL, latitude DOUBLE, longitude DOUBLE)";
    public static String CT_CONTRATO    = "CREATE TABLE contrato (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, dataSolicitacao VARCHAR, dataAceito VARCHAR, contratante_id INTEGER REFERENCES contratante (id), diarista_id INTEGER REFERENCES diarista (id))";
    public static String CT_DIARIA    = "CREATE TABLE diaria (id INTEGER PRIMARY KEY AUTOINCREMENT, data VARCHAR NOT NULL)";
    public static String CT_CONTRATO_DIARIA    = "CREATE TABLE contrato_diaria (contratato_id INTEGER REFERENCES contrato (id), diaria_id INTEGER REFERENCES diaria (id))";




}
