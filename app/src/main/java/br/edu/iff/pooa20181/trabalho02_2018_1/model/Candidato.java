package br.edu.iff.pooa20181.trabalho02_2018_1.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Candidato extends RealmObject implements Serializable
{
    private String nome;
    private String partido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey
    private int id;
    private String num_na_urna;

    private String cargo;
    private String num_votos;
    private String uf;
    private String municipio;

    public Candidato(String nome, String partido, String num_na_urna, String cargo, String num_votos, String uf, String municipio) {
        this.nome = nome;
        this.partido = partido;
        this.num_na_urna = num_na_urna;
        this.cargo = cargo;
        this.num_votos = num_votos;
        this.uf = uf;
        this.municipio = municipio;
    }

    public Candidato() {
    }

    public Candidato(int id, String nome, String num_urna, String cargo)
    {
        this.id = id;
        this.nome = nome;
        this.num_na_urna = num_urna;
        this.cargo = cargo;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public String getNum_na_urna() {
        return num_na_urna;
    }

    public void setNum_na_urna(String num_na_urna) {
        this.num_na_urna = num_na_urna;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNum_votos() {
        return num_votos;
    }

    public void setNum_votos(String num_votos) {
        this.num_votos = num_votos;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
