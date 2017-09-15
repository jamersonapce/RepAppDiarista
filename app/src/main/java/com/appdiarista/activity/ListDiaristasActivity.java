package com.appdiarista.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.appdiarista.adapter.DiaristaAdapter;
import com.appdiarista.appdiarista.R;
import com.appdiarista.dao.DiaristaDao;
import com.appdiarista.model.Diarista;

import java.util.List;

public class ListDiaristasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_diaristas);
        Log.i("msg","entrou na activity lista dia");
        ListView lv = (ListView) findViewById(R.id.listDiaristasCadastradas);
        Log.i("msg","depois de findViewById listDiaristasCadastradas");
        List<Diarista> diaristas = new DiaristaDao(this).listAll();
        DiaristaAdapter adapter = new DiaristaAdapter(this, diaristas);
        Log.i("msg","depois de chamar adapter diarista");
        lv.setAdapter(adapter);

    }
}
