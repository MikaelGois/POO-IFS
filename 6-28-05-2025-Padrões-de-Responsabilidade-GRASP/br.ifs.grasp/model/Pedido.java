package br.ifs.grasp.model;

import br.ifs.grasp.service.cambio.IConversorMoeda;
import br.ifs.grasp.service.desconto.IEstrategiaDesconto;
import br.ifs.grasp.service.desconto.SemDesconto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Usuario solicitante;
    private List<ItemPedido> itens;
    private String codigoMoedaCorrenteDoPedido;
    private IConversorMoeda conversorMoeda;

    public Pedido(Usuario solicitante, String codigoMoedaCorrenteDoPedido, IConversorMoeda conversorMoeda) {
        this.solicitante = solicitante;
        this.itens = new ArrayList<>();
        this.codigoMoedaCorrenteDoPedido = codigoMoedaCorrenteDoPedido;
        this.conversorMoeda = conversorMoeda;
    }

    public boolean adicionarItem(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0) {
            return false;
        }
        ItemPedido item = new ItemPedido(produto, quantidade);
        return this.itens.add(item);
    }

    public Moeda converterParaMoedaDoPedido(Moeda valor) throws Exception {
        if (this.conversorMoeda == null) {
            throw new IllegalStateException("Conversor de moeda não configurado para o pedido.");
        }
        if (valor.getCodigoISO().equals(this.codigoMoedaCorrenteDoPedido)) {
            return valor;
        }
        return this.conversorMoeda.converter(valor, this.codigoMoedaCorrenteDoPedido);
    }

    public Moeda calcularTotalBrutoNaMoedaDoPedido() throws Exception {
        Moeda totalBruto = new Moeda(BigDecimal.ZERO, this.codigoMoedaCorrenteDoPedido);
        for (ItemPedido item : itens) {
            Moeda subTotalItem = item.calcularSubtotal();
            Moeda subTotalConvertido = converterParaMoedaDoPedido(subTotalItem);
            totalBruto = totalBruto.soma(subTotalConvertido);
        }
        return totalBruto;
    }

    public Moeda aplicarDesconto(Moeda totalBruto, IEstrategiaDesconto estrategia) {
        if (estrategia == null) {
            return new SemDesconto().aplicarDesconto(totalBruto, this);
        }
        if (!totalBruto.getCodigoISO().equals(this.codigoMoedaCorrenteDoPedido)) {
            throw new IllegalArgumentException("Total bruto não está na moeda corrente do pedido para aplicar desconto.");
        }
        return estrategia.aplicarDesconto(totalBruto, this);
    }

    public Moeda calcularTotal(IEstrategiaDesconto estrategia) throws Exception {
        Moeda totalBruto = calcularTotalBrutoNaMoedaDoPedido();
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
    public String getCodigoMoedaCorrenteDoPedido() { return codigoMoedaCorrenteDoPedido; }

    // Setters
    public void setSolicitante(Usuario solicitante) { this.solicitante = solicitante; }

    @Override
    public String toString() {
        // Para exibir o total, precisaríamos de uma estratégia aqui,
        // ou o toString calcularia o total bruto. Vamos manter simples por enquanto.
        return "Pedido{" +
                "solicitante=" + (solicitante != null ? solicitante.getNome() : "null") +
                ", moedaCorrente='" + codigoMoedaCorrenteDoPedido + '\'' +
                ", numeroDeItens=" + itens.size() +
                '}';
    }
}
