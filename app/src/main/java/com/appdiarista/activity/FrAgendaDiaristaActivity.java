package com.appdiarista.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.appdiarista.adapter.DiariaAdapter;
import com.appdiarista.appdiarista.R;
import com.appdiarista.dao.ContratoDao;
import com.appdiarista.dao.ContratoDiariaDao;
import com.appdiarista.dao.DiariaDao;
import com.appdiarista.model.Contratante;
import com.appdiarista.model.Contrato;
import com.appdiarista.model.ContratoDiaria;
import com.appdiarista.model.Diaria;
import com.appdiarista.model.Diarista;
import com.appdiarista.util.DataUtil;
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
        Bundle bundle = getArguments();
        View vf = inflater.inflate(R.layout.fragment_fr_agenda_diarista, container, false);
        // Define o arquivo /layout/main.xml como layout principal da aplicação

        // ListView
        ListView lvDatas = (ListView) vf.findViewById(R.id.lvDatas);

        TextView tvValor = (TextView) vf.findViewById(R.id.tvValorTotal);
        double valor = bundle.getDouble("valor");
        final int idContratante = bundle.getInt("idContratante");
        final int idDiarista = bundle.getInt("idDiarista");

        DiariaAdapter adapter = new DiariaAdapter(vf.getContext(), datas, valor, tvValor);
        final List<String> diariasSelecionadas = adapter.diariasSelec();
        lvDatas.setAdapter(adapter);
        // Inflate the layout for this fragment
        FloatingActionButton fab = (FloatingActionButton) vf.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("msg", "acionou botao");
                try {
                    String hoje = DataUtil.dataHoje();
                    Contrato contrato = new Contrato(hoje, hoje, new Diarista(idDiarista), new Contratante(idContratante));
                    int idContrato = new ContratoDao(getActivity()).inserir(contrato);
                    Log.i("msg", "inseriu contrato id: " + idContrato);
                    for (String d: diariasSelecionadas) {
                        Diaria diaria = new Diaria(d);
                        int idDiaria = new DiariaDao(getActivity()).inserir(diaria);
                        Log.i("msg", "inseriu diaria id: " + idDiaria);
                        ContratoDiaria contratoDiaria = new ContratoDiaria(new Contrato(idContrato), new Diaria(idDiaria, null));
                        new ContratoDiariaDao(getActivity()).inserir(contratoDiaria);

                    }
                    Intent it = new Intent(getActivity(),ListDiaristasActivity.class);
                    startActivity(it);
                    Toast.makeText(getActivity(),"Solicitação realizada com sucesso!",Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return vf;
    }

//    @Override
//    public void onClick(View v) {
//        Log.i("msg", "agendou lllllllllllllll");
//    }
}
