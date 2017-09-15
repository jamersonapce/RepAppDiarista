package com.appdiarista.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.appdiarista.appdiarista.R;
import com.appdiarista.dao.DiaristaDao;
import com.appdiarista.model.Diarista;
import com.appdiarista.util.DataSelecionada;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class CadDiaristaActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
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
    private GoogleApiClient mGoogleApiClient;
    private TextView tvEnderecoresposta;
    private ImageButton ibEndereco;

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
        etValorDiaria = (EditText) findViewById(R.id.etValorDiaria);
        etSobreMim = (EditText) findViewById(R.id.etSobreMim);
        tvEnderecoresposta = (TextView) findViewById(R.id.tvEnderecoresposta);
        ibEndereco = (ImageButton) findViewById(R.id.ibEndereco);

        callConnection();
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
        return new Diarista(nome, telefone, nascimento, cpf, email, senha, sobreMim, valorDiaria, latitude, longitude);
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

    private synchronized void callConnection(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location l = LocationServices
                .FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        if(l != null){
            Log.i("LOG", "latitude: "+l.getLatitude());
            Log.i("LOG", "longitude: "+l.getLongitude());
            longitude = l.getLongitude();
            latitude = l.getLatitude();
        }else{
            Log.i("LOG", "coordenadas nulas");
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("LOG", "onConnectionSuspended(" + i + ")");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("LOG", "onConnectionFailed("+connectionResult+")");
    }

    public void buscarEndereco(View v){
        ibEndereco.setVisibility(View.GONE);
        new BuscaEndereco().execute(String.valueOf(latitude),String.valueOf(longitude));
    }

    private class BuscaEndereco extends AsyncTask<String, Integer,String> {

        @Override
        protected String doInBackground(String... params) {
            String latitude = params[0];
            String longitude = params[1];

            String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng=";
            url = url + latitude + "," + longitude;
            //Irá guardar o resultado da requisição do servico (um conteudo em JSON)
            String resultado = null;
            //Irá guardar o endereco obtido a partir da requisição
            String endereco = null;

            try {
                //Cria uma url de servico para que possamos usar a requisicao
                URL urlservico = new URL(url);
                //Abre uma conexao
                HttpURLConnection conexao = (HttpURLConnection) urlservico.openConnection();
                //Define qual o verbo HTTP da requisicao
                conexao.setRequestMethod("GET");
                //Dispara a chamada
                conexao.connect();
                //Verifica qual o retorno desta chamada
                if(conexao.getResponseCode()==HttpURLConnection.HTTP_OK){
                    //Deu tudo certo e o retorno da chamada foi 200
                    //StringBuilder ideal para concatenações
                    //Cada mudança de conteúdo de uma String, um novo objeto é criado
                    StringBuilder sb = new StringBuilder();
                    //InputStreamReader leitura em caracter a caracter ou byte a byte
                    //BufferedReader leitura de um bloco, fazendo menos chamadas ao Sistema Operacional
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
                    //Realiza a leitura de uma linha do buffer (bloco) e atribui a uma String
                    String linha = buffer.readLine();
                    while (linha != null){
                        //Concatena com o objeto StringBuilder
                        sb.append(linha);
                        linha = buffer.readLine();
                    }
                    resultado = sb.toString();
                    //Encerra a comunicação com o serviço
                    conexao.disconnect();
                }
                else {
                    resultado = "ERRO";
                }

            }
            catch (Exception e){

            }
            if (resultado != null){
                        //Resultado veio com alguma informação
                        //Definimos um objeto jresp do tipo Objeto JSON
                        JSONObject jresp = null;

                        try {
                            //Criacao de um objeto JSON
                            //Toda a resposta da requisição vira um objeto JSON
                            jresp = new JSONObject(resultado);
                            //Verificamos se o valor da propriedade status é OK
                            //Se for, indica que a requisição foi realizada com sucesso
                            if (jresp.optString("status").equals("OK")){
                                //Buscamos então entre os objetos dentro do jresp um array chamado results
                                JSONArray arrayderesults = jresp.getJSONArray("results");
                                for (int i=0; i < arrayderesults.length();i++){
                                    //Pegamos um item dentro do array
                                    JSONObject objJSON = arrayderesults.getJSONObject(i);
                                    //Deste objeto pegamos um atraibuto chamado formatted_address
                                    endereco = objJSON.getString("formatted_address");
                                    if (endereco != null)
                                        //Ao encontrarmos um valor, saimos do laço
                                        break;
                                }
                            }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return endereco;

        }

        @Override
        protected void onPostExecute(String s) {
            tvEnderecoresposta.setText(s);
            Log.i("msg","endereço: "+s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
}
