package br.ifs.grasp.model;

public class ItemPedido {
    private Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public double calcularSubtotal() {
        if (produto == null || quantidade <= 0) {
            return 0.0; // Retorna 0 se o produto for nulo ou a quantidade for inválida
        }
        return this.produto.getPreco() * this.quantidade;
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
                '}';
    }
}
