package com.appdiarista.util;

/**
 * Created by FR£D on 06/09/2017.
 */

public class DataBaseSql {

    public static String CTUSUARIO = "CREATE TABLE usuario (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, login VARCHAR UNIQUE NOT NULL, senha VARCHAR NOT NULL, tipoUsuario_id INTEGER REFERENCES tipoUsuario (id));";
    public static String CTTIPOUSUARIO ="CREATE TABLE tipoUsuario (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, descricao VARCHAR UNIQUE NOT NULL);";
//            "CREATE TABLE contratante (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome VARCHAR NOT NULL, telefone VARCHAR NOT NULL, dataNascimento DATE, cpf VARCHAR NOT NULL, email VARCHAR, sobreMim VARCHAR, valorDiaria DECIMAL NOT NULL, latitude DOUBLE, longitude DOUBLE);" +
//            "CREATE TABLE contrato (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, dataSolicitacao DATE, dataAceito DATE, contratante_id INTEGER REFERENCES contratante (id), diarista_id INTEGER REFERENCES diarista (id));" +
//            "CREATE TABLE contrato_diaria (contratato_id INTEGER REFERENCES contrato (id), diaria_id INTEGER REFERENCES diaria (id));" +
//            "CREATE TABLE diaria (id INTEGER PRIMARY KEY AUTOINCREMENT, data DATE NOT NULL);" +
//            "CREATE TABLE diarista (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome VARCHAR NOT NULL, telefone VARCHAR NOT NULL, dataNascimento DATE, cpf VARCHAR NOT NULL, email VARCHAR, sobreMim VARCHAR, valorDiaria DECIMAL NOT NULL, latitude DOUBLE, longitude DOUBLE);" +


}
