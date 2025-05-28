package br.ifs.grasp.model;

import java.util.List;

public class Pedido {
    private Usuario solicitante;
    private List<ItemPedido> itens;

    public Pedido(Usuario solicitante, List<ItemPedido> itens) {
        this.solicitante = solicitante;
        this.itens = itens;
    }

    public boolean adicionarItem(ItemPedido item) {
        if (item == null || itens.contains(item)) {
            return false; // Não adiciona se o item for nulo ou já existir
        }
        itens.add(item);
        return true;
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
