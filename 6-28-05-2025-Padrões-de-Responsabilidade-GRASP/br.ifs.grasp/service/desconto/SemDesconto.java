package br.ifs.grasp.service.desconto;

import br.ifs.grasp.model.Pedido;

public class SemDesconto implements IEstrategiaDesconto {
    @Override
    public double aplicarDesconto(double totalBruto, Pedido pedido) {
        System.out.println("Nenhum desconto aplicado");
        return totalBruto;
    }
}
