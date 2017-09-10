package com.appdiarista.dao;

import android.content.Context;
import android.database.Cursor;

import com.appdiarista.model.Usuario;
import com.appdiarista.util.UsuarioUtil;


public class LoginDao {

    private DataBaseHelper db;
//    private SQLiteDatabase db;

    public LoginDao(Context context) {
        db = new DataBaseHelper(context);
    }

    public Usuario logar(String login, String senha){
        String[] parametros = {login, senha};
        Cursor c = db.getReadableDatabase().rawQuery("select login, tipoUsuario_id from usuario where login=? and senha=?", parametros);
        Usuario usuario = null;
        while(c.moveToNext()){
            usuario = UsuarioUtil.criarUsuarioDb(c);
        }
        return usuario;
    }

}
