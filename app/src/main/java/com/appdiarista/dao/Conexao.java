package com.appdiarista.dao;

import android.database.sqlite.SQLiteDatabase;

import com.appdiarista.util.DataBaseSql;

/**
 * Created by FR£D on 09/09/2017.
 */

public class Conexao {
    private static SQLiteDatabase db;

    public static SQLiteDatabase  getBd(){
//        File file = new File("/sdcard/bd_interno/");
//        db = SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory()+"/bd_interno/"+"appdiarista.db", null);
//        if(file.listFiles() != null)
//            for(File ff : file.listFiles())
//                Log.i("msg","-"+ff.getName());
        if(db == null) {
            db = SQLiteDatabase.openOrCreateDatabase("/sdcard/bd_interno/appdiarista.db", null);
            db. execSQL(DataBaseSql.CTTIPOUSUARIO);
            db. execSQL(DataBaseSql.CTUSUARIO);
            db.execSQL("insert into tipoUsuario(id,descricao) values (1,'DIARISTA');");
            db.execSQL("insert into tipoUsuario(id,descricao) values (2,'CONTRATANTE');");
            db.execSQL("insert into usuario (login,senha,tipoUsuario_id) values('fredericond@hotmail.com','1234',2);");
        }
        return db;
    }
}
