package br.ifs.grasp.service;

import br.ifs.grasp.model.Pedido;
import br.ifs.grasp.model.Relatorio;

public interface IRelatorio {
    Relatorio gerarRelatorio(Pedido pedido);
}
