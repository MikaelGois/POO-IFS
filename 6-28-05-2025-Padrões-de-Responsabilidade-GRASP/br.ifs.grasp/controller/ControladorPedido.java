package br.ifs.grasp.controller;

import br.ifs.grasp.model.Pedido;
import br.ifs.grasp.model.Produto;
import br.ifs.grasp.model.Relatorio;
import br.ifs.grasp.model.Usuario;
import br.ifs.grasp.repository.IRepositorio;
import br.ifs.grasp.service.INotificacao;
import br.ifs.grasp.service.IPagamento;
import br.ifs.grasp.service.IRelatorio;
import br.ifs.grasp.service.desconto.IEstrategiaDesconto;
import br.ifs.grasp.service.desconto.SemDesconto;
import br.ifs.grasp.service.validation.IValidacao;
import br.ifs.grasp.exception.ValidacaoException;

public class ControladorPedido {
    private IPagamento servicoPagamento;
    private IRepositorio repositorio;
    private IRelatorio servicoRelatorio;
    private INotificacao servicoNotificacao;
    private IValidacao servicoValidacao;

    public ControladorPedido(IPagamento servicoPagamento, IRepositorio repositorio,
                             IRelatorio servicoRelatorio, INotificacao servicoNotificacao,
                             IValidacao servicoValidacao) {
        this.servicoPagamento = servicoPagamento;
        this.repositorio = repositorio;
        this.servicoRelatorio = servicoRelatorio;
        this.servicoNotificacao = servicoNotificacao;
        this.servicoValidacao = servicoValidacao;
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

        try {
            this.servicoValidacao.validarProduto(produto);
            this.servicoValidacao.validarEstoque(produto, quantidade);

            System.out.println("Adicionando produto " + produto.getNome() + " (qtd: " + quantidade + ") ao pedido de " + pedido.getSolicitante().getNome());
            return pedido.adicionarItem(produto, quantidade);

        } catch (ValidacaoException e) {
            System.err.println("Falha ao tentar adicionar item - " + e.getMessage());
            return false;
        }
    }

    public double calcularTotalPedido(Pedido pedido, IEstrategiaDesconto estrategiaDesconto) {
        if (pedido == null) {
            System.out.println("Erro: Pedido nulo para calcular total.");
            return 0.0;
        }
        IEstrategiaDesconto estrategia = (estrategiaDesconto != null) ? estrategiaDesconto : new SemDesconto();
        System.out.println("Calculando total do pedido com estrategia " + estrategia.getClass().getSimpleName());
        return pedido.calcularTotal(estrategia);
    }

    public boolean finalizarPedido(Pedido pedido, IEstrategiaDesconto estrategiaDesconto) {
        if (pedido == null || pedido.getItens().isEmpty()) {
            System.out.println("Erro: Pedido nulo ou vazio, não pode ser finalizado.");
            return false;
        }

        System.out.println("\nIniciando finalização do pedido para " + pedido.getSolicitante().getNome());

        double totalFinal = calcularTotalPedido(pedido, estrategiaDesconto);
        System.out.println("Total final calculado: R$ " + String.format("%.2f", totalFinal));

        boolean pagamentoOk = servicoPagamento.processarPagamento(totalFinal);
        if (!pagamentoOk) {
            System.out.println("Controlador: Falha ao processar pagamento. Pedido não finalizado.");
            return false;
        }
        System.out.println("Controlador: Pagamento processado com sucesso.");

        boolean salvamentoOk = repositorio.salvarPedido(pedido);
        if (!salvamentoOk) {
            System.out.println("Erro: Falha ao salvar o pedido após pagamento. Registrar para verificação.");

        } else {
            System.out.println("Pedido salvo com sucesso.");
        }

        Relatorio relatorio = servicoRelatorio.gerarRelatorio(pedido);
        System.out.println("Relatório gerado.");

        servicoNotificacao.enviarNotificacao(relatorio);

        System.out.println("Pedido finalizado com sucesso!");
        return true;
    }
}
