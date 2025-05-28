package br.ifs.grasp.model;

import java.util.List;

public class Pedido {
    private Usuario solicitante;
    private List<ItemPedido> itens;

    public Pedido(Usuario solicitante, List<ItemPedido> itens) {
        this.solicitante = solicitante;
        this.itens = itens;
    }

    public boolean adicionarItem(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0) {
            return false; // Produto ou quantidade inválida
        }
        ItemPedido item = new ItemPedido(produto, quantidade);
        return itens.add(item);
    }

    public double calcularTotal() {
        double total = 0.0;
        for (ItemPedido item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    // Getters
    public Usuario getSolicitante() { return solicitante; }
    public List<ItemPedido> getItens() { return itens; }

    // Setters
    public void setSolicitante(Usuario solicitante) { this.solicitante = solicitante; }
    public void setItens(List<ItemPedido> itens) { this.itens = itens; }
}
