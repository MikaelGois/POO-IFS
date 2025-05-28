package br.ifs.grasp.repository;

import br.ifs.grasp.model.Pedido;

public interface IRepositorio {
    boolean salvarPedido(Pedido pedido);
}
