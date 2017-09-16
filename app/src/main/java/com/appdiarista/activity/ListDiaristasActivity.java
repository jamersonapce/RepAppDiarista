package com.appdiarista.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
        ListView lv = (ListView) findViewById(R.id.listDiaristasCadastradas);
        List<Diarista> diaristas = new DiaristaDao(this).listAll();
        DiaristaAdapter adapter = new DiaristaAdapter(this, diaristas);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(ListDiaristasActivity.this, PerfilDiaristaActivity.class);
                Bundle bd = new Bundle();
                Long idDr = id;
                bd.putInt("idDiarista", Integer.valueOf(idDr.toString()));
                intent.putExtras(bd);
                startActivity(intent);
            }
        });
    }
}
