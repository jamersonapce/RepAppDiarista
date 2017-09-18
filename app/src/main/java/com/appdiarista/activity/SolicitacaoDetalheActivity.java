package com.appdiarista.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.appdiarista.appdiarista.R;
import com.appdiarista.dao.ContratanteDao;
import com.appdiarista.dao.ContratoDao;
import com.appdiarista.dao.DiaristaDao;
import com.appdiarista.model.Contratante;
import com.appdiarista.model.Diarista;
import com.appdiarista.util.BuscaEndereco;

public class SolicitacaoDetalheActivity extends AppCompatActivity {

    private TextView tvNome;
    private TextView tvEndereco;
    private TextView tvSobreMim;
    private Contratante ct;
    private Diarista diarista;
    String loginDiarista;
    private Button btAceitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao_detalhe);
        Bundle b = getIntent().getExtras();
        int idContratante = b.getInt("idContratante");
        loginDiarista = b.getString("loginDiarista");
        ct = new ContratanteDao(this).buscaContratante(idContratante);
        diarista = new DiaristaDao(this).buscaDiarista(loginDiarista);
        boolean aceito = new ContratoDao(this).ehContratoAceito(diarista.getId(), idContratante);

        tvNome = (TextView) findViewById(R.id.tvNome);
        tvEndereco = (TextView) findViewById(R.id.tvEnderecoresposta);
        tvSobreMim = (TextView) findViewById(R.id.tvSobreMim);

        btAceitar = (Button) findViewById(R.id.btAceitarSol);
        if(aceito)
            btAceitar.setVisibility(View.INVISIBLE);
        tvNome.setText(ct.getNome());
        tvSobreMim.setText(ct.getSobreMim());
        new BuscaEndereco(tvEndereco).execute( String.valueOf(ct.getLatitude()), String.valueOf(ct.getLongitude()) );
    }

    public void aceitar(View v){
        new ContratoDao(this).aceitaContrato(String.valueOf(ct.getId()), String.valueOf(diarista.getId()));
        Intent intent = new Intent(this, ListaContratanteActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("loginDiarista", loginDiarista);
        intent.putExtras(bundle);
        startActivity(intent);
        Toast.makeText(this,"Solicitação de diária aceita com sucesso",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try
        {
            MenuItem menuFormatar = menu.add(0, 0, 0, "Sair");
        }
        catch (Exception e) {

        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent it = new Intent(this,LoginActivity.class);
        startActivity(it);
        return true;
    }
}
