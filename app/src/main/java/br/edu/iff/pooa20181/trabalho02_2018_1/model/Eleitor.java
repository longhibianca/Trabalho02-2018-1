package br.edu.iff.pooa20181.trabalho02_2018_1.model;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Eleitor extends RealmObject implements Serializable
{
    private String nome;
    private String nome_da_mae;
    private Date dt_nascimento;
    private String zona;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey
    private int id;
    private String num_titulo;

    private String secao;
    private String municipio;

    public Eleitor(int id, String nome, String num_titulo, String zona, String secao)
    {
        this.id = id;
        this.nome = nome;
        this.num_titulo = num_titulo;
        this.zona = zona;
        this.secao = secao;
    }

    public Eleitor(int id,String nome, String nome_da_mae, Date dt_nascimento, String zona, String num_titulo, String secao, String municipio) {
        this.id = id;
        this.nome = nome;
        this.nome_da_mae = nome_da_mae;
        this.dt_nascimento = dt_nascimento;
        this.zona = zona;
        this.num_titulo = num_titulo;
        this.secao = secao;
        this.municipio = municipio;
    }

    public Eleitor() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome_da_mae() {
        return nome_da_mae;
    }

    public void setNome_da_mae(String nome_da_mae) {
        this.nome_da_mae = nome_da_mae;
    }

    public Date getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(Date dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getNum_titulo() {
        return num_titulo;
    }

    public void setNum_titulo(String num_titulo) {
        this.num_titulo = num_titulo;
    }

    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
