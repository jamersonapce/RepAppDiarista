package com.appdiarista.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.appdiarista.util.DataBaseSql;

public class DataBaseHelper extends SQLiteOpenHelper{
    private SQLiteDatabase db;

    public DataBaseHelper(Context context) {
        super(context, "appDiarista", null, 8);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("msg", "vai executar sql");
        db. execSQL(DataBaseSql.CT_TIPOUSUARIO);
        db. execSQL(DataBaseSql.CT_USUARIO);
        db. execSQL(DataBaseSql.CT_CONTRATANTE);
        db. execSQL(DataBaseSql.CT_DIARISTA);
        db. execSQL(DataBaseSql.CT_CONTRATO);
        db. execSQL(DataBaseSql.CT_DIARIA);
        db. execSQL(DataBaseSql.CT_CONTRATO_DIARIA);

        execSqlsInit(db);
        Log.i("msg", "executou sqls");

    }

    public void execSqlsInit(SQLiteDatabase db) {
        db.execSQL("insert into tipoUsuario(id,descricao) values (1,'DIARISTA')");
        db.execSQL("insert into tipoUsuario(id,descricao) values (2,'CONTRATANTE')");
        db.execSQL("insert into usuario (login,senha,tipoUsuario_id) values('fredericond@hotmail.com','1234',2)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("msg", "entrou no onUpgrade");

    }

}
