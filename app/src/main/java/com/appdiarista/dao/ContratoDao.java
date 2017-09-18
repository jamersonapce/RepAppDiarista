package com.appdiarista.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.appdiarista.model.Contrato;
import com.appdiarista.model.Diarista;
import com.appdiarista.util.DataUtil;


public class ContratoDao {

    private DataBaseHelper db;

    public ContratoDao(Context context) {
        db = new DataBaseHelper(context);
    }

    public int inserir(Contrato contrato) throws SQLiteException, Exception{
        Log.i("msg", "id diarista-contrato: "+contrato.getDiarista().getId());
        Log.i("msg", "id contratante-contrato: "+contrato.getContratante().getId());
            ContentValues valuesUs = new ContentValues();
            valuesUs.put("dataSolicitacao", contrato.getDataSolicitacao());
            valuesUs.put("dataAceito", contrato.getDataAceito());
            valuesUs.put("diarista_id", contrato.getDiarista().getId());
            valuesUs.put("contratante_id", contrato.getContratante().getId());
            long nr = db.getWritableDatabase().insert("contrato", null, valuesUs);
            int id = 0;
            if (nr > 0)
                id = buscaContrato(contrato.getDiarista().getId(), contrato.getContratante().getId());
        return id;
    }

    public int buscaContrato(int idDiarista,int idContratante){
        Diarista diarista = null;
        String sql = "select id from contrato where diarista_id=? and contratante_id=?";
        Cursor cursor = db.getReadableDatabase().rawQuery(sql, new String[]{String.valueOf(idDiarista),String.valueOf(idContratante)});
        int idx = 0;
        while(cursor.moveToNext()){
            idx = cursor.getInt(cursor.getColumnIndex("id"));
        }
        return idx;
    }

    public boolean ehContratoAceito(int idDiarista,int idContratante){
        Diarista diarista = null;
        String sql = "select id from contrato where diarista_id=? and contratante_id=? and not dataAceito is null";
        Cursor cursor = db.getReadableDatabase().rawQuery(sql, new String[]{String.valueOf(idDiarista),String.valueOf(idContratante)});
        int idx = 0;
        while(cursor.moveToNext()){
            idx = cursor.getInt(cursor.getColumnIndex("id"));
        }
        return idx > 0;
    }

    public void aceitaContrato(String idContratante, String idDiarista){
        String[]args = new String[]{idContratante,idDiarista};
        ContentValues values = new ContentValues();
        values.put("dataAceito", DataUtil.dataHoje());
        db.getWritableDatabase().update("contrato",values,"contratante_id=? and diarista_id=?",args);
    }
}
