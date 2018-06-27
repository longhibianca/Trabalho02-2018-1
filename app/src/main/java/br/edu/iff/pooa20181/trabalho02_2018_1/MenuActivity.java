package br.edu.iff.pooa20181.trabalho02_2018_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MenuActivity extends AppCompatActivity {

    RadioGroup grupo_selecao;
    RadioButton op_eleitor;
    RadioButton op_candidato;
    RadioButton opcao;
    Button btn_ir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        grupo_selecao = (RadioGroup) findViewById(R.id.grupo_selecao);
        btn_ir = (Button) findViewById(R.id.btn_opcao);
        op_eleitor = (RadioButton) findViewById(R.id.op_eleitor);

        btn_ir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = grupo_selecao.getCheckedRadioButtonId();
                opcao = (RadioButton) findViewById(selectedId);
                if(opcao.getText().toString().equals("Eleitor"))
                {
                    Intent intent = new Intent(MenuActivity.this,ListagemEleitorActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
