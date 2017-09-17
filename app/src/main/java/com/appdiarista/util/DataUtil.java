package com.appdiarista.util;

import java.util.Calendar;

/**
 * Created by FR£D on 17/09/2017.
 */

public class DataUtil {

    public static String dataHoje(){
        Calendar data = Calendar.getInstance();
        int dia = data.get(Calendar.DAY_OF_MONTH);
        int mes = data.get(Calendar.MONTH)+1;
        int ano = data.get(Calendar.YEAR);
        String diaStr = String.valueOf(dia);
        String mesStr = String.valueOf(mes);
        if(mes < 10)
            mesStr = "0"+mes;
        if( dia < 10)
            diaStr = "0"+dia;
        return diaStr + "/" + mesStr + "/" + ano;
    }
}
