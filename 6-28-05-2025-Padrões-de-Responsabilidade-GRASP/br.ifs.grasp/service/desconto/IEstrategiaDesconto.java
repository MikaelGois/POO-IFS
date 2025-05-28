package br.ifs.grasp.service.desconto;

import br.ifs.grasp.model.Pedido;

public interface IEstrategiaDesconto {
    double aplicarDesconto(double totalBruto, Pedido pedido);
}
