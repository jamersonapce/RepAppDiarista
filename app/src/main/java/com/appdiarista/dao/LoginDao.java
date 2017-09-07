package com.appdiarista.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.appdiarista.model.TipoUsuario;
import com.appdiarista.model.Usuario;


public class LoginDao {

    private SQLiteDatabase db;

    public LoginDao(Context context) {
        db = new DataBaseHelper(context).getDb();
    }

    public Usuario logar(String login, String senha){
        String[] colunas = {"login", "tipoUsuario"};
        String where = "login = ? and senha = ?";
        String[] parametros = {login, senha};

        Cursor c = db.query("usuario",colunas, where, parametros, null, null, null);
        String loginDb = null;
        String tipoUsuarioDb = null;
        while(c.moveToFirst()){
            loginDb = c.getString(c.getColumnIndex("login"));
            tipoUsuarioDb = c.getString(c.getColumnIndex("tipoUsuario"));
        }
        return new Usuario(loginDb, null, new TipoUsuario(tipoUsuarioDb));
    }
}
