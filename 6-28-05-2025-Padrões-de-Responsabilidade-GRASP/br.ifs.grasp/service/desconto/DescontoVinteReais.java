package br.ifs.grasp.service.desconto;

import br.ifs.grasp.model.Moeda;
import br.ifs.grasp.model.Pedido;

import java.math.BigDecimal;

public class DescontoVinteReais implements IEstrategiaDesconto {
    private static final BigDecimal VALOR_DESCONTO_FIXO = new BigDecimal("20.00");

    @Override
    public Moeda aplicarDesconto(Moeda totalBruto, Pedido pedido) {
        System.out.println("Aplicando desconto fixo de R$ " + String.format("%.2f", VALOR_DESCONTO_FIXO));
        BigDecimal totalComDesconto = totalBruto.getValor().subtract(VALOR_DESCONTO_FIXO);
        return new Moeda(totalComDesconto, totalBruto.getCodigoISO());
    }
}
