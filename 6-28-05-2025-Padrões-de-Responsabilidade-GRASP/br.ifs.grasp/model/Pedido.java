package br.ifs.grasp.model;

import java.util.List;

public class Pedido {
    private Usuario solicitante;
    private List<ItemPedido> itens;

    public Pedido(Usuario solicitante) {
        this.solicitante = solicitante;
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

    public boolean finalizarPedido() {
        if (itens.isEmpty()) {
            System.out.println("Pedido vazio.");
            return false;
        }
        System.out.println("Pedido finalizado.");
        return true;
    }

    // Getters
    public Usuario getSolicitante() { return solicitante; }
    public List<ItemPedido> getItens() { return itens; }

    // Setters
    public void setSolicitante(Usuario solicitante) { this.solicitante = solicitante; }

    @Override
    public String toString() {
        return "Pedido{" +
                "solicitante=" + (solicitante != null ? solicitante.getNome() : "null") +
                ", numeroDeItens=" + itens.size() +
                ", totalParcial=" + calcularTotal() +
                '}';
    }
}
