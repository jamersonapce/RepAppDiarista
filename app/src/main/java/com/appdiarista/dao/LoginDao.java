package com.appdiarista.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.appdiarista.model.Usuario;
import com.appdiarista.util.UsuarioUtil;


public class LoginDao {

//    private DataBaseHelper db;
    private SQLiteDatabase db;

    public LoginDao(Context context) {
//        db = new DataBaseHelper(context).getDb();
//        db = Conexao.getBd();
//        db = new DataBaseHelper(context);
        db = Conexao.getBd();

    }

    public Usuario logar(String login, String senha){
//        execSqls(db.getWritableDatabase());

        String[] parametros = {login, senha};
        Cursor c = db.rawQuery("select login, tipoUsuario_id from usuario where login=? and senha=?", parametros);
        Usuario usuario = null;
        while(c.moveToFirst()){
            usuario = UsuarioUtil.criarUsuarioDb(c);
        }
        return usuario;
    }

    public void execSqls(SQLiteDatabase db1){
        ContentValues values = new ContentValues();
        values.put("id", 1);
        values.put("descricao", "DIARISTA");
        long r = db1.insert("tipoUsuario", null, values);
        Log.i("insert", "retorno " + r);

        ContentValues values2 = new ContentValues();
        values2.put("id", 2);
        values2.put("descricao", "CONTRATANTE");
        long r2 = db1.insert("tipoUsuario", null, values2);
        Log.i("insert", "retorno tipo usuario 2 " + r2);

        ContentValues values3 = new ContentValues();
        values3.put("login", "fredericond@hotmail.com");
        values3.put("senha", "1234");
        values3.put("tipoUsuario_id", 2);
        long r3 = db1.insert("usuario", null, values3);
        Log.i("insert", "retorno contratante " + r3);
    }
}
