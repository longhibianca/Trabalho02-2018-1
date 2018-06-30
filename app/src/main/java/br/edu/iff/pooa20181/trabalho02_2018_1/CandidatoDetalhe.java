package br.edu.iff.pooa20181.trabalho02_2018_1;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.iff.pooa20181.trabalho02_2018_1.model.Candidato;
import io.realm.Realm;

public class CandidatoDetalhe extends AppCompatActivity {

    EditText edtNomeCandidato, edtPartido, edtNumUrna, edtCargo, edtNumVotos, edtUf, edtMunicipioCand;

    Button btnSalvar, btnAlterar, btnExcluir;

    //id passado da pagina
    int id;
    Candidato candidato;
    private Realm realm;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidato_detalhe);

        edtNomeCandidato = (EditText) findViewById(R.id.edtNome);
        edtPartido = (EditText) findViewById(R.id.edtPartido);
        edtNumUrna = (EditText) findViewById(R.id.edtNumUrna);
        edtCargo = (EditText) findViewById(R.id.edtCargo);
        edtNumVotos = (EditText) findViewById(R.id.edtNumVotos);
        edtUf = (EditText) findViewById(R.id.edtUf);
        edtMunicipioCand = (EditText) findViewById(R.id.edtMunicipio);

        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnAlterar = (Button) findViewById(R.id.btnAlterar);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);

        //id recebido da página de listagem de candidato
        Intent intent = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        if(id != 0)
        {
            //desativa o botão de salvar
            btnSalvar.setEnabled(false);
            btnSalvar.setClickable(false);
            btnSalvar.setVisibility(View.INVISIBLE);

            btnAlterar.setEnabled(true);
            btnAlterar.setClickable(true);
            btnAlterar.setVisibility(View.VISIBLE);

            //pega o primeiro candidato com o id passado entre as views
            candidato = realm.where(Candidato.class).equalTo("id",id).findFirst();

            edtNomeCandidato.setText(candidato.getNome());
            edtPartido.setText(candidato.getPartido());
            edtNumUrna.setText(candidato.getNum_na_urna());
            edtCargo.setText(candidato.getCargo());
            edtNumVotos.setText(candidato.getNum_votos());
            edtUf.setText(candidato.getUf());
            edtMunicipioCand.setText(candidato.getMunicipio());
        }
        else
        {
            //desativa os botoes de editar e excluir
            btnAlterar.setEnabled(false);
            btnAlterar.setClickable(false);
            btnAlterar.setVisibility(View.INVISIBLE);
            btnExcluir.setEnabled(false);
            btnExcluir.setClickable(false);
            btnExcluir.setVisibility(View.INVISIBLE);
        }

        btnSalvar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                salvar();
            }
        });
        btnAlterar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                alterar();
            }
        });
        btnExcluir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                deletar();
            }
        });
    }

    //método para deletar candidato
    public void deletar(){
        realm.beginTransaction();
        candidato.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Candidato deletado com sucesso",Toast.LENGTH_LONG).show();
        this.finish();

    }

    //método para salvar candidato
    public void salvar() {


        int proximoID = 1;
        if(realm.where(Candidato.class).max("id") !=null)
            proximoID = realm.where(Candidato.class).max("id").intValue()+1;

        realm.beginTransaction();
        Candidato candidato = new Candidato();
        candidato.setId(proximoID);
        setEGrava(candidato);

        realm.copyToRealm(candidato);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Candidato cadastrado com successo",Toast.LENGTH_LONG).show();
        this.finish();

    }

    private void setEGrava(Candidato candidato){

        candidato.setNome(edtNomeCandidato.getText().toString());
        candidato.setPartido(edtPartido.getText().toString());
        candidato.setNum_na_urna(edtNumUrna.getText().toString());
        candidato.setCargo(edtCargo.getText().toString());
        candidato.setNum_votos(edtCargo.getText().toString());
        candidato.setUf(edtUf.getText().toString());
        candidato.setMunicipio(edtMunicipioCand.getText().toString());

    }

    public void alterar() {

        realm.beginTransaction();

        setEGrava(candidato);

        realm.copyToRealm(candidato);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Candidato alterado com sucesso",Toast.LENGTH_LONG).show();
        this.finish();

    }
}
