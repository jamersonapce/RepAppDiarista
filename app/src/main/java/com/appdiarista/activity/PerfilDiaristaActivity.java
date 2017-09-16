package com.appdiarista.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.appdiarista.appdiarista.R;

import java.util.ArrayList;
import java.util.List;

public class PerfilDiaristaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_diarista);

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
            fragments.add(new FrDetalhesDiaristaActivity());
            fragments.add(new FrAgendaDiaristaActivity());

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
    }
}
