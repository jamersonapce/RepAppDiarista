package com.appdiarista.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.appdiarista.adapter.DiariaAdapter;
import com.appdiarista.appdiarista.R;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FrAgendaDiaristaActivity extends Fragment  {

    private List<String> datas;
    public FrAgendaDiaristaActivity() {
        GregorianCalendar dataGc = new GregorianCalendar();
        int dia = dataGc.get(Calendar.DAY_OF_MONTH);
        int qtdDias = dataGc.getActualMaximum(Calendar.DAY_OF_MONTH);
        int mes = dataGc.get(Calendar.MONTH)+1;
        int ano = dataGc.get(Calendar.YEAR);
        datas = new ArrayList<String>();
        String diaStr;
        String mesStr = ""+mes;
        if(mes < 10)
            mesStr = "0"+mes;
        for (int i = (dia-1); i < qtdDias; i++){
            diaStr = String.valueOf(i+1);
            if((i+1) < 10)
                diaStr = "0"+String.valueOf(i+1);
            datas.add(diaStr+"/"+ mesStr +"/"+ ano);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vf = inflater.inflate(R.layout.fragment_fr_agenda_diarista, container, false);
        // Define o arquivo /layout/main.xml como layout principal da aplicação

        // ListView
        ListView lvDatas = (ListView) vf.findViewById(R.id.lvDatas);


        lvDatas.setAdapter(new DiariaAdapter(vf.getContext(),datas));
        // Inflate the layout for this fragment

        FloatingActionButton fab = (FloatingActionButton) vf.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("msg", "agendou lllllllllllllll");
            }
        });
        return vf;
    }

//    @Override
//    public void onClick(View v) {
//        Log.i("msg", "agendou lllllllllllllll");
//    }
}
