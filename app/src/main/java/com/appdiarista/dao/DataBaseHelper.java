package com.appdiarista.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.appdiarista.util.DataBaseSql;

public class DataBaseHelper extends SQLiteOpenHelper{
    private SQLiteDatabase db;

    public DataBaseHelper(Context context) {
        super(context, "appDiarista", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db. execSQL(DataBaseSql.SQL);
        db.execSQL("insert into tipousuario(id,descricao) values (1,'DIARISTA')");
        db.execSQL("insert into tipousuario(id,descricao) values (2,'CONTRATANTE')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase getDb(){
        if(db==null){
            db = this.getWritableDatabase();
        }
        return db;
    }
}
