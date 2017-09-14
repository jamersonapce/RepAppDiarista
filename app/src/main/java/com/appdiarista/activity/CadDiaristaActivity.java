package com.appdiarista.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.appdiarista.appdiarista.R;
import com.appdiarista.util.DataSelecionada;

import java.util.Calendar;

public class CadDiaristaActivity extends AppCompatActivity {
    private  int dia,mes,ano;
    private EditText etNascimento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_diarista);

        etNascimento = (EditText) findViewById(R.id.etNascimento);
    }

    private void criarDatePicker(EditText et) {
        final Calendar c= Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        ano=c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                new DataSelecionada(et),ano,mes,dia);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    public void showCalendario(View v){
        criarDatePicker(etNascimento);
    }
}
