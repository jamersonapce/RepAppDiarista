package com.appdiarista.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;

import com.appdiarista.model.Diarista;


public class DiaristaDao {

    private DataBaseHelper db;

    public DiaristaDao(Context context) {
        db = new DataBaseHelper(context);
    }

    public void inserir(Diarista diarista) throws SQLiteException, Exception{
//        try {
            ContentValues valuesUs = new ContentValues();
            ContentValues valuesDr = new ContentValues();
            valuesDr.put("nome", diarista.getNome());
            valuesDr.put("email", diarista.getEmail());
            valuesUs.put("login", diarista.getEmail());
            valuesUs.put("senha", diarista.getSenha());
            valuesUs.put("tipoUsuario_id", 1);
            valuesDr.put("dataNascimento", diarista.getDataNascimento());
            valuesDr.put("cpf", diarista.getCpf());
            valuesDr.put("telefone", diarista.getTelefone());
            valuesDr.put("longitude", diarista.getLongitude());
            valuesDr.put("latitude", diarista.getLatitude());
            valuesDr.put("valorDiaria", diarista.getValorDiaria());
            valuesDr.put("sobreMim", diarista.getSobreMim());
            long nr = db.getWritableDatabase().insert("diarista", null, valuesDr);
            long nrU = 0;
            if (nr > 0)
                nrU = db.getWritableDatabase().insert("usuario", null, valuesUs);
//        }catch (SQLiteException sqle){
//            Log.e("error","Erro ao inserir Diarista: "+sqle.getMessage());
//        }
    }

}
