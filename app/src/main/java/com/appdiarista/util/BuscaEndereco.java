package com.appdiarista.util;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BuscaEndereco extends AsyncTask<String, Integer,String> {

        TextView tvEnderecoresposta;

    public BuscaEndereco(TextView tvEnderecoresposta) {
        this.tvEnderecoresposta = tvEnderecoresposta;
    }

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