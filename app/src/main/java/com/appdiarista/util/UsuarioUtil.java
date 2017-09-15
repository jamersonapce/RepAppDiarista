package com.appdiarista.util;


import android.database.Cursor;

import com.appdiarista.model.TipoUsuario;
import com.appdiarista.model.Usuario;

public class UsuarioUtil {

    public static Usuario criarUsuarioDb(Cursor cursor){
        Usuario model = new Usuario(
                cursor.getString(cursor.getColumnIndex("login")),
                null,
                new TipoUsuario(cursor.getInt(cursor.getColumnIndex("tipoUsuario_id")),null)
        );
        return model;
    }
}
