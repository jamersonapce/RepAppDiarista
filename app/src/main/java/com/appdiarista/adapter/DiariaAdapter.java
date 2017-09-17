package com.appdiarista.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.appdiarista.appdiarista.R;

import java.util.List;

/**
 * Created by FR£D on 15/09/2017.
 */

public class DiariaAdapter extends BaseAdapter {
    private Context ctx;
    private List<String> diarias;

    public DiariaAdapter(Context ctx, List<String> diarias) {
        this.ctx = ctx;
        this.diarias = diarias;
    }

    @Override
    public int getCount() {
        return diarias.size();
    }

    @Override
    public Object getItem(int position) {
        return diarias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String diaria = diarias.get(position);
        View v = LayoutInflater.from(ctx).inflate(R.layout.list_diarias,parent,false);
        TextView tvData = (TextView) v.findViewById(R.id.tvData);
        tvData.setText(diaria);
        return v;
    }
}
