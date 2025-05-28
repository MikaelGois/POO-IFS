package br.ifs.grasp.model;

public class ItemPedido {
    private Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public double calcularSubtotal() {
        return produto.getPreco() * quantidade;
    }

    // Getters
    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }

    // Setters
    public void setProduto(Produto produto) { this.produto = produto; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}
