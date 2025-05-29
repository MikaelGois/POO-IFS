package br.ifs.grasp.model;

import java.math.BigDecimal;

public class ItemPedido {
    private Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Moeda calcularSubtotal() {
        if (this.produto == null || this.produto.getPreco() == null) {
            return new Moeda(BigDecimal.ZERO, "BRL");
        }
        BigDecimal subtotalValor = this.produto.getPreco().getValor().multiply(BigDecimal.valueOf(this.quantidade));
        return new Moeda(subtotalValor, this.produto.getPreco().getCodigoISO());
    }

    // Getters
    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }

    // Setters
    public void setProduto(Produto produto) { this.produto = produto; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "quantidade=" + quantidade +
                ", produto=" + (produto != null ? produto.getNome() : "null") +
                ", subtotal=" + calcularSubtotal() +
                '}';
    }
}
