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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.iff.pooa20181.trabalho02_2018_1.model.Eleitor;
import io.realm.Realm;

public class EleitorDetalhe extends AppCompatActivity {

    EditText edtNome, edtNomeMae, edtMunicipio, edtNumTitulo, edtDtNasc, edtZona, edtSecao;

    Button btnSalvar, btnAlterar, btnExcluir;

    //id passado da pagina
    int id;
    Eleitor eleitor;
    private Realm realm;

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleitor_detalhe);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtNomeMae = (EditText) findViewById(R.id.edtNomeMae);
        edtMunicipio = (EditText) findViewById(R.id.edtMunicipio);
        edtNumTitulo = (EditText) findViewById(R.id.edtNumTitulo);
        edtDtNasc = (EditText) findViewById(R.id.edtDtNascimento);
        edtZona = (EditText) findViewById(R.id.edtZona);
        edtSecao = (EditText) findViewById(R.id.edtSecao);

        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnAlterar = (Button) findViewById(R.id.btnAlterar);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);

        //id recebido da página de listagem de eleitor
        Intent intent    = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        if(id != 0)
        {
            //desativa o botão de salvar
            btnSalvar.setEnabled(false);
            btnSalvar.setClickable(false);
            btnSalvar.setVisibility(View.INVISIBLE);

            //pega o primeiro eleitor com o id passado entre as views
            eleitor = realm.where(Eleitor.class).equalTo("id",id).findFirst();

            edtNome.setText(eleitor.getNome());
            edtNomeMae.setText(eleitor.getNome_da_mae());
            edtNumTitulo.setText(eleitor.getNum_titulo());
            edtDtNasc.setText(formato.format((Date) eleitor.getDt_nascimento()));
            edtSecao.setText(eleitor.getSecao());
            edtZona.setText(eleitor.getZona());
            edtMunicipio.setText(eleitor.getMunicipio());
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

    //método para deletar eleitor
    public void deletar(){
        realm.beginTransaction();
        eleitor.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Eleitor deletado com sucesso",Toast.LENGTH_LONG).show();
        this.finish();

    }

    public void salvar() {


        int proximoID = 1;
        if(realm.where(Eleitor.class).max("id") !=null)
            proximoID = realm.where(Eleitor.class).max("id").intValue()+1;

        realm.beginTransaction();
        Eleitor eleitor = new Eleitor();
        eleitor.setId(proximoID);
        setEGrava(eleitor);

        realm.copyToRealm(eleitor);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Eleitor cadastrado com successo",Toast.LENGTH_LONG).show();
        this.finish();

    }

    private void setEGrava(Eleitor eleitor){

        eleitor.setNome(edtNome.getText().toString());
        eleitor.setNome_da_mae(edtNomeMae.getText().toString());
        eleitor.setNum_titulo(edtNumTitulo.getText().toString());
        eleitor.setSecao(edtSecao.getText().toString());
        eleitor.setZona(edtZona.getText().toString());
        eleitor.setMunicipio(edtMunicipio.getText().toString());

        try {
            eleitor.setDt_nascimento((Date) formato.parse(edtDtNasc.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void alterar() {

        realm.beginTransaction();

        setEGrava(eleitor);

        realm.copyToRealm(eleitor);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Eleitor alterado com sucesso",Toast.LENGTH_LONG).show();
        this.finish();

    }
}
