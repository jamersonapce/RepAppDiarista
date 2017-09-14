package com.appdiarista.util;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;

/**
 * Created by FR£D on 08/08/2017.
 */

public class DataSelecionada implements DatePickerDialog.OnDateSetListener {
    private EditText etData;

    public DataSelecionada(EditText etData){
        this.etData = etData;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.etData.setText(dayOfMonth+"/"+(month+1)+"/"+year);
    }
}
