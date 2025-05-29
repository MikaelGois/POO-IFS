package br.ifs.grasp.controller;

import br.ifs.grasp.model.Moeda;
import br.ifs.grasp.model.Pedido;
import br.ifs.grasp.model.Produto;
import br.ifs.grasp.model.Relatorio;
import br.ifs.grasp.model.Usuario;
import br.ifs.grasp.repository.IRepositorio;
import br.ifs.grasp.service.INotificacao;
import br.ifs.grasp.service.IPagamento;
import br.ifs.grasp.service.IRelatorio;
import br.ifs.grasp.service.cambio.IConversorMoeda;
import br.ifs.grasp.service.desconto.IConfiguracaoDesconto;
import br.ifs.grasp.service.desconto.IEstrategiaDesconto;
import br.ifs.grasp.service.validation.IValidacao;
import br.ifs.grasp.exception.ValidacaoException;

public class ControladorPedido {
    private IPagamento servicoPagamento;
    private IRepositorio repositorio;
    private IRelatorio servicoRelatorio;
    private INotificacao servicoNotificacao;
    private IValidacao servicoValidacao;
    private IConfiguracaoDesconto servicoConfiguracaoDesconto;
    private IConversorMoeda servicoConversorMoeda;

    public ControladorPedido(IPagamento servicoPagamento, IRepositorio repositorio,
                             IRelatorio servicoRelatorio, INotificacao servicoNotificacao,
                             IValidacao servicoValidacao, IConfiguracaoDesconto servicoConfiguracaoDesconto,
                             IConversorMoeda servicoConversorMoeda) {
        this.servicoPagamento = servicoPagamento;
        this.repositorio = repositorio;
        this.servicoRelatorio = servicoRelatorio;
        this.servicoNotificacao = servicoNotificacao;
        this.servicoValidacao = servicoValidacao;
        this.servicoConfiguracaoDesconto = servicoConfiguracaoDesconto;
        this.servicoConversorMoeda = servicoConversorMoeda;
    }

    public Pedido iniciarPedido(Usuario solicitante, String codigoMoedaDoPedido) {
        if (solicitante == null) {
            System.err.println("Erro: Solicitante não pode ser vazio.");
            return null;
        }
        if (codigoMoedaDoPedido == null || codigoMoedaDoPedido.trim().isEmpty()) {
            System.err.println("Erro: Codigo da moeda do pedido não pode ser vazio.");
            return null;
        }
        System.out.println("Iniciando novo pedido para " + solicitante.getNome() + " em " + codigoMoedaDoPedido);
        return new Pedido(solicitante, codigoMoedaDoPedido, this.servicoConversorMoeda);
    }

    public boolean adicionarItemAoPedido(Pedido pedido, Produto produto, int quantidade) {
        if (pedido == null || produto == null || quantidade <= 0) {
            System.err.println("Erro: Dados inválidos para adicionar item ao pedido.");
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

    public Moeda calcularTotalPedido(Pedido pedido, String cupomDesconto) {
        if (pedido == null) {
            System.err.println("Erro: Pedido vazio.");
            return null;
        }
        try {
            IEstrategiaDesconto estrategia = this.servicoConfiguracaoDesconto.getEstrategia(cupomDesconto);
            System.out.println("Calculando total do pedido com cupom '" + cupomDesconto + "', estratégia: " + estrategia.getClass().getSimpleName());
            return pedido.calcularTotal(estrategia);
        } catch (Exception e) {
            System.err.println("Erro ao calcular total do pedido - " + e.getMessage());
            return null;
        }
    }

    public boolean finalizarPedido(Pedido pedido, String cupomDesconto) throws Exception {
        if (pedido == null || pedido.getItens().isEmpty()) {
            System.err.println("Erro: Pedido vazio, não pode ser finalizado.");
            return false;
        }

        System.out.println("\nIniciando finalização do pedido para " + pedido.getSolicitante().getNome() + " em " + pedido.getCodigoMoedaCorrenteDoPedido());

        Moeda totalFinal = calcularTotalPedido(pedido, cupomDesconto);
        if (totalFinal == null) {
            System.err.println("Não foi possível calcular o total final. Pedido não finalizado.");
            return false;
        }
        System.out.println("Total final calculado: R$ " + totalFinal);

        boolean pagamentoOk = this.servicoPagamento.processarPagamento(totalFinal.getValor().doubleValue());
        if (!pagamentoOk) {
            System.err.println("Falha ao processar pagamento. Pedido não finalizado.");
            return false;
        }
        System.out.println("Pagamento processado com sucesso.");

        boolean salvamentoOk = this.repositorio.salvarPedido(pedido);
        if (!salvamentoOk) {
            System.err.println("Erro: Falha ao salvar o pedido após pagamento. Registrar para verificação.");

        } else {
            System.out.println("Pedido salvo com sucesso.");
        }

        Relatorio relatorio = this.servicoRelatorio.gerarRelatorio(pedido);
        System.out.println("Relatório gerado.");

        this.servicoNotificacao.enviarNotificacao(relatorio);
        System.out.println("Pedido finalizado com sucesso!");
        return true;
    }
}
