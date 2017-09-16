package com.appdiarista.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appdiarista.appdiarista.R;
import com.appdiarista.util.BuscaEndereco;

/**
 * A simple {@link Fragment} subclass.
 */
public class FrDetalhesDiaristaActivity extends Fragment {


    private String sobreMim;
    private String valor;

    public FrDetalhesDiaristaActivity() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            Log.i("msg",bundle.getString("sobreMim"));
            Log.i("msg",bundle.getString("valor"));
        }
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fr_detalhes_diarista, container, false);
        TextView tvSobreMim = (TextView) v.findViewById(R.id.tvSobreMim);
        tvSobreMim.setText(bundle.getString("sobreMim"));
        TextView tvValor = (TextView) v.findViewById(R.id.tvDetalhes_valor);
        tvValor.setText("R$ "+bundle.getString("valor"));
        TextView tvEndereco = (TextView) v.findViewById(R.id.tvDetalhes_endereco);
        new BuscaEndereco(tvEndereco).execute(bundle.getString("latitude"),bundle.getString("longitude"));
        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
