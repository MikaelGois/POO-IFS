package br.ifs.grasp.service.desconto;

import br.ifs.grasp.model.Pedido;

public class DescontoVinteReais implements IEstrategiaDesconto {
    private static final double VALOR_DESCONTO_FIXO = 20.00;

    @Override
    public double aplicarDesconto(double totalBruto, Pedido pedido) {
        System.out.println("Aplicando desconto fixo de R$ " + String.format("%.2f", VALOR_DESCONTO_FIXO));
        double totalComDesconto = totalBruto - VALOR_DESCONTO_FIXO;
        return totalComDesconto > 0 ? totalComDesconto : 0;
    }
}
