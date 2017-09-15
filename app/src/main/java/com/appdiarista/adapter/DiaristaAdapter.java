package com.appdiarista.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appdiarista.appdiarista.R;
import com.appdiarista.model.Diarista;

import java.util.List;

/**
 * Created by FR£D on 15/09/2017.
 */

public class DiaristaAdapter extends BaseAdapter {
    private Context ctx;
    private List<Diarista> diaristas;

    public DiaristaAdapter(Context ctx, List<Diarista> diaristas) {
        this.ctx = ctx;
        this.diaristas = diaristas;
    }

    @Override
    public int getCount() {
        return diaristas.size();
    }

    @Override
    public Object getItem(int position) {
        return diaristas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return diaristas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Diarista diarista = diaristas.get(position);
        View v = LayoutInflater.from(ctx).inflate(R.layout.list_diaristas,parent,false);
        ImageView iv = (ImageView) v.findViewById(R.id.imgLogo);
        iv.setImageResource(R.drawable.common_signin_btn_icon_dark);
        TextView tvNome = (TextView) v.findViewById(R.id.txtNome);
        tvNome.setText(diarista.getNome());
        TextView tvSobreMim = (TextView) v.findViewById(R.id.txtSobreMim);
        tvSobreMim.setText(diarista.getSobreMim());
        TextView tvValor = (TextView) v.findViewById(R.id.txtValor);
        String valor = String.valueOf(diarista.getValorDiaria());
        String valorCv = valor.replace(",", "").replace(".", ",");
        tvValor.setText("R$ " + valorCv);
        return v;
    }
}
