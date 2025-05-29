package br.ifs.grasp.model;

import br.ifs.grasp.service.desconto.IEstrategiaDesconto;
import br.ifs.grasp.service.desconto.SemDesconto;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Usuario solicitante;
    private List<ItemPedido> itens;

    public Pedido(Usuario solicitante) {
        this.solicitante = solicitante;
        this.itens = new ArrayList<>();
    }

    public boolean adicionarItem(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0) {
            return false; // Produto ou quantidade inválida
        }
        ItemPedido item = new ItemPedido(produto, quantidade);
        return this.itens.add(item);
    }

    public double calcularTotalBruto() {
        double totalBruto = 0.0;
        for (ItemPedido item : itens) {
            totalBruto += item.calcularSubtotal();
        }
        return totalBruto;
    }

    public double aplicarDesconto(double totalBruto, IEstrategiaDesconto estrategia) {
        if (estrategia == null) {
            return new SemDesconto().aplicarDesconto(totalBruto, this);
        }
        return estrategia.aplicarDesconto(totalBruto, this);
    }

    public double calcularTotal(IEstrategiaDesconto estrategia) {
        double totalBruto = calcularTotalBruto();
        return aplicarDesconto(totalBruto, estrategia);
    }

    public boolean finalizarPedido() {
        if (this.itens.isEmpty()) {
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
        // Para exibir o total, precisaríamos de uma estratégia aqui,
        // ou o toString calcularia o total bruto. Vamos manter simples por enquanto.
        return "Pedido{" +
                "solicitante=" + (solicitante != null ? solicitante.getNome() : "null") +
                ", numeroDeItens=" + itens.size() +
                '}';
    }
}
