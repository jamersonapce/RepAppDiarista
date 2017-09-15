package com.appdiarista.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.appdiarista.appdiarista.R;
import com.appdiarista.dao.LoginDao;
import com.appdiarista.model.Usuario;

public class LoginActivity extends AppCompatActivity {
    private EditText etLogin;
    private TextInputLayout tilSenha;
    LoginDao loginDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginDao = new LoginDao(this);

        etLogin = (EditText) findViewById(R.id.etLogin);
        tilSenha = (TextInputLayout) findViewById(R.id.tilSenha);
    }

    public void logar(View v){


        String login = etLogin.getText().toString();
        String senha = tilSenha.getEditText().getText().toString();

        Log.i("login", login);
        Log.i("senha", senha);

        Usuario usuario = loginDao.logar(login, senha);
        Log.i("usuario","como ta: "+usuario);
        if(usuario != null){
            if(usuario.getTipoUsuario().getId() == 2){
                Intent it = new Intent(this, ListDiaristasActivity.class);
                startActivity(it);
            }else {
                Toast.makeText(this, "Login Correto!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Login ou senha incorretos!", Toast.LENGTH_SHORT).show();
        }
    }

    public void irParaEscolhaCadastro(View v) {
        Intent it = new Intent(this, EscolhaCadastroActivity.class);
        startActivity(it);
    }
}
