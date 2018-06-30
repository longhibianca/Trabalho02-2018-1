package br.edu.iff.pooa20181.trabalho02_2018_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.List;

import br.edu.iff.pooa20181.trabalho02_2018_1.adapter.CandidatoAdapter;
import br.edu.iff.pooa20181.trabalho02_2018_1.adapter.ClickRecyclerViewListener;
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Candidato;
import io.realm.Realm;

public class ListagemCandidatoActivity extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_candidato);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("listagem candidato", "estou no fab");
                Intent intent = new Intent(ListagemCandidatoActivity.this,CandidatoDetalhe.class);
                //id passado para a pagina de salvar,editar e criar candidato
                intent.putExtra("id",0);
                startActivity(intent);
            }
        });
    }

    protected void onResume() {

        Log.i("listagem candidato", "estou no on resume");
        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lista_candidato);

        recyclerView.setAdapter(new CandidatoAdapter(getCandidatos(),this,this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public List<Candidato> getCandidatos(){

        Log.i("listagem candidato", "estou no getCandidatos");
        return (List) realm.where(Candidato.class).findAll();

    }

    @Override
    public void onClick(Object object) {
        Log.i("listagem candidato", "estou no onclick");
        Candidato candidato = (Candidato) object;
        Intent intent = new Intent(ListagemCandidatoActivity.this,CandidatoDetalhe.class);
        intent.putExtra("id",candidato.getId());
        startActivity(intent);
    }

    public void finish(){
        Log.i("listagem candidato", "estou no finish");
        super.finish();
        realm.close();
    }

}
