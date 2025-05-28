package br.ifs.grasp.service.desconto;

import br.ifs.grasp.model.Pedido;

public class DescontoBlackFriday implements IEstrategiaDesconto {
    private static final double PERCETUAL_DESCONTO = 0.20;

    @Override
    public double aplicarDesconto(double totalBruto, Pedido pedido) {
        System.out.println("Aplicando desconto de Black Friday de " + (PERCETUAL_DESCONTO * 100) + "%");
        return totalBruto * PERCETUAL_DESCONTO;
    }
}
