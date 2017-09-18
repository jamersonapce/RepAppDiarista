package com.appdiarista.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

import com.appdiarista.model.Diarista;

import java.util.ArrayList;
import java.util.List;


public class DiaristaDao {

    private DataBaseHelper db;

    public DiaristaDao(Context context) {
        db = new DataBaseHelper(context);
    }

    public void inserir(Diarista diarista) throws SQLiteException, Exception{
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
    }
    public List<Diarista> listAll(){
        List<Diarista> diaristas = new ArrayList<Diarista>();
        Cursor cursor = db.getReadableDatabase().rawQuery("select id,nome,sobreMim, valorDiaria from diarista", new String[]{});
        Diarista diarista;
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String sobreMim = cursor.getString(cursor.getColumnIndex("sobreMim"));
            double valor = cursor.getDouble(cursor.getColumnIndex("valorDiaria"));
            diarista = new Diarista(id,nome,sobreMim, valor);
            diaristas.add(diarista);
        }
        return diaristas;
    }

    public Diarista buscaDiarista(int id){
        Diarista diarista = null;
        String sql = "select id,nome,sobreMim, valorDiaria, latitude, longitude from diarista where id=?";
        Cursor cursor = db.getReadableDatabase().rawQuery(sql, new String[]{String.valueOf(id)});
        while(cursor.moveToNext()){
            int idx = cursor.getInt(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String sobreMim = cursor.getString(cursor.getColumnIndex("sobreMim"));
            double valor = cursor.getDouble(cursor.getColumnIndex("valorDiaria"));
            double latitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
            double longitude = cursor.getDouble(cursor.getColumnIndex("longitude"));
            diarista = new Diarista(idx,nome,sobreMim, valor, latitude, longitude);
        }
        return diarista;
    }

    public Diarista buscaDiarista(String login){
        Diarista diarista = null;
        String sql = "select id from diarista where email=?";
        Cursor cursor = db.getReadableDatabase().rawQuery(sql, new String[]{login});
        while(cursor.moveToNext()){
            int idx = cursor.getInt(cursor.getColumnIndex("id"));
            diarista = new Diarista(idx);
        }
        return diarista;
    }
}
