package com.firedevz.sistemadegestaofinanceira.modelo;


public class Rendimento {

    int idDescricao;
    String descricao,tipo,data;
    double valor;

    public Rendimento(int idDescricao, String descricao, double valor, String tipo, String data) {
        this.idDescricao = idDescricao;
        this.descricao = descricao;
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
    }

    public Rendimento(String descricao, double valor, String tipo, String data) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
    }

    public Rendimento() {

    }

    public int getIdDescricao() {
        return idDescricao;
    }

    public void setIdDescricao(int idDescricao) {
        this.idDescricao = idDescricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Rendimento{" +
                "descricao='" + descricao + '\'' +
                ", tipo='" + tipo + '\'' +
                ", valor=" + valor +
                '}';
    }
}
