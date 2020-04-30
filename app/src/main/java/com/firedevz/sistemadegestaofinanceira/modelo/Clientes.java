package com.firedevz.sistemadegestaofinanceira.modelo;



public class Clientes {

    String nome, telefone, morada,email;
    int Id;


    public Clientes() {

    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String contacto) {
        this.telefone = contacto;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", morada='" + morada + '\'' +
                ", email='" + email + '\'' +
                ", Id=" + Id +
                '}';
    }
}
