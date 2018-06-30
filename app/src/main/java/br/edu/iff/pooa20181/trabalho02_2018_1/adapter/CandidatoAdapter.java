package br.edu.iff.pooa20181.trabalho02_2018_1.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.iff.pooa20181.trabalho02_2018_1.R;
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Candidato;

public class CandidatoAdapter extends RecyclerView.Adapter {

    private List<Candidato> candidatos;
    private Context context;
    private static ClickRecyclerViewListener clickRecyclerViewListener;

    public CandidatoAdapter(List<Candidato> candidatos, Context context,ClickRecyclerViewListener clickRecyclerViewListener) {
        Log.i("candidato adapter", "estou no construtor");
        this.candidatos = candidatos;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.i("listagem candidato", "estou no on create view holder");
        View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_item_candidato, parent, false);
        CandidatoViewHolder candidatoViewHolder = new CandidatoViewHolder(view);
        return candidatoViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Log.i("listagem candidato", "estou no on bind view holder");

        CandidatoViewHolder candidatoHolder = (CandidatoViewHolder) viewHolder;

        Candidato candidato  = this.candidatos.get(position) ;

        //define o que vai mostrar no cardview da lista
        candidatoHolder.nomeCandidato.setText(candidato.getNome());
        candidatoHolder.partido.setText(candidato.getPartido());
        candidatoHolder.num_urna.setText(candidato.getNum_na_urna());
        candidatoHolder.cargo.setText(candidato.getCargo());

    }

    @Override
    public int getItemCount() {
        Log.i("listagem candidato", "estou no get item count");
        return candidatos.size();
    }

    public class CandidatoViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeCandidato;
        private final TextView partido;
        private final TextView num_urna;
        private final TextView cargo;

        public CandidatoViewHolder(View itemView) {

            super(itemView);
            Log.i("listagem candidato", "estou no candidato view holder");
            nomeCandidato = (TextView) itemView.findViewById(R.id.tvNomeCandidato);
            partido = (TextView) itemView.findViewById(R.id.tvPartido);
            num_urna = (TextView) itemView.findViewById(R.id.tvNumUrna);
            cargo = (TextView) itemView.findViewById(R.id.tvCargo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("listagem candidato", "estou no candidato view holder - onclick");
                    clickRecyclerViewListener.onClick(candidatos.get(getLayoutPosition()));

                }
            });


        }
    }
}
