package br.ifs.grasp.service.desconto;

import br.ifs.grasp.model.Moeda;
import br.ifs.grasp.model.Pedido;

public interface IEstrategiaDesconto {
    Moeda aplicarDesconto(Moeda totalBruto, Pedido pedido);
}