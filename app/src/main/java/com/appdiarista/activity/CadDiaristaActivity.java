package com.appdiarista.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appdiarista.appdiarista.R;
import com.appdiarista.dao.DiaristaDao;
import com.appdiarista.model.Diarista;
import com.appdiarista.util.DataSelecionada;

import java.util.Calendar;

public class CadDiaristaActivity extends AppCompatActivity  implements LocationListener {
    private  int dia,mes,ano;
    private EditText etNome;
    private EditText etEmail;
    private EditText etSenha;
    private EditText etNascimento;
    private EditText etCpf;
    private EditText etTelefone;
    private TextView tvLogitude;
    private TextView tvLatitude;
    private EditText etValorDiaria;
    private EditText etSobreMim;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_diarista);

        etNome = (EditText) findViewById(R.id.etNome);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSenha = (EditText) findViewById(R.id.etSenha);
        etNascimento = (EditText) findViewById(R.id.etNascimento);
        etCpf = (EditText) findViewById(R.id.etCpf);
        etTelefone = (EditText) findViewById(R.id.etTelefone);
//        tvLongitude = (TextView) findViewById(R.id.tvLatitude);
//        tvLatitude  = (TextView) findViewById(R.id.tvLatitude);
        etValorDiaria = (EditText) findViewById(R.id.etValorDiaria);
        etSobreMim = (EditText) findViewById(R.id.etSobreMim);


    }
    public void cadastrar(View v){

        Diarista diarista = criaDiaristaView();

        DiaristaDao dao = new DiaristaDao(this);
        try {
            dao.inserir(diarista);
            Intent it = new Intent(this,LoginActivity.class);
            startActivity(it);
            Toast.makeText(this,"Cadastro realizado com sucesso",Toast.LENGTH_LONG).show();
        }
        catch (SQLiteException sqle) {
            Toast.makeText(this,"Erro ao inserir no banco de dados",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,"Ocorreu um erro",Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    public Diarista criaDiaristaView() {
        String nome = etNome.getText().toString();
        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();
        String nascimento = etNascimento.getText().toString();
        String cpf = etCpf.getText().toString();
        String telefone = etTelefone.getText().toString();
        double valorDiaria = Double.parseDouble(etValorDiaria.getText().toString());
        String sobreMim = etSobreMim.getText().toString();
        return new Diarista(nome, telefone, nascimento, cpf, email, senha, sobreMim, valorDiaria, null, null);
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

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        Log.i("msg","Latitude"+latitude + " Longitude: "+longitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
