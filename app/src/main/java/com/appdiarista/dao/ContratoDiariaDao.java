package com.appdiarista.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.appdiarista.model.ContratoDiaria;


public class ContratoDiariaDao {

    private DataBaseHelper db;

    public ContratoDiariaDao(Context context) {
        db = new DataBaseHelper(context);
    }

    public void inserir(ContratoDiaria contratoDiaria) throws SQLiteException, Exception{
            Log.i("msg", "id contrato-contratoDiaria: "+contratoDiaria.getContrato().getId());
            Log.i("msg", "id diaria-contratoDiaria: "+contratoDiaria.getDiaria().getId());
            ContentValues valuesUs = new ContentValues();
            valuesUs.put("contrato_id", contratoDiaria.getContrato().getId());
            valuesUs.put("diaria_id", contratoDiaria.getDiaria().getId());
            long nr = db.getWritableDatabase().insert("contrato_diaria", null, valuesUs);
            Log.i("msg", "inseriu contratoDiaria rows: " + nr);
    }

//    public int buscaContrato(int idDiaria,int idContratante){
//        Diarista diarista = null;
//        String sql = "select id from contrato where diarista_id=? and contratante_id=?";
//        Cursor cursor = db.getReadableDatabase().rawQuery(sql, new String[]{String.valueOf(idDiarista),String.valueOf(idContratante)});
//        int idx = 0;
//        while(cursor.moveToNext()){
//            idx = cursor.getInt(cursor.getColumnIndex("id"));
//        }
//        return idx;
//    }
}
