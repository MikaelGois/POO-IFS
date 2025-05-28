package br.ifs.grasp.controller;

import br.ifs.grasp.model.Pedido;
import br.ifs.grasp.model.Produto;
import br.ifs.grasp.model.Relatorio;
import br.ifs.grasp.model.Usuario;
import br.ifs.grasp.repository.Repositorio;
import br.ifs.grasp.service.ServicoNotificacao;
import br.ifs.grasp.service.ServicoPagamento;
import br.ifs.grasp.service.ServicoRelatorio;


public class ControladorPedido {
    private ServicoPagamento servicoPagamento;
    private Repositorio repositorio;
    private ServicoRelatorio servicoRelatorio;
    private ServicoNotificacao servicoNotificacao;

    public ControladorPedido() {
        this.servicoPagamento = new ServicoPagamento();
        this.repositorio = new Repositorio();
        this.servicoRelatorio = new ServicoRelatorio();
        this.servicoNotificacao = new ServicoNotificacao();
    }

    public Pedido iniciarPedido(Usuario solicitante) {
        if (solicitante == null) {
            System.out.println("Erro: Solicitante não pode ser nulo.");
            return null;
        }
        System.out.println("Iniciando novo pedido para " + solicitante.getNome());
        return new Pedido(solicitante);
    }

    public boolean adicionarItemAoPedido(Pedido pedido, Produto produto, int quantidade) {
        if (pedido == null || produto == null || quantidade <= 0) {
            System.out.println("Erro: Dados inválidos para adicionar item ao pedido.");
            return false;
        }

        System.out.println("Controlador: Adicionando produto " + produto.getNome() + " (qtd: " + quantidade + ") ao pedido de " + pedido.getSolicitante().getNome());
        return pedido.adicionarItem(produto, quantidade);
    }

    public double calcularTotalPedido(Pedido pedido, String cupomDesconto) {
        if (pedido == null) {
            System.out.println("Erro: Pedido nulo para calcular total.");
            return 0.0;
        }
        double total = pedido.calcularTotal();
        if (cupomDesconto != null && !cupomDesconto.isEmpty()) {
            System.out.println("Controlador: Aplicando desconto (lógica a ser implementada com Polymorphism/Strategy) para o cupom: " + cupomDesconto);
            // Exemplo muito simples:
            if ("DESCONTO10".equals(cupomDesconto) && total > 0) {
                total *= 0.9; // 10% de desconto
            }
        }
        return total;
    }

    public boolean finalizarPedido(Pedido pedido, String cupomDesconto) {
        if (pedido == null || pedido.getItens().isEmpty()) {
            System.out.println("Erro: Pedido nulo ou vazio, não pode ser finalizado.");
            return false;
        }

        System.out.println("\nControlador: Iniciando finalização do pedido para " + pedido.getSolicitante().getNome());

        double totalFinal = calcularTotalPedido(pedido, cupomDesconto);
        System.out.println("Controlador: Total final calculado: R$ " + String.format("%.2f", totalFinal));

        boolean pagamentoOk = this.servicoPagamento.processarPagamento(totalFinal);
        if (!pagamentoOk) {
            System.out.println("Controlador: Falha ao processar pagamento. Pedido não finalizado.");
            return false;
        }
        System.out.println("Controlador: Pagamento processado com sucesso.");

        boolean salvamentoOk = this.repositorio.salvarPedido(pedido);
        if (!salvamentoOk) {
            System.out.println("Erro: Falha ao salvar o pedido após pagamento. Registrar para verificação.");

        } else {
            System.out.println("Pedido salvo com sucesso.");
        }

        Relatorio relatorio = this.servicoRelatorio.gerarRelatorio(pedido);
        System.out.println("Relatório gerado.");

        this.servicoNotificacao.enviarNotificacao(relatorio);
        System.out.println("Notificação enviada.");

        System.out.println("Pedido finalizado com sucesso!");
        return true;
    }
}
