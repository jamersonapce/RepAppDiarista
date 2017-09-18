package com.appdiarista.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.appdiarista.appdiarista.R;
import com.appdiarista.dao.ContratanteDao;
import com.appdiarista.dao.DiaristaDao;
import com.appdiarista.model.Diarista;

import java.util.ArrayList;
import java.util.List;

public class PerfilDiaristaActivity extends AppCompatActivity {
    private String sobreMim;
    private String endereço;
    private String valor;
    private Double latitude;
    private Double longitude;
    Diarista diarista;
    int idContratante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_diarista);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int idDiarista = extras.getInt("idDiarista");
        String email = extras.getString("email");
        Log.i("msg", "email perfil: "+email);
        idContratante = new ContratanteDao(this).buscaContratante(email);
        diarista = new DiaristaDao(this).buscaDiarista(idDiarista);
        if(diarista != null){
            sobreMim = diarista.getSobreMim();
            valor = String.valueOf(diarista.getValorDiaria()).replace(",","").replace(".",",");
            latitude = diarista.getLatitude();
            longitude = diarista.getLongitude();
        }
        ViewPager vp = (ViewPager) findViewById(R.id.vpPerfilDiarista);
        vp.setAdapter(new MyAdapter(getSupportFragmentManager()));

        TabLayout tl = (TabLayout) findViewById(R.id.tabLtPerfilDiarista);
        tl.setupWithViewPager(vp);
    }

    private class MyAdapter extends FragmentPagerAdapter{
        private final List<Fragment> fragments;
        private final List<String> titulos;
        public MyAdapter(FragmentManager fm) {
            super(fm);
            fragments = new ArrayList<Fragment>();
            Bundle b = new Bundle();
            b.putString("sobreMim",sobreMim);
            b.putString("valor",valor);
            b.putString("latitude",String.valueOf(latitude));
            b.putString("longitude",String.valueOf(longitude));
            b.putString("valor",valor);
            FrDetalhesDiaristaActivity frDetalhes = new FrDetalhesDiaristaActivity();
            frDetalhes.setArguments(b);
            fragments.add(frDetalhes);
            FrAgendaDiaristaActivity frAgendaDiaristaActivity = new FrAgendaDiaristaActivity();
            Bundle b2 = new Bundle();
            b2.putDouble("valor",diarista.getValorDiaria());
            b2.putInt("idContratante",idContratante);
            b2.putInt("idDiarista",diarista.getId());
            frAgendaDiaristaActivity.setArguments(b2);
            fragments.add(frAgendaDiaristaActivity);

            titulos = new ArrayList<String>();
            titulos.add("Detalhes");
            titulos.add("Agenda");
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titulos.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try
        {
            MenuItem menuFormatar = menu.add(0, 0, 0, "Sair");
        }
        catch (Exception e) {

        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent it = new Intent(this,LoginActivity.class);
        startActivity(it);
        return true;
    }
}
