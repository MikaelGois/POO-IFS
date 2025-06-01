package br.ifs.grasp.service.desconto;

import br.ifs.grasp.model.Moeda;
import br.ifs.grasp.model.Pedido;

public class SemDesconto implements IEstrategiaDesconto {
    @Override
    public Moeda aplicarDesconto(Moeda totalBruto, Pedido pedido) {
        System.out.println("Nenhum desconto aplicado");
        return totalBruto;
    }
}
