package com.appdiarista.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

import com.appdiarista.model.Diaria;


public class DiariaDao {

    private DataBaseHelper db;

    public DiariaDao(Context context) {
        db = new DataBaseHelper(context);
    }

    public int inserir(Diaria diaria) throws SQLiteException, Exception{
            ContentValues valuesUs = new ContentValues();
            valuesUs.put("data", diaria.getData());
            long nr = db.getWritableDatabase().insert("diaria", null, valuesUs);
            int id = 0;
            if (nr > 0)
                id = buscaDiaria(diaria.getData());
        return id;
    }

    public int buscaDiaria(String data){
        String sql = "select id from diaria where data=?";
        Cursor cursor = db.getReadableDatabase().rawQuery(sql, new String[]{data});
        int idx = 0;
        while(cursor.moveToNext()){
            idx = cursor.getInt(cursor.getColumnIndex("id"));
        }
        return idx;
    }
}
