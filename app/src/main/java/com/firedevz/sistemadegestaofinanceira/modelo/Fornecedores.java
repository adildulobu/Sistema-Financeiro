package com.firedevz.sistemadegestaofinanceira.modelo;

/**
 * Created by PUDENTE on 2/1/2018.
 */

public class Fornecedores {
    String nome,id,contacto,localizacao;


    public Fornecedores() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "Fornecedores{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
