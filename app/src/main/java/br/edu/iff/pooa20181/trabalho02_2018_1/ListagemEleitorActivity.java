package br.edu.iff.pooa20181.trabalho02_2018_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import br.edu.iff.pooa20181.trabalho02_2018_1.adapter.ClickRecyclerViewListener;
import br.edu.iff.pooa20181.trabalho02_2018_1.adapter.EleitorAdapter;
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Eleitor;
import io.realm.Realm;

public class ListagemEleitorActivity extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_eleitor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListagemEleitorActivity.this,EleitorDetalhe.class);
                //intent.putExtra("id",0);
                startActivity(intent);
            }
        });
    }

    protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lista_eleitor);

        recyclerView.setAdapter(new EleitorAdapter(getEventos(),this,this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public List<Eleitor> getEventos(){

        return (List) realm.where(Eleitor.class).findAll();

    }

    @Override
    public void onClick(Object object) {
        Eleitor eleitor = (Eleitor) object;
        Intent intent = new Intent(ListagemEleitorActivity.this,EleitorDetalhe.class);
        intent.putExtra("id",eleitor.getNum_titulo());
        startActivity(intent);
    }

    public void finish(){
        super.finish();
        realm.close();
    }
}
