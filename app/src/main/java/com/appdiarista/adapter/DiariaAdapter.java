package com.appdiarista.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.appdiarista.appdiarista.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FR£D on 15/09/2017.
 */

public class DiariaAdapter extends BaseAdapter{
    private Context ctx;
    private List<String> diarias;
    private double valor;
    private TextView tv;
    private List<String> diariasSel;
    String diaria;

    public DiariaAdapter(Context ctx, List<String> diarias, double valor, TextView tv) {
        this.ctx = ctx;
        this.diarias = diarias;
        this.valor = valor;
        this.tv = tv;
        diariasSel = new ArrayList<String>();
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

    public List<String> diariasSelec(){
        return diariasSel;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        diaria = diarias.get(position);
        View v = LayoutInflater.from(ctx).inflate(R.layout.list_diarias,parent,false);
        TextView tvData = (TextView) v.findViewById(R.id.tvData);
        tvData.setText(diaria);
        Switch ch = (Switch) v.findViewById(R.id.chkData);
        ch.setTag(diaria);
//        ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Log.i("msg",diaria);
//                if(isChecked){
//                    diariasSel.add(diaria);
//                }
//
//            }
//        });
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Switch ch = (Switch) v;
                Log.i("msg","tag data: "+ch.getTag());
                if(ch.isChecked()){
                    diariasSel.add((String) ch.getTag());
                    calculaTotal();
                }else{
                    diariasSel.remove((String) ch.getTag());
                    calculaTotal();
                }
            }
            public void calculaTotal(){
                double total = (valor*diariasSel.size());
                String totalStr = new BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN).toString();
                tv.setText("Total: R$ "+totalStr.replace(",","").replace(".",","));
//                tv.setText("Total: R$ "+String.valueOf(totalStr).replace(",","").replace(".",","));
            }
        });
        return v;
    }

//    @Override
//    public void onClick(View v) {
//        Log.i("msg",diaria);
//        if(ch.isChecked()){
//            diariasSel.add(diaria);
//        }
//    }
}
