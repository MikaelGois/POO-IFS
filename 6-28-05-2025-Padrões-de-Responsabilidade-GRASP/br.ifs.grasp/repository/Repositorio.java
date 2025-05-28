package br.ifs.grasp.repository;

import br.ifs.grasp.model.Pedido;

public class Repositorio implements IRepositorio {
    @Override
    public boolean salvarPedido(Pedido pedido) {
        if (pedido == null || pedido.getItens().isEmpty()) {
            System.out.println("Erro: Pedido inválido.");
            return false;
        }
        System.out.println("Pedido salvo com sucesso: " + pedido);
        return true;
    }
}
