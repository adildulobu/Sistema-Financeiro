package com.firedevz.sistemadegestaofinanceira.modelo;

public class Venda {

    int idVenda,idProduto,idCliente;
    float quantidade,desconto,precoTotal;
    String tipoVenda;

    public Venda() {
    }

    public Venda(int idVenda, int idProduto, float quantidade, String tipoVenda, float precoTotal, float desconto, int idCliente) {
        this.idVenda = idVenda;
        this.idProduto = idProduto;
        this.idCliente = idCliente;
        this.quantidade = quantidade;
        this.desconto = desconto;
        this.precoTotal = precoTotal;
        this.tipoVenda = tipoVenda;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public float getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(float precoTotal) {
        this.precoTotal = precoTotal;
    }

    public String getTipoVenda() {
        return tipoVenda;
    }

    public void setTipoVenda(String tipoVenda) {
        this.tipoVenda = tipoVenda;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "idVenda=" + idVenda +
                ", idProduto=" + idProduto +
                ", idCliente=" + idCliente +
                ", quantidade=" + quantidade +
                ", desconto=" + desconto +
                ", precoTotal=" + precoTotal +
                ", tipoVenda='" + tipoVenda + '\'' +
                '}';
    }
}
