package br.ifs.grasp.service;

import br.ifs.grasp.model.Pedido;
import br.ifs.grasp.model.Relatorio;

public class ServicoRelatorio implements IRelatorio{
    @Override
    public Relatorio gerarRelatorio(Pedido pedido) throws Exception {
        String dados = "Relatório do Pedido:\nSolicitante: " + pedido.getSolicitante().getNome() +
                "\nTotal: R$ " + String.format("%.2f", pedido.calcularTotalBrutoNaMoedaDoPedido()) +
                "\nNúmero de Itens: " + pedido.getItens().size();
        System.out.println("Gerando relatório para o pedido de: " + pedido.getSolicitante().getNome());

        if (pedido == null || pedido.getItens().isEmpty()) {
            return new Relatorio("Dados do pedido indisponíveis.");
        }

        return new Relatorio(dados);
    }
}
