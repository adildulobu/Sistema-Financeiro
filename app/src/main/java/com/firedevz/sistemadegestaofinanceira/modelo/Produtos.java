package com.firedevz.sistemadegestaofinanceira.modelo;

import android.widget.ListView;

/**
 * Created by PUDENTE on 2/1/2018.
 */

public class Produtos {

    String nome,dataEntrada,prazo,categoria,unidade;
    int Quantidade,id,estoqueMinimo;
    float preco,precoCompra;

    public Produtos() {
    }

    public Produtos(int id,String nome, String dataEntrada, String categoria, String prazo, float precoCompra, float preco, int quantidade, String unidade, int estoqueMinimo) {
        this.nome = nome;
        this.dataEntrada = dataEntrada;
        this.prazo = prazo;
        this.categoria = categoria;
        this.unidade = unidade;
        Quantidade = quantidade;
        this.id = id;
        this.estoqueMinimo = estoqueMinimo;
        this.preco = preco;
        this.precoCompra = precoCompra;
    }

    public Produtos(String nome, String dataEntrada, String categoria, String prazo, float precoCompra, float preco, int quantidade, String unidade, int estoqueMinimo) {
        this.nome = nome;
        this.dataEntrada = dataEntrada;
        this.prazo = prazo;
        this.categoria = categoria;
        this.unidade = unidade;
        Quantidade = quantidade;
        this.estoqueMinimo = estoqueMinimo;
        this.preco = preco;
        this.precoCompra = precoCompra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int quantidade) {
        Quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(float precoCompra) {
        this.precoCompra = precoCompra;
    }

    @Override
    public String toString() {
        return "Produtos{" +
                "nome='" + nome + '\'' +
                ", dataEntrada='" + dataEntrada + '\'' +
                ", prazo='" + prazo + '\'' +
                ", categoria='" + categoria + '\'' +
                ", unidade='" + unidade + '\'' +
                ", Quantidade=" + Quantidade +
                ", id=" + id +
                ", estoqueMinimo=" + estoqueMinimo +
                ", preco=" + preco +
                ", precoCompra=" + precoCompra +
                '}';
    }
}
