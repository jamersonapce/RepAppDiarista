package com.appdiarista.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.appdiarista.appdiarista.R;
import com.appdiarista.dao.LoginDao;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void logar(View v){
        LoginDao loginDao = new LoginDao(this);
        loginDao.logar("","");
    }
    public void irCadastrar(View v){
        Toast.makeText(this, "Foi pro cadastrar", Toast.LENGTH_SHORT).show();
    }
}
