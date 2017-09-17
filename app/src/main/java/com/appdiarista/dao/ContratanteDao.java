package com.appdiarista.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

import com.appdiarista.model.Contratante;

import java.util.ArrayList;
import java.util.List;


public class ContratanteDao {

    private DataBaseHelper db;

    public ContratanteDao(Context context) {
        db = new DataBaseHelper(context);
    }

    public void inserir(Contratante contratante) throws SQLiteException, Exception{
        ContentValues valuesUs = new ContentValues();
        ContentValues valuesDr = new ContentValues();
        valuesDr.put("nome", contratante.getNome());
        valuesDr.put("email", contratante.getEmail());
        valuesUs.put("login", contratante.getEmail());
        valuesUs.put("senha", contratante.getSenha());
        valuesUs.put("tipoUsuario_id", contratante.getTipoUsuario().getId());
        valuesDr.put("dataNascimento", contratante.getDataNascimento());
        valuesDr.put("cpf", contratante.getCpf());
        valuesDr.put("telefone", contratante.getTelefone());
        valuesDr.put("longitude", contratante.getLongitude());
        valuesDr.put("latitude", contratante.getLatitude());
        valuesDr.put("sobreMim", contratante.getSobreMim());
        long nr = db.getWritableDatabase().insert("contratante", null, valuesDr);
        long nrU = 0;
        if (nr > 0)
            nrU = db.getWritableDatabase().insert("usuario", null, valuesUs);
    }
    public List<Contratante> solicitantes(String loginDiarista){

        List<Contratante> contratantes = new ArrayList<Contratante>();

//        Cursor cursor = db.getReadableDatabase().rawQuery("select id,nome,sobreMim, telefone from contratante", new String[]{});
        Cursor cursor = db.getReadableDatabase().rawQuery("select cte.id, cte.nome, cte.sobreMim, cte.telefone from contratante as cte" +
                " INNER JOIN contrato as cto  ON cto.contratante_id = cte.id" +
                " INNER JOIN diarista as dta  ON cto.diarista_id = dta.id" +
                " WHERE dta.email = ? group by cto.contratante_id", new String[]{loginDiarista});
        Contratante contratante;
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String sobreMim = cursor.getString(cursor.getColumnIndex("sobreMim"));
            String telefone = cursor.getString(cursor.getColumnIndex("telefone"));
            contratante = new Contratante(id,nome,telefone,sobreMim);
            contratantes.add(contratante);
        }


        return contratantes;
    }


    public int buscaContratante(String email){
        String sql = "select id from contratante where email=?";
        Cursor cursor = db.getReadableDatabase().rawQuery(sql, new String[]{email});
        int idx = 0;
        while(cursor.moveToNext()){
            idx = cursor.getInt(cursor.getColumnIndex("id"));
        }
        return idx;
    }

    public Contratante buscaContratante(int id){
        String sql = "select id, nome, telefone, sobreMim, latitude, longitude from contratante where id=?";
        Cursor cursor = db.getReadableDatabase().rawQuery(sql, new String[]{String.valueOf(id)});
        Contratante cont = null;
        while(cursor.moveToNext()){
            cont = new Contratante(
                cursor.getInt(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("nome")),
                cursor.getString(cursor.getColumnIndex("telefone")),
                cursor.getString(cursor.getColumnIndex("sobreMim")),
                cursor.getDouble(cursor.getColumnIndex("latitude")),
                cursor.getDouble(cursor.getColumnIndex("longitude")));
        }
        return cont;
    }
}
